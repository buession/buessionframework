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

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.commands.BinaryJedisCommands;

import java.util.List;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisBinaryBitMapRedisOperations<C extends BinaryJedisCommands> extends AbstractJedisBinaryRedisOperations implements JedisBinaryBitMapRedisOperations {

	public AbstractJedisBinaryBitMapRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final byte[] value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("offset",
				offset).put("value", value);

		if(isTransaction()){
			return execute((C cmd)->statusForBool(getTransaction().setbit(key, offset, value).get()),
					ProtocolCommand.SETBIT, args);
		}else{
			return execute((C cmd)->statusForBool(cmd.setbit(key, offset, value)), ProtocolCommand.SETBIT, args);
		}
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final boolean value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("offset",
				offset).put("value", value);

		if(isTransaction()){
			return execute((C cmd)->statusForBool(getTransaction().setbit(SafeEncoder.encode(key), offset, value).get()), ProtocolCommand.SETBIT, args);
		}else{
			return execute((C cmd)->statusForBool(cmd.setbit(key, offset, value)), ProtocolCommand.SETBIT, args);
		}
	}

	@Override
	public Status getBit(final byte[] key, final long offset){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("offset",
				offset);

		if(isTransaction()){
			return execute((C cmd)->statusForBool(getTransaction().getbit(key, offset).get()), ProtocolCommand.GETBIT,
					args);
		}else{
			return execute((C cmd)->statusForBool(cmd.getbit(key, offset)), ProtocolCommand.GETBIT, args);
		}
	}

	@Override
	public List<Long> bitField(final byte[] key, final byte[]... arguments){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put(
				"arguments", arguments);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().bitfield(key, arguments).get(), ProtocolCommand.BITFIELD, args);
		}else{
			return execute((C cmd)->cmd.bitfield(key, arguments), ProtocolCommand.BITFIELD, args);
		}
	}

	@Override
	public Long bitCount(final byte[] key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().bitcount(key).get(), ProtocolCommand.BITCOUNT, args);
		}else{
			return execute((C cmd)->cmd.bitcount(key), ProtocolCommand.BITCOUNT, args);
		}
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("start",
				start).put("end", end);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().bitcount(key, start, end).get(), ProtocolCommand.BITCOUNT, args);
		}else{
			return execute((C cmd)->cmd.bitcount(key, start, end), ProtocolCommand.BITCOUNT, args);
		}
	}

}
