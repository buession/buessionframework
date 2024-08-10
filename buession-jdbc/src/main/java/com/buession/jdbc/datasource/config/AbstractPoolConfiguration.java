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

import java.time.Duration;

/**
 * 连接池配置基类
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public abstract class AbstractPoolConfiguration implements PoolConfiguration {

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
	 * 是否启用 JMX
	 */
	private Boolean jmxEnabled;

	/**
	 * JMX 管理对象的名称
	 */
	private String jmxName;

	public Integer getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(Integer initialSize) {
		this.initialSize = initialSize;
	}

	public Integer getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	public Integer getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

	public Integer getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}

	public Duration getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(Duration maxWait) {
		this.maxWait = maxWait;
	}

	public Boolean getTestOnCreate() {
		return testOnCreate;
	}

	public void setTestOnCreate(Boolean testOnCreate) {
		this.testOnCreate = testOnCreate;
	}

	public Boolean getTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(Boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	public Boolean getTestOnReturn() {
		return testOnReturn;
	}

	public void setTestOnReturn(Boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	public Boolean getTestWhileIdle() {
		return testWhileIdle;
	}

	public void setTestWhileIdle(Boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	public Duration getMinEvictableIdle() {
		return minEvictableIdle;
	}

	public void setMinEvictableIdle(Duration minEvictableIdle) {
		this.minEvictableIdle = minEvictableIdle;
	}

	public Duration getMaxEvictableIdle() {
		return maxEvictableIdle;
	}

	public void setMaxEvictableIdle(Duration maxEvictableIdle) {
		this.maxEvictableIdle = maxEvictableIdle;
	}

	public Integer getNumTestsPerEvictionRun() {
		return numTestsPerEvictionRun;
	}

	public void setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}

	public Duration getTimeBetweenEvictionRuns() {
		return timeBetweenEvictionRuns;
	}

	public void setTimeBetweenEvictionRuns(Duration timeBetweenEvictionRuns) {
		this.timeBetweenEvictionRuns = timeBetweenEvictionRuns;
	}

	public Boolean getJmxEnabled() {
		return jmxEnabled;
	}

	public void setJmxEnabled(Boolean jmxEnabled) {
		this.jmxEnabled = jmxEnabled;
	}

	public String getJmxName() {
		return jmxName;
	}

	public void setJmxName(String jmxName) {
		this.jmxName = jmxName;
	}

}
