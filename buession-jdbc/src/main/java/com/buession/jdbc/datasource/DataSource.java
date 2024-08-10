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
	 * 返回 {@link ClassLoader} 实例
	 *
	 * @return {@link ClassLoader} 实例
	 *
	 * @since 3.0.0
	 */
	ClassLoader getDriverClassLoader();

	/**
	 * 设置 {@link ClassLoader} 实例
	 *
	 * @param driverClassLoader
	 *        {@link ClassLoader} 实例
	 */
	void setDriverClassLoader(ClassLoader driverClassLoader);

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
	 * 返回登录超时
	 *
	 * @return 登录超时
	 */
	Duration getLoginTimeout();

	/**
	 * 设置登录超时
	 *
	 * @param loginTimeout
	 * 		登录超时
	 */
	void setLoginTimeout(Duration loginTimeout);

	/**
	 * 返回为支持 catalog 概念的数据库设置默认 catalog
	 *
	 * @return 为支持 catalog 概念的数据库设置默认 catalog
	 *
	 * @since 3.0.0
	 */
	String getDefaultCatalog();

	/**
	 * 设置为支持 catalog 概念的数据库设置默认 catalog
	 *
	 * @param defaultCatalog
	 * 		为支持 catalog 概念的数据库设置默认 catalog
	 *
	 * @since 3.0.0
	 */
	void setDefaultCatalog(String defaultCatalog);

	/**
	 * 返回设置的默认模式为支持模式的概念数据库
	 *
	 * @return 设置的默认模式为支持模式的概念数据库
	 *
	 * @since 3.0.0
	 */
	String getDefaultSchema();

	/**
	 * 设置默认模式为支持模式的概念数据库
	 *
	 * @param defaultSchema
	 * 		默认模式为支持模式的概念数据库
	 *
	 * @since 3.0.0
	 */
	void setDefaultSchema(String defaultSchema);

	/**
	 * 返回在将每个新连接创建后，将其添加到池中之前执行的SQL语句
	 *
	 * @return 每个新连接创建后，将其添加到池中之前执行的SQL语句
	 *
	 * @since 3.0.0
	 */
	String getInitSQL();

	/**
	 * 设置每个新连接创建后，将其添加到池中之前执行的SQL语句
	 *
	 * @param initSQL
	 * 		每个新连接创建后，将其添加到池中之前执行的SQL语句
	 *
	 * @since 3.0.0
	 */
	void setInitSQL(String initSQL);

	/**
	 * 返回从连接池获取连接时, 验证连接是否可用的SQL语句
	 *
	 * @return 从连接池获取连接时, 验证连接是否可用的SQL语句
	 *
	 * @since 3.0.0
	 */
	String getValidationQuery();

	/**
	 * 设置从连接池获取连接时, 验证连接是否可用的SQL语句
	 *
	 * @param validationQuery
	 * 		从连接池获取连接时, 验证连接是否可用的SQL语句
	 *
	 * @since 3.0.0
	 */
	void setValidationQuery(String validationQuery);

	/**
	 * 返回连接有效SQL的执行查询超时时间
	 *
	 * @return 连接有效SQL的执行查询超时时间
	 *
	 * @since 3.0.0
	 */
	Duration getValidationQueryTimeout();

	/**
	 * 设置连接有效SQL的执行查询超时时间
	 *
	 * @param validationQueryTimeout
	 * 		连接有效SQL的执行查询超时时间
	 *
	 * @since 3.0.0
	 */
	void setValidationQueryTimeout(Duration validationQueryTimeout);

	/**
	 * 返回查询超时时间
	 *
	 * @return 查询超时时间
	 *
	 * @since 3.0.0
	 */
	Duration getQueryTimeout();

	/**
	 * 设置查询超时时间
	 *
	 * @param queryTimeout
	 * 		查询超时时间
	 *
	 * @since 3.0.0
	 */
	void setQueryTimeout(Duration queryTimeout);

	/**
	 * 返回默认事务隔离级别
	 *
	 * @return 默认事务隔离级别
	 *
	 * @since 3.0.0
	 */
	TransactionIsolation getDefaultTransactionIsolation();

	/**
	 * 设置默认事务隔离级别
	 *
	 * @param defaultTransactionIsolation
	 * 		默认事务隔离级别
	 *
	 * @since 3.0.0
	 */
	void setDefaultTransactionIsolation(TransactionIsolation defaultTransactionIsolation);

	/**
	 * 返回连接是否是只读模式
	 *
	 * @return 连接是否是只读模式
	 *
	 * @since 3.0.0
	 */
	default Boolean isDefaultReadOnly() {
		return getDefaultReadOnly();
	}

	/**
	 * 返回连接是否是只读模式
	 *
	 * @return 连接是否是只读模式
	 *
	 * @since 3.0.0
	 */
	Boolean getDefaultReadOnly();

	/**
	 * 设置连接是否是只读模式
	 *
	 * @param defaultReadOnly
	 * 		连接是否是只读模式
	 *
	 * @since 3.0.0
	 */
	void setDefaultReadOnly(Boolean defaultReadOnly);

	/**
	 * 返回是否自动提交事务
	 *
	 * @return 是否自动提交事务
	 *
	 * @since 3.0.0
	 */
	default Boolean isDefaultAutoCommit() {
		return getDefaultAutoCommit();
	}

	/**
	 * 返回是否自动提交事务
	 *
	 * @return 是否自动提交事务
	 *
	 * @since 3.0.0
	 */
	Boolean getDefaultAutoCommit();

	/**
	 * 设置是否自动提交事务
	 *
	 * @param defaultAutoCommit
	 * 		是否自动提交事务
	 *
	 * @since 3.0.0
	 */
	void setDefaultAutoCommit(Boolean defaultAutoCommit);

	/**
	 * 返回 PoolGuard 是否可以访问底层连接
	 *
	 * @return PoolGuard 是否可以访问底层连接
	 *
	 * @since 3.0.0
	 */
	Boolean isAccessToUnderlyingConnectionAllowed();

	/**
	 * 返回 PoolGuard 是否可以访问底层连接
	 *
	 * @return PoolGuard 是否可以访问底层连接
	 *
	 * @since 3.0.0
	 */
	Boolean getAccessToUnderlyingConnectionAllowed();

	/**
	 * 设置 PoolGuard 是否可以访问底层连接
	 *
	 * @param accessToUnderlyingConnectionAllowed
	 * 		PoolGuard 是否可以访问底层连接
	 *
	 * @since 3.0.0
	 */
	void setAccessToUnderlyingConnectionAllowed(Boolean accessToUnderlyingConnectionAllowed);

	/**
	 * 返回连接属性
	 *
	 * @return 连接属性
	 *
	 * @since 3.0.0
	 */
	Properties getConnectionProperties();

	/**
	 * 设置连接属性
	 *
	 * @param connectionProperties
	 * 		连接属性
	 *
	 * @since 3.0.0
	 */
	void setConnectionProperties(Properties connectionProperties);

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
