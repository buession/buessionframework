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
package com.buession.jdbc.datasource;

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.jdbc.datasource.pool.GenericPoolConfiguration;

import javax.sql.DataSource;

/**
 * 一般 DataSource
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class GenericDataSource extends AbstractDataSource<DataSource, GenericPoolConfiguration> {

	/**
	 * 构造函数
	 */
	public GenericDataSource() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param driverClassName
	 * 		数据库驱动类名
	 * @param url
	 * 		JDBC URL
	 *
	 * @since 2.3.0
	 */
	public GenericDataSource(String driverClassName, String url) {
		super(driverClassName, url);
	}

	/**
	 * 构造函数
	 *
	 * @param driverClassName
	 * 		数据库驱动类名
	 * @param url
	 * 		JDBC URL
	 * @param username
	 * 		数据库账号
	 *
	 * @since 2.3.0
	 */
	public GenericDataSource(String driverClassName, String url, String username) {
		super(driverClassName, url, username);
	}

	/**
	 * 构造函数
	 *
	 * @param driverClassName
	 * 		数据库驱动类名
	 * @param url
	 * 		JDBC URL
	 * @param username
	 * 		数据库账号
	 * @param password
	 * 		数据库密码
	 *
	 * @since 2.3.0
	 */
	public GenericDataSource(String driverClassName, String url, String username, String password) {
		super(driverClassName, url, username, password);
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfiguration
	 * 		连接池配置
	 */
	public GenericDataSource(GenericPoolConfiguration poolConfiguration) {
		super(poolConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param driverClassName
	 * 		数据库驱动类名
	 * @param url
	 * 		JDBC URL
	 * @param poolConfiguration
	 * 		连接池配置
	 *
	 * @since 2.3.0
	 */
	public GenericDataSource(String driverClassName, String url, GenericPoolConfiguration poolConfiguration) {
		super(driverClassName, url, poolConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param driverClassName
	 * 		数据库驱动类名
	 * @param url
	 * 		JDBC URL
	 * @param username
	 * 		数据库账号
	 * @param poolConfiguration
	 * 		连接池配置
	 *
	 * @since 2.3.0
	 */
	public GenericDataSource(String driverClassName, String url, String username,
							 GenericPoolConfiguration poolConfiguration) {
		super(driverClassName, url, username, poolConfiguration);
	}

	/**
	 * 构造函数
	 *
	 * @param driverClassName
	 * 		数据库驱动类名
	 * @param url
	 * 		JDBC URL
	 * @param username
	 * 		数据库账号
	 * @param password
	 * 		数据库密码
	 * @param poolConfiguration
	 * 		连接池配置
	 *
	 * @since 2.3.0
	 */
	public GenericDataSource(String driverClassName, String url, String username, String password,
							 GenericPoolConfiguration poolConfiguration) {
		super(driverClassName, url, username, password, poolConfiguration);
	}

	@Override
	public DataSource createDataSource() {
		return createDataSource(null);
	}

	@Override
	protected DataSource createDataSource(final PropertyMapper propertyMapper) {
		return null;
	}

}
