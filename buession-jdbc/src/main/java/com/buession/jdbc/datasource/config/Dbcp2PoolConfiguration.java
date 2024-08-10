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

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.PreparedStatement;
import java.time.Duration;

/**
 * Dhcp2 数据源连接池配置 {@link BasicDataSource}
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class Dbcp2PoolConfiguration extends AbstractPoolConfiguration {

	/**
	 * 是否缓存 preparedStatement，也就是PSCache；
	 * PSCache 对支持游标的数据库性能提升巨大，比如说 oracle，在mysql下建议关闭
	 */
	private Boolean poolPreparedStatements;

	/**
	 * 可以在语句池中同时分配的最大语句数
	 */
	private Integer maxOpenPreparedStatements;

	/**
	 * 是否清除与该连接关联的已缓存的 {@link PreparedStatement} 对象；启用后，每次连接返回到池中时，所有缓存的 PreparedStatement 对象都会被清除
	 */
	private Boolean clearStatementPoolOnReturn;

	/**
	 * 从连接池借用连接时是否对废弃连接的检查
	 *
	 * @since 3.0.0
	 */
	private Boolean removeAbandonedOnBorrow;

	/**
	 * 后台维护线程会在运行时定期检查连接池中的连接，并移除那些被认为是废弃的连接
	 *
	 * @since 3.0.0
	 */
	private Boolean removeAbandonedOnMaintenance;

	/**
	 * 指定连接在被认为是废弃连接（abandoned connection）之前的超时时间
	 *
	 * @since 3.0.0
	 */
	private Duration removeAbandonedTimeout;

	/**
	 * 是否在每次连接被借出时记录当前的堆栈跟踪信息
	 *
	 * @since 3.0.0
	 */
	private Boolean abandonedUsageTracking;

	/**
	 * 当连接被认为是废弃并且被移除时是否记录日志
	 *
	 * @since 3.0.0
	 */
	private Boolean logAbandoned;

	/**
	 * 空闲的连接被释放最低要待时间，但有额外条件
	 * 额外的条件是池中至少保留有 minIdle 所指定的个数的连接；
	 * 当 miniEvictableIdleTime 被设置为一个正数，空闲连接驱逐者首先检测 miniEvictableIdleTime ，
	 * 当空闲连接被驱逐者访问时，首先与 miniEvictableIdleTime 所指定的值进行比较（而不考虑当前池中的空闲连接数），
	 * 然后比较 softMinEvictableIdleTime 所指定的连接数，包括 minIdle 条件
	 */
	private Duration softMinEvictableIdle;

	/**
	 * 空闲对象驱逐策略名称
	 */
	private String evictionPolicyClassName;

	/**
	 * 后进先出，设置为true表明连接池（如果池中有可用的空闲连接时）将返回最后一次使用的租借对象（最后进入），
	 * 设置为false则表明池将表现为FIFO队列（先进先出）—将会按照它们被归还的顺序从空闲连接实例池中获取连接
	 */
	private Boolean lifo;

	/**
	 * 是否将连接池的管理信息注册为 JMX (Java Management Extensions) MBean
	 *
	 * @since 3.0.0
	 */
	private Boolean registerConnectionMBean;

	/**
	 * 返回是否缓存 preparedStatement，也就是PSCache
	 *
	 * @return 是否缓存 preparedStatement，也就是PSCache
	 */
	public Boolean isPoolPreparedStatements() {
		return getPoolPreparedStatements();
	}

	/**
	 * 返回是否缓存 preparedStatement，也就是PSCache
	 *
	 * @return 是否缓存 preparedStatement，也就是PSCache
	 */
	public Boolean getPoolPreparedStatements() {
		return poolPreparedStatements;
	}

	/**
	 * 设置是否缓存 preparedStatement，也就是PSCache；
	 * PSCache 对支持游标的数据库性能提升巨大，比如说 oracle，在mysql下建议关闭
	 *
	 * @param poolPreparedStatements
	 * 		是否缓存 preparedStatement
	 */
	public void setPoolPreparedStatements(Boolean poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
	}

	/**
	 * 返回可以在语句池中同时分配的最大语句数
	 *
	 * @return 可以在语句池中同时分配的最大语句数
	 */
	public Integer getMaxOpenPreparedStatements() {
		return maxOpenPreparedStatements;
	}

	/**
	 * 设置可以在语句池中同时分配的最大语句数
	 *
	 * @param maxOpenPreparedStatements
	 * 		可以在语句池中同时分配的最大语句数
	 */
	public void setMaxOpenPreparedStatements(Integer maxOpenPreparedStatements) {
		this.maxOpenPreparedStatements = maxOpenPreparedStatements;
	}

	/**
	 * 返回是否清除与该连接关联的已缓存的 {@link PreparedStatement} 对象
	 *
	 * @return 是否清除与该连接关联的已缓存的 {@link PreparedStatement} 对象
	 */
	public Boolean isClearStatementPoolOnReturn() {
		return getClearStatementPoolOnReturn();
	}

	/**
	 * 返回是否清除与该连接关联的已缓存的 {@link PreparedStatement} 对象
	 *
	 * @return 是否清除与该连接关联的已缓存的 {@link PreparedStatement} 对象
	 */
	public Boolean getClearStatementPoolOnReturn() {
		return clearStatementPoolOnReturn;
	}

	/**
	 * 设置是否清除与该连接关联的已缓存的 {@link PreparedStatement} 对象；启用后，每次连接返回到池中时，所有缓存的 PreparedStatement 对象都会被清除
	 *
	 * @param clearStatementPoolOnReturn
	 * 		是否清除与该连接关联的已缓存的 {@link PreparedStatement} 对象
	 */
	public void setClearStatementPoolOnReturn(Boolean clearStatementPoolOnReturn) {
		this.clearStatementPoolOnReturn = clearStatementPoolOnReturn;
	}

	/**
	 * 返回从连接池借用连接时是否对废弃连接的检查
	 *
	 * @return 从连接池借用连接时是否对废弃连接的检查
	 *
	 * @since 3.0.0
	 */
	public Boolean isRemoveAbandonedOnBorrow() {
		return getRemoveAbandonedOnBorrow();
	}

	/**
	 * 返回从连接池借用连接时是否对废弃连接的检查
	 *
	 * @return 从连接池借用连接时是否对废弃连接的检查
	 *
	 * @since 3.0.0
	 */
	public Boolean getRemoveAbandonedOnBorrow() {
		return removeAbandonedOnBorrow;
	}

	/**
	 * 设置从连接池借用连接时是否对废弃连接的检查
	 *
	 * @param removeAbandonedOnBorrow
	 * 		从连接池借用连接时是否对废弃连接的检查
	 *
	 * @since 3.0.0
	 */
	public void setRemoveAbandonedOnBorrow(Boolean removeAbandonedOnBorrow) {
		this.removeAbandonedOnBorrow = removeAbandonedOnBorrow;
	}

	/**
	 * 返回后台维护线程会在运行时定期检查连接池中的连接，并移除那些被认为是废弃的连接
	 *
	 * @return 后台维护线程会在运行时定期检查连接池中的连接，并移除那些被认为是废弃的连接
	 *
	 * @since 3.0.0
	 */
	public Boolean isRemoveAbandonedOnMaintenance() {
		return getRemoveAbandonedOnMaintenance();
	}

	/**
	 * 返回后台维护线程会在运行时定期检查连接池中的连接，并移除那些被认为是废弃的连接
	 *
	 * @return 后台维护线程会在运行时定期检查连接池中的连接，并移除那些被认为是废弃的连接
	 *
	 * @since 3.0.0
	 */
	public Boolean getRemoveAbandonedOnMaintenance() {
		return removeAbandonedOnMaintenance;
	}

	/**
	 * 设置后台维护线程会在运行时定期检查连接池中的连接，并移除那些被认为是废弃的连接
	 *
	 * @param removeAbandonedOnMaintenance
	 * 		后台维护线程会在运行时定期检查连接池中的连接，并移除那些被认为是废弃的连接
	 *
	 * @since 3.0.0
	 */
	public void setRemoveAbandonedOnMaintenance(Boolean removeAbandonedOnMaintenance) {
		this.removeAbandonedOnMaintenance = removeAbandonedOnMaintenance;
	}

	/**
	 * 返回指定连接在被认为是废弃连接（abandoned connection）之前的超时时间
	 *
	 * @return 指定连接在被认为是废弃连接（abandoned connection）之前的超时时间
	 *
	 * @since 3.0.0
	 */
	public Duration getRemoveAbandonedTimeout() {
		return removeAbandonedTimeout;
	}

	/**
	 * 设置指定连接在被认为是废弃连接（abandoned connection）之前的超时时间
	 *
	 * @param removeAbandonedTimeout
	 * 		指定连接在被认为是废弃连接（abandoned connection）之前的超时时间
	 *
	 * @since 3.0.0
	 */
	public void setRemoveAbandonedTimeout(Duration removeAbandonedTimeout) {
		this.removeAbandonedTimeout = removeAbandonedTimeout;
	}

	/**
	 * 返回是否在每次连接被借出时记录当前的堆栈跟踪信息
	 *
	 * @return 是否在每次连接被借出时记录当前的堆栈跟踪信息
	 *
	 * @since 3.0.0
	 */
	public Boolean isAbandonedUsageTracking() {
		return getAbandonedUsageTracking();
	}

	/**
	 * 返回是否在每次连接被借出时记录当前的堆栈跟踪信息
	 *
	 * @return 是否在每次连接被借出时记录当前的堆栈跟踪信息
	 *
	 * @since 3.0.0
	 */
	public Boolean getAbandonedUsageTracking() {
		return abandonedUsageTracking;
	}

	/**
	 * 设置是否在每次连接被借出时记录当前的堆栈跟踪信息
	 *
	 * @param abandonedUsageTracking
	 * 		是否在每次连接被借出时记录当前的堆栈跟踪信息
	 *
	 * @since 3.0.0
	 */
	public void setAbandonedUsageTracking(Boolean abandonedUsageTracking) {
		this.abandonedUsageTracking = abandonedUsageTracking;
	}

	/**
	 * 返回当连接被认为是废弃并且被移除时是否记录日志
	 *
	 * @return 当连接被认为是废弃并且被移除时是否记录日志
	 *
	 * @since 3.0.0
	 */
	public Boolean isLogAbandoned() {
		return getLogAbandoned();
	}

	/**
	 * 返回当连接被认为是废弃并且被移除时是否记录日志
	 *
	 * @return 当连接被认为是废弃并且被移除时是否记录日志
	 *
	 * @since 3.0.0
	 */
	public Boolean getLogAbandoned() {
		return logAbandoned;
	}

	/**
	 * 设置当连接被认为是废弃并且被移除时是否记录日志
	 *
	 * @param logAbandoned
	 * 		当连接被认为是废弃并且被移除时是否记录日志
	 *
	 * @since 3.0.0
	 */
	public void setLogAbandoned(Boolean logAbandoned) {
		this.logAbandoned = logAbandoned;
	}

	/**
	 * 返回空闲的连接被释放最低要待时间，但有额外条件
	 *
	 * @return 空闲的连接被释放最低要待时间
	 */
	public Duration getSoftMinEvictableIdle() {
		return softMinEvictableIdle;
	}

	/**
	 * 设置空闲的连接被释放最低要待时间
	 *
	 * @param softMinEvictableIdle
	 * 		空闲的连接被释放最低要待时间
	 */
	public void setSoftMinEvictableIdle(Duration softMinEvictableIdle) {
		this.softMinEvictableIdle = softMinEvictableIdle;
	}

	/**
	 * 返回空闲对象驱逐策略名称
	 *
	 * @return 空闲对象驱逐策略名称
	 */
	public String getEvictionPolicyClassName() {
		return evictionPolicyClassName;
	}

	/**
	 * 设置空闲对象驱逐策略名称
	 *
	 * @param evictionPolicyClassName
	 * 		空闲对象驱逐策略名称
	 */
	public void setEvictionPolicyClassName(String evictionPolicyClassName) {
		this.evictionPolicyClassName = evictionPolicyClassName;
	}

	/**
	 * 是否后进先出
	 *
	 * @return 后进先出
	 */
	public Boolean isLifo() {
		return getLifo();
	}

	/**
	 * 是否后进先出
	 *
	 * @return 后进先出
	 */
	public Boolean getLifo() {
		return lifo;
	}

	/**
	 * 设置后进先出
	 *
	 * @param lifo
	 * 		后进先出
	 */
	public void setLifo(Boolean lifo) {
		this.lifo = lifo;
	}

	/**
	 * 返回是否将连接池的管理信息注册为 JMX (Java Management Extensions) MBean
	 *
	 * @return 是否将连接池的管理信息注册为 JMX (Java Management Extensions) MBean
	 */
	public Boolean isRegisterConnectionMBean() {
		return getRegisterConnectionMBean();
	}

	/**
	 * 返回是否将连接池的管理信息注册为 JMX (Java Management Extensions) MBean
	 *
	 * @return 是否将连接池的管理信息注册为 JMX (Java Management Extensions) MBean
	 */
	public Boolean getRegisterConnectionMBean() {
		return registerConnectionMBean;
	}

	/**
	 * 设置是否将连接池的管理信息注册为 JMX (Java Management Extensions) MBean
	 *
	 * @param registerConnectionMBean
	 * 		是否将连接池的管理信息注册为 JMX (Java Management Extensions) MBean
	 */
	public void setRegisterConnectionMBean(Boolean registerConnectionMBean) {
		this.registerConnectionMBean = registerConnectionMBean;
		super.setJmxEnabled(registerConnectionMBean);
	}

	@Override
	public void setJmxEnabled(Boolean jmxEnabled) {
		setRegisterConnectionMBean(jmxEnabled);
	}

}
