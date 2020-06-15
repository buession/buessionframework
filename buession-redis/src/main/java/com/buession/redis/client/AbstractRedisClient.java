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
package com.buession.redis.client;

import com.buession.core.Executor;
import com.buession.core.utils.NumberUtils;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.RedisConnectionFactory;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.command.GeoCommands;
import com.buession.redis.exception.RedisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 */
public abstract class AbstractRedisClient implements RedisClient {

	private RedisConnectionFactory connectionFactory;

	private RedisConnection connection;

	private boolean enableTransactionSupport = false;

	private final static Logger logger = LoggerFactory.getLogger(AbstractRedisClient.class);

	public AbstractRedisClient(){
		super();
	}

	public AbstractRedisClient(RedisConnection connection){
		setConnection(connection);
	}

	@Override
	public RedisConnection getConnection(){
		return connection;
	}

	@Override
	public void setConnection(RedisConnection connection){
		this.connection = connection;
		connectionFactory = new RedisConnectionFactory(connection);
	}

	public boolean isEnableTransactionSupport(){
		return getEnableTransactionSupport();
	}

	public boolean getEnableTransactionSupport(){
		return enableTransactionSupport;
	}

	public void setEnableTransactionSupport(boolean enableTransactionSupport){
		this.enableTransactionSupport = enableTransactionSupport;
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius){
		return geoRadius(key, longitude, longitude, radius, GeoUnit.M);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius){
		return geoRadius(key, longitude, longitude, radius, GeoUnit.M);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius, final GeoArgument geoArgument){
		return geoRadius(key, longitude, latitude, radius, GeoUnit.M, geoArgument);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoCommands.GeoArgument geoArgument){
		return geoRadius(key, longitude, latitude, radius, GeoUnit.M, geoArgument);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius){
		return geoRadiusByMember(key, member, radius, GeoUnit.M);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius){
		return geoRadiusByMember(key, member, radius, GeoUnit.M);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoArgument geoArgument){
		return geoRadiusByMember(key, member, radius, GeoUnit.M, geoArgument);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoCommands.GeoArgument geoArgument){
		return geoRadiusByMember(key, member, radius, GeoUnit.M, geoArgument);
	}

	@Override
	public Long hIncrBy(final String key, final String field, final int value){
		return hIncrBy(key, field, (long) value);
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final int value){
		return hIncrBy(key, field, (long) value);
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final float value){
		return hIncrByFloat(key, field, (double) value);
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final float value){
		return hIncrByFloat(key, field, (double) value);
	}

	@Override
	public Long hDecrBy(final String key, final String field, final int value){
		return hDecrBy(key, field, (long) value);
	}

	@Override
	public Long hDecrBy(final byte[] key, final byte[] field, final int value){
		return hDecrBy(key, field, (long) value);
	}

	@Override
	public Long hDecrBy(final String key, final String field, final long value){
		return hIncrBy(key, field, value > 0 ? value * -1 : value);
	}

