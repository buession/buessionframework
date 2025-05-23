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
import com.buession.jdbc.core.Jmx;
import com.buession.jdbc.core.TransactionIsolation;
import com.buession.jdbc.datasource.pool.Dbcp2PoolConfiguration;
import org.apache.commons.dbcp2.BasicDataSource;

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
	protected BasicDataSource createDataSource(final PropertyMapper nullPropertyMapper,
											   final PropertyMapper hasTextPropertyMapper) {
		final BasicDataSource dataSource = new BasicDataSource();

		nullPropertyMapper.from(this::getDriverClassLoader).to(dataSource::setDriverClassLoader);
		hasTextPropertyMapper.from(this::getDriverClassName).to(dataSource::setDriverClassName);

		hasTextPropertyMapper.from(this::getUrl).to(dataSource::setUrl);
		hasTextPropertyMapper.from(this::getUsername).to(dataSource::setUsername);
		hasTextPropertyMapper.from(this::getPassword).to(dataSource::setPassword);

		hasTextPropertyMapper.from(this::getDefaultCatalog).to(dataSource::setDefaultCatalog);
		hasTextPropertyMapper.from(this::getDefaultSchema).to(dataSource::setDefaultSchema);

		hasTextPropertyMapper.from(this::getConnectionFactoryClassName).to(dataSource::setConnectionFactoryClassName);

		hasTextPropertyMapper.from(this::getConnectionInitSqls).to(dataSource::setConnectionInitSqls);

		nullPropertyMapper.from(this::isFastFailValidation).to(dataSource::setFastFailValidation);

		nullPropertyMapper.from(this::getQueryTimeout).to(dataSource::setDefaultQueryTimeout);

		nullPropertyMapper.from(this::getDefaultTransactionIsolation).as(TransactionIsolation::getValue)
				.to(dataSource::setDefaultTransactionIsolation);
		nullPropertyMapper.from(this::isDefaultAutoCommit).to(dataSource::setDefaultAutoCommit);
		nullPropertyMapper.from(this::isAutoCommitOnReturn).to(dataSource::setAutoCommitOnReturn);
		nullPropertyMapper.from(this::isRollbackOnReturn).to(dataSource::setRollbackOnReturn);

		nullPropertyMapper.from(this::isDefaultReadOnly).to(dataSource::setDefaultReadOnly);

		nullPropertyMapper.from(this::isAccessToUnderlyingConnectionAllowed)
				.to(dataSource::setAccessToUnderlyingConnectionAllowed);
		nullPropertyMapper.from(this::isLogExpiredConnections).to(dataSource::setLogExpiredConnections);
		nullPropertyMapper.from(this::isCacheState).to(dataSource::setCacheState);
		nullPropertyMapper.from(this::getDisconnectionSqlCodes).to(dataSource::setDisconnectionSqlCodes);

		if(this.getConnectionProperties() != null){
			this.getConnectionProperties().forEach((name, value)->{
				if(value != null){
					dataSource.addConnectionProperty(name.toString(), value.toString());
				}
			});
		}

		return dataSource;
	}

	@Override
	protected void applyPoolConfiguration(final BasicDataSource dataSource,
										  final Dbcp2PoolConfiguration poolConfiguration,
										  final PropertyMapper nullPropertyMapper,
										  final PropertyMapper hasTextPropertyMapper) {
		nullPropertyMapper.from(poolConfiguration::getInitialSize).to(dataSource::setInitialSize);
		nullPropertyMapper.from(poolConfiguration::getMinIdle).to(dataSource::setMinIdle);
		nullPropertyMapper.from(poolConfiguration::getMaxIdle).to(dataSource::setMaxIdle);
		nullPropertyMapper.from(poolConfiguration::getMaxTotal).to(dataSource::setMaxTotal);

		nullPropertyMapper.from(poolConfiguration::getMaxWait).to(dataSource::setMaxWait);
		nullPropertyMapper.from(poolConfiguration::getMaxConnLifetime).to(dataSource::setMaxConn);

		nullPropertyMapper.from(poolConfiguration::isTestOnCreate).to(dataSource::setTestOnCreate);
		nullPropertyMapper.from(poolConfiguration::isTestOnBorrow).to(dataSource::setTestOnBorrow);
		nullPropertyMapper.from(poolConfiguration::isTestOnReturn).to(dataSource::setTestOnReturn);
		nullPropertyMapper.from(poolConfiguration::isTestWhileIdle).to(dataSource::setTestWhileIdle);

		hasTextPropertyMapper.from(poolConfiguration::getValidationQuery).to(dataSource::setValidationQuery);
		nullPropertyMapper.from(poolConfiguration::getValidationQueryTimeout).to(dataSource::setValidationQueryTimeout);

		nullPropertyMapper.from(poolConfiguration::getMinEvictableIdle).to(dataSource::setMinEvictableIdle);
		nullPropertyMapper.from(poolConfiguration::getSoftMinEvictableIdle).to(dataSource::setSoftMinEvictableIdle);
		hasTextPropertyMapper.from(poolConfiguration::getEvictionPolicyClassName)
				.to(dataSource::setEvictionPolicyClassName);

		nullPropertyMapper.from(poolConfiguration::getNumTestsPerEvictionRun).to(dataSource::setNumTestsPerEvictionRun);
		nullPropertyMapper.from(poolConfiguration::getTimeBetweenEvictionRuns)
				.to(dataSource::setDurationBetweenEvictionRuns);

		nullPropertyMapper.from(poolConfiguration::isPoolPreparedStatements).to(dataSource::setPoolPreparedStatements);
		nullPropertyMapper.from(poolConfiguration::getMaxOpenPreparedStatements)
				.to(dataSource::setMaxOpenPreparedStatements);
		nullPropertyMapper.from(poolConfiguration::isClearStatementPoolOnReturn)
				.to(dataSource::setClearStatementPoolOnReturn);

		nullPropertyMapper.from(poolConfiguration::isRemoveAbandonedOnBorrow)
				.to(dataSource::setRemoveAbandonedOnBorrow);
		nullPropertyMapper.from(poolConfiguration::isRemoveAbandonedOnMaintenance)
				.to(dataSource::setRemoveAbandonedOnMaintenance);
		nullPropertyMapper.from(poolConfiguration::getRemoveAbandonedTimeout).to(dataSource::setRemoveAbandonedTimeout);
		nullPropertyMapper.from(poolConfiguration::isAbandonedUsageTracking).to(dataSource::setAbandonedUsageTracking);
		nullPropertyMapper.from(poolConfiguration::isLogAbandoned).to(dataSource::setLogAbandoned);

		nullPropertyMapper.from(poolConfiguration::isLifo).to(dataSource::setLifo);

		nullPropertyMapper.from(poolConfiguration::isRegisterConnectionMBean)
				.to(dataSource::setRegisterConnectionMBean);
		if(poolConfiguration.getJmx() != null){
			final Jmx jmx = poolConfiguration.getJmx();

			nullPropertyMapper.from(jmx.isEnabled()).to(dataSource::setRegisterConnectionMBean);
			hasTextPropertyMapper.from(jmx::getName).to(dataSource::setJmxName);
		}
	}

}
