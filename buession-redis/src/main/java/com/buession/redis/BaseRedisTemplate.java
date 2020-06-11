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

import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.Executor;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Redis 基本操作 Template
 *
 * @author Yong.Teng
 */
public class BaseRedisTemplate extends AbstractRedisTemplate {

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
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.exists(makeRawKey(key)), ProtocolCommand.EXISTS, args);
	}

	@Override
	public boolean exists(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.exists(makeByteKey(key)), ProtocolCommand.EXISTS, args);
	}

	@Override
	public Type type(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.type(makeRawKey(key)), ProtocolCommand.TYPE, args);
	}

	@Override
	public Type type(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.type(makeByteKey(key)), ProtocolCommand.TYPE, args);
	}

	@Override
	public Status rename(final String key, final String newKey){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("newKey", newKey);
		return execute((client)->client.rename(makeRawKey(key), makeRawKey(newKey)), ProtocolCommand.RENAME, args);
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("newKey", newKey);
		return execute((client)->client.rename(makeByteKey(key), makeByteKey(newKey)), ProtocolCommand.RENAME, args);
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("newKey", newKey);
		return execute((client)->client.renameNx(makeRawKey(key), makeRawKey(newKey)), ProtocolCommand.RENAMENX, args);
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("newKey", newKey);
		return execute((client)->client.renameNx(makeByteKey(key), makeByteKey(newKey)), ProtocolCommand.RENAMENX, args);
	}

	@Override
	public String randomKey(){
		return execute((client)->client.randomKey(), ProtocolCommand.RANDOMKEY);
	}

	@Override
	public Set<String> keys(final String pattern){
		final CommandArguments args = CommandArguments.getInstance().put("pattern", pattern);
		return execute((client)->client.keys(pattern), ProtocolCommand.KEYS, args);
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("pattern", pattern);
		return execute((client)->client.keys(pattern), ProtocolCommand.KEYS, args);
	}

	@Override
	public Status expire(final String key, final int lifetime){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("lifetime", lifetime);
		return execute((client)->client.expire(makeRawKey(key), lifetime), ProtocolCommand.EXPIRE, args);
	}

	@Override
	public Status expire(final byte[] key, final int lifetime){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("lifetime", lifetime);
		return execute((client)->client.expire(makeByteKey(key), lifetime), ProtocolCommand.EXPIRE, args);
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("unixTimestamp", unixTimestamp);
		return execute((client)->client.expireAt(makeRawKey(key), unixTimestamp), ProtocolCommand.EXPIREAT, args);
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("unixTimestamp", unixTimestamp);
		return execute((client)->client.expireAt(makeByteKey(key), unixTimestamp), ProtocolCommand.EXPIREAT, args);
	}

	@Override
	public Status pExpire(final String key, final int lifetime){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("lifetime", lifetime);
		return execute((client)->client.pExpire(makeRawKey(key), lifetime), ProtocolCommand.PEXPIRE, args);
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("lifetime", lifetime);
		return execute((client)->client.pExpire(makeByteKey(key), lifetime), ProtocolCommand.PEXPIRE, args);
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("unixTimestamp", unixTimestamp);
		return execute((client)->client.pExpireAt(makeRawKey(key), unixTimestamp), ProtocolCommand.PEXPIREAT, args);
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("unixTimestamp", unixTimestamp);
		return execute((client)->client.pExpireAt(makeByteKey(key), unixTimestamp), ProtocolCommand.PEXPIREAT, args);
	}

	@Override
	public Long ttl(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.ttl(makeRawKey(key)), ProtocolCommand.TTL, args);
	}

	@Override
	public Long ttl(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.ttl(makeByteKey(key)), ProtocolCommand.TTL, args);
	}

	@Override
	public Long pTtl(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.pTtl(makeRawKey(key)), ProtocolCommand.PTTL, args);
	}

	@Override
	public Long pTtl(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.pTtl(makeByteKey(key)), ProtocolCommand.PTTL, args);
	}

	@Override
	public Status persist(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.persist(makeRawKey(key)), ProtocolCommand.PERSIST, args);
	}

	@Override
	public Status persist(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.persist(makeByteKey(key)), ProtocolCommand.PERSIST, args);
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor);
		return execute((client)->client.scan(cursor), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor);
		return execute((client)->client.scan(cursor), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor);
		return execute((client)->client.scan(cursor), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor);
		return execute((client)->client.scan(cursor), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor, final String pattern){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.scan(cursor, pattern), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final int cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.scan(cursor, pattern), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.scan(cursor, pattern), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.scan(cursor, pattern), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.scan(cursor, pattern), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.scan(cursor, pattern), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("count", count);
		return execute((client)->client.scan(cursor, count), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("count", count);
		return execute((client)->client.scan(cursor, count), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("count", count);
		return execute((client)->client.scan(cursor, count), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("count", count);
		return execute((client)->client.scan(cursor, count), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor, final String pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.scan(cursor, pattern, count), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final int cursor, final byte[] pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.scan(cursor, pattern, count), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.scan(cursor, pattern, count), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.scan(cursor, pattern, count), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.scan(cursor, pattern, count), ProtocolCommand.SCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.scan(cursor, pattern, count), ProtocolCommand.SCAN, args);
	}

	@Override
	public List<String> sort(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.sort(makeRawKey(key)), ProtocolCommand.SORT, args);
	}

	@Override
	public List<byte[]> sort(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.sort(makeByteKey(key)), ProtocolCommand.SORT, args);
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("sortArgument", sortArgument);
		return execute((client)->client.sort(makeRawKey(key), sortArgument), ProtocolCommand.SORT, args);
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("sortArgument", sortArgument);
		return execute((client)->client.sort(makeByteKey(key), sortArgument), ProtocolCommand.SORT, args);
	}

	@Override
	public Long sort(final String key, final String destKey){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey);
		return execute((client)->client.sort(makeRawKey(key), makeRawKey(destKey)), ProtocolCommand.SORT, args);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey);
		return execute((client)->client.sort(makeByteKey(key), makeByteKey(destKey)), ProtocolCommand.SORT, args);
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey).put("sortArgument", sortArgument);
		return execute((client)->client.sort(makeRawKey(key), makeRawKey(destKey), sortArgument), ProtocolCommand.SORT, args);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey).put("sortArgument", sortArgument);
		return execute((client)->client.sort(makeByteKey(key), makeByteKey(destKey), sortArgument), ProtocolCommand.SORT, args);
	}

	@Override
	public byte[] dump(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.dump(makeRawKey(key)), ProtocolCommand.DUMP, args);
	}

	@Override
	public byte[] dump(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.dump(makeByteKey(key)), ProtocolCommand.DUMP, args);
	}

	@Override
	public Status restore(final String key, final String serializedValue, final int ttl){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("serializedValue", serializedValue).put("ttl", ttl);
		return execute((client)->client.restore(makeRawKey(key), serializedValue, ttl), ProtocolCommand.RESTORE, args);
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("serializedValue", serializedValue).put("ttl", ttl);
		return execute((client)->client.restore(makeByteKey(key), serializedValue, ttl), ProtocolCommand.RESTORE, args);
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("host", host).put("port", port).put("db", db).put("timeout", timeout);
		return execute((client)->client.migrate(makeRawKey(key), host, port, db, timeout), ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("host", host).put("port", port).put("db", db).put("timeout", timeout);
		return execute((client)->client.migrate(makeByteKey(key), host, port, db, timeout), ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout,
	 MigrateOperation migrateOperation){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("host", host).put("port", port).put("db", db).put("timeout", timeout).put("migrateOperation", migrateOperation);
		return execute((client)->client.migrate(makeRawKey(key), host, port, db, timeout, migrateOperation), ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int d,
	 final int timeouinal MigrateOperation migrateOperation){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("host", host).put("port", port).put("db", db).put("timeout", timeout).put("migrateOperation", migrateOperation);
		return execute((client)->client.migrate(makeByteKey(key), host, port, db, timeout, migrateOperation), ProtocolCommand.MIGRATE, args);
	}

	@Override
	public Long del(final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
		return execute((client)->client.del(makeRawKeys(keys)), ProtocolCommand.DEL, args);
	}

	@Override
	public Long del(final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
		return execute((client)->client.del(makeByteKeys(keys)), ProtocolCommand.DEL, args);
	}

	@Override
	public Status move(final String key, final int db){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("db", db);
		return execute((client)->client.move(makeRawKey(key), db), ProtocolCommand.MOVE, args);
	}

	@Override
	public Status move(final byte[] key, final int db){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("db", db);
		return execute((client)->client.move(makeByteKey(key), db), ProtocolCommand.MOVE, args);
	}

	@Override
	public Status set(final String key, final String value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.set(makeRawKey(key), value), ProtocolCommand.SET, args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.set(makeByteKey(key), value), ProtocolCommand.SET, args);
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("setArgument", setArgument);
		return execute((client)->client.set(makeRawKey(key), value, setArgument), ProtocolCommand.SET, args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("setArgument", setArgument);
		return execute((client)->client.set(makeByteKey(key), value, setArgument), ProtocolCommand.SET, args);
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("lifetime", lifetime);
		return execute((client)->client.setEx(makeRawKey(key), value, lifetime), ProtocolCommand.SETEX, args);
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("lifetime", lifetime);
		return execute((client)->client.setEx(makeByteKey(key), value, lifetime), ProtocolCommand.SETEX, args);
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("lifetime", lifetime);
		return execute((client)->client.pSetEx(makeRawKey(key), value, lifetime), ProtocolCommand.PSETEX, args);
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("lifetime", lifetime);
		return execute((client)->client.pSetEx(makeByteKey(key), value, lifetime), ProtocolCommand.PSETEX, args);
	}

	@Override
	public Status setNx(final String key, final String value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.setNx(makeRawKey(key), value), ProtocolCommand.SETNX, args);
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.setNx(makeByteKey(key), value), ProtocolCommand.SETNX, args);
	}

	@Override
	public Long append(final String key, final String value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.append(makeRawKey(key), value), ProtocolCommand.APPEND, args);
	}

	@Override
	public Long append(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.append(makeByteKey(key), value), ProtocolCommand.APPEND, args);
	}

	@Override
	public String get(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.get(makeRawKey(key)), ProtocolCommand.GET, args);
	}

	@Override
	public byte[] get(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.get(makeByteKey(key)), ProtocolCommand.GET, args);
	}

	@Override
	public String getSet(final String key, final String value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.getSet(makeRawKey(key), value), ProtocolCommand.GETSET, args);
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.getSet(makeByteKey(key), value), ProtocolCommand.GETSET, args);
	}

	@Override
	public Status mSet(final Map<String, String> values){
		final CommandArguments args = CommandArguments.getInstance().put("values", values);
		return execute((client)->client.mSet(values), ProtocolCommand.MSET, args);
	}

	@Override
	public Status mSetNx(final Map<String, String> values){
		final CommandArguments args = CommandArguments.getInstance().put("values", values);
		return execute((client)->client.mSetNx(values), ProtocolCommand.MSET, args);
	}

	@Override
	public List<String> mGet(final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
		return execute((client)->client.mGet(makeRawKeys(keys)), ProtocolCommand.MGET, args);
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
		return execute((client)->client.mGet(makeByteKeys(keys)), ProtocolCommand.MGET, args);
	}

	@Override
	public Long incr(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.incr(makeRawKey(key)), ProtocolCommand.INCR, args);
	}

	@Override
	public Long incr(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.incr(makeByteKey(key)), ProtocolCommand.INCR, args);
	}

	@Override
	public Long incrBy(final String key, final int value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.incrBy(makeRawKey(key), value), ProtocolCommand.INCRBY, args);
	}

	@Override
	public Long incrBy(final byte[] key, final int value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.incrBy(makeByteKey(key), value), ProtocolCommand.INCRBY, args);
	}

	@Override
	public Long incrBy(final String key, final long value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.incrBy(makeRawKey(key), value), ProtocolCommand.INCRBY, args);
	}

	@Override
	public Long incrBy(final byte[] key, final long value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.incrBy(makeByteKey(key), value), ProtocolCommand.INCRBY, args);
	}

	@Override
	public Double incrByFloat(final String key, final float value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.incrByFloat(makeRawKey(key), value), ProtocolCommand.INCRBYFLOAT, args);
	}

	@Override
	public Double incrByFloat(final byte[] key, final float value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.incrByFloat(makeByteKey(key), value), ProtocolCommand.INCRBYFLOAT, args);
	}

	@Override
	public Double incrByFloat(final String key, final double value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.incrByFloat(makeRawKey(key), value), ProtocolCommand.INCRBYFLOAT, args);
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.incrByFloat(makeByteKey(key), value), ProtocolCommand.INCRBYFLOAT, args);
	}

	@Override
	public Long decr(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.decr(makeRawKey(key)), ProtocolCommand.DECR, args);
	}

	@Override
	public Long decr(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.decr(makeByteKey(key)), ProtocolCommand.DECR, args);
	}

	@Override
	public Long decrBy(final String key, final int value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.decrBy(makeRawKey(key), value), ProtocolCommand.DECRBY, args);
	}

	@Override
	public Long decrBy(final byte[] key, final int value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.decrBy(makeByteKey(key), value), ProtocolCommand.DECRBY, args);
	}

	@Override
	public Long decrBy(final String key, final long value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.decrBy(makeRawKey(key), value), ProtocolCommand.DECRBY, args);
	}

	@Override
	public Long decrBy(final byte[] key, final long value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.decrBy(makeByteKey(key), value), ProtocolCommand.DECRBY, args);
	}

	@Override
	public Long setRange(final String key, final int offset, final String value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setRange(makeRawKey(key), offset, value), ProtocolCommand.SETRANGE, args);
	}

	@Override
	public Long setRange(final byte[] key, final int offset, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setRange(makeByteKey(key), offset, value, ProtocolCommand.SETRANGE, args));
	}

	@Override
	public Long setRange(final String key, final long offset, final String value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setRange(makeRawKey(key), offset, value), ProtocolCommand.SETRANGE, args);
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setRange(makeByteKey(key), offset, value), ProtocolCommand.SETRANGE, args);
	}

	@Override
	public String getRange(final String key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("value", value);
		return execute((client)->client.getRange(makeRawKey(key), start, end), ProtocolCommand.GETRANGE, args);
	}

	@Override
	public byte[] getRange(final byte[] key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("value", value);
		return execute((client)->client.getRange(makeByteKey(key), start, end), ProtocolCommand.GETRANGE, args);
	}

	@Override
	public String getRange(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("value", value);
		return execute((client)->client.getRange(makeRawKey(key), start, end), ProtocolCommand.GETRANGE, args);
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("value", value);
		return execute((client)->client.getRange(makeByteKey(key), start, end), ProtocolCommand.GETRANGE, args);
	}

	@Override
	public String substr(final String key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("value", value);
		return execute((client)->client.substr(makeRawKey(key), start, end), ProtocolCommand.SUBSTR, args);
	}

	@Override
	public byte[] substr(final byte[] key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("value", value);
		return execute((client)->client.substr(makeByteKey(key), start, end), ProtocolCommand.SUBSTR, args);
	}

	@Override
	public String substr(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("value", value);
		return execute((client)->client.substr(makeRawKey(key), start, end), ProtocolCommand.SUBSTR, args);
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("value", value);
		return execute((client)->client.substr(makeByteKey(key), start, end), ProtocolCommand.SUBSTR, args);
	}

	@Override
	public Long strlen(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.strlen(makeRawKey(key)), ProtocolCommand.STRLEN, args);
	}

	@Override
	public Long strlen(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.strlen(makeByteKey(key)), ProtocolCommand.STRLEN, args);
	}

	@Override
	public boolean hExists(final String key, final String field){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field);
		return execute((client)->client.hExists(makeRawKey(key), field), ProtocolCommand.HEXISTS, args);
	}

	@Override
	public boolean hExists(final byte[] key, final byte[] field){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field);
		return execute((client)->client.hExists(makeByteKey(key), field), ProtocolCommand.HEXISTS, args);
	}

	@Override
	public Set<String> hKeys(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.hKeys(makeRawKey(key)), ProtocolCommand.HKEYS, args);
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.hKeys(makeByteKey(key)), ProtocolCommand.HKEYS, args);
	}

	@Override
	public List<String> hVals(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.hVals(makeRawKey(key)), ProtocolCommand.HKEYS, args);
	}

	@Override
	public List<byte[]> hVals(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.hVals(makeByteKey(key)), ProtocolCommand.HKEYS, args);
	}

	@Override
	public Status hSet(final String key, final String field, final String value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
		return execute((client)->client.hSet(makeRawKey(key), field, value), ProtocolCommand.HSET, args);
	}

	@Override
	public Status hSet(final byte[] key, final byte[] field, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
		return execute((client)->client.hSet(makeByteKey(key), field, value), ProtocolCommand.HSET, args);
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
		return execute((client)->client.hSetNx(makeRawKey(key), field, value), ProtocolCommand.HSETNX, args);
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
		return execute((client)->client.hSetNx(makeByteKey(key), field, value), ProtocolCommand.HSETNX, args);
	}

	@Override
	public String hGet(final String key, final String field){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field);
		return execute((client)->client.hGet(makeRawKey(key), field), ProtocolCommand.HGET, args);
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field);
		return execute((client)->client.hGet(makeByteKey(key), field), ProtocolCommand.HGET, args);
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("data", data);
		return execute((client)->client.hMSet(makeRawKey(key), data), ProtocolCommand.HMSET, args);
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("data", data);
		return execute((client)->client.hMSet(key, data), ProtocolCommand.HMSET, args);
	}

	@Override
	public List<String> hMGet(final String key, final String... fields){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("fields", fields);
		return execute((client)->client.hMGet(makeRawKey(key), fields), ProtocolCommand.HMGET, args);
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("fields", fields);
		return execute((client)->client.hMGet(makeByteKey(key), fields), ProtocolCommand.HMGET, args);
	}

	@Override
	public Map<String, String> hGetAll(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.hGetAll(makeRawKey(key)), ProtocolCommand.HGETALL, args);
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.hGetAll(makeByteKey(key)), ProtocolCommand.HGETALL, args);
	}

	@Override
	public Long hDel(final String key, final String... fields){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("fields", fields);
		return execute((client)->client.hDel(makeRawKey(key), fields), ProtocolCommand.HDEL, args);
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("fields", fields);
		return execute((client)->client.hDel(makeByteKey(key), fields), ProtocolCommand.HDEL, args);
	}

	@Override
	public Long hStrLen(final String key, final String field){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field);
		return execute((client)->client.hStrLen(makeRawKey(key), field), ProtocolCommand.HSTRLEN, args);
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field);
		return execute((client)->client.hStrLen(makeByteKey(key), field), ProtocolCommand.HSTRLEN, args);
	}

	@Override
	public Long hLen(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.hLen(makeRawKey(key)), ProtocolCommand.HLEN, args);
	}

	@Override
	public Long hLen(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.hLen(makeByteKey(key)), ProtocolCommand.HLEN, args);
	}

	@Override
	public Long hIncrBy(final String key, final String field, final int value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
		return execute((client)->client.hIncrBy(makeRawKey(key), field, value), ProtocolCommand.HINCRBY, args);
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final int value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
		return execute((client)->client.hIncrBy(makeByteKey(key), field, value), ProtocolCommand.HINCRBY, args);
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
		return execute((client)->client.hIncrBy(makeRawKey(key), field, value), ProtocolCommand.HINCRBY, args);
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
		return execute((client)->client.hIncrBy(makeByteKey(key), field, value), ProtocolCommand.HINCRBY, args);
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final float value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
		return execute((client)->client.hIncrByFloat(makeRawKey(key), field, value), ProtocolCommand.HINCRBYFLOAT, args);
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final float value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
		return execute((client)->client.hIncrByFloat(makeByteKey(key), field, value), ProtocolCommand.HINCRBYFLOAT, args);
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
		return execute((client)->client.hIncrByFloat(makeRawKey(key), field, value), ProtocolCommand.HINCRBYFLOAT, args);
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
		return execute((client)->client.hIncrByFloat(makeByteKey(key), field, value), ProtocolCommand.HINCRBYFLOAT, args);
	}

	@Override
	public Long hDecrBy(final String key, final String field, final int value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
		return execute((client)->client.hDecrBy(makeRawKey(key), field, value), ProtocolCommand.HDECRBY, args);
	}

	@Override
	public Long hDecrBy(final byte[] key, final byte[] field, final int value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
		return execute((client)->client.hDecrBy(makeByteKey(key), field, value), ProtocolCommand.HDECRBY, args);
	}

	@Override
	public Long hDecrBy(final String key, final String field, final long value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
		return execute((client)->client.hDecrBy(makeRawKey(key), field, value), ProtocolCommand.HDECRBY, args);
	}

	@Override
	public Long hDecrBy(final byte[] key, final byte[] field, final long value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value", value);
		return execute((client)->client.hDecrBy(makeByteKey(key), field, value), ProtocolCommand.HDECRBY, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
		return execute((client)->client.hScan(makeRawKey(key), cursor), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
		return execute((client)->client.hScan(makeByteKey(key), cursor), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
		return execute((client)->client.hScan(makeRawKey(key), cursor), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
		return execute((client)->client.hScan(makeByteKey(key), cursor), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
		return execute((client)->client.hScan(makeRawKey(key), cursor), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
		return execute((client)->client.hScan(makeByteKey(key), cursor), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final String pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.hScan(makeRawKey(key), cursor, pattern), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.hScan(makeByteKey(key), cursor, pattern), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.hScan(makeRawKey(key), cursor, pattern), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.hScan(makeByteKey(key), cursor, pattern), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.hScan(makeRawKey(key), cursor, pattern), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.hScan(makeByteKey(key), cursor, pattern), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.hScan(makeRawKey(key), cursor, count), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.hScan(makeByteKey(key), cursor, count), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.hScan(makeRawKey(key), cursor, count), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.hScan(makeByteKey(key), cursor, count), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.hScan(makeRawKey(key), cursor, count), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.hScan(makeByteKey(key), cursor, count), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final String pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.hScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.hScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute ((client)->client.hScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.hScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.hScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.HSCAN, args);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.hScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.HSCAN, args);
	}

	@Override
	public Long lPush(final String key, final String... values){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);
		return execute((client)->client.lPush(makeRawKey(key), values), ProtocolCommand.LPUSH, args);
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);
		return execute((client)->client.lPush(makeByteKey(key), values), ProtocolCommand.LPUSH, args);
	}

	@Override
	public Long lPushX(final String key, final String... values){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);
		return execute((client)->client.lPushX(makeRawKey(key), values), ProtocolCommand.LPUSHX, args);
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);
		return execute((client)->client.lPushX(makeByteKey(key), values), ProtocolCommand.LPUSHX, args);
	}

	@Override
	public Long lInsert(final String key, final String value, final ListPosition position, final String pivot){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("position", position).put("pivot", pivot);
		return execute((client)->client.lInsert(makeRawKey(key), value, position, pivot), ProtocolCommand.LINSERT, args);
	}

	@Override
	public Long lInsert(final byte[] key, final byte[] value, final ListPosition position, final byte[] pivot){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("position", position).put("pivot", pivot);
		return execute((client)->client.lInsert(makeByteKey(key), value, position, pivot), ProtocolCommand.LINSERT, args);
	}

	@Override
	public Status lSet(final String key, final int index, final String value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index).put("value", value);
		return execute((client)->client.lSet(makeRawKey(key), index, value), ProtocolCommand.LSET, args);
	}

	@Override
	public Status lSet(final byte[] key, final int index, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index).put("value", value);
		return execute((client)->client.lSet(makeByteKey(key), index, value), ProtocolCommand.LSET, args);
	}

	@Override
	public Status lSet(final String key, final long index, final String value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index).put("value", value);
		return execute((client)->client.lSet(makeRawKey(key), index, value), ProtocolCommand.LSET, args);
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index).put("value", value);
		return execute((client)->client.lSet(makeByteKey(key), index, value), ProtocolCommand.LSET, args);
	}

	@Override
	public String lIndex(final String key, final int index){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index);
		return execute((client)->client.lIndex(makeRawKey(key), index), ProtocolCommand.LINSEX, args);
	}

	@Override
	public byte[] lIndex(final byte[] key, final int index){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index);
		return execute((client)->client.lIndex(makeByteKey(key), index), ProtocolCommand.LINSEX, args);
	}

	@Override
	public String lIndex(final String key, final long index){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index);
		return execute((client)->client.lIndex(makeRawKey(key), index), ProtocolCommand.LINSEX, args);
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index);
		return execute((client)->client.lIndex(makeByteKey(key), index), ProtocolCommand.LINSEX, args);
	}

	@Override
	public String lPop(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.lPop(makeRawKey(key)), ProtocolCommand.LPOP, args);
	}

	@Override
	public byte[] lPop(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.lPop(makeByteKey(key)), ProtocolCommand.LPOP, args);
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("timeout", timeout);
		return execute((client)->client.blPop(makeRawKeys(keys), timeout), ProtocolCommand.BLPOP, args);
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("timeout", timeout);
		return execute((client)->client.blPop(makeByteKeys(keys), timeout), ProtocolCommand.BLPOP, args);
	}

	@Override
	public String rPop(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.rPop(makeRawKey(key)), ProtocolCommand.RPOP, args);
	}

	@Override
	public byte[] rPop(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.rPop(makeByteKey(key)), ProtocolCommand.RPOP, args);
	}

	@Override
	public String rPoplPush(final String source, final String destKey){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey);
		return execute((client)->client.rPoplPush(makeRawKey(source), makeRawKey(destKey)), ProtocolCommand.RPOPLPUSH, args);
	}

	@Override
	public byte[] rPoplPush(final byte[] source, final byte[] destKey){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey);
		return execute((client)->client.rPoplPush(makeByteKey(source), makeByteKey(destKey)), ProtocolCommand.RPOPLPUSH, args);
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys).put("timeout", timeout);
		return execute((client)->client.brPop(makeRawKeys(keys), timeout), ProtocolCommand.BRPOP, args);
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys).put("timeout", timeout);
		return execute((client)->client.brPop(makeByteKeys(keys), timeout), ProtocolCommand.BRPOP, args);
	}

	@Override
	public String brPoplPush(final String source, final String destKey, final int timeout){
		final CommandArguments args = CommandArguments.getInstance().put("source", source).put("destKey", destKey).put("timeout", timeout);
		return client.brPoplPush(makeRawKey(source), makeRawKey(destKey), timeout, ProtocolCommand.BRPOPLPUSH, args);
	}

	@Override
	public byte[] brPoplPush(final byte[] source, final byte[] destKey, final int timeout){
		final CommandArguments args = CommandArguments.getInstance().put("source", source).put("destKey", destKey).put("timeout", timeout);
		return client.brPoplPush(makeByteKey(source), makeByteKey(destKey), timeout, ProtocolCommand.BRPOPLPUSH, args);
	}

	@Override
	public Long rPush(final String key, final String... values){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);
		return execute((client)->client.rPush(makeRawKey(key), values), ProtocolCommand.RPUSH, args);
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);
		return execute((client)->client.rPush(makeByteKey(key), values), ProtocolCommand.RPUSH, args);
	}

	@Override
	public Long rPushX(final String key, final String... values){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);
		return execute((client)->client.rPushX(makeRawKey(key), values), ProtocolCommand.RPUSHX, args);
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);
		return execute((client)->client.rPushX(makeByteKey(key), values), ProtocolCommand.RPUSHX, args);
	}

	@Override
	public Status lTrim(final String key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.lTrim(makeRawKey(key), start, end), ProtocolCommand.LTRIM, args);
	}

	@Override
	public Status lTrim(final byte[] key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.lTrim(makeByteKey(key), start, end), ProtocolCommand.LTRIM, args);
	}

	@Override
	public Status lTrim(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.lTrim(makeRawKey(key), start, end), ProtocolCommand.LTRIM, args);
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.lTrim(makeByteKey(key), start, end), ProtocolCommand.LTRIM, args);
	}

	@Override
	public Long lRem(final String key, final String value, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("count", count);
		return execute((client)->client.lRem(makeRawKey(key), value, count), ProtocolCommand.LREM, args);
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("count", count);
		return execute((client)->client.lRem(makeByteKey(key), value, count), ProtocolCommand.LREM, args);
	}

	@Override
	public Long lRem(final String key, final String value, final long count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("count", count);
		return execute((client)->client.lRem(makeRawKey(key), value, count), ProtocolCommand.LREM, args);
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("count", count);
		return execute((client)->client.lRem(makeByteKey(key), value, count), ProtocolCommand.LREM, args);
	}

	@Override
	public List<String> lRange(final String key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.lRange(makeRawKey(key), start, end), ProtocolCommand.LRANG, args);
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.lRange(makeByteKey(key), start, end), ProtocolCommand.LRANG, args);
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.lRange(makeRawKey(key), start, end), ProtocolCommand.LRANG, args);
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.lRange(makeByteKey(key), start, end), ProtocolCommand.LRANG, args);
	}

	@Override
	public Long lLen(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.lLen(makeRawKey(key)), ProtocolCommand.LLEN, args);
	}

	@Override
	public Long lLen(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.lLen(makeByteKey(key)), ProtocolCommand.LLEN, args);
	}

	@Override
	public Long sAdd(final String key, final String... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.sAdd(makeRawKey(key), members), ProtocolCommand.SADD, args);
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.sAdd(makeByteKey(key), members), ProtocolCommand.SADD, args);
	}

	@Override
	public Long sCard(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.sCard(makeRawKey(key)), ProtocolCommand.SCARD, args);
	}

	@Override
	public Long sCard(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.sCard(makeByteKey(key)), ProtocolCommand.SCARD, args);
	}

	@Override
	public boolean sisMember(final String key, final String member){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
		return execute((client)->client.sisMember(makeRawKey(key), member), ProtocolCommand.SISMEMBER, args);
	}

	@Override
	public boolean sisMember(final byte[] key, final byte[] member){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
		return execute((client)->client.sisMember(makeByteKey(key), member), ProtocolCommand.SISMEMBER, args);
	}

	@Override
	public Set<String> sMembers(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.sMembers(makeRawKey(key)), ProtocolCommand.SMEMBERS, args);
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.sMembers(makeByteKey(key)), ProtocolCommand.SMEMBERS, args);
	}

	@Override
	public String sPop(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.sPop(makeRawKey(key)), ProtocolCommand.SMEMBERS, args);
	}

	@Override
	public byte[] sPop(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.sPop(makeByteKey(key)), ProtocolCommand.SMEMBERS, args);
	}

	@Override
	public String sRandMember(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.sRandMember(makeRawKey(key)), ProtocolCommand.SRANDMEMBER, args);
	}

	@Override
	public byte[] sRandMember(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.sRandMember(makeByteKey(key)), ProtocolCommand.SRANDMEMBER, args);
	}

	@Override
	public List<String> sRandMember(final String key, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("count", count);
		return execute((client)->client.sRandMember(makeRawKey(key), count), ProtocolCommand.SRANDMEMBER, args);
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("count", count);
		return execute((client)->client.sRandMember(makeByteKey(key), count), ProtocolCommand.SRANDMEMBER, args);
	}

	@Override
	public List<String> sRandMember(final String key, final long count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("count", count);
		return execute((client)->client.sRandMember(makeRawKey(key), count), ProtocolCommand.SRANDMEMBER, args);
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final long count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("count", count);
		return execute((client)->client.sRandMember(makeByteKey(key), count), ProtocolCommand.SRANDMEMBER, args);
	}

	@Override
	public Long sRem(final String key, final String... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.sRem(makeRawKey(key), members), ProtocolCommand.SREM, args);
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.sRem(makeByteKey(key), members), ProtocolCommand.SREM, args);
	}

	@Override
	public Set<String> sDiff(final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
		return execute((client)->client.sDiff(makeRawKeys(keys)), ProtocolCommand.SDIFF, args);
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
		return execute((client)->client.sDiff(makeByteKeys(keys)), ProtocolCommand.SDIFF, args);
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
		return execute((client)->client.sDiffStore(makeRawKey(destKey), makeRawKeys(keys)), ProtocolCommand.SDIFFSTORE, args);
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
		return execute((client)->client.sDiffStore(makeByteKey(destKey), makeByteKeys(keys)), ProtocolCommand.SDIFFSTORE, args);
	}

	@Override
	public Set<String> sInter(final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
		return execute((client)->client.sInter(makeRawKeys(keys)), ProtocolCommand.SINTER, args);
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
		return execute((client)->client.sInter(makeByteKeys(keys)), ProtocolCommand.SINTER, args);
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
		return execute((client)->client.sDiffStore(makeRawKey(destKey), makeRawKeys(keys)), ProtocolCommand.SINTERSTORE, args);
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
		return execute((client)->client.sDiffStore(makeByteKey(destKey), makeByteKeys(keys)), ProtocolCommand.SINTERSTORE, args);
	}

	@Override
	public Set<String> sUnion(final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
		return execute((client)->client.sUnion(makeRawKeys(keys)), ProtocolCommand.SUNION, args);
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);
		return execute((client)->client.sUnion(makeByteKeys(keys)), ProtocolCommand.SUNION, args);
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
		return execute((client)->client.sUnionStore(makeRawKey(destKey), makeRawKeys(keys)), ProtocolCommand.SUNIONSTORE, args);
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
		return execute((client)->client.sUnionStore(makeByteKey(destKey), makeByteKeys(keys)), ProtocolCommand.SUNIONSTORE, args);
	}

	@Override
	public Status sMove(final String key, final String destKey, final String member){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey).put("member", member);
		return execute((client)->client.sMove(makeRawKey(key), makeRawKey(destKey), member), ProtocolCommand.SMOVE, args);
	}

	@Override
	public Status sMove(final byte[] key, final byte[] destKey, final byte[] member){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey).put("member", member);
		return execute((client)->client.sMove(makeByteKey(key), makeByteKey(destKey), member), ProtocolCommand.SMOVE, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
		return execute((client)->client.sScan(makeRawKey(key), cursor), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
		return execute((client)->client.sScan(makeByteKey(key), cursor), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
		return execute((client)->client.sScan(makeRawKey(key), cursor), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
		return execute((client)->client.sScan(makeByteKey(key), cursor), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
		return execute((client)->client.sScan(makeRawKey(key), cursor), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
		return execute((client)->client.sScan(makeByteKey(key), cursor), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.sScan(makeRawKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.sScan(makeByteKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.sScan(makeRawKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.sScan(makeByteKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.sScan(makeRawKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.sScan(makeByteKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.sScan(makeRawKey(key), cursor, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.sScan(makeByteKey(key), cursor, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.sScan(makeRawKey(key), cursor, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.sScan(makeByteKey(key), cursor, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final int count){
		return execute((client)->client.sScan(makeRawKey(key), cursor, count));
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.sScan(makeByteKey(key), cursor, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.sScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.sScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.sScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.sScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern, fina  int coun){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.sScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
	 final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute ((client)->client.sScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Number> members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.zAdd(makeRawKey(key), members), ProtocolCommand.ZADD, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Number> members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.zAdd(makeByteKey(key), members), ProtocolCommand.ZADD, args);
	}

	@Override
	public Long zAdd(final String key, final List<KeyValue<String, Number>> members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.zAdd(makeRawKey(key), members), ProtocolCommand.ZADD, args);
	}

	@Override
	public Long zAdd(final byte[] key, final List<KeyValue<byte[], Number>> members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.zAdd(makeByteKey(key), members), ProtocolCommand.ZADD, args);
	}

	@Override
	public Double zScore(final String key, final String member){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
		return execute((client)->client.zScore(makeRawKey(key), member), ProtocolCommand.ZSCORE, args);
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
		return execute((client)->client.zScore(makeByteKey(key), member), ProtocolCommand.ZSCORE, args);
	}

	@Override
	public Long zCard(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.zCard(makeRawKey(key)), ProtocolCommand.ZCARD, args);
	}

	@Override
	public Long zCard(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.zCard(makeByteKey(key)), ProtocolCommand.ZCARD, args);
	}

	@Override
	public Double zIncrBy(final String key, final String member, final float increment){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment);
		return execute((client)->client.zIncrBy(makeRawKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final float increment){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment);
		return execute((client)->client.zIncrBy(makeByteKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
	}

	@Override
	public Double zIncrBy(final String key, final String member, final double increment){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment);
		return execute((client)->client.zIncrBy(makeRawKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final double increment){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment);
		return execute((client)->client.zIncrBy(makeByteKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
	}

	@Override
	public Double zIncrBy(final String key, final String member, final int increment){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment);
		return execute((client)->client.zIncrBy(makeRawKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final int increment){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment);
		return execute((client)->client.zIncrBy(makeByteKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
	}

	@Override
	public Double zIncrBy(final String key, final String member, final long increment){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment);
		return execute((client)->client.zIncrBy(makeRawKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final long increment){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("increment", increment);
		return execute((client)->client.zIncrBy(makeByteKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
	}

	@Override
	public Long zCount(final String key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zCount(makeRawKey(key), min, max), ProtocolCommand.ZCOUNT, args);
	}

	@Override
	public Long zCount(final byte[] key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zCount(makeByteKey(key), min, max), ProtocolCommand.ZCOUNT, args);
	}

	@Override
	public Long zCount(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zCount(makeRawKey(key), min, max), ProtocolCommand.ZCOUNT, args);
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zCount(makeByteKey(key), min, max), ProtocolCommand.ZCOUNT, args);
	}

	@Override
	public Long zCount(final String key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zCount(makeRawKey(key), min, max), ProtocolCommand.ZCOUNT, args);
	}

	@Override
	public Long zCount(final byte[] key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zCount(makeByteKey(key), min, max), ProtocolCommand.ZCOUNT, args);
	}

	@Override
	public Long zCount(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zCount(makeRawKey(key), min, max), ProtocolCommand.ZCOUNT, args);
	}

	@Override
	public Long zCount(final byte[] key, final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zCount(makeByteKey(key), min, max), ProtocolCommand.ZCOUNT, args);
	}

	@Override
	public Set<String> zRange(final String key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRange(makeRawKey(key), start, end), ProtocolCommand.ZRANGE, args);
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRange(makeByteKey(key), start, end), ProtocolCommand.ZRANGE, args);
	}

	@Override
	public Set<String> zRange(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRange(makeRawKey(key), start, end), ProtocolCommand.ZRANGE, args);
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRange(makeByteKey(key), start, end), ProtocolCommand.ZRANGE, args);
	}

	@Override
	public Set<Tuple> zRangeWithScores(final String key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRangeWithScores(makeRawKey(key), start, end), ProtocolCommand.ZRANGE, args);
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRangeWithScores(makeByteKey(key), start, end), ProtocolCommand.ZRANGE, args);
	}

	@Override
	public Set<Tuple> zRangeWithScores(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRangeWithScores(makeRawKey(key), start, end), ProtocolCommand.ZRANGE, args);
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRangeWithScores(makeByteKey(key), start, end), ProtocolCommand.ZRANGE, args);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final float min, final float max, final int offset,
	 final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max, final int offset, finant count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]>zRangeByScore(final byte[] key, final double min, final double max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final int min, final int max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]>zRangeByScore(final byte[] key, final int min, final int max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		retrn execute((client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final long min, final long max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max ,final int offset,final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max, final int offset,
		final int count){
			final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return e ecute((client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[]key,final long min,final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key,final String min,final String max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max, final int offset, final int ount){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return exeute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return e ecute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final intfset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return xecute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key,final long min, final long max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execut((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min,final byte[] max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]>zRangeByLex(final byte[] key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRangeByLex(final String key,final double min,final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]>zRangeByLex(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]>zRangeByLex(final byte[] key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]>zRangeByLex(final byte[] key,final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override 
	ublic Set<byte[]>zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final float min, final float max, final int offset,final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final float min, final float max, final int offset,final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final double min, final double max, final int offset,final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final int offset,final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final int min, final int max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final int min, final int max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final long min, final long max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]>zRan eByLex(final byte[] key, final long min, final long max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute(client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return ex e((client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max,final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Long zRank(final String key, final String member){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
		return execute((client)->client.zRank(makeRawKey(key), member), ProtocolCommand.ZRANK, args);
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
		return execute((client)->client.zRank(makeByteKey(key), member), ProtocolCommand.ZRANK, args);
	}

	@Override
	public Long zRevRank(final String key,final String member){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
		return execute((client)->client.zRevRank(makeRawKey(key), member), ProtocolCommand.ZREVRANK, args);
	}

	@Override
	public Long zRevRank(final byte[] key,final byte[] member){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);
		return execute((client)->client.zRevRank(makeByteKey(key), member), ProtocolCommand.ZREVRANK, args);
	}

	@Override
	public Long zRem(final String key, final String... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.zRem(makeRawKey(key), members), ProtocolCommand.ZREM, args);
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.zRem(makeByteKey(key), members), ProtocolCommand.ZREM, args);
	}

	@Override
	public Long zRemRangeByRank(final String key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRemRangeByRank(makeRawKey(key), start, end), ProtocolCommand.ZREMRANGEBYRANK, args);
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRemRangeByRank(makeByteKey(key), start, end), ProtocolCommand.ZREMRANGEBYRANK, args);
	}

	@Override
	public Long zRemRangeByRank(final String key,final long start,final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRemRangeByRank(makeRawKey(key), start, end), ProtocolCommand.ZREMRANGEBYRANK, args);
	}

	@Override
	public Long zRemRangeByRank(final byte[]key,final long start,final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRemRangeByRank(makeByteKey(key, start, end), ProtocolCommand.ZREMRANGEBYRANK, args);
	}

	@Override
	public Long zRemRangeByScore(final String key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByScore(makeRawKey(key), min, max)), ProtocolCommand.ZREMRANGEBYSCORE, args);
	}

	@Override
	public Long zRemRangeByScore(final byte[] key,final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByScore(makeByteKey(key), min, max)), ProtocolCommand.ZREMRANGEBYSCORE, args);
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByScore(makeRawKey(key), min, max)), ProtocolCommand.ZREMRANGEBYSCORE, args);
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByScore(makeByteKey(key), min, max)), ProtocolCommand.ZREMRANGEBYSCORE, args);
	}

	@Override
	public Long zRemRangeByScore(final String key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByScore(makeRawKey(key), min, max)), ProtocolCommand.ZREMRANGEBYSCORE, args);
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByScore(makeByteKey(key), min, max)), ProtocolCommand.ZREMRANGEBYSCORE, args);
	}

	@Override
	public Long zRemRangeByScore(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByScore(makeRawKey(key), min, max)), ProtocolCommand.ZREMRANGEBYSCORE, args);
	}

	@Override
	public Long zRemRangeByScore(final byte[] key,final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByScore(makeByteKey(key), min, max)), ProtocolCommand.ZREMRANGEBYSCORE, args);
	}

	@Override
	public Long zRemRangeByScore(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByScore(makeRawKey(key), min, max)), ProtocolCommand.ZREMRANGEBYSCORE, args);
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByScore(makeByteKey(key), min, max)), ProtocolCommand.ZREMRANGEBYSCORE, args);
	}

	@Override
	public Long zRemRangeByLex(final String key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByLex(makeRawKey(key), min, max)), ProtocolCommand.ZREMRANGEBYLEX, args);
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByLex(makeByteKey(key), min, max)), ProtocolCommand.ZREMRANGEBYLEX, args);
	}

	@Override
	public Long zRemRangeByLex(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByLex(makeRawKey(key), min, max)), ProtocolCommand.ZREMRANGEBYLEX, args);
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByLex(makeByteKey(key), min, max)), ProtocolCommand.ZREMRANGEBYLEX, args);
	}

	@Override
	public Long zRemRangeByLex(final String key, final int min,f inal int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByLex(makeRawKey(key), min, max)), ProtocolCommand.ZREMRANGEBYLEX, args);
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByLex(makeByteKey(key), min, max)), ProtocolCommand.ZREMRANGEBYLEX, args);
	}

	@Override
	public Long zRemRangeByLex(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByLex(makeRawKey(key), min, max)), ProtocolCommand.ZREMRANGEBYLEX, args);
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByLex(makeByteKey(key), min, max)), ProtocolCommand.ZREMRANGEBYLEX, args);
	}

	@Override
	public Long zRemRangeByLex(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByLex(makeRawKey(key), min, max)), ProtocolCommand.ZREMRANGEBYLEX, args);
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByLex(makeByteKey(key), min, max)), ProtocolCommand.ZREMRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRevRange(final String key,final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRevRange(makeRawKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
	}

	@Override
	public Set<byte[]>zRevRange(final byte[] key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRevRange(makeByteKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
	}

	@Override
	public Set<String> zRevRange(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRevRange(makeRawKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
	}

	@Override
	public Set<byte[]>zRevRange(final byte[]key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRevRange(makeByteKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final String key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRevRangeWithScores(makeRawKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRevRangeWithScores(makeByteKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRevRangeWithScores(makeRawKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRevRangeWithScores(makeByteKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]>zRevRangeByScore(final byte[] key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]>zRevRangeByScore(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]>zRevRangeByScore(final byte[] key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]>zRevRangeByScore(final byte[] key, final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]>zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<ring> zRevRangeByScore(final String key, final float min, final float max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override public Set<byte[]> zRevRangeByScore(final byte[] key, final float min, final float max, final int offset, final int count){
	final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScore(makeBteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final int min, final int max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return ex e((client)->client.zRevRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Overide
	public Set<byte[]> zRevRangeByScore(final byte[] key, final int min, final int max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Overrid
	public Set<String> zRevRangeByScore(final String key, final long min, final long max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Overide
	public Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<byte]> zRvRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		ret n execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(fnal Str key,final float min,final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key,final byte[] min,final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key,final float max,final float min,final int offset, final int count){
	final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final loat max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithores(final byte[] key, final double min, final double max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max, final int offset, final int cont){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key,final String min, final String max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		re execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRageByScoreWithSoresmakeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Overrid
	public Set<byte[]>zRevRangeByLex(final byte[] key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByLex(makeByteKey(key),min,max), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return xecute((client)->client.zRevRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]>zRevRangeByLex(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]>zRevRangeByLex(final byte[] key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]>zRevRangeByLex(final byte[] key, final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key,f inal String min, final String max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final float min, final float max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Ovrride
	public Set<byte[]> zRevRangeByLex(final byte[] key, final float min, final float max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final double min, final double max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
		final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key,final int min,nal int max,final int offset,final int ount){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((cliet)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]>zRevRangeByLex(final byte[] key, final int min, final int max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute(lient)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final long min, final long max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute(c ient)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max, final int offset, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@erride
	public Set<String> zRevRangeByLex(final String key, final String min, final String max, final int offset,final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);
		return execute((client)->client.evRangeByLx(makeByteKey(key), min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Long zLexCount(final String key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zLexCount(makeRawKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
	}

	@Override
	public Long zLexCount(final byte[]key, final float min, final float max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zLexCount(makeByteKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
	}

	@Override
	public Long zLexCount(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->cent.zLexCount(makeRawKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
	}

	@Override
	public Long zLexCount(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zLexCount(makeByteKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
	}

	@Override
	public Long zLexCount(final String key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zLexCount(makeRawKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
	}

	@Override
	public Long zLexCount(final byte[] key, final int min, final int max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		retur execute((client)->client.zLexCount(makeByteKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
	}

	@Override
	public Long zLexCount(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zLexCount(makeRawKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
	}

	@Override
	public Long zLexCount(final byte[] key, final long min, final long max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zLexCount(makeByteKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
	}

	@Override
	public Long zLexCount(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zLexCount(makeRawKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
	}

	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);
		return execute((client)->client.zLexCount(makeByteKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
		return execute((client)->client.zInterStore(makeRawKey(destKey), makeRawKeys(keys)), ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
		return execute((client)->client.zInterStore(makeByteKey(destKey), makeByteKeys(keys)), ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
		return execute((client)->client.zInterStore(makeRawKey(destKey), aggregate, makeRawKeys(keys)), ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zInterStore(final byte[]destKey,final Aggregate aggregate,final byte[]...keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
		return execute((client)->client.zInterStore(makeByteKey(destKey), aggregate, makeByteKeys(keys)), ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zInterStore(final String destKey,final double[]weights,final String...keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
		return execute((client)->client.zInterStore(makeRawKey(destKey), weights ,makeRawKeys(keys)), ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zInterStore(final byte[]destKey,final double[]weights,final byte[]...keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
		return execute((client)->client.zInterStore(makeByteKey(destKey), weights, makeByteKeys(keys)), ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final double[] weights, final String...keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
		eturn execute((client)->client.zInterStore(makeRawKey(destKey), aggregate, weights, makeRawKeys(keys)), ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double[] weights, final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
		return execute((client)->client.zInterStore(makeByteKey(destKey), aggregate, weights, makeByteKeys(keys)), ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
		return execute((client)->client.zUnionStore(makeRawKey(destKey), makeRawKeys(keys)), ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);
		return execute((client)->client.zUnionStore(makeByteKey(destKey), makeByteKeys(keys))), ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
		return execute((client)->client.zUnionStore(makeRawKey(destKey), aggregate, makeRawKeys(keys)), ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
		return execute((client)->client.zUnionStore(makeByteKey(destKey), aggregate, makeByteKeys(keys)), ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long zUnionStore(final String destKey, final double[] weights, final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
		return execute((client)->cl ient.zUnionStore(makeRawKey(destKey), weights, makeRawKeys(keys)), ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
		return execute((client)->client.zUnionStore(makeByteKey(destKey), weights, makeByteKeys(keys)), ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate final double[] weights, final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
		return execute((client)->client.zUnionStore(makeRawKey(destKey), aggregate, weights, makeRaw(keys)), ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final double[] weights, final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate", aggregate).put("keys", keys);
		return execute((client)->client.zUnionStore(makeByteKey(destKey), aggregate, weights, makeByteKeys(keys)), ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
		return execute((client)->client.zScan(makeRawKey(key), cursor), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
		return execute((client)->client.zScan(makeByteKey(key), cursor), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor){
		return execute((client)->client.zScan(makeRawKey(key), cursor), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
		return execute(clint)->clien.zScan(makeByteKey(key), cursor), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
		return execute((client)->client.zScan(makeRawKey(key), cursor), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);
		retun execute((client)->client.zScan(makeByteKey(key), cursor), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.zScan(makeRawKey(key), cursor, patern), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.zScan(makeByteKey(key), cursor, pattern), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.zScan(makeRawKey(key), cursor, pattern), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key,final long cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.zScan(makeByteKey(key), cursor, pattern), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.zScan(makeRawKey(key), cursor, pattern), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.zScan(makeByteKey(key), cursor, pattern), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.zScan(makeRawKey(key), cursor, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.zScan(makeByteKey(key), cursor, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.zScan(makeRawKey(key), cursor, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.zScan(makeByteKey(key), cursor, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key,final String cursor,final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.zScan(makeRawKey(key), cursor, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.zScan(makeByteKey(key), cursor, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.zScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.zScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.zScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.zScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.zScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[]cursor, final byte[] pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("pattern", pattern).put("count", count);
		return execute((client)->client.zScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("longitude", longitude).put("latitude", latitude);
		return execute((client)->client.geoAdd(makeRawKey(key), member, longitude, latitude), ProtocolCommand.GEOADD, args);
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("longitude", longitude).put("latitude", latitude);
		return execute((client)->client.geoAdd(makeByteKey(key), member, longitude, latitude), ProtocolCommand.GEOADD, args);
	}

	@Override
	public Long geoAdd(final String key, final String member, final Geo geo){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("geo", geo);
		return execute((client)->client.geoAdd(makeRawKey(key), member, geo), ProtocolCommand.GEOADD, args);
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final Geo geo){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("geo", geo);
		return execute((client)->client.geoAdd(makeByteKey(key), member, geo), ProtocolCommand.GEOADD, args);
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("memberCoordinates",memberCoordinates);
		return execute((client)->client.geoAdd(makeRawKey(key), memberCoordinates), ProtocolCommand.GEOADD, args);
	}

	@Override
	public Long geoAdd(final byte[]key,final Map<byte[],Geo>memberCoordinates){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("memberCoordinates",memberCoordinates);
		return execute((client)->client.geoAdd(makeByteKey(key), memberCoordinates), ProtocolCommand.GEOADD, args);
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.geoPos(makeRawKey(key), members), ProtocolCommand.GEOPOS, args);
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.geoPos(makeByteKey(key), members), ProtocolCommand.GEOPOS, args);
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member1", member1).put("member2", member2);
		return execute((client)->client.geoDist(makeRawKey(key), member1, member2), ProtocolCommand.GEODIST, args);
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member1", member1).put("member2", member2);
		return execute((client)->client.geoDist(makeByteKey(key), member1, member2), ProtocolCommand.GEODIST, args);
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member1", member1).put("member2", member2).put("unit", unit);
		return execute((client)->client.geoDist(makeRawKey(key), member1, member2, unit), ProtocolCommand.GEODIST, args);
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member1", member1).put("member2", member2).put("unit", unit);
		return execute((client)->client.geoDist(makeByteKey(key), member1, member2, unit), ProtocolCommand.GEODIST, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude, final double radius){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("longitude", longitude).put("latitude", latitude).put("radius", radius);
		return execute((client)->client.geoRadius(makeRawKey(key), longitude, latitude, radius), ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[]key,final double ongitude,final double latitude, final double radius){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("longitude", longitude).put("latitude", latitude).put("radius", radius);
		return execute((client)->client.geoRadius(makeByteKey(key), longitude, latitude, radius), ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("geo", geo).put("radius", radius);
		return execute((client)->client.geoRadius(makeRawKey(key), geo, radius), ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("geo", geo).put("radius", radius);
		return execute((client)->client.geoRadius(makeByteKey(key), geo, radius), ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude, final double radius, final GeoUnit unit){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("longitude", longitude).put("latitude", latitude).put("radius", radius).put("unit", unit);
		return execute((client)->client.geoRadius(makeRawKey(key), longitude, latitude, radius, unit), ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude, final double radius,final GeoUnit unit){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("longitude", longitude).put("latitude", latitude).put("radius", radius).put("unit", unit);
		return execute((client)->client.geoRadius(makeByteKey(key), longitude, latitude, radius, unit), ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRdius(final String key, final Geo geo, final double radius, final GeoUnit unit){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("geo", geo).put("radius", radius).put("unit", unit);
		return execute((client)->client.geoRadius(makeRawKey(key), geo, radius, unit), ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius, final GeoUnit unit){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("geo", geo).put("radius", radius).put("unit", unit);
		return execute((client)->client.geoRadius(makeByteKey(key), geo, radius, unit), ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key,final double longitude,final double latitude, final double radius,final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("longitude", longitude).put("latitude", latitude).put("radius", radius).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadius(makeRawKey(key), longitude, latitude, radius, geoArgument), ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(byte[] key, final double longitde, final double latitude, final double raius, final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("longitude", longitude).put("latitude", latitude).put("radius", radius).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadius(makeByteKey(key), longitude, latitude ,radius, geoArgument), ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public Lits<GeoRadius> geoRadius(final String key, final Geo geo, final double radius, final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("geo", geo).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadius(keRawKekey), geo, radius, geoArgument), ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius, final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("geo", geo).put("radius", radius).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadius(makeByteKey(key), geo, radius, geoArgument), ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public ListGeoRadius>geoRadius(final String key, final double longitude, final double latitude, final double radius, final GeoUnit unit, final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("geo", geo).put("radius", radius).put("unit", unit).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadius(makeRawKey(key), longitude, latitude, radius, unit, geoArgument)), ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude, final double radius, final GeoUnit unit, final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("geo", geo).put("radius", radius).put("unit", unit).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadius(makeByteKey(key), longitude, latitude, radius, unit, geoArgument)), ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final Geo geo, final double radius, final Geonit unit, final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("geo", geo).put("radius", radius).put("unit", unit).put("geoArgument", geoArgument);
		return execute(client)->client.geoRadius(makeRawKey(key), geo, radius, unit, geoArgument), ProtocolCommand.GEORADIUS, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final Geo geo, final double radius, final GeoUnit unit, final GeoArgument geArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("geo", geo).put("radius", radius).put("unit", unit).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadius(makeByteKey(key), geo, radius, unit, geoArgument), ProtocolCommand.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("radius", radius);
		return execute((client)->client.geoRadiusByMember(makeRawKey(key), member, radius), ProtocolCommand.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("radius", radius);
		return execute(client)->client.geoRadiusByMember(makeByteKey(key), member, radius), ProtocolCommand.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final Sting member, final double radius, final GeoUnit unit){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("radius", radius).put("unit", unit);
		return execute((client)->client.geoRadiusByMember(makeRawKey(key), member, radius, unit), ProtocolCommand.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius, final GeoUnit unit){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("radius", radius).put("unit", unit);
		return execute((client)->client.geoRadiusByMember(makeByteKey(key), member, radius, unit), ProtocolCommand.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius, final GeoArument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("radius", radius).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadiusByMember(makeRawKey(key), member, radius, geoArgument), ProtocolCommand.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius, final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("radius", radius).put("geoArgument", geoArgument);
		retun execute((client)->client.geoRadiusByMember(makeByteKey(key), member, radius, geoArgument), ProtocolCommand.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius, final GeoUnit unit, final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("radius", radius).put("unit", unit).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadiusByMember(makeRawKey(key), member, radius, unit, geoArgument), ProtocolCommand.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius, final GeoUnit unit, final GeoArgument geoArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put("radius", radius).put("unit", unit).put("geoArgument", geoArgument);
		return execute((client)->client.geoRadiusByMember(makeByteKey(key), member, radius, unit, geoArgument), ProtocolCommand.GEORADIUSBYMEMBER, args);
	}

	@Override
	public List<String> geoHash(final String key, final String... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.geoHash(makeRawKey(key), members), ProtocolCommand.GEOHASH, args);
	}

	@Override
	public List<byte[]>geoHash(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);
		return execute((client)->client.geoHash(makeByteKey(key), members), ProtocolCommand.GEOHASH, args);
	}

	@Override
	public Status setBit(final String key, final long offset, final String value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setBit(makeRawKey(key), offset, value), ProtocolCommand.SETBIT, args);
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
		return execute(client)->client.setBit(makeByteKey(key), offset, value), ProtocolCommand.SETBIT, args);
	}

	@Override
	public Status setBit(final String key,final long offset,final boolean value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setBit(makeRawKey(key), offset, value), ProtocolCommand.SETBIT, args);
	}

	@Override
	public Status setBit(final byte[]key,final long offset,final boolean value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setBit(makeByteKey(key), offset, value), ProtocolCommand.SETBIT, args);
	}

	@Override
	public Status getBit(final String key, final long offset){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset);
		return execute((client)->client.getBit(makeRawKey(key), offset), ProtocolCommand.GETBIT, args);
	}

	@Override
	public Status getBit(final byte[] key, final long offset){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset);
		return execute((client)->client.getBit(makeByteKey(key), offset), ProtocolCommand.GETBIT, args);
	}

	@Override
	public Long bitPos(final String key, final boolean value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execue((client)->client.bitPos(makeRawKey(key), value), ProtocolCommand.BITPOS, args);
	}

	@Override
	public Long bitPos(final byte[]key,final boolean value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);
		return execute((client)->client.bitPos(makeByteKey(key), value), ProtocolCommand.BITPOS, args);
	}

	@Override
	public Long bitPos(final String key,final boolean value,final int start,final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("start", start).put("end", end);
		return execute((client)->client.bitPos(makeRawKey(key), value, start, end), ProtocolCommand.BITPOS, args);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("start", start).put("end", end);
		return execute((client)->client.bitPos(makeByteKey(key), value, start, end), ProtocolCommand.BITPOS, args);
	}

	@Override
	public Long bitOp(final Operation operation,final String destKey,final String...keys){
		final CommandArguments args = CommandArguments.getInstance().put("operation", operation).put("destKey", destKey).put("keys", keys);
		return execute((client)->client.bitOp(operation,makeRawKey(destKey), makeRawKeys(keys)), ProtocolCommand.BITOP, args);
	}

	@Override
	public Long bitOp(final Operation operation, final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("operation", operation).put("destKey", destKey).put("keys", keys);
		return execute((client)->client.bitOp(operation,makeByteKey(destKey), makeByteKeys(keys)), ProtocolCommand.BITOP, args);
	}

	@Override
	public List<Long> bitField(final String key, final String... arguments){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("arguments", arguments);
		return execute((client)->client.bitField(makeRawKey(key), arguments), ProtocolCommand.BITFIELD, args);
	}

	@Override
	public List<Long> bitField(final byte[] key, final byte[]... arguments){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("arguments", arguments);
		return execute((client)->client.bitField(makeByteKey(key), arguments), ProtocolCommand.BITFIELD, args);
	}

	@Override
	public Long bitCount(final String key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.bitCount(makeRawKey(key)), ProtocolCommand.BITCOUNT, args);
	}

	@Override
	public Long bitCount(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		return execute((client)->client.bitCount(makeByteKey(key)), ProtocolCommand.BITCOUNT, args);
	}

	@Override
	public Long bitCount(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.bitCount(makeRawKey(key), start, end), ProtocolCommand.BITCOUNT, args);
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end", end);
		return execute((client)->client.bitCount(makeByteKey(key), start, end), ProtocolCommand.BITCOUNT, args);
	}

	@Override
	public Transaction multi(){
		return execute((client)->client.multi(), ProtocolCommand.MULTI);
	}

	@Override
	public void exec(final Transaction transaction){
		final CommandArguments args = CommandArguments.getInstance().put("transaction", transaction);

		execute(new Executor<Void>(){

			@Override
			public Void execute(RedisClient client){
				client.exec(transaction);
				return null;
			}

		}, ProtocolCommand.EXEC, args);
	}

	@Override
	public void discard(final Transaction transaction){
		final CommandArguments args = CommandArguments.getInstance().put("transaction", transaction);

		execute(new Executor<Void>(){

			@Override
			public Void execute(RedisClient client){
				client.discard(transaction);
				return null;
			}

		}, ProtocolCommand.DISCARD, args);
	}

	@Override
	public Status watch(final String... keys){
		return execute((client)->client.watch(makeRawKeys(keys)), ProtocolCommand.WATCH);
	}

	@Override
	public Status watch(final byte[]... keys){
		return execute((client)->client.watch(makeByteKeys(keys)), ProtocolCommand.WATCH);
	}

	@Override
	public Status unwatch(){
		return execute((client)->client.unwatch(), ProtocolCommand.UNWATCH);
	}

	@Override
	public Long publish(final String channel, final String message){
		final CommandArguments args = CommandArguments.getInstance().put("channel", channel).put("message", message);
		return execute((client)->client.publish(channel, message), ProtocolCommand.PUBLISH, args);
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message){
		final CommandArguments args = CommandArguments.getInstance().put("channel", channel).put("message", message);
		return execute((client)->client.publish(channel, message), ProtocolCommand.PUBLISH, args);
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
		final CommandArguments args = CommandArguments.getInstance().put("channels", channels).put("pubSubListener", pubSubListener);

		execute(new Executor<Void>(){

			@Override
			public Void execute(RedisClient client){
				client.subscribe(channels,pubSubListener);
				return null;
			}

		}, ProtocolCommand.SUBSCRIBE, args);
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
		final CommandArguments args = CommandArguments.getInstance().put("channels", channels).put("pubSubListener", pubSubListener);

		execute(new Executor<Void>(){

			@Override
			public Void execute(RedisClient client){
				client.subscribe(channels,pubSubListener);
				return null;
			}

		}, ProtocolCommand.SUBSCRIBE, args);
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
		final CommandArguments args = CommandArguments.getInstance().put("patterns", patterns).put("pubSubListener", pubSubListener);

		execute(new Executor<Void>(){

			@Override
			public Void execute(RedisClient client){
				client.pSubscribe(patterns, pubSubListener);
				return null;
			}

		}, ProtocolCommand.PSUBSCRIBE, args);
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
		final CommandArguments args = CommandArguments.getInstance().put("patterns", patterns).put("pubSubListener", pubSubListener);

		execute(new Executor<Void>(){

			@Override
			public Void execute(RedisClient client){
				client.pSubscribe(patterns, pubSubListener);
				return null;
			}

		}, ProtocolCommand.PSUBSCRIBE, args);
	}

	@Override
	public Object unSubscribe(){
		return execute((client)->client.unSubscribe(), ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	public Object unSubscribe(final String... channels){
		final CommandArguments args = CommandArguments.getInstance().put("channels", channels);
		return execute((client)->client.unSubscribe(channels), ProtocolCommand.UNSUBSCRIBE, args);
	}

	@Override
	public Object unSubscribe(final byte[]... channels){
		final CommandArguments args = CommandArguments.getInstance().put("channels", channels);
		return execute((client)->client.unSubscribe(channels), ProtocolCommand.UNSUBSCRIBE, args);
	}

	@Override
	public Object pUnSubscribe(){
		return execute((client)->client.pUnSubscribe(), ProtocolCommand.PUNSUBSCRIBE);
	}

	@Override
	public Object pUnSubscribe(final String... patterns){
		final CommandArguments args = CommandArguments.getInstance().put("patterns", patterns);
		return execute((client)->client.pUnSubscribe(patterns), ProtocolCommand.PUNSUBSCRIBE, args);
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns){
		final CommandArguments args = CommandArguments.getInstance().put("patterns", patterns);
		return execute((client)->client.pUnSubscribe(patterns), ProtocolCommand.PUNSUBSCRIBE, args);
	}

	@Override
	public Object eval(final String script){
		final CommandArguments args = CommandArguments.getInstance().put("script", script);
		return execute((client)->client.eval(script), ProtocolCommand.EAVL, args);
	}

	@Override
	public Object eval(final byte[] script){
		final CommandArguments args = CommandArguments.getInstance().put("script", script);
		return execute((client)->client.eval(script), ProtocolCommand.EAVL, args);
	}

	@Override
	public Object eval(final String script,final String...params){
		final CommandArguments args = CommandArguments.getInstance().put("script", script).put("params", params);
		return execute((client)->client.eval(script, params), ProtocolCommand.EAVL, args);
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params){
		final CommandArguments args = CommandArguments.getInstance().put("script", script).put("params", params);
		return execute((client)->client.eval(script, params), ProtocolCommand.EAVL, args);
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] args){
		final CommandArguments args = CommandArguments.getInstance().put("script", script).put("keys", keys).put("args", args);
		return execute((client)->client.eval(script, keys, args), ProtocolCommand.EAVL, args);
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] args){
		final CommandArguments args = CommandArguments.getInstance().put("script", script).put("keys", keys).put("args", args);
		return execute((client)->client.eval(script, keys, args), ProtocolCommand.EAVL, args);
	}

	@Override
	public Object evalSha(final String digest){
		final CommandArguments args = CommandArguments.getInstance().put("digest", digest);
		return execute((client)->client.evalSha(digest), ProtocolCommand.EAVL_SHA, args);
	}

	@Override
	public Object evalSha(final byte[] digest){
		final CommandArguments args = CommandArguments.getInstance().put("digest", digest);
		return execute((client)->client.evalSha(digest), ProtocolCommand.EAVL_SHA, args);
	}

	@Override
	public Object evalSha(final String digest, final String... params){
		final CommandArguments args = CommandArguments.getInstance().put("digest", digest).put("params", params);
		return execute((client)->client.evalSha(digest, params), ProtocolCommand.EAVL_SHA, args);
	}

	@Override
	public Object evalSha(final byte[] digest ,final byte[]... params){
		final CommandArguments args = CommandArguments.getInstance().put("digest", digest).put("params", params);
		return execute((client)->client.evalSha(digest, params), ProtocolCommand.EAVL_SHA, args);
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments){
		final CommandArguments args = CommandArguments.getInstance().put("digest", digest).put("keys", keys).put("arguments", arguments);
		return execute((client)->client.evalSha(digest, keys, arguments), ProtocolCommand.EAVL_SHA, args);
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments){
		final CommandArguments args = CommandArguments.getInstance().put("digest", digest).put("keys", keys).put("arguments", arguments);
		return execute((client)->client.evalSha(digest, keys, arguments), ProtocolCommand.EAVL_SHA, args);
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1){
		final CommandArguments args = CommandArguments.getInstance().put("sha1", sha1);
		return execute((client)->client.scriptExists(sha1), ProtocolCommand.SCRIPT_EXISTS, args);
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1){
		final CommandArguments args = CommandArguments.getInstance().put("sha1", sha1);
		return execute((client)->client.scriptExists(sha1), ProtocolCommand.SCRIPT_EXISTS, args);
	}

	@Override
	public String scriptLoad(final String script){
		final CommandArguments args = CommandArguments.getInstance().put("script", script);
		return execute((client)->client.scriptLoad(script), ProtocolCommand.SCRIPT_LOAD, args);
	}

	@Override
	public byte[] scriptLoad(final byte[] script){
		final CommandArguments args = CommandArguments.getInstance().put("script", script);
		return execute((client)->client.scriptLoad(script), ProtocolCommand.SCRIPT_LOAD, args);
	}

	@Override
	public Status scriptKill(){
		return execute((client)->client.scriptKill(), ProtocolCommand.SCRIPT_KILL);
	}

	@Override
	public Status scriptFlush(){
		return execute((client)->client.scriptFlush(), ProtocolCommand.SCRIPT_FLUSH);
	}

}
