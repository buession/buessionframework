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

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.DefaultLettuceClientConfig;
import io.lettuce.core.LettuceClientConfig;
import io.lettuce.core.internal.HostAndPort;
import io.lettuce.core.providers.ConnectionProvider;
import io.lettuce.core.resource.Delay;

import java.time.Duration;
import java.util.Set;

/**
 * Builder for creating RedisClient instances (standalone Redis connections).
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 * @param <C>
 * 		the client type that this builder creates
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class SentinelClientBuilder<K, V, C> extends AbstractClientBuilder<K, V, SentinelClientBuilder<K, V,
		C>, C> {

	private String masterName = null;

	private Set<HostAndPort> sentinels = null;

	private LettuceClientConfig sentinelClientConfig = null;

	private Delay sentinelReconnectDelay = Delay.constant(Duration.ofMillis(5000L));

	protected SentinelClientBuilder() {

	}

	/**
	 * Sets the master name for the Redis Sentinel configuration.
	 * <p>
	 * This is the name of the Redis master as configured in the Sentinel instances. The Sentinel will
	 * monitor this master and provide failover capabilities.
	 *
	 * @param masterName
	 * 		the master name (must not be null or empty)
	 *
	 * @return this builder
	 */
	public SentinelClientBuilder<K, V, C> masterName(String masterName) {
		this.masterName = masterName;
		return this;
	}

	/**
	 * Sets the master name for the Redis Sentinel configuration.
	 * <p>
	 * This is the name of the Redis master as configured in the Sentinel instances. The Sentinel will
	 * monitor this master and provide failover capabilities.
	 *
	 * @param masterName
	 * 		the master name (must not be null or empty)
	 *
	 * @return this builder
	 */
	public SentinelClientBuilder<K, V, C> masterName(byte[] masterName) {
		this.masterName = SafeEncoder.encode(masterName);
		return this;
	}

	/**
	 * Sets the sentinel nodes to connect to.
	 * <p>
	 * At least one sentinel must be specified. The client will use these sentinels to discover the
	 * current master and monitor for failover events.
	 *
	 * @param sentinels
	 * 		the set of sentinel nodes
	 *
	 * @return this builder
	 */
	public SentinelClientBuilder<K, V, C> sentinels(Set<HostAndPort> sentinels) {
		this.sentinels = sentinels;
		return this;
	}

	/**
	 * Sets the client configuration for Sentinel connections.
	 * <p>
	 * This configuration is used for connections to the Sentinel instances. It may have different
	 * authentication credentials and settings than the master connections.
	 *
	 * @param sentinelClientConfig
	 * 		the client configuration for sentinel connections
	 *
	 * @return this builder
	 */
	public SentinelClientBuilder<K, V, C> sentinelClientConfig(LettuceClientConfig sentinelClientConfig) {
		this.sentinelClientConfig = sentinelClientConfig;
		return this;
	}

	/**
	 * Sets the delay between re-subscribing to sentinel node after a disconnection.
	 * <p>
	 * In case connection to sentinel nodes is lost, the client will try to reconnect to them. This
	 * method sets the delay between re-subscribing to sentinel nodes after a disconnection.
	 * </p>
	 *
	 * @param reconnectDelay
	 * 		the delay between re-subscribing to sentinel nodes after a disconnection
	 *
	 * @return this builder
	 */
	public SentinelClientBuilder<K, V, C> sentinelReconnectDelay(Delay reconnectDelay) {
		Assert.isNull(reconnectDelay, "reconnectDelay must not be null");
		this.sentinelReconnectDelay = reconnectDelay;
		return this;
	}

	@Override
	protected SentinelClientBuilder<K, V, C> self() {
		return this;
	}

	@Override
	protected ConnectionProvider<K, V> createDefaultConnectionProvider() {
		return null;
		/*
		return new SentineledConnectionProvider(this.masterName, this.clientConfig, this.cache,
				this.poolConfig, this.sentinels, this.sentinelClientConfig, sentinelReconnectDelay);

		 */
	}

	@Override
	protected void validateSpecificConfiguration() {
		validateCommonConfiguration();

		if(masterName == null || masterName.trim().isEmpty()){
			throw new IllegalArgumentException("Master name is required for Sentinel mode");
		}

		if(Validate.isEmpty(sentinels)){
			throw new IllegalArgumentException("At least one sentinel must be specified for Sentinel mode");
		}
	}

	@Override
	public C build() {
		if(sentinelClientConfig == null){
			sentinelClientConfig = DefaultLettuceClientConfig.builder().build();
		}

		return super.build();
	}

}
