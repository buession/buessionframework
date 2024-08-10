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

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.jdbc.core.TransactionIsolation;
import com.buession.jdbc.datasource.config.TomcatPoolConfiguration;

import java.time.Duration;
import java.util.Properties;

/**
 * Tomcat DataSource
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class TomcatDataSource
		extends AbstractDataSource<org.apache.tomcat.jdbc.pool.DataSource, TomcatPoolConfiguration> {

	/**
	 * JNDI 数据源的名称
	 *
	 * @since 3.0.0
	 */
	private String jndi;

	/**
	 * 是否允许在同一个数据库连接中使用不同的用户名进行身份验证
	 *
	 * @since 3.0.0
	 */
	private Boolean alternateUsernameAllowed;

	/**
	 * 连接归还到池时，设置为自动提交
	 *
	 * @since 3.0.0
	 */
	private Boolean commitOnReturn;

	/**
	 * 连接归还到池时，是否回滚所有操作
	 *
	 * @since 3.0.0
	 */
	private Boolean rollbackOnReturn;

	/**
	 * 实现 {@link org.apache.tomcat.jdbc.pool.Validator} 接口并提供无参构造方法的实现类，用来替代连接验证SQL，对连接进行验证
	 *
	 * @since 3.0.0
	 */
	private String validatorClassName;

	/**
	 * 验证连接时间间隔
	 *
	 * @since 3.0.0
	 */
	private Duration validationInterval;

	/**
	 * JDBC 拦截器，{@link org.apache.tomcat.jdbc.pool.JdbcInterceptor} 的实现
	 *
	 * @since 3.0.0
	 */
	private String jdbcInterceptors;

	/**
	 * 底层数据源相关的属性
	 *
	 * @since 3.0.0
	 */
	private Properties dbProperties;

	/**
	 * 构造函数
	 */
	public TomcatDataSource() {
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
	public TomcatDataSource(String driverClassName, String url) {
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
	public TomcatDataSource(String driverClassName, String url, String username) {
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
	public TomcatDataSource(String driverClassName, String url, String username, String password) {
		super(driverClassName, url, username, password);
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfiguration
	 * 		连接池配置
	 */
	public TomcatDataSource(TomcatPoolConfiguration poolConfiguration) {
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
	public TomcatDataSource(String driverClassName, String url, TomcatPoolConfiguration poolConfiguration) {
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
							TomcatPoolConfiguration poolConfiguration) {
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
							TomcatPoolConfiguration poolConfiguration) {
		super(driverClassName, url, username, password, poolConfiguration);
	}

	/**
	 * 返回 JNDI 数据源的名称
	 *
	 * @return JNDI 数据源的名称
	 *
	 * @since 3.0.0
	 */
	public String getJndi() {
		return jndi;
	}

	/**
	 * 设置 JNDI 数据源的名称
	 *
	 * @param jndi
	 * 		JNDI 数据源的名称
	 *
	 * @since 3.0.0
	 */
	public void setJndi(String jndi) {
		this.jndi = jndi;
	}

	/**
	 * 返回是否允许在同一个数据库连接中使用不同的用户名进行身份验证
	 *
	 * @return 是否允许在同一个数据库连接中使用不同的用户名进行身份验证
	 *
	 * @since 3.0.0
	 */
	public Boolean isAlternateUsernameAllowed() {
		return getAlternateUsernameAllowed();
	}

	/**
	 * 返回是否允许在同一个数据库连接中使用不同的用户名进行身份验证
	 *
	 * @return 是否允许在同一个数据库连接中使用不同的用户名进行身份验证
	 *
	 * @since 3.0.0
	 */
	public Boolean getAlternateUsernameAllowed() {
		return alternateUsernameAllowed;
	}

	/**
	 * 设置是否允许在同一个数据库连接中使用不同的用户名进行身份验证
	 *
	 * @param alternateUsernameAllowed
	 * 		是否允许在同一个数据库连接中使用不同的用户名进行身份验证
	 *
	 * @since 3.0.0
	 */
	public void setAlternateUsernameAllowed(Boolean alternateUsernameAllowed) {
		this.alternateUsernameAllowed = alternateUsernameAllowed;
	}

	/**
	 * 返回连接归还到池时，设置为自动提交
	 *
	 * @return 连接归还到池时，设置为自动提交
	 *
	 * @since 3.0.0
	 */
	public Boolean isCommitOnReturn() {
		return getCommitOnReturn();
	}

	/**
	 * 返回连接归还到池时，设置为自动提交
	 *
	 * @return 连接归还到池时，设置为自动提交
	 *
	 * @since 3.0.0
	 */
	public Boolean getCommitOnReturn() {
		return commitOnReturn;
	}

	/**
	 * 设置连接归还到池时，是否自动提交
	 *
	 * @param commitOnReturn
	 * 		连接归还到池时，是否自动提交
	 *
	 * @since 3.0.0
	 */
	public void setCommitOnReturn(Boolean commitOnReturn) {
		this.commitOnReturn = commitOnReturn;
	}

	/**
	 * 返回连接归还到池时，是否回滚所有操作
	 *
	 * @return 连接归还到池时，是否回滚所有操作
	 *
	 * @since 3.0.0
	 */
	public Boolean isRollbackOnReturn() {
		return getRollbackOnReturn();
	}

	/**
	 * 返回连接归还到池时，是否回滚所有操作
	 *
	 * @return 连接归还到池时，是否回滚所有操作
	 *
	 * @since 3.0.0
	 */
	public Boolean getRollbackOnReturn() {
		return rollbackOnReturn;
	}

	/**
	 * 设置连接归还到池时，是否回滚所有操作
	 *
	 * @param rollbackOnReturn
	 * 		连接归还到池时，是否回滚所有操作
	 *
	 * @since 3.0.0
	 */
	public void setRollbackOnReturn(Boolean rollbackOnReturn) {
		this.rollbackOnReturn = rollbackOnReturn;
	}

	/**
	 * 返回实现 {@link org.apache.tomcat.jdbc.pool.Validator} 接口并提供无参构造方法的实现类，用来替代连接验证SQL，对连接进行验证的类名
	 *
	 * @return 实现 {@link org.apache.tomcat.jdbc.pool.Validator} 接口并提供无参构造方法的实现类，用来替代连接验证SQL，对连接进行验证的类名
	 *
	 * @since 3.0.0
	 */
	public String getValidatorClassName() {
		return validatorClassName;
	}

	/**
	 * 设置实现 {@link org.apache.tomcat.jdbc.pool.Validator} 接口并提供无参构造方法的实现类，用来替代连接验证SQL，对连接进行验证的类名
	 *
	 * @param validatorClassName
	 * 		实现 {@link org.apache.tomcat.jdbc.pool.Validator} 接口并提供无参构造方法的实现类，用来替代连接验证SQL，对连接进行验证的类名
	 *
	 * @since 3.0.0
	 */
	public void setValidatorClassName(String validatorClassName) {
		this.validatorClassName = validatorClassName;
	}

	/**
	 * 返回验证连接时间间隔
	 *
	 * @return 验证连接时间间隔
	 *
	 * @since 3.0.0
	 */
	public Duration getValidationInterval() {
		return validationInterval;
	}

	/**
	 * 设置验证连接时间间隔
	 *
	 * @param validationInterval
	 * 		验证连接时间间隔
	 *
	 * @since 3.0.0
	 */
	public void setValidationInterval(Duration validationInterval) {
		this.validationInterval = validationInterval;
	}

	/**
	 * 返回 JDBC 拦截器，{@link org.apache.tomcat.jdbc.pool.JdbcInterceptor} 的实现
	 *
	 * @return JDBC 拦截器
	 *
	 * @since 3.0.0
	 */
	public String getJdbcInterceptors() {
		return jdbcInterceptors;
	}

	/**
	 * 设置 JDBC 拦截器，{@link org.apache.tomcat.jdbc.pool.JdbcInterceptor} 的实现
	 *
	 * @param jdbcInterceptors
	 * 		JDBC 拦截器
	 *
	 * @since 3.0.0
	 */
	public void setJdbcInterceptors(String jdbcInterceptors) {
		this.jdbcInterceptors = jdbcInterceptors;
	}

	/**
	 * 返回底层数据源相关的属性
	 *
	 * @return 底层数据源相关的属性
	 */
	public Properties getDbProperties() {
		return dbProperties;
	}

	/**
	 * 设置底层数据源相关的属性
	 *
	 * @param dbProperties
	 * 		底层数据源相关的属性
	 */
	public void setDbProperties(Properties dbProperties) {
		this.dbProperties = dbProperties;
		super.setConnectionProperties(dbProperties);
	}

	@Override
	public void setConnectionProperties(Properties connectionProperties) {
		setDbProperties(connectionProperties);
	}

	@Override
	protected org.apache.tomcat.jdbc.pool.DataSource createDataSource(final PropertyMapper propertyMapper) {
		final org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();

		propertyMapper.from(this::getDriverClassName).to(dataSource::setDriverClassName);
		propertyMapper.from(this::getJndi).to(dataSource::setDataSourceJNDI);
		propertyMapper.from(this::getUrl).to(dataSource::setUrl);
		propertyMapper.from(this::getUsername).to(dataSource::setUsername);
		propertyMapper.from(this::getPassword).to(dataSource::setPassword);
		propertyMapper.from(this::getLoginTimeout).as((v)->(int) v.getSeconds()).to(dataSource::setLoginTimeout);
		propertyMapper.from(this::isAlternateUsernameAllowed).to(dataSource::setAlternateUsernameAllowed);
		propertyMapper.from(this::getDefaultCatalog).to(dataSource::setDefaultCatalog);
		propertyMapper.from(this::getInitSQL).to(dataSource::setInitSQL);
		propertyMapper.from(this::getValidationQuery).to(dataSource::setValidationQuery);
		propertyMapper.from(this::getValidatorClassName).to(dataSource::setValidatorClassName);
		propertyMapper.from(this::getValidationInterval).as(Duration::toMillis).to(dataSource::setValidationInterval);
		propertyMapper.from(this::getValidationQueryTimeout).as((v)->(int) v.getSeconds())
				.to(dataSource::setValidationQueryTimeout);
		propertyMapper.from(this::getDefaultTransactionIsolation).as(TransactionIsolation::getValue)
				.to(dataSource::setDefaultTransactionIsolation);
		propertyMapper.from(this::isCommitOnReturn).to(dataSource::setCommitOnReturn);
		propertyMapper.from(this::isRollbackOnReturn).to(dataSource::setRollbackOnReturn);
		propertyMapper.from(this::isDefaultAutoCommit).to(dataSource::setDefaultAutoCommit);
		propertyMapper.from(this::isDefaultReadOnly).to(dataSource::setDefaultReadOnly);
		propertyMapper.from(this::isAccessToUnderlyingConnectionAllowed)
				.to(dataSource::setAccessToUnderlyingConnectionAllowed);
		propertyMapper.from(this::getJdbcInterceptors).to(dataSource::setJdbcInterceptors);
		propertyMapper.from(this::getDbProperties).to(dataSource::setDbProperties);

		return dataSource;
	}

	@Override
	protected void applyPoolConfiguration(final org.apache.tomcat.jdbc.pool.DataSource dataSource,
										  final TomcatPoolConfiguration poolConfiguration,
										  final PropertyMapper propertyMapper) {
		propertyMapper.from(poolConfiguration::getName).to(dataSource::setName);
		propertyMapper.from(poolConfiguration::getInitialSize).to(dataSource::setInitialSize);
		propertyMapper.from(poolConfiguration::getMinIdle).to(dataSource::setMinIdle);
		propertyMapper.from(poolConfiguration::getMaxIdle).to(dataSource::setMaxIdle);
		propertyMapper.from(poolConfiguration::getMaxActive).to(dataSource::setMaxActive);
		propertyMapper.from(poolConfiguration::getMaxAge).as(Duration::toMillis).to(dataSource::setMaxAge);
		propertyMapper.from(poolConfiguration::getMaxWait).as((v)->(int) v.toMillis()).to(dataSource::setMaxWait);
		propertyMapper.from(poolConfiguration::isTestOnConnect).to(dataSource::setTestOnConnect);
		propertyMapper.from(poolConfiguration::isTestOnBorrow).to(dataSource::setTestOnBorrow);
		propertyMapper.from(poolConfiguration::isTestOnReturn).to(dataSource::setTestOnReturn);
		propertyMapper.from(poolConfiguration::isTestWhileIdle).to(dataSource::setTestWhileIdle);
		propertyMapper.from(poolConfiguration::isRemoveAbandoned).to(dataSource::setRemoveAbandoned);
		propertyMapper.from(poolConfiguration::getRemoveAbandonedTimeout).as((v)->(int) v.getSeconds())
				.to(dataSource::setRemoveAbandonedTimeout);
		propertyMapper.from(poolConfiguration::getSuspectTimeout).as((v)->(int) v.getSeconds())
				.to(dataSource::setSuspectTimeout);
		propertyMapper.from(poolConfiguration::isUseDisposableConnectionFacade)
				.to(dataSource::setUseDisposableConnectionFacade);
		propertyMapper.from(poolConfiguration::isUseStatementFacade).to(dataSource::setUseStatementFacade);
		propertyMapper.from(poolConfiguration::isIgnoreExceptionOnPreLoad).to(dataSource::setIgnoreExceptionOnPreLoad);
		propertyMapper.from(poolConfiguration::isFairQueue).to(dataSource::setFairQueue);
		propertyMapper.from(poolConfiguration::isLogAbandoned).to(dataSource::setLogAbandoned);
		propertyMapper.from(poolConfiguration::getAbandonWhenPercentageFull)
				.to(dataSource::setAbandonWhenPercentageFull);
		propertyMapper.from(poolConfiguration::getNumTestsPerEvictionRun).to(dataSource::setNumTestsPerEvictionRun);
		propertyMapper.from(poolConfiguration::getTimeBetweenEvictionRuns).as((v)->(int) v.toMillis())
				.to(dataSource::setTimeBetweenEvictionRunsMillis);
		propertyMapper.from(poolConfiguration::getMinEvictableIdle).as((v)->(int) v.getSeconds())
				.to(dataSource::setMinEvictableIdleTimeMillis);
		propertyMapper.from(poolConfiguration::isPropagateInterruptState).to(dataSource::setPropagateInterruptState);
		propertyMapper.from(poolConfiguration::isLogValidationErrors).to(dataSource::setLogValidationErrors);
		propertyMapper.from(poolConfiguration::isUseLock).to(dataSource::setUseLock);
		propertyMapper.from(poolConfiguration::isUseEquals).to(dataSource::setUseEquals);
		propertyMapper.from(poolConfiguration::getJmxEnabled).to(dataSource::setJmxEnabled);

		if(Boolean.TRUE.equals(poolConfiguration.getFairQueue()) &&
				"Linux".equalsIgnoreCase(System.getProperties().getProperty("os.name"))){
			dataSource.getDbProperties().setProperty("org.apache.tomcat.jdbc.pool.FairBlockingQueue.ignoreOS", "true");
		}
	}

}
