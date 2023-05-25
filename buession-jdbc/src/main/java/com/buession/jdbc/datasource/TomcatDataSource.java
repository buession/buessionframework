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
import com.buession.jdbc.core.TransactionIsolation;
import com.buession.jdbc.datasource.config.TomcatPoolConfiguration;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Tomcat DataSource 抽象类
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class TomcatDataSource extends AbstractDataSource<DataSource, TomcatPoolConfiguration> {

	/**
	 * 构造函数
	 */
	public TomcatDataSource(){
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
	public TomcatDataSource(String driverClassName, String url){
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
	public TomcatDataSource(String driverClassName, String url, String username){
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
	public TomcatDataSource(String driverClassName, String url, String username, String password){
		super(driverClassName, url, username, password);
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfiguration
	 * 		连接池配置
	 */
	public TomcatDataSource(TomcatPoolConfiguration poolConfiguration){
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
	public TomcatDataSource(String driverClassName, String url, TomcatPoolConfiguration poolConfiguration){
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
	public TomcatDataSource(String driverClassName, String url, String username,
							TomcatPoolConfiguration poolConfiguration){
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
	public TomcatDataSource(String driverClassName, String url, String username, String password,
							TomcatPoolConfiguration poolConfiguration){
		super(driverClassName, url, username, password, poolConfiguration);
	}

	@Override
	public DataSource createDataSource(){
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();
		final DataSource dataSource = new DataSource();

		propertyMapper.from(this::getDriverClassName).to(dataSource::setDriverClassName);
		propertyMapper.from(this::getUrl).to(dataSource::setUrl);
		propertyMapper.from(this::getUsername).to(dataSource::setUsername);
		propertyMapper.from(this::getPassword).to(dataSource::setPassword);

		initialize(dataSource);

		return dataSource;
	}

	@Override
	protected void applyPoolConfiguration(final DataSource dataSource, final TomcatPoolConfiguration poolConfiguration){
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();

		propertyMapper.from(poolConfiguration::getName).to(dataSource::setName);
		propertyMapper.from(poolConfiguration::getDefaultCatalog).to(dataSource::setDefaultCatalog);
		propertyMapper.from(poolConfiguration::getInitialSize).to(dataSource::setInitialSize);
		propertyMapper.from(poolConfiguration::getMaxActive).to(dataSource::setMaxActive);
		propertyMapper.from(poolConfiguration::getMinIdle).to(dataSource::setMinIdle);
		propertyMapper.from(poolConfiguration::getMaxIdle).to(dataSource::setMaxIdle);
		propertyMapper.from(poolConfiguration::getMaxWait).as((v)->(int) TimeUnit.MILLISECONDS.toSeconds(v.toMillis()))
				.to(dataSource::setMaxWait);
		propertyMapper.from(poolConfiguration::getMaxAge).to(dataSource::setMaxAge);
		propertyMapper.from(poolConfiguration::getInitSQL).to(dataSource::setInitSQL);
		propertyMapper.from(poolConfiguration::getValidationQuery).to(dataSource::setValidationQuery);
		propertyMapper.from(poolConfiguration::getValidationInterval).as(
				Duration::toMillis).to(dataSource::setValidationInterval);
		propertyMapper.from(poolConfiguration::getValidationQueryTimeout)
				.as((v)->(int) TimeUnit.MILLISECONDS.toSeconds(v.toMillis())).to(dataSource::setValidationQueryTimeout);
		propertyMapper.from(poolConfiguration::getValidatorClassName).to(dataSource::setValidatorClassName);
		propertyMapper.from(poolConfiguration::getLogValidationErrors).to(dataSource::setLogValidationErrors);
		propertyMapper.from(poolConfiguration::getTestOnConnect).to(dataSource::setTestOnConnect);
		propertyMapper.from(poolConfiguration::getTestOnBorrow).to(dataSource::setTestOnBorrow);
		propertyMapper.from(poolConfiguration::getTestOnReturn).to(dataSource::setTestOnReturn);
		propertyMapper.from(poolConfiguration::getTestWhileIdle).to(dataSource::setTestWhileIdle);
		propertyMapper.from(poolConfiguration::getTimeBetweenEvictionRuns)
				.as((v)->(int) TimeUnit.MILLISECONDS.toSeconds(v.toMillis()))
				.to(dataSource::setTimeBetweenEvictionRunsMillis);
		propertyMapper.from(poolConfiguration::getNumTestsPerEvictionRun).to(dataSource::setNumTestsPerEvictionRun);
		propertyMapper.from(poolConfiguration::getMinEvictableIdleTime)
				.as((v)->(int) TimeUnit.MILLISECONDS.toSeconds(v.toMillis()))
				.to(dataSource::setMinEvictableIdleTimeMillis);
		propertyMapper.from(poolConfiguration::getDefaultTransactionIsolation).as(TransactionIsolation::getValue)
				.to(dataSource::setDefaultTransactionIsolation);
		propertyMapper.from(poolConfiguration::getDefaultAutoCommit).to(dataSource::setDefaultAutoCommit);
		propertyMapper.from(poolConfiguration::getCommitOnReturn).to(dataSource::setCommitOnReturn);
		propertyMapper.from(poolConfiguration::getRollbackOnReturn).to(dataSource::setRollbackOnReturn);
		propertyMapper.from(poolConfiguration::getDefaultReadOnly).to(dataSource::setDefaultReadOnly);
		propertyMapper.from(poolConfiguration::getRemoveAbandoned).to(dataSource::setRemoveAbandoned);
		propertyMapper.from(poolConfiguration::getRemoveAbandonedTimeout)
				.as((v)->(int) TimeUnit.MILLISECONDS.toSeconds(v.toMillis()))
				.to(dataSource::setRemoveAbandonedTimeout);
		propertyMapper.from(poolConfiguration::getLogAbandoned).to(dataSource::setLogAbandoned);
		propertyMapper.from(poolConfiguration::getAbandonWhenPercentageFull)
				.to(dataSource::setAbandonWhenPercentageFull);
		propertyMapper.from(poolConfiguration::getSuspectTimeout)
				.as((v)->(int) TimeUnit.MILLISECONDS.toSeconds(v.toMillis())).to(dataSource::setSuspectTimeout);
		propertyMapper.from(poolConfiguration::getAlternateUsernameAllowed).to(dataSource::setAlternateUsernameAllowed);
		propertyMapper.from(poolConfiguration::getJdbcInterceptors).to(dataSource::setJdbcInterceptors);
		propertyMapper.from(poolConfiguration::getIgnoreExceptionOnPreLoad).to(dataSource::setIgnoreExceptionOnPreLoad);
		propertyMapper.from(poolConfiguration::getUseEquals).to(dataSource::setUseEquals);
		propertyMapper.from(poolConfiguration::getUseLock).to(dataSource::setUseLock);
		propertyMapper.from(poolConfiguration::getFairQueue).to(dataSource::setFairQueue);
		propertyMapper.from(poolConfiguration::getUseDisposableConnectionFacade)
				.to(dataSource::setUseDisposableConnectionFacade);
		propertyMapper.from(poolConfiguration::getPropagateInterruptState).to(dataSource::setPropagateInterruptState);
		propertyMapper.from(poolConfiguration::getUseStatementFacade).to(dataSource::setUseStatementFacade);
		propertyMapper.from(poolConfiguration::getAccessToUnderlyingConnectionAllowed)
				.to(dataSource::setAccessToUnderlyingConnectionAllowed);
		propertyMapper.from(poolConfiguration::getJmxEnabled).to(dataSource::setJmxEnabled);

		if(Objects.equals(poolConfiguration.getFairQueue(), Boolean.TRUE) &&
				"Linux".equalsIgnoreCase(System.getProperties().getProperty("os.name"))){
			dataSource.getDbProperties().setProperty("org.apache.tomcat.jdbc.pool.FairBlockingQueue.ignoreOS", "true");
		}
	}

}
