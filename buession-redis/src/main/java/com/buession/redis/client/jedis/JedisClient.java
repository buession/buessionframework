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
import com.buession.core.utils.NumberUtils;
import com.buession.redis.client.SimpleRedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.Client;
import com.buession.redis.core.Executor;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.Info;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Transaction;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.jedis.AggregateConvert;
import com.buession.redis.core.convert.jedis.BitOperationConvert;
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
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.utils.ClientUtil;
import com.buession.redis.utils.InfoUtil;
import redis.clients.jedis.BinaryJedisPubSub;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.DebugParams;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisMonitor;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.util.SafeEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class JedisClient extends AbstractJedisRedisClient<Jedis> implements SimpleRedisClient {

    public JedisClient(){
        super();
    }

    public JedisClient(RedisConnection connection){
        super(connection);
    }

    @Override
    public boolean exists(final byte[] key){
        return execute(ProtocolCommand.EXISTS, new Executor<Jedis, Boolean>() {

            @Override
            public Boolean execute(Jedis client){
                return client.exists(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Type type(final byte[] key){
        return execute(ProtocolCommand.TYPE, new Executor<Jedis, Type>() {

            @Override
            public Type execute(Jedis client){
                return returnEnum(client.type(key), Type.class);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Status rename(final String key, final String newKey){
        return execute(ProtocolCommand.RENAME, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.rename(key, newKey));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("newKey", newKey));
    }

    @Override
    public Status rename(final byte[] key, final byte[] newKey){
        return execute(ProtocolCommand.RENAME, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.rename(key, newKey));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("newKey", newKey));
    }

    @Override
    public Status renameNx(final String key, final String newKey){
        return execute(ProtocolCommand.RENAMENX, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus(client.renamenx(key, newKey) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("newKey", newKey));
    }

    @Override
    public Status renameNx(final byte[] key, final byte[] newKey){
        return execute(ProtocolCommand.RENAMENX, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus(client.renamenx(key, newKey) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("newKey", newKey));
    }

    @Override
    public String randomKey(){
        return execute(ProtocolCommand.RANDOMKEY, new Executor<Jedis, String>() {

            @Override
            public String execute(Jedis client){
                return client.randomKey();
            }

        });
    }

    @Override
    public Set<String> keys(final String pattern){
        return execute(ProtocolCommand.KEYS, new Executor<Jedis, Set<String>>() {

            @Override
            public Set<String> execute(Jedis client){
                return client.keys(pattern);
            }

        }, OperationsCommandArguments.getInstance().put("pattern", pattern));
    }

    @Override
    public Set<byte[]> keys(final byte[] pattern){
        return execute(ProtocolCommand.KEYS, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.keys(pattern);
            }

        }, OperationsCommandArguments.getInstance().put("pattern", pattern));
    }

    @Override
    public Status expire(final byte[] key, final int lifetime){
        return execute(ProtocolCommand.EXPIRE, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus(client.expire(key, lifetime) == 1);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("lifetime", lifetime));
    }

    @Override
    public Status expireAt(final byte[] key, final long unixTimestamp){
        return execute(ProtocolCommand.EXPIREAT, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus(client.expireAt(key, unixTimestamp) == 1);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("unixTimestamp", unixTimestamp));
    }

    @Override
    public Status pExpire(final byte[] key, final int lifetime){
        return execute(ProtocolCommand.PEXPIRE, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus(client.pexpire(key, lifetime) == 1);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("lifetime", lifetime));
    }

    @Override
    public Status pExpireAt(final byte[] key, final long unixTimestamp){
        return execute(ProtocolCommand.PEXPIREAT, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus(client.pexpireAt(key, unixTimestamp) == 1);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("unixTimestamp", unixTimestamp));
    }

    @Override
    public Long ttl(final byte[] key){
        return execute(ProtocolCommand.TTL, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.ttl(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long pTtl(final byte[] key){
        return execute(ProtocolCommand.PTTL, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.pttl(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Status persist(final byte[] key){
        return execute(ProtocolCommand.PERSIST, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus(client.persist(key) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public ScanResult<List<String>> scan(final String cursor){
        return execute(ProtocolCommand.SCAN, new Executor<Jedis, ScanResult<List<String>>>() {

            @Override
            public ScanResult<List<String>> execute(Jedis client){
                final ScanResultConvert.ListScanResultConvert<String> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                return convert.deconvert(client.scan(cursor));
            }

        }, OperationsCommandArguments.getInstance().put("cursor", cursor));
    }

    @Override
    public ScanResult<List<byte[]>> scan(final byte[] cursor){
        return execute(ProtocolCommand.SCAN, new Executor<Jedis, ScanResult<List<byte[]>>>() {

            @Override
            public ScanResult<List<byte[]>> execute(Jedis client){
                final ScanResultConvert.ListScanResultConvert<byte[]> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                return convert.deconvert(client.scan(cursor));
            }

        }, OperationsCommandArguments.getInstance().put("cursor", cursor));
    }

    @Override
    public ScanResult<List<String>> scan(final String cursor, final String pattern){
        return execute(ProtocolCommand.SCAN, new Executor<Jedis, ScanResult<List<String>>>() {

            @Override
            public ScanResult<List<String>> execute(Jedis client){
                final ScanResultConvert.ListScanResultConvert<String> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                final ScanParams params = new ScanParams();

                return convert.deconvert(client.scan(cursor, params.match(pattern)));
            }

        }, OperationsCommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern));
    }

    @Override
    public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
        return execute(ProtocolCommand.SCAN, new Executor<Jedis, ScanResult<List<byte[]>>>() {

            @Override
            public ScanResult<List<byte[]>> execute(Jedis client){
                final ScanResultConvert.ListScanResultConvert<byte[]> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                final ScanParams params = new ScanParams();

                return convert.deconvert(client.scan(cursor, params.match(pattern)));
            }

        }, OperationsCommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern));
    }

    @Override
    public ScanResult<List<String>> scan(final String cursor, final int count){
        return execute(ProtocolCommand.SCAN, new Executor<Jedis, ScanResult<List<String>>>() {

            @Override
            public ScanResult<List<String>> execute(Jedis client){
                final ScanResultConvert.ListScanResultConvert<String> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                final ScanParams params = new ScanParams();

                return convert.deconvert(client.scan(cursor, params.count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("cursor", cursor).put("count", count));
    }

    @Override
    public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count){
        return execute(ProtocolCommand.SCAN, new Executor<Jedis, ScanResult<List<byte[]>>>() {

            @Override
            public ScanResult<List<byte[]>> execute(Jedis client){
                final ScanResultConvert.ListScanResultConvert<byte[]> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                final ScanParams params = new ScanParams();

                return convert.deconvert(client.scan(cursor, params.count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("cursor", cursor).put("count", count));
    }

    @Override
    public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count){
        return execute(ProtocolCommand.SCAN, new Executor<Jedis, ScanResult<List<String>>>() {

            @Override
            public ScanResult<List<String>> execute(Jedis client){
                final ScanResultConvert.ListScanResultConvert<String> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                final ScanParams params = new ScanParams();

                return convert.deconvert(client.scan(cursor, params.match(pattern).count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count", count));
    }

    @Override
    public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count){
        return execute(ProtocolCommand.SCAN, new Executor<Jedis, ScanResult<List<byte[]>>>() {

            @Override
            public ScanResult<List<byte[]>> execute(Jedis client){
                final ScanResultConvert.ListScanResultConvert<byte[]> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                final ScanParams params = new ScanParams();

                return convert.deconvert(client.scan(cursor, params.match(pattern).count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count", count));
    }

    @Override
    public List<byte[]> sort(final byte[] key){
        return execute(ProtocolCommand.SORT, new Executor<Jedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(Jedis client){
                return client.sort(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public List<byte[]> sort(final byte[] key, final SortArgument sortArgument){
        return execute(ProtocolCommand.SORT, new Executor<Jedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(Jedis client){
                final SortArgumentConvert convert = new SortArgumentConvert();
                return client.sort(key, convert.convert(sortArgument));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("sortArgument", sortArgument));
    }

    @Override
    public Long sort(final String key, final String destKey){
        return execute(ProtocolCommand.SORT, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.sort(key, destKey);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("destKey", destKey));
    }

    @Override
    public Long sort(final byte[] key, final byte[] destKey){
        return execute(ProtocolCommand.SORT, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.sort(key, destKey);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("destKey", destKey));
    }

    @Override
    public Long sort(final String key, final String destKey, final SortArgument sortArgument){
        return execute(ProtocolCommand.SORT, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final SortArgumentConvert convert = new SortArgumentConvert();
                return client.sort(key, convert.convert(sortArgument), destKey);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("sortArgument", sortArgument).put("destKey",
                destKey));
    }

    @Override
    public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
        return execute(ProtocolCommand.SORT, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final SortArgumentConvert convert = new SortArgumentConvert();
                return client.sort(key, convert.convert(sortArgument), destKey);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("sortArgument", sortArgument).put("destKey",
                destKey));
    }

    @Override
    public byte[] dump(final byte[] key){
        return execute(ProtocolCommand.DUMP, new Executor<Jedis, byte[]>() {

            @Override
            public byte[] execute(Jedis client){
                return client.dump(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
        return execute(ProtocolCommand.RESTORE, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.restore(key, ttl, serializedValue));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("serializedValue", serializedValue).put
                ("ttl", ttl));
    }

    @Override
    public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
        return execute(ProtocolCommand.MIGRATE, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.migrate(host, port, key, db, timeout));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("host", host).put("port", port).put("key",
                key).put("db", db).put("timeout", timeout));
    }

    @Override
    public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
        return execute(ProtocolCommand.MIGRATE, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.migrate(host, port, key, db, timeout));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("host", host).put("port", port).put("key",
                key).put("db", db).put("timeout", timeout));
    }

    @Override
    public Status migrate(final String key, final String host, final int port, final int db, final int timeout, final
    MigrateOperation migrateOperation){
        return execute(ProtocolCommand.MIGRATE, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                final MigrateOperationConvert convert = new MigrateOperationConvert();
                return returnForOK(client.migrate(host, port, db, timeout, convert.convert(migrateOperation), key));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("host", host).put("port", port).put("db", db)
                .put("timeout", timeout).put("migrate", migrateOperation));
    }

    @Override
    public Status migrate(byte[] key, String host, int port, int db, int timeout, MigrateOperation migrateOperation){
        return execute(ProtocolCommand.MIGRATE, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                final MigrateOperationConvert convert = new MigrateOperationConvert();
                return returnForOK(client.migrate(host, port, db, timeout, convert.convert(migrateOperation), key));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("host", host).put("port", port).put("db", db)
                .put("timeout", timeout).put("migrate", migrateOperation));
    }

    @Override
    public Long del(final String... keys){
        return execute(ProtocolCommand.DEL, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.del(keys);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long del(byte[]... keys){
        return execute(ProtocolCommand.DEL, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.del(keys);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Status move(final byte[] key, final int db){
        return execute(ProtocolCommand.MOVE, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus(client.move(key, db) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("db", db));
    }

    @Override
    public Status set(final byte[] key, final byte[] value){
        return execute(ProtocolCommand.SET, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.set(key, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Status set(final byte[] key, final byte[] value, final SetArgument setArgument){
        return execute(ProtocolCommand.SET, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                final SetArgumentConvert convert = new SetArgumentConvert();
                return returnForOK(client.set(key, value, convert.convert(setArgument)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value).put("setArgument",
                setArgument));
    }

    @Override
    public Status setEx(final byte[] key, final byte[] value, final int lifetime){
        return execute(ProtocolCommand.SETEX, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.setex(key, lifetime, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value).put("lifetime", lifetime));
    }

    @Override
    public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
        return execute(ProtocolCommand.PSETEX, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.psetex(key, lifetime, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value).put("lifetime", lifetime));
    }

    @Override
    public Status setNx(final byte[] key, final byte[] value){
        return execute(ProtocolCommand.SETNX, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus(client.setnx(key, value) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long append(final byte[] key, final byte[] value){
        return execute(ProtocolCommand.SETNX, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.append(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public byte[] get(final byte[] key){
        return execute(ProtocolCommand.GET, new Executor<Jedis, byte[]>() {

            @Override
            public byte[] execute(Jedis client){
                return client.get(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public byte[] getSet(final byte[] key, final byte[] value){
        return execute(ProtocolCommand.GETSET, new Executor<Jedis, byte[]>() {

            @Override
            public byte[] execute(Jedis client){
                return client.getSet(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Status mSet(Map<String, String> values){
        return execute(ProtocolCommand.MSET, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                List<String> temp = new ArrayList<>(values.size() * 2);

                values.forEach((key, value)->{
                    temp.add(key);
                    temp.add(value);
                });

                return returnForOK(client.mset(temp.toArray(new String[]{})));
            }

        }, OperationsCommandArguments.getInstance().put("values", values));
    }

    @Override
    public Status mSetNx(final Map<String, String> values){
        return execute(ProtocolCommand.MSETNX, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                List<String> temp = new ArrayList<>(values.size() * 2);

                values.forEach((key, value)->{
                    temp.add(key);
                    temp.add(value);
                });

                return returnStatus(client.msetnx(temp.toArray(new String[]{})) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("values", values));
    }

    @Override
    public List<String> mGet(final String... keys){
        return execute(ProtocolCommand.MGET, new Executor<Jedis, List<String>>() {

            @Override
            public List<String> execute(Jedis client){
                return client.mget(keys);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public List<byte[]> mGet(final byte[]... keys){
        return execute(ProtocolCommand.MGET, new Executor<Jedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(Jedis client){
                return client.mget(keys);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long incr(final byte[] key){
        return execute(ProtocolCommand.INCR, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.incr(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long incrBy(final byte[] key, final int value){
        return execute(ProtocolCommand.INCRBY, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.incrBy(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long incrBy(final byte[] key, final long value){
        return execute(ProtocolCommand.INCRBY, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.incrBy(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Double incrByFloat(final byte[] key, final float value){
        return execute(ProtocolCommand.INCRBYFLOAT, new Executor<Jedis, Double>() {

            @Override
            public Double execute(Jedis client){
                return client.incrByFloat(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Double incrByFloat(final byte[] key, final double value){
        return execute(ProtocolCommand.INCRBYFLOAT, new Executor<Jedis, Double>() {

            @Override
            public Double execute(Jedis client){
                return client.incrByFloat(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long decr(final byte[] key){
        return execute(ProtocolCommand.DECR, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.decr(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long decrBy(final byte[] key, final int value){
        return execute(ProtocolCommand.DECRBY, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.decrBy(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long decrBy(final byte[] key, final long value){
        return execute(ProtocolCommand.DECRBY, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.decrBy(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long setRange(final byte[] key, final long offset, final byte[] value){
        return execute(ProtocolCommand.SETRANGE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.setrange(key, offset, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value));
    }

    @Override
    public byte[] getRange(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.GETRANGE, new Executor<Jedis, byte[]>() {

            @Override
            public byte[] execute(Jedis client){
                return client.getrange(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public byte[] substr(final byte[] key, final int start, final int end){
        return execute(ProtocolCommand.SUBSTR, new Executor<Jedis, byte[]>() {

            @Override
            public byte[] execute(Jedis client){
                return client.substr(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Long strlen(final byte[] key){
        return execute(ProtocolCommand.STRLEN, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.strlen(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public boolean hExists(final byte[] key, final byte[] field){
        return execute(ProtocolCommand.HEXISTS, new Executor<Jedis, Boolean>() {

            @Override
            public Boolean execute(Jedis client){
                return client.hexists(key, field);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field));
    }

    @Override
    public Set<byte[]> hKeys(final byte[] key){
        return execute(ProtocolCommand.HKEYS, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.hkeys(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public List<byte[]> hVals(final byte[] key){
        return execute(ProtocolCommand.HVALS, new Executor<Jedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(Jedis client){
                return client.hvals(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Status hSet(final byte[] key, final byte[] field, final byte[] value){
        return execute(ProtocolCommand.HMSET, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus(client.hset(key, field, value) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field).put("value", value));
    }

    @Override
    public Status hSetNx(final byte[] key, final byte[] field, final byte[] value){
        return execute(ProtocolCommand.HSETNX, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus(client.hsetnx(key, field, value) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field).put("value", value));
    }

    @Override
    public byte[] hGet(final byte[] key, final byte[] field){
        return execute(ProtocolCommand.HSETNX, new Executor<Jedis, byte[]>() {

            @Override
            public byte[] execute(Jedis client){
                return client.hget(key, field);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field));
    }

    @Override
    public Status hMSet(final byte[] key, final Map<byte[], byte[]> data){
        return execute(ProtocolCommand.HSETNX, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.hmset(key, data));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("data", data));
    }

    @Override
    public List<byte[]> hMGet(final byte[] key, final byte[]... fields){
        return execute(ProtocolCommand.HMGET, new Executor<Jedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(Jedis client){
                return client.hmget(key, fields);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("fields", ArrayUtils.toString(fields)));
    }

    @Override
    public Map<byte[], byte[]> hGetAll(final byte[] key){
        return execute(ProtocolCommand.HGETALL, new Executor<Jedis, Map<byte[], byte[]>>() {

            @Override
            public Map<byte[], byte[]> execute(Jedis client){
                return client.hgetAll(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long hDel(final byte[] key, final byte[]... fields){
        return execute(ProtocolCommand.HDEL, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.hdel(key, fields);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("fields", ArrayUtils.toString(fields)));
    }

    @Override
    public Long hStrLen(final byte[] key, final byte[] field){
        return execute(ProtocolCommand.HSTRLEN, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.hstrlen(key, field);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field));
    }

    @Override
    public Long hLen(final byte[] key){
        return execute(ProtocolCommand.HLEN, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.hlen(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long hIncrBy(final byte[] key, final byte[] field, final long value){
        return execute(ProtocolCommand.HINCRBY, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.hincrBy(key, field, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field).put("value", value));
    }

    @Override
    public Double hIncrByFloat(final byte[] key, final byte[] field, final double value){
        return execute(ProtocolCommand.HINCRBYFLOAT, new Executor<Jedis, Double>() {

            @Override
            public Double execute(Jedis client){
                return client.hincrByFloat(key, field, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("field", field).put("value", value));
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
        return execute(ProtocolCommand.HSCAN, new Executor<Jedis, ScanResult<Map<byte[], byte[]>>>() {

            @Override
            public ScanResult<Map<byte[], byte[]>> execute(Jedis client){
                final ScanResultConvert.MapScanResultConvert<byte[], byte[]> convert = new ScanResultConvert
                        .MapScanResultConvert<>();
                return convert.deconvert(client.hscan(key, cursor));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor));
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern){
        return execute(ProtocolCommand.HSCAN, new Executor<Jedis, ScanResult<Map<byte[], byte[]>>>() {

            @Override
            public ScanResult<Map<byte[], byte[]>> execute(Jedis client){
                final ScanResultConvert.MapScanResultConvert<byte[], byte[]> convert = new ScanResultConvert
                        .MapScanResultConvert<>();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.hscan(key, cursor, scanParams.match(pattern)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern));
    }

    @Override
    public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final int count){
        return execute(ProtocolCommand.HSCAN, new Executor<Jedis, ScanResult<Map<byte[], byte[]>>>() {

            @Override
            public ScanResult<Map<byte[], byte[]>> execute(Jedis client){
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
        return execute(ProtocolCommand.HSCAN, new Executor<Jedis, ScanResult<Map<byte[], byte[]>>>() {

            @Override
            public ScanResult<Map<byte[], byte[]>> execute(Jedis client){
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
        return execute(ProtocolCommand.LPUSH, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.lpush(key, values);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("values", ArrayUtils.toString(values)));
    }

    @Override
    public Long lPushX(final byte[] key, final byte[]... values){
        return execute(ProtocolCommand.LPUSHX, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.lpushx(key, values);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("values", ArrayUtils.toString(values)));
    }

    @Override
    public Long lInsert(final byte[] key, final byte[] value, final ListPosition position, final byte[] pivot){
        return execute(ProtocolCommand.LPUSHX, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final ListPositionConvert convert = new ListPositionConvert();
                return client.linsert(key, convert.convert(position), pivot, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("position", position).put("pivot", pivot).put
                ("value", value));
    }

    @Override
    public Status lSet(final byte[] key, final long index, final byte[] value){
        return execute(ProtocolCommand.LSET, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.lset(key, index, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("index", index).put("value", value));
    }

    @Override
    public byte[] lIndex(final byte[] key, final long index){
        return execute(ProtocolCommand.LINDEX, new Executor<Jedis, byte[]>() {

            @Override
            public byte[] execute(Jedis client){
                return client.lindex(key, index);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("index", index));
    }

    @Override
    public byte[] lPop(final byte[] key){
        return execute(ProtocolCommand.LPOP, new Executor<Jedis, byte[]>() {

            @Override
            public byte[] execute(Jedis client){
                return client.lpop(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public List<String> blPop(final String[] keys, final int timeout){
        return execute(ProtocolCommand.LPOP, new Executor<Jedis, List<String>>() {

            @Override
            public List<String> execute(Jedis client){
                return client.blpop(timeout, keys);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)).put("timeout", timeout));
    }

    @Override
    public List<byte[]> blPop(final byte[][] keys, final int timeout){
        return execute(ProtocolCommand.BLPOP, new Executor<Jedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(Jedis client){
                return client.blpop(timeout, keys);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)).put("timeout", timeout));
    }

    @Override
    public byte[] rPop(final byte[] key){
        return execute(ProtocolCommand.RPOP, new Executor<Jedis, byte[]>() {

            @Override
            public byte[] execute(Jedis client){
                return client.rpop(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public String rPoplPush(final String source, final String destKey){
        return execute(ProtocolCommand.RPOPLPUSH, new Executor<Jedis, String>() {

            @Override
            public String execute(Jedis client){
                return client.rpoplpush(source, destKey);
            }

        }, OperationsCommandArguments.getInstance().put("source", source).put("destKey", destKey));
    }

    @Override
    public byte[] rPoplPush(final byte[] source, final byte[] destKey){
        return execute(ProtocolCommand.RPOPLPUSH, new Executor<Jedis, byte[]>() {

            @Override
            public byte[] execute(Jedis client){
                return client.rpoplpush(source, destKey);
            }

        }, OperationsCommandArguments.getInstance().put("source", source).put("destKey", destKey));
    }

    @Override
    public List<String> brPop(final String[] keys, final int timeout){
        return execute(ProtocolCommand.BRPOP, new Executor<Jedis, List<String>>() {

            @Override
            public List<String> execute(Jedis client){
                return client.brpop(timeout, keys);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)).put("timeout", timeout));
    }

    @Override
    public List<byte[]> brPop(final byte[][] keys, final int timeout){
        return execute(ProtocolCommand.BRPOP, new Executor<Jedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(Jedis client){
                return client.brpop(timeout, keys);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)).put("timeout", timeout));
    }

    @Override
    public String brPoplPush(final String source, final String destKey, final int timeout){
        return execute(ProtocolCommand.BRPOPLPUSH, new Executor<Jedis, String>() {

            @Override
            public String execute(Jedis client){
                return client.brpoplpush(source, destKey, timeout);
            }

        }, OperationsCommandArguments.getInstance().put("source", source).put("destKey", destKey).put("timeout",
                timeout));
    }

    @Override
    public byte[] brPoplPush(final byte[] source, final byte[] destKey, final int timeout){
        return execute(ProtocolCommand.BRPOPLPUSH, new Executor<Jedis, byte[]>() {

            @Override
            public byte[] execute(Jedis client){
                return client.brpoplpush(source, destKey, timeout);
            }

        }, OperationsCommandArguments.getInstance().put("source", source).put("destKey", destKey).put("timeout",
                timeout));
    }

    @Override
    public Long rPush(final byte[] key, final byte[]... values){
        return execute(ProtocolCommand.RPUSH, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.rpush(key, values);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("values", ArrayUtils.toString(values)));
    }

    @Override
    public Long rPushX(final byte[] key, final byte[]... values){
        return execute(ProtocolCommand.RPUSHX, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.rpushx(key, values);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("values", ArrayUtils.toString(values)));
    }

    @Override
    public Status lTrim(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.LTRIM, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.ltrim(key, start, end));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Long lRem(final byte[] key, final byte[] value, final long count){
        return execute(ProtocolCommand.LREM, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.lrem(key, count, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value).put("count", count));
    }

    @Override
    public List<byte[]> lRange(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.LRANGE, new Executor<Jedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(Jedis client){
                return client.lrange(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Long lLen(final byte[] key){
        return execute(ProtocolCommand.LLEN, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.llen(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long sAdd(final byte[] key, final byte[]... members){
        return execute(ProtocolCommand.SADD, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.sadd(key, members);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", ArrayUtils.toString(members)));
    }

    @Override
    public Long sCard(final byte[] key){
        return execute(ProtocolCommand.SCARD, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.scard(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public boolean sisMember(final byte[] key, final byte[] member){
        return execute(ProtocolCommand.SISMEMBER, new Executor<Jedis, Boolean>() {

            @Override
            public Boolean execute(Jedis client){
                return client.sismember(key, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member));
    }

    @Override
    public Set<byte[]> sMembers(final byte[] key){
        return execute(ProtocolCommand.SMEMBERS, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.smembers(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public byte[] sPop(final byte[] key){
        return execute(ProtocolCommand.SPOP, new Executor<Jedis, byte[]>() {

            @Override
            public byte[] execute(Jedis client){
                return client.spop(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public byte[] sRandMember(final byte[] key){
        return execute(ProtocolCommand.SRANDMEMBER, new Executor<Jedis, byte[]>() {

            @Override
            public byte[] execute(Jedis client){
                return client.srandmember(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public List<byte[]> sRandMember(final byte[] key, final int count){
        return execute(ProtocolCommand.SRANDMEMBER, new Executor<Jedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(Jedis client){
                return client.srandmember(key, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("count", count));
    }

    @Override
    public Long sRem(final byte[] key, final byte[]... members){
        return execute(ProtocolCommand.SREM, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.srem(key, members);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", ArrayUtils.toString(members)));
    }

    @Override
    public Set<String> sDiff(final String... keys){
        return execute(ProtocolCommand.SDIFF, new Executor<Jedis, Set<String>>() {

            @Override
            public Set<String> execute(Jedis client){
                return client.sdiff(keys);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Set<byte[]> sDiff(byte[]... keys){
        return execute(ProtocolCommand.SDIFF, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.sdiff(keys);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long sDiffStore(final String destKey, final String... keys){
        return execute(ProtocolCommand.SDIFFSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.sdiffstore(destKey, keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long sDiffStore(final byte[] destKey, final byte[]... keys){
        return execute(ProtocolCommand.SDIFFSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.sdiffstore(destKey, keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Set<String> sInter(final String... keys){
        return execute(ProtocolCommand.SINTER, new Executor<Jedis, Set<String>>() {

            @Override
            public Set<String> execute(Jedis client){
                return client.sinter(keys);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Set<byte[]> sInter(final byte[]... keys){
        return execute(ProtocolCommand.SINTER, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.sinter(keys);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long sInterStore(final String destKey, final String... keys){
        return execute(ProtocolCommand.SINTERSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.sinterstore(destKey, keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long sInterStore(final byte[] destKey, final byte[]... keys){
        return execute(ProtocolCommand.SINTERSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.sinterstore(destKey, keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Set<String> sUnion(final String... keys){
        return execute(ProtocolCommand.SUNION, new Executor<Jedis, Set<String>>() {

            @Override
            public Set<String> execute(Jedis client){
                return client.sunion(keys);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Set<byte[]> sUnion(final byte[]... keys){
        return execute(ProtocolCommand.SUNION, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.sunion(keys);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long sUnionStore(final String destKey, final String... keys){
        return execute(ProtocolCommand.SUNIONSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.sunionstore(destKey, keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long sUnionStore(final byte[] destKey, final byte[]... keys){
        return execute(ProtocolCommand.SUNIONSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.sunionstore(destKey, keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Status sMove(final String source, final String destKey, final String member){
        return execute(ProtocolCommand.SMOVE, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus(client.smove(source, destKey, member) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("source", source).put("destKey", destKey).put("member",
                member));
    }

    @Override
    public Status sMove(final byte[] source, final byte[] destKey, final byte[] member){
        return execute(ProtocolCommand.SMOVE, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus(client.smove(source, destKey, member) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("source", source).put("destKey", destKey).put("member",
                member));
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
        return execute(ProtocolCommand.SSCAN, new Executor<Jedis, ScanResult<List<byte[]>>>() {

            @Override
            public ScanResult<List<byte[]>> execute(Jedis client){
                final ScanResultConvert.ListScanResultConvert<byte[]> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                return convert.deconvert(client.sscan(key, cursor));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor));
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
        return execute(ProtocolCommand.SSCAN, new Executor<Jedis, ScanResult<List<byte[]>>>() {

            @Override
            public ScanResult<List<byte[]>> execute(Jedis client){
                final ScanResultConvert.ListScanResultConvert<byte[]> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.sscan(key, cursor, scanParams.match(pattern)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern));
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count){
        return execute(ProtocolCommand.SSCAN, new Executor<Jedis, ScanResult<List<byte[]>>>() {

            @Override
            public ScanResult<List<byte[]>> execute(Jedis client){
                final ScanResultConvert.ListScanResultConvert<byte[]> convert = new ScanResultConvert
                        .ListScanResultConvert<>();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.sscan(key, cursor, scanParams.count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count));
    }

    @Override
    public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count){
        return execute(ProtocolCommand.SSCAN, new Executor<Jedis, ScanResult<List<byte[]>>>() {

            @Override
            public ScanResult<List<byte[]>> execute(Jedis client){
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
        return execute(ProtocolCommand.ZADD, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final MapNumberConvert.MapNumberDoubleConvert<byte[]> convert = new MapNumberConvert
                        .MapNumberDoubleConvert<>();
                return client.zadd(key, convert.convert(members));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", members));
    }

    @Override
    public Double zScore(final byte[] key, final byte[] member){
        return execute(ProtocolCommand.ZSCORE, new Executor<Jedis, Double>() {

            @Override
            public Double execute(Jedis client){
                return client.zscore(key, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member));
    }

    @Override
    public Long zCard(final byte[] key){
        return execute(ProtocolCommand.ZCARD, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.zcard(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Double zIncrBy(final byte[] key, final byte[] member, final double increment){
        return execute(ProtocolCommand.ZINCRBY, new Executor<Jedis, Double>() {

            @Override
            public Double execute(Jedis client){
                return client.zincrby(key, increment, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment));
    }

    @Override
    public Long zCount(final byte[] key, final double min, final double max){
        return execute(ProtocolCommand.ZCOUNT, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.zcount(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<byte[]> zRange(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.ZRANGE, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.zrange(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.ZRANGE, new Executor<Jedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(Jedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrangeWithScores(key, start, end));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.zrangeByScore(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.zrangeByScore(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset, final
    int count){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.zrangeByScore(key, min, max, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset, final
    int count){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.zrangeByScore(key, min, max, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<Jedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(Jedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrangeByScoreWithScores(key, min, max));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<Jedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(Jedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrangeByScoreWithScores(key, min, max));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
                                              final int count){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<Jedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(Jedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrangeByScoreWithScores(key, min, max, offset, count));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int offset,
                                              final int count){
        return execute(ProtocolCommand.ZRANGEBYSCORE, new Executor<Jedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(Jedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrangeByScoreWithScores(key, min, max, offset, count));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZRANGEBYLEX, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.zrangeByLex(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset, final int
            count){
        return execute(ProtocolCommand.ZRANGEBYLEX, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.zrangeByLex(key, min, max, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Long zRank(final byte[] key, final byte[] member){
        return execute(ProtocolCommand.ZRANK, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.zrank(key, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member));
    }

    @Override
    public Long zRevRank(final byte[] key, final byte[] member){
        return execute(ProtocolCommand.ZREVRANK, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.zrevrank(key, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member));
    }

    @Override
    public Long zRem(final byte[] key, final byte[]... members){
        return execute(ProtocolCommand.ZREM, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.zrem(key, members);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", ArrayUtils.toString(members)));
    }

    @Override
    public Long zRemRangeByRank(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.ZREMRANGEBYRANK, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.zremrangeByRank(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Long zRemRangeByScore(final byte[] key, final double min, final double max){
        return execute(ProtocolCommand.ZREMRANGEBYSCORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.zremrangeByScore(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZREMRANGEBYSCORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.zremrangeByScore(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZREMRANGEBYLEX, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.zremrangeByLex(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<byte[]> zRevRange(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.ZREVRANGE, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.zrevrange(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.ZREVRANGE, new Executor<Jedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(Jedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrevrangeWithScores(key, start, end));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.zrevrangeByScore(key, max, min);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.zrevrangeByScore(key, max, min);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset, final
    int count){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.zrevrangeByScore(key, max, min, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset, final
    int count){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.zrevrangeByScore(key, max, min, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<Jedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(Jedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrevrangeByScoreWithScores(key, max, min));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<Jedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(Jedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrevrangeByScoreWithScores(key, max, min));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max, final int
            offset, final int count){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<Jedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(Jedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrevrangeByScoreWithScores(key, max, min, offset, count));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("max", max).put("min", min).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int
            offset, final int count){
        return execute(ProtocolCommand.ZREVRANGEBYSCORE, new Executor<Jedis, Set<Tuple>>() {

            @Override
            public Set<Tuple> execute(Jedis client){
                final TupleConvert.SetTupleConvert convert = new TupleConvert.SetTupleConvert();
                return convert.deconvert(client.zrevrangeByScoreWithScores(key, max, min, offset, count));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("max", max).put("min", min).put("offset",
                offset).put("count", count));
    }

    @Override
    public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZREVRANGEBYLEX, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.zrevrangeByLex(key, max, min);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset, final
    int count){
        return execute(ProtocolCommand.ZREVRANGEBYLEX, new Executor<Jedis, Set<byte[]>>() {

            @Override
            public Set<byte[]> execute(Jedis client){
                return client.zrevrangeByLex(key, max, min, offset, count);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset",
                offset).put("count", count));
    }

    @Override
    public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
        return execute(ProtocolCommand.ZLEXCOUNT, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.zlexcount(key, min, max);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("min", min).put("max", max));
    }

    @Override
    public Long zInterStore(final String destKey, final String... keys){
        return execute(ProtocolCommand.ZINTERSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.zinterstore(destKey, keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long zInterStore(final byte[] destKey, final byte[]... keys){
        return execute(ProtocolCommand.ZINTERSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.zinterstore(destKey, keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long zInterStore(final String destKey, final Aggregate aggregate, final String... keys){
        return execute(ProtocolCommand.ZINTERSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final AggregateConvert convert = new AggregateConvert();
                final ZParams zParams = new ZParams();

                return client.zinterstore(destKey, zParams.aggregate(convert.convert(aggregate)), keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys",
                ArrayUtils.toString(keys)));
    }

    @Override
    public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
        return execute(ProtocolCommand.ZINTERSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final AggregateConvert convert = new AggregateConvert();
                final ZParams zParams = new ZParams();

                return client.zinterstore(destKey, zParams.aggregate(convert.convert(aggregate)), keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys",
                ArrayUtils.toString(keys)));
    }

    @Override
    public Long zInterStore(final String destKey, final double[] weights, final String... keys){
        return execute(ProtocolCommand.ZINTERSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final ZParams zParams = new ZParams();
                return client.zinterstore(destKey, zParams.weights(weights), keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys",
                ArrayUtils.toString(keys)));
    }

    @Override
    public Long zInterStore(final byte[] destKey, final double[] weights, final byte[]... keys){
        return execute(ProtocolCommand.ZINTERSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final ZParams zParams = new ZParams();
                return client.zinterstore(destKey, zParams.weights(weights), keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys",
                ArrayUtils.toString(keys)));
    }

    @Override
    public Long zInterStore(final String destKey, final Aggregate aggregate, final double[] weights, final String...
            keys){
        return execute(ProtocolCommand.ZINTERSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final AggregateConvert convert = new AggregateConvert();
                final ZParams zParams = new ZParams();

                return client.zinterstore(destKey, zParams.aggregate(convert.convert(aggregate)).weights(weights),
                        keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys",
                ArrayUtils.toString(keys)));
    }

    @Override
    public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double[] weights, final byte[]...
            keys){
        return execute(ProtocolCommand.ZINTERSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final AggregateConvert convert = new AggregateConvert();
                final ZParams zParams = new ZParams();

                return client.zinterstore(destKey, zParams.aggregate(convert.convert(aggregate)).weights(weights),
                        keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys",
                ArrayUtils.toString(keys)));
    }

    @Override
    public Long zUnionStore(final String destKey, final String... keys){
        return execute(ProtocolCommand.ZUNIONSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.zunionstore(destKey, keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long zUnionStore(final byte[] destKey, final byte[]... keys){
        return execute(ProtocolCommand.ZUNIONSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.zunionstore(destKey, keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long zUnionStore(final String destKey, final Aggregate aggregate, final String... keys){
        return execute(ProtocolCommand.ZUNIONSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final AggregateConvert convert = new AggregateConvert();
                final ZParams params = new ZParams();

                return client.zunionstore(destKey, params.aggregate(convert.convert(aggregate)), keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys",
                ArrayUtils.toString(keys)));
    }

    @Override
    public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
        return execute(ProtocolCommand.ZUNIONSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final AggregateConvert convert = new AggregateConvert();
                final ZParams params = new ZParams();

                return client.zunionstore(destKey, params.aggregate(convert.convert(aggregate)), keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys",
                ArrayUtils.toString(keys)));
    }

    @Override
    public Long zUnionStore(final String destKey, final double[] weights, final String... keys){
        return execute(ProtocolCommand.ZUNIONSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final ZParams params = new ZParams();

                return client.zunionstore(destKey, params.weights(weights), keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys",
                ArrayUtils.toString(keys)));
    }

    @Override
    public Long zUnionStore(final byte[] destKey, final double[] weights, final byte[]... keys){
        return execute(ProtocolCommand.ZUNIONSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final ZParams params = new ZParams();

                return client.zunionstore(destKey, params.weights(weights), keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys",
                ArrayUtils.toString(keys)));
    }

    @Override
    public Long zUnionStore(final String destKey, final Aggregate aggregate, final double[] weights, final String...
            keys){
        return execute(ProtocolCommand.ZUNIONSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final AggregateConvert convert = new AggregateConvert();
                final ZParams params = new ZParams();

                return client.zunionstore(destKey, params.aggregate(convert.convert(aggregate)).weights(weights), keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys",
                ArrayUtils.toString(keys)));
    }

    @Override
    public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final double[] weights, final byte[]...
            keys){
        return execute(ProtocolCommand.ZUNIONSTORE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final AggregateConvert convert = new AggregateConvert();
                final ZParams params = new ZParams();

                return client.zunionstore(destKey, params.aggregate(convert.convert(aggregate)).weights(weights), keys);
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys",
                ArrayUtils.toString(keys)));
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
        return execute(ProtocolCommand.ZSCAN, new Executor<Jedis, ScanResult<List<Tuple>>>() {

            @Override
            public ScanResult<List<Tuple>> execute(Jedis client){
                final ScanResultConvert.ListTupleScanResultConvert convert = new ScanResultConvert
                        .ListTupleScanResultConvert();
                return convert.deconvert(client.zscan(key, cursor));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor));
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
        return execute(ProtocolCommand.ZSCAN, new Executor<Jedis, ScanResult<List<Tuple>>>() {

            @Override
            public ScanResult<List<Tuple>> execute(Jedis client){
                final ScanResultConvert.ListTupleScanResultConvert convert = new ScanResultConvert
                        .ListTupleScanResultConvert();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.zscan(key, cursor, scanParams.match(pattern)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern));
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count){
        return execute(ProtocolCommand.ZSCAN, new Executor<Jedis, ScanResult<List<Tuple>>>() {

            @Override
            public ScanResult<List<Tuple>> execute(Jedis client){
                final ScanResultConvert.ListTupleScanResultConvert convert = new ScanResultConvert
                        .ListTupleScanResultConvert();
                final ScanParams scanParams = new ScanParams();

                return convert.deconvert(client.zscan(key, cursor, scanParams.count(count)));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count));
    }

    @Override
    public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count){
        return execute(ProtocolCommand.ZSCAN, new Executor<Jedis, ScanResult<List<Tuple>>>() {

            @Override
            public ScanResult<List<Tuple>> execute(Jedis client){
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
        return execute(ProtocolCommand.PFADD, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus(client.pfadd(key, elements) > 0);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("elements", ArrayUtils.toString(elements)));
    }

    @Override
    public Status pfMerge(final String destKey, final String... keys){
        return execute(ProtocolCommand.PFMERGE, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.pfmerge(destKey, keys));
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Status pfMerge(final byte[] destKey, final byte[]... keys){
        return execute(ProtocolCommand.PFMERGE, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.pfmerge(destKey, keys));
            }

        }, OperationsCommandArguments.getInstance().put("destKey", destKey).put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long pfCount(final String... keys){
        return execute(ProtocolCommand.PFCOUNT, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.pfcount(keys);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long pfCount(final byte[]... keys){
        return execute(ProtocolCommand.PFCOUNT, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.pfcount(keys);
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
        return execute(ProtocolCommand.GEOADD, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.geoadd(key, longitude, latitude, member);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member", member).put("longitude", longitude)
                .put("latitude", latitude));
    }

    @Override
    public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates){
        return execute(ProtocolCommand.GEOADD, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final GeoConvert.GeoMapConvert<byte[]> convert = new GeoConvert.GeoMapConvert<>();
                return client.geoadd(key, convert.convert(memberCoordinates));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("memberCoordinates", memberCoordinates));
    }

    @Override
    public List<Geo> geoPos(final byte[] key, final byte[]... members){
        return execute(ProtocolCommand.GEOPOS, new Executor<Jedis, List<Geo>>() {

            @Override
            public List<Geo> execute(Jedis client){
                final GeoConvert.ListMapConvert convert = new GeoConvert.ListMapConvert();
                return convert.deconvert(client.geopos(key, members));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", ArrayUtils.toString(members)));
    }

    @Override
    public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
        return execute(ProtocolCommand.GEODIST, new Executor<Jedis, Double>() {

            @Override
            public Double execute(Jedis client){
                return client.geodist(key, member1, member2);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member1", member1).put("member2", member2));
    }

    @Override
    public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit){
        return execute(ProtocolCommand.GEODIST, new Executor<Jedis, Double>() {

            @Override
            public Double execute(Jedis client){
                final GeoUnitConvert convert = new GeoUnitConvert();
                return client.geodist(key, member1, member2, convert.convert(unit));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("member1", member1).put("member2", member2)
                .put("unit", unit));
    }

    @Override
    public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude, final double
            radius, final GeoUnit unit){
        return execute(ProtocolCommand.GEORADIUS, new Executor<Jedis, List<GeoRadius>>() {

            @Override
            public List<GeoRadius> execute(Jedis client){
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
        return execute(ProtocolCommand.GEORADIUS, new Executor<Jedis, List<GeoRadius>>() {

            @Override
            public List<GeoRadius> execute(Jedis client){
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
        return execute(ProtocolCommand.GEORADIUSBYMEMBER, new Executor<Jedis, List<GeoRadius>>() {

            @Override
            public List<GeoRadius> execute(Jedis client){
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
        return execute(ProtocolCommand.PFMERGE, new Executor<Jedis, List<GeoRadius>>() {

            @Override
            public List<GeoRadius> execute(Jedis client){
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
        return execute(ProtocolCommand.PFMERGE, new Executor<Jedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(Jedis client){
                return client.geohash(key, members);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("members", ArrayUtils.toString(members)));
    }

    @Override
    public Status setBit(final byte[] key, final long offset, final byte[] value){
        return execute(ProtocolCommand.SETBIT, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus(client.setbit(key, offset, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value));
    }

    @Override
    public Status setBit(final byte[] key, final long offset, final boolean value){
        return execute(ProtocolCommand.SETBIT, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus(client.setbit(key, offset, value));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value));
    }

    @Override
    public Status getBit(final byte[] key, final long offset){
        return execute(ProtocolCommand.GETBIT, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus(client.getbit(key, offset));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("offset", offset));
    }

    @Override
    public Long bitPos(final byte[] key, final boolean value){
        return execute(ProtocolCommand.BITPOS, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.bitpos(key, value);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value));
    }

    @Override
    public Long bitPos(final byte[] key, final boolean value, final int start, final int end){
        return execute(ProtocolCommand.BITPOS, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.bitpos(key, value, new BitPosParams(start, end));
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("value", value).put("start", start).put
                ("end", end));
    }

    @Override
    public Long bitOp(final Operation operation, final String destKey, final String... keys){
        return execute(ProtocolCommand.BITOP, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final BitOperationConvert convert = new BitOperationConvert();
                return client.bitop(convert.convert(operation), destKey, keys);
            }

        }, OperationsCommandArguments.getInstance().put("operation", operation).put("destKey", destKey).put("keys",
                ArrayUtils.toString(keys)));
    }

    @Override
    public Long bitOp(final Operation operation, final byte[] destKey, final byte[]... keys){
        return execute(ProtocolCommand.BITOP, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                final BitOperationConvert convert = new BitOperationConvert();
                return client.bitop(convert.convert(operation), destKey, keys);
            }

        }, OperationsCommandArguments.getInstance().put("operation", operation).put("destKey", destKey).put("keys",
                ArrayUtils.toString(keys)));
    }

    @Override
    public List<Long> bitField(final byte[] key, final byte[]... arguments){
        return execute(ProtocolCommand.BITFIELD, new Executor<Jedis, List<Long>>() {

            @Override
            public List<Long> execute(Jedis client){
                return client.bitfield(key, arguments);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("arguments", ArrayUtils.toString(arguments)));
    }

    @Override
    public Long bitCount(final byte[] key){
        return execute(ProtocolCommand.BITCOUNT, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.bitcount(key);
            }

        }, OperationsCommandArguments.getInstance().put("key", key));
    }

    @Override
    public Long bitCount(final byte[] key, final long start, final long end){
        return execute(ProtocolCommand.BITCOUNT, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.bitcount(key, start, end);
            }

        }, OperationsCommandArguments.getInstance().put("key", key).put("start", start).put("end", end));
    }

    @Override
    public Transaction multi(){
        return execute(ProtocolCommand.MULTI, new Executor<Jedis, Transaction>() {

            @Override
            public Transaction execute(Jedis client){
                return null;// client.multi();
            }

        });
    }

    @Override
    public void exec(final Transaction transaction){
        execute(ProtocolCommand.EXEC, new Executor<Jedis, Void>() {

            @Override
            public Void execute(Jedis client){
                return null;
            }

        });
    }

    @Override
    public void discard(final Transaction transaction){
        execute(ProtocolCommand.DISCARD, new Executor<Jedis, Void>() {

            @Override
            public Void execute(Jedis client){
                return null;
            }

        });
    }

    @Override
    public Status watch(final String... keys){
        return execute(ProtocolCommand.WATCH, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.watch(keys));
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Status watch(final byte[]... keys){
        return execute(ProtocolCommand.WATCH, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.watch(keys));
            }

        }, OperationsCommandArguments.getInstance().put("keys", ArrayUtils.toString(keys)));
    }

    @Override
    public Status unwatch(){
        return execute(ProtocolCommand.UNWATCH, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.unwatch());
            }

        });
    }

    @Override
    public Long publish(final String channel, final String message){
        return execute(ProtocolCommand.PUBLISH, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.publish(channel, message);
            }

        }, OperationsCommandArguments.getInstance().put("channel", channel).put("message", message));
    }

    @Override
    public Long publish(final byte[] channel, final byte[] message){
        return execute(ProtocolCommand.PUBLISH, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.publish(channel, message);
            }

        }, OperationsCommandArguments.getInstance().put("channel", channel).put("message", message));
    }

    @Override
    public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
        execute(ProtocolCommand.SUBSCRIBE, new Executor<Jedis, Void>() {

            @Override
            public Void execute(Jedis client){
                client.subscribe(new JedisPubSub() {

                    @Override
                    public void onMessage(String channel, String message){
                        pubSubListener.onMessage(channel, message);
                    }

                    @Override
                    public void onPMessage(String pattern, String channel, String message){
                        pubSubListener.onPMessage(pattern, channel, message);
                    }

                    @Override
                    public void onSubscribe(String channel, int subscribedChannels){
                        pubSubListener.onSubscribe(channel, subscribedChannels);
                    }

                    @Override
                    public void onUnsubscribe(String channel, int subscribedChannels){
                        pubSubListener.onUnsubscribe(channel, subscribedChannels);
                    }

                    @Override
                    public void onPUnsubscribe(String pattern, int subscribedChannels){
                        pubSubListener.onPUnsubscribe(pattern, subscribedChannels);
                    }

                    @Override
                    public void onPSubscribe(String pattern, int subscribedChannels){
                        pubSubListener.onPSubscribe(pattern, subscribedChannels);
                    }

                    @Override
                    public void onPong(String pattern){
                        pubSubListener.onPong(pattern);
                    }
                }, channels);

                return null;
            }

        }, OperationsCommandArguments.getInstance().put("channels", ArrayUtils.toString(channels)).put
                ("pubSubListener", pubSubListener));
    }

    @Override
    public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
        execute(ProtocolCommand.SUBSCRIBE, new Executor<Jedis, Void>() {

            @Override
            public Void execute(Jedis client){
                client.subscribe(new BinaryJedisPubSub() {

                    @Override
                    public void onMessage(byte[] channel, byte[] message){
                        pubSubListener.onMessage(channel, message);
                    }

                    @Override
                    public void onPMessage(byte[] pattern, byte[] channel, byte[] message){
                        pubSubListener.onPMessage(pattern, channel, message);
                    }

                    @Override
                    public void onSubscribe(byte[] channel, int subscribedChannels){
                        pubSubListener.onSubscribe(channel, subscribedChannels);
                    }

                    @Override
                    public void onUnsubscribe(byte[] channel, int subscribedChannels){
                        pubSubListener.onUnsubscribe(channel, subscribedChannels);
                    }

                    @Override
                    public void onPUnsubscribe(byte[] pattern, int subscribedChannels){
                        pubSubListener.onPUnsubscribe(pattern, subscribedChannels);
                    }

                    @Override
                    public void onPSubscribe(byte[] pattern, int subscribedChannels){
                        pubSubListener.onPSubscribe(pattern, subscribedChannels);
                    }

                }, channels);

                return null;
            }

        }, OperationsCommandArguments.getInstance().put("channels", ArrayUtils.toString(channels)).put
                ("pubSubListener", pubSubListener));
    }

    @Override
    public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
        execute(ProtocolCommand.PSUBSCRIBE, new Executor<Jedis, Void>() {

            @Override
            public Void execute(Jedis client){
                client.psubscribe(new JedisPubSub() {

                    @Override
                    public void onMessage(String channel, String message){
                        pubSubListener.onMessage(channel, message);
                    }

                    @Override
                    public void onPMessage(String pattern, String channel, String message){
                        pubSubListener.onPMessage(pattern, channel, message);
                    }

                    @Override
                    public void onSubscribe(String channel, int subscribedChannels){
                        pubSubListener.onSubscribe(channel, subscribedChannels);
                    }

                    @Override
                    public void onUnsubscribe(String channel, int subscribedChannels){
                        pubSubListener.onUnsubscribe(channel, subscribedChannels);
                    }

                    @Override
                    public void onPUnsubscribe(String pattern, int subscribedChannels){
                        pubSubListener.onPUnsubscribe(pattern, subscribedChannels);
                    }

                    @Override
                    public void onPSubscribe(String pattern, int subscribedChannels){
                        pubSubListener.onPSubscribe(pattern, subscribedChannels);
                    }

                    @Override
                    public void onPong(String pattern){
                        pubSubListener.onPong(pattern);
                    }
                }, patterns);

                return null;
            }

        }, OperationsCommandArguments.getInstance().put("patterns", ArrayUtils.toString(patterns)).put
                ("pubSubListener", pubSubListener));
    }

    @Override
    public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
        execute(ProtocolCommand.PSUBSCRIBE, new Executor<Jedis, Void>() {

            @Override
            public Void execute(Jedis client){
                client.psubscribe(new BinaryJedisPubSub() {

                    @Override
                    public void onMessage(byte[] channel, byte[] message){
                        pubSubListener.onMessage(channel, message);
                    }

                    @Override
                    public void onPMessage(byte[] pattern, byte[] channel, byte[] message){
                        pubSubListener.onPMessage(pattern, channel, message);
                    }

                    @Override
                    public void onSubscribe(byte[] channel, int subscribedChannels){
                        pubSubListener.onSubscribe(channel, subscribedChannels);
                    }

                    @Override
                    public void onUnsubscribe(byte[] channel, int subscribedChannels){
                        pubSubListener.onUnsubscribe(channel, subscribedChannels);
                    }

                    @Override
                    public void onPUnsubscribe(byte[] pattern, int subscribedChannels){
                        pubSubListener.onPUnsubscribe(pattern, subscribedChannels);
                    }

                    @Override
                    public void onPSubscribe(byte[] pattern, int subscribedChannels){
                        pubSubListener.onPSubscribe(pattern, subscribedChannels);
                    }

                }, patterns);

                return null;
            }

        }, OperationsCommandArguments.getInstance().put("patterns", ArrayUtils.toString(patterns)).put
                ("pubSubListener", pubSubListener));
    }

    @Override
    public Status select(final int db){
        return execute(ProtocolCommand.SELECT, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.select(db));
            }

        }, OperationsCommandArguments.getInstance().put("db", db));
    }

    @Override
    public Status swapdb(final int db1, final int db2){
        return execute(ProtocolCommand.SWAPDB, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.swapDB(db1, db2));
            }

        }, OperationsCommandArguments.getInstance().put("db1", db1).put("db2", db2));
    }

    @Override
    public Long dbSize(){
        return execute(ProtocolCommand.DBSIZE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.dbSize();
            }

        });
    }

    @Override
    public Status flushDb(){
        return execute(ProtocolCommand.FLUSHDB, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.flushDB());
            }

        });
    }

    @Override
    public Status flushAll(){
        return execute(ProtocolCommand.FLUSHALL, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.flushAll());
            }

        });
    }

    @Override
    public Object eval(final String script){
        return execute(ProtocolCommand.EVAL, new Executor<Jedis, Object>() {

            @Override
            public Object execute(Jedis client){
                return client.eval(script);
            }

        }, OperationsCommandArguments.getInstance().put("script", script));
    }

    @Override
    public Object eval(final byte[] script){
        return execute(ProtocolCommand.EVAL, new Executor<Jedis, Object>() {

            @Override
            public Object execute(Jedis client){
                return client.eval(script);
            }

        }, OperationsCommandArguments.getInstance().put("script", script));
    }

    @Override
    public Object eval(final String script, final String... params){
        return execute(ProtocolCommand.EVAL, new Executor<Jedis, Object>() {

            @Override
            public Object execute(Jedis client){
                return client.eval(script, params == null ? 0 : params.length, params);
            }

        }, OperationsCommandArguments.getInstance().put("script", script).put("params", ArrayUtils.toString(params)));
    }

    @Override
    public Object eval(final byte[] script, final byte[]... params){
        return execute(ProtocolCommand.EVAL, new Executor<Jedis, Object>() {

            @Override
            public Object execute(Jedis client){
                return client.eval(script, params == null ? 0 : params.length, params);
            }

        }, OperationsCommandArguments.getInstance().put("script", script).put("params", ArrayUtils.toString(params)));
    }

    @Override
    public Object eval(final String script, final String[] keys, final String[] args){
        return execute(ProtocolCommand.EVAL, new Executor<Jedis, Object>() {

            @Override
            public Object execute(Jedis client){
                return client.eval(script, Arrays.asList(keys), Arrays.asList(args));
            }

        }, OperationsCommandArguments.getInstance().put("script", script).put("keys", ArrayUtils.toString(keys)).put
                ("args", ArrayUtils.toString(args)));
    }

    @Override
    public Object eval(final byte[] script, final byte[][] keys, final byte[][] args){
        return execute(ProtocolCommand.EVAL, new Executor<Jedis, Object>() {

            @Override
            public Object execute(Jedis client){
                return client.eval(script, Arrays.asList(keys), Arrays.asList(args));
            }

        }, OperationsCommandArguments.getInstance().put("script", script).put("keys", ArrayUtils.toString(keys)).put
                ("args", ArrayUtils.toString(args)));
    }

    @Override
    public Object evalSha(final String digest){
        return execute(ProtocolCommand.EVALSHA, new Executor<Jedis, Object>() {

            @Override
            public Object execute(Jedis client){
                return client.evalsha(digest);
            }

        }, OperationsCommandArguments.getInstance().put("digest", digest));
    }

    @Override
    public Object evalSha(final byte[] digest){
        return execute(ProtocolCommand.EVALSHA, new Executor<Jedis, Object>() {

            @Override
            public Object execute(Jedis client){
                return client.evalsha(digest);
            }

        }, OperationsCommandArguments.getInstance().put("digest", digest));
    }

    @Override
    public Object evalSha(final String digest, final String... params){
        return execute(ProtocolCommand.EVALSHA, new Executor<Jedis, Object>() {

            @Override
            public Object execute(Jedis client){
                return client.evalsha(digest, params == null ? 0 : params.length, params);
            }

        }, OperationsCommandArguments.getInstance().put("digest", digest).put("params", ArrayUtils.toString(params)));
    }

    @Override
    public Object evalSha(final byte[] digest, final byte[]... params){
        return execute(ProtocolCommand.EVALSHA, new Executor<Jedis, Object>() {

            @Override
            public Object execute(Jedis client){
                return client.evalsha(digest, params == null ? 0 : params.length, params);
            }

        }, OperationsCommandArguments.getInstance().put("digest", digest).put("params", ArrayUtils.toString(params)));
    }

    @Override
    public Object evalSha(final String digest, final String[] keys, final String[] args){
        return execute(ProtocolCommand.EVALSHA, new Executor<Jedis, Object>() {

            @Override
            public Object execute(Jedis client){
                return client.evalsha(digest, Arrays.asList(keys), Arrays.asList(args));
            }

        }, OperationsCommandArguments.getInstance().put("digest", digest).put("keys", ArrayUtils.toString(keys)).put
                ("args", ArrayUtils.toString(args)));
    }

    @Override
    public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] args){
        return execute(ProtocolCommand.EVALSHA, new Executor<Jedis, Object>() {

            @Override
            public Object execute(Jedis client){
                return client.evalsha(digest, Arrays.asList(keys), Arrays.asList(args));
            }

        }, OperationsCommandArguments.getInstance().put("digest", digest).put("keys", ArrayUtils.toString(keys)).put
                ("args", ArrayUtils.toString(args)));
    }

    @Override
    public List<Boolean> scriptExists(final String... sha1){
        return execute(ProtocolCommand.SCRIPT_EXISTS, new Executor<Jedis, List<Boolean>>() {

            @Override
            public List<Boolean> execute(Jedis client){
                return client.scriptExists(sha1);
            }

        }, OperationsCommandArguments.getInstance().put("sha1", ArrayUtils.toString(sha1)));
    }

    @Override
    public List<Boolean> scriptExists(final byte[]... sha1){
        return execute(ProtocolCommand.SCRIPT_EXISTS, new Executor<Jedis, List<Boolean>>() {

            @Override
            public List<Boolean> execute(Jedis client){
                List<Long> ret = client.scriptExists(sha1);

                if(ret == null){
                    return null;
                }else{
                    List<Boolean> result = new ArrayList<>();

                    for(Long v : ret){
                        result.add(v == 1);
                    }

                    return result;
                }
            }

        }, OperationsCommandArguments.getInstance().put("sha1", ArrayUtils.toString(sha1)));
    }

    @Override
    public String scriptLoad(final String script){
        return execute(ProtocolCommand.SCRIPT_LOAD, new Executor<Jedis, String>() {

            @Override
            public String execute(Jedis client){
                return client.scriptLoad(script);
            }

        }, OperationsCommandArguments.getInstance().put("script", script));
    }

    @Override
    public byte[] scriptLoad(final byte[] script){
        return execute(ProtocolCommand.SCRIPT_LOAD, new Executor<Jedis, byte[]>() {

            @Override
            public byte[] execute(Jedis client){
                return client.scriptLoad(script);
            }

        }, OperationsCommandArguments.getInstance().put("script", script));
    }

    @Override
    public Status scriptKill(){
        return execute(ProtocolCommand.SCRIPT_KILL, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.scriptKill());
            }

        });
    }

    @Override
    public Status scriptFlush(){
        return execute(ProtocolCommand.SCRIPT_FLUSH, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.scriptFlush());
            }

        });
    }

    @Override
    public Status save(){
        return execute(ProtocolCommand.SAVE, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.save());
            }

        });
    }

    @Override
    public String bgSave(){
        return execute(ProtocolCommand.BGSAVE, new Executor<Jedis, String>() {

            @Override
            public String execute(Jedis client){
                return client.bgsave();
            }

        });
    }

    @Override
    public String bgRewriteAof(){
        return execute(ProtocolCommand.BGREWRITEAOF, new Executor<Jedis, String>() {

            @Override
            public String execute(Jedis client){
                return client.bgrewriteaof();
            }

        });
    }

    @Override
    public Long lastSave(){
        return execute(ProtocolCommand.LASTSAVE, new Executor<Jedis, Long>() {

            @Override
            public Long execute(Jedis client){
                return client.lastsave();
            }

        });
    }

    @Override
    public Status slaveOf(final String host, final int port){
        return execute(ProtocolCommand.SLAVEOF, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.slaveof(host, port));
            }

        }, OperationsCommandArguments.getInstance().put("host", host).put("port", port));
    }

    @Override
    public Status auth(final String password){
        return execute(ProtocolCommand.AUTH, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.auth(password));
            }

        }, OperationsCommandArguments.getInstance().put("password", password));
    }

    @Override
    public Status auth(final byte[] password){
        return auth(SafeEncoder.encode(password));
    }

    @Override
    public Info info(final InfoSection section){
        return execute(ProtocolCommand.INFO, new Executor<Jedis, Info>() {

            @Override
            public Info execute(Jedis client){
                return InfoUtil.convert(client.info(section.name().toLowerCase()));
            }

        }, OperationsCommandArguments.getInstance().put("section", section));
    }

    @Override
    public Info info(){
        return execute(ProtocolCommand.INFO, new Executor<Jedis, Info>() {

            @Override
            public Info execute(Jedis client){
                return InfoUtil.convert(client.info());
            }

        });
    }

    @Override
    public RedisServerTime time(){
        return execute(ProtocolCommand.TIME, new Executor<Jedis, RedisServerTime>() {

            @Override
            public RedisServerTime execute(Jedis client){
                return returnRedisServerTime(client.time());
            }

        });
    }

    @Override
    public Status clientSetName(final String name){
        return execute(ProtocolCommand.CLIENT_SETNAME, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.clientSetname(name));
            }

        }, OperationsCommandArguments.getInstance().put("name", name));
    }

    @Override
    public Status clientSetName(final byte[] name){
        return execute(ProtocolCommand.CLIENT_SETNAME, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.clientSetname(name));
            }

        }, OperationsCommandArguments.getInstance().put("name", name));
    }

    @Override
    public String clientGetName(){
        return execute(ProtocolCommand.CLIENT_GETNAME, new Executor<Jedis, String>() {

            @Override
            public String execute(Jedis client){
                return client.clientGetname();
            }

        });
    }

    @Override
    public List<Client> clientList(){
        return execute(ProtocolCommand.CLIENT_LIST, new Executor<Jedis, List<Client>>() {

            @Override
            public List<Client> execute(Jedis client){
                return ClientUtil.parse(client.clientList());
            }

        });
    }

    @Override
    public Status clientKill(final String host, final int port){
        return execute(ProtocolCommand.CLIENT_KILL, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.clientKill(host + ":" + port));
            }

        }, OperationsCommandArguments.getInstance().put("host", host).put("port", port));
    }

    @Override
    public Status quit(){
        return execute(ProtocolCommand.QUIT, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.quit());
            }

        });
    }

    @Override
    public void shutdown(){
        execute(ProtocolCommand.SHUTDOWN, new Executor<Jedis, Void>() {

            @Override
            public Void execute(Jedis client){
                client.shutdown();
                return null;
            }

        });
    }

    @Override
    public void shutdown(final boolean save){
        shutdown();
    }

    @Override
    public Status configSet(final String parameter, final float value){
        return configSet(parameter, Float.toString(value));
    }

    @Override
    public Status configSet(final byte[] parameter, final float value){
        return configSet(parameter, NumberUtils.float2bytes(value));
    }

    @Override
    public Status configSet(final String parameter, final double value){
        return configSet(parameter, Double.toString(value));
    }

    @Override
    public Status configSet(final byte[] parameter, final double value){
        return configSet(parameter, NumberUtils.double2bytes(value));
    }

    @Override
    public Status configSet(final String parameter, final int value){
        return configSet(parameter, Integer.toString(value));
    }

    @Override
    public Status configSet(final byte[] parameter, final int value){
        return configSet(parameter, NumberUtils.int2bytes(value));
    }

    @Override
    public Status configSet(final String parameter, final long value){
        return configSet(parameter, Long.toString(value));
    }

    @Override
    public Status configSet(final byte[] parameter, final long value){
        return configSet(parameter, NumberUtils.long2bytes(value));
    }

    @Override
    public Status configSet(final String parameter, final String value){
        return execute(ProtocolCommand.CONFIG_SET, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.configSet(parameter, value));
            }

        }, OperationsCommandArguments.getInstance().put("parameter", parameter).put("value", value));
    }

    @Override
    public Status configSet(final byte[] parameter, final byte[] value){
        return execute(ProtocolCommand.CONFIG_SET, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.configSet(parameter, value));
            }

        }, OperationsCommandArguments.getInstance().put("parameter", parameter).put("value", value));
    }

    @Override
    public List<String> configGet(final String parameter){
        return execute(ProtocolCommand.CONFIG_GET, new Executor<Jedis, List<String>>() {

            @Override
            public List<String> execute(Jedis client){
                return client.configGet(parameter);
            }

        }, OperationsCommandArguments.getInstance().put("parameter", parameter));
    }

    @Override
    public List<byte[]> configGet(final byte[] parameter){
        return execute(ProtocolCommand.CONFIG_GET, new Executor<Jedis, List<byte[]>>() {

            @Override
            public List<byte[]> execute(Jedis client){
                return client.configGet(parameter);
            }

        }, OperationsCommandArguments.getInstance().put("parameter", parameter));
    }

    @Override
    public Status configResetStat(){
        return execute(ProtocolCommand.CONFIG_RESETSTAT, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.configResetStat());
            }

        });
    }

    @Override
    public Status configRewrite(){
        return execute(ProtocolCommand.CONFIG_REWRITE, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnForOK(client.configRewrite());
            }

        });
    }

    @Override
    public Object sync(){
        return execute(ProtocolCommand.SYNC, new Executor<Jedis, Void>() {

            @Override
            public Void execute(Jedis client){
                client.sync();
                return null;
            }

        });
    }

    @Override
    public byte[] echo(byte[] str){
        return execute(ProtocolCommand.GEODIST, new Executor<Jedis, byte[]>() {

            @Override
            public byte[] execute(Jedis client){
                return client.echo(str);
            }

        }, OperationsCommandArguments.getInstance().put("str", str));
    }

    @Override
    public Status ping(){
        return execute(ProtocolCommand.PING, new Executor<Jedis, Status>() {

            @Override
            public Status execute(Jedis client){
                return returnStatus("PONG".equalsIgnoreCase(client.ping()));
            }

        });
    }

    @Override
    public Object object(final ObjectCommand command, final String key){
        return execute(ProtocolCommand.OBJECT, new Executor<Jedis, Object>() {

            @Override
            public Object execute(Jedis client){
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

    @Override
    public Object object(final ObjectCommand command, final byte[] key){
        return execute(ProtocolCommand.OBJECT, new Executor<Jedis, Object>() {

            @Override
            public Object execute(Jedis client){
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

    @Override
    public Object slowLog(final SlowLogCommand command, final String... args){
        return execute(ProtocolCommand.SLOWLOG, new Executor<Jedis, Object>() {

            @Override
            public Object execute(Jedis client){
                if(SlowLogCommand.GET == command){
                    return client.slowlogGet();
                }else if(SlowLogCommand.LEN == command){
                    return client.slowlogLen();
                }else if(SlowLogCommand.RESET == command){
                    return client.slowlogReset();
                }else{
                    return null;
                }
            }

        }, OperationsCommandArguments.getInstance().put("command", command).put("args", ArrayUtils.toString(args)));
    }

    @Override
    public Object slowLog(final SlowLogCommand command, final byte[]... args){
        return execute(ProtocolCommand.SLOWLOG, new Executor<Jedis, Object>() {

            @Override
            public Object execute(Jedis client){
                if(SlowLogCommand.GET == command){
                    return client.slowlogGet();
                }else if(SlowLogCommand.LEN == command){
                    return client.slowlogLen();
                }else if(SlowLogCommand.RESET == command){
                    return client.slowlogReset();
                }else{
                    return null;
                }
            }

        }, OperationsCommandArguments.getInstance().put("command", command).put("args", ArrayUtils.toString(args)));
    }

    @Override
    public void monitor(final RedisMonitor redisMonitor){
        execute(ProtocolCommand.MONITOR, new Executor<Jedis, Void>() {

            @Override
            public Void execute(Jedis client){
                client.monitor(new JedisMonitor() {

                    @Override
                    public void onCommand(final String command){
                        redisMonitor.onCommand(command);
                    }
                });

                return null;
            }

        });
    }

    @Override
    public String debugObject(final String key){
        return execute(ProtocolCommand.DEBUG_OBJECT, new Executor<Jedis, String>() {

            @Override
            public String execute(Jedis client){
                return client.debug(DebugParams.OBJECT(key));
            }

        });
    }

    @Override
    public String debugSegfault(){
        return execute(ProtocolCommand.DEBUG_SEGFAULT, new Executor<Jedis, String>() {

            @Override
            public String execute(Jedis client){
                return client.debug(DebugParams.SEGFAULT());
            }

        });
    }

}
