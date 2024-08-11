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
import com.buession.core.utils.MethodUtils;
import com.buession.core.validator.Validate;
import com.buession.jdbc.datasource.pool.OraclePoolConfiguration;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Properties;

/**
 * Oracle DataSource
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class OracleDataSource extends AbstractDataSource<PoolDataSource, OraclePoolConfiguration> {

	/**
	 * 网络协议
	 */
	private String networkProtocol;

	/**
	 * 数据库服务器的名称或地址
	 */
	private String serverName;

	/**
	 * 数据库服务器的端口
	 */
	private Integer portNumber;

	/**
	 * 连接到 Oracle 数据库服务的服务名，服务名用于在 Oracle 数据库中标识特定的服务或数据库实例
	 */
	private String serviceName;

	/**
	 * 数据源名称
	 */
	private String dataSourceName;

	/**
	 * 数据源描述
	 */
	private String dataSourceDescription;

	/**
	 * 要连接的 Oracle 数据库的名称
	 */
	private String databaseName;

	/**
	 * 角色名称
	 */
	private String roleName;

	/**
	 * 连接到 Oracle Pluggable Database (PDB) 的角色的属性
	 */
	private Properties pdbRoles;

	/**
	 * 连接工厂类名
	 */
	private String connectionFactoryClassName;

	/**
	 * 是否启用快速连接故障切换
	 */
	private Boolean fastConnectionFailoverEnabled;

	/**
	 * 验证连接使用的 SQL
	 */
	private String sqlForValidateConnection;

	/**
	 * 连接验证操作的超时时间
	 */
	private Duration connectionValidationTimeout;

	/**
	 * Oracle Notification Services (ONS) 的属性
	 */
	private String onsConfiguration;

	/**
	 * 是否启用分片模式
	 */
	private Boolean shardingMode;

	/**
	 * 每个分片（Shard）中允许的最大连接数
	 */
	private Integer maxConnectionsPerShard;

	/**
	 * 每个服务的最大连接数
	 */
	private Integer maxConnectionsPerService;

	private final static Logger logger = LoggerFactory.getLogger(OracleDataSource.class);

	/**
	 * 构造函数
	 */
	public OracleDataSource() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param driverClassName
	 * 		数据库驱动类名
	 * @param url
	 * 		JDBC URL
	 */
	public OracleDataSource(String driverClassName, String url) {
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
	 */
	public OracleDataSource(String driverClassName, String url, String username) {
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
	 */
	public OracleDataSource(String driverClassName, String url, String username, String password) {
		super(driverClassName, url, username, password);
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfiguration
	 * 		连接池配置
	 */
	public OracleDataSource(OraclePoolConfiguration poolConfiguration) {
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
	 */
	public OracleDataSource(String driverClassName, String url, OraclePoolConfiguration poolConfiguration) {
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
	 */
	public OracleDataSource(String driverClassName, String url, String username,
							OraclePoolConfiguration poolConfiguration) {
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
	 */
	public OracleDataSource(String driverClassName, String url, String username, String password,
							OraclePoolConfiguration poolConfiguration) {
		super(driverClassName, url, username, password, poolConfiguration);
	}

	/**
	 * 返回网络协议
	 *
	 * @return 网络协议
	 */
	public String getNetworkProtocol() {
		return networkProtocol;
	}

	/**
	 * 设置网络协议
	 *
	 * @param networkProtocol
	 * 		网络协议
	 */
	public void setNetworkProtocol(String networkProtocol) {
		this.networkProtocol = networkProtocol;
	}

	/**
	 * 返回数据库服务器的名称或地址
	 *
	 * @return 数据库服务器的名称或地址
	 */
	public String getServerName() {
		return serverName;
	}

	/**
	 * 设置数据库服务器的名称或地址
	 *
	 * @param serverName
	 * 		数据库服务器的名称或地址
	 */
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	/**
	 * 返回数据库服务器的端口
	 *
	 * @return 数据库服务器的端口
	 */
	public Integer getPortNumber() {
		return portNumber;
	}

	/**
	 * 设置数据库服务器的端口
	 *
	 * @param portNumber
	 * 		数据库服务器的端口
	 */
	public void setPortNumber(Integer portNumber) {
		this.portNumber = portNumber;
	}

	/**
	 * 返回连接到 Oracle 数据库服务的服务名，服务名用于在 Oracle 数据库中标识特定的服务或数据库实例
	 *
	 * @return 连接到 Oracle 数据库服务的服务名
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * 设置连接到 Oracle 数据库服务的服务名，服务名用于在 Oracle 数据库中标识特定的服务或数据库实例
	 *
	 * @param serviceName
	 * 		连接到 Oracle 数据库服务的服务名
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * 返回数据源名称
	 *
	 * @return 数据源名称
	 */
	public String getDataSourceName() {
		return dataSourceName;
	}

	/**
	 * 设置数据源名称
	 *
	 * @param dataSourceName
	 * 		数据源名称
	 */
	public void setDataSourceName(String dataSourceName) {
		this.dataSourceName = dataSourceName;
	}

	/**
	 * 返回数据源描述
	 *
	 * @return 数据源描述
	 */
	public String getDataSourceDescription() {
		return dataSourceDescription;
	}

	/**
	 * 设置数据源描述
	 *
	 * @param dataSourceDescription
	 * 		数据源描述
	 */
	public void setDataSourceDescription(String dataSourceDescription) {
		this.dataSourceDescription = dataSourceDescription;
	}

	/**
	 * 返回要连接的 Oracle 数据库的名称
	 *
	 * @return 要连接的 Oracle 数据库的名称
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * 设置
	 *
	 * @param databaseName
	 * 		要连接的 Oracle 数据库的名称
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/**
	 * 返回角色名称
	 *
	 * @return 角色名称
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 设置角色名称
	 *
	 * @param roleName
	 * 		角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * 返回连接到 Oracle Pluggable Database (PDB) 的角色的属性
	 *
	 * @return 连接到 Oracle Pluggable Database (PDB) 的角色的属性
	 */
	public Properties getPdbRoles() {
		return pdbRoles;
	}

	/**
	 * 设置连接到 Oracle Pluggable Database (PDB) 的角色的属性
	 *
	 * @param pdbRoles
	 * 		连接到 Oracle Pluggable Database (PDB) 的角色的属性
	 */
	public void setPdbRoles(Properties pdbRoles) {
		this.pdbRoles = pdbRoles;
	}

	/**
	 * 返回连接工厂类名
	 *
	 * @return 连接工厂类名
	 */
	public String getConnectionFactoryClassName() {
		return connectionFactoryClassName;
	}

	/**
	 * 设置连接工厂类名
	 *
	 * @param connectionFactoryClassName
	 * 		连接工厂类名
	 */
	public void setConnectionFactoryClassName(String connectionFactoryClassName) {
		this.connectionFactoryClassName = connectionFactoryClassName;
	}

	/**
	 * 返回是否启用快速连接故障切换
	 *
	 * @return 是否启用快速连接故障切换
	 */
	public Boolean isFastConnectionFailoverEnabled() {
		return getFastConnectionFailoverEnabled();
	}

	/**
	 * 返回是否启用快速连接故障切换
	 *
	 * @return 是否启用快速连接故障切换
	 */
	public Boolean getFastConnectionFailoverEnabled() {
		return fastConnectionFailoverEnabled;
	}

	/**
	 * 设置是否启用快速连接故障切换
	 *
	 * @param fastConnectionFailoverEnabled
	 * 		是否启用快速连接故障切换
	 */
	public void setFastConnectionFailoverEnabled(Boolean fastConnectionFailoverEnabled) {
		this.fastConnectionFailoverEnabled = fastConnectionFailoverEnabled;
	}

	/**
	 * 返回验证连接使用的 SQL
	 *
	 * @return 验证连接使用的 SQL
	 */
	public String getSqlForValidateConnection() {
		return sqlForValidateConnection;
	}

	/**
	 * 设置验证连接使用的 SQL
	 *
	 * @param sqlForValidateConnection
	 * 		验证连接使用的 SQL
	 */
	public void setSqlForValidateConnection(String sqlForValidateConnection) {
		this.sqlForValidateConnection = sqlForValidateConnection;
		super.setValidationQuery(sqlForValidateConnection);
	}

	@Override
	public void setValidationQuery(String validationQuery) {
		setSqlForValidateConnection(validationQuery);
	}

	/**
	 * 返回连接验证操作的超时时间
	 *
	 * @return 连接验证操作的超时时间
	 */
	public Duration getConnectionValidationTimeout() {
		return connectionValidationTimeout;
	}

	/**
	 * 设置连接验证操作的超时时间
	 *
	 * @param connectionValidationTimeout
	 * 		连接验证操作的超时时间
	 */
	public void setConnectionValidationTimeout(Duration connectionValidationTimeout) {
		this.connectionValidationTimeout = connectionValidationTimeout;
		super.setValidationQueryTimeout(connectionValidationTimeout);
	}

	@Override
	public void setValidationQueryTimeout(Duration validationQueryTimeout) {
		setConnectionValidationTimeout(validationQueryTimeout);
	}

	/**
	 * 返回 Oracle Notification Services (ONS) 的属性
	 *
	 * @return Oracle Notification Services (ONS) 的属性
	 */
	public String getOnsConfiguration() {
		return onsConfiguration;
	}

	/**
	 * 设置 Oracle Notification Services (ONS) 的属性
	 *
	 * @param onsConfiguration
	 * 		Oracle Notification Services (ONS) 的属性
	 */
	public void setOnsConfiguration(String onsConfiguration) {
		this.onsConfiguration = onsConfiguration;
	}

	/**
	 * 返回是否启用分片模式
	 *
	 * @return 是否启用分片模式
	 */
	public Boolean isShardingMode() {
		return getShardingMode();
	}

	/**
	 * 返回是否启用分片模式
	 *
	 * @return 是否启用分片模式
	 */
	public Boolean getShardingMode() {
		return shardingMode;
	}

	/**
	 * 设置是否启用分片模式
	 *
	 * @param shardingMode
	 * 		是否启用分片模式
	 */
	public void setShardingMode(Boolean shardingMode) {
		this.shardingMode = shardingMode;
	}

	/**
	 * 返回每个分片（Shard）中允许的最大连接数
	 *
	 * @return 每个分片（Shard）中允许的最大连接数
	 */
	public Integer getMaxConnectionsPerShard() {
		return maxConnectionsPerShard;
	}

	/**
	 * 设置每个分片（Shard）中允许的最大连接数
	 *
	 * @param maxConnectionsPerShard
	 * 		每个分片（Shard）中允许的最大连接数
	 */
	public void setMaxConnectionsPerShard(Integer maxConnectionsPerShard) {
		this.maxConnectionsPerShard = maxConnectionsPerShard;
	}

	/**
	 * 返回每个服务的最大连接数
	 *
	 * @return 每个服务的最大连接数
	 */
	public Integer getMaxConnectionsPerService() {
		return maxConnectionsPerService;
	}

	/**
	 * 设置每个服务的最大连接数
	 *
	 * @param maxConnectionsPerService
	 * 		每个服务的最大连接数
	 */
	public void setMaxConnectionsPerService(Integer maxConnectionsPerService) {
		this.maxConnectionsPerService = maxConnectionsPerService;
	}

	@Override
	protected PoolDataSource createDataSource(final PropertyMapper propertyMapper) {
		final PoolDataSource dataSource = new PoolDataSourceImpl();

		try{
			if(Validate.hasText(this.getNetworkProtocol())){
				dataSource.setNetworkProtocol(this.getNetworkProtocol());
			}

			if(Validate.hasText(this.getUrl())){
				dataSource.setURL(this.getUrl());
			}
			if(Validate.hasText(this.getServerName())){
				dataSource.setServerName(this.getServerName());
			}
			if(this.getPortNumber() != null){
				dataSource.setPortNumber(this.getPortNumber());
			}
			if(Validate.hasText(this.getServiceName())){
				dataSource.setServerName(this.getServiceName());
			}
			if(Validate.hasText(this.getDataSourceName())){
				dataSource.setDataSourceName(this.getDataSourceName());
			}
			if(Validate.hasText(this.getDataSourceDescription())){
				dataSource.setDescription(this.getDataSourceDescription());
			}
			if(Validate.hasText(this.getDatabaseName())){
				dataSource.setDatabaseName(this.getDatabaseName());
			}
			if(Validate.hasText(this.getUsername())){
				dataSource.setUser(this.getUsername());
			}
			if(Validate.hasText(this.getPassword())){
				dataSource.setPassword(this.getPassword());
			}

			if(this.getLoginTimeout() != null){
				dataSource.setLoginTimeout((int) this.getLoginTimeout().getSeconds());
			}

			if(Validate.hasText(this.getConnectionFactoryClassName())){
				dataSource.setConnectionFactoryClassName(this.getConnectionFactoryClassName());
			}

			if(Validate.hasText(this.getRoleName())){
				dataSource.setRoleName(this.getRoleName());
			}
			if(this.getPdbRoles() != null){
				try{
					Method method = dataSource.getClass().getMethod("setPdbRoles", Properties.class);
					MethodUtils.setAccessible(method);
					method.invoke(dataSource, this.getPdbRoles());
				}catch(NoSuchMethodException e){
					//
				}catch(InvocationTargetException e){
					//
				}catch(IllegalAccessException e){
					//
				}
			}

			if(Validate.hasText(this.getSqlForValidateConnection())){
				dataSource.setSQLForValidateConnection(this.getSqlForValidateConnection());
			}
			if(this.getConnectionValidationTimeout() != null){
				dataSource.setConnectionValidationTimeout((int) this.getConnectionValidationTimeout().getSeconds());
			}

			if(this.isFastConnectionFailoverEnabled() != null){
				dataSource.setFastConnectionFailoverEnabled(this.isFastConnectionFailoverEnabled());
			}

			if(this.getQueryTimeout() != null){
				dataSource.setQueryTimeout((int) this.getQueryTimeout().getSeconds());
			}

			if(Validate.hasText(this.getOnsConfiguration())){
				dataSource.setONSConfiguration(this.getOnsConfiguration());
			}

			if(this.isShardingMode() != null){
				dataSource.setShardingMode(this.isShardingMode());
			}
			if(this.getMaxConnectionsPerShard() != null){
				dataSource.setMaxConnectionsPerShard(this.getMaxConnectionsPerShard());
			}
			if(this.getMaxConnectionsPerService() != null){
				//dataSource.setMaxConnectionsPerService();
			}

			if(Validate.isNotEmpty(this.getConnectionProperties())){
				dataSource.setConnectionProperties(this.getConnectionProperties());
			}
		}catch(SQLException e){
			logger.error("OracleDataSource set error: {}.", e.getMessage(), e);
		}

		return dataSource;
	}

	@Override
	protected void applyPoolConfiguration(final oracle.ucp.jdbc.PoolDataSource dataSource,
										  final OraclePoolConfiguration poolConfiguration,
										  final PropertyMapper propertyMapper) {
		try{
			if(poolConfiguration.getPoolName() != null){
				dataSource.setConnectionPoolName(poolConfiguration.getPoolName());
			}

			if(poolConfiguration.getInitialPoolSize() != null){
				dataSource.setInitialPoolSize(poolConfiguration.getInitialPoolSize());
			}
			if(poolConfiguration.getMinIdle() != null){
				dataSource.setMinIdle(poolConfiguration.getMinIdle());
			}
			if(poolConfiguration.getMinPoolSize() != null){
				dataSource.setMinPoolSize(poolConfiguration.getMinPoolSize());
			}
			if(poolConfiguration.getMaxPoolSize() != null){
				dataSource.setMaxPoolSize(poolConfiguration.getMaxPoolSize());
			}

			if(poolConfiguration.isValidateConnectionOnBorrow() != null){
				dataSource.setValidateConnectionOnBorrow(poolConfiguration.isValidateConnectionOnBorrow());
			}

			if(poolConfiguration.isCreateConnectionInBorrowThread() != null){
				dataSource.setCreateConnectionInBorrowThread(poolConfiguration.isCreateConnectionInBorrowThread());
			}

			if(poolConfiguration.getConnectionWait() != null){
				dataSource.setConnectionWaitDuration(poolConfiguration.getConnectionWait());
			}

			if(poolConfiguration.getTimeoutCheckInterval() != null){
				dataSource.setTimeoutCheckInterval((int) poolConfiguration.getTimeoutCheckInterval().getSeconds());
			}

			if(poolConfiguration.getInactiveConnectionTimeout() != null){
				dataSource.setInactiveConnectionTimeout(
						(int) poolConfiguration.getInactiveConnectionTimeout().getSeconds());
			}
			if(poolConfiguration.getTimeToLiveConnectionTimeout() != null){
				dataSource.setTimeToLiveConnectionTimeout(
						(int) poolConfiguration.getTimeToLiveConnectionTimeout().getSeconds());
			}

			if(poolConfiguration.getTrustIdleConnection() != null){
				dataSource.setSecondsToTrustIdleConnection(
						(int) poolConfiguration.getTrustIdleConnection().getSeconds());
			}
			if(poolConfiguration.getMaxConnectionReuseTime() != null){
				dataSource.setMaxConnectionReuseTime(poolConfiguration.getMaxConnectionReuseTime().toMillis());
			}

			if(poolConfiguration.getConnectionLabelingHighCost() != null){
				dataSource.setConnectionLabelingHighCost(
						(int) poolConfiguration.getConnectionLabelingHighCost().toMillis());
			}
			if(poolConfiguration.getHighCostConnectionReuseThreshold() != null){
				dataSource.setHighCostConnectionReuseThreshold(poolConfiguration.getHighCostConnectionReuseThreshold());
			}
			if(poolConfiguration.getConnectionRepurposeThreshold() != null){
				dataSource.setConnectionRepurposeThreshold(poolConfiguration.getConnectionRepurposeThreshold());
			}

			if(poolConfiguration.getConnectionHarvestTriggerCount() != null){
				dataSource.setConnectionHarvestTriggerCount(poolConfiguration.getConnectionHarvestTriggerCount());
			}
			if(poolConfiguration.getConnectionHarvestMaxCount() != null){
				dataSource.setConnectionHarvestMaxCount(poolConfiguration.getConnectionHarvestMaxCount());
			}

			if(poolConfiguration.getMaxStatements() != null){
				dataSource.setMaxStatements(poolConfiguration.getMaxStatements());
			}

			if(poolConfiguration.isReadOnlyInstanceAllowed() != null){
				dataSource.setReadOnlyInstanceAllowed(poolConfiguration.isReadOnlyInstanceAllowed());
			}

			if(poolConfiguration.getAbandonedConnectionTimeout() != null){
				dataSource.setAbandonedConnectionTimeout(
						(int) poolConfiguration.getAbandonedConnectionTimeout().getSeconds());
			}
		}catch(SQLException e){
			logger.error("OracleDataSource pool set error: {}.", e.getMessage(), e);
		}
	}

}
