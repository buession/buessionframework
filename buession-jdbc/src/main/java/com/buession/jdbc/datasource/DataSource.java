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
package com.buession.jdbc.datasource;

import com.buession.jdbc.datasource.config.PoolConfiguration;

/**
 * 数据源接口
 *
 * @param <T>
 *        {@link javax.sql.DataSource} 数据源类型
 * @param <P>
 * 		连接池配置
 *
 * @author Yong.Teng
 */
public interface DataSource<T extends javax.sql.DataSource, P extends PoolConfiguration> {

	/**
	 * 返回数据库驱动类名
	 *
	 * @return 数据库驱动类名
	 *
	 * @since 2.3.0
	 */
	String getDriverClassName();

	/**
	 * 设置数据库驱动类名
	 *
	 * @param driverClassName
	 * 		数据库驱动类名
	 *
	 * @since 2.3.0
	 */
	void setDriverClassName(String driverClassName);

	/**
	 * 返回 JDBC URL
	 *
	 * @return JDBC URL
	 *
	 * @since 2.3.0
	 */
	String getUrl();

	/**
	 * 设置 JDBC URL
	 *
	 * @param url
	 * 		JDBC URL
	 *
	 * @since 2.3.0
	 */
	void setUrl(String url);

	/**
	 * 返回数据库账号
	 *
	 * @return 数据库账号
	 *
	 * @since 2.3.0
	 */
	String getUsername();

	/**
	 * 设置数据库账号
	 *
	 * @param username
	 * 		数据库账号
	 *
	 * @since 2.3.0
	 */
	void setUsername(String username);

	/**
	 * 返回数据库密码
	 *
	 * @return 数据库密码
	 *
	 * @since 2.3.0
	 */
	String getPassword();

	/**
	 * 设置数据库密码
	 *
	 * @param password
	 * 		数据库密码
	 *
	 * @since 2.3.0
	 */
	void setPassword(String password);

	/**
	 * 获取连接池配置
	 *
	 * @return 连接池配置
	 */
	P getPoolConfiguration();

	/**
	 * 设置连接池配置
	 *
	 * @param poolConfiguration
	 * 		连接池配置
	 */
	void setPoolConfiguration(P poolConfiguration);

	/**
	 * 初始化原始 {@link javax.sql.DataSource} 实例
	 *
	 * @return 原始 {@link javax.sql.DataSource} 实例
	 */
	T createDataSource();

}
