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

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.jdbc.core.TransactionIsolation;
import com.buession.jdbc.datasource.config.Dbcp2PoolConfiguration;
import org.apache.commons.dbcp2.BasicDataSource;

import java.time.Duration;
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
	protected void applyPoolConfiguration(final BasicDataSource dataSource,
										  final Dbcp2PoolConfiguration poolConfiguration){
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();

		propertyMapper.from(poolConfiguration::getDefaultCatalog).to(dataSource::setDefaultCatalog);
		propertyMapper.from(poolConfiguration::getMaxConnLifetime).as(
				Duration::toMillis).to(dataSource::setMaxConnLifetimeMillis);
		propertyMapper.from(poolConfiguration::getDefaultQueryTimeout)
				.as((v)->(int) TimeUnit.MILLISECONDS.toSeconds(v.toMillis())).to(dataSource::setDefaultQueryTimeout);
		propertyMapper.from(poolConfiguration::getInitialSize).to(dataSource::setInitialSize);
		propertyMapper.from(poolConfiguration::getMinIdle).to(dataSource::setMinIdle);
		propertyMapper.from(poolConfiguration::getMaxIdle).to(dataSource::setMaxIdle);
		propertyMapper.from(poolConfiguration::getMaxTotal).to(dataSource::setMaxTotal);
		propertyMapper.from(poolConfiguration::getMaxWait).as(Duration::toMillis).to(dataSource::setMaxWaitMillis);
		propertyMapper.from(poolConfiguration::getTestOnCreate).to(dataSource::setTestOnCreate);
		propertyMapper.from(poolConfiguration::getTestOnBorrow).to(dataSource::setTestOnBorrow);
		propertyMapper.from(poolConfiguration::getTestOnReturn).to(dataSource::setTestOnReturn);
		propertyMapper.from(poolConfiguration::getTestWhileIdle).to(dataSource::setTestWhileIdle);
		propertyMapper.from(poolConfiguration::getConnectionFactoryClassName)
				.to(dataSource::setConnectionFactoryClassName);
		propertyMapper.from(poolConfiguration::getConnectionInitSqls).to(dataSource::setConnectionInitSqls);
		propertyMapper.from(poolConfiguration::getValidationQuery).to(dataSource::setValidationQuery);
		propertyMapper.from(poolConfiguration::getValidationQueryTimeout)
				.as((v)->(int) TimeUnit.MILLISECONDS.toSeconds(v.toMillis())).to(dataSource::setValidationQueryTimeout);
		propertyMapper.from(poolConfiguration::getDefaultSchema).to(dataSource::setDefaultSchema);
		propertyMapper.from(poolConfiguration::getDefaultTransactionIsolation).as(TransactionIsolation::getValue)
				.to(dataSource::setDefaultTransactionIsolation);
		propertyMapper.from(poolConfiguration::getDefaultAutoCommit).to(dataSource::setDefaultAutoCommit);
		propertyMapper.from(poolConfiguration::getAutoCommitOnReturn).to(dataSource::setAutoCommitOnReturn);
		propertyMapper.from(poolConfiguration::getRollbackOnReturn).to(dataSource::setRollbackOnReturn);
		propertyMapper.from(poolConfiguration::getDefaultReadOnly).to(dataSource::setDefaultReadOnly);
		propertyMapper.from(poolConfiguration::getLogExpiredConnections).to(dataSource::setLogExpiredConnections);
		propertyMapper.from(poolConfiguration::getAccessToUnderlyingConnectionAllowed)
				.to(dataSource::setAccessToUnderlyingConnectionAllowed);
		propertyMapper.from(poolConfiguration::getCacheState).to(dataSource::setCacheState);
		propertyMapper.from(poolConfiguration::getPoolPreparedStatements).to(dataSource::setPoolPreparedStatements);
		propertyMapper.from(poolConfiguration::getMaxOpenPreparedStatements)
				.to(dataSource::setMaxOpenPreparedStatements);
		propertyMapper.from(poolConfiguration::getClearStatementPoolOnReturn)
				.to(dataSource::setClearStatementPoolOnReturn);
		propertyMapper.from(poolConfiguration::getEvictionPolicyClassName).to(dataSource::setEvictionPolicyClassName);
		propertyMapper.from(poolConfiguration::getTimeBetweenEvictionRuns).as(
				Duration::toMillis).to(dataSource::setTimeBetweenEvictionRunsMillis);
		propertyMapper.from(poolConfiguration::getNumTestsPerEvictionRun).to(dataSource::setNumTestsPerEvictionRun);
		propertyMapper.from(poolConfiguration::getMinEvictableIdleTime).as(
				Duration::toMillis).to(dataSource::setMinEvictableIdleTimeMillis);
		propertyMapper.from(poolConfiguration::getSoftMinEvictableIdleTime).as(
				Duration::toMillis).to(dataSource::setSoftMinEvictableIdleTimeMillis);
		propertyMapper.from(poolConfiguration::getLifo).to(dataSource::setLifo);
		propertyMapper.from(poolConfiguration::getFastFailValidation).to(dataSource::setFastFailValidation);
		propertyMapper.from(poolConfiguration::getDisconnectionSqlCodes).to(dataSource::setDisconnectionSqlCodes);
		propertyMapper.from(poolConfiguration::getJmxName).to(dataSource::setJmxName);
	}

}
