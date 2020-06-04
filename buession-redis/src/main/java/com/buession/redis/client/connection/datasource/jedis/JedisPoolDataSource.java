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
package com.buession.redis.client.connection.datasource.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis 单机模式连接池数据源
 *
 * @author Yong.Teng
 */
public class JedisPoolDataSource extends AbstractGenericJedisDataSource implements PoolJedisDataSource<Jedis> {

	/**
	 * 连接池配置
	 */
	private JedisPoolConfig poolConfig;

	/**
	 * 构造函数
	 */
	public JedisPoolDataSource(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 */
	public JedisPoolDataSource(String host){
		super(host);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 */
	public JedisPoolDataSource(String host, int port){
		super(host, port);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 * @param password
	 * 		密码
	 */
	public JedisPoolDataSource(String host, int port, String password){
		super(host, port, password);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 */
	public JedisPoolDataSource(String host, int port, String password, int database){
		super(host, port, password, database);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 */
	public JedisPoolDataSource(String host, int port, String password, int database, int connectTimeout,
			int soTimeout){
		super(host, port, password, database, connectTimeout, soTimeout);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 * @param database
	 * 		数据库
	 */
	public JedisPoolDataSource(String host, int port, int database){
		super(host, port, database);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 * @param database
	 * 		数据库
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 */
	public JedisPoolDataSource(String host, int port, int database, int connectTimeout, int soTimeout){
		super(host, port, database, connectTimeout, soTimeout);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param password
	 */
	public JedisPoolDataSource(String host, String password){
		super(host, password);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param password
	 * 		密码
	 * @param database
	 */
	public JedisPoolDataSource(String host, String password, int database){
		super(host, password, database);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * 		Redis 端口
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 */
	public JedisPoolDataSource(String host, String password, int database, int connectTimeout, int soTimeout){
		super(host, password, database, connectTimeout, soTimeout);
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		连接池配置
	 */
	public JedisPoolDataSource(JedisPoolConfig poolConfig){
		this.poolConfig = poolConfig;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param poolConfig
	 * 		连接池配置
	 */
	public JedisPoolDataSource(String host, JedisPoolConfig poolConfig){
		super(host);
		this.poolConfig = poolConfig;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 * @param poolConfig
	 * 		连接池配置
	 */
	public JedisPoolDataSource(String host, int port, JedisPoolConfig poolConfig){
		super(host, port);
		this.poolConfig = poolConfig;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 * @param password
	 * 		密码
	 * @param poolConfig
	 * 		连接池配置
	 */
	public JedisPoolDataSource(String host, int port, String password, JedisPoolConfig poolConfig){
		super(host, port, password);
		this.poolConfig = poolConfig;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param poolConfig
	 * 		连接池配置
	 */
	public JedisPoolDataSource(String host, int port, String password, int database, JedisPoolConfig poolConfig){
		super(host, port, password, database);
		this.poolConfig = poolConfig;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param poolConfig
	 * 		连接池配置
	 */
	public JedisPoolDataSource(String host, int port, String password, int database, int connectTimeout, int soTimeout
			, JedisPoolConfig poolConfig){
		super(host, port, password, database, connectTimeout, soTimeout);
		this.poolConfig = poolConfig;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 * @param database
	 * 		数据库
	 * @param poolConfig
	 * 		连接池配置
	 */
	public JedisPoolDataSource(String host, int port, int database, JedisPoolConfig poolConfig){
		super(host, port, database);
		this.poolConfig = poolConfig;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 * @param database
	 * 		数据库
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param poolConfig
	 * 		连接池配置
	 */
	public JedisPoolDataSource(String host, int port, int database, int connectTimeout, int soTimeout,
			JedisPoolConfig poolConfig){
		super(host, port, database, connectTimeout, soTimeout);
		this.poolConfig = poolConfig;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param password
	 * 		密码
	 * @param poolConfig
	 * 		连接池配置
	 */
	public JedisPoolDataSource(String host, String password, JedisPoolConfig poolConfig){
		super(host, password);
		this.poolConfig = poolConfig;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param poolConfig
	 * 		连接池配置
	 */
	public JedisPoolDataSource(String host, String password, int database, JedisPoolConfig poolConfig){
		super(host, password, database);
		this.poolConfig = poolConfig;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param connectTimeout
	 * 		连接超时
	 * @param soTimeout
	 * 		读取超时
	 * @param poolConfig
	 * 		连接池配置
	 */
	public JedisPoolDataSource(String host, String password, int database, int connectTimeout, int soTimeout,
			JedisPoolConfig poolConfig){
		super(host, password, database, connectTimeout, soTimeout);
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

}
