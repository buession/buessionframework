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

import com.buession.redis.client.connection.datasource.AbstractDataSource;

/**
 * Lettuce 数据源抽象类
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public abstract class AbstractLettuceDataSource extends AbstractDataSource implements LettuceRedisDataSource {

	/**
	 * 计算线程池大小
	 *
	 * @since 4.0.0
	 */
	private int computationThreadPoolSize;

	/**
	 * IO 线程池大小
	 *
	 * @since 4.0.0
	 */
	private int ioThreadPoolSize;

	/**
	 * 请求队列大小
	 *
	 * @since 4.0.0
	 */
	private int requestQueueSize;

	@Override
	public int getComputationThreadPoolSize() {
		return computationThreadPoolSize;
	}

	@Override
	public void setComputationThreadPoolSize(int computationThreadPoolSize) {
		this.computationThreadPoolSize = computationThreadPoolSize;
	}

	@Override
	public int getIoThreadPoolSize() {
		return ioThreadPoolSize;
	}

	@Override
	public void setIoThreadPoolSize(int ioThreadPoolSize) {
		this.ioThreadPoolSize = ioThreadPoolSize;
	}

	@Override
	public int getRequestQueueSize() {
		return requestQueueSize;
	}

	@Override
	public void setRequestQueueSize(int requestQueueSize) {
		this.requestQueueSize = requestQueueSize;
	}

}
