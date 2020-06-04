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

import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.BinaryHashCommands;
import com.buession.redis.core.command.BinaryKeyCommands;
import com.buession.redis.core.command.BinaryListCommands;
import com.buession.redis.core.command.BinarySetCommands;
import com.buession.redis.core.command.BinarySortedSetCommands;
import com.buession.redis.core.command.BinaryStringCommands;
import com.buession.redis.core.command.HashCommands;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.command.ListCommands;
import com.buession.redis.core.command.SetCommands;
import com.buession.redis.core.command.SortedSetCommands;
import com.buession.redis.core.command.StringCommands;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis 基本操作 Template
 *
 * @author Yong.Teng
 */
public class BaseRedisTemplate extends RedisAccessor implements KeyCommands, BinaryKeyCommands, StringCommands,
		BinaryStringCommands, HashCommands, BinaryHashCommands, ListCommands, BinaryListCommands, SetCommands,
		BinarySetCommands, SortedSetCommands, BinarySortedSetCommands {

	/**
	 * 构造函数
	 */
	public BaseRedisTemplate(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param connection
	 * 		Redis 连接对称
	 */
	public BaseRedisTemplate(RedisConnection connection){
		super(connection);
	}

	@Override
	public boolean exists(final String key){
		return execute((RedisClient client)->client.exists(makeRawKey(key)));
	}

	@Override
	public boolean exists(final byte[] key){
		return execute((RedisClient client)->client.exists(makeByteKey(key)));
	}

	@Override
	public Type type(final String key){
		return execute((RedisClient client)->client.type(makeRawKey(key)));
	}

	@Override
	public Type type(final byte[] key){
		return execute((RedisClient client)->client.type(makeByteKey(key)));
	}

	@Override
	public Status rename(final String key, final String newKey){
		return execute((RedisClient client)->client.rename(makeRawKey(key), makeRawKey(newKey)));
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
		return execute((RedisClient client)->client.rename(makeByteKey(key), makeByteKey(newKey)));
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		return execute((RedisClient client)->client.renameNx(makeRawKey(key), makeRawKey(newKey)));
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		return execute((RedisClient client)->client.renameNx(makeByteKey(key), makeByteKey(newKey)));
	}

	@Override
	public String randomKey(){
		return execute((RedisClient client)->client.randomKey());
	}

	@Override
	public Set<String> keys(final String pattern){
		return execute((RedisClient client)->client.keys(pattern));
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern){
		return execute((RedisClient client)->client.keys(pattern));
	}

	@Override
	public Status expire(final String key, final int lifetime){
		return execute((RedisClient client)->client.expire(makeRawKey(key), lifetime));
	}

	@Override
	public Status expire(final byte[] key, final int lifetime){
		return execute((RedisClient client)->client.expire(makeByteKey(key), lifetime));
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp){
		return execute((RedisClient client)->client.expireAt(makeRawKey(key), unixTimestamp));
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		return execute((RedisClient client)->client.expireAt(makeByteKey(key), unixTimestamp));
	}

	@Override
	public Status pExpire(final String key, final int lifetime){
		return execute((RedisClient client)->client.pExpire(makeRawKey(key), lifetime));
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		return execute((RedisClient client)->client.pExpire(makeByteKey(key), lifetime));
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp){
		return execute((RedisClient client)->client.pExpireAt(makeRawKey(key), unixTimestamp));
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		return execute((RedisClient client)->client.pExpireAt(makeByteKey(key), unixTimestamp));
	}

	@Override
	public Long ttl(final String key){
		return execute((RedisClient client)->client.ttl(makeRawKey(key)));
	}

	@Override
	public Long ttl(final byte[] key){
		return execute((RedisClient client)->client.ttl(makeByteKey(key)));
	}

	@Override
	public Long pTtl(final String key){
		return execute((RedisClient client)->client.pTtl(makeRawKey(key)));
	}

	@Override
	public Long pTtl(final byte[] key){
		return execute((RedisClient client)->client.pTtl(makeByteKey(key)));
	}

	@Override
	public Status persist(final String key){
		return execute((RedisClient client)->client.persist(makeRawKey(key)));
	}

	@Override
	public Status persist(final byte[] key){
		return execute((RedisClient client)->client.persist(makeByteKey(key)));
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor){
		return execute((RedisClient client)->client.scan(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor){
		return execute((RedisClient client)->client.scan(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor){
		return execute((RedisClient client)->client.scan(cursor));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		return execute((RedisClient client)->client.scan(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor, final String pattern){
		return execute((RedisClient client)->client.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final int cursor, final byte[] pattern){
		return execute((RedisClient client)->client.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern){
		return execute((RedisClient client)->client.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern){
		return execute((RedisClient client)->client.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern){
		return execute((RedisClient client)->client.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		return execute((RedisClient client)->client.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor, final int count){
		return execute((RedisClient client)->client.scan(cursor, count));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final int count){
		return execute((RedisClient client)->client.scan(cursor, count));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final int count){
		return execute((RedisClient client)->client.scan(cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count){
		return execute((RedisClient client)->client.scan(cursor, count));
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor, final String pattern, final int count){
		return execute((RedisClient client)->client.scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final int cursor, final byte[] pattern, final int count){
		return execute((RedisClient client)->client.scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern, final int count){
		return execute((RedisClient client)->client.scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern, final int count){
		return execute((RedisClient client)->client.scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count){
		return execute((RedisClient client)->client.scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count){
		return execute((RedisClient client)->client.scan(cursor, pattern, count));
	}

	@Override
	public List<String> sort(final String key){
		return execute((RedisClient client)->client.sort(makeRawKey(key)));
	}

	@Override
	public List<byte[]> sort(final byte[] key){
		return execute((RedisClient client)->client.sort(makeByteKey(key)));
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument){
		return execute((RedisClient client)->client.sort(makeRawKey(key), sortArgument));
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument){
		return execute((RedisClient client)->client.sort(makeByteKey(key), sortArgument));
	}

	@Override
	public Long sort(final String key, final String destKey){
		return execute((RedisClient client)->client.sort(makeRawKey(key), makeRawKey(destKey)));
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey){
		return execute((RedisClient client)->client.sort(makeByteKey(key), makeByteKey(destKey)));
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument){
		return execute((RedisClient client)->client.sort(makeRawKey(key), makeRawKey(destKey), sortArgument));
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		return execute((RedisClient client)->client.sort(makeByteKey(key), makeByteKey(destKey), sortArgument));
	}

	@Override
	public byte[] dump(final String key){
		return execute((RedisClient client)->client.dump(makeRawKey(key)));
	}

	@Override
	public byte[] dump(final byte[] key){
		return execute((RedisClient client)->client.dump(makeByteKey(key)));
	}

	@Override
	public Status restore(final String key, final String serializedValue, final int ttl){
		return execute((RedisClient client)->client.restore(makeRawKey(key), serializedValue, ttl));
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		return execute((RedisClient client)->client.restore(makeByteKey(key), serializedValue, ttl));
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
		return execute((RedisClient client)->client.migrate(makeRawKey(key), host, port, db, timeout));
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
		return execute((RedisClient client)->client.migrate(makeByteKey(key), host, port, db, timeout));
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation migrateOperation){
		return execute((RedisClient client)->client.migrate(makeRawKey(key), host, port, db, timeout,
				migrateOperation));
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation migrateOperation){
		return execute((RedisClient client)->client.migrate(makeByteKey(key), host, port, db, timeout,
				migrateOperation));
	}

	@Override
	public Long del(final String... keys){
		return execute((RedisClient client)->client.del(makeRawKeys(keys)));
	}

	@Override
	public Long del(final byte[]... keys){
		return execute((RedisClient client)->client.del(makeByteKeys(keys)));
	}

	@Override
	public Status move(final String key, final int db){
		return execute((RedisClient client)->client.move(makeRawKey(key), db));
	}

	@Override
	public Status move(final byte[] key, final int db){
		return execute((RedisClient client)->client.move(makeByteKey(key), db));
	}

	@Override
	public Status set(final String key, final String value){
		return execute((RedisClient client)->client.set(makeRawKey(key), value));
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		return execute((RedisClient client)->client.set(makeByteKey(key), value));
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument){
		return execute((RedisClient client)->client.set(makeRawKey(key), value, setArgument));
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument){
		return execute((RedisClient client)->client.set(makeByteKey(key), value, setArgument));
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime){
		return execute((RedisClient client)->client.setEx(makeRawKey(key), value, lifetime));
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		return execute((RedisClient client)->client.setEx(makeByteKey(key), value, lifetime));
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime){
		return execute((RedisClient client)->client.pSetEx(makeRawKey(key), value, lifetime));
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		return execute((RedisClient client)->client.pSetEx(makeByteKey(key), value, lifetime));
	}

	@Override
	public Status setNx(final String key, final String value){
		return execute((RedisClient client)->client.setNx(makeRawKey(key), value));
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		return execute((RedisClient client)->client.setNx(makeByteKey(key), value));
	}

	@Override
	public Long append(final String key, final String value){
		return execute((RedisClient client)->client.append(makeRawKey(key), value));
	}

	@Override
	public Long append(final byte[] key, final byte[] value){
		return execute((RedisClient client)->client.append(makeByteKey(key), value));
	}

	@Override
	public String get(final String key){
		return execute((RedisClient client)->client.get(makeRawKey(key)));
	}

	@Override
	public byte[] get(final byte[] key){
		return execute((RedisClient client)->client.get(makeByteKey(key)));
	}

	@Override
	public String getSet(final String key, final String value){
		return execute((RedisClient client)->client.getSet(makeRawKey(key), value));
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		return execute((RedisClient client)->client.getSet(makeByteKey(key), value));
	}

	@Override
	public Status mSet(final Map<String, String> values){
		return execute((RedisClient client)->client.mSet(values));
	}

	@Override
	public Status mSetNx(final Map<String, String> values){
		return execute((RedisClient client)->client.mSetNx(values));
	}

	@Override
	public List<String> mGet(final String... keys){
		return execute((RedisClient client)->client.mGet(makeRawKeys(keys)));
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys){
		return execute((RedisClient client)->client.mGet(makeByteKeys(keys)));
	}

	@Override
	public Long incr(final String key){
		return execute((RedisClient client)->client.incr(makeRawKey(key)));
	}

	@Override
	public Long incr(final byte[] key){
		return execute((RedisClient client)->client.incr(makeByteKey(key)));
	}

	@Override
	public Long incrBy(final String key, final int value){
		return execute((RedisClient client)->client.incrBy(makeRawKey(key), value));
	}

	@Override
	public Long incrBy(final byte[] key, final int value){
		return execute((RedisClient client)->client.incrBy(makeByteKey(key), value));
	}

	@Override
	public Long incrBy(final String key, final long value){
		return execute((RedisClient client)->client.incrBy(makeRawKey(key), value));
	}

	@Override
	public Long incrBy(final byte[] key, final long value){
		return execute((RedisClient client)->client.incrBy(makeByteKey(key), value));
	}

	@Override
	public Double incrByFloat(final String key, final float value){
		return execute((RedisClient client)->client.incrByFloat(makeRawKey(key), value));
	}

	@Override
	public Double incrByFloat(final byte[] key, final float value){
		return execute((RedisClient client)->client.incrByFloat(makeByteKey(key), value));
	}

	@Override
	public Double incrByFloat(final String key, final double value){
		return execute((RedisClient client)->client.incrByFloat(makeRawKey(key), value));
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value){
		return execute((RedisClient client)->client.incrByFloat(makeByteKey(key), value));
	}

	@Override
	public Long decr(final String key){
		return execute((RedisClient client)->client.decr(makeRawKey(key)));
	}

	@Override
	public Long decr(final byte[] key){
		return execute((RedisClient client)->client.decr(makeByteKey(key)));
	}

	@Override
	public Long decrBy(final String key, final int value){
		return execute((RedisClient client)->client.decrBy(makeRawKey(key), value));
	}

	@Override
	public Long decrBy(final byte[] key, final int value){
		return execute((RedisClient client)->client.decrBy(makeByteKey(key), value));
	}

	@Override
	public Long decrBy(final String key, final long value){
		return execute((RedisClient client)->client.decrBy(makeRawKey(key), value));
	}

	@Override
	public Long decrBy(final byte[] key, final long value){
		return execute((RedisClient client)->client.decrBy(makeByteKey(key), value));
	}

	@Override
	public Long setRange(final String key, final int offset, final String value){
		return execute((RedisClient client)->client.setRange(makeRawKey(key), offset, value));
	}

	@Override
	public Long setRange(final byte[] key, final int offset, final byte[] value){
		return execute((RedisClient client)->client.setRange(makeByteKey(key), offset, value));
	}

	@Override
	public Long setRange(final String key, final long offset, final String value){
		return execute((RedisClient client)->client.setRange(makeRawKey(key), offset, value));
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value){
		return execute((RedisClient client)->client.setRange(makeByteKey(key), offset, value));
	}

	@Override
	public String getRange(final String key, final int start, final int end){
		return execute((RedisClient client)->client.getRange(makeRawKey(key), start, end));
	}

	@Override
	public byte[] getRange(final byte[] key, final int start, final int end){
		return execute((RedisClient client)->client.getRange(makeByteKey(key), start, end));
	}

	@Override
	public String getRange(final String key, final long start, final long end){
		return execute((RedisClient client)->client.getRange(makeRawKey(key), start, end));
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		return execute((RedisClient client)->client.getRange(makeByteKey(key), start, end));
	}

	@Override
	public String substr(final String key, final int start, final int end){
		return execute((RedisClient client)->client.substr(makeRawKey(key), start, end));
	}

	@Override
	public byte[] substr(final byte[] key, final int start, final int end){
		return execute((RedisClient client)->client.substr(makeByteKey(key), start, end));
	}

	@Override
	public String substr(final String key, final long start, final long end){
		return execute((RedisClient client)->client.substr(makeRawKey(key), start, end));
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end){
		return execute((RedisClient client)->client.substr(makeByteKey(key), start, end));
	}

	@Override
	public Long strlen(final String key){
		return execute((RedisClient client)->client.strlen(makeRawKey(key)));
	}

	@Override
	public Long strlen(final byte[] key){
		return execute((RedisClient client)->client.strlen(makeByteKey(key)));
	}

	@Override
	public boolean hExists(final String key, final String field){
		return execute((RedisClient client)->client.hExists(makeRawKey(key), field));
	}

	@Override
	public boolean hExists(final byte[] key, final byte[] field){
		return execute((RedisClient client)->client.hExists(makeByteKey(key), field));
	}

	@Override
	public Set<String> hKeys(final String key){
		return execute((RedisClient client)->client.hKeys(makeRawKey(key)));
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key){
		return execute((RedisClient client)->client.hKeys(makeByteKey(key)));
	}

	@Override
	public List<String> hVals(final String key){
		return execute((RedisClient client)->client.hVals(makeRawKey(key)));
	}

	@Override
	public List<byte[]> hVals(final byte[] key){
		return execute((RedisClient client)->client.hVals(makeByteKey(key)));
	}

	@Override
	public Status hSet(final String key, final String field, final String value){
		return execute((RedisClient client)->client.hSet(makeRawKey(key), field, value));
	}

	@Override
	public Status hSet(final byte[] key, final byte[] field, final byte[] value){
		return execute((RedisClient client)->client.hSet(makeByteKey(key), field, value));
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value){
		return execute((RedisClient client)->client.hSetNx(makeRawKey(key), field, value));
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value){
		return execute((RedisClient client)->client.hSetNx(makeByteKey(key), field, value));
	}

	@Override
	public String hGet(final String key, final String field){
		return execute((RedisClient client)->client.hGet(makeRawKey(key), field));
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field){
		return execute((RedisClient client)->client.hGet(makeByteKey(key), field));
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data){
		return execute((RedisClient client)->client.hMSet(makeRawKey(key), data));
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data){
		return execute((RedisClient client)->client.hMSet(key, data));
	}

	@Override
	public List<String> hMGet(final String key, final String... fields){
		return execute((RedisClient client)->client.hMGet(makeRawKey(key), fields));
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields){
		return execute((RedisClient client)->client.hMGet(makeByteKey(key), fields));
	}

	@Override
	public Map<String, String> hGetAll(final String key){
		return execute((RedisClient client)->client.hGetAll(makeRawKey(key)));
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key){
		return execute((RedisClient client)->client.hGetAll(makeByteKey(key)));
	}

	@Override
	public Long hStrLen(final String key, final String field){
		return execute((RedisClient client)->client.hStrLen(makeRawKey(key), field));
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field){
		return execute((RedisClient client)->client.hStrLen(makeByteKey(key), field));
	}

	@Override
	public Long hLen(final String key){
		return execute((RedisClient client)->client.hLen(makeRawKey(key)));
	}

	@Override
	public Long hLen(final byte[] key){
		return execute((RedisClient client)->client.hLen(makeByteKey(key)));
	}

	@Override
	public Long hIncrBy(final String key, final String field, final int value){
		return execute((RedisClient client)->client.hIncrBy(makeRawKey(key), field, value));
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final int value){
		return execute((RedisClient client)->client.hIncrBy(makeByteKey(key), field, value));
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value){
		return execute((RedisClient client)->client.hIncrBy(makeRawKey(key), field, value));
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value){
		return execute((RedisClient client)->client.hIncrBy(makeByteKey(key), field, value));
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final float value){
		return execute((RedisClient client)->client.hIncrByFloat(makeRawKey(key), field, value));
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final float value){
		return execute((RedisClient client)->client.hIncrByFloat(makeByteKey(key), field, value));
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value){
		return execute((RedisClient client)->client.hIncrByFloat(makeRawKey(key), field, value));
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value){
		return execute((RedisClient client)->client.hIncrByFloat(makeByteKey(key), field, value));
	}

	@Override
	public Long hDecrBy(final String key, final String field, final int value){
		return execute((RedisClient client)->client.hDecrBy(makeRawKey(key), field, value));
	}

	@Override
	public Long hDecrBy(final byte[] key, final byte[] field, final int value){
		return execute((RedisClient client)->client.hDecrBy(makeByteKey(key), field, value));
	}

	@Override
	public Long hDecrBy(final String key, final String field, final long value){
		return execute((RedisClient client)->client.hDecrBy(makeRawKey(key), field, value));
	}

	@Override
	public Long hDecrBy(final byte[] key, final byte[] field, final long value){
		return execute((RedisClient client)->client.hDecrBy(makeByteKey(key), field, value));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor){
		return execute((RedisClient client)->client.hScan(makeRawKey(key), cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor){
		return execute((RedisClient client)->client.hScan(makeByteKey(key), cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor){
		return execute((RedisClient client)->client.hScan(makeRawKey(key), cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor){
		return execute((RedisClient client)->client.hScan(makeByteKey(key), cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor){
		return execute((RedisClient client)->client.hScan(makeRawKey(key), cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
		return execute((RedisClient client)->client.hScan(makeByteKey(key), cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final String pattern){
		return execute((RedisClient client)->client.hScan(makeRawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern){
		return execute((RedisClient client)->client.hScan(makeByteKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern){
		return execute((RedisClient client)->client.hScan(makeRawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern){
		return execute((RedisClient client)->client.hScan(makeByteKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern){
		return execute((RedisClient client)->client.hScan(makeRawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute((RedisClient client)->client.hScan(makeByteKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final int count){
		return execute((RedisClient client)->client.hScan(makeRawKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final int count){
		return execute((RedisClient client)->client.hScan(makeByteKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final int count){
		return execute((RedisClient client)->client.hScan(makeRawKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final int count){
		return execute((RedisClient client)->client.hScan(makeByteKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final int count){
		return execute((RedisClient client)->client.hScan(makeRawKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final int count){
		return execute((RedisClient client)->client.hScan(makeByteKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final String pattern,
			final int count){
		return execute((RedisClient client)->client.hScan(makeRawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern,
			final int count){
		return execute((RedisClient client)->client.hScan(makeByteKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern,
			final int count){
		return execute((RedisClient client)->client.hScan(makeRawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern,
			final int count){
		return execute((RedisClient client)->client.hScan(makeByteKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
			final int count){
		return execute((RedisClient client)->client.hScan(makeRawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		return execute((RedisClient client)->client.hScan(makeByteKey(key), cursor, pattern, count));
	}

	@Override
	public Long hDel(final String key, final String... fields){
		return execute((RedisClient client)->client.hDel(makeRawKey(key), fields));
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields){
		return execute((RedisClient client)->client.hDel(makeByteKey(key), fields));
	}

	@Override
	public Long lPush(final String key, final String... values){
		return execute((RedisClient client)->client.lPush(makeRawKey(key), values));
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values){
		return execute((RedisClient client)->client.lPush(makeByteKey(key), values));
	}

	@Override
	public Long lPushX(final String key, final String... values){
		return execute((RedisClient client)->client.lPushX(makeRawKey(key), values));
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values){
		return execute((RedisClient client)->client.lPushX(makeByteKey(key), values));
	}

	@Override
	public Long lInsert(final String key, final String value, final ListPosition position, final String pivot){
		return execute((RedisClient client)->client.lInsert(makeRawKey(key), value, position, pivot));
	}

	@Override
	public Long lInsert(final byte[] key, final byte[] value, final ListPosition position, final byte[] pivot){
		return execute((RedisClient client)->client.lInsert(makeByteKey(key), value, position, pivot));
	}

	@Override
	public Status lSet(final String key, final int index, final String value){
		return execute((RedisClient client)->client.lSet(makeRawKey(key), index, value));
	}

	@Override
	public Status lSet(final byte[] key, final int index, final byte[] value){
		return execute((RedisClient client)->client.lSet(makeByteKey(key), index, value));
	}

	@Override
	public Status lSet(final String key, final long index, final String value){
		return execute((RedisClient client)->client.lSet(makeRawKey(key), index, value));
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value){
		return execute((RedisClient client)->client.lSet(makeByteKey(key), index, value));
	}

	@Override
	public String lIndex(final String key, final int index){
		return execute((RedisClient client)->client.lIndex(makeRawKey(key), index));
	}

	@Override
	public byte[] lIndex(final byte[] key, final int index){
		return execute((RedisClient client)->client.lIndex(makeByteKey(key), index));
	}

	@Override
	public String lIndex(final String key, final long index){
		return execute((RedisClient client)->client.lIndex(makeRawKey(key), index));
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index){
		return execute((RedisClient client)->client.lIndex(makeByteKey(key), index));
	}

	@Override
	public String lPop(final String key){
		return execute((RedisClient client)->client.lPop(makeRawKey(key)));
	}

	@Override
	public byte[] lPop(final byte[] key){
		return execute((RedisClient client)->client.lPop(makeByteKey(key)));
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout){
		return execute((RedisClient client)->client.blPop(makeRawKeys(keys), timeout));
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout){
		return execute((RedisClient client)->client.blPop(makeByteKeys(keys), timeout));
	}

	@Override
	public String rPop(final String key){
		return execute((RedisClient client)->client.rPop(makeRawKey(key)));
	}

	@Override
	public byte[] rPop(final byte[] key){
		return execute((RedisClient client)->client.rPop(makeByteKey(key)));
	}

	@Override
	public String rPoplPush(final String source, final String destKey){
		return execute((RedisClient client)->client.rPoplPush(makeRawKey(source), makeRawKey(destKey)));
	}

	@Override
	public byte[] rPoplPush(final byte[] source, final byte[] destKey){
		return execute((RedisClient client)->client.rPoplPush(makeByteKey(source), makeByteKey(destKey)));
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout){
		return execute((RedisClient client)->client.brPop(makeRawKeys(keys), timeout));
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout){
		return execute((RedisClient client)->client.brPop(makeByteKeys(keys), timeout));
	}

	@Override
	public String brPoplPush(final String source, final String destKey, final int timeout){
		return client.brPoplPush(makeRawKey(source), makeRawKey(destKey), timeout);
	}

	@Override
	public byte[] brPoplPush(final byte[] source, final byte[] destKey, final int timeout){
		return client.brPoplPush(makeByteKey(source), makeByteKey(destKey), timeout);
	}

	@Override
	public Long rPush(final String key, final String... values){
		return execute((RedisClient client)->client.rPush(makeRawKey(key), values));
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values){
		return execute((RedisClient client)->client.rPush(makeByteKey(key), values));
	}

	@Override
	public Long rPushX(final String key, final String... values){
		return execute((RedisClient client)->client.rPushX(makeRawKey(key), values));
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values){
		return execute((RedisClient client)->client.rPushX(makeByteKey(key), values));
	}

	@Override
	public Status lTrim(final String key, final int start, final int end){
		return execute((RedisClient client)->client.lTrim(makeRawKey(key), start, end));
	}

	@Override
	public Status lTrim(final byte[] key, final int start, final int end){
		return execute((RedisClient client)->client.lTrim(makeByteKey(key), start, end));
	}

	@Override
	public Status lTrim(final String key, final long start, final long end){
		return execute((RedisClient client)->client.lTrim(makeRawKey(key), start, end));
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end){
		return execute((RedisClient client)->client.lTrim(makeByteKey(key), start, end));
	}

	@Override
	public Long lRem(final String key, final String value, final int count){
		return execute((RedisClient client)->client.lRem(makeRawKey(key), value, count));
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final int count){
		return execute((RedisClient client)->client.lRem(makeByteKey(key), value, count));
	}

	@Override
	public Long lRem(final String key, final String value, final long count){
		return execute((RedisClient client)->client.lRem(makeRawKey(key), value, count));
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count){
		return execute((RedisClient client)->client.lRem(makeByteKey(key), value, count));
	}

	@Override
	public List<String> lRange(final String key, final int start, final int end){
		return execute((RedisClient client)->client.lRange(makeRawKey(key), start, end));
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final int start, final int end){
		return execute((RedisClient client)->client.lRange(makeByteKey(key), start, end));
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end){
		return execute((RedisClient client)->client.lRange(makeRawKey(key), start, end));
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end){
		return execute((RedisClient client)->client.lRange(makeByteKey(key), start, end));
	}

	@Override
	public Long lLen(final String key){
		return execute((RedisClient client)->client.lLen(makeRawKey(key)));
	}

	@Override
	public Long lLen(final byte[] key){
		return execute((RedisClient client)->client.lLen(makeByteKey(key)));
	}

	@Override
	public Long sAdd(final String key, final String... members){
		return execute((RedisClient client)->client.sAdd(makeRawKey(key), members));
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members){
		return execute((RedisClient client)->client.sAdd(makeByteKey(key), members));
	}

	@Override
	public Long sCard(final String key){
		return execute((RedisClient client)->client.sCard(makeRawKey(key)));
	}

	@Override
	public Long sCard(final byte[] key){
		return execute((RedisClient client)->client.sCard(makeByteKey(key)));
	}

	@Override
	public boolean sisMember(final String key, final String member){
		return execute((RedisClient client)->client.sisMember(makeRawKey(key), member));
	}

	@Override
	public boolean sisMember(final byte[] key, final byte[] member){
		return execute((RedisClient client)->client.sisMember(makeByteKey(key), member));
	}

	@Override
	public Set<String> sMembers(final String key){
		return execute((RedisClient client)->client.sMembers(makeRawKey(key)));
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		return execute((RedisClient client)->client.sMembers(makeByteKey(key)));
	}

	@Override
	public String sPop(final String key){
		return execute((RedisClient client)->client.sPop(makeRawKey(key)));
	}

	@Override
	public byte[] sPop(final byte[] key){
		return execute((RedisClient client)->client.sPop(makeByteKey(key)));
	}

	@Override
	public String sRandMember(final String key){
		return execute((RedisClient client)->client.sRandMember(makeRawKey(key)));
	}

	@Override
	public byte[] sRandMember(final byte[] key){
		return execute((RedisClient client)->client.sRandMember(makeByteKey(key)));
	}

	@Override
	public List<String> sRandMember(final String key, final int count){
		return execute((RedisClient client)->client.sRandMember(makeRawKey(key), count));
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final int count){
		return execute((RedisClient client)->client.sRandMember(makeByteKey(key), count));
	}

	@Override
	public List<String> sRandMember(final String key, final long count){
		return execute((RedisClient client)->client.sRandMember(makeRawKey(key), count));
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final long count){
		return execute((RedisClient client)->client.sRandMember(makeByteKey(key), count));
	}

	@Override
	public Long sRem(final String key, final String... members){
		return execute((RedisClient client)->client.sRem(makeRawKey(key), members));
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members){
		return execute((RedisClient client)->client.sRem(makeByteKey(key), members));
	}

	@Override
	public Set<String> sDiff(final String... keys){
		return execute((RedisClient client)->client.sDiff(makeRawKeys(keys)));
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys){
		return execute((RedisClient client)->client.sDiff(makeByteKeys(keys)));
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys){
		return execute((RedisClient client)->client.sDiffStore(makeRawKey(destKey), makeRawKeys(keys)));
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys){
		return execute((RedisClient client)->client.sDiffStore(makeByteKey(destKey), makeByteKeys(keys)));
	}

	@Override
	public Set<String> sInter(final String... keys){
		return execute((RedisClient client)->client.sInter(makeRawKeys(keys)));
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys){
		return execute((RedisClient client)->client.sInter(makeByteKeys(keys)));
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys){
		return execute((RedisClient client)->client.sDiffStore(makeRawKey(destKey), makeRawKeys(keys)));
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys){
		return execute((RedisClient client)->client.sDiffStore(makeByteKey(destKey), makeByteKeys(keys)));
	}

	@Override
	public Set<String> sUnion(final String... keys){
		return execute((RedisClient client)->client.sUnion(makeRawKeys(keys)));
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys){
		return execute((RedisClient client)->client.sUnion(makeByteKeys(keys)));
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys){
		return execute((RedisClient client)->client.sUnionStore(makeRawKey(destKey), makeRawKeys(keys)));
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys){
		return execute((RedisClient client)->client.sUnionStore(makeByteKey(destKey), makeByteKeys(keys)));
	}

	@Override
	public Status sMove(final String source, final String destKey, final String member){
		return execute((RedisClient client)->client.sMove(makeRawKey(source), makeRawKey(destKey), member));
	}

	@Override
	public Status sMove(final byte[] source, final byte[] destKey, final byte[] member){
		return execute((RedisClient client)->client.sMove(makeByteKey(source), makeByteKey(destKey), member));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final int count){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final int count){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final int count){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final int count){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final int count){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern, final int count){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern, final int count){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern, final int count){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
			final int count){
		return execute((RedisClient client)->client.sScan(makeRawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		return execute((RedisClient client)->client.sScan(makeByteKey(key), cursor, pattern, count));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Number> members){
		return execute((RedisClient client)->client.zAdd(makeRawKey(key), members));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Number> members){
		return execute((RedisClient client)->client.zAdd(makeByteKey(key), members));
	}

	@Override
	public Long zAdd(final String key, final List<KeyValue<String, Number>> members){
		return execute((RedisClient client)->client.zAdd(makeRawKey(key), members));
	}

	@Override
	public Long zAdd(final byte[] key, final List<KeyValue<byte[], Number>> members){
		return execute((RedisClient client)->client.zAdd(makeByteKey(key), members));
	}

	@Override
	public Double zScore(final String key, final String member){
		return execute((RedisClient client)->client.zScore(makeRawKey(key), member));
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member){
		return execute((RedisClient client)->client.zScore(makeByteKey(key), member));
	}

	@Override
	public Long zCard(final String key){
		return execute((RedisClient client)->client.zCard(makeRawKey(key)));
	}

	@Override
	public Long zCard(final byte[] key){
		return execute((RedisClient client)->client.zCard(makeByteKey(key)));
	}

	@Override
	public Double zIncrBy(final String key, final String member, final float increment){
		return execute((RedisClient client)->client.zIncrBy(makeRawKey(key), member, increment));
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final float increment){
		return execute((RedisClient client)->client.zIncrBy(makeByteKey(key), member, increment));
	}

	@Override
	public Double zIncrBy(final String key, final String member, final double increment){
		return execute((RedisClient client)->client.zIncrBy(makeRawKey(key), member, increment));
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final double increment){
		return execute((RedisClient client)->client.zIncrBy(makeByteKey(key), member, increment));
	}

	@Override
	public Double zIncrBy(final String key, final String member, final int increment){
		return execute((RedisClient client)->client.zIncrBy(makeRawKey(key), member, increment));
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final int increment){
		return execute((RedisClient client)->client.zIncrBy(makeByteKey(key), member, increment));
	}

	@Override
	public Double zIncrBy(final String key, final String member, final long increment){
		return execute((RedisClient client)->client.zIncrBy(makeRawKey(key), member, increment));
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final long increment){
		return execute((RedisClient client)->client.zIncrBy(makeByteKey(key), member, increment));
	}

	@Override
	public Long zCount(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zCount(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zCount(makeByteKey(key), min, max));
	}

	@Override
	public Long zCount(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zCount(makeByteKey(key), min, max));
	}

	@Override
	public Long zCount(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zCount(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zCount(makeByteKey(key), min, max));
	}

	@Override
	public Long zCount(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zCount(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zCount(makeByteKey(key), min, max));
	}

	@Override
	public Long zCount(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zCount(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zCount(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRange(final String key, final int start, final int end){
		return execute((RedisClient client)->client.zRange(makeRawKey(key), start, end));
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final int start, final int end){
		return execute((RedisClient client)->client.zRange(makeByteKey(key), start, end));
	}

	@Override
	public Set<String> zRange(final String key, final long start, final long end){
		return execute((RedisClient client)->client.zRange(makeRawKey(key), start, end));
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final long start, final long end){
		return execute((RedisClient client)->client.zRange(makeByteKey(key), start, end));
	}

	@Override
	public Set<Tuple> zRangeWithScores(final String key, final int start, final int end){
		return execute((RedisClient client)->client.zRangeWithScores(makeRawKey(key), start, end));
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final int start, final int end){
		return execute((RedisClient client)->client.zRangeWithScores(makeByteKey(key), start, end));
	}

	@Override
	public Set<Tuple> zRangeWithScores(final String key, final long start, final long end){
		return execute((RedisClient client)->client.zRangeWithScores(makeRawKey(key), start, end));
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
		return execute((RedisClient client)->client.zRangeWithScores(makeByteKey(key), start, end));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset,
				count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset,
				count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset,
				count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset,
				count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset,
				count));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final int min, final int max, final int offset, final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final int min, final int max, final int offset, final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Long zRank(final String key, final String member){
		return execute((RedisClient client)->client.zRank(makeRawKey(key), member));
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member){
		return execute((RedisClient client)->client.zRank(makeByteKey(key), member));
	}

	@Override
	public Long zRevRank(final String key, final String member){
		return execute((RedisClient client)->client.zRevRank(makeRawKey(key), member));
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member){
		return execute((RedisClient client)->client.zRevRank(makeByteKey(key), member));
	}

	@Override
	public Long zRem(final String key, final String... members){
		return execute((RedisClient client)->client.zRem(makeRawKey(key), members));
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members){
		return execute((RedisClient client)->client.zRem(makeByteKey(key), members));
	}

	@Override
	public Long zRemRangeByRank(final String key, final int start, final int end){
		return execute((RedisClient client)->client.zRemRangeByRank(makeRawKey(key), start, end));
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final int start, final int end){
		return execute((RedisClient client)->client.zRemRangeByRank(makeByteKey(key), start, end));
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end){
		return execute((RedisClient client)->client.zRemRangeByRank(makeRawKey(key), start, end));
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end){
		return execute((RedisClient client)->client.zRemRangeByRank(makeByteKey(key), start, end));
	}

	@Override
	public Long zRemRangeByScore(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zRemRangeByScore(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zRemRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRevRange(final String key, final int start, final int end){
		return execute((RedisClient client)->client.zRevRange(makeRawKey(key), start, end));
	}

	@Override
	public Set<byte[]> zRevRange(final byte[] key, final int start, final int end){
		return execute((RedisClient client)->client.zRevRange(makeByteKey(key), start, end));
	}

	@Override
	public Set<String> zRevRange(final String key, final long start, final long end){
		return execute((RedisClient client)->client.zRevRange(makeRawKey(key), start, end));
	}

	@Override
	public Set<byte[]> zRevRange(final byte[] key, final long start, final long end){
		return execute((RedisClient client)->client.zRevRange(makeByteKey(key), start, end));
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final String key, final int start, final int end){
		return execute((RedisClient client)->client.zRevRangeWithScores(makeRawKey(key), start, end));
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final int start, final int end){
		return execute((RedisClient client)->client.zRevRangeWithScores(makeByteKey(key), start, end));
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final String key, final long start, final long end){
		return execute((RedisClient client)->client.zRevRangeWithScores(makeRawKey(key), start, end));
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
		return execute((RedisClient client)->client.zRevRangeWithScores(makeByteKey(key), start, end));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min, offset, count));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min, offset, count));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min, offset, count));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min, offset, count));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeRawKey(key), max, min, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScore(makeByteKey(key), max, min, offset, count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final float max, final float min, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
			final int offset, final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
			final int offset, final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max,
			final int offset, final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeRawKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
			final int offset, final int count){
		return execute((RedisClient client)->client.zRevRangeByScoreWithScores(makeByteKey(key), max, min, offset,
				count));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute((RedisClient client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count));
	}

	@Override
	public Long zLexCount(final String key, final float min, final float max){
		return execute((RedisClient client)->client.zLexCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final float min, final float max){
		return execute((RedisClient client)->client.zLexCount(makeByteKey(key), min, max));
	}

	@Override
	public Long zLexCount(final String key, final double min, final double max){
		return execute((RedisClient client)->client.zLexCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final double min, final double max){
		return execute((RedisClient client)->client.zLexCount(makeByteKey(key), min, max));
	}

	@Override
	public Long zLexCount(final String key, final int min, final int max){
		return execute((RedisClient client)->client.zLexCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final int min, final int max){
		return execute((RedisClient client)->client.zLexCount(makeByteKey(key), min, max));
	}

	@Override
	public Long zLexCount(final String key, final long min, final long max){
		return execute((RedisClient client)->client.zLexCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final long min, final long max){
		return execute((RedisClient client)->client.zLexCount(makeByteKey(key), min, max));
	}

	@Override
	public Long zLexCount(final String key, final String min, final String max){
		return execute((RedisClient client)->client.zLexCount(makeRawKey(key), min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
		return execute((RedisClient client)->client.zLexCount(makeByteKey(key), min, max));
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys){
		return execute((RedisClient client)->client.zInterStore(makeRawKey(destKey), makeRawKeys(keys)));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys){
		return execute((RedisClient client)->client.zInterStore(makeByteKey(destKey), makeByteKeys(keys)));
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final String... keys){
		return execute((RedisClient client)->client.zInterStore(makeRawKey(destKey), aggregate, makeRawKeys(keys)));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		return execute((RedisClient client)->client.zInterStore(makeByteKey(destKey), aggregate, makeByteKeys(keys)));
	}

	@Override
	public Long zInterStore(final String destKey, final double[] weights, final String... keys){
		return execute((RedisClient client)->client.zInterStore(makeRawKey(destKey), weights, makeRawKeys(keys)));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		return execute((RedisClient client)->client.zInterStore(makeByteKey(destKey), weights, makeByteKeys(keys)));
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		return execute((RedisClient client)->client.zInterStore(makeRawKey(destKey), aggregate, weights,
				makeRawKeys(keys)));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		return execute((RedisClient client)->client.zInterStore(makeByteKey(destKey), aggregate, weights,
				makeByteKeys(keys)));
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys){
		return execute((RedisClient client)->client.zUnionStore(makeRawKey(destKey), makeRawKeys(keys)));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys){
		return execute((RedisClient client)->client.zUnionStore(makeByteKey(destKey), makeByteKeys(keys)));
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final String... keys){
		return execute((RedisClient client)->client.zUnionStore(makeRawKey(destKey), aggregate, makeRawKeys(keys)));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		return execute((RedisClient client)->client.zUnionStore(makeByteKey(destKey), aggregate, makeByteKeys(keys)));
	}

	@Override
	public Long zUnionStore(final String destKey, final double[] weights, final String... keys){
		return execute((RedisClient client)->client.zUnionStore(makeRawKey(destKey), weights, makeRawKeys(keys)));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		return execute((RedisClient client)->client.zUnionStore(makeByteKey(destKey), weights, makeByteKeys(keys)));
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		return execute((RedisClient client)->client.zUnionStore(makeRawKey(destKey), aggregate, weights,
				makeRawKeys(keys)));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		return execute((RedisClient client)->client.zUnionStore(makeByteKey(destKey), aggregate, weights,
				makeByteKeys(keys)));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final int count){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final int count){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final int count){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final int count){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final int count){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern, final int count){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final int count){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern, final int count){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final int count){
		return execute((RedisClient client)->client.zScan(makeRawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count){
		return execute((RedisClient client)->client.zScan(makeByteKey(key), cursor, pattern, count));
	}

}
