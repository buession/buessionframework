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
package com.buession.redis.client.jedis;

import com.buession.core.Geo;
import com.buession.core.Status;
import com.buession.core.utils.ArrayUtils;
import com.buession.redis.client.AbstractRedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.Executor;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.jedis.GeoArgumentConvert;
import com.buession.redis.core.convert.jedis.GeoConvert;
import com.buession.redis.core.convert.jedis.GeoRadiusConvert;
import com.buession.redis.core.convert.jedis.GeoUnitConvert;
import com.buession.redis.core.convert.jedis.ListPositionConvert;
import com.buession.redis.core.convert.jedis.MapNumberConvert;
import com.buession.redis.core.convert.jedis.ScanResultConvert;
import com.buession.redis.core.convert.jedis.SetArgumentConvert;
import com.buession.redis.core.convert.jedis.SortArgumentConvert;
import com.buession.redis.core.convert.jedis.TupleConvert;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.core.types.KeyValue;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.RedisException;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.util.SafeEncoder;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisClient<T extends JedisCommands> extends AbstractRedisClient implements
        JedisRedisClient<T> {

    public AbstractJedisRedisClient(){
        super();
    }

    public AbstractJedisRedisClient(RedisConnection connection){
        super(connection);
    }

    @Override
    public boolean exists(final String key){
        return execute(ProtocolCommand.EXISTS, new Executor<T, Boolean>() {

            @Override
            public Boolean execute(T client){
                return client.exists(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Type type(final String key){
        return execute(ProtocolCommand.TYPE, new Executor<T, Type>() {

            @Override
            public Type execute(T client){
                return returnEnum(client.type(key), Type.class);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Status expire(final String key, final int lifetime){
        return execute(ProtocolCommand.EXPIRE, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnStatus(client.expire(key, lifetime) == 1);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("lifetime", lifetime));
    }

    @Override
    public Status expireAt(final String key, final long unixTimestamp){
        return execute(ProtocolCommand.EXPIREAT, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnStatus(client.expireAt(key, unixTimestamp) == 1);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("unixTimestamp", unixTimestamp));
    }

    @Override
    public Status pExpire(final String key, final int lifetime){
        return execute(ProtocolCommand.PEXPIRE, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnStatus(client.pexpire(key, lifetime) == 1);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("lifetime", lifetime));
    }

    @Override
    public Status pExpireAt(final String key, final long unixTimestamp){
        return execute(ProtocolCommand.PEXPIREAT, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnStatus(client.pexpireAt(key, unixTimestamp) == 1);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("unixTimestamp", unixTimestamp));
    }

    @Override
    public Long ttl(final String key){
        return execute(ProtocolCommand.TTL, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.ttl(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long pTtl(final String key){
        return execute(ProtocolCommand.PTTL, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.pttl(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Status persist(final String key){
        return execute(ProtocolCommand.PERSIST, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnStatus(client.persist(key) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public List<String> sort(final String key){
        return execute(ProtocolCommand.SORT, new Executor<T, List<String>>() {

            @Override
            public List<String> execute(T client){
                return client.sort(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public List<String> sort(final String key, final SortArgument sortArgument){
        return execute(ProtocolCommand.SORT, new Executor<T, List<String>>() {

            @Override
            public List<String> execute(T client){
                final SortArgumentConvert convert = new SortArgumentConvert();
                return client.sort(key, convert.convert(sortArgument));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("sortArgument", sortArgument));
    }

    @Override
    public byte[] dump(final String key){
        return execute(ProtocolCommand.DUMP, new Executor<T, byte[]>() {

            @Override
            public byte[] execute(T client){
                return client.dump(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Status move(final String key, final int db){
        return execute(ProtocolCommand.MOVE, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnStatus(client.move(key, db) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("db", db));
    }

    @Override
    public Status restore(final String key, final String serializedValue, final int ttl){
        return execute(ProtocolCommand.RESTORE, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnForOK(client.restore(key, ttl, SafeEncoder.encode(serializedValue)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("serializedValue", serializedValue).put
                ("ttl", ttl));
    }

    @Override
    public Status set(final String key, final String value){
        return execute(ProtocolCommand.SET, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnForOK(client.set(key, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Status set(final String key, final String value, final SetArgument setArgument){
        return execute(ProtocolCommand.SET, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                final SetArgumentConvert convert = new SetArgumentConvert();
                return returnForOK(client.set(key, value, convert.convert(setArgument)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value).put("setArgument",
                setArgument));
    }

    @Override
    public Status setEx(final String key, final String value, final int lifetime){
        return execute(ProtocolCommand.SETEX, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnForOK(client.setex(key, lifetime, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value).put("lifetime", lifetime));
    }

    @Override
    public Status pSetEx(final String key, final String value, final int lifetime){
        return execute(ProtocolCommand.PSETEX, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnForOK(client.psetex(key, lifetime, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value).put("lifetime", lifetime));
    }

    @Override
    public Status setNx(final String key, final String value){
        return execute(ProtocolCommand.SETNX, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnStatus(client.setnx(key, value) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long append(final String key, final String value){
        return execute(ProtocolCommand.APPEND, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.append(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public String get(final String key){
        return execute(ProtocolCommand.GET, new Executor<T, String>() {

            @Override
            public String execute(T client){
                return client.get(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public String getSet(final String key, final String value){
        return execute(ProtocolCommand.GETSET, new Executor<T, String>() {

            @Override
            public String execute(T client){
                return client.getSet(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long incr(final String key){
        return execute(ProtocolCommand.INCR, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.incr(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long incrBy(final String key, final int value){
        return execute(ProtocolCommand.INCRBY, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.incrBy(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long incrBy(final String key, final long value){
        return execute(ProtocolCommand.INCRBY, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.incrBy(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Double incrByFloat(final String key, final float value){
        return execute(ProtocolCommand.INCRBYFLOAT, new Executor<T, Double>() {

            @Override
            public Double execute(T client){
                return client.incrByFloat(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Double incrByFloat(String key, double value){
        return execute(ProtocolCommand.INCRBYFLOAT, new Executor<T, Double>() {

            @Override
            public Double execute(T client){
                return client.incrByFloat(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long decr(final String key){
        return execute(ProtocolCommand.DECR, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.decr(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long decrBy(final String key, final int value){
        return execute(ProtocolCommand.DECRBY, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.decrBy(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long decrBy(final String key, final long value){
        return execute(ProtocolCommand.DECRBY, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.decrBy(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long setRange(final String key, final long offset, final String value){
        return execute(ProtocolCommand.SETRANGE, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.setrange(key, offset, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value));
    }

    @Override
    public String getRange(final String key, final long start, final long end){
        return execute(ProtocolCommand.GETRANGE, new Executor<T, String>() {

            @Override
            public String execute(T client){
                return client.getrange(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public String substr(final String key, final int start, final int end){
        return execute(ProtocolCommand.SUBSTR, new Executor<T, String>() {

            @Override
            public String execute(T client){
                return client.substr(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Long strlen(final String key){
        return execute(ProtocolCommand.STRLEN, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.strlen(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public boolean hExists(final String key, final String field){
        return execute(ProtocolCommand.HEXISTS, new Executor<T, Boolean>() {

            @Override
            public Boolean execute(T client){
                return client.hexists(key, field);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field));
    }

    @Override
    public Set<String> hKeys(final String key){
        return execute(ProtocolCommand.HKEYS, new Executor<T, Set<String>>() {

            @Override
            public Set<String> execute(T client){
                return client.hkeys(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public List<String> hVals(final String key){
        return execute(ProtocolCommand.HVALS, new Executor<T, List<String>>() {

            @Override
            public List<String> execute(T client){
                return client.hvals(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Status hSet(final String key, final String field, final String value){
        return execute(ProtocolCommand.HSET, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnStatus(client.hset(key, field, value) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field).put("value", value));
    }

    @Override
    public Status hSetNx(final String key, final String field, final String value){
        return execute(ProtocolCommand.HSETNX, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnStatus(client.hsetnx(key, field, value) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field).put("value", value));
    }

    @Override
    public String hGet(final String key, final String field){
        return execute(ProtocolCommand.HGET, new Executor<T, String>() {

            @Override
            public String execute(T client){
                return client.hget(key, field);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field));
    }

    @Override
    public Status hMSet(final String key, final Map<String, String> data){
        return execute(ProtocolCommand.HMSET, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnForOK(client.hmset(key, data));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("data", data));
    }

    @Override
    public List<String> hMGet(final String key, final String... fields){
        return execute(ProtocolCommand.HMGET, new Executor<T, List<String>>() {

            @Override
            public List<String> execute(T client){
                return client.hmget(key, fields);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("fields", ArrayUtils.toString(fields)));
    }

    @Override
    public Map<String, String> hGetAll(final String key){
        return execute(ProtocolCommand.HGETALL, new Executor<T, Map<String, String>>() {

            @Override
            public Map<String, String> execute(T client){
                return client.hgetAll(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long hDel(final String key, final String... fields){
        return execute(ProtocolCommand.HDEL, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.hdel(key, fields);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("fields", ArrayUtils.toString(fields)));
    }

    @Override
    public Long hStrLen(final String key, final String field){
        return execute(ProtocolCommand.HSTRLEN, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.hstrlen(key, field);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field));
    }

    @Override
    public Long hLen(final String key){
        return execute(ProtocolCommand.HLEN, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.hlen(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long hIncrBy(String key, String field, long value){
        return execute(ProtocolCommand.HINCRBY, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.hincrBy(key, field, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field).put("value", value));
    }

    @Override
    public Double hIncrByFloat(final String key, final String field, final double value){
        return execute(ProtocolCommand.HINCRBYFLOAT, new Executor<T, Double>() {

            @Override
            public Double execute(T client){
                return client.hincrByFloat(key, field, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field).put("value", value));
    }

    @Override
    public ScanResult<Map<String, String>> hScan(final String key, final String cursor){
        return execute(ProtocolCommand.HSCAN, new Executor<T, ScanResult<Map<String, String>>>() {

            @Override
            public ScanResult<Map<String, String>> execute(T client){
                final ScanResultConvert.MapScanResultConvert<String, String> convert = new ScanResultConvert
                        .MapScanResultConvert<>();
                return convert.deconvert(client.hscan(key, cursor));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor));
    }

    @Override
    public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern){
        return execute(ProtocolCommand.HSCAN, new Executor<T, ScanResult<Map<String, String>>>() {

            @Override
            public ScanResult<Map<String, String>> execute(T client){
                final ScanParams scanParams = new ScanParams();
                final ScanResultConvert.MapScanResultConvert<String, String> convert = new ScanResultConvert
                        .MapScanResultConvert<>();

                return convert.deconvert(client.hscan(key, cursor, scanParams.match(pattern)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern));
    }

    @Override
    public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final int count){
        return execute(ProtocolCommand.HSCAN, new Executor<T, ScanResult<Map<String, String>>>() {

            @Override
            public ScanResult<Map<String, String>> execute(T client){
                final ScanParams scanParams = new ScanParams();
                final ScanResultConvert.MapScanResultConvert<String, String> convert = new ScanResultConvert
                        .MapScanResultConvert<>();

                return convert.deconvert(client.hscan(key, cursor, scanParams.count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count));
    }

    @Override
    public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern, final
    int count){
        return execute(ProtocolCommand.HSCAN, new Executor<T, ScanResult<Map<String, String>>>() {

            @Override
            public ScanResult<Map<String, String>> execute(T client){
                final ScanResultConvert.MapScanResultConvert<String, String> convert = new ScanResultConvert
                        .MapScanResultConvert<>();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.hscan(key, cursor, scanParams.match(pattern).count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put
                ("count", count));
    }

    @Override
    public Long lPush(final String key, final String... values){
        return execute(ProtocolCommand.LPUSH, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.lpush(key, values);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("values", ArrayUtils.toString(values)));
    }

    @Override
    public Long lPushX(final String key, final String... values){
        return execute(ProtocolCommand.LPUSHX, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.lpushx(key, values);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("values", ArrayUtils.toString(values)));
    }

    @Override
    public Long lInsert(final String key, final String value, final ListPosition position, final String pivot){
        return execute(ProtocolCommand.LINSERT, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                final ListPositionConvert convert = new ListPositionConvert();
                return client.linsert(key, convert.convert(position), pivot, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("position", position).put("pivot", pivot).put
                ("value", value));
    }

    @Override
    public Status lSet(final String key, final long index, final String value){
        return execute(ProtocolCommand.LSET, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnForOK(client.lset(key, index, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("index", index).put("value", value));
    }

    @Override
    public String lIndex(final String key, final long index){
        return execute(ProtocolCommand.LINDEX, new Executor<T, String>() {

            @Override
            public String execute(T client){
                return client.lindex(key, index);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("index", index));
    }

    @Override
    public String lPop(final String key){
        return execute(ProtocolCommand.LPOP, new Executor<T, String>() {

            @Override
            public String execute(T client){
                return client.lpop(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public String rPop(final String key){
        return execute(ProtocolCommand.RPOP, new Executor<T, String>() {

            @Override
            public String execute(T client){
                return client.rpop(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long rPush(final String key, final String... values){
        return execute(ProtocolCommand.RPUSH, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.rpush(key, values);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("values", ArrayUtils.toString(values)));
    }

    @Override
    public Long rPushX(final String key, final String... values){
        return execute(ProtocolCommand.RPUSHX, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.rpushx(key, values);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("values", ArrayUtils.toString(values)));
    }

    @Override
    public Status lTrim(final String key, final long start, final long end){
        return execute(ProtocolCommand.LTRIM, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnForOK(client.ltrim(key, start, end));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Long lRem(final String key, final String value, final long count){
        return execute(ProtocolCommand.LREM, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.lrem(key, count, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value).put("count", count));
    }

    @Override
    public List<String> lRange(final String key, final long start, final long end){
        return execute(ProtocolCommand.LRANGE, new Executor<T, List<String>>() {

            @Override
            public List<String> execute(T client){
                return client.lrange(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Long lLen(final String key){
        return execute(ProtocolCommand.LLEN, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.llen(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long sAdd(final String key, final String... members){
        return execute(ProtocolCommand.SADD, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.sadd(key, members);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", ArrayUtils.toString(members)));
    }

    @Override
    public Long sCard(final String key){
        return execute(ProtocolCommand.SCARD, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.scard(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public boolean sisMember(final String key, final String member){
        return execute(ProtocolCommand.SISMEMBER, new Executor<T, Boolean>() {

            @Override
            public Boolean execute(T client){
                return client.sismember(key, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member));
    }

    @Override
    public Set<String> sMembers(final String key){
        return execute(ProtocolCommand.SMEMBERS, new Executor<T, Set<String>>() {

            @Override
            public Set<String> execute(T client){
                return client.smembers(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public String sPop(final String key){
        return execute(ProtocolCommand.SPOP, new Executor<T, String>() {

            @Override
            public String execute(T client){
                return client.spop(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public String sRandMember(final String key){
        return execute(ProtocolCommand.SRANDMEMBER, new Executor<T, String>() {

            @Override
            public String execute(T client){
                return client.srandmember(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public List<String> sRandMember(final String key, final int count){
        return execute(ProtocolCommand.SRANDMEMBER, new Executor<T, List<String>>() {

            @Override
            public List<String> execute(T client){
                return client.srandmember(key, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("count", count));
    }

    @Override
    public Long sRem(final String key, final String... members){
        return execute(ProtocolCommand.SREM, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.srem(key, members);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", ArrayUtils.toString(members)));
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final String cursor){
        return execute(ProtocolCommand.SREM, new Executor<T, ScanResult<List<String>>>() {

            @Override
            public ScanResult<List<String>> execute(T client){
                final ScanResultConvert.ListScanResultConvert<String> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                return convert.deconvert(client.sscan(key, cursor));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor));
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
        return execute(ProtocolCommand.SSCAN, new Executor<T, ScanResult<List<String>>>() {

            @Override
            public ScanResult<List<String>> execute(T client){
                final ScanResultConvert.ListScanResultConvert<String> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.sscan(key, cursor, scanParams.match(pattern)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern));
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final String cursor, final int count){
        return execute(ProtocolCommand.SSCAN, new Executor<T, ScanResult<List<String>>>() {

            @Override
            public ScanResult<List<String>> execute(T client){
                final ScanResultConvert.ListScanResultConvert<String> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.sscan(key, cursor, scanParams.count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count));
    }

    @Override
    public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern, final int count){
        return execute(ProtocolCommand.SSCAN, new Executor<T, ScanResult<List<String>>>() {

            @Override
            public ScanResult<List<String>> execute(T client){
                final ScanResultConvert.ListScanResultConvert<String> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.sscan(key, cursor, scanParams.match(pattern).count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put
                ("count", count));
    }

    @Override
    public Long zAdd(final String key, final Map<String, Number> members){
        return execute(ProtocolCommand.ZADD, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                final MapNumberConvert.MapNumberDoubleConvert<String> convert = new MapNumberConvert
                        .MapNumberDoubleConvert<>();
                return client.zadd(key, convert.convert(members));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", members));
    }

    @Override
    public Long zAdd(final String key, final List<KeyValue<String, Number>> members){
        Map<String, Number> data = new LinkedHashMap<>(members == null ? 0 : members.size());

        if(members != null){
            for(KeyValue<String, Number> member : members){
                data.put(member.getKey(), member.getValue());
            }
        }

        return zAdd(key, data);
    }

    @Override
    public Long zAdd(final byte[] key, final List<KeyValue<byte[], Number>> members){
        Map<byte[], Number> data = new LinkedHashMap<>(members == null ? 0 : members.size());

        if(members != null){
            for(KeyValue<byte[], Number> member : members){
                data.put(member.getKey(), member.getValue());
            }
        }

        return zAdd(key, data);
    }

    @Override
    public Double zScore(final String key, final String member){
        return execute(ProtocolCommand.ZSCORE, new Executor<T, Double>() {

            @Override
            public Double execute(T client){
                return client.zscore(key, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member));
    }

    @Override
    public Long zCard(final String key){
        return execute(ProtocolCommand.ZCARD, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.zcard(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Double zIncrBy(final String key, final String member, final double increment){
        return execute(ProtocolCommand.ZINCRBY, new Executor<T, Double>() {

            @Override
            public Double execute(T client){
                return client.zincrby(key, increment, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment));
    }

    @Override
    public Long zCount(final String key, final double min, final double max){
        return execute(ProtocolCommand.ZCOUNT, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.zcount(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<String> zRange(final String key, final long start, final long end){
        return execute(ProtocolCommand.ZRANGE, new Executor<T, Set<String>>() {

            @Override
            public Set<String> execute(T client){
                return client.zrange(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Set<Tuple> zRangeWithScores(final String key, final long start, final long end){
        return execute(ProtocolCommand.ZRANGE, new Executor<T, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(T client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrangeWithScores(key, start, end));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Set<String> zRangeByScore(final String key, final double min, final double max){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<T, Set<String>>() {

            @Override
            public Set<String> execute(T client){
                return client.zrangeByScore(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<String> zRangeByScore(final String key, final String min, final String max){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<T, Set<String>>() {

            @Override
            public Set<String> execute(T client){
                return client.zrangeByScore(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<String> zRangeByScore(final String key, final double min, final double max, final int offset, final
    int count){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<T, Set<String>>() {

            @Override
            public Set<String> execute(T client){
                return client.zrangeByScore(key, min, max, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<String> zRangeByScore(final String key, final String min, final String max, final int offset, final
    int count){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<T, Set<String>>() {

            @Override
            public Set<String> execute(T client){
                return client.zrangeByScore(key, min, max, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<T, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(T client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrangeByScoreWithScores(key, min, max));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<T, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(T client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrangeByScoreWithScores(key, min, max));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(String key, double min, double max, int offset, int count){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<T, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(T client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrangeByScoreWithScores(key, min, max, offset, count));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final int offset,
                                              final int count){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<T, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(T client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrangeByScoreWithScores(key, min, max, offset, count));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<String> zRangeByLex(final String key, final String min, final String max){
        return execute(ProtocolCommand.ZRANGEBYLEX, new Executor<T, Set<String>>() {

            @Override
            public Set<String> execute(T client){
                return client.zrangeByLex(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<String> zRangeByLex(final String key, final String min, final String max, final int offset, final int
            count){
        return execute(ProtocolCommand.ZRANGEBYLEX, new Executor<T, Set<String>>() {

            @Override
            public Set<String> execute(T client){
                return client.zrangeByLex(key, min, max, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Long zRank(final String key, final String member){
        return execute(ProtocolCommand.ZRANK, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.zrank(key, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member));
    }

    @Override
    public Long zRevRank(final String key, final String member){
        return execute(ProtocolCommand.ZREVRANK, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.zrevrank(key, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member));
    }

    @Override
    public Long zRem(final String key, final String... members){
        return execute(ProtocolCommand.ZREM, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.zrem(key, members);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", ArrayUtils.toString(members)));
    }

    @Override
    public Long zRemRangeByRank(final String key, final long start, final long end){
        return execute(ProtocolCommand.ZREMRANGEBYRANK, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.zremrangeByRank(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Long zRemRangeByScore(final String key, final double min, final double max){
        return execute(ProtocolCommand.ZREMRANGEBYSCORE, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.zremrangeByScore(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Long zRemRangeByScore(final String key, final String min, final String max){
        return execute(ProtocolCommand.ZREMRANGEBYSCORE, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.zremrangeByScore(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Long zRemRangeByLex(final String key, final String min, final String max){
        return execute(ProtocolCommand.ZREMRANGEBYLEX, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.zremrangeByLex(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<String> zRevRange(final String key, final long start, final long end){
        return execute(ProtocolCommand.ZREVRANGE, new Executor<T, Set<String>>() {

            @Override
            public Set<String> execute(T client){
                return client.zrevrange(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Set<Tuple> zRevRangeWithScores(final String key, final long start, final long end){
        return execute(ProtocolCommand.ZREVRANGE, new Executor<T, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(T client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrevrangeWithScores(key, start, end));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Set<String> zRevRangeByScore(final String key, final double max, final double min){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<T, Set<String>>() {

            @Override
            public Set<String> execute(T client){
                return client.zrevrangeByScore(key, max, min);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("max", max).put("min", min));
    }

    @Override
    public Set<String> zRevRangeByScore(final String key, final String max, final String min){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<T, Set<String>>() {

            @Override
            public Set<String> execute(T client){
                return client.zrevrangeByScore(key, max, min);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("max", max).put("min", min));
    }

    @Override
    public Set<String> zRevRangeByScore(final String key, final double max, final double min, final int offset, final
    int count){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<T, Set<String>>() {

            @Override
            public Set<String> execute(T client){
                return client.zrevrangeByScore(key, max, min, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("max", max).put("min", min).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<String> zRevRangeByScore(final String key, final String min, final String max, final int offset, final
    int count){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<T, Set<String>>() {

            @Override
            public Set<String> execute(T client){
                return client.zrevrangeByScore(key, max, min, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<T, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(T client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrevrangeByScoreWithScores(key, max, min));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<T, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(T client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrevrangeByScoreWithScores(key, max, min));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max, final int
            offset, final int count){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<T, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(T client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrevrangeByScoreWithScores(key, max, min, offset, count));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("max", max).put("min", min).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max, final int
            offset, final int count){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<T, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(T client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrevrangeByScoreWithScores(key, max, min, offset, count));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("max", max).put("min", min).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<String> zRevRangeByLex(final String key, final String min, final String max){
        return execute(ProtocolCommand.ZREVRANGEBYLEX, new Executor<T, Set<String>>() {

            @Override
            public Set<String> execute(T client){
                return client.zrevrangeByLex(key, max, min);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<String> zRevRangeByLex(final String key, final String min, final String max, final int offset, final
    int count){
        return execute(ProtocolCommand.ZREVRANGEBYLEX, new Executor<T, Set<String>>() {

            @Override
            public Set<String> execute(T client){
                return client.zrevrangeByLex(key, max, min, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Long zLexCount(final String key, final String min, final String max){
        return execute(ProtocolCommand.ZLEXCOUNT, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.zlexcount(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final String key, final String cursor){
        return execute(ProtocolCommand.ZSCAN, new Executor<T, ScanResult<List<Tuple>>>() {

            @Override
            public ScanResult<List<Tuple>> execute(T client){
                final ScanResultConvert.ListTupleScanResultConvert convert = new ScanResultConvert
                        .ListTupleScanResultConvert();
                return convert.deconvert(client.zscan(key, cursor));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor));
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern){
        return execute(ProtocolCommand.ZSCAN, new Executor<T, ScanResult<List<Tuple>>>() {

            @Override
            public ScanResult<List<Tuple>> execute(T client){
                final ScanResultConvert.ListTupleScanResultConvert convert = new ScanResultConvert
                        .ListTupleScanResultConvert();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.zscan(key, cursor, scanParams.match(pattern)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern));
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final int count){
        return execute(ProtocolCommand.ZSCAN, new Executor<T, ScanResult<List<Tuple>>>() {

            @Override
            public ScanResult<List<Tuple>> execute(T client){
                final ScanResultConvert.ListTupleScanResultConvert convert = new ScanResultConvert
                        .ListTupleScanResultConvert();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.zscan(key, cursor, scanParams.count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count));
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final int count){
        return execute(ProtocolCommand.ZSCAN, new Executor<T, ScanResult<List<Tuple>>>() {

            @Override
            public ScanResult<List<Tuple>> execute(T client){
                final ScanResultConvert.ListTupleScanResultConvert convert = new ScanResultConvert
                        .ListTupleScanResultConvert();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.zscan(key, cursor, scanParams.match(pattern).count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put
                ("count", count));
    }

    @Override
    public Status pfAdd(final String key, final String... elements){
        return execute(ProtocolCommand.PFADD, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnStatus(client.pfadd(key, elements) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("elements", ArrayUtils.toString(elements)));
    }

    @Override
    public Long geoAdd(final String key, final String member, final double longitude, final double latitude){
        return execute(ProtocolCommand.GEOADD, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.geoadd(key, longitude, latitude, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("longitude", longitude)
                .put("latitude", latitude));
    }

    @Override
    public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates){
        return execute(ProtocolCommand.GEOADD, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                final GeoConvert.GeoMapConvert<String> convert = new GeoConvert.GeoMapConvert<>();
                return client.geoadd(key, convert.convert(memberCoordinates));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("memberCoordinates", memberCoordinates));
    }

    @Override
    public List<Geo> geoPos(final String key, final String... members){
        return execute(ProtocolCommand.GEOPOS, new Executor<T, List<Geo>>() {

            @Override
            public List<Geo> execute(T client){
                final GeoConvert.ListMapConvert convert = new GeoConvert.ListMapConvert();
                return convert.deconvert(client.geopos(key, members));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", ArrayUtils.toString(members)));
    }

    @Override
    public Double geoDist(final String key, final String member1, final String member2){
        return execute(ProtocolCommand.GEODIST, new Executor<T, Double>() {

            @Override
            public Double execute(T client){
                return client.geodist(key, member1, member2);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member1", member1).put("member2", member2));
    }

    @Override
    public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit){
        return execute(ProtocolCommand.GEODIST, new Executor<T, Double>() {

            @Override
            public Double execute(T client){
                final GeoUnitConvert convert = new GeoUnitConvert();
                return client.geodist(key, member1, member2, convert.convert(unit));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member1", member1).put("member2", member2)
                .put("unit", unit));
    }

    @Override
    public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude, final double
            radius, final GeoUnit unit){
        return execute(ProtocolCommand.GEORADIUS, new Executor<T, List<GeoRadius>>() {

            @Override
            public List<GeoRadius> execute(T client){
                final GeoUnitConvert geoUnitConvert = new GeoUnitConvert();
                final GeoRadiusConvert.ListGeoRadiusConvert geoRadiusConvert = new GeoRadiusConvert
                        .ListGeoRadiusConvert();

                return geoRadiusConvert.deconvert(client.georadius(key, longitude, latitude, radius, geoUnitConvert
                        .convert(unit)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("longitude", longitude).put("latitude",
                latitude).put("radius", radius).put("unit", unit));
    }

    @Override
    public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude, final double
            radius, final GeoUnit unit, GeoArgument geoArgument){
        return execute(ProtocolCommand.GEORADIUS, new Executor<T, List<GeoRadius>>() {

            @Override
            public List<GeoRadius> execute(T client){
                final GeoUnitConvert geoUnitConvert = new GeoUnitConvert();
                final GeoArgumentConvert geoArgumentConvert = new GeoArgumentConvert();
                final GeoRadiusConvert.ListGeoRadiusConvert geoRadiusConvert = new GeoRadiusConvert
                        .ListGeoRadiusConvert();

                return geoRadiusConvert.deconvert(client.georadius(key, longitude, latitude, radius, geoUnitConvert
                        .convert(unit), geoArgumentConvert.convert(geoArgument)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("longitude", longitude).put("latitude",
                latitude).put("radius", radius).put("unit", unit).put("geoArgument", geoArgument));
    }

    @Override
    public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius, final
    GeoUnit unit){
        return execute(ProtocolCommand.GEORADIUSBYMEMBER, new Executor<T, List<GeoRadius>>() {

            @Override
            public List<GeoRadius> execute(T client){
                final GeoUnitConvert geoUnitConvert = new GeoUnitConvert();
                final GeoRadiusConvert.ListGeoRadiusConvert geoRadiusConvert = new GeoRadiusConvert
                        .ListGeoRadiusConvert();

                return geoRadiusConvert.deconvert(client.georadiusByMember(key, member, radius, geoUnitConvert
                        .convert(unit)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("radius", radius).put
                ("unit", unit));
    }

    @Override
    public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius, final
    GeoUnit unit, final GeoArgument geoArgument){
        return execute(ProtocolCommand.GEORADIUSBYMEMBER, new Executor<T, List<GeoRadius>>() {

            @Override
            public List<GeoRadius> execute(T client){
                final GeoUnitConvert geoUnitConvert = new GeoUnitConvert();
                final GeoArgumentConvert geoArgumentConvert = new GeoArgumentConvert();
                final GeoRadiusConvert.ListGeoRadiusConvert geoRadiusConvert = new GeoRadiusConvert
                        .ListGeoRadiusConvert();

                return geoRadiusConvert.deconvert(client.georadiusByMember(key, member, radius, geoUnitConvert
                        .convert(unit), geoArgumentConvert.convert(geoArgument)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("radius", radius).put
                ("unit", unit).put("geoArgument", geoArgument));
    }

    @Override
    public List<String> geoHash(final String key, final String... members){
        return execute(ProtocolCommand.GEOHASH, new Executor<T, List<String>>() {

            @Override
            public List<String> execute(T client){
                return client.geohash(key, members);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", ArrayUtils.toString(members)));
    }

    @Override
    public Status setBit(final String key, final long offset, final String value){
        return execute(ProtocolCommand.SETBIT, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnStatus(client.setbit(key, offset, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value));
    }

    @Override
    public Status setBit(final String key, final long offset, final boolean value){
        return execute(ProtocolCommand.SETBIT, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnStatus(client.setbit(key, offset, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value));
    }

    @Override
    public Status getBit(final String key, final long offset){
        return execute(ProtocolCommand.GETBIT, new Executor<T, Status>() {

            @Override
            public Status execute(T client){
                return returnStatus(client.getbit(key, offset));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("offset", offset));
    }

    @Override
    public Long bitPos(final String key, final boolean value){
        return execute(ProtocolCommand.BITPOS, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.bitpos(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long bitPos(final String key, final boolean value, final int start, final int end){
        return execute(ProtocolCommand.BITPOS, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.bitpos(key, value, new BitPosParams(start, end));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value).put("start", start).put
                ("end", end));
    }

    @Override
    public List<Long> bitField(final String key, final String... arguments){
        return execute(ProtocolCommand.BITFIELD, new Executor<T, List<Long>>() {

            @Override
            public List<Long> execute(T client){
                return client.bitfield(key, arguments);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("arguments", ArrayUtils.toString(arguments)));
    }

    @Override
    public Long bitCount(final String key){
        return execute(ProtocolCommand.BITCOUNT, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.bitcount(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long bitCount(final String key, final long start, final long end){
        return execute(ProtocolCommand.BITCOUNT, new Executor<T, Long>() {

            @Override
            public Long execute(T client){
                return client.bitcount(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Object unSubscribe(){
        throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
    }

    @Override
    public Object unSubscribe(final String... channels){
        throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
    }

    @Override
    public Object unSubscribe(final byte[]... channels){
        throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
    }

    @Override
    public Object pUnSubscribe(){
        throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
    }

    @Override
    public Object pUnSubscribe(final String... patterns){
        throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
    }

    @Override
    public Object pUnSubscribe(final byte[]... patterns){
        throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
    }

    @Override
    public String echo(final String str){
        return execute(ProtocolCommand.ECHO, new Executor<T, String>() {

            @Override
            public String execute(T client){
                return client.echo(str);
            }

        }, OperationsCommandArguments.getInstance().put("str", str));
    }

    protected <R> R execute(final ProtocolCommand command, final Executor<T, R> executor) throws RedisException{
        return super.doExecute(command, executor);
    }

    protected <R> R execute(final ProtocolCommand command, final Executor<T, R> executor, final
    OperationsCommandArguments arguments){
        return super.doExecute(command, executor, arguments);
    }

}
