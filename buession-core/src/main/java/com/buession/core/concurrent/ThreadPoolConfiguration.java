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
package com.buession.core.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public class ThreadPoolConfiguration {

	/**
	 * 线程名称，当设置了 {@link #threadFactory} 时无效
	 */
	@Deprecated
	private String name;

	/**
	 * 线程名称前缀，当设置了 {@link #threadFactory} 时无效
	 *
	 * @since 2.3.3
	 */
	private String namePrefix;

	/**
	 * 线程池核心线程大小
	 */
	private int corePoolSize;

	/**
	 * 线程池最大线程数量
	 */
	private int maximumPoolSize;

	/**
	 * 空闲线程存活时间
	 */
	private long keepAliveTime;

	/**
	 * {@link #keepAliveTime} 的计量单位
	 */
	@Deprecated
	private TimeUnit timeUnit;

	/**
	 * {@link #keepAliveTime} 的计量单位
	 *
	 * @since 2.3.3
	 */
	private TimeUnit keepAliveTimeUnit;

	/**
	 * 空闲线程超过 keepAliveTime 后是否直接停掉
	 */
	private Boolean allowCoreThreadTimeOut;

	/**
	 * 工作队列 {@link BlockingQueue}
	 */
	private BlockingQueue<Runnable> workQueue;

	/**
	 * 创建线程的工厂 {@link ThreadFactory}
	 */
	private ThreadFactory threadFactory;

	/**
	 * 拒绝策略 {@link RejectedExecutionHandler}
	 */
	private RejectedExecutionHandler rejectedHandler;

	/**
	 * 是否后台运行，当设置了 {@link #threadFactory} 时无效
	 */
	private Boolean daemon;

	/**
	 * 优先级，当设置了 {@link #threadFactory} 时无效
	 */
	private Integer priority;

	/**
	 * 返回线程名称，当设置了 {@link #threadFactory} 时无效
	 *
	 * @return 线程名称
	 */
	@Deprecated
	public String getName() {
		return name;
	}

	/**
	 * 返回线程名称，当设置了 {@link #threadFactory} 时无效
	 *
	 * @param name
	 * 		线程名称
	 */
	@Deprecated
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回线程名称前缀，当设置了 {@link #threadFactory} 时无效
	 *
	 * @return 线程名称前缀
	 *
	 * @since 2.3.3
	 */
	public String getNamePrefix() {
		return namePrefix;
	}

	/**
	 * 返回线程名称前缀，当设置了 {@link #threadFactory} 时无效
	 *
	 * @param namePrefix
	 * 		线程名称前缀
	 *
	 * @since 2.3.3
	 */
	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	/**
	 * 返回线程池核心线程大小；
	 * <p>线程池中会维护一个最小的线程数量，即使这些线程处理空闲状态，他们也不会被销毁。</p>
	 *
	 * @return 线程池核心线程大小
	 */
	public int getCorePoolSize() {
		return corePoolSize;
	}

	/**
	 * 设置线程池核心线程大小
	 * <p>线程池中会维护一个最小的线程数量，即使这些线程处理空闲状态，他们也不会被销毁。</p>
	 *
	 * @param corePoolSize
	 * 		线程池核心线程大小
	 */
	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	/**
	 * 线程池最大线程数量
	 * <p>一个任务被提交到线程池以后，首先会找有没有空闲存活线程，如果有则直接将任务交给这个空闲线程来执行，
	 * 如果没有则会缓存到工作队列中，如果工作队列满了，才会创建一个新线程，
	 * 然后从工作队列的头部取出一个任务交由新线程来处理，而将刚提交的任务放入工作队列尾部。
	 * 线程池不会无限制的去创建新线程，它会有一个最大线程数量的限制，这个数量即由 maximunPoolSize 指定。</p>
	 *
	 * @return 线程池最大线程数量
	 */
	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	/**
	 * 线程池最大线程数量
	 * <p>一个任务被提交到线程池以后，首先会找有没有空闲存活线程，如果有则直接将任务交给这个空闲线程来执行，
	 * 如果没有则会缓存到工作队列中，如果工作队列满了，才会创建一个新线程，
	 * 然后从工作队列的头部取出一个任务交由新线程来处理，而将刚提交的任务放入工作队列尾部。
	 * 线程池不会无限制的去创建新线程，它会有一个最大线程数量的限制，这个数量即由 maximunPoolSize 指定。</p>
	 *
	 * @param maximumPoolSize
	 * 		线程池最大线程数量
	 */
	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}

	/**
	 * 返回空闲线程存活时间
	 * <p>一个线程如果处于空闲状态，并且当前的线程数量大于 corePoolSize，那么在指定时间后，这个空闲线程会被销毁，
	 * 这里的指定时间由 keepAliveTime 来设定。</p>
	 *
	 * @return 空闲线程存活时间
	 */
	public long getKeepAliveTime() {
		return keepAliveTime;
	}

	/**
	 * 设置空闲线程存活时间
	 * <p>一个线程如果处于空闲状态，并且当前的线程数量大于 corePoolSize，那么在指定时间后，这个空闲线程会被销毁，
	 * 这里的指定时间由 keepAliveTime 来设定。</p>
	 *
	 * @param keepAliveTime
	 * 		空闲线程存活时间
	 */
	public void setKeepAliveTime(long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

	/**
	 * 返回 {@link #keepAliveTime} 的计量单位
	 *
	 * @return {@link #keepAliveTime} 的计量单位
	 */
	@Deprecated
	public TimeUnit getTimeUnit() {
		return timeUnit;
	}

	/**
	 * 设置 {@link #keepAliveTime} 的计量单位
	 *
	 * @param timeUnit
	 *        {@link #keepAliveTime} 的计量单位
	 */
	@Deprecated
	public void setTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
		this.keepAliveTimeUnit = timeUnit;
	}

	/**
	 * 返回 {@link #keepAliveTime} 的计量单位
	 *
	 * @return {@link #keepAliveTime} 的计量单位
	 *
	 * @since 2.3.3
	 */
	public TimeUnit getKeepAliveTimeUnit() {
		return keepAliveTimeUnit;
	}

	/**
	 * 设置 {@link #keepAliveTime} 的计量单位
	 *
	 * @param keepAliveTimeUnit
	 *        {@link #keepAliveTime} 的计量单位
	 *
	 * @since 2.3.3
	 */
	public void setKeepAliveTimeTimeUnit(TimeUnit keepAliveTimeUnit) {
		this.keepAliveTimeUnit = keepAliveTimeUnit;
		this.timeUnit = keepAliveTimeUnit;
	}

	/**
	 * 返回空闲线程超过 keepAliveTime 后是否直接停掉
	 *
	 * @return 空闲线程超过 keepAliveTime 后是否直接停掉
	 */
	public Boolean getAllowCoreThreadTimeOut() {
		return allowCoreThreadTimeOut;
	}

	/**
	 * 设置空闲线程超过 keepAliveTime 后是否直接停掉
	 *
	 * @param allowCoreThreadTimeOut
	 * 		空闲线程超过 keepAliveTime 后是否直接停掉
	 */
	public void setAllowCoreThreadTimeOut(Boolean allowCoreThreadTimeOut) {
		this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
	}

	/**
	 * 返回工作队列
	 * <p>新任务被提交后，会先进入到此工作队列中，任务调度时再从队列中取出任务。</p>
	 *
	 * @return 工作队列 {@link BlockingQueue}
	 */
	public BlockingQueue<Runnable> getWorkQueue() {
		return workQueue;
	}

	/**
	 * 设置工作队列
	 * <p>新任务被提交后，会先进入到此工作队列中，任务调度时再从队列中取出任务。</p>
	 *
	 * @param workQueue
	 * 		工作队列 {@link BlockingQueue}
	 */
	public void setWorkQueue(BlockingQueue<Runnable> workQueue) {
		this.workQueue = workQueue;
	}

	/**
	 * 返回创建线程的工厂
	 * <p>可以通过线程工厂给每个创建出来的线程做些更有意义的事情，比如 设定线程名、设置 daemon 和优先级等等。</p>
	 *
	 * @return 创建线程的工厂 {@link ThreadFactory}
	 */
	public ThreadFactory getThreadFactory() {
		return threadFactory;
	}

	/**
	 * 设置创建线程的工厂
	 * <p>可以通过线程工厂给每个创建出来的线程做些更有意义的事情，比如 设定线程名、设置 daemon 和优先级等等。</p>
	 *
	 * @param threadFactory
	 * 		创建线程的工厂 {@link ThreadFactory}
	 */
	public void setThreadFactory(ThreadFactory threadFactory) {
		this.threadFactory = threadFactory;
	}

	/**
	 * 返回拒绝策略
	 * <p>当工作队列中的任务已到达最大限制，并且线程池中的线程数量也达到最大限制；
	 * 这时如果有新任务提交进来，通过 {@link RejectedExecutionHandler} 策略进行处理。</p>
	 *
	 * @return 拒绝策略 {@link RejectedExecutionHandler}
	 */
	public RejectedExecutionHandler getRejectedHandler() {
		return rejectedHandler;
	}

	/**
	 * 设置拒绝策略
	 * <p>当工作队列中的任务已到达最大限制，并且线程池中的线程数量也达到最大限制；
	 * 这时如果有新任务提交进来，通过 {@link RejectedExecutionHandler} 策略进行处理。</p>
	 *
	 * @param rejectedHandler
	 * 		拒绝策略 {@link RejectedExecutionHandler}
	 */
	public void setRejectedHandler(RejectedExecutionHandler rejectedHandler) {
		this.rejectedHandler = rejectedHandler;
	}

	/**
	 * 返回是否后台运行，当设置了 {@link #threadFactory} 时无效
	 *
	 * @return 是否后台运行
	 */
	public Boolean getDaemon() {
		return daemon;
	}

	/**
	 * 设置是否后台运行，当设置了 {@link #threadFactory} 时无效
	 *
	 * @param daemon
	 * 		是否后台运行
	 */
	public void setDaemon(Boolean daemon) {
		this.daemon = daemon;
	}

	/**
	 * 返回优先级，当设置了 {@link #threadFactory} 时无效
	 *
	 * @return 优先级
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * 设置优先级，当设置了 {@link #threadFactory} 时无效
	 *
	 * @param priority
	 * 		优先级
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

}
