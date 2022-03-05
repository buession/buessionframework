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

import com.buession.core.validator.Validate;
import com.buession.jdbc.datasource.config.Dbcp2PoolConfiguration;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.concurrent.TimeUnit;

/**
 * DBCP2 DataSource 抽象类
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class Dbcp2DataSource extends AbstractDataSource<BasicDataSource, Dbcp2PoolConfiguration> {

	/**
	 * 构造函数
	 */
	public Dbcp2DataSource(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfiguration
	 * 		连接池配置
	 */
	public Dbcp2DataSource(Dbcp2PoolConfiguration poolConfiguration){
		super(poolConfiguration);
	}

	@Override
	public BasicDataSource createDataSource(){
		BasicDataSource dataSource = new BasicDataSource();

		applyPoolConfiguration(dataSource, getPoolConfiguration());

		return dataSource;
	}

	@Override
	protected void applyPoolConfiguration(final BasicDataSource dataSource, final Dbcp2PoolConfiguration poolConfiguration){
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

		if(poolConfiguration.getDefaultCatalog() != null){
			dataSource.setDefaultCatalog(poolConfiguration.getDefaultCatalog());
		}

		if(poolConfiguration.getMaxConnLifetime() != null){
			dataSource.setMaxConnLifetimeMillis(poolConfiguration.getMaxConnLifetime().toMillis());
		}

		if(poolConfiguration.getDefaultQueryTimeout() != null){
			dataSource.setDefaultQueryTimeout((int) TimeUnit.MILLISECONDS.toSeconds(poolConfiguration.getDefaultQueryTimeout().toMillis()));
		}

		dataSource.setInitialSize(poolConfiguration.getInitialSize());
		dataSource.setMinIdle(poolConfiguration.getMinIdle());
		dataSource.setMaxIdle(poolConfiguration.getMaxIdle());
		dataSource.setMaxTotal(poolConfiguration.getMaxTotal());

		if(poolConfiguration.getMaxWait() != null){
			dataSource.setMaxWaitMillis(poolConfiguration.getMaxWait().toMillis());
		}

		dataSource.setTestOnCreate(poolConfiguration.isTestOnCreate());
		dataSource.setTestOnBorrow(poolConfiguration.isTestOnBorrow());
		dataSource.setTestOnReturn(poolConfiguration.isTestOnReturn());
		dataSource.setTestWhileIdle(poolConfiguration.isTestWhileIdle());

		if(Validate.hasText(poolConfiguration.getConnectionFactoryClassName())){
			dataSource.setConnectionFactoryClassName(poolConfiguration.getConnectionFactoryClassName());
		}

		if(poolConfiguration.getConnectionInitSqls() != null){
			dataSource.setConnectionInitSqls(poolConfiguration.getConnectionInitSqls());
		}

		if(poolConfiguration.getValidationQuery() != null){
			dataSource.setValidationQuery(poolConfiguration.getValidationQuery());
		}

		if(poolConfiguration.getValidationQueryTimeout() != null){
			dataSource.setValidationQueryTimeout((int) TimeUnit.MILLISECONDS.toSeconds(poolConfiguration.getValidationQueryTimeout().toMillis()));
		}

		if(poolConfiguration.getDefaultSchema() != null){
			dataSource.setDefaultSchema(poolConfiguration.getDefaultSchema());
		}

		if(poolConfiguration.getDefaultTransactionIsolation() != null){
			dataSource.setDefaultTransactionIsolation(poolConfiguration.getDefaultTransactionIsolation().getValue());
		}

		dataSource.setDefaultAutoCommit(poolConfiguration.isDefaultAutoCommit());
		dataSource.setAutoCommitOnReturn(poolConfiguration.isAutoCommitOnReturn());
		dataSource.setRollbackOnReturn(poolConfiguration.isRollbackOnReturn());
		dataSource.setDefaultReadOnly(poolConfiguration.isDefaultReadOnly());
		dataSource.setLogExpiredConnections(poolConfiguration.isLogExpiredConnections());
		dataSource.setAccessToUnderlyingConnectionAllowed(poolConfiguration.isAccessToUnderlyingConnectionAllowed());
		dataSource.setCacheState(poolConfiguration.isCacheState());
		dataSource.setPoolPreparedStatements(poolConfiguration.isPoolPreparedStatements());
		dataSource.setMaxOpenPreparedStatements(poolConfiguration.getMaxOpenPreparedStatements());
		dataSource.setClearStatementPoolOnReturn(poolConfiguration.isClearStatementPoolOnReturn());

		if(poolConfiguration.getEvictionPolicyClassName() != null){
			dataSource.setEvictionPolicyClassName(poolConfiguration.getEvictionPolicyClassName());
		}

		if(poolConfiguration.getTimeBetweenEvictionRuns() != null){
			dataSource.setTimeBetweenEvictionRunsMillis(poolConfiguration.getTimeBetweenEvictionRuns().toMillis());
		}

		dataSource.setNumTestsPerEvictionRun(poolConfiguration.getNumTestsPerEvictionRun());

		if(poolConfiguration.getMinEvictableIdleTime() != null){
			dataSource.setMinEvictableIdleTimeMillis(poolConfiguration.getMinEvictableIdleTime().toMillis());
		}

		if(poolConfiguration.getSoftMinEvictableIdleTime() != null){
			dataSource.setSoftMinEvictableIdleTimeMillis(poolConfiguration.getSoftMinEvictableIdleTime().toMillis());
		}

		dataSource.setLifo(poolConfiguration.isLifo());
		dataSource.setFastFailValidation(poolConfiguration.isFastFailValidation());

		if(poolConfiguration.getDisconnectionSqlCodes() != null){
			dataSource.setDisconnectionSqlCodes(poolConfiguration.getDisconnectionSqlCodes());
		}

		if(poolConfiguration.getJmxName() != null){
			dataSource.setJmxName(poolConfiguration.getJmxName());
		}
	}

}
