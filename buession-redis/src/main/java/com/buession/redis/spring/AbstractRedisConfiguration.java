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

import com.buession.redis.core.Constants;
import com.buession.net.ssl.SslConfiguration;

/**
 * Redis 工厂配置抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractRedisConfiguration implements RedisConfiguration {

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 连接超时（单位：秒）
	 */
	private int connectTimeout = Constants.DEFAULT_CONNECT_TIMEOUT;

	/**
	 * 读取超时（单位：秒）
	 */
	private int soTimeout = Constants.DEFAULT_SO_TIMEOUT;

	/**
	 * Infinite 读取超时（单位：秒）
	 *
	 * @since 2.0.0
	 */
	private int infiniteSoTimeout;

	/**
	 * Client Name
	 */
	private String clientName;

	/**
	 * SSL 配置
	 */
	private SslConfiguration sslConfiguration;

	@Override
	public String getUsername(){
		return username;
	}

	@Override
	public void setUsername(String username){
		this.username = username;
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
	public int getConnectTimeout(){
		return connectTimeout;
	}

	@Override
	public void setConnectTimeout(int connectTimeout){
		this.connectTimeout = connectTimeout;
	}

	@Override
	public int getSoTimeout(){
		return soTimeout;
	}

	@Override
	public void setSoTimeout(int soTimeout){
		this.soTimeout = soTimeout;
	}

	@Override
	public int getInfiniteSoTimeout(){
		return infiniteSoTimeout;
	}

	@Override
	public void setInfiniteSoTimeout(int infiniteSoTimeout){
		this.infiniteSoTimeout = infiniteSoTimeout;
	}

	@Override
	public SslConfiguration getSslConfiguration(){
		return sslConfiguration;
	}

	@Override
	public void setSslConfiguration(SslConfiguration sslConfiguration){
		this.sslConfiguration = sslConfiguration;
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
