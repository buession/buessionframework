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

import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.jedis.JedisScanParams;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class ShardedJedisSortedSetOperations extends AbstractSortedSetOperations<ShardedJedis, ShardedJedisPipeline> {

	public ShardedJedisSortedSetOperations(final JedisRedisClient<ShardedJedis> client){
		super(client);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Number> members){
		final Map<byte[], Double> data = new LinkedHashMap<>(members.size());

		members.forEach((k, v)->data.put(k, v.doubleValue()));

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zadd(key, data)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zadd(key, data)));
		}else{
			return execute((cmd)->cmd.zadd(key, data));
		}
	}

	@Override
	public Long zCard(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zcard(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zcard(key)));
		}else{
			return execute((cmd)->cmd.zcard(key));
		}
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zcount(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zcount(key, min, max)));
		}else{
			return execute((cmd)->cmd.zcount(key, min, max));
		}
	}

	@Override
	public Long zCount(final byte[] key, final byte[] min, final byte[] max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zcount(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zcount(key, min, max)));
		}else{
			return execute((cmd)->cmd.zcount(key, min, max));
		}
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final double increment){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zincrby(key, increment, member)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zincrby(key, increment, member)));
		}else{
			return execute((cmd)->cmd.zincrby(key, increment, member));
		}
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys){
		commandAllNotSupportedException(ProtocolCommand.ZINTERSTORE);
		return null;
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys){
		commandAllNotSupportedException(ProtocolCommand.ZINTERSTORE);
		return null;
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final String... keys){
		commandAllNotSupportedException(ProtocolCommand.ZINTERSTORE);
		return null;
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		commandAllNotSupportedException(ProtocolCommand.ZINTERSTORE);
		return null;
	}

	@Override
	public Long zInterStore(final String destKey, final double[] weights, final String... keys){
		commandAllNotSupportedException(ProtocolCommand.ZINTERSTORE);
		return null;
	}

	@Override
	public Long zInterStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		commandAllNotSupportedException(ProtocolCommand.ZINTERSTORE);
		return null;
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		commandAllNotSupportedException(ProtocolCommand.ZINTERSTORE);
		return null;
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		commandAllNotSupportedException(ProtocolCommand.ZINTERSTORE);
		return null;
	}

	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zlexcount(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zlexcount(key, min, max)));
		}else{
			return execute((cmd)->cmd.zlexcount(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final long start, final long end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrange(key, start, end)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrange(key, start, end)));
		}else{
			return execute((cmd)->cmd.zrange(key, start, end));
		}
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrangeWithScores(key, start, end),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeWithScores(key, start, end),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeWithScores(key, start, end)));
		}
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrangeByLex(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByLex(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrangeByLex(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrangeByLex(key, min, max, offset, count)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByLex(key, min, max, offset,
					count)));
		}else{
			return execute((cmd)->cmd.zrangeByLex(key, min, max, offset, count));
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrangeByScore(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrangeByScore(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrangeByScore(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrangeByScore(key, min, max, offset, count)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScore(key, min, max, offset,
					count)));
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrangeByScoreWithScores(key, min, max),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScoreWithScores(key, min, max),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrangeByScoreWithScores(key, min, max),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScoreWithScores(key, min, max),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
			final int count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrangeByScoreWithScores(key, min, max, offset,
					count), SET_TUPLE_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScoreWithScores(key, min, max,
					offset, count), SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeByScoreWithScores(key, min, max, offset
					, count)));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrangeByScoreWithScores(key, min, max, offset,
					count), SET_TUPLE_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScoreWithScores(key, min, max,
					offset, count), SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeByScoreWithScores(key, min, max, offset
					, count)));
		}
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrank(key, member)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrank(key, member)));
		}else{
			return execute((cmd)->cmd.zrank(key, member));
		}
	}

	@Override
	public Tuple zPopMax(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zpopmax(key), TUPLE_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zpopmax(key), TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->TUPLE_EXPOSE_CONVERTER.convert(cmd.zpopmax(key)));
		}
	}

	@Override
	public Set<Tuple> zPopMax(final byte[] key, final int count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zpopmax(key, count),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zpopmax(key, count),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zpopmax(key, count)));
		}
	}

	@Override
	public Tuple zPopMin(final byte[] key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zpopmin(key), TUPLE_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zpopmin(key), TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->TUPLE_EXPOSE_CONVERTER.convert(cmd.zpopmin(key)));
		}
	}

	@Override
	public Set<Tuple> zPopMin(final byte[] key, final int count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zpopmin(key, count),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zpopmin(key, count),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zpopmin(key, count)));
		}
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrem(key, members)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrem(key, members)));
		}else{
			return execute((cmd)->cmd.zrem(key, members));
		}
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zremrangeByLex(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zremrangeByLex(key, min, max)));
		}else{
			return execute((cmd)->cmd.zremrangeByLex(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zremrangeByScore(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zremrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zremrangeByScore(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zremrangeByScore(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zremrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zremrangeByScore(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zremrangeByRank(key, start, end)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zremrangeByRank(key, start, end)));
		}else{
			return execute((cmd)->cmd.zremrangeByRank(key, start, end));
		}
	}

	@Override
	public Set<byte[]> zRevRange(final byte[] key, final long start, final long end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrange(key, start, end)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrange(key, start, end)));
		}else{
			return execute((cmd)->cmd.zrevrange(key, start, end));
		}
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrangeWithScores(key, start, end),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeWithScores(key, start, end),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeWithScores(key, start, end)));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrangeByLex(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByLex(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrevrangeByLex(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrangeByLex(key, min, max, offset, count)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByLex(key, min, max, offset,
					count)));
		}else{
			return execute((cmd)->cmd.zrevrangeByLex(key, min, max, offset, count));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrangeByScore(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrangeByScore(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrangeByScore(key, min, max, offset,
					count)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScore(key, min, max, offset,
					count)));
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrangeByScore(key, min, max, offset,
					count)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScore(key, min, max, offset,
					count)));
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrangeByScoreWithScores(key, min, max),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScoreWithScores(key, min, max)
					, SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrangeByScoreWithScores(key, min, max),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScoreWithScores(key, min, max)
					, SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
			final int offset, final int count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrangeByScoreWithScores(key, min, max,
					offset, count), SET_TUPLE_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScoreWithScores(key, min, max,
					offset, count), SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeByScoreWithScores(key, min, max,
					offset, count)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
			final int offset, final int count){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrangeByScoreWithScores(key, min, max,
					offset, count), SET_TUPLE_EXPOSE_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScoreWithScores(key, min, max,
					offset, count), SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeByScoreWithScores(key, min, max,
					offset, count)));
		}
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrank(key, member)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrank(key, member)));
		}else{
			return execute((cmd)->cmd.zrevrank(key, member));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.ZSCAN);
		return execute((cmd)->LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.zscan(key, cursor)));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.ZSCAN);
		return execute((cmd)->LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.zscan(key, cursor,
				new JedisScanParams(pattern))));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.ZSCAN);
		return execute((cmd)->LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.zscan(key, cursor,
				new JedisScanParams(count))));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.ZSCAN);
		return execute((cmd)->LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.zscan(key, cursor,
				new JedisScanParams(pattern, count))));
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zscore(key, member)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zscore(key, member)));
		}else{
			return execute((cmd)->cmd.zscore(key, member));
		}
	}

	@Override
	public Long zUnionStore(String destKey, String... keys){
		commandAllNotSupportedException(ProtocolCommand.ZUNIONSTORE);
		return null;
	}

	@Override
	public Long zUnionStore(byte[] destKey, byte[]... keys){
		commandAllNotSupportedException(ProtocolCommand.ZUNIONSTORE);
		return null;
	}

	@Override
	public Long zUnionStore(String destKey, Aggregate aggregate, String... keys){
		commandAllNotSupportedException(ProtocolCommand.ZUNIONSTORE);
		return null;
	}

	@Override
	public Long zUnionStore(byte[] destKey, Aggregate aggregate, byte[]... keys){
		commandAllNotSupportedException(ProtocolCommand.ZUNIONSTORE);
		return null;
	}

	@Override
	public Long zUnionStore(String destKey, double[] weights, String... keys){
		commandAllNotSupportedException(ProtocolCommand.ZUNIONSTORE);
		return null;
	}

	@Override
	public Long zUnionStore(byte[] destKey, double[] weights, byte[]... keys){
		commandAllNotSupportedException(ProtocolCommand.ZUNIONSTORE);
		return null;
	}

	@Override
	public Long zUnionStore(String destKey, Aggregate aggregate, double[] weights, String... keys){
		commandAllNotSupportedException(ProtocolCommand.ZUNIONSTORE);
		return null;
	}

	@Override
	public Long zUnionStore(byte[] destKey, Aggregate aggregate, double[] weights, byte[]... keys){
		commandAllNotSupportedException(ProtocolCommand.ZUNIONSTORE);
		return null;
	}

}
