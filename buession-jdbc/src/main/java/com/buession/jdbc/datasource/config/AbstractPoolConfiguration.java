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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.jdbc.datasource.config;

import java.util.Properties;

/**
 * 连接池配置基类
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public abstract class AbstractPoolConfiguration implements PoolConfiguration {

	/**
	 * 驱动名
	 */
	@Deprecated
	private String driverClassName;

	/**
	 * 连接 URL
	 */
	@Deprecated
	private String url;

	@Deprecated
	private String username;

	@Deprecated
	private String password;

	private Properties properties;

	/**
	 * 返回驱动名
	 *
	 * @return 驱动名
	 */
	@Deprecated
	public String getDriverClassName() {
		return driverClassName;
	}

	/**
	 * 设置驱动名
	 *
	 * @param driverClassName
	 * 		驱动名
	 */
	@Deprecated
	public void setDriverClassName(String driverClassName) {

	}

	/**
	 * 返回连接 URL
	 *
	 * @return 连接 URL
	 */
	@Deprecated
	public String getUrl() {
		return url;
	}

	/**
	 * 设置连接 URL
	 *
	 * @param url
	 * 		连接 URL
	 */
	@Deprecated
	public void setUrl(String url) {

	}

	/**
	 * 返回用户名
	 *
	 * @return 用户名
	 */
	@Deprecated
	public String getUsername() {
		return username;
	}

	/**
	 * 设置用户名
	 *
	 * @param username
	 * 		用户名
	 */
	@Deprecated
	public void setUsername(String username) {

	}

	/**
	 * 返回密码
	 *
	 * @return 密码
	 */
	@Deprecated
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码
	 *
	 * @param password
	 * 		密码
	 */
	@Deprecated
	public void setPassword(String password) {

	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	@Override
	public void setProperties(Properties properties) {
		this.properties = properties;
	}

}
