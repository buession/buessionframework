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
package com.buession.jdbc.datasource.config;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSourceStatLogger;
import com.buession.jdbc.core.TransactionIsolation;

import javax.management.ObjectName;
import java.time.Duration;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Druid 数据源连接池配置 {@link DruidAbstractDataSource}
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class DruidPoolConfiguration extends AbstractPoolConfiguration {

	/**
	 * 连接池名称
	 */
	private String name;

	/**
	 * 为支持 catalog 概念的数据库设置默认 catalog
	 */
	private String defaultCatalog;

	/**
	 * 数据库类型名称
	 */
	private String dbTypeName;

	private ScheduledExecutorService createScheduler;

	private ScheduledExecutorService destroyScheduler;

	private Boolean initExceptionThrow;

	private Integer maxCreateTaskCount;

	private volatile Integer maxWaitThreadCount;

	/**
	 * 初始化时建立连接的个数
	 */
	private Integer initialSize = DruidAbstractDataSource.DEFAULT_INITIAL_SIZE;

	/**
	 * 最大连接池数量
	 */
	private Integer maxActive = DruidAbstractDataSource.DEFAULT_MAX_ACTIVE_SIZE;

	/**
	 * 最小空闲连接数
	 */
	private Integer minIdle = DruidAbstractDataSource.DEFAULT_MIN_IDLE;

	/**
	 * 最大空闲连接数
	 */
	private Integer maxIdle = DruidAbstractDataSource.DEFAULT_MAX_IDLE;

	/**
	 * 获取连接时最大等待时间
	 * 配置了 maxWait 之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置 useUnfairLock 属性为 true 使用非公平锁
	 */
	private Duration maxWait = Duration.ofMillis(DruidAbstractDataSource.DEFAULT_MAX_WAIT);

	private Long timeBetweenConnectError = DruidAbstractDataSource.DEFAULT_TIME_BETWEEN_CONNECT_ERROR_MILLIS;

	/**
	 * 连接出错重试次数
	 */
	private Integer connectionErrorRetryAttempts;

	/**
	 * 在第一次创建时用来初始化物理连接的SQL语句集合，只在配置的连接工厂创建连接时被执行一次
	 */
	private Collection<String> connectionInitSqls;

	private Boolean asyncCloseConnectionEnable;

	private Boolean accessToUnderlyingConnectionAllowed;

	/**
	 * 验证连接使用的 SQL
	 */
	private String validationQuery;

	private String validConnectionCheckerClassName;

	/**
	 * 验证SQL的执行超时时间，为负数表示关闭连接验证超时
	 */
	private Duration validationQueryTimeout;

	private Duration queryTimeout;

	private Integer notFullTimeoutRetryCount;

	/**
	 * 从连接池获取一个连接时，验证有效性；
	 * 指明在从池中租借对象时是否要进行验证有效，如果对象验证失败，则对象将从池子释放，然后我们将尝试租借另一个
	 */
	private Boolean testOnBorrow = DruidAbstractDataSource.DEFAULT_TEST_ON_BORROW;

	/**
	 * 连接被归还到连接池时，验证有效性
	 */
	private Boolean testOnReturn = DruidAbstractDataSource.DEFAULT_TEST_ON_RETURN;

	/**
	 * 连接空闲时，验证有效性；
	 * 指明对象是否需要通过对象驱逐者进行校验（如果有的话），假如一个对象验证失败，则对象将被从池中释放
	 */
	private Boolean testWhileIdle = DruidAbstractDataSource.DEFAULT_WHILE_IDLE;

	/**
	 * 空闲对象驱逐线程运行时的休眠时间
	 */
	private Duration timeBetweenEvictionRuns = Duration.ofMillis(
			DruidAbstractDataSource.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS);

	/**
	 * 在每个空闲对象驱逐线程运行过程中中进行检查的对象个数
	 */
	private Integer numTestsPerEvictionRun = DruidAbstractDataSource.DEFAULT_NUM_TESTS_PER_EVICTION_RUN;

	/**
	 * 空闲的连接被释放最低要待时间
	 */
	private Duration minEvictableIdleTime = Duration.ofMillis(
			DruidAbstractDataSource.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS);

	private Duration maxEvictableIdleTime = Duration.ofMillis(
			DruidAbstractDataSource.DEFAULT_MAX_EVICTABLE_IDLE_TIME_MILLIS);

	private Duration keepAliveBetweenTime = Duration.ofMillis(
			DruidAbstractDataSource.DEFAULT_TIME_BETWEEN_EVICTION_RUNS_MILLIS);

	private Duration phyTimeout = Duration.ofMillis(DruidAbstractDataSource.DEFAULT_PHY_TIMEOUT_MILLIS);

	private Long phyMaxUseCount;

	/**
	 * 默认事务隔离级别
	 */
	private TransactionIsolation defaultTransactionIsolation;

	private Long transactionThreshold;

	private Duration transactionQueryTimeout;

	/**
	 * 默认是否自动提交事务
	 */
	private Boolean defaultAutoCommit;

	/**
	 * 默认连接是否是只读模式
	 */
	private Boolean defaultReadOnly;

	/**
	 * 是否缓存 preparedStatement，也就是PSCache；
	 * PSCache 对支持游标的数据库性能提升巨大，比如说 oracle，在mysql下建议关闭
	 */
	private Boolean poolPreparedStatements;

	/**
	 * 最大打开 PSCache 数，在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些
	 */
	private Integer maxOpenPreparedStatements;

	private Boolean sharePreparedStatements;

	private Integer maxPoolPreparedStatementPerConnectionSize;

	/**
	 * 插件配置
	 */
	private Set<String> filters;

	private Boolean clearFiltersEnable;

	/**
	 * {@link com.alibaba.druid.pool.ExceptionSorter} 实例名称
	 */
	private String exceptionSorterClassName;

	private DruidDataSourceStatLogger statLogger;

	private Duration timeBetweenLogStats;

	/**
	 * 是否移除抛弃的（abandoned）连接，一个连接使用超过了 removeAbandonedTimeout 上限就被视为抛弃的，
	 * 开启该开关可以恢复那些应用没有关闭的连接
	 */
	private Boolean removeAbandoned;

	/**
	 * 一个连接使用超过多久就视为抛弃的，该值应该超过你的应用中最长的SQL可能运行的时间
	 */
	private Duration removeAbandonedTimeout;

	/**
	 * 记录抛弃连接的应用的堆栈信息；
	 * 会增加系统开销，因为为了能够在可能发生的连接被抛弃时记录堆栈 ，应用每次获取连接时都需要生成堆栈信息
	 */
	private Boolean logAbandoned;

	private Boolean useOracleImplicitCache;

	private Boolean initVariants;

	private Boolean initGlobalVariants;

	private Boolean failFast;

	private Integer onFatalErrorMaxActive;

	private Boolean breakAfterAcquireFailure;

	private Boolean dupCloseLogEnable;

	private Boolean useUnfairLock;

	private Boolean useLocalSessionState;

	private ObjectName objectName;

	/**
	 * 返回连接池名称
	 *
	 * @return 连接池名称
	 */
	public String getName(){
		return name;
	}

	/**
	 * 设置连接池名称
	 *
	 * @param name
	 * 		连接池名称
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * 返回为支持 catalog 概念的数据库设置默认 catalog
	 *
	 * @return 为支持 catalog 概念的数据库设置默认 catalog
	 */
	public String getDefaultCatalog(){
		return defaultCatalog;
	}

	/**
	 * 设置为支持 catalog 概念的数据库设置默认 catalog
	 *
	 * @param defaultCatalog
	 * 		为支持 catalog 概念的数据库设置默认 catalog
	 */
	public void setDefaultCatalog(String defaultCatalog){
		this.defaultCatalog = defaultCatalog;
	}

	/**
	 * 返回数据库类型名称
	 *
	 * @return 数据库类型名称
	 */
	public String getDbTypeName(){
		return dbTypeName;
	}

	/**
	 * 设置数据库类型名称
	 *
	 * @param dbTypeName
	 * 		数据库类型名称
	 */
	public void setDbTypeName(String dbTypeName){
		this.dbTypeName = dbTypeName;
	}

	public ScheduledExecutorService getCreateScheduler(){
		return createScheduler;
	}

	public void setCreateScheduler(ScheduledExecutorService createScheduler){
		this.createScheduler = createScheduler;
	}

	public ScheduledExecutorService getDestroyScheduler(){
		return destroyScheduler;
	}

	public void setDestroyScheduler(ScheduledExecutorService destroyScheduler){
		this.destroyScheduler = destroyScheduler;
	}

	@Deprecated
	public Boolean isInitExceptionThrow(){
		return getInitExceptionThrow();
	}

	public Boolean getInitExceptionThrow(){
		return initExceptionThrow;
	}

	public void setInitExceptionThrow(Boolean initExceptionThrow){
		this.initExceptionThrow = initExceptionThrow;
	}

	public Integer getMaxCreateTaskCount(){
		return maxCreateTaskCount;
	}

	public void setMaxCreateTaskCount(Integer maxCreateTaskCount){
		this.maxCreateTaskCount = maxCreateTaskCount;
	}

	public Integer getMaxWaitThreadCount(){
		return maxWaitThreadCount;
	}

	public void setMaxWaitThreadCount(Integer maxWaitThreadCount){
		this.maxWaitThreadCount = maxWaitThreadCount;
	}

	/**
	 * 返回初始化时建立连接的个数
	 *
	 * @return 初始化时建立连接的个数
	 */
	public Integer getInitialSize(){
		return initialSize;
	}

	/**
	 * 设置初始化时建立连接的个数
	 *
	 * @param initialSize
	 * 		初始化时建立连接的个数
	 */
	public void setInitialSize(Integer initialSize){
		this.initialSize = initialSize;
	}

	/**
	 * 返回最大连接池数量
	 *
	 * @return 最大连接池数量
	 */
	public Integer getMaxActive(){
		return maxActive;
	}

	/**
	 * 设置最大连接池数量
	 *
	 * @param maxActive
	 * 		最大连接池数量
	 */
	public void setMaxActive(Integer maxActive){
		this.maxActive = maxActive;
	}

	/**
	 * 返回最小空闲连接数
	 *
	 * @return 最小空闲连接数
	 */
	public Integer getMinIdle(){
		return minIdle;
	}

	/**
	 * 设置最小空闲连接数
	 *
	 * @param minIdle
	 * 		最小空闲连接数
	 */
	public void setMinIdle(Integer minIdle){
		this.minIdle = minIdle;
	}

	/**
	 * 返回最大空闲连接数
	 *
	 * @return 最大空闲连接数
	 */
	public Integer getMaxIdle(){
		return maxIdle;
	}

	/**
	 * 设置空闲连接数
	 *
	 * @param maxIdle
	 * 		最大空闲连接数
	 */
	public void setMaxIdle(Integer maxIdle){
		this.maxIdle = maxIdle;
	}

	/**
	 * 返回获取连接时最大等待时间
	 *
	 * @return 获取连接时最大等待时间
	 */
	public Duration getMaxWait(){
		return maxWait;
	}

	/**
	 * 设置获取连接时最大等待时间
	 *
	 * @param maxWait
	 * 		获取连接时最大等待时间
	 */
	public void setMaxWait(Duration maxWait){
		this.maxWait = maxWait;
	}

	public Long getTimeBetweenConnectError(){
		return timeBetweenConnectError;
	}

	public void setTimeBetweenConnectError(Long timeBetweenConnectError){
		this.timeBetweenConnectError = timeBetweenConnectError;
	}

	/**
	 * 返回连接出错重试次数
	 *
	 * @return 连接出错重试次数
	 */
	public Integer getConnectionErrorRetryAttempts(){
		return connectionErrorRetryAttempts;
	}

	/**
	 * 设置连接出错重试次数
	 *
	 * @param connectionErrorRetryAttempts
	 * 		连接出错重试次数
	 */
	public void setConnectionErrorRetryAttempts(Integer connectionErrorRetryAttempts){
		this.connectionErrorRetryAttempts = connectionErrorRetryAttempts;
	}

	/**
	 * 返回在第一次创建时用来初始化物理连接的SQL语句集合
	 *
	 * @return 在第一次创建时用来初始化物理连接的SQL语句集合
	 */
	public Collection<String> getConnectionInitSqls(){
		return connectionInitSqls;
	}

	/**
	 * 设置在第一次创建时用来初始化物理连接的SQL语句集合
	 *
	 * @param connectionInitSqls
	 * 		在第一次创建时用来初始化物理连接的SQL语句集合
	 */
	public void setConnectionInitSqls(Collection<String> connectionInitSqls){
		this.connectionInitSqls = connectionInitSqls;
	}

	@Deprecated
	public Boolean isAsyncCloseConnectionEnable(){
		return getAsyncCloseConnectionEnable();
	}

	public Boolean getAsyncCloseConnectionEnable(){
		return asyncCloseConnectionEnable;
	}

	public void setAsyncCloseConnectionEnable(Boolean asyncCloseConnectionEnable){
		this.asyncCloseConnectionEnable = asyncCloseConnectionEnable;
	}

	@Deprecated
	public Boolean isAccessToUnderlyingConnectionAllowed(){
		return getAccessToUnderlyingConnectionAllowed();
	}

	public Boolean getAccessToUnderlyingConnectionAllowed(){
		return accessToUnderlyingConnectionAllowed;
	}

	public void setAccessToUnderlyingConnectionAllowed(Boolean accessToUnderlyingConnectionAllowed){
		this.accessToUnderlyingConnectionAllowed = accessToUnderlyingConnectionAllowed;
	}

	/**
	 * 返回验证连接使用的 SQL
	 *
	 * @return 验证连接使用的 SQL
	 */
	public String getValidationQuery(){
		return validationQuery;
	}

	/**
	 * 设置验证连接使用的 SQL
	 *
	 * @param validationQuery
	 * 		验证连接使用的 SQL
	 */
	public void setValidationQuery(String validationQuery){
		this.validationQuery = validationQuery;
	}

	public String getValidConnectionCheckerClassName(){
		return validConnectionCheckerClassName;
	}

	public void setValidConnectionCheckerClassName(String validConnectionCheckerClassName){
		this.validConnectionCheckerClassName = validConnectionCheckerClassName;
	}

	/**
	 * 返回验证SQL的执行超时时间，为负数表示关闭连接验证超时
	 *
	 * @return 验证SQL的执行超时时间
	 */
	public Duration getValidationQueryTimeout(){
		return validationQueryTimeout;
	}

	/**
	 * 设置验证SQL的执行超时时间，为负数表示关闭连接验证超时
	 *
	 * @param validationQueryTimeout
	 * 		验证SQL的执行超时时间
	 */
	public void setValidationQueryTimeout(Duration validationQueryTimeout){
		this.validationQueryTimeout = validationQueryTimeout;
	}

	public Duration getQueryTimeout(){
		return queryTimeout;
	}

	public void setQueryTimeout(Duration queryTimeout){
		this.queryTimeout = queryTimeout;
	}

	public Integer getNotFullTimeoutRetryCount(){
		return notFullTimeoutRetryCount;
	}

	public void setNotFullTimeoutRetryCount(Integer notFullTimeoutRetryCount){
		this.notFullTimeoutRetryCount = notFullTimeoutRetryCount;
	}

	/**
	 * 返回从连接池获取一个连接时，是否验证有效性
	 *
	 * @return 从连接池获取一个连接时，验证有效性
	 */
	@Deprecated
	public Boolean isTestOnBorrow(){
		return getTestOnBorrow();
	}

	/**
	 * 返回从连接池获取一个连接时，是否验证有效性
	 *
	 * @return 从连接池获取一个连接时，验证有效性
	 */
	public Boolean getTestOnBorrow(){
		return testOnBorrow;
	}

	/**
	 * 设置从连接池获取一个连接时，是否验证有效性
	 *
	 * @param testOnBorrow
	 * 		从连接池获取一个连接时，是否验证有效性
	 */
	public void setTestOnBorrow(Boolean testOnBorrow){
		this.testOnBorrow = testOnBorrow;
	}

	/**
	 * 返回连接被归还到连接池时，是否验证有效性
	 *
	 * @return 连接被归还到连接池时，是否验证有效性
	 */
	@Deprecated
	public Boolean isTestOnReturn(){
		return getTestOnReturn();
	}

	/**
	 * 返回连接被归还到连接池时，是否验证有效性
	 *
	 * @return 连接被归还到连接池时，是否验证有效性
	 */
	public Boolean getTestOnReturn(){
		return testOnReturn;
	}

	/**
	 * 设置连接被归还到连接池时，是否验证有效性
	 *
	 * @param testOnReturn
	 * 		连接被归还到连接池时，是否验证有效性
	 */
	public void setTestOnReturn(Boolean testOnReturn){
		this.testOnReturn = testOnReturn;
	}

	/**
	 * 返回连接空闲时，是否验证有效性
	 *
	 * @return 连接空闲时，是否验证有效性
	 */
	@Deprecated
	public Boolean isTestWhileIdle(){
		return getTestWhileIdle();
	}

	/**
	 * 返回连接空闲时，是否验证有效性
	 *
	 * @return 连接空闲时，是否验证有效性
	 */
	public Boolean getTestWhileIdle(){
		return testWhileIdle;
	}

	/**
	 * 设置连接空闲时，是否验证有效性
	 *
	 * @param testWhileIdle
	 * 		连接空闲时，是否验证有效性
	 */
	public void setTestWhileIdle(Boolean testWhileIdle){
		this.testWhileIdle = testWhileIdle;
	}

	/**
	 * 返回空闲对象驱逐线程运行时的休眠时间
	 *
	 * @return 空闲对象驱逐线程运行时的休眠时间
	 */
	public Duration getTimeBetweenEvictionRuns(){
		return timeBetweenEvictionRuns;
	}

	/**
	 * 设置空闲对象驱逐线程运行时的休眠时间
	 *
	 * @param timeBetweenEvictionRuns
	 * 		空闲对象驱逐线程运行时的休眠时间
	 */
	public void setTimeBetweenEvictionRuns(Duration timeBetweenEvictionRuns){
		this.timeBetweenEvictionRuns = timeBetweenEvictionRuns;
	}

	/**
	 * 返回在每个空闲对象驱逐线程运行过程中中进行检查的对象个数
	 *
	 * @return 在每个空闲对象驱逐线程运行过程中中进行检查的对象个数
	 */
	public Integer getNumTestsPerEvictionRun(){
		return numTestsPerEvictionRun;
	}

	/**
	 * 设置在每个空闲对象驱逐线程运行过程中中进行检查的对象个数
	 *
	 * @param numTestsPerEvictionRun
	 * 		在每个空闲对象驱逐线程运行过程中中进行检查的对象个数
	 */
	public void setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun){
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}

	/**
	 * 返回空闲的连接被释放最低要待时间
	 *
	 * @return 空闲的连接被释放最低要待时间
	 */
	public Duration getMinEvictableIdleTime(){
		return minEvictableIdleTime;
	}

	/**
	 * 设置空闲的连接被释放最低要待时间
	 *
	 * @param minEvictableIdleTime
	 * 		空闲的连接被释放最低要待时间
	 */
	public void setMinEvictableIdleTime(Duration minEvictableIdleTime){
		this.minEvictableIdleTime = minEvictableIdleTime;
	}

	public Duration getMaxEvictableIdleTime(){
		return maxEvictableIdleTime;
	}

	public void setMaxEvictableIdleTime(Duration maxEvictableIdleTime){
		this.maxEvictableIdleTime = maxEvictableIdleTime;
	}

	public Duration getKeepAliveBetweenTime(){
		return keepAliveBetweenTime;
	}

	public void setKeepAliveBetweenTime(Duration keepAliveBetweenTime){
		this.keepAliveBetweenTime = keepAliveBetweenTime;
	}

	public Duration getPhyTimeout(){
		return phyTimeout;
	}

	public void setPhyTimeout(Duration phyTimeout){
		this.phyTimeout = phyTimeout;
	}

	public Long getPhyMaxUseCount(){
		return phyMaxUseCount;
	}

	public void setPhyMaxUseCount(Long phyMaxUseCount){
		this.phyMaxUseCount = phyMaxUseCount;
	}

	/**
	 * 返回默认事务隔离级别
	 *
	 * @return 默认事务隔离级别
	 */
	public TransactionIsolation getDefaultTransactionIsolation(){
		return defaultTransactionIsolation;
	}

	/**
	 * 设置默认事务隔离级别
	 *
	 * @param defaultTransactionIsolation
	 * 		默认事务隔离级别
	 */
	public void setDefaultTransactionIsolation(TransactionIsolation defaultTransactionIsolation){
		this.defaultTransactionIsolation = defaultTransactionIsolation;
	}

	public Long getTransactionThreshold(){
		return transactionThreshold;
	}

	public void setTransactionThreshold(Long transactionThreshold){
		this.transactionThreshold = transactionThreshold;
	}

	public Duration getTransactionQueryTimeout(){
		return transactionQueryTimeout;
	}

	public void setTransactionQueryTimeout(Duration transactionQueryTimeout){
		this.transactionQueryTimeout = transactionQueryTimeout;
	}

	/**
	 * 返回是否自动提交事务
	 *
	 * @return 是否自动提交事务
	 */
	@Deprecated
	public Boolean isDefaultAutoCommit(){
		return getDefaultAutoCommit();
	}

	/**
	 * 返回是否自动提交事务
	 *
	 * @return 是否自动提交事务
	 */
	public Boolean getDefaultAutoCommit(){
		return defaultAutoCommit;
	}

	/**
	 * 设置是否自动提交事务
	 *
	 * @param defaultAutoCommit
	 * 		是否自动提交事务
	 */
	public void setDefaultAutoCommit(Boolean defaultAutoCommit){
		this.defaultAutoCommit = defaultAutoCommit;
	}

	/**
	 * 返回连接是否是只读模式
	 *
	 * @return 连接是否是只读模式
	 */
	@Deprecated
	public Boolean isDefaultReadOnly(){
		return getDefaultReadOnly();
	}

	/**
	 * 返回连接是否是只读模式
	 *
	 * @return 连接是否是只读模式
	 */
	public Boolean getDefaultReadOnly(){
		return defaultReadOnly;
	}

	/**
	 * 设置连接是否是只读模式
	 *
	 * @param defaultReadOnly
	 * 		连接是否是只读模式
	 */
	public void setDefaultReadOnly(Boolean defaultReadOnly){
		this.defaultReadOnly = defaultReadOnly;
	}

	/**
	 * 返回是否缓存 preparedStatement
	 *
	 * @return 是否缓存 preparedStatement
	 */
	@Deprecated
	public Boolean isPoolPreparedStatements(){
		return getPoolPreparedStatements();
	}

	/**
	 * 返回是否缓存 preparedStatement
	 *
	 * @return 是否缓存 preparedStatement
	 */
	public Boolean getPoolPreparedStatements(){
		return poolPreparedStatements;
	}

	/**
	 * 设置是否缓存 preparedStatement，PSCache 对支持游标的数据库性能提升巨大，比如说 oracle，在mysql下建议关闭
	 *
	 * @param poolPreparedStatements
	 * 		是否缓存 preparedStatement
	 */
	public void setPoolPreparedStatements(Boolean poolPreparedStatements){
		this.poolPreparedStatements = poolPreparedStatements;
	}

	/**
	 * 返回最大打开 PSCache 数
	 *
	 * @return 最大打开 PSCache 数
	 */
	public Integer getMaxOpenPreparedStatements(){
		return maxOpenPreparedStatements;
	}

	/**
	 * 设置最大打开 PSCache 数，在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些
	 *
	 * @param maxOpenPreparedStatements
	 * 		最大打开 PSCache 数
	 */
	public void setMaxOpenPreparedStatements(Integer maxOpenPreparedStatements){
		this.maxOpenPreparedStatements = maxOpenPreparedStatements;
	}

	@Deprecated
	public Boolean isSharePreparedStatements(){
		return getSharePreparedStatements();
	}

	public Boolean getSharePreparedStatements(){
		return sharePreparedStatements;
	}

	public void setSharePreparedStatements(Boolean sharePreparedStatements){
		this.sharePreparedStatements = sharePreparedStatements;
	}

	public Integer getMaxPoolPreparedStatementPerConnectionSize(){
		return maxPoolPreparedStatementPerConnectionSize;
	}

	public void setMaxPoolPreparedStatementPerConnectionSize(Integer maxPoolPreparedStatementPerConnectionSize){
		this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
	}

	/**
	 * 返回插件配置
	 *
	 * @return 插件配置
	 */
	public Set<String> getFilters(){
		return filters;
	}

	/**
	 * 设置插件配置
	 *
	 * @param filters
	 * 		插件配置
	 */
	public void setFilters(Set<String> filters){
		this.filters = filters;
	}

	@Deprecated
	public Boolean isClearFiltersEnable(){
		return getClearFiltersEnable();
	}

	public Boolean getClearFiltersEnable(){
		return clearFiltersEnable;
	}

	public void setClearFiltersEnable(Boolean clearFiltersEnable){
		this.clearFiltersEnable = clearFiltersEnable;
	}

	/**
	 * 返回 {@link com.alibaba.druid.pool.ExceptionSorter} 实例名称
	 *
	 * @return {@link com.alibaba.druid.pool.ExceptionSorter} 实例名称
	 */
	public String getExceptionSorterClassName(){
		return exceptionSorterClassName;
	}

	/**
	 * 设置 {@link com.alibaba.druid.pool.ExceptionSorter} 实例名称
	 *
	 * @param exceptionSorterClassName
	 *        {@link com.alibaba.druid.pool.ExceptionSorter} 实例名称
	 */
	public void setExceptionSorterClassName(String exceptionSorterClassName){
		this.exceptionSorterClassName = exceptionSorterClassName;
	}

	public DruidDataSourceStatLogger getStatLogger(){
		return statLogger;
	}

	public void setStatLogger(DruidDataSourceStatLogger statLogger){
		this.statLogger = statLogger;
	}

	public Duration getTimeBetweenLogStats(){
		return timeBetweenLogStats;
	}

	public void setTimeBetweenLogStats(Duration timeBetweenLogStats){
		this.timeBetweenLogStats = timeBetweenLogStats;
	}

	/**
	 * 返回是否移除抛弃的（abandoned）连接
	 *
	 * @return 是否移除抛弃的（abandoned）连接
	 */
	@Deprecated
	public Boolean isRemoveAbandoned(){
		return getRemoveAbandoned();
	}

	/**
	 * 返回是否移除抛弃的（abandoned）连接
	 *
	 * @return 是否移除抛弃的（abandoned）连接
	 */
	public Boolean getRemoveAbandoned(){
		return removeAbandoned;
	}

	/**
	 * 设置是否移除抛弃的（abandoned）连接
	 *
	 * @param removeAbandoned
	 * 		是否移除抛弃的（abandoned）连接
	 */
	public void setRemoveAbandoned(Boolean removeAbandoned){
		this.removeAbandoned = removeAbandoned;
	}

	/**
	 * 返回一个连接使用超过多久就视为抛弃的
	 *
	 * @return 一个连接使用超过多久就视为抛弃的
	 */
	public Duration getRemoveAbandonedTimeout(){
		return removeAbandonedTimeout;
	}

	/**
	 * 设置一个连接使用超过多久就视为抛弃的，该值应该超过你的应用中最长的SQL可能运行的时间
	 *
	 * @param removeAbandonedTimeout
	 * 		一个连接使用超过多久就视为抛弃的，该值应该超过你的应用中最长的SQL可能运行的时间
	 */
	public void setRemoveAbandonedTimeout(Duration removeAbandonedTimeout){
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}

	/**
	 * 返回是否记录抛弃连接的应用的堆栈信息
	 *
	 * @return 是否记录抛弃连接的应用的堆栈信息
	 */
	@Deprecated
	public Boolean isLogAbandoned(){
		return getLogAbandoned();
	}

	/**
	 * 返回是否记录抛弃连接的应用的堆栈信息
	 *
	 * @return 是否记录抛弃连接的应用的堆栈信息
	 */
	public Boolean getLogAbandoned(){
		return logAbandoned;
	}

	/**
	 * 设置是否记录抛弃连接的应用的堆栈信息
	 *
	 * @param logAbandoned
	 * 		是否记录抛弃连接的应用的堆栈信息
	 */
	public void setLogAbandoned(Boolean logAbandoned){
		this.logAbandoned = logAbandoned;
	}

	@Deprecated
	public Boolean isUseOracleImplicitCache(){
		return getUseOracleImplicitCache();
	}

	public Boolean getUseOracleImplicitCache(){
		return useOracleImplicitCache;
	}

	public void setUseOracleImplicitCache(Boolean useOracleImplicitCache){
		this.useOracleImplicitCache = useOracleImplicitCache;
	}

	@Deprecated
	public Boolean isInitVariants(){
		return getInitVariants();
	}

	public Boolean getInitVariants(){
		return initVariants;
	}

	public void setInitVariants(Boolean initVariants){
		this.initVariants = initVariants;
	}

	@Deprecated
	public Boolean isInitGlobalVariants(){
		return getInitGlobalVariants();
	}

	public Boolean getInitGlobalVariants(){
		return initGlobalVariants;
	}

	public void setInitGlobalVariants(Boolean initGlobalVariants){
		this.initGlobalVariants = initGlobalVariants;
	}

	@Deprecated
	public Boolean isFailFast(){
		return getFailFast();
	}

	public Boolean getFailFast(){
		return failFast;
	}

	public void setFailFast(Boolean failFast){
		this.failFast = failFast;
	}

	public Integer getOnFatalErrorMaxActive(){
		return onFatalErrorMaxActive;
	}

	public void setOnFatalErrorMaxActive(Integer onFatalErrorMaxActive){
		this.onFatalErrorMaxActive = onFatalErrorMaxActive;
	}

	@Deprecated
	public Boolean isBreakAfterAcquireFailure(){
		return getBreakAfterAcquireFailure();
	}

	public Boolean getBreakAfterAcquireFailure(){
		return breakAfterAcquireFailure;
	}

	public void setBreakAfterAcquireFailure(Boolean breakAfterAcquireFailure){
		this.breakAfterAcquireFailure = breakAfterAcquireFailure;
	}

	@Deprecated
	public Boolean isDupCloseLogEnable(){
		return getDupCloseLogEnable();
	}

	public Boolean getDupCloseLogEnable(){
		return dupCloseLogEnable;
	}

	public void setDupCloseLogEnable(Boolean dupCloseLogEnable){
		this.dupCloseLogEnable = dupCloseLogEnable;
	}

	public Boolean getUseUnfairLock(){
		return useUnfairLock;
	}

	public void setUseUnfairLock(Boolean useUnfairLock){
		this.useUnfairLock = useUnfairLock;
	}

	@Deprecated
	public Boolean isUseLocalSessionState(){
		return getUseLocalSessionState();
	}

	public Boolean getUseLocalSessionState(){
		return useLocalSessionState;
	}

	public void setUseLocalSessionState(Boolean useLocalSessionState){
		this.useLocalSessionState = useLocalSessionState;
	}

	public ObjectName getObjectName(){
		return objectName;
	}

	public void setObjectName(ObjectName objectName){
		this.objectName = objectName;
	}

}
