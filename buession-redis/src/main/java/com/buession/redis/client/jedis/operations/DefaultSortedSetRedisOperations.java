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
import com.buession.lang.KeyValue;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.JedisScanParams;
import com.buession.redis.core.JedisZParams;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.JedisCommands;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class DefaultSortedSetRedisOperations<C extends JedisCommands> extends AbstractJedisRedisOperations implements JedisSortedSetRedisOperations {

	public DefaultSortedSetRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Number> members){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"members", members);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(final C jc){
				final Map<String, Double> data = new LinkedHashMap<>(members.size());

				members.forEach((key, value)->data.put(key, value.doubleValue()));

				if(isTransaction()){
					return getTransaction().zadd(key, data).get();
				}else{
					return jc.zadd(key, data);
				}
			}

		}, ProtocolCommand.ZADD, arguments);
	}

	@Override
	public Double zScore(final String key, final String member){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"member", member);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zscore(key, member).get(), ProtocolCommand.ZSCORE, arguments);
		}else{
			return execute((C jc)->jc.zscore(key, member), ProtocolCommand.ZSCORE, arguments);
		}
	}

	@Override
	public Long zCard(final String key){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zcard(key).get(), ProtocolCommand.ZCARD, arguments);
		}else{
			return execute((C jc)->jc.zcard(key), ProtocolCommand.ZCARD, arguments);
		}
	}

	@Override
	public Double zIncrBy(final String key, final String member, final double increment){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"member", member).put("increment", increment);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zincrby(key, increment, member).get(), ProtocolCommand.ZINCRBY,
					arguments);
		}else{
			return execute((C jc)->jc.zincrby(key, increment, member), ProtocolCommand.ZINCRBY, arguments);
		}
	}

	@Override
	public Long zCount(final String key, final double min, final double max){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zcount(key, min, max).get(), ProtocolCommand.ZCOUNT, arguments);
		}else{
			return execute((C jc)->jc.zcount(key, min, max), ProtocolCommand.ZCOUNT, arguments);
		}
	}

	@Override
	public Long zCount(final String key, final String min, final String max){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zcount(key, min, max).get(), ProtocolCommand.ZCOUNT, arguments);
		}else{
			return execute((C jc)->jc.zcount(key, min, max), ProtocolCommand.ZCOUNT, arguments);
		}
	}

	@Override
	public Set<String> zRange(final String key, final long start, final long end){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"start", start).put("end", end);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zrange(key, start, end).get(), ProtocolCommand.ZRANGE, arguments);
		}else{
			return execute((C jc)->jc.zrange(key, start, end), ProtocolCommand.ZRANGE, arguments);
		}
	}

	@Override
	public Set<Tuple> zRangeWithScores(final String key, final long start, final long end){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"start", start).put("end", end);

		if(isTransaction()){
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeWithScores(key, start,
					end).get()), ProtocolCommand.ZRANGE, arguments);
		}else{
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(jc.zrangeWithScores(key, start, end)),
					ProtocolCommand.ZRANGE, arguments);
		}
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zrangeByScore(key, min, max).get(), ProtocolCommand.ZRANGEBYSCORE,
					arguments);
		}else{
			return execute((C jc)->jc.zrangeByScore(key, min, max), ProtocolCommand.ZRANGEBYSCORE, arguments);
		}
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zrangeByScore(key, min, max).get(), ProtocolCommand.ZRANGEBYSCORE,
					arguments);
		}else{
			return execute((C jc)->jc.zrangeByScore(key, min, max), ProtocolCommand.ZRANGEBYSCORE, arguments);
		}
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max, final int offset,
			final int count){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zrangeByScore(key, min, max, offset, count).get(),
					ProtocolCommand.ZRANGEBYSCORE, arguments);
		}else{
			return execute((C jc)->jc.zrangeByScore(key, min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE,
					arguments);
		}
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max, final int offset,
			final int count){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zrangeByScore(key, min, max, offset, count).get(),
					ProtocolCommand.ZRANGEBYSCORE, arguments);
		}else{
			return execute((C jc)->jc.zrangeByScore(key, min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE,
					arguments);
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max);

		if(isTransaction()){
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key,
					min, max).get()), ProtocolCommand.ZRANGEBYSCORE, arguments);
		}else{
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(jc.zrangeByScoreWithScores(key, min, max)),
					ProtocolCommand.ZRANGEBYSCORE, arguments);
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max);

		if(isTransaction()){
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key,
					min, max).get()), ProtocolCommand.ZRANGEBYSCORE, arguments);
		}else{
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(jc.zrangeByScoreWithScores(key, min, max)),
					ProtocolCommand.ZRANGEBYSCORE, arguments);
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final int offset,
			final int count){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key,
					min, max, offset, count).get()), ProtocolCommand.ZRANGEBYSCORE, arguments);
		}else{
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(jc.zrangeByScoreWithScores(key, min, max, offset
					, count)), ProtocolCommand.ZRANGEBYSCORE, arguments);
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final int offset,
			final int count){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key,
					min, max, offset, count).get()), ProtocolCommand.ZRANGEBYSCORE, arguments);
		}else{
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(jc.zrangeByScoreWithScores(key, min, max, offset
					, count)), ProtocolCommand.ZRANGEBYSCORE, arguments);
		}
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zrangeByLex(key, min, max).get(), ProtocolCommand.ZRANGEBYLEX,
					arguments);
		}else{
			return execute((C jc)->jc.zrangeByLex(key, min, max), ProtocolCommand.ZRANGEBYLEX, arguments);
		}
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max, final int offset,
			final int count){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zrangeByLex(key, min, max, offset, count).get(),
					ProtocolCommand.ZRANGEBYLEX, arguments);
		}else{
			return execute((C jc)->jc.zrangeByLex(key, min, max, offset, count), ProtocolCommand.ZRANGEBYLEX,
					arguments);
		}
	}

	@Override
	public Long zRank(final String key, final String member){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"member", member);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zrank(key, member).get(), ProtocolCommand.ZRANK, arguments);
		}else{
			return execute((C jc)->jc.zrank(key, member), ProtocolCommand.ZRANK, arguments);
		}
	}

	@Override
	public Long zRevRank(final String key, final String member){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"member", member);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zrevrank(key, member).get(), ProtocolCommand.ZRANK, arguments);
		}else{
			return execute((C jc)->jc.zrevrank(key, member), ProtocolCommand.ZRANK, arguments);
		}
	}

	@Override
	public Long zRem(final String key, final String... members){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"members", members);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zrem(key, members).get(), ProtocolCommand.ZREM, arguments);
		}else{
			return execute((C jc)->jc.zrem(key, members), ProtocolCommand.ZREM, arguments);
		}
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"start", start).put("end", end);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zremrangeByRank(key, start, end).get(),
					ProtocolCommand.ZREMRANGEBYRANK, arguments);
		}else{
			return execute((C jc)->jc.zremrangeByRank(key, start, end), ProtocolCommand.ZREMRANGEBYRANK, arguments);
		}
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zremrangeByScore(key, min, max).get(),
					ProtocolCommand.ZREMRANGEBYSCORE, arguments);
		}else{
			return execute((C jc)->jc.zremrangeByScore(key, min, max), ProtocolCommand.ZREMRANGEBYSCORE, arguments);
		}
	}

	@Override
	public Long zRemRangeByScore(final String key, final String min, final String max){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zremrangeByScore(key, min, max).get(),
					ProtocolCommand.ZREMRANGEBYSCORE, arguments);
		}else{
			return execute((C jc)->jc.zremrangeByScore(key, min, max), ProtocolCommand.ZREMRANGEBYSCORE, arguments);
		}
	}

	@Override
	public Long zRemRangeByLex(final String key, final String min, final String max){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zremrangeByLex(key, min, max).get(),
					ProtocolCommand.ZREMRANGEBYLEX, arguments);
		}else{
			return execute((C jc)->getTransaction().zremrangeByLex(key, min, max).get(),
					ProtocolCommand.ZREMRANGEBYLEX, arguments);
		}
	}

	@Override
	public Set<String> zRevRange(final String key, final long start, final long end){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"start", start).put("end", end);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zrevrange(key, start, end).get(), ProtocolCommand.ZREVRANGE,
					arguments);
		}else{
			return execute((C jc)->jc.zrevrange(key, start, end), ProtocolCommand.ZREVRANGE, arguments);
		}
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final String key, final long start, final long end){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"start", start).put("end", end);

		if(isTransaction()){
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeWithScores(key, start,
					end).get()), ProtocolCommand.ZREVRANGE, arguments);
		}else{
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(jc.zrevrangeWithScores(key, start, end)),
					ProtocolCommand.ZREVRANGE, arguments);
		}
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zrevrangeByScore(key, min, max).get(),
					ProtocolCommand.ZREVRANGEBYSCORE, arguments);
		}else{
			return execute((C jc)->jc.zrevrangeByScore(key, min, max), ProtocolCommand.ZREVRANGEBYSCORE, arguments);
		}
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zrevrangeByScore(key, min, max).get(),
					ProtocolCommand.ZREVRANGEBYSCORE, arguments);
		}else{
			return execute((C jc)->jc.zrevrangeByScore(key, min, max), ProtocolCommand.ZREVRANGEBYSCORE, arguments);
		}
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max, final int offset,
			final int count){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zrevrangeByScore(key, min, max, offset, count).get(),
					ProtocolCommand.ZREVRANGEBYSCORE, arguments);
		}else{
			return execute((C jc)->jc.zrevrangeByScore(key, min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE
					, arguments);
		}
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max, final int offset,
			final int count){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zrevrangeByScore(key, min, max, offset, count).get(),
					ProtocolCommand.ZREVRANGEBYSCORE, arguments);
		}else{
			return execute((C jc)->jc.zrevrangeByScore(key, min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE
					, arguments);
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max);

		if(isTransaction()){
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max).get()), ProtocolCommand.ZREVRANGEBYSCORE, arguments);
		}else{
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(jc.zrevrangeByScoreWithScores(key, min, max)),
					ProtocolCommand.ZREVRANGEBYSCORE, arguments);
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max);

		if(isTransaction()){
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max).get()), ProtocolCommand.ZREVRANGEBYSCORE, arguments);
		}else{
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(jc.zrevrangeByScoreWithScores(key, min, max)),
					ProtocolCommand.ZREVRANGEBYSCORE, arguments);
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
			final int offset, final int count){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max, offset, count).get()), ProtocolCommand.ZREVRANGEBYSCORE, arguments);
		}else{
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(jc.zrevrangeByScoreWithScores(key, min, max,
					offset, count)), ProtocolCommand.ZREVRANGEBYSCORE, arguments);
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max,
			final int offset, final int count){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max, offset, count).get()), ProtocolCommand.ZREVRANGEBYSCORE, arguments);
		}else{
			return execute((C jc)->JedisClientUtils.setTupleDeconvert(jc.zrevrangeByScoreWithScores(key, min, max,
					offset, count)), ProtocolCommand.ZREVRANGEBYSCORE, arguments);
		}
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zrevrangeByLex(key, min, max).get(),
					ProtocolCommand.ZREVRANGEBYLEX, arguments);
		}else{
			return execute((C jc)->jc.zrevrangeByLex(key, min, max), ProtocolCommand.ZREVRANGEBYLEX, arguments);
		}
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max, final int offset,
			final int count){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max).put("min", min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zrevrangeByLex(key, min, max, offset, count).get(),
					ProtocolCommand.ZREVRANGEBYLEX, arguments);
		}else{
			return execute((C jc)->jc.zrevrangeByLex(key, min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX,
					arguments);
		}
	}

	@Override
	public Long zLexCount(final String key, final String min, final String max){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put("min"
				, min).put("max", max).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((C jc)->getTransaction().zlexcount(key, min, max).get(), ProtocolCommand.ZLEXCOUNT,
					arguments);
		}else{
			return execute((C jc)->jc.zlexcount(key, min, max), ProtocolCommand.ZLEXCOUNT, arguments);
		}
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C jc){
				if(isTransaction()){
					return getTransaction().zinterstore(destKey, keys).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).zinterstore(destKey, keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
					}
				}
			}

		}, ProtocolCommand.ZINTERSTORE, arguments);
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final String... keys){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys"
						, keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C jc){
				if(isTransaction()){
					return getTransaction().zinterstore(destKey,
							new JedisZParams(JedisClientUtils.aggregateConvert(aggregate)), keys).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).zinterstore(destKey,
								new JedisZParams(JedisClientUtils.aggregateConvert(aggregate)), keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
					}
				}
			}

		}, ProtocolCommand.ZINTERSTORE, arguments);
	}

	@Override
	public Long zInterStore(final String destKey, final double[] weights, final String... keys){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys",
						keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C jc){
				if(isTransaction()){
					return getTransaction().zinterstore(destKey, new JedisZParams(weights), keys).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).zinterstore(destKey, new JedisZParams(weights), keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
					}
				}
			}

		}, ProtocolCommand.ZINTERSTORE, arguments);
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put(
						"weights", weights).put("keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C jc){
				if(isTransaction()){
					return getTransaction().zinterstore(destKey,
							new JedisZParams(JedisClientUtils.aggregateConvert(aggregate), weights), keys).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).zinterstore(destKey,
								new JedisZParams(JedisClientUtils.aggregateConvert(aggregate), weights), keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
					}
				}
			}

		}, ProtocolCommand.ZINTERSTORE, arguments);
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C jc){
				if(isTransaction()){
					return getTransaction().zunionstore(destKey, keys).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).zunionstore(destKey, keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
					}
				}
			}

		}, ProtocolCommand.ZUNIONSTORE, arguments);
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final String... keys){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys"
						, keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C jc){
				if(isTransaction()){
					return getTransaction().zunionstore(destKey,
							new JedisZParams(JedisClientUtils.aggregateConvert(aggregate)), keys).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).zunionstore(destKey,
								new JedisZParams(JedisClientUtils.aggregateConvert(aggregate)), keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
					}
				}
			}

		}, ProtocolCommand.ZUNIONSTORE, arguments);
	}

	@Override
	public Long zUnionStore(final String destKey, final double[] weights, final String... keys){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys",
						keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C jc){
				if(isTransaction()){
					return getTransaction().zunionstore(destKey, new JedisZParams(weights), keys).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).zunionstore(destKey, new JedisZParams(weights), keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
					}
				}
			}

		}, ProtocolCommand.ZUNIONSTORE, arguments);
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put(
						"weights", weights).put("keys", keys);

		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C jc){
				if(isTransaction()){
					return getTransaction().zunionstore(destKey,
							new JedisZParams(JedisClientUtils.aggregateConvert(aggregate), weights), keys).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).zunionstore(destKey,
								new JedisZParams(JedisClientUtils.aggregateConvert(aggregate), weights), keys);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
					}
				}
			}

		}, ProtocolCommand.ZUNIONSTORE, arguments);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"cursor", cursor);

		return execute(new Executor<C, ScanResult<List<Tuple>>>() {

			@Override
			public ScanResult<List<Tuple>> execute(C jc){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
				}else{
					return JedisClientUtils.listTupleScanResultDeconvert(jc.zscan(key, cursor));
				}
			}

		}, ProtocolCommand.ZSCAN, arguments);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"cursor", cursor).put("pattern", pattern);

		return execute(new Executor<C, ScanResult<List<Tuple>>>() {

			@Override
			public ScanResult<List<Tuple>> execute(C jc){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
				}else{
					return JedisClientUtils.listTupleScanResultDeconvert(jc.zscan(key, cursor,
							new JedisScanParams(pattern)));
				}
			}

		}, ProtocolCommand.ZSCAN, arguments);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final int count){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"cursor", cursor).put("count", count);

		return execute(new Executor<C, ScanResult<List<Tuple>>>() {

			@Override
			public ScanResult<List<Tuple>> execute(C jc){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
				}else{
					return JedisClientUtils.listTupleScanResultDeconvert(jc.zscan(key, cursor,
							new JedisScanParams(count)));
				}
			}

		}, ProtocolCommand.ZSCAN, arguments);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final int count){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("key", key).put(
				"cursor", cursor).put("pattern", pattern).put("count", count);

		return execute(new Executor<C, ScanResult<List<Tuple>>>() {

			@Override
			public ScanResult<List<Tuple>> execute(C jc){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
				}else{
					return JedisClientUtils.listTupleScanResultDeconvert(jc.zscan(key, cursor,
							new JedisScanParams(pattern, count)));
				}
			}

		}, ProtocolCommand.ZSCAN, arguments);
	}

}
