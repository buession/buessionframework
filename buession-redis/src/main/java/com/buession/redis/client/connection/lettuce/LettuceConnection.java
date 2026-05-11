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

import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisStandaloneConnection;
import com.buession.redis.client.connection.datasource.lettuce.LettuceDataSource;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.exception.RedisConnectionFailureException;
import io.lettuce.core.DefaultLettuceClientConfig;
import io.lettuce.core.RedisStandaloneClient;
import io.lettuce.core.builders.StandaloneClientBuilder;
import io.lettuce.core.codec.RedisCodec;

import java.util.Optional;

/**
 * Lettuce 单机模式连接器
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceConnection<K, V> extends AbstractLettuceRedisConnection<K, V, RedisStandaloneClient<K, V>>
		implements RedisStandaloneConnection {

	/**
	 * 构造函数
	 */
	public LettuceConnection() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public LettuceConnection(LettuceDataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 */
	public LettuceConnection(LettuceDataSource dataSource, PoolConfig poolConfig) {
		super(dataSource, poolConfig);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param codec
	 * 		Redis 编解码器
	 */
	public LettuceConnection(LettuceDataSource dataSource, RedisCodec<K, V> codec) {
		super(dataSource, codec);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param codec
	 * 		Redis 编解码器
	 */
	public LettuceConnection(LettuceDataSource dataSource, PoolConfig poolConfig, RedisCodec<K, V> codec) {
		super(dataSource, poolConfig, codec);
	}

	@Override
	protected void internalInit() {
		super.internalInit();
		if(client == null){
			final LettuceDataSource dataSource = (LettuceDataSource) getDataSource();
			final DefaultLettuceClientConfig.Builder clientConfigBuilder = DefaultLettuceClientConfig.builder();

			commonClientConfigBuilder(clientConfigBuilder);

			if(dataSource.getDatabase() > 0){
				clientConfigBuilder.database(dataSource.getDatabase());
			}

			final StandaloneClientBuilder<K, V, RedisStandaloneClient<K, V>> builder = RedisStandaloneClient.<K, V>builder()
					.hostAndPort(dataSource.getHost(), dataSource.getPort()).codec(getCodec())
					.clientConfig(clientConfigBuilder.build());

			Optional.ofNullable(getConnectionPoolConfig()).ifPresent(builder::poolConfig);
			//Optional.ofNullable(getCacheConfig()).ifPresent(builder::cacheConfig);

			client = builder.build();
		}
	}

	@Override
	protected Status doConnect() throws RedisConnectionFailureException {
		if(isConnected()){
			return Status.SUCCESS;
		}

		return client == null ? Status.FAILURE : Status.SUCCESS;
	}

}
