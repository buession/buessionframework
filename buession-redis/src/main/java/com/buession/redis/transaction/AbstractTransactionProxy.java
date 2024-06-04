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
package com.buession.redis.transaction;

import com.buession.redis.core.FutureResult;
import com.buession.redis.transaction.Transaction;
import com.buession.redis.transaction.TransactionProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 事务代理抽象类
 *
 * @param <T>
 * 		原生事务类型
 * @param <FR>
 * 		事务异步结果
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public abstract class AbstractTransactionProxy<T, FR extends FutureResult<?>> implements TransactionProxy<T, FR> {

	private Transaction target;

	private final T object;

	private final Queue<FR> txResults = new LinkedList<>();

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public AbstractTransactionProxy(final Transaction target, final T object) {
		//Assert.isNull(target, "Redis Transaction cloud not be null.");
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
	public List<Object> exec() {
		logger.info("Redis transaction exec.");
		target.exec();
		return txResults.stream().map((r)->r.convert(r.get())).collect(Collectors.toList());
	}

	@Override
	public String discard() {
		logger.info("Redis transaction discard.");
		return target.discard();
	}

	@Override
	public void close() {
		logger.info("Redis pipeline close.");
		txResults.clear();
		target.close();
	}

	protected void setTarget(Transaction target) {
		this.target = target;
	}

}
