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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
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
	private String driverClassName;

	/**
	 * 连接 URL
	 */
	private String url;

	private String username;

	private String password;

	private Properties properties;

	/**
	 * 返回驱动名
	 *
	 * @return 驱动名
	 */
	public String getDriverClassName(){
		return driverClassName;
	}

	/**
	 * 设置驱动名
	 *
	 * @param driverClassName
	 * 		驱动名
	 */
	public void setDriverClassName(String driverClassName){
		this.driverClassName = driverClassName;
	}

	/**
	 * 返回连接 URL
	 *
	 * @return 连接 URL
	 */
	public String getUrl(){
		return url;
	}

	/**
	 * 设置连接 URL
	 *
	 * @param url
	 * 		连接 URL
	 */
	public void setUrl(String url){
		this.url = url;
	}

	/**
	 * 返回用户名
	 *
	 * @return 用户名
	 */
	public String getUsername(){
		return username;
	}

	/**
	 * 设置用户名
	 *
	 * @param username
	 * 		用户名
	 */
	public void setUsername(String username){
		this.username = username;
	}

	/**
	 * 返回密码
	 *
	 * @return 密码
	 */
	public String getPassword(){
		return password;
	}

	/**
	 * 设置密码
	 *
	 * @param password
	 * 		密码
	 */
	public void setPassword(String password){
		this.password = password;
	}

	@Override
	public Properties getProperties(){
		return properties;
	}

	@Override
	public void setProperties(Properties properties){
		this.properties = properties;
	}
}
