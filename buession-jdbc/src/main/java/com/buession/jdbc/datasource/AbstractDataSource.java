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

import com.buession.jdbc.core.TransactionIsolation;
import com.buession.jdbc.datasource.config.PoolConfiguration;

import java.time.Duration;
import java.util.Properties;

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
	 * {@link ClassLoader} 实例
	 *
	 * @since 3.0.0
	 */
	private ClassLoader driverClassLoader;

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
	 * 登录超时
	 *
	 * @since 3.0.0
	 */
	private Duration loginTimeout;

	/**
	 * 为支持 catalog 概念的数据库设置默认 catalog
	 *
	 * @since 3.0.0
	 */
	private String defaultCatalog;

	/**
	 * 设置的默认模式为支持模式的概念数据库
	 *
	 * @since 3.0.0
	 */
	private String defaultSchema;

	/**
	 * 设置一个SQL语句，在将每个新连接创建后，将其添加到池中之前执行该语句
	 *
	 * @since 3.0.0
	 */
	private String initSQL;

	/**
	 * 设置一个SQL语句, 从连接池获取连接时, 先执行改 sql, 验证连接是否可用
	 *
	 * @since 3.0.0
	 */
	private String validationQuery;

	/**
	 * 连接有效SQL的执行查询超时时间
	 *
	 * @since 3.0.0
	 */
	private Duration validationQueryTimeout;

	/**
	 * 查询超时时间
	 *
	 * @since 3.0.0
	 */
	private Duration queryTimeout;

	/**
	 * 默认事务隔离级别
	 *
	 * @since 3.0.0
	 */
	private TransactionIsolation defaultTransactionIsolation = TransactionIsolation.DEFAULT;

	/**
	 * 默认连接是否是只读模式
	 *
	 * @since 3.0.0
	 */
	private Boolean defaultReadOnly;

	/**
	 * 默认是否自动提交事务
	 *
	 * @since 3.0.0
	 */
	private Boolean defaultAutoCommit;

	/**
	 * PoolGuard 是否可以访问底层连接
	 *
	 * @since 3.0.0
	 */
	private Boolean accessToUnderlyingConnectionAllowed;

	/**
	 * 连接属性
	 *
	 * @since 3.0.0
	 */
	private Properties connectionProperties;

	/**
	 * 连接池配置
	 */
	private P poolConfiguration;

	/**
	 * 构造函数
	 */
	public AbstractDataSource() {
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
	public AbstractDataSource(String driverClassName, String url) {
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
	public AbstractDataSource(String driverClassName, String url, String username) {
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
	public AbstractDataSource(String driverClassName, String url, String username, String password) {
		this(driverClassName, url, username);
		this.password = password;
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfiguration
	 * 		连接池配置
	 */
	public AbstractDataSource(P poolConfiguration) {
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
	public AbstractDataSource(String driverClassName, String url, P poolConfiguration) {
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
	public AbstractDataSource(String driverClassName, String url, String username, P poolConfiguration) {
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
							  P poolConfiguration) {
		this(driverClassName, url, username, password);
		this.poolConfiguration = poolConfiguration;
	}

	@Override
	public ClassLoader getDriverClassLoader() {
		return driverClassLoader;
	}

	@Override
	public void setDriverClassLoader(ClassLoader driverClassLoader) {
		this.driverClassLoader = driverClassLoader;
	}

	@Override
	public String getDriverClassName() {
		return driverClassName;
	}

	@Override
	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Duration getLoginTimeout() {
		return loginTimeout;
	}

	@Override
	public void setLoginTimeout(Duration loginTimeout) {
		this.loginTimeout = loginTimeout;
	}

	@Override
	public String getDefaultCatalog() {
		return defaultCatalog;
	}

	@Override
	public void setDefaultCatalog(String defaultCatalog) {
		this.defaultCatalog = defaultCatalog;
	}

	@Override
	public String getDefaultSchema() {
		return defaultSchema;
	}

	@Override
	public void setDefaultSchema(String defaultSchema) {
		this.defaultSchema = defaultSchema;
	}

	public String getInitSQL() {
		return initSQL;
	}

	public void setInitSQL(String initSQL) {
		this.initSQL = initSQL;
	}

	@Override
	public String getValidationQuery() {
		return validationQuery;
	}

	@Override
	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	@Override
	public Duration getValidationQueryTimeout() {
		return validationQueryTimeout;
	}


	@Override
	public void setValidationQueryTimeout(Duration validationQueryTimeout) {
		this.validationQueryTimeout = validationQueryTimeout;
	}

	@Override
	public Duration getQueryTimeout() {
		return queryTimeout;
	}

	@Override
	public void setQueryTimeout(Duration queryTimeout) {
		this.queryTimeout = queryTimeout;
	}

	@Override
	public TransactionIsolation getDefaultTransactionIsolation() {
		return defaultTransactionIsolation;
	}

	@Override
	public void setDefaultTransactionIsolation(TransactionIsolation defaultTransactionIsolation) {
		this.defaultTransactionIsolation = defaultTransactionIsolation;
	}

	@Override
	public Boolean getDefaultReadOnly() {
		return defaultReadOnly;
	}

	@Override
	public void setDefaultReadOnly(Boolean defaultReadOnly) {
		this.defaultReadOnly = defaultReadOnly;
	}

	@Override
	public Boolean getDefaultAutoCommit() {
		return defaultAutoCommit;
	}

	@Override
	public void setDefaultAutoCommit(Boolean defaultAutoCommit) {
		this.defaultAutoCommit = defaultAutoCommit;
	}

	@Override
	public Boolean isAccessToUnderlyingConnectionAllowed() {
		return getAccessToUnderlyingConnectionAllowed();
	}

	@Override
	public Boolean getAccessToUnderlyingConnectionAllowed() {
		return accessToUnderlyingConnectionAllowed;
	}

	@Override
	public void setAccessToUnderlyingConnectionAllowed(Boolean accessToUnderlyingConnectionAllowed) {
		this.accessToUnderlyingConnectionAllowed = accessToUnderlyingConnectionAllowed;
	}

	public Properties getConnectionProperties() {
		return connectionProperties;
	}

	public void setConnectionProperties(Properties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	@Override
	public P getPoolConfiguration() {
		return poolConfiguration;
	}

	@Override
	public void setPoolConfiguration(P poolConfiguration) {
		this.poolConfiguration = poolConfiguration;
	}

	@Deprecated
	protected void initialize(final T dataSource) {
		if(getPoolConfiguration() != null){
			applyPoolConfiguration(dataSource, getPoolConfiguration());
		}
	}

	@Deprecated
	protected void applyPoolConfiguration(final T dataSource, final P poolConfiguration) {

	}

}
