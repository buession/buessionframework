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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis;

import com.buession.core.Geo;
import com.buession.core.Status;
import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.Server;
import com.buession.redis.core.operations.BitMapOperations;
import com.buession.redis.core.operations.GeoOperations;
import com.buession.redis.core.operations.HashOperations;
import com.buession.redis.core.operations.KeyOperations;
import com.buession.redis.core.operations.ListOperations;
import com.buession.redis.core.operations.LuaOperations;
import com.buession.redis.core.operations.PubSubOperations;
import com.buession.redis.core.operations.SetOperations;
import com.buession.redis.core.operations.SortedSetOperations;
import com.buession.redis.core.operations.StringOperations;
import com.buession.redis.core.operations.TransactionOperations;
import com.buession.redis.core.types.KeyValue;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
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
        ListOperations, SetOperations, SortedSetOperations, GeoOperations, BitMapOperations, TransactionOperations,
        PubSubOperations, LuaOperations {

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
     *         Redis 连接对称
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
        return migrate(key, host, Server.DEFAULT_PORT, db, timeout);
    }

    @Override
    public Status migrate(final byte[] key, final String host, final int db, final int timeout){
        return migrate(key, host, Server.DEFAULT_PORT, db, timeout);
    }

    @Override
    public Status migrate(final String key, final String host, final int db, final int timeout, final
    MigrateOperation migrateOperation){
        return migrate(key, host, Server.DEFAULT_PORT, db, timeout, migrateOperation);
    }

    @Override
    public Status migrate(final byte[] key, final String host, final int db, final int timeout, final
    MigrateOperation migrateOperation){
        return migrate(key, host, Server.DEFAULT_PORT, db, timeout, migrateOperation);
    }

    @Override
    public Status del(final String key){
        return Status.valueOf(del(new String[]{key}) > 0);
    }

    @Override
    public Status del(final byte[] key){
        return Status.valueOf(del(new byte[][]{key}) > 0);
    }

    @Override
    public Status delete(final String key){
        return del(key);
    }

    @Override
    public Status delete(final byte[] key){
        return del(key);
    }

    @Override
    public Long delete(final String... keys){
        return del(keys);
    }

    @Override
    public Long delete(final byte[]... keys){
        return del(keys);
    }

    @Override
    public <V> Status set(final String key, final V value){
        return set(key, serializer.encode(value));
    }

    @Override
    public <V> Status set(final byte[] key, final V value){
        return set(key, serializer.encodeAsBytes(value));
    }

    @Override
    public <V> Status set(final String key, final V value, final SetArgument setArgument){
        return set(key, serializer.encode(value), setArgument);
    }

    @Override
    public <V> Status set(final byte[] key, final V value, final SetArgument setArgument){
        return set(key, serializer.encodeAsBytes(value), setArgument);
    }

    @Override
    public <V> Status setEx(final String key, final V value, final int lifetime){
        return setEx(key, serializer.encode(value), lifetime);
    }

    @Override
    public <V> Status setEx(final byte[] key, final V value, final int lifetime){
        return setEx(key, serializer.encodeAsBytes(value), lifetime);
    }

    @Override
    public <V> Status pSetEx(final String key, final V value, final int lifetime){
        return pSetEx(key, serializer.encode(value), lifetime);
    }

    @Override
    public <V> Status pSetEx(final byte[] key, final V value, final int lifetime){
        return pSetEx(key, serializer.encodeAsBytes(value), lifetime);
    }

    @Override
    public <V> Status setNx(final String key, final V value){
        return setNx(key, serializer.encode(value));
    }

    @Override
    public <V> Status setNx(final byte[] key, final V value){
        return setNx(key, serializer.encodeAsBytes(value));
    }

    @Override
    public <V> V getObject(final String key){
        return serializer.decode(get(key));
    }

    @Override
    public <V> V getObject(final byte[] key){
        return serializer.decode(get(key));
    }

    @Override
    public <V> V getObject(final String key, final Class<V> clazz){
        return serializer.decode(get(key), clazz);
    }

    @Override
    public <V> V getObject(final byte[] key, final Class<V> clazz){
        return serializer.decode(get(key), clazz);
    }

    @Override
    public <V> V getObject(final String key, final TypeReference<V> type){
        return serializer.decode(get(key), type);
    }

    @Override
    public <V> V getObject(final byte[] key, final TypeReference<V> type){
        return serializer.decode(get(key), type);
    }

    @Override
    public <V> V getSet(final String key, final V value){
        return serializer.decode(getSet(key, serializer.encode(value)));
    }

    @Override
    public <V> V getSet(final byte[] key, final V value){
        return serializer.decode(getSet(key, serializer.encode(value)));
    }

    @Override
    public <V> V getSet(final String key, final V value, final Class<V> clazz){
        return serializer.decode(getSet(key, serializer.encode(value)), clazz);
    }

    @Override
    public <V> V getSet(final byte[] key, final V value, final Class<V> clazz){
        return serializer.decode(getSet(key, serializer.encode(value)), clazz);
    }

    @Override
    public <V> V getSet(final String key, final V value, final TypeReference<V> type){
        return serializer.decode(getSet(key, serializer.encode(value)), type);
    }

    @Override
    public <V> V getSet(final byte[] key, final V value, final TypeReference<V> type){
        return serializer.decode(getSet(key, serializer.encode(value)), type);
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
        return returnObjectValueFromListString(mGet(keys));
    }

    @Override
    public <V> List<V> mGetObject(final byte[]... keys){
        return returnObjectValueFromListByte(mGet(keys));
    }

    @Override
    public <V> List<V> mGetObject(final String[] keys, final Class<V> clazz){
        return returnObjectValueFromListString(mGet(keys), clazz);
    }

    @Override
    public <V> List<V> mGetObject(final byte[][] keys, final Class<V> clazz){
        return returnObjectValueFromListByte(mGet(keys), clazz);
    }

    @Override
    public <V> List<V> mGetObject(final String[] keys, final TypeReference<V> type){
        return returnObjectValueFromListString(mGet(keys), type);
    }

    @Override
    public <V> List<V> mGetObject(final byte[][] keys, final TypeReference<V> type){
        return returnObjectValueFromListByte(mGet(keys), type);
    }

    @Override
    public <V> List<V> hValsObject(final String key){
        return returnObjectValueFromListString(hVals(key));
    }

    @Override
    public <V> List<V> hValsObject(final byte[] key){
        return returnObjectValueFromListByte(hVals(key));
    }

    @Override
    public <V> List<V> hValsObject(final String key, final Class<V> clazz){
        return returnObjectValueFromListString(hVals(key), clazz);
    }

    @Override
    public <V> List<V> hValsObject(final byte[] key, final Class<V> clazz){
        return returnObjectValueFromListByte(hVals(key), clazz);
    }

    @Override
    public <V> List<V> hValsObject(final String key, final TypeReference<V> type){
        return returnObjectValueFromListString(hVals(key), type);
    }

    @Override
    public <V> List<V> hValsObject(final byte[] key, final TypeReference<V> type){
        return returnObjectValueFromListByte(hVals(key), type);
    }

    @Override
    public <V> Status hSet(final String key, final String field, final V value){
        return hSet(key, field, serializer.encode(value));
    }

    @Override
    public <V> Status hSet(final byte[] key, final byte[] field, final V value){
        return hSet(key, field, serializer.encodeAsBytes(value));
    }

    @Override
    public <V> Status hSetNx(final String key, final String field, final V value){
        return hSetNx(key, field, serializer.encode(value));
    }

    @Override
    public <V> Status hSetNx(final byte[] key, final byte[] field, final V value){
        return hSetNx(key, field, serializer.encodeAsBytes(value));
    }

    @Override
    public <V> V hGetObject(final String key, final String field){
        return serializer.decode(hGet(key, field));
    }

    @Override
    public <V> V hGetObject(final byte[] key, final byte[] field){
        return serializer.decode(hGet(key, field));
    }

    @Override
    public <V> V hGetObject(final String key, final String field, final Class<V> clazz){
        return serializer.decode(hGet(key, field), clazz);
    }

    @Override
    public <V> V hGetObject(final byte[] key, final byte[] field, final Class<V> clazz){
        return serializer.decode(hGet(key, field), clazz);
    }

    @Override
    public <V> V hGetObject(final String key, final String field, final TypeReference<V> type){
        return serializer.decode(hGet(key, field), type);
    }

    @Override
    public <V> V hGetObject(final byte[] key, final byte[] field, final TypeReference<V> type){
        return serializer.decode(hGet(key, field), type);
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
                    temp.put(kv.getKey(), serializer.encode(kv.getValue()));
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
                    temp.put(kv.getKey(), serializer.encodeAsBytes(kv.getValue()));
                }else{
                    temp.put(kv.getKey(), serializer.encodeAsBytes(kv.getValue()));
                }
            }

            return hMSet(key, temp);
        }
    }

    @Override
    public <V> List<V> hMGetObject(final String key, final String... fields){
        return returnObjectValueFromListString(hMGet(key, fields));
    }

    @Override
    public <V> List<V> hMGetObject(final byte[] key, final byte[]... fields){
        return returnObjectValueFromListByte(hMGet(key, fields));
    }

    @Override
    public <V> List<V> hMGetObject(final String key, final String[] fields, final Class<V> clazz){
        return returnObjectValueFromListString(hMGet(key, fields), clazz);
    }

    @Override
    public <V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final Class<V> clazz){
        return returnObjectValueFromListByte(hMGet(key, fields), clazz);
    }

    @Override
    public <V> List<V> hMGetObject(final String key, final String[] fields, final TypeReference<V> type){
        return returnObjectValueFromListString(hMGet(key, fields), type);
    }

    @Override
    public <V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final TypeReference<V> type){
        return returnObjectValueFromListByte(hMGet(key, fields), type);
    }

    @Override
    public <V> Map<String, V> hGetAllObject(final String key){
        return returnObjectValueFromMapString(hGetAll(key));
    }

    @Override
    public <V> Map<byte[], V> hGetAllObject(final byte[] key){
        return returnObjectValueFromMapByte(hGetAll(key));
    }

    @Override
    public <V> Map<String, V> hGetAllObject(final String key, final Class<V> clazz){
        return returnObjectValueFromMapString(hGetAll(key), clazz);
    }

    @Override
    public <V> Map<byte[], V> hGetAllObject(final byte[] key, final Class<V> clazz){
        return returnObjectValueFromMapByte(hGetAll(key), clazz);
    }

    @Override
    public <V> Map<String, V> hGetAllObject(final String key, final TypeReference<V> type){
        return returnObjectValueFromMapString(hGetAll(key), type);
    }

    @Override
    public <V> Map<byte[], V> hGetAllObject(final byte[] key, final TypeReference<V> type){
        return returnObjectValueFromMapByte(hGetAll(key), type);
    }

    @Override
    public Status hDel(final String key, final String field){
        return Status.valueOf(hDel(key, new String[]{field}) > 0);
    }

    @Override
    public Status hDel(final byte[] key, final byte[] field){
        return Status.valueOf(hDel(key, new byte[][]{field}) > 0);
    }

    @Override
    public Status hDelete(final String key, final String field){
        return hDel(key, field);
    }

    @Override
    public Status hDelete(final byte[] key, final byte[] field){
        return hDel(key, field);
    }

    @Override
    public Long hDelete(final String key, final String... fields){
        return hDel(key, fields);
    }

    @Override
    public Long hDelete(final byte[] key, final byte[]... fields){
        return hDel(key, fields);
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
        return lPush(key, serializer.encode(value));
    }

    @Override
    public <V> Long lPush(final byte[] key, final V value){
        return lPush(key, serializer.encodeAsBytes(value));
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
        return lPushX(key, serializer.encode(value));
    }

    @Override
    public <V> Long lPushX(final byte[] key, final V value){
        return lPushX(key, serializer.encodeAsBytes(value));
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
        return lInsert(key, serializer.encode(value), position, serializer.encode(pivot));
    }

    @Override
    public <V> Long lInsert(final byte[] key, final V value, final ListPosition position, final V pivot){
        return lInsert(key, serializer.encodeAsBytes(value), position, serializer.encodeAsBytes(pivot));
    }

    @Override
    public <V> Status lSet(final String key, final int index, final V value){
        return lSet(key, index, serializer.encode(value));
    }

    @Override
    public <V> Status lSet(final byte[] key, final int index, final V value){
        return lSet(key, index, serializer.encodeAsBytes(value));
    }

    @Override
    public <V> Status lSet(final String key, final long index, final V value){
        return lSet(key, index, serializer.encode(value));
    }

    @Override
    public <V> Status lSet(final byte[] key, final long index, final V value){
        return lSet(key, index, serializer.encodeAsBytes(value));
    }

    @Override
    public <V> V lIndexObject(final String key, final int index){
        return serializer.decode(lIndex(key, index));
    }

    @Override
    public <V> V lIndexObject(final byte[] key, final int index){
        return serializer.decode(lIndex(key, index));
    }

    @Override
    public <V> V lIndexObject(final String key, final int index, final Class<V> clazz){
        return serializer.decode(lIndex(key, index), clazz);
    }

    @Override
    public <V> V lIndexObject(final byte[] key, final int index, final Class<V> clazz){
        return serializer.decode(lIndex(key, index), clazz);
    }

    @Override
    public <V> V lIndexObject(final String key, final int index, final TypeReference<V> type){
        return serializer.decode(lIndex(key, index), type);
    }

    @Override
    public <V> V lIndexObject(final byte[] key, final int index, final TypeReference<V> type){
        return serializer.decode(lIndex(key, index), type);
    }

    @Override
    public <V> V lIndexObject(final String key, final long index){
        return serializer.decode(lIndex(key, index));
    }

    @Override
    public <V> V lIndexObject(final byte[] key, final long index){
        return serializer.decode(lIndex(key, index));
    }

    @Override
    public <V> V lIndexObject(final String key, final long index, final Class<V> clazz){
        return serializer.decode(lIndex(key, index), clazz);
    }

    @Override
    public <V> V lIndexObject(final byte[] key, final long index, final Class<V> clazz){
        return serializer.decode(lIndex(key, index), clazz);
    }

    @Override
    public <V> V lIndexObject(final String key, final long index, final TypeReference<V> type){
        return serializer.decode(lIndex(key, index), type);
    }

    @Override
    public <V> V lIndexObject(final byte[] key, final long index, final TypeReference<V> type){
        return serializer.decode(lIndex(key, index), type);
    }

    @Override
    public <V> V lPopObject(final String key){
        return serializer.decode(lPop(key));
    }

    @Override
    public <V> V lPopObject(final byte[] key){
        return serializer.decode(lPop(key));
    }

    @Override
    public <V> V lPopObject(final String key, final Class<V> clazz){
        return serializer.decode(lPop(key), clazz);
    }

    @Override
    public <V> V lPopObject(final byte[] key, final Class<V> clazz){
        return serializer.decode(lPop(key), clazz);
    }

    @Override
    public <V> V lPopObject(final String key, final TypeReference<V> type){
        return serializer.decode(lPop(key), type);
    }

    @Override
    public <V> V lPopObject(final byte[] key, final TypeReference<V> type){
        return serializer.decode(lPop(key), type);
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
    public <V> V rPopObject(final String key){
        return serializer.decode(rPop(key));
    }

    @Override
    public <V> V rPopObject(final byte[] key){
        return serializer.decode(rPop(key));
    }

    @Override
    public <V> V rPopObject(final String key, final Class<V> clazz){
        return serializer.decode(rPop(key), clazz);
    }

    @Override
    public <V> V rPopObject(final byte[] key, final Class<V> clazz){
        return serializer.decode(rPop(key), clazz);
    }

    @Override
    public <V> V rPopObject(final String key, final TypeReference<V> type){
        return serializer.decode(rPop(key), type);
    }

    @Override
    public <V> V rPopObject(final byte[] key, final TypeReference<V> type){
        return serializer.decode(rPop(key), type);
    }

    @Override
    public <V> V rPoplPushObject(final String source, final String destKey){
        return serializer.decode(rPoplPush(source, destKey));
    }

    @Override
    public <V> V rPoplPushObject(final byte[] source, final byte[] destKey){
        return serializer.decode(rPoplPush(source, destKey));
    }

    @Override
    public <V> V rPoplPushObject(final String source, final String destKey, final Class<V> clazz){
        return serializer.decode(rPoplPush(source, destKey), clazz);
    }

    @Override
    public <V> V rPoplPushObject(final byte[] source, final byte[] destKey, final Class<V> clazz){
        return serializer.decode(rPoplPush(source, destKey), clazz);
    }

    @Override
    public <V> V rPoplPushObject(final String source, final String destKey, final TypeReference<V> type){
        return serializer.decode(rPoplPush(source, destKey), type);
    }

    @Override
    public <V> V rPoplPushObject(final byte[] source, final byte[] destKey, final TypeReference<V> type){
        return serializer.decode(rPoplPush(source, destKey), type);
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
    public Long rPush(final String key, final String value){
        return rPush(key, new String[]{value});
    }

    @Override
    public Long rPush(final byte[] key, final byte[] value){
        return rPush(key, new byte[][]{value});
    }

    @Override
    public <V> Long rPush(final String key, final V value){
        return rPush(key, serializer.encode(value));
    }

    @Override
    public <V> Long rPush(final byte[] key, final V value){
        return rPush(key, serializer.encodeAsBytes(value));
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
        return rPushX(key, serializer.encode(value));
    }

    @Override
    public <V> Long rPushX(final byte[] key, final V value){
        return rPushX(key, serializer.encodeAsBytes(value));
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
        return returnObjectValueFromListString(lRange(key, start, end));
    }

    @Override
    public <V> List<V> lRangeObject(final byte[] key, final int start, final int end){
        return returnObjectValueFromListByte(lRange(key, start, end));
    }

    @Override
    public <V> List<V> lRangeObject(final String key, final long start, final long end){
        return returnObjectValueFromListString(lRange(key, start, end));
    }

    @Override
    public <V> List<V> lRangeObject(final byte[] key, final long start, final long end){
        return returnObjectValueFromListByte(lRange(key, start, end));
    }

    @Override
    public <V> List<V> lRangeObject(final String key, final int start, final int end, final Class<V> clazz){
        return returnObjectValueFromListString(lRange(key, start, end), clazz);
    }

    @Override
    public <V> List<V> lRangeObject(final byte[] key, final int start, final int end, final Class<V> clazz){
        return returnObjectValueFromListByte(lRange(key, start, end), clazz);
    }

    @Override
    public <V> List<V> lRangeObject(final String key, final long start, final long end, final Class<V> clazz){
        return returnObjectValueFromListString(lRange(key, start, end), clazz);
    }

    @Override
    public <V> List<V> lRangeObject(final byte[] key, final long start, final long end, final Class<V> clazz){
        return returnObjectValueFromListByte(lRange(key, start, end), clazz);
    }

    @Override
    public <V> List<V> lRangeObject(final String key, final int start, final int end, final TypeReference<V> type){
        return returnObjectValueFromListString(lRange(key, start, end), type);
    }

    @Override
    public <V> List<V> lRangeObject(final byte[] key, final int start, final int end, final TypeReference<V> type){
        return returnObjectValueFromListByte(lRange(key, start, end), type);
    }

    @Override
    public <V> List<V> lRangeObject(final String key, final long start, final long end, final TypeReference<V> type){
        return returnObjectValueFromListString(lRange(key, start, end), type);
    }

    @Override
    public <V> List<V> lRangeObject(final byte[] key, final long start, final long end, final TypeReference<V> type){
        return returnObjectValueFromListByte(lRange(key, start, end), type);
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
        return sAdd(key, serializer.encode(member));
    }

    @Override
    public <V> Long sAdd(final byte[] key, final V member){
        return sAdd(key, serializer.encodeAsBytes(member));
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
        return returnObjectValueFromSetString(sMembers(key));
    }

    @Override
    public <V> Set<V> sMembersObject(final byte[] key){
        return returnObjectValueFromSetByte(sMembers(key));
    }

    @Override
    public <V> Set<V> sMembersObject(final String key, final Class<V> clazz){
        return returnObjectValueFromSetString(sMembers(key), clazz);
    }

    @Override
    public <V> Set<V> sMembersObject(final byte[] key, final Class<V> clazz){
        return returnObjectValueFromSetByte(sMembers(key), clazz);
    }

    @Override
    public <V> Set<V> sMembersObject(final String key, final TypeReference<V> type){
        return returnObjectValueFromSetString(sMembers(key), type);
    }

    @Override
    public <V> Set<V> sMembersObject(final byte[] key, final TypeReference<V> type){
        return returnObjectValueFromSetByte(sMembers(key), type);
    }

    @Override
    public <V> V sPopObject(final String key){
        return serializer.decode(sPop(key));
    }

    @Override
    public <V> V sPopObject(final byte[] key){
        return serializer.decode(sPop(key));
    }

    @Override
    public <V> V sPopObject(final String key, final Class<V> clazz){
        return serializer.decode(sPop(key), clazz);
    }

    @Override
    public <V> V sPopObject(final byte[] key, final Class<V> clazz){
        return serializer.decode(sPop(key), clazz);
    }

    @Override
    public <V> V sPopObject(final String key, final TypeReference<V> type){
        return serializer.decode(sPop(key), type);
    }

    @Override
    public <V> V sPopObject(final byte[] key, final TypeReference<V> type){
        return serializer.decode(sPop(key), type);
    }

    @Override
    public <V> V sRandMemberObject(final String key){
        return serializer.decode(sRandMember(key));
    }

    @Override
    public <V> V sRandMemberObject(final byte[] key){
        return serializer.decode(sRandMember(key));
    }

    @Override
    public <V> V sRandMemberObject(final String key, final Class<V> clazz){
        return serializer.decode(sRandMember(key), clazz);
    }

    @Override
    public <V> V sRandMemberObject(final byte[] key, final Class<V> clazz){
        return serializer.decode(sRandMember(key), clazz);
    }

    @Override
    public <V> V sRandMemberObject(final String key, final TypeReference<V> type){
        return serializer.decode(sRandMember(key), type);
    }

    @Override
    public <V> V sRandMemberObject(final byte[] key, final TypeReference<V> type){
        return serializer.decode(sRandMember(key), type);
    }

    @Override
    public <V> List<V> sRandMemberObject(final String key, final int count){
        return returnObjectValueFromListString(sRandMember(key, count));
    }

    @Override
    public <V> List<V> sRandMemberObject(final byte[] key, final int count){
        return returnObjectValueFromListByte(sRandMember(key, count));
    }

    @Override
    public <V> List<V> sRandMemberObject(final String key, final long count){
        return returnObjectValueFromListString(sRandMember(key, count));
    }

    @Override
    public <V> List<V> sRandMemberObject(final byte[] key, final long count){
        return returnObjectValueFromListByte(sRandMember(key, count));
    }

    @Override
    public <V> List<V> sRandMemberObject(final String key, final int count, final Class<V> clazz){
        return returnObjectValueFromListString(sRandMember(key, count), clazz);
    }

    @Override
    public <V> List<V> sRandMemberObject(final byte[] key, final int count, final Class<V> clazz){
        return returnObjectValueFromListByte(sRandMember(key, count), clazz);
    }

    @Override
    public <V> List<V> sRandMemberObject(final String key, final long count, final Class<V> clazz){
        return returnObjectValueFromListString(sRandMember(key, count), clazz);
    }

    @Override
    public <V> List<V> sRandMemberObject(final byte[] key, final long count, final Class<V> clazz){
        return returnObjectValueFromListByte(sRandMember(key, count), clazz);
    }

    @Override
    public <V> List<V> sRandMemberObject(final String key, final int count, final TypeReference<V> type){
        return returnObjectValueFromListString(sRandMember(key, count), type);
    }

    @Override
    public <V> List<V> sRandMemberObject(final byte[] key, final int count, final TypeReference<V> type){
        return returnObjectValueFromListByte(sRandMember(key, count), type);
    }

    @Override
    public <V> List<V> sRandMemberObject(final String key, final long count, final TypeReference<V> type){
        return returnObjectValueFromListString(sRandMember(key, count), type);
    }

    @Override
    public <V> List<V> sRandMemberObject(final byte[] key, final long count, final TypeReference<V> type){
        return returnObjectValueFromListByte(sRandMember(key, count), type);
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
        return sRem(key, serializer.encode(member));
    }

    @Override
    public <V> Long sRem(final byte[] key, final V member){
        return sRem(key, serializer.encodeAsBytes(member));
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
        return returnObjectValueFromSetString(sDiff(key));
    }

    @Override
    public <V> Set<V> sDiffObject(final byte[] key){
        return returnObjectValueFromSetByte(sDiff(key));
    }

    @Override
    public <V> Set<V> sDiffObject(final String key, final Class<V> clazz){
        return returnObjectValueFromSetString(sDiff(key), clazz);
    }

    @Override
    public <V> Set<V> sDiffObject(final byte[] key, final Class<V> clazz){
        return returnObjectValueFromSetByte(sDiff(key), clazz);
    }

    @Override
    public <V> Set<V> sDiffObject(final String key, final TypeReference<V> type){
        return returnObjectValueFromSetString(sDiff(key), type);
    }

    @Override
    public <V> Set<V> sDiffObject(final byte[] key, final TypeReference<V> type){
        return returnObjectValueFromSetByte(sDiff(key), type);
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
        final List<KeyValue<String, Number>> members = new ArrayList<>();

        members.add(member);

        return zAdd(key, members);
    }

    @Override
    public Long zAdd(final byte[] key, final KeyValue<byte[], Number> member){
        final List<KeyValue<byte[], Number>> members = new ArrayList<>();

        members.add(member);

        return zAdd(key, members);
    }

    @Override
    public Double zIncr(final String key, final String member){
        return null;
    }

    @Override
    public Double zIncr(final byte[] key, final byte[] member){
        return null;
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

    @Override
    public Geo geoPos(final String key, final String member){
        List<Geo> result = geoPos(key, new String[]{member});
        return Validate.isEmpty(result) ? null : result.get(0);
    }

    @Override
    public Geo geoPos(final byte[] key, final byte[] member){
        List<Geo> result = geoPos(key, new byte[][]{member});
        return Validate.isEmpty(result) ? null : result.get(0);
    }

    @Override
    public String geoHash(final String key, final String member){
        List<String> result = geoHash(key, new String[]{member});
        return Validate.isEmpty(result) ? null : result.get(0);
    }

    @Override
    public byte[] geoHash(final byte[] key, final byte[] member){
        List<byte[]> result = geoHash(key, new byte[][]{member});
        return Validate.isEmpty(result) ? null : result.get(0);
    }

    @Override
    public Long bitOp(final Operation operation, final String destKey, final String key){
        return bitOp(operation, destKey, new String[]{key});
    }

    @Override
    public Long bitOp(final Operation operation, final byte[] destKey, final byte[] key){
        return bitOp(operation, destKey, new byte[][]{key});
    }

    @Override
    public Status watch(final String key){
        return watch(new String[]{key});
    }

    @Override
    public Status watch(final byte[] key){
        return watch(new byte[][]{key});
    }

    @Override
    public void subscribe(final String channel, final PubSubListener<String> pubSubListener){
        subscribe(new String[]{channel}, pubSubListener);
    }

    @Override
    public void subscribe(final byte[] channel, final PubSubListener<byte[]> pubSubListener){
        subscribe(new byte[][]{channel}, pubSubListener);
    }

    @Override
    public void pSubscribe(final String pattern, final PubSubListener<String> pubSubListener){
        pSubscribe(new String[]{pattern}, pubSubListener);
    }

    @Override
    public void pSubscribe(final byte[] pattern, final PubSubListener<byte[]> pubSubListener){
        pSubscribe(new byte[][]{pattern}, pubSubListener);
    }

    @Override
    public Object unSubscribe(final String channel){
        return unSubscribe(new String[]{channel});
    }

    @Override
    public Object unSubscribe(final byte[] channel){
        return unSubscribe(new byte[][]{channel});
    }

    @Override
    public Object pUnSubscribe(final String pattern){
        return pUnSubscribe(new String[]{pattern});
    }

    @Override
    public Object pUnSubscribe(final byte[] pattern){
        return pUnSubscribe(new byte[][]{pattern});
    }

    @Override
    public Object eval(final String script, final String param){
        return eval(script, new String[]{param});
    }

    @Override
    public Object eval(final byte[] script, final byte[] param){
        return eval(script, new byte[][]{param});
    }

    @Override
    public Object evalSha(final String digest, final String param){
        return evalSha(digest, new String[]{param});
    }

    @Override
    public Object evalSha(final byte[] digest, final byte[] param){
        return evalSha(digest, new byte[][]{param});
    }

    @Override
    public Boolean scriptExists(final String sha1){
        final List<Boolean> result = scriptExists(new String[]{sha1});
        return Validate.isEmpty(result) ? false : result.get(0);
    }

    @Override
    public Boolean scriptExists(final byte[] sha1){
        final List<Boolean> result = scriptExists(new byte[][]{sha1});
        return Validate.isEmpty(result) ? false : result.get(0);
    }

}
