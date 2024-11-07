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
package com.buession.redis;

import com.buession.core.type.TypeReference;
import com.buession.core.validator.Validate;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.Direction;
import com.buession.redis.core.GtLt;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.operations.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Redis 命令操作封装扩展，可序列化对象和反序列化为对象
 *
 * @author Yong.Teng
 * @see BaseRedisTemplate
 */
public class RedisTemplate extends BaseRedisTemplate implements BitMapOperations, ClusterOperations,
		ConnectionOperations, GenericOperations, GeoOperations, HashOperations, HyperLogLogOperations, KeyOperations,
		ListOperations, PubSubOperations, ScriptingOperations, ServerOperations, SetOperations, SortedSetOperations,
		StreamOperations, StringOperations, TransactionOperations {

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
		return execute((client)->client.hashOperations().hGet(rawKey(key), field),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field) {
		return execute((client)->client.hashOperations().hGet(rawKey(key), field),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V hGetObject(final String key, final String field, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hGet(rawKey(key), field),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hGet(rawKey(key), field),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V hGetObject(final String key, final String field, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hGet(rawKey(key), field),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hGet(rawKey(key), field),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key) {
		return execute((client)->client.hashOperations().hGetAll(rawKey(key)),
				new Converter.SimpleMapStringConverter<>(this));
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key) {
		return execute((client)->client.hashOperations().hGetAll(rawKey(key)),
				new Converter.SimpleMapBinaryConverter<>(this));
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hGetAll(rawKey(key)),
				new Converter.ClazzMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hGetAll(rawKey(key)),
				new Converter.ClazzMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hGetAll(rawKey(key)),
				new Converter.TypeMapStringConverter<>(this, type));
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hGetAll(rawKey(key)),
				new Converter.TypeMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hMGet(rawKey(key), fields),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hMGet(rawKey(key), fields),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String[] fields, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hMGet(rawKey(key), fields),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hMGet(rawKey(key), fields),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String[] fields, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hMGet(rawKey(key), fields),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hMGet(rawKey(key), fields),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public Status hMSet(final String key, final List<KeyValue<String, Object>> data) {
		Map<String, String> temp = data.stream()
				.collect(Collectors.toMap(KeyValue::getKey, (e)->serializer.serialize(e.getValue()),
						(key1, key2)->key2, LinkedHashMap::new));

		return hMSet(key, temp);
	}

	@Override
	public Status hMSet(final byte[] key, final List<KeyValue<byte[], Object>> data) {
		Map<byte[], byte[]> temp = data.stream()
				.collect(Collectors.toMap(KeyValue::getKey, (e)->serializer.serializeAsBytes(e.getValue()),
						(key1, key2)->key2, LinkedHashMap::new));

		return hMSet(key, temp);
	}

	@Override
	public <V> Map<String, V> hRandFieldWithValuesObject(final String key, final long count) {
		return execute((client)->client.hashOperations().hRandFieldWithValues(rawKey(key), count),
				new Converter.SimpleMapStringConverter<>(this));
	}

	@Override
	public <V> Map<byte[], V> hRandFieldWithValuesObject(final byte[] key, final long count) {
		return execute((client)->client.hashOperations().hRandFieldWithValues(rawKey(key), count),
				new Converter.SimpleMapBinaryConverter<>(this));
	}

	@Override
	public <V> Map<String, V> hRandFieldWithValuesObject(final String key, long count, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hRandFieldWithValues(rawKey(key), count),
				new Converter.ClazzMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> Map<byte[], V> hRandFieldWithValuesObject(final byte[] key, final long count, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hRandFieldWithValues(rawKey(key), count),
				new Converter.ClazzMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Map<String, V> hRandFieldWithValuesObject(final String key, final long count,
														 final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hRandFieldWithValues(rawKey(key), count),
				new Converter.TypeMapStringConverter<>(this, type));
	}

	@Override
	public <V> Map<byte[], V> hRandFieldWithValuesObject(final byte[] key, final long count,
														 final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hRandFieldWithValues(rawKey(key), count),
				new Converter.TypeMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor),
				new Converter.SimpleScanResultMapStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor),
				new Converter.SimpleScanResultMapBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor),
				new Converter.ClazzScanResultMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor),
				new Converter.ClazzScanResultMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor,
													  final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor),
				new Converter.TypeScanResultMapStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor,
													  final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor),
				new Converter.TypeScanResultMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor),
				new Converter.SimpleScanResultMapStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor),
				new Converter.SimpleScanResultMapBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor),
				new Converter.ClazzScanResultMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor),
				new Converter.ClazzScanResultMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor,
													  final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor),
				new Converter.TypeScanResultMapStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor,
													  final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor),
				new Converter.TypeScanResultMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final String pattern) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern),
				new Converter.SimpleScanResultMapStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final byte[] pattern) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern),
				new Converter.SimpleScanResultMapBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final String pattern,
													  final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern),
				new Converter.ClazzScanResultMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final byte[] pattern,
													  final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern),
				new Converter.ClazzScanResultMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final String pattern,
													  final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern),
				new Converter.TypeScanResultMapStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final byte[] pattern,
													  final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern),
				new Converter.TypeScanResultMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern),
				new Converter.SimpleScanResultMapStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern),
				new Converter.SimpleScanResultMapBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
													  final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern),
				new Converter.ClazzScanResultMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
													  final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern),
				new Converter.ClazzScanResultMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
													  final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern),
				new Converter.TypeScanResultMapStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
													  final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern),
				new Converter.TypeScanResultMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final long count) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, count),
				new Converter.SimpleScanResultMapStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final long count) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, count),
				new Converter.SimpleScanResultMapBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final long count,
													  final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, count),
				new Converter.ClazzScanResultMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final long count,
													  final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, count),
				new Converter.ClazzScanResultMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, long count,
													  TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, count),
				new Converter.TypeScanResultMapStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final long count,
													  final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, count),
				new Converter.TypeScanResultMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final long count) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, count),
				new Converter.SimpleScanResultMapStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final long count) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, count),
				new Converter.SimpleScanResultMapBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final long count,
													  final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, count),
				new Converter.ClazzScanResultMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final long count,
													  final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, count),
				new Converter.ClazzScanResultMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final long count,
													  final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, count),
				new Converter.TypeScanResultMapStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final long count,
													  final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, count),
				new Converter.TypeScanResultMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final String pattern,
													  final long count) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern, count),
				new Converter.SimpleScanResultMapStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final byte[] pattern,
													  final long count) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern, count),
				new Converter.SimpleScanResultMapBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final String pattern,
													  final long count, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern, count),
				new Converter.ClazzScanResultMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final byte[] pattern,
													  final long count, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern, count),
				new Converter.ClazzScanResultMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final String pattern,
													  final long count, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern, count),
				new Converter.TypeScanResultMapStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final byte[] pattern,
													  final long count, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern, count),
				new Converter.TypeScanResultMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
													  final long count) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern, count),
				new Converter.SimpleScanResultMapStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
													  final long count) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern, count),
				new Converter.SimpleScanResultMapBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
													  final long count, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern, count),
				new Converter.ClazzScanResultMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
													  final long count, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern, count),
				new Converter.ClazzScanResultMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
													  final long count, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern, count),
				new Converter.TypeScanResultMapStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
													  final long count, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern, count),
				new Converter.TypeScanResultMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> Long hSet(final String key, final String field, final V value) {
		return hSet(key, field, serializer.serialize(value));
	}

	@Override
	public <V> Long hSet(final byte[] key, final byte[] field, final V value) {
		return hSet(key, field, serializer.serializeAsBytes(value));
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
		return execute((client)->client.hashOperations().hVals(rawKey(key)),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key) {
		return execute((client)->client.hashOperations().hVals(rawKey(key)),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> hValsObject(final String key, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hVals(rawKey(key)),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.hashOperations().hVals(rawKey(key)),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hValsObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hVals(rawKey(key)),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.hashOperations().hVals(rawKey(key)),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V lIndexObject(final String key, final long index) {
		return execute((client)->client.listOperations().lIndex(rawKey(key), index),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index) {
		return execute((client)->client.listOperations().lIndex(rawKey(key), index),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V lIndexObject(final String key, final long index, final Class<V> clazz) {
		return execute((client)->client.listOperations().lIndex(rawKey(key), index),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index, final Class<V> clazz) {
		return execute((client)->client.listOperations().lIndex(rawKey(key), index),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V lIndexObject(final String key, final long index, final TypeReference<V> type) {
		return execute((client)->client.listOperations().lIndex(rawKey(key), index),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index, final TypeReference<V> type) {
		return execute((client)->client.listOperations().lIndex(rawKey(key), index),
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
	public <V> Status lSet(final String key, final long index, final V value) {
		return lSet(key, index, serializer.serialize(value));
	}

	@Override
	public <V> Status lSet(final byte[] key, final long index, final V value) {
		return lSet(key, index, serializer.serializeAsBytes(value));
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final long start, final long end) {
		return execute((client)->client.listOperations().lRange(rawKey(key), start, end),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end) {
		return execute((client)->client.listOperations().lRange(rawKey(key), start, end),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.listOperations().lRange(rawKey(key), start, end),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.listOperations().lRange(rawKey(key), start, end),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.listOperations().lRange(rawKey(key), start, end),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.listOperations().lRange(rawKey(key), start, end),
				new Converter.TypeListBinaryConverter<>(this, type));
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
	public <V> List<V> blPopObject(final String[] keys, final int timeout) {
		return execute((client)->client.listOperations().blPop(rawKeys(keys), timeout),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> blPopObject(final byte[][] keys, final int timeout) {
		return execute((client)->client.listOperations().blPop(rawKeys(keys), timeout),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> blPopObject(final String[] keys, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listOperations().blPop(rawKeys(keys), timeout),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> blPopObject(final byte[][] keys, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listOperations().blPop(rawKeys(keys), timeout),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> blPopObject(final String[] keys, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listOperations().blPop(rawKeys(keys), timeout),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> blPopObject(final byte[][] keys, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listOperations().blPop(rawKeys(keys), timeout),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> brPopObject(final String[] keys, final int timeout) {
		return execute((client)->client.listOperations().brPop(rawKeys(keys), timeout),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> brPopObject(final byte[][] keys, final int timeout) {
		return execute((client)->client.listOperations().brPop(rawKeys(keys), timeout),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> brPopObject(final String[] keys, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listOperations().brPop(rawKeys(keys), timeout),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> brPopObject(final byte[][] keys, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listOperations().brPop(rawKeys(keys), timeout),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> brPopObject(final String[] keys, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listOperations().brPop(rawKeys(keys), timeout),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> brPopObject(final byte[][] keys, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listOperations().brPop(rawKeys(keys), timeout),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V brPoplPushObject(final String key, final String destKey, final int timeout) {
		return execute((client)->client.listOperations().brPoplPush(rawKey(key), rawKey(destKey), timeout),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout) {
		return execute((client)->client.listOperations().brPoplPush(rawKey(key), rawKey(destKey), timeout),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V brPoplPushObject(final String key, final String destKey, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listOperations().brPoplPush(rawKey(key), rawKey(destKey), timeout),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listOperations().brPoplPush(rawKey(key), rawKey(destKey), timeout),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V brPoplPushObject(final String key, final String destKey, final int timeout,
								  final TypeReference<V> type) {
		return execute((client)->client.listOperations().brPoplPush(rawKey(key), rawKey(destKey), timeout),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout,
								  final TypeReference<V> type) {
		return execute((client)->client.listOperations().brPoplPush(rawKey(key), rawKey(destKey), timeout),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V lPopObject(final String key) {
		return execute((client)->client.listOperations().lPop(rawKey(key)),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V lPopObject(final byte[] key) {
		return execute((client)->client.listOperations().lPop(rawKey(key)),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V lPopObject(final String key, final Class<V> clazz) {
		return execute((client)->client.listOperations().lPop(rawKey(key)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V lPopObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.listOperations().lPop(rawKey(key)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V lPopObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.listOperations().lPop(rawKey(key)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V lPopObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.listOperations().lPop(rawKey(key)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> Long lPush(final String key, final V value) {
		return lPush(key, serializer.serialize(value));
	}

	@Override
	public <V> Long lPush(final byte[] key, final V value) {
		return lPush(key, serializer.serializeAsBytes(value));
	}

	@Override
	public <V> Long lPush(final String key, final V... values) {
		return lPush(key, serializer.serialize(values));
	}

	@Override
	public <V> Long lPush(final byte[] key, final V... values) {
		return lPush(key, serializer.serializeAsBytes(values));
	}

	@Override
	public <V> Long lPushX(final String key, final V value) {
		return lPushX(key, serializer.serialize(value));
	}

	@Override
	public <V> Long lPushX(final byte[] key, final V value) {
		return lPushX(key, serializer.serializeAsBytes(value));
	}

	@Override
	public <V> Long lPushX(final String key, final V... values) {
		return lPushX(key, serializer.serialize(values));
	}

	@Override
	public <V> Long lPushX(final byte[] key, final V... values) {
		return lPushX(key, serializer.serializeAsBytes(values));
	}

	@Override
	public <V> V rPopObject(final String key) {
		return execute((client)->client.listOperations().rPop(rawKey(key)),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V rPopObject(final byte[] key) {
		return execute((client)->client.listOperations().rPop(rawKey(key)),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V rPopObject(final String key, final Class<V> clazz) {
		return execute((client)->client.listOperations().rPop(rawKey(key)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V rPopObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.listOperations().rPop(rawKey(key)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V rPopObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.listOperations().rPop(rawKey(key)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V rPopObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.listOperations().rPop(rawKey(key)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V rPoplPushObject(final String key, final String destKey) {
		return execute((client)->client.listOperations().rPoplPush(rawKey(key), rawKey(destKey)),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V rPoplPushObject(final byte[] key, final byte[] destKey) {
		return execute((client)->client.listOperations().rPoplPush(rawKey(key), rawKey(destKey)),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V rPoplPushObject(final String key, final String destKey, final Class<V> clazz) {
		return execute((client)->client.listOperations().rPoplPush(rawKey(key), rawKey(destKey)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V rPoplPushObject(final byte[] key, final byte[] destKey, final Class<V> clazz) {
		return execute((client)->client.listOperations().rPoplPush(rawKey(key), rawKey(destKey)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V rPoplPushObject(final String key, final String destKey, final TypeReference<V> type) {
		return execute((client)->client.listOperations().rPoplPush(rawKey(key), rawKey(destKey)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V rPoplPushObject(final byte[] key, final byte[] destKey, final TypeReference<V> type) {
		return execute((client)->client.listOperations().rPoplPush(rawKey(key), rawKey(destKey)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> Long rPush(final String key, final V value) {
		return rPush(key, serializer.serialize(value));
	}

	@Override
	public <V> Long rPush(final byte[] key, final V value) {
		return rPush(key, serializer.serializeAsBytes(value));
	}

	@Override
	public <V> Long rPush(final String key, final V... values) {
		return rPush(key, serializer.serialize(values));
	}

	@Override
	public <V> Long rPush(final byte[] key, final V... values) {
		return rPush(key, serializer.serializeAsBytes(values));
	}

	@Override
	public <V> Long rPushX(final String key, final V value) {
		return rPushX(key, serializer.serialize(value));
	}

	@Override
	public <V> Long rPushX(final byte[] key, final V value) {
		return rPushX(key, serializer.serializeAsBytes(value));
	}

	@Override
	public <V> Long rPushX(final String key, final V... values) {
		return rPushX(key, serializer.serialize(values));
	}

	@Override
	public <V> Long rPushX(final byte[] key, final V... values) {
		return rPushX(key, serializer.serializeAsBytes(values));
	}

	@Override
	public <V> Long sAdd(final String key, final V... members) {
		return sAdd(key, serializer.serialize(members));
	}

	@Override
	public <V> Long sAdd(final byte[] key, final V... members) {
		return sAdd(key, serializer.serializeAsBytes(members));
	}

	@Override
	public <V> Set<V> sDiffObject(final String[] keys) {
		return execute((client)->client.setOperations().sDiff(rawKeys(keys)),
				new Converter.SimpleSetStringConverter<>(this));
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[][] keys) {
		return execute((client)->client.setOperations().sDiff(rawKeys(keys)),
				new Converter.SimpleSetBinaryConverter<>(this));
	}

	@Override
	public <V> Set<V> sDiffObject(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.setOperations().sDiff(rawKeys(keys)),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.setOperations().sDiff(rawKeys(keys)),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sDiffObject(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sDiff(rawKeys(keys)),
				new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sDiff(rawKeys(keys)),
				new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sInterObject(final String[] keys) {
		return execute((client)->client.setOperations().sInter(rawKeys(keys)),
				new Converter.SimpleSetStringConverter<>(this));
	}

	@Override
	public <V> Set<V> sInterObject(final byte[][] keys) {
		return execute((client)->client.setOperations().sInter(rawKeys(keys)),
				new Converter.SimpleSetBinaryConverter<>(this));
	}

	@Override
	public <V> Set<V> sInterObject(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.setOperations().sInter(rawKeys(keys)),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sInterObject(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.setOperations().sInter(rawKeys(keys)),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sInterObject(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sInter(rawKeys(keys)),
				new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sInterObject(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sInter(rawKeys(keys)),
				new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sMembersObject(final String key) {
		return execute((client)->client.setOperations().sMembers(rawKey(key)),
				new Converter.SimpleSetStringConverter<>(this));
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key) {
		return execute((client)->client.setOperations().sMembers(rawKey(key)),
				new Converter.SimpleSetBinaryConverter<>(this));
	}

	@Override
	public <V> Set<V> sMembersObject(final String key, final Class<V> clazz) {
		return execute((client)->client.setOperations().sMembers(rawKey(key)),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.setOperations().sMembers(rawKey(key)),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sMembersObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sMembers(rawKey(key)),
				new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sMembers(rawKey(key)),
				new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> V sPopObject(final String key) {
		return execute((client)->client.setOperations().sPop(rawKey(key)), new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V sPopObject(final byte[] key) {
		return execute((client)->client.setOperations().sPop(rawKey(key)), new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V sPopObject(final String key, final Class<V> clazz) {
		return execute((client)->client.setOperations().sPop(rawKey(key)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V sPopObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.setOperations().sPop(rawKey(key)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V sPopObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sPop(rawKey(key)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V sPopObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sPop(rawKey(key)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sPopObject(final String key, final long count) {
		return execute((client)->client.setOperations().sPop(rawKey(key), count),
				new Converter.SimpleSetStringConverter<>(this));
	}

	@Override
	public <V> Set<V> sPopObject(final byte[] key, final long count) {
		return execute((client)->client.setOperations().sPop(rawKey(key), count),
				new Converter.SimpleSetBinaryConverter<>(this));

	}

	@Override
	public <V> Set<V> sPopObject(final String key, final long count, final Class<V> clazz) {
		return execute((client)->client.setOperations().sPop(rawKey(key), count),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sPopObject(final byte[] key, final long count, final Class<V> clazz) {
		return execute((client)->client.setOperations().sPop(rawKey(key), count),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sPopObject(final String key, final long count, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sPop(rawKey(key), count),
				new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sPopObject(final byte[] key, final long count, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sPop(rawKey(key), count),
				new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> V sRandMemberObject(final String key) {
		return execute((client)->client.setOperations().sRandMember(rawKey(key)),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key) {
		return execute((client)->client.setOperations().sRandMember(rawKey(key)),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V sRandMemberObject(final String key, final Class<V> clazz) {
		return execute((client)->client.setOperations().sRandMember(rawKey(key)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.setOperations().sRandMember(rawKey(key)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V sRandMemberObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sRandMember(rawKey(key)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sRandMember(rawKey(key)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count) {
		return execute((client)->client.setOperations().sRandMember(rawKey(key), count),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count) {
		return execute((client)->client.setOperations().sRandMember(rawKey(key), count),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count, final Class<V> clazz) {
		return execute((client)->client.setOperations().sRandMember(rawKey(key), count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count, final Class<V> clazz) {
		return execute((client)->client.setOperations().sRandMember(rawKey(key), count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sRandMember(rawKey(key), count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sRandMember(rawKey(key), count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> Long sRem(final String key, final V member) {
		return sRem(key, serializer.serialize(member));
	}

	@Override
	public <V> Long sRem(final byte[] key, final V member) {
		return sRem(key, serializer.serializeAsBytes(member));
	}

	@Override
	public <V> Long sRem(final String key, final V... members) {
		return sRem(key, serializer.serialize(members));
	}

	@Override
	public <V> Long sRem(final byte[] key, final V... members) {
		return sRem(key, serializer.serializeAsBytes(members));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final long cursor) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor),
				new Converter.SimpleScanResultListStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor),
				new Converter.SimpleScanResultListBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor),
				new Converter.SimpleScanResultListStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor),
				new Converter.SimpleScanResultListBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor,
											   final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor,
											   final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final String pattern) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern),
				new Converter.SimpleScanResultListStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final byte[] pattern) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern),
				new Converter.SimpleScanResultListBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final String pattern,
											   final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final byte[] pattern,
											   final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final String pattern,
											   final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final byte[] pattern,
											   final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern),
				new Converter.SimpleScanResultListStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern),
				new Converter.SimpleScanResultListBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern,
											   final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
											   final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern,
											   final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
											   final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final long count) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count),
				new Converter.SimpleScanResultListStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final long count) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count),
				new Converter.SimpleScanResultListBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final long count,
											   final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final long count,
											   final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final long cursor, long count,
											   TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final long count,
											   final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final long count) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count),
				new Converter.SimpleScanResultListStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final long count) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count),
				new Converter.SimpleScanResultListBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final long count,
											   final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final long count,
											   final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final long count,
											   final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final long count,
											   final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final String pattern,
											   final long count) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count),
				new Converter.SimpleScanResultListStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final byte[] pattern,
											   final long count) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count),
				new Converter.SimpleScanResultListBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final String pattern,
											   final long count, final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final byte[] pattern,
											   final long count, final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final long cursor, final String pattern,
											   final long count, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final long cursor, final byte[] pattern,
											   final long count, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern,
											   final long count) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count),
				new Converter.SimpleScanResultListStringConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
											   final long count) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count),
				new Converter.SimpleScanResultListBinaryConverter<>(this));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern,
											   final long count, final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
											   final long count, final Class<V> clazz) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final String key, final String cursor, final String pattern,
											   final long count, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<List<V>> sScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
											   final long count, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sUnionObject(final String[] keys) {
		return execute((client)->client.setOperations().sUnion(rawKeys(keys)),
				new Converter.SimpleSetStringConverter<>(this));
	}

	@Override
	public <V> Set<V> sUnionObject(final byte[][] keys) {
		return execute((client)->client.setOperations().sUnion(rawKeys(keys)),
				new Converter.SimpleSetBinaryConverter<>(this));
	}

	@Override
	public <V> Set<V> sUnionObject(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.setOperations().sUnion(rawKeys(keys)),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sUnionObject(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.setOperations().sUnion(rawKeys(keys)),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sUnionObject(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sUnion(rawKeys(keys)),
				new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sUnionObject(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.setOperations().sUnion(rawKeys(keys)),
				new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> Long zAdd(final String key, final double score, final V member) {
		return zAdd(key, score, serializer.serialize(member));
	}

	@Override
	public <V> Long zAdd(final byte[] key, final double score, final V member) {
		return zAdd(key, score, serializer.serialize(member));
	}

	@Override
	public <V> Long zAdd(final String key, final double score, final V member, final NxXx nxXx) {
		return zAdd(key, score, serializer.serialize(member), nxXx);
	}

	@Override
	public <V> Long zAdd(final byte[] key, final double score, final V member, final NxXx nxXx) {
		return zAdd(key, score, serializer.serialize(member), nxXx);
	}

	@Override
	public <V> Long zAdd(final String key, final double score, final V member, final GtLt gtLt) {
		return zAdd(key, score, serializer.serialize(member), gtLt);
	}

	@Override
	public <V> Long zAdd(final byte[] key, final double score, final V member, final GtLt gtLt) {
		return zAdd(key, score, serializer.serialize(member), gtLt);
	}

	@Override
	public <V> Long zAdd(final String key, final double score, final V member, final boolean ch) {
		return zAdd(key, score, serializer.serialize(member), ch);
	}

	@Override
	public <V> Long zAdd(final byte[] key, final double score, final V member, final boolean ch) {
		return zAdd(key, score, serializer.serialize(member), ch);
	}

	@Override
	public <V> Long zAdd(final String key, final double score, final V member, final NxXx nxXx, final GtLt gtLt) {
		return zAdd(key, score, serializer.serialize(member), nxXx, gtLt);
	}

	@Override
	public <V> Long zAdd(final byte[] key, final double score, final V member, final NxXx nxXx, final GtLt gtLt) {
		return zAdd(key, score, serializer.serialize(member), nxXx, gtLt);
	}

	@Override
	public <V> Long zAdd(final String key, final double score, final V member, final NxXx nxXx, final boolean ch) {
		return zAdd(key, score, serializer.serialize(member), nxXx, ch);
	}

	@Override
	public <V> Long zAdd(final byte[] key, final double score, final V member, final NxXx nxXx, final boolean ch) {
		return zAdd(key, score, serializer.serialize(member), nxXx, ch);
	}

	@Override
	public <V> Long zAdd(final String key, final double score, final V member, final GtLt gtLt, final boolean ch) {
		return zAdd(key, score, serializer.serialize(member), gtLt, ch);
	}

	@Override
	public <V> Long zAdd(final byte[] key, final double score, final V member, final GtLt gtLt, final boolean ch) {
		return zAdd(key, score, serializer.serialize(member), gtLt, ch);
	}

	@Override
	public <V> Long zAdd(final String key, final double score, final V member, final NxXx nxXx, final GtLt gtLt,
						 final boolean ch) {
		return zAdd(key, score, serializer.serialize(member), nxXx, gtLt, ch);
	}

	@Override
	public <V> Long zAdd(final byte[] key, final double score, final V member, final NxXx nxXx, final GtLt gtLt,
						 final boolean ch) {
		return zAdd(key, score, serializer.serialize(member), nxXx, gtLt, ch);
	}

	@Override
	public <V> List<V> zDiffObject(final String[] keys) {
		return execute((client)->client.sortedSetOperations().zDiff(rawKeys(keys)),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zDiffObject(final byte[][] keys) {
		return execute((client)->client.sortedSetOperations().zDiff(rawKeys(keys)),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zDiffObject(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zDiff(rawKeys(keys)),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zDiffObject(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zDiff(rawKeys(keys)),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zDiffObject(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zDiff(rawKeys(keys)),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zDiffObject(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zDiff(rawKeys(keys)),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys)),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys)),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys)),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys)),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys)),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys)),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final Aggregate aggregate, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final Aggregate aggregate, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final double[] weights) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), weights),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final double[] weights) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), weights),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final double[] weights, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), weights),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final double[] weights, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), weights),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final double[] weights, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), weights),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final double[] weights, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), weights),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final Aggregate aggregate, final double[] weights) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate, weights),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate, final double[] weights) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate, weights),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final Aggregate aggregate, final double[] weights,
									final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate, weights),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate, final double[] weights,
									final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate, weights),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInterObject(final String[] keys, final Aggregate aggregate, final double[] weights,
									final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate, weights),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate, final double[] weights,
									final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate, weights),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V zRandMemberObject(final String key) {
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key)),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V zRandMemberObject(final byte[] key) {
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key)),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V zRandMemberObject(final String key, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V zRandMemberObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V zRandMemberObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V zRandMemberObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRandMemberObject(final String key, final long count) {
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key), count),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRandMemberObject(final byte[] key, final long count) {
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key), count),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRandMemberObject(final String key, final long count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key), count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRandMemberObject(final byte[] key, final long count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key), count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRandMemberObject(final String key, final long count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key), count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRandMemberObject(final byte[] key, final long count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key), count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeObject(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRange(rawKey(key), start, end),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeObject(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRange(rawKey(key), start, end),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeObject(final String key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRange(rawKey(key), start, end),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeObject(final byte[] key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRange(rawKey(key), start, end),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeObject(final String key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRange(rawKey(key), start, end),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeObject(final byte[] key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRange(rawKey(key), start, end),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final String key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final byte[] key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final String key, final double min, final double max,
										 final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final byte[] key, final double min, final double max,
										 final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final String key, final String min, final String max) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final byte[] key, final byte[] min, final byte[] max) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final String key, final String min, final String max, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final byte[] key, final byte[] min, final byte[] max, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final String key, final String min, final String max,
										 final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByLexObject(final byte[] key, final byte[] min, final byte[] max,
										 final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max),
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
	public <V> List<V> zRangeByScoreObject(final String key, final String min, final String max) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final byte[] key, final byte[] min, final byte[] max) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final String key, final String min, final String max, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final byte[] key, final byte[] min, final byte[] max, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final String key, final String min, final String max,
										   final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final byte[] key, final byte[] min, final byte[] max,
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
	public <V> List<V> zRangeByScoreObject(final String key, final String min, final String max, final long offset,
										   final long count) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max, offset, count),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final byte[] key, final byte[] min, final byte[] max, final long offset,
										   final long count) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max, offset, count),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final String key, final String min, final String max, final long offset,
										   final long count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final byte[] key, final byte[] min, final byte[] max, final long offset,
										   final long count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final String key, final String min, final String max, final long offset,
										   final long count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByScoreObject(final byte[] key, final byte[] min, final byte[] max, final long offset,
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
	public <V> List<V> zRevRangeByLexObject(final String key, final String min, final String max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final byte[] key, final byte[] min, final byte[] max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final String key, final String min, final String max,
											final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final byte[] key, final byte[] min, final byte[] max,
											final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final String key, final String min, final String max,
											final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final byte[] key, final byte[] min, final byte[] max,
											final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final String key, final double min, final double max, final long offset,
											final long count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final byte[] key, final double min, final double max, final long offset,
											final long count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final String key, final double min, final double max, final long offset,
											final long count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final byte[] key, final double min, final double max, final long offset,
											final long count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final String key, final double min, final double max, final long offset,
											final long count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final byte[] key, final double min, final double max, final long offset,
											final long count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final String key, final String min, final String max, final long offset,
											final long count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final byte[] key, final byte[] min, final byte[] max, final long offset,
											final long count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final String key, final String min, final String max, final long offset,
											final long count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final byte[] key, final byte[] min, final byte[] max, final long offset,
											final long count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final String key, final String min, final String max, final long offset,
											final long count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByLexObject(final byte[] key, final byte[] min, final byte[] max, final long offset,
											final long count, final TypeReference<V> type) {
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
	public <V> List<V> zRevRangeByScoreObject(final String key, final String min, final String max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final byte[] key, final byte[] min, final byte[] max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final String key, final String min, final String max,
											  final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final byte[] key, final byte[] min, final byte[] max,
											  final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final String key, final String min, final String max,
											  final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final byte[] key, final byte[] min, final byte[] max,
											  final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final String key, final double min, final double max, final long offset,
											  final long count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final byte[] key, final double min, final double max, final long offset,
											  final long count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final String key, final double min, final double max, final long offset,
											  final long count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final byte[] key, final double min, final double max, final long offset,
											  final long count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final String key, final double min, final double max, final long offset,
											  final long count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final byte[] key, final double min, final double max, final long offset,
											  final long count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final String key, final String min, final String max, final long offset,
											  final long count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final byte[] key, final byte[] min, final byte[] max, final long offset,
											  final long count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final String key, final String min, final String max, final long offset,
											  final long count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final byte[] key, final byte[] min, final byte[] max, final long offset,
											  final long count, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final String key, final String min, final String max, final long offset,
											  final long count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByScoreObject(final byte[] key, final byte[] min, final byte[] max, final long offset,
											  final long count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys)),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys)),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys)),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys)),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys)),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys)),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final double[] weights) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), weights),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final double[] weights) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), weights),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final double[] weights, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), weights),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final double[] weights, final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), weights),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final double[] weights, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), weights),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final double[] weights, final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), weights),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate, final double[] weights) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate, weights),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate, final double[] weights) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate, weights),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate, final double[] weights,
									final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate, weights),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate, final double[] weights,
									final Class<V> clazz) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate, weights),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate, final double[] weights,
									final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate, weights),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate, final double[] weights,
									final TypeReference<V> type) {
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate, weights),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V getObject(final String key) {
		return execute((client)->client.stringOperations().get(rawKey(key)),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V getObject(final byte[] key) {
		return execute((client)->client.stringOperations().get(rawKey(key)),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V getObject(final String key, final Class<V> clazz) {
		return execute((client)->client.stringOperations().get(rawKey(key)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V getObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.stringOperations().get(rawKey(key)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V getObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().get(rawKey(key)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V getObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().get(rawKey(key)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V getExObject(final String key, final GetExArgument getExArgument) {
		return execute((client)->client.stringOperations().getEx(rawKey(key), getExArgument),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V getExObject(final byte[] key, final GetExArgument getExArgument) {
		return execute((client)->client.stringOperations().getEx(rawKey(key), getExArgument),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V getExObject(final String key, final GetExArgument getExArgument, final Class<V> clazz) {
		return execute((client)->client.stringOperations().getEx(rawKey(key), getExArgument),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V getExObject(final byte[] key, final GetExArgument getExArgument, final Class<V> clazz) {
		return execute((client)->client.stringOperations().getEx(rawKey(key), getExArgument),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V getExObject(final String key, final GetExArgument getExArgument, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().getEx(rawKey(key), getExArgument),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V getExObject(final byte[] key, final GetExArgument getExArgument, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().getEx(rawKey(key), getExArgument),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V getSet(final String key, final V value) {
		return execute((client)->client.stringOperations().getSet(rawKey(key), serializer.serialize(value)),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V getSet(final byte[] key, final V value) {
		return execute((client)->client.stringOperations().getSet(rawKey(key), serializer.serializeAsBytes(value)),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V getSet(final String key, final V value, final Class<V> clazz) {
		return execute((client)->client.stringOperations().getSet(rawKey(key), serializer.serialize(value)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V getSet(final byte[] key, final V value, final Class<V> clazz) {
		return execute((client)->client.stringOperations().getSet(rawKey(key), serializer.serializeAsBytes(value)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V getSet(final String key, final V value, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().getSet(rawKey(key), serializer.serialize(value)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V getSet(final byte[] key, final V value, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().getSet(rawKey(key), serializer.serializeAsBytes(value)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V getDelObject(final String key) {
		return execute((client)->client.stringOperations().getDel(rawKey(key)),
				new Converter.SimpleStringConverter<>(this));
	}

	@Override
	public <V> V getDelObject(final byte[] key) {
		return execute((client)->client.stringOperations().getDel(rawKey(key)),
				new Converter.SimpleBinaryConverter<>(this));
	}

	@Override
	public <V> V getDelObject(final String key, final Class<V> clazz) {
		return execute((client)->client.stringOperations().getDel(rawKey(key)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V getDelObject(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.stringOperations().getDel(rawKey(key)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V getDelObject(final String key, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().getDel(rawKey(key)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V getDelObject(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().getDel(rawKey(key)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> mGetObject(final String... keys) {
		return execute((client)->client.stringOperations().mGet(rawKeys(keys)),
				new Converter.SimpleListStringConverter<>(this));
	}

	@Override
	public <V> List<V> mGetObject(final byte[]... keys) {
		return execute((client)->client.stringOperations().mGet(rawKeys(keys)),
				new Converter.SimpleListBinaryConverter<>(this));
	}

	@Override
	public <V> List<V> mGetObject(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.stringOperations().mGet(rawKeys(keys)),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> mGetObject(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.stringOperations().mGet(rawKeys(keys)),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> mGetObject(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().mGet(rawKeys(keys)),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> mGetObject(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.stringOperations().mGet(rawKeys(keys)),
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
	public <V> Status set(final String key, final V value, final SetArgument setArgument) {
		return set(key, serializer.serialize(value), setArgument);
	}

	@Override
	public <V> Status set(final byte[] key, final V value, final SetArgument setArgument) {
		return set(key, serializer.serializeAsBytes(value), setArgument);
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

	@Override
	public void discard() {
		super.discard();
		resetTransactionOrPipeline();
	}

	@Override
	public List<Object> exec() {
		List<Object> result = super.exec();

		if(result != null){
			Map<Integer, Function<?, ?>> map = txConverters.get();

			if(Validate.isNotEmpty(map)){
				for(int i = 0; i < result.size(); i++){
					Function<Object, Object> fun = (Function<Object, Object>) map.get(i);

					if(fun != null){
						result.set(i, fun.apply(result.get(i)));
					}
				}
			}
		}

		resetTransactionOrPipeline();

		return result;
	}

}
