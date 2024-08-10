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

import com.buession.core.builder.ListBuilder;
import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.jdbc.core.TransactionIsolation;
import com.buession.jdbc.datasource.config.Dbcp2PoolConfiguration;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.PreparedStatement;
import java.time.Duration;
import java.util.Collection;
import java.util.Set;

/**
 * DBCP2 DataSource
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class Dbcp2DataSource extends AbstractDataSource<BasicDataSource, Dbcp2PoolConfiguration> {

	/**
	 * 在第一次创建时用来初始化物理连接的SQL语句集合，只在配置的连接工厂创建连接时被执行一次
	 *
	 * @since 3.0.0
	 */
	private Collection<String> connectionInitSqls;

	/**
	 * 实现 {@link org.apache.commons.dbcp2.ConnectionFactory} 接口，连接工厂实现类
	 *
	 * @since 3.0.0
	 */
	private String connectionFactoryClassName;

	/**
	 * 连接的最大存活时间；
	 * 如果超过这个时间，则连接在下次激活、钝化、校验时都将会失败。如果设置为0或小于0的值，则连接的存活时间是无限的
	 *
	 * @since 3.0.0
	 */
	private Duration maxConnLifetime;

	/**
	 * 验证快速失败
	 *
	 * @since 3.0.0
	 */
	private Boolean fastFailValidation;

	/**
	 * 一个过期的连接被连接池关闭时，写日志标识；
	 * 如果连接存货时间超过 maxConnLifetimeMillis，连接将被连接池会抽，此时默认输出日志
	 *
	 * @since 3.0.0
	 */
	private Boolean logExpiredConnections;

	/**
	 * 连接归还到池时，设置为自动提交
	 *
	 * @since 3.0.0
	 */
	private Boolean autoCommitOnReturn;

	/**
	 * 连接归还到池时，是否回滚所有操作
	 *
	 * @since 3.0.0
	 */
	private Boolean rollbackOnReturn;

	/**
	 * 缓存状态，如果设置为true，
	 * 池化的连接将在第一次读或写，以及随后的写的时候缓存当前的只读状态和自动提交设置。
	 * 这样就省去了对 getter 的任何进一步的调用时对数据库的额外查询。
	 * 如果直接访问底层连接，只读状态和/或自动提交设置改变缓存值将不会被反映到当前的状态，在这种情况下，应该将该属性设置为false以禁用缓存
	 *
	 * @since 3.0.0
	 */
	private Boolean cacheState;

	/**
	 * 用于指定一组 SQL 错误代码，当连接池检测到这些错误代码时，会将连接标记为失效并将其从连接池中移除
	 *
	 * @since 3.0.0
	 */
	private Set<String> disconnectionSqlCodes;

	/**
	 * 构造函数
	 */
	public Dbcp2DataSource() {
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
	public Dbcp2DataSource(String driverClassName, String url) {
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
	public Dbcp2DataSource(String driverClassName, String url, String username) {
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
	public Dbcp2DataSource(String driverClassName, String url, String username, String password) {
		super(driverClassName, url, username, password);
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfiguration
	 * 		连接池配置
	 */
	public Dbcp2DataSource(Dbcp2PoolConfiguration poolConfiguration) {
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
	public Dbcp2DataSource(String driverClassName, String url, Dbcp2PoolConfiguration poolConfiguration) {
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
	public Dbcp2DataSource(String driverClassName, String url, String username,
						   Dbcp2PoolConfiguration poolConfiguration) {
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
	public Dbcp2DataSource(String driverClassName, String url, String username, String password,
						   Dbcp2PoolConfiguration poolConfiguration) {
		super(driverClassName, url, username, password, poolConfiguration);
	}

	@Override
	public void setInitSQL(String initSQL) {
		super.setInitSQL(initSQL);
		setConnectionInitSqls(ListBuilder.of(initSQL));
	}

	/**
	 * 返回在第一次创建时用来初始化物理连接的SQL语句集合
	 *
	 * @return 在第一次创建时用来初始化物理连接的SQL语句集合
	 *
	 * @since 3.0.0
	 */
	public Collection<String> getConnectionInitSqls() {
		return connectionInitSqls;
	}

	/**
	 * 设置在第一次创建时用来初始化物理连接的SQL语句集合
	 *
	 * @param connectionInitSqls
	 * 		在第一次创建时用来初始化物理连接的SQL语句集合
	 *
	 * @since 3.0.0
	 */
	public void setConnectionInitSqls(Collection<String> connectionInitSqls) {
		this.connectionInitSqls = connectionInitSqls;
	}

	/**
	 * 返回 {@link org.apache.commons.dbcp2.ConnectionFactory} 接口实现，连接工厂实现
	 *
	 * @return {@link org.apache.commons.dbcp2.ConnectionFactory} 接口实现，连接工厂实现类
	 *
	 * @since 3.0.0
	 */
	public String getConnectionFactoryClassName() {
		return connectionFactoryClassName;
	}

	/**
	 * 设置{@link org.apache.commons.dbcp2.ConnectionFactory} 接口实现，连接工厂实现类
	 *
	 * @param connectionFactoryClassName
	 *        {@link org.apache.commons.dbcp2.ConnectionFactory} 接口实现，连接工厂实现类
	 *
	 * @since 3.0.0
	 */
	public void setConnectionFactoryClassName(String connectionFactoryClassName) {
		this.connectionFactoryClassName = connectionFactoryClassName;
	}

	/**
	 * 返回连接的最大存活时间
	 *
	 * @return 连接的最大存活时间
	 *
	 * @since 3.0.0
	 */
	public Duration getMaxConnLifetime() {
		return maxConnLifetime;
	}

	/**
	 * 设置连接的最大存活时间
	 *
	 * @param maxConnLifetime
	 * 		连接的最大存活时间
	 *
	 * @since 3.0.0
	 */
	public void setMaxConnLifetime(Duration maxConnLifetime) {
		this.maxConnLifetime = maxConnLifetime;
	}

	/**
	 * 返回验证快速失败
	 *
	 * @return 验证快速失败
	 *
	 * @since 3.0.0
	 */
	public Boolean isFastFailValidation() {
		return getFastFailValidation();
	}

	/**
	 * 返回验证快速失败
	 *
	 * @return 验证快速失败
	 *
	 * @since 3.0.0
	 */
	public Boolean getFastFailValidation() {
		return fastFailValidation;
	}

	/**
	 * 设置验证快速失败
	 *
	 * @param fastFailValidation
	 * 		验证快速失败
	 *
	 * @since 3.0.0
	 */
	public void setFastFailValidation(Boolean fastFailValidation) {
		this.fastFailValidation = fastFailValidation;
	}

	/**
	 * 返回一个过期的连接被连接池关闭时，写日志标识
	 *
	 * @return 一个过期的连接被连接池关闭时，写日志标识
	 *
	 * @since 3.0.0
	 */
	public Boolean isLogExpiredConnections() {
		return getLogExpiredConnections();
	}

	/**
	 * 返回一个过期的连接被连接池关闭时，写日志标识
	 *
	 * @return 一个过期的连接被连接池关闭时，写日志标识
	 *
	 * @since 3.0.0
	 */
	public Boolean getLogExpiredConnections() {
		return logExpiredConnections;
	}

	/**
	 * 设置一个过期的连接被连接池关闭时，写日志标识
	 *
	 * @param logExpiredConnections
	 * 		一个过期的连接被连接池关闭时，写日志标识
	 *
	 * @since 3.0.0
	 */
	public void setLogExpiredConnections(Boolean logExpiredConnections) {
		this.logExpiredConnections = logExpiredConnections;
	}

	/**
	 * 返回连接归还到池时，设置为自动提交
	 *
	 * @return 连接归还到池时，设置为自动提交
	 *
	 * @since 3.0.0
	 */
	public Boolean isAutoCommitOnReturn() {
		return getAutoCommitOnReturn();
	}

	/**
	 * 返回连接归还到池时，设置为自动提交
	 *
	 * @return 连接归还到池时，设置为自动提交
	 *
	 * @since 3.0.0
	 */
	public Boolean getAutoCommitOnReturn() {
		return autoCommitOnReturn;
	}

	/**
	 * 设置连接归还到池时，是否自动提交
	 *
	 * @param autoCommitOnReturn
	 * 		连接归还到池时，是否自动提交
	 *
	 * @since 3.0.0
	 */
	public void setAutoCommitOnReturn(Boolean autoCommitOnReturn) {
		this.autoCommitOnReturn = autoCommitOnReturn;
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
	 * 返回缓存状态
	 *
	 * @return 缓存状态
	 *
	 * @since 3.0.0
	 */
	public Boolean isCacheState() {
		return getCacheState();
	}

	/**
	 * 返回缓存状态
	 *
	 * @return 缓存状态
	 *
	 * @since 3.0.0
	 */
	public Boolean getCacheState() {
		return cacheState;
	}

	/**
	 * 设置缓存状态
	 *
	 * @param cacheState
	 * 		缓存状态
	 *
	 * @since 3.0.0
	 */
	public void setCacheState(Boolean cacheState) {
		this.cacheState = cacheState;
	}

	/**
	 * 返回指定一组 SQL 错误代码，当连接池检测到这些错误代码时，会将连接标记为失效并将其从连接池中移除
	 *
	 * @return 指定的 SQL 错误代码，当连接池检测到这些错误代码时，会将连接标记为失效并将其从连接池中移除
	 *
	 * @since 3.0.0
	 */
	public Set<String> getDisconnectionSqlCodes() {
		return disconnectionSqlCodes;
	}

	/**
	 * 设置一组 SQL 错误代码，当连接池检测到这些错误代码时，会将连接标记为失效并将其从连接池中移除
	 *
	 * @param disconnectionSqlCodes
	 * 		一组 SQL 错误代码，当连接池检测到这些错误代码时，会将连接标记为失效并将其从连接池中移除
	 *
	 * @since 3.0.0
	 */
	public void setDisconnectionSqlCodes(Set<String> disconnectionSqlCodes) {
		this.disconnectionSqlCodes = disconnectionSqlCodes;
	}

	@Override
	public BasicDataSource createDataSource() {
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenHasText();
		final BasicDataSource dataSource = new BasicDataSource();

		propertyMapper.from(this::getDriverClassLoader).to(dataSource::setDriverClassLoader);
		propertyMapper.from(this::getDriverClassName).to(dataSource::setDriverClassName);
		propertyMapper.from(this::getUrl).to(dataSource::setUrl);
		propertyMapper.from(this::getUsername).to(dataSource::setUsername);
		propertyMapper.from(this::getPassword).to(dataSource::setPassword);
		propertyMapper.from(this::getDefaultCatalog).to(dataSource::setDefaultCatalog);
		propertyMapper.from(this::getDefaultSchema).to(dataSource::setDefaultSchema);
		propertyMapper.from(this::getConnectionInitSqls).to(dataSource::setConnectionInitSqls);
		propertyMapper.from(this::getValidationQuery).to(dataSource::setValidationQuery);
		propertyMapper.from(this::getValidationQueryTimeout).to(dataSource::setValidationQueryTimeout);
		propertyMapper.from(this::getQueryTimeout).to(dataSource::setDefaultQueryTimeout);
		propertyMapper.from(this::getDefaultTransactionIsolation).as(TransactionIsolation::getValue)
				.to(dataSource::setDefaultTransactionIsolation);
		propertyMapper.from(this::getDefaultReadOnly).to(dataSource::setDefaultReadOnly);
		propertyMapper.from(this::getDefaultAutoCommit).to(dataSource::setDefaultAutoCommit);
		propertyMapper.from(this::getAutoCommitOnReturn).to(dataSource::setAutoCommitOnReturn);
		propertyMapper.from(this::getRollbackOnReturn).to(dataSource::setRollbackOnReturn);
		propertyMapper.from(this::getAccessToUnderlyingConnectionAllowed)
				.to(dataSource::setAccessToUnderlyingConnectionAllowed);
		propertyMapper.from(this::getConnectionFactoryClassName).to(dataSource::setConnectionFactoryClassName);
		propertyMapper.from(this::getMaxConnLifetime).to(dataSource::setMaxConn);
		propertyMapper.from(this::getFastFailValidation).to(dataSource::setFastFailValidation);
		propertyMapper.from(this::getLogExpiredConnections).to(dataSource::setLogExpiredConnections);
		propertyMapper.from(this::getCacheState).to(dataSource::setCacheState);
		propertyMapper.from(this::getDisconnectionSqlCodes).to(dataSource::setDisconnectionSqlCodes);

		if(this.getConnectionProperties() != null){
			this.getConnectionProperties().forEach((name, value)->{
				if(value != null){
					dataSource.addConnectionProperty(name.toString(), value.toString());
				}
			});
		}

		if(getPoolConfiguration() != null){
			applyPoolConfiguration(dataSource, propertyMapper);
		}

		return dataSource;
	}

	protected void applyPoolConfiguration(final BasicDataSource dataSource, final PropertyMapper propertyMapper) {
		final Dbcp2PoolConfiguration poolConfiguration = getPoolConfiguration();

		propertyMapper.from(poolConfiguration::getInitialSize).to(dataSource::setInitialSize);
		propertyMapper.from(poolConfiguration::getMinIdle).to(dataSource::setMinIdle);
		propertyMapper.from(poolConfiguration::getMaxIdle).to(dataSource::setMaxIdle);
		propertyMapper.from(poolConfiguration::getMaxTotal).to(dataSource::setMaxTotal);
		propertyMapper.from(poolConfiguration::getMaxWait).to(dataSource::setMaxWait);
		propertyMapper.from(poolConfiguration::getTestOnCreate).to(dataSource::setTestOnCreate);
		propertyMapper.from(poolConfiguration::getTestOnBorrow).to(dataSource::setTestOnBorrow);
		propertyMapper.from(poolConfiguration::getTestOnReturn).to(dataSource::setTestOnReturn);
		propertyMapper.from(poolConfiguration::getTestWhileIdle).to(dataSource::setTestWhileIdle);
		propertyMapper.from(poolConfiguration::getPoolPreparedStatements).to(dataSource::setPoolPreparedStatements);
		propertyMapper.from(poolConfiguration::getMaxOpenPreparedStatements)
				.to(dataSource::setMaxOpenPreparedStatements);
		propertyMapper.from(poolConfiguration::getClearStatementPoolOnReturn)
				.to(dataSource::setClearStatementPoolOnReturn);
		propertyMapper.from(poolConfiguration::getRemoveAbandonedOnBorrow).to(dataSource::setRemoveAbandonedOnBorrow);
		propertyMapper.from(poolConfiguration::getRemoveAbandonedOnMaintenance)
				.to(dataSource::setRemoveAbandonedOnMaintenance);
		propertyMapper.from(poolConfiguration::getRemoveAbandonedTimeout).to(dataSource::setRemoveAbandonedTimeout);
		propertyMapper.from(poolConfiguration::getAbandonedUsageTracking).to(dataSource::setAbandonedUsageTracking);
		propertyMapper.from(poolConfiguration::getLogAbandoned).to(dataSource::setLogAbandoned);
		propertyMapper.from(poolConfiguration::getMinEvictableIdle).to(dataSource::setMinEvictableIdle);
		propertyMapper.from(poolConfiguration::getSoftMinEvictableIdle).to(dataSource::setSoftMinEvictableIdle);
		propertyMapper.from(poolConfiguration::getNumTestsPerEvictionRun).to(dataSource::setNumTestsPerEvictionRun);
		propertyMapper.from(poolConfiguration::getTimeBetweenEvictionRuns)
				.to(dataSource::setDurationBetweenEvictionRuns);
		propertyMapper.from(poolConfiguration::getEvictionPolicyClassName).to(dataSource::setEvictionPolicyClassName);
		propertyMapper.from(poolConfiguration::getLifo).to(dataSource::setLifo);
		propertyMapper.from(poolConfiguration::getRegisterConnectionMBean).to(dataSource::setRegisterConnectionMBean);
		propertyMapper.from(poolConfiguration::getJmxName).to(dataSource::setJmxName);
	}

}
