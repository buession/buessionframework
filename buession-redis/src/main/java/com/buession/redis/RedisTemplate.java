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
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.operations.*;
import redis.clients.jedis.ListPosition;

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
		return deserialize(hGet(key, field));
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field){
		return deserializeBytes(hGet(key, field));
	}

	@Override
	public <V> V hGetObject(final String key, final String field, final Class<V> clazz){
		return deserialize(hGet(key, field), clazz);
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field, final Class<V> clazz){
		return deserializeBytes(hGet(key, field), clazz);
	}

	@Override
	public <V> V hGetObject(final String key, final String field, final TypeReference<V> type){
		return deserialize(hGet(key, field), type);
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field, final TypeReference<V> type){
		return deserializeBytes(hGet(key, field), type);
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key){
		return deserialize(hGetAll(key));
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key){
		return deserializeBytes(hGetAll(key));
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key, final Class<V> clazz){
		return deserialize(hGetAll(key), clazz);
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key, final Class<V> clazz){
		return deserializeBytes(hGetAll(key), clazz);
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key, final TypeReference<V> type){
		return deserialize(hGetAll(key), type);
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key, final TypeReference<V> type){
		return deserializeBytes(hGetAll(key), type);
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String... fields){
		return deserialize(hMGet(key, fields));
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[]... fields){
		return deserializeBytes(hMGet(key, fields));
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String[] fields, final Class<V> clazz){
		return deserialize(hMGet(key, fields), clazz);
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final Class<V> clazz){
		return deserializeBytes(hMGet(key, fields), clazz);
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String[] fields, final TypeReference<V> type){
		return deserialize(hMGet(key, fields), type);
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final TypeReference<V> type){
		return deserializeBytes(hMGet(key, fields), type);
	}

	@Override
	public <V> Status hMSet(final String key, final List<KeyValue<String, V>> data){
		Map<String, String> temp = new LinkedHashMap<>(data.size());

		for(KeyValue<String, V> kv : data){
			temp.put(kv.getKey(), serialize(kv.getValue()));
		}

		return hMSet(key, temp);
	}

	@Override
	public <V> Status hMSet(final byte[] key, final List<KeyValue<byte[], V>> data){
		Map<byte[], byte[]> temp = new LinkedHashMap<>(data.size());

		for(KeyValue<byte[], V> kv : data){
			temp.put(kv.getKey(), serializeAsBytes(kv.getValue()));
		}

		return hMSet(key, temp);
	}

	@Override
	public <V> Status hSet(final String key, final String field, final V value){
		return hSet(key, field, serialize(value));
	}

	@Override
	public <V> Status hSet(final byte[] key, final byte[] field, final V value){
		return hSet(key, field, serializeAsBytes(value));
	}

	@Override
	public <V> Status hSetNx(final String key, final String field, final V value){
		return hSetNx(key, field, serialize(value));
	}

	@Override
	public <V> Status hSetNx(final byte[] key, final byte[] field, final V value){
		return hSetNx(key, field, serializeAsBytes(value));
	}

	@Override
	public <V> List<V> hValsObject(final String key){
		return deserialize(hVals(key));
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key){
		return deserializeBytes(hVals(key));
	}

	@Override
	public <V> List<V> hValsObject(final String key, final Class<V> clazz){
		return deserialize(hVals(key), clazz);
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key, final Class<V> clazz){
		return deserializeBytes(hVals(key), clazz);
	}

	@Override
	public <V> List<V> hValsObject(final String key, final TypeReference<V> type){
		return deserialize(hVals(key), type);
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key, final TypeReference<V> type){
		return deserializeBytes(hVals(key), type);
	}

	@Override
	public <V> List<V> blPopObject(final String key, final int timeout){
		return deserialize(blPop(key, timeout));
	}

	@Override
	public <V> List<V> blPopObject(final byte[] key, final int timeout){
		return deserializeBytes(blPop(key, timeout));
	}

	@Override
	public <V> List<V> blPopObject(final String key, final int timeout, final Class<V> clazz){
		return deserialize(blPop(key, timeout), clazz);
	}

	@Override
	public <V> List<V> blPopObject(final byte[] key, final int timeout, final Class<V> clazz){
		return deserializeBytes(blPop(key, timeout), clazz);
	}

	@Override
	public <V> List<V> blPopObject(final String key, final int timeout, final TypeReference<V> type){
		return deserialize(blPop(key, timeout), type);
	}

	@Override
	public <V> List<V> blPopObject(final byte[] key, final int timeout, final TypeReference<V> type){
		return deserializeBytes(blPop(key, timeout), type);
	}

	@Override
	public <V> List<V> brPopObject(final String key, final int timeout){
		return deserialize(brPop(key, timeout));
	}

	@Override
	public <V> List<V> brPopObject(final byte[] key, final int timeout){
		return deserializeBytes(brPop(key, timeout));
	}

	@Override
	public <V> List<V> brPopObject(final String key, final int timeout, final Class<V> clazz){
		return deserialize(brPop(key, timeout), clazz);
	}

	@Override
	public <V> List<V> brPopObject(final byte[] key, final int timeout, final Class<V> clazz){
		return deserializeBytes(brPop(key, timeout), clazz);
	}

	@Override
	public <V> List<V> brPopObject(final String key, final int timeout, final TypeReference<V> type){
		return deserialize(brPop(key, timeout), type);
	}

	@Override
	public <V> List<V> brPopObject(final byte[] key, final int timeout, final TypeReference<V> type){
		return deserializeBytes(brPop(key, timeout), type);
	}

	@Override
	public <V> V brPoplPushObject(final String key, final String destKey, final int timeout){
		return deserialize(brPoplPush(key, destKey, timeout));
	}

	@Override
	public <V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout){
		return deserializeBytes(brPoplPush(key, destKey, timeout));
	}

	@Override
	public <V> V brPoplPushObject(final String key, final String destKey, final int timeout, final Class<V> clazz){
		return deserialize(brPoplPush(key, destKey, timeout), clazz);
	}

	@Override
	public <V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout, final Class<V> clazz){
		return deserializeBytes(brPoplPush(key, destKey, timeout), clazz);
	}

	@Override
	public <V> V brPoplPushObject(final String key, final String destKey, final int timeout,
			final TypeReference<V> type){
		return deserialize(brPoplPush(key, destKey, timeout), type);
	}

	@Override
	public <V> V brPoplPushObject(final byte[] key, final byte[] destKey, final int timeout,
			final TypeReference<V> type){
		return deserializeBytes(brPoplPush(key, destKey, timeout), type);
	}

	@Override
	public <V> V lIndexObject(final String key, final int index){
		return deserialize(lIndex(key, index));
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final int index){
		return deserializeBytes(lIndex(key, index));
	}

	@Override
	public <V> V lIndexObject(final String key, final int index, final Class<V> clazz){
		return deserialize(lIndex(key, index), clazz);
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final int index, final Class<V> clazz){
		return deserializeBytes(lIndex(key, index), clazz);
	}

	@Override
	public <V> V lIndexObject(final String key, final int index, final TypeReference<V> type){
		return deserialize(lIndex(key, index), type);
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final int index, final TypeReference<V> type){
		return deserializeBytes(lIndex(key, index), type);
	}

	@Override
	public <V> V lIndexObject(final String key, final long index){
		return deserialize(lIndex(key, index));
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index){
		return deserializeBytes(lIndex(key, index));
	}

	@Override
	public <V> V lIndexObject(final String key, final long index, final Class<V> clazz){
		return deserialize(lIndex(key, index), clazz);
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index, final Class<V> clazz){
		return deserializeBytes(lIndex(key, index), clazz);
	}

	@Override
	public <V> V lIndexObject(final String key, final long index, final TypeReference<V> type){
		return deserialize(lIndex(key, index), type);
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index, final TypeReference<V> type){
		return deserializeBytes(lIndex(key, index), type);
	}

	@Override
	public <V> Long lInsert(final String key, final V value, final ListPosition position, final V pivot){
		return lInsert(key, serialize(value), position, serialize(pivot));
	}

	@Override
	public <V> Long lInsert(final byte[] key, final V value, final ListPosition position, final V pivot){
		return lInsert(key, serializeAsBytes(value), position, serializeAsBytes(pivot));
	}

	@Override
	public <V> V lPopObject(final String key){
		return deserialize(lPop(key));
	}

	@Override
	public <V> V lPopObject(final byte[] key){
		return deserializeBytes(lPop(key));
	}

	@Override
	public <V> V lPopObject(final String key, final Class<V> clazz){
		return deserialize(lPop(key), clazz);
	}

	@Override
	public <V> V lPopObject(final byte[] key, final Class<V> clazz){
		return deserializeBytes(lPop(key), clazz);
	}

	@Override
	public <V> V lPopObject(final String key, final TypeReference<V> type){
		return deserialize(lPop(key), type);
	}

	@Override
	public <V> V lPopObject(final byte[] key, final TypeReference<V> type){
		return deserializeBytes(lPop(key), type);
	}

	@Override
	public <V> Long lPush(final String key, final V value){
		return lPush(key, serialize(value));
	}

	@Override
	public <V> Long lPush(final byte[] key, final V value){
		return lPush(key, serializeAsBytes(value));
	}

	@Override
	public <V> Long lPush(final String key, final V... values){
		return lPush(key, serialize(values));
	}

	@Override
	public <V> Long lPush(final byte[] key, final V... values){
		return lPush(key, serializeAsBytes(values));
	}

	@Override
	public <V> Long lPushX(final String key, final V value){
		return lPushX(key, serialize(value));
	}

	@Override
	public <V> Long lPushX(final byte[] key, final V value){
		return lPushX(key, serializeAsBytes(value));
	}

	@Override
	public <V> Long lPushX(final String key, final V... values){
		return lPushX(key, serialize(values));
	}

	@Override
	public <V> Long lPushX(final byte[] key, final V... values){
		return lPushX(key, serializeAsBytes(values));
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final int start, final int end){
		return deserialize(lRange(key, start, end));
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final int start, final int end){
		return deserializeBytes(lRange(key, start, end));
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final long start, final long end){
		return deserialize(lRange(key, start, end));
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end){
		return deserializeBytes(lRange(key, start, end));
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final int start, final int end, final Class<V> clazz){
		return deserialize(lRange(key, start, end), clazz);
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final int start, final int end, final Class<V> clazz){
		return deserializeBytes(lRange(key, start, end), clazz);
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final long start, final long end, final Class<V> clazz){
		return deserialize(lRange(key, start, end), clazz);
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end, final Class<V> clazz){
		return deserializeBytes(lRange(key, start, end), clazz);
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final int start, final int end, final TypeReference<V> type){
		return deserialize(lRange(key, start, end), type);
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final int start, final int end, final TypeReference<V> type){
		return deserializeBytes(lRange(key, start, end), type);
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final long start, final long end, final TypeReference<V> type){
		return deserialize(lRange(key, start, end), type);
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end, final TypeReference<V> type){
		return deserializeBytes(lRange(key, start, end), type);
	}

	@Override
	public <V> Status lSet(final String key, final int index, final V value){
		return lSet(key, index, serialize(value));
	}

	@Override
	public <V> Status lSet(final byte[] key, final int index, final V value){
		return lSet(key, index, serializeAsBytes(value));
	}

	@Override
	public <V> Status lSet(final String key, final long index, final V value){
		return lSet(key, index, serialize(value));
	}

	@Override
	public <V> Status lSet(final byte[] key, final long index, final V value){
		return lSet(key, index, serializeAsBytes(value));
	}

	@Override
	public <V> V rPopObject(final String key){
		return deserialize(rPop(key));
	}

	@Override
	public <V> V rPopObject(final byte[] key){
		return deserializeBytes(rPop(key));
	}

	@Override
	public <V> V rPopObject(final String key, final Class<V> clazz){
		return deserialize(rPop(key), clazz);
	}

	@Override
	public <V> V rPopObject(final byte[] key, final Class<V> clazz){
		return deserializeBytes(rPop(key), clazz);
	}

	@Override
	public <V> V rPopObject(final String key, final TypeReference<V> type){
		return deserialize(rPop(key), type);
	}

	@Override
	public <V> V rPopObject(final byte[] key, final TypeReference<V> type){
		return deserializeBytes(rPop(key), type);
	}

	@Override
	public <V> V rPoplPushObject(final String key, final String destKey){
		return deserialize(rPoplPush(key, destKey));
	}

	@Override
	public <V> V rPoplPushObject(final byte[] key, final byte[] destKey){
		return deserializeBytes(rPoplPush(key, destKey));
	}

	@Override
	public <V> V rPoplPushObject(final String key, final String destKey, final Class<V> clazz){
		return deserialize(rPoplPush(key, destKey), clazz);
	}

	@Override
	public <V> V rPoplPushObject(final byte[] key, final byte[] destKey, final Class<V> clazz){
		return deserializeBytes(rPoplPush(key, destKey), clazz);
	}

	@Override
	public <V> V rPoplPushObject(final String key, final String destKey, final TypeReference<V> type){
		return deserialize(rPoplPush(key, destKey), type);
	}

	@Override
	public <V> V rPoplPushObject(final byte[] key, final byte[] destKey, final TypeReference<V> type){
		return deserializeBytes(rPoplPush(key, destKey), type);
	}

	@Override
	public <V> Long rPush(final String key, final V value){
		return rPush(key, serialize(value));
	}

	@Override
	public <V> Long rPush(final byte[] key, final V value){
		return rPush(key, serializeAsBytes(value));
	}

	@Override
	public <V> Long rPush(final String key, final V... values){
		return rPush(key, serialize(values));
	}

	@Override
	public <V> Long rPush(final byte[] key, final V... values){
		return rPush(key, serializeAsBytes(values));
	}

	@Override
	public <V> Long rPushX(final String key, final V value){
		return rPushX(key, serialize(value));
	}

	@Override
	public <V> Long rPushX(final byte[] key, final V value){
		return rPushX(key, serializeAsBytes(value));
	}

	@Override
	public <V> Long rPushX(final String key, final V... values){
		return rPushX(key, serialize(values));
	}

	@Override
	public <V> Long rPushX(final byte[] key, final V... values){
		return rPushX(key, serializeAsBytes(values));
	}

	@Override
	public <V> Long sAdd(final String key, final V member){
		return sAdd(key, serialize(member));
	}

	@Override
	public <V> Long sAdd(final byte[] key, final V member){
		return sAdd(key, serializeAsBytes(member));
	}

	@Override
	public <V> Long sAdd(final String key, final V... members){
		return sAdd(key, serialize(members));
	}

	@Override
	public <V> Long sAdd(final byte[] key, final V... members){
		return sAdd(key, serializeAsBytes(members));
	}

	@Override
	public <V> Set<V> sDiffObject(final String key){
		return deserialize(sDiff(key));
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[] key){
		return deserializeBytes(sDiff(key));
	}

	@Override
	public <V> Set<V> sDiffObject(final String key, final Class<V> clazz){
		return deserialize(sDiff(key), clazz);
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[] key, final Class<V> clazz){
		return deserializeBytes(sDiff(key), clazz);
	}

	@Override
	public <V> Set<V> sDiffObject(final String key, final TypeReference<V> type){
		return deserialize(sDiff(key), type);
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[] key, final TypeReference<V> type){
		return deserializeBytes(sDiff(key), type);
	}

	@Override
	public <V> Set<V> sMembersObject(final String key){
		return deserialize(sMembers(key));
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key){
		return deserializeBytes(sMembers(key));
	}

	@Override
	public <V> Set<V> sMembersObject(final String key, final Class<V> clazz){
		return deserialize(sMembers(key), clazz);
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key, final Class<V> clazz){
		return deserializeBytes(sMembers(key), clazz);
	}

	@Override
	public <V> Set<V> sMembersObject(final String key, final TypeReference<V> type){
		return deserialize(sMembers(key), type);
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key, final TypeReference<V> type){
		return deserializeBytes(sMembers(key), type);
	}

	@Override
	public <V> V sPopObject(final String key){
		return deserialize(sPop(key));
	}

	@Override
	public <V> V sPopObject(final byte[] key){
		return deserializeBytes(sPop(key));
	}

	@Override
	public <V> V sPopObject(final String key, final Class<V> clazz){
		return deserialize(sPop(key), clazz);
	}

	@Override
	public <V> V sPopObject(final byte[] key, final Class<V> clazz){
		return deserializeBytes(sPop(key), clazz);
	}

	@Override
	public <V> V sPopObject(final String key, final TypeReference<V> type){
		return deserialize(sPop(key), type);
	}

	@Override
	public <V> V sPopObject(final byte[] key, final TypeReference<V> type){
		return deserializeBytes(sPop(key), type);
	}

	@Override
	public <V> V sRandMemberObject(final String key){
		return deserialize(sRandMember(key));
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key){
		return deserializeBytes(sRandMember(key));
	}

	@Override
	public <V> V sRandMemberObject(final String key, final Class<V> clazz){
		return deserialize(sRandMember(key), clazz);
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key, final Class<V> clazz){
		return deserializeBytes(sRandMember(key), clazz);
	}

	@Override
	public <V> V sRandMemberObject(final String key, final TypeReference<V> type){
		return deserialize(sRandMember(key), type);
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key, final TypeReference<V> type){
		return deserializeBytes(sRandMember(key), type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final int count){
		return deserialize(sRandMember(key, count));
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final int count){
		return deserializeBytes(sRandMember(key, count));
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count){
		return deserialize(sRandMember(key, count));
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count){
		return deserializeBytes(sRandMember(key, count));
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final int count, final Class<V> clazz){
		return deserialize(sRandMember(key, count), clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final int count, final Class<V> clazz){
		return deserializeBytes(sRandMember(key, count), clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count, final Class<V> clazz){
		return deserialize(sRandMember(key, count), clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count, final Class<V> clazz){
		return deserializeBytes(sRandMember(key, count), clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final int count, final TypeReference<V> type){
		return deserialize(sRandMember(key, count), type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final int count, final TypeReference<V> type){
		return deserializeBytes(sRandMember(key, count), type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count, final TypeReference<V> type){
		return deserialize(sRandMember(key, count), type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count, final TypeReference<V> type){
		return deserializeBytes(sRandMember(key, count), type);
	}

	@Override
	public <V> Long sRem(final String key, final V member){
		return sRem(key, serialize(member));
	}

	@Override
	public <V> Long sRem(final byte[] key, final V member){
		return sRem(key, serializeAsBytes(member));
	}

	@Override
	public <V> Long sRem(final String key, final V... members){
		return sRem(key, serialize(members));
	}

	@Override
	public <V> Long sRem(final byte[] key, final V... members){
		return sRem(key, serializeAsBytes(members));
	}

	@Override
	public <V> V getObject(final String key){
		return deserialize(get(key));
	}

	@Override
	public <V> V getObject(final byte[] key){
		return deserializeBytes(get(key));
	}

	@Override
	public <V> V getObject(final String key, final Class<V> clazz){
		return deserialize(get(key), clazz);
	}

	@Override
	public <V> V getObject(final byte[] key, final Class<V> clazz){
		return deserializeBytes(get(key), clazz);
	}

	@Override
	public <V> V getObject(final String key, final TypeReference<V> type){
		return deserialize(get(key), type);
	}

	@Override
	public <V> V getObject(final byte[] key, final TypeReference<V> type){
		return deserializeBytes(get(key), type);
	}

	@Override
	public <V> V getSet(final String key, final V value){
		return deserialize(getSet(key, serialize(value)));
	}

	@Override
	public <V> V getSet(final byte[] key, final V value){
		return deserializeBytes(getSet(key, serializeAsBytes(value)));
	}

	@Override
	public <V> V getSet(final String key, final V value, final Class<V> clazz){
		return deserialize(getSet(key, serialize(value)), clazz);
	}

	@Override
	public <V> V getSet(final byte[] key, final V value, final Class<V> clazz){
		return deserializeBytes(getSet(key, serializeAsBytes(value)), clazz);
	}

	@Override
	public <V> V getSet(final String key, final V value, final TypeReference<V> type){
		return deserialize(getSet(key, serialize(value)), type);
	}

	@Override
	public <V> V getSet(final byte[] key, final V value, final TypeReference<V> type){
		return deserializeBytes(getSet(key, serializeAsBytes(value)), type);
	}

	@Override
	public <V> List<V> mGetObject(final String... keys){
		return deserialize(mGet(keys));
	}

	@Override
	public <V> List<V> mGetObject(final byte[]... keys){
		return deserializeBytes(mGet(keys));
	}

	@Override
	public <V> List<V> mGetObject(final String[] keys, final Class<V> clazz){
		return deserialize(mGet(keys), clazz);
	}

	@Override
	public <V> List<V> mGetObject(final byte[][] keys, final Class<V> clazz){
		return deserializeBytes(mGet(keys), clazz);
	}

	@Override
	public <V> List<V> mGetObject(final String[] keys, final TypeReference<V> type){
		return deserialize(mGet(keys), type);
	}

	@Override
	public <V> List<V> mGetObject(final byte[][] keys, final TypeReference<V> type){
		return deserializeBytes(mGet(keys), type);
	}

	@Override
	public <V> Status pSetEx(final String key, final V value, final int lifetime){
		return pSetEx(key, serialize(value), lifetime);
	}

	@Override
	public <V> Status pSetEx(final byte[] key, final V value, final int lifetime){
		return pSetEx(key, serializeAsBytes(value), lifetime);
	}

	@Override
	public <V> Status set(final String key, final V value){
		return set(key, serialize(value));
	}

	@Override
	public <V> Status set(final byte[] key, final V value){
		return set(key, serializeAsBytes(value));
	}

	@Override
	public <V> Status set(final String key, final V value, final SetArgument setArgument){
		return set(key, serialize(value), setArgument);
	}

	@Override
	public <V> Status set(final byte[] key, final V value, final SetArgument setArgument){
		return set(key, serializeAsBytes(value), setArgument);
	}

	@Override
	public <V> Status setEx(final String key, final V value, final int lifetime){
		return setEx(key, serialize(value), lifetime);
	}

	@Override
	public <V> Status setEx(final byte[] key, final V value, final int lifetime){
		return setEx(key, serializeAsBytes(value), lifetime);
	}

	@Override
	public <V> Status setNx(final String key, final V value){
		return setNx(key, serialize(value));
	}

	@Override
	public <V> Status setNx(final byte[] key, final V value){
		return setNx(key, serializeAsBytes(value));
	}

}
