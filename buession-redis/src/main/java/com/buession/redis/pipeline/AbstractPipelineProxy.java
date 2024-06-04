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
package com.buession.redis.pipeline;

import com.buession.redis.core.FutureResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 管道代理抽象类
 *
 * @param <T>
 * 		原生管道类型
 * @param <FR>
 * 		管道异步结果
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public abstract class AbstractPipelineProxy<T, FR extends FutureResult<?>> implements PipelineProxy<T, FR> {

	private Pipeline target;

	private final T object;

	private final Queue<FR> txResults = new LinkedList<>();

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public AbstractPipelineProxy(final Pipeline target, final T object) {
		//Assert.isNull(target, "Redis Pipeline cloud not be null.");
		this.target = target;
		this.object = object;
	}

	@Override
	public T getObject() {
		return object;
	}

	@Override
	public Queue<FR> getTxResults() {
		return txResults;
	}

	@Override
	public void sync() {
		logger.info("Redis pipeline sync.");
		target.sync();
	}

	@Override
	public List<Object> syncAndReturnAll() {
		logger.info("Redis pipeline syncAndReturnAll.");

		target.sync();
		return txResults.stream().map((r)->r.convert(r.get())).collect(Collectors.toList());
	}

	@Override
	public void close() {
		logger.info("Redis pipeline close.");
		txResults.clear();
		target.close();
	}

	protected void setTarget(Pipeline target) {
		this.target = target;
	}

}