	@Override
	public Long hDecrBy(final byte[] key, final byte[] field, final long value){
		return hIncrBy(key, field, value > 0 ? value * -1 : value);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor){
		return hScan(key, Integer.toString(cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor){
		return hScan(key, NumberUtils.int2bytes(cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor){
		return hScan(key, Long.toString(cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor){
		return hScan(key, NumberUtils.long2bytes(cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final String pattern){
		return hScan(key, Integer.toString(cursor), pattern);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern){
		return hScan(key, NumberUtils.int2bytes(cursor), pattern);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern){
		return hScan(key, Long.toString(cursor), pattern);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern){
		return hScan(key, NumberUtils.long2bytes(cursor), pattern);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final int count){
		return hScan(key, Integer.toString(cursor), count);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final int count){
		return hScan(key, NumberUtils.int2bytes(cursor), count);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final int count){
		return hScan(key, Long.toString(cursor), count);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final int count){
		return hScan(key, NumberUtils.long2bytes(cursor), count);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final String pattern,
			final int count){
		return hScan(key, Integer.toString(cursor), pattern, count);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern,
			final int count){
		return hScan(key, NumberUtils.int2bytes(cursor), pattern, count);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern,
			final int count){
		return hScan(key, Long.toString(cursor), pattern, count);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern,
			final int count){
		return hScan(key, NumberUtils.long2bytes(cursor), pattern, count);
	}

	@Override
	public String lIndex(final String key, final int index){
		return lIndex(key, (long) index);
	}

	@Override
	public byte[] lIndex(final byte[] key, final int index){
		return lIndex(key, (long) index);
	}

	@Override
	public List<String> lRange(final String key, final int start, final int end){
		return lRange(key, (long) start, (long) end);
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final int start, final int end){
		return lRange(key, (long) start, (long) end);
	}

	@Override
	public Long lRem(final String key, final String value, final int count){
		return lRem(key, value, (long) count);
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final int count){
		return lRem(key, value, (long) count);
	}

	@Override
	public Status lSet(final String key, final int index, final String value){
		return lSet(key, (long) index, value);
	}

	@Override
	public Status lSet(final byte[] key, final int index, final byte[] value){
		return lSet(key, (long) index, value);
	}

	@Override
	public Status lTrim(final String key, final int start, final int end){
		return lTrim(key, (long) start, (long) end);
	}

	@Override
	public Status lTrim(final byte[] key, final int start, final int end){
		return lTrim(key, (long) start, (long) end);
	}

	/*@Override
	public Long incrBy(final String key, final int value){
		return incrBy(key, (long) value);
	}

	@Override
	public Long incrBy(final byte[] key, final int value){
		return incrBy(key, (long) value);
	}

	@Override
	public Double incrByFloat(final String key, final float value){
		return incrByFloat(key, (double) value);
	}

	@Override
	public Double incrByFloat(final byte[] key, final float value){
		return incrByFloat(key, (double) value);
	}

	@Override
	public Long decrBy(final String key, final int value){
		return decrBy(key, (long) value);
	}

	@Override
	public Long decrBy(final byte[] key, final int value){
		return decrBy(key, (long) value);
	}

	@Override
	public Long setRange(final String key, final int offset, final String value){
		return setRange(key, (long) offset, value);
	}

	@Override
	public Long setRange(final byte[] key, final int offset, final byte[] value){
		return setRange(key, (long) offset, value);
	}

	@Override
	public String getRange(final String key, final int start, final int end){
		return getRange(key, (long) start, (long) end);
	}

	@Override
	public byte[] getRange(final byte[] key, final int start, final int end){
		return getRange(key, (long) start, (long) end);
	}

	@Override
	public String substr(final String key, final long start, final long end){
		return substr(key, (int) start, (int) end);
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end){
		return substr(key, (int) start, (int) end);
	}

	@Override
	public List<String> sRandMember(final String key, final long count){
		return sRandMember(key, (int) count);
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final long count){
		return sRandMember(key, (int) count);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor){
		return sScan(key, Integer.toString(cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor){
		return sScan(key, NumberUtils.int2bytes(cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor){
		return sScan(key, Long.toString(cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor){
		return sScan(key, NumberUtils.long2bytes(cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern){
		return sScan(key, Integer.toString(cursor), pattern);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern){
		return sScan(key, NumberUtils.int2bytes(cursor), pattern);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern){
		return sScan(key, Long.toString(cursor), pattern);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern){
		return sScan(key, NumberUtils.long2bytes(cursor), pattern);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final int count){
		return sScan(key, Integer.toString(cursor), count);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final int count){
		return sScan(key, NumberUtils.int2bytes(cursor), count);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final int count){
		return sScan(key, Long.toString(cursor), count);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final int count){
		return sScan(key, NumberUtils.long2bytes(cursor), count);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern, final int count){
		return sScan(key, Integer.toString(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
		return sScan(key, NumberUtils.int2bytes(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern, final int count){
		return sScan(key, Long.toString(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern, final int count){
		return sScan(key, NumberUtils.long2bytes(cursor), pattern, count);
	}

	@Override
	public Long zAdd(final String key, final List<KeyValue<String, Number>> members){
		final Map<String, Number> data = members.stream().collect(Collectors.toMap(KeyValue::getKey,
				KeyValue::getValue, (oldVal, currVal)->currVal, LinkedHashMap::new));
		return zAdd(key, data);
	}

	@Override
	public Long zAdd(final byte[] key, final List<KeyValue<byte[], Number>> members){
		final Map<byte[], Number> data = members.stream().collect(Collectors.toMap(KeyValue::getKey,
				KeyValue::getValue, (oldVal, currVal)->currVal, LinkedHashMap::new));
		return zAdd(key, data);
	}

	@Override
	public Double zIncrBy(final String key, final String member, final float increment){
		return zIncrBy(key, member, (double) increment);
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final float increment){
		return zIncrBy(key, member, (double) increment);
	}

	@Override
	public Double zIncrBy(final String key, final String member, final int increment){
		return zIncrBy(key, member, (double) increment);
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final int increment){
		return zIncrBy(key, member, (double) increment);
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
	public Long zCount(final String key, final float min, final float max){
		return zCount(key, (double) min, (double) max);
	}

	@Override
	public Long zCount(final byte[] key, final float min, final float max){
		return zCount(key, (double) min, (double) max);
	}

	@Override
	public Long zCount(final String key, final int min, final int max){
		return zCount(key, (double) min, (double) max);
	}

	@Override
	public Long zCount(final byte[] key, final int min, final int max){
		return zCount(key, (double) min, (double) max);
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
	public Set<String> zRange(final String key, final int start, final int end){
		return zRange(key, (long) start, (long) end);
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final int start, final int end){
		return zRange(key, (long) start, (long) end);
	}

	@Override
	public Set<Tuple> zRangeWithScores(final String key, final int start, final int end){
		return zRangeWithScores(key, (long) start, (long) end);
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final int start, final int end){
		return zRangeWithScores(key, (long) start, (long) end);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final float min, final float max){
		return zRangeByScore(key, (double) min, (double) max);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max){
		return zRangeByScore(key, (double) min, (double) max);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final int min, final int max){
		return zRangeByScore(key, (double) min, (double) max);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final int min, final int max){
		return zRangeByScore(key, (double) min, (double) max);
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
	public Set<String> zRangeByScore(final String key, final float min, final float max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final int min, final int max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final long min, final long max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max){
		return zRangeByScoreWithScores(key, (double) min, (double) max);
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
	public Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final float min, final float max){
		return zRangeByLex(key, Float.toString(min), Float.toString(max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final float min, final float max){
		return zRangeByLex(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max));
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
	public Set<String> zRangeByLex(final String key, final int min, final int max){
		return zRangeByLex(key, Integer.toString(min), Integer.toString(max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final int min, final int max){
		return zRangeByLex(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max));
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
	public Set<String> zRangeByLex(final String key, final float min, final float max, final int offset,
			final int count){
		return zRangeByLex(key, Float.toString(min), Float.toString(max), offset, count);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return zRangeByLex(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max), offset, count);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final double min, final double max, final int offset,
			final int count){
		return zRangeByLex(key, Double.toString(min), Double.toString(max), offset, count);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return zRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max), offset, count);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final int min, final int max, final int offset, final int count){
		return zRangeByLex(key, Integer.toString(min), Integer.toString(max), offset, count);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final int min, final int max, final int offset, final int count){
		return zRangeByLex(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max), offset, count);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final long min, final long max, final int offset,
			final int count){
		return zRangeByLex(key, Long.toString(min), Long.toString(max), offset, count);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRangeByLex(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max), offset, count);
	}

	@Override
	public Long zRemRangeByRank(final String key, final int start, final int end){
		return zRemRangeByRank(key, (long) start, (long) end);
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final int start, final int end){
		return zRemRangeByRank(key, (long) start, (long) end);
	}

	@Override
	public Long zRemRangeByScore(final String key, final float min, final float max){
		return zRemRangeByScore(key, (double) min, (double) max);
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final float min, final float max){
		return zRemRangeByScore(key, (double) min, (double) max);
	}

	@Override
	public Long zRemRangeByScore(final String key, final int min, final int max){
		return zRemRangeByScore(key, (double) min, (double) max);
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final int min, final int max){
		return zRemRangeByScore(key, (double) min, (double) max);
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
	public Long zRemRangeByLex(final String key, final float min, final float max){
		return zRemRangeByLex(key, Float.toString(min), Float.toString(max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final float min, final float max){
		return zRemRangeByLex(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max));
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
	public Long zRemRangeByLex(final String key, final int min, final int max){
		return zRemRangeByLex(key, Integer.toString(min), Integer.toString(max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final int min, final int max){
		return zRemRangeByLex(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max));
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
	public Set<String> zRevRange(final String key, final int start, final int end){
		return zRevRange(key, (long) start, (long) end);
	}

	@Override
	public Set<byte[]> zRevRange(final byte[] key, final int start, final int end){
		return zRevRange(key, (long) start, (long) end);
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final String key, final int start, final int end){
		return zRevRangeWithScores(key, (long) start, (long) end);
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final int start, final int end){
		return zRevRangeWithScores(key, (long) start, (long) end);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final float min, final float max){
		return zRevRangeByScore(key, (double) min, (double) max);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final float min, final float max){
		return zRevRangeByScore(key, (double) min, (double) max);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final int min, final int max){
		return zRevRangeByScore(key, (double) min, (double) max);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final int min, final int max){
		return zRevRangeByScore(key, (double) min, (double) max);
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
	public Set<String> zRevRangeByScore(final String key, final float min, final float max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByScore(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final float min, final float max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final float max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max);
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
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final float min, final float max, final int offset,
			final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByScoreWithScores(key, (double) min, (double) max, offset, count);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final float min, final float max){
		return zRevRangeByLex(key, Float.toString(min), Float.toString(max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final float min, final float max){
		return zRevRangeByLex(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max));
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
	public Set<String> zRevRangeByLex(final String key, final int min, final int max){
		return zRevRangeByLex(key, Integer.toString(min), Integer.toString(max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final int min, final int max){
		return zRevRangeByLex(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max));
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
	public Set<String> zRevRangeByLex(final String key, final float min, final float max, final int offset,
			final int count){
		return zRevRangeByLex(key, Float.toString(min), Float.toString(max), offset, count);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return zRevRangeByLex(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max), offset, count);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final double min, final double max, final int offset,
			final int count){
		return zRevRangeByLex(key, Double.toString(min), Double.toString(max), offset, count);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return zRevRangeByLex(key, NumberUtils.double2bytes(min), NumberUtils.double2bytes(max), offset, count);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByLex(key, Integer.toString(min), Integer.toString(max), offset, count);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return zRevRangeByLex(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max), offset, count);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByLex(key, Long.toString(min), Long.toString(max), offset, count);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return zRevRangeByLex(key, NumberUtils.long2bytes(min), NumberUtils.long2bytes(max), offset, count);
	}

	@Override
	public Long zLexCount(final String key, final float min, final float max){
		return zLexCount(key, Float.toString(min), Float.toString(max));
	}

	@Override
	public Long zLexCount(final byte[] key, final float min, final float max){
		return zLexCount(key, NumberUtils.float2bytes(min), NumberUtils.float2bytes(max));
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
	public Long zLexCount(final String key, final int min, final int max){
		return zLexCount(key, Integer.toString(min), Integer.toString(max));
	}

	@Override
	public Long zLexCount(final byte[] key, final int min, final int max){
		return zLexCount(key, NumberUtils.int2bytes(min), NumberUtils.int2bytes(max));
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
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor){
		return zScan(key, Integer.toString(cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor){
		return zScan(key, NumberUtils.int2bytes(cursor));
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
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern){
		return zScan(key, Integer.toString(cursor), pattern);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern){
		return zScan(key, NumberUtils.int2bytes(cursor), pattern);
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
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final int count){
		return zScan(key, Integer.toString(cursor), Integer.toString(count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final int count){
		return zScan(key, NumberUtils.int2bytes(cursor), NumberUtils.int2bytes(count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final int count){
		return zScan(key, Long.toString(cursor), Long.toString(count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final int count){
		return zScan(key, NumberUtils.long2bytes(cursor), NumberUtils.long2bytes(count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern, final int count){
		return zScan(key, Integer.toString(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final int count){
		return zScan(key, Long.toString(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
		return zScan(key, NumberUtils.int2bytes(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(byte[] key, long cursor, byte[] pattern, int count){
		return zScan(key, NumberUtils.long2bytes(cursor), pattern, count);
	}

	@Override
	public Status setBit(final String key, final int offset, final String value){
		return setBit(key, (long) offset, value);
	}

	@Override
	public Status setBit(final byte[] key, final int offset, final byte[] value){
		return setBit(key, (long) offset, value);
	}

	@Override
	public Status setBit(final String key, final int offset, final boolean value){
		return setBit(key, (long) offset, value);
	}

	@Override
	public Status setBit(final byte[] key, final int offset, final boolean value){
		return setBit(key, (long) offset, value);
	}

	@Override
	public Status getBit(final String key, final int offset){
		return getBit(key, (long) offset);
	}

	@Override
	public Status getBit(final byte[] key, final int offset){
		return getBit(key, (long) offset);
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end){
		return bitPos(key, value, (int) start, (int) end);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end){
		return bitPos(key, value, (int) start, (int) end);
	}

	@Override
	public Long bitCount(final String key, final int start, final int end){
		return bitCount(key, (long) start, (long) end);
	}

	@Override
	public Long bitCount(final byte[] key, final int start, final int end){
		return bitCount(key, (long) start, (long) end);
	}

	@Override
	public Status configSet(final String parameter, final float value){
		return configSet(parameter, Float.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final float value){
		return configSet(parameter, NumberUtils.float2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final double value){
		return configSet(parameter, Double.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final double value){
		return configSet(parameter, NumberUtils.double2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final int value){
		return configSet(parameter, Integer.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final long value){
		return configSet(parameter, NumberUtils.long2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final long value){
		return configSet(parameter, Long.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final int value){
		return configSet(parameter, NumberUtils.int2bytes(value));
	}*/

	protected <C, R> R doExecute(final Executor<C, R> executor){
		RedisConnection connection;

		if(enableTransactionSupport){
			// only bind resources in case of potential transaction synchronization
			connection = RedisConnectionUtils.bindConnection(connectionFactory, true);
		}else{
			connection = RedisConnectionUtils.getConnection(connectionFactory);
		}

		try{
			return connection.execute(executor);
		}catch(RedisException e){
			throw e;
		}finally{
			RedisConnectionUtils.releaseConnection(connectionFactory, connection, enableTransactionSupport);
		}
	}

	protected boolean isTransaction(){
		return getConnection().isTransaction();
	}

	protected void close(){
		try{
			connection.close();
		}catch(IOException e){
			logger.error("RedisConnection close error: {}", e.getMessage());
		}
	}

}
