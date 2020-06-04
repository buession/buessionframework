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

/**
 * Jedis 单机模式数据源
 *
 * @author Yong.Teng
 */
public class GenericJedisDataSource extends AbstractGenericJedisDataSource {

	/**
	 * 构造函数
	 */
	public GenericJedisDataSource(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 */
	public GenericJedisDataSource(final String host){
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
	public GenericJedisDataSource(final String host, final int port){
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
	public GenericJedisDataSource(final String host, final int port, final String password){
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
	public GenericJedisDataSource(final String host, final int port, final String password, final int database){
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
	public GenericJedisDataSource(final String host, final int port, final String password, final int database,
			final int connectTimeout, final int soTimeout){
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
	public GenericJedisDataSource(final String host, final int port, final int database){
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
	public GenericJedisDataSource(final String host, final int port, final int database, final int connectTimeout,
			final int soTimeout){
		super(host, port, database, connectTimeout, soTimeout);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param password
	 * 		密码
	 */
	public GenericJedisDataSource(final String host, final String password){
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
	 * 		数据库
	 */
	public GenericJedisDataSource(final String host, final String password, final int database){
		super(host, password, database);
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
	public GenericJedisDataSource(final String host, final String password, final int database,
			final int connectTimeout, final int soTimeout){
		super(host, password, database, connectTimeout, soTimeout);
	}

}
