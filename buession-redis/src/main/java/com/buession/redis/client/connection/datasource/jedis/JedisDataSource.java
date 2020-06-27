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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection.datasource.jedis;

import com.buession.redis.client.connection.datasource.StandaloneDataSource;
import com.buession.redis.core.Server;
import redis.clients.jedis.Jedis;

/**
 * Jedis 单机模式数据源
 *
 * @author Yong.Teng
 */
public class JedisDataSource extends AbstractJedisDataSource<Jedis> implements StandaloneDataSource {

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
	 * 数据库
	 */
	private int database = Server.DEFAULT_DATABASE;

	/**
	 * Client Name
	 */
	private String clientName;

	/**
	 * 构造函数
	 */
	public JedisDataSource(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 */
	public JedisDataSource(final String host){
		this();
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
	public JedisDataSource(final String host, final int port){
		this();
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
	public JedisDataSource(final String host, final int port, final String password){
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
	public JedisDataSource(final String host, final int port, final String password, final int database){
		this(host, port, password);
		this.database = database;
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
	 * @param clientName
	 * 		Client Name
	 */
	public JedisDataSource(final String host, final int port, final String password, final int database,
			final String clientName){
		this(host, port, password, database);
		this.clientName = clientName;
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
	public JedisDataSource(final String host, final int port, final int database){
		this(host, port);
		this.database = database;
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
	 * @param clientName
	 * 		Client Name
	 */
	public JedisDataSource(final String host, final int port, final int database, final String clientName){
		this(host, port, database);
		this.clientName = clientName;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param password
	 * 		密码
	 */
	public JedisDataSource(final String host, final String password){
		this(host);
		this.password = password;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param password
	 * 		密码
	 * @param clientName
	 * 		Client Name
	 */
	public JedisDataSource(final String host, final String password, final String clientName){
		this(host, password);
		this.clientName = clientName;
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
	public JedisDataSource(final String host, final String password, final int database){
		this(host, password);
		this.database = database;
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
	 * @param clientName
	 * 		Client Name
	 */
	public JedisDataSource(final String host, final String password, final int database, final String clientName){
		this(host, password, database);
		this.clientName = clientName;
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

	@Override
	public int getDatabase(){
		return database;
	}

	@Override
	public void setDatabase(int database){
		this.database = database;
	}

	@Override
	public String getClientName(){
		return clientName;
	}

	@Override
	public void setClientName(String clientName){
		this.clientName = clientName;
	}

}
