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
import com.buession.lang.Status;
import com.buession.redis.client.ShardedRedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.JedisScanParams;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.command.ListCommands;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.SortingParams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class ShardedJedisClient extends AbstractJedisRedisClient<ShardedJedis> implements ShardedRedisClient {

	public ShardedJedisClient(){
		super();
	}

	public ShardedJedisClient(RedisConnection connection){
		super(connection);
	}

	@Override
	public boolean exists(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((ShardedJedis cmd)->getTransaction().exists(key).get(), ProtocolCommand.EXISTS, args);
		}else{
			return execute((ShardedJedis cmd)->cmd.exists(key), ProtocolCommand.EXISTS, args);
		}
	}

	@Override
	public Type type(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((ShardedJedis cmd)->ReturnUtils.enumValueOf(getTransaction().type(key).get(), Type.class),
					ProtocolCommand.TYPE, args);
		}else{
			return execute((cmd)->ReturnUtils.enumValueOf(cmd.type(key), Type.class), ProtocolCommand.TYPE, args);
		}
	}

	@Override
	public Status expire(final byte[] key, final int lifetime){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("lifetime", lifetime);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().expire(key, lifetime).get() == 1),
					ProtocolCommand.EXPIRE, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.expire(key, lifetime) == 1), ProtocolCommand.EXPIRE,
					args);
		}
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("unixTimestamp",
				unixTimestamp);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().expireAt(key, unixTimestamp).get() == 1),
					ProtocolCommand.EXPIREAT, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.expireAt(key, unixTimestamp) == 1),
					ProtocolCommand.EXPIREAT, args);
		}
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("lifetime", lifetime);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().pexpire(key, lifetime).get() == 1),
					ProtocolCommand.PEXPIRE, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pexpire(key, lifetime) == 1), ProtocolCommand.PEXPIRE,
					args);
		}
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("unixTimestamp",
				unixTimestamp);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().pexpireAt(key, unixTimestamp).get() == 1)
					, ProtocolCommand.PEXPIREAT, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pexpireAt(key, unixTimestamp) == 1),
					ProtocolCommand.PEXPIREAT, args);
		}
	}

	@Override
	public Long ttl(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().ttl(key).get(), ProtocolCommand.TTL, args);
		}else{
			return execute((cmd)->cmd.ttl(key), ProtocolCommand.TTL, args);
		}
	}

	@Override
	public Long pTtl(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().pttl(key).get(), ProtocolCommand.PTTL, args);
		}else{
			return execute((cmd)->cmd.pttl(key), ProtocolCommand.PTTL, args);
		}
	}

	@Override
	public Status persist(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().persist(key).get() > 0),
					ProtocolCommand.PERSIST, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.persist(key) > 0), ProtocolCommand.PERSIST, args);
		}
	}

	@Override
	public List<byte[]> sort(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key).get(), ProtocolCommand.SORT, args);
		}else{
			return execute((cmd)->cmd.sort(key), ProtocolCommand.SORT, args);
		}
	}

	@Override
	public List<byte[]> sort(final byte[] key, final KeyCommands.SortArgument sortArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);
		final SortingParams sortingParams = JedisClientUtils.sortArgumentConvert(sortArgument);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key, sortingParams).get(), ProtocolCommand.SORT, args);
		}else{
			return execute((cmd)->cmd.sort(key, sortingParams), ProtocolCommand.SORT, args);
		}
	}

	@Override
	public byte[] dump(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().dump(key).get(), ProtocolCommand.DUMP, args);
		}else{
			return execute((cmd)->cmd.dump(key), ProtocolCommand.DUMP, args);
		}
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("serializedValue",
				serializedValue).put("ttl", ttl);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().restore(key, ttl, serializedValue).get()),
					ProtocolCommand.RESTORE, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.restore(key, ttl, serializedValue)),
					ProtocolCommand.RESTORE, args);
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("host", host).put("port",
				port).put("key", key).put("db", db).put("timeout", timeout);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().migrate(host, port, key, db, timeout).get()), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(getShard(cmd, key).migrate(host, port, key, db, timeout)),
					ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("host", host).put("port",
				port).put("key", key).put("db", db).put("timeout", timeout);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().migrate(host, port, key, db, timeout).get()), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(getShard(cmd, key).migrate(host, port, key, db, timeout)),
					ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation migrateOperation){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("host", host).put("port",
				port).put("key", key).put("db", db).put("timeout", timeout).put("migrate", migrateOperation);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().migrate(host, port, db, timeout,
					JedisClientUtils.migrateOperationConvert(migrateOperation), key).get()), ProtocolCommand.MIGRATE,
					args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(getShard(cmd, key).migrate(host, port, db, timeout,
					JedisClientUtils.migrateOperationConvert(migrateOperation), key)), ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation migrateOperation){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("host", host).put("port",
				port).put("key", key).put("db", db).put("timeout", timeout).put("migrate", migrateOperation);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().migrate(host, port, db, timeout,
					JedisClientUtils.migrateOperationConvert(migrateOperation), key).get()), ProtocolCommand.MIGRATE,
					args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(getShard(cmd, key).migrate(host, port, db, timeout,
					JedisClientUtils.migrateOperationConvert(migrateOperation), key)), ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Long del(final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);

		return execute(new Executor<ShardedJedis, Long>() {

			@Override
			public Long execute(ShardedJedis cmd){
				if(isTransaction()){
					return getTransaction().del(keys).get();
				}else{
					if(keys != null){
						long result = 0;

						for(String key : keys){
							result += cmd.del(key);
						}

						return result;
					}

					return 0L;
				}
			}

		}, ProtocolCommand.DEL, args);
	}

	@Override
	public Long del(final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);

		return execute(new Executor<ShardedJedis, Long>() {

			@Override
			public Long execute(ShardedJedis cmd){
				if(isTransaction()){
					return getTransaction().del(keys).get();
				}else{
					if(keys != null){
						long result = 0;

						for(byte[] key : keys){
							result += cmd.del(key);
						}

						return result;
					}

					return 0L;
				}
			}

		}, ProtocolCommand.DEL, args);
	}

	@Override
	public Status move(final byte[] key, final int db){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("db", db);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().move(key, db).get() > 0),
					ProtocolCommand.MOVE, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.move(key, db) > 0), ProtocolCommand.MOVE, args);
		}
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().set(key, value).get()), ProtocolCommand.SET
					, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.set(key, value)), ProtocolCommand.SET, args);
		}
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final StringCommands.SetArgument setArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put(
				"setArgument", setArgument);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().set(key, value,
					JedisClientUtils.setArgumentConvert(setArgument)).get()), ProtocolCommand.SET, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.set(key, value,
					JedisClientUtils.setArgumentConvert(setArgument))), ProtocolCommand.SET, args);
		}
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("lifetime"
				, lifetime);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().setex(key, lifetime, value).get()),
					ProtocolCommand.SETEX, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.setex(key, lifetime, value)), ProtocolCommand.SETEX,
					args);
		}
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("lifetime"
				, lifetime);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().psetex(key, lifetime, value).get()),
					ProtocolCommand.PSETEX, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.psetex(key, lifetime, value)), ProtocolCommand.PSETEX,
					args);
		}
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().setnx(key, value).get() > 0),
					ProtocolCommand.SETNX, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setnx(key, value) > 0), ProtocolCommand.SETNX, args);
		}
	}

	@Override
	public Long append(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);

		if(isTransaction()){
			return execute((cmd)->getTransaction().append(key, value).get(), ProtocolCommand.APPEND, args);
		}else{
			return execute((cmd)->cmd.append(key, value), ProtocolCommand.APPEND, args);
		}
	}

	@Override
	public byte[] get(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().get(key).get(), ProtocolCommand.GET, args);
		}else{
			return execute((cmd)->cmd.get(key), ProtocolCommand.GET, args);
		}
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);

		if(isTransaction()){
			return execute((cmd)->getTransaction().getSet(key, value).get(), ProtocolCommand.GETSET, args);
		}else{
			return execute((cmd)->cmd.getSet(key, value), ProtocolCommand.GETSET, args);
		}
	}

	@Override
	public Long incr(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().incr(key).get(), ProtocolCommand.INCR, args);
		}else{
			return execute((cmd)->cmd.incr(key), ProtocolCommand.INCR, args);
		}
	}

	@Override
	public Long incrBy(final byte[] key, final long value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);

		if(isTransaction()){
			return execute((cmd)->getTransaction().incrBy(key, value).get(), ProtocolCommand.INCRBY, args);
		}else{
			return execute((cmd)->cmd.incrBy(key, value), ProtocolCommand.INCRBY, args);
		}
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);

		if(isTransaction()){
			return execute((cmd)->getTransaction().incrByFloat(key, value).get(), ProtocolCommand.INCRBYFLOAT, args);
		}else{
			return execute((cmd)->cmd.incrByFloat(key, value), ProtocolCommand.INCRBYFLOAT, args);
		}
	}

	@Override
	public Long decr(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().decr(key).get(), ProtocolCommand.DECR, args);
		}else{
			return execute((cmd)->cmd.decr(key), ProtocolCommand.DECR, args);
		}
	}

	@Override
	public Long decrBy(final byte[] key, final long value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);

		if(isTransaction()){
			return execute((cmd)->getTransaction().decrBy(key, value).get(), ProtocolCommand.DECRBY, args);
		}else{
			return execute((cmd)->cmd.decrBy(key, value), ProtocolCommand.DECRBY, args);
		}
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("offset", offset).put("value"
				, value);

		if(isTransaction()){
			return execute((cmd)->getTransaction().setrange(key, offset, value).get(), ProtocolCommand.SETRANGE, args);
		}else{
			return execute((cmd)->cmd.setrange(key, offset, value), ProtocolCommand.SETRANGE, args);
		}
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end",
				end);

		if(isTransaction()){
			return execute((cmd)->getTransaction().getrange(key, start, end).get(), ProtocolCommand.GETRANGE, args);
		}else{
			return execute((cmd)->cmd.getrange(key, start, end), ProtocolCommand.GETRANGE, args);
		}
	}

	@Override
	public byte[] substr(final byte[] key, final int start, final int end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end",
				end);

		if(isTransaction()){
			return execute((cmd)->SafeEncoder.encode(getTransaction().substr(key, start, end).get()),
					ProtocolCommand.SUBSTR, args);
		}else{
			return execute((cmd)->cmd.substr(key, start, end), ProtocolCommand.SUBSTR, args);
		}
	}

	@Override
	public Long strlen(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().strlen(key).get(), ProtocolCommand.STRLEN, args);
		}else{
			return execute((cmd)->cmd.strlen(key), ProtocolCommand.STRLEN, args);
		}
	}

	@Override
	public boolean hExists(final byte[] key, final byte[] field){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hexists(key, field).get(), ProtocolCommand.HEXISTS, args);
		}else{
			return execute((cmd)->cmd.hexists(key, field), ProtocolCommand.HEXISTS, args);
		}
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hkeys(key).get(), ProtocolCommand.HKEYS, args);
		}else{
			return execute((cmd)->cmd.hkeys(key), ProtocolCommand.HKEYS, args);
		}
	}

	@Override
	public List<byte[]> hVals(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		return execute(new Executor<ShardedJedis, List<byte[]>>() {

			@Override
			public List<byte[]> execute(ShardedJedis cmd){
				if(isTransaction()){
					return getTransaction().hvals(key).get();
				}else{
					Collection<byte[]> result = cmd.hvals(key);
					return result == null ? null : new ArrayList<>(result);
				}
			}

		}, ProtocolCommand.HVALS, args);
	}

	@Override
	public Status hSet(final byte[] key, final byte[] field, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value",
				value);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().hset(key, field, value).get() > 0),
					ProtocolCommand.HSET, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.hset(key, field, value) > 0), ProtocolCommand.HSET,
					args);
		}
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value",
				value);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().hsetnx(key, field, value).get() > 0),
					ProtocolCommand.HSETNX, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.hsetnx(key, field, value) > 0), ProtocolCommand.HSETNX
					, args);
		}
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hget(key, field).get(), ProtocolCommand.HGET, args);
		}else{
			return execute((cmd)->cmd.hget(key, field), ProtocolCommand.HGET, args);
		}
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("data", data);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().hmset(key, data).get()),
					ProtocolCommand.HMSET, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.hmset(key, data)), ProtocolCommand.HMSET, args);
		}
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("fields", fields);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hmget(key, fields).get(), ProtocolCommand.HMGET, args);
		}else{
			return execute((cmd)->cmd.hmget(key, fields), ProtocolCommand.HMGET, args);
		}
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hgetAll(key).get(), ProtocolCommand.HGETALL, args);
		}else{
			return execute((cmd)->cmd.hgetAll(key), ProtocolCommand.HGETALL, args);
		}
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hstrlen(key, field).get(), ProtocolCommand.HSTRLEN, args);
		}else{
			return execute((cmd)->cmd.hstrlen(key, field), ProtocolCommand.HSTRLEN, args);
		}
	}

	@Override
	public Long hLen(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hlen(key).get(), ProtocolCommand.HLEN, args);
		}else{
			return execute((cmd)->cmd.hlen(key), ProtocolCommand.HLEN, args);
		}
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value",
				value);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hincrBy(key, field, value).get(), ProtocolCommand.HINCRBY, args);
		}else{
			return execute((cmd)->cmd.hincrBy(key, field, value), ProtocolCommand.HINCRBY, args);
		}
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("field", field).put("value",
				value);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hincrByFloat(key, field, value).get(), ProtocolCommand.HINCRBYFLOAT
					, args);
		}else{
			return execute((cmd)->cmd.hincrByFloat(key, field, value), ProtocolCommand.HINCRBYFLOAT, args);
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor)), ProtocolCommand.HSCAN
					, args);
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put(
				"pattern", pattern);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor,
					new JedisScanParams(pattern))), ProtocolCommand.HSCAN, args);
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count"
				, count);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor,
					new JedisScanParams(count))), ProtocolCommand.HSCAN, args);
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put(
				"pattern", pattern).put("count", count);

		return execute((cmd)->JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor, new JedisScanParams(pattern
				, count))), ProtocolCommand.HSCAN, args);
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().hdel(key, fields).get(), ProtocolCommand.HDEL, args);
		}else{
			return execute((cmd)->cmd.hdel(key, fields), ProtocolCommand.HDEL, args);
		}
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);

		if(isTransaction()){
			return execute((cmd)->getTransaction().lpush(key, values).get(), ProtocolCommand.LPUSH, args);
		}else{
			return execute((cmd)->cmd.lpush(key, values), ProtocolCommand.LPUSH, args);
		}
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);

		if(isTransaction()){
			return execute((cmd)->getTransaction().lpushx(key, values).get(), ProtocolCommand.LPUSHX, args);
		}else{
			return execute((cmd)->cmd.lpushx(key, values), ProtocolCommand.LPUSHX, args);
		}
	}

	@Override
	public Long lInsert(final byte[] key, final byte[] value, final ListCommands.ListPosition position,
			final byte[] pivot){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("position", position).put(
				"pivot", pivot).put("value", value);

		if(isTransaction()){
			return execute((cmd)->cmd.linsert(key, JedisClientUtils.listPositionConvert(position), pivot, value),
					ProtocolCommand.LINSERT, args);
		}else{
			return execute((cmd)->cmd.linsert(key, JedisClientUtils.listPositionConvert(position), pivot, value),
					ProtocolCommand.LINSERT, args);
		}
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index).put("value",
				value);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().lset(key, index, value).get()),
					ProtocolCommand.LSET, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.lset(key, index, value)), ProtocolCommand.LSET, args);
		}
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("index", index);

		if(isTransaction()){
			return execute((cmd)->getTransaction().lindex(key, index).get(), ProtocolCommand.LINDEX, args);
		}else{
			return execute((cmd)->cmd.lindex(key, index), ProtocolCommand.LINDEX, args);
		}
	}

	@Override
	public byte[] lPop(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().lpop(key).get(), ProtocolCommand.LPOP, args);
		}else{
			return execute((cmd)->cmd.lpop(key), ProtocolCommand.LPOP, args);
		}
	}

	@Override
	public byte[] rPop(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().rpop(key).get(), ProtocolCommand.RPOP, args);
		}else{
			return execute((cmd)->cmd.rpop(key), ProtocolCommand.RPOP, args);
		}
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);

		if(isTransaction()){
			return execute((cmd)->getTransaction().rpush(key, values).get(), ProtocolCommand.RPUSH, args);
		}else{
			return execute((cmd)->cmd.rpush(key, values), ProtocolCommand.RPUSH, args);
		}
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("values", values);

		if(isTransaction()){
			return execute((cmd)->getTransaction().rpushx(key, values).get(), ProtocolCommand.RPUSHX, args);
		}else{
			return execute((cmd)->cmd.rpushx(key, values), ProtocolCommand.RPUSHX, args);
		}
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end",
				end);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().ltrim(key, start, end).get()),
					ProtocolCommand.LTRIM, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.ltrim(key, start, end)), ProtocolCommand.LTRIM, args);
		}
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value).put("count",
				count);

		if(isTransaction()){
			return execute((cmd)->getTransaction().lrem(key, count, value).get(), ProtocolCommand.LREM, args);
		}else{
			return execute((cmd)->cmd.lrem(key, count, value), ProtocolCommand.LREM, args);
		}
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end",
				end);

		if(isTransaction()){
			return execute((cmd)->getTransaction().lrange(key, start, end).get(), ProtocolCommand.LRANGE, args);
		}else{
			return execute((cmd)->cmd.lrange(key, start, end), ProtocolCommand.LRANGE, args);
		}
	}

	@Override
	public Long lLen(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().llen(key).get(), ProtocolCommand.LLEN, args);
		}else{
			return execute((cmd)->cmd.llen(key), ProtocolCommand.LLEN, args);
		}
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sadd(key, members).get(), ProtocolCommand.SADD, args);
		}else{
			return execute((cmd)->cmd.sadd(key, members), ProtocolCommand.SADD, args);
		}
	}

	@Override
	public Long sCard(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().scard(key).get(), ProtocolCommand.SCARD, args);
		}else{
			return execute((cmd)->cmd.scard(key), ProtocolCommand.SCARD, args);
		}
	}

	@Override
	public boolean sisMember(final byte[] key, final byte[] member){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sismember(key, member).get(), ProtocolCommand.SISMEMBER, args);
		}else{
			return execute((cmd)->cmd.sismember(key, member), ProtocolCommand.SISMEMBER, args);
		}
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().smembers(key).get(), ProtocolCommand.SMEMBERS, args);
		}else{
			return execute((cmd)->cmd.smembers(key), ProtocolCommand.SMEMBERS, args);
		}
	}

	@Override
	public byte[] sPop(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().spop(key).get(), ProtocolCommand.SPOP, args);
		}else{
			return execute((cmd)->cmd.spop(key), ProtocolCommand.SPOP, args);
		}
	}

	@Override
	public byte[] sRandMember(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().srandmember(key).get(), ProtocolCommand.SRANDMEMBER, args);
		}else{
			return execute((cmd)->cmd.srandmember(key), ProtocolCommand.SRANDMEMBER, args);
		}
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("count", count);

		if(isTransaction()){
			return execute((cmd)->getTransaction().srandmember(key, count).get(), ProtocolCommand.SRANDMEMBER, args);
		}else{
			return execute((cmd)->cmd.srandmember(key, count), ProtocolCommand.SRANDMEMBER, args);
		}
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);

		if(isTransaction()){
			return execute((cmd)->getTransaction().srem(key, members).get(), ProtocolCommand.SREM, args);
		}else{
			return execute((cmd)->cmd.srem(key, members), ProtocolCommand.SREM, args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.sscan(key, cursor)),
					ProtocolCommand.SSCAN, args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put(
				"pattern", pattern);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.sscan(key, cursor,
					new JedisScanParams(pattern))), ProtocolCommand.SSCAN, args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count"
				, count);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.sscan(key, cursor,
					new JedisScanParams(count))), ProtocolCommand.SSCAN, args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put(
				"pattern", pattern).put("count", count);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.sscan(key, cursor,
					new JedisScanParams(pattern, count))), ProtocolCommand.SSCAN, args);
		}
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Number> members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);

		return execute(new Executor<ShardedJedis, Long>() {

			@Override
			public Long execute(ShardedJedis cmd){
				final Map<byte[], Double> data = new LinkedHashMap<>(members.size());

				members.forEach((key, value)->data.put(key, value.doubleValue()));

				if(isTransaction()){
					return getTransaction().zadd(key, data).get();
				}else{
					return cmd.zadd(key, data);
				}
			}

		}, ProtocolCommand.ZADD, args);
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zscore(key, member).get(), ProtocolCommand.ZSCORE, args);
		}else{
			return execute((cmd)->cmd.zscore(key, member), ProtocolCommand.ZSCORE, args);
		}
	}

	@Override
	public Long zCard(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zcard(key).get(), ProtocolCommand.ZCARD, args);
		}else{
			return execute((cmd)->cmd.zcard(key), ProtocolCommand.ZCARD, args);
		}
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final double increment){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put(
				"increment", increment);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zincrby(key, increment, member).get(), ProtocolCommand.ZINCRBY,
					args);
		}else{
			return execute((cmd)->cmd.zincrby(key, increment, member), ProtocolCommand.ZINCRBY, args);
		}
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zcount(key, min, max).get(), ProtocolCommand.ZCOUNT, args);
		}else{
			return execute((cmd)->cmd.zcount(key, min, max), ProtocolCommand.ZCOUNT, args);
		}
	}

	@Override
	public Long zCount(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zcount(key, min, max).get(), ProtocolCommand.ZCOUNT, args);
		}else{
			return execute((cmd)->cmd.zcount(key, min, max), ProtocolCommand.ZCOUNT, args);
		}
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end",
				end);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zrange(key, start, end).get(), ProtocolCommand.ZRANGE, args);
		}else{
			return execute((cmd)->cmd.zrange(key, start, end), ProtocolCommand.ZRANGE, args);
		}
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end",
				end);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeWithScores(key, start,
					end).get()), ProtocolCommand.ZRANGE, args);
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeWithScores(key, start, end)),
					ProtocolCommand.ZRANGE, args);
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByScore(key, min, max).get(), ProtocolCommand.ZRANGEBYSCORE,
					args);
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max), ProtocolCommand.ZRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByScore(key, min, max).get(), ProtocolCommand.ZRANGEBYSCORE,
					args);
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max), ProtocolCommand.ZRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		final CommandArguments args =
				CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByScore(key, min, max).get(), ProtocolCommand.ZRANGEBYSCORE,
					args);
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max), ProtocolCommand.ZRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		final CommandArguments args =
				CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByScore(key, min, max, offset, count).get(),
					ProtocolCommand.ZRANGEBYSCORE, args);
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max, offset, count), ProtocolCommand.ZRANGEBYSCORE,
					args);
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key, min
					, max).get()), ProtocolCommand.ZRANGEBYSCORE, args);
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeByScoreWithScores(key, min, max)),
					ProtocolCommand.ZRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key, min
					, max).get()), ProtocolCommand.ZRANGEBYSCORE, args);
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeByScoreWithScores(key, min, max)),
					ProtocolCommand.ZRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
			final int count){
		final CommandArguments args =
				CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key, min
					, max, offset, count).get()), ProtocolCommand.ZRANGEBYSCORE, args);
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeByScoreWithScores(key, min, max, offset
					, count)), ProtocolCommand.ZRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByLex(key, min, max).get(), ProtocolCommand.ZRANGEBYLEX,
					args);
		}else{
			return execute((cmd)->cmd.zrangeByLex(key, min, max), ProtocolCommand.ZRANGEBYLEX, args);
		}
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		final CommandArguments args =
				CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByLex(key, min, max, offset, count).get(),
					ProtocolCommand.ZRANGEBYLEX, args);
		}else{
			return execute((cmd)->cmd.zrangeByLex(key, min, max, offset, count), ProtocolCommand.ZRANGEBYLEX, args);
		}
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zrank(key, member).get(), ProtocolCommand.ZRANK, args);
		}else{
			return execute((cmd)->cmd.zrank(key, member), ProtocolCommand.ZRANK, args);
		}
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrank(key, member).get(), ProtocolCommand.ZRANK, args);
		}else{
			return execute((cmd)->cmd.zrevrank(key, member), ProtocolCommand.ZRANK, args);
		}
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zrem(key, members).get(), ProtocolCommand.ZREM, args);
		}else{
			return execute((cmd)->cmd.zrem(key, members), ProtocolCommand.ZREM, args);
		}
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end",
				end);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zremrangeByRank(key, start, end).get(),
					ProtocolCommand.ZREMRANGEBYRANK, args);
		}else{
			return execute((cmd)->cmd.zremrangeByRank(key, start, end), ProtocolCommand.ZREMRANGEBYRANK, args);
		}
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zremrangeByScore(key, min, max).get(),
					ProtocolCommand.ZREMRANGEBYSCORE, args);
		}else{
			return execute((cmd)->cmd.zremrangeByScore(key, min, max), ProtocolCommand.ZREMRANGEBYSCORE, args);
		}
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zremrangeByScore(key, min, max).get(),
					ProtocolCommand.ZREMRANGEBYSCORE, args);
		}else{
			return execute((cmd)->cmd.zremrangeByScore(key, min, max), ProtocolCommand.ZREMRANGEBYSCORE, args);
		}
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zremrangeByLex(key, min, max).get(), ProtocolCommand.ZREMRANGEBYLEX
					, args);
		}else{
			return execute((cmd)->getTransaction().zremrangeByLex(key, min, max).get(), ProtocolCommand.ZREMRANGEBYLEX
					, args);
		}
	}

	@Override
	public Set<byte[]> zRevRange(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end",
				end);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrange(key, start, end).get(), ProtocolCommand.ZREVRANGE, args);
		}else{
			return execute((cmd)->cmd.zrevrange(key, start, end), ProtocolCommand.ZREVRANGE, args);
		}
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("end",
				end);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeWithScores(key, start,
					end).get()), ProtocolCommand.ZREVRANGE, args);
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeWithScores(key, start, end)),
					ProtocolCommand.ZREVRANGE, args);
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByScore(key, min, max).get(),
					ProtocolCommand.ZREVRANGEBYSCORE, args);
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByScore(key, min, max).get(),
					ProtocolCommand.ZREVRANGEBYSCORE, args);
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max), ProtocolCommand.ZREVRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		final CommandArguments args =
				CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByScore(key, min, max, offset, count).get(),
					ProtocolCommand.ZREVRANGEBYSCORE, args);
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE
					, args);
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		final CommandArguments args =
				CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByScore(key, min, max, offset, count).get(),
					ProtocolCommand.ZREVRANGEBYSCORE, args);
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max, offset, count), ProtocolCommand.ZREVRANGEBYSCORE
					, args);
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max).get()), ProtocolCommand.ZREVRANGEBYSCORE, args);
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeByScoreWithScores(key, min, max)),
					ProtocolCommand.ZREVRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max).get()), ProtocolCommand.ZREVRANGEBYSCORE, args);
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeByScoreWithScores(key, min, max)),
					ProtocolCommand.ZREVRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
			final int offset, final int count){
		final CommandArguments args =
				CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max, offset, count).get()), ProtocolCommand.ZREVRANGEBYSCORE, args);
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeByScoreWithScores(key, min, max,
					offset, count)), ProtocolCommand.ZREVRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
			final int offset, final int count){
		final CommandArguments args =
				CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max, offset, count).get()), ProtocolCommand.ZREVRANGEBYSCORE, args);
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeByScoreWithScores(key, min, max,
					offset, count)), ProtocolCommand.ZREVRANGEBYSCORE, args);
		}
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args =
				CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("min", min).put(
						"max", max);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByLex(key, min, max).get(), ProtocolCommand.ZREVRANGEBYLEX
					, args);
		}else{
			return execute((cmd)->cmd.zrevrangeByLex(key, min, max), ProtocolCommand.ZREVRANGEBYLEX, args);
		}
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		final CommandArguments args =
				CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("min", min).put(
						"max", max).put("offset", offset).put("count", count);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByLex(key, min, max, offset, count).get(),
					ProtocolCommand.ZREVRANGEBYLEX, args);
		}else{
			return execute((cmd)->cmd.zrevrangeByLex(key, min, max, offset, count), ProtocolCommand.ZREVRANGEBYLEX,
					args);
		}
	}

	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args =
				CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("min", min).put(
						"max", max);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zlexcount(key, min, max).get(), ProtocolCommand.ZLEXCOUNT, args);
		}else{
			return execute((cmd)->cmd.zlexcount(key, min, max), ProtocolCommand.ZLEXCOUNT, args);
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listTupleScanResultDeconvert(cmd.zscan(key, cursor)),
					ProtocolCommand.ZSCAN, args);
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put(
				"pattern", pattern);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listTupleScanResultDeconvert(cmd.zscan(key, cursor,
					new JedisScanParams(pattern))), ProtocolCommand.ZSCAN, args);
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put("count"
				, count);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listTupleScanResultDeconvert(cmd.zscan(key, cursor,
					new JedisScanParams(count))), ProtocolCommand.ZSCAN, args);
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("cursor", cursor).put(
				"pattern", pattern).put("count", count);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listTupleScanResultDeconvert(cmd.zscan(key, cursor,
					new JedisScanParams(pattern, count))), ProtocolCommand.ZSCAN, args);
		}
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("elements", elements);

		if(isTransaction()){
			return execute((cmd)->statusForBool(getTransaction().pfadd(key, elements).get() > 0),
					ProtocolCommand.PFADD, args);
		}else{
			return execute((cmd)->statusForBool(cmd.pfadd(key, elements) > 0), ProtocolCommand.PFADD, args);
		}
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("member", member).put(
				"longitude", longitude).put("latitude", latitude);

		if(isTransaction()){
			return execute((cmd)->getTransaction().geoadd(key, longitude, latitude, member).get(),
					ProtocolCommand.GEOADD, args);
		}else{
			return execute((cmd)->cmd.geoadd(key, longitude, latitude, member), ProtocolCommand.GEOADD, args);
		}
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("memberCoordinates",
				memberCoordinates);

		if(isTransaction()){
			return execute((cmd)->getTransaction().geoadd(key, JedisClientUtils.geoMapConvert(memberCoordinates)).get(), ProtocolCommand.GEOADD, args);
		}else{
			return execute((cmd)->cmd.geoadd(key, JedisClientUtils.geoMapConvert(memberCoordinates)),
					ProtocolCommand.GEOADD, args);
		}
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("members", members);

		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.geoListDeconvert(getTransaction().geopos(key, members).get()),
					ProtocolCommand.GEOPOS, args);
		}else{
			return execute((cmd)->JedisClientUtils.geoListDeconvert(cmd.geopos(key, members)), ProtocolCommand.GEOPOS,
					args);
		}
	}

	protected final static Jedis getShard(final ShardedJedis shardedJedis, final String key){
		return shardedJedis.getShard(key);
	}

	protected final Jedis getShard(final ShardedJedis shardedJedis, final byte[] key){
		return shardedJedis.getShard(key);
	}

}
