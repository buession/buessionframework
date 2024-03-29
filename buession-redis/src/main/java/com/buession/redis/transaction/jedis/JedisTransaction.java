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
package com.buession.redis.transaction.jedis;

import com.buession.core.utils.Assert;
import com.buession.redis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Jedis 事务
 *
 * @author Yong.Teng
 */
public class JedisTransaction implements Transaction {

	private final redis.clients.jedis.Transaction delegate;

	private final static Logger logger = LoggerFactory.getLogger(JedisTransaction.class);

	public JedisTransaction(redis.clients.jedis.Transaction transaction) {
		Assert.isNull(transaction, "Redis Transaction cloud not be null.");
		this.delegate = transaction;
	}

	public redis.clients.jedis.Transaction primitive() {
		return delegate;
	}

	@Override
	public List<Object> exec() {
		logger.info("Redis transaction exec.");
		return delegate.exec();
	}

	@Override
	public String discard() {
		logger.info("Redis transaction discard.");
		return delegate.discard();
	}

	@Override
	public void close() {
		logger.info("Redis transaction close.");
		delegate.close();
	}

}