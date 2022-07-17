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

import com.buession.jdbc.core.TransactionIsolation;
import org.apache.commons.dbcp2.BasicDataSource;

import java.time.Duration;
import java.util.Collection;
import java.util.Set;

/**
 * Dhcp2 数据源连接池配置 {@link BasicDataSource}
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class Dbcp2PoolConfiguration extends AbstractPoolConfiguration {

	/**
	 * 为支持 catalog 概念的数据库设置默认 catalog
	 */
	private String defaultCatalog;

	/**
	 * 连接的最大存活时间；
	 * 如果超过这个时间，则连接在下次激活、钝化、校验时都将会失败。如果设置为0或小于0的值，则连接的存活时间是无限的
	 */
	private Duration maxConnLifetime;

	/**
	 * 查询超时时间
	 */
	private Duration defaultQueryTimeout;

	/**
	 * 初始连接数，池被启动时初始化的创建的连接个数
	 */
	private Integer initialSize;

	/**
	 * 最小空闲连接数，可以在池中保持空闲的最小连接数，低于设置值时，空闲连接将被创建，以努力保持最小空闲连接数 &gt;= minIdle
	 */
	private Integer minIdle;

	/**
	 * 最大空闲连接数，在池中，可以保持空闲状态的最大连接数，超出设置值之外的空闲连接在归还到连接池时将被释放，如设置为负数，则不限制
	 */
	private Integer maxIdle;

	/**
	 * 最大活动连接数，可以在这个池中同一时刻被分配的有效连接数的最大值，如设置为负数，则不限制
	 */
	private Integer maxTotal;

	/**
	 * 从连接池获取一个连接时，最大的等待时间，设置为-1时，如果没有可用连接，连接池会一直无限期等待，直到获取到连接为止
	 */
	private Duration maxWait;

	/**
	 * 连接创建后，马上验证有效性；
	 * 指明对象在创建后是否需要验证是否有效，如果对象验证失败，则触发对象创建的租借尝试将失败
	 */
	private Boolean testOnCreate;

	/**
	 * 从连接池获取一个连接时，验证有效性；
	 * 指明在从池中租借对象时是否要进行验证有效，如果对象验证失败，则对象将从池子释放，然后我们将尝试租借另一个
	 */
	private Boolean testOnBorrow;

	/**
	 * 连接被归还到连接池时，验证有效性
	 */
	private Boolean testOnReturn;

	/**
	 * 连接空闲时，验证有效性；
	 * 指明对象是否需要通过对象驱逐者进行校验（如果有的话），假如一个对象验证失败，则对象将被从池中释放
	 */
	private Boolean testWhileIdle;

	/**
	 * 实现 {@link org.apache.commons.dbcp2.ConnectionFactory} 接口，连接工厂实现类
	 */
	private String connectionFactoryClassName;

	/**
	 * 在第一次创建时用来初始化物理连接的SQL语句集合，只在配置的连接工厂创建连接时被执行一次
	 */
	private Collection<String> connectionInitSqls;

	/**
	 * 设置一个SQL语句, 从连接池获取连接时, 先执行改 sql, 验证连接是否可用
	 */
	private String validationQuery;

	/**
	 * 连接有效SQL的执行查询超时时间
	 */
	private Duration validationQueryTimeout;

	/**
	 * 设置的默认模式为支持模式的概念数据库
	 */
	private String defaultSchema;

	/**
	 * 默认事务隔离级别
	 */
	private TransactionIsolation defaultTransactionIsolation = TransactionIsolation.DEFAULT;

	/**
	 * 默认是否自动提交事务
	 */
	private Boolean defaultAutoCommit;

	/**
	 * 连接归还到池时，设置为自动提交
	 */
	private Boolean autoCommitOnReturn;

	/**
	 * 连接归还到池时，是否回滚所有操作
	 */
	private Boolean rollbackOnReturn;

	/**
	 * 默认连接是否是只读模式
	 */
	private Boolean defaultReadOnly;

	/**
	 * 一个过期的连接被连接池关闭时，写日志标识；
	 * 如果连接存货时间超过 maxConnLifetimeMillis，连接将被连接池会抽，此时默认输出日志
	 */
	private Boolean logExpiredConnections = true;

	/**
	 * PoolGuard 是否可以访问底层连接
	 */
	private Boolean accessToUnderlyingConnectionAllowed;

	/**
	 * 缓存状态，如果设置为true，
	 * 池化的连接将在第一次读或写，以及随后的写的时候缓存当前的只读状态和自动提交设置。
	 * 这样就省去了对 getter 的任何进一步的调用时对数据库的额外查询。
	 * 如果直接访问底层连接，只读状态和/或自动提交设置改变缓存值将不会被反映到当前的状态，在这种情况下，应该将该属性设置为false以禁用缓存
	 */
	private Boolean cacheState = true;

	/**
	 * 该连接池的预处理语句池是否生效
	 */
	private Boolean poolPreparedStatements;

	/**
	 * 可以在语句池中同时分配的最大语句数
	 */
	private Integer maxOpenPreparedStatements;

	private Boolean clearStatementPoolOnReturn;

	/**
	 * 空闲对象驱逐策略名称
	 */
	private String evictionPolicyClassName;

	/**
	 * 空闲对象驱逐线程运行时的休眠时间
	 */
	private Duration timeBetweenEvictionRuns;

	/**
	 * 在每个空闲对象驱逐线程运行过程中中进行检查的对象个数
	 */
	private Integer numTestsPerEvictionRun;

	/**
	 * 空闲的连接被释放最低要待时间
	 */
	private Duration minEvictableIdleTime;

	/**
	 * 空闲的连接被释放最低要待时间，但有额外条件
	 * 额外的条件是池中至少保留有 minIdle 所指定的个数的连接；
	 * 当 miniEvictableIdleTime 被设置为一个正数，空闲连接驱逐者首先检测 miniEvictableIdleTime ，
	 * 当空闲连接被驱逐者访问时，首先与 miniEvictableIdleTime 所指定的值进行比较（而不考虑当前池中的空闲连接数），
	 * 然后比较 softMinEvictableIdleTime 所指定的连接数，包括 minIdle 条件
	 */
	private Duration softMinEvictableIdleTime;

	/**
	 * 后进先出，设置为true表明连接池（如果池中有可用的空闲连接时）将返回最后一次使用的租借对象（最后进入），
	 * 设置为false则表明池将表现为FIFO队列（先进先出）—将会按照它们被归还的顺序从空闲连接实例池中获取连接
	 */
	private Boolean lifo;

	/**
	 * 验证快速失败
	 */
	private Boolean fastFailValidation;

	private Set<String> disconnectionSqlCodes;

	private String jmxName;

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
	 * 返回连接的最大存活时间
	 *
	 * @return 连接的最大存活时间
	 */
	public Duration getMaxConnLifetime(){
		return maxConnLifetime;
	}

	/**
	 * 设置连接的最大存活时间
	 *
	 * @param maxConnLifetime
	 * 		连接的最大存活时间
	 */
	public void setMaxConnLifetime(Duration maxConnLifetime){
		this.maxConnLifetime = maxConnLifetime;
	}

	/**
	 * 返回查询超时时间
	 *
	 * @return 查询超时时间
	 */
	public Duration getDefaultQueryTimeout(){
		return defaultQueryTimeout;
	}

	/**
	 * 设置查询超时时间
	 *
	 * @param defaultQueryTimeout
	 * 		查询超时时间
	 */
	public void setDefaultQueryTimeout(Duration defaultQueryTimeout){
		this.defaultQueryTimeout = defaultQueryTimeout;
	}

	/**
	 * 返回初始连接数
	 *
	 * @return 初始连接数
	 */
	public Integer getInitialSize(){
		return initialSize;
	}

	/**
	 * 设置初始连接数
	 *
	 * @param initialSize
	 * 		初始连接数
	 */
	public void setInitialSize(Integer initialSize){
		this.initialSize = initialSize;
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
	 * 设置最大空闲连接数，如设置为负数，则不限制
	 *
	 * @param maxIdle
	 * 		最大空闲连接数
	 */
	public void setMaxIdle(Integer maxIdle){
		this.maxIdle = maxIdle;
	}

	/**
	 * 返回最大活动连接数
	 *
	 * @return 最大活动连接数
	 */
	public Integer getMaxTotal(){
		return maxTotal;
	}

	/**
	 * 设置最大活动连接数，如设置为负数，则不限制
	 *
	 * @param maxTotal
	 * 		最大活动连接数
	 */
	public void setMaxTotal(Integer maxTotal){
		this.maxTotal = maxTotal;
	}

	/**
	 * 返回从连接池获取一个连接时，最大的等待时间
	 *
	 * @return 从连接池获取一个连接时，最大的等待时间
	 */
	public Duration getMaxWait(){
		return maxWait;
	}

	/**
	 * 设置从连接池获取一个连接时，最大的等待时间
	 *
	 * @param maxWait
	 * 		从连接池获取一个连接时，最大的等待时间
	 */
	public void setMaxWait(Duration maxWait){
		this.maxWait = maxWait;
	}

	/**
	 * 返回连接创建后，是否马上验证有效性
	 *
	 * @return 连接创建后，是否马上验证有效性
	 */
	@Deprecated
	public Boolean isTestOnCreate(){
		return getTestOnCreate();
	}

	/**
	 * 返回连接创建后，是否马上验证有效性
	 *
	 * @return 连接创建后，是否马上验证有效性
	 */
	public Boolean getTestOnCreate(){
		return testOnCreate;
	}

	/**
	 * 设置连接创建后，是否马上验证有效性
	 *
	 * @param testOnCreate
	 * 		连接创建后，是否马上验证有效性
	 */
	public void setTestOnCreate(Boolean testOnCreate){
		this.testOnCreate = testOnCreate;
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
	 * 返回 {@link org.apache.commons.dbcp2.ConnectionFactory} 接口实现，连接工厂实现
	 *
	 * @return {@link org.apache.commons.dbcp2.ConnectionFactory} 接口实现，连接工厂实现类
	 */
	public String getConnectionFactoryClassName(){
		return connectionFactoryClassName;
	}

	/**
	 * 设置{@link org.apache.commons.dbcp2.ConnectionFactory} 接口实现，连接工厂实现类
	 *
	 * @param connectionFactoryClassName
	 *        {@link org.apache.commons.dbcp2.ConnectionFactory} 接口实现，连接工厂实现类
	 */
	public void setConnectionFactoryClassName(String connectionFactoryClassName){
		this.connectionFactoryClassName = connectionFactoryClassName;
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

	/**
	 * 返回从连接池获取连接时, 验证连接是否可用的SQL语句
	 *
	 * @return 从连接池获取连接时, 验证连接是否可用的SQL语句
	 */
	public String getValidationQuery(){
		return validationQuery;
	}

	/**
	 * 设置从连接池获取连接时, 验证连接是否可用的SQL语句
	 *
	 * @param validationQuery
	 * 		从连接池获取连接时, 验证连接是否可用的SQL语句
	 */
	public void setValidationQuery(String validationQuery){
		this.validationQuery = validationQuery;
	}

	/**
	 * 返回连接有效SQL的执行查询超时时间
	 *
	 * @return 连接有效SQL的执行查询超时时间
	 */
	public Duration getValidationQueryTimeout(){
		return validationQueryTimeout;
	}

	/**
	 * 设置连接有效SQL的执行查询超时时间
	 *
	 * @param validationQueryTimeout
	 * 		连接有效SQL的执行查询超时时间
	 */
	public void setValidationQueryTimeout(Duration validationQueryTimeout){
		this.validationQueryTimeout = validationQueryTimeout;
	}

	/**
	 * 返回设置的默认模式为支持模式的概念数据库
	 *
	 * @return 设置的默认模式为支持模式的概念数据库
	 */
	public String getDefaultSchema(){
		return defaultSchema;
	}

	/**
	 * 设置默认模式为支持模式的概念数据库
	 *
	 * @param defaultSchema
	 * 		默认模式为支持模式的概念数据库
	 */
	public void setDefaultSchema(String defaultSchema){
		this.defaultSchema = defaultSchema;
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
	 * 返回连接归还到池时，设置为自动提交
	 *
	 * @return 连接归还到池时，设置为自动提交
	 */
	@Deprecated
	public Boolean isAutoCommitOnReturn(){
		return getAutoCommitOnReturn();
	}

	/**
	 * 返回连接归还到池时，设置为自动提交
	 *
	 * @return 连接归还到池时，设置为自动提交
	 */
	public Boolean getAutoCommitOnReturn(){
		return autoCommitOnReturn;
	}

	/**
	 * 设置连接归还到池时，是否自动提交
	 *
	 * @param autoCommitOnReturn
	 * 		连接归还到池时，是否自动提交
	 */
	public void setAutoCommitOnReturn(Boolean autoCommitOnReturn){
		this.autoCommitOnReturn = autoCommitOnReturn;
	}

	/**
	 * 返回连接归还到池时，是否回滚所有操作
	 *
	 * @return 连接归还到池时，是否回滚所有操作
	 */
	@Deprecated
	public Boolean isRollbackOnReturn(){
		return getRollbackOnReturn();
	}

	/**
	 * 返回连接归还到池时，是否回滚所有操作
	 *
	 * @return 连接归还到池时，是否回滚所有操作
	 */
	public Boolean getRollbackOnReturn(){
		return rollbackOnReturn;
	}

	/**
	 * 设置连接归还到池时，是否回滚所有操作
	 *
	 * @param rollbackOnReturn
	 * 		连接归还到池时，是否回滚所有操作
	 */
	public void setRollbackOnReturn(Boolean rollbackOnReturn){
		this.rollbackOnReturn = rollbackOnReturn;
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
	 * 返回一个过期的连接被连接池关闭时，写日志标识
	 *
	 * @return 一个过期的连接被连接池关闭时，写日志标识
	 */
	@Deprecated
	public Boolean isLogExpiredConnections(){
		return getLogExpiredConnections();
	}

	/**
	 * 返回一个过期的连接被连接池关闭时，写日志标识
	 *
	 * @return 一个过期的连接被连接池关闭时，写日志标识
	 */
	public Boolean getLogExpiredConnections(){
		return logExpiredConnections;
	}

	/**
	 * 设置一个过期的连接被连接池关闭时，写日志标识
	 *
	 * @param logExpiredConnections
	 * 		一个过期的连接被连接池关闭时，写日志标识
	 */
	public void setLogExpiredConnections(Boolean logExpiredConnections){
		this.logExpiredConnections = logExpiredConnections;
	}

	/**
	 * 返回 PoolGuard 是否可以访问底层连接
	 *
	 * @return PoolGuard 是否可以访问底层连接
	 */
	@Deprecated
	public Boolean isAccessToUnderlyingConnectionAllowed(){
		return getAccessToUnderlyingConnectionAllowed();
	}

	/**
	 * 返回 PoolGuard 是否可以访问底层连接
	 *
	 * @return PoolGuard 是否可以访问底层连接
	 */
	public Boolean getAccessToUnderlyingConnectionAllowed(){
		return accessToUnderlyingConnectionAllowed;
	}

	/**
	 * 设置 PoolGuard 是否可以访问底层连接
	 *
	 * @param accessToUnderlyingConnectionAllowed
	 * 		PoolGuard 是否可以访问底层连接
	 */
	public void setAccessToUnderlyingConnectionAllowed(Boolean accessToUnderlyingConnectionAllowed){
		this.accessToUnderlyingConnectionAllowed = accessToUnderlyingConnectionAllowed;
	}

	/**
	 * 返回缓存状态
	 *
	 * @return 缓存状态
	 */
	@Deprecated
	public Boolean isCacheState(){
		return getCacheState();
	}

	/**
	 * 返回缓存状态
	 *
	 * @return 缓存状态
	 */
	public Boolean getCacheState(){
		return cacheState;
	}

	/**
	 * 设置缓存状态
	 *
	 * @param cacheState
	 * 		缓存状态
	 */
	public void setCacheState(Boolean cacheState){
		this.cacheState = cacheState;
	}

	/**
	 * 返回该连接池的预处理语句池是否生效
	 *
	 * @return 该连接池的预处理语句池是否生效
	 */
	@Deprecated
	public Boolean isPoolPreparedStatements(){
		return getPoolPreparedStatements();
	}

	/**
	 * 返回该连接池的预处理语句池是否生效
	 *
	 * @return 该连接池的预处理语句池是否生效
	 */
	public Boolean getPoolPreparedStatements(){
		return poolPreparedStatements;
	}

	/**
	 * 设置该连接池的预处理语句池是否生效
	 *
	 * @param poolPreparedStatements
	 * 		该连接池的预处理语句池是否生效
	 */
	public void setPoolPreparedStatements(Boolean poolPreparedStatements){
		this.poolPreparedStatements = poolPreparedStatements;
	}

	/**
	 * 返回可以在语句池中同时分配的最大语句数
	 *
	 * @return 可以在语句池中同时分配的最大语句数
	 */
	public Integer getMaxOpenPreparedStatements(){
		return maxOpenPreparedStatements;
	}

	/**
	 * 设置可以在语句池中同时分配的最大语句数
	 *
	 * @param maxOpenPreparedStatements
	 * 		可以在语句池中同时分配的最大语句数
	 */
	public void setMaxOpenPreparedStatements(Integer maxOpenPreparedStatements){
		this.maxOpenPreparedStatements = maxOpenPreparedStatements;
	}

	/**
	 * 返回空闲对象驱逐策略名称
	 *
	 * @return 空闲对象驱逐策略名称
	 */
	public String getEvictionPolicyClassName(){
		return evictionPolicyClassName;
	}

	/**
	 * 设置空闲对象驱逐策略名称
	 *
	 * @param evictionPolicyClassName
	 * 		空闲对象驱逐策略名称
	 */
	public void setEvictionPolicyClassName(String evictionPolicyClassName){
		this.evictionPolicyClassName = evictionPolicyClassName;
	}

	@Deprecated
	public Boolean isClearStatementPoolOnReturn(){
		return getClearStatementPoolOnReturn();
	}

	public Boolean getClearStatementPoolOnReturn(){
		return clearStatementPoolOnReturn;
	}

	public void setClearStatementPoolOnReturn(Boolean clearStatementPoolOnReturn){
		this.clearStatementPoolOnReturn = clearStatementPoolOnReturn;
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

	/**
	 * 返回空闲的连接被释放最低要待时间，但有额外条件
	 *
	 * @return 空闲的连接被释放最低要待时间
	 */
	public Duration getSoftMinEvictableIdleTime(){
		return softMinEvictableIdleTime;
	}

	/**
	 * 设置空闲的连接被释放最低要待时间
	 *
	 * @param softMinEvictableIdleTime
	 * 		空闲的连接被释放最低要待时间
	 */
	public void setSoftMinEvictableIdleTime(Duration softMinEvictableIdleTime){
		this.softMinEvictableIdleTime = softMinEvictableIdleTime;
	}

	/**
	 * 是否后进先出
	 *
	 * @return 后进先出
	 */
	@Deprecated
	public Boolean isLifo(){
		return getLifo();
	}

	/**
	 * 是否后进先出
	 *
	 * @return 后进先出
	 */
	public Boolean getLifo(){
		return lifo;
	}

	/**
	 * 设置后进先出
	 *
	 * @param lifo
	 * 		后进先出
	 */
	public void setLifo(Boolean lifo){
		this.lifo = lifo;
	}

	/**
	 * 返回验证快速失败
	 *
	 * @return 验证快速失败
	 */
	@Deprecated
	public Boolean isFastFailValidation(){
		return getFastFailValidation();
	}

	/**
	 * 返回验证快速失败
	 *
	 * @return 验证快速失败
	 */
	public Boolean getFastFailValidation(){
		return fastFailValidation;
	}

	/**
	 * 设置验证快速失败
	 *
	 * @param fastFailValidation
	 * 		验证快速失败
	 */
	public void setFastFailValidation(Boolean fastFailValidation){
		this.fastFailValidation = fastFailValidation;
	}

	public Set<String> getDisconnectionSqlCodes(){
		return disconnectionSqlCodes;
	}

	public void setDisconnectionSqlCodes(Set<String> disconnectionSqlCodes){
		this.disconnectionSqlCodes = disconnectionSqlCodes;
	}

	public String getJmxName(){
		return jmxName;
	}

	public void setJmxName(String jmxName){
		this.jmxName = jmxName;
	}

}
