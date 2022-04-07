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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.core.utils.NumberUtils;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.SortedSetOperations;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.KeyedZSetElementConverter;
import com.buession.redis.core.internal.convert.jedis.ScanResultConverter;
import com.buession.redis.core.internal.convert.jedis.TupleConverter;
import com.buession.redis.core.internal.jedis.JedisScanParams;
import com.buession.redis.exception.RedisExceptionUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Jedis 有序集合命令操作抽象类
 *
 * @param <CMD>
 * 		Jedis 原始命令对象
 *
 * @author Yong.Teng
 */
public abstract class AbstractSortedSetOperations<CMD> extends AbstractJedisRedisOperations<CMD>
		implements SortedSetOperations<CMD> {

	protected final static KeyedZSetElementConverter.KeyedZSetElementExposeConverter KEYED_Z_SET_ELEMENT_EXPOSE_CONVERTER = new KeyedZSetElementConverter.KeyedZSetElementExposeConverter();

	public AbstractSortedSetOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Number> members){
		final Map<String, Double> data = new LinkedHashMap<>(members.size());

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
	public Long zCard(final String key){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zcard(key)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zcard(key)));
		}else{
			return execute((C cmd)->cmd.zcard(key));
		}
	}

	@Override
	public Long zCount(final String key, final double min, final double max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zcount(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zcount(key, min, max)));
		}else{
			return execute((cmd)->cmd.zcount(key, min, max));
		}
	}

	@Override
	public Long zCount(final String key, final long min, final long max){
		return zCount(key, (double) min, (double) max);
	}

	@Override
	public Long zCount(final byte[] key, final long min, final long max){
		return zCount(key, (double) min, (double) max);
	}

	@Override
	public Long zCount(final String key, final String min, final String max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zcount(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zcount(key, min, max)));
		}else{
			return execute((cmd)->cmd.zcount(key, min, max));
		}
	}

	@Override
	public Double zIncrBy(final String key, final String member, final double increment){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zincrby(key, increment, member)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zincrby(key, increment, member)));
		}else{
			return execute((cmd)->cmd.zincrby(key, increment, member));
		}
	}

	@Override
	public Double zIncrBy(final String key, final String member, final long increment){
		return zIncrBy(key, member, (double) increment);
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final long increment){
		return zIncrBy(key, member, (double) increment);
	}

	@Override
	public Long zLexCount(final String key, final double min, final double max){
		return zLexCount(key, Double.toString(min), Double.toString(max));
	}

	@Override
	public Long zLexCount(final byte[] key, final double min, final double max){
		return zLexCount(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
	}

	@Override
	public Long zLexCount(final String key, final long min, final long max){
		return zLexCount(key, Long.toString(min), Long.toString(max));
	}

	@Override
	public Long zLexCount(final byte[] key, final long min, final long max){
		return zLexCount(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max));
	}

	@Override
	public Long zLexCount(final String key, final String min, final String max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zlexcount(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zlexcount(key, min, max)));
		}else{
			return execute((cmd)->cmd.zlexcount(key, min, max));
		}
	}

	@Override
	public Set<String> zRange(final String key, final long start, final long end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrange(key, start, end)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrange(key, start, end)));
		}else{
			return execute((cmd)->cmd.zrange(key, start, end));
		}
	}

	@Override
	public Set<Tuple> zRangeWithScores(final String key, final long start, final long end){
		final TupleConverter.SetTupleExposeConverter converter = new TupleConverter.SetTupleExposeConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrangeWithScores(key, start, end), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeWithScores(key, start, end),
					converter));
		}else{
			return execute((cmd)->cmd.zrangeWithScores(key, start, end), converter);
		}
	}

	@Override
	public Set<String> zRangeByLex(final String key, final double min, final double max){
		return zRangeByLex(key, Double.toString(min), Double.toString(max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max){
		return zRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final long min, final long max){
		return zRangeByLex(key, Long.toString(min), Long.toString(max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max){
		return zRangeByLex(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrangeByLex(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByLex(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrangeByLex(key, min, max));
		}
	}

	@Override
	public Set<String> zRangeByLex(final String key, final double min, final double max, final long offset,
								   final long count){
		return zRangeByLex(key, Double.toString(min), Double.toString(max), offset, count);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final long offset,
								   final long count){
		return zRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max), offset, count);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final long min, final long max, final long offset,
								   final long count){
		return zRangeByLex(key, Long.toString(min), Long.toString(max), offset, count);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max, final long offset,
								   final long count){
		return zRangeByLex(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max), offset, count);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max, final long offset,
								   final long count){
		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().zrangeByLex(key, min, max, (int) offset, (int) count)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByLex(key, min, max, (int) offset,
					(int) count)));
		}else{
			return execute((cmd)->cmd.zrangeByLex(key, min, max, (int) offset, (int) count));
		}
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<String> zRangeByScore(final String key, final long min, final long max){
		return zRangeByScore(key, (double) min, (double) max);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max){
		return zRangeByScore(key, (double) min, (double) max);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrangeByScore(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max, final long offset,
									 final long count){
		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().zrangeByScore(key, min, max, (int) offset, (int) count)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScore(key, min, max, (int) offset,
					(int) count)));
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count));
		}
	}

	@Override
	public Set<String> zRangeByScore(final String key, final long min, final long max, final long offset,
									 final long count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max, final long offset,
									 final long count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max, final long offset,
									 final long count){
		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().zrangeByScore(key, min, max, (int) offset, (int) count)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScore(key, min, max, (int) offset,
					(int) count)));
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max, (int) offset, (int) count));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max){
		final TupleConverter.SetTupleExposeConverter converter = new TupleConverter.SetTupleExposeConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrangeByScoreWithScores(key, min, max),
					converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScoreWithScores(key, min, max),
					converter));
		}else{
			return execute((cmd)->cmd.zrangeByScoreWithScores(key, min, max), converter);
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max){
		final TupleConverter.SetTupleExposeConverter converter = new TupleConverter.SetTupleExposeConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrangeByScoreWithScores(key, min, max),
					converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScoreWithScores(key, min, max),
					converter));
		}else{
			return execute((cmd)->cmd.zrangeByScoreWithScores(key, min, max), converter);
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final long offset,
											  final long count){
		final TupleConverter.SetTupleExposeConverter converter = new TupleConverter.SetTupleExposeConverter();

		if(isPipeline()){
			return pipelineExecute(
					(cmd)->newJedisResult(getPipeline().zrangeByScoreWithScores(key, min, max, (int) offset,
							(int) count), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScoreWithScores(key, min, max,
					(int) offset, (int) count), converter));
		}else{
			return execute((cmd)->cmd.zrangeByScoreWithScores(key, min, max, (int) offset, (int) count), converter);
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max, final long offset,
											  final long count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max, final long offset,
											  final long count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final long offset,
											  final long count){
		final TupleConverter.SetTupleExposeConverter converter = new TupleConverter.SetTupleExposeConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrangeByScoreWithScores(key, min, max, offset,
					count), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScoreWithScores(key, min, max,
					offset, count), converter));
		}else{
			return execute((cmd)->cmd.zrangeByScoreWithScores(key, min, max, offset, count), converter);
		}
	}

	@Override
	public Long zRank(final String key, final String member){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrank(key, member)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrank(key, member)));
		}else{
			return execute((cmd)->cmd.zrank(key, member));
		}
	}

	@Override
	public Tuple zPopMax(final String key){
		final TupleConverter.TupleExposeConverter converter = new TupleConverter.TupleExposeConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zpopmax(key), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zpopmax(key), converter));
		}else{
			return execute((cmd)->cmd.zpopmax(key), converter);
		}
	}

	@Override
	public Set<Tuple> zPopMax(final String key, final long count){
		return zPopMax(key, (int) count);
	}

	@Override
	public Set<Tuple> zPopMax(final byte[] key, final long count){
		return zPopMax(key, (int) count);
	}

	@Override
	public Tuple zPopMin(final String key){
		final TupleConverter.TupleExposeConverter converter = new TupleConverter.TupleExposeConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zpopmin(key), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zpopmin(key), converter));
		}else{
			return execute((cmd)->cmd.zpopmin(key), converter);
		}
	}

	@Override
	public Set<Tuple> zPopMin(final String key, final long count){
		return zPopMin(key, (int) count);
	}

	@Override
	public Set<Tuple> zPopMin(final byte[] key, final long count){
		return zPopMin(key, (int) count);
	}

	@Override
	public Long zRem(final String key, final String... members){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrem(key, members)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrem(key, members)));
		}else{
			return execute((cmd)->cmd.zrem(key, members));
		}
	}

	@Override
	public Long zRemRangeByLex(final String key, final double min, final double max){
		return zRemRangeByLex(key, Double.toString(min), Double.toString(max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final double min, final double max){
		return zRemRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final long min, final long max){
		return zRemRangeByLex(key, Long.toString(min), Long.toString(max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final long min, final long max){
		return zRemRangeByLex(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final String min, final String max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zremrangeByLex(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zremrangeByLex(key, min, max)));
		}else{
			return execute((cmd)->cmd.zremrangeByLex(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zremrangeByScore(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zremrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zremrangeByScore(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByScore(final String key, final long min, final long max){
		return zRemRangeByScore(key, (double) min, (double) max);
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final long min, final long max){
		return zRemRangeByScore(key, (double) min, (double) max);
	}

	@Override
	public Long zRemRangeByScore(final String key, final String min, final String max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zremrangeByScore(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zremrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zremrangeByScore(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zremrangeByRank(key, start, end)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zremrangeByRank(key, start, end)));
		}else{
			return execute((cmd)->cmd.zremrangeByRank(key, start, end));
		}
	}

	@Override
	public Set<String> zRevRange(final String key, final long start, final long end){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrange(key, start, end)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrange(key, start, end)));
		}else{
			return execute((cmd)->cmd.zrevrange(key, start, end));
		}
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final String key, final long start, final long end){
		final TupleConverter.SetTupleExposeConverter converter = new TupleConverter.SetTupleExposeConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrangeWithScores(key, start, end),
					converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeWithScores(key, start, end),
					converter));
		}else{
			return execute((cmd)->cmd.zrevrangeWithScores(key, start, end), converter);
		}
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final double min, final double max){
		return zRevRangeByLex(key, Double.toString(min), Double.toString(max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max){
		return zRevRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final long min, final long max){
		return zRevRangeByLex(key, Long.toString(min), Long.toString(max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max){
		return zRevRangeByLex(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrangeByLex(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByLex(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrevrangeByLex(key, min, max));
		}
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final double min, final double max, final long offset,
									  final long count){
		return zRevRangeByLex(key, Double.toString(min), Double.toString(max), offset, count);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final long offset,
									  final long count){
		return zRevRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max), offset, count);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final long min, final long max, final long offset,
									  final long count){
		return zRevRangeByLex(key, Long.toString(min), Long.toString(max), offset, count);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max, final long offset,
									  final long count){
		return zRevRangeByLex(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max), offset, count);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max, final long offset,
									  final long count){
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
	public Set<String> zRevRangeByScore(final String key, final double min, final double max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final long min, final long max){
		return zRevRangeByScore(key, (double) min, (double) max);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max){
		return zRevRangeByScore(key, (double) min, (double) max);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrangeByScore(key, min, max)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max, final long offset,
										final long count){
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
	public Set<String> zRevRangeByScore(final String key, final long min, final long max, final long offset,
										final long count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max, final long offset,
										final long count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max, final long offset,
										final long count){
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
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max){
		final TupleConverter.SetTupleExposeConverter converter = new TupleConverter.SetTupleExposeConverter();

		if(isTransaction()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrangeByScoreWithScores(key, min, max),
					converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScoreWithScores(key, min, max)
					, converter));
		}else{
			return execute((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), converter);
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max){
		final TupleConverter.SetTupleExposeConverter converter = new TupleConverter.SetTupleExposeConverter();

		if(isTransaction()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrangeByScoreWithScores(key, min, max),
					converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScoreWithScores(key, min, max)
					, converter));
		}else{
			return execute((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max), converter);
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
												 final long offset,
												 final long count){
		return zRevRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
												 final long offset,
												 final long count){
		return zRevRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max, final long offset,
												 final long count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max, final long offset,
												 final long count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max,
												 final long offset, final long count){
		final TupleConverter.SetTupleExposeConverter converter = new TupleConverter.SetTupleExposeConverter();

		if(isTransaction()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrangeByScoreWithScores(key, min, max,
					offset, count), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScoreWithScores(key, min, max,
					offset, count), converter));
		}else{
			return execute((cmd)->cmd.zrevrangeByScoreWithScores(key, min, max, offset, count), converter);
		}
	}

	@Override
	public Long zRevRank(final String key, final String member){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zrevrank(key, member)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrank(key, member)));
		}else{
			return execute((cmd)->cmd.zrevrank(key, member));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor){
		return zScan(key, Long.toString(cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor){
		return zScan(key, NumberUtils.long2bytes(cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.ZSCAN,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute(
				(cmd)->new ScanResultConverter.ListTupleScanResultExposeConverter().convert(cmd.zscan(key, cursor)));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern){
		return zScan(key, Long.toString(cursor), pattern);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern){
		return zScan(key, NumberUtils.long2bytes(cursor), pattern);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.ZSCAN,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute(
				(cmd)->new ScanResultConverter.ListTupleScanResultExposeConverter().convert(cmd.zscan(key, cursor,
						new JedisScanParams(pattern))));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final long count){
		return zScan(key, Long.toString(cursor), Long.toString(count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final long count){
		return zScan(key, cursor, NumberUtils.long2bytes(count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final long count){
		return zScan(key, NumberUtils.long2bytes(cursor), NumberUtils.long2bytes(count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final long count){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.ZSCAN,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute(
				(cmd)->new ScanResultConverter.ListTupleScanResultExposeConverter().convert(cmd.zscan(key, cursor,
						new JedisScanParams((int) count))));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final long count){
		return zScan(key, Long.toString(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern, final long count){
		return zScan(key, NumberUtils.long2bytes(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final long count){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.ZSCAN,
				CommandNotSupported.PIPELINE | CommandNotSupported.TRANSACTION, client.getConnection());
		return execute(
				(cmd)->new ScanResultConverter.ListTupleScanResultExposeConverter().convert(cmd.zscan(key, cursor,
						new JedisScanParams(pattern, (int) count))));
	}

	@Override
	public Double zScore(final String key, final String member){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().zscore(key, member)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zscore(key, member)));
		}else{
			return execute((cmd)->cmd.zscore(key, member));
		}
	}

}
