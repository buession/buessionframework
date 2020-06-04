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

import com.buession.redis.core.Server;
import redis.clients.jedis.Jedis;

/**
 * Jedis 单机数据源抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractGenericJedisDataSource extends AbstractJedisDataSource<Jedis> implements JedisDataSource {

	/**
	 * Redis 主机地址
	 */
	private String host = Server.DEFAULT_HOST;

	/**
	 * Redis 端口
	 */
	private int port = Server.DEFAULT_PORT;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 构造函数
	 */
	public AbstractGenericJedisDataSource(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 */
	public AbstractGenericJedisDataSource(String host){
		this.host = host;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 */
	public AbstractGenericJedisDataSource(String host, int port){
		this.host = host;
		this.port = port;
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
	public AbstractGenericJedisDataSource(String host, int port, String password){
		this(host, port);
		this.password = password;
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
	public AbstractGenericJedisDataSource(String host, int port, String password, int database){
		this(host, port, password);
		setDatabase(database);
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
	public AbstractGenericJedisDataSource(String host, int port, String password, int database, int connectTimeout,
			int soTimeout){
		this(host, port, password, database);
		setConnectTimeout(connectTimeout);
		setSoTimeout(soTimeout);
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
	public AbstractGenericJedisDataSource(String host, int port, int database){
		this(host, port);
		setDatabase(database);
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
	public AbstractGenericJedisDataSource(String host, int port, int database, int connectTimeout, int soTimeout){
		this(host, port, database);
		setConnectTimeout(connectTimeout);
		setSoTimeout(soTimeout);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param password
	 * 		密码
	 */
	public AbstractGenericJedisDataSource(String host, String password){
		this.host = host;
		this.password = password;
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
	 */
	public AbstractGenericJedisDataSource(String host, String password, int database){
		this(host, password);
		setDatabase(database);
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
	 */
	public AbstractGenericJedisDataSource(String host, String password, int database, int connectTimeout,
			int soTimeout){
		this(host, password, database);
		setConnectTimeout(connectTimeout);
		setSoTimeout(soTimeout);
	}

	@Override
	public String getHost(){
		return host;
	}

	@Override
	public void setHost(String host){
		this.host = host;
	}

	@Override
	public int getPort(){
		return port;
	}

	@Override
	public void setPort(int port){
		this.port = port;
	}

	@Override
	public String getPassword(){
		return password;
	}

	@Override
	public void setPassword(String password){
		this.password = password;
	}

}
