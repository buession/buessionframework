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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis;

import com.buession.core.type.TypeReference;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.Direction;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.ZRangeType;
import com.buession.redis.core.command.args.GetExArgument;
import com.buession.redis.core.command.args.JsonGetArgument;
import com.buession.redis.core.command.args.SetArgument;
import com.buession.redis.core.operations.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis 命令操作封装扩展，可序列化对象和反序列化为对象
 *
 * @author Yong.Teng
 */
public class RedisTemplate extends AbstractRedisTemplate implements AutoSuggestOperations, BitMapOperations,
		BloomFilterOperations, ClusterOperations, ConnectionOperations, CountMinSketchOperations,
		CuckooFilterOperations, GenericOperations, GeoOperations, HashOperations, HyperLogLogOperations,
		JsonOperations, KeyOperations, ListOperations, PubSubOperations, ScriptingOperations, SearchOperations,
		ServerOperations, SetOperations, SortedSetOperations, StreamOperations, StringOperations, TDigestOperations,
		TimeSeriesOperations, TopKOperations, TransactionOperations, VectorSetOperations {

	/**
	 * 构造函数
	 */
	public RedisTemplate() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		数据源
	 */
	public RedisTemplate(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public <V> V hGet(final String key, final String field, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGet(key, field),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V hGet(final byte[] key, final byte[] field, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGet(key, field),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V hGet(final String key, final String field, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGet(key, field),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V hGet(final byte[] key, final byte[] field, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGet(key, field),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> Map<String, V> hGetAll(final String key, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGetAll(key),
				new Converter.ClazzMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> Map<byte[], V> hGetAll(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGetAll(key),
				new Converter.ClazzMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Map<String, V> hGetAll(final String key, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGetAll(key),
				new Converter.TypeMapStringConverter<>(this, type));
	}

	@Override
	public <V> Map<byte[], V> hGetAll(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGetAll(key),
				new Converter.TypeMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetDel(final String key, final String[] fields, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGetDel(key, fields),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetDel(final byte[] key, final byte[][] fields, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGetDel(key, fields),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetDel(final String key, final String[] fields, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGetDel(key, fields),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetDel(final byte[] key, final byte[][] fields, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGetDel(key, fields),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetEx(final String key, final String[] fields, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGetEx(key, fields),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetEx(final byte[] key, final byte[][] fields, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGetEx(key, fields),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetEx(final String key, final String[] fields, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGetEx(key, fields),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetEx(final byte[] key, final byte[][] fields, TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGetEx(key, fields),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetEx(final String key, final GetExArgument argument, final String[] fields,
							  final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGetEx(key, argument, fields),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetEx(final byte[] key, final GetExArgument argument, final byte[][] fields,
							  final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGetEx(key, argument, fields),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetEx(final String key, final GetExArgument argument, final String[] fields,
							  final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGetEx(key, argument, fields),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetEx(final byte[] key, final GetExArgument argument, final byte[][] fields,
							  final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGetEx(key, argument, fields),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> hMGet(final String key, final String[] fields, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hMGet(key, fields),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hMGet(final byte[] key, final byte[][] fields, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hMGet(key, fields),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hMGet(final String key, final String[] fields, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hMGet(key, fields),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> hMGet(final byte[] key, final byte[][] fields, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hMGet(key, fields),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> Map<String, V> hRandFieldWithValues(final String key, final int count, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hRandFieldWithValues(key, count),
				new Converter.ClazzMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> Map<byte[], V> hRandFieldWithValues(final byte[] key, final int count, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hRandFieldWithValues(key, count),
				new Converter.ClazzMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Map<String, V> hRandFieldWithValues(final String key, final int count, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hRandFieldWithValues(key, count),
				new Converter.TypeMapStringConverter<>(this, type));
	}

	@Override
	public <V> Map<byte[], V> hRandFieldWithValues(final byte[] key, final int count, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hRandFieldWithValues(key, count),
				new Converter.TypeMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<KeyValue<String, V>> hScan(final String key, final String cursor, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hScan(key, cursor),
				new Converter.ClazzScanResultStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<KeyValue<byte[], V>> hScan(final byte[] key, final byte[] cursor, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hScan(key, cursor),
				new Converter.ClazzScanResultBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<KeyValue<String, V>> hScan(final String key, final String cursor,
													 final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hScan(key, cursor),
				new Converter.TypeScanResultStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<KeyValue<byte[], V>> hScan(final byte[] key, final byte[] cursor,
													 final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hScan(key, cursor),
				new Converter.TypeScanResultBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<KeyValue<String, V>> hScan(final String key, final String cursor, final String pattern,
													 final Class<V> clazz) {
		return execute((client)->client.hashCommands().hScan(key, cursor, pattern),
				new Converter.ClazzScanResultStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<KeyValue<byte[], V>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
													 final Class<V> clazz) {
		return execute((client)->client.hashCommands().hScan(key, cursor, pattern),
				new Converter.ClazzScanResultBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<KeyValue<String, V>> hScan(final String key, final String cursor, final String pattern,
													 final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hScan(key, cursor, pattern),
				new Converter.TypeScanResultStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<KeyValue<byte[], V>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
													 final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hScan(key, cursor, pattern),
				new Converter.TypeScanResultBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<KeyValue<String, V>> hScan(final String key, final String cursor, final int count,
													 final Class<V> clazz) {
		return execute((client)->client.hashCommands().hScan(key, cursor, count),
				new Converter.ClazzScanResultStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<KeyValue<byte[], V>> hScan(final byte[] key, final byte[] cursor, final int count,
													 final Class<V> clazz) {
		return execute((client)->client.hashCommands().hScan(key, cursor, count),
				new Converter.ClazzScanResultBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<KeyValue<String, V>> hScan(final String key, final String cursor, final int count,
													 final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hScan(key, cursor, count),
				new Converter.TypeScanResultStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<KeyValue<byte[], V>> hScan(final byte[] key, final byte[] cursor, final int count,
													 final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hScan(key, cursor, count),
				new Converter.TypeScanResultBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<KeyValue<String, V>> hScan(final String key, final String cursor, final String pattern,
													 final int count, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hScan(key, cursor, pattern, count),
				new Converter.ClazzScanResultStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<KeyValue<byte[], V>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
													 final int count, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hScan(key, cursor, pattern, count),
				new Converter.ClazzScanResultBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<KeyValue<String, V>> hScan(final String key, final String cursor, final String pattern,
													 final int count, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hScan(key, cursor, pattern, count),
				new Converter.TypeScanResultStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<KeyValue<byte[], V>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
													 final int count, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hScan(key, cursor, pattern, count),
				new Converter.TypeScanResultBinaryConverter<>(this, type));
	}

	@Override
	public <V> Status hSetNx(final String key, final String field, final V value) {
		return hSetNx(key, field, serializer.serialize(value));
	}

	@Override
	public <V> Status hSetNx(final byte[] key, final byte[] field, final V value) {
		return hSetNx(key, field, serializer.serializeAsBytes(value));
	}

	@Override
	public <V> List<V> hVals(final String key, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hVals(key),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hVals(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hVals(key),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hVals(final String key, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hVals(key), new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> hVals(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hVals(key), new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V jsonGet(final String key, final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonGet(key), new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V jsonGet(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonGet(key), new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V jsonGet(final String key, final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonGet(key), new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V jsonGet(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonGet(key), new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V jsonGet(final String key, final JsonGetArgument argument, final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonGet(key, argument),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V jsonGet(final byte[] key, final JsonGetArgument argument, final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonGet(key, argument),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V jsonGet(final String key, final JsonGetArgument argument, final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonGet(key, argument),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V jsonGet(final byte[] key, final JsonGetArgument argument, final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonGet(key, argument),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonGet(final String key, final String[] path, final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonGet(key, path),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonGet(final byte[] key, final byte[][] path, final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonGet(key, path),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonGet(final String key, final String[] path, final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonGet(key, path),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonGet(final byte[] key, final byte[][] path, final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonGet(key, path),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonGet(final String key, final JsonGetArgument argument, final String[] path,
							   final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonGet(key, argument, path),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonGet(final byte[] key, final JsonGetArgument argument, final byte[][] path,
							   final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonGet(key, argument, path),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonGet(final String key, final JsonGetArgument argument, final String[] path,
							   final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonGet(key, argument, path),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonGet(final byte[] key, final JsonGetArgument argument, final byte[][] path,
							   final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonGet(key, argument, path),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonMGet(final String[] keys, final String path, final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonMGet(keys, path),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonMGet(final byte[][] keys, final byte[] path, final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonMGet(keys, path),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonMGet(final String[] keys, final String path, final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonMGet(keys, path),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonMGet(final byte[][] keys, final byte[] path, final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonMGet(keys, path),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> Status jsonSet(final String key, final String path, final V value) {
		return jsonSet(key, path, serializer.serialize(value));
	}

	@Override
	public <V> Status jsonSet(final byte[] key, final byte[] path, final V value) {
		return jsonSet(key, path, serializer.serializeAsBytes(value));
	}

	@Override
	public <V> Status jsonSet(final String key, final String path, final V value, final NxXx nxXx) {
		return jsonSet(key, path, serializer.serialize(value), nxXx);
	}

	@Override
	public <V> Status jsonSet(final byte[] key, final byte[] path, final V value, final NxXx nxXx) {
		return jsonSet(key, path, serializer.serializeAsBytes(value), nxXx);
	}

	@Override
	public <V> V blMove(final String key, final String destKey, final Direction from, final Direction to,
						final int timeout, final Class<V> clazz) {
		return execute((client)->client.listCommands().blMove(key, destKey, from, to, timeout),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V blMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
						final int timeout, final Class<V> clazz) {
		return execute((client)->client.listCommands().blMove(key, destKey, from, to, timeout),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V blMove(final String key, final String destKey, final Direction from, final Direction to,
						final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listCommands().blMove(key, destKey, from, to, timeout),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V blMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
						final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listCommands().blMove(key, destKey, from, to, timeout),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<String, List<V>> blMPop(final int timeout, final String[] keys, final Direction direction,
												final Class<V> clazz) {
		return execute((client)->client.listCommands().blMPop(timeout, keys, direction),
				new Converter.ClazzListKeyValueStringConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> blMPop(final int timeout, final byte[][] keys, final Direction direction,
												final Class<V> clazz) {
		return execute((client)->client.listCommands().blMPop(timeout, keys, direction),
				new Converter.ClazzListKeyValueBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<String, List<V>> blMPop(final int timeout, final String[] keys, final Direction direction,
												final TypeReference<V> type) {
		return execute((client)->client.listCommands().blMPop(timeout, keys, direction),
				new Converter.TypeListKeyValueStringConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> blMPop(final int timeout, final byte[][] keys, final Direction direction,
												final TypeReference<V> type) {
		return execute((client)->client.listCommands().blMPop(timeout, keys, direction),
				new Converter.TypeListKeyValueBinaryConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<String, List<V>> blMPop(final int timeout, final String[] keys, final Direction direction,
												final int count, final Class<V> clazz) {
		return execute((client)->client.listCommands().blMPop(timeout, keys, direction, count),
				new Converter.ClazzListKeyValueStringConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> blMPop(final int timeout, final byte[][] keys, final Direction direction,
												final int count, final Class<V> clazz) {
		return execute((client)->client.listCommands().blMPop(timeout, keys, direction, count),
				new Converter.ClazzListKeyValueBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<String, List<V>> blMPop(final int timeout, final String[] keys, final Direction direction,
												final int count, final TypeReference<V> type) {
		return execute((client)->client.listCommands().blMPop(timeout, keys, direction, count),
				new Converter.TypeListKeyValueStringConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> blMPop(final int timeout, final byte[][] keys, final Direction direction,
												final int count, final TypeReference<V> type) {
		return execute((client)->client.listCommands().blMPop(timeout, keys, direction, count),
				new Converter.TypeListKeyValueBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> blPop(final String[] keys, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listCommands().blPop(keys, timeout),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> blPop(final byte[][] keys, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listCommands().blPop(keys, timeout),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> blPop(final String[] keys, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listCommands().blPop(keys, timeout),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> blPop(final byte[][] keys, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listCommands().blPop(keys, timeout),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> brPop(final String[] keys, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listCommands().brPop(keys, timeout),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> brPop(final byte[][] keys, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listCommands().brPop(keys, timeout),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> brPop(final String[] keys, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listCommands().brPop(keys, timeout),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> brPop(final byte[][] keys, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listCommands().brPop(keys, timeout),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V brPoplPush(final String key, final String destKey, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listCommands().brPoplPush(key, destKey, timeout),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V brPoplPush(final byte[] key, final byte[] destKey, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listCommands().brPoplPush(key, destKey, timeout),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V brPoplPush(final String key, final String destKey, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listCommands().brPoplPush(key, destKey, timeout),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V brPoplPush(final byte[] key, final byte[] destKey, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listCommands().brPoplPush(key, destKey, timeout),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V lIndex(final String key, final long index, final Class<V> clazz) {
		return execute((client)->client.listCommands().lIndex(key, index),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V lIndex(final byte[] key, final long index, final Class<V> clazz) {
		return execute((client)->client.listCommands().lIndex(key, index),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V lIndex(final String key, final long index, final TypeReference<V> type) {
		return execute((client)->client.listCommands().lIndex(key, index),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V lIndex(final byte[] key, final long index, final TypeReference<V> type) {
		return execute((client)->client.listCommands().lIndex(key, index),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> Long lInsert(final String key, final ListPosition position, final V pivot, final V value) {
		return lInsert(key, position, serializer.serialize(pivot), serializer.serialize(value));
	}

	@Override
	public <V> Long lInsert(final byte[] key, final ListPosition position, final V pivot, final V value) {
		return lInsert(key, position, serializer.serializeAsBytes(pivot), serializer.serializeAsBytes(value));
	}

	@Override
	public <V> V lMove(final String key, final String destKey, final Direction from, final Direction to,
					   final Class<V> clazz) {
		return execute((client)->client.listCommands().lMove(key, destKey, from, to),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V lMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
					   final Class<V> clazz) {
		return execute((client)->client.listCommands().lMove(key, destKey, from, to),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V lMove(final String key, final String destKey, final Direction from, final Direction to,
					   final TypeReference<V> type) {
		return execute((client)->client.listCommands().lMove(key, destKey, from, to),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V lMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
					   final TypeReference<V> type) {
		return execute((client)->client.listCommands().lMove(key, destKey, from, to),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<String, List<V>> lMPop(final String[] keys, final Direction direction, final Class<V> clazz) {
		return execute((client)->client.listCommands().lMPop(keys, direction),
				new Converter.ClazzListKeyValueStringConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> lMPop(final byte[][] keys, final Direction direction, final Class<V> clazz) {
		return execute((client)->client.listCommands().lMPop(keys, direction),
				new Converter.ClazzListKeyValueBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<String, List<V>> lMPop(final String[] keys, final Direction direction,
											   final TypeReference<V> type) {
		return execute((client)->client.listCommands().lMPop(keys, direction),
				new Converter.TypeListKeyValueStringConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> lMPop(final byte[][] keys, final Direction direction,
											   final TypeReference<V> type) {
		return execute((client)->client.listCommands().lMPop(keys, direction),
				new Converter.TypeListKeyValueBinaryConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<String, List<V>> lMPop(final String[] keys, final Direction direction, final int count,
											   final Class<V> clazz) {
		return execute((client)->client.listCommands().lMPop(keys, direction, count),
				new Converter.ClazzListKeyValueStringConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> lMPop(final byte[][] keys, final Direction direction, final int count,
											   final Class<V> clazz) {
		return execute((client)->client.listCommands().lMPop(keys, direction, count),
				new Converter.ClazzListKeyValueBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<String, List<V>> lMPop(final String[] keys, final Direction direction, final int count,
											   final TypeReference<V> type) {
		return execute((client)->client.listCommands().lMPop(keys, direction, count),
				new Converter.TypeListKeyValueStringConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> lMPop(final byte[][] keys, final Direction direction, final int count,
											   final TypeReference<V> type) {
		return execute((client)->client.listCommands().lMPop(keys, direction, count),
				new Converter.TypeListKeyValueBinaryConverter<>(this, type));
	}

	@Override
	public <V> V lPop(final String key, final Class<V> clazz) {
		return execute((client)->client.listCommands().lPop(key), new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V lPop(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.listCommands().lPop(key), new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V lPop(final String key, final TypeReference<V> type) {
		return execute((client)->client.listCommands().lPop(key), new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V lPop(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.listCommands().lPop(key), new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> lPop(final String key, final int count, final Class<V> clazz) {
		return execute((client)->client.listCommands().lPop(key, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> lPop(final byte[] key, final int count, final Class<V> clazz) {
		return execute((client)->client.listCommands().lPop(key, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> lPop(final String key, final int count, final TypeReference<V> type) {
		return execute((client)->client.listCommands().lPop(key, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> lPop(final byte[] key, final int count, final TypeReference<V> type) {
		return execute((client)->client.listCommands().lPop(key, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public <V> Long lPush(final String key, final V... values) {
		return lPush(key, serializer.serialize(values));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public <V> Long lPush(final byte[] key, final V... values) {
		return lPush(key, serializer.serializeAsBytes(values));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public <V> Long lPushX(final String key, final V... values) {
		return lPushX(key, serializer.serialize(values));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public <V> Long lPushX(final byte[] key, final V... values) {
		return lPushX(key, serializer.serializeAsBytes(values));
	}

	@Override
	public <V> List<V> lRange(final String key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.listCommands().lRange(key, start, end),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> lRange(final byte[] key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.listCommands().lRange(key, start, end),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> lRange(final String key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.listCommands().lRange(key, start, end),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> lRange(final byte[] key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.listCommands().lRange(key, start, end),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> Status lSet(final String key, final long index, final V value) {
		return lSet(key, index, serializer.serialize(value));
	}

	@Override
	public <V> Status lSet(final byte[] key, final long index, final V value) {
		return lSet(key, index, serializer.serializeAsBytes(value));
	}

	@Override
	public <V> V rPop(final String key, final Class<V> clazz) {
		return execute((client)->client.listCommands().rPop(key), new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V rPop(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.listCommands().rPop(key), new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V rPop(final String key, final TypeReference<V> type) {
		return execute((client)->client.listCommands().rPop(key), new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V rPop(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.listCommands().rPop(key), new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> rPop(final String key, final int count, final Class<V> clazz) {
		return execute((client)->client.listCommands().rPop(key, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> rPop(final byte[] key, final int count, final Class<V> clazz) {
		return execute((client)->client.listCommands().rPop(key, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> rPop(final String key, final int count, final TypeReference<V> type) {
		return execute((client)->client.listCommands().rPop(key, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> rPop(final byte[] key, final int count, final TypeReference<V> type) {
		return execute((client)->client.listCommands().rPop(key, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V rPoplPush(final String key, final String destKey, final Class<V> clazz) {
		return execute((client)->client.listCommands().rPoplPush(key, destKey),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V rPoplPush(final byte[] key, final byte[] destKey, final Class<V> clazz) {
		return execute((client)->client.listCommands().rPoplPush(key, destKey),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V rPoplPush(final String key, final String destKey, final TypeReference<V> type) {
		return execute((client)->client.listCommands().rPoplPush(key, destKey),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V rPoplPush(final byte[] key, final byte[] destKey, final TypeReference<V> type) {
		return execute((client)->client.listCommands().rPoplPush(key, destKey),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public <V> Long rPush(final String key, final V... values) {
		return rPush(key, serializer.serialize(values));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public <V> Long rPush(final byte[] key, final V... values) {
		return rPush(key, serializer.serializeAsBytes(values));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public <V> Long rPushX(final String key, final V... values) {
		return rPushX(key, serializer.serialize(values));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public <V> Long rPushX(final byte[] key, final V... values) {
		return rPushX(key, serializer.serializeAsBytes(values));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public <V> Long sAdd(final String key, final V... members) {
		return sAdd(key, serializer.serialize(members));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public <V> Long sAdd(final byte[] key, final V... members) {
		return sAdd(key, serializer.serializeAsBytes(members));
	}

	@Override
	public <V> Set<V> sDiff(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.setCommands().sDiff(keys),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sDiff(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.setCommands().sDiff(keys),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sDiff(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sDiff(keys), new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sDiff(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sDiff(keys), new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sInter(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.setCommands().sInter(keys),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sInter(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.setCommands().sInter(keys),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sInter(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sInter(keys), new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sInter(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sInter(keys), new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sMembers(final String key, final Class<V> clazz) {
		return execute((client)->client.setCommands().sMembers(key),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sMembers(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.setCommands().sMembers(key),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sMembers(final String key, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sMembers(key),
				new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sMembers(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sMembers(key),
				new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> V sPop(final String key, final Class<V> clazz) {
		return execute((client)->client.setCommands().sPop(key), new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V sPop(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.setCommands().sPop(key), new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V sPop(final String key, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sPop(key), new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V sPop(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sPop(key), new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sPop(final String key, final int count, final Class<V> clazz) {
		return execute((client)->client.setCommands().sPop(key, count),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sPop(final byte[] key, final int count, final Class<V> clazz) {
		return execute((client)->client.setCommands().sPop(key, count),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sPop(final String key, final int count, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sPop(key, count),
				new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sPop(final byte[] key, final int count, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sPop(key, count),
				new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> V sRandMember(final String key, final Class<V> clazz) {
		return execute((client)->client.setCommands().sRandMember(key),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V sRandMember(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.setCommands().sRandMember(key),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V sRandMember(final String key, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sRandMember(key),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V sRandMember(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sRandMember(key),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> sRandMember(final String key, final int count, final Class<V> clazz) {
		return execute((client)->client.setCommands().sRandMember(key, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> sRandMember(final byte[] key, final int count, final Class<V> clazz) {
		return execute((client)->client.setCommands().sRandMember(key, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> sRandMember(final String key, final int count, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sRandMember(key, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> sRandMember(final byte[] key, final int count, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sRandMember(key, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public <V> Long sRem(final String key, final V... members) {
		return sRem(key, serializer.serialize(members));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public <V> Long sRem(final byte[] key, final V... members) {
		return sRem(key, serializer.serializeAsBytes(members));
	}

	@Override
	public <V> ScanResult<V> sScan(final String key, final String cursor, final Class<V> clazz) {
		return execute((client)->client.setCommands().sScan(key, cursor),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final Class<V> clazz) {
		return execute((client)->client.setCommands().sScan(key, cursor),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<V> sScan(final String key, final String cursor, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sScan(key, cursor),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sScan(key, cursor),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<V> sScan(final String key, final String cursor, final String pattern,
								   final Class<V> clazz) {
		return execute((client)->client.setCommands().sScan(key, cursor, pattern),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
								   final Class<V> clazz) {
		return execute((client)->client.setCommands().sScan(key, cursor, pattern),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<V> sScan(final String key, final String cursor, final String pattern,
								   final TypeReference<V> type) {
		return execute((client)->client.setCommands().sScan(key, cursor, pattern),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
								   final TypeReference<V> type) {
		return execute((client)->client.setCommands().sScan(key, cursor, pattern),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<V> sScan(final String key, final String cursor, final String pattern, final int count,
								   final Class<V> clazz) {
		return execute((client)->client.setCommands().sScan(key, cursor, pattern, count),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count,
								   final Class<V> clazz) {
		return execute((client)->client.setCommands().sScan(key, cursor, pattern, count),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<V> sScan(final String key, final String cursor, final String pattern, final int count,
								   final TypeReference<V> type) {
		return execute((client)->client.setCommands().sScan(key, cursor, pattern, count),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count,
								   final TypeReference<V> type) {
		return execute((client)->client.setCommands().sScan(key, cursor, pattern, count),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<V> sScan(final String key, final String cursor, final int count, final Class<V> clazz) {
		return execute((client)->client.setCommands().sScan(key, cursor, count),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final int count, final Class<V> clazz) {
		return execute((client)->client.setCommands().sScan(key, cursor, count),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<V> sScan(final String key, final String cursor, final int count,
								   final TypeReference<V> type) {
		return execute((client)->client.setCommands().sScan(key, cursor, count),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final int count,
								   final TypeReference<V> type) {
		return execute((client)->client.setCommands().sScan(key, cursor, count),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sUnion(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.setCommands().sUnion(keys),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sUnion(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.setCommands().sUnion(keys),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sUnion(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sUnion(keys), new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sUnion(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sUnion(keys), new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zDiff(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zDiff(keys),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zDiff(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zDiff(keys),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zDiff(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zDiff(keys),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zDiff(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zDiff(keys),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInter(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zInter(keys),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInter(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zInter(keys),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInter(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zInter(keys),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInter(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zInter(keys),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInter(final String[] keys, final Aggregate aggregate, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zInter(keys, aggregate),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInter(final byte[][] keys, final Aggregate aggregate, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zInter(keys, aggregate),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInter(final String[] keys, final Aggregate aggregate, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zInter(keys, aggregate),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInter(final byte[][] keys, final Aggregate aggregate, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zInter(keys, aggregate),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInter(final String[] keys, final Aggregate aggregate, final double[] weights,
							  final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zInter(keys, aggregate, weights),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInter(final byte[][] keys, final Aggregate aggregate, final double[] weights,
							  final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zInter(keys, aggregate, weights),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInter(final String[] keys, final Aggregate aggregate, final double[] weights,
							  final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zInter(keys, aggregate, weights),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInter(final byte[][] keys, final Aggregate aggregate, final double[] weights,
							  final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zInter(keys, aggregate, weights),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInter(final String[] keys, final double[] weights, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zInter(keys, weights),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInter(final byte[][] keys, final double[] weights, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zInter(keys, weights),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInter(final String[] keys, final double[] weights, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zInter(keys, weights),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInter(final byte[][] keys, final double[] weights, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zInter(keys, weights),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V zRandMember(final String key, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRandMember(key),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V zRandMember(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRandMember(key),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V zRandMember(final String key, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRandMember(key),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V zRandMember(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRandMember(key),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRandMember(final String key, final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRandMember(key, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRandMember(final byte[] key, final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRandMember(key, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRandMember(final String key, final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRandMember(key, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRandMember(final byte[] key, final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRandMember(key, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final ZRangeType type,
							  final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, type),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
							  final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, type),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final ZRangeType zRangeType,
							  final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, zRangeType),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType zRangeType,
							  final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, zRangeType),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final ZRangeType type,
							  final boolean rev, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, type, rev),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
							  final boolean rev, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, type, rev),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final ZRangeType zRangeType,
							  final boolean rev, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, zRangeType, rev),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType zRangeType,
							  final boolean rev, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, zRangeType, rev),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final int offset, final int count,
							  final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final int offset, final int count,
							  final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final int offset, final int count,
							  final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final int offset, final int count,
							  final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final ZRangeType type,
							  final int offset, final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, type, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
							  final int offset, final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, type, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final ZRangeType zRangeType,
							  final int offset, final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, zRangeType, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType zRangeType,
							  final int offset, final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, zRangeType, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final ZRangeType type,
							  final boolean rev, final int offset, final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, type, rev, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
							  final boolean rev, final int offset, final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, type, rev, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final ZRangeType zRangeType,
							  final boolean rev, final int offset, final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, zRangeType, rev, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType zRangeType,
							  final boolean rev, final int offset, final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, zRangeType, rev, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final boolean rev, final int offset,
							  final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, rev, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final boolean rev, final int offset,
							  final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, rev, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final boolean rev, final int offset,
							  final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, rev, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final boolean rev, final int offset,
							  final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(key, start, end, rev, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByLex(final String key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRangeByLex(key, min, max),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByLex(final byte[] key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRangeByLex(key, min, max),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByLex(final String key, final double min, final double max, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRangeByLex(key, min, max),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByLex(final byte[] key, final double min, final double max, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRangeByLex(key, min, max),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByLex(final String key, final double min, final double max, final int offset,
								   final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRangeByLex(key, min, max, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
								   final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRangeByLex(key, min, max, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByLex(final String key, final double min, final double max, final int offset,
								   final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRangeByLex(key, min, max, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
								   final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRangeByLex(key, min, max, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByScore(final String key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRangeByScore(key, min, max),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByScore(final byte[] key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRangeByScore(key, min, max),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByScore(final String key, final double min, final double max,
									 final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRangeByScore(key, min, max),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByScore(final byte[] key, final double min, final double max,
									 final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRangeByScore(key, min, max),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByScore(final String key, final double min, final double max, final int offset,
									 final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRangeByScore(key, min, max, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
									 final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRangeByScore(key, min, max, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByScore(final String key, final double min, final double max, final int offset,
									 final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRangeByScore(key, min, max, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
									 final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRangeByScore(key, min, max, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRange(final String key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRevRange(key, start, end),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRange(final byte[] key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRevRange(key, start, end),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRange(final String key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRevRange(key, start, end),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRange(final byte[] key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRevRange(key, start, end),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByLex(final String key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRevRangeByLex(key, min, max),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLex(final byte[] key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRevRangeByLex(key, min, max),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLex(final String key, final double min, final double max,
									  final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRevRangeByLex(key, min, max),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByLex(final byte[] key, final double min, final double max,
									  final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRevRangeByLex(key, min, max),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByLex(final String key, final double min, final double max, final int offset,
									  final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRevRangeByLex(key, min, max, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
									  final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRevRangeByLex(key, min, max, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLex(final String key, final double min, final double max, final int offset,
									  final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRevRangeByLex(key, min, max, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
									  final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRevRangeByLex(key, min, max, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByScore(final String key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScore(key, min, max),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScore(final byte[] key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScore(key, min, max),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScore(final String key, final double min, final double max,
										final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScore(key, min, max),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByScore(final byte[] key, final double min, final double max,
										final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScore(key, min, max),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByScore(final String key, final double min, final double max, final int offset,
										final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScore(key, min, max, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
										final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScore(key, min, max, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScore(final String key, final double min, final double max, final int offset,
										final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScore(key, min, max, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
										final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScore(key, min, max, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnion(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zUnion(keys),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnion(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zUnion(keys),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnion(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zUnion(keys),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnion(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zUnion(keys),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnion(final String[] keys, final Aggregate aggregate, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, aggregate),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnion(final byte[][] keys, final Aggregate aggregate, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, aggregate),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnion(final String[] keys, final Aggregate aggregate, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, aggregate),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnion(final byte[][] keys, final Aggregate aggregate, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, aggregate),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnion(final String[] keys, final Aggregate aggregate, final double[] weights,
							  final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, aggregate, weights),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnion(final byte[][] keys, final Aggregate aggregate, final double[] weights,
							  final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, aggregate, weights),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnion(final String[] keys, final Aggregate aggregate, final double[] weights,
							  final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, aggregate, weights),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnion(final byte[][] keys, final Aggregate aggregate, final double[] weights,
							  final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, aggregate, weights),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnion(final String[] keys, final double[] weights, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, weights),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnion(final byte[][] keys, final double[] weights, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, weights),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnion(final String[] keys, final double[] weights, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, weights),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnion(final byte[][] keys, final double[] weights, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zUnion(keys, weights),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V get(final String key, final Class<V> clazz) {
		return execute((client)->client.stringCommands().get(key), new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V get(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.stringCommands().get(key), new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V get(final String key, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().get(key), new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V get(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().get(key), new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V getDel(final String key, final Class<V> clazz) {
		return execute((client)->client.stringCommands().getDel(key),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V getDel(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.stringCommands().getDel(key),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V getDel(final String key, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().getDel(key), new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V getDel(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().getDel(key), new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V getEx(final String key, final Class<V> clazz) {
		return execute((client)->client.stringCommands().getEx(key),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V getEx(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.stringCommands().getEx(key),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V getEx(final String key, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().getEx(key),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V getEx(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().getEx(key),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V getEx(final String key, final GetExArgument argument, final Class<V> clazz) {
		return execute((client)->client.stringCommands().getEx(key, argument),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V getEx(final byte[] key, final GetExArgument argument, final Class<V> clazz) {
		return execute((client)->client.stringCommands().getEx(key, argument),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V getEx(final String key, final GetExArgument argument, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().getEx(key, argument),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V getEx(final byte[] key, final GetExArgument argument, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().getEx(key, argument),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V getSet(final String key, final V value, final Class<V> clazz) {
		return execute((client)->client.stringCommands().getSet(key, serializer.serialize(value)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V getSet(final byte[] key, final V value, final Class<V> clazz) {
		return execute((client)->client.stringCommands().getSet(key, serializer.serializeAsBytes(value)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V getSet(final String key, final V value, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().getSet(key, serializer.serialize(value)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V getSet(final byte[] key, final V value, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().getSet(key, serializer.serializeAsBytes(value)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> mGet(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.stringCommands().mGet(keys),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> mGet(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.stringCommands().mGet(keys),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> mGet(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().mGet(keys),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> mGet(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().mGet(keys),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> Status pSetEx(final String key, final V value, final int lifetime) {
		return pSetEx(key, serializer.serialize(value), lifetime);
	}

	@Override
	public <V> Status pSetEx(final byte[] key, final V value, final int lifetime) {
		return pSetEx(key, serializer.serializeAsBytes(value), lifetime);
	}

	@Override
	public <V> Status set(final String key, final V value) {
		return set(key, serializer.serialize(value));
	}

	@Override
	public <V> Status set(final byte[] key, final V value) {
		return set(key, serializer.serializeAsBytes(value));
	}

	@Override
	public <V> Status set(final String key, final V value, final SetArgument argument) {
		return set(key, serializer.serialize(value), argument);
	}

	@Override
	public <V> Status set(final byte[] key, final V value, final SetArgument argument) {
		return set(key, serializer.serializeAsBytes(value), argument);
	}

	@Override
	public <V> Status setEx(final String key, final V value, final int lifetime) {
		return setEx(key, serializer.serialize(value), lifetime);
	}

	@Override
	public <V> Status setEx(final byte[] key, final V value, final int lifetime) {
		return setEx(key, serializer.serializeAsBytes(value), lifetime);
	}

	@Override
	public <V> Status setNx(final String key, final V value) {
		return setNx(key, serializer.serialize(value));
	}

	@Override
	public <V> Status setNx(final byte[] key, final V value) {
		return setNx(key, serializer.serializeAsBytes(value));
	}

}
