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
package com.buession.redis.client.connection.datasource;

import com.buession.redis.core.Constants;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.core.SslOptions;

import java.util.function.Consumer;

/**
 * Redis 数据源抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractDataSource implements DataSource {

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 客户端名称
	 */
	private String clientName;

	/**
	 * 连接超时（单位：毫秒）
	 */
	private int connectTimeout = Constants.DEFAULT_CONNECT_TIMEOUT;

	/**
	 * 读取超时（单位：毫秒）
	 */
	private int soTimeout = Constants.DEFAULT_SO_TIMEOUT;

	/**
	 * Infinite 读取超时（单位：毫秒）
	 *
	 * @since 2.0.0
	 */
	private int infiniteSoTimeout = Constants.DEFAULT_INFINITE_SO_TIMEOUT;

	/**
	 * 连接池配置
	 *
	 * @since 2.0.0
	 */
	private PoolConfig poolConfig;

	/**
	 * 是否自动重连
	 *
	 * @since 4.0.0
	 */
	private boolean autoReconnect;

	/**
	 * SSL 配置
	 *
	 * @since 4.0.0
	 */
	private SslOptions sslOptions;

	/**
	 * 返回用户名
	 *
	 * @return 用户名
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * 设置用户名
	 *
	 * @param username
	 * 		用户名
	 */
	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 返回密码
	 *
	 * @return 密码
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码
	 *
	 * @param password
	 * 		密码
	 */
	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 返回客户端名称
	 *
	 * @return 客户端名称
	 */
	@Override
	public String getClientName() {
		return clientName;
	}

	/**
	 * 设置客户端名称
	 *
	 * @param clientName
	 * 		客户端名称
	 */
	@Override
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@Override
	public int getConnectTimeout() {
		return connectTimeout;
	}

	@Override
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	@Override
	public int getSoTimeout() {
		return soTimeout;
	}

	@Override
	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}

	@Override
	public int getInfiniteSoTimeout() {
		return infiniteSoTimeout;
	}

	@Override
	public void setInfiniteSoTimeout(int infiniteSoTimeout) {
		this.infiniteSoTimeout = infiniteSoTimeout;
	}

	@Override
	public PoolConfig getPoolConfig() {
		return poolConfig;
	}

	@Override
	public void setPoolConfig(PoolConfig poolConfig) {
		this.poolConfig = poolConfig;
	}

	@Override
	public SslOptions getSslOptions() {
		return sslOptions;
	}

	@Override
	public void setSslOptions(SslOptions sslOptions) {
		this.sslOptions = sslOptions;
	}

	@Override
	public void apply(Consumer<DataSource> consumer) {
		if(consumer != null){
			consumer.accept(this);
		}
	}

}
