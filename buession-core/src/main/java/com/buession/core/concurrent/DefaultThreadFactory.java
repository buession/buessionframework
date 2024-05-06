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
package com.buession.core.concurrent;

import org.springframework.lang.NonNull;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 默认线程工厂
 *
 * @author Yong.Teng
 * @see ThreadFactory
 * @since 2.3.3
 */
public class DefaultThreadFactory implements ThreadFactory {

	/**
	 * 线程池组
	 */
	private final ThreadGroup group;

	/**
	 * 线程池序号
	 */
	private final AtomicInteger threadNumber = new AtomicInteger(0);

	/**
	 * 名称前缀
	 */
	private final String namePrefix;

	/**
	 * 优先级
	 */
	private Integer priority;

	/**
	 * 是否后台运行
	 */
	private Boolean daemon;

	/**
	 * 构造函数
	 */
	public DefaultThreadFactory() {
		this("pool");
	}

	/**
	 * 构造函数
	 *
	 * @param namePrefix
	 * 		名称前缀
	 */
	public DefaultThreadFactory(final String namePrefix) {
		SecurityManager securityManager = System.getSecurityManager();
		group = securityManager == null ? Thread.currentThread().getThreadGroup() : securityManager.getThreadGroup();
		this.namePrefix = namePrefix + "-thread-";
	}

	/**
	 * 构造函数
	 *
	 * @param namePrefix
	 * 		名称前缀
	 * @param priority
	 * 		优先级
	 */
	public DefaultThreadFactory(final String namePrefix, final Integer priority) {
		this(namePrefix);
		this.priority = priority;
	}

	/**
	 * 构造函数
	 *
	 * @param namePrefix
	 * 		名称前缀
	 * @param daemon
	 * 		是否后台运行
	 */
	public DefaultThreadFactory(final String namePrefix, final Boolean daemon) {
		this(namePrefix);
		this.daemon = daemon;
	}

	/**
	 * 构造函数
	 *
	 * @param namePrefix
	 * 		名称前缀
	 * @param priority
	 * 		优先级
	 * @param daemon
	 * 		是否后台运行
	 */
	public DefaultThreadFactory(final String namePrefix, final Integer priority, final Boolean daemon) {
		this(namePrefix, priority);
		this.daemon = daemon;
	}

	/**
	 * 返回优先级
	 *
	 * @return 优先级
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * 设置优先级
	 *
	 * @param priority
	 * 		优先级
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * 返回是否后台运行
	 *
	 * @return 是否后台运行
	 */
	public Boolean getDaemon() {
		return daemon;
	}

	/**
	 * 设置是否后台运行
	 *
	 * @param daemon
	 * 		是否后台运行
	 */
	public void setDaemon(Boolean daemon) {
		this.daemon = daemon;
	}

	@Override
	public Thread newThread(@NonNull Runnable runnable) {
		final Thread thread = new Thread(group, runnable, namePrefix + threadNumber.getAndIncrement(), 0);

		if(thread.getPriority() != Thread.NORM_PRIORITY){
			thread.setPriority(priority);
		}

		if(daemon != null){
			thread.setDaemon(daemon);
		}

		return thread;
	}

}
