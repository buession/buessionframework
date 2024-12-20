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

import com.buession.jdbc.core.Jmx;
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
	 * 连接池无法成功建立连接，是否会立即抛出异常或进行重试，以及重试的超时时间
	 */
	private Duration initializationFailTimeout;

	/**
	 * 接池中最大连接数，如设置为负数，则不限制
	 */
	private Integer maxPoolSize;

	/**
	 * 设置一个SQL语句, 从连接池获取连接时, 先执行改 sql, 验证连接是否可用
	 * 如果是使用了 JDBC 4 那么不建议配置这个选项, 因为JDBC 4 使用 ping 命令, 更加高效
	 */
	private String connectionTestQuery;

	/**
	 * 检测连接是否有效的超时时间
	 *
	 * @since 3.0.0
	 */
	private Duration validationTimeout;

	/**
	 * 连接允许在池中闲置的最长时间，仅适用于 minimumIdle 定义为小于 maximumPoolSize，值为 0 时空闲连接永远不会从池中删除
	 */
	private Duration idleTimeout;

	/**
	 * 池中连接的最大生存期，值为 0 时表示无限寿命, 推荐设置的比数据库的 wait_timeout 小几秒到几分钟
	 */
	private Duration maxLifetime;

	/**
	 * 连接在空闲时发送“keep-alive”心跳的间隔时间
	 */
	private Duration keepaliveTime;

	/**
	 * 线程工厂
	 */
	private ThreadFactory threadFactory;

	/**
	 * 调度任务执行器
	 */
	private ScheduledExecutorService scheduledExecutor;

	/**
	 * 控制在记录消息之前连接可能离开池的时间量，表明可能存在连接泄漏，值为 0 时泄漏检测被禁用
	 */
	private Duration leakDetectionThreshold;

	/**
	 * 是否允许将连接池挂起
	 */
	private Boolean allowPoolSuspension;

	/**
	 * 连接池的性能指标跟踪器 {@link MetricsTrackerFactory} 类名
	 *
	 * @since 3.0.0
	 */
	private String metricsTrackerFactoryClassName;

	/**
	 * 允许将 HikariCP 连接池的性能指标集成到基于 Dropwizard Metrics 的监控系统中
	 *
	 * @since 3.0.0
	 */
	private String metricRegistryClassName;

	/**
	 * 允许将 HikariCP 连接池的健康检查功能与基于 Dropwizard Metrics 的健康检查机制集成
	 *
	 * @since 3.0.0
	 */
	private String healthCheckRegistryClassName;

	/**
	 * 健康检查的参数集合
	 */
	private Properties healthCheckProperties;

	/**
	 * 是否自动注册 JMX 相关的 MBeans
	 */
	private Boolean registerMbeans;

	/**
	 * 返回连接池无法成功建立连接，是否会立即抛出异常或进行重试，以及重试的超时时间
	 *
	 * @return 连接池无法成功建立连接，是否会立即抛出异常或进行重试，以及重试的超时时间
	 */
	public Duration getInitializationFailTimeout() {
		return initializationFailTimeout;
	}

	/**
	 * 设置连接池无法成功建立连接，是否会立即抛出异常或进行重试，以及重试的超时时间
	 *
	 * @param initializationFailTimeout
	 * 		连接池无法成功建立连接，是否会立即抛出异常或进行重试，以及重试的超时时间
	 */
	public void setInitializationFailTimeout(Duration initializationFailTimeout) {
		this.initializationFailTimeout = initializationFailTimeout;
	}

	/**
	 * 返回接池中最大连接数
	 *
	 * @return 接池中最大连接数
	 */
	public Integer getMaxPoolSize() {
		return maxPoolSize;
	}

	/**
	 * 设置接池中最大连接数
	 *
	 * @param maxPoolSize
	 * 		接池中最大连接数
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
	 * 返回从连接池获取连接时, 验证连接是否可用的SQL语句
	 *
	 * @return 从连接池获取连接时, 验证连接是否可用的SQL语句
	 *
	 * @since 3.0.0
	 */
	public String getConnectionTestQuery() {
		return connectionTestQuery;
	}

	/**
	 * 设置从连接池获取连接时, 验证连接是否可用的SQL语句
	 *
	 * @param connectionTestQuery
	 * 		从连接池获取连接时, 验证连接是否可用的SQL语句
	 *
	 * @since 3.0.0
	 */
	public void setConnectionTestQuery(String connectionTestQuery) {
		this.connectionTestQuery = connectionTestQuery;
		super.setValidationQuery(connectionTestQuery);
	}

	/**
	 * 返回检测连接是否有效的超时时间
	 *
	 * @return 检测连接是否有效的超时时间
	 *
	 * @since 3.0.0
	 */
	public Duration getValidationTimeout() {
		return validationTimeout;
	}

	/**
	 * 设置检测连接是否有效的超时时间，不能大于 {@link com.buession.jdbc.datasource.HikariDataSource#getConnectionTimeout()}
	 *
	 * @param validationTimeout
	 * 		检测连接是否有效的超时时间
	 *
	 * @since 3.0.0
	 */
	public void setValidationTimeout(Duration validationTimeout) {
		this.validationTimeout = validationTimeout;
		super.setValidationQueryTimeout(validationTimeout);
	}

	/**
	 * 返回连接允许在池中闲置的最长时间
	 *
	 * @return 连接允许在池中闲置的最长时间
	 */
	public Duration getIdleTimeout() {
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
	public void setIdleTimeout(Duration idleTimeout) {
		this.idleTimeout = idleTimeout;
	}

	/**
	 * 返回池中连接的最大生存期，值为 0 时表示无限寿命
	 *
	 * @return 池中连接的最大生存期
	 */
	public Duration getMaxLifetime() {
		return maxLifetime;
	}

	/**
	 * 设置池中连接的最大生存期，值为 0 时表示无限寿命, 推荐设置的比数据库的 wait_timeout 小几秒到几分钟
	 *
	 * @param maxLifetime
	 * 		池中连接的最大生存期
	 */
	public void setMaxLifetime(Duration maxLifetime) {
		this.maxLifetime = maxLifetime;
	}

	/**
	 * 返回连接在空闲时发送“keep-alive”心跳的间隔时间
	 *
	 * @return 连接在空闲时发送“keep-alive”心跳的间隔时间
	 */
	public Duration getKeepaliveTime() {
		return keepaliveTime;
	}

	/**
	 * 设置连接在空闲时发送“keep-alive”心跳的间隔时间
	 *
	 * @param keepaliveTime
	 * 		连接在空闲时发送“keep-alive”心跳的间隔时间
	 */
	public void setKeepaliveTime(Duration keepaliveTime) {
		this.keepaliveTime = keepaliveTime;
	}

	/**
	 * 返回线程工厂
	 *
	 * @return 线程工厂
	 */
	public ThreadFactory getThreadFactory() {
		return threadFactory;
	}

	/**
	 * 设置线程工厂
	 *
	 * @param threadFactory
	 * 		线程工厂
	 */
	public void setThreadFactory(ThreadFactory threadFactory) {
		this.threadFactory = threadFactory;
	}

	/**
	 * 返回调度任务执行器
	 *
	 * @return 调度任务执行器
	 */
	public ScheduledExecutorService getScheduledExecutor() {
		return scheduledExecutor;
	}

	/**
	 * 设置调度任务执行器
	 *
	 * @param scheduledExecutor
	 * 		调度任务执行器
	 */
	public void setScheduledExecutor(ScheduledExecutorService scheduledExecutor) {
		this.scheduledExecutor = scheduledExecutor;
	}

	/**
	 * 返回记录消息之前连接可能离开池的时间量，表明可能存在连接泄漏，值为 0 时泄漏检测被禁用
	 *
	 * @return 记录消息之前连接可能离开池的时间量
	 */
	public Duration getLeakDetectionThreshold() {
		return leakDetectionThreshold;
	}

	/**
	 * 设置记录消息之前连接可能离开池的时间量，表明可能存在连接泄漏，值为 0 时泄漏检测被禁用
	 *
	 * @param leakDetectionThreshold
	 * 		记录消息之前连接可能离开池的时间量
	 */
	public void setLeakDetectionThreshold(Duration leakDetectionThreshold) {
		this.leakDetectionThreshold = leakDetectionThreshold;
	}

	/**
	 * 返回是否允许将连接池挂起
	 *
	 * @return 是否允许将连接池挂起
	 */
	public Boolean isAllowPoolSuspension() {
		return getAllowPoolSuspension();
	}

	/**
	 * 返回是否允许将连接池挂起
	 *
	 * @return 是否允许将连接池挂起
	 */
	public Boolean getAllowPoolSuspension() {
		return allowPoolSuspension;
	}

	/**
	 * 设置是否允许将连接池挂起
	 *
	 * @param allowPoolSuspension
	 * 		是否允许将连接池挂起
	 */
	public void setAllowPoolSuspension(Boolean allowPoolSuspension) {
		this.allowPoolSuspension = allowPoolSuspension;
	}

	/**
	 * 返回连接池的性能指标跟踪器 {@link MetricsTrackerFactory} 类名
	 *
	 * @return 连接池的性能指标跟踪器 {@link MetricsTrackerFactory} 类名
	 *
	 * @since 3.0.0
	 */
	public String getMetricsTrackerFactoryClassName() {
		return metricsTrackerFactoryClassName;
	}

	/**
	 * 设置连接池的性能指标跟踪器 {@link MetricsTrackerFactory} 类名
	 *
	 * @param metricsTrackerFactoryClassName
	 * 		连接池的性能指标跟踪器 {@link MetricsTrackerFactory} 类名
	 *
	 * @since 3.0.0
	 */
	public void setMetricsTrackerFactoryClassName(String metricsTrackerFactoryClassName) {
		this.metricsTrackerFactoryClassName = metricsTrackerFactoryClassName;
	}

	/**
	 * 返回允许将 HikariCP 连接池的性能指标集成到基于 Dropwizard Metrics 的监控系统中
	 *
	 * @return 允许将 HikariCP 连接池的性能指标集成到基于 Dropwizard Metrics 的监控系统中
	 *
	 * @since 3.0.0
	 */
	public String getMetricRegistryClassName() {
		return metricRegistryClassName;
	}

	/**
	 * 设置允许将 HikariCP 连接池的性能指标集成到基于 Dropwizard Metrics 的监控系统中
	 *
	 * @param metricRegistryClassName
	 * 		允许将 HikariCP 连接池的性能指标集成到基于 Dropwizard Metrics 的监控系统中
	 *
	 * @since 3.0.0
	 */
	public void setMetricRegistryClassName(String metricRegistryClassName) {
		this.metricRegistryClassName = metricRegistryClassName;
	}

	/**
	 * 返回允许将 HikariCP 连接池的健康检查功能与基于 Dropwizard Metrics 的健康检查机制集成
	 *
	 * @return 允许将 HikariCP 连接池的健康检查功能与基于 Dropwizard Metrics 的健康检查机制集成
	 */
	public String getHealthCheckRegistryClassName() {
		return healthCheckRegistryClassName;
	}

	/**
	 * 设置允许将 HikariCP 连接池的健康检查功能与基于 Dropwizard Metrics 的健康检查机制集成
	 *
	 * @param healthCheckRegistryClassName
	 * 		允许将 HikariCP 连接池的健康检查功能与基于 Dropwizard Metrics 的健康检查机制集成
	 */
	public void setHealthCheckRegistryClassName(String healthCheckRegistryClassName) {
		this.healthCheckRegistryClassName = healthCheckRegistryClassName;
	}

	/**
	 * 返回健康检查的参数集合
	 *
	 * @return 健康检查的参数集合
	 */
	public Properties getHealthCheckProperties() {
		return healthCheckProperties;
	}

	/**
	 * 设置健康检查的参数集合
	 *
	 * @param healthCheckProperties
	 * 		健康检查的参数集合
	 */
	public void setHealthCheckProperties(Properties healthCheckProperties) {
		this.healthCheckProperties = healthCheckProperties;
	}

	/**
	 * 返回是否自动注册 JMX 相关的 MBeans
	 *
	 * @return 是否自动注册 JMX 相关的 MBeans
	 */
	public Boolean isRegisterMbeans() {
		return getRegisterMbeans();
	}

	/**
	 * 返回是否自动注册 JMX 相关的 MBeans
	 *
	 * @return 是否自动注册 JMX 相关的 MBeans
	 */
	public Boolean getRegisterMbeans() {
		if(registerMbeans == null){
			if(getJmx() != null){
				return getJmx().isEnabled();
			}
		}

		return registerMbeans;
	}

	/**
	 * 设置是否自动注册 JMX 相关的 MBeans
	 *
	 * @param registerMbeans
	 * 		是否自动注册 JMX 相关的 MBeans
	 */
	public void setRegisterMbeans(Boolean registerMbeans) {
		this.registerMbeans = registerMbeans;

		if(getJmx() == null){
			setJmx(new Jmx(registerMbeans));
		}else{
			getJmx().setEnabled(registerMbeans);
		}
	}

}
