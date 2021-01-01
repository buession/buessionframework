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
package com.buession.redis;

import com.buession.core.serializer.type.TypeReference;
import com.buession.core.utils.ReflectUtils;
import com.buession.core.validator.Validate;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.operations.*;
import com.buession.redis.transaction.Converters;
import com.buession.redis.transaction.TxResult;
import redis.clients.jedis.ListPosition;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis 基本操作封装扩展，可序列化对象和反序列化为对象
 *
 * @author Yong.Teng
 */
public class RedisTemplate extends BaseRedisTemplate implements ConnectionOperations, GeoOperations, HashOperations,
		HyperLogLogOperations, KeyOperations, ListOperations, PubSubOperations, ScriptingOperations, ServerOperations,
		SetOperations, SortedSetOperations, StringOperations, TransactionOperations {

	private ThreadLocal<Map<Integer, TxResult>> txResults = new ThreadLocal<>();

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

	@Override
	public <V> V hGetObject(final String key, final String field){
		return simpleStringCall(hGet(key, field));
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field){
		return simpleBinaryCall(hGet(key, field));
	}

	@Override
	public <V> V hGetObject(final String key, final String field, final Class<V> clazz){
		return simpleStringCall(hGet(key, field), clazz);
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field, final Class<V> clazz){
		return simpleBinaryCall(hGet(key, field), clazz);
	}

	@Override
	public <V> V hGetObject(final String key, final String field, final TypeReference<V> type){
		return simpleStringCall(hGet(key, field), type);
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field, final TypeReference<V> type){
		return simpleBinaryCall(hGet(key, field), type);
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key){
		return mapStringCall(hGetAll(key));
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key){
		return mapBinaryCall(hGetAll(key));
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key, final Class<V> clazz){
		return mapStringCall(hGetAll(key), clazz);
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key, final Class<V> clazz){
		return mapBinaryCall(hGetAll(key), clazz);
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key, final TypeReference<V> type){
		return mapStringCall(hGetAll(key), type);
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key, final TypeReference<V> type){
		return mapBinaryCall(hGetAll(key), type);
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String... fields){
		return listStringCall(hMGet(key, fields));
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[]... fields){
		return listBinaryCall(hMGet(key, fields));
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String[] fields, final Class<V> clazz){
		return listStringCall(hMGet(key, fields), clazz);
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final Class<V> clazz){
		return listBinaryCall(hMGet(key, fields), clazz);
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String[] fields, final TypeReference<V> type){
		return listStringCall(hMGet(key, fields), type);
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final TypeReference<V> type){
		return listBinaryCall(hMGet(key, fields), type);
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
		return listStringCall(hVals(key));
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key){
		return listBinaryCall(hVals(key));
	}

	@Override
	public <V> List<V> hValsObject(final String key, final Class<V> clazz){
		return listStringCall(hVals(key), clazz);
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key, final Class<V> clazz){
		return listBinaryCall(hVals(key), clazz);
	}

	@Override
	public <V> List<V> hValsObject(final String key, final TypeReference<V> type){
		return listStringCall(hVals(key), type);
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key, final TypeReference<V> type){
		return listBinaryCall(hVals(key), type);
	}

	@Override
	public <V> List<V> blPopObject(final String key, final int timeout){
		return listStringCall(blPop(key, timeout));
	}

	@Override
	public <V> List<V> blPopObject(final byte[] key, final int timeout){
		return listBinaryCall(blPop(key, timeout));
	}

	@Override
	public <V> List<V> blPopObject(final String key, final int timeout, final Class<V> clazz){
		return listStringCall(blPop(key, timeout), clazz);
	}

	@Override
	public <V> List<V> blPopObject(final byte[] key, final int timeout, final Class<V> clazz){
		return listBinaryCall(blPop(key, timeout), clazz);
	}

	@Override
	public <V> List<V> blPopObject(final String key, final int timeout, final TypeReference<V> type){
		return listStringCall(blPop(key, timeout), type);
	}

	@Override
	public <V> List<V> blPopObject(final byte[] key, final int timeout, final TypeReference<V> type){
		return listBinaryCall(blPop(key, timeout), type);
	}

	@Override
	public <V> List<V> brPopObject(final String key, final int timeout){
		return listStringCall(brPop(key, timeout));
	}

	@Override
	public <V> List<V> brPopObject(final byte[] key, final int timeout){
		return listBinaryCall(brPop(key, timeout));
	}

	@Override
	public <V> List<V> brPopObject(final String key, final int timeout, final Class<V> clazz){
		return listStringCall(brPop(key, timeout), clazz);
	}

	@Override
	public <V> List<V> brPopObject(final byte[] key, final int timeout, final Class<V> clazz){
		return listBinaryCall(brPop(key, timeout), clazz);
	}

	@Override
	public <V> List<V> brPopObject(final String key, final int timeout, final TypeReference<V> type){
		return listStringCall(brPop(key, timeout), type);
	}

	@Override
	public <V> List<V> brPopObject(final byte[] key, final int timeout, final TypeReference<V> type){
		return listBinaryCall(brPop(key, timeout), type);
	}

	@Override
	public <V> V brPoplPushObject(final String key, final String destKey, final int timeout){
		return simpleStringCall(brPoplPush(key, destKey, timeout));
	}

	@Override
	public <V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout){
		return simpleBinaryCall(brPoplPush(key, destKey, timeout));
	}

	@Override
	public <V> V brPoplPushObject(final String key, final String destKey, final int timeout, final Class<V> clazz){
		return simpleStringCall(brPoplPush(key, destKey, timeout), clazz);
	}

	@Override
	public <V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout, final Class<V> clazz){
		return simpleBinaryCall(brPoplPush(key, destKey, timeout), clazz);
	}

	@Override
	public <V> V brPoplPushObject(final String key, final String destKey, final int timeout,
			final TypeReference<V> type){
		return simpleStringCall(brPoplPush(key, destKey, timeout), type);
	}

	@Override
	public <V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout,
			final TypeReference<V> type){
		return simpleBinaryCall(brPoplPush(key, destKey, timeout), type);
	}

	@Override
	public <V> V lIndexObject(final String key, final int index){
		return simpleStringCall(lIndex(key, index));
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final int index){
		return simpleBinaryCall(lIndex(key, index));
	}

	@Override
	public <V> V lIndexObject(final String key, final int index, final Class<V> clazz){
		return simpleStringCall(lIndex(key, index), clazz);
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final int index, final Class<V> clazz){
		return simpleBinaryCall(lIndex(key, index), clazz);
	}

	@Override
	public <V> V lIndexObject(final String key, final int index, final TypeReference<V> type){
		return simpleStringCall(lIndex(key, index), type);
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final int index, final TypeReference<V> type){
		return simpleBinaryCall(lIndex(key, index), type);
	}

	@Override
	public <V> V lIndexObject(final String key, final long index){
		return simpleStringCall(lIndex(key, index));
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index){
		return simpleBinaryCall(lIndex(key, index));
	}

	@Override
	public <V> V lIndexObject(final String key, final long index, final Class<V> clazz){
		return simpleStringCall(lIndex(key, index), clazz);
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index, final Class<V> clazz){
		return simpleBinaryCall(lIndex(key, index), clazz);
	}

	@Override
	public <V> V lIndexObject(final String key, final long index, final TypeReference<V> type){
		return simpleStringCall(lIndex(key, index), type);
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index, final TypeReference<V> type){
		return simpleBinaryCall(lIndex(key, index), type);
	}

	@Override
	public <V> Long lInsert(final String key, final V value, final ListPosition position, final V pivot){
		return lInsert(key, serializer.serialize(value), position, serializer.serialize(pivot));
	}

	@Override
	public <V> Long lInsert(final byte[] key, final V value, final ListPosition position, final V pivot){
		return lInsert(key, serializer.serializeAsBytes(value), position, serializer.serializeAsBytes(pivot));
	}

	@Override
	public <V> V lPopObject(final String key){
		return simpleStringCall(lPop(key));
	}

	@Override
	public <V> V lPopObject(final byte[] key){
		return simpleBinaryCall(lPop(key));
	}

	@Override
	public <V> V lPopObject(final String key, final Class<V> clazz){
		return simpleStringCall(lPop(key), clazz);
	}

	@Override
	public <V> V lPopObject(final byte[] key, final Class<V> clazz){
		return simpleBinaryCall(lPop(key), clazz);
	}

	@Override
	public <V> V lPopObject(final String key, final TypeReference<V> type){
		return simpleStringCall(lPop(key), type);
	}

	@Override
	public <V> V lPopObject(final byte[] key, final TypeReference<V> type){
		return simpleBinaryCall(lPop(key), type);
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
	public <V> List<V> lRangeObject(final String key, final int start, final int end){
		return listStringCall(lRange(key, start, end));
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final int start, final int end){
		return listBinaryCall(lRange(key, start, end));
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final long start, final long end){
		return listStringCall(lRange(key, start, end));
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end){
		return listBinaryCall(lRange(key, start, end));
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final int start, final int end, final Class<V> clazz){
		return listStringCall(lRange(key, start, end), clazz);
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final int start, final int end, final Class<V> clazz){
		return listBinaryCall(lRange(key, start, end), clazz);
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final long start, final long end, final Class<V> clazz){
		return listStringCall(lRange(key, start, end), clazz);
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end, final Class<V> clazz){
		return listBinaryCall(lRange(key, start, end), clazz);
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final int start, final int end, final TypeReference<V> type){
		return listStringCall(lRange(key, start, end), type);
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final int start, final int end, final TypeReference<V> type){
		return listBinaryCall(lRange(key, start, end), type);
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final long start, final long end, final TypeReference<V> type){
		return listStringCall(lRange(key, start, end), type);
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end, final TypeReference<V> type){
		return listBinaryCall(lRange(key, start, end), type);
	}

	@Override
	public <V> Status lSet(final String key, final int index, final V value){
		return lSet(key, index, serializer.serialize(value));
	}

	@Override
	public <V> Status lSet(final byte[] key, final int index, final V value){
		return lSet(key, index, serializer.serializeAsBytes(value));
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
	public <V> V rPopObject(final String key){
		return simpleStringCall(rPop(key));
	}

	@Override
	public <V> V rPopObject(final byte[] key){
		return simpleBinaryCall(rPop(key));
	}

	@Override
	public <V> V rPopObject(final String key, final Class<V> clazz){
		return simpleStringCall(rPop(key), clazz);
	}

	@Override
	public <V> V rPopObject(final byte[] key, final Class<V> clazz){
		return simpleBinaryCall(rPop(key), clazz);
	}

	@Override
	public <V> V rPopObject(final String key, final TypeReference<V> type){
		return simpleStringCall(rPop(key), type);
	}

	@Override
	public <V> V rPopObject(final byte[] key, final TypeReference<V> type){
		return simpleBinaryCall(rPop(key), type);
	}

	@Override
	public <V> V rPoplPushObject(final String key, final String destKey){
		return simpleStringCall(rPoplPush(key, destKey));
	}

	@Override
	public <V> V rPoplPushObject(final byte[] key, final byte[] destKey){
		return simpleBinaryCall(rPoplPush(key, destKey));
	}

	@Override
	public <V> V rPoplPushObject(final String key, final String destKey, final Class<V> clazz){
		return simpleStringCall(rPoplPush(key, destKey), clazz);
	}

	@Override
	public <V> V rPoplPushObject(final byte[] key, final byte[] destKey, final Class<V> clazz){
		return simpleBinaryCall(rPoplPush(key, destKey), clazz);
	}

	@Override
	public <V> V rPoplPushObject(final String key, final String destKey, final TypeReference<V> type){
		return simpleStringCall(rPoplPush(key, destKey), type);
	}

	@Override
	public <V> V rPoplPushObject(final byte[] key, final byte[] destKey, final TypeReference<V> type){
		return simpleBinaryCall(rPoplPush(key, destKey), type);
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
	public <V> Set<V> sDiffObject(final String key){
		return setStringCall(sDiff(key));
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[] key){
		return setBinaryCall(sDiff(key));
	}

	@Override
	public <V> Set<V> sDiffObject(final String key, final Class<V> clazz){
		return setStringCall(sDiff(key), clazz);
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[] key, final Class<V> clazz){
		return setBinaryCall(sDiff(key), clazz);
	}

	@Override
	public <V> Set<V> sDiffObject(final String key, final TypeReference<V> type){
		return setStringCall(sDiff(key), type);
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[] key, final TypeReference<V> type){
		return setBinaryCall(sDiff(key), type);
	}

	@Override
	public <V> Set<V> sMembersObject(final String key){
		return setStringCall(sMembers(key));
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key){
		return setBinaryCall(sMembers(key));
	}

	@Override
	public <V> Set<V> sMembersObject(final String key, final Class<V> clazz){
		return setStringCall(sMembers(key), clazz);
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key, final Class<V> clazz){
		return setBinaryCall(sMembers(key), clazz);
	}

	@Override
	public <V> Set<V> sMembersObject(final String key, final TypeReference<V> type){
		return setStringCall(sMembers(key), type);
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key, final TypeReference<V> type){
		return setBinaryCall(sMembers(key), type);
	}

	@Override
	public <V> V sPopObject(final String key){
		return simpleStringCall(sPop(key));
	}

	@Override
	public <V> V sPopObject(final byte[] key){
		return simpleBinaryCall(sPop(key));
	}

	@Override
	public <V> V sPopObject(final String key, final Class<V> clazz){
		return simpleStringCall(sPop(key), clazz);
	}

	@Override
	public <V> V sPopObject(final byte[] key, final Class<V> clazz){
		return simpleBinaryCall(sPop(key), clazz);
	}

	@Override
	public <V> V sPopObject(final String key, final TypeReference<V> type){
		return simpleStringCall(sPop(key), type);
	}

	@Override
	public <V> V sPopObject(final byte[] key, final TypeReference<V> type){
		return simpleBinaryCall(sPop(key), type);
	}

	@Override
	public <V> V sRandMemberObject(final String key){
		return simpleStringCall(sRandMember(key));
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key){
		return simpleBinaryCall(sRandMember(key));
	}

	@Override
	public <V> V sRandMemberObject(final String key, final Class<V> clazz){
		return simpleStringCall(sRandMember(key), clazz);
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key, final Class<V> clazz){
		return simpleBinaryCall(sRandMember(key), clazz);
	}

	@Override
	public <V> V sRandMemberObject(final String key, final TypeReference<V> type){
		return simpleStringCall(sRandMember(key), type);
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key, final TypeReference<V> type){
		return simpleBinaryCall(sRandMember(key), type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final int count){
		return listStringCall(sRandMember(key, count));
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final int count){
		return listBinaryCall(sRandMember(key, count));
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count){
		return listStringCall(sRandMember(key, count));
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count){
		return listBinaryCall(sRandMember(key, count));
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final int count, final Class<V> clazz){
		return listStringCall(sRandMember(key, count), clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final int count, final Class<V> clazz){
		return listBinaryCall(sRandMember(key, count), clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count, final Class<V> clazz){
		return listStringCall(sRandMember(key, count), clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count, final Class<V> clazz){
		return listBinaryCall(sRandMember(key, count), clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final int count, final TypeReference<V> type){
		return listStringCall(sRandMember(key, count), type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final int count, final TypeReference<V> type){
		return listBinaryCall(sRandMember(key, count), type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count, final TypeReference<V> type){
		return listStringCall(sRandMember(key, count), type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count, final TypeReference<V> type){
		return listBinaryCall(sRandMember(key, count), type);
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
	public <V> V getObject(final String key){
		return simpleStringCall(get(key));
	}

	@Override
	public <V> V getObject(final byte[] key){
		return simpleBinaryCall(get(key));
	}

	@Override
	public <V> V getObject(final String key, final Class<V> clazz){
		return simpleStringCall(get(key), clazz);
	}

	@Override
	public <V> V getObject(final byte[] key, final Class<V> clazz){
		return simpleBinaryCall(get(key), clazz);
	}

	@Override
	public <V> V getObject(final String key, final TypeReference<V> type){
		return simpleStringCall(get(key), type);
	}

	@Override
	public <V> V getObject(final byte[] key, final TypeReference<V> type){
		return simpleBinaryCall(get(key), type);
	}

	@Override
	public <V> V getSet(final String key, final V value){
		return simpleStringCall(getSet(key, serializer.serialize(value)));
	}

	@Override
	public <V> V getSet(final byte[] key, final V value){
		return simpleBinaryCall(getSet(key, serializer.serializeAsBytes(value)));
	}

	@Override
	public <V> V getSet(final String key, final V value, final Class<V> clazz){
		return simpleStringCall(getSet(key, serializer.serialize(value)), clazz);
	}

	@Override
	public <V> V getSet(final byte[] key, final V value, final Class<V> clazz){
		return simpleBinaryCall(getSet(key, serializer.serializeAsBytes(value)), clazz);
	}

	@Override
	public <V> V getSet(final String key, final V value, final TypeReference<V> type){
		return simpleStringCall(getSet(key, serializer.serialize(value)), type);
	}

	@Override
	public <V> V getSet(final byte[] key, final V value, final TypeReference<V> type){
		return simpleBinaryCall(getSet(key, serializer.serializeAsBytes(value)), type);
	}

	@Override
	public <V> List<V> mGetObject(final String... keys){
		return listStringCall(mGet(keys));
	}

	@Override
	public <V> List<V> mGetObject(final byte[]... keys){
		return listBinaryCall(mGet(keys));
	}

	@Override
	public <V> List<V> mGetObject(final String[] keys, final Class<V> clazz){
		return listStringCall(mGet(keys), clazz);
	}

	@Override
	public <V> List<V> mGetObject(final byte[][] keys, final Class<V> clazz){
		return listBinaryCall(mGet(keys), clazz);
	}

	@Override
	public <V> List<V> mGetObject(final String[] keys, final TypeReference<V> type){
		return listStringCall(mGet(keys), type);
	}

	@Override
	public <V> List<V> mGetObject(final byte[][] keys, final TypeReference<V> type){
		return listBinaryCall(mGet(keys), type);
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

		if(isPipeline() || isTransaction()){
			index.set(-1);
			txResults.remove();
		}
	}

	@Override
	public List<Object> exec(){
		List<Object> result = isPipeline() ? getConnection().getPipeline().syncAndReturnAll() : super.exec();

		result = Validate.isEmpty(result) ? result : deserializeMixedResults(result);

		if(isPipeline() || isTransaction()){
			index.set(-1);
			txResults.remove();
		}

		return result;
	}

	protected <V> V simpleStringCall(final String result){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.StringDeserialize<>(serializer),
					String.class));
			return null;
		}else{
			return serializer.deserialize(result);
		}
	}

	protected <V> V simpleBinaryCall(final byte[] result){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.BinaryDeserialize<>(serializer),
					byte[].class));
			return null;
		}else{
			return serializer.deserializeBytes(result);
		}
	}

	protected <V> V simpleStringCall(final String result, final Class<V> clazz){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.StringDeserialize<>(serializer, clazz),
					String.class));
			return null;
		}else{
			return serializer.deserialize(result, clazz);
		}
	}

	protected <V> V simpleBinaryCall(final byte[] result, final Class<V> clazz){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.BinaryDeserialize<>(serializer, clazz),
					byte[].class));
			return null;
		}else{
			return serializer.deserializeBytes(result, clazz);
		}
	}

	protected <V> V simpleStringCall(final String result, final TypeReference<V> type){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.StringDeserialize<>(serializer, type),
					String.class));
			return null;
		}else{
			return serializer.deserialize(result, type);
		}
	}

	protected <V> V simpleBinaryCall(final byte[] result, final TypeReference<V> type){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.BinaryDeserialize<>(serializer, type),
					byte[].class));
			return null;
		}else{
			return serializer.deserializeBytes(result, type);
		}
	}

	protected <V> Map<String, V> mapStringCall(final Map<String, String> result){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.StringMapDeserialize<>(serializer),
					Map.class));
			return null;
		}else{
			return serializer.deserialize(result);
		}
	}

	protected <V> Map<byte[], V> mapBinaryCall(final Map<byte[], byte[]> result){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.BinaryMapDeserialize<>(serializer),
					Map.class));
			return null;
		}else{
			return serializer.deserializeBytes(result);
		}
	}

	protected <V> Map<String, V> mapStringCall(final Map<String, String> result, final Class<V> clazz){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.StringMapDeserialize<>(serializer, clazz),
					Map.class));
			return null;
		}else{
			return serializer.deserialize(result, clazz);
		}
	}

	protected <V> Map<byte[], V> mapBinaryCall(final Map<byte[], byte[]> result, final Class<V> clazz){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.BinaryMapDeserialize<>(serializer, clazz),
					Map.class));
			return null;
		}else{
			return serializer.deserializeBytes(result, clazz);
		}
	}

	protected <V> Map<String, V> mapStringCall(final Map<String, String> result, final TypeReference<V> type){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.StringMapDeserialize<>(serializer, type),
					Map.class));
			return null;
		}else{
			return serializer.deserialize(result, type);
		}
	}

	protected <V> Map<byte[], V> mapBinaryCall(final Map<byte[], byte[]> result, final TypeReference<V> type){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.BinaryMapDeserialize<>(serializer, type),
					Map.class));
			return null;
		}else{
			return serializer.deserializeBytes(result, type);
		}
	}

	protected <V> List<V> listStringCall(final List<String> result){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.StringListDeserialize<>(serializer),
					List.class));
			return null;
		}else{
			return serializer.deserialize(result);
		}
	}

	protected <V> List<V> listBinaryCall(final List<byte[]> result){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.BinaryListDeserialize<>(serializer),
					List.class));
			return null;
		}else{
			return serializer.deserializeBytes(result);
		}
	}

	protected <V> List<V> listStringCall(final List<String> result, final Class<V> clazz){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.StringListDeserialize<>(serializer, clazz),
					List.class));
			return null;
		}else{
			return serializer.deserialize(result, clazz);
		}
	}

	protected <V> List<V> listBinaryCall(final List<byte[]> result, final Class<V> clazz){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.StringListDeserialize<>(serializer, clazz),
					List.class));
			return null;
		}else{
			return serializer.deserializeBytes(result, clazz);
		}
	}

	protected <V> List<V> listStringCall(final List<String> result, final TypeReference<V> type){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get() - 1, new TxResult<>(new Converters.StringListDeserialize<>(serializer,
					type), List.class));
			return null;
		}else{
			return serializer.deserialize(result, type);
		}
	}

	protected <V> List<V> listBinaryCall(final List<byte[]> result, final TypeReference<V> type){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.BinaryMapDeserialize<>(serializer, type),
					List.class));
			return null;
		}else{
			return serializer.deserializeBytes(result, type);
		}
	}

	protected <V> Set<V> setStringCall(final Set<String> result){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.StringSetDeserialize<>(serializer),
					Set.class));
			return null;
		}else{
			return serializer.deserialize(result);
		}
	}

	protected <V> Set<V> setBinaryCall(final Set<byte[]> result){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.BinarySetDeserialize<>(serializer),
					Set.class));
			return null;
		}else{
			return serializer.deserializeBytes(result);
		}
	}

	protected <V> Set<V> setStringCall(final Set<String> result, final Class<V> clazz){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.StringSetDeserialize<>(serializer, clazz),
					Set.class));
			return null;
		}else{
			return serializer.deserialize(result, clazz);
		}
	}

	protected <V> Set<V> setBinaryCall(final Set<byte[]> result, final Class<V> clazz){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.StringSetDeserialize<>(serializer, clazz),
					Set.class));
			return null;
		}else{
			return serializer.deserializeBytes(result, clazz);
		}
	}

	protected <V> Set<V> setStringCall(final Set<String> result, final TypeReference<V> type){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.StringSetDeserialize<>(serializer, type),
					Set.class));
			return null;
		}else{
			return serializer.deserialize(result, type);
		}
	}

	protected <V> Set<V> setBinaryCall(final Set<byte[]> result, final TypeReference<V> type){
		if(isPipeline() || isTransaction()){
			getTxResults().put(index.get(), new TxResult<>(new Converters.BinaryMapDeserialize<>(serializer, type),
					Set.class));
			return null;
		}else{
			return serializer.deserializeBytes(result, type);
		}
	}

	protected Map<Integer, TxResult> getTxResults(){
		Map<Integer, TxResult> txResult = txResults.get();

		if(txResult == null){
			txResult = new LinkedHashMap<>(16, 0.8F);
			txResults.set(txResult);
		}

		return txResult;
	}

	protected List<Object> deserializeMixedResults(List<Object> result){
		Map<Integer, TxResult> cache = txResults.get();

		if(cache == null){
			return result;
		}

		TxResult<?, ?> txResult;

		for(int i = 0; i < index.get(); i++){
			txResult = cache.get(i);
			if(txResult == null){
				continue;
			}

			Method method = ReflectUtils.findMethod(txResult.getConverter().getClass(), "convert",
					txResult.getParamTypes());

			if(method != null){
				Object value = result.get(i);
				Object ret = ReflectUtils.invokeMethod(method, txResult.getConverter(), value);

				result.set(i, ret);
			}
		}

		return result;
	}

}
