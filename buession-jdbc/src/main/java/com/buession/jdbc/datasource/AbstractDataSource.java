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
 * DataSource 抽象类
 *
 * @param <T>
 *        {@link javax.sql.DataSource} 数据源类型
 * @param <P>
 * 		连接池配置 {@link PoolConfiguration} 实现
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public abstract class AbstractDataSource<T extends javax.sql.DataSource, P extends PoolConfiguration>
		implements DataSource<T, P> {

	/**
	 * 数据库驱动类名
	 *
	 * @since 2.3.0
	 */
	private String driverClassName;

	/**
	 * JDBC URL
	 *
	 * @since 2.3.0
	 */
	private String url;

	/**
	 * 数据库账号
	 *
	 * @since 2.3.0
	 */
	private String username;

	/**
	 * 数据库密码
	 *
	 * @since 2.3.0
	 */
	private String password;

	/**
	 * 连接池配置
	 */
	private P poolConfiguration;

	/**
	 * 构造函数
	 */
	public AbstractDataSource(){
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
	public AbstractDataSource(String driverClassName, String url){
		this.driverClassName = driverClassName;
		this.url = url;
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
	public AbstractDataSource(String driverClassName, String url, String username){
		this(driverClassName, url);
		this.username = username;
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
	public AbstractDataSource(String driverClassName, String url, String username, String password){
		this(driverClassName, url, username);
		this.password = password;
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfiguration
	 * 		连接池配置
	 */
	public AbstractDataSource(P poolConfiguration){
		this.poolConfiguration = poolConfiguration;
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
	public AbstractDataSource(String driverClassName, String url, P poolConfiguration){
		this(driverClassName, url);
		this.poolConfiguration = poolConfiguration;
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
	public AbstractDataSource(String driverClassName, String url, String username, P poolConfiguration){
		this(driverClassName, url, username);
		this.poolConfiguration = poolConfiguration;
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
	public AbstractDataSource(String driverClassName, String url, String username, String password,
							  P poolConfiguration){
		this(driverClassName, url, username, password);
		this.poolConfiguration = poolConfiguration;
	}

	@Override
	public String getDriverClassName(){
		return driverClassName;
	}

	@Override
	public void setDriverClassName(String driverClassName){
		this.driverClassName = driverClassName;
	}

	@Override
	public String getUrl(){
		return url;
	}

	@Override
	public void setUrl(String url){
		this.url = url;
	}

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
	public P getPoolConfiguration(){
		return poolConfiguration;
	}

	@Override
	public void setPoolConfiguration(P poolConfiguration){
		this.poolConfiguration = poolConfiguration;
	}

	protected void initialize(final T dataSource){
		if(getPoolConfiguration() != null){
			applyPoolConfiguration(dataSource, getPoolConfiguration());
		}
	}

	protected void applyPoolConfiguration(final T dataSource, final P poolConfiguration){

	}

}
