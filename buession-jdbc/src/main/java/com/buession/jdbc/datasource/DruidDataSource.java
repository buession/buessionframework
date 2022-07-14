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
import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.core.validator.Validate;
import com.buession.jdbc.core.TransactionIsolation;
import com.buession.jdbc.datasource.config.DruidPoolConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.Duration;
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
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();

		propertyMapper.from(poolConfiguration::getName).to(dataSource::setName);
		propertyMapper.from(poolConfiguration::getDefaultCatalog).to(dataSource::setDefaultCatalog);
		propertyMapper.from(poolConfiguration::getDbTypeName).to(dataSource::setDbType);
		propertyMapper.from(poolConfiguration::getCreateScheduler).to(dataSource::setCreateScheduler);
		propertyMapper.from(poolConfiguration::getCreateScheduler).to(dataSource::setCreateScheduler);
		propertyMapper.from(poolConfiguration::getDestroyScheduler).to(dataSource::setDestroyScheduler);
		propertyMapper.from(poolConfiguration::getInitExceptionThrow).to(dataSource::setInitExceptionThrow);
		propertyMapper.from(poolConfiguration::getMaxCreateTaskCount).to(dataSource::setMaxCreateTaskCount);
		propertyMapper.from(poolConfiguration::getMaxWaitThreadCount).to(dataSource::setMaxWaitThreadCount);
		propertyMapper.from(poolConfiguration::getMaxWaitThreadCount).to(dataSource::setMaxWaitThreadCount);
		propertyMapper.from(poolConfiguration::getInitialSize).to(dataSource::setInitialSize);
		propertyMapper.from(poolConfiguration::getMaxActive).to(dataSource::setMaxActive);
		propertyMapper.from(poolConfiguration::getMinIdle).to(dataSource::setMinIdle);
		propertyMapper.from(poolConfiguration::getMaxIdle).to(dataSource::setMaxIdle);
		propertyMapper.from(poolConfiguration::getMaxWait).as(Duration::toMillis).to(dataSource::setMaxWait);
		propertyMapper.from(poolConfiguration::getTimeBetweenConnectError)
				.to(dataSource::setTimeBetweenConnectErrorMillis);
		propertyMapper.from(poolConfiguration::getConnectionErrorRetryAttempts)
				.to(dataSource::setConnectionErrorRetryAttempts);
		propertyMapper.from(poolConfiguration::getConnectionInitSqls).to(dataSource::setConnectionInitSqls);
		propertyMapper.from(poolConfiguration::getAsyncCloseConnectionEnable)
				.to(dataSource::setAsyncCloseConnectionEnable);
		propertyMapper.from(poolConfiguration::getAccessToUnderlyingConnectionAllowed)
				.to(dataSource::setAccessToUnderlyingConnectionAllowed);
		propertyMapper.from(poolConfiguration::getValidationQuery).to(dataSource::setValidationQuery);

		if(poolConfiguration.getValidConnectionCheckerClassName() != null){
			try{
				dataSource.setValidConnectionCheckerClassName(poolConfiguration.getValidConnectionCheckerClassName());
			}catch(Exception e){
				if(logger.isErrorEnabled()){
					logger.error("Set valid connection checker error: {}", e.getMessage());
				}
			}
		}

		propertyMapper.from(poolConfiguration::getValidationQueryTimeout).as((v)->(int) TimeUnit.MILLISECONDS.toSeconds(
				v.toMillis())).to(dataSource::setValidationQueryTimeout);
		propertyMapper.from(poolConfiguration::getQueryTimeout).as((v)->(int) TimeUnit.MILLISECONDS.toSeconds(
				v.toMillis())).to(dataSource::setQueryTimeout);
		propertyMapper.from(poolConfiguration::getNotFullTimeoutRetryCount).to(dataSource::setNotFullTimeoutRetryCount);
		propertyMapper.from(poolConfiguration::getTestOnBorrow).to(dataSource::setTestOnBorrow);
		propertyMapper.from(poolConfiguration::getTestOnReturn).to(dataSource::setTestOnReturn);
		propertyMapper.from(poolConfiguration::getTestWhileIdle).to(dataSource::setTestWhileIdle);
		propertyMapper.from(poolConfiguration::getTimeBetweenEvictionRuns).as(Duration::toMillis)
				.to(dataSource::setTimeBetweenEvictionRunsMillis);
		propertyMapper.from(poolConfiguration::getNumTestsPerEvictionRun).to(dataSource::setNumTestsPerEvictionRun);
		propertyMapper.from(poolConfiguration::getMinEvictableIdleTime).as(Duration::toMillis)
				.to(dataSource::setMinEvictableIdleTimeMillis);
		propertyMapper.from(poolConfiguration::getMaxEvictableIdleTime).as(Duration::toMillis)
				.to(dataSource::setMaxEvictableIdleTimeMillis);
		propertyMapper.from(poolConfiguration::getKeepAliveBetweenTime).as(Duration::toMillis)
				.to(dataSource::setKeepAliveBetweenTimeMillis);
		propertyMapper.from(poolConfiguration::getPhyTimeout).as(Duration::toMillis)
				.to(dataSource::setPhyTimeoutMillis);
		propertyMapper.from(poolConfiguration::getPhyMaxUseCount).to(dataSource::setPhyMaxUseCount);
		propertyMapper.from(poolConfiguration::getDefaultTransactionIsolation).as(TransactionIsolation::getValue)
				.to(dataSource::setDefaultTransactionIsolation);
		propertyMapper.from(poolConfiguration::getTransactionThreshold).to(dataSource::setTransactionThresholdMillis);
		propertyMapper.from(poolConfiguration::getTransactionQueryTimeout)
				.as((v)->(int) TimeUnit.MILLISECONDS.toSeconds(v.toMillis()))
				.to(dataSource::setTransactionQueryTimeout);
		propertyMapper.from(poolConfiguration::getDefaultAutoCommit).to(dataSource::setDefaultAutoCommit);
		propertyMapper.from(poolConfiguration::getDefaultReadOnly).to(dataSource::setDefaultReadOnly);
		propertyMapper.from(poolConfiguration::getDefaultReadOnly).to(dataSource::setDefaultReadOnly);
		propertyMapper.from(poolConfiguration::getPoolPreparedStatements).to(dataSource::setPoolPreparedStatements);
		propertyMapper.from(poolConfiguration::getMaxOpenPreparedStatements)
				.to(dataSource::setMaxOpenPreparedStatements);
		propertyMapper.from(poolConfiguration::getSharePreparedStatements).to(dataSource::setSharePreparedStatements);
		propertyMapper.from(poolConfiguration::getMaxPoolPreparedStatementPerConnectionSize)
				.to(dataSource::setMaxPoolPreparedStatementPerConnectionSize);

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

		propertyMapper.from(poolConfiguration::getClearFiltersEnable).to(dataSource::setClearFiltersEnable);

		try{
			dataSource.setExceptionSorterClassName(poolConfiguration.getExceptionSorterClassName());
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("Set exception sorter error: {}", e.getMessage());
			}
		}

		propertyMapper.from(poolConfiguration::getStatLogger).to(dataSource::setStatLogger);
		propertyMapper.from(poolConfiguration::getTimeBetweenLogStats).as(Duration::toMillis)
				.to(dataSource::setTimeBetweenLogStatsMillis);
		propertyMapper.from(poolConfiguration::getRemoveAbandoned).to(dataSource::setRemoveAbandoned);
		propertyMapper.from(poolConfiguration::getRemoveAbandonedTimeout).as(Duration::toMillis)
				.to(dataSource::setRemoveAbandonedTimeoutMillis);
		propertyMapper.from(poolConfiguration::getLogAbandoned).to(dataSource::setLogAbandoned);
		propertyMapper.from(poolConfiguration::getUseOracleImplicitCache).to(dataSource::setUseOracleImplicitCache);
		propertyMapper.from(poolConfiguration::getInitVariants).to(dataSource::setInitVariants);
		propertyMapper.from(poolConfiguration::getInitGlobalVariants).to(dataSource::setInitGlobalVariants);
		propertyMapper.from(poolConfiguration::getFailFast).to(dataSource::setFailFast);
		propertyMapper.from(poolConfiguration::getOnFatalErrorMaxActive).to(dataSource::setOnFatalErrorMaxActive);
		propertyMapper.from(poolConfiguration::getBreakAfterAcquireFailure).to(dataSource::setBreakAfterAcquireFailure);
		propertyMapper.from(poolConfiguration::getDupCloseLogEnable).to(dataSource::setDupCloseLogEnable);
		propertyMapper.from(poolConfiguration::getUseUnfairLock).to(dataSource::setUseUnfairLock);
		propertyMapper.from(poolConfiguration::getUseLocalSessionState).to(dataSource::setUseLocalSessionState);
		propertyMapper.from(poolConfiguration::getObjectName).to(dataSource::setObjectName);
	}

}
