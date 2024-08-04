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
package io.lettuce.core;

import com.buession.core.validator.Validate;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.codec.ByteArrayCodec;

import java.util.Optional;

/**
 * Lettuce 单机连接池对象工厂
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceFactory extends AbstractLettuceFactory<StatefulRedisConnection<byte[], byte[]>, RedisClient> {

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 */
	public LettuceFactory(final String host, final int port) {
		this(host, port, null);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 * @param lettuceClientConfig
	 * 		客户端配置
	 */
	public LettuceFactory(final String host, final int port, final LettuceClientConfig lettuceClientConfig) {
		this(RedisURI.builder().withHost(host).withPort(port).build(), lettuceClientConfig);
	}

	/**
	 * 构造函数
	 *
	 * @param uri
	 *        {@link RedisURI}
	 */
	public LettuceFactory(final RedisURI uri) {
		super(RedisClient.create(uri));
	}

	/**
	 * 构造函数
	 *
	 * @param uri
	 *        {@link RedisURI}
	 * @param lettuceClientConfig
	 * 		客户端配置
	 */
	public LettuceFactory(final RedisURI uri, final LettuceClientConfig lettuceClientConfig) {
		this(buildRedisURI(uri, lettuceClientConfig));
	}

	@Override
	public StatefulRedisConnection<byte[], byte[]> create() throws Exception {
		return getClient().connect(new ByteArrayCodec());
	}

	private static RedisURI buildRedisURI(final RedisURI uri, final LettuceClientConfig lettuceClientConfig) {
		if(lettuceClientConfig != null){
			if(Validate.hasText(lettuceClientConfig.getPassword())){
				final RedisCredentialsProvider redisCredentialsProvider = Validate.hasText(
						lettuceClientConfig.getPassword()) ? new StaticCredentialsProvider(
						Validate.hasText(lettuceClientConfig.getUser()) ? lettuceClientConfig.getUser() :
								null, lettuceClientConfig.getPassword().toCharArray()) : null;
				uri.setCredentialsProvider(redisCredentialsProvider);
			}

			uri.setDatabase(lettuceClientConfig.getDatabase());
			Optional.ofNullable(lettuceClientConfig.getConnectionTimeout()).ifPresent(uri::setTimeout);
			uri.setClientName(lettuceClientConfig.getClientName());
		}

		return uri;
	}

}
