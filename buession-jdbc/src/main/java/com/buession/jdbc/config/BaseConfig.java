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
package com.buession.jdbc.config;

import com.buession.jdbc.core.Jmx;
import com.buession.jdbc.core.TransactionIsolation;

import java.time.Duration;

/**
 * 数据源基本配置
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class BaseConfig implements Config {

	/***********************************************************/
	/*                     数据源基本配置开始                     */
	/***********************************************************/

	/**
	 * 登录超时
	 *
	 * @since 3.0.0
	 */
	private Duration loginTimeout;

	/**
	 * 查询超时时间
	 */
	private Duration queryTimeout;

	/**
	 * 默认事务隔离级别
	 */
	private TransactionIsolation defaultTransactionIsolation = TransactionIsolation.DEFAULT;

	/**
	 * 默认连接是否是只读模式
	 */
	private Boolean defaultReadOnly;

	/**
	 * 默认是否自动提交事务
	 */
	private Boolean defaultAutoCommit;

	/**
	 * PoolGuard 是否可以访问底层连接
	 */
	private Boolean accessToUnderlyingConnectionAllowed;

	/***********************************************************/
	/*                     数据源基本配置结束                     */
	/***********************************************************/


	/***********************************************************/
	/*                       连接池配置开始                      */
	/***********************************************************/
	/**
	 * 连接池的名称
	 */
	private String poolName;

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
	 * 最大连接数，可以在这个池中同一时刻被分配的有效连接数的最大值，如设置为负数，则不限制
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
	 * 设置一个SQL语句, 从连接池获取连接时, 先执行改 sql, 验证连接是否可用
	 */
	private String validationQuery;

	/**
	 * 连接有效SQL的执行查询超时时间
	 */
	private Duration validationQueryTimeout;

	/**
	 * 空闲的连接被释放最低要待时间
	 */
	private Duration minEvictableIdle;

	/**
	 * 空闲的连接被释放最高要待时间
	 */
	private Duration maxEvictableIdle;

	/**
	 * 在每个空闲对象驱逐线程运行过程中中进行检查的对象个数
	 */
	private Integer numTestsPerEvictionRun;

	/**
	 * 空闲对象驱逐线程运行时的休眠时间
	 */
	private Duration timeBetweenEvictionRuns;

	/**
	 * 当连接被认为是废弃并且被移除时是否记录日志
	 */
	private Boolean logAbandoned;

	/**
	 * 指定连接在被认为是废弃连接（abandoned connection）之前的超时时间
	 */
	private Duration removeAbandonedTimeout;

	/**
	 * JMX 管理对象配置
	 */
	private Jmx jmx;
	/***********************************************************/
	/*                       连接池配置结束                      */

	/***********************************************************/

	/**
	 * 返回登录超时
	 *
	 * @return 登录超时
	 *
	 * @since 3.0.0
	 */
	public Duration getLoginTimeout() {
		return loginTimeout;
	}

	/**
	 * 设置登录超时
	 *
	 * @param loginTimeout
	 * 		登录超时
	 *
	 * @since 3.0.0
	 */
	public void setLoginTimeout(Duration loginTimeout) {
		this.loginTimeout = loginTimeout;
	}

	@Override
	public Duration getQueryTimeout() {
		return queryTimeout;
	}

	@Override
	public void setQueryTimeout(Duration queryTimeout) {
		this.queryTimeout = queryTimeout;
	}

	@Override
	public TransactionIsolation getDefaultTransactionIsolation() {
		return defaultTransactionIsolation;
	}

	@Override
	public void setDefaultTransactionIsolation(TransactionIsolation defaultTransactionIsolation) {
		this.defaultTransactionIsolation = defaultTransactionIsolation;
	}

	@Override
	public Boolean getDefaultReadOnly() {
		return defaultReadOnly;
	}

	@Override
	public void setDefaultReadOnly(Boolean defaultReadOnly) {
		this.defaultReadOnly = defaultReadOnly;
	}

	@Override
	public Boolean getDefaultAutoCommit() {
		return defaultAutoCommit;
	}

	@Override
	public void setDefaultAutoCommit(Boolean defaultAutoCommit) {
		this.defaultAutoCommit = defaultAutoCommit;
	}

	@Override
	public Boolean getAccessToUnderlyingConnectionAllowed() {
		return accessToUnderlyingConnectionAllowed;
	}

	@Override
	public void setAccessToUnderlyingConnectionAllowed(Boolean accessToUnderlyingConnectionAllowed) {
		this.accessToUnderlyingConnectionAllowed = accessToUnderlyingConnectionAllowed;
	}

	@Override
	public String getPoolName() {
		return poolName;
	}

	@Override
	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	@Override
	public Integer getInitialSize() {
		return initialSize;
	}

	@Override
	public void setInitialSize(Integer initialSize) {
		this.initialSize = initialSize;
	}

	@Override
	public Integer getMinIdle() {
		return minIdle;
	}

	@Override
	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	@Override
	public Integer getMaxIdle() {
		return maxIdle;
	}

	@Override
	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

	@Override
	public Integer getMaxTotal() {
		return maxTotal;
	}

	@Override
	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}

	@Override
	public Duration getMaxWait() {
		return maxWait;
	}

	@Override
	public void setMaxWait(Duration maxWait) {
		this.maxWait = maxWait;
	}

	@Override
	public Boolean getTestOnCreate() {
		return testOnCreate;
	}

	@Override
	public void setTestOnCreate(Boolean testOnCreate) {
		this.testOnCreate = testOnCreate;
	}

	@Override
	public Boolean getTestOnBorrow() {
		return testOnBorrow;
	}

	@Override
	public void setTestOnBorrow(Boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	@Override
	public Boolean getTestOnReturn() {
		return testOnReturn;
	}

	@Override
	public void setTestOnReturn(Boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	@Override
	public Boolean getTestWhileIdle() {
		return testWhileIdle;
	}

	@Override
	public void setTestWhileIdle(Boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	@Override
	public String getValidationQuery() {
		return validationQuery;
	}

	@Override
	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	@Override
	public Duration getValidationQueryTimeout() {
		return validationQueryTimeout;
	}

	@Override
	public void setValidationQueryTimeout(Duration validationQueryTimeout) {
		this.validationQueryTimeout = validationQueryTimeout;
	}

	@Override
	public Duration getMinEvictableIdle() {
		return minEvictableIdle;
	}

	@Override
	public void setMinEvictableIdle(Duration minEvictableIdle) {
		this.minEvictableIdle = minEvictableIdle;
	}

	@Override
	public Duration getMaxEvictableIdle() {
		return maxEvictableIdle;
	}

	@Override
	public void setMaxEvictableIdle(Duration maxEvictableIdle) {
		this.maxEvictableIdle = maxEvictableIdle;
	}

	@Override
	public Integer getNumTestsPerEvictionRun() {
		return numTestsPerEvictionRun;
	}

	@Override
	public void setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}

	@Override
	public Duration getTimeBetweenEvictionRuns() {
		return timeBetweenEvictionRuns;
	}

	@Override
	public void setTimeBetweenEvictionRuns(Duration timeBetweenEvictionRuns) {
		this.timeBetweenEvictionRuns = timeBetweenEvictionRuns;
	}

	@Override
	public Boolean getLogAbandoned() {
		return logAbandoned;
	}

	@Override
	public void setLogAbandoned(Boolean logAbandoned) {
		this.logAbandoned = logAbandoned;
	}

	@Override
	public Duration getRemoveAbandonedTimeout() {
		return removeAbandonedTimeout;
	}

	@Override
	public void setRemoveAbandonedTimeout(Duration removeAbandonedTimeout) {
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}

	@Override
	public Jmx getJmx() {
		return jmx;
	}

	@Override
	public void setJmx(Jmx jmx) {
		this.jmx = jmx;
	}

}
