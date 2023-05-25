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

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.jdbc.datasource.config.HikariPoolConfiguration;

import java.time.Duration;

/**
 * Hikari DataSource 抽象类
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class HikariDataSource extends AbstractDataSource<com.zaxxer.hikari.HikariDataSource, HikariPoolConfiguration> {

	/**
	 * 构造函数
	 */
	public HikariDataSource(){
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
	public HikariDataSource(String driverClassName, String url){
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
	public HikariDataSource(String driverClassName, String url, String username){
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
	public HikariDataSource(String driverClassName, String url, String username, String password){
		super(driverClassName, url, username, password);
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfiguration
	 * 		连接池配置
	 */
	public HikariDataSource(HikariPoolConfiguration poolConfiguration){
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
	public HikariDataSource(String driverClassName, String url, HikariPoolConfiguration poolConfiguration){
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
	public HikariDataSource(String driverClassName, String url, String username,
							HikariPoolConfiguration poolConfiguration){
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
	public HikariDataSource(String driverClassName, String url, String username, String password,
							HikariPoolConfiguration poolConfiguration){
		super(driverClassName, url, username, password, poolConfiguration);
	}

	@Override
	public com.zaxxer.hikari.HikariDataSource createDataSource(){
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();
		final com.zaxxer.hikari.HikariDataSource dataSource = new com.zaxxer.hikari.HikariDataSource();

		propertyMapper.from(this::getDriverClassName).to(dataSource::setDriverClassName);
		propertyMapper.from(this::getUrl).to(dataSource::setJdbcUrl);
		propertyMapper.from(this::getUsername).to(dataSource::setUsername);
		propertyMapper.from(this::getPassword).to(dataSource::setPassword);

		initialize(dataSource);

		return dataSource;
	}

	@Override
	protected void applyPoolConfiguration(final com.zaxxer.hikari.HikariDataSource dataSource,
										  final HikariPoolConfiguration poolConfiguration){
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();

		propertyMapper.from(poolConfiguration::getCatalog).to(dataSource::setCatalog);
		propertyMapper.from(poolConfiguration::getConnectionTimeout).as(
				Duration::toMillis).to(dataSource::setConnectionTimeout);
		propertyMapper.from(poolConfiguration::getIdleTimeout).as(Duration::toMillis).to(dataSource::setIdleTimeout);
		propertyMapper.from(poolConfiguration::getLeakDetectionThreshold).to(dataSource::setLeakDetectionThreshold);
		propertyMapper.from(poolConfiguration::getMaxLifetime).as(Duration::toMillis).to(dataSource::setMaxLifetime);
		propertyMapper.from(poolConfiguration::getKeepaliveTime).as(Duration::toMillis)
				.to(dataSource::setKeepaliveTime);
		propertyMapper.from(poolConfiguration::getMinIdle).to(dataSource::setMinimumIdle);
		propertyMapper.from(poolConfiguration::getMaxPoolSize).to(dataSource::setMaximumPoolSize);
		propertyMapper.from(poolConfiguration::getInitializationFailTimeout).as(Duration::toMillis)
				.to(dataSource::setInitializationFailTimeout);
		propertyMapper.from(poolConfiguration::getConnectionInitSql).to(dataSource::setConnectionInitSql);
		propertyMapper.from(poolConfiguration::getConnectionTestQuery).to(dataSource::setConnectionTestQuery);
		propertyMapper.from(poolConfiguration::getValidationTimeout).as(Duration::toMillis)
				.to(dataSource::setValidationTimeout);
		propertyMapper.from(poolConfiguration::getPoolName).to(dataSource::setPoolName);
		propertyMapper.from(poolConfiguration::getTransactionIsolation).as((v)->"TRANSACTION_" + v.name())
				.to(dataSource::setTransactionIsolation);
		propertyMapper.from(poolConfiguration::getAutoCommit).to(dataSource::setAutoCommit);
		propertyMapper.from(poolConfiguration::getReadOnly).to(dataSource::setReadOnly);
		propertyMapper.from(poolConfiguration::getIsolateInternalQueries).to(dataSource::setIsolateInternalQueries);
		propertyMapper.from(poolConfiguration::getRegisterMbeans).to(dataSource::setRegisterMbeans);
		propertyMapper.from(poolConfiguration::getAllowPoolSuspension).to(dataSource::setAllowPoolSuspension);
		propertyMapper.from(poolConfiguration::getThreadFactory).to(dataSource::setThreadFactory);
		propertyMapper.from(poolConfiguration::getScheduledExecutor).to(dataSource::setScheduledExecutor);
		propertyMapper.from(poolConfiguration::getMetricsTrackerFactory).to(dataSource::setMetricsTrackerFactory);
		propertyMapper.from(poolConfiguration::getMetricRegistry).to(dataSource::setMetricRegistry);
		propertyMapper.from(poolConfiguration::getHealthCheckRegistry).to(dataSource::setHealthCheckRegistry);
		propertyMapper.from(poolConfiguration::getHealthCheckProperties).to(dataSource::setHealthCheckProperties);
		propertyMapper.from(poolConfiguration::getProperties).to(dataSource::setDataSourceProperties);
	}

}
