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

import com.buession.lang.KeyValue;
import com.buession.redis.client.operations.SortedSetRedisOperations;
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
public interface JedisSortedSetRedisOperations extends SortedSetRedisOperations {

	@Override
	default Long zAdd(final String key, final List<KeyValue<String, Number>> members){
		final Map<String, Number> data = members.stream().collect(Collectors.toMap(KeyValue::getKey,
				KeyValue::getValue, (oldVal, currVal)->currVal, LinkedHashMap::new));
		return zAdd(key, data);
	}

	@Override
	default Double zIncrBy(final String key, final String member, final float increment){
		return zIncrBy(key, member, (double) increment);
	}

	@Override
	default Double zIncrBy(final String key, final String member, final int increment){
		return zIncrBy(key, member, (double) increment);
	}

	@Override
	default Double zIncrBy(final String key, final String member, final long increment){
		return zIncrBy(key, member, (double) increment);
	}

	@Override
	default Long zCount(final String key, final float min, final float max){
		return zCount(key, (double) min, (double) max);
	}

	@Override
	default Long zCount(final String key, final int min, final int max){
		return zCount(key, (double) min, (double) max);
	}

	@Override
	default Long zCount(final String key, final long min, final long max){
		return zCount(key, (double) min, (double) max);
	}

	@Override
	default Set<String> zRange(final String key, final int start, final int end){
		return zRange(key, (long) start, (long) end);
	}

	@Override
	default Set<Tuple> zRangeWithScores(final String key, final int start, final int end){
		return zRangeWithScores(key, (long) start, (long) end);
	}

