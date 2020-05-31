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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.redis.client.AbstractRedisOperations;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.transaction.Transaction;
import com.buession.redis.transaction.jedis.JedisTransaction;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisOperations extends AbstractRedisOperations<JedisRedisClient> {

	public AbstractJedisRedisOperations(final JedisRedisClient client){
		super(client);
	}

	protected redis.clients.jedis.Transaction getTransaction(){
		JedisTransaction jedisTransaction = (JedisTransaction) client.getConnection().getTransaction();
		return jedisTransaction.primitive();
	}

	protected Jedis getShard(final ShardedJedis shardedJedis, final String key){
		return shardedJedis.getShard(key);
	}

	protected Jedis getShard(final ShardedJedis shardedJedis, final byte[] key){
		return shardedJedis.getShard(key);
	}

}
