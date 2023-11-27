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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.time.Duration;

/**
 * 连接池配置
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class PoolConfig {

	/**
	 * 池模式，为 true 时，后进先出；为 false 时，先进先出
	 */
	private boolean lifo = GenericObjectPoolConfig.DEFAULT_LIFO;

	/**
	 * 当从池中获取资源或者将资源还回池中时，是否使用 {@link java.util.concurrent.locks.ReentrantLock} 的公平锁机制
	 */
	private boolean fairness = GenericObjectPoolConfig.DEFAULT_FAIRNESS;

	/**
	 * 当连接池资源用尽后，调用者获取连接时的最大等待时间
	 */
	private Duration maxWait = GenericObjectPoolConfig.DEFAULT_MAX_WAIT;

	/**
	 * 连接的最小空闲时间，达到此值后且已达最大空闲连接数该空闲连接可能会被移除
	 */
	private Duration minEvictableIdleTime = Duration.ofMillis(60000);

	/**
	 * 连接空闲的最小时间，达到此值后空闲链接将会被移除，且保留 minIdle 个空闲连接数
	 */
	private Duration softMinEvictableIdleTime = GenericObjectPoolConfig.DEFAULT_SOFT_MIN_EVICTABLE_IDLE_DURATION;

	/**
	 * 驱逐策略的类名
	 */
	private String evictionPolicyClassName = GenericObjectPoolConfig.DEFAULT_EVICTION_POLICY_CLASS_NAME;

	/**
	 * 关闭驱逐线程的超时时间
	 */
	private Duration evictorShutdownTimeout = GenericObjectPoolConfig.DEFAULT_EVICTOR_SHUTDOWN_TIMEOUT;

	/**
	 * 检测空闲对象线程每次运行时检测的空闲对象的数量；
	 * 如果 numTestsPerEvictionRun >= 0, 则取 numTestsPerEvictionRun 和池内的连接数的较小值作为每次检测的连接数；
	 * 如果 numTestsPerEvictionRun < 0，则每次检查的连接数是检查时池内连接的总数除以这个值的绝对值再向上取整的结果
	 */
	private int numTestsPerEvictionRun = -1;

	/**
	 * 在创建对象时检测对象是否有效，配置 true 会降低性能
	 */
	private boolean testOnCreate = GenericObjectPoolConfig.DEFAULT_TEST_ON_CREATE;

	/**
	 * 在从对象池获取对象时是否检测对象有效，配置 true 会降低性能
	 */
	private boolean testOnBorrow = GenericObjectPoolConfig.DEFAULT_TEST_ON_BORROW;

	/**
	 * 在向对象池中归还对象时是否检测对象有效，配置 true 会降低性能
	 */
	private boolean testOnReturn = GenericObjectPoolConfig.DEFAULT_TEST_ON_RETURN;

	/**
	 * 在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性；建议配置为 true，不影响性能，并且保证安全性
	 */
	private boolean testWhileIdle = true;

	/**
	 * 空闲连接检测的周期，如果为负值，表示不运行检测线程
	 */
	private Duration timeBetweenEvictionRuns = Duration.ofMillis(30000);

	/**
	 * 当对象池没有空闲对象时，新的获取对象的请求是否阻塞（true 阻塞，maxWaitMillis 才生效；false 连接池没有资源立马抛异常）
	 */
	private boolean blockWhenExhausted = GenericObjectPoolConfig.DEFAULT_BLOCK_WHEN_EXHAUSTED;

	/**
	 * 是否注册 JMX
	 */
	private boolean jmxEnabled = GenericObjectPoolConfig.DEFAULT_JMX_ENABLE;

	/**
	 * JMX 前缀
	 */
	private String jmxNamePrefix = GenericObjectPoolConfig.DEFAULT_JMX_NAME_PREFIX;

	/**
	 * 使用 base + jmxNamePrefix + i 来生成 ObjectName
	 */
	private String jmxNameBase = GenericObjectPoolConfig.DEFAULT_JMX_NAME_BASE;

	/**
	 * 最大连接数
	 */
	private int maxTotal = GenericObjectPoolConfig.DEFAULT_MAX_TOTAL;

	/**
	 * 最小空闲连接数
	 */
	private int minIdle = GenericObjectPoolConfig.DEFAULT_MIN_IDLE;

	/**
	 * 最大空闲连接数
	 */
	private int maxIdle = GenericObjectPoolConfig.DEFAULT_MAX_IDLE;

	/**
	 * 返回池模式
	 *
	 * @return 池模式，为 true 时，后进先出；为 false 时，先进先出
	 */
	public boolean getLifo() {
		return lifo;
	}

	/**
	 * 设置池模式
	 *
	 * @param lifo
	 * 		池模式，为 true 时，后进先出；为 false 时，先进先出
	 */
	public void setLifo(boolean lifo) {
		this.lifo = lifo;
	}

	/**
	 * 返回当从池中获取资源或者将资源还回池中时，是否使用 {@link java.util.concurrent.locks.ReentrantLock} 的公平锁机制
	 *
	 * @return 当从池中获取资源或者将资源还回池中时，是否使用 {@link java.util.concurrent.locks.ReentrantLock} 的公平锁机制
	 */
	public boolean getFairness() {
		return fairness;
	}

	/**
	 * 设置当从池中获取资源或者将资源还回池中时，是否使用 {@link java.util.concurrent.locks.ReentrantLock} 的公平锁机制
	 *
	 * @param fairness
	 * 		当从池中获取资源或者将资源还回池中时，是否使用 {@link java.util.concurrent.locks.ReentrantLock} 的公平锁机制
	 */
	public void setFairness(boolean fairness) {
		this.fairness = fairness;
	}

	/**
	 * 返回当连接池资源用尽后，调用者获取连接时的最大等待时间
	 *
	 * @return 当连接池资源用尽后，调用者获取连接时的最大等待时间
	 */
	public Duration getMaxWait() {
		return maxWait;
	}

	/**
	 * 设置当连接池资源用尽后，调用者获取连接时的最大等待时间
	 *
	 * @param maxWait
	 * 		当连接池资源用尽后，调用者获取连接时的最大等待时间
	 */
	public void setMaxWait(Duration maxWait) {
		this.maxWait = maxWait;
	}

	/**
	 * 返回连接的最小空闲时间，达到此值后且已达最大空闲连接数该空闲连接可能会被移除
	 *
	 * @return 连接的最小空闲时间，达到此值后且已达最大空闲连接数该空闲连接可能会被移除
	 */
	public Duration getMinEvictableIdleTime() {
		return minEvictableIdleTime;
	}

	/**
	 * 设置连接的最小空闲时间，达到此值后且已达最大空闲连接数该空闲连接可能会被移除
	 *
	 * @param minEvictableIdleTime
	 * 		连接的最小空闲时间，达到此值后且已达最大空闲连接数该空闲连接可能会被移除
	 */
	public void setMinEvictableIdleTime(Duration minEvictableIdleTime) {
		this.minEvictableIdleTime = minEvictableIdleTime;
	}

	/**
	 * 返回连接空闲的最小时间，达到此值后空闲链接将会被移除，且保留 minIdle 个空闲连接数
	 *
	 * @return 连接空闲的最小时间，达到此值后空闲链接将会被移除，且保留 minIdle 个空闲连接数
	 */
	public Duration getSoftMinEvictableIdleTime() {
		return softMinEvictableIdleTime;
	}

	/**
	 * 设置连接空闲的最小时间，达到此值后空闲链接将会被移除，且保留 minIdle 个空闲连接数
	 *
	 * @param softMinEvictableIdleTime
	 * 		连接空闲的最小时间，达到此值后空闲链接将会被移除，且保留 minIdle 个空闲连接数
	 */
	public void setSoftMinEvictableIdleTime(Duration softMinEvictableIdleTime) {
		this.softMinEvictableIdleTime = softMinEvictableIdleTime;
	}

	/**
	 * 返回驱逐策略的类名
	 *
	 * @return 驱逐策略的类名
	 */
	public String getEvictionPolicyClassName() {
		return evictionPolicyClassName;
	}

	/**
	 * 设置驱逐策略的类名
	 *
	 * @param evictionPolicyClassName
	 * 		驱逐策略的类名
	 */
	public void setEvictionPolicyClassName(String evictionPolicyClassName) {
		this.evictionPolicyClassName = evictionPolicyClassName;
	}

	/**
	 * 返回关闭驱逐线程的超时时间
	 *
	 * @return 关闭驱逐线程的超时时间
	 */
	public Duration getEvictorShutdownTimeout() {
		return evictorShutdownTimeout;
	}

	/**
	 * 设置关闭驱逐线程的超时时间
	 *
	 * @param evictorShutdownTimeout
	 * 		关闭驱逐线程的超时时间
	 */
	public void setEvictorShutdownTimeout(Duration evictorShutdownTimeout) {
		this.evictorShutdownTimeout = evictorShutdownTimeout;
	}

	/**
	 * 返回检测空闲对象线程每次运行时检测的空闲对象的数量
	 *
	 * @return 检测空闲对象线程每次运行时检测的空闲对象的数量；
	 * 如果 numTestsPerEvictionRun &gt;= 0, 则取 numTestsPerEvictionRun 和池内的连接数的较小值作为每次检测的连接数；
	 * 如果 numTestsPerEvictionRun &lt; 0，则每次检查的连接数是检查时池内连接的总数除以这个值的绝对值再向上取整的结果
	 */
	public int getNumTestsPerEvictionRun() {
		return numTestsPerEvictionRun;
	}

	/**
	 * 设置检测空闲对象线程每次运行时检测的空闲对象的数量
	 *
	 * @param numTestsPerEvictionRun
	 * 		检测空闲对象线程每次运行时检测的空闲对象的数量
	 */
	public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}

	/**
	 * 返回在创建对象时检测对象是否有效
	 *
	 * @return 在创建对象时检测对象是否有效
	 */
	public boolean isTestOnCreate() {
		return getTestOnCreate();
	}

	/**
	 * 返回在创建对象时检测对象是否有效
	 *
	 * @return 在创建对象时检测对象是否有效
	 */
	public boolean getTestOnCreate() {
		return testOnCreate;
	}

	/**
	 * 设置在创建对象时检测对象是否有效
	 *
	 * @param testOnCreate
	 * 		在创建对象时检测对象是否有效，配置 true 会降低性能
	 */
	public void setTestOnCreate(boolean testOnCreate) {
		this.testOnCreate = testOnCreate;
	}

	/**
	 * 返回在从对象池获取对象时是否检测对象有效
	 *
	 * @return 在从对象池获取对象时是否检测对象有效
	 */
	public boolean isTestOnBorrow() {
		return getTestOnBorrow();
	}

	/**
	 * 返回在从对象池获取对象时是否检测对象有效
	 *
	 * @return 在从对象池获取对象时是否检测对象有效
	 */
	public boolean getTestOnBorrow() {
		return testOnBorrow;
	}

	/**
	 * 设置在从对象池获取对象时是否检测对象有效
	 *
	 * @param testOnBorrow
	 * 		在从对象池获取对象时是否检测对象有效，配置 true 会降低性能
	 */
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	/**
	 * 返回在向对象池中归还对象时是否检测对象有效
	 *
	 * @return 在向对象池中归还对象时是否检测对象有效
	 */
	public boolean isTestOnReturn() {
		return getTestOnReturn();
	}

	/**
	 * 返回在向对象池中归还对象时是否检测对象有效
	 *
	 * @return 在向对象池中归还对象时是否检测对象有效
	 */
	public boolean getTestOnReturn() {
		return testOnReturn;
	}

	/**
	 * 设置在向对象池中归还对象时是否检测对象有效
	 *
	 * @param testOnReturn
	 * 		在向对象池中归还对象时是否检测对象有效，配置 true 会降低性能
	 */
	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	/**
	 * 返回在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性
	 *
	 * @return 在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性
	 */
	public boolean isTestWhileIdle() {
		return getTestWhileIdle();
	}

	/**
	 * 返回在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性
	 *
	 * @return 在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性
	 */
	public boolean getTestWhileIdle() {
		return testWhileIdle;
	}

	/**
	 * 设置在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性；建议配置为 true，不影响性能，并且保证安全性
	 *
	 * @param testWhileIdle
	 * 		在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性
	 */
	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	/**
	 * 返回空闲连接检测的周期，如果为负值，表示不运行检测线程
	 *
	 * @return 空闲连接检测的周期，如果为负值，表示不运行检测线程
	 */
	public Duration getTimeBetweenEvictionRuns() {
		return timeBetweenEvictionRuns;
	}

	/**
	 * 设置空闲连接检测的周期，如果为负值，表示不运行检测线程
	 *
	 * @param timeBetweenEvictionRuns
	 * 		空闲连接检测的周期
	 */
	public void setTimeBetweenEvictionRuns(Duration timeBetweenEvictionRuns) {
		this.timeBetweenEvictionRuns = timeBetweenEvictionRuns;
	}

	/**
	 * 返回当对象池没有空闲对象时，新的获取对象的请求是否阻塞
	 *
	 * @return 当对象池没有空闲对象时，新的获取对象的请求是否阻塞（true 阻塞，maxWaitMillis 才生效； false 连接池没有资源立马抛异常）
	 */
	public boolean isBlockWhenExhausted() {
		return getBlockWhenExhausted();
	}

	/**
	 * 返回当对象池没有空闲对象时，新的获取对象的请求是否阻塞
	 *
	 * @return 当对象池没有空闲对象时，新的获取对象的请求是否阻塞（true 阻塞，maxWaitMillis 才生效； false 连接池没有资源立马抛异常）
	 */
	public boolean getBlockWhenExhausted() {
		return blockWhenExhausted;
	}

	/**
	 * 设置当对象池没有空闲对象时，新的获取对象的请求是否阻塞（true 阻塞，maxWaitMillis 才生效； false 连接池没有资源立马抛异常）
	 *
	 * @param blockWhenExhausted
	 * 		当对象池没有空闲对象时，新的获取对象的请求是否阻塞
	 */
	public void setBlockWhenExhausted(boolean blockWhenExhausted) {
		this.blockWhenExhausted = blockWhenExhausted;
	}

	/**
	 * 返回是否注册 JMX
	 *
	 * @return 是否注册 JMX
	 */
	public boolean isJmxEnabled() {
		return getJmxEnabled();
	}

	/**
	 * 返回是否注册 JMX
	 *
	 * @return 是否注册 JMX
	 */
	public boolean getJmxEnabled() {
		return jmxEnabled;
	}

	/**
	 * 设置是否注册 JMX
	 *
	 * @param jmxEnabled
	 * 		是否注册 JMX
	 */
	public void setJmxEnabled(boolean jmxEnabled) {
		this.jmxEnabled = jmxEnabled;
	}

	/**
	 * 返回 JMX 前缀
	 *
	 * @return JMX 前缀
	 */
	public String getJmxNamePrefix() {
		return jmxNamePrefix;
	}

	/**
	 * 设置 JMX 前缀
	 *
	 * @param jmxNamePrefix
	 * 		JMX 前缀
	 */
	public void setJmxNamePrefix(String jmxNamePrefix) {
		this.jmxNamePrefix = jmxNamePrefix;
	}

	public String getJmxNameBase() {
		return jmxNameBase;
	}

	public void setJmxNameBase(String jmxNameBase) {
		this.jmxNameBase = jmxNameBase;
	}

	/**
	 * 返回最大连接数
	 *
	 * @return 最大连接数
	 */
	public int getMaxTotal() {
		return maxTotal;
	}

	/**
	 * 设置最大连接数
	 *
	 * @param maxTotal
	 * 		最大连接数
	 */
	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	/**
	 * 返回最小空闲连接数
	 *
	 * @return 最小空闲连接数
	 */
	public int getMinIdle() {
		return minIdle;
	}

	/**
	 * 设置最小空闲连接数
	 *
	 * @param minIdle
	 * 		最小空闲连接数
	 */
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	/**
	 * 返回最大空闲连接数
	 *
	 * @return 最大空闲连接数
	 */
	public int getMaxIdle() {
		return maxIdle;
	}

	/**
	 * 设置最大空闲连接数
	 *
	 * @param maxIdle
	 * 		最大空闲连接数
	 */
	public void setMaxIdle(int maxIdle) {
		this.maxIdle = maxIdle;
	}

	/**
	 * 转换为 jedis {@link GenericObjectPoolConfig}
	 *
	 * @param poolConfig
	 *        {@link GenericObjectPoolConfig} 实例
	 * @param <T>
	 *        {@link GenericObjectPoolConfig} 类型
	 *
	 * @return {@link GenericObjectPoolConfig} 实例
	 *
	 * @since 2.3.2
	 */
	public <T> GenericObjectPoolConfig<T> toGenericObjectPoolConfig(final GenericObjectPoolConfig<T> poolConfig) {
		poolConfig.setLifo(getLifo());
		poolConfig.setFairness(getFairness());
		poolConfig.setMaxWait(getMaxWait());
		poolConfig.setMinEvictableIdleTime(getMinEvictableIdleTime());
		poolConfig.setSoftMinEvictableIdleTime(getSoftMinEvictableIdleTime());
		poolConfig.setEvictionPolicyClassName(getEvictionPolicyClassName());
		poolConfig.setEvictorShutdownTimeout(getEvictorShutdownTimeout());
		poolConfig.setNumTestsPerEvictionRun(getNumTestsPerEvictionRun());
		poolConfig.setTestOnCreate(getTestOnCreate());
		poolConfig.setTestOnBorrow(getTestOnBorrow());
		poolConfig.setTestOnReturn(getTestOnReturn());
		poolConfig.setTestWhileIdle(getTestWhileIdle());
		poolConfig.setTimeBetweenEvictionRuns(getTimeBetweenEvictionRuns());
		poolConfig.setBlockWhenExhausted(getBlockWhenExhausted());
		poolConfig.setJmxEnabled(getJmxEnabled());
		poolConfig.setJmxNamePrefix(getJmxNamePrefix());
		poolConfig.setJmxNameBase(getJmxNameBase());
		poolConfig.setMaxTotal(getMaxTotal());
		poolConfig.setMinIdle(getMinIdle());
		poolConfig.setMaxIdle(getMaxIdle());

		return poolConfig;
	}

}
