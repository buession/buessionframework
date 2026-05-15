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
package com.buession.redis.client.connection.datasource.lettuce;

import com.buession.redis.client.connection.datasource.DataSource;

import java.time.Duration;

/**
 * Lettuce 数据源
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public interface LettuceRedisDataSource extends DataSource {

	/**
	 * 返回计算线程池大小
	 *
	 * @return 计算线程池大小
	 *
	 * @since 4.0.0
	 */
	int getComputationThreadPoolSize();

	/**
	 * 设置计算线程池大小
	 *
	 * @param computationThreadPoolSize
	 * 		计算线程池大小
	 *
	 * @since 4.0.0
	 */
	void setComputationThreadPoolSize(int computationThreadPoolSize);

	/**
	 * 获取 I/O 线程池大小
	 *
	 * @return I/O 线程池大小
	 *
	 * @since 4.0.0
	 */
	int getIoThreadPoolSize();

	/**
	 * 设置 I/O 线程池大小
	 *
	 * @param ioThreadPoolSize
	 * 		I/O 线程池大小
	 *
	 * @since 4.0.0
	 */
	void setIoThreadPoolSize(int ioThreadPoolSize);

	/**
	 * 返回请求队列大小
	 *
	 * @return 请求队列大小
	 *
	 * @since 4.0.0
	 */
	int getRequestQueueSize();

	/**
	 * 设置请求队列大小
	 *
	 * @param requestQueueSize
	 * 		请求队列大小
	 *
	 * @since 4.0.0
	 */
	void setRequestQueueSize(int requestQueueSize);

	/**
	 * 返回关闭超时时间
	 *
	 * @return 关闭超时时间（单位：毫秒）
	 *
	 * @since 4.0.0
	 */
	int getShutdownTimeout();

	/**
	 * 设置关闭超时时间
	 *
	 * @param shutdownTimeout
	 * 		关闭超时时间（单位：毫秒）
	 *
	 * @since 4.0.0
	 */
	void setShutdownTimeout(int shutdownTimeout);

}
