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
package com.buession.redis;

import com.buession.core.collect.Maps;
import com.buession.core.serializer.type.TypeReference;
import com.buession.core.validator.Validate;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.Direction;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.operations.*;
import com.buession.redis.serializer.Serializer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Redis 基本操作封装扩展，可序列化对象和反序列化为对象
 *
 * @author Yong.Teng
 */
public class RedisTemplate extends BaseRedisTemplate implements BitMapOperations, ClusterOperations,
		ConnectionOperations, GeoOperations/*,
		HashOperations, HyperLogLogOperations, KeyOperations, ListOperations, PubSubOperations, ScriptingOperations,
		ServerOperations, SetOperations, SortedSetOperations, StringOperations, TransactionOperations*/ {

	private final static ThreadLocal<Map<Integer, Function<?, ?>>> txConverters = new ThreadLocal<>();

	/**
	 * 构造函数
	 */
	public RedisTemplate(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param connection
	 * 		Redis 连接对称
	 */
	public RedisTemplate(RedisConnection connection){
		super(connection);
	}
	/*

	@Override
	public <V> V hGetObject(final String key, final String field){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				hGet(key, field), this);
		return operations.operation();
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				hGet(key, field), this);
		return operations.operation();
	}

	@Override
	public <V> V hGetObject(final String key, final String field, final Class<V> clazz){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				hGet(key, field), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field, final Class<V> clazz){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				hGet(key, field), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V hGetObject(final String key, final String field, final TypeReference<V> type){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				hGet(key, field), this);
		return operations.operation(type);
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field, final TypeReference<V> type){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				hGet(key, field), this);
		return operations.operation(type);
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key){
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				hGetAll(key), this);
		return operations.operation();
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key){
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				hGetAll(key), this);
		return operations.operation();
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key, final Class<V> clazz){
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				hGetAll(key), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key, final Class<V> clazz){
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				hGetAll(key), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key, final TypeReference<V> type){
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				hGetAll(key), this);
		return operations.operation(type);
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key, final TypeReference<V> type){
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				hGetAll(key), this);
		return operations.operation(type);
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String... fields){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				hMGet(key, fields), this);
		return operations.operation();
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[]... fields){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				hMGet(key, fields), this);
		return operations.operation();
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String[] fields, final Class<V> clazz){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				hMGet(key, fields), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final Class<V> clazz){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				hMGet(key, fields), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String[] fields, final TypeReference<V> type){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				hMGet(key, fields), this);
		return operations.operation(type);
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final TypeReference<V> type){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				hMGet(key, fields), this);
		return operations.operation(type);
	}

	@Override
	public <V> Status hMSet(final String key, final List<KeyValue<String, V>> data){
		Map<String, String> temp = new LinkedHashMap<>(data.size());

		for(KeyValue<String, V> kv : data){
			temp.put(kv.getKey(), serializer.serialize(kv.getValue()));
		}

		return hMSet(key, temp);
	}

	@Override
	public <V> Status hMSet(final byte[] key, final List<KeyValue<byte[], V>> data){
		Map<byte[], byte[]> temp = new LinkedHashMap<>(data.size());

		for(KeyValue<byte[], V> kv : data){
			temp.put(kv.getKey(), serializer.serializeAsBytes(kv.getValue()));
		}

		return hMSet(key, temp);
	}

	@Override
	public <V> Map<String, V> hRandFieldWithValuesObject(final String key, final long count){
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				hRandFieldWithValues(key, count), this);
		return operations.operation();
	}

	@Override
	public <V> Map<byte[], V> hRandFieldWithValuesObject(final byte[] key, final long count){
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				hRandFieldWithValues(key, count), this);
		return operations.operation();
	}

	@Override
	public <V> Map<String, V> hRandFieldWithValuesObject(final String key, long count, final Class<V> clazz){
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				hRandFieldWithValues(key, count), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> Map<byte[], V> hRandFieldWithValuesObject(final byte[] key, final long count, final Class<V> clazz){
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				hRandFieldWithValues(key, count), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> Map<String, V> hRandFieldWithValuesObject(final String key, final long count,
														 final TypeReference<V> type){
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				hRandFieldWithValues(key, count), this);
		return operations.operation(type);
	}

	@Override
	public <V> Map<byte[], V> hRandFieldWithValuesObject(final byte[] key, final long count,
														 final TypeReference<V> type){
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				hRandFieldWithValues(key, count), this);
		return operations.operation(type);
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor){
		final ScanResult<Map<String, String>> result = hScan(key, cursor);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation());
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation());
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final Class<V> clazz){
		final ScanResult<Map<String, String>> result = hScan(key, cursor);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final Class<V> clazz){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final TypeReference<V> type){
		final ScanResult<Map<String, String>> result = hScan(key, cursor);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final TypeReference<V> type){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor){
		final ScanResult<Map<String, String>> result = hScan(key, cursor);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(cursor, operations.operation());
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(cursor, operations.operation());
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final Class<V> clazz){
		final ScanResult<Map<String, String>> result = hScan(key, cursor);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(cursor, operations.operation(clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final Class<V> clazz){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(cursor, operations.operation(clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor,
													  final TypeReference<V> type){
		final ScanResult<Map<String, String>> result = hScan(key, cursor);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(cursor, operations.operation(type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor,
													  final TypeReference<V> type){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(cursor, operations.operation(type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final String pattern){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, pattern);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation());
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final byte[] pattern){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, pattern);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation());
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final String pattern,
													  final Class<V> clazz){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, pattern);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final byte[] pattern,
													  final Class<V> clazz){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, pattern);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final String pattern,
													  final TypeReference<V> type){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, pattern);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final byte[] pattern,
													  final TypeReference<V> type){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, pattern);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, pattern);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(cursor, operations.operation());
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, pattern);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(cursor, operations.operation());
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
													  final Class<V> clazz){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, pattern);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(cursor, operations.operation(clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
													  final Class<V> clazz){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, pattern);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(cursor, operations.operation(clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
													  final TypeReference<V> type){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, pattern);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(cursor, operations.operation(type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
													  final TypeReference<V> type){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, pattern);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(cursor, operations.operation(type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final long count){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, count);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation());
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final long count){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, count);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation());
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final long count,
													  final Class<V> clazz){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, count);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final long count,
													  final Class<V> clazz){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, count);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, long count,
													  TypeReference<V> type){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, count);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final long count,
													  final TypeReference<V> type){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, count);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final long count){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, count);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation());
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final long count){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, count);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation());
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final long count,
													  final Class<V> clazz){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, count);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final long count,
													  final Class<V> clazz){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, count);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final long count,
													  final TypeReference<V> type){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, count);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final long count,
													  final TypeReference<V> type){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, count);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final String pattern,
													  final long count){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, pattern, count);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation());
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final byte[] pattern,
													  final long count){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, pattern, count);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation());
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final String pattern,
													  final long count, final Class<V> clazz){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, pattern, count);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final byte[] pattern,
													  final long count, final Class<V> clazz){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, pattern, count);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final String pattern,
													  final long count, final TypeReference<V> type){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, pattern, count);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final byte[] pattern,
													  final long count, final TypeReference<V> type){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, pattern, count);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(type));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
													  final long count){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, pattern, count);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation());
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
													  final long count){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, pattern, count);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation());
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
													  final long count, final Class<V> clazz){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, pattern, count);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(clazz));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
													  final long count, final Class<V> clazz){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, pattern, count);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(clazz));
	}

	@Override
	public <V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
													  final long count, final TypeReference<V> type){
		final ScanResult<Map<String, String>> result = hScan(key, cursor, pattern, count);
		final ObjectOperations.MapStringObjectOperations operations = new ObjectOperations.MapStringObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(type));
	}

	@Override
	public <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
													  final long count, final TypeReference<V> type){
		final ScanResult<Map<byte[], byte[]>> result = hScan(key, cursor, pattern, count);
		final ObjectOperations.MapBinaryObjectOperations operations = new ObjectOperations.MapBinaryObjectOperations(
				result.getResults(), this);

		return new ScanResult<>(result.getCursor(), operations.operation(type));
	}

	@Override
	public <V> Status hSet(final String key, final String field, final V value){
		return hSet(key, field, serializer.serialize(value));
	}

	@Override
	public <V> Status hSet(final byte[] key, final byte[] field, final V value){
		return hSet(key, field, serializer.serializeAsBytes(value));
	}

	@Override
	public <V> Status hSetNx(final String key, final String field, final V value){
		return hSetNx(key, field, serializer.serialize(value));
	}

	@Override
	public <V> Status hSetNx(final byte[] key, final byte[] field, final V value){
		return hSetNx(key, field, serializer.serializeAsBytes(value));
	}

	@Override
	public <V> List<V> hValsObject(final String key){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				hVals(key), this);
		return operations.operation();
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				hVals(key), this);
		return operations.operation();
	}

	@Override
	public <V> List<V> hValsObject(final String key, final Class<V> clazz){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				hVals(key), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key, final Class<V> clazz){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				hVals(key), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> List<V> hValsObject(final String key, final TypeReference<V> type){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				hVals(key), this);
		return operations.operation(type);
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key, final TypeReference<V> type){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				hVals(key), this);
		return operations.operation(type);
	}

	@Override
	public <V> V lIndexObject(final String key, final long index){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				lIndex(key, index), this);
		return operations.operation();
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				lIndex(key, index), this);
		return operations.operation();
	}

	@Override
	public <V> V lIndexObject(final String key, final long index, final Class<V> clazz){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				lIndex(key, index), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index, final Class<V> clazz){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				lIndex(key, index), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V lIndexObject(final String key, final long index, final TypeReference<V> type){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				lIndex(key, index), this);
		return operations.operation(type);
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index, final TypeReference<V> type){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				lIndex(key, index), this);
		return operations.operation(type);
	}

	@Override
	public <V> Long lInsert(final String key, final ListPosition position, final V pivot, final V value){
		return lInsert(key, position, serializer.serialize(pivot), serializer.serialize(value));
	}

	@Override
	public <V> Long lInsert(final byte[] key, final ListPosition position, final V pivot, final V value){
		return lInsert(key, position, serializer.serializeAsBytes(pivot), serializer.serializeAsBytes(value));
	}

	@Override
	public <V> Status lSet(final String key, final long index, final V value){
		return lSet(key, index, serializer.serialize(value));
	}

	@Override
	public <V> Status lSet(final byte[] key, final long index, final V value){
		return lSet(key, index, serializer.serializeAsBytes(value));
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final long start, final long end){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				lRange(key, start, end), this);
		return operations.operation();
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				lRange(key, start, end), this);
		return operations.operation();
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final long start, final long end, final Class<V> clazz){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				lRange(key, start, end), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end, final Class<V> clazz){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				lRange(key, start, end), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final long start, final long end, final TypeReference<V> type){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				lRange(key, start, end), this);
		return operations.operation(type);
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end, final TypeReference<V> type){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				lRange(key, start, end), this);
		return operations.operation(type);
	}

	@Override
	public <V> V lMoveObject(final String key, final String destKey, final Direction from, final Direction to){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				lMove(key, destKey, from, to), this);
		return operations.operation();
	}

	@Override
	public <V> V lMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				lMove(key, destKey, from, to), this);
		return operations.operation();
	}

	@Override
	public <V> V lMoveObject(final String key, final String destKey, final Direction from, final Direction to,
							 final Class<V> clazz){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				lMove(key, destKey, from, to), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V lMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
							 final Class<V> clazz){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				lMove(key, destKey, from, to), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V lMoveObject(final String key, final String destKey, final Direction from, final Direction to,
							 final TypeReference<V> type){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				lMove(key, destKey, from, to), this);
		return operations.operation(type);
	}

	@Override
	public <V> V lMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
							 final TypeReference<V> type){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				lMove(key, destKey, from, to), this);
		return operations.operation(type);
	}

	@Override
	public <V> V blMoveObject(final String key, final String destKey, final Direction from, final Direction to,
							  final int timeout){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				blMove(key, destKey, from, to, timeout), this);
		return operations.operation();
	}

	@Override
	public <V> V blMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
							  final int timeout){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				blMove(key, destKey, from, to, timeout), this);
		return operations.operation();
	}

	@Override
	public <V> V blMoveObject(final String key, final String destKey, final Direction from, final Direction to,
							  final int timeout, final Class<V> clazz){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				blMove(key, destKey, from, to, timeout), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V blMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
							  final int timeout, final Class<V> clazz){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				blMove(key, destKey, from, to, timeout), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V blMoveObject(final String key, final String destKey, final Direction from, final Direction to,
							  final int timeout, final TypeReference<V> type){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				blMove(key, destKey, from, to, timeout), this);
		return operations.operation(type);
	}

	@Override
	public <V> V blMoveObject(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
							  final int timeout, final TypeReference<V> type){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				blMove(key, destKey, from, to, timeout), this);
		return operations.operation(type);
	}

	@Override
	public <V> List<V> blPopObject(final String[] keys, final int timeout){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				blPop(keys, timeout), this);
		return operations.operation();
	}

	@Override
	public <V> List<V> blPopObject(final byte[][] keys, final int timeout){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				blPop(keys, timeout), this);
		return operations.operation();
	}

	@Override
	public <V> List<V> blPopObject(final String[] keys, final int timeout, final Class<V> clazz){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				blPop(keys, timeout), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> List<V> blPopObject(final byte[][] keys, final int timeout, final Class<V> clazz){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				blPop(keys, timeout), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> List<V> blPopObject(final String[] keys, final int timeout, final TypeReference<V> type){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				blPop(keys, timeout), this);
		return operations.operation(type);
	}

	@Override
	public <V> List<V> blPopObject(final byte[][] keys, final int timeout, final TypeReference<V> type){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				blPop(keys, timeout), this);
		return operations.operation(type);
	}

	@Override
	public <V> List<V> brPopObject(final String[] keys, final int timeout){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				brPop(keys, timeout), this);
		return operations.operation();
	}

	@Override
	public <V> List<V> brPopObject(final byte[][] keys, final int timeout){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				brPop(keys, timeout), this);
		return operations.operation();
	}

	@Override
	public <V> List<V> brPopObject(final String[] keys, final int timeout, final Class<V> clazz){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				brPop(keys, timeout), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> List<V> brPopObject(final byte[][] keys, final int timeout, final Class<V> clazz){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				brPop(keys, timeout), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> List<V> brPopObject(final String[] keys, final int timeout, final TypeReference<V> type){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				brPop(keys, timeout), this);
		return operations.operation(type);
	}

	@Override
	public <V> List<V> brPopObject(final byte[][] keys, final int timeout, final TypeReference<V> type){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				brPop(keys, timeout), this);
		return operations.operation(type);
	}

	@Override
	public <V> V brPoplPushObject(final String key, final String destKey, final int timeout){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				brPoplPush(key, destKey, timeout), this);
		return operations.operation();
	}

	@Override
	public <V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				brPoplPush(key, destKey, timeout), this);
		return operations.operation();
	}

	@Override
	public <V> V brPoplPushObject(final String key, final String destKey, final int timeout, final Class<V> clazz){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				brPoplPush(key, destKey, timeout), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout, final Class<V> clazz){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				brPoplPush(key, destKey, timeout), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V brPoplPushObject(final String key, final String destKey, final int timeout,
								  final TypeReference<V> type){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				brPoplPush(key, destKey, timeout), this);
		return operations.operation(type);
	}

	@Override
	public <V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout,
								  final TypeReference<V> type){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				brPoplPush(key, destKey, timeout), this);
		return operations.operation(type);
	}

	@Override
	public <V> V lPopObject(final String key){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				lPop(key), this);
		return operations.operation();
	}

	@Override
	public <V> V lPopObject(final byte[] key){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				lPop(key), this);
		return operations.operation();
	}

	@Override
	public <V> V lPopObject(final String key, final Class<V> clazz){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				lPop(key), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V lPopObject(final byte[] key, final Class<V> clazz){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				lPop(key), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V lPopObject(final String key, final TypeReference<V> type){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				lPop(key), this);
		return operations.operation(type);
	}

	@Override
	public <V> V lPopObject(final byte[] key, final TypeReference<V> type){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				lPop(key), this);
		return operations.operation(type);
	}

	@Override
	public <V> Long lPush(final String key, final V value){
		return lPush(key, serializer.serialize(value));
	}

	@Override
	public <V> Long lPush(final byte[] key, final V value){
		return lPush(key, serializer.serializeAsBytes(value));
	}

	@Override
	public <V> Long lPush(final String key, final V... values){
		return lPush(key, serializer.serialize(values));
	}

	@Override
	public <V> Long lPush(final byte[] key, final V... values){
		return lPush(key, serializer.serializeAsBytes(values));
	}

	@Override
	public <V> Long lPushX(final String key, final V value){
		return lPushX(key, serializer.serialize(value));
	}

	@Override
	public <V> Long lPushX(final byte[] key, final V value){
		return lPushX(key, serializer.serializeAsBytes(value));
	}

	@Override
	public <V> Long lPushX(final String key, final V... values){
		return lPushX(key, serializer.serialize(values));
	}

	@Override
	public <V> Long lPushX(final byte[] key, final V... values){
		return lPushX(key, serializer.serializeAsBytes(values));
	}

	@Override
	public <V> V rPopObject(final String key){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				rPop(key), this);
		return operations.operation();
	}

	@Override
	public <V> V rPopObject(final byte[] key){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				rPop(key), this);
		return operations.operation();
	}

	@Override
	public <V> V rPopObject(final String key, final Class<V> clazz){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				rPop(key), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V rPopObject(final byte[] key, final Class<V> clazz){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				rPop(key), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V rPopObject(final String key, final TypeReference<V> type){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				rPop(key), this);
		return operations.operation(type);
	}

	@Override
	public <V> V rPopObject(final byte[] key, final TypeReference<V> type){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				rPop(key), this);
		return operations.operation(type);
	}

	@Override
	public <V> V rPoplPushObject(final String key, final String destKey){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				rPoplPush(key, destKey), this);
		return operations.operation();
	}

	@Override
	public <V> V rPoplPushObject(final byte[] key, final byte[] destKey){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				rPoplPush(key, destKey), this);
		return operations.operation();
	}

	@Override
	public <V> V rPoplPushObject(final String key, final String destKey, final Class<V> clazz){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				rPoplPush(key, destKey), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V rPoplPushObject(final byte[] key, final byte[] destKey, final Class<V> clazz){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				rPoplPush(key, destKey), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V rPoplPushObject(final String key, final String destKey, final TypeReference<V> type){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				rPoplPush(key, destKey), this);
		return operations.operation(type);
	}

	@Override
	public <V> V rPoplPushObject(final byte[] key, final byte[] destKey, final TypeReference<V> type){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				rPoplPush(key, destKey), this);
		return operations.operation(type);
	}

	@Override
	public <V> Long rPush(final String key, final V value){
		return rPush(key, serializer.serialize(value));
	}

	@Override
	public <V> Long rPush(final byte[] key, final V value){
		return rPush(key, serializer.serializeAsBytes(value));
	}

	@Override
	public <V> Long rPush(final String key, final V... values){
		return rPush(key, serializer.serialize(values));
	}

	@Override
	public <V> Long rPush(final byte[] key, final V... values){
		return rPush(key, serializer.serializeAsBytes(values));
	}

	@Override
	public <V> Long rPushX(final String key, final V value){
		return rPushX(key, serializer.serialize(value));
	}

	@Override
	public <V> Long rPushX(final byte[] key, final V value){
		return rPushX(key, serializer.serializeAsBytes(value));
	}

	@Override
	public <V> Long rPushX(final String key, final V... values){
		return rPushX(key, serializer.serialize(values));
	}

	@Override
	public <V> Long rPushX(final byte[] key, final V... values){
		return rPushX(key, serializer.serializeAsBytes(values));
	}

	@Override
	public <V> Long sAdd(final String key, final V member){
		return sAdd(key, serializer.serialize(member));
	}

	@Override
	public <V> Long sAdd(final byte[] key, final V member){
		return sAdd(key, serializer.serializeAsBytes(member));
	}

	@Override
	public <V> Long sAdd(final String key, final V... members){
		return sAdd(key, serializer.serialize(members));
	}

	@Override
	public <V> Long sAdd(final byte[] key, final V... members){
		return sAdd(key, serializer.serializeAsBytes(members));
	}

	@Override
	public <V> Set<V> sDiffObject(final String key, final String[] keys){
		final ObjectOperations.SetStringObjectOperations operations = new ObjectOperations.SetStringObjectOperations(
				sDiff(key, keys), this);
		return operations.operation();
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[] key, final byte[][] keys){
		final ObjectOperations.SetBinaryObjectOperations operations = new ObjectOperations.SetBinaryObjectOperations(
				sDiff(key, keys), this);
		return operations.operation();
	}

	@Override
	public <V> Set<V> sDiffObject(final String key, final String[] keys, final Class<V> clazz){
		final ObjectOperations.SetStringObjectOperations operations = new ObjectOperations.SetStringObjectOperations(
				sDiff(key, keys), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[] key, final byte[][] keys, final Class<V> clazz){
		final ObjectOperations.SetBinaryObjectOperations operations = new ObjectOperations.SetBinaryObjectOperations(
				sDiff(key, keys), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> Set<V> sDiffObject(final String key, final String[] keys, final TypeReference<V> type){
		final ObjectOperations.SetStringObjectOperations operations = new ObjectOperations.SetStringObjectOperations(
				sDiff(key, keys), this);
		return operations.operation(type);
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[] key, final byte[][] keys, final TypeReference<V> type){
		final ObjectOperations.SetBinaryObjectOperations operations = new ObjectOperations.SetBinaryObjectOperations(
				sDiff(key, keys), this);
		return operations.operation(type);
	}

	@Override
	public <V> Set<V> sMembersObject(final String key){
		final ObjectOperations.SetStringObjectOperations operations = new ObjectOperations.SetStringObjectOperations(
				sMembers(key), this);
		return operations.operation();
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key){
		final ObjectOperations.SetBinaryObjectOperations operations = new ObjectOperations.SetBinaryObjectOperations(
				sMembers(key), this);
		return operations.operation();
	}

	@Override
	public <V> Set<V> sMembersObject(final String key, final Class<V> clazz){
		final ObjectOperations.SetStringObjectOperations operations = new ObjectOperations.SetStringObjectOperations(
				sMembers(key), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key, final Class<V> clazz){
		final ObjectOperations.SetBinaryObjectOperations operations = new ObjectOperations.SetBinaryObjectOperations(
				sMembers(key), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> Set<V> sMembersObject(final String key, final TypeReference<V> type){
		final ObjectOperations.SetStringObjectOperations operations = new ObjectOperations.SetStringObjectOperations(
				sMembers(key), this);
		return operations.operation(type);
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key, final TypeReference<V> type){
		final ObjectOperations.SetBinaryObjectOperations operations = new ObjectOperations.SetBinaryObjectOperations(
				sMembers(key), this);
		return operations.operation(type);
	}

	@Override
	public <V> V sPopObject(final String key){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				sPop(key), this);
		return operations.operation();
	}

	@Override
	public <V> V sPopObject(final byte[] key){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				sPop(key), this);
		return operations.operation();
	}

	@Override
	public <V> V sPopObject(final String key, final Class<V> clazz){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				sPop(key), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V sPopObject(final byte[] key, final Class<V> clazz){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				sPop(key), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V sPopObject(final String key, final TypeReference<V> type){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				sPop(key), this);
		return operations.operation(type);
	}

	@Override
	public <V> V sPopObject(final byte[] key, final TypeReference<V> type){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				sPop(key), this);
		return operations.operation(type);
	}

	@Override
	public <V> Set<V> sPopObject(final String key, final long count){
		final ObjectOperations.SetStringObjectOperations operations = new ObjectOperations.SetStringObjectOperations(
				sPop(key, count), this);
		return operations.operation();
	}

	@Override
	public <V> Set<V> sPopObject(final byte[] key, final long count){
		final ObjectOperations.SetBinaryObjectOperations operations = new ObjectOperations.SetBinaryObjectOperations(
				sPop(key, count), this);
		return operations.operation();
	}

	@Override
	public <V> Set<V> sPopObject(final String key, final long count, final Class<V> clazz){
		final ObjectOperations.SetStringObjectOperations operations = new ObjectOperations.SetStringObjectOperations(
				sPop(key, count), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> Set<V> sPopObject(final byte[] key, final long count, final Class<V> clazz){
		final ObjectOperations.SetBinaryObjectOperations operations = new ObjectOperations.SetBinaryObjectOperations(
				sPop(key, count), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> Set<V> sPopObject(final String key, final long count, final TypeReference<V> type){
		final ObjectOperations.SetStringObjectOperations operations = new ObjectOperations.SetStringObjectOperations(
				sPop(key, count), this);
		return operations.operation(type);
	}

	@Override
	public <V> Set<V> sPopObject(final byte[] key, final long count, final TypeReference<V> type){
		final ObjectOperations.SetBinaryObjectOperations operations = new ObjectOperations.SetBinaryObjectOperations(
				sPop(key, count), this);
		return operations.operation(type);
	}

	@Override
	public <V> V sRandMemberObject(final String key){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				sRandMember(key), this);
		return operations.operation();
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				sRandMember(key), this);
		return operations.operation();
	}

	@Override
	public <V> V sRandMemberObject(final String key, final Class<V> clazz){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				sRandMember(key), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key, final Class<V> clazz){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				sRandMember(key), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V sRandMemberObject(final String key, final TypeReference<V> type){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				sRandMember(key), this);
		return operations.operation(type);
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key, final TypeReference<V> type){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				sRandMember(key), this);
		return operations.operation(type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				sRandMember(key, count), this);
		return operations.operation();
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				sRandMember(key, count), this);
		return operations.operation();
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count, final Class<V> clazz){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				sRandMember(key, count), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count, final Class<V> clazz){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				sRandMember(key, count), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count, final TypeReference<V> type){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				sRandMember(key, count), this);
		return operations.operation(type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count, final TypeReference<V> type){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				sRandMember(key, count), this);
		return operations.operation(type);
	}

	@Override
	public <V> Long sRem(final String key, final V member){
		return sRem(key, serializer.serialize(member));
	}

	@Override
	public <V> Long sRem(final byte[] key, final V member){
		return sRem(key, serializer.serializeAsBytes(member));
	}

	@Override
	public <V> Long sRem(final String key, final V... members){
		return sRem(key, serializer.serialize(members));
	}

	@Override
	public <V> Long sRem(final byte[] key, final V... members){
		return sRem(key, serializer.serializeAsBytes(members));
	}

	@Override
	public <V> Long zAdd(final String key, final double score, final V member){
		return zAdd(key, score, serializer.serialize(member));
	}

	@Override
	public <V> Long zAdd(final byte[] key, final double score, final V member){
		return zAdd(key, score, serializer.serialize(member));
	}

	@Override
	public <V> Long zAdd(final String key, final double score, final V member, final ZAddArgument argument){
		return zAdd(key, score, serializer.serialize(member), argument);
	}

	@Override
	public <V> Long zAdd(final byte[] key, final double score, final V member, final ZAddArgument argument){
		return zAdd(key, score, serializer.serialize(member), argument);
	}

	@Override
	public <V> Set<V> zDiffObject(final String key, final String[] keys){
		final ObjectOperations.SetStringObjectOperations operations = new ObjectOperations.SetStringObjectOperations(
				zDiff(key, keys), this);
		return operations.operation();
	}

	@Override
	public <V> Set<V> zDiffObject(final byte[] key, final byte[][] keys){
		final ObjectOperations.SetBinaryObjectOperations operations = new ObjectOperations.SetBinaryObjectOperations(
				zDiff(key, keys), this);
		return operations.operation();
	}

	@Override
	public <V> Set<V> zDiffObject(final String key, final String[] keys, final Class<V> clazz){
		final ObjectOperations.SetStringObjectOperations operations = new ObjectOperations.SetStringObjectOperations(
				zDiff(key, keys), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> Set<V> zDiffObject(final byte[] key, final byte[][] keys, final Class<V> clazz){
		final ObjectOperations.SetBinaryObjectOperations operations = new ObjectOperations.SetBinaryObjectOperations(
				zDiff(key, keys), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> Set<V> zDiffObject(final String key, final String[] keys, final TypeReference<V> type){
		final ObjectOperations.SetStringObjectOperations operations = new ObjectOperations.SetStringObjectOperations(
				zDiff(key, keys), this);
		return operations.operation(type);
	}

	@Override
	public <V> Set<V> zDiffObject(final byte[] key, final byte[][] keys, final TypeReference<V> type){
		final ObjectOperations.SetBinaryObjectOperations operations = new ObjectOperations.SetBinaryObjectOperations(
				zDiff(key, keys), this);
		return operations.operation(type);
	}

	@Override
	public <V> V getObject(final String key){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				get(key), this);
		return operations.operation();
	}

	@Override
	public <V> V getObject(final byte[] key){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(get(key),
				this);
		return operations.operation();
	}

	@Override
	public <V> V getObject(final String key, final Class<V> clazz){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				get(key), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V getObject(final byte[] key, final Class<V> clazz){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(get(key),
				this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V getObject(final String key, final TypeReference<V> type){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				get(key), this);
		return operations.operation(type);
	}

	@Override
	public <V> V getObject(final byte[] key, final TypeReference<V> type){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(get(key),
				this);
		return operations.operation(type);
	}

	@Override
	public <V> V getSet(final String key, final V value){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				getSet(key, serializer.serialize(value)), this);
		return operations.operation();
	}

	@Override
	public <V> V getSet(final byte[] key, final V value){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				getSet(key, serializer.serializeAsBytes(value)), this);
		return operations.operation();
	}

	@Override
	public <V> V getSet(final String key, final V value, final Class<V> clazz){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				getSet(key, serializer.serialize(value)), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V getSet(final byte[] key, final V value, final Class<V> clazz){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				getSet(key, serializer.serializeAsBytes(value)), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V getSet(final String key, final V value, final TypeReference<V> type){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				getSet(key, serializer.serialize(value)), this);
		return operations.operation(type);
	}

	@Override
	public <V> V getSet(final byte[] key, final V value, final TypeReference<V> type){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				getSet(key, serializer.serializeAsBytes(value)), this);
		return operations.operation(type);
	}

	@Override
	public <V> V getExObject(final String key, final GetExArgument getExArgument){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				getEx(key, getExArgument), this);
		return operations.operation();
	}

	@Override
	public <V> V getExObject(final byte[] key, final GetExArgument getExArgument){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				getEx(key, getExArgument), this);
		return operations.operation();
	}

	@Override
	public <V> V getExObject(final String key, final GetExArgument getExArgument, final Class<V> clazz){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				getEx(key, getExArgument), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V getExObject(final byte[] key, final GetExArgument getExArgument, final Class<V> clazz){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				getEx(key, getExArgument),
				this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V getExObject(final String key, final GetExArgument getExArgument, final TypeReference<V> type){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				getEx(key, getExArgument), this);
		return operations.operation(type);
	}

	@Override
	public <V> V getExObject(final byte[] key, final GetExArgument getExArgument, final TypeReference<V> type){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				getEx(key, getExArgument),
				this);
		return operations.operation(type);
	}

	@Override
	public <V> V getDelObject(final String key){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				getDel(key), this);
		return operations.operation();
	}

	@Override
	public <V> V getDelObject(final byte[] key){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				getDel(key), this);
		return operations.operation();
	}

	@Override
	public <V> V getDelObject(final String key, final Class<V> clazz){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				getDel(key), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V getDelObject(final byte[] key, final Class<V> clazz){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				getDel(key), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> V getDelObject(final String key, final TypeReference<V> type){
		final ObjectOperations.StringObjectOperations operations = new ObjectOperations.StringObjectOperations(
				getDel(key), this);
		return operations.operation(type);
	}

	@Override
	public <V> V getDelObject(final byte[] key, final TypeReference<V> type){
		final ObjectOperations.BinaryObjectOperations operations = new ObjectOperations.BinaryObjectOperations(
				getDel(key), this);
		return operations.operation(type);
	}

	@Override
	public <V> List<V> mGetObject(final String... keys){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				mGet(keys), this);
		return operations.operation();
	}

	@Override
	public <V> List<V> mGetObject(final byte[]... keys){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				mGet(keys), this);
		return operations.operation();
	}

	@Override
	public <V> List<V> mGetObject(final String[] keys, final Class<V> clazz){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				mGet(keys), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> List<V> mGetObject(final byte[][] keys, final Class<V> clazz){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				mGet(keys), this);
		return operations.operation(clazz);
	}

	@Override
	public <V> List<V> mGetObject(final String[] keys, final TypeReference<V> type){
		final ObjectOperations.ListStringObjectOperations operations = new ObjectOperations.ListStringObjectOperations(
				mGet(keys), this);
		return operations.operation(type);
	}

	@Override
	public <V> List<V> mGetObject(final byte[][] keys, final TypeReference<V> type){
		final ObjectOperations.ListBinaryObjectOperations operations = new ObjectOperations.ListBinaryObjectOperations(
				mGet(keys), this);
		return operations.operation(type);
	}

	@Override
	public <V> Status pSetEx(final String key, final V value, final int lifetime){
		return pSetEx(key, serializer.serialize(value), lifetime);
	}

	@Override
	public <V> Status pSetEx(final byte[] key, final V value, final int lifetime){
		return pSetEx(key, serializer.serializeAsBytes(value), lifetime);
	}

	@Override
	public <V> Status set(final String key, final V value){
		return set(key, serializer.serialize(value));
	}

	@Override
	public <V> Status set(final byte[] key, final V value){
		return set(key, serializer.serializeAsBytes(value));
	}

	@Override
	public <V> Status set(final String key, final V value, final SetArgument setArgument){
		return set(key, serializer.serialize(value), setArgument);
	}

	@Override
	public <V> Status set(final byte[] key, final V value, final SetArgument setArgument){
		return set(key, serializer.serializeAsBytes(value), setArgument);
	}

	@Override
	public <V> Status setEx(final String key, final V value, final int lifetime){
		return setEx(key, serializer.serialize(value), lifetime);
	}

	@Override
	public <V> Status setEx(final byte[] key, final V value, final int lifetime){
		return setEx(key, serializer.serializeAsBytes(value), lifetime);
	}

	@Override
	public <V> Status setNx(final String key, final V value){
		return setNx(key, serializer.serialize(value));
	}

	@Override
	public <V> Status setNx(final byte[] key, final V value){
		return setNx(key, serializer.serializeAsBytes(value));
	}

	@Override
	public void discard(){
		super.discard();

		if(isTransactionOrPipeline()){
			index.set(-1);
			txConverters.remove();
		}
	}

	@Override
	public List<Object> exec(){
		List<Object> result = super.exec();

		Map<Integer, Function<?, ?>> map = txConverters.get();

		if(Validate.isNotEmpty(map)){
			for(int i = 0; i < result.size(); i++){
				Function<Object, Object> fun = (Function<Object, Object>) map.get(i);

				if(fun != null){
					result.set(i, fun.apply(result.get(i)));
				}
			}
		}

		index.set(-1);
		txConverters.remove();

		return result;
	}

	protected static Map<Integer, Function<?, ?>> getTxConverters(){
		Map<Integer, Function<?, ?>> txResult = txConverters.get();

		if(txResult == null){
			txResult = new LinkedHashMap<>(16, 0.8F);
			txConverters.set(txResult);
		}

		return txResult;
	}

	interface ObjectOperations<T> {

		abstract class AbstractObjectOperations<T> implements ObjectOperations<T> {

			protected final T value;

			protected final RedisTemplate redisTemplate;

			protected final AtomicInteger index;

			protected final Serializer serializer;

			public AbstractObjectOperations(final T value, final RedisTemplate redisTemplate){
				this.value = value;
				this.redisTemplate = redisTemplate;
				this.index = redisTemplate.index;
				this.serializer = redisTemplate.serializer;
			}

			protected static <V> void addConverter(AtomicInteger index, Function<String, V> function){
				getTxConverters().put(index.get(), function);
			}

			protected static <V> void addBinaryConverter(AtomicInteger index, Function<byte[], V> function){
				getTxConverters().put(index.get(), function);
			}

		}

		final class StringObjectOperations extends AbstractObjectOperations<String> {

			public StringObjectOperations(final String value, final RedisTemplate redisTemplate){
				super(value, redisTemplate);
			}

			public <V> V operation(){
				if(redisTemplate.isTransactionOrPipeline()){
					addConverter(index, serializer::deserialize);
				}else{
					return serializer.deserialize(this.value);
				}

				return null;
			}

			public <V> V operation(final Class<V> clazz){
				if(redisTemplate.isTransactionOrPipeline()){
					addConverter(index, (value)->serializer.deserialize(value, clazz));
				}else{
					return serializer.deserialize(this.value, clazz);
				}

				return null;
			}

			public <V> V operation(final TypeReference<V> type){
				if(redisTemplate.isTransactionOrPipeline()){
					addConverter(index, (value)->serializer.deserialize(value, type));
				}else{
					return serializer.deserialize(this.value, type);
				}

				return null;
			}

		}

		final class BinaryObjectOperations extends AbstractObjectOperations<byte[]> {

			public BinaryObjectOperations(final byte[] value, final RedisTemplate redisTemplate){
				super(value, redisTemplate);
			}

			public <V> V operation(){
				if(redisTemplate.isTransactionOrPipeline()){
					addBinaryConverter(index, serializer::deserializeBytes);
				}else{
					return serializer.deserializeBytes(this.value);
				}

				return null;
			}

			public <V> V operation(final Class<V> clazz){
				if(redisTemplate.isTransactionOrPipeline()){
					addBinaryConverter(index, (value)->serializer.deserializeBytes(value, clazz));
				}else{
					return serializer.deserializeBytes(this.value, clazz);
				}

				return null;
			}

			public <V> V operation(final TypeReference<V> type){
				if(redisTemplate.isTransactionOrPipeline()){
					addBinaryConverter(index, (value)->serializer.deserializeBytes(value, type));
				}else{
					return serializer.deserializeBytes(this.value, type);
				}

				return null;
			}

		}

		final class ListStringObjectOperations extends AbstractObjectOperations<List<String>> {

			public ListStringObjectOperations(final List<String> value, final RedisTemplate redisTemplate){
				super(value, redisTemplate);
			}

			public <V> List<V> operation(){
				if(redisTemplate.isTransactionOrPipeline()){
					addConverter(index, serializer::deserialize);
				}else{
					if(this.value != null){
						final List<V> result = new ArrayList<>(this.value.size());

						for(String s : this.value){
							result.add(serializer.deserialize(s));
						}

						return result;
					}
				}

				return null;
			}

			public <V> List<V> operation(final Class<V> clazz){
				if(redisTemplate.isTransactionOrPipeline()){
					addConverter(index, (value)->serializer.deserialize(value, clazz));
				}else{
					if(this.value != null){
						return this.value.stream().map((value)->serializer.deserialize(value, clazz))
								.collect(Collectors.toList());
					}
				}

				return null;
			}

			public <V> List<V> operation(final TypeReference<V> type){
				if(redisTemplate.isTransactionOrPipeline()){
					addConverter(index, (value)->serializer.deserialize(value, type));
				}else{
					if(this.value != null){
						return this.value.stream().map((value)->serializer.deserialize(value, type))
								.collect(Collectors.toCollection(ArrayList::new));
					}
				}

				return null;
			}

		}

		final class ListBinaryObjectOperations extends AbstractObjectOperations<List<byte[]>> {

			public ListBinaryObjectOperations(final List<byte[]> value, final RedisTemplate redisTemplate){
				super(value, redisTemplate);
			}

			public <V> List<V> operation(){
				if(redisTemplate.isTransactionOrPipeline()){
					addBinaryConverter(index, serializer::deserializeBytes);
				}else{
					if(this.value != null){
						final List<V> result = new ArrayList<>(this.value.size());

						for(byte[] b : this.value){
							result.add(serializer.deserializeBytes(b));
						}

						return result;
					}
				}

				return null;
			}

			public <V> List<V> operation(final Class<V> clazz){
				if(redisTemplate.isTransactionOrPipeline()){
					addBinaryConverter(index, (value)->serializer.deserializeBytes(value, clazz));
				}else{
					if(this.value != null){
						return this.value.stream().map((value)->serializer.deserializeBytes(value, clazz))
								.collect(Collectors.toList());
					}
				}

				return null;
			}

			public <V> List<V> operation(final TypeReference<V> type){
				if(redisTemplate.isTransactionOrPipeline()){
					addBinaryConverter(index, (value)->serializer.deserializeBytes(value, type));
				}else{
					if(this.value != null){
						return this.value.stream().map((value)->serializer.deserializeBytes(value, type))
								.collect(Collectors.toCollection(ArrayList::new));
					}
				}

				return null;
			}

		}

		final class SetStringObjectOperations extends AbstractObjectOperations<Set<String>> {

			public SetStringObjectOperations(final Set<String> value, final RedisTemplate redisTemplate){
				super(value, redisTemplate);
			}

			public <V> Set<V> operation(){
				if(redisTemplate.isTransactionOrPipeline()){
					addConverter(index, serializer::deserialize);
				}else{
					if(this.value != null){
						final Set<V> result = new LinkedHashSet<>(this.value.size());

						for(String s : this.value){
							result.add(serializer.deserialize(s));
						}

						return result;
					}
				}

				return null;
			}

			public <V> Set<V> operation(final Class<V> clazz){
				if(redisTemplate.isTransactionOrPipeline()){
					addConverter(index, (value)->serializer.deserialize(value, clazz));
				}else{
					if(this.value != null){
						return this.value.stream().map((value)->serializer.deserialize(value, clazz))
								.collect(Collectors.toCollection(LinkedHashSet::new));
					}
				}

				return null;
			}

			public <V> Set<V> operation(final TypeReference<V> type){
				if(redisTemplate.isTransactionOrPipeline()){
					addConverter(index, (value)->serializer.deserialize(value, type));
				}else{
					if(this.value != null){
						return this.value.stream().map((value)->serializer.deserialize(value, type))
								.collect(Collectors.toCollection(LinkedHashSet::new));
					}
				}

				return null;
			}

		}

		final class SetBinaryObjectOperations extends AbstractObjectOperations<Set<byte[]>> {

			public SetBinaryObjectOperations(final Set<byte[]> value, final RedisTemplate redisTemplate){
				super(value, redisTemplate);
			}

			public <V> Set<V> operation(){
				if(redisTemplate.isTransactionOrPipeline()){
					addBinaryConverter(index, serializer::deserializeBytes);
				}else{
					if(this.value != null){
						final Set<V> result = new LinkedHashSet<>(this.value.size());

						for(byte[] b : this.value){
							result.add(serializer.deserializeBytes(b));
						}

						return result;
					}
				}

				return null;
			}

			public <V> Set<V> operation(final Class<V> clazz){
				if(redisTemplate.isTransactionOrPipeline()){
					addBinaryConverter(index, (value)->serializer.deserializeBytes(value, clazz));
				}else{
					if(this.value != null){
						return this.value.stream().map((value)->serializer.deserializeBytes(value, clazz))
								.collect(Collectors.toCollection(LinkedHashSet::new));
					}
				}

				return null;
			}

			public <V> Set<V> operation(final TypeReference<V> type){
				if(redisTemplate.isTransactionOrPipeline()){
					addBinaryConverter(index, (value)->serializer.deserializeBytes(value, type));
				}else{
					if(this.value != null){
						return this.value.stream().map((value)->serializer.deserializeBytes(value, type))
								.collect(Collectors.toCollection(LinkedHashSet::new));
					}
				}

				return null;
			}

		}

		final class MapStringObjectOperations extends AbstractObjectOperations<Map<String, String>> {

			public MapStringObjectOperations(final Map<String, String> value, final RedisTemplate redisTemplate){
				super(value, redisTemplate);
			}

			public <V> Map<String, V> operation(){
				if(redisTemplate.isTransactionOrPipeline()){
					addConverter(index, serializer::deserialize);
				}else{
					if(this.value != null){
						return Maps.map(this.value, (key)->key, serializer::deserialize);
					}
				}

				return null;
			}

			public <V> Map<String, V> operation(final Class<V> clazz){
				if(redisTemplate.isTransactionOrPipeline()){
					addConverter(index, (value)->serializer.deserialize(value, clazz));
				}else{
					if(this.value != null){
						return Maps.map(this.value, (key)->key, (value)->serializer.deserialize(value, clazz));
					}
				}

				return null;
			}

			public <V> Map<String, V> operation(final TypeReference<V> type){
				if(redisTemplate.isTransactionOrPipeline()){
					addConverter(index, (value)->serializer.deserialize(value, type));
				}else{
					if(this.value != null){
						return Maps.map(this.value, (key)->key, (value)->serializer.deserialize(value, type));
					}
				}

				return null;
			}

		}

		final class MapBinaryObjectOperations extends AbstractObjectOperations<Map<byte[], byte[]>> {

			public MapBinaryObjectOperations(final Map<byte[], byte[]> value, final RedisTemplate redisTemplate){
				super(value, redisTemplate);
			}

			public <V> Map<byte[], V> operation(){
				if(redisTemplate.isTransactionOrPipeline()){
					addBinaryConverter(index, serializer::deserializeBytes);
				}else{
					if(this.value != null){
						return Maps.map(this.value, (key)->key, serializer::deserializeBytes);
					}
				}

				return null;
			}

			public <V> Map<byte[], V> operation(final Class<V> clazz){
				if(redisTemplate.isTransactionOrPipeline()){
					addBinaryConverter(index, (value)->serializer.deserializeBytes(value, clazz));
				}else{
					if(this.value != null){
						return Maps.map(this.value, (key)->key, (value)->serializer.deserializeBytes(value, clazz));
					}
				}

				return null;
			}

			public <V> Map<byte[], V> operation(final TypeReference<V> type){
				if(redisTemplate.isTransactionOrPipeline()){
					addBinaryConverter(index, (value)->serializer.deserializeBytes(value, type));
				}else{
					if(this.value != null){
						return Maps.map(this.value, (key)->key, (value)->serializer.deserializeBytes(value, type));
					}
				}

				return null;
			}

		}

	}

	 */

}
