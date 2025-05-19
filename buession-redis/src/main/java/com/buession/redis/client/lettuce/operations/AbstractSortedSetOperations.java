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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.lettuce.operations;

import com.buession.core.utils.NumberUtils;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.client.operations.SortedSetOperations;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.ScoredValue;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 有序集合命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link LettuceRedisClient}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public abstract class AbstractSortedSetOperations<C extends LettuceRedisClient>
		extends AbstractLettuceRedisOperations<C> implements SortedSetOperations {

	public AbstractSortedSetOperations(final C client) {
		super(client);
	}

	@Override
	public Tuple zPopMin(final String key) {
		return zPopMin(SafeEncoder.encode(key));
	}

	@Override
	public List<Tuple> zPopMin(final String key, final long count) {
		return zPopMin(SafeEncoder.encode(key), count);
	}

	@Override
	public Tuple zPopMax(final String key) {
		return zPopMax(SafeEncoder.encode(key));
	}

	@Override
	public List<Tuple> zPopMax(final String key, final long count) {
		return zPopMax(SafeEncoder.encode(key), count);
	}

	@Override
	public Long zCard(final String key) {
		return zCard(SafeEncoder.encode(key));
	}

	@Override
	public Long zCount(final String key, final double min, final double max) {
		return zCount(SafeEncoder.encode(key), min, max);
	}

	@Deprecated
	@Override
	public Long zCount(final String key, final String min, final String max) {
		return zCount(key, Double.parseDouble(min), Double.parseDouble(max));
	}

	@Deprecated
	@Override
	public Long zCount(final byte[] key, final byte[] min, final byte[] max) {
		return zCount(key, NumberUtils.bytes2double(min), NumberUtils.bytes2double(max));
	}

	@Override
	public Double zIncrBy(final String key, final double increment, final String member) {
		return zIncrBy(SafeEncoder.encode(key), increment, SafeEncoder.encode(member));
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys) {
		return zInterStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys));
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		return zInterStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys), aggregate);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final double... weights) {
		return zInterStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys), weights);
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights) {
		return zInterStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys), aggregate, weights);
	}

	@Override
	public Long zLexCount(final String key, final double min, final double max) {
		return zLexCount(SafeEncoder.encode(key), NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
	}

	@Override
	public Long zLexCount(final byte[] key, final double min, final double max) {
		return zLexCount(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
	}

	@Deprecated
	@Override
	public Long zLexCount(final String key, final String min, final String max) {
		return zLexCount(SafeEncoder.encode(key), SafeEncoder.encode(min), SafeEncoder.encode(max));
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end) {
		return zRangeWithScores(SafeEncoder.encode(key), start, end);
	}

	@Deprecated
	@Override
	public List<String> zRangeByScore(final String key, final String min, final String max) {
		return zRangeByScore(key, Double.parseDouble(min), Double.parseDouble(max));
	}

	@Deprecated
	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max) {
		return zRangeByScore(key, NumberUtils.bytes2double(min), NumberUtils.bytes2double(max));
	}

	@Deprecated
	@Override
	public List<String> zRangeByScore(final String key, final String min, final String max, final long offset,
									  final long count) {
		return zRangeByScore(key, Double.parseDouble(min), Double.parseDouble(max), offset, count);
	}

	@Deprecated
	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final long offset,
									  final long count) {
		return zRangeByScore(key, NumberUtils.bytes2double(min), NumberUtils.bytes2double(max), offset, count);
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max) {
		return zRangeByScoreWithScores(SafeEncoder.encode(key), min, max);
	}

	@Deprecated
	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max) {
		return zRangeByScoreWithScores(key, Double.parseDouble(min), Double.parseDouble(max));
	}

	@Deprecated
	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max) {
		return zRangeByScoreWithScores(key, NumberUtils.bytes2double(min), NumberUtils.bytes2double(max));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final long offset,
											   final long count) {
		return zRangeByScoreWithScores(SafeEncoder.encode(key), min, max, offset, count);
	}

	@Deprecated
	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final long offset,
											   final long count) {
		return zRangeByScoreWithScores(key, Double.parseDouble(min), Double.parseDouble(max), offset, count);
	}

	@Deprecated
	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final long offset,
											   final long count) {
		return zRangeByScoreWithScores(key, NumberUtils.bytes2double(min), NumberUtils.bytes2double(max), offset,
				count);
	}

	@Override
	public Long zRank(final String key, final String member) {
		return zRank(SafeEncoder.encode(key), SafeEncoder.encode(member));
	}

	@Override
	public Long zRem(final String key, final String... members) {
		return zRem(SafeEncoder.encode(key), SafeEncoder.encode(members));
	}

	@Override
	public Long zRemRangeByLex(final String key, final double min, final double max) {
		return zRemRangeByLex(SafeEncoder.encode(key), NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final double min, final double max) {
		return zRemRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
	}

	@Deprecated
	@Override
	public Long zRemRangeByLex(final String key, final String min, final String max) {
		return zRemRangeByLex(SafeEncoder.encode(key), SafeEncoder.encode(min), SafeEncoder.encode(max));
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max) {
		return zRemRangeByScore(SafeEncoder.encode(key), min, max);
	}

	@Deprecated
	@Override
	public Long zRemRangeByScore(final String key, final String min, final String max) {
		return zRemRangeByScore(key, Double.parseDouble(min), Double.parseDouble(max));
	}

	@Deprecated
	@Override
	public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max) {
		return zRemRangeByScore(key, NumberUtils.bytes2double(min), NumberUtils.bytes2double(max));
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end) {
		return zRemRangeByRank(SafeEncoder.encode(key), start, end);
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final String key, final long start, final long end) {
		return zRevRangeWithScores(SafeEncoder.encode(key), start, end);
	}

	@Deprecated
	@Override
	public List<String> zRevRangeByScore(final String key, final String min, final String max) {
		return zRevRangeByScore(key, Double.parseDouble(min), Double.parseDouble(max));
	}

	@Deprecated
	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max) {
		return zRevRangeByScore(key, NumberUtils.bytes2double(min), NumberUtils.bytes2double(max));
	}

	@Deprecated
	@Override
	public List<String> zRevRangeByScore(final String key, final String min, final String max, final long offset,
										 final long count) {
		return zRevRangeByScore(key, Double.parseDouble(min), Double.parseDouble(max), offset, count);
	}

	@Deprecated
	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final long offset,
										 final long count) {
		return zRevRangeByScore(key, NumberUtils.bytes2double(min), NumberUtils.bytes2double(max), offset, count);
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max) {
		return zRevRangeByScoreWithScores(SafeEncoder.encode(key), min, max);
	}

	@Deprecated
	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max) {
		return zRevRangeByScoreWithScores(SafeEncoder.encode(key), Double.parseDouble(min), Double.parseDouble(max));
	}

	@Deprecated
	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max) {
		return zRevRangeByScoreWithScores(key, NumberUtils.bytes2double(min), NumberUtils.bytes2double(max));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
												  final long offset, final long count) {
		return zRevRangeByScoreWithScores(SafeEncoder.encode(key), min, max, offset, count);
	}

	@Deprecated
	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max,
												  final long offset, final long count) {
		return zRevRangeByScoreWithScores(SafeEncoder.encode(key), Double.parseDouble(min), Double.parseDouble(max),
				offset, count);
	}

	@Deprecated
	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
												  final long offset, final long count) {
		return zRevRangeByScoreWithScores(key, NumberUtils.bytes2double(min), NumberUtils.bytes2double(max), offset,
				count);
	}

	@Override
	public Long zRevRank(final String key, final String member) {
		return zRevRank(SafeEncoder.encode(key), SafeEncoder.encode(member));
	}


	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor) {
		return zScan(key, Long.toString(cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor) {
		return zScan(key, NumberUtils.long2bytes(cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern) {
		return zScan(key, Long.toString(cursor), pattern);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern) {
		return zScan(key, NumberUtils.long2bytes(cursor), pattern);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final long count) {
		return zScan(key, Long.toString(cursor), Long.toString(count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final long count) {
		return zScan(key, NumberUtils.long2bytes(cursor), NumberUtils.long2bytes(count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final long count) {
		return zScan(key, Long.toString(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern, final long count) {
		return zScan(key, NumberUtils.long2bytes(cursor), pattern, count);
	}

	@Override
	public Double zScore(final String key, final String member) {
		return zScore(SafeEncoder.encode(key), SafeEncoder.encode(member));
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys) {
		return zUnionStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys));
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		return zUnionStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys), aggregate);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final double... weights) {
		return zUnionStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys), weights);
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights) {
		return zUnionStore(SafeEncoder.encode(destKey), SafeEncoder.encode(keys), aggregate, weights);
	}

	@SuppressWarnings({"unchecked"})
	protected static ScoredValue<byte[]>[] createScoredValueFromValues(final Map<String, Double> values) {
		if(values == null){
			return null;
		}else{
			final ScoredValue<byte[]>[] result = new ScoredValue[values.size()];
			int i = 0;

			for(Map.Entry<String, Double> e : values.entrySet()){
				result[i++] = e.getValue() == null ? ScoredValue.empty() : ScoredValue.just(e.getValue(),
						SafeEncoder.encode(e.getKey()));
			}

			return result;
		}
	}

	@SuppressWarnings({"unchecked"})
	protected static ScoredValue<byte[]>[] createScoredValueFromBinaryValues(final Map<byte[], Double> values) {
		if(values == null){
			return null;
		}else{
			final ScoredValue<byte[]>[] result = new ScoredValue[values.size()];
			int i = 0;

			for(Map.Entry<byte[], Double> e : values.entrySet()){
				result[i++] = e.getValue() == null ? ScoredValue.empty() : ScoredValue.just(e.getValue(), e.getKey());
			}

			return result;
		}
	}

}
