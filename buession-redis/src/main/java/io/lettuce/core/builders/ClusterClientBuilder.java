/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership.
 * The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 *
 * =========================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +-------------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										       |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package io.lettuce.core.builders;

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.core.validator.Validate;
import com.buession.redis.client.connection.datasource.lettuce.LettuceClusterDataSource;
import io.lettuce.core.internal.HostAndPort;
import io.lettuce.core.providers.ConnectionProvider;

import java.time.Duration;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Builder for creating RedisClient instances (cluster Redis connections).
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class ClusterClientBuilder<K, V, C>
		extends AbstractClientBuilder<K, V, ClusterClientBuilder<K, V, C>, C> {

	private Set<HostAndPort> nodes = null;

	private int maxAttempts = 5;

	private Duration maxTotalRetriesDuration;

	private Duration topologyRefreshPeriod = null;

	protected ClusterClientBuilder() {

	}

	/**
	 * Sets the cluster nodes to connect to.
	 * <p>
	 * At least one node must be specified. The client will discover other nodes in the cluster automatically.
	 * </p>
	 *
	 * @param nodes
	 * 		the set of cluster nodes
	 *
	 * @return this builder
	 */
	public ClusterClientBuilder<K, V, C> nodes(Set<HostAndPort> nodes) {
		this.nodes = nodes;
		return this;
	}

	/**
	 * Sets the maximum number of attempts for cluster operations.
	 * <p>
	 * When a cluster operation fails (e.g., due to node failure or slot migration), the client will retry up
	 * to this many times before giving up.
	 * </p>
	 *
	 * @param maxAttempts
	 * 		the maximum number of attempts (must be positive)
	 *
	 * @return this builder
	 */
	public ClusterClientBuilder<K, V, C> maxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
		return this;
	}

	/**
	 * Sets the maximum total duration for retries across all attempts.
	 * <p>
	 * This provides a time-based limit on retries in addition to the attempt-based limit. If not set,
	 * it will be calculated as socketTimeout * maxAttempts.
	 * </p>
	 *
	 * @param maxTotalRetriesDuration
	 * 		the maximum total retry duration
	 *
	 * @return this builder
	 */
	public ClusterClientBuilder<K, V, C> maxTotalRetriesDuration(Duration maxTotalRetriesDuration) {
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
		return this;
	}

	/**
	 * Sets the topology refresh period for cluster slot mapping updates.
	 * <p>
	 * The client will periodically refresh its view of the cluster topology to handle slot migrations and node changes.
	 * A shorter period provides faster adaptation to cluster changes but increases overhead.
	 * </p>
	 *
	 * @param topologyRefreshPeriod
	 * 		the topology refresh period
	 *
	 * @return this builder
	 */
	public ClusterClientBuilder<K, V, C> topologyRefreshPeriod(Duration topologyRefreshPeriod) {
		this.topologyRefreshPeriod = topologyRefreshPeriod;
		return this;
	}

	@Override
	protected ClusterClientBuilder<K, V, C> self() {
		return this;
	}

	protected C createCle() {
		/*
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();
		final LettuceClusterDataSource dataSource = (LettuceClusterDataSource) getDataSource();
		final Set<RedisURI> redisURIs = createRedisURIs(dataSource, propertyMapper);
		final ClusterClientOptions.Builder clusterClientOptionsBuilder = ClusterClientOptions.builder();
		final RedisClusterClient redisClusterClient = RedisClusterClient.create(redisURIs);

		if(getTopologyRefreshPeriod() != null){
			ClusterTopologyRefreshOptions clusterTopologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
					.adaptiveRefreshTriggersTimeout(getTopologyRefreshPeriod())
					.enablePeriodicRefresh(getTopologyRefreshPeriod())
					.build();
			clusterClientOptionsBuilder.topologyRefreshOptions(clusterTopologyRefreshOptions);

		}
		if(getMaxRedirects() > 0){
			clusterClientOptionsBuilder.maxRedirects(getMaxRedirects());
		}
		if(getMaxTotalRetriesDuration() != null){
		}

		redisClusterClient.setOptions(clusterClientOptionsBuilder.build());

		return redisClusterClient.connect(codec);

		 */
		return null;
	}

	@Override
	protected ConnectionProvider<K, V> createDefaultConnectionProvider() {
		return null;
	}

	@Override
	protected void validateSpecificConfiguration() {

	}

	/*
	private Set<RedisURI> createRedisURIs(final LettuceClusterDataSource dataSource,
	                                      final PropertyMapper propertyMapper) {
		final RedisCredentialsProvider redisCredentialsProvider = Validate.hasText(dataSource.getPassword()) ?
				new StaticCredentialsProvider(Validate.hasText(dataSource.getUsername()) ? dataSource.getUsername() :
											  null, dataSource.getPassword().toCharArray()) : null;
		return dataSource.getNodes().stream().map((node)->{
			int port = node.getPort() == 0 ? com.buession.redis.core.RedisNode.DEFAULT_PORT : node.getPort();
			final RedisURI redisURI = RedisURI.create(node.getHost(), port);

			propertyMapper.from(redisCredentialsProvider).to(redisURI::setCredentialsProvider);
			propertyMapper.from(dataSource.getClientName()).to(redisURI::setClientName);

			if(dataSource.getConnectTimeout() > 0){
				redisURI.setTimeout(Duration.ofMillis(dataSource.getConnectTimeout()));
			}

			redisURI.setSsl(dataSource.getSslConfiguration() != null);

			return redisURI;
		}).collect(Collectors.toSet());
	}
	
	 */

}
