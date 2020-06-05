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
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.JedisScanParams;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.JedisCommands;

import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class DefaultJedisSetRedisOperations<C extends JedisCommands> extends AbstractJedisRedisOperations implements JedisSetRedisOperations {

	public DefaultJedisSetRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Long sAdd(final String key, final String... members){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("members"
				, members);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().sadd(key, members).get(), ProtocolCommand.SADD, args);
		}else{
			return execute((C cmd)->cmd.sadd(key, members), ProtocolCommand.SADD, args);
		}
	}

	@Override
	public Long sCard(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().scard(key).get(), ProtocolCommand.SCARD, args);
		}else{
			return execute((C cmd)->cmd.scard(key), ProtocolCommand.SCARD, args);
		}
	}

	@Override
	public boolean sisMember(final String key, final String member){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("member",
				member);

		if(isTransaction()){
			return execute((C cmd)->cmd.sismember(key, member), ProtocolCommand.SISMEMBER, args);
		}else{
			return execute((C cmd)->cmd.sismember(key, member), ProtocolCommand.SISMEMBER, args);
		}
	}

	@Override
	public Set<String> sMembers(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().smembers(key).get(), ProtocolCommand.SMEMBERS, args);
		}else{
			return execute((C cmd)->cmd.smembers(key), ProtocolCommand.SMEMBERS, args);
		}
	}

	@Override
	public String sPop(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().spop(key).get(), ProtocolCommand.SPOP, args);
		}else{
			return execute((C cmd)->cmd.spop(key), ProtocolCommand.SPOP, args);
		}
	}

	@Override
	public String sRandMember(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().srandmember(key).get(), ProtocolCommand.SRANDMEMBER, args);
		}else{
			return execute((C cmd)->cmd.srandmember(key), ProtocolCommand.SRANDMEMBER, args);
		}
	}

	@Override
	public List<String> sRandMember(final String key, final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("count",
				count);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().srandmember(key, count).get(), ProtocolCommand.SRANDMEMBER, args);
		}else{
			return execute((C cmd)->cmd.srandmember(key, count), ProtocolCommand.SRANDMEMBER, args);
		}
	}

	@Override
	public Long sRem(final String key, final String... members){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("members"
				, members);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().srem(key, members).get(), ProtocolCommand.SREM, args);
		}else{
			return execute((C cmd)->cmd.srem(key, members), ProtocolCommand.SREM, args);
		}
	}

	@Override
	public Set<String> sDiff(final String... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("keys", keys);

		return execute(new Executor<C, Set<String>>() {

			@Override
			public Set<String> execute(C cmd){
				if(isTransaction()){
					return getTransaction().sdiff(keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).sdiff(keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SDIFF);
					}
				}
			}

		}, ProtocolCommand.SDIFF, args);
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("destKey", destKey).put(
				"keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C cmd){
				if(isTransaction()){
					return getTransaction().sdiffstore(destKey, keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).sdiffstore(destKey, keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SDIFFSTORE);
					}
				}
			}

		}, ProtocolCommand.SDIFFSTORE, args);
	}

	@Override
	public Set<String> sInter(final String... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("keys", keys);

		return execute(new Executor<C, Set<String>>() {

			@Override
			public Set<String> execute(C cmd){
				if(isTransaction()){
					return getTransaction().sinter(keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).sinter(keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SINTER);
					}
				}
			}

		}, ProtocolCommand.SINTER, args);
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("destKey", destKey).put(
				"keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C cmd){
				if(isTransaction()){
					return getTransaction().sinterstore(destKey, keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).sinterstore(destKey, keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SINTERSTORE);
					}
				}
			}

		}, ProtocolCommand.SINTERSTORE, args);
	}

	@Override
	public Set<String> sUnion(final String... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("keys", keys);

		return execute(new Executor<C, Set<String>>() {

			@Override
			public Set<String> execute(C cmd){
				if(isTransaction()){
					return getTransaction().sunion(keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).sunion(keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SUNION);
					}
				}
			}

		}, ProtocolCommand.SUNION, args);
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("destKey", destKey).put(
				"keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C cmd){
				if(isTransaction()){
					return getTransaction().sunionstore(destKey, keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).sunionstore(destKey, keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SUNIONSTORE);
					}
				}
			}

		}, ProtocolCommand.SUNIONSTORE, args);
	}

	@Override
	public Status sMove(final String source, final String destKey, final String member){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("source", source).put(
				"destKey", destKey).put("member", member);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				Long ret;

				if(isTransaction()){
					ret = getTransaction().smove(source, destKey, member).get();
				}else{
					if(cmd instanceof Jedis){
						ret = ((Jedis) cmd).smove(source, destKey, member);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SMOVE);
					}
				}

				return statusForBool(ret > 0);
			}

		}, ProtocolCommand.SMOVE, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("cursor",
				cursor);

		return execute(new Executor<C, ScanResult<List<String>>>() {

			@Override
			public ScanResult<List<String>> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
				}else{
					return JedisClientUtils.listScanResultDeconvert(cmd.sscan(key, cursor));
				}
			}

		}, ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("cursor",
				cursor).put("pattern", pattern);

		return execute(new Executor<C, ScanResult<List<String>>>() {

			@Override
			public ScanResult<List<String>> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
				}else{
					return JedisClientUtils.listScanResultDeconvert(cmd.sscan(key, cursor,
							new JedisScanParams(pattern)));
				}
			}

		}, ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("cursor",
				cursor).put("count", count);

		return execute(new Executor<C, ScanResult<List<String>>>() {

			@Override
			public ScanResult<List<String>> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
				}else{
					return JedisClientUtils.listScanResultDeconvert(cmd.sscan(key, cursor,
							new JedisScanParams(count)));
				}
			}

		}, ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
			final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("cursor",
				cursor).put("pattern", pattern).put("count", count);

		return execute(new Executor<C, ScanResult<List<String>>>() {

			@Override
			public ScanResult<List<String>> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
				}else{
					return JedisClientUtils.listScanResultDeconvert(cmd.sscan(key, cursor, new JedisScanParams(pattern
							, count)));
				}
			}

		}, ProtocolCommand.SSCAN, args);
	}

}
