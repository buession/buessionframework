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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection.jedis;

import com.buession.redis.client.connection.AbstractRedisConnection;
import com.buession.redis.client.connection.SslConfiguration;
import com.buession.redis.client.connection.datasource.jedis.JedisRedisDataSource;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.commands.JedisCommands;

import java.io.IOException;

/**
 * Jedis Redis 连接对象抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisConnection<T extends JedisCommands> extends AbstractRedisConnection<T> implements JedisRedisConnection<T> {

	/**
	 * 连接池配置
	 */
	private JedisPoolConfig poolConfig;

	/**
	 * 构造函数
	 */
	public AbstractJedisRedisConnection(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 */
	public AbstractJedisRedisConnection(JedisRedisDataSource<T> dataSource){
		super(dataSource);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 */
	public AbstractJedisRedisConnection(JedisRedisDataSource<T> dataSource, int connectTimeout, int soTimeout){
		super(dataSource, connectTimeout, soTimeout);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public AbstractJedisRedisConnection(JedisRedisDataSource<T> dataSource, SslConfiguration sslConfiguration){
		super(dataSource, sslConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public AbstractJedisRedisConnection(JedisRedisDataSource<T> dataSource, int connectTimeout, int soTimeout,
			SslConfiguration sslConfiguration){
		super(dataSource, connectTimeout, soTimeout, sslConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 */
	public AbstractJedisRedisConnection(JedisRedisDataSource<T> dataSource, JedisPoolConfig poolConfig){
		super(dataSource);
		this.poolConfig = poolConfig;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 */
	public AbstractJedisRedisConnection(JedisRedisDataSource<T> dataSource, JedisPoolConfig poolConfig,
			int connectTimeout, int soTimeout){
		super(dataSource, connectTimeout, soTimeout);
		this.poolConfig = poolConfig;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public AbstractJedisRedisConnection(JedisRedisDataSource<T> dataSource, JedisPoolConfig poolConfig,
			SslConfiguration sslConfiguration){
		super(dataSource, sslConfiguration);
		this.poolConfig = poolConfig;
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		Redis 数据源
	 * @param poolConfig
	 * 		连接池配置
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	public AbstractJedisRedisConnection(JedisRedisDataSource<T> dataSource, JedisPoolConfig poolConfig,
			int connectTimeout, int soTimeout, SslConfiguration sslConfiguration){
		super(dataSource, connectTimeout, soTimeout, sslConfiguration);
		this.poolConfig = poolConfig;
	}

	@Override
	public JedisPoolConfig getPoolConfig(){
		return poolConfig;
	}

	@Override
	public void setPoolConfig(JedisPoolConfig poolConfig){
		this.poolConfig = poolConfig;
	}

	@Override
	protected void doDisconnect() throws IOException{
	}

	@Override
	protected void doClose() throws IOException{
	}

}
