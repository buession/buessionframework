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

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.core.validator.Validate;

import java.util.Optional;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 默认线程池执行器
 *
 * @author Yong.Teng
 * @since 2.3.3
 */
public class DefaultThreadPoolExecutor extends ThreadPoolExecutor {

	public final static long DEFAULT_KEEP_ALIVE_TIME = 60;

	/**
	 * 构造函数
	 */
	public DefaultThreadPoolExecutor() {
		this(new ThreadPoolConfiguration());
	}

	/**
	 * 构造函数
	 *
	 * @param configuration
	 * 		线程池配置
	 */
	public DefaultThreadPoolExecutor(final ThreadPoolConfiguration configuration) {
		super(initValue(configuration.getCorePoolSize(), Runtime.getRuntime().availableProcessors() << 1),
				initValue(configuration.getMaximumPoolSize(), Runtime.getRuntime().availableProcessors() << 1),
				initValue(configuration.getKeepAliveTime(), DEFAULT_KEEP_ALIVE_TIME), configuration.getTimeUnit(),
				initValue(configuration.getWorkQueue(), new LinkedBlockingQueue<>()),
				initValue(configuration.getThreadFactory(), createDefaultThreadFactory(configuration)));

		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();

		propertyMapper.from(configuration.getAllowCoreThreadTimeOut()).to(this::allowCoreThreadTimeOut);
		propertyMapper.from(configuration.getRejectedHandler()).to(this::setRejectedExecutionHandler);
	}

	protected static int initValue(final int value, final int defaultValue) {
		return value >= 0 ? value : defaultValue;
	}

	protected static long initValue(final long value, final long defaultValue) {
		return value >= 0 ? value : defaultValue;
	}

	protected static <T> T initValue(final T value, final T defaultValue) {
		return Optional.ofNullable(value).orElse(defaultValue);
	}

	protected static ThreadFactory createDefaultThreadFactory(final ThreadPoolConfiguration configuration) {
		final DefaultThreadFactory threadFactory = Validate.hasText(
				configuration.getNamePrefix()) ? new DefaultThreadFactory(
				configuration.getNamePrefix()) : new DefaultThreadFactory();
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();

		propertyMapper.from(configuration.getPriority()).to(threadFactory::setPriority);
		propertyMapper.from(configuration.getDaemon()).to(threadFactory::setDaemon);

		return threadFactory;
	}

}
