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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.ShardedJedisClient;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.RedisExceptionUtils;
import com.buession.redis.transaction.Transaction;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class ShardedJedisTransactionOperations extends AbstractTransactionOperations<ShardedJedis,
		ShardedJedisPipeline> {

	public ShardedJedisTransactionOperations(final ShardedJedisClient client){
		super(client);
	}

	@Override
	public void discard(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.DISCARD, client.getConnection());
	}

	@Override
	public List<Object> exec(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.EXEC, client.getConnection());
		return null;
	}

	@Override
	public Transaction multi(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.MULTI, client.getConnection());
		return null;
	}

	@Override
	public Status unwatch(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.UNWATCH, client.getConnection());
		return null;
	}

	@Override
	public Status watch(final String... keys){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.WATCH, client.getConnection());
		return null;
	}

	@Override
	public Status watch(final byte[]... keys){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.WATCH, client.getConnection());
		return null;
	}

}
