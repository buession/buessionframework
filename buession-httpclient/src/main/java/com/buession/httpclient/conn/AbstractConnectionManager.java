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
package com.buession.httpclient.conn;

import com.buession.httpclient.core.Configuration;

/**
 * 连接管理器抽象类
 *
 * @param <CM>
 * 		原生连接管理器
 *
 * @author Yong.Teng
 */
public abstract class AbstractConnectionManager<CM> implements ConnectionManager {

	private Configuration configuration = new Configuration();

	private CM clientConnectionManager;

	/**
	 * @since 2.3.0
	 */
	private Boolean connectionManagerShared;

	/**
	 * 构造函数，创建驱动默认连接管理器
	 */
	public AbstractConnectionManager() {
		clientConnectionManager = createDefaultClientConnectionManager();
	}

	/**
	 * 构造函数，创建驱动默认连接管理器
	 *
	 * @param configuration
	 * 		连接对象
	 */
	public AbstractConnectionManager(Configuration configuration) {
		this.configuration = configuration;
		clientConnectionManager = createDefaultClientConnectionManager();
	}

	/**
	 * 构造函数
	 *
	 * @param clientConnectionManager
	 * 		驱动连接管理器
	 */
	public AbstractConnectionManager(CM clientConnectionManager) {
		this.clientConnectionManager = clientConnectionManager;
	}

	/**
	 * 构造函数
	 *
	 * @param configuration
	 * 		连接对象
	 * @param clientConnectionManager
	 * 		驱动连接管理器
	 */
	public AbstractConnectionManager(Configuration configuration, CM clientConnectionManager) {
		this.configuration = configuration;
		this.clientConnectionManager = clientConnectionManager;
	}

	/**
	 * 获取连接对象
	 *
	 * @return 连接对象
	 */
	@Override
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * 设置连接对象
	 *
	 * @param configuration
	 * 		连接对象
	 */
	@Override
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * 获取驱动连接管理器
	 *
	 * @return 连接管理器
	 */
	public CM getClientConnectionManager() {
		return clientConnectionManager;
	}

	/**
	 * 设置连接管理器
	 *
	 * @param clientConnectionManager
	 * 		连接管理器
	 */
	public void setClientConnectionManager(CM clientConnectionManager) {
		this.clientConnectionManager = clientConnectionManager;
	}

	/**
	 * 返回链接管理器是否共享
	 *
	 * @return True / False
	 *
	 * @since 2.3.0
	 */
	public Boolean getConnectionManagerShared() {
		return connectionManagerShared;
	}

	/**
	 * 设置链接管理器是否共享
	 *
	 * @param connectionManagerShared
	 * 		链接管理器是否共享
	 *
	 * @since 2.3.0
	 */
	public void setConnectionManagerShared(Boolean connectionManagerShared) {
		this.connectionManagerShared = connectionManagerShared;
	}

	/**
	 * 创建驱动默认连接管理器
	 *
	 * @return 连接管理器
	 */
	protected abstract CM createDefaultClientConnectionManager();

}
