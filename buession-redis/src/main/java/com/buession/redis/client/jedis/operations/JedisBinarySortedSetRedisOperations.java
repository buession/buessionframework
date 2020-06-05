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

import com.buession.core.utils.NumberUtils;
import com.buession.lang.KeyValue;
import com.buession.redis.client.operations.BinarySortedSetRedisOperations;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 */
public interface JedisBinarySortedSetRedisOperations extends BinarySortedSetRedisOperations {

	@Override
	default Long zAdd(final byte[] key, final List<KeyValue<byte[], Number>> members){
		final Map<byte[], Number> data = members.stream().collect(Collectors.toMap(KeyValue::getKey,
				KeyValue::getValue, (oldVal, currVal)->currVal, LinkedHashMap::new));
		return zAdd(key, data);
	}

	@Override
	default Double zIncrBy(final byte[] key, final byte[] member, final float increment){
		return zIncrBy(key, member, (double) increment);
	}

	@Override
	default Double zIncrBy(final byte[] key, final byte[] member, final int increment){
		return zIncrBy(key, member, (double) increment);
	}

	@Override
	default Double zIncrBy(final byte[] key, final byte[] member, final long increment){
		return zIncrBy(key, member, (double) increment);
	}

	@Override
	default Long zCount(final byte[] key, final float min, final float max){
		return zCount(key, (double) min, (double) max);
	}

	@Override
	default Long zCount(final byte[] key, final int min, final int max){
		return zCount(key, (double) min, (double) max);
	}

	@Override
	default Long zCount(final byte[] key, final long min, final long max){
		return zCount(key, (double) min, (double) max);
	}

	@Override
	default Set<byte[]> zRange(final byte[] key, final int start, final int end){
		return zRange(key, (long) start, (long) end);
	}

	@Override
	default Set<Tuple> zRangeWithScores(final byte[] key, final int start, final int end){
		return zRangeWithScores(key, (long) start, (long) end);
	}

	@Override
	default Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max){
		return zRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<byte[]> zRangeByScore(final byte[] key, final int min, final int max){
		return zRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max){
		return zRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<byte[]> zRangeByScore(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<byte[]> zRangeByLex(final byte[] key, final float min, final float max){
		return zRangeByLex(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max));
	}

	@Override
	default Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max){
		return zRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
	}

	@Override
	default Set<byte[]> zRangeByLex(final byte[] key, final int min, final int max){
		return zRangeByLex(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max));
	}

	@Override
	default Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max){
		return zRangeByLex(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max));
	}

	@Override
	default Set<byte[]> zRangeByLex(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return zRangeByLex(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max), offset, count);
	}

	@Override
	default Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return zRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max), offset, count);
	}

	@Override
	default Set<byte[]> zRangeByLex(final byte[] key, final int min, final int max, final int offset, final int count){
		return zRangeByLex(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max), offset, count);
	}

	@Override
	default Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRangeByLex(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max), offset, count);
	}

	@Override
	default Long zRemRangeByRank(final byte[] key, final int start, final int end){
		return zRemRangeByRank(key, (long) start, (long) end);
	}

	@Override
	default Long zRemRangeByScore(final byte[] key, final float min, final float max){
		return zRemRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Long zRemRangeByScore(final byte[] key, final int min, final int max){
		return zRemRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Long zRemRangeByScore(final byte[] key, final long min, final long max){
		return zRemRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Long zRemRangeByLex(final byte[] key, final float min, final float max){
		return zRemRangeByLex(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max));
	}

	@Override
	default Long zRemRangeByLex(final byte[] key, final double min, final double max){
		return zRemRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
	}

	@Override
	default Long zRemRangeByLex(final byte[] key, final int min, final int max){
		return zRemRangeByLex(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max));
	}

	@Override
	default Long zRemRangeByLex(final byte[] key, final long min, final long max){
		return zRemRangeByLex(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max));
	}

	@Override
	default Set<byte[]> zRevRange(final byte[] key, final int start, final int end){
		return zRevRange(key, (long) start, (long) end);
	}

	@Override
	default Set<Tuple> zRevRangeWithScores(final byte[] key, final int start, final int end){
		return zRevRangeWithScores(key, (long) start, (long) end);
	}

	@Override
	default Set<byte[]> zRevRangeByScore(final byte[] key, final float min, final float max){
		return zRevRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<byte[]> zRevRangeByScore(final byte[] key, final int min, final int max){
		return zRevRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max){
		return zRevRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<byte[]> zRevRangeByScore(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<byte[]> zRevRangeByScore(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final float max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final float max, final int offset
			, final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<byte[]> zRevRangeByLex(final byte[] key, final float min, final float max){
		return zRevRangeByLex(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max));
	}

	@Override
	default Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max){
		return zRevRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
	}

	@Override
	default Set<byte[]> zRevRangeByLex(final byte[] key, final int min, final int max){
		return zRevRangeByLex(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max));
	}

	@Override
	default Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max){
		return zRevRangeByLex(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max));
	}

	@Override
	default Set<byte[]> zRevRangeByLex(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return zRevRangeByLex(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max), offset, count);
	}

	@Override
	default Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return zRevRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max), offset, count);
	}

	@Override
	default Set<byte[]> zRevRangeByLex(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByLex(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max), offset, count);
	}

	@Override
	default Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByLex(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max), offset, count);
	}

	@Override
	default Long zLexCount(final byte[] key, final float min, final float max){
		return zLexCount(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max));
	}

	@Override
	default Long zLexCount(final byte[] key, final double min, final double max){
		return zLexCount(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max));
	}

	@Override
	default Long zLexCount(final byte[] key, final int min, final int max){
		return zLexCount(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max));
	}

	@Override
	default Long zLexCount(final byte[] key, final long min, final long max){
		return zLexCount(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor){
		return zScan(key, NumberUtils.int2bytes(cursor));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor){
		return zScan(key, NumberUtils.long2bytes(cursor));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern){
		return zScan(key, NumberUtils.int2bytes(cursor), pattern);
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern){
		return zScan(key, NumberUtils.long2bytes(cursor), pattern);
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final int count){
		return zScan(key, NumberUtils.int2bytes(cursor), NumberUtils.int2bytes(count));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final int count){
		return zScan(key, NumberUtils.long2bytes(cursor), NumberUtils.long2bytes(count));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
		return zScan(key, NumberUtils.int2bytes(cursor), pattern, count);
	}

	@Override
	default ScanResult<List<Tuple>> zScan(byte[] key, long cursor, byte[] pattern, int count){
		return zScan(key, NumberUtils.long2bytes(cursor), pattern, count);
	}

}
