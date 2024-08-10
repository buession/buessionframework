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
package com.buession.jdbc.datasource.config;

import com.alibaba.druid.pool.DruidAbstractDataSource;

import java.sql.PreparedStatement;
import java.time.Duration;

/**
 * Druid 数据源连接池配置 {@link DruidAbstractDataSource}
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class DruidPoolConfiguration extends AbstractPoolConfiguration {

	/**
	 * 最大连接数，可以在这个池中同一时刻被分配的有效连接数的最大值，如设置为负数，则不限制
	 */
	private Integer maxActive;

	/**
	 * 数据库连接池中的空闲连接是否保持存活
	 *
	 * @since 3.0.0
	 */
	private Boolean keepAlive;

	/**
	 * 连接池中空闲连接的保活间隔时间，这个属性决定了在连接池中，空闲连接每隔多长时间会被检查并进行保活操作，以确保这些连接在空闲时不会被数据库服务器断开
	 */
	private Duration keepAliveBetweenTime;

	/**
	 * 测试连接是否有效时是否使用 PING 方法
	 *
	 * @since 3.0.0
	 */
	private Boolean usePingMethod;

	/**
	 * 连接池在将连接返回到连接池时，是否保留连接的原始事务隔离级别
	 *
	 * @since 3.0.0
	 */
	private Boolean keepConnectionUnderlyingTransactionIsolation;

	/**
	 * 连接池中创建连接任务的最大数量
	 */
	private Integer maxCreateTaskCount;

	/**
	 * 连接池中等待数据库连接的最大线程数量
	 */
	private Integer maxWaitThreadCount;

	/**
	 * 在发生致命错误（fatal error）时连接池的最大活动连接数
	 */
	private Integer onFatalErrorMaxActive;

	/**
	 * 在获取连接失败后，是否立即断开（标记为不可用）连接池中的所有连接
	 */
	private Boolean breakAfterAcquireFailure;

	/**
	 * 当连接池未满且没有可用连接时，系统在超时前重试获取连接的次数
	 */
	private Integer notFullTimeoutRetryCount;

	/**
	 * 是否使用本地会话状态；这个配置主要用于处理连接池中的数据库连接在会话级别的状态管理。
	 */
	private Boolean useLocalSessionState;

	/**
	 * 是否缓存 {@link PreparedStatement}，也就是PSCache；
	 * PSCache 对支持游标的数据库性能提升巨大，比如说 oracle，在mysql下建议关闭
	 */
	private Boolean poolPreparedStatements;

	/**
	 * 连接池中是否启用 {@link PreparedStatement} 的共享功能
	 */
	private Boolean sharePreparedStatements;

	/**
	 * 每个数据库连接上可以缓存的 {@link PreparedStatement} 对象的最大数量
	 */
	private Integer maxPoolPreparedStatementPerConnectionSize;

	/**
	 * 最大打开 PSCache 数，在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些
	 */
	private Integer maxOpenPreparedStatements;

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

	/**
	 * 日志统计信息的记录间隔时间
	 */
	private Duration timeBetweenLogStats;

	/**
	 * 是否在连接关闭时记录日志
	 */
	private Boolean dupCloseLogEnable;

	/**
	 * 控制在获取连接和关闭连接时是否记录线程不一致的情况
	 *
	 * @since 3.0.0
	 */
	private Boolean logDifferentThread;

	/**
	 * 返回最大连接数
	 *
	 * @return 最大连接数
	 */
	public Integer getMaxActive() {
		return maxActive;
	}

	/**
	 * 设置最大连接数
	 *
	 * @param maxActive
	 * 		最大连接数
	 */
	public void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
		super.setMaxTotal(maxActive);
	}

	@Override
	public void setMaxTotal(Integer maxTotal) {
		setMaxActive(maxTotal);
	}

	/**
	 * 返回数据库连接池中的空闲连接是否保持存活
	 *
	 * @return 数据库连接池中的空闲连接是否保持存活
	 *
	 * @since 3.0.0
	 */
	public Boolean isKeepAlive() {
		return getKeepAlive();
	}

	/**
	 * 返回数据库连接池中的空闲连接是否保持存活
	 *
	 * @return 数据库连接池中的空闲连接是否保持存活
	 *
	 * @since 3.0.0
	 */
	public Boolean getKeepAlive() {
		return keepAlive;
	}

	/**
	 * 设置数据库连接池中的空闲连接是否保持存活
	 *
	 * @param keepAlive
	 * 		数据库连接池中的空闲连接是否保持存活
	 *
	 * @since 3.0.0
	 */
	public void setKeepAlive(Boolean keepAlive) {
		this.keepAlive = keepAlive;
	}

	/**
	 * 返回连接池中空闲连接的保活间隔时间
	 *
	 * @return 连接池中空闲连接的保活间隔时间
	 */
	public Duration getKeepAliveBetweenTime() {
		return keepAliveBetweenTime;
	}

	/**
	 * 设置连接池中空闲连接的保活间隔时间
	 *
	 * @param keepAliveBetweenTime
	 * 		连接池中空闲连接的保活间隔时间
	 */
	public void setKeepAliveBetweenTime(Duration keepAliveBetweenTime) {
		this.keepAliveBetweenTime = keepAliveBetweenTime;
	}

	/**
	 * 返回测试连接是否有效时是否使用 PING 方法
	 *
	 * @return 测试连接是否有效时是否使用 PING 方法
	 *
	 * @since 3.0.0
	 */
	public Boolean isUsePingMethod() {
		return getUsePingMethod();
	}

	/**
	 * 返回测试连接是否有效时是否使用 PING 方法
	 *
	 * @return 测试连接是否有效时是否使用 PING 方法
	 *
	 * @since 3.0.0
	 */
	public Boolean getUsePingMethod() {
		return usePingMethod;
	}

	/**
	 * 设置测试连接是否有效时是否使用 PING 方法
	 *
	 * @param usePingMethod
	 * 		测试连接是否有效时是否使用 PING 方法
	 *
	 * @since 3.0.0
	 */
	public void setUsePingMethod(Boolean usePingMethod) {
		this.usePingMethod = usePingMethod;
	}

	/**
	 * 返回连接池在将连接返回到连接池时，是否保留连接的原始事务隔离级别
	 *
	 * @return 连接池在将连接返回到连接池时，是否保留连接的原始事务隔离级别
	 *
	 * @since 3.0.0
	 */
	public Boolean isKeepConnectionUnderlyingTransactionIsolation() {
		return getKeepConnectionUnderlyingTransactionIsolation();
	}

	/**
	 * 返回连接池在将连接返回到连接池时，是否保留连接的原始事务隔离级别
	 *
	 * @return 连接池在将连接返回到连接池时，是否保留连接的原始事务隔离级别
	 *
	 * @since 3.0.0
	 */
	public Boolean getKeepConnectionUnderlyingTransactionIsolation() {
		return keepConnectionUnderlyingTransactionIsolation;
	}

	/**
	 * 设置连接池在将连接返回到连接池时，是否保留连接的原始事务隔离级别
	 *
	 * @param keepConnectionUnderlyingTransactionIsolation
	 * 		连接池在将连接返回到连接池时，是否保留连接的原始事务隔离级别
	 *
	 * @since 3.0.0
	 */
	public void setKeepConnectionUnderlyingTransactionIsolation(Boolean keepConnectionUnderlyingTransactionIsolation) {
		this.keepConnectionUnderlyingTransactionIsolation = keepConnectionUnderlyingTransactionIsolation;
	}

	/**
	 * 返回获取连接时最大等待时间，配置了 maxWait 之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置 useUnfairLock 属性为 true 使用非公平锁
	 *
	 * @return 获取连接时最大等待时间
	 */
	public Duration getMaxWait() {
		return super.getMaxWait();
	}

	/**
	 * 设置获取连接时最大等待时间，配置了 maxWait 之后，缺省启用公平锁，并发效率会有所下降，如果需要可以通过配置 useUnfairLock 属性为 true 使用非公平锁
	 *
	 * @param maxWait
	 * 		获取连接时最大等待时间
	 */
	public void setMaxWait(Duration maxWait) {
		super.setMaxWait(maxWait);
	}

	/**
	 * 返回连接池中创建连接任务的最大数量
	 *
	 * @return 连接池中创建连接任务的最大数量
	 */
	public Integer getMaxCreateTaskCount() {
		return maxCreateTaskCount;
	}

	/**
	 * 设置连接池中创建连接任务的最大数量
	 *
	 * @param maxCreateTaskCount
	 * 		连接池中创建连接任务的最大数量
	 */
	public void setMaxCreateTaskCount(Integer maxCreateTaskCount) {
		this.maxCreateTaskCount = maxCreateTaskCount;
	}

	/**
	 * 返回连接池中等待数据库连接的最大线程数量
	 *
	 * @return 连接池中等待数据库连接的最大线程数量
	 */
	public Integer getMaxWaitThreadCount() {
		return maxWaitThreadCount;
	}

	/**
	 * 设置连接池中等待数据库连接的最大线程数量
	 *
	 * @param maxWaitThreadCount
	 * 		连接池中等待数据库连接的最大线程数量
	 */
	public void setMaxWaitThreadCount(Integer maxWaitThreadCount) {
		this.maxWaitThreadCount = maxWaitThreadCount;
	}

	/**
	 * 返回在发生致命错误（fatal error）时连接池的最大活动连接数
	 *
	 * @return 在发生致命错误（fatal error）时连接池的最大活动连接数
	 */
	public Integer getOnFatalErrorMaxActive() {
		return onFatalErrorMaxActive;
	}

	/**
	 * 设置在发生致命错误（fatal error）时连接池的最大活动连接数
	 *
	 * @param onFatalErrorMaxActive
	 * 		在发生致命错误（fatal error）时连接池的最大活动连接数
	 */
	public void setOnFatalErrorMaxActive(Integer onFatalErrorMaxActive) {
		this.onFatalErrorMaxActive = onFatalErrorMaxActive;
	}

	/**
	 * 返回在获取连接失败后，是否立即断开（标记为不可用）连接池中的所有连接
	 *
	 * @return 在获取连接失败后，是否立即断开（标记为不可用）连接池中的所有连接
	 */
	public Boolean isBreakAfterAcquireFailure() {
		return getBreakAfterAcquireFailure();
	}

	/**
	 * 返回在获取连接失败后，是否立即断开（标记为不可用）连接池中的所有连接
	 *
	 * @return 在获取连接失败后，是否立即断开（标记为不可用）连接池中的所有连接
	 */
	public Boolean getBreakAfterAcquireFailure() {
		return breakAfterAcquireFailure;
	}

	/**
	 * 设置在获取连接失败后，是否立即断开（标记为不可用）连接池中的所有连接
	 *
	 * @param breakAfterAcquireFailure
	 * 		在获取连接失败后，是否立即断开（标记为不可用）连接池中的所有连接
	 */
	public void setBreakAfterAcquireFailure(Boolean breakAfterAcquireFailure) {
		this.breakAfterAcquireFailure = breakAfterAcquireFailure;
	}

	/**
	 * 返回当连接池未满且没有可用连接时，系统在超时前重试获取连接的次数
	 *
	 * @return 当连接池未满且没有可用连接时，系统在超时前重试获取连接的次数
	 */
	public Integer getNotFullTimeoutRetryCount() {
		return notFullTimeoutRetryCount;
	}

	/**
	 * 设置当连接池未满且没有可用连接时，系统在超时前重试获取连接的次数
	 *
	 * @param notFullTimeoutRetryCount
	 * 		当连接池未满且没有可用连接时，系统在超时前重试获取连接的次数
	 */
	public void setNotFullTimeoutRetryCount(Integer notFullTimeoutRetryCount) {
		this.notFullTimeoutRetryCount = notFullTimeoutRetryCount;
	}

	/**
	 * 返回是否使用本地会话状态
	 *
	 * @return 是否使用本地会话状态
	 */
	public Boolean isUseLocalSessionState() {
		return getUseLocalSessionState();
	}

	/**
	 * 返回是否使用本地会话状态
	 *
	 * @return 是否使用本地会话状态
	 */
	public Boolean getUseLocalSessionState() {
		return useLocalSessionState;
	}

	/**
	 * 设置是否使用本地会话状态
	 *
	 * @param useLocalSessionState
	 * 		是否使用本地会话状态
	 */
	public void setUseLocalSessionState(Boolean useLocalSessionState) {
		this.useLocalSessionState = useLocalSessionState;
	}

	/**
	 * 返回是否缓存 preparedStatement，也就是PSCache
	 *
	 * @return 是否缓存 preparedStatement，也就是PSCache
	 */
	public Boolean isPoolPreparedStatements() {
		return getPoolPreparedStatements();
	}

	/**
	 * 返回是否缓存 {@link PreparedStatement}，也就是PSCache
	 *
	 * @return 是否缓存 preparedStatement，也就是PSCache
	 */
	public Boolean getPoolPreparedStatements() {
		return poolPreparedStatements;
	}

	/**
	 * 设置是否缓存 {@link PreparedStatement}，也就是PSCache；
	 * PSCache 对支持游标的数据库性能提升巨大，比如说 oracle，在mysql下建议关闭
	 *
	 * @param poolPreparedStatements
	 * 		是否缓存 preparedStatement
	 */
	public void setPoolPreparedStatements(Boolean poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
	}

	/**
	 * 返回连接池中是否启用 {@link PreparedStatement} 的共享功能
	 *
	 * @return 连接池中是否启用 {@link PreparedStatement} 的共享功能
	 */
	public Boolean isSharePreparedStatements() {
		return getSharePreparedStatements();
	}

	/**
	 * 返回连接池中是否启用 {@link PreparedStatement} 的共享功能
	 *
	 * @return 连接池中是否启用 {@link PreparedStatement} 的共享功能
	 */
	public Boolean getSharePreparedStatements() {
		return sharePreparedStatements;
	}

	/**
	 * 设置连接池中是否启用 {@link PreparedStatement} 的共享功能
	 *
	 * @param sharePreparedStatements
	 * 		连接池中是否启用 {@link PreparedStatement} 的共享功能
	 */
	public void setSharePreparedStatements(Boolean sharePreparedStatements) {
		this.sharePreparedStatements = sharePreparedStatements;
	}

	/**
	 * 返回每个数据库连接上可以缓存的 {@link PreparedStatement} 对象的最大数量
	 *
	 * @return 每个数据库连接上可以缓存的 {@link PreparedStatement} 对象的最大数量
	 */
	public Integer getMaxPoolPreparedStatementPerConnectionSize() {
		return maxPoolPreparedStatementPerConnectionSize;
	}

	/**
	 * 设置每个数据库连接上可以缓存的 {@link PreparedStatement} 对象的最大数量
	 *
	 * @param maxPoolPreparedStatementPerConnectionSize
	 * 		每个数据库连接上可以缓存的 {@link PreparedStatement} 对象的最大数量
	 */
	public void setMaxPoolPreparedStatementPerConnectionSize(Integer maxPoolPreparedStatementPerConnectionSize) {
		this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
	}

	/**
	 * 返回最大打开 PSCache 数
	 *
	 * @return 最大打开 PSCache 数
	 */
	public Integer getMaxOpenPreparedStatements() {
		return maxOpenPreparedStatements;
	}

	/**
	 * 设置最大打开 PSCache 数，在Druid中，不会存在Oracle下PSCache占用内存过多的问题，可以把这个数值配置大一些
	 *
	 * @param maxOpenPreparedStatements
	 * 		最大打开 PSCache 数
	 */
	public void setMaxOpenPreparedStatements(Integer maxOpenPreparedStatements) {
		this.maxOpenPreparedStatements = maxOpenPreparedStatements;
	}

	/**
	 * 返回是否移除抛弃的（abandoned）连接
	 *
	 * @return 是否移除抛弃的（abandoned）连接
	 */
	public Boolean isRemoveAbandoned() {
		return getRemoveAbandoned();
	}

	/**
	 * 返回是否移除抛弃的（abandoned）连接
	 *
	 * @return 是否移除抛弃的（abandoned）连接
	 */
	public Boolean getRemoveAbandoned() {
		return removeAbandoned;
	}

	/**
	 * 设置是否移除抛弃的（abandoned）连接
	 *
	 * @param removeAbandoned
	 * 		是否移除抛弃的（abandoned）连接
	 */
	public void setRemoveAbandoned(Boolean removeAbandoned) {
		this.removeAbandoned = removeAbandoned;
	}

	/**
	 * 返回一个连接使用超过多久就视为抛弃的
	 *
	 * @return 一个连接使用超过多久就视为抛弃的
	 */
	public Duration getRemoveAbandonedTimeout() {
		return removeAbandonedTimeout;
	}

	/**
	 * 设置一个连接使用超过多久就视为抛弃的，该值应该超过你的应用中最长的SQL可能运行的时间
	 *
	 * @param removeAbandonedTimeout
	 * 		一个连接使用超过多久就视为抛弃的，该值应该超过你的应用中最长的SQL可能运行的时间
	 */
	public void setRemoveAbandonedTimeout(Duration removeAbandonedTimeout) {
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}

	/**
	 * 返回是否记录抛弃连接的应用的堆栈信息
	 *
	 * @return 是否记录抛弃连接的应用的堆栈信息
	 */
	public Boolean isLogAbandoned() {
		return getLogAbandoned();
	}

	/**
	 * 返回是否记录抛弃连接的应用的堆栈信息
	 *
	 * @return 是否记录抛弃连接的应用的堆栈信息
	 */
	public Boolean getLogAbandoned() {
		return logAbandoned;
	}

	/**
	 * 设置是否记录抛弃连接的应用的堆栈信息
	 *
	 * @param logAbandoned
	 * 		是否记录抛弃连接的应用的堆栈信息
	 */
	public void setLogAbandoned(Boolean logAbandoned) {
		this.logAbandoned = logAbandoned;
	}

	/**
	 * 返回日志统计信息的记录间隔时间
	 *
	 * @return 日志统计信息的记录间隔时间
	 */
	public Duration getTimeBetweenLogStats() {
		return timeBetweenLogStats;
	}

	/**
	 * 设置日志统计信息的记录间隔时间
	 *
	 * @param timeBetweenLogStats
	 * 		日志统计信息的记录间隔时间
	 */
	public void setTimeBetweenLogStats(Duration timeBetweenLogStats) {
		this.timeBetweenLogStats = timeBetweenLogStats;
	}

	/**
	 * 返回是否在连接关闭时记录日志
	 *
	 * @return 是否在连接关闭时记录日志
	 */
	public Boolean isDupCloseLogEnable() {
		return getDupCloseLogEnable();
	}

	/**
	 * 返回是否在连接关闭时记录日志
	 *
	 * @return 是否在连接关闭时记录日志
	 */
	public Boolean getDupCloseLogEnable() {
		return dupCloseLogEnable;
	}

	/**
	 * 设置是否在连接关闭时记录日志
	 *
	 * @param dupCloseLogEnable
	 * 		是否在连接关闭时记录日志
	 */
	public void setDupCloseLogEnable(Boolean dupCloseLogEnable) {
		this.dupCloseLogEnable = dupCloseLogEnable;
	}

	/**
	 * 返回是否在获取连接和关闭连接时是否记录线程不一致的情况
	 *
	 * @return 是否在获取连接和关闭连接时是否记录线程不一致的情况
	 *
	 * @since 3.0.0
	 */
	public Boolean isLogDifferentThread() {
		return getLogDifferentThread();
	}

	/**
	 * 返回是否在获取连接和关闭连接时是否记录线程不一致的情况
	 *
	 * @return 是否在获取连接和关闭连接时是否记录线程不一致的情况
	 *
	 * @since 3.0.0
	 */
	public Boolean getLogDifferentThread() {
		return logDifferentThread;
	}

	/**
	 * 设置在获取连接和关闭连接时是否记录线程不一致的情况
	 *
	 * @param logDifferentThread
	 * 		是否在获取连接和关闭连接时是否记录线程不一致的情况
	 *
	 * @since 3.0.0
	 */
	public void setLogDifferentThread(Boolean logDifferentThread) {
		this.logDifferentThread = logDifferentThread;
	}
}
