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

import com.buession.redis.core.RedisNode;
import io.lettuce.core.api.StatefulRedisConnection;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * Lettuce 连接池
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettucePool extends Pool<StatefulRedisConnection<byte[], byte[]>> {

	private final static Logger logger = LoggerFactory.getLogger(LettucePool.class);

	/**
	 * 构造函数
	 */
	public LettucePool() {
		this(RedisNode.DEFAULT_HOST, RedisNode.DEFAULT_PORT);
	}

	/**
	 * 构造函数
	 *
	 * @param url
	 * 		连接地址
	 */
	public LettucePool(final String url) {
		this(URI.create(url));
	}

	/**
	 * 构造函数
	 *
	 * @param url
	 * 		连接地址
	 * @param lettuceClientConfig
	 * 		客户端配置
	 */
	public LettucePool(final String url, final LettuceClientConfig lettuceClientConfig) {
		this(new GenericObjectPoolConfig<>(), new LettuceFactory(RedisURI.create(url), lettuceClientConfig));
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 */
	public LettucePool(final String host, final int port) {
		this(new LettuceFactory(host, port));
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
	public LettucePool(final String host, final int port, final LettuceClientConfig lettuceClientConfig) {
		this(new LettuceFactory(host, port, lettuceClientConfig));
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 * @param user
	 * 		用户名
	 * @param password
	 * 		密码
	 */
	public LettucePool(final String host, final int port, final String user, final String password) {
		this(new LettuceFactory(host, port, DefaultLettuceClientConfig.builder().credentials(user, password).build()));
	}

	/**
	 * 构造函数
	 *
	 * @param factory
	 * 		线程池对象工厂
	 */
	public LettucePool(final PooledObjectFactory<StatefulRedisConnection<byte[], byte[]>> factory) {
		super(factory);
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		线程池配置
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig) {
		this(poolConfig, RedisNode.DEFAULT_HOST, RedisNode.DEFAULT_PORT);
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		线程池配置
	 * @param url
	 * 		连接地址
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String url) {
		this(poolConfig, URI.create(url));
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		线程池配置
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port) {
		this(poolConfig, new LettuceFactory(host, port, DefaultLettuceClientConfig.builder().build()));
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		线程池配置
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 * @param user
	 * 		用户名
	 * @param password
	 * 		密码
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final String user, final String password) {
		this(poolConfig, new LettuceFactory(host, port,
				DefaultLettuceClientConfig.builder().credentials(user, password).build()));
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		线程池配置
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 * @param lettuceClientConfig
	 * 		客户端配置
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final LettuceClientConfig lettuceClientConfig) {
		this(poolConfig, new LettuceFactory(host, port, lettuceClientConfig));
	}

	/**
	 * 构造函数
	 *
	 * @param uri
	 * 		连接 URL
	 */
	public LettucePool(final URI uri) {
		this(new LettucePoolConfig<>(), uri);
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		线程池配置
	 * @param uri
	 * 		连接 URL
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final URI uri) {
		this(poolConfig, uri, DefaultLettuceClientConfig.builder().build());
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		线程池配置
	 * @param uri
	 * 		连接 URL
	 * @param lettuceClientConfig
	 * 		客户端配置
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig, final URI uri,
					   final LettuceClientConfig lettuceClientConfig) {
		this(poolConfig, new LettuceFactory(RedisURI.create(uri), lettuceClientConfig));
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		线程池配置
	 * @param factory
	 * 		连接线对象工厂
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final PooledObjectFactory<StatefulRedisConnection<byte[], byte[]>> factory) {
		super(factory, poolConfig);
	}

	@Override
	public StatefulRedisConnection<byte[], byte[]> getResource() {
		StatefulRedisConnection<byte[], byte[]> connection = super.getResource();
		return connection;
	}

	@Override
	public void returnResource(final StatefulRedisConnection<byte[], byte[]> resource) {
		if(resource != null){
			try{
				super.returnResource(resource);
			}catch(RuntimeException e){
				super.returnBrokenResource(resource);
				logger.warn("Resource is returned to the pool as broken", e);
			}
		}
	}
	
}
