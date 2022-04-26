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
package com.buession.redis.spring;

import com.buession.net.ssl.SslConfiguration;

/**
 * Redis 工厂配置
 *
 * @author Yong.Teng
 */
public interface RedisConfiguration {

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
	 * 获取密码
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
	 * 获取连接超时
	 *
	 * @return 连接超时（单位：秒）
	 */
	int getConnectTimeout();

	/**
	 * 设置连接超时
	 *
	 * @param connectTimeout
	 * 		连接超时（单位：秒）
	 */
	void setConnectTimeout(int connectTimeout);

	/**
	 * 设置读取超时
	 *
	 * @return 读取超时（单位：秒）
	 */
	int getSoTimeout();

	/**
	 * 设置读取超时
	 *
	 * @param soTimeout
	 * 		读取超时（单位：秒）
	 */
	void setSoTimeout(int soTimeout);

	/**
	 * 获取客户端名称
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
	 * SSL 配置
	 *
	 * @return SSL 配置
	 */
	SslConfiguration getSslConfiguration();

	/**
	 * 设置 SSL 配置
	 *
	 * @param sslConfiguration
	 * 		SSL 配置
	 */
	void setSslConfiguration(SslConfiguration sslConfiguration);

}
