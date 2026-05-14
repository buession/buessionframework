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

import com.buession.core.validator.Validate;
import com.buession.redis.client.connection.datasource.ClusterDataSource;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.internal.HostAndPort;
import io.lettuce.core.providers.ClusterConnectionProvider;
import io.lettuce.core.providers.ConnectionProvider;

import java.time.Duration;
import java.util.Set;

/**
 * Builder for creating RedisClient instances (cluster Redis connections).
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class ClusterClientBuilder<K, V, C>
		extends AbstractClientBuilder<K, V, ClusterClientBuilder<K, V, C>, C> {

	private Set<HostAndPort> nodes = null;

	private int maxRedirects = ClusterDataSource.DEFAULT_MAX_ATTEMPTS;

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
	 * 设置最大尝试次数
	 *
	 * @param maxRedirects
	 * 		最大尝试次数
	 *
	 * @return this builder
	 */
	public ClusterClientBuilder<K, V, C> maxRedirects(int maxRedirects) {
		this.maxRedirects = maxRedirects;
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

	@Override
	protected ConnectionProvider<K, V> createDefaultConnectionProvider() {
		ClusterTopologyRefreshOptions.Builder topologyRefreshOptionsBuilder =
				ClusterTopologyRefreshOptions.builder();

		if(topologyRefreshPeriod != null){
			topologyRefreshOptionsBuilder.enablePeriodicRefresh(topologyRefreshPeriod);
		}

		if(maxTotalRetriesDuration != null){
			topologyRefreshOptionsBuilder.adaptiveRefreshTriggersTimeout(maxTotalRetriesDuration);
		}

		topologyRefreshOptionsBuilder.refreshTriggersReconnectAttempts(maxRedirects).closeStaleConnections(true);

		if(this.maxRedirects > 0){
			return new ClusterConnectionProvider<>(this.nodes, this.clientConfig, this.poolConfig,
					topologyRefreshOptionsBuilder.build(), this.maxRedirects, this.codec);
		}else{
			return new ClusterConnectionProvider<>(this.nodes, this.clientConfig, this.poolConfig,
					topologyRefreshOptionsBuilder.build(), this.codec);
		}
	}

	@Override
	protected void validateSpecificConfiguration() {
		validateCommonConfiguration();

		if(Validate.isEmpty(nodes)){
			throw new IllegalArgumentException("At least one cluster node must be specified for cluster mode");
		}

		if(maxRedirects <= 0){
			throw new IllegalArgumentException("Max attempts must be positive for cluster mode");
		}

		if(maxTotalRetriesDuration != null && maxTotalRetriesDuration.isNegative()){
			throw new IllegalArgumentException("Max total retries duration cannot be negative for cluster mode");
		}

		if(topologyRefreshPeriod != null && topologyRefreshPeriod.isNegative()){
			throw new IllegalArgumentException("Topology refresh period cannot be negative for cluster mode");
		}
	}

}
