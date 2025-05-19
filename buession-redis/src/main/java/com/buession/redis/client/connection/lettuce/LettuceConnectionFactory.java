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
package com.buession.redis.client.connection.lettuce;

import com.buession.redis.client.connection.AbstractConnectionFactory;
import com.buession.redis.client.connection.RedisClusterConnection;
import com.buession.redis.client.connection.RedisSentinelConnection;
import com.buession.redis.client.connection.RedisStandaloneConnection;
import com.buession.redis.client.connection.datasource.jedis.JedisSentinelDataSource;
import com.buession.redis.client.connection.datasource.lettuce.LettuceClusterDataSource;
import com.buession.redis.client.connection.datasource.lettuce.LettuceDataSource;
import com.buession.redis.client.connection.datasource.lettuce.LettuceRedisDataSource;
import com.buession.redis.client.connection.jedis.JedisSentinelConnection;

/**
 * Lettuce Redis 连接工厂
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceConnectionFactory extends AbstractConnectionFactory<LettuceRedisDataSource> {

	public LettuceConnectionFactory(final LettuceRedisDataSource dataSource) {
		super(dataSource);
	}

	@Override
	public RedisStandaloneConnection getStandaloneConnection() {
		final LettuceDataSource dataSource = (LettuceDataSource) getDataSource();

		if(dataSource.getPoolConfig() == null){
			return new LettuceConnection(dataSource, dataSource.getConnectTimeout(), dataSource.getSoTimeout(),
					dataSource.getInfiniteSoTimeout(), dataSource.getSslConfiguration());
		}else{
			return new LettuceConnection(dataSource, dataSource.getPoolConfig(), dataSource.getConnectTimeout(),
					dataSource.getSoTimeout(), dataSource.getInfiniteSoTimeout(), dataSource.getSslConfiguration());
		}
	}

	@Override
	public RedisSentinelConnection getSentinelConnection() {
		final JedisSentinelDataSource dataSource = (JedisSentinelDataSource) getDataSource();

		if(dataSource.getPoolConfig() == null){
			return new JedisSentinelConnection(dataSource, dataSource.getConnectTimeout(), dataSource.getSoTimeout(),
					dataSource.getInfiniteSoTimeout(), dataSource.getSentinelConnectTimeout(),
					dataSource.getSentinelSoTimeout(), dataSource.getSslConfiguration());
		}else{
			return new JedisSentinelConnection(dataSource, dataSource.getPoolConfig(), dataSource.getConnectTimeout(),
					dataSource.getSoTimeout(), dataSource.getInfiniteSoTimeout(),
					dataSource.getSentinelConnectTimeout(), dataSource.getSentinelSoTimeout(),
					dataSource.getSslConfiguration());
		}
	}

	@Override
	public RedisClusterConnection getClusterConnection() {
		final LettuceClusterDataSource dataSource = (LettuceClusterDataSource) getDataSource();

		if(dataSource.getPoolConfig() == null){
			return new LettuceClusterConnection(dataSource, dataSource.getConnectTimeout(), dataSource.getSoTimeout(),
					dataSource.getInfiniteSoTimeout(), dataSource.getMaxRedirects(),
					dataSource.getMaxTotalRetriesDuration(), dataSource.getSslConfiguration());
		}else{
			return new LettuceClusterConnection(dataSource, dataSource.getPoolConfig(),
					dataSource.getConnectTimeout(), dataSource.getSoTimeout(), dataSource.getInfiniteSoTimeout(),
					dataSource.getMaxRedirects(), dataSource.getMaxTotalRetriesDuration(),
					dataSource.getSslConfiguration());
		}
	}

}
