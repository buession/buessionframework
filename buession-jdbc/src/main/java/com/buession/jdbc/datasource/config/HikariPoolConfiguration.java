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
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.metrics.MetricsTrackerFactory;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/**
 * Hikari 数据源连接池配置 {@link HikariConfig}
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class HikariPoolConfiguration extends AbstractPoolConfiguration {

	/**
	 * 为支持 catalog 概念的数据库设置默认 catalog
	 */
	private String catalog;

	/**
	 * 从连接池获取连接时最大等待时间
	 */
	private Duration connectionTimeout;

	/**
	 * 连接允许在池中闲置的最长时间，仅适用于 minimumIdle 定义为小于 maximumPoolSize，值为 0 时空闲连接永远不会从池中删除
	 */
	private Duration idleTimeout;

	/**
	 * 控制在记录消息之前连接可能离开池的时间量，表明可能存在连接泄漏，值为 0 时泄漏检测被禁用
	 */
	private Long leakDetectionThreshold;

	/**
	 * 池中连接的最大生存期，值为 0 时表示无限寿命, 推荐设置的比数据库的 wait_timeout 小几秒到几分钟
	 */
	private Duration maxLifetime;

	/**
	 * 连接存活时间，值必须小于 maxLifetime 值
	 * 只会发生在空闲的连接上，当对一个给定的连接进行 "keepalive "的时间到了，该连接将从池中移除
	 */
	private Duration keepaliveTime;

	/**
	 * 最小空闲连接数量
	 */
	private Integer minIdle;

	/**
	 * 连接池中可以保留连接的最大数
	 */
	private Integer maxPoolSize;

	private Duration initializationFailTimeout;

	/**
	 * 设置一个SQL语句，在将每个新连接创建后，将其添加到池中之前执行该语句
	 */
	private String connectionInitSql;

	/**
	 * 设置一个SQL语句, 从连接池获取连接时, 先执行改 sql, 验证连接是否可用
	 * 如果是使用了 JDBC 4 那么不建议配置这个选项, 因为JDBC 4 使用 ping 命令, 更加高效
	 */
	private String connectionTestQuery;

	/**
	 * 检测连接是否有效的超时时间
	 */
	private Duration validationTimeout;

	/**
	 * 用户定义连接池的名称，主要出现在日志记录和 JMX 管理控制台中以识别池和池配置
	 */
	private String poolName;

	/**
	 * 设置的默认模式为支持模式的概念数据库；如果未指定此属性，则使用由JDBC驱动程序定义的默认模式
	 */
	private String schema;

	/**
	 * 事务隔离级别
	 */
	private TransactionIsolation transactionIsolation;

	/**
	 * 是否自动提交事务
	 */
	private Boolean autoCommit;

	/**
	 * 连接是否是只读模式
	 */
	private Boolean readOnly;

	private Boolean isolateInternalQueries;

	/**
	 * 是否自动注册 JMX 相关的 bean
	 */
	private Boolean registerMbeans;

	/**
	 * 是否允许JMX 将连接池挂起
	 */
	private Boolean allowPoolSuspension;

	/**
	 * 线程工厂
	 */
	private ThreadFactory threadFactory;

	/**
	 * 调度任务执行器
	 */
	private ScheduledExecutorService scheduledExecutor;

	private MetricsTrackerFactory metricsTrackerFactory;

	private Object metricRegistry;

	private Object healthCheckRegistry;

	private Properties healthCheckProperties;

	/**
	 * 返回为支持 catalog 概念的数据库设置默认 catalog
	 *
	 * @return 为支持 catalog 概念的数据库设置默认 catalog
	 */
	public String getCatalog(){
		return catalog;
	}

	/**
	 * 设置为支持 catalog 概念的数据库设置默认 catalog
	 *
	 * @param catalog
	 * 		为支持 catalog 概念的数据库设置默认 catalog
	 */
	public void setCatalog(String catalog){
		this.catalog = catalog;
	}

	/**
	 * 返回从连接池获取连接时最大等待时间
	 *
	 * @return 从连接池获取连接时最大等待时间
	 */
	public Duration getConnectionTimeout(){
		return connectionTimeout;
	}

	/**
	 * 设置从连接池获取连接时最大等待时间
	 *
	 * @param connectionTimeout
	 * 		从连接池获取连接时最大等待时间
	 */
	public void setConnectionTimeout(Duration connectionTimeout){
		this.connectionTimeout = connectionTimeout;
	}

	/**
	 * 返回连接允许在池中闲置的最长时间
	 *
	 * @return 连接允许在池中闲置的最长时间
	 */
	public Duration getIdleTimeout(){
		return idleTimeout;
	}

	/**
	 * 设置连接允许在池中闲置的最长时间，仅适用于 minimumIdle 定义为小于 maximumPoolSize，值为 0 时空闲连接永远不会从池中删除
	 * 如果 idleTimeout + 1秒 &gt; maxLifetime 且 maxLifetime &gt; 0，则会被重置为 0；
	 * 如果  idleTimeout != 0 且小于 10 秒，则会被重置为 10 秒
	 *
	 * @param idleTimeout
	 * 		连接允许在池中闲置的最长时间
	 */
	public void setIdleTimeout(Duration idleTimeout){
		this.idleTimeout = idleTimeout;
	}

	/**
	 * 返回记录消息之前连接可能离开池的时间量，表明可能存在连接泄漏，值为 0 时泄漏检测被禁用
	 *
	 * @return 记录消息之前连接可能离开池的时间量
	 */
	public Long getLeakDetectionThreshold(){
		return leakDetectionThreshold;
	}

	/**
	 * 设置记录消息之前连接可能离开池的时间量，表明可能存在连接泄漏，值为 0 时泄漏检测被禁用
	 *
	 * @param leakDetectionThreshold
	 * 		记录消息之前连接可能离开池的时间量
	 */
	public void setLeakDetectionThreshold(Long leakDetectionThreshold){
		this.leakDetectionThreshold = leakDetectionThreshold;
	}

	/**
	 * 返回池中连接的最大生存期，值为 0 时表示无限寿命
	 *
	 * @return 池中连接的最大生存期
	 */
	public Duration getMaxLifetime(){
		return maxLifetime;
	}

	/**
	 * 设置池中连接的最大生存期，值为 0 时表示无限寿命, 推荐设置的比数据库的 wait_timeout 小几秒到几分钟
	 *
	 * @param maxLifetime
	 * 		池中连接的最大生存期
	 */
	public void setMaxLifetime(Duration maxLifetime){
		this.maxLifetime = maxLifetime;
	}

	/**
	 * 返回连接存活时间，当对一个给定的连接进行 "keepalive "的时间到了，该连接将从池中移除
	 *
	 * @return 连接存活时间
	 */
	public Duration getKeepaliveTime(){
		return keepaliveTime;
	}

	/**
	 * 设置连接存活时间，值必须小于 maxLifetime 值
	 *
	 * @param keepaliveTime
	 * 		连接存活时间
	 */
	public void setKeepaliveTime(Duration keepaliveTime){
		this.keepaliveTime = keepaliveTime;
	}

	/**
	 * 返回最小空闲连接数量
	 *
	 * @return 最小空闲连接数量
	 */
	public Integer getMinIdle(){
		return minIdle;
	}

	/**
	 * 设置最小空闲连接数量
	 *
	 * @param minIdle
	 * 		最小空闲连接数量
	 */
	public void setMinIdle(Integer minIdle){
		this.minIdle = minIdle;
	}

	/**
	 * 返回连接池中可以保留连接的最大数
	 *
	 * @return 连接池中可以保留连接的最大数
	 */
	public Integer getMaxPoolSize(){
		return maxPoolSize;
	}

	/**
	 * 设置连接池中可以保留连接的最大数
	 *
	 * @param maxPoolSize
	 * 		连接池中可以保留连接的最大数
	 */
	public void setMaxPoolSize(Integer maxPoolSize){
		this.maxPoolSize = maxPoolSize;
	}

	public Duration getInitializationFailTimeout(){
		return initializationFailTimeout;
	}

	public void setInitializationFailTimeout(Duration initializationFailTimeout){
		this.initializationFailTimeout = initializationFailTimeout;
	}

	/**
	 * 返回在将每个新连接创建后，将其添加到池中之前执行的SQL语句
	 *
	 * @return 每个新连接创建后，将其添加到池中之前执行的SQL语句
	 */
	public String getConnectionInitSql(){
		return connectionInitSql;
	}

	/**
	 * 设置每个新连接创建后，将其添加到池中之前执行的SQL语句
	 *
	 * @param connectionInitSql
	 * 		每个新连接创建后，将其添加到池中之前执行的SQL语句
	 */
	public void setConnectionInitSql(String connectionInitSql){
		this.connectionInitSql = connectionInitSql;
	}

	/**
	 * 返回从连接池获取连接时, 验证连接是否可用的SQL语句
	 *
	 * @return 从连接池获取连接时, 验证连接是否可用的SQL语句
	 */
	public String getConnectionTestQuery(){
		return connectionTestQuery;
	}

	/**
	 * 设置从连接池获取连接时, 验证连接是否可用的SQL语句
	 *
	 * @param connectionTestQuery
	 * 		从连接池获取连接时, 验证连接是否可用的SQL语句
	 */
	public void setConnectionTestQuery(String connectionTestQuery){
		this.connectionTestQuery = connectionTestQuery;
	}

	/**
	 * 返回检测连接是否有效的超时时间
	 *
	 * @return 检测连接是否有效的超时时间
	 */
	public Duration getValidationTimeout(){
		return validationTimeout;
	}

	/**
	 * 设置检测连接是否有效的超时时间，不能大于 {@link #connectionTimeout}
	 *
	 * @param validationTimeout
	 * 		检测连接是否有效的超时时间
	 */
	public void setValidationTimeout(Duration validationTimeout){
		this.validationTimeout = validationTimeout;
	}

	/**
	 * 返回用户定义连接池的名称
	 *
	 * @return 用户定义连接池的名称
	 */
	public String getPoolName(){
		return poolName;
	}

	/**
	 * 设置连接池的名称
	 *
	 * @param poolName
	 * 		连接池的名称
	 */
	public void setPoolName(String poolName){
		this.poolName = poolName;
	}

	/**
	 * 返回默认模式为支持模式的概念数据库
	 *
	 * @return 默认模式为支持模式的概念数据库
	 */
	public String getSchema(){
		return schema;
	}

	/**
	 * 设置默认模式为支持模式的概念数据库
	 *
	 * @param schema
	 * 		默认模式为支持模式的概念数据库
	 */
	public void setSchema(String schema){
		this.schema = schema;
	}

	/**
	 * 返回事务隔离级别
	 *
	 * @return 事务隔离级别
	 */
	public TransactionIsolation getTransactionIsolation(){
		return transactionIsolation;
	}

	/**
	 * 设置事务隔离级别
	 *
	 * @param transactionIsolation
	 * 		事务隔离级别
	 */
	public void setTransactionIsolation(TransactionIsolation transactionIsolation){
		this.transactionIsolation = transactionIsolation;
	}

	/**
	 * 返回是否自动提交事务
	 *
	 * @return 是否自动提交事务
	 */
	@Deprecated
	public Boolean isAutoCommit(){
		return getAutoCommit();
	}

	/**
	 * 返回是否自动提交事务
	 *
	 * @return 是否自动提交事务
	 */
	public Boolean getAutoCommit(){
		return autoCommit;
	}

	/**
	 * 设置是否自动提交事务
	 *
	 * @param autoCommit
	 * 		是否自动提交事务
	 */
	public void setAutoCommit(Boolean autoCommit){
		this.autoCommit = autoCommit;
	}

	/**
	 * 返回连接是否是只读模式
	 *
	 * @return 连接是否是只读模式
	 */
	@Deprecated
	public Boolean isReadOnly(){
		return getReadOnly();
	}

	/**
	 * 返回连接是否是只读模式
	 *
	 * @return 连接是否是只读模式
	 */
	public Boolean getReadOnly(){
		return readOnly;
	}

	/**
	 * 设置连接是否是只读模式
	 *
	 * @param readOnly
	 * 		连接是否是只读模式
	 */
	public void setReadOnly(Boolean readOnly){
		this.readOnly = readOnly;
	}

	@Deprecated
	public Boolean isIsolateInternalQueries(){
		return getIsolateInternalQueries();
	}

	public Boolean getIsolateInternalQueries(){
		return isolateInternalQueries;
	}

	public void setIsolateInternalQueries(Boolean isolateInternalQueries){
		this.isolateInternalQueries = isolateInternalQueries;
	}

	/**
	 * 返回是否自动注册 JMX 相关的 bean
	 *
	 * @return 是否自动注册 JMX 相关的 bean
	 */
	@Deprecated
	public Boolean isRegisterMbeans(){
		return getRegisterMbeans();
	}

	/**
	 * 返回是否自动注册 JMX 相关的 bean
	 *
	 * @return 是否自动注册 JMX 相关的 bean
	 */
	public Boolean getRegisterMbeans(){
		return registerMbeans;
	}

	/**
	 * 设置是否自动注册 JMX 相关的 bean
	 *
	 * @param registerMbeans
	 * 		是否自动注册 JMX 相关的 bean
	 */
	public void setRegisterMbeans(Boolean registerMbeans){
		this.registerMbeans = registerMbeans;
	}

	/**
	 * 返回是否允许JMX 将连接池挂起
	 *
	 * @return 是否允许JMX 将连接池挂起
	 */
	@Deprecated
	public Boolean isAllowPoolSuspension(){
		return getAllowPoolSuspension();
	}

	/**
	 * 返回是否允许JMX 将连接池挂起
	 *
	 * @return 是否允许JMX 将连接池挂起
	 */
	public Boolean getAllowPoolSuspension(){
		return allowPoolSuspension;
	}

	/**
	 * 设置是否允许JMX 将连接池挂起
	 *
	 * @param allowPoolSuspension
	 * 		是否允许JMX 将连接池挂起
	 */
	public void setAllowPoolSuspension(Boolean allowPoolSuspension){
		this.allowPoolSuspension = allowPoolSuspension;
	}

	/**
	 * 返回线程工厂
	 *
	 * @return 线程工厂
	 */
	public ThreadFactory getThreadFactory(){
		return threadFactory;
	}

	/**
	 * 设置线程工厂
	 *
	 * @param threadFactory
	 * 		线程工厂
	 */
	public void setThreadFactory(ThreadFactory threadFactory){
		this.threadFactory = threadFactory;
	}

	/**
	 * 返回调度任务执行器
	 *
	 * @return 调度任务执行器
	 */
	public ScheduledExecutorService getScheduledExecutor(){
		return scheduledExecutor;
	}

	/**
	 * 设置调度任务执行器
	 *
	 * @param scheduledExecutor
	 * 		调度任务执行器
	 */
	public void setScheduledExecutor(ScheduledExecutorService scheduledExecutor){
		this.scheduledExecutor = scheduledExecutor;
	}

	public MetricsTrackerFactory getMetricsTrackerFactory(){
		return metricsTrackerFactory;
	}

	public void setMetricsTrackerFactory(MetricsTrackerFactory metricsTrackerFactory){
		this.metricsTrackerFactory = metricsTrackerFactory;
	}

	public Object getMetricRegistry(){
		return metricRegistry;
	}

	public void setMetricRegistry(Object metricRegistry){
		this.metricRegistry = metricRegistry;
	}

	public Object getHealthCheckRegistry(){
		return healthCheckRegistry;
	}

	public void setHealthCheckRegistry(Object healthCheckRegistry){
		this.healthCheckRegistry = healthCheckRegistry;
	}

	public Properties getHealthCheckProperties(){
		return healthCheckProperties;
	}

	public void setHealthCheckProperties(Properties healthCheckProperties){
		this.healthCheckProperties = healthCheckProperties;
	}

}
