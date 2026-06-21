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
import com.buession.redis.core.Options;
import com.buession.redis.core.command.args.FnxFxx;
import com.buession.redis.core.command.args.GetExType;
import com.buession.redis.core.command.args.PxExType;
import com.buession.redis.core.command.args.sortedset.Aggregate;
import com.buession.redis.core.command.args.list.Direction;
import com.buession.redis.core.command.args.list.Position;
import com.buession.redis.core.command.args.NxXx;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.args.sortedset.ZRangeType;
import com.buession.redis.core.command.args.json.JsonGetArgument;
import com.buession.redis.core.command.args.string.SetType;
import com.buession.redis.core.operations.*;
import com.buession.redis.utils.KeyUtils;

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

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		数据源
	 * @param options
	 * 		配置选项
	 */
	public RedisTemplate(DataSource dataSource, Options options) {
		super(dataSource, options);
	}

	@Override
	public <V> V hGet(final String key, final String field, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGet(KeyUtils.rawKey(this, key), field),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V hGet(final byte[] key, final byte[] field, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGet(KeyUtils.rawKey(this, key), field),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V hGet(final String key, final String field, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGet(KeyUtils.rawKey(this, key), field),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V hGet(final byte[] key, final byte[] field, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGet(KeyUtils.rawKey(this, key), field),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> Map<String, V> hGetAll(final String key, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGetAll(KeyUtils.rawKey(this, key)),
				new Converter.ClazzMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> Map<byte[], V> hGetAll(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGetAll(KeyUtils.rawKey(this, key)),
				new Converter.ClazzMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Map<String, V> hGetAll(final String key, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGetAll(KeyUtils.rawKey(this, key)),
				new Converter.TypeMapStringConverter<>(this, type));
	}

	@Override
	public <V> Map<byte[], V> hGetAll(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGetAll(KeyUtils.rawKey(this, key)),
				new Converter.TypeMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetDel(final String key, final String[] fields, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGetDel(KeyUtils.rawKey(this, key), fields),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetDel(final byte[] key, final byte[][] fields, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGetDel(KeyUtils.rawKey(this, key), fields),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetDel(final String key, final String[] fields, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGetDel(KeyUtils.rawKey(this, key), fields),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetDel(final byte[] key, final byte[][] fields, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGetDel(KeyUtils.rawKey(this, key), fields),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetEx(final String key, final String[] fields, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGetEx(KeyUtils.rawKey(this, key), fields),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetEx(final byte[] key, final byte[][] fields, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGetEx(KeyUtils.rawKey(this, key), fields),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetEx(final String key, final String[] fields, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGetEx(KeyUtils.rawKey(this, key), fields),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetEx(final byte[] key, final byte[][] fields, TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGetEx(KeyUtils.rawKey(this, key), fields),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetEx(final String key, final GetExType exType, final long expires, final String[] fields,
	                          final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGetEx(KeyUtils.rawKey(this, key), exType, expires, fields),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetEx(final byte[] key, final GetExType exType, final long expires, final byte[][] fields,
	                          final Class<V> clazz) {
		return execute((client)->client.hashCommands().hGetEx(KeyUtils.rawKey(this, key), exType, expires, fields),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hGetEx(final String key, final GetExType exType, final long expires, final String[] fields,
	                          final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGetEx(KeyUtils.rawKey(this, key), exType, expires, fields),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> hGetEx(final byte[] key, final GetExType exType, final long expires, final byte[][] fields,
	                          final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hGetEx(KeyUtils.rawKey(this, key), exType, expires, fields),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> hMGet(final String key, final String[] fields, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hMGet(KeyUtils.rawKey(this, key), fields),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hMGet(final byte[] key, final byte[][] fields, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hMGet(KeyUtils.rawKey(this, key), fields),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hMGet(final String key, final String[] fields, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hMGet(KeyUtils.rawKey(this, key), fields),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> hMGet(final byte[] key, final byte[][] fields, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hMGet(KeyUtils.rawKey(this, key), fields),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> Map<String, V> hRandFieldWithValues(final String key, final int count, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hRandFieldWithValues(KeyUtils.rawKey(this, key), count),
				new Converter.ClazzMapStringConverter<>(this, clazz));
	}

	@Override
	public <V> Map<byte[], V> hRandFieldWithValues(final byte[] key, final int count, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hRandFieldWithValues(KeyUtils.rawKey(this, key), count),
				new Converter.ClazzMapBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Map<String, V> hRandFieldWithValues(final String key, final int count, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hRandFieldWithValues(KeyUtils.rawKey(this, key), count),
				new Converter.TypeMapStringConverter<>(this, type));
	}

	@Override
	public <V> Map<byte[], V> hRandFieldWithValues(final byte[] key, final int count, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hRandFieldWithValues(KeyUtils.rawKey(this, key), count),
				new Converter.TypeMapBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<KeyValue<String, V>> hScan(final String key, final String cursor, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hScan(KeyUtils.rawKey(this, key), cursor),
				new Converter.ClazzScanResultStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<KeyValue<byte[], V>> hScan(final byte[] key, final byte[] cursor, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hScan(KeyUtils.rawKey(this, key), cursor),
				new Converter.ClazzScanResultBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<KeyValue<String, V>> hScan(final String key, final String cursor,
	                                                 final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hScan(KeyUtils.rawKey(this, key), cursor),
				new Converter.TypeScanResultStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<KeyValue<byte[], V>> hScan(final byte[] key, final byte[] cursor,
	                                                 final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hScan(KeyUtils.rawKey(this, key), cursor),
				new Converter.TypeScanResultBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<KeyValue<String, V>> hScan(final String key, final String cursor, final String pattern,
	                                                 final Class<V> clazz) {
		return execute((client)->client.hashCommands().hScan(KeyUtils.rawKey(this, key), cursor, pattern),
				new Converter.ClazzScanResultStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<KeyValue<byte[], V>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
	                                                 final Class<V> clazz) {
		return execute((client)->client.hashCommands().hScan(KeyUtils.rawKey(this, key), cursor, pattern),
				new Converter.ClazzScanResultBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<KeyValue<String, V>> hScan(final String key, final String cursor, final String pattern,
	                                                 final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hScan(KeyUtils.rawKey(this, key), cursor, pattern),
				new Converter.TypeScanResultStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<KeyValue<byte[], V>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
	                                                 final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hScan(KeyUtils.rawKey(this, key), cursor, pattern),
				new Converter.TypeScanResultBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<KeyValue<String, V>> hScan(final String key, final String cursor, final int count,
	                                                 final Class<V> clazz) {
		return execute((client)->client.hashCommands().hScan(KeyUtils.rawKey(this, key), cursor, count),
				new Converter.ClazzScanResultStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<KeyValue<byte[], V>> hScan(final byte[] key, final byte[] cursor, final int count,
	                                                 final Class<V> clazz) {
		return execute((client)->client.hashCommands().hScan(KeyUtils.rawKey(this, key), cursor, count),
				new Converter.ClazzScanResultBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<KeyValue<String, V>> hScan(final String key, final String cursor, final int count,
	                                                 final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hScan(KeyUtils.rawKey(this, key), cursor, count),
				new Converter.TypeScanResultStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<KeyValue<byte[], V>> hScan(final byte[] key, final byte[] cursor, final int count,
	                                                 final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hScan(KeyUtils.rawKey(this, key), cursor, count),
				new Converter.TypeScanResultBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<KeyValue<String, V>> hScan(final String key, final String cursor, final String pattern,
	                                                 final int count, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hScan(KeyUtils.rawKey(this, key), cursor, pattern, count),
				new Converter.ClazzScanResultStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<KeyValue<byte[], V>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
	                                                 final int count, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hScan(KeyUtils.rawKey(this, key), cursor, pattern, count),
				new Converter.ClazzScanResultBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<KeyValue<String, V>> hScan(final String key, final String cursor, final String pattern,
	                                                 final int count, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hScan(KeyUtils.rawKey(this, key), cursor, pattern, count),
				new Converter.TypeScanResultStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<KeyValue<byte[], V>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
	                                                 final int count, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hScan(KeyUtils.rawKey(this, key), cursor, pattern, count),
				new Converter.TypeScanResultBinaryConverter<>(this, type));
	}

	@Override
	public <V> Long hSet(final String key, final String field, final V value) {
		return hSet(key, field, serializer.serialize(value));
	}

	@Override
	public <V> Long hSet(final byte[] key, final byte[] field, final V value) {
		return hSet(key, field, serializer.serialize(value));
	}

	@Override
	public <V> Status hSetEx(final String key, final String field, final V value) {
		return hSetEx(key, field, serializer.serialize(value));
	}

	@Override
	public <V> Status hSetEx(final byte[] key, final byte[] field, final V value) {
		return hSetEx(key, field, serializer.serialize(value));
	}

	@Override
	public <V> Status hSetEx(final String key, final FnxFxx fnxFxx, final String field, final V value) {
		return hSetEx(key, fnxFxx, field, serializer.serialize(value));
	}

	@Override
	public <V> Status hSetEx(final byte[] key, final FnxFxx fnxFxx, final byte[] field, final V value) {
		return hSetEx(key, fnxFxx, field, serializer.serialize(value));
	}

	@Override
	public <V> Status hSetEx(final String key, final FnxFxx fnxFxx, final PxExType exType, final long expires,
	                         final String field, final V value) {
		return hSetEx(key, fnxFxx, exType, expires, field, serializer.serialize(value));
	}

	@Override
	public <V> Status hSetEx(final byte[] key, final FnxFxx fnxFxx, final PxExType exType, final long expires,
	                         final byte[] field, final V value) {
		return hSetEx(key, fnxFxx, exType, expires, field, serializer.serialize(value));
	}

	@Override
	public <V> Status hSetEx(final String key, final PxExType exType, final long expires, final String field,
	                         final V value) {
		return hSetEx(key, exType, expires, field, serializer.serialize(value));
	}

	@Override
	public <V> Status hSetEx(final byte[] key, final PxExType exType, final long expires, final byte[] field,
	                         final V value) {
		return hSetEx(key, exType, expires, field, serializer.serialize(value));
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
		return execute((client)->client.hashCommands().hVals(KeyUtils.rawKey(this, key)),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hVals(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.hashCommands().hVals(KeyUtils.rawKey(this, key)),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> hVals(final String key, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hVals(KeyUtils.rawKey(this, key)),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> hVals(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.hashCommands().hVals(KeyUtils.rawKey(this, key)),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V jsonGet(final String key, final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonGet(KeyUtils.rawKey(this, key)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V jsonGet(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonGet(KeyUtils.rawKey(this, key)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V jsonGet(final String key, final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonGet(KeyUtils.rawKey(this, key)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V jsonGet(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonGet(KeyUtils.rawKey(this, key)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V jsonGet(final String key, final JsonGetArgument argument, final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonGet(KeyUtils.rawKey(this, key), argument),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V jsonGet(final byte[] key, final JsonGetArgument argument, final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonGet(KeyUtils.rawKey(this, key), argument),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V jsonGet(final String key, final JsonGetArgument argument, final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonGet(KeyUtils.rawKey(this, key), argument),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V jsonGet(final byte[] key, final JsonGetArgument argument, final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonGet(KeyUtils.rawKey(this, key), argument),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonGet(final String key, final String[] path, final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonGet(KeyUtils.rawKey(this, key), path),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonGet(final byte[] key, final byte[][] path, final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonGet(KeyUtils.rawKey(this, key), path),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonGet(final String key, final String[] path, final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonGet(KeyUtils.rawKey(this, key), path),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonGet(final byte[] key, final byte[][] path, final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonGet(KeyUtils.rawKey(this, key), path),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonGet(final String key, final JsonGetArgument argument, final String[] path,
	                           final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonGet(KeyUtils.rawKey(this, key), argument, path),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonGet(final byte[] key, final JsonGetArgument argument, final byte[][] path,
	                           final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonGet(KeyUtils.rawKey(this, key), argument, path),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonGet(final String key, final JsonGetArgument argument, final String[] path,
	                           final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonGet(KeyUtils.rawKey(this, key), argument, path),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonGet(final byte[] key, final JsonGetArgument argument, final byte[][] path,
	                           final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonGet(KeyUtils.rawKey(this, key), argument, path),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonMGet(final String[] keys, final String path, final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonMGet(KeyUtils.rawKeys(this, keys), path),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonMGet(final byte[][] keys, final byte[] path, final Class<V> clazz) {
		return execute((client)->client.jsonCommands().jsonMGet(KeyUtils.rawKeys(this, keys), path),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> jsonMGet(final String[] keys, final String path, final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonMGet(KeyUtils.rawKeys(this, keys), path),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> jsonMGet(final byte[][] keys, final byte[] path, final TypeReference<V> type) {
		return execute((client)->client.jsonCommands().jsonMGet(KeyUtils.rawKeys(this, keys), path),
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
		return execute((client)->client.listCommands()
						.blMove(KeyUtils.rawKey(this, key), KeyUtils.rawKey(this, destKey), from, to, timeout),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V blMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
	                    final int timeout, final Class<V> clazz) {
		return execute((client)->client.listCommands()
						.blMove(KeyUtils.rawKey(this, key), KeyUtils.rawKey(this, destKey), from, to, timeout),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V blMove(final String key, final String destKey, final Direction from, final Direction to,
	                    final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listCommands()
						.blMove(KeyUtils.rawKey(this, key), KeyUtils.rawKey(this, destKey), from, to, timeout),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V blMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
	                    final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listCommands()
						.blMove(KeyUtils.rawKey(this, key), KeyUtils.rawKey(this, destKey), from, to, timeout),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<String, List<V>> blMPop(final int timeout, final String[] keys, final Direction direction,
	                                            final Class<V> clazz) {
		return execute((client)->client.listCommands().blMPop(timeout, KeyUtils.rawKeys(this, keys), direction),
				new Converter.ClazzListKeyValueStringConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> blMPop(final int timeout, final byte[][] keys, final Direction direction,
	                                            final Class<V> clazz) {
		return execute((client)->client.listCommands().blMPop(timeout, KeyUtils.rawKeys(this, keys), direction),
				new Converter.ClazzListKeyValueBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<String, List<V>> blMPop(final int timeout, final String[] keys, final Direction direction,
	                                            final TypeReference<V> type) {
		return execute((client)->client.listCommands().blMPop(timeout, KeyUtils.rawKeys(this, keys), direction),
				new Converter.TypeListKeyValueStringConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> blMPop(final int timeout, final byte[][] keys, final Direction direction,
	                                            final TypeReference<V> type) {
		return execute((client)->client.listCommands().blMPop(timeout, KeyUtils.rawKeys(this, keys), direction),
				new Converter.TypeListKeyValueBinaryConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<String, List<V>> blMPop(final int timeout, final String[] keys, final Direction direction,
	                                            final int count, final Class<V> clazz) {
		return execute((client)->client.listCommands().blMPop(timeout, KeyUtils.rawKeys(this, keys), direction, count),
				new Converter.ClazzListKeyValueStringConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> blMPop(final int timeout, final byte[][] keys, final Direction direction,
	                                            final int count, final Class<V> clazz) {
		return execute((client)->client.listCommands().blMPop(timeout, KeyUtils.rawKeys(this, keys), direction, count),
				new Converter.ClazzListKeyValueBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<String, List<V>> blMPop(final int timeout, final String[] keys, final Direction direction,
	                                            final int count, final TypeReference<V> type) {
		return execute((client)->client.listCommands().blMPop(timeout, KeyUtils.rawKeys(this, keys), direction, count),
				new Converter.TypeListKeyValueStringConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> blMPop(final int timeout, final byte[][] keys, final Direction direction,
	                                            final int count, final TypeReference<V> type) {
		return execute((client)->client.listCommands().blMPop(timeout, KeyUtils.rawKeys(this, keys), direction, count),
				new Converter.TypeListKeyValueBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> blPop(final String[] keys, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listCommands().blPop(KeyUtils.rawKeys(this, keys), timeout),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> blPop(final byte[][] keys, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listCommands().blPop(KeyUtils.rawKeys(this, keys), timeout),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> blPop(final String[] keys, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listCommands().blPop(KeyUtils.rawKeys(this, keys), timeout),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> blPop(final byte[][] keys, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listCommands().blPop(KeyUtils.rawKeys(this, keys), timeout),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> brPop(final String[] keys, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listCommands().brPop(KeyUtils.rawKeys(this, keys), timeout),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> brPop(final byte[][] keys, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listCommands().brPop(KeyUtils.rawKeys(this, keys), timeout),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> brPop(final String[] keys, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listCommands().brPop(KeyUtils.rawKeys(this, keys), timeout),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> brPop(final byte[][] keys, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listCommands().brPop(KeyUtils.rawKeys(this, keys), timeout),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V brPoplPush(final String key, final String destKey, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listCommands()
						.brPoplPush(KeyUtils.rawKey(this, key), KeyUtils.rawKey(this, destKey), timeout),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V brPoplPush(final byte[] key, final byte[] destKey, final int timeout, final Class<V> clazz) {
		return execute((client)->client.listCommands()
						.brPoplPush(KeyUtils.rawKey(this, key), KeyUtils.rawKey(this, destKey), timeout),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V brPoplPush(final String key, final String destKey, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listCommands()
						.brPoplPush(KeyUtils.rawKey(this, key), KeyUtils.rawKey(this, destKey), timeout),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V brPoplPush(final byte[] key, final byte[] destKey, final int timeout, final TypeReference<V> type) {
		return execute((client)->client.listCommands()
						.brPoplPush(KeyUtils.rawKey(this, key), KeyUtils.rawKey(this, destKey), timeout),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V lIndex(final String key, final long index, final Class<V> clazz) {
		return execute((client)->client.listCommands().lIndex(KeyUtils.rawKey(this, key), index),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V lIndex(final byte[] key, final long index, final Class<V> clazz) {
		return execute((client)->client.listCommands().lIndex(KeyUtils.rawKey(this, key), index),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V lIndex(final String key, final long index, final TypeReference<V> type) {
		return execute((client)->client.listCommands().lIndex(KeyUtils.rawKey(this, key), index),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V lIndex(final byte[] key, final long index, final TypeReference<V> type) {
		return execute((client)->client.listCommands().lIndex(KeyUtils.rawKey(this, key), index),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> Long lInsert(final String key, final Position position, final V pivot, final V value) {
		return lInsert(key, position, serializer.serialize(pivot), serializer.serialize(value));
	}

	@Override
	public <V> Long lInsert(final byte[] key, final Position position, final V pivot, final V value) {
		return lInsert(key, position, serializer.serializeAsBytes(pivot), serializer.serializeAsBytes(value));
	}

	@Override
	public <V> V lMove(final String key, final String destKey, final Direction from, final Direction to,
	                   final Class<V> clazz) {
		return execute((client)->client.listCommands()
						.lMove(KeyUtils.rawKey(this, key), KeyUtils.rawKey(this, destKey), from, to),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V lMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
	                   final Class<V> clazz) {
		return execute((client)->client.listCommands()
						.lMove(KeyUtils.rawKey(this, key), KeyUtils.rawKey(this, destKey), from, to),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V lMove(final String key, final String destKey, final Direction from, final Direction to,
	                   final TypeReference<V> type) {
		return execute((client)->client.listCommands()
						.lMove(KeyUtils.rawKey(this, key), KeyUtils.rawKey(this, destKey), from, to),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V lMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
	                   final TypeReference<V> type) {
		return execute((client)->client.listCommands()
						.lMove(KeyUtils.rawKey(this, key), KeyUtils.rawKey(this, destKey), from, to),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<String, List<V>> lMPop(final String[] keys, final Direction direction, final Class<V> clazz) {
		return execute((client)->client.listCommands().lMPop(KeyUtils.rawKeys(this, keys), direction),
				new Converter.ClazzListKeyValueStringConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> lMPop(final byte[][] keys, final Direction direction, final Class<V> clazz) {
		return execute((client)->client.listCommands().lMPop(KeyUtils.rawKeys(this, keys), direction),
				new Converter.ClazzListKeyValueBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<String, List<V>> lMPop(final String[] keys, final Direction direction,
	                                           final TypeReference<V> type) {
		return execute((client)->client.listCommands().lMPop(KeyUtils.rawKeys(this, keys), direction),
				new Converter.TypeListKeyValueStringConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> lMPop(final byte[][] keys, final Direction direction,
	                                           final TypeReference<V> type) {
		return execute((client)->client.listCommands().lMPop(KeyUtils.rawKeys(this, keys), direction),
				new Converter.TypeListKeyValueBinaryConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<String, List<V>> lMPop(final String[] keys, final Direction direction, final int count,
	                                           final Class<V> clazz) {
		return execute((client)->client.listCommands().lMPop(KeyUtils.rawKeys(this, keys), direction, count),
				new Converter.ClazzListKeyValueStringConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> lMPop(final byte[][] keys, final Direction direction, final int count,
	                                           final Class<V> clazz) {
		return execute((client)->client.listCommands().lMPop(KeyUtils.rawKeys(this, keys), direction, count),
				new Converter.ClazzListKeyValueBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> KeyValue<String, List<V>> lMPop(final String[] keys, final Direction direction, final int count,
	                                           final TypeReference<V> type) {
		return execute((client)->client.listCommands().lMPop(KeyUtils.rawKeys(this, keys), direction, count),
				new Converter.TypeListKeyValueStringConverter<>(this, type));
	}

	@Override
	public <V> KeyValue<byte[], List<V>> lMPop(final byte[][] keys, final Direction direction, final int count,
	                                           final TypeReference<V> type) {
		return execute((client)->client.listCommands().lMPop(KeyUtils.rawKeys(this, keys), direction, count),
				new Converter.TypeListKeyValueBinaryConverter<>(this, type));
	}

	@Override
	public <V> V lPop(final String key, final Class<V> clazz) {
		return execute((client)->client.listCommands().lPop(KeyUtils.rawKey(this, key)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V lPop(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.listCommands().lPop(KeyUtils.rawKey(this, key)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V lPop(final String key, final TypeReference<V> type) {
		return execute((client)->client.listCommands().lPop(KeyUtils.rawKey(this, key)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V lPop(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.listCommands().lPop(KeyUtils.rawKey(this, key)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> lPop(final String key, final int count, final Class<V> clazz) {
		return execute((client)->client.listCommands().lPop(KeyUtils.rawKey(this, key), count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> lPop(final byte[] key, final int count, final Class<V> clazz) {
		return execute((client)->client.listCommands().lPop(KeyUtils.rawKey(this, key), count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> lPop(final String key, final int count, final TypeReference<V> type) {
		return execute((client)->client.listCommands().lPop(KeyUtils.rawKey(this, key), count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> lPop(final byte[] key, final int count, final TypeReference<V> type) {
		return execute((client)->client.listCommands().lPop(KeyUtils.rawKey(this, key), count),
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
		return execute((client)->client.listCommands().lRange(KeyUtils.rawKey(this, key), start, end),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> lRange(final byte[] key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.listCommands().lRange(KeyUtils.rawKey(this, key), start, end),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> lRange(final String key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.listCommands().lRange(KeyUtils.rawKey(this, key), start, end),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> lRange(final byte[] key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.listCommands().lRange(KeyUtils.rawKey(this, key), start, end),
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
		return execute((client)->client.listCommands().rPop(KeyUtils.rawKey(this, key)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V rPop(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.listCommands().rPop(KeyUtils.rawKey(this, key)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V rPop(final String key, final TypeReference<V> type) {
		return execute((client)->client.listCommands().rPop(KeyUtils.rawKey(this, key)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V rPop(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.listCommands().rPop(KeyUtils.rawKey(this, key)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> rPop(final String key, final int count, final Class<V> clazz) {
		return execute((client)->client.listCommands().rPop(KeyUtils.rawKey(this, key), count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> rPop(final byte[] key, final int count, final Class<V> clazz) {
		return execute((client)->client.listCommands().rPop(KeyUtils.rawKey(this, key), count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> rPop(final String key, final int count, final TypeReference<V> type) {
		return execute((client)->client.listCommands().rPop(KeyUtils.rawKey(this, key), count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> rPop(final byte[] key, final int count, final TypeReference<V> type) {
		return execute((client)->client.listCommands().rPop(KeyUtils.rawKey(this, key), count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V rPoplPush(final String key, final String destKey, final Class<V> clazz) {
		return execute((client)->client.listCommands().rPoplPush(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey)), new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V rPoplPush(final byte[] key, final byte[] destKey, final Class<V> clazz) {
		return execute((client)->client.listCommands().rPoplPush(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey)), new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V rPoplPush(final String key, final String destKey, final TypeReference<V> type) {
		return execute((client)->client.listCommands().rPoplPush(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey)), new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V rPoplPush(final byte[] key, final byte[] destKey, final TypeReference<V> type) {
		return execute((client)->client.listCommands().rPoplPush(KeyUtils.rawKey(this, key),
				KeyUtils.rawKey(this, destKey)), new Converter.TypeBinaryConverter<>(this, type));
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
		return execute((client)->client.setCommands().sDiff(KeyUtils.rawKeys(this, keys)),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sDiff(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.setCommands().sDiff(KeyUtils.rawKeys(this, keys)),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sDiff(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sDiff(KeyUtils.rawKeys(this, keys)),
				new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sDiff(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sDiff(KeyUtils.rawKeys(this, keys)),
				new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sInter(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.setCommands().sInter(KeyUtils.rawKeys(this, keys)),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sInter(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.setCommands().sInter(KeyUtils.rawKeys(this, keys)),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sInter(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sInter(KeyUtils.rawKeys(this, keys)),
				new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sInter(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sInter(KeyUtils.rawKeys(this, keys)),
				new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sMembers(final String key, final Class<V> clazz) {
		return execute((client)->client.setCommands().sMembers(KeyUtils.rawKey(this, key)),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sMembers(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.setCommands().sMembers(KeyUtils.rawKey(this, key)),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sMembers(final String key, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sMembers(KeyUtils.rawKey(this, key)),
				new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sMembers(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sMembers(KeyUtils.rawKey(this, key)),
				new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> V sPop(final String key, final Class<V> clazz) {
		return execute((client)->client.setCommands().sPop(KeyUtils.rawKey(this, key)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V sPop(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.setCommands().sPop(KeyUtils.rawKey(this, key)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V sPop(final String key, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sPop(KeyUtils.rawKey(this, key)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V sPop(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sPop(KeyUtils.rawKey(this, key)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sPop(final String key, final int count, final Class<V> clazz) {
		return execute((client)->client.setCommands().sPop(KeyUtils.rawKey(this, key), count),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sPop(final byte[] key, final int count, final Class<V> clazz) {
		return execute((client)->client.setCommands().sPop(KeyUtils.rawKey(this, key), count),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sPop(final String key, final int count, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sPop(KeyUtils.rawKey(this, key), count),
				new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sPop(final byte[] key, final int count, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sPop(KeyUtils.rawKey(this, key), count),
				new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> V sRandMember(final String key, final Class<V> clazz) {
		return execute((client)->client.setCommands().sRandMember(KeyUtils.rawKey(this, key)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V sRandMember(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.setCommands().sRandMember(KeyUtils.rawKey(this, key)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V sRandMember(final String key, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sRandMember(KeyUtils.rawKey(this, key)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V sRandMember(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sRandMember(KeyUtils.rawKey(this, key)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> sRandMember(final String key, final int count, final Class<V> clazz) {
		return execute((client)->client.setCommands().sRandMember(KeyUtils.rawKey(this, key), count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> sRandMember(final byte[] key, final int count, final Class<V> clazz) {
		return execute((client)->client.setCommands().sRandMember(KeyUtils.rawKey(this, key), count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> sRandMember(final String key, final int count, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sRandMember(KeyUtils.rawKey(this, key), count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> sRandMember(final byte[] key, final int count, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sRandMember(KeyUtils.rawKey(this, key), count),
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
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final Class<V> clazz) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<V> sScan(final String key, final String cursor, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<V> sScan(final String key, final String cursor, final String pattern,
	                               final Class<V> clazz) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, pattern),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
	                               final Class<V> clazz) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, pattern),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<V> sScan(final String key, final String cursor, final String pattern,
	                               final TypeReference<V> type) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, pattern),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
	                               final TypeReference<V> type) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, pattern),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<V> sScan(final String key, final String cursor, final String pattern, final int count,
	                               final Class<V> clazz) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, pattern, count),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count,
	                               final Class<V> clazz) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, pattern, count),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<V> sScan(final String key, final String cursor, final String pattern, final int count,
	                               final TypeReference<V> type) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, pattern, count),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count,
	                               final TypeReference<V> type) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, pattern, count),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<V> sScan(final String key, final String cursor, final int count, final Class<V> clazz) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, count),
				new Converter.ClazzScanResultListStringConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final int count, final Class<V> clazz) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, count),
				new Converter.ClazzScanResultListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> ScanResult<V> sScan(final String key, final String cursor, final int count,
	                               final TypeReference<V> type) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, count),
				new Converter.TypeScanResultListStringConverter<>(this, type));
	}

	@Override
	public <V> ScanResult<V> sScan(final byte[] key, final byte[] cursor, final int count,
	                               final TypeReference<V> type) {
		return execute((client)->client.setCommands().sScan(KeyUtils.rawKey(this, key), cursor, count),
				new Converter.TypeScanResultListBinaryConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sUnion(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.setCommands().sUnion(KeyUtils.rawKeys(this, keys)),
				new Converter.ClazzSetStringConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sUnion(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.setCommands().sUnion(KeyUtils.rawKeys(this, keys)),
				new Converter.ClazzSetBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> Set<V> sUnion(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sUnion(KeyUtils.rawKeys(this, keys)),
				new Converter.TypeSetStringConverter<>(this, type));
	}

	@Override
	public <V> Set<V> sUnion(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.setCommands().sUnion(KeyUtils.rawKeys(this, keys)),
				new Converter.TypeSetBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zDiff(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zDiff(KeyUtils.rawKeys(this, keys)),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zDiff(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zDiff(KeyUtils.rawKeys(this, keys)),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zDiff(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zDiff(KeyUtils.rawKeys(this, keys)),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zDiff(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zDiff(KeyUtils.rawKeys(this, keys)),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInter(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zInter(KeyUtils.rawKeys(this, keys)),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInter(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zInter(KeyUtils.rawKeys(this, keys)),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInter(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zInter(KeyUtils.rawKeys(this, keys)),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInter(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zInter(KeyUtils.rawKeys(this, keys)),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInter(final String[] keys, final Aggregate aggregate, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zInter(KeyUtils.rawKeys(this, keys), aggregate),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInter(final byte[][] keys, final Aggregate aggregate, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zInter(KeyUtils.rawKeys(this, keys), aggregate),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInter(final String[] keys, final Aggregate aggregate, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zInter(KeyUtils.rawKeys(this, keys), aggregate),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInter(final byte[][] keys, final Aggregate aggregate, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zInter(KeyUtils.rawKeys(this, keys), aggregate),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInter(final String[] keys, final Aggregate aggregate, final double[] weights,
	                          final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zInter(KeyUtils.rawKeys(this, keys), aggregate, weights),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInter(final byte[][] keys, final Aggregate aggregate, final double[] weights,
	                          final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zInter(KeyUtils.rawKeys(this, keys), aggregate, weights),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInter(final String[] keys, final Aggregate aggregate, final double[] weights,
	                          final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zInter(KeyUtils.rawKeys(this, keys), aggregate, weights),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInter(final byte[][] keys, final Aggregate aggregate, final double[] weights,
	                          final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zInter(KeyUtils.rawKeys(this, keys), aggregate, weights),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInter(final String[] keys, final double[] weights, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zInter(KeyUtils.rawKeys(this, keys), weights),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInter(final byte[][] keys, final double[] weights, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zInter(KeyUtils.rawKeys(this, keys), weights),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zInter(final String[] keys, final double[] weights, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zInter(KeyUtils.rawKeys(this, keys), weights),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zInter(final byte[][] keys, final double[] weights, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zInter(KeyUtils.rawKeys(this, keys), weights),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V zRandMember(final String key, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRandMember(KeyUtils.rawKey(this, key)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V zRandMember(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRandMember(KeyUtils.rawKey(this, key)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V zRandMember(final String key, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRandMember(KeyUtils.rawKey(this, key)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V zRandMember(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRandMember(KeyUtils.rawKey(this, key)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRandMember(final String key, final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRandMember(KeyUtils.rawKey(this, key), count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRandMember(final byte[] key, final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRandMember(KeyUtils.rawKey(this, key), count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRandMember(final String key, final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRandMember(KeyUtils.rawKey(this, key), count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRandMember(final byte[] key, final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRandMember(KeyUtils.rawKey(this, key), count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final ZRangeType type,
	                          final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end, type),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
	                          final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end, type),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final ZRangeType zRangeType,
	                          final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end, zRangeType),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType zRangeType,
	                          final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end, zRangeType),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final ZRangeType type,
	                          final boolean rev, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end, type, rev),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
	                          final boolean rev, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end, type, rev),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final ZRangeType zRangeType,
	                          final boolean rev, final TypeReference<V> type) {
		return execute(
				(client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end, zRangeType, rev),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType zRangeType,
	                          final boolean rev, final TypeReference<V> type) {
		return execute(
				(client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end, zRangeType, rev),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final int offset, final int count,
	                          final Class<V> clazz) {
		return execute(
				(client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final int offset, final int count,
	                          final Class<V> clazz) {
		return execute(
				(client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final int offset, final int count,
	                          final TypeReference<V> type) {
		return execute(
				(client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final int offset, final int count,
	                          final TypeReference<V> type) {
		return execute(
				(client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final ZRangeType type,
	                          final int offset, final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands()
						.zRange(KeyUtils.rawKey(this, key), start, end, type, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
	                          final int offset, final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands()
						.zRange(KeyUtils.rawKey(this, key), start, end, type, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final ZRangeType zRangeType,
	                          final int offset, final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands()
						.zRange(KeyUtils.rawKey(this, key), start, end, zRangeType, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType zRangeType,
	                          final int offset, final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands()
						.zRange(KeyUtils.rawKey(this, key), start, end, zRangeType, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final ZRangeType type,
	                          final boolean rev, final int offset, final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands()
						.zRange(KeyUtils.rawKey(this, key), start, end, type, rev, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType type,
	                          final boolean rev, final int offset, final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands()
						.zRange(KeyUtils.rawKey(this, key), start, end, type, rev, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final ZRangeType zRangeType,
	                          final boolean rev, final int offset, final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands()
						.zRange(KeyUtils.rawKey(this, key), start, end, zRangeType, rev, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final ZRangeType zRangeType,
	                          final boolean rev, final int offset, final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands()
						.zRange(KeyUtils.rawKey(this, key), start, end, zRangeType, rev, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final boolean rev, final int offset,
	                          final int count, final Class<V> clazz) {
		return execute(
				(client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end, rev, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final boolean rev, final int offset,
	                          final int count, final Class<V> clazz) {
		return execute(
				(client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end, rev, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRange(final String key, final long start, final long end, final boolean rev, final int offset,
	                          final int count, final TypeReference<V> type) {
		return execute(
				(client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end, rev, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRange(final byte[] key, final long start, final long end, final boolean rev, final int offset,
	                          final int count, final TypeReference<V> type) {
		return execute(
				(client)->client.sortedSetCommands().zRange(KeyUtils.rawKey(this, key), start, end, rev, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByLex(final String key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRangeByLex(KeyUtils.rawKey(this, key), min, max),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByLex(final byte[] key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRangeByLex(KeyUtils.rawKey(this, key), min, max),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByLex(final String key, final double min, final double max, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRangeByLex(KeyUtils.rawKey(this, key), min, max),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByLex(final byte[] key, final double min, final double max, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRangeByLex(KeyUtils.rawKey(this, key), min, max),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByLex(final String key, final double min, final double max, final int offset,
	                               final int count, final Class<V> clazz) {
		return execute(
				(client)->client.sortedSetCommands().zRangeByLex(KeyUtils.rawKey(this, key), min, max, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
	                               final int count, final Class<V> clazz) {
		return execute(
				(client)->client.sortedSetCommands().zRangeByLex(KeyUtils.rawKey(this, key), min, max, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByLex(final String key, final double min, final double max, final int offset,
	                               final int count, final TypeReference<V> type) {
		return execute(
				(client)->client.sortedSetCommands().zRangeByLex(KeyUtils.rawKey(this, key), min, max, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
	                               final int count, final TypeReference<V> type) {
		return execute(
				(client)->client.sortedSetCommands().zRangeByLex(KeyUtils.rawKey(this, key), min, max, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByScore(final String key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRangeByScore(KeyUtils.rawKey(this, key), min, max),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByScore(final byte[] key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRangeByScore(KeyUtils.rawKey(this, key), min, max),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByScore(final String key, final double min, final double max,
	                                 final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRangeByScore(KeyUtils.rawKey(this, key), min, max),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByScore(final byte[] key, final double min, final double max,
	                                 final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRangeByScore(KeyUtils.rawKey(this, key), min, max),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByScore(final String key, final double min, final double max, final int offset,
	                                 final int count, final Class<V> clazz) {
		return execute(
				(client)->client.sortedSetCommands().zRangeByScore(KeyUtils.rawKey(this, key), min, max, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
	                                 final int count, final Class<V> clazz) {
		return execute(
				(client)->client.sortedSetCommands().zRangeByScore(KeyUtils.rawKey(this, key), min, max, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRangeByScore(final String key, final double min, final double max, final int offset,
	                                 final int count, final TypeReference<V> type) {
		return execute(
				(client)->client.sortedSetCommands().zRangeByScore(KeyUtils.rawKey(this, key), min, max, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
	                                 final int count, final TypeReference<V> type) {
		return execute(
				(client)->client.sortedSetCommands().zRangeByScore(KeyUtils.rawKey(this, key), min, max, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRange(final String key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRevRange(KeyUtils.rawKey(this, key), start, end),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRange(final byte[] key, final long start, final long end, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRevRange(KeyUtils.rawKey(this, key), start, end),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRange(final String key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRevRange(KeyUtils.rawKey(this, key), start, end),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRange(final byte[] key, final long start, final long end, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRevRange(KeyUtils.rawKey(this, key), start, end),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByLex(final String key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRevRangeByLex(KeyUtils.rawKey(this, key), min, max),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLex(final byte[] key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRevRangeByLex(KeyUtils.rawKey(this, key), min, max),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLex(final String key, final double min, final double max,
	                                  final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRevRangeByLex(KeyUtils.rawKey(this, key), min, max),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByLex(final byte[] key, final double min, final double max,
	                                  final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRevRangeByLex(KeyUtils.rawKey(this, key), min, max),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByLex(final String key, final double min, final double max, final int offset,
	                                  final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands()
						.zRevRangeByLex(KeyUtils.rawKey(this, key), min, max, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
	                                  final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands()
						.zRevRangeByLex(KeyUtils.rawKey(this, key), min, max, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByLex(final String key, final double min, final double max, final int offset,
	                                  final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands()
						.zRevRangeByLex(KeyUtils.rawKey(this, key), min, max, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
	                                  final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands()
						.zRevRangeByLex(KeyUtils.rawKey(this, key), min, max, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByScore(final String key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScore(KeyUtils.rawKey(this, key), min, max),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScore(final byte[] key, final double min, final double max, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScore(KeyUtils.rawKey(this, key), min, max),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScore(final String key, final double min, final double max,
	                                    final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScore(KeyUtils.rawKey(this, key), min, max),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByScore(final byte[] key, final double min, final double max,
	                                    final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zRevRangeByScore(KeyUtils.rawKey(this, key), min, max),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByScore(final String key, final double min, final double max, final int offset,
	                                    final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands()
						.zRevRangeByScore(KeyUtils.rawKey(this, key), min, max, offset, count),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
	                                    final int count, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands()
						.zRevRangeByScore(KeyUtils.rawKey(this, key), min, max, offset, count),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zRevRangeByScore(final String key, final double min, final double max, final int offset,
	                                    final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands()
						.zRevRangeByScore(KeyUtils.rawKey(this, key), min, max, offset, count),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
	                                    final int count, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands()
						.zRevRangeByScore(KeyUtils.rawKey(this, key), min, max, offset, count),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnion(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zUnion(KeyUtils.rawKeys(this, keys)),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnion(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zUnion(KeyUtils.rawKeys(this, keys)),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnion(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zUnion(KeyUtils.rawKeys(this, keys)),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnion(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zUnion(KeyUtils.rawKeys(this, keys)),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnion(final String[] keys, final Aggregate aggregate, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zUnion(KeyUtils.rawKeys(this, keys), aggregate),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnion(final byte[][] keys, final Aggregate aggregate, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zUnion(KeyUtils.rawKeys(this, keys), aggregate),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnion(final String[] keys, final Aggregate aggregate, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zUnion(KeyUtils.rawKeys(this, keys), aggregate),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnion(final byte[][] keys, final Aggregate aggregate, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zUnion(KeyUtils.rawKeys(this, keys), aggregate),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnion(final String[] keys, final Aggregate aggregate, final double[] weights,
	                          final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zUnion(KeyUtils.rawKeys(this, keys), aggregate, weights),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnion(final byte[][] keys, final Aggregate aggregate, final double[] weights,
	                          final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zUnion(KeyUtils.rawKeys(this, keys), aggregate, weights),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnion(final String[] keys, final Aggregate aggregate, final double[] weights,
	                          final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zUnion(KeyUtils.rawKeys(this, keys), aggregate, weights),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnion(final byte[][] keys, final Aggregate aggregate, final double[] weights,
	                          final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zUnion(KeyUtils.rawKeys(this, keys), aggregate, weights),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnion(final String[] keys, final double[] weights, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zUnion(KeyUtils.rawKeys(this, keys), weights),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnion(final byte[][] keys, final double[] weights, final Class<V> clazz) {
		return execute((client)->client.sortedSetCommands().zUnion(KeyUtils.rawKeys(this, keys), weights),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> zUnion(final String[] keys, final double[] weights, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zUnion(KeyUtils.rawKeys(this, keys), weights),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> zUnion(final byte[][] keys, final double[] weights, final TypeReference<V> type) {
		return execute((client)->client.sortedSetCommands().zUnion(KeyUtils.rawKeys(this, keys), weights),
				new Converter.TypeListBinaryConverter<>(this, type));
	}

	@Override
	public <V> V get(final String key, final Class<V> clazz) {
		return execute((client)->client.stringCommands().get(KeyUtils.rawKey(this, key)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V get(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.stringCommands().get(KeyUtils.rawKey(this, key)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V get(final String key, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().get(KeyUtils.rawKey(this, key)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V get(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().get(KeyUtils.rawKey(this, key)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V getDel(final String key, final Class<V> clazz) {
		return execute((client)->client.stringCommands().getDel(KeyUtils.rawKey(this, key)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V getDel(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.stringCommands().getDel(KeyUtils.rawKey(this, key)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V getDel(final String key, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().getDel(KeyUtils.rawKey(this, key)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V getDel(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().getDel(KeyUtils.rawKey(this, key)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V getEx(final String key, final Class<V> clazz) {
		return execute((client)->client.stringCommands().getEx(KeyUtils.rawKey(this, key)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V getEx(final byte[] key, final Class<V> clazz) {
		return execute((client)->client.stringCommands().getEx(KeyUtils.rawKey(this, key)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V getEx(final String key, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().getEx(KeyUtils.rawKey(this, key)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V getEx(final byte[] key, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().getEx(KeyUtils.rawKey(this, key)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V getEx(final String key, final GetExType exType, final long expires, final Class<V> clazz) {
		return execute((client)->client.stringCommands().getEx(KeyUtils.rawKey(this, key), exType, expires),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V getEx(final byte[] key, final GetExType exType, final long expires, final Class<V> clazz) {
		return execute((client)->client.stringCommands().getEx(KeyUtils.rawKey(this, key), exType, expires),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V getEx(final String key, final GetExType exType, final long expires, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().getEx(KeyUtils.rawKey(this, key), exType, expires),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V getEx(final byte[] key, final GetExType exType, final long expires, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().getEx(KeyUtils.rawKey(this, key), exType, expires),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> V getSet(final String key, final V value, final Class<V> clazz) {
		return execute(
				(client)->client.stringCommands().getSet(KeyUtils.rawKey(this, key), serializer.serialize(value)),
				new Converter.ClazzStringConverter<>(this, clazz));
	}

	@Override
	public <V> V getSet(final byte[] key, final V value, final Class<V> clazz) {
		return execute((client)->client.stringCommands()
						.getSet(KeyUtils.rawKey(this, key), serializer.serializeAsBytes(value)),
				new Converter.ClazzBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> V getSet(final String key, final V value, final TypeReference<V> type) {
		return execute(
				(client)->client.stringCommands().getSet(KeyUtils.rawKey(this, key), serializer.serialize(value)),
				new Converter.TypeStringConverter<>(this, type));
	}

	@Override
	public <V> V getSet(final byte[] key, final V value, final TypeReference<V> type) {
		return execute((client)->client.stringCommands()
						.getSet(KeyUtils.rawKey(this, key), serializer.serializeAsBytes(value)),
				new Converter.TypeBinaryConverter<>(this, type));
	}

	@Override
	public <V> List<V> mGet(final String[] keys, final Class<V> clazz) {
		return execute((client)->client.stringCommands().mGet(KeyUtils.rawKeys(this, keys)),
				new Converter.ClazzListStringConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> mGet(final byte[][] keys, final Class<V> clazz) {
		return execute((client)->client.stringCommands().mGet(KeyUtils.rawKeys(this, keys)),
				new Converter.ClazzListBinaryConverter<>(this, clazz));
	}

	@Override
	public <V> List<V> mGet(final String[] keys, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().mGet(KeyUtils.rawKeys(this, keys)),
				new Converter.TypeListStringConverter<>(this, type));
	}

	@Override
	public <V> List<V> mGet(final byte[][] keys, final TypeReference<V> type) {
		return execute((client)->client.stringCommands().mGet(KeyUtils.rawKeys(this, keys)),
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
	public <V> Status set(final String key, final V value, final SetType setType) {
		return set(key, serializer.serialize(value), setType);
	}

	@Override
	public <V> Status set(final byte[] key, final V value, final SetType setType) {
		return set(key, serializer.serializeAsBytes(value), setType);
	}

	@Override
	public <V> Status set(final String key, final V value, final SetType setType, final PxExType pxExType,
	                      final long expires) {
		return set(key, serializer.serialize(value), setType, pxExType, expires);
	}

	@Override
	public <V> Status set(final byte[] key, final V value, final SetType setType, final PxExType pxExType,
	                      final long expires) {
		return set(key, serializer.serializeAsBytes(value), setType, pxExType, expires);
	}

	@Override
	public <V> Status set(final String key, final V value, final PxExType pxExType, final long expires) {
		return set(key, serializer.serialize(value), pxExType, expires);
	}

	@Override
	public <V> Status set(final byte[] key, final V value, final PxExType pxExType, final long expires) {
		return set(key, serializer.serializeAsBytes(value), pxExType, expires);
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
