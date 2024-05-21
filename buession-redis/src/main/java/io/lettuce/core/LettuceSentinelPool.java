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

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
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
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public LettucePool(final String url, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier) {
		this(new GenericObjectPoolConfig<>(), new LettuceFactory(RedisURI.create(url), (int) RedisURI.DEFAULT_TIMEOUT,
				(int) RedisURI.DEFAULT_TIMEOUT, null, sslSocketFactory, sslParameters, hostnameVerifier));
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
	 * @param ssl
	 * 		是否为 SSL
	 */
	public LettucePool(final String host, final int port, final boolean ssl) {
		this(new LettuceFactory(host, port, ssl));
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 * @param ssl
	 * 		是否为 SSL
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public LettucePool(final String host, final int port, final boolean ssl, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		this(new LettuceFactory(host, port, ssl, sslSocketFactory, sslParameters, hostnameVerifier));
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
		this(new LettuceFactory(host, port, user, password));
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
		this(poolConfig, host, port, (int) RedisURI.DEFAULT_TIMEOUT);
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
	 * @param ssl
	 * 		是否为 SSL
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final boolean ssl) {
		this(poolConfig, host, port, (int) RedisURI.DEFAULT_TIMEOUT, ssl);
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
	 * @param ssl
	 * 		是否为 SSL
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final boolean ssl, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		this(poolConfig, host, port, (int) RedisURI.DEFAULT_TIMEOUT, ssl, sslSocketFactory, sslParameters,
				hostnameVerifier);
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
		this(poolConfig, host, port, (int) RedisURI.DEFAULT_TIMEOUT, user, password, RedisNode.DEFAULT_DATABASE);
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
	 * @param timeout
	 * 		超时
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout) {
		this(poolConfig, host, port, timeout, null);
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
	 * @param timeout
	 * 		超时
	 * @param ssl
	 * 		是否为 SSL
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout, final boolean ssl) {
		this(poolConfig, host, port, timeout, null, ssl);
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
	 * @param timeout
	 * 		超时
	 * @param ssl
	 * 		是否为 SSL
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout, final boolean ssl,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier) {
		this(poolConfig, host, port, timeout, null, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
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
	 * @param timeout
	 * 		超时
	 * @param password
	 * 		密码
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout, final String password) {
		this(poolConfig, host, port, timeout, password, RedisNode.DEFAULT_DATABASE);
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
	 * @param timeout
	 * 		超时
	 * @param password
	 * 		密码
	 * @param ssl
	 * 		是否为 SSL
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout, final String password, final boolean ssl) {
		this(poolConfig, host, port, timeout, password, RedisNode.DEFAULT_DATABASE, ssl);
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
	 * @param timeout
	 * 		超时
	 * @param password
	 * 		密码
	 * @param ssl
	 * 		是否为 SSL
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout, final String password, final boolean ssl,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier) {
		this(poolConfig, host, port, timeout, password, RedisNode.DEFAULT_DATABASE, ssl, sslSocketFactory,
				sslParameters, hostnameVerifier);
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
	 * @param timeout
	 * 		超时
	 * @param user
	 * 		用户名
	 * @param password
	 * 		密码
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout, final String user, final String password) {
		this(poolConfig, host, port, timeout, user, password, RedisNode.DEFAULT_DATABASE);
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
	 * @param timeout
	 * 		超时
	 * @param user
	 * 		用户名
	 * @param password
	 * 		密码
	 * @param ssl
	 * 		是否为 SSL
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout, final String user, final String password,
					   final boolean ssl) {
		this(poolConfig, host, port, timeout, user, password, RedisNode.DEFAULT_DATABASE, ssl);
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
	 * @param timeout
	 * 		超时
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout, final String password,
					   final int database) {
		this(poolConfig, host, port, timeout, password, database, null);
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
	 * @param timeout
	 * 		超时
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param ssl
	 * 		是否为 SSL
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout, final String password, final int database,
					   final boolean ssl) {
		this(poolConfig, host, port, timeout, password, database, null, ssl);
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
	 * @param timeout
	 * 		超时
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param ssl
	 * 		是否为 SSL
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout, final String password,
					   final int database, final boolean ssl, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		this(poolConfig, host, port, timeout, password, database, null, ssl, sslSocketFactory,
				sslParameters, hostnameVerifier);
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
	 * @param timeout
	 * 		超时
	 * @param user
	 * 		用户名
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout, final String user, final String password,
					   final int database) {
		this(poolConfig, host, port, timeout, user, password, database, null);
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
	 * @param timeout
	 * 		超时
	 * @param user
	 * 		用户名
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param ssl
	 * 		是否为 SSL
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout, final String user, final String password,
					   final int database, final boolean ssl) {
		this(poolConfig, host, port, timeout, user, password, database, null, ssl);
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
	 * @param timeout
	 * 		超时
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param clientName
	 * 		客户端名称
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout, final String password, final int database,
					   final String clientName) {
		this(poolConfig, host, port, timeout, timeout, password, database, clientName);
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
	 * @param timeout
	 * 		超时
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param clientName
	 * 		客户端名称
	 * @param ssl
	 * 		是否为 SSL
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout, final String password, final int database,
					   final String clientName, final boolean ssl) {
		this(poolConfig, host, port, timeout, timeout, password, database, clientName, ssl);
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
	 * @param timeout
	 * 		超时
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param clientName
	 * 		客户端名称
	 * @param ssl
	 * 		是否为 SSL
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout, final String password, final int database,
					   final String clientName, final boolean ssl, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		this(poolConfig, host, port, timeout, timeout, password, database, clientName, ssl, sslSocketFactory,
				sslParameters, hostnameVerifier);
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
	 * @param timeout
	 * 		超时
	 * @param user
	 * 		用户名
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param clientName
	 * 		客户端名称
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout, final String user, final String password,
					   final int database, final String clientName) {
		this(poolConfig, host, port, timeout, timeout, user, password, database, clientName);
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
	 * @param timeout
	 * 		超时
	 * @param user
	 * 		用户名
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param clientName
	 * 		客户端名称
	 * @param ssl
	 * 		是否为 SSL
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int timeout, final String user, final String password,
					   final int database, final String clientName, final boolean ssl) {
		this(poolConfig, host, port, timeout, timeout, user, password, database, clientName, ssl);
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
	 * @param connectionTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param clientName
	 * 		客户端名称
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int connectionTimeout, final int soTimeout,
					   final String password, final int database, final String clientName) {
		this(poolConfig, host, port, connectionTimeout, soTimeout, null, password, database, clientName);
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
	 * @param connectionTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param clientName
	 * 		客户端名称
	 * @param ssl
	 * 		是否为 SSL
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int connectionTimeout, final int soTimeout,
					   final String password, final int database, final String clientName, final boolean ssl) {
		this(poolConfig, host, port, connectionTimeout, soTimeout, password, database, clientName, ssl,
				null, null, null);
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
	 * @param connectionTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param clientName
	 * 		客户端名称
	 * @param ssl
	 * 		是否为 SSL
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int connectionTimeout, final int soTimeout,
					   final String password, final int database, final String clientName, final boolean ssl,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier) {
		this(poolConfig, new LettuceFactory(host, port, connectionTimeout, soTimeout, null, password,
				database, clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier));
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
	 * @param connectionTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param user
	 * 		用户名
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param clientName
	 * 		客户端名称
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int connectionTimeout, final int soTimeout,
					   final String user, final String password, final int database, final String clientName) {
		this(poolConfig, new LettuceFactory(host, port, connectionTimeout, soTimeout, user, password, database,
				clientName));
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
	 * @param connectionTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param user
	 * 		用户名
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param clientName
	 * 		客户端名称
	 * @param ssl
	 * 		是否为 SSL
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int connectionTimeout, final int soTimeout,
					   final String user, final String password, final int database, final String clientName,
					   final boolean ssl) {
		this(poolConfig, host, port, connectionTimeout, soTimeout, user, password, database, clientName, ssl, null,
				null, null);
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
	 * @param connectionTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param user
	 * 		用户名
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param clientName
	 * 		客户端名称
	 * @param ssl
	 * 		是否为 SSL
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int connectionTimeout, final int soTimeout,
					   final String user, final String password, final int database, final String clientName,
					   final boolean ssl, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier) {
		this(poolConfig, new LettuceFactory(host, port, connectionTimeout, soTimeout, user, password, database,
				clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier));
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
	 * @param connectionTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param infiniteSoTimeout
	 * 		Infinite 超时
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param clientName
	 * 		客户端名称
	 * @param ssl
	 * 		是否为 SSL
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int connectionTimeout, final int soTimeout,
					   final int infiniteSoTimeout, final String password, final int database,
					   final String clientName, final boolean ssl, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		this(poolConfig, host, port, connectionTimeout, soTimeout, infiniteSoTimeout, null, password,
				database, clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier);
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
	 * @param connectionTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param infiniteSoTimeout
	 * 		Infinite 超时
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param clientName
	 * 		客户端名称
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int connectionTimeout, final int soTimeout,
					   final int infiniteSoTimeout, final String user, final String password, final int database,
					   final String clientName) {
		this(poolConfig, new LettuceFactory(host, port, connectionTimeout, soTimeout, user, password, database,
				clientName));
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
	 * @param connectionTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param infiniteSoTimeout
	 * 		Infinite 超时
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param clientName
	 * 		客户端名称
	 * @param ssl
	 * 		是否为 SSL
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig,
					   final String host, final int port, final int connectionTimeout, final int soTimeout,
					   final int infiniteSoTimeout, final String user, final String password, final int database,
					   final String clientName, final boolean ssl, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		this(poolConfig, new LettuceFactory(host, port, connectionTimeout, soTimeout, user, password, database,
				clientName, ssl, sslSocketFactory, sslParameters, hostnameVerifier));
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
	 * @param uri
	 * 		连接 URL
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public LettucePool(final URI uri, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier) {
		this(new LettucePoolConfig<>(), uri, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	/**
	 * 构造函数
	 *
	 * @param uri
	 * 		连接 URL
	 * @param timeout
	 * 		超时
	 */
	public LettucePool(final URI uri, final int timeout) {
		this(new LettucePoolConfig<>(), uri, timeout);
	}

	/**
	 * 构造函数
	 *
	 * @param uri
	 * 		连接 URL
	 * @param timeout
	 * 		超时
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public LettucePool(final URI uri, final int timeout, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		this(new LettucePoolConfig<>(), uri, timeout, sslSocketFactory, sslParameters, hostnameVerifier);
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
		this(poolConfig, uri, (int) RedisURI.DEFAULT_TIMEOUT);
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		线程池配置
	 * @param uri
	 * 		连接 URL
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig, final URI uri,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier) {
		this(poolConfig, uri, (int) RedisURI.DEFAULT_TIMEOUT, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		线程池配置
	 * @param uri
	 * 		连接 URL
	 * @param timeout
	 * 		超时
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig, final URI uri,
					   final int timeout) {
		this(poolConfig, uri, timeout, timeout);
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		线程池配置
	 * @param uri
	 * 		连接 URL
	 * @param timeout
	 * 		超时
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig, final URI uri,
					   final int timeout, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier) {
		this(poolConfig, uri, timeout, timeout, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		线程池配置
	 * @param uri
	 * 		连接 URL
	 * @param connectionTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig, final URI uri,
					   final int connectionTimeout, final int soTimeout) {
		this(poolConfig, uri, connectionTimeout, soTimeout, null, null, null);
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		线程池配置
	 * @param uri
	 * 		连接 URL
	 * @param connectionTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig, final URI uri,
					   final int connectionTimeout, final int soTimeout, final SSLSocketFactory sslSocketFactory,
					   final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier) {
		this(poolConfig, new LettuceFactory(RedisURI.create(uri), connectionTimeout, soTimeout, sslSocketFactory,
				sslParameters, hostnameVerifier));
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		线程池配置
	 * @param uri
	 * 		连接 URL
	 * @param connectionTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param infiniteSoTimeout
	 * 		Infinite 超时
	 * @param sslSocketFactory
	 *        {@link SSLSocketFactory}
	 * @param sslParameters
	 *        {@link SSLParameters}
	 * @param hostnameVerifier
	 *        {@link HostnameVerifier}
	 */
	public LettucePool(final GenericObjectPoolConfig<StatefulRedisConnection<byte[], byte[]>> poolConfig, final URI uri,
					   final int connectionTimeout, final int soTimeout, final int infiniteSoTimeout,
					   final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
					   final HostnameVerifier hostnameVerifier) {
		this(poolConfig, new LettuceFactory(RedisURI.create(uri), connectionTimeout, soTimeout, infiniteSoTimeout, null,
				sslSocketFactory, sslParameters, hostnameVerifier));
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
		//jedis.setDataSource(this);
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
