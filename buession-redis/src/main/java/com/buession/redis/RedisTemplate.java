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
import com.buession.core.utils.Assert;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.operations.HashOperations;
import com.buession.redis.core.operations.KeyOperations;
import com.buession.redis.core.operations.ListOperations;
import com.buession.redis.core.operations.SetOperations;
import com.buession.redis.core.operations.SortedSetOperations;
import com.buession.redis.core.operations.StringOperations;
import com.buession.redis.utils.ReturnUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis 基本操作封装扩展，可序列化对象和反序列化为对象
 *
 * @author Yong.Teng
 */
public class RedisTemplate extends BaseRedisTemplate implements KeyOperations, StringOperations, HashOperations,
		ListOperations, SetOperations, SortedSetOperations {

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
	public Status expireAt(final String key, final Date date){
		Assert.isNull(date, "Expire date could not be null");
		return expireAt(key, date.getTime() / 1000L);
	}

	@Override
	public Status expireAt(final byte[] key, final Date date){
		Assert.isNull(date, "Expire date could not be null");
		return expireAt(key, date.getTime() / 1000L);
	}

	@Override
	public Status pExpireAt(final String key, final Date date){
		Assert.isNull(date, "Expire date could not be null");
		return pExpireAt(key, date.getTime());
	}

	@Override
	public Status pExpireAt(final byte[] key, final Date date){
		Assert.isNull(date, "Expire date could not be null");
		return pExpireAt(key, date.getTime());
	}

	@Override
	public Date ttlAt(final String key){
		return new Date(System.currentTimeMillis() + ttl(key) * 1000L);
	}

	@Override
	public Date ttlAt(final byte[] key){
		return new Date(System.currentTimeMillis() + ttl(key) * 1000L);
	}

	@Override
	public Date pTtlAt(final String key){
		return new Date(System.currentTimeMillis() + pTtl(key));
	}

	@Override
	public Date pTtlAt(final byte[] key){
		return new Date(System.currentTimeMillis() + pTtl(key));
	}

	@Override
	public Status restore(final String key, final String serializedValue, final Date ttl){
		Assert.isNull(ttl, "Ttl date could not be null");
		return restore(key, serializedValue, ttl);
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final Date ttl){
		Assert.isNull(ttl, "Ttl date could not be null");
		return restore(key, serializedValue, ttl);
	}

	@Override
	public Status migrate(final String key, final String host, final int db, final int timeout){
		return migrate(key, host, RedisNode.DEFAULT_PORT, db, timeout);
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int db, final int timeout){
		return migrate(key, host, RedisNode.DEFAULT_PORT, db, timeout);
	}

	@Override
	public Status migrate(final String key, final String host, final int db, final int timeout,
			final MigrateOperation migrateOperation){
		return migrate(key, host, RedisNode.DEFAULT_PORT, db, timeout, migrateOperation);
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int db, final int timeout,
			final MigrateOperation migrateOperation){
		return migrate(key, host, RedisNode.DEFAULT_PORT, db, timeout, migrateOperation);
	}

	@Override
	public Status del(final String key){
		return ReturnUtils.statusForBool(del(new String[]{key}) > 0);
	}

	@Override
	public Status del(final byte[] key){
		return ReturnUtils.statusForBool(del(new byte[][]{key}) > 0);
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
	public <V> Status pSetEx(final String key, final V value, final int lifetime){
		return pSetEx(key, serializer.serialize(value), lifetime);
	}

	@Override
	public <V> Status pSetEx(final byte[] key, final V value, final int lifetime){
		return pSetEx(key, serializer.serializeAsBytes(value), lifetime);
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
	public <V> V getObject(final String key){
		return serializer.deserialize(get(key));
	}

	@Override
	public <V> V getObject(final byte[] key){
		return serializer.deserialize(get(key));
	}

	@Override
	public <V> V getObject(final String key, final Class<V> clazz){
		return serializer.deserialize(get(key), clazz);
	}

	@Override
	public <V> V getObject(final byte[] key, final Class<V> clazz){
		return serializer.deserialize(get(key), clazz);
	}

	@Override
	public <V> V getObject(final String key, final TypeReference<V> type){
		return serializer.deserialize(get(key), type);
	}

	@Override
	public <V> V getObject(final byte[] key, final TypeReference<V> type){
		return serializer.deserialize(get(key), type);
	}

	@Override
	public <V> V getSet(final String key, final V value){
		return serializer.deserialize(getSet(key, serializer.serialize(value)));
	}

	@Override
	public <V> V getSet(final byte[] key, final V value){
		return serializer.deserialize(getSet(key, serializer.serialize(value)));
	}

	@Override
	public <V> V getSet(final String key, final V value, final Class<V> clazz){
		return serializer.deserialize(getSet(key, serializer.serialize(value)), clazz);
	}

	@Override
	public <V> V getSet(final byte[] key, final V value, final Class<V> clazz){
		return serializer.deserialize(getSet(key, serializer.serialize(value)), clazz);
	}

	@Override
	public <V> V getSet(final String key, final V value, final TypeReference<V> type){
		return serializer.deserialize(getSet(key, serializer.serialize(value)), type);
	}

	@Override
	public <V> V getSet(final byte[] key, final V value, final TypeReference<V> type){
		return serializer.deserialize(getSet(key, serializer.serialize(value)), type);
	}

	@Override
	public Status mSet(final List<KeyValue<String, String>> values){
		if(values == null){
			return Status.FAILURE;
		}else{
			Map<String, String> data = new LinkedHashMap<>(values.size());

			for(KeyValue<String, String> v : values){
				data.put(v.getKey(), v.getValue());
			}

			return mSet(data);
		}
	}

	@Override
	public Status mSetNx(final List<KeyValue<String, String>> values){
		if(values == null){
			return Status.FAILURE;
		}else{
			Map<String, String> data = new LinkedHashMap<>(values.size());

			for(KeyValue<String, String> v : values){
				data.put(v.getKey(), v.getValue());
			}

			return mSetNx(data);
		}
	}

	@Override
	public <V> List<V> mGetObject(final String... keys){
		return ReturnUtils.objectFromListString(serializer, mGet(keys));
	}

	@Override
	public <V> List<V> mGetObject(final byte[]... keys){
		return ReturnUtils.objectFromListByte(serializer, mGet(keys));
	}

	@Override
	public <V> List<V> mGetObject(final String[] keys, final Class<V> clazz){
		return ReturnUtils.objectFromListString(serializer, mGet(keys), clazz);
	}

	@Override
	public <V> List<V> mGetObject(final byte[][] keys, final Class<V> clazz){
		return ReturnUtils.objectFromListByte(serializer, mGet(keys), clazz);
	}

	@Override
	public <V> List<V> mGetObject(final String[] keys, final TypeReference<V> type){
		return ReturnUtils.objectFromListString(serializer, mGet(keys), type);
	}

	@Override
	public <V> List<V> mGetObject(final byte[][] keys, final TypeReference<V> type){
		return ReturnUtils.objectFromListByte(serializer, mGet(keys), type);
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
	public <V> V hGetObject(final String key, final String field){
		return serializer.deserialize(hGet(key, field));
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field){
		return serializer.deserialize(hGet(key, field));
	}

	@Override
	public <V> V hGetObject(final String key, final String field, final Class<V> clazz){
		return serializer.deserialize(hGet(key, field), clazz);
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field, final Class<V> clazz){
		return serializer.deserialize(hGet(key, field), clazz);
	}

	@Override
	public <V> V hGetObject(final String key, final String field, final TypeReference<V> type){
		return serializer.deserialize(hGet(key, field), type);
	}

	@Override
	public <V> V hGetObject(final byte[] key, final byte[] field, final TypeReference<V> type){
		return serializer.deserialize(hGet(key, field), type);
	}

	@Override
	public <V> Status hMSet(final String key, final List<KeyValue<String, V>> data){
		if(data == null){
			return client.hMSet(key, null);
		}else{
			Map<String, String> temp = new LinkedHashMap<>(data.size());

			for(KeyValue<String, V> kv : data){
				if(kv.getValue() instanceof CharSequence){
					temp.put(kv.getKey(), kv.getValue().toString());
				}else{
					temp.put(kv.getKey(), serializer.serialize(kv.getValue()));
				}
			}

			return hMSet(key, temp);
		}
	}

	@Override
	public <V> Status hMSet(final byte[] key, final List<KeyValue<byte[], V>> data){
		if(data == null){
			return client.hMSet(key, null);
		}else{
			Map<byte[], byte[]> temp = new LinkedHashMap<>(data.size());

			for(KeyValue<byte[], V> kv : data){
				if(kv.getValue() instanceof CharSequence){
					temp.put(kv.getKey(), kv.getValue().toString().getBytes());
				}else{
					temp.put(kv.getKey(), serializer.serializeAsBytes(kv.getValue()));
				}
			}

			return hMSet(key, temp);
		}
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
	public Status hDel(final String key, final String field){
		return ReturnUtils.statusForBool(hDel(key, new String[]{field}) > 0);
	}

	@Override
	public Status hDel(final byte[] key, final byte[] field){
		return ReturnUtils.statusForBool(hDel(key, new byte[][]{field}) > 0);
	}

	@Override
	public Long lPush(final String key, final String value){
		return lPush(key, new String[]{value});
	}

	@Override
	public Long lPush(final byte[] key, final byte[] value){
		return lPush(key, new byte[][]{value});
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
		return lPush(key, serializer(values));
	}

	@Override
	public <V> Long lPush(final byte[] key, final V... values){
		return lPush(key, serializerAsByte(values));
	}

	@Override
	public Long lPushX(final String key, final String value){
		return lPushX(key, new String[]{value});
	}

	@Override
	public Long lPushX(final byte[] key, final byte[] value){
		return lPushX(key, new byte[][]{value});
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
		return lPushX(key, serializer(values));
	}

	@Override
	public <V> Long lPushX(final byte[] key, final V... values){
		return lPushX(key, serializerAsByte(values));
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
	public <V> V lIndexObject(final String key, final int index){
		return serializer.deserialize(lIndex(key, index));
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final int index){
		return serializer.deserialize(lIndex(key, index));
	}

	@Override
	public <V> V lIndexObject(final String key, final int index, final Class<V> clazz){
		return serializer.deserialize(lIndex(key, index), clazz);
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final int index, final Class<V> clazz){
		return serializer.deserialize(lIndex(key, index), clazz);
	}

	@Override
	public <V> V lIndexObject(final String key, final int index, final TypeReference<V> type){
		return serializer.deserialize(lIndex(key, index), type);
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final int index, final TypeReference<V> type){
		return serializer.deserialize(lIndex(key, index), type);
	}

	@Override
	public <V> V lIndexObject(final String key, final long index){
		return serializer.deserialize(lIndex(key, index));
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index){
		return serializer.deserialize(lIndex(key, index));
	}

	@Override
	public <V> V lIndexObject(final String key, final long index, final Class<V> clazz){
		return serializer.deserialize(lIndex(key, index), clazz);
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index, final Class<V> clazz){
		return serializer.deserialize(lIndex(key, index), clazz);
	}

	@Override
	public <V> V lIndexObject(final String key, final long index, final TypeReference<V> type){
		return serializer.deserialize(lIndex(key, index), type);
	}

	@Override
	public <V> V lIndexObject(final byte[] key, final long index, final TypeReference<V> type){
		return serializer.deserialize(lIndex(key, index), type);
	}

	@Override
	public <V> V lPopObject(final String key){
		return serializer.deserialize(lPop(key));
	}

	@Override
	public <V> V lPopObject(final byte[] key){
		return serializer.deserialize(lPop(key));
	}

	@Override
	public <V> V lPopObject(final String key, final Class<V> clazz){
		return serializer.deserialize(lPop(key), clazz);
	}

	@Override
	public <V> V lPopObject(final byte[] key, final Class<V> clazz){
		return serializer.deserialize(lPop(key), clazz);
	}

	@Override
	public <V> V lPopObject(final String key, final TypeReference<V> type){
		return serializer.deserialize(lPop(key), type);
	}

	@Override
	public <V> V lPopObject(final byte[] key, final TypeReference<V> type){
		return serializer.deserialize(lPop(key), type);
	}

	@Override
	public List<String> blPop(final String key, final int timeout){
		return blPop(new String[]{key}, timeout);
	}

	@Override
	public List<byte[]> blPop(final byte[] key, final int timeout){
		return blPop(new byte[][]{key}, timeout);
	}

	@Override
	public <V> List<V> blPopObject(final String key, final int timeout){
		return ReturnUtils.objectFromListString(serializer, blPop(key, timeout));
	}

	@Override
	public <V> List<V> blPopObject(final byte[] key, final int timeout){
		return ReturnUtils.objectFromListByte(serializer, blPop(key, timeout));
	}

	@Override
	public <V> List<V> blPopObject(final String key, final int timeout, final Class<V> clazz){
		return ReturnUtils.objectFromListString(serializer, blPop(key, timeout), clazz);
	}

	@Override
	public <V> List<V> blPopObject(final byte[] key, final int timeout, final Class<V> clazz){
		return ReturnUtils.objectFromListByte(serializer, blPop(key, timeout), clazz);
	}

	@Override
	public <V> List<V> blPopObject(final String key, final int timeout, final TypeReference<V> type){
		return ReturnUtils.objectFromListString(serializer, blPop(key, timeout), type);
	}

	@Override
	public <V> List<V> blPopObject(final byte[] key, final int timeout, final TypeReference<V> type){
		return ReturnUtils.objectFromListByte(serializer, blPop(key, timeout), type);
	}

	@Override
	public <V> V rPopObject(final String key){
		return serializer.deserialize(rPop(key));
	}

	@Override
	public <V> V rPopObject(final byte[] key){
		return serializer.deserialize(rPop(key));
	}

	@Override
	public <V> V rPopObject(final String key, final Class<V> clazz){
		return serializer.deserialize(rPop(key), clazz);
	}

	@Override
	public <V> V rPopObject(final byte[] key, final Class<V> clazz){
		return serializer.deserialize(rPop(key), clazz);
	}

	@Override
	public <V> V rPopObject(final String key, final TypeReference<V> type){
		return serializer.deserialize(rPop(key), type);
	}

	@Override
	public <V> V rPopObject(final byte[] key, final TypeReference<V> type){
		return serializer.deserialize(rPop(key), type);
	}

	@Override
	public <V> V rPoplPushObject(final String source, final String destKey){
		return serializer.deserialize(rPoplPush(source, destKey));
	}

	@Override
	public <V> V rPoplPushObject(final byte[] source, final byte[] destKey){
		return serializer.deserialize(rPoplPush(source, destKey));
	}

	@Override
	public <V> V rPoplPushObject(final String source, final String destKey, final Class<V> clazz){
		return serializer.deserialize(rPoplPush(source, destKey), clazz);
	}

	@Override
	public <V> V rPoplPushObject(final byte[] source, final byte[] destKey, final Class<V> clazz){
		return serializer.deserialize(rPoplPush(source, destKey), clazz);
	}

	@Override
	public <V> V rPoplPushObject(final String source, final String destKey, final TypeReference<V> type){
		return serializer.deserialize(rPoplPush(source, destKey), type);
	}

	@Override
	public <V> V rPoplPushObject(final byte[] source, final byte[] destKey, final TypeReference<V> type){
		return serializer.deserialize(rPoplPush(source, destKey), type);
	}

	@Override
	public List<String> brPop(final String key, final int timeout){
		return brPop(new String[]{key}, timeout);
	}

	@Override
	public List<byte[]> brPop(final byte[] key, final int timeout){
		return brPop(new byte[][]{key}, timeout);
	}

	@Override
	public <V> List<V> brPopObject(final String key, final int timeout){
		return ReturnUtils.objectFromListString(serializer, brPop(key, timeout));
	}

	@Override
	public <V> List<V> brPopObject(final byte[] key, final int timeout){
		return ReturnUtils.objectFromListByte(serializer, brPop(key, timeout));
	}

	@Override
	public <V> List<V> brPopObject(final String key, final int timeout, final Class<V> clazz){
		return ReturnUtils.objectFromListString(serializer, brPop(key, timeout), clazz);
	}

	@Override
	public <V> List<V> brPopObject(final byte[] key, final int timeout, final Class<V> clazz){
		return ReturnUtils.objectFromListByte(serializer, brPop(key, timeout), clazz);
	}

	@Override
	public <V> List<V> brPopObject(final String key, final int timeout, final TypeReference<V> type){
		return ReturnUtils.objectFromListString(serializer, brPop(key, timeout), type);
	}

	@Override
	public <V> List<V> brPopObject(final byte[] key, final int timeout, final TypeReference<V> type){
		return ReturnUtils.objectFromListByte(serializer, brPop(key, timeout), type);
	}

	@Override
	public <V> V brPoplPushObject(final String source, final String destKey, final int timeout){
		return serializer.deserialize(brPoplPush(source, destKey, timeout));
	}

	@Override
	public <V> V brPoplPushObject(final byte[] source, final byte[] destKey, final int timeout){
		return serializer.deserialize(brPoplPush(source, destKey, timeout));
	}

	@Override
	public <V> V brPoplPushObject(final String source, final String destKey, final int timeout, final Class<V> clazz){
		return serializer.deserialize(brPoplPush(source, destKey, timeout), clazz);
	}

	@Override
	public <V> V brPoplPushObject(final byte[] source, final byte[] destKey, final int timeout, final Class<V> clazz){
		return serializer.deserialize(brPoplPush(source, destKey, timeout), clazz);
	}

	@Override
	public <V> V brPoplPushObject(final String source, final String destKey, final int timeout,
			final TypeReference<V> type){
		return serializer.deserialize(brPoplPush(source, destKey, timeout), type);
	}

	@Override
	public <V> V brPoplPushObject(final byte[] source, final byte[] destKey, final int timeout,
			final TypeReference<V> type){
		return serializer.deserialize(brPoplPush(source, destKey, timeout), type);
	}

	@Override
	public Long rPush(final String key, final String value){
		return rPush(key, new String[]{value});
	}

	@Override
	public Long rPush(final byte[] key, final byte[] value){
		return rPush(key, new byte[][]{value});
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
		return rPush(key, serializer(values));
	}

	@Override
	public <V> Long rPush(final byte[] key, final V... values){
		return rPush(key, serializerAsByte(values));
	}

	@Override
	public Long rPushX(final String key, final String value){
		return rPushX(key, new String[]{value});
	}

	@Override
	public Long rPushX(final byte[] key, final byte[] value){
		return rPushX(key, new byte[][]{value});
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
		return rPushX(key, serializer(values));
	}

	@Override
	public <V> Long rPushX(final byte[] key, final V... values){
		return rPushX(key, serializerAsByte(values));
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final int start, final int end){
		return ReturnUtils.objectFromListString(serializer, lRange(key, start, end));
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final int start, final int end){
		return ReturnUtils.objectFromListByte(serializer, lRange(key, start, end));
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final long start, final long end){
		return ReturnUtils.objectFromListString(serializer, lRange(key, start, end));
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end){
		return ReturnUtils.objectFromListByte(serializer, lRange(key, start, end));
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final int start, final int end, final Class<V> clazz){
		return ReturnUtils.objectFromListString(serializer, lRange(key, start, end), clazz);
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final int start, final int end, final Class<V> clazz){
		return ReturnUtils.objectFromListByte(serializer, lRange(key, start, end), clazz);
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final long start, final long end, final Class<V> clazz){
		return ReturnUtils.objectFromListString(serializer, lRange(key, start, end), clazz);
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end, final Class<V> clazz){
		return ReturnUtils.objectFromListByte(serializer, lRange(key, start, end), clazz);
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final int start, final int end, final TypeReference<V> type){
		return ReturnUtils.objectFromListString(serializer, lRange(key, start, end), type);
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final int start, final int end, final TypeReference<V> type){
		return ReturnUtils.objectFromListByte(serializer, lRange(key, start, end), type);
	}

	@Override
	public <V> List<V> lRangeObject(final String key, final long start, final long end, final TypeReference<V> type){
		return ReturnUtils.objectFromListString(serializer, lRange(key, start, end), type);
	}

	@Override
	public <V> List<V> lRangeObject(final byte[] key, final long start, final long end, final TypeReference<V> type){
		return ReturnUtils.objectFromListByte(serializer, lRange(key, start, end), type);
	}

	@Override
	public Long sAdd(final String key, final String member){
		return sAdd(key, new String[]{member});
	}

	@Override
	public Long sAdd(final byte[] key, final byte[] member){
		return sAdd(key, new byte[][]{member});
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
		return sAdd(key, serializer(members));
	}

	@Override
	public <V> Long sAdd(final byte[] key, final V... members){
		return sAdd(key, serializerAsByte(members));
	}

	@Override
	public <V> Set<V> sMembersObject(final String key){
		return ReturnUtils.objectFromSetString(serializer, sMembers(key));
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key){
		return ReturnUtils.objectFromSetByte(serializer, sMembers(key));
	}

	@Override
	public <V> Set<V> sMembersObject(final String key, final Class<V> clazz){
		return ReturnUtils.objectFromSetString(serializer, sMembers(key), clazz);
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key, final Class<V> clazz){
		return ReturnUtils.objectFromSetByte(serializer, sMembers(key), clazz);
	}

	@Override
	public <V> Set<V> sMembersObject(final String key, final TypeReference<V> type){
		return ReturnUtils.objectFromSetString(serializer, sMembers(key), type);
	}

	@Override
	public <V> Set<V> sMembersObject(final byte[] key, final TypeReference<V> type){
		return ReturnUtils.objectFromSetByte(serializer, sMembers(key), type);
	}

	@Override
	public <V> V sPopObject(final String key){
		return serializer.deserialize(sPop(key));
	}

	@Override
	public <V> V sPopObject(final byte[] key){
		return serializer.deserialize(sPop(key));
	}

	@Override
	public <V> V sPopObject(final String key, final Class<V> clazz){
		return serializer.deserialize(sPop(key), clazz);
	}

	@Override
	public <V> V sPopObject(final byte[] key, final Class<V> clazz){
		return serializer.deserialize(sPop(key), clazz);
	}

	@Override
	public <V> V sPopObject(final String key, final TypeReference<V> type){
		return serializer.deserialize(sPop(key), type);
	}

	@Override
	public <V> V sPopObject(final byte[] key, final TypeReference<V> type){
		return serializer.deserialize(sPop(key), type);
	}

	@Override
	public <V> V sRandMemberObject(final String key){
		return serializer.deserialize(sRandMember(key));
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key){
		return serializer.deserialize(sRandMember(key));
	}

	@Override
	public <V> V sRandMemberObject(final String key, final Class<V> clazz){
		return serializer.deserialize(sRandMember(key), clazz);
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key, final Class<V> clazz){
		return serializer.deserialize(sRandMember(key), clazz);
	}

	@Override
	public <V> V sRandMemberObject(final String key, final TypeReference<V> type){
		return serializer.deserialize(sRandMember(key), type);
	}

	@Override
	public <V> V sRandMemberObject(final byte[] key, final TypeReference<V> type){
		return serializer.deserialize(sRandMember(key), type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final int count){
		return ReturnUtils.objectFromListString(serializer, sRandMember(key, count));
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final int count){
		return ReturnUtils.objectFromListByte(serializer, sRandMember(key, count));
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count){
		return ReturnUtils.objectFromListString(serializer, sRandMember(key, count));
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count){
		return ReturnUtils.objectFromListByte(serializer, sRandMember(key, count));
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final int count, final Class<V> clazz){
		return ReturnUtils.objectFromListString(serializer, sRandMember(key, count), clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final int count, final Class<V> clazz){
		return ReturnUtils.objectFromListByte(serializer, sRandMember(key, count), clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count, final Class<V> clazz){
		return ReturnUtils.objectFromListString(serializer, sRandMember(key, count), clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count, final Class<V> clazz){
		return ReturnUtils.objectFromListByte(serializer, sRandMember(key, count), clazz);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final int count, final TypeReference<V> type){
		return ReturnUtils.objectFromListString(serializer, sRandMember(key, count), type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final int count, final TypeReference<V> type){
		return ReturnUtils.objectFromListByte(serializer, sRandMember(key, count), type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final String key, final long count, final TypeReference<V> type){
		return ReturnUtils.objectFromListString(serializer, sRandMember(key, count), type);
	}

	@Override
	public <V> List<V> sRandMemberObject(final byte[] key, final long count, final TypeReference<V> type){
		return ReturnUtils.objectFromListByte(serializer, sRandMember(key, count), type);
	}

	@Override
	public Long sRem(final String key, final String member){
		return sRem(key, new String[]{member});
	}

	@Override
	public Long sRem(final byte[] key, final byte[] member){
		return sRem(key, new byte[][]{member});
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
		return sRem(key, serializer(members));
	}

	@Override
	public <V> Long sRem(final byte[] key, final V... members){
		return sRem(key, serializerAsByte(members));
	}

	@Override
	public Set<String> sDiff(final String key){
		return sDiff(new String[]{key});
	}

	@Override
	public Set<byte[]> sDiff(final byte[] key){
		return sDiff(new byte[][]{key});
	}

	@Override
	public <V> Set<V> sDiffObject(final String key){
		return ReturnUtils.objectFromSetString(serializer, sDiff(key));
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[] key){
		return ReturnUtils.objectFromSetByte(serializer, sDiff(key));
	}

	@Override
	public <V> Set<V> sDiffObject(final String key, final Class<V> clazz){
		return ReturnUtils.objectFromSetString(serializer, sDiff(key), clazz);
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[] key, final Class<V> clazz){
		return ReturnUtils.objectFromSetByte(serializer, sDiff(key), clazz);
	}

	@Override
	public <V> Set<V> sDiffObject(final String key, final TypeReference<V> type){
		return ReturnUtils.objectFromSetString(serializer, sDiff(key), type);
	}

	@Override
	public <V> Set<V> sDiffObject(final byte[] key, final TypeReference<V> type){
		return ReturnUtils.objectFromSetByte(serializer, sDiff(key), type);
	}

	@Override
	public Long sDiffStore(final String destKey, final String key){
		return sDiffStore(destKey, new String[]{key});
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[] key){
		return sDiffStore(destKey, new byte[][]{key});
	}

	@Override
	public Set<String> sInter(final String key){
		return sInter(new String[]{key});
	}

	@Override
	public Set<byte[]> sInter(final byte[] key){
		return sInter(new byte[][]{key});
	}

	@Override
	public Long sInterStore(final String destKey, final String key){
		return sInterStore(destKey, new String[]{key});
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[] key){
		return sInterStore(destKey, new byte[][]{key});
	}

	@Override
	public Set<String> sUnion(final String key){
		return sUnion(new String[]{key});
	}

	@Override
	public Set<byte[]> sUnion(final byte[] key){
		return sUnion(new byte[][]{key});
	}

	@Override
	public Long sUnionStore(final String destKey, final String key){
		return sUnionStore(destKey, new String[]{key});
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[] key){
		return sUnionStore(destKey, new byte[][]{key});
	}

	@Override
	public Long zAdd(final String key, final float score, final String member){
		return zAdd(key, new Float(score), member);
	}

	@Override
	public Long zAdd(final byte[] key, final float score, final byte[] member){
		return zAdd(key, new Float(score), member);
	}

	@Override
	public Long zAdd(final String key, final double score, final String member){
		return zAdd(key, new Double(score), member);
	}

	@Override
	public Long zAdd(final byte[] key, final double score, final byte[] member){
		return zAdd(key, new Double(score), member);
	}

	@Override
	public Long zAdd(final String key, final int score, final String member){
		return zAdd(key, new Integer(score), member);
	}

	@Override
	public Long zAdd(final byte[] key, final int score, final byte[] member){
		return zAdd(key, new Integer(score), member);
	}

	@Override
	public Long zAdd(final String key, final long score, final String member){
		return zAdd(key, new Long(score), member);
	}

	@Override
	public Long zAdd(final byte[] key, final long score, final byte[] member){
		return zAdd(key, new Long(score), member);
	}

	@Override
	public Long zAdd(final String key, final Number score, final String member){
		final Map<String, Number> members = new HashMap<>(1);

		members.put(member, score);

		return zAdd(key, members);
	}

	@Override
	public Long zAdd(final byte[] key, final Number score, final byte[] member){
		final Map<byte[], Number> members = new HashMap<>(1);

		members.put(member, score);

		return zAdd(key, members);
	}

	@Override
	public Long zAdd(final String key, final KeyValue<String, Number> member){
		return zAdd(key, Arrays.asList(member));
	}

	@Override
	public Long zAdd(final byte[] key, final KeyValue<byte[], Number> member){
		return zAdd(key, Arrays.asList(member));
	}

	@Override
	public Long zRem(final String key, final String member){
		return zRem(key, new String[]{member});
	}

	@Override
	public Long zRem(final byte[] key, final byte[] member){
		return zRem(key, new byte[][]{member});
	}

	@Override
	public Long zInterStore(final String destKey, final String key){
		return zInterStore(destKey, new String[]{key});
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[] key){
		return zInterStore(destKey, new byte[][]{key});
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final String key){
		return zInterStore(destKey, aggregate, new String[]{key});
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final byte[] key){
		return zInterStore(destKey, aggregate, new byte[][]{key});
	}

	@Override
	public Long zInterStore(final String destKey, final double weight, final String key){
		return zInterStore(destKey, new double[]{weight}, new String[]{key});
	}

	@Override
	public Long zInterStore(final byte[] destKey, final double weight, final byte[] key){
		return zInterStore(destKey, new double[]{weight}, new byte[][]{key});
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final double weight, final String key){
		return zInterStore(destKey, aggregate, new double[]{weight}, new String[]{key});
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double weight, final byte[] key){
		return zInterStore(destKey, aggregate, new double[]{weight}, new byte[][]{key});
	}

	@Override
	public Long zUnionStore(final String destKey, final String key){
		return zUnionStore(destKey, new String[]{key});
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[] key){
		return zUnionStore(destKey, new byte[][]{key});
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final String key){
		return zUnionStore(destKey, aggregate, new String[]{key});
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final byte[] key){
		return zUnionStore(destKey, aggregate, new byte[][]{key});
	}

	@Override
	public Long zUnionStore(final String destKey, final double weight, final String key){
		return zUnionStore(destKey, new double[]{weight}, new String[]{key});
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final double weight, final byte[] key){
		return zUnionStore(destKey, new double[]{weight}, new byte[][]{key});
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final double weight, final String key){
		return zUnionStore(destKey, aggregate, new double[]{weight}, new String[]{key});
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final double weight, final byte[] key){
		return zUnionStore(destKey, aggregate, new double[]{weight}, new byte[][]{key});
	}
	
}
