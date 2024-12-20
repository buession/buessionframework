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
import com.buession.core.validator.Validate;
import com.buession.jdbc.core.Jmx;
import com.buession.jdbc.core.TransactionIsolation;
import com.buession.jdbc.datasource.pool.HikariPoolConfiguration;
import com.zaxxer.hikari.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Properties;

/**
 * Hikari DataSource
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class HikariDataSource extends AbstractDataSource<com.zaxxer.hikari.HikariDataSource, HikariPoolConfiguration> {

	/**
	 * JNDI 数据源的名称
	 *
	 * @since 3.0.0
	 */
	private String jndiName;

	/**
	 * 从连接池获取连接时最大等待时间
	 */
	private Duration connectionTimeout;

	/**
	 * 为支持 catalog 概念的数据库设置默认 catalog
	 *
	 * @since 3.0.0
	 */
	private String catalog;

	/**
	 * 设置的默认模式为支持模式的概念数据库；如果未指定此属性，则使用由JDBC驱动程序定义的默认模式
	 *
	 * @since 3.0.0
	 */
	private String schema;

	/**
	 * 设置一个SQL语句，在将每个新连接创建后，将其添加到池中之前执行该语句
	 *
	 * @since 3.0.0
	 */
	private String connectionInitSql;

	/**
	 * 默认事务隔离级别
	 *
	 * @since 3.0.0
	 */
	private TransactionIsolation transactionIsolation = TransactionIsolation.DEFAULT;

	/**
	 * 是否将 HikariCP 执行的内部查询与应用程序的查询隔离
	 */
	private Boolean isolateInternalQueries;

	/**
	 * 是否自动提交事务
	 *
	 * @since 3.0.0
	 */
	private Boolean autoCommit;

	/**
	 * 连接是否是只读模式
	 *
	 * @since 3.0.0
	 */
	private Boolean readOnly;

	/**
	 * 底层数据源相关的属性
	 *
	 * @since 3.0.0
	 */
	private Properties dataSourceProperties;

	private final static Logger logger = LoggerFactory.getLogger(HikariDataSource.class);

	/**
	 * 构造函数
	 */
	public HikariDataSource() {
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
	public HikariDataSource(String driverClassName, String url) {
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
	public HikariDataSource(String driverClassName, String url, String username) {
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
	public HikariDataSource(String driverClassName, String url, String username, String password) {
		super(driverClassName, url, username, password);
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfiguration
	 * 		连接池配置
	 */
	public HikariDataSource(HikariPoolConfiguration poolConfiguration) {
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
	public HikariDataSource(String driverClassName, String url, HikariPoolConfiguration poolConfiguration) {
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
							HikariPoolConfiguration poolConfiguration) {
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
							HikariPoolConfiguration poolConfiguration) {
		super(driverClassName, url, username, password, poolConfiguration);
	}

	/**
	 * 返回 JNDI 数据源的名称
	 *
	 * @return JNDI 数据源的名称
	 *
	 * @since 3.0.0
	 */
	public String getJndiName() {
		return jndiName;
	}

	/**
	 * 设置 JNDI 数据源的名称
	 *
	 * @param jndiName
	 * 		JNDI 数据源的名称
	 *
	 * @since 3.0.0
	 */
	public void setJndiName(String jndiName) {
		this.jndiName = jndiName;
	}

	/**
	 * 返回从连接池获取连接时最大等待时间
	 *
	 * @return 从连接池获取连接时最大等待时间
	 */
	public Duration getConnectionTimeout() {
		return connectionTimeout;
	}

	/**
	 * 设置从连接池获取连接时最大等待时间
	 *
	 * @param connectionTimeout
	 * 		从连接池获取连接时最大等待时间
	 */
	public void setConnectionTimeout(Duration connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * 返回为支持 catalog 概念的数据库设置默认 catalog
	 *
	 * @return 为支持 catalog 概念的数据库设置默认 catalog
	 *
	 * @since 3.0.0
	 */
	public String getCatalog() {
		return catalog;
	}

	/**
	 * 设置为支持 catalog 概念的数据库设置默认 catalog
	 *
	 * @param catalog
	 * 		为支持 catalog 概念的数据库设置默认 catalog
	 *
	 * @since 3.0.0
	 */
	public void setCatalog(String catalog) {
		this.catalog = catalog;
		super.setDefaultCatalog(catalog);
	}

	@Override
	public void setDefaultCatalog(String defaultCatalog) {
		setCatalog(defaultCatalog);
	}

	/**
	 * 返回默认模式为支持模式的概念数据库
	 *
	 * @return 默认模式为支持模式的概念数据库
	 *
	 * @since 3.0.0
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * 设置默认模式为支持模式的概念数据库
	 *
	 * @param schema
	 * 		默认模式为支持模式的概念数据库
	 *
	 * @since 3.0.0
	 */
	public void setSchema(String schema) {
		this.schema = schema;
		super.setDefaultSchema(schema);
	}

	@Override
	public void setDefaultSchema(String defaultSchema) {
		setSchema(defaultSchema);
	}

	/**
	 * 返回在将每个新连接创建后，将其添加到池中之前执行的SQL语句
	 *
	 * @return 每个新连接创建后，将其添加到池中之前执行的SQL语句
	 *
	 * @since 3.0.0
	 */
	public String getConnectionInitSql() {
		return connectionInitSql;
	}

	/**
	 * 设置每个新连接创建后，将其添加到池中之前执行的SQL语句
	 *
	 * @param connectionInitSql
	 * 		每个新连接创建后，将其添加到池中之前执行的SQL语句
	 *
	 * @since 3.0.0
	 */
	public void setConnectionInitSql(String connectionInitSql) {
		this.connectionInitSql = connectionInitSql;
		super.setInitSQL(connectionInitSql);
	}

	@Override
	public void setInitSQL(String initSQL) {
		setConnectionInitSql(initSQL);
	}

	/**
	 * 返回默认事务隔离级别
	 *
	 * @return 默认事务隔离级别
	 */
	public TransactionIsolation getTransactionIsolation() {
		return transactionIsolation;
	}

	/**
	 * 设置默认事务隔离级别
	 *
	 * @param transactionIsolation
	 * 		默认事务隔离级别
	 */
	public void setTransactionIsolation(TransactionIsolation transactionIsolation) {
		this.transactionIsolation = transactionIsolation;
		super.setDefaultTransactionIsolation(transactionIsolation);
	}

	@Override
	public void setDefaultTransactionIsolation(TransactionIsolation defaultTransactionIsolation) {
		setTransactionIsolation(defaultTransactionIsolation);
	}

	/**
	 * 返回是否将 HikariCP 执行的内部查询与应用程序的查询隔离
	 *
	 * @return 是否将 HikariCP 执行的内部查询与应用程序的查询隔离
	 */
	public Boolean isIsolateInternalQueries() {
		return getIsolateInternalQueries();
	}

	/**
	 * 返回是否将 HikariCP 执行的内部查询与应用程序的查询隔离
	 *
	 * @return 是否将 HikariCP 执行的内部查询与应用程序的查询隔离
	 */
	public Boolean getIsolateInternalQueries() {
		return isolateInternalQueries;
	}

	/**
	 * 设置是否将 HikariCP 执行的内部查询与应用程序的查询隔离
	 *
	 * @param isolateInternalQueries
	 * 		是否将 HikariCP 执行的内部查询与应用程序的查询隔离
	 */
	public void setIsolateInternalQueries(Boolean isolateInternalQueries) {
		this.isolateInternalQueries = isolateInternalQueries;
	}

	/**
	 * 返回是否自动提交事务
	 *
	 * @return 是否自动提交事务
	 */
	public Boolean isAutoCommit() {
		return getAutoCommit();
	}

	/**
	 * 返回是否自动提交事务
	 *
	 * @return 是否自动提交事务
	 */
	public Boolean getAutoCommit() {
		return autoCommit;
	}

	/**
	 * 设置是否自动提交事务
	 *
	 * @param autoCommit
	 * 		是否自动提交事务
	 */
	public void setAutoCommit(Boolean autoCommit) {
		this.autoCommit = autoCommit;
		super.setDefaultAutoCommit(autoCommit);
	}

	@Override
	public void setDefaultAutoCommit(Boolean defaultAutoCommit) {
		setAutoCommit(defaultAutoCommit);
	}

	/**
	 * 返回连接是否是只读模式
	 *
	 * @return 连接是否是只读模式
	 */
	public Boolean isReadOnly() {
		return getReadOnly();
	}

	/**
	 * 返回连接是否是只读模式
	 *
	 * @return 连接是否是只读模式
	 */
	public Boolean getReadOnly() {
		return readOnly;
	}

	/**
	 * 设置连接是否是只读模式
	 *
	 * @param readOnly
	 * 		连接是否是只读模式
	 */
	public void setReadOnly(Boolean readOnly) {
		this.readOnly = readOnly;
		super.setDefaultReadOnly(readOnly);
	}

	@Override
	public void setDefaultReadOnly(Boolean defaultReadOnly) {
		setReadOnly(defaultReadOnly);
	}

	/**
	 * 返回底层数据源相关的属性
	 *
	 * @return 底层数据源相关的属性
	 */
	public Properties getDataSourceProperties() {
		return dataSourceProperties;
	}

	/**
	 * 设置底层数据源相关的属性
	 *
	 * @param dataSourceProperties
	 * 		底层数据源相关的属性
	 */
	public void setDataSourceProperties(Properties dataSourceProperties) {
		this.dataSourceProperties = dataSourceProperties;
		super.setConnectionProperties(dataSourceProperties);
	}

	@Override
	public void setConnectionProperties(Properties connectionProperties) {
		setDataSourceProperties(connectionProperties);
	}

	@Override
	protected com.zaxxer.hikari.HikariDataSource createDataSource(final PropertyMapper nullPropertyMapper,
																  final PropertyMapper hasTextPropertyMapper) {
		final com.zaxxer.hikari.HikariDataSource dataSource = new com.zaxxer.hikari.HikariDataSource();

		hasTextPropertyMapper.from(this::getDriverClassName).to(dataSource::setDriverClassName);

		hasTextPropertyMapper.from(this::getJndiName).to(dataSource::setDataSourceJNDI);
		hasTextPropertyMapper.from(this::getUrl).to(dataSource::setJdbcUrl);
		hasTextPropertyMapper.from(this::getUsername).to(dataSource::setUsername);
		hasTextPropertyMapper.from(this::getPassword).to(dataSource::setPassword);

		nullPropertyMapper.from(this::getConnectionTimeout).as(Duration::toMillis).to(dataSource::setConnectionTimeout);

		hasTextPropertyMapper.from(this::getCatalog).to(dataSource::setCatalog);
		hasTextPropertyMapper.from(this::getSchema).to(dataSource::setSchema);

		hasTextPropertyMapper.from(this::getConnectionInitSql).to(dataSource::setConnectionInitSql);

		if(this.getTransactionIsolation() != null){
			if(this.getTransactionIsolation() != TransactionIsolation.DEFAULT){
				dataSource.setTransactionIsolation("TRANSACTION_" + this.getTransactionIsolation().name());
			}
		}

		nullPropertyMapper.from(this::isIsolateInternalQueries).to(dataSource::setIsolateInternalQueries);
		nullPropertyMapper.from(this::isAutoCommit).to(dataSource::setAutoCommit);

		nullPropertyMapper.from(this::isReadOnly).to(dataSource::setReadOnly);

		nullPropertyMapper.from(this::getDataSourceProperties).to(dataSource::setDataSourceProperties);

		return dataSource;
	}

	@Override
	protected void applyPoolConfiguration(final com.zaxxer.hikari.HikariDataSource dataSource,
										  final HikariPoolConfiguration poolConfiguration,
										  final PropertyMapper nullPropertyMapper,
										  final PropertyMapper hasTextPropertyMapper) {
		hasTextPropertyMapper.from(poolConfiguration::getPoolName).to(dataSource::setPoolName);

		nullPropertyMapper.from(poolConfiguration::getInitializationFailTimeout).as(Duration::toMillis)
				.to(dataSource::setInitializationFailTimeout);

		nullPropertyMapper.from(poolConfiguration::getMinIdle).to(dataSource::setMinimumIdle);
		nullPropertyMapper.from(poolConfiguration::getMaxPoolSize).to(dataSource::setMaximumPoolSize);

		hasTextPropertyMapper.from(poolConfiguration::getConnectionTestQuery).to(dataSource::setConnectionTestQuery);
		nullPropertyMapper.from(poolConfiguration::getValidationTimeout).as(Duration::toMillis)
				.to(dataSource::setValidationTimeout);

		nullPropertyMapper.from(poolConfiguration::getKeepaliveTime).as(Duration::toMillis)
				.to(dataSource::setKeepaliveTime);
		nullPropertyMapper.from(poolConfiguration::getIdleTimeout).as(Duration::toMillis)
				.to(dataSource::setIdleTimeout);
		nullPropertyMapper.from(poolConfiguration::getMaxLifetime).as(Duration::toMillis)
				.to(dataSource::setMaxLifetime);

		nullPropertyMapper.from(poolConfiguration::getThreadFactory).to(dataSource::setThreadFactory);
		nullPropertyMapper.from(poolConfiguration::getScheduledExecutor).to(dataSource::setScheduledExecutor);
		nullPropertyMapper.from(poolConfiguration::getLeakDetectionThreshold).as(Duration::toMillis)
				.to(dataSource::setLeakDetectionThreshold);
		nullPropertyMapper.from(poolConfiguration::isAllowPoolSuspension).to(dataSource::setAllowPoolSuspension);

		if(Validate.hasText(poolConfiguration.getMetricsTrackerFactoryClassName())){
			try{
				dataSource.setMetricsTrackerFactory(
						ObjectUtils.newInstance(poolConfiguration.getMetricsTrackerFactoryClassName()));
			}catch(IllegalStateException e){
				logger.error("load metricsTrackerFactory[{}] error : {}",
						poolConfiguration.getMetricsTrackerFactoryClassName(), e.getMessage());
			}
		}

		if(Validate.hasText(poolConfiguration.getMetricRegistryClassName())){
			try{
				dataSource.setMetricRegistry(
						ObjectUtils.newInstance(poolConfiguration.getMetricRegistryClassName()));
			}catch(IllegalStateException e){
				logger.error("load metricRegistry[{}] error : {}", poolConfiguration.getMetricRegistryClassName(),
						e.getMessage());
			}
		}

		if(Validate.hasText(poolConfiguration.getHealthCheckRegistryClassName())){
			try{
				dataSource.setHealthCheckRegistry(
						ObjectUtils.newInstance(poolConfiguration.getHealthCheckRegistryClassName()));
			}catch(IllegalStateException e){
				logger.error("load healthCheckRegistry[{}] error : {}",
						poolConfiguration.getHealthCheckRegistryClassName(), e.getMessage());
			}
		}

		nullPropertyMapper.from(poolConfiguration::getHealthCheckProperties).to(dataSource::setHealthCheckProperties);

		nullPropertyMapper.from(poolConfiguration::isRegisterMbeans).to(dataSource::setRegisterMbeans);
		if(poolConfiguration.getJmx() != null){
			final Jmx jmx = poolConfiguration.getJmx();

			nullPropertyMapper.from(jmx::isEnabled).to(dataSource::setRegisterMbeans);
		}
	}

}
