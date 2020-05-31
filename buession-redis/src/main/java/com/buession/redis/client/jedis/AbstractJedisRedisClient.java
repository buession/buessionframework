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
package com.buession.redis.client.jedis;

import com.buession.lang.Status;
import com.buession.redis.client.AbstractRedisClient;
import com.buession.redis.client.BinaryHashRedisOperations;
import com.buession.redis.client.BinaryKeyRedisOperations;
import com.buession.redis.client.BinaryListRedisOperations;
import com.buession.redis.client.BinarySetRedisOperations;
import com.buession.redis.client.BinaryStringRedisOperations;
import com.buession.redis.client.HashRedisOperations;
import com.buession.redis.client.KeyRedisOperations;
import com.buession.redis.client.ListRedisOperations;
import com.buession.redis.client.SetRedisOperations;
import com.buession.redis.client.StringRedisOperations;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.jedis.operations.DefaultBinaryHashRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultBinaryKeyRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultBinaryListRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultBinarySetRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultBinaryStringRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultHashRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultKeyRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultListRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultSetRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultStringRedisOperations;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.transaction.Transaction;
import redis.clients.jedis.commands.JedisCommands;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisClient<C extends JedisCommands> extends AbstractRedisClient implements JedisRedisClient<C> {

	protected final KeyRedisOperations keyOperations = new DefaultKeyRedisOperations<>(this);

	protected final BinaryKeyRedisOperations binaryKeyOperations = new DefaultBinaryKeyRedisOperations<>(this);

	protected final StringRedisOperations stringOperations = new DefaultStringRedisOperations<>(this);

	protected final BinaryStringRedisOperations binaryStringOperations =
			new DefaultBinaryStringRedisOperations<>(this);

	protected final HashRedisOperations hashOperations = new DefaultHashRedisOperations<>(this);

	protected final BinaryHashRedisOperations binaryHashOperations = new DefaultBinaryHashRedisOperations<>(this);

	protected final ListRedisOperations listOperations = new DefaultListRedisOperations<>(this);

	protected final BinaryListRedisOperations binaryListOperations = new DefaultBinaryListRedisOperations<>(this);

	protected final SetRedisOperations setOperations = new DefaultSetRedisOperations<>(this);

	protected final BinarySetRedisOperations binarySetOperations = new DefaultBinarySetRedisOperations<>(this);

	public AbstractJedisRedisClient(){
		super();
	}

	public AbstractJedisRedisClient(RedisConnection connection){
		super(connection);
	}

	@Override
	public boolean exists(final String key){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.exists(key));
	}

	@Override
	public boolean exists(final byte[] key){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.exists(key));
	}

	@Override
	public Type type(final String key){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.type(key));
	}

	@Override
	public Type type(final byte[] key){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.type(key));
	}

	@Override
	public Status expire(final String key, final int lifetime){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.expire(key, lifetime));
	}

	@Override
	public Status expire(final byte[] key, final int lifetime){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.expire(key, lifetime));
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.expireAt(key, unixTimestamp));
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.expireAt(key, unixTimestamp));
	}

	@Override
	public Status pExpire(final String key, final int lifetime){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.pExpire(key, lifetime));
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.pExpire(key, lifetime));
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.pExpireAt(key, unixTimestamp));
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.pExpireAt(key, unixTimestamp));
	}

	@Override
	public Long ttl(final String key){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.ttl(key));
	}

	@Override
	public Long ttl(final byte[] key){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.ttl(key));
	}

	@Override
	public Long pTtl(final String key){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.pTtl(key));
	}

	@Override
	public Long pTtl(final byte[] key){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.pTtl(key));
	}

	@Override
	public Status persist(final String key){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.persist(key));
	}

	@Override
	public Status persist(final byte[] key){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.persist(key));
	}

	@Override
	public List<String> sort(final String key){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.sort(key));
	}

	@Override
	public List<byte[]> sort(final byte[] key){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.sort(key));
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.sort(key, sortArgument));
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.sort(key, sortArgument));
	}

	@Override
	public byte[] dump(final String key){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.dump(key));
	}

	@Override
	public byte[] dump(final byte[] key){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.dump(key));
	}

	@Override
	public Status restore(final String key, final String serializedValue, final int ttl){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.restore(key, serializedValue, ttl));
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.restore(key, serializedValue, ttl));
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.migrate(key, host, port, db, timeout));
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.migrate(key, host, port, db, timeout));
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation migrateOperation){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.migrate(key, host, port, db, timeout,
				migrateOperation));
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation migrateOperation){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.migrate(key, host, port, db, timeout,
				migrateOperation));
	}

	@Override
	public Long del(final String... keys){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.del(keys));
	}

	@Override
	public Long del(final byte[]... keys){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.del(keys));
	}

	@Override
	public Status move(final String key, final int db){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.move(key, db));
	}

	@Override
	public Status move(final byte[] key, final int db){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.move(key, db));
	}

	@Override
	public Status set(final String key, final String value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.set(key, value));
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.set(key, value));
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument){
		return execute(stringOperations, (StringRedisOperations ops)->ops.set(key, value, setArgument));
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.set(key, value, setArgument));
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime){
		return execute(stringOperations, (StringRedisOperations ops)->ops.setEx(key, value, lifetime));
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.setEx(key, value, lifetime));
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime){
		return execute(stringOperations, (StringRedisOperations ops)->ops.pSetEx(key, value, lifetime));
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.pSetEx(key, value, lifetime));
	}

	@Override
	public Status setNx(final String key, final String value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.setNx(key, value));
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.setNx(key, value));
	}

	@Override
	public Long append(final String key, final String value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.append(key, value));
	}

	@Override
	public Long append(final byte[] key, final byte[] value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.append(key, value));
	}

	@Override
	public String get(final String key){
		return execute(stringOperations, (StringRedisOperations ops)->ops.get(key));
	}

	@Override
	public byte[] get(final byte[] key){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.get(key));
	}

	@Override
	public String getSet(final String key, final String value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.getSet(key, value));
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.getSet(key, value));
	}

	@Override
	public Status mSet(final Map<String, String> values){
		return execute(stringOperations, (StringRedisOperations ops)->ops.mSet(values));
	}

	@Override
	public Status mSetNx(final Map<String, String> values){
		return execute(stringOperations, (StringRedisOperations ops)->ops.mSetNx(values));
	}

	@Override
	public Long incr(final String key){
		return execute(stringOperations, (StringRedisOperations ops)->ops.incr(key));
	}

	@Override
	public Long incr(final byte[] key){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.incr(key));
	}

	@Override
	public Long incrBy(final String key, final int value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.incrBy(key, value));
	}

	@Override
	public Long incrBy(final byte[] key, final int value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.incrBy(key, value));
	}

	@Override
	public Long incrBy(final String key, final long value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.incrBy(key, value));
	}

	@Override
	public Long incrBy(final byte[] key, final long value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.incrBy(key, value));
	}

	@Override
	public Double incrByFloat(final String key, final float value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.incrByFloat(key, value));
	}

	@Override
	public Double incrByFloat(final byte[] key, final float value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.incrByFloat(key, value));
	}

	@Override
	public Double incrByFloat(final String key, final double value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.incrByFloat(key, value));
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.incrByFloat(key, value));
	}

	@Override
	public Long decr(final String key){
		return execute(stringOperations, (StringRedisOperations ops)->ops.decr(key));
	}

	@Override
	public Long decr(final byte[] key){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.decr(key));
	}

	@Override
	public Long decrBy(final String key, final int value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.decrBy(key, value));
	}

	@Override
	public Long decrBy(final byte[] key, final int value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.decrBy(key, value));
	}

	@Override
	public Long decrBy(final String key, final long value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.decrBy(key, value));
	}

	@Override
	public Long decrBy(final byte[] key, final long value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.decrBy(key, value));
	}

	@Override
	public Long setRange(final String key, final int offset, final String value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.setRange(key, offset, value));
	}

	@Override
	public Long setRange(final byte[] key, final int offset, final byte[] value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.setRange(key, offset, value));
	}

	@Override
	public Long setRange(final String key, final long offset, final String value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.setRange(key, offset, value));
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.setRange(key, offset, value));
	}

	@Override
	public String getRange(final String key, final int start, final int end){
		return execute(stringOperations, (StringRedisOperations ops)->ops.getRange(key, start, end));
	}

	@Override
	public byte[] getRange(final byte[] key, final int start, final int end){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.getRange(key, start, end));
	}

	@Override
	public String getRange(final String key, final long start, final long end){
		return execute(stringOperations, (StringRedisOperations ops)->ops.getRange(key, start, end));
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.getRange(key, start, end));
	}

	@Override
	public String substr(final String key, final int start, final int end){
		return execute(stringOperations, (StringRedisOperations ops)->ops.substr(key, start, end));
	}

	@Override
	public byte[] substr(final byte[] key, final int start, final int end){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.substr(key, start, end));
	}

	@Override
	public String substr(final String key, final long start, final long end){
		return execute(stringOperations, (StringRedisOperations ops)->ops.substr(key, start, end));
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.substr(key, start, end));
	}

	@Override
	public Long strlen(final String key){
		return execute(stringOperations, (StringRedisOperations ops)->ops.strlen(key));
	}

	@Override
	public Long strlen(final byte[] key){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.strlen(key));
	}

	@Override
	public boolean hExists(final String key, final String field){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hExists(key, field));
	}

	@Override
	public boolean hExists(final byte[] key, final byte[] field){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hExists(key, field));
	}

	@Override
	public Set<String> hKeys(final String key){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hKeys(key));
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hKeys(key));
	}

	@Override
	public List<String> hVals(final String key){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hVals(key));
	}

	@Override
	public List<byte[]> hVals(final byte[] key){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hVals(key));
	}

	@Override
	public Status hSet(final String key, final String field, final String value){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hSet(key, field, value));
	}

	@Override
	public Status hSet(final byte[] key, final byte[] field, final byte[] value){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hSet(key, field, value));
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hSetNx(key, field, value));
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hSet(key, field, value));
	}

	@Override
	public String hGet(final String key, final String field){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hGet(key, field));
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hGet(key, field));
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hMSet(key, data));
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hMSet(key, data));
	}

	@Override
	public List<String> hMGet(final String key, final String... fields){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hMGet(key, fields));
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hMGet(key, fields));
	}

	@Override
	public Map<String, String> hGetAll(final String key){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hGetAll(key));
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hGetAll(key));
	}

	@Override
	public Long hStrLen(final String key, final String field){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hStrLen(key, field));
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hStrLen(key, field));
	}

	@Override
	public Long hLen(final String key){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hLen(key));
	}

	@Override
	public Long hLen(final byte[] key){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hLen(key));
	}

	@Override
	public Long hIncrBy(final String key, final String field, final int value){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hIncrBy(key, field, value));
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final int value){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hIncrBy(key, field, value));
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hIncrBy(key, field, value));
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hIncrBy(key, field, value));
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final float value){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hIncrByFloat(key, field, value));
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final float value){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hIncrByFloat(key, field, value));
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hIncrByFloat(key, field, value));
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hIncrByFloat(key, field, value));
	}

	@Override
	public Long hDecrBy(final String key, final String field, final int value){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hDecrBy(key, field, value));
	}

	@Override
	public Long hDecrBy(final byte[] key, final byte[] field, final int value){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hDecrBy(key, field, value));
	}

	@Override
	public Long hDecrBy(final String key, final String field, final long value){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hDecrBy(key, field, value));
	}

	@Override
	public Long hDecrBy(final byte[] key, final byte[] field, final long value){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hDecrBy(key, field, value));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hScan(key, cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hScan(key, cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hScan(key, cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hScan(key, cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hScan(key, cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hScan(key, cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final String pattern){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final int count){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hScan(key, cursor, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final int count){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hScan(key, cursor, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final int count){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hScan(key, cursor, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final int count){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hScan(key, cursor, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final int count){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hScan(key, cursor, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final int count){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hScan(key, cursor, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final String pattern,
			final int count){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern,
			final int count){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern,
			final int count){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern,
			final int count){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
			final int count){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hScan(key, cursor, pattern, count));
	}

	@Override
	public Long hDel(final String key, final String... fields){
		return execute(hashOperations, (HashRedisOperations ops)->ops.hDel(key, fields));
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields){
		return execute(binaryHashOperations, (BinaryHashRedisOperations ops)->ops.hDel(key, fields));
	}

	@Override
	public Long lPush(final String key, final String... values){
		return execute(listOperations, (ListRedisOperations ops)->ops.lPush(key, values));
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.lPush(key, values));
	}

	@Override
	public Long lPushX(final String key, final String... values){
		return execute(listOperations, (ListRedisOperations ops)->ops.lPushX(key, values));
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.lPushX(key, values));
	}

	@Override
	public Long lInsert(final String key, final String value, final ListPosition position, final String pivot){
		return execute(listOperations, (ListRedisOperations ops)->ops.lInsert(key, value, position, pivot));
	}

	@Override
	public Long lInsert(final byte[] key, final byte[] value, final ListPosition position, final byte[] pivot){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.lInsert(key, value, position,
				pivot));
	}

	@Override
	public Status lSet(final String key, final int index, final String value){
		return execute(listOperations, (ListRedisOperations ops)->ops.lSet(key, index, value));
	}

	@Override
	public Status lSet(final byte[] key, final int index, final byte[] value){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.lSet(key, index, value));
	}

	@Override
	public Status lSet(final String key, final long index, final String value){
		return execute(listOperations, (ListRedisOperations ops)->ops.lSet(key, index, value));
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.lSet(key, index, value));
	}

	@Override
	public String lIndex(final String key, final int index){
		return execute(listOperations, (ListRedisOperations ops)->ops.lIndex(key, index));
	}

	@Override
	public byte[] lIndex(final byte[] key, final int index){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.lIndex(key, index));
	}

	@Override
	public String lIndex(final String key, final long index){
		return execute(listOperations, (ListRedisOperations ops)->ops.lIndex(key, index));
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.lIndex(key, index));
	}

	@Override
	public String lPop(final String key){
		return execute(listOperations, (ListRedisOperations ops)->ops.lPop(key));
	}

	@Override
	public byte[] lPop(final byte[] key){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.lPop(key));
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout){
		return execute(listOperations, (ListRedisOperations ops)->ops.blPop(keys, timeout));
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.blPop(keys, timeout));
	}

	@Override
	public String rPop(final String key){
		return execute(listOperations, (ListRedisOperations ops)->ops.rPop(key));
	}

	@Override
	public byte[] rPop(final byte[] key){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.rPop(key));
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout){
		return execute(listOperations, (ListRedisOperations ops)->ops.brPop(keys, timeout));
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.brPop(keys, timeout));
	}

	@Override
	public Long rPush(final String key, final String... values){
		return execute(listOperations, (ListRedisOperations ops)->ops.rPush(key, values));
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.rPush(key, values));
	}

	@Override
	public Long rPushX(final String key, final String... values){
		return execute(listOperations, (ListRedisOperations ops)->ops.rPushX(key, values));
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.rPushX(key, values));
	}

	@Override
	public Status lTrim(final String key, final int start, final int end){
		return execute(listOperations, (ListRedisOperations ops)->ops.lTrim(key, start, end));
	}

	@Override
	public Status lTrim(final byte[] key, final int start, final int end){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.lTrim(key, start, end));
	}

	@Override
	public Status lTrim(final String key, final long start, final long end){
		return execute(listOperations, (ListRedisOperations ops)->ops.lTrim(key, start, end));
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.lTrim(key, start, end));
	}

	@Override
	public Long lRem(final String key, final String value, final int count){
		return execute(listOperations, (ListRedisOperations ops)->ops.lRem(key, value, count));
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final int count){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.lRem(key, value, count));
	}

	@Override
	public Long lRem(final String key, final String value, final long count){
		return execute(listOperations, (ListRedisOperations ops)->ops.lRem(key, value, count));
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.lRem(key, value, count));
	}

	@Override
	public List<String> lRange(final String key, final int start, final int end){
		return execute(listOperations, (ListRedisOperations ops)->ops.lRange(key, start, end));
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final int start, final int end){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.lRange(key, start, end));
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end){
		return execute(listOperations, (ListRedisOperations ops)->ops.lRange(key, start, end));
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.lRange(key, start, end));
	}

	@Override
	public Long lLen(final String key){
		return execute(listOperations, (ListRedisOperations ops)->ops.lLen(key));
	}

	@Override
	public Long lLen(final byte[] key){
		return execute(binaryListOperations, (BinaryListRedisOperations ops)->ops.lLen(key));
	}

	@Override
	public Long sAdd(final String key, final String... members){
		return execute(setOperations, (SetRedisOperations ops)->ops.sAdd(key, members));
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members){
		return execute(binarySetOperations, (BinarySetRedisOperations ops)->ops.sAdd(key, members));
	}

	@Override
	public Long sCard(final String key){
		return execute(setOperations, (SetRedisOperations ops)->ops.sCard(key));
	}

	@Override
	public Long sCard(final byte[] key){
		return execute(binarySetOperations, (BinarySetRedisOperations ops)->ops.sCard(key));
	}

	@Override
	public boolean sisMember(final String key, final String member){
		return execute(setOperations, (SetRedisOperations ops)->ops.sisMember(key, member));
	}

	@Override
	public boolean sisMember(final byte[] key, final byte[] member){
		return execute(binarySetOperations, (BinarySetRedisOperations ops)->ops.sisMember(key, member));
	}

	@Override
	public Set<String> sMembers(final String key){
		return execute(setOperations, (SetRedisOperations ops)->ops.sMembers(key));
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		return execute(binarySetOperations, (BinarySetRedisOperations ops)->ops.sMembers(key));
	}

	@Override
	public String sPop(final String key){
		return execute(setOperations, (SetRedisOperations ops)->ops.sPop(key));
	}

	@Override
	public byte[] sPop(final byte[] key){
		return execute(binarySetOperations, (BinarySetRedisOperations ops)->ops.sPop(key));
	}

	@Override
	public String sRandMember(final String key){
		return execute(setOperations, (SetRedisOperations ops)->ops.sRandMember(key));
	}

	@Override
	public byte[] sRandMember(final byte[] key){
		return execute(binarySetOperations, (BinarySetRedisOperations ops)->ops.sRandMember(key));
	}

	@Override
	public List<String> sRandMember(final String key, final int count){
		return execute(setOperations, (SetRedisOperations ops)->ops.sRandMember(key, count));
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final int count){
		return execute(binarySetOperations, (BinarySetRedisOperations ops)->ops.sRandMember(key, count));
	}

	@Override
	public List<String> sRandMember(final String key, final long count){
		return execute(setOperations, (SetRedisOperations ops)->ops.sRandMember(key, count));
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final long count){
		return execute(binarySetOperations, (BinarySetRedisOperations ops)->ops.sRandMember(key, count));
	}

	@Override
	public Long sRem(final String key, final String... members){
		return execute(setOperations, (SetRedisOperations ops)->ops.sRem(key, members));
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members){
		return execute(binarySetOperations, (BinarySetRedisOperations ops)->ops.sRem(key, members));
	}

	@Override
	public Set<String> sDiff(final String... keys){
		return execute(setOperations, (SetRedisOperations ops)->ops.sDiff(keys));
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys){
		return execute(binarySetOperations, (BinarySetRedisOperations ops)->ops.sDiff(keys));
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys){
		return execute(setOperations, (SetRedisOperations ops)->ops.sDiffStore(destKey, keys));
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys){
		return execute(binarySetOperations, (BinarySetRedisOperations ops)->ops.sDiffStore(destKey, keys));
	}

	@Override
	public Set<String> sInter(final String... keys){
		return execute(setOperations, (SetRedisOperations ops)->ops.sInter(keys));
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys){
		return execute(binarySetOperations, (BinarySetRedisOperations ops)->ops.sInter(keys));
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys){
		return execute(setOperations, (SetRedisOperations ops)->ops.sInterStore(destKey, keys));
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys){
		return execute(binarySetOperations, (BinarySetRedisOperations ops)->ops.sInterStore(destKey, keys));
	}

}
