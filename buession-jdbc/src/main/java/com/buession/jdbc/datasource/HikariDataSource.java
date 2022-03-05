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
package com.buession.jdbc.datasource;

import com.buession.jdbc.datasource.config.HikariPoolConfiguration;

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
	 * @param poolConfiguration
	 * 		连接池配置
	 */
	public HikariDataSource(HikariPoolConfiguration poolConfiguration){
		super(poolConfiguration);
	}

	@Override
	public com.zaxxer.hikari.HikariDataSource createDataSource(){
		com.zaxxer.hikari.HikariDataSource dataSource = new com.zaxxer.hikari.HikariDataSource();

		applyPoolConfiguration(dataSource, getPoolConfiguration());

		return dataSource;
	}

	@Override
	protected void applyPoolConfiguration(final com.zaxxer.hikari.HikariDataSource dataSource, final HikariPoolConfiguration poolConfiguration){
		if(poolConfiguration.getDriverClassName() != null){
			dataSource.setDriverClassName(poolConfiguration.getDriverClassName());
		}

		if(poolConfiguration.getUrl() != null){
			dataSource.setJdbcUrl(poolConfiguration.getUrl());
		}

		if(poolConfiguration.getUsername() != null){
			dataSource.setUsername(poolConfiguration.getUsername());
		}

		if(poolConfiguration.getPassword() != null){
			dataSource.setPassword(poolConfiguration.getPassword());
		}

		if(poolConfiguration.getCatalog() != null){
			dataSource.setCatalog(poolConfiguration.getCatalog());
		}

		if(poolConfiguration.getConnectionTimeout() != null){
			dataSource.setConnectionTimeout(poolConfiguration.getConnectionTimeout().toMillis());
		}

		if(poolConfiguration.getIdleTimeout() != null){
			dataSource.setIdleTimeout(poolConfiguration.getIdleTimeout().toMillis());
		}

		dataSource.setLeakDetectionThreshold(poolConfiguration.getLeakDetectionThreshold());

		if(poolConfiguration.getMaxLifetime() != null){
			dataSource.setMaxLifetime(poolConfiguration.getMaxLifetime().toMillis());
		}

		if(poolConfiguration.getKeepaliveTime() != null){
			dataSource.setKeepaliveTime(poolConfiguration.getKeepaliveTime().toMillis());
		}

		if(poolConfiguration.getMinIdle() >= 0){
			dataSource.setMinimumIdle(poolConfiguration.getMinIdle());
		}

		if(poolConfiguration.getMaxPoolSize() >= 1){
			dataSource.setMaximumPoolSize(poolConfiguration.getMaxPoolSize());
		}

		if(poolConfiguration.getInitializationFailTimeout() != null){
			dataSource.setInitializationFailTimeout(poolConfiguration.getInitializationFailTimeout().toMillis());
		}

		if(poolConfiguration.getConnectionInitSql() != null){
			dataSource.setConnectionInitSql(poolConfiguration.getConnectionInitSql());
		}

		if(poolConfiguration.getConnectionTestQuery() != null){
			dataSource.setConnectionTestQuery(poolConfiguration.getConnectionTestQuery());
		}

		if(poolConfiguration.getValidationTimeout() != null){
			dataSource.setValidationTimeout(poolConfiguration.getValidationTimeout().toMillis());
		}

		if(poolConfiguration.getPoolName() != null){
			dataSource.setPoolName(poolConfiguration.getPoolName());
		}

		if(poolConfiguration.getTransactionIsolation() != null){
			dataSource.setTransactionIsolation("TRANSACTION_" + poolConfiguration.getTransactionIsolation().name());
		}

		dataSource.setAutoCommit(poolConfiguration.isAutoCommit());
		dataSource.setReadOnly(poolConfiguration.isReadOnly());
		dataSource.setIsolateInternalQueries(poolConfiguration.isIsolateInternalQueries());
		dataSource.setRegisterMbeans(poolConfiguration.isRegisterMbeans());
		dataSource.setAllowPoolSuspension(poolConfiguration.isAllowPoolSuspension());

		if(poolConfiguration.getThreadFactory() != null){
			dataSource.setThreadFactory(poolConfiguration.getThreadFactory());
		}

		if(poolConfiguration.getScheduledExecutor() != null){
			dataSource.setScheduledExecutor(poolConfiguration.getScheduledExecutor());
		}

		if(poolConfiguration.getMetricsTrackerFactory() != null){
			dataSource.setMetricsTrackerFactory(poolConfiguration.getMetricsTrackerFactory());
		}

		if(poolConfiguration.getMetricRegistry() != null){
			dataSource.setMetricRegistry(poolConfiguration.getMetricRegistry());
		}

		if(poolConfiguration.getHealthCheckRegistry() != null){
			dataSource.setHealthCheckRegistry(poolConfiguration.getHealthCheckRegistry());
		}

		if(poolConfiguration.getHealthCheckProperties() != null){
			dataSource.setHealthCheckProperties(poolConfiguration.getHealthCheckProperties());
		}

		if(poolConfiguration.getProperties() != null){
			dataSource.setDataSourceProperties(poolConfiguration.getProperties());
		}
	}

}
