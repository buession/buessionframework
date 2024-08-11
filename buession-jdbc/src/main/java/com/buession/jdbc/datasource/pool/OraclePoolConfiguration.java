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
package com.buession.jdbc.datasource.pool;

import java.time.Duration;

/**
 * Oracle 数据源连接池配置
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class OraclePoolConfiguration extends AbstractPoolConfiguration {

	/**
	 * 初始连接数，池被启动时初始化的创建的连接个数
	 */
	private Integer initialPoolSize;

	/**
	 * 连接池中最小的连接数量，当为 0 时，连接池在空闲时可以完全没有连接
	 */
	private Integer minPoolSize;

	/**
	 * 连接池中可以保留连接的最大数，如设置为负数，则不限制
	 */
	private Integer maxPoolSize;

	/**
	 * 获取连接时最大等待时间
	 */
	private Duration connectionWait;

	/**
	 * 连接在连接池中保持空闲状态的最大时间
	 */
	private Duration inactiveConnectionTimeout;

	/**
	 * 连接在连接池中保持空闲状态的最大时间
	 */
	private Duration maxIdleTime;

	/**
	 * 连接在连接池中的最大生命周期
	 */
	private Duration timeToLiveConnectionTimeout;

	/**
	 * 在一个连接空闲指定时间后，认为该连接仍然有效并且可以直接重用的时间阈值
	 */
	private Duration trustIdleConnection;

	/**
	 * 连接池中的连接在被强制关闭之前可以被重复使用的最长时间
	 */
	private Duration maxConnectionReuseTime;

	/**
	 * 连接池中的连接可以被重复使用的最大次数
	 */
	private Integer maxConnectionReuseCount;

	/**
	 * 定义在连接标签操作中被认为是“高成本”的阈值时间
	 */
	private Duration connectionLabelingHighCost;

	/**
	 * 连接的重用次数在达到这个阈值后会被标记为“高成本重用”
	 */
	private Integer highCostConnectionReuseThreshold;

	/**
	 * 定义连接在达到指定的重用次数后，是否应该被重新配置（repurpose）
	 */
	private Integer connectionRepurposeThreshold;

	/**
	 * 连接池检查连接超时的间隔时间
	 */
	private Duration timeoutCheckInterval;

	/**
	 * 连接池中每个连接所能缓存的最大 SQL 预编译语句（Prepared Statements）的数量
	 */
	private Integer maxStatements;

	/**
	 * 触发连接池收割（Harvesting）操作的阈值，表示连接池中空闲连接的数量达到多少时触发收割操作
	 */
	private Integer connectionHarvestTriggerCount;

	/**
	 * 在一次连接收割操作中，最多可以从连接池中收回的连接数量
	 */
	private Integer connectionHarvestMaxCount;

	/**
	 * 从连接池获取一个连接时，验证有效性；
	 * 指明在从池中租借对象时是否要进行验证有效，如果对象验证失败，则对象将从池子释放，然后我们将尝试租借另一个
	 */
	private Boolean validateConnectionOnBorrow;

	/**
	 * 连接在被认为是“被遗弃”状态下的最大时间
	 */
	private Duration abandonedConnectionTimeout;

	/**
	 * 否允许从只读实例获取连接
	 */
	private Boolean readOnlyInstanceAllowed;

	/**
	 * 是否在借用连接的线程中创建连接
	 */
	private Boolean createConnectionInBorrowThread;

	/**
	 * 返回初始连接数
	 *
	 * @return 初始连接数
	 */
	public Integer getInitialPoolSize() {
		return initialPoolSize;
	}

	/**
	 * 设置初始连接数
	 *
	 * @param initialPoolSize
	 * 		初始连接数
	 */
	public void setInitialPoolSize(Integer initialPoolSize) {
		this.initialPoolSize = initialPoolSize;
		super.setInitialSize(initialPoolSize);
	}

	@Override
	public void setInitialSize(Integer initialPoolSize) {
		setInitialPoolSize(initialPoolSize);
	}

	/**
	 * 返回连接池中最小的连接数量
	 *
	 * @return 连接池中最小的连接数量
	 */
	public Integer getMinPoolSize() {
		return minPoolSize;
	}

	/**
	 * 设置连接池中最小的连接数量
	 *
	 * @param minPoolSize
	 * 		连接池中最小的连接数量
	 */
	public void setMinPoolSize(Integer minPoolSize) {
		this.minPoolSize = minPoolSize;
	}

	/**
	 * 返回连接池中可以保留连接的最大数
	 *
	 * @return 连接池中可以保留连接的最大数
	 */
	public Integer getMaxPoolSize() {
		return maxPoolSize;
	}

	/**
	 * 设置连接池中可以保留连接的最大数
	 *
	 * @param maxPoolSize
	 * 		连接池中可以保留连接的最大数
	 */
	public void setMaxPoolSize(Integer maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
		super.setMaxTotal(maxPoolSize);
	}

	@Override
	public void setMaxTotal(Integer maxTotal) {
		setMaxPoolSize(maxTotal);
	}

	/**
	 * 返回获取连接时最大等待时间
	 *
	 * @return 获取连接时最大等待时间
	 */
	public Duration getConnectionWait() {
		return connectionWait;
	}

	/**
	 * 设置获取连接时最大等待时间
	 *
	 * @param connectionWait
	 * 		获取连接时最大等待时间
	 */
	public void setConnectionWait(Duration connectionWait) {
		this.connectionWait = connectionWait;
		super.setMaxWait(connectionWait);
	}

	@Override
	public void setMaxWait(Duration maxWait) {
		setConnectionWait(maxWait);
	}

	/**
	 * 返回连接在连接池中保持空闲状态的最大时间
	 *
	 * @return 连接在连接池中保持空闲状态的最大时间
	 */
	public Duration getInactiveConnectionTimeout() {
		return inactiveConnectionTimeout;
	}

	/**
	 * 设置连接在连接池中保持空闲状态的最大时间
	 *
	 * @param inactiveConnectionTimeout
	 * 		连接在连接池中保持空闲状态的最大时间
	 */
	public void setInactiveConnectionTimeout(Duration inactiveConnectionTimeout) {
		this.inactiveConnectionTimeout = inactiveConnectionTimeout;
		this.maxIdleTime = inactiveConnectionTimeout;
	}

	/**
	 * 返回连接在连接池中保持空闲状态的最大时间
	 *
	 * @return 连接在连接池中保持空闲状态的最大时间
	 */
	public Duration getMaxIdleTime() {
		return maxIdleTime;
	}

	/**
	 * 设置连接在连接池中保持空闲状态的最大时间
	 *
	 * @param maxIdleTime
	 * 		连接在连接池中保持空闲状态的最大时间
	 */
	public void setMaxIdleTime(Duration maxIdleTime) {
		this.maxIdleTime = maxIdleTime;
		this.inactiveConnectionTimeout = maxIdleTime;
	}

	/**
	 * 返回连接在连接池中的最大生命周期
	 *
	 * @return 连接在连接池中的最大生命周期
	 */
	public Duration getTimeToLiveConnectionTimeout() {
		return timeToLiveConnectionTimeout;
	}

	/**
	 * 设置连接在连接池中的最大生命周期
	 *
	 * @param timeToLiveConnectionTimeout
	 * 		连接在连接池中的最大生命周期
	 */
	public void setTimeToLiveConnectionTimeout(Duration timeToLiveConnectionTimeout) {
		this.timeToLiveConnectionTimeout = timeToLiveConnectionTimeout;
	}

	/**
	 * 返回在一个连接空闲指定时间后，认为该连接仍然有效并且可以直接重用的时间阈值
	 *
	 * @return 在一个连接空闲指定时间后，认为该连接仍然有效并且可以直接重用的时间阈值
	 */
	public Duration getTrustIdleConnection() {
		return trustIdleConnection;
	}

	/**
	 * 设置在一个连接空闲指定时间后，认为该连接仍然有效并且可以直接重用的时间阈值
	 *
	 * @param trustIdleConnection
	 * 		在一个连接空闲指定时间后，认为该连接仍然有效并且可以直接重用的时间阈值
	 */
	public void setTrustIdleConnection(Duration trustIdleConnection) {
		this.trustIdleConnection = trustIdleConnection;
	}

	/**
	 * 返回连接池中的连接在被强制关闭之前可以被重复使用的最长时间
	 *
	 * @return 连接池中的连接在被强制关闭之前可以被重复使用的最长时间
	 */
	public Duration getMaxConnectionReuseTime() {
		return maxConnectionReuseTime;
	}

	/**
	 * 设置连接池中的连接在被强制关闭之前可以被重复使用的最长时间
	 *
	 * @param maxConnectionReuseTime
	 * 		连接池中的连接在被强制关闭之前可以被重复使用的最长时间
	 */
	public void setMaxConnectionReuseTime(Duration maxConnectionReuseTime) {
		this.maxConnectionReuseTime = maxConnectionReuseTime;
	}

	/**
	 * 返回连接池中的连接可以被重复使用的最大次数
	 *
	 * @return 连接池中的连接可以被重复使用的最大次数
	 */
	public Integer getMaxConnectionReuseCount() {
		return maxConnectionReuseCount;
	}

	/**
	 * 设置连接池中的连接可以被重复使用的最大次数
	 *
	 * @param maxConnectionReuseCount
	 * 		连接池中的连接可以被重复使用的最大次数
	 */
	public void setMaxConnectionReuseCount(Integer maxConnectionReuseCount) {
		this.maxConnectionReuseCount = maxConnectionReuseCount;
	}

	/**
	 * 返回定义在连接标签操作中被认为是“高成本”的阈值时间
	 *
	 * @return 定义在连接标签操作中被认为是“高成本”的阈值时间
	 */
	public Duration getConnectionLabelingHighCost() {
		return connectionLabelingHighCost;
	}

	/**
	 * 设置定义在连接标签操作中被认为是“高成本”的阈值时间
	 *
	 * @param connectionLabelingHighCost
	 * 		定义在连接标签操作中被认为是“高成本”的阈值时间
	 */
	public void setConnectionLabelingHighCost(Duration connectionLabelingHighCost) {
		this.connectionLabelingHighCost = connectionLabelingHighCost;
	}

	/**
	 * 返回连接的重用次数在达到这个阈值后会被标记为“高成本重用”
	 *
	 * @return 连接的重用次数在达到这个阈值后会被标记为“高成本重用”
	 */
	public Integer getHighCostConnectionReuseThreshold() {
		return highCostConnectionReuseThreshold;
	}

	/**
	 * 设置连接的重用次数在达到这个阈值后会被标记为“高成本重用”
	 *
	 * @param highCostConnectionReuseThreshold
	 * 		连接的重用次数在达到这个阈值后会被标记为“高成本重用”
	 */
	public void setHighCostConnectionReuseThreshold(Integer highCostConnectionReuseThreshold) {
		this.highCostConnectionReuseThreshold = highCostConnectionReuseThreshold;
	}

	/**
	 * 返回定义连接在达到指定的重用次数后，是否应该被重新配置（repurpose）
	 *
	 * @return 定义连接在达到指定的重用次数后，是否应该被重新配置（repurpose）
	 */
	public Integer getConnectionRepurposeThreshold() {
		return connectionRepurposeThreshold;
	}

	/**
	 * 设置定义连接在达到指定的重用次数后，是否应该被重新配置（repurpose）
	 *
	 * @param connectionRepurposeThreshold
	 * 		定义连接在达到指定的重用次数后，是否应该被重新配置（repurpose）
	 */
	public void setConnectionRepurposeThreshold(Integer connectionRepurposeThreshold) {
		this.connectionRepurposeThreshold = connectionRepurposeThreshold;
	}

	/**
	 * 返回连接池检查连接超时的间隔时间
	 *
	 * @return 连接池检查连接超时的间隔时间
	 */
	public Duration getTimeoutCheckInterval() {
		return timeoutCheckInterval;
	}

	/**
	 * 设置连接池检查连接超时的间隔时间
	 *
	 * @param timeoutCheckInterval
	 * 		连接池检查连接超时的间隔时间
	 */
	public void setTimeoutCheckInterval(Duration timeoutCheckInterval) {
		this.timeoutCheckInterval = timeoutCheckInterval;
	}

	/**
	 * 返回连接池中每个连接所能缓存的最大 SQL 预编译语句（Prepared Statements）的数量
	 *
	 * @return 连接池中每个连接所能缓存的最大 SQL 预编译语句（Prepared Statements）的数量
	 */
	public Integer getMaxStatements() {
		return maxStatements;
	}

	/**
	 * 设置连接池中每个连接所能缓存的最大 SQL 预编译语句（Prepared Statements）的数量
	 *
	 * @param maxStatements
	 * 		连接池中每个连接所能缓存的最大 SQL 预编译语句（Prepared Statements）的数量
	 */
	public void setMaxStatements(Integer maxStatements) {
		this.maxStatements = maxStatements;
	}

	/**
	 * 返回触发连接池收割（Harvesting）操作的阈值，表示连接池中空闲连接的数量达到多少时触发收割操作
	 *
	 * @return 触发连接池收割（Harvesting）操作的阈值，表示连接池中空闲连接的数量达到多少时触发收割操作
	 */
	public Integer getConnectionHarvestTriggerCount() {
		return connectionHarvestTriggerCount;
	}

	/**
	 * 设置触发连接池收割（Harvesting）操作的阈值，表示连接池中空闲连接的数量达到多少时触发收割操作
	 *
	 * @param connectionHarvestTriggerCount
	 * 		触发连接池收割（Harvesting）操作的阈值，表示连接池中空闲连接的数量达到多少时触发收割操作
	 */
	public void setConnectionHarvestTriggerCount(Integer connectionHarvestTriggerCount) {
		this.connectionHarvestTriggerCount = connectionHarvestTriggerCount;
	}

	/**
	 * 返回在一次连接收割操作中，最多可以从连接池中收回的连接数量
	 *
	 * @return 在一次连接收割操作中，最多可以从连接池中收回的连接数量
	 */
	public Integer getConnectionHarvestMaxCount() {
		return connectionHarvestMaxCount;
	}

	/**
	 * 设置在一次连接收割操作中，最多可以从连接池中收回的连接数量
	 *
	 * @param connectionHarvestMaxCount
	 * 		在一次连接收割操作中，最多可以从连接池中收回的连接数量
	 */
	public void setConnectionHarvestMaxCount(Integer connectionHarvestMaxCount) {
		this.connectionHarvestMaxCount = connectionHarvestMaxCount;
	}

	/**
	 * 返回从连接池获取一个连接时，是否验证有效性
	 *
	 * @return 从连接池获取一个连接时，验证有效性
	 */
	public Boolean isValidateConnectionOnBorrow() {
		return getValidateConnectionOnBorrow();
	}

	/**
	 * 返回从连接池获取一个连接时，是否验证有效性
	 *
	 * @return 从连接池获取一个连接时，验证有效性
	 */
	public Boolean getValidateConnectionOnBorrow() {
		return validateConnectionOnBorrow;
	}

	/**
	 * 设置从连接池获取一个连接时，是否验证有效性
	 *
	 * @param validateConnectionOnBorrow
	 * 		从连接池获取一个连接时，是否验证有效性
	 */
	public void setValidateConnectionOnBorrow(Boolean validateConnectionOnBorrow) {
		this.validateConnectionOnBorrow = validateConnectionOnBorrow;
		super.setTestOnBorrow(validateConnectionOnBorrow);
	}

	@Override
	public void setTestOnBorrow(Boolean testOnBorrow) {
		setValidateConnectionOnBorrow(testOnBorrow);
	}

	/**
	 * 返回连接在被认为是“被遗弃”状态下的最大时间
	 *
	 * @return 连接在被认为是“被遗弃”状态下的最大时间
	 */
	public Duration getAbandonedConnectionTimeout() {
		return abandonedConnectionTimeout;
	}

	/**
	 * 设置连接在被认为是“被遗弃”状态下的最大时间
	 *
	 * @param abandonedConnectionTimeout
	 * 		连接在被认为是“被遗弃”状态下的最大时间
	 */
	public void setAbandonedConnectionTimeout(Duration abandonedConnectionTimeout) {
		this.abandonedConnectionTimeout = abandonedConnectionTimeout;
		super.setRemoveAbandonedTimeout(abandonedConnectionTimeout);
	}

	@Override
	public void setRemoveAbandonedTimeout(Duration removeAbandonedTimeout) {
		setAbandonedConnectionTimeout(removeAbandonedTimeout);
	}

	/**
	 * 返回否允许从只读实例获取连接
	 *
	 * @return 否允许从只读实例获取连接
	 */
	public Boolean isReadOnlyInstanceAllowed() {
		return getReadOnlyInstanceAllowed();
	}

	/**
	 * 返回否允许从只读实例获取连接
	 *
	 * @return 否允许从只读实例获取连接
	 */
	public Boolean getReadOnlyInstanceAllowed() {
		return readOnlyInstanceAllowed;
	}

	/**
	 * 设置否允许从只读实例获取连接
	 *
	 * @param readOnlyInstanceAllowed
	 * 		否允许从只读实例获取连接
	 */
	public void setReadOnlyInstanceAllowed(Boolean readOnlyInstanceAllowed) {
		this.readOnlyInstanceAllowed = readOnlyInstanceAllowed;
	}

	/**
	 * 返回是否在借用连接的线程中创建连接
	 *
	 * @return 是否在借用连接的线程中创建连接
	 */
	public Boolean isCreateConnectionInBorrowThread() {
		return getCreateConnectionInBorrowThread();
	}

	/**
	 * 返回是否在借用连接的线程中创建连接
	 *
	 * @return 是否在借用连接的线程中创建连接
	 */
	public Boolean getCreateConnectionInBorrowThread() {
		return createConnectionInBorrowThread;
	}

	/**
	 * 设置是否在借用连接的线程中创建连接
	 *
	 * @param createConnectionInBorrowThread
	 * 		是否在借用连接的线程中创建连接
	 */
	public void setCreateConnectionInBorrowThread(Boolean createConnectionInBorrowThread) {
		this.createConnectionInBorrowThread = createConnectionInBorrowThread;
	}

}
