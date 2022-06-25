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

import com.buession.core.collect.Arrays;
import com.buession.core.validator.Validate;
import com.buession.jdbc.datasource.config.DruidPoolConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

/**
 * Druid DataSource 抽象类
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class DruidDataSource
		extends AbstractDataSource<com.alibaba.druid.pool.DruidDataSource, DruidPoolConfiguration> {

	private final static Logger logger = LoggerFactory.getLogger(DruidDataSource.class);

	/**
	 * 构造函数
	 */
	public DruidDataSource(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfiguration
	 * 		连接池配置
	 */
	public DruidDataSource(DruidPoolConfiguration poolConfiguration){
		super(poolConfiguration);
	}

	@Override
	public com.alibaba.druid.pool.DruidDataSource createDataSource(){
		com.alibaba.druid.pool.DruidDataSource dataSource = new com.alibaba.druid.pool.DruidDataSource();

		applyPoolConfiguration(dataSource, getPoolConfiguration());

		return dataSource;
	}

	@Override
	protected void applyPoolConfiguration(final com.alibaba.druid.pool.DruidDataSource dataSource,
										  final DruidPoolConfiguration poolConfiguration){
		if(poolConfiguration.getDriverClassName() != null){
			dataSource.setDriverClassName(poolConfiguration.getDriverClassName());
		}

		if(poolConfiguration.getUrl() != null){
			dataSource.setUrl(poolConfiguration.getUrl());
		}

		if(poolConfiguration.getUsername() != null){
			dataSource.setUsername(poolConfiguration.getUsername());
		}

		if(poolConfiguration.getPassword() != null){
			dataSource.setPassword(poolConfiguration.getPassword());
		}

		if(poolConfiguration.getName() != null){
			dataSource.setName(poolConfiguration.getName());
		}

		if(poolConfiguration.getDefaultCatalog() != null){
			dataSource.setDefaultCatalog(poolConfiguration.getDefaultCatalog());
		}

		if(poolConfiguration.getDbTypeName() != null){
			dataSource.setDbType(poolConfiguration.getDbTypeName());
		}

		if(poolConfiguration.getCreateScheduler() != null){
			dataSource.setCreateScheduler(poolConfiguration.getCreateScheduler());
		}

		if(poolConfiguration.getDestroyScheduler() != null){
			dataSource.setDestroyScheduler(poolConfiguration.getDestroyScheduler());
		}

		dataSource.setInitExceptionThrow(poolConfiguration.isInitExceptionThrow());
		dataSource.setMaxCreateTaskCount(poolConfiguration.getMaxCreateTaskCount());
		dataSource.setMaxWaitThreadCount(poolConfiguration.getMaxWaitThreadCount());
		dataSource.setInitialSize(poolConfiguration.getInitialSize());
		dataSource.setMaxActive(poolConfiguration.getMaxActive());
		dataSource.setMinIdle(poolConfiguration.getMinIdle());
		dataSource.setMaxIdle(poolConfiguration.getMaxIdle());

		if(poolConfiguration.getMaxWait() != null){
			dataSource.setMaxWait(poolConfiguration.getMaxWait().toMillis());
		}

		dataSource.setTimeBetweenConnectErrorMillis(poolConfiguration.getTimeBetweenConnectError());
		dataSource.setConnectionErrorRetryAttempts(poolConfiguration.getConnectionErrorRetryAttempts());

		if(poolConfiguration.getConnectionInitSqls() != null){
			dataSource.setConnectionInitSqls(poolConfiguration.getConnectionInitSqls());
		}

		dataSource.setAsyncCloseConnectionEnable(poolConfiguration.isAsyncCloseConnectionEnable());
		dataSource.setAccessToUnderlyingConnectionAllowed(poolConfiguration.isAccessToUnderlyingConnectionAllowed());

		if(poolConfiguration.getValidationQuery() != null){
			dataSource.setValidationQuery(poolConfiguration.getValidationQuery());
		}

		if(poolConfiguration.getValidConnectionCheckerClassName() != null){
			try{
				dataSource.setValidConnectionCheckerClassName(poolConfiguration.getValidConnectionCheckerClassName());
			}catch(Exception e){
				if(logger.isErrorEnabled()){
					logger.error("Set valid connection checker error: {}", e.getMessage());
				}
			}
		}

		if(poolConfiguration.getValidationQueryTimeout() != null){
			dataSource.setValidationQueryTimeout(
					(int) TimeUnit.MILLISECONDS.toSeconds(poolConfiguration.getValidationQueryTimeout().toMillis()));
		}

		if(poolConfiguration.getQueryTimeout() != null){
			dataSource.setQueryTimeout(
					(int) TimeUnit.MILLISECONDS.toSeconds(poolConfiguration.getQueryTimeout().toMillis()));
		}

		dataSource.setNotFullTimeoutRetryCount(poolConfiguration.getNotFullTimeoutRetryCount());
		dataSource.setTestOnBorrow(poolConfiguration.isTestOnBorrow());
		dataSource.setTestOnReturn(poolConfiguration.isTestOnReturn());
		dataSource.setTestWhileIdle(poolConfiguration.isTestWhileIdle());

		if(poolConfiguration.getTimeBetweenEvictionRuns() != null){
			dataSource.setTimeBetweenEvictionRunsMillis(poolConfiguration.getTimeBetweenEvictionRuns().toMillis());
		}

		dataSource.setNumTestsPerEvictionRun(poolConfiguration.getNumTestsPerEvictionRun());

		if(poolConfiguration.getMinEvictableIdleTime() != null){
			dataSource.setMinEvictableIdleTimeMillis(poolConfiguration.getMinEvictableIdleTime().toMillis());
		}

		if(poolConfiguration.getMaxEvictableIdleTime() != null){
			dataSource.setMaxEvictableIdleTimeMillis(poolConfiguration.getMaxEvictableIdleTime().toMillis());
		}

		if(poolConfiguration.getKeepAliveBetweenTime() != null){
			dataSource.setKeepAliveBetweenTimeMillis(poolConfiguration.getKeepAliveBetweenTime().toMillis());
		}

		if(poolConfiguration.getPhyTimeout() != null){
			dataSource.setPhyTimeoutMillis(poolConfiguration.getPhyTimeout().toMillis());
		}

		dataSource.setPhyMaxUseCount(poolConfiguration.getPhyMaxUseCount());

		if(poolConfiguration.getDefaultTransactionIsolation() != null){
			dataSource.setDefaultTransactionIsolation(poolConfiguration.getDefaultTransactionIsolation().getValue());
		}

		dataSource.setTransactionThresholdMillis(poolConfiguration.getTransactionThreshold());

		if(poolConfiguration.getTransactionQueryTimeout() != null){
			dataSource.setTransactionQueryTimeout(
					(int) TimeUnit.MILLISECONDS.toSeconds(poolConfiguration.getTransactionQueryTimeout().toMillis()));
		}

		dataSource.setDefaultAutoCommit(poolConfiguration.isDefaultAutoCommit());
		dataSource.setDefaultReadOnly(poolConfiguration.isDefaultReadOnly());
		dataSource.setPoolPreparedStatements(poolConfiguration.isPoolPreparedStatements());
		dataSource.setMaxOpenPreparedStatements(poolConfiguration.getMaxOpenPreparedStatements());
		dataSource.setSharePreparedStatements(poolConfiguration.isSharePreparedStatements());
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(
				poolConfiguration.getMaxPoolPreparedStatementPerConnectionSize());

		if(Validate.isNotEmpty(poolConfiguration.getFilters())){
			try{
				dataSource.setFilters(Arrays.toString(poolConfiguration.getFilters().toArray(new String[]{}), ","));
			}catch(SQLException e){
				if(logger.isErrorEnabled()){
					logger.error("Set filters error: {}(errorCode: {}, sqlState: {})", e.getMessage(), e.getErrorCode(),
							e.getSQLState());
				}
			}
		}

		dataSource.setClearFiltersEnable(poolConfiguration.isClearFiltersEnable());

		try{
			dataSource.setExceptionSorterClassName(poolConfiguration.getExceptionSorterClassName());
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("Set exception sorter error: {}", e.getMessage());
			}
		}

		if(poolConfiguration.getStatLogger() != null){
			dataSource.setStatLogger(poolConfiguration.getStatLogger());
		}

		dataSource.setTimeBetweenLogStatsMillis(poolConfiguration.getTimeBetweenLogStats());

		dataSource.setRemoveAbandoned(poolConfiguration.isRemoveAbandoned());

		if(poolConfiguration.getRemoveAbandonedTimeout() != null){
			dataSource.setRemoveAbandonedTimeoutMillis(poolConfiguration.getRemoveAbandonedTimeout().toMillis());
		}

		dataSource.setLogAbandoned(poolConfiguration.isLogAbandoned());
		dataSource.setUseOracleImplicitCache(poolConfiguration.isUseOracleImplicitCache());
		dataSource.setInitVariants(poolConfiguration.isInitVariants());
		dataSource.setInitGlobalVariants(poolConfiguration.isInitGlobalVariants());
		dataSource.setFailFast(poolConfiguration.isFailFast());
		dataSource.setOnFatalErrorMaxActive(poolConfiguration.getOnFatalErrorMaxActive());
		dataSource.setBreakAfterAcquireFailure(poolConfiguration.isBreakAfterAcquireFailure());
		dataSource.setDupCloseLogEnable(poolConfiguration.isDupCloseLogEnable());

		if(poolConfiguration.getUseUnfairLock() != null){
			dataSource.setUseUnfairLock(poolConfiguration.getUseUnfairLock());
		}

		dataSource.setUseLocalSessionState(poolConfiguration.isUseLocalSessionState());

		dataSource.setObjectName(poolConfiguration.getObjectName());
	}

}
