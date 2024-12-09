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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection.jedis;

import com.buession.redis.client.connection.AbstractConnectionFactory;
import com.buession.redis.client.connection.RedisClusterConnection;
import com.buession.redis.client.connection.RedisSentinelConnection;
import com.buession.redis.client.connection.RedisStandaloneConnection;
import com.buession.redis.client.connection.datasource.jedis.JedisClusterDataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisRedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisSentinelDataSource;
import com.buession.redis.exception.RedisConnectionFailureException;

/**
 * Jedis Redis 连接工厂
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisConnectionFactory extends AbstractConnectionFactory<JedisRedisDataSource> {

	public JedisConnectionFactory(final JedisRedisDataSource dataSource) {
		super(dataSource);
	}

	@Override
	public RedisStandaloneConnection getStandaloneConnection() {
		final JedisDataSource dataSource = (JedisDataSource) getDataSource();

		if(dataSource.getPoolConfig() == null){
			return new JedisConnection(dataSource, dataSource.getConnectTimeout(), dataSource.getSoTimeout(),
					dataSource.getInfiniteSoTimeout(), dataSource.getSslConfiguration());
		}else{
			return new JedisConnection(dataSource, dataSource.getPoolConfig(), dataSource.getConnectTimeout(),
					dataSource.getSoTimeout(), dataSource.getInfiniteSoTimeout(), dataSource.getSslConfiguration());
		}
	}

	@Override
	public RedisSentinelConnection getSentinelConnection() {
		if(isRedisSentinelAware()){
			final JedisSentinelDataSource dataSource = (JedisSentinelDataSource) getDataSource();

			if(dataSource.getPoolConfig() == null){
				return new JedisSentinelConnection(dataSource, dataSource.getConnectTimeout(),
						dataSource.getSoTimeout(), dataSource.getInfiniteSoTimeout(),
						dataSource.getSentinelConnectTimeout(), dataSource.getSentinelSoTimeout(),
						dataSource.getSslConfiguration());
			}else{
				return new JedisSentinelConnection(dataSource, dataSource.getPoolConfig(),
						dataSource.getConnectTimeout(), dataSource.getSoTimeout(), dataSource.getInfiniteSoTimeout(),
						dataSource.getSentinelConnectTimeout(), dataSource.getSentinelSoTimeout(),
						dataSource.getSslConfiguration());
			}
		}

		throw new RedisConnectionFailureException("No Sentinels datasource");
	}

	@Override
	public RedisClusterConnection getClusterConnection() {
		if(isRedisClusterAware()){
			final JedisClusterDataSource dataSource = (JedisClusterDataSource) getDataSource();

			if(dataSource.getPoolConfig() == null){
				return new JedisClusterConnection(dataSource, dataSource.getConnectTimeout(),
						dataSource.getSoTimeout(), dataSource.getInfiniteSoTimeout(), dataSource.getMaxRedirects(),
						dataSource.getMaxTotalRetriesDuration(), dataSource.getSslConfiguration());
			}else{
				return new JedisClusterConnection(dataSource, dataSource.getPoolConfig(),
						dataSource.getConnectTimeout(), dataSource.getSoTimeout(), dataSource.getInfiniteSoTimeout(),
						dataSource.getMaxRedirects(), dataSource.getMaxTotalRetriesDuration(),
						dataSource.getSslConfiguration());
			}
		}

		throw new RedisConnectionFailureException("No Cluster datasource");
	}

}
