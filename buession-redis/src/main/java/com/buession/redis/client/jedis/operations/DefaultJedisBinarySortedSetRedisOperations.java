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
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.JedisScanParams;
import com.buession.redis.core.JedisZParams;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.command.SortedSetCommands;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.BinaryJedisCommands;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class DefaultJedisBinarySortedSetRedisOperations<C extends BinaryJedisCommands> extends AbstractJedisBinaryRedisOperations implements JedisBinarySortedSetRedisOperations {

	public DefaultJedisBinarySortedSetRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Number> members){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("members"
				, members);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C cmd){
				final Map<byte[], Double> data = new LinkedHashMap<>(members.size());

				members.forEach((key, value)->data.put(key, value.doubleValue()));

				if(isTransaction()){
					return getTransaction().zadd(key, data).get();
				}else{
					return cmd.zadd(key, data);
				}
			}

		}, ProtocolCommand.ZADD, args);
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("member",
				member);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zscore(key, member).get(), ProtocolCommand.ZSCORE, args);
		}else{
			return execute((C cmd)->cmd.zscore(key, member), ProtocolCommand.ZSCORE, args);
		}
	}

	@Override
	public Long zCard(final byte[] key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zcard(key).get(), ProtocolCommand.ZCARD, args);
		}else{
			return execute((C cmd)->cmd.zcard(key), ProtocolCommand.ZCARD, args);
		}
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final double increment){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("member",
				member).put("increment", increment);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zincrby(key, increment, member).get(), ProtocolCommand.ZINCRBY,
					args);
		}else{
			return execute((C cmd)->cmd.zincrby(key, increment, member), ProtocolCommand.ZINCRBY, args);
		}
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zcount(key, min, max).get(), ProtocolCommand.ZCOUNT, args);
		}else{
			return execute((C cmd)->cmd.zcount(key, min, max), ProtocolCommand.ZCOUNT, args);
		}
	}

	@Override
	public Long zCount(final byte[] key, final byte[] min, final byte[] max){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zcount(key, min, max).get(), ProtocolCommand.ZCOUNT, args);
		}else{
			return execute((C cmd)->cmd.zcount(key, min, max), ProtocolCommand.ZCOUNT, args);
		}
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final long start, final long end){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("start",
				start).put("end", end);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zrange(key, start, end).get(), ProtocolCommand.ZRANGE, args);
		}else{
			return execute((C cmd)->cmd.zrange(key, start, end), ProtocolCommand.ZRANGE, args);
		}
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("start",
				start).put("end", end);

		if(isTransaction()){
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeWithScores(key, start,
					end).get()), ProtocolCommand.ZRANGE, args);
		}else{
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeWithScores(key, start, end)),
					ProtocolCommand.ZRANGE, args);
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zrangeByScore(key, min, max).get(), ProtocolCommand.ZRANGEBYSCORE
					, args);
		}else{
			return execute((C cmd)->cmd.zrangeByScore(key, min, max), ProtocolCommand.ZRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zrangeByScore(key, min, max).get(), ProtocolCommand.ZRANGEBYSCORE
					, args);
		}else{
			return execute((C cmd)->cmd.zrangeByScore(key, min, max), ProtocolCommand.ZRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zrangeByScore(key, min, max).get(), ProtocolCommand.ZRANGEBYSCORE
					, args);
		}else{
			return execute((C cmd)->cmd.zrangeByScore(key, min, max), ProtocolCommand.ZRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zrangeByScore(key, min, max, offset, count).get(),
					ProtocolCommand.ZRANGEBYSCORE, args);
		}else{
			return execute((C cmd)->cmd.zrangeByScore(key, min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE,
					args);
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max);

		if(isTransaction()){
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key,
					min, max).get()), ProtocolCommand.ZRANGEBYSCORE, args);
		}else{
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeByScoreWithScores(key, min, max)),
					ProtocolCommand.ZRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max);

		if(isTransaction()){
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key,
					min, max).get()), ProtocolCommand.ZRANGEBYSCORE, args);
		}else{
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeByScoreWithScores(key, min, max)),
					ProtocolCommand.ZRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
			final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key,
					min, max, offset, count).get()), ProtocolCommand.ZRANGEBYSCORE, args);
		}else{
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeByScoreWithScores(key, min, max,
					offset, count)), ProtocolCommand.ZRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key,
					min, max, offset, count).get()), ProtocolCommand.ZRANGEBYSCORE, args);
		}else{
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeByScoreWithScores(key, min, max,
					offset, count)), ProtocolCommand.ZRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zrangeByLex(key, min, max).get(), ProtocolCommand.ZRANGEBYLEX,
					args);
		}else{
			return execute((C cmd)->cmd.zrangeByLex(key, min, max), ProtocolCommand.ZRANGEBYLEX, args);
		}
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zrangeByLex(key, min, max, offset, count).get(),
					ProtocolCommand.ZRANGEBYLEX, args);
		}else{
			return execute((C cmd)->cmd.zrangeByLex(key, min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
		}
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("member",
				member);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zrank(key, member).get(), ProtocolCommand.ZRANK, args);
		}else{
			return execute((C cmd)->cmd.zrank(key, member), ProtocolCommand.ZRANK, args);
		}
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("member",
				member);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zrevrank(key, member).get(), ProtocolCommand.ZRANK, args);
		}else{
			return execute((C cmd)->cmd.zrevrank(key, member), ProtocolCommand.ZRANK, args);
		}
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("members"
				, members);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zrem(key, members).get(), ProtocolCommand.ZREM, args);
		}else{
			return execute((C cmd)->cmd.zrem(key, members), ProtocolCommand.ZREM, args);
		}
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("start",
				start).put("end", end);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zremrangeByRank(key, start, end).get(),
					ProtocolCommand.ZREMRANGEBYRANK, args);
		}else{
			return execute((C cmd)->cmd.zremrangeByRank(key, start, end), ProtocolCommand.ZREMRANGEBYRANK, args);
		}
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zremrangeByScore(key, min, max).get(),
					ProtocolCommand.ZREMRANGEBYSCORE, args);
		}else{
			return execute((C cmd)->cmd.zremrangeByScore(key, min, max), ProtocolCommand.ZREMRANGEBYSCORE, args);
		}
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zremrangeByScore(key, min, max).get(),
					ProtocolCommand.ZREMRANGEBYSCORE, args);
		}else{
			return execute((C cmd)->cmd.zremrangeByScore(key, min, max), ProtocolCommand.ZREMRANGEBYSCORE, args);
		}
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zremrangeByLex(key, min, max).get(),
					ProtocolCommand.ZREMRANGEBYLEX, args);
		}else{
			return execute((C cmd)->getTransaction().zremrangeByLex(key, min, max).get(),
					ProtocolCommand.ZREMRANGEBYLEX, args);
		}
	}

	@Override
	public Set<byte[]> zRevRange(final byte[] key, final long start, final long end){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("start",
				start).put("end", end);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zrevrange(key, start, end).get(), ProtocolCommand.ZREVRANGE,
					args);
		}else{
			return execute((C cmd)->cmd.zrevrange(key, start, end), ProtocolCommand.ZREVRANGE, args);
		}
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("start",
				start).put("end", end);

		if(isTransaction()){
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeWithScores(key, start
					, end).get()), ProtocolCommand.ZREVRANGE, args);
		}else{
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeWithScores(key, start, end)),
					ProtocolCommand.ZREVRANGE, args);
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zrevrangeByScore(key, min, max).get(),
					ProtocolCommand.ZREVRANGEBYSCORE, args);
		}else{
			return execute((C cmd)->cmd.zrevrangeByScore(key, min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zrevrangeByScore(key, min, max).get(),
					ProtocolCommand.ZREVRANGEBYSCORE, args);
		}else{
			return execute((C cmd)->cmd.zrevrangeByScore(key, min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zrevrangeByScore(key, min, max, offset, count).get(),
					ProtocolCommand.ZREVRANGEBYSCORE, args);
		}else{
			return execute((C cmd)->cmd.zrevrangeByScore(key, min, max, offset, count),
					ProtocolCommand.ZREVRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zrevrangeByScore(key, min, max, offset, count).get(),
					ProtocolCommand.ZREVRANGEBYSCORE, args);
		}else{
			return execute((C cmd)->cmd.zrevrangeByScore(key, min, max, offset, count),
					ProtocolCommand.ZREVRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max);

		if(isTransaction()){
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key
					, min, max).get()), ProtocolCommand.ZREVRANGEBYSCORE, args);
		}else{
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeByScoreWithScores(key, min, max)),
					ProtocolCommand.ZREVRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max);

		if(isTransaction()){
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key
					, min, max).get()), ProtocolCommand.ZREVRANGEBYSCORE, args);
		}else{
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeByScoreWithScores(key, min, max)),
					ProtocolCommand.ZREVRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
			final int offset, final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key
					, min, max, offset, count).get()), ProtocolCommand.ZREVRANGEBYSCORE, args);
		}else{
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeByScoreWithScores(key, min, max,
					offset, count)), ProtocolCommand.ZREVRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
			final int offset, final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key
					, min, max, offset, count).get()), ProtocolCommand.ZREVRANGEBYSCORE, args);
		}else{
			return execute((C cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeByScoreWithScores(key, min, max,
					offset, count)), ProtocolCommand.ZREVRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zrevrangeByLex(key, min, max).get(),
					ProtocolCommand.ZREVRANGEBYLEX, args);
		}else{
			return execute((C cmd)->cmd.zrevrangeByLex(key, min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
		}
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max).put("min", min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zrevrangeByLex(key, min, max, offset, count).get(),
					ProtocolCommand.ZREVRANGEBYLEX, args);
		}else{
			return execute((C cmd)->cmd.zrevrangeByLex(key, min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX,
					args);
		}
	}

	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("min",
				min).put("max", max).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((C cmd)->getTransaction().zlexcount(key, min, max).get(), ProtocolCommand.ZLEXCOUNT, args);
		}else{
			return execute((C cmd)->cmd.zlexcount(key, min, max), ProtocolCommand.ZLEXCOUNT, args);
		}
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("destKey", destKey).put(
				"keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C cmd){
				if(isTransaction()){
					return getTransaction().zinterstore(destKey, keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).zinterstore(destKey, keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
					}
				}
			}

		}, ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final SortedSetCommands.Aggregate aggregate, final byte[]... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("destKey", destKey).put(
				"aggregate", aggregate).put("keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C cmd){
				if(isTransaction()){
					return getTransaction().zinterstore(destKey,
							new JedisZParams(JedisClientUtils.aggregateConvert(aggregate)), keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).zinterstore(destKey,
								new JedisZParams(JedisClientUtils.aggregateConvert(aggregate)), keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
					}
				}
			}

		}, ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("destKey", destKey).put(
				"weights", weights).put("keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C cmd){
				if(isTransaction()){
					return getTransaction().zinterstore(destKey, new JedisZParams(weights), keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).zinterstore(destKey, new JedisZParams(weights), keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
					}
				}
			}

		}, ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final SortedSetCommands.Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("destKey", destKey).put(
				"aggregate", aggregate).put("weights", weights).put("keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C cmd){
				if(isTransaction()){
					return getTransaction().zinterstore(destKey,
							new JedisZParams(JedisClientUtils.aggregateConvert(aggregate), weights), keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).zinterstore(destKey,
								new JedisZParams(JedisClientUtils.aggregateConvert(aggregate), weights), keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
					}
				}
			}

		}, ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("destKey", destKey).put(
				"keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C cmd){
				if(isTransaction()){
					return getTransaction().zunionstore(destKey, keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).zunionstore(destKey, keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
					}
				}
			}

		}, ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final SortedSetCommands.Aggregate aggregate, final byte[]... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("destKey", destKey).put(
				"aggregate", aggregate).put("keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C cmd){
				if(isTransaction()){
					return getTransaction().zunionstore(destKey,
							new JedisZParams(JedisClientUtils.aggregateConvert(aggregate)), keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).zunionstore(destKey,
								new JedisZParams(JedisClientUtils.aggregateConvert(aggregate)), keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
					}
				}
			}

		}, ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("destKey", destKey).put(
				"weights", weights).put("keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C cmd){
				if(isTransaction()){
					return getTransaction().zunionstore(destKey, new JedisZParams(weights), keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).zunionstore(destKey, new JedisZParams(weights), keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
					}
				}
			}

		}, ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final SortedSetCommands.Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("destKey", destKey).put(
				"aggregate", aggregate).put("weights", weights).put("keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C cmd){
				if(isTransaction()){
					return getTransaction().zunionstore(destKey,
							new JedisZParams(JedisClientUtils.aggregateConvert(aggregate), weights), keys).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).zunionstore(destKey,
								new JedisZParams(JedisClientUtils.aggregateConvert(aggregate), weights), keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
					}
				}
			}

		}, ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("cursor",
				cursor);

		return execute(new Executor<C, ScanResult<List<Tuple>>>() {

			@Override
			public ScanResult<List<Tuple>> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
				}else{
					return JedisClientUtils.listTupleScanResultDeconvert(cmd.zscan(key, cursor));
				}
			}

		}, ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("cursor",
				cursor).put("pattern", pattern);

		return execute(new Executor<C, ScanResult<List<Tuple>>>() {

			@Override
			public ScanResult<List<Tuple>> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
				}else{
					return JedisClientUtils.listTupleScanResultDeconvert(cmd.zscan(key, cursor,
							new JedisScanParams(pattern)));
				}
			}

		}, ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("cursor",
				cursor).put("count", count);

		return execute(new Executor<C, ScanResult<List<Tuple>>>() {

			@Override
			public ScanResult<List<Tuple>> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
				}else{
					return JedisClientUtils.listTupleScanResultDeconvert(cmd.zscan(key, cursor,
							new JedisScanParams(count)));
				}
			}

		}, ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("cursor",
				cursor).put("pattern", pattern).put("count", count);

		return execute(new Executor<C, ScanResult<List<Tuple>>>() {

			@Override
			public ScanResult<List<Tuple>> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
				}else{
					return JedisClientUtils.listTupleScanResultDeconvert(cmd.zscan(key, cursor,
							new JedisScanParams(pattern, count)));
				}
			}

		}, ProtocolCommand.ZSCAN, args);
	}

}
