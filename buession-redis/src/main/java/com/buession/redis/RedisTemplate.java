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
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;

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
		HyperLogLogOperations, KeyOperations, StringOperations, ListOperations, SetOperations, SortedSetOperations,
		TransactionOperations, PubSubOperations, ScriptOperations, ServerOperations, DebugOperations {

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
		return deserialize(hGet(key, field));
	}

	@Override
	public <V> V hGetObject(final String key, final String field, final Class<V> clazz){
		return deserialize(hGet(key, field), clazz);
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field, final Class<V> clazz){
		return deserialize(hGet(key, field), clazz);
	}

	@Override
	public <V> V hGetObject(final String key, final String field, final TypeReference<V> type){
		return deserialize(hGet(key, field), type);
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field, final TypeReference<V> type){
		return deserialize(hGet(key, field), type);
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key){
		return ReturnUtils.objectFromMapString(serializer, hGetAll(key));
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key){
		return ReturnUtils.objectFromMapByte(serializer, hGetAll(key));
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key, final Class<V> clazz){
		return ReturnUtils.objectFromMapString(serializer, hGetAll(key), clazz);
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key, final Class<V> clazz){
		return ReturnUtils.objectFromMapByte(serializer, hGetAll(key), clazz);
	}

	@Override
	public <V> Map<String, V> hGetAllObject(final String key, final TypeReference<V> type){
		return ReturnUtils.objectFromMapString(serializer, hGetAll(key), type);
	}

	@Override
	public <V> Map<byte[], V> hGetAllObject(final byte[] key, final TypeReference<V> type){
		return ReturnUtils.objectFromMapByte(serializer, hGetAll(key), type);
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String... fields){
		return ReturnUtils.objectFromListString(serializer, hMGet(key, fields));
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[]... fields){
		return ReturnUtils.objectFromListByte(serializer, hMGet(key, fields));
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String[] fields, final Class<V> clazz){
		return ReturnUtils.objectFromListString(serializer, hMGet(key, fields), clazz);
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final Class<V> clazz){
		return ReturnUtils.objectFromListByte(serializer, hMGet(key, fields), clazz);
	}

	@Override
	public <V> List<V> hMGetObject(final String key, final String[] fields, final TypeReference<V> type){
		return ReturnUtils.objectFromListString(serializer, hMGet(key, fields), type);
	}

	@Override
	public <V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final TypeReference<V> type){
		return ReturnUtils.objectFromListByte(serializer, hMGet(key, fields), type);
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
		return ReturnUtils.objectFromListString(serializer, hVals(key));
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key){
		return ReturnUtils.objectFromListByte(serializer, hVals(key));
	}

	@Override
	public <V> List<V> hValsObject(final String key, final Class<V> clazz){
		return ReturnUtils.objectFromListString(serializer, hVals(key), clazz);
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key, final Class<V> clazz){
		return ReturnUtils.objectFromListByte(serializer, hVals(key), clazz);
	}

	@Override
	public <V> List<V> hValsObject(final String key, final TypeReference<V> type){
		return ReturnUtils.objectFromListString(serializer, hVals(key), type);
	}

	@Override
	public <V> List<V> hValsObject(final byte[] key, final TypeReference<V> type){
		return ReturnUtils.objectFromListByte(serializer, hVals(key), type);
	}

	protected <V> String serialize(final V object){
		return serializer.serialize(object);
	}

	protected <V> byte[] serializeAsBytes(final V object){
		return serializer.serializeAsBytes(object);
	}

	protected <V> V deserialize(final String str){
		return serializer.deserialize(str);
	}

	protected <V> V deserialize(final byte[] str){
		return serializer.deserialize(str);
	}

	protected <V> V deserialize(final String str, final Class<V> clazz){
		return serializer.deserialize(str, clazz);
	}

	protected <V> V deserialize(final byte[] str, final Class<V> clazz){
		return serializer.deserialize(str, clazz);
	}

	protected <V> V deserialize(final String str, final TypeReference<V> type){
		return serializer.deserialize(str, type);
	}

	protected <V> V deserialize(final byte[] str, final TypeReference<V> type){
		return serializer.deserialize(str, type);
	}

}
