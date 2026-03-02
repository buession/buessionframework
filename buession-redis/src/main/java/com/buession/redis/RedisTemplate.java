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

import com.buession.core.collect.Maps;
import com.buession.core.type.TypeReference;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.Direction;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.args.GetExArgument;
import com.buession.redis.core.command.args.HSetExArgument;
import com.buession.redis.core.command.args.JsonGetArgument;
import com.buession.redis.core.command.args.SetArgument;
import com.buession.redis.core.command.args.ZRangeArgument;
import com.buession.redis.core.operations.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Redis 命令操作封装扩展，可序列化对象和反序列化为对象
 *
 * @author Yong.Teng
 * @see AbstractRedisTemplate
 */
public class RedisTemplate extends AbstractRedisTemplate
		implements BloomFilterOperations, BitMapOperations, CuckooFilterOperations, ClusterOperations,
		CountMinSketchOperations, ConnectionOperations, GenericOperations, GeoOperations, HashOperations,
		HyperLogLogOperations, JsonOperations, ListOperations, PubSubOperations, ScriptingOperations,
		/*SearchOperations, */ServerOperations, SetOperations, SortedSetOperations, StreamOperations, StringOperations,
		TransactionOperations, KeyOperations {

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
	public <V> V hGetObject(final String key, final String field) {
		return execute((client)->client.hashOperations().hGet(key, field), new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field) {
		return execute((client)->client.hashOperations().hGet(key, field), new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V hGetObject(final String key, final String field, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hGet(key, field),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hGet(key, field),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V hGetObject(final String key, final String field, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hGet(key, field),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hGet(key, field),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key) {
		return execute((client)->client.hashOperations().hGetAll(key), new Converter.SimpleMapStringConverter<>(this));
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key) {
		return execute((client)->client.hashOperations().hGetAll(key), new Converter.SimpleMapBinaryConverter<>(this));
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hGetAll(key),
				new Converter.ClazzMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hGetAll(key),
				new Converter.ClazzMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hGetAll(key),
				new Converter.TypeMapStringConverter<>(this, type));
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hGetAll(key),
				new Converter.TypeMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetDelObject(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hGetDel(key, fields),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> hGetDelObject(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hGetDel(key, fields),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> hGetDelObject(final String key, final String[] fields, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hGetDel(key, fields),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetDelObject(final byte[] key, final byte[][] fields, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hGetDel(key, fields),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetDelObject(final String key, final String[] fields, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hGetDel(key, fields),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetDelObject(final byte[] key, final byte[][] fields, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hGetDel(key, fields),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetExObject(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hGetEx(key, fields),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> hGetExObject(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hGetEx(key, fields),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> hGetExObject(final String key, final String[] fields, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hGetEx(key, fields),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetExObject(final byte[] key, final byte[][] fields, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hGetEx(key, fields),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetExObject(final String key, final String[] fields, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hGetEx(key, fields),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetExObject(final byte[] key, final byte[][] fields, TypeReference<V> type) {
		return execute((client)->client.hashOperations().hGetEx(key, fields),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetExObject(final String key, final GetExArgument argument, final String... fields) {
		return execute((client)->client.hashOperations().hGetEx(key, argument, fields),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> hGetExObject(final byte[] key, final GetExArgument argument, final byte[]... fields) {
		return execute((client)->client.hashOperations().hGetEx(key, argument, fields),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> hGetExObject(final String key, final GetExArgument argument, final String[] fields,
									final Class<V> clazz) {
		return execute((client)->client.hashOperations().hGetEx(key, argument, fields),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetExObject(final byte[] key, final GetExArgument argument, final byte[][] fields,
									final Class<V> clazz) {
		return execute((client)->client.hashOperations().hGetEx(key, argument, fields),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetExObject(final String key, final GetExArgument argument, final String[] fields,
									final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hGetEx(key, argument, fields),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetExObject(final byte[] key, final GetExArgument argument, final byte[][] fields,
									final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hGetEx(key, argument, fields),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hMGet(key, fields),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hMGet(key, fields),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String[] fields, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hMGet(key, fields),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hMGet(key, fields),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String[] fields, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hMGet(key, fields),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hMGet(key, fields),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> Status hMSet(final String key, final List<KeyValue<String, V>> data) {
		return hMSet(key, listKeyValue2StringMap(data));
	}

	@Override
	public <V> Status hMSet(final byte[] key, final List<KeyValue<byte[], V>> data) {
		return hMSet(key, listKeyValue2BinaryMap(data));
	}

	@Override
	public <V> Map<String, V> hRandFieldWithValuesObject(final String key, final long count) {
		return execute((client)->client.hashOperations().hRandFieldWithValues(key, count),
				new Converter.SimpleMapStringConverter<>(this));
	}

	@Override
	public <V> Map<byte[], V> hRandFieldWithValuesObject(final byte[] key, final long count) {
		return execute((client)->client.hashOperations().hRandFieldWithValues(key, count),
				new Converter.SimpleMapBinaryConverter<>(this));
	}

	@Override
	public <V> Map<String, V> hRandFieldWithValuesObject(final String key, long count, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hRandFieldWithValues(key, count),
				new Converter.ClazzMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> Map<byte[], V> hRandFieldWithValuesObject(final byte[] key, final long count, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hRandFieldWithValues(key, count),
				new Converter.ClazzMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Map<String, V> hRandFieldWithValuesObject(final String key, final long count,
														 final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hRandFieldWithValues(key, count),
				new Converter.TypeMapStringConverter<>(this, type));
	}

	@Override
	public <V> Map<byte[], V> hRandFieldWithValuesObject(final byte[] key, final long count,
														 final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hRandFieldWithValues(key, count),
				new Converter.TypeMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor) {
		return execute((client)->client.hashOperations().hScan(key, cursor),
				new Converter.SimpleScanResultMapStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor) {
		return execute((client)->client.hashOperations().hScan(key, cursor),
				new Converter.SimpleScanResultMapBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(key, cursor),
				new Converter.ClazzScanResultMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(key, cursor),
				new Converter.ClazzScanResultMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor,
													  final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(key, cursor),
				new Converter.TypeScanResultMapStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor,
													  final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(key, cursor),
				new Converter.TypeScanResultMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern) {
		return execute((client)->client.hashOperations().hScan(key, cursor, pattern),
				new Converter.SimpleScanResultMapStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern) {
		return execute((client)->client.hashOperations().hScan(key, cursor, pattern),
				new Converter.SimpleScanResultMapBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
													  final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(key, cursor, pattern),
				new Converter.ClazzScanResultMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
													  final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(key, cursor, pattern),
				new Converter.ClazzScanResultMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
													  final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(key, cursor, pattern),
				new Converter.TypeScanResultMapStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
													  final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(key, cursor, pattern),
				new Converter.TypeScanResultMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final long count) {
		return execute((client)->client.hashOperations().hScan(key, cursor, count),
				new Converter.SimpleScanResultMapStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final long count) {
		return execute((client)->client.hashOperations().hScan(key, cursor, count),
				new Converter.SimpleScanResultMapBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final long count,
													  final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(key, cursor, count),
				new Converter.ClazzScanResultMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final long count,
													  final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(key, cursor, count),
				new Converter.ClazzScanResultMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final long count,
													  final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(key, cursor, count),
				new Converter.TypeScanResultMapStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final long count,
													  final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(key, cursor, count),
				new Converter.TypeScanResultMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
													  final long count) {
		return execute((client)->client.hashOperations().hScan(key, cursor, pattern, count),
				new Converter.SimpleScanResultMapStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
													  final long count) {
		return execute((client)->client.hashOperations().hScan(key, cursor, pattern, count),
				new Converter.SimpleScanResultMapBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
													  final long count, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(key, cursor, pattern, count),
				new Converter.ClazzScanResultMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
													  final long count, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(key, cursor, pattern, count),
				new Converter.ClazzScanResultMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
													  final long count, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(key, cursor, pattern, count),
				new Converter.TypeScanResultMapStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
													  final long count, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(key, cursor, pattern, count),
				new Converter.TypeScanResultMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> Long hSet(final String key, final List<KeyValue<String, V>> data) {
		return hSet(key, listKeyValue2StringMap(data));
	}

	@Override
	public <V> Long hSet(final byte[] key, final List<KeyValue<byte[], V>> data) {
		return hSet(key, listKeyValue2BinaryMap(data));
	}

	@Override
	public <V> Status hSetEx(final String key, final List<KeyValue<String, V>> data) {
		return hSetEx(key, listKeyValue2StringMap(data));
	}

	@Override
	public <V> Status hSetEx(final byte[] key, final List<KeyValue<byte[], V>> data) {
		return hSetEx(key, listKeyValue2BinaryMap(data));
	}

	@Override
	public <V> Status hSetEx(final String key, final List<KeyValue<String, V>> data, final HSetExArgument argument) {
		return hSetEx(key, listKeyValue2StringMap(data), argument);
	}

	@Override
	public <V> Status hSetEx(final byte[] key, final List<KeyValue<byte[], V>> data, final HSetExArgument argument) {
		return hSetEx(key, listKeyValue2BinaryMap(data), argument);
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
	public <V> List<V> hValsObject(final String key) {
		return execute((client)->client.hashOperations().hVals(key), new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key) {
		return execute((client)->client.hashOperations().hVals(key), new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> hValsObject(final String key, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hVals(key),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hVals(key),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hValsObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hVals(key),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hVals(key),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V jsonGetObject(final String key) {
		return execute((client)->client.jsonOperations().jsonGet(key), new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V jsonGetObject(final byte[] key) {
		return execute((client)->client.jsonOperations().jsonGet(key), new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V jsonGetObject(final String key, final Class<V> clazz) {
		return execute((client)->client.jsonOperations().jsonGet(key),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V jsonGetObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.jsonOperations().jsonGet(key),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V jsonGetObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.jsonOperations().jsonGet(key), new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V jsonGetObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.jsonOperations().jsonGet(key), new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V jsonGetObject(final String key, final JsonGetArgument argument) {
		return execute((client)->client.jsonOperations().jsonGet(key, argument),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V jsonGetObject(final byte[] key, final JsonGetArgument argument) {
		return execute((client)->client.jsonOperations().jsonGet(key, argument),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V jsonGetObject(final String key, final JsonGetArgument argument, final Class<V> clazz) {
		return execute((client)->client.jsonOperations().jsonGet(key, argument),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V jsonGetObject(final byte[] key, final JsonGetArgument argument, final Class<V> clazz) {
		return execute((client)->client.jsonOperations().jsonGet(key, argument),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V jsonGetObject(final String key, final JsonGetArgument argument, final TypeReference<V> type) {
		return execute((client)->client.jsonOperations().jsonGet(key), new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V jsonGetObject(final byte[] key, final JsonGetArgument argument, final TypeReference<V> type) {
		return execute((client)->client.jsonOperations().jsonGet(key), new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonGetObject(final String key, final String... path) {
		return execute((client)->client.jsonOperations().jsonGet(key, path),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> jsonGetObject(final byte[] key, final byte[]... path) {
		return execute((client)->client.jsonOperations().jsonGet(key, path),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> jsonGetObject(final String key, final String[] path, final Class<V> clazz) {
		return execute((client)->client.jsonOperations().jsonGet(key, path),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonGetObject(final byte[] key, final byte[][] path, final Class<V> clazz) {
		return execute((client)->client.jsonOperations().jsonGet(key, path),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonGetObject(final String key, final String[] path, final TypeReference<V> type) {
		return execute((client)->client.jsonOperations().jsonGet(key, path),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonGetObject(final byte[] key, final byte[][] path, final TypeReference<V> type) {
		return execute((client)->client.jsonOperations().jsonGet(key, path),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonGetObject(final String key, final JsonGetArgument argument, final String... path) {
		return execute((client)->client.jsonOperations().jsonGet(key, argument, path),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> jsonGetObject(final byte[] key, final JsonGetArgument argument, final byte[]... path) {
		return execute((client)->client.jsonOperations().jsonGet(key, argument, path),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> jsonGetObject(final String key, final JsonGetArgument argument, final String[] path,
									 final Class<V> clazz) {
		return execute((client)->client.jsonOperations().jsonGet(key, argument, path),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonGetObject(final byte[] key, final JsonGetArgument argument, final byte[][] path,
									 final Class<V> clazz) {
		return execute((client)->client.jsonOperations().jsonGet(key, argument, path),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonGetObject(final String key, final JsonGetArgument argument, final String[] path,
									 final TypeReference<V> type) {
		return execute((client)->client.jsonOperations().jsonGet(key, argument, path),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonGetObject(final byte[] key, final JsonGetArgument argument, final byte[][] path,
									 final TypeReference<V> type) {
		return execute((client)->client.jsonOperations().jsonGet(key, argument, path),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonMGetObject(final String[] keys, final String path) {
		return execute((client)->client.jsonOperations().jsonMGet(keys, path),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> jsonMGetObject(final byte[][] keys, final byte[] path) {
		return execute((client)->client.jsonOperations().jsonMGet(keys, path),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> jsonMGetObject(final String[] keys, final String path, final Class<V> clazz) {
		return execute((client)->client.jsonOperations().jsonMGet(keys, path),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonMGetObject(final byte[][] keys, final byte[] path, final Class<V> clazz) {
		return execute((client)->client.jsonOperations().jsonMGet(keys, path),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonMGetObject(final String[] keys, final String path, final TypeReference<V> type) {
		return execute((client)->client.jsonOperations().jsonMGet(keys, path),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonMGetObject(final byte[][] keys, final byte[] path, final TypeReference<V> type) {
		return execute((client)->client.jsonOperations().jsonMGet(keys, path),
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
	public <V> V blMoveObject(final String key, final String destKey, final Direction from, final Direction to,
							  final int timeout) {
		return execute((client)->client.listOperations().blMove(rawKey(destKey), rawKey(destKey), from, to, timeout),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V blMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
							  final int timeout) {
		return execute((client)->client.listOperations().blMove(rawKey(destKey), rawKey(destKey), from, to, timeout),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V blMoveObject(final String key, final String destKey, final Direction from, final Direction to,
							  final int timeout, final Class<V> clazz) {
		return execute((client)->client.listOperations().blMove(rawKey(destKey), rawKey(destKey), from, to, timeout),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V blMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
							  final int timeout, final Class<V> clazz) {
		return execute((client)->client.listOperations().blMove(rawKey(destKey), rawKey(destKey), from, to, timeout),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V blMoveObject(final String key, final String destKey, final Direction from, final Direction to,
							  final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listOperations().blMove(rawKey(destKey), rawKey(destKey), from, to, timeout),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V blMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
							  final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listOperations().blMove(rawKey(destKey), rawKey(destKey), from, to, timeout),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<String, List<V>> blMPopObject(final int timeout, final String[] keys,
													  final Direction direction) {
		return execute((client)->client.listOperations().blMPop(timeout, keys),
				new Converter.TypeListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> blMPopObject(final int timeout, final byte[][] keys,
													  final Direction direction) {
		return execute((client)->client.listOperations().blMPop(timeout, keys),
				new Converter.TypeListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<String, List<V>> blMPopObject(final int timeout, final String[] keys, final Direction direction,
													  final Class<V> clazz) {
		return execute((client)->client.listOperations().blMPop(timeout, keys, direction),
				new Converter.TypeListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> blMPopObject(final int timeout, final byte[][] keys, final Direction direction,
													  final Class<V> clazz) {
		return execute((client)->client.listOperations().blMPop(timeout, keys, direction),
				new Converter.TypeListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<String, List<V>> blMPopObject(final int timeout, final String[] keys, final Direction direction,
													  final TypeReference<V> type) {
		return execute((client)->client.listOperations().blMPop(timeout, keys, direction),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> blMPopObject(final int timeout, final byte[][] keys, final Direction direction,
													  final TypeReference<V> type) {
		return execute((client)->client.listOperations().blMPop(timeout, keys, direction),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> blPopObject(final String[] keys, final int timeout) {
		return execute((client)->client.listOperations().blPop(keys, timeout),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> blPopObject(final byte[][] keys, final int timeout) {
		return execute((client)->client.listOperations().blPop(keys, timeout),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> blPopObject(final String[] keys, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listOperations().blPop(keys, timeout),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> blPopObject(final byte[][] keys, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listOperations().blPop(keys, timeout),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> blPopObject(final String[] keys, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listOperations().blPop(keys, timeout),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> blPopObject(final byte[][] keys, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listOperations().blPop(keys, timeout),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> brPopObject(final String[] keys, final int timeout) {
		return execute((client)->client.listOperations().brPop(keys, timeout),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> brPopObject(final byte[][] keys, final int timeout) {
		return execute((client)->client.listOperations().brPop(keys, timeout),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> brPopObject(final String[] keys, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listOperations().brPop(keys, timeout),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> brPopObject(final byte[][] keys, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listOperations().brPop(keys, timeout),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> brPopObject(final String[] keys, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listOperations().brPop(keys, timeout),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> brPopObject(final byte[][] keys, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listOperations().brPop(keys, timeout),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V brPoplPushObject(final String key, final String destKey, final int timeout) {
		return execute((client)->client.listOperations().brPoplPush(key, rawKey(destKey), timeout),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout) {
		return execute((client)->client.listOperations().brPoplPush(key, rawKey(destKey), timeout),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V brPoplPushObject(final String key, final String destKey, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listOperations().brPoplPush(key, rawKey(destKey), timeout),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listOperations().brPoplPush(key, rawKey(destKey), timeout),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V brPoplPushObject(final String key, final String destKey, final int timeout,
								  final TypeReference<V> type) {
		return execute((client)->client.listOperations().brPoplPush(key, rawKey(destKey), timeout),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout,
								  final TypeReference<V> type) {
		return execute((client)->client.listOperations().brPoplPush(key, rawKey(destKey), timeout),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V lIndexObject(final String key, final long index) {
		return execute((client)->client.listOperations().lIndex(key, index),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index) {
		return execute((client)->client.listOperations().lIndex(key, index),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V lIndexObject(final String key, final long index, final Class<V> clazz) {
		return execute((client)->client.listOperations().lIndex(key, index),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index, final Class<V> clazz) {
		return execute((client)->client.listOperations().lIndex(key, index),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V lIndexObject(final String key, final long index, final TypeReference<V> type) {
		return execute((client)->client.listOperations().lIndex(key, index),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index, final TypeReference<V> type) {
		return execute((client)->client.listOperations().lIndex(key, index),
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
	public <V> V lMoveObject(final String key, final String destKey, final Direction from, final Direction to) {
		return execute((client)->client.listOperations().lMove(rawKey(destKey), rawKey(destKey), from, to),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V lMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to) {
		return execute((client)->client.listOperations().lMove(rawKey(destKey), rawKey(destKey), from, to),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V lMoveObject(final String key, final String destKey, final Direction from, final Direction to,
							 final Class<V> clazz) {
		return execute((client)->client.listOperations().lMove(rawKey(destKey), rawKey(destKey), from, to),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V lMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
							 final Class<V> clazz) {
		return execute((client)->client.listOperations().lMove(rawKey(destKey), rawKey(destKey), from, to),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V lMoveObject(final String key, final String destKey, final Direction from, final Direction to,
							 final TypeReference<V> type) {
		return execute((client)->client.listOperations().lMove(rawKey(destKey), rawKey(destKey), from, to),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V lMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
							 final TypeReference<V> type) {
		return execute((client)->client.listOperations().lMove(rawKey(destKey), rawKey(destKey), from, to),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<String, List<V>> lMPopObject(final int timeout, final String[] keys,
													 final Direction direction) {
		return execute((client)->client.listOperations().lMPop(timeout, keys),
				new Converter.TypeListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> lMPopObject(final int timeout, final byte[][] keys,
													 final Direction direction) {
		return execute((client)->client.listOperations().lMPop(timeout, keys),
				new Converter.TypeListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<String, List<V>> lMPopObject(final int timeout, final String[] keys, final Direction direction,
													 final Class<V> clazz) {
		return execute((client)->client.listOperations().lMPop(timeout, keys, direction),
				new Converter.TypeListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> lMPopObject(final int timeout, final byte[][] keys, final Direction direction,
													 final Class<V> clazz) {
		return execute((client)->client.listOperations().lMPop(timeout, keys, direction),
				new Converter.TypeListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<String, List<V>> lMPopObject(final int timeout, final String[] keys, final Direction direction,
													 final TypeReference<V> type) {
		return execute((client)->client.listOperations().lMPop(timeout, keys, direction),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> lMPopObject(final int timeout, final byte[][] keys, final Direction direction,
													 final TypeReference<V> type) {
		return execute((client)->client.listOperations().lMPop(timeout, keys, direction),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V lPopObject(final String key) {
		return execute((client)->client.listOperations().lPop(key), new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V lPopObject(final byte[] key) {
		return execute((client)->client.listOperations().lPop(key), new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V lPopObject(final String key, final Class<V> clazz) {
		return execute((client)->client.listOperations().lPop(key), new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V lPopObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.listOperations().lPop(key), new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V lPopObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.listOperations().lPop(key), new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V lPopObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.listOperations().lPop(key), new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> lPopObject(final String key, final int count) {
		return execute((client)->client.listOperations().lPop(key, count),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> lPopObject(final byte[] key, final int count) {
		return execute((client)->client.listOperations().lPop(key, count),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> lPopObject(final String key, final int count, final Class<V> clazz) {
		return execute((client)->client.listOperations().lPop(key, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> lPopObject(final byte[] key, final int count, final Class<V> clazz) {
		return execute((client)->client.listOperations().lPop(key, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> lPopObject(final String key, final int count, final TypeReference<V> type) {
		return execute((client)->client.listOperations().lPop(key, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> lPopObject(final byte[] key, final int count, final TypeReference<V> type) {
		return execute((client)->client.listOperations().lPop(key, count),
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
	public <V> List<V> lRangeObject(final String key, final long start, final long end) {
		return execute((client)->client.listOperations().lRange(key, start, end),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end) {
		return execute((client)->client.listOperations().lRange(key, start, end),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.listOperations().lRange(key, start, end),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.listOperations().lRange(key, start, end),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.listOperations().lRange(key, start, end),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.listOperations().lRange(key, start, end),
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
	public <V> V rPopObject(final String key) {
		return execute((client)->client.listOperations().rPop(key), new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V rPopObject(final byte[] key) {
		return execute((client)->client.listOperations().rPop(key), new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V rPopObject(final String key, final Class<V> clazz) {
		return execute((client)->client.listOperations().rPop(key), new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V rPopObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.listOperations().rPop(key), new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V rPopObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.listOperations().rPop(key), new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V rPopObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.listOperations().rPop(key), new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V rPoplPushObject(final String key, final String destKey) {
		return execute((client)->client.listOperations().rPoplPush(key, rawKey(destKey)),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V rPoplPushObject(final byte[] key, final byte[] destKey) {
		return execute((client)->client.listOperations().rPoplPush(key, rawKey(destKey)),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V rPoplPushObject(final String key, final String destKey, final Class<V> clazz) {
		return execute((client)->client.listOperations().rPoplPush(key, rawKey(destKey)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V rPoplPushObject(final byte[] key, final byte[] destKey, final Class<V> clazz) {
		return execute((client)->client.listOperations().rPoplPush(key, rawKey(destKey)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V rPoplPushObject(final String key, final String destKey, final TypeReference<V> type) {
		return execute((client)->client.listOperations().rPoplPush(key, rawKey(destKey)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V rPoplPushObject(final byte[] key, final byte[] destKey, final TypeReference<V> type) {
		return execute((client)->client.listOperations().rPoplPush(key, rawKey(destKey)),
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
	public <V> Set<V> sDiffObject(final String[] keys) {
		return execute((client)->client.setOperations().sDiff(keys), new Converter.SimpleSetStringConverter<>(this));
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[][] keys) {
		return execute((client)->client.setOperations().sDiff(keys), new Converter.SimpleSetBinaryConverter<>(this));
	}

	@Override
	public <V> Set<V> sDiffObject(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.setOperations().sDiff(keys),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.setOperations().sDiff(keys),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sDiffObject(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sDiff(keys),
				new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sDiff(keys),
				new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sInterObject(final String[] keys) {
		return execute((client)->client.setOperations().sInter(keys), new Converter.SimpleSetStringConverter<>(this));
	}

	@Override
	public <V> Set<V> sInterObject(final byte[][] keys) {
		return execute((client)->client.setOperations().sInter(keys), new Converter.SimpleSetBinaryConverter<>(this));
	}

	@Override
	public <V> Set<V> sInterObject(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.setOperations().sInter(keys),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sInterObject(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.setOperations().sInter(keys),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sInterObject(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sInter(keys),
				new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sInterObject(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sInter(keys),
				new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sMembersObject(final String key) {
		return execute((client)->client.setOperations().sMembers(key), new Converter.SimpleSetStringConverter<>(this));
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key) {
		return execute((client)->client.setOperations().sMembers(key), new Converter.SimpleSetBinaryConverter<>(this));
	}

	@Override
	public <V> Set<V> sMembersObject(final String key, final Class<V> clazz) {
		return execute((client)->client.setOperations().sMembers(key),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.setOperations().sMembers(key),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sMembersObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sMembers(key),
				new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sMembers(key),
				new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> V sPopObject(final String key) {
		return execute((client)->client.setOperations().sPop(key), new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V sPopObject(final byte[] key) {
		return execute((client)->client.setOperations().sPop(key), new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V sPopObject(final String key, final Class<V> clazz) {
		return execute((client)->client.setOperations().sPop(key), new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V sPopObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.setOperations().sPop(key), new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V sPopObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sPop(key), new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V sPopObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sPop(key), new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sPopObject(final String key, final long count) {
		return execute((client)->client.setOperations().sPop(key, count),
				new Converter.SimpleSetStringConverter<>(this));
	}

	@Override
	public <V> Set<V> sPopObject(final byte[] key, final long count) {
		return execute((client)->client.setOperations().sPop(key, count),
				new Converter.SimpleSetBinaryConverter<>(this));
	}

	@Override
	public <V> Set<V> sPopObject(final String key, final long count, final Class<V> clazz) {
		return execute((client)->client.setOperations().sPop(key, count),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sPopObject(final byte[] key, final long count, final Class<V> clazz) {
		return execute((client)->client.setOperations().sPop(key, count),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sPopObject(final String key, final long count, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sPop(key, count),
				new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sPopObject(final byte[] key, final long count, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sPop(key, count),
				new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> V sRandMemberObject(final String key) {
		return execute((client)->client.setOperations().sRandMember(key), new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key) {
		return execute((client)->client.setOperations().sRandMember(key), new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V sRandMemberObject(final String key, final Class<V> clazz) {
		return execute((client)->client.setOperations().sRandMember(key),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.setOperations().sRandMember(key),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V sRandMemberObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sRandMember(key),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sRandMember(key),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count) {
		return execute((client)->client.setOperations().sRandMember(key, count),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count) {
		return execute((client)->client.setOperations().sRandMember(key, count),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count, final Class<V> clazz) {
		return execute((client)->client.setOperations().sRandMember(key, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count, final Class<V> clazz) {
		return execute((client)->client.setOperations().sRandMember(key, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sRandMember(key, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sRandMember(key, count),
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
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor) {
		return execute((client)->client.setOperations().sScan(key, cursor),
				new Converter.SimpleScanResultListStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor) {
		return execute((client)->client.setOperations().sScan(key, cursor),
				new Converter.SimpleScanResultListBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(key, cursor),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(key, cursor),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(key, cursor),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(key, cursor),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern) {
		return execute((client)->client.setOperations().sScan(key, cursor, pattern),
				new Converter.SimpleScanResultListStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern) {
		return execute((client)->client.setOperations().sScan(key, cursor, pattern),
				new Converter.SimpleScanResultListBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern,
											   final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(key, cursor, pattern),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
											   final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(key, cursor, pattern),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern,
											   final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(key, cursor, pattern),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
											   final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(key, cursor, pattern),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final long count) {
		return execute((client)->client.setOperations().sScan(key, cursor, count),
				new Converter.SimpleScanResultListStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final long count) {
		return execute((client)->client.setOperations().sScan(key, cursor, count),
				new Converter.SimpleScanResultListBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final long count,
											   final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(key, cursor, count),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final long count,
											   final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(key, cursor, count),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final long count,
											   final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(key, cursor, count),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final long count,
											   final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(key, cursor, count),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern,
											   final long count) {
		return execute((client)->client.setOperations().sScan(key, cursor, pattern, count),
				new Converter.SimpleScanResultListStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
											   final long count) {
		return execute((client)->client.setOperations().sScan(key, cursor, pattern, count),
				new Converter.SimpleScanResultListBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern,
											   final long count, final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(key, cursor, pattern, count),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
											   final long count, final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(key, cursor, pattern, count),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern,
											   final long count, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(key, cursor, pattern, count),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
											   final long count, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(key, cursor, pattern, count),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sUnionObject(final String[] keys) {
		return execute((client)->client.setOperations().sUnion(keys), new Converter.SimpleSetStringConverter<>(this));
	}

	@Override
	public <V> Set<V> sUnionObject(final byte[][] keys) {
		return execute((client)->client.setOperations().sUnion(keys), new Converter.SimpleSetBinaryConverter<>(this));
	}

	@Override
	public <V> Set<V> sUnionObject(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.setOperations().sUnion(keys),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sUnionObject(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.setOperations().sUnion(keys),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sUnionObject(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sUnion(keys),
				new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sUnionObject(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sUnion(keys),
				new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zDiffObject(final String[] keys) {
		return execute((client)->client.sortedSetOperations().zDiff(keys),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zDiffObject(final byte[][] keys) {
		return execute((client)->client.sortedSetOperations().zDiff(keys),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zDiffObject(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zDiff(keys),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zDiffObject(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zDiff(keys),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zDiffObject(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zDiff(keys),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zDiffObject(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zDiff(keys),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys) {
		return execute((client)->client.sortedSetOperations().zInter(keys),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys) {
		return execute((client)->client.sortedSetOperations().zInter(keys),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zInter(keys),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zInter(keys),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zInter(keys),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zInter(keys),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zInter(keys, aggregate),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zInter(keys, aggregate),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final Aggregate aggregate, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zInter(keys, aggregate),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zInter(keys, aggregate),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final Aggregate aggregate, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zInter(keys, aggregate),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zInter(keys, aggregate),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final double[] weights) {
		return execute((client)->client.sortedSetOperations().zInter(keys, weights),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final double[] weights) {
		return execute((client)->client.sortedSetOperations().zInter(keys, weights),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final double[] weights, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zInter(keys, weights),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final double[] weights, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zInter(keys, weights),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final double[] weights, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zInter(keys, weights),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final double[] weights, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zInter(keys, weights),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final Aggregate aggregate, final double[] weights) {
		return execute((client)->client.sortedSetOperations().zInter(keys, aggregate, weights),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate, final double[] weights) {
		return execute((client)->client.sortedSetOperations().zInter(keys, aggregate, weights),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final Aggregate aggregate, final double[] weights,
									final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zInter(keys, aggregate, weights),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate, final double[] weights,
									final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zInter(keys, aggregate, weights),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final Aggregate aggregate, final double[] weights,
									final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zInter(keys, aggregate, weights),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate, final double[] weights,
									final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zInter(keys, aggregate, weights),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V zRandMemberObject(final String key) {
		return execute((client)->client.sortedSetOperations().zRandMember(key),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V zRandMemberObject(final byte[] key) {
		return execute((client)->client.sortedSetOperations().zRandMember(key),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V zRandMemberObject(final String key, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRandMember(key),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V zRandMemberObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRandMember(key),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V zRandMemberObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRandMember(key),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V zRandMemberObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRandMember(key),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRandMemberObject(final String key, final int count) {
		return execute((client)->client.sortedSetOperations().zRandMember(key, count),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRandMemberObject(final byte[] key, final int count) {
		return execute((client)->client.sortedSetOperations().zRandMember(key, count),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRandMemberObject(final String key, final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRandMember(key, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRandMemberObject(final byte[] key, final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRandMember(key, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRandMemberObject(final String key, final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRandMember(key, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRandMemberObject(final byte[] key, final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRandMember(key, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeObject(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeObject(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeObject(final String key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeObject(final byte[] key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeObject(final String key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeObject(final byte[] key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeObject(final String key, final long start, final long end, final ZRangeArgument argument) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, argument),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeObject(final byte[] key, final long start, final long end, final ZRangeArgument argument) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, argument),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeObject(final String key, final long start, final long end, final ZRangeArgument argument,
									final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, argument),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeObject(final byte[] key, final long start, final long end, final ZRangeArgument argument,
									final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, argument),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeObject(final String key, final long start, final long end, final ZRangeArgument argument,
									final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, argument),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeObject(final byte[] key, final long start, final long end, final ZRangeArgument argument,
									final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, argument),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeObject(final String key, final long start, final long end, final int offset,
									final int count) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, offset, count),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeObject(final byte[] key, final long start, final long end, final int offset,
									final int count) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, offset, count),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeObject(final String key, final long start, final long end, final int offset,
									final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeObject(final byte[] key, final long start, final long end, final int offset,
									final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeObject(final String key, final long start, final long end, final int offset,
									final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeObject(final byte[] key, final long start, final long end, final int offset,
									final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeObject(final String key, final long start, final long end, final ZRangeArgument argument,
									final int offset, final int count) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, argument, offset, count),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeObject(final byte[] key, final long start, final long end, final ZRangeArgument argument,
									final int offset, final int count) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, argument, offset, count),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeObject(final String key, final long start, final long end, final ZRangeArgument argument,
									final int offset, final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, argument, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeObject(final byte[] key, final long start, final long end, final ZRangeArgument argument,
									final int offset, final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, argument, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeObject(final String key, final long start, final long end, final ZRangeArgument argument,
									final int offset, final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, argument, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeObject(final byte[] key, final long start, final long end, final ZRangeArgument argument,
									final int offset, final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end, argument, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(key, min, max),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(key, min, max),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final String key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(key, min, max),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final byte[] key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(key, min, max),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final String key, final double min, final double max,
										 final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(key, min, max),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final byte[] key, final double min, final double max,
										 final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(key, min, max),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final String key, final double min, final double max, final int offset,
										 final int count) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(key, min, max, offset, count),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final byte[] key, final double min, final double max, final int offset,
										 final int count) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(key, min, max, offset, count),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final String key, final double min, final double max, final int offset,
										 final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(key, min, max, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final byte[] key, final double min, final double max, final int offset,
										 final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(key, min, max, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final String key, final double min, final double max, final int offset,
										 final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(key, min, max, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final byte[] key, final double min, final double max, final int offset,
										 final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(key, min, max, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final String key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final byte[] key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final String key, final double min, final double max,
										   final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final byte[] key, final double min, final double max,
										   final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final String key, final double min, final double max, final long offset,
										   final long count) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max, offset, count),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final byte[] key, final double min, final double max, final long offset,
										   final long count) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max, offset, count),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final String key, final double min, final double max, final long offset,
										   final long count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final byte[] key, final double min, final double max, final long offset,
										   final long count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final String key, final double min, final double max, final long offset,
										   final long count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final byte[] key, final double min, final double max, final long offset,
										   final long count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeObject(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRevRange(key, start, end),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeObject(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRevRange(key, start, end),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeObject(final String key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRange(key, start, end),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeObject(final byte[] key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRange(key, start, end),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeObject(final String key, final long start, final long end,
									   final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRange(key, start, end),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeObject(final byte[] key, final long start, final long end,
									   final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRange(key, start, end),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final String key, final double min, final double max,
											final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final byte[] key, final double min, final double max,
											final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final String key, final double min, final double max,
											final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final byte[] key, final double min, final double max,
											final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final String key, final double min, final double max, final int offset,
											final int count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final byte[] key, final double min, final double max, final int offset,
											final int count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final String key, final double min, final double max, final int offset,
											final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final byte[] key, final double min, final double max, final int offset,
											final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final String key, final double min, final double max, final int offset,
											final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final byte[] key, final double min, final double max, final int offset,
											final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final String key, final double min, final double max,
											  final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final byte[] key, final double min, final double max,
											  final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final String key, final double min, final double max,
											  final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final byte[] key, final double min, final double max,
											  final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final String key, final double min, final double max, final int offset,
											  final int count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final byte[] key, final double min, final double max, final int offset,
											  final int count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final String key, final double min, final double max, final int offset,
											  final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final byte[] key, final double min, final double max, final int offset,
											  final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final String key, final double min, final double max, final int offset,
											  final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final byte[] key, final double min, final double max, final int offset,
											  final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys) {
		return execute((client)->client.sortedSetOperations().zUnion(keys),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys) {
		return execute((client)->client.sortedSetOperations().zUnion(keys),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zUnion(keys),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zUnion(keys),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zUnion(keys),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zUnion(keys),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, aggregate),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, aggregate),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, aggregate),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, aggregate),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, aggregate),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, aggregate),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final double[] weights) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, weights),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final double[] weights) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, weights),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final double[] weights, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, weights),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final double[] weights, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, weights),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final double[] weights, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, weights),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final double[] weights, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, weights),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate, final double[] weights) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, aggregate, weights),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate, final double[] weights) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, aggregate, weights),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate, final double[] weights,
									final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, aggregate, weights),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate, final double[] weights,
									final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, aggregate, weights),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate, final double[] weights,
									final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, aggregate, weights),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate, final double[] weights,
									final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, aggregate, weights),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V getObject(final String key) {
		return execute((client)->client.stringOperations().get(key), new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V getObject(final byte[] key) {
		return execute((client)->client.stringOperations().get(key), new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V getObject(final String key, final Class<V> clazz) {
		return execute((client)->client.stringOperations().get(key), new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V getObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.stringOperations().get(key), new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V getObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().get(key), new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V getObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().get(key), new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V getDelObject(final String key) {
		return execute((client)->client.stringOperations().getDel(key), new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V getDelObject(final byte[] key) {
		return execute((client)->client.stringOperations().getDel(key), new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V getDelObject(final String key, final Class<V> clazz) {
		return execute((client)->client.stringOperations().getDel(key),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V getDelObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.stringOperations().getDel(key),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V getDelObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().getDel(key),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V getDelObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().getDel(key),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V getExObject(final String key, final GetExArgument getExArgument) {
		return execute((client)->client.stringOperations().getEx(key, getExArgument),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V getExObject(final byte[] key, final GetExArgument getExArgument) {
		return execute((client)->client.stringOperations().getEx(key, getExArgument),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V getExObject(final String key, final GetExArgument getExArgument, final Class<V> clazz) {
		return execute((client)->client.stringOperations().getEx(key, getExArgument),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V getExObject(final byte[] key, final GetExArgument getExArgument, final Class<V> clazz) {
		return execute((client)->client.stringOperations().getEx(key, getExArgument),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V getExObject(final String key, final GetExArgument getExArgument, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().getEx(key, getExArgument),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V getExObject(final byte[] key, final GetExArgument getExArgument, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().getEx(key, getExArgument),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V getSet(final String key, final V value) {
		return execute((client)->client.stringOperations().getSet(key, serializer.serialize(value)),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V getSet(final byte[] key, final V value) {
		return execute((client)->client.stringOperations().getSet(key, serializer.serializeAsBytes(value)),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V getSet(final String key, final V value, final Class<V> clazz) {
		return execute((client)->client.stringOperations().getSet(key, serializer.serialize(value)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V getSet(final byte[] key, final V value, final Class<V> clazz) {
		return execute((client)->client.stringOperations().getSet(key, serializer.serializeAsBytes(value)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V getSet(final String key, final V value, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().getSet(key, serializer.serialize(value)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V getSet(final byte[] key, final V value, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().getSet(key, serializer.serializeAsBytes(value)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> mGetObject(final String... keys) {
		return execute((client)->client.stringOperations().mGet(keys), new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> mGetObject(final byte[]... keys) {
		return execute((client)->client.stringOperations().mGet(keys), new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> mGetObject(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.stringOperations().mGet(keys),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> mGetObject(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.stringOperations().mGet(keys),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> mGetObject(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().mGet(keys),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> mGetObject(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().mGet(keys),
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

	protected <V> Map<String, String> listKeyValue2StringMap(final List<KeyValue<String, V>> data) {
		return data.stream().collect(
				Collectors.toMap(KeyValue::getKey, (e)->serializer.serialize(e.getValue()), (key1, key2)->key2,
						LinkedHashMap::new));
	}

	protected <V> Map<byte[], byte[]> listKeyValue2BinaryMap(final List<KeyValue<byte[], V>> data) {
		return data.stream().collect(
				Collectors.toMap(KeyValue::getKey, (e)->serializer.serializeAsBytes(e.getValue()), (key1, key2)->key2,
						LinkedHashMap::new));
	}

}
