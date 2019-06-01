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
import com.buession.core.validator.Validate;
import com.buession.redis.client.ShardedRedisClient;
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
import com.buession.redis.core.convert.jedis.MigrateOperationConvert;
import com.buession.redis.core.convert.jedis.ScanResultConvert;
import com.buession.redis.core.convert.jedis.SetArgumentConvert;
import com.buession.redis.core.convert.jedis.SortArgumentConvert;
import com.buession.redis.core.convert.jedis.TupleConvert;
import com.buession.redis.core.operations.OperationsCommandArguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.util.SafeEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class ShardedJedisClient extends AbstractJedisRedisClient<ShardedJedis> implements ShardedRedisClient {

    private final static Logger logger = LoggerFactory.getLogger(ShardedJedisClient.class);

    public ShardedJedisClient(){
        super();
    }

    public ShardedJedisClient(RedisConnection connection){
        super(connection);
    }

    @Override
    public boolean exists(final byte[] key){
        return execute(ProtocolCommand.EXISTS, new Executor<ShardedJedis, Boolean>() {

            @Override
            public Boolean execute(ShardedJedis client){
                return client.exists(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Type type(final byte[] key){
        return execute(ProtocolCommand.TYPE, new Executor<ShardedJedis, Type>() {

            @Override
            public Type execute(ShardedJedis client){
                return returnEnum(client.type(key), Type.class);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Status expire(final byte[] key, final int lifetime){
        return execute(ProtocolCommand.EXPIRE, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnStatus(client.expire(key, lifetime) == 1);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("lifetime", lifetime));
    }

    @Override
    public Status expireAt(final byte[] key, final long unixTimestamp){
        return execute(ProtocolCommand.EXPIREAT, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnStatus(client.expireAt(key, unixTimestamp) == 1);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("unixTimestamp", unixTimestamp));
    }

    @Override
    public Status pExpire(final byte[] key, final int lifetime){
        return execute(ProtocolCommand.PEXPIRE, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnStatus(client.pexpire(key, lifetime) == 1);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("lifetime", lifetime));
    }

    @Override
    public Status pExpireAt(final byte[] key, final long unixTimestamp){
        return execute(ProtocolCommand.PEXPIREAT, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnStatus(client.pexpireAt(key, unixTimestamp) == 1);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("unixTimestamp", unixTimestamp));
    }

    @Override
    public Long ttl(final byte[] key){
        return execute(ProtocolCommand.TTL, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.ttl(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long pTtl(final byte[] key){
        return execute(ProtocolCommand.PTTL, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.pttl(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Status persist(final byte[] key){
        return execute(ProtocolCommand.PERSIST, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnStatus(client.persist(key) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public List<byte[]> sort(final byte[] key){
        return execute(ProtocolCommand.SORT, new Executor<ShardedJedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(ShardedJedis client){
                return client.sort(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public List<byte[]> sort(final byte[] key, final SortArgument sortArgument){
        return execute(ProtocolCommand.SORT, new Executor<ShardedJedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(ShardedJedis client){
                final SortArgumentConvert convert = new SortArgumentConvert();
                return client.sort(key, convert.convert(sortArgument));
            }
        }, OperationsCommandArguments.getInstance().put("key", key).put("sortArgument", sortArgument));
    }

    @Override
    public byte[] dump(final byte[] key){
        return execute(ProtocolCommand.DUMP, new Executor<ShardedJedis, byte[]>() {

            @Override
            public byte[] execute(ShardedJedis client){
                return client.dump(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
        return execute(ProtocolCommand.RESTORE, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnForOK(client.restore(key, ttl, serializedValue));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("serializedValue", serializedValue).put
                ("ttl", ttl));
    }

    @Override
    public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
        return execute(ProtocolCommand.MIGRATE, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnForOK(getShard(client, key).migrate(host, port, key, db, timeout));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("host", host).put("port", port).put("key",
                key).put("db", db).put("timeout", timeout));
    }

    @Override
    public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
        return execute(ProtocolCommand.MIGRATE, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnForOK(getShard(client, key).migrate(host, port, key, db, timeout));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("host", host).put("port", port).put("key",
                key).put("db", db).put("timeout", timeout));
    }

    @Override
    public Status migrate(final String key, final String host, final int port, final int db, final int timeout, final
    MigrateOperation migrateOperation){
        return execute(ProtocolCommand.MIGRATE, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                final MigrateOperationConvert convert = new MigrateOperationConvert();
                return returnForOK(getShard(client, key).migrate(host, port, db, timeout, convert.convert
                        (migrateOperation), key));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("host", host).put("port", port).put("db", db)
                .put("timeout", timeout).put("migrate", migrateOperation));
    }

    @Override
    public Status migrate(byte[] key, String host, int port, int db, int timeout, MigrateOperation migrateOperation){
        return execute(ProtocolCommand.MIGRATE, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                final MigrateOperationConvert convert = new MigrateOperationConvert();
                return returnForOK(getShard(client, key).migrate(host, port, db, timeout, convert.convert
                        (migrateOperation), key));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("host", host).put("port", port).put("db", db)
                .put("timeout", timeout).put("migrate", migrateOperation));
    }

    @Override
    public Long del(final String... keys){
        return execute(ProtocolCommand.DEL, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                if(keys != null){
                    if(keys.length == 1){
                        return client.del(keys[0]);
                    }else{
                        long result = 0;

                        for(String key : keys){
                            result += client.del(key);
                        }

                        return result;
                    }
                }

                return 0L;
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long del(byte[]... keys){
        return execute(ProtocolCommand.DEL, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                if(keys != null){
                    if(keys.length == 1){
                        return client.del(keys[0]);
                    }else{
                        long result = 0;

                        for(byte[] key : keys){
                            result += client.del(key);
                        }

                        return result;
                    }
                }

                return 0L;
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Status move(final byte[] key, final int db){
        return execute(ProtocolCommand.MOVE, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnStatus(client.move(key, db) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("db", db));
    }

    @Override
    public Status set(final byte[] key, final byte[] value){
        return execute(ProtocolCommand.SET, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnForOK(client.set(key, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Status set(final byte[] key, final byte[] value, final SetArgument setArgument){
        return execute(ProtocolCommand.SET, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                final SetArgumentConvert convert = new SetArgumentConvert();
                return returnForOK(client.set(key, value, convert.convert(setArgument)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value).put("setArgument",
                setArgument));
    }

    @Override
    public Status setEx(final byte[] key, final byte[] value, final int lifetime){
        return execute(ProtocolCommand.SETEX, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnForOK(client.setex(key, lifetime, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value).put("lifetime", lifetime));
    }

    @Override
    public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
        return execute(ProtocolCommand.PSETEX, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnForOK(client.psetex(key, lifetime, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value).put("lifetime", lifetime));
    }

    @Override
    public Status setNx(final byte[] key, final byte[] value){
        return execute(ProtocolCommand.SETNX, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnStatus(client.setnx(key, value) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long append(final byte[] key, final byte[] value){
        return execute(ProtocolCommand.SETNX, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.append(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public byte[] get(final byte[] key){
        return execute(ProtocolCommand.GET, new Executor<ShardedJedis, byte[]>() {

            @Override
            public byte[] execute(ShardedJedis client){
                return client.get(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public byte[] getSet(final byte[] key, final byte[] value){
        return execute(ProtocolCommand.GETSET, new Executor<ShardedJedis, byte[]>() {

            @Override
            public byte[] execute(ShardedJedis client){
                return client.getSet(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Status mSet(final Map<String, String> values){
        return execute(ProtocolCommand.MSET, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                values.forEach((key, value)->{
                    getShard(client, key).set(key, value);
                });

                return Status.SUCCESS;
            }

        }, OperationsCommandArguments.getInstance().put("values", values));
    }

    @Override
    public Status mSetNx(final Map<String, String> values){
        return execute(ProtocolCommand.MSET, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                values.forEach((key, value)->{
                    getShard(client, key).setnx(key, value);
                });

                return Status.SUCCESS;
            }

        }, OperationsCommandArguments.getInstance().put("values", values));
    }

    @Override
    public Long incr(final byte[] key){
        return execute(ProtocolCommand.INCR, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.incr(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long incrBy(final byte[] key, final int value){
        return execute(ProtocolCommand.INCRBY, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.incrBy(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long incrBy(final byte[] key, final long value){
        return execute(ProtocolCommand.INCRBY, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.incrBy(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Double incrByFloat(final byte[] key, final float value){
        return execute(ProtocolCommand.INCRBYFLOAT, new Executor<ShardedJedis, Double>() {

            @Override
            public Double execute(ShardedJedis client){
                return client.incrByFloat(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Double incrByFloat(final byte[] key, final double value){
        return execute(ProtocolCommand.INCRBYFLOAT, new Executor<ShardedJedis, Double>() {

            @Override
            public Double execute(ShardedJedis client){
                return client.incrByFloat(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long decr(final byte[] key){
        return execute(ProtocolCommand.DECR, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.decr(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long decrBy(final byte[] key, final int value){
        return execute(ProtocolCommand.DECRBY, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.decrBy(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long decrBy(final byte[] key, final long value){
        return execute(ProtocolCommand.DECRBY, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.decrBy(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long setRange(final byte[] key, final long offset, final byte[] value){
        return execute(ProtocolCommand.SETRANGE, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.setrange(key, offset, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value));
    }

    @Override
    public byte[] getRange(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.GETRANGE, new Executor<ShardedJedis, byte[]>() {

            @Override
            public byte[] execute(ShardedJedis client){
                return client.getrange(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public byte[] substr(final byte[] key, final int start, final int end){
        return execute(ProtocolCommand.SUBSTR, new Executor<ShardedJedis, byte[]>() {

            @Override
            public byte[] execute(ShardedJedis client){
                return client.substr(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Long strlen(final byte[] key){
        return execute(ProtocolCommand.STRLEN, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.strlen(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public boolean hExists(final byte[] key, final byte[] field){
        return execute(ProtocolCommand.HEXISTS, new Executor<ShardedJedis, Boolean>() {

            @Override
            public Boolean execute(ShardedJedis client){
                return client.hexists(key, field);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field));
    }

    @Override
    public Set<byte[]> hKeys(final byte[] key){
        return execute(ProtocolCommand.HKEYS, new Executor<ShardedJedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(ShardedJedis client){
                return client.hkeys(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public List<byte[]> hVals(final byte[] key){
        return execute(ProtocolCommand.HVALS, new Executor<ShardedJedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(ShardedJedis client){
                Collection<byte[]> result = client.hvals(key);
                return result == null ? null : new ArrayList<>(result);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Status hSet(final byte[] key, final byte[] field, final byte[] value){
        return execute(ProtocolCommand.HMSET, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnStatus(client.hset(key, field, value) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field).put("value", value));
    }

    @Override
    public Status hSetNx(final byte[] key, final byte[] field, final byte[] value){
        return execute(ProtocolCommand.HSETNX, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnStatus(client.hsetnx(key, field, value) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field).put("value", value));
    }

    @Override
    public byte[] hGet(final byte[] key, final byte[] field){
        return execute(ProtocolCommand.HSETNX, new Executor<ShardedJedis, byte[]>() {

            @Override
            public byte[] execute(ShardedJedis client){
                return client.hget(key, field);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field));
    }

    @Override
    public Status hMSet(final byte[] key, final Map<byte[], byte[]> data){
        return execute(ProtocolCommand.HSETNX, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnForOK(client.hmset(key, data));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("data", data));
    }

    @Override
    public List<byte[]> hMGet(final byte[] key, final byte[]... fields){
        return execute(ProtocolCommand.HMGET, new Executor<ShardedJedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(ShardedJedis client){
                return client.hmget(key, fields);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("fields", ArrayUtils.toString(fields)));
    }

    @Override
    public Map<byte[], byte[]> hGetAll(final byte[] key){
        return execute(ProtocolCommand.HGETALL, new Executor<ShardedJedis, Map<byte[], byte[]>>() {

            @Override
            public Map<byte[], byte[]> execute(ShardedJedis client){
                return client.hgetAll(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long hDel(final byte[] key, final byte[]... fields){
        return execute(ProtocolCommand.HDEL, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.hdel(key, fields);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("fields", ArrayUtils.toString(fields)));
    }

    @Override
    public Long hStrLen(final byte[] key, final byte[] field){
        return execute(ProtocolCommand.HSTRLEN, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.hstrlen(key, field);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field));
    }

    @Override
    public Long hLen(final byte[] key){
        return execute(ProtocolCommand.HLEN, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.hlen(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long hIncrBy(final byte[] key, final byte[] field, final long value){
        return execute(ProtocolCommand.HINCRBY, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.hincrBy(key, field, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field).put("value", value));
    }

    @Override
    public Double hIncrByFloat(final byte[] key, final byte[] field, final double value){
        return execute(ProtocolCommand.HINCRBYFLOAT, new Executor<ShardedJedis, Double>() {

            @Override
            public Double execute(ShardedJedis client){
                return client.hincrByFloat(key, field, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field).put("value", value));
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
        return execute(ProtocolCommand.HSCAN, new Executor<ShardedJedis, ScanResult<Map<byte[], byte[]>>>() {

            @Override
            public ScanResult<Map<byte[], byte[]>> execute(ShardedJedis client){
                final ScanResultConvert.MapScanResultConvert<byte[], byte[]> convert = new ScanResultConvert
                        .MapScanResultConvert<>();
                return convert.deconvert(client.hscan(key, cursor));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor));
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern){
        return execute(ProtocolCommand.HSCAN, new Executor<ShardedJedis, ScanResult<Map<byte[], byte[]>>>() {

            @Override
            public ScanResult<Map<byte[], byte[]>> execute(ShardedJedis client){
                final ScanResultConvert.MapScanResultConvert<byte[], byte[]> convert = new ScanResultConvert
                        .MapScanResultConvert<>();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.hscan(key, cursor, scanParams.match(pattern)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern));
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final int count){
        return execute(ProtocolCommand.HSCAN, new Executor<ShardedJedis, ScanResult<Map<byte[], byte[]>>>() {

            @Override
            public ScanResult<Map<byte[], byte[]>> execute(ShardedJedis client){
                final ScanResultConvert.MapScanResultConvert<byte[], byte[]> convert = new ScanResultConvert
                        .MapScanResultConvert<>();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.hscan(key, cursor, scanParams.count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count));
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern, final
    int count){
        return execute(ProtocolCommand.HSCAN, new Executor<ShardedJedis, ScanResult<Map<byte[], byte[]>>>() {

            @Override
            public ScanResult<Map<byte[], byte[]>> execute(ShardedJedis client){
                final ScanResultConvert.MapScanResultConvert<byte[], byte[]> convert = new ScanResultConvert
                        .MapScanResultConvert<>();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.hscan(key, cursor, scanParams.match(pattern).count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put
                ("count", count));
    }

    @Override
    public Long lPush(final byte[] key, final byte[]... values){
        return execute(ProtocolCommand.LPUSH, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.lpush(key, values);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("values", ArrayUtils.toString(values)));
    }

    @Override
    public Long lPushX(final byte[] key, final byte[]... values){
        return execute(ProtocolCommand.LPUSHX, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.lpushx(key, values);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("values", ArrayUtils.toString(values)));
    }

    @Override
    public Long lInsert(final byte[] key, final byte[] value, final ListPosition position, final byte[] pivot){
        return execute(ProtocolCommand.LPUSHX, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                final ListPositionConvert convert = new ListPositionConvert();
                return client.linsert(key, convert.convert(position), pivot, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("position", position).put("pivot", pivot).put
                ("value", value));
    }

    @Override
    public Status lSet(final byte[] key, final long index, final byte[] value){
        return execute(ProtocolCommand.LSET, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnForOK(client.lset(key, index, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("index", index).put("value", value));
    }

    @Override
    public byte[] lIndex(final byte[] key, final long index){
        return execute(ProtocolCommand.LINDEX, new Executor<ShardedJedis, byte[]>() {

            @Override
            public byte[] execute(ShardedJedis client){
                return client.lindex(key, index);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("index", index));
    }

    @Override
    public byte[] lPop(final byte[] key){
        return execute(ProtocolCommand.LPOP, new Executor<ShardedJedis, byte[]>() {

            @Override
            public byte[] execute(ShardedJedis client){
                return client.lpop(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public List<String> blPop(final String[] keys, final int timeout){
        if(keys != null && keys.length > 1){
            logger.warn("{} only blpop the first key.", ShardedJedisClient.class.getName());
        }

        return execute(ProtocolCommand.LPOP, new Executor<ShardedJedis, List<String>>() {

            @Override
            public List<String> execute(ShardedJedis client){
                return Validate.isEmpty(keys) ? null : client.blpop(timeout, keys[0]);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)).put("timeout", timeout));
    }

    @Override
    public List<byte[]> blPop(final byte[][] keys, final int timeout){
        if(keys != null && keys.length > 1){
            logger.warn("{} only blpop the first key.", ShardedJedisClient.class.getName());
        }

        return execute(ProtocolCommand.BLPOP, new Executor<ShardedJedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(ShardedJedis client){
                return Validate.isEmpty(keys) ? null : client.blpop(keys[0]);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)).put("timeout", timeout));
    }

    @Override
    public byte[] rPop(final byte[] key){
        return execute(ProtocolCommand.RPOP, new Executor<ShardedJedis, byte[]>() {

            @Override
            public byte[] execute(ShardedJedis client){
                return client.rpop(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public List<String> brPop(String[] keys, int timeout){
        if(keys != null && keys.length > 1){
            logger.warn("{} only brpop the first key.", ShardedJedisClient.class.getName());
        }

        return execute(ProtocolCommand.BRPOP, new Executor<ShardedJedis, List<String>>() {

            @Override
            public List<String> execute(ShardedJedis client){
                return Validate.isEmpty(keys) ? null : client.brpop(timeout, keys[0]);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)).put("timeout", timeout));
    }

    @Override
    public List<byte[]> brPop(final byte[][] keys, final int timeout){
        if(keys != null && keys.length > 1){
            logger.warn("{} only brpop the first key.", ShardedJedisClient.class.getName());
        }

        return execute(ProtocolCommand.BRPOP, new Executor<ShardedJedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(ShardedJedis client){
                return Validate.isEmpty(keys) ? null : client.brpop(keys[0]);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)).put("timeout", timeout));
    }

    @Override
    public Long rPush(final byte[] key, final byte[]... values){
        return execute(ProtocolCommand.RPUSH, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.rpush(key, values);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("values", ArrayUtils.toString(values)));
    }

    @Override
    public Long rPushX(final byte[] key, final byte[]... values){
        return execute(ProtocolCommand.RPUSHX, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.rpushx(key, values);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("values", ArrayUtils.toString(values)));
    }

    @Override
    public Status lTrim(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.LTRIM, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnForOK(client.ltrim(key, start, end));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Long lRem(final byte[] key, final byte[] value, final long count){
        return execute(ProtocolCommand.LREM, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.lrem(key, count, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value).put("count", count));
    }

    @Override
    public List<byte[]> lRange(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.LRANGE, new Executor<ShardedJedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(ShardedJedis client){
                return client.lrange(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Long lLen(final byte[] key){
        return execute(ProtocolCommand.LLEN, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.llen(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long sAdd(final byte[] key, final byte[]... members){
        return execute(ProtocolCommand.SADD, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.sadd(key, members);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", ArrayUtils.toString(members)));
    }

    @Override
    public Long sCard(final byte[] key){
        return execute(ProtocolCommand.SCARD, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.scard(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public boolean sisMember(final byte[] key, final byte[] member){
        return execute(ProtocolCommand.SISMEMBER, new Executor<ShardedJedis, Boolean>() {

            @Override
            public Boolean execute(ShardedJedis client){
                return client.sismember(key, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member));
    }

    @Override
    public Set<byte[]> sMembers(final byte[] key){
        return execute(ProtocolCommand.SMEMBERS, new Executor<ShardedJedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(ShardedJedis client){
                return client.smembers(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public byte[] sPop(final byte[] key){
        return execute(ProtocolCommand.SPOP, new Executor<ShardedJedis, byte[]>() {

            @Override
            public byte[] execute(ShardedJedis client){
                return client.spop(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public byte[] sRandMember(final byte[] key){
        return execute(ProtocolCommand.SRANDMEMBER, new Executor<ShardedJedis, byte[]>() {

            @Override
            public byte[] execute(ShardedJedis client){
                return client.srandmember(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public List<byte[]> sRandMember(final byte[] key, final int count){
        return execute(ProtocolCommand.SRANDMEMBER, new Executor<ShardedJedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(ShardedJedis client){
                return getShard(client, key).srandmember(key, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("count", count));
    }

    @Override
    public Long sRem(final byte[] key, final byte[]... members){
        return execute(ProtocolCommand.SREM, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.srem(key, members);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", ArrayUtils.toString(members)));
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
        return execute(ProtocolCommand.SSCAN, new Executor<ShardedJedis, ScanResult<List<byte[]>>>() {

            @Override
            public ScanResult<List<byte[]>> execute(ShardedJedis client){
                final ScanResultConvert.ListScanResultConvert<byte[]> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                return convert.deconvert(client.sscan(key, cursor));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor));
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
        return execute(ProtocolCommand.SSCAN, new Executor<ShardedJedis, ScanResult<List<byte[]>>>() {

            @Override
            public ScanResult<List<byte[]>> execute(ShardedJedis client){
                final ScanResultConvert.ListScanResultConvert<byte[]> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.sscan(key, cursor, scanParams.match(pattern)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern));
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count){
        return execute(ProtocolCommand.SSCAN, new Executor<ShardedJedis, ScanResult<List<byte[]>>>() {

            @Override
            public ScanResult<List<byte[]>> execute(ShardedJedis client){
                final ScanResultConvert.ListScanResultConvert<byte[]> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.sscan(key, cursor, scanParams.count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count));
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count){
        return execute(ProtocolCommand.SSCAN, new Executor<ShardedJedis, ScanResult<List<byte[]>>>() {

            @Override
            public ScanResult<List<byte[]>> execute(ShardedJedis client){
                final ScanResultConvert.ListScanResultConvert<byte[]> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.sscan(key, cursor, scanParams.match(pattern).count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put
                ("count", count));
    }

    @Override
    public Long zAdd(final byte[] key, final Map<byte[], Number> members){
        return execute(ProtocolCommand.ZADD, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                final MapNumberConvert.MapNumberDoubleConvert<byte[]> convert = new MapNumberConvert
                        .MapNumberDoubleConvert<>();
                return client.zadd(key, convert.convert(members));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", members));
    }

    @Override
    public Double zScore(final byte[] key, final byte[] member){
        return execute(ProtocolCommand.ZSCORE, new Executor<ShardedJedis, Double>() {

            @Override
            public Double execute(ShardedJedis client){
                return client.zscore(key, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member));
    }

    @Override
    public Long zCard(final byte[] key){
        return execute(ProtocolCommand.ZCARD, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.zcard(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Double zIncrBy(final byte[] key, final byte[] member, final double increment){
        return execute(ProtocolCommand.ZINCRBY, new Executor<ShardedJedis, Double>() {

            @Override
            public Double execute(ShardedJedis client){
                return client.zincrby(key, increment, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment));
    }

    @Override
    public Long zCount(final byte[] key, final double min, final double max){
        return execute(ProtocolCommand.ZCOUNT, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.zcount(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<byte[]> zRange(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.ZRANGE, new Executor<ShardedJedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(ShardedJedis client){
                return client.zrange(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.ZRANGE, new Executor<ShardedJedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(ShardedJedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrangeWithScores(key, start, end));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<ShardedJedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(ShardedJedis client){
                return client.zrangeByScore(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<ShardedJedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(ShardedJedis client){
                return client.zrangeByScore(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset, final
    int count){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<ShardedJedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(ShardedJedis client){
                return client.zrangeByScore(key, min, max, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset, final
    int count){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<ShardedJedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(ShardedJedis client){
                return client.zrangeByScore(key, min, max, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<ShardedJedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(ShardedJedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrangeByScoreWithScores(key, min, max));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<ShardedJedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(ShardedJedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrangeByScoreWithScores(key, min, max));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
                                              final int count){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<ShardedJedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(ShardedJedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrangeByScoreWithScores(key, min, max, offset, count));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int offset,
                                              final int count){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<ShardedJedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(ShardedJedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrangeByScoreWithScores(key, min, max, offset, count));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZRANGEBYLEX, new Executor<ShardedJedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(ShardedJedis client){
                return client.zrangeByLex(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset, final int
            count){
        return execute(ProtocolCommand.ZRANGEBYLEX, new Executor<ShardedJedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(ShardedJedis client){
                return client.zrangeByLex(key, min, max, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Long zRank(final byte[] key, final byte[] member){
        return execute(ProtocolCommand.ZRANK, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.zrank(key, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member));
    }

    @Override
    public Long zRevRank(final byte[] key, final byte[] member){
        return execute(ProtocolCommand.ZREVRANK, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.zrevrank(key, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member));
    }

    @Override
    public Long zRem(final byte[] key, final byte[]... members){
        return execute(ProtocolCommand.ZREM, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.zrem(key, members);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", ArrayUtils.toString(members)));
    }

    @Override
    public Long zRemRangeByRank(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.ZREMRANGEBYRANK, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.zremrangeByRank(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Long zRemRangeByScore(final byte[] key, final double min, final double max){
        return execute(ProtocolCommand.ZREMRANGEBYSCORE, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.zremrangeByScore(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZREMRANGEBYSCORE, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.zremrangeByScore(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZREMRANGEBYLEX, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.zremrangeByLex(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<byte[]> zRevRange(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.ZREVRANGE, new Executor<ShardedJedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(ShardedJedis client){
                return client.zrevrange(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.ZREVRANGE, new Executor<ShardedJedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(ShardedJedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrevrangeWithScores(key, start, end));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<ShardedJedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(ShardedJedis client){
                return client.zrevrangeByScore(key, max, min);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<ShardedJedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(ShardedJedis client){
                return client.zrevrangeByScore(key, max, min);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset, final
    int count){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<ShardedJedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(ShardedJedis client){
                return client.zrevrangeByScore(key, max, min, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset, final
    int count){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<ShardedJedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(ShardedJedis client){
                return client.zrevrangeByScore(key, max, min, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<ShardedJedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(ShardedJedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrevrangeByScoreWithScores(key, max, min));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<ShardedJedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(ShardedJedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrevrangeByScoreWithScores(key, max, min));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max, final int
            offset, final int count){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<ShardedJedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(ShardedJedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrevrangeByScoreWithScores(key, max, min, offset, count));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("max", max).put("min", min).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int
            offset, final int count){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<ShardedJedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(ShardedJedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrevrangeByScoreWithScores(key, max, min, offset, count));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("max", max).put("min", min).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZREVRANGEBYLEX, new Executor<ShardedJedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(ShardedJedis client){
                return client.zrevrangeByLex(key, max, min);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset, final
    int count){
        return execute(ProtocolCommand.ZREVRANGEBYLEX, new Executor<ShardedJedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(ShardedJedis client){
                return client.zrevrangeByLex(key, max, min, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZLEXCOUNT, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.zlexcount(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
        return execute(ProtocolCommand.ZSCAN, new Executor<ShardedJedis, ScanResult<List<Tuple>>>() {

            @Override
            public ScanResult<List<Tuple>> execute(ShardedJedis client){
                final ScanResultConvert.ListTupleScanResultConvert convert = new ScanResultConvert
                        .ListTupleScanResultConvert();
                return convert.deconvert(client.zscan(key, cursor));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor));
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
        return execute(ProtocolCommand.ZSCAN, new Executor<ShardedJedis, ScanResult<List<Tuple>>>() {

            @Override
            public ScanResult<List<Tuple>> execute(ShardedJedis client){
                final ScanResultConvert.ListTupleScanResultConvert convert = new ScanResultConvert
                        .ListTupleScanResultConvert();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.zscan(key, cursor, scanParams.match(pattern)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern));
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count){
        return execute(ProtocolCommand.ZSCAN, new Executor<ShardedJedis, ScanResult<List<Tuple>>>() {

            @Override
            public ScanResult<List<Tuple>> execute(ShardedJedis client){
                final ScanResultConvert.ListTupleScanResultConvert convert = new ScanResultConvert
                        .ListTupleScanResultConvert();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.zscan(key, cursor, scanParams.count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count));
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count){
        return execute(ProtocolCommand.ZSCAN, new Executor<ShardedJedis, ScanResult<List<Tuple>>>() {

            @Override
            public ScanResult<List<Tuple>> execute(ShardedJedis client){
                final ScanResultConvert.ListTupleScanResultConvert convert = new ScanResultConvert
                        .ListTupleScanResultConvert();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.zscan(key, cursor, scanParams.match(pattern).count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put
                ("count", count));
    }

    @Override
    public Status pfAdd(final byte[] key, final byte[]... elements){
        return execute(ProtocolCommand.PFADD, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnStatus(client.pfadd(key, elements) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("elements", ArrayUtils.toString(elements)));
    }

    @Override
    public Long pfCount(final String... keys){
        if(keys != null && keys.length > 1){
            logger.warn("{} only pfcount the first key.", ShardedJedisClient.class.getName());
        }

        return execute(ProtocolCommand.PFCOUNT, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return keys == null ? 0L : client.pfcount(keys[0]);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long pfCount(final byte[]... keys){
        if(keys != null && keys.length > 1){
            logger.warn("{} only pfcount the first key.", ShardedJedisClient.class.getName());
        }

        return execute(ProtocolCommand.PFCOUNT, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return keys == null ? 0L : client.pfcount(keys[0]);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
        return execute(ProtocolCommand.GEOADD, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.geoadd(key, longitude, latitude, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("longitude", longitude).put("latitude",
                latitude).put("member", member));
    }

    @Override
    public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates){
        return execute(ProtocolCommand.GEOADD, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                final GeoConvert.GeoMapConvert<byte[]> convert = new GeoConvert.GeoMapConvert<>();
                return client.geoadd(key, convert.convert(memberCoordinates));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("memberCoordinates", memberCoordinates));
    }

    @Override
    public List<Geo> geoPos(final byte[] key, final byte[]... members){
        return execute(ProtocolCommand.GEOPOS, new Executor<ShardedJedis, List<Geo>>() {

            @Override
            public List<Geo> execute(ShardedJedis client){
                final GeoConvert.ListMapConvert convert = new GeoConvert.ListMapConvert();
                return convert.deconvert(client.geopos(key, members));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", ArrayUtils.toString(members)));
    }

    @Override
    public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
        return execute(ProtocolCommand.GEODIST, new Executor<ShardedJedis, Double>() {

            @Override
            public Double execute(ShardedJedis client){
                return client.geodist(key, member1, member2);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member1", member1).put("member2", member2));
    }

    @Override
    public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit){
        return execute(ProtocolCommand.GEODIST, new Executor<ShardedJedis, Double>() {

            @Override
            public Double execute(ShardedJedis client){
                final GeoUnitConvert convert = new GeoUnitConvert();
                return client.geodist(key, member1, member2, convert.convert(unit));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member1", member1).put("member2", member2)
                .put("unit", unit));
    }

    @Override
    public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude, final double
            radius, final GeoUnit unit){
        return execute(ProtocolCommand.GEORADIUS, new Executor<ShardedJedis, List<GeoRadius>>() {

            @Override
            public List<GeoRadius> execute(ShardedJedis client){
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
    public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude, final double
            radius, final GeoUnit unit, final GeoArgument geoArgument){
        return execute(ProtocolCommand.GEORADIUS, new Executor<ShardedJedis, List<GeoRadius>>() {

            @Override
            public List<GeoRadius> execute(ShardedJedis client){
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
    public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius, final
    GeoUnit unit){
        return execute(ProtocolCommand.GEORADIUSBYMEMBER, new Executor<ShardedJedis, List<GeoRadius>>() {

            @Override
            public List<GeoRadius> execute(ShardedJedis client){
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
    public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius, final
    GeoUnit unit, final GeoArgument geoArgument){
        return execute(ProtocolCommand.PFMERGE, new Executor<ShardedJedis, List<GeoRadius>>() {

            @Override
            public List<GeoRadius> execute(ShardedJedis client){
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
    public List<byte[]> geoHash(final byte[] key, final byte[]... members){
        return execute(ProtocolCommand.PFMERGE, new Executor<ShardedJedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(ShardedJedis client){
                return client.geohash(key, members);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", ArrayUtils.toString(members)));
    }

    @Override
    public Status setBit(final byte[] key, final long offset, final byte[] value){
        return execute(ProtocolCommand.SETBIT, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnStatus(client.setbit(key, offset, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value));
    }

    @Override
    public Status setBit(final byte[] key, final long offset, final boolean value){
        return execute(ProtocolCommand.SETBIT, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnStatus(client.setbit(key, offset, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value));
    }

    @Override
    public Status getBit(final byte[] key, final long offset){
        return execute(ProtocolCommand.GETBIT, new Executor<ShardedJedis, Status>() {

            @Override
            public Status execute(ShardedJedis client){
                return returnStatus(client.getbit(key, offset));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("offset", offset));
    }

    @Override
    public Long bitPos(final byte[] key, final boolean value){
        return bitPos(SafeEncoder.encode(key), value);
    }

    @Override
    public Long bitPos(final byte[] key, final boolean value, final int start, final int end){
        return bitPos(SafeEncoder.encode(key), value, start, end);
    }

    @Override
    public List<Long> bitField(final byte[] key, final byte[]... arguments){
        return execute(ProtocolCommand.BITFIELD, new Executor<ShardedJedis, List<Long>>() {

            @Override
            public List<Long> execute(ShardedJedis client){
                return client.bitfield(key, arguments);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("arguments", ArrayUtils.toString(arguments)));
    }

    @Override
    public Long bitCount(final byte[] key){
        return execute(ProtocolCommand.BITCOUNT, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.bitcount(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long bitCount(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.BITCOUNT, new Executor<ShardedJedis, Long>() {

            @Override
            public Long execute(ShardedJedis client){
                return client.bitcount(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public byte[] echo(final byte[] str){
        return execute(ProtocolCommand.ECHO, new Executor<ShardedJedis, byte[]>() {

            @Override
            public byte[] execute(ShardedJedis client){
                return client.echo(str);
            }

        }, OperationsCommandArguments.getInstance().put("str", str));
    }

    @Override
    public Object object(final ObjectCommand command, final String key){
        return object(command, SafeEncoder.encode(key));
    }

    @Override
    public Object object(final ObjectCommand command, final byte[] key){
        return execute(ProtocolCommand.OBJECT, new Executor<ShardedJedis, Object>() {

            @Override
            public Object execute(ShardedJedis client){
                if(ObjectCommand.ENCODING == command){
                    return client.objectEncoding(key);
                }else if(ObjectCommand.IDLETIME == command){
                    return client.objectIdletime(key);
                }else if(ObjectCommand.REFCOUNT == command){
                    return client.objectRefcount(key);
                }else{
                    return null;
                }
            }

        }, OperationsCommandArguments.getInstance().put("command", command).put("key", key));
    }

    protected Jedis getShard(final ShardedJedis shardedJedis, final String key){
        return shardedJedis.getShard(key);
    }

    protected Jedis getShard(final ShardedJedis shardedJedis, final byte[] key){
        return shardedJedis.getShard(key);
    }
}
