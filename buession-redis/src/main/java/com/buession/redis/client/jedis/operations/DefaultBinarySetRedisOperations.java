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

import com.buession.core.Executor;
import com.buession.redis.client.BinarySetRedisOperations;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.BinaryJedisCommands;

import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class DefaultBinarySetRedisOperations<C extends BinaryJedisCommands> extends AbstractJedisBinaryRedisOperations implements BinarySetRedisOperations {

	public DefaultBinarySetRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"members", members);

		if(isTransaction()){
			return execute((C jc)->getTransaction().sAdd(key, members), ProtocolCommand.SADD, arguments);
		}else{
			return execute((C jc)->jc.sadd(key, members), ProtocolCommand.SADD, arguments);
		}
	}

	@Override
	public Long sCard(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().sCard(key), ProtocolCommand.SCARD, arguments);
		}else{
			return execute((C jc)->jc.scard(key), ProtocolCommand.SCARD, arguments);
		}
	}

	@Override
	public boolean sisMember(final byte[] key, final byte[] member){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"member", member);

		if(isTransaction()){
			return execute((C jc)->getTransaction().sisMember(key, member), ProtocolCommand.SISMEMBER, arguments);
		}else{
			return execute((C jc)->jc.sismember(key, member), ProtocolCommand.SISMEMBER, arguments);
		}
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().sMembers(key), ProtocolCommand.SMEMBERS, arguments);
		}else{
			return execute((C jc)->jc.smembers(key), ProtocolCommand.SMEMBERS, arguments);
		}
	}

	@Override
	public byte[] sPop(final byte[] key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().sPop(key), ProtocolCommand.SPOP, arguments);
		}else{
			return execute((C jc)->jc.spop(key), ProtocolCommand.SPOP, arguments);
		}
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final int count){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"count", count);

		if(isTransaction()){
			return execute((C jc)->getTransaction().sRandMember(key, count), ProtocolCommand.SRANDMEMBER, arguments);
		}else{
			return execute((C jc)->jc.srandmember(key, count), ProtocolCommand.SRANDMEMBER, arguments);
		}
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final long count){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"count", count);

		if(isTransaction()){
			return execute((C jc)->getTransaction().sRandMember(key, count), ProtocolCommand.SRANDMEMBER, arguments);
		}else{
			return execute((C jc)->jc.srandmember(key, (int) count), ProtocolCommand.SRANDMEMBER, arguments);
		}
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"members", members);

		if(isTransaction()){
			return execute((C jc)->getTransaction().sRem(key, members), ProtocolCommand.SREM, arguments);
		}else{
			return execute((C jc)->jc.srem(key, members), ProtocolCommand.SREM, arguments);
		}
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("keys", keys);

		if(isTransaction()){
			return execute((C jc)->getTransaction().sDiff(keys), ProtocolCommand.SDIFF, arguments);
		}else{
			return execute(new Executor<C, Set<byte[]>>() {

				@Override
				public Set<byte[]> execute(C jc){
					if(jc instanceof Jedis){
						return ((Jedis) jc).sdiff(keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SDIFF);
					}
				}
			}, ProtocolCommand.SDIFF, arguments);
		}
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", keys);

		if(isTransaction()){
			return execute((C jc)->getTransaction().sDiffStore(destKey, keys), ProtocolCommand.SDIFFSTORE, arguments);
		}else{
			return execute(new Executor<C, Long>() {

				@Override
				public Long execute(C jc){
					if(jc instanceof Jedis){
						return ((Jedis) jc).sdiffstore(destKey, keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SDIFFSTORE);
					}
				}
			}, ProtocolCommand.SDIFFSTORE, arguments);
		}
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("keys", keys);

		if(isTransaction()){
			return execute((C jc)->getTransaction().sInter(keys), ProtocolCommand.SINTER, arguments);
		}else{
			return execute(new Executor<C, Set<byte[]>>() {

				@Override
				public Set<byte[]> execute(C jc){
					if(jc instanceof Jedis){
						return ((Jedis) jc).sinter(keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SINTER);
					}
				}
			}, ProtocolCommand.SINTER, arguments);
		}
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", keys);

		if(isTransaction()){
			return execute((C jc)->getTransaction().sInterStore(destKey, keys), ProtocolCommand.SINTERSTORE,
					arguments);
		}else{
			return execute(new Executor<C, Long>() {

				@Override
				public Long execute(C jc){
					if(jc instanceof Jedis){
						return ((Jedis) jc).sinterstore(destKey, keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SINTERSTORE);
					}
				}
			}, ProtocolCommand.SINTERSTORE, arguments);
		}
	}

}