	@Override
	default Set<String> zRangeByScore(final String key, final float min, final float max){
		return zRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<String> zRangeByScore(final String key, final int min, final int max){
		return zRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<String> zRangeByScore(final String key, final long min, final long max){
		return zRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<String> zRangeByScore(final String key, final float min, final float max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<String> zRangeByScore(final String key, final int min, final int max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<String> zRangeByScore(final String key, final long min, final long max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<String> zRangeByLex(final String key, final float min, final float max){
		return zRangeByLex(key, Float.toString(min), Float.toString(max));
	}

	@Override
	default Set<String> zRangeByLex(final String key, final double min, final double max){
		return zRangeByLex(key, Double.toString(min), Double.toString(max));
	}

	@Override
	default Set<String> zRangeByLex(final String key, final int min, final int max){
		return zRangeByLex(key, Integer.toString(min), Integer.toString(max));
	}

	@Override
	default Set<String> zRangeByLex(final String key, final long min, final long max){
		return zRangeByLex(key, Long.toString(min), Long.toString(max));
	}

	@Override
	default Set<String> zRangeByLex(final String key, final float min, final float max, final int offset,
			final int count){
		return zRangeByLex(key, Float.toString(min), Float.toString(max), offset, count);
	}

	@Override
	default Set<String> zRangeByLex(final String key, final double min, final double max, final int offset,
			final int count){
		return zRangeByLex(key, Double.toString(min), Double.toString(max), offset, count);
	}

	@Override
	default Set<String> zRangeByLex(final String key, final int min, final int max, final int offset, final int count){
		return zRangeByLex(key, Integer.toString(min), Integer.toString(max), offset, count);
	}

	@Override
	default Set<String> zRangeByLex(final String key, final long min, final long max, final int offset,
			final int count){
		return zRangeByLex(key, Long.toString(min), Long.toString(max), offset, count);
	}

	@Override
	default Long zRemRangeByRank(final String key, final int start, final int end){
		return zRemRangeByRank(key, (long) start, (long) end);
	}

	@Override
	default Long zRemRangeByScore(final String key, final float min, final float max){
		return zRemRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Long zRemRangeByScore(final String key, final int min, final int max){
		return zRemRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Long zRemRangeByScore(final String key, final long min, final long max){
		return zRemRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Long zRemRangeByLex(final String key, final float min, final float max){
		return zRemRangeByLex(key, Float.toString(min), Float.toString(max));
	}

	@Override
	default Long zRemRangeByLex(final String key, final double min, final double max){
		return zRemRangeByLex(key, Double.toString(min), Double.toString(max));
	}

	@Override
	default Long zRemRangeByLex(final String key, final int min, final int max){
		return zRemRangeByLex(key, Integer.toString(min), Integer.toString(max));
	}

	@Override
	default Long zRemRangeByLex(final String key, final long min, final long max){
		return zRemRangeByLex(key, Long.toString(min), Long.toString(max));
	}

	@Override
	default Set<String> zRevRange(final String key, final int start, final int end){
		return zRevRange(key, (long) start, (long) end);
	}

	@Override
	default Set<Tuple> zRevRangeWithScores(final String key, final int start, final int end){
		return zRevRangeWithScores(key, (long) start, (long) end);
	}

	@Override
	default Set<String> zRevRangeByScore(final String key, final float min, final float max){
		return zRevRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<String> zRevRangeByScore(final String key, final int min, final int max){
		return zRevRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<String> zRevRangeByScore(final String key, final long min, final long max){
		return zRevRangeByScore(key, (double) min, (double) max);
	}

	@Override
	default Set<String> zRevRangeByScore(final String key, final float min, final float max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<String> zRevRangeByScore(final String key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<String> zRevRangeByScore(final String key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final String key, final float min, final float max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final String key, final float min, final float max, final int offset
			, final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	default Set<String> zRevRangeByLex(final String key, final float min, final float max){
		return zRevRangeByLex(key, Float.toString(min), Float.toString(max));
	}

	@Override
	default Set<String> zRevRangeByLex(final String key, final double min, final double max){
		return zRevRangeByLex(key, Double.toString(min), Double.toString(max));
	}

	@Override
	default Set<String> zRevRangeByLex(final String key, final int min, final int max){
		return zRevRangeByLex(key, Integer.toString(min), Integer.toString(max));
	}

	@Override
	default Set<String> zRevRangeByLex(final String key, final long min, final long max){
		return zRevRangeByLex(key, Long.toString(min), Long.toString(max));
	}

	@Override
	default Set<String> zRevRangeByLex(final String key, final float min, final float max, final int offset,
			final int count){
		return zRevRangeByLex(key, Float.toString(min), Float.toString(max), offset, count);
	}

	@Override
	default Set<String> zRevRangeByLex(final String key, final double min, final double max, final int offset,
			final int count){
		return zRevRangeByLex(key, Double.toString(min), Double.toString(max), offset, count);
	}

	@Override
	default Set<String> zRevRangeByLex(final String key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByLex(key, Integer.toString(min), Integer.toString(max), offset, count);
	}

	@Override
	default Set<String> zRevRangeByLex(final String key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByLex(key, Long.toString(min), Long.toString(max), offset, count);
	}

	@Override
	default Long zLexCount(final String key, final float min, final float max){
		return zLexCount(key, Float.toString(min), Float.toString(max));
	}

	@Override
	default Long zLexCount(final String key, final double min, final double max){
		return zLexCount(key, Double.toString(min), Double.toString(max));
	}

	@Override
	default Long zLexCount(final String key, final int min, final int max){
		return zLexCount(key, Integer.toString(min), Integer.toString(max));
	}

	@Override
	default Long zLexCount(final String key, final long min, final long max){
		return zLexCount(key, Long.toString(min), Long.toString(max));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final int cursor){
		return zScan(key, Integer.toString(cursor));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final long cursor){
		return zScan(key, Long.toString(cursor));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern){
		return zScan(key, Integer.toString(cursor), pattern);
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern){
		return zScan(key, Long.toString(cursor), pattern);
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final int cursor, final int count){
		return zScan(key, Integer.toString(cursor), Integer.toString(count));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final long cursor, final int count){
		return zScan(key, Long.toString(cursor), Long.toString(count));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern, final int count){
		return zScan(key, Integer.toString(cursor), pattern, count);
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final int count){
		return zScan(key, Long.toString(cursor), pattern, count);
	}

}
