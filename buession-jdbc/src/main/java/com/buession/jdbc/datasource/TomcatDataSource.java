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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.jdbc.datasource;

import com.buession.jdbc.datasource.config.TomcatPoolConfiguration;
import org.apache.tomcat.jdbc.pool.DataSource;

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
	 * @param poolConfiguration
	 * 		连接池配置
	 */
	public TomcatDataSource(TomcatPoolConfiguration poolConfiguration){
		super(poolConfiguration);
	}

	@Override
	public DataSource createDataSource(){
		DataSource dataSource = new DataSource();

		applyPoolConfiguration(dataSource, getPoolConfiguration());

		return dataSource;
	}

	@Override
	protected void applyPoolConfiguration(final DataSource dataSource, final TomcatPoolConfiguration poolConfiguration){
		if(poolConfiguration.getName() != null){
			dataSource.setName(poolConfiguration.getName());
		}

		if(poolConfiguration.getDefaultCatalog() != null){
			dataSource.setDefaultCatalog(poolConfiguration.getDefaultCatalog());
		}

		dataSource.setInitialSize(poolConfiguration.getInitialSize());
		dataSource.setMaxActive(poolConfiguration.getMaxActive());
		dataSource.setMinIdle(poolConfiguration.getMinIdle());
		dataSource.setMaxIdle(poolConfiguration.getMaxIdle());
		dataSource.setMaxWait(poolConfiguration.getMaxWait());
		dataSource.setMaxAge(poolConfiguration.getMaxAge());

		if(poolConfiguration.getInitSQL() != null){
			dataSource.setInitSQL(poolConfiguration.getInitSQL());
		}

		if(poolConfiguration.getValidationQuery() != null){
			dataSource.setValidationQuery(poolConfiguration.getValidationQuery());
		}

		dataSource.setValidationInterval(poolConfiguration.getValidationInterval());
		dataSource.setValidationQueryTimeout((int) TimeUnit.MILLISECONDS.toSeconds(poolConfiguration.getValidationQueryTimeout()));

		if(poolConfiguration.getValidatorClassName() != null){
			dataSource.setValidatorClassName(poolConfiguration.getValidatorClassName());
		}

		dataSource.setLogValidationErrors(poolConfiguration.isLogValidationErrors());

		dataSource.setTestOnConnect(poolConfiguration.isTestOnConnect());
		dataSource.setTestOnBorrow(poolConfiguration.isTestOnBorrow());
		dataSource.setTestOnReturn(poolConfiguration.isTestOnReturn());
		dataSource.setTestWhileIdle(poolConfiguration.isTestWhileIdle());
		dataSource.setTimeBetweenEvictionRunsMillis(poolConfiguration.getTimeBetweenEvictionRuns());
		dataSource.setNumTestsPerEvictionRun(poolConfiguration.getNumTestsPerEvictionRun());
		dataSource.setMinEvictableIdleTimeMillis(poolConfiguration.getMinEvictableIdleTime());

		if(poolConfiguration.getDefaultTransactionIsolation() != null){
			dataSource.setDefaultTransactionIsolation(poolConfiguration.getDefaultTransactionIsolation().getValue());
		}

		if(poolConfiguration.isDefaultAutoCommit() != null){
			dataSource.setDefaultAutoCommit(poolConfiguration.isDefaultAutoCommit());
		}

		dataSource.setCommitOnReturn(poolConfiguration.isCommitOnReturn());
		dataSource.setRollbackOnReturn(poolConfiguration.isRollbackOnReturn());

		if(poolConfiguration.isDefaultReadOnly() != null){
			dataSource.setDefaultReadOnly(poolConfiguration.isDefaultReadOnly());
		}

		dataSource.setRemoveAbandoned(poolConfiguration.isRemoveAbandoned());
		dataSource.setRemoveAbandonedTimeout((int) TimeUnit.MILLISECONDS.toSeconds(poolConfiguration.getRemoveAbandonedTimeout()));
		dataSource.setLogAbandoned(poolConfiguration.isLogAbandoned());
		dataSource.setAbandonWhenPercentageFull(poolConfiguration.getAbandonWhenPercentageFull());
		dataSource.setSuspectTimeout((int) TimeUnit.MILLISECONDS.toSeconds(poolConfiguration.getSuspectTimeout()));
		dataSource.setAlternateUsernameAllowed(poolConfiguration.isAlternateUsernameAllowed());

		if(poolConfiguration.getJdbcInterceptors() != null){
			dataSource.setJdbcInterceptors(poolConfiguration.getJdbcInterceptors());
		}

		dataSource.setIgnoreExceptionOnPreLoad(poolConfiguration.isIgnoreExceptionOnPreLoad());
		dataSource.setUseEquals(poolConfiguration.isUseEquals());
		dataSource.setUseLock(poolConfiguration.isUseLock());
		dataSource.setFairQueue(poolConfiguration.isFairQueue());
		dataSource.setUseDisposableConnectionFacade(poolConfiguration.isUseDisposableConnectionFacade());
		dataSource.setPropagateInterruptState(poolConfiguration.isPropagateInterruptState());
		dataSource.setUseStatementFacade(poolConfiguration.isUseStatementFacade());
		dataSource.setAccessToUnderlyingConnectionAllowed(poolConfiguration.isAccessToUnderlyingConnectionAllowed());
		dataSource.setJmxEnabled(poolConfiguration.isJmxEnabled());

		if(poolConfiguration.isFairQueue() && "Linux".equalsIgnoreCase(System.getProperties().getProperty("os.name"))){
			dataSource.getDbProperties().setProperty("org.apache.tomcat.jdbc.pool.FairBlockingQueue.ignoreOS", "true");
		}
	}

}
