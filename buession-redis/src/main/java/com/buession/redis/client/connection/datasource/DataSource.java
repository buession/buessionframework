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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection.datasource;

import com.buession.net.ssl.SslConfiguration;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.PoolConfig;

/**
 * Redis 数据源
 *
 * @author Yong.Teng
 */
public interface DataSource {

	/**
	 * 返回用户名
	 *
	 * @return 用户名
	 */
	String getUsername();

	/**
	 * 设置用户名
	 *
	 * @param username
	 * 		用户名
	 */
	void setUsername(String username);

	/**
	 * 返回密码
	 *
	 * @return 密码
	 */
	String getPassword();

	/**
	 * 设置密码
	 *
	 * @param password
	 * 		密码
	 */
	void setPassword(String password);

	/**
	 * 返回客户端名称
	 *
	 * @return 客户端名称
	 */
	String getClientName();

	/**
	 * 设置客户端名称
	 *
	 * @param clientName
	 * 		客户端名称
	 */
	void setClientName(String clientName);

	/**
	 * 获取连接超时
	 *
	 * @return 连接超时（单位：毫秒）
	 */
	int getConnectTimeout();

	/**
	 * 设置连接超时
	 *
	 * @param connectTimeout
	 * 		连接超时（单位：毫秒）
	 */
	void setConnectTimeout(int connectTimeout);

	/**
	 * 设置读取超时
	 *
	 * @return 读取超时（单位：毫秒）
	 */
	int getSoTimeout();

	/**
	 * 设置读取超时
	 *
	 * @param soTimeout
	 * 		读取超时（单位：毫秒）
	 */
	void setSoTimeout(int soTimeout);

	/**
	 * 返回 Infinite 读取超时
	 *
	 * @return Infinite 读取超时（单位：毫秒）
	 */
	int getInfiniteSoTimeout();

	/**
	 * 设置 Infinite 读取超时
	 *
	 * @param infiniteSoTimeout
	 * 		Infinite 读取超时（单位：毫秒）
	 */
	void setInfiniteSoTimeout(int infiniteSoTimeout);

	/**
	 * 返回连接池配置
	 *
	 * @return 连接池配置
	 *
	 * @since 2.0.0
	 */
	PoolConfig getPoolConfig();

	/**
	 * 设置连接池配置
	 *
	 * @param poolConfig
	 * 		连接池配置
	 *
	 * @since 2.0.0
	 */
	void setPoolConfig(PoolConfig poolConfig);

	/**
	 * 返回 SSL 配置
	 *
	 * @return SSL 配置
	 *
	 * @since 2.0.0
	 */
	SslConfiguration getSslConfiguration();

	/**
	 * 设置 SSL 配置
	 *
	 * @param sslConfiguration
	 * 		SSL 配置
	 *
	 * @since 2.0.0
	 */
	void setSslConfiguration(SslConfiguration sslConfiguration);

	/**
	 * 获取 Redis 连接实例 {@link RedisConnection}
	 *
	 * @return Redis 连接实例 {@link RedisConnection}
	 *
	 * @since 2.0.0
	 */
	@Deprecated
	RedisConnection getConnection();

}
