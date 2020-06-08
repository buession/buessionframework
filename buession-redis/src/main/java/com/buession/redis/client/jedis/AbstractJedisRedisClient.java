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

import com.buession.core.Executor;
import com.buession.core.utils.NumberUtils;
import com.buession.lang.Status;
import com.buession.redis.client.AbstractRedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.JedisScanParams;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.transaction.jedis.JedisTransaction;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.commands.JedisCommands;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.buession.redis.utils.ReturnUtils.statusForOK;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisClient<C extends JedisCommands> extends AbstractRedisClient implements JedisRedisClient<C> {

	public AbstractJedisRedisClient(){
		super();
	}

	public AbstractJedisRedisClient(RedisConnection connection){
		super(connection);
	}

	@Override
	public boolean exists(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().exists(key).get(), ProtocolCommand.EXISTS, args);
		}else{
			return execute((cmd)->cmd.exists(key), ProtocolCommand.EXISTS, args);
		}
	}

	@Override
	public Type type(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.enumValueOf(getTransaction().type(key).get(), Type.class),
					ProtocolCommand.TYPE, args);
		}else{
			return execute((cmd)->ReturnUtils.enumValueOf(cmd.type(key), Type.class), ProtocolCommand.TYPE, args);
		}
	}

	@Override
	public Status expire(final String key, final int lifetime){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("lifetime"
				, lifetime);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().expire(key, lifetime).get() == 1),
					ProtocolCommand.EXPIRE, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.expire(key, lifetime) == 1), ProtocolCommand.EXPIRE,
					args);
		}
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put(
				"unixTimestamp", unixTimestamp);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().expireAt(key, unixTimestamp).get() == 1),
					ProtocolCommand.EXPIREAT, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.expireAt(key, unixTimestamp) == 1),
					ProtocolCommand.EXPIREAT, args);
		}
	}

	@Override
	public Status pExpire(final String key, final int lifetime){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("lifetime"
				, lifetime);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().pexpire(key, lifetime).get() == 1),
					ProtocolCommand.PEXPIRE, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pexpire(key, lifetime) == 1), ProtocolCommand.PEXPIRE,
					args);
		}
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put(
				"unixTimestamp", unixTimestamp);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().pexpireAt(key, unixTimestamp).get() == 1)
					, ProtocolCommand.PEXPIREAT, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pexpireAt(key, unixTimestamp) == 1),
					ProtocolCommand.PEXPIREAT, args);
		}
	}

	@Override
	public Long ttl(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().ttl(key).get(), ProtocolCommand.TTL, args);
		}else{
			return execute((cmd)->cmd.ttl(key), ProtocolCommand.TTL, args);
		}
	}

	@Override
	public Long pTtl(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().pttl(key).get(), ProtocolCommand.PTTL, args);
		}else{
			return execute((cmd)->cmd.pttl(key), ProtocolCommand.PTTL, args);
		}
	}

	@Override
	public Status persist(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().persist(key).get() > 0),
					ProtocolCommand.PERSIST, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.persist(key) > 0), ProtocolCommand.PERSIST, args);
		}
	}

	@Override
	public List<String> sort(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key).get(), ProtocolCommand.SORT, args);
		}else{
			return execute((cmd)->cmd.sort(key), ProtocolCommand.SORT, args);
		}
	}

	@Override
	public List<String> sort(final String key, final KeyCommands.SortArgument sortArgument){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put(
				"sortArgument", sortArgument);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key, JedisClientUtils.sortArgumentConvert(sortArgument)).get()
					, ProtocolCommand.SORT, args);
		}else{
			return execute((cmd)->cmd.sort(key, JedisClientUtils.sortArgumentConvert(sortArgument)),
					ProtocolCommand.SORT, args);
		}
	}

	@Override
	public byte[] dump(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().dump(key).get(), ProtocolCommand.DUMP, args);
		}else{
			return execute((cmd)->cmd.dump(key), ProtocolCommand.DUMP, args);
		}
	}

	@Override
	public Status restore(final String key, final String serializedValue, final int ttl){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put(
				"serializedValue", serializedValue).put("ttl", ttl);

		if(isTransaction()){
			return execute((cmd)->statusForOK(getTransaction().restore(key, ttl, SafeEncoder.encode(serializedValue)).get()), ProtocolCommand.RESTORE, args);
		}else{
			return execute((cmd)->statusForOK(cmd.restore(key, ttl, SafeEncoder.encode(serializedValue))),
					ProtocolCommand.RESTORE, args);
		}
	}

	@Override
	public Status move(final String key, final int db){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("db", db);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().move(key, db).get() > 0),
					ProtocolCommand.MOVE, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.move(key, db) > 0), ProtocolCommand.MOVE, args);
		}
	}

	@Override
	public Status set(final String key, final String value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value);

		if(isTransaction()){
			return execute((cmd)->statusForOK(getTransaction().set(key, value).get()), ProtocolCommand.SET, args);
		}else{
			return execute((cmd)->statusForOK(cmd.set(key, value)), ProtocolCommand.SET, args);
		}
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value).put("setArgument", setArgument);

		if(isTransaction()){
			return execute((cmd)->statusForOK(getTransaction().set(key, value,
					JedisClientUtils.setArgumentConvert(setArgument)).get()), ProtocolCommand.SET, args);
		}else{
			return execute((cmd)->statusForOK(cmd.set(key, value, JedisClientUtils.setArgumentConvert(setArgument))),
					ProtocolCommand.SET, args);
		}
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value).put("lifetime", lifetime);

		if(isTransaction()){
			return execute((cmd)->statusForOK(getTransaction().setex(key, lifetime, value).get()),
					ProtocolCommand.SETEX, args);
		}else{
			return execute((cmd)->statusForOK(cmd.setex(key, lifetime, value)), ProtocolCommand.SETEX, args);
		}
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value).put("lifetime", lifetime);

		if(isTransaction()){
			return execute((cmd)->statusForOK(getTransaction().psetex(key, lifetime, value).get()),
					ProtocolCommand.PSETEX, args);
		}else{
			return execute((cmd)->statusForOK(cmd.psetex(key, lifetime, value)), ProtocolCommand.PSETEX, args);
		}
	}

	@Override
	public Status setNx(final String key, final String value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().setnx(key, value).get() > 0),
					ProtocolCommand.SETNX, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(isTransaction() ?
					getTransaction().setnx(key, value).get() > 0 : cmd.setnx(key, value) > 0), ProtocolCommand.SETNX,
					args);
		}
	}

	@Override
	public Long append(final String key, final String value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value);

		if(isTransaction()){
			return execute((cmd)->getTransaction().append(key, value).get(), ProtocolCommand.APPEND, args);
		}else{
			return execute((cmd)->cmd.append(key, value), ProtocolCommand.APPEND, args);
		}
	}

	@Override
	public String get(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().get(key).get(), ProtocolCommand.GET, args);
		}else{
			return execute((cmd)->cmd.get(key), ProtocolCommand.GET, args);
		}
	}

	@Override
	public String getSet(final String key, final String value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value);

		if(isTransaction()){
			return execute((cmd)->getTransaction().getSet(key, value).get(), ProtocolCommand.GETSET, args);
		}else{
			return execute((cmd)->cmd.getSet(key, value), ProtocolCommand.GETSET, args);
		}
	}

	@Override
	public Long incr(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().incr(key).get(), ProtocolCommand.INCR, args);
		}else{
			return execute((cmd)->cmd.incr(key), ProtocolCommand.INCR, args);
		}
	}

	@Override
	public Long incrBy(final String key, final int value){
		return incrBy(key, (long) value);
	}

	@Override
	public Long incrBy(final byte[] key, final int value){
		return incrBy(key, (long) value);
	}

	@Override
	public Long incrBy(final String key, final long value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value);

		if(isTransaction()){
			return execute((cmd)->getTransaction().incrBy(key, value).get(), ProtocolCommand.INCRBY, args);
		}else{
			return execute((cmd)->cmd.incrBy(key, value), ProtocolCommand.INCRBY, args);
		}
	}

	@Override
	public Double incrByFloat(final String key, final float value){
		return incrByFloat(key, (double) value);
	}

	@Override
	public Double incrByFloat(final byte[] key, final float value){
		return incrByFloat(key, (double) value);
	}

	@Override
	public Double incrByFloat(final String key, final double value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value);

		if(isTransaction()){
			return execute((cmd)->getTransaction().incrByFloat(key, value).get(), ProtocolCommand.INCRBYFLOAT, args);
		}else{
			return execute((cmd)->cmd.incrByFloat(key, value), ProtocolCommand.INCRBYFLOAT, args);
		}
	}

	@Override
	public Long decr(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().decr(key).get(), ProtocolCommand.DECR, args);
		}else{
			return execute((cmd)->cmd.decr(key), ProtocolCommand.DECR, args);
		}
	}

	@Override
	public Long decrBy(final String key, final int value){
		return decrBy(key, (long) value);
	}

	@Override
	public Long decrBy(final byte[] key, final int value){
		return decrBy(key, (long) value);
	}

	@Override
	public Long decrBy(final String key, final long value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value);

		if(isTransaction()){
			return execute((cmd)->getTransaction().decrBy(key, value).get(), ProtocolCommand.DECRBY, args);
		}else{
			return execute((cmd)->cmd.decrBy(key, value), ProtocolCommand.DECRBY, args);
		}
	}

	@Override
	public Long setRange(final String key, final int offset, final String value){
		return setRange(key, (long) offset, value);
	}

	@Override
	public Long setRange(final byte[] key, final int offset, final byte[] value){
		return setRange(key, (long) offset, value);
	}

	@Override
	public Long setRange(final String key, final long offset, final String value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("offset",
				offset).put("value", value);

		if(isTransaction()){
			return execute((cmd)->getTransaction().setrange(key, offset, value).get(), ProtocolCommand.SETRANGE, args);
		}else{
			return execute((cmd)->cmd.setrange(key, offset, value), ProtocolCommand.SETRANGE, args);
		}
	}

	@Override
	public String getRange(final String key, final int start, final int end){
		return getRange(key, (long) start, (long) end);
	}

	@Override
	public byte[] getRange(final byte[] key, final int start, final int end){
		return getRange(key, (long) start, (long) end);
	}

	@Override
	public String getRange(final String key, final long start, final long end){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("start",
				start).put("end", end);

		if(isTransaction()){
			return execute((cmd)->getTransaction().getrange(key, start, end).get(), ProtocolCommand.GETRANGE, args);
		}else{
			return execute((cmd)->cmd.getrange(key, start, end), ProtocolCommand.GETRANGE, args);
		}
	}

	@Override
	public String substr(final String key, final int start, final int end){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("start",
				start).put("end", end);

		if(isTransaction()){
			return execute((cmd)->getTransaction().substr(key, start, end).get(), ProtocolCommand.SUBSTR, args);
		}else{
			return execute((cmd)->cmd.substr(key, start, end), ProtocolCommand.SUBSTR, args);
		}
	}

	@Override
	public String substr(final String key, final long start, final long end){
		return substr(key, (int) start, (int) end);
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end){
		return substr(key, (int) start, (int) end);
	}

	@Override
	public Long strlen(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().strlen(key).get(), ProtocolCommand.STRLEN, args);
		}else{
			return execute((cmd)->cmd.strlen(key), ProtocolCommand.STRLEN, args);
		}
	}

	@Override
	public boolean hExists(final String key, final String field){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("field",
				field);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hexists(key, field).get(), ProtocolCommand.HEXISTS, args);
		}else{
			return execute((cmd)->cmd.hexists(key, field), ProtocolCommand.HEXISTS, args);
		}
	}

	@Override
	public Set<String> hKeys(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hkeys(key).get(), ProtocolCommand.HKEYS, args);
		}else{
			return execute((cmd)->cmd.hkeys(key), ProtocolCommand.HKEYS, args);
		}
	}

	@Override
	public List<String> hVals(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hvals(key).get(), ProtocolCommand.HVALS, args);
		}else{
			return execute((cmd)->cmd.hvals(key), ProtocolCommand.HVALS, args);
		}
	}

	@Override
	public Status hSet(final String key, final String field, final String value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("field",
				field).put("value", value);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().hset(key, field, value).get() > 0),
					ProtocolCommand.HSET, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.hset(key, field, value) > 0), ProtocolCommand.HSET,
					args);
		}
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("field",
				field).put("value", value);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().hsetnx(key, field, value).get() > 0),
					ProtocolCommand.HSETNX, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.hsetnx(key, field, value) > 0), ProtocolCommand.HSETNX
					, args);
		}
	}

	@Override
	public String hGet(final String key, final String field){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("field",
				field);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hget(key, field).get(), ProtocolCommand.HGET, args);
		}else{
			return execute((cmd)->cmd.hget(key, field), ProtocolCommand.HGET, args);
		}
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("data",
				data);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().hmset(key, data).get()),
					ProtocolCommand.HMSET, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.hmset(key, data)), ProtocolCommand.HMSET, args);
		}
	}

	@Override
	public List<String> hMGet(final String key, final String... fields){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("fields",
				fields);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hmget(key, fields).get(), ProtocolCommand.HMGET, args);
		}else{
			return execute((cmd)->cmd.hmget(key, fields), ProtocolCommand.HMGET, args);
		}
	}

	@Override
	public Map<String, String> hGetAll(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hgetAll(key).get(), ProtocolCommand.HGETALL, args);
		}else{
			return execute((cmd)->cmd.hgetAll(key), ProtocolCommand.HGETALL, args);
		}
	}

	@Override
	public Long hStrLen(final String key, final String field){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("field",
				field);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hstrlen(key, field).get(), ProtocolCommand.HSTRLEN, args);
		}else{
			return execute((cmd)->cmd.hstrlen(key, field), ProtocolCommand.HSTRLEN, args);
		}
	}

	@Override
	public Long hLen(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hlen(key).get(), ProtocolCommand.HLEN, args);
		}else{
			return execute((cmd)->cmd.hlen(key), ProtocolCommand.HLEN, args);
		}
	}

	@Override
	public Long hIncrBy(final String key, final String field, final int value){
		return hIncrBy(key, field, (long) value);
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final int value){
		return hIncrBy(key, field, (long) value);
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("field",
				field).put("value", value);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hincrBy(key, field, value).get(), ProtocolCommand.HINCRBY, args);
		}else{
			return execute((cmd)->cmd.hincrBy(key, field, value), ProtocolCommand.HINCRBY, args);
		}
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final float value){
		return hIncrByFloat(key, field, (double) value);
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final float value){
		return hIncrByFloat(key, field, (double) value);
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("field",
				field).put("value", value);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hincrByFloat(key, field, value).get(), ProtocolCommand.HINCRBYFLOAT
					, args);
		}else{
			return execute((cmd)->cmd.hincrByFloat(key, field, value), ProtocolCommand.HINCRBYFLOAT, args);
		}
	}

	@Override
	public Long hDecrBy(final String key, final String field, final int value){
		return hDecrBy(key, field, (long) value);
	}

	@Override
	public Long hDecrBy(final byte[] key, final byte[] field, final int value){
		return hDecrBy(key, field, (long) value);
	}

	@Override
	public Long hDecrBy(final String key, final String field, final long value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("field",
				field).put("value", value);

		final long val = value > 0 ? value * -1 : value;
		if(isTransaction()){
			return execute((cmd)->getTransaction().hincrBy(key, field, val).get(), ProtocolCommand.HINCRBY, args);
		}else{
			return execute((cmd)->cmd.hincrBy(key, field, val), ProtocolCommand.HINCRBY, args);
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor){
		return hScan(key, Integer.toString(cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor){
		return hScan(key, NumberUtils.int2bytes(cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor){
		return hScan(key, Long.toString(cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor){
		return hScan(key, NumberUtils.long2bytes(cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("cursor",
				cursor);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor)), ProtocolCommand.HSCAN
					, args);
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final String pattern){
		return hScan(key, Integer.toString(cursor), pattern);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern){
		return hScan(key, NumberUtils.int2bytes(cursor), pattern);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern){
		return hScan(key, Long.toString(cursor), pattern);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern){
		return hScan(key, NumberUtils.long2bytes(cursor), pattern);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("cursor",
				cursor).put("pattern", pattern);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor,
					new JedisScanParams(pattern))), ProtocolCommand.HSCAN, args);
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final int count){
		return hScan(key, Integer.toString(cursor), count);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final int count){
		return hScan(key, NumberUtils.int2bytes(cursor), count);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final int count){
		return hScan(key, Long.toString(cursor), count);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final int count){
		return hScan(key, NumberUtils.long2bytes(cursor), count);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("cursor",
				cursor).put("count", count);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor,
					new JedisScanParams(count))), ProtocolCommand.HSCAN, args);
		}
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final String pattern,
			final int count){
		return hScan(key, Integer.toString(cursor), pattern, count);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern,
			final int count){
		return hScan(key, NumberUtils.int2bytes(cursor), pattern, count);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern,
			final int count){
		return hScan(key, Long.toString(cursor), pattern, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern,
			final int count){
		return hScan(key, NumberUtils.long2bytes(cursor), pattern, count);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
			final int count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("cursor",
				cursor).put("pattern", pattern).put("count", count);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor,
					new JedisScanParams(pattern, count))), ProtocolCommand.HSCAN, args);
		}
	}

	@Override
	public Long hDel(final String key, final String... fields){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hdel(key, fields).get(), ProtocolCommand.HDEL, args);
		}else{
			return execute((cmd)->cmd.hdel(key, fields), ProtocolCommand.HDEL, args);
		}
	}

	@Override
	public Long lPush(final String key, final String... values){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("values",
				values);

		if(isTransaction()){
			return execute((cmd)->getTransaction().lpush(key, values).get(), ProtocolCommand.LPUSH, args);
		}else{
			return execute((cmd)->cmd.lpush(key, values), ProtocolCommand.LPUSH, args);
		}
	}

	@Override
	public Long lPushX(final String key, final String... values){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("values",
				values);

		if(isTransaction()){
			return execute((cmd)->getTransaction().lpushx(key, values).get(), ProtocolCommand.LPUSHX, args);
		}else{
			return execute((cmd)->cmd.lpushx(key, values), ProtocolCommand.LPUSHX, args);
		}
	}

	@Override
	public Long lInsert(final String key, final String value, final ListPosition position, final String pivot){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("position"
				, position).put("pivot", pivot).put("value", value);

		if(isTransaction()){
			return execute((cmd)->cmd.linsert(key, JedisClientUtils.listPositionConvert(position), pivot, value),
					ProtocolCommand.LINSERT, args);
		}else{
			return execute((cmd)->cmd.linsert(key, JedisClientUtils.listPositionConvert(position), pivot, value),
					ProtocolCommand.LINSERT, args);
		}
	}

	@Override
	public Status lSet(final String key, final int index, final String value){
		return lSet(key, (long) index, value);
	}

	@Override
	public Status lSet(final byte[] key, final int index, final byte[] value){
		return lSet(key, (long) index, value);
	}

	@Override
	public Status lSet(final String key, final long index, final String value){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("index",
				index).put("value", value);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().lset(key, index, value).get()),
					ProtocolCommand.LSET, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.lset(key, index, value)), ProtocolCommand.LSET, args);
		}
	}

	@Override
	public String lIndex(final String key, final int index){
		return lIndex(key, (long) index);
	}

	@Override
	public byte[] lIndex(final byte[] key, final int index){
		return lIndex(key, (long) index);
	}

	@Override
	public String lIndex(final String key, final long index){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("index",
				index);

		if(isTransaction()){
			return execute((cmd)->getTransaction().lindex(key, index).get(), ProtocolCommand.LINDEX, args);
		}else{
			return execute((cmd)->cmd.lindex(key, index), ProtocolCommand.LINDEX, args);
		}
	}

	@Override
	public String lPop(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().lpop(key).get(), ProtocolCommand.LPOP, args);
		}else{
			return execute((cmd)->cmd.lpop(key), ProtocolCommand.LPOP, args);
		}
	}

	@Override
	public String rPop(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().rpop(key).get(), ProtocolCommand.RPOP, args);
		}else{
			return execute((cmd)->cmd.rpop(key), ProtocolCommand.RPOP, args);
		}
	}

	@Override
	public Long rPush(final String key, final String... values){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("values",
				values);

		if(isTransaction()){
			return execute((cmd)->getTransaction().rpush(key, values).get(), ProtocolCommand.RPUSH, args);
		}else{
			return execute((cmd)->cmd.rpush(key, values), ProtocolCommand.RPUSH, args);
		}
	}

	@Override
	public Long rPushX(final String key, final String... values){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("values",
				values);

		if(isTransaction()){
			return execute((cmd)->getTransaction().rpushx(key, values).get(), ProtocolCommand.RPUSHX, args);
		}else{
			return execute((cmd)->cmd.rpushx(key, values), ProtocolCommand.RPUSHX, args);
		}
	}

	@Override
	public Status lTrim(final String key, final int start, final int end){
		return lTrim(key, (long) start, (long) end);
	}

	@Override
	public Status lTrim(final byte[] key, final int start, final int end){
		return lTrim(key, (long) start, (long) end);
	}

	@Override
	public Status lTrim(final String key, final long start, final long end){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("start",
				start).put("end", end);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().ltrim(key, start, end).get()),
					ProtocolCommand.LTRIM, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.ltrim(key, start, end)), ProtocolCommand.LTRIM, args);
		}
	}

	@Override
	public Long lRem(final String key, final String value, final int count){
		return lRem(key, value, (long) count);
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final int count){
		return lRem(key, value, (long) count);
	}

	@Override
	public Long lRem(final String key, final String value, final long count){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("value",
				value).put("count", count);

		if(isTransaction()){
			return execute((cmd)->getTransaction().lrem(key, count, value).get(), ProtocolCommand.LREM, args);
		}else{
			return execute((cmd)->cmd.lrem(key, count, value), ProtocolCommand.LREM, args);
		}
	}

	@Override
	public List<String> lRange(final String key, final int start, final int end){
		return lRange(key, (long) start, (long) end);
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final int start, final int end){
		return lRange(key, (long) start, (long) end);
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key).put("start",
				start).put("end", end);

		if(isTransaction()){
			return execute((cmd)->getTransaction().lrange(key, start, end).get(), ProtocolCommand.LRANGE, args);
		}else{
			return execute((cmd)->cmd.lrange(key, start, end), ProtocolCommand.LRANGE, args);
		}
	}

	@Override
	public Long lLen(final String key){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().llen(key).get(), ProtocolCommand.LLEN, args);
		}else{
			return execute((cmd)->cmd.llen(key), ProtocolCommand.LLEN, args);
		}
	}

	protected redis.clients.jedis.Transaction getTransaction(){
		JedisTransaction jedisTransaction = (JedisTransaction) getConnection().getTransaction();
		return jedisTransaction.primitive();
	}

	protected <R> R execute(final Executor<C, R> executor, final ProtocolCommand command) throws RedisException{
		return super.doExecute(executor, command, null);
	}

	protected <R> R execute(final Executor<C, R> executor, final ProtocolCommand command,
			final OperationsCommandArguments arguments){
		return super.doExecute(executor, command, arguments);
	}

}