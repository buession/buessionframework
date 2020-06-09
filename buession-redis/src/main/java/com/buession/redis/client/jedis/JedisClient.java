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
import com.buession.lang.Geo;
import com.buession.lang.Status;
import com.buession.redis.client.GenericRedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.Client;
import com.buession.redis.core.Info;
import com.buession.redis.core.JedisScanParams;
import com.buession.redis.core.JedisZParams;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.command.ListCommands;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.command.SortedSetCommands;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.transaction.Transaction;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.params.MigrateParams;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 */
public class JedisClient extends AbstractJedisRedisClient<Jedis> implements GenericRedisClient {

	public JedisClient(){
		super();
	}

	public JedisClient(RedisConnection connection){
		super(connection);
	}

	@Override
	public boolean exists(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->getTransaction().exists(key).get(), ProtocolCommand.EXISTS, args);
		}else{
			return execute((cmd)->cmd.exists(key), ProtocolCommand.EXISTS, args);
		}
	}

	@Override
	public Type type(final byte[] key){
		final CommandArguments args = CommandArguments.getInstance().put("key", key);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.enumValueOf(getTransaction().type(key).get(), Type.class),
			 ProtocolCommand.TYPE, args);
		}else{
			return execute((cmd)->ReturnUtils.enumValueOf(cmd.type(key), Type.class), ProtocolCommand.TYPE, args);
		}
	}

	@Override
	public Status rename(final String key, final String newKey){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("newKey", newKey);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().rename(key, newKey).get()),
			 ProtocolCommand.RENAME, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.rename(key, newKey)), ProtocolCommand.RENAME, args);
		}
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("newKey", newKey);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().rename(key, newKey).get()),
			 ProtocolCommand.RENAME, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.rename(key, newKey)), ProtocolCommand.RENAME, args);
		}
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("newKey", newKey);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().renamenx(key, newKey).get() > 0),
			 ProtocolCommand.RENAME, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.renamenx(key, newKey) > 0), ProtocolCommand.RENAME,
			 args);
		}
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("newKey", newKey);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().renamenx(key, newKey).get() > 0),
			 ProtocolCommand.RENAMENX, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.renamenx(key, newKey) > 0), ProtocolCommand.RENAMENX,
			 args);
		}
	}

	@Override
	public String randomKey(){
		if(isTransaction()){
			return execute((cmd)->getTransaction().randomKey().get(), ProtocolCommand.RANDOMKEY);
		}else{
			return execute((cmd)->cmd.randomKey(), ProtocolCommand.RANDOMKEY);
		}
	}

	@Override
	public Set<String> keys(final String pattern){
		final CommandArguments args = CommandArguments.getInstance().put("pattern", pattern);

		if(isTransaction()){
			return execute((cmd)->getTransaction().keys(pattern).get(), ProtocolCommand.KEYS, args);
		}else{
			return execute((cmd)->cmd.keys(pattern), ProtocolCommand.KEYS, args);
		}
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("pattern", pattern);

		if(isTransaction()){
			return execute((cmd)->getTransaction().keys(pattern).get(), ProtocolCommand.KEYS, args);
		}else{
			return execute((cmd)->cmd.keys(pattern), ProtocolCommand.KEYS, args);
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
	public ScanResult<List<String>> scan(final String cursor){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.scan(cursor)), ProtocolCommand.SCAN,
			 args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.scan(cursor)), ProtocolCommand.SCAN,
			 args);
		}
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.scan(cursor,
			 new JedisScanParams(pattern))), ProtocolCommand.SCAN, args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.scan(cursor,
			 new JedisScanParams(pattern))), ProtocolCommand.SCAN, args);
		}
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("count", count);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.scan(cursor,
			 new JedisScanParams(count))), ProtocolCommand.SCAN, args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count){
		final CommandArguments args = CommandArguments.getInstance().put("cursor", cursor).put("count", count);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.scan(cursor,
			 new JedisScanParams(count))), ProtocolCommand.SCAN, args);
		}
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count){
		final CommandArguments args =
		 CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count", count);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.scan(cursor,
			 new JedisScanParams(pattern, count))), ProtocolCommand.SCAN, args);
		}
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count){
		final CommandArguments args =
		 CommandArguments.getInstance().put("cursor", cursor).put("pattern", pattern).put("count", count);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.scan(cursor,
			 new JedisScanParams(pattern, count))), ProtocolCommand.SCAN, args);
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
		final SortingParams soringParams = JedisClientUtils.sortArgumentConvert(sortArgument);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key, soringParams).get(), ProtocolCommand.SORT, args);
		}else{
			return execute((cmd)->cmd.sort(key, soringParams), ProtocolCommand.SORT, args);
		}
	}

	@Override
	public Long sort(final String key, final String destKey){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key, destKey).get(), ProtocolCommand.SORT, args);
		}else{
			return execute((cmd)->cmd.sort(key, destKey), ProtocolCommand.SORT, args);
		}
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key, destKey).get(), ProtocolCommand.SORT, args);
		}else{
			return execute((cmd)->cmd.sort(key, destKey), ProtocolCommand.SORT, args);
		}
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey).put(
				"sortArgument", sortArgument);
		final SortingParams soringParams = JedisClientUtils.sortArgumentConvert(sortArgument);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key, soringParams, destKey).get(), ProtocolCommand.SORT, args);
		}else{
			return execute((cmd)->cmd.sort(key, soringParams, destKey), ProtocolCommand.SORT, args);
		}
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("destKey", destKey).put(
				"sortArgument", sortArgument);
		final SortingParams soringParams = JedisClientUtils.sortArgumentConvert(sortArgument);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key, soringParams, destKey).get(), ProtocolCommand.SORT, args);
		}else{
			return execute((cmd)->cmd.sort(key, soringParams, destKey), ProtocolCommand.SORT, args);
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
			return execute((cmd)->ReturnUtils.statusForOK(cmd.migrate(host, port, key, db, timeout)),
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
			return execute((cmd)->ReturnUtils.statusForOK(cmd.migrate(host, port, key, db, timeout)),
			 ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout,
	 final MigrateOperation migrateOperation){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("host", host).put("port",
		 port).put("key", key).put("db", db).put("timeout", timeout).put("migte", migrateOperation);
		final MigrateParams migrateParams = JedisClientUtils.migrateOperationConvert(migrateOperation);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().migrate(host, port, db, timeout,
			 migrateParams, key).get()), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.migrate(host, port, db, timeout, migrateParams, key)),
			 ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
	 final MigrateOperation migrateOperation){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("host", host).put("port",
		 port).put("key", key).put("db", db).put("timeout", timeout).put("migrate", migrateOperation);
		final MigrateParams migrateParams = JedisClientUtils.migrateOperationConvert(migrateOperation);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().migrate(host, port, db, timeout,
			 migrateParams, key).get()), ProtocolCommand.MIGRATE, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.migrate(host, port, db, timeout, migrateParams, key)),
			 ProtocolCommand.MIGRATE, args);
		}
	}

	@Override
	public Long del(final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().del(keys).get(), ProtocolCommand.DEL, args);
		}else{
			return execute((cmd)->cmd.del(keys), ProtocolCommand.DEL, args);
		}
	}

	@Override
	public Long del(final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().del(keys).get(), ProtocolCommand.DEL, args);
		}else{
			return execute((cmd)->cmd.del(keys), ProtocolCommand.DEL, args);
		}
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
		final SetParams setParams = JedisClientUtils.setArgumentConvert(setArgument);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().set(key, value, setParams).get()),
			 ProtocolCommand.SET, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.set(key, value, setParams)), ProtocolCommand.SET, args);
		}
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("vue", value).put("lifetime",
		 lifetime);

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
	public Status mSet(final Map<String, String> values){
		final CommandArguments args = CommandArguments.getInstance().put("values", values);
		final List<String> temp = new ArrayList<>(values.size() * 2);

		values.forEach((key, value)->{
			temp.add(key);
			temp.add(value);
		});

		final String[] keysValues = temp.stream().toArray(String[]::new);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().mset(keysValues).get()),
			 ProtocolCommand.MSET, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.mset(keysValues)), ProtocolCommand.MSET, args);
		}
	}

	@Override
	public Status mSetNx(final Map<String, String> values){
		final CommandArguments args = CommandArguments.getInstance().put("values", values);
		final List<String> temp = new ArrayList<>(values.size() * 2);

		values.forEach((key, value)->{
			temp.add(key);
			temp.add(value);
		});

		final String[] keysValues = temp.stream().toArray(String[]::new);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().msetnx(keysValues).get() > 0),
			 ProtocolCommand.MSETNX, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.msetnx(keysValues) > 0), ProtocolCommand.MSETNX, args);
		}
	}

	@Override
	public List<String> mGet(final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().mget(keys).get(), ProtocolCommand.MGET, args);
		}else{
			return execute((cmd)->cmd.mget(keys), ProtocolCommand.MGET, args);
		}
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().mget(keys).get(), ProtocolCommand.MGET, args);
		}else{
			return execute((cmd)->cmd.mget(keys), ProtocolCommand.MGET, args);
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

		if(isTransaction()){
			return execute((cmd)->getTransaction().hvals(key).get(), ProtocolCommand.HVALS, args);
		}else{
			return execute((cmd)->cmd.hvals(key), ProtocolCommand.HVALS, args);
		}
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

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor,
			 new JedisScanParams(pattern, count))), ProtocolCommand.HSCAN, args);
		}
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
		final redis.clients.jedis.ListPosition pos = JedisClientUtils.listPositionConvert(position);

		if(isTransaction()){
			return execute((cmd)->cmd.linsert(key, pos, pivot, value), ProtocolCommand.LINSERT, args);
		}else{
			return execute((cmd)->cmd.linsert(key, pos, pivot, value), ProtocolCommand.LINSERT, args);
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
	public List<String> blPop(final String[] keys, final int timeout){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys).put("timeout", timeout);

		if(isTransaction()){
			return execute((cmd)->getTransaction().blpop(timeout, keys).get(), ProtocolCommand.BLPOP, args);
		}else{
			return execute((cmd)->cmd.blpop(timeout, keys), ProtocolCommand.BLPOP, args);
		}
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys).put("timeout", timeout);

		return execute(new Executor<Jedis, List<byte[]>>() {

			@Override
			public List<byte[]> execute(Jedis cmd){
				if(isTransaction()){
					List<String> ret = getTransaction().blpop(timeout, keys).get();
					return ret == null ? null : ret.stream().map(SafeEncoder::encode).collect(Collectors.toList());
				}else{
					return cmd.blpop(timeout, keys);
				}
			}

		}, ProtocolCommand.BLPOP, args);
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
	public String rPoplPush(final String source, final String destKey){
		final CommandArguments args = CommandArguments.getInstance().put("source", source).put("destKey", destKey);

		if(isTransaction()){
			return execute((cmd)->getTransaction().rpoplpush(source, destKey).get(), ProtocolCommand.RPOPLPUSH, args);
		}else{
			return execute((cmd)->cmd.rpoplpush(source, destKey), ProtocolCommand.RPOPLPUSH, args);
		}
	}

	@Override
	public byte[] rPoplPush(final byte[] source, final byte[] destKey){
		final CommandArguments args = CommandArguments.getInstance().put("source", source).put("destKey", destKey);

		if(isTransaction()){
			return execute((cmd)->getTransaction().rpoplpush(source, destKey).get(), ProtocolCommand.RPOPLPUSH, args);
		}else{
			return execute((cmd)->cmd.rpoplpush(source, destKey), ProtocolCommand.RPOPLPUSH, args);
		}
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys).put("timeout", timeout);

		if(isTransaction()){
			return execute((cmd)->getTransaction().brpop(timeout, keys).get(), ProtocolCommand.BRPOP, args);
		}else{
			return execute((cmd)->cmd.brpop(timeout, keys), ProtocolCommand.BRPOP, args);
		}
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys).put("timeout", timeout);

		return execute(new Executor<Jedis, List<byte[]>>() {

			@Override
			public List<byte[]> execute(Jedis cmd){
				if(isTransaction()){
					List<String> ret = getTransaction().brpop(timeout, keys).get();
					return ret == null ? null : ret.stream().map(SafeEncoder::encode).collect(Collectors.toList());
				}else{
					return cmd.brpop(timeout, keys);
				}
			}

		}, ProtocolCommand.BLPOP, args);
	}

	@Override
	public String brPoplPush(final String source, final String destKey, final int timeout){
		final CommandArguments args =
		 CommandArguments.getInstance().put("source", source).put("destKey", destKey).put("timeout", timeout);

		if(isTransaction()){
			return execute((cmd)->getTransaction().brpoplpush(source, destKey, timeout).get(),
			 ProtocolCommand.BRPOPLPUSH, args);
		}else{
			return execute((cmd)->cmd.brpoplpush(source, destKey, timeout), ProtocolCommand.BRPOPLPUSH, args);
		}
	}

	@Override
	public byte[] brPoplPush(final byte[] source, final byte[] destKey, final int timeout){
		final CommandArguments args =
		 CommandArguments.getInstance().put("source", source).put("destKey", destKey).put("timeout", timeout);

		if(isTransaction()){
			return execute((cmd)->getTransaction().brpoplpush(source, destKey, timeout).get(),
			 ProtocolCommand.BRPOPLPUSH, args);
		}else{
			return execute((cmd)->cmd.brpoplpush(source, destKey, timeout), ProtocolCommand.BRPOPLPUSH, args);
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
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("start", start).put("ed",
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
	public Set<String> sDiff(final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sdiff(keys).get(), ProtocolCommand.SDIFF, args);
		}else{
			return execute((cmd)->cmd.sdiff(keys), ProtocolCommand.SDIFF, args);
		}
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sdiff(keys).get(), ProtocolCommand.SDIFF, args);
		}else{
			return execute((cmd)->cmd.sdiff(keys), ProtocolCommand.SDIFF, args);
		}
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sdiffstore(destKey, keys).get(), ProtocolCommand.SDIFFSTORE, args);
		}else{
			return execute((cmd)->cmd.sdiffstore(destKey, keys), ProtocolCommand.SDIFFSTORE, args);
		}
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sdiffstore(destKey, keys).get(), ProtocolCommand.SDIFFSTORE, args);
		}else{
			return execute((cmd)->cmd.sdiffstore(destKey, keys), ProtocolCommand.SDIFFSTORE, args);
		}
	}

	@Override
	public Set<String> sInter(final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sinter(keys).get(), ProtocolCommand.SINTER, args);
		}else{
			return execute((cmd)->cmd.sinter(keys), ProtocolCommand.SINTER, args);
		}
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sinter(keys).get(), ProtocolCommand.SINTER, args);
		}else{
			return execute((cmd)->cmd.sinter(keys), ProtocolCommand.SINTER, args);
		}
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sinterstore(destKey, keys).get(), ProtocolCommand.SINTERSTORE,
			 args);
		}else{
			return execute((cmd)->cmd.sinterstore(destKey, keys), ProtocolCommand.SINTERSTORE, args);
		}
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sinterstore(destKey, keys).get(), ProtocolCommand.SINTERSTORE,
			 args);
		}else{
			return execute((cmd)->cmd.sinterstore(destKey, keys), ProtocolCommand.SINTERSTORE, args);
		}
	}

	@Override
	public Set<String> sUnion(final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sunion(keys).get(), ProtocolCommand.SINTER, args);
		}else{
			return execute((cmd)->cmd.sunion(keys), ProtocolCommand.SINTER, args);
		}
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sunion(keys).get(), ProtocolCommand.SINTER, args);
		}else{
			return execute((cmd)->cmd.sunion(keys), ProtocolCommand.SINTER, args);
		}
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sunionstore(destKey, keys).get(), ProtocolCommand.SINTERSTORE,
			 args);
		}else{
			return execute((cmd)->cmd.sunionstore(destKey, keys), ProtocolCommand.SINTERSTORE, args);
		}
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sunionstore(destKey, keys).get(), ProtocolCommand.SINTERSTORE,
			 args);
		}else{
			return execute((cmd)->cmd.sunionstore(destKey, keys), ProtocolCommand.SINTERSTORE, args);
		}
	}

	@Override
	public Status sMove(final String source, final String destKey, final String member){
		final CommandArguments args =
		 CommandArguments.getInstance().put("source", source).put("destKey", destKey).put("member", member);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().smove(source, destKey, member).get() > 0)
			, ProtocolCommand.SMOVE, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.smove(source, destKey, member) > 0),
			 ProtocolCommand.SMOVE, args);
		}
	}

	@Override
	public Status sMove(final byte[] source, final byte[] destKey, final byte[] member){
		final CommandArguments args =
		 CommandArguments.getInstance().put("source", source).put("destKey", destKey).put("member", member);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().smove(source, destKey, member).get() > 0)
			, ProtocolCommand.SMOVE, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.smove(source, destKey, member) > 0),
			 ProtocolCommand.SMOVE, args);
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
		final Map<byte[], Double> data = new LinkedHashMap<>(members.size());

		members.forEach((k, v)->data.put(k, v.doubleValue()));

		if(isTransaction()){
			return execute((cmd)->getTransaction().zadd(key, data).get(), ProtocolCommand.ZADD, args);
		}else{
			return execute((cmd)->cmd.zadd(key, data), ProtocolCommand.ZADD, args);
		}
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
		 CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put(
		 		"count", count);

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
		 CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put(
		 		"count", count);

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
		 CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put(
		 		"count", count);

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
	public Set<byte[]> zRangeByLex(final byte key[], final byte[] min, final byte[] max, final int offset,
	 final int count){
		final CommandArguments args =
		 CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put(
		 		"count", count);

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
		 CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put(
		 		"count", count);

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
		 CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put(
		 		"count", count);

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
		 CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put(
		 		"count", count);

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
		 CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("offset", offset).put(
		 		"count", count);

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
		 CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("min", min).put("max",
		  max);

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
		 CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("min", min).put("max",
		  max).put("offset", offset).put("count", count);

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
		 CommandArguments.getInstance().put("key", key).put("min", min).put("max", max).put("min", min).put("max",
		  max);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zlexcount(key, min, max).get(), ProtocolCommand.ZLEXCOUNT, args);
		}else{
			return execute((cmd)->cmd.zlexcount(key, min, max), ProtocolCommand.ZLEXCOUNT, args);
		}
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zunionstore(destKey, keys).get(), ProtocolCommand.ZUNIONSTORE,
			 args);
		}else{
			return execute((cmd)->cmd.zunionstore(destKey, keys), ProtocolCommand.ZUNIONSTORE, args);
		}
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zunionstore(destKey, keys).get(), ProtocolCommand.ZUNIONSTORE,
			 args);
		}else{
			return execute((cmd)->cmd.zunionstore(destKey, keys), ProtocolCommand.ZUNIONSTORE, args);
		}
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate",
		 aggregate).put("keys", keys);
		final ZParams zParams = new JedisZParams(JedisClientUtils.aggregateConvert(aggregate));

		if(isTransaction()){
			return execute((cmd)->getTransaction().zunionstore(destKey, zParams, keys).get(),
			 ProtocolCommand.ZUNIONSTORE, args);
		}else{
			return execute((cmd)->cmd.zunionstore(destKey, zParams, keys), ProtocolCommand.ZUNIONSTORE, args);
		}
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final SortedSetCommands.Aggregate aggregate, final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate",
		 aggregate).put("keys", keys);
		final ZParams zParams = new JedisZParams(JedisClientUtils.aggregateConvert(aggregate));

		if(isTransaction()){
			return execute((cmd)->getTransaction().zunionstore(destKey, zParams, keys).get(),
			 ProtocolCommand.ZUNIONSTORE, args);
		}else{
			return execute((cmd)->cmd.zunionstore(destKey, zParams, keys), ProtocolCommand.ZUNIONSTORE, args);
		}
	}

	@Override
	public Long zUnionStore(final String destKey, final double[] weights, final String... keys){
		final CommandArguments args =
		 CommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zunionstore(destKey, new JedisZParams(weights), keys).get(),
			 ProtocolCommand.ZUNIONSTORE, args);
		}else{
			return execute((cmd)->cmd.zunionstore(destKey, new JedisZParams(weights), keys),
			 ProtocolCommand.ZUNIONSTORE, args);
		}
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		final CommandArguments args =
		 CommandArguments.getInstance().put("destKey", destKey).put("weights", weights).put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zunionstore(destKey, new JedisZParams(weights), keys).get(),
			 ProtocolCommand.ZUNIONSTORE, args);
		}else{
			return execute((cmd)->cmd.zunionstore(destKey, new JedisZParams(weights), keys),
			 ProtocolCommand.ZUNIONSTORE, args);
		}
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final double[] weights,
	 final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate",
		 aggregate).put("weights", weights).put("keys", keys);
		final ZParams zParams = new JedisZParams(JedisClientUtils.aggregateConvert(aggregate), weights);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zunionstore(destKey, zParams, keys).get(),
			 ProtocolCommand.ZUNIONSTORE, args);
		}else{
			return execute((cmd)->cmd.zunionstore(destKey, zParams, keys), ProtocolCommand.ZUNIONSTORE, args);
		}
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final SortedSetCommands.Aggregate aggregate, final double[] weights,
	 final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("aggregate",
		 aggregate).put("weights", weights).put("keys", keys);
		final ZParams zParams = new JedisZParams(JedisClientUtils.aggregateConvert(aggregate), weights);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zunionstore(destKey, zParams, keys).get(),
			 ProtocolCommand.ZUNIONSTORE, args);
		}else{
			return execute((cmd)->cmd.zunionstore(destKey, zParams, keys), ProtocolCommand.ZUNIONSTORE, args);
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
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().pfadd(key, elements).get() > 0),
			 ProtocolCommand.PFADD, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pfadd(key, elements) > 0), ProtocolCommand.PFADD,
			 args);
		}
	}

	@Override
	public Status pfMerge(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().pfmerge(destKey, keys).get()),
			 ProtocolCommand.PFMERGE, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.pfmerge(destKey, keys)), ProtocolCommand.PFMERGE, args);
		}
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("destKey", destKey).put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().pfmerge(destKey keys).get()),
			 ProtocolCommand.PFMERGE, args);
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.pfmerge(destKey, keys)), ProtocolCommand.PFMERGE, args);
		}
	}

	@Override
	public Long pfCount(final String... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().pfcount(keys).get(), ProtocolCommand.PFCOUNT, args);
		}else{
			return execute((cmd)->cmd.pfcount(keys), ProtocolCommand.PFCOUNT, args);
		}
	}

	@Override
	public Long pfCount(final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().pfcount(keys).get(), ProtocolCommand.PFCOUNT, args);
		}else{
			return execute((cmd)->cmd.pfcount(keys), ProtocolCommand.PFCOUNT, args);
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
		final Map<byte[], GeoCoordinate> memberCoordinateMap = JedisClientUtils.geoMapConvert(memberCoordinates);

		if(isTransaction()){
			return execute((cmd)->getTransaction().geoadd(key, memberCoordinateMap).get(), ProtocolCommand.GEOADD,
			 args);
		}else{
			return execute((cmd)->cmd.geoadd(key, memberCoordinateMap), ProtocolCommand.GEOADD, args);
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

	@Override
	public Long bitOp(final Operation operation, final String destKey, final String... keys){
		return execute(bitMapOperations, (ops)->ops.bitOp(operation, destKey, keys));
	}

	@Override
	public Long bitOp(final Operation operation, final byte[] destKey, final byte[]... keys){
		return execute(binaryBitMapOperations, (ops)->ops.bitOp(operation, destKey, keys));
	}

	@Override
	public Transaction multi(){
		return execute(transactionOperations, (ops)->ops.multi());
	}

	@Override
	public void exec(final Transaction transaction){
		execute(transactionOperations, new Executor<TransactionRedisOperations, Void>() {

			@Override
			public Void execute(TransactionRedisOperations ops){
				ops.exec(transaction);
				return null;
			}

		});
	}

	@Override
	public void discard(final Transaction transaction){
		execute(transactionOperations, new Executor<TransactionRedisOperations, Void>() {

			@Override
			public Void execute(TransactionRedisOperations ops){
				ops.discard(transaction);
				return null;
			}

		});
	}

	@Override
	public Status watch(final String... keys){
		return execute(transactionOperations, (ops)->ops.watch(keys));
	}

	@Override
	public Status watch(final byte[]... keys){
		return execute(binaryTransactionOperations, (ops)->ops.watch(keys));
	}

	@Override
	public Status unwatch(){
		return execute(transactionOperations, (ops)->ops.unwatch());
	}

	@Override
	public Long publish(final String channel, final String message){
		return execute(pubSubOperations, (ops)->ops.publish(channel, message));
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message){
		return execute(binaryPubSubOperations, (ops)->ops.publish(channel, message));
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
		execute(pubSubOperations, new Executor<PubSubRedisOperations, Void>() {

			@Override
			public Void execute(PubSubRedisOperations ops){
				ops.subscribe(channels, pubSubListener);
				return null;
			}

		});
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
		execute(binaryPubSubOperations, new Executor<BinaryPubSubRedisOperations, Void>() {

			@Override
			public Void execute(BinaryPubSubRedisOperations ops){
				ops.subscribe(channels, pubSubListener);
				return null;
			}

		});
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
		execute(pubSubOperations, new Executor<PubSubRedisOperations, Void>() {

			@Override
			public Void execute(PubSubRedisOperations ops){
				ops.pSubscribe(patterns, pubSubListener);
				return null;
			}

		});
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
		execute(binaryPubSubOperations, new Executor<BinaryPubSubRedisOperations, Void>() {

			@Override
			public Void execute(BinaryPubSubRedisOperations ops){
				ops.pSubscribe(patterns, pubSubListener);
				return null;
			}

		});
	}

	@Override
	public Object unSubscribe(){
		return execute(pubSubOperations, (ops)->ops.unSubscribe());
	}

	@Override
	public Object unSubscribe(final String... channels){
		return execute(pubSubOperations, (ops)->ops.unSubscribe(channels));
	}

	@Override
	public Object unSubscribe(final byte[]... channels){
		return execute(binaryPubSubOperations, (ops)->ops.unSubscribe(channels));
	}

	@Override
	public Object pUnSubscribe(){
		return execute(pubSubOperations, (ops)->ops.pUnSubscribe());
	}

	@Override
	public Object pUnSubscribe(final String... patterns){
		return execute(pubSubOperations, (ops)->ops.pUnSubscribe(patterns));
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns){
		return execute(binaryPubSubOperations, (ops)->ops.pUnSubscribe(patterns));
	}

	@Override
	public Status select(final int db){
		return execute(databaseOperations, (ops)->ops.select(db));
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		return execute(databaseOperations, (ops)->ops.swapdb(db1, db2));
	}

	@Override
	public Long dbSize(){
		return execute(databaseOperations, (ops)->ops.dbSize());
	}

	@Override
	public Status flushDb(){
		return execute(databaseOperations, (ops)->ops.flushDb());
	}

	@Override
	public Status flushAll(){
		return execute(databaseOperations, (ops)->ops.flushAll());
	}

	@Override
	public Object eval(final String script){
		return execute(luaOperations, (ops)->ops.eval(script));
	}

	@Override
	public Object eval(final byte[] script){
		return execute(binaryLuaOperations, (ops)->ops.eval(script));
	}

	@Override
	public Object eval(final String script, final String... params){
		return execute(luaOperations, (ops)->ops.eval(script, params));
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params){
		return execute(binaryLuaOperations, (ops)->ops.eval(script, params));
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments){
		return execute(luaOperations, (ops)->ops.eval(script, keys, arguments));
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments){
		return execute(binaryLuaOperations, (ops)->ops.eval(script, keys, arguments));
	}

	@Override
	public Object evalSha(final String digest){
		return execute(luaOperations, (ops)->ops.evalSha(digest));
	}

	@Override
	public Object evalSha(final byte[] digest){
		return execute(binaryLuaOperations, (ops)->ops.evalSha(digest));
	}

	@Override
	public Object evalSha(final String digest, final String... params){
		return execute(luaOperations, (ops)->ops.evalSha(digest, params));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... params){
		return execute(binaryLuaOperations, (ops)->ops.evalSha(digest, params));
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments){
		return execute(luaOperations, (ops)->ops.evalSha(digest, keys, arguments));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments){
		return execute(binaryLuaOperations, (ops)->ops.evalSha(digest, keys, arguments));
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1){
		return execute(luaOperations, (ops)->ops.scriptExists(sha1));
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1){
		return execute(binaryLuaOperations, (ops)->ops.scriptExists(sha1));
	}

	@Override
	public String scriptLoad(final String script){
		return execute(luaOperations, (ops)->ops.scriptLoad(script));
	}

	@Override
	public byte[] scriptLoad(final byte[] script){
		return execute(binaryLuaOperations, (ops)->ops.scriptLoad(script));
	}

	@Override
	public Status scriptKill(){
		return execute(luaOperations, (ops)->ops.scriptKill());
	}

	@Override
	public Status scriptFlush(){
		return execute(luaOperations, (ops)->ops.scriptFlush());
	}

	@Override
	public Status save(){
		return execute(persistenceOperations, (ops)->ops.save());
	}

	@Override
	public String bgSave(){
		return execute(persistenceOperations, (ops)->ops.bgSave());
	}

	@Override
	public String bgRewriteAof(){
		return execute(persistenceOperations, (ops)->ops.bgRewriteAof());
	}

	@Override
	public Long lastSave(){
		return execute(persistenceOperations, (ops)->ops.lastSave());
	}

	@Override
	public Status slaveOf(final String host, final int port){
		return execute(replicationOperations, (ops)->ops.slaveOf(host, port));
	}

	@Override
	public Status slaveOf(final byte[] host, final int port){
		return execute(binaryReplicationOperations, (ops)->ops.slaveOf(host, port));
	}

	@Override
	public Role role(){
		return execute(replicationOperations, (ops)->ops.role());
	}

	@Override
	public Status auth(final String password){
		return execute(clientAndServerOperations, (ops)->ops.auth(password));
	}

	@Override
	public Status auth(final byte[] password){
		return execute(binaryClientAndServerOperations, (ops)->ops.auth(password));
	}

	@Override
	public Info info(final InfoSection section){
		return execute(clientAndServerOperations, (ops)->ops.info(section));
	}

	@Override
	public Info info(){
		return execute(clientAndServerOperations, (ops)->ops.info());
	}

	@Override
	public RedisServerTime time(){
		return execute(clientAndServerOperations, (ops)->ops.time());
	}

	@Override
	public Status clientSetName(final String name){
		return execute(clientAndServerOperations, (ops)->ops.clientSetName(name));
	}

	@Override
	public Status clientSetName(final byte[] name){
		return execute(binaryClientAndServerOperations, (ops)->ops.clientSetName(name));
	}

	@Override
	public String clientGetName(){
		return execute(clientAndServerOperations, (ops)->ops.clientGetName());
	}

	@Override
	public List<Client> clientList(){
		return execute(clientAndServerOperations, (ops)->ops.clientList());
	}

	@Override
	public Status clientKill(final String host, final int port){
		return execute(clientAndServerOperations, (ops)->ops.clientKill(host, port));
	}

	@Override
	public Status quit(){
		return execute(clientAndServerOperations, (ops)->ops.quit());
	}

	@Override
	public void shutdown(){
		execute(clientAndServerOperations, new Executor<ClientAndServerRedisOperations, Void>() {

			@Override
			public Void execute(ClientAndServerRedisOperations ops){
				ops.shutdown();
				return null;
			}

		});
	}

	@Override
	public void shutdown(final boolean save){
		execute(clientAndServerOperations, new Executor<ClientAndServerRedisOperations, Void>() {

			@Override
			public Void execute(ClientAndServerRedisOperations ops){
				ops.shutdown(save);
				return null;
			}

		});
	}

	@Override
	public Status configSet(final String parameter, final float value){
		return execute(configureOperations, (ops)->ops.configSet(parameter, value));
	}

	@Override
	public Status configSet(final byte[] parameter, final float value){
		return execute(binaryConfigureOperations, (ops)->ops.configSet(parameter, value));
	}

	@Override
	public Status configSet(final String parameter, final double value){
		return execute(configureOperations, (ops)->ops.configSet(parameter, value));
	}

	@Override
	public Status configSet(final byte[] parameter, final double value){
		return execute(binaryConfigureOperations, (ops)->ops.configSet(parameter, value));
	}

	@Override
	public Status configSet(final String parameter, final int value){
		return execute(configureOperations, (ops)->ops.configSet(parameter, value));
	}

	@Override
	public Status configSet(final byte[] parameter, final int value){
		return execute(binaryConfigureOperations, (ops)->ops.configSet(parameter, value));
	}

	@Override
	public Status configSet(final String parameter, final long value){
		return execute(configureOperations, (ops)->ops.configSet(parameter, value));
	}

	@Override
	public Status configSet(final byte[] parameter, final long value){
		return execute(binaryConfigureOperations, (ops)->ops.configSet(parameter, value));
	}

	@Override
	public Status configSet(final String parameter, final String value){
		return execute(configureOperations, (ops)->ops.configSet(parameter, value));
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		return execute(binaryConfigureOperations, (ops)->ops.configSet(parameter, value));
	}

	@Override
	public List<String> configGet(final String parameter){
		return execute(configureOperations, (ops)->ops.configGet(parameter));
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter){
		return execute(binaryConfigureOperations, (ops)->ops.configGet(parameter));
	}

	@Override
	public Status configResetStat(){
		return execute(configureOperations, (ops)->ops.configResetStat());
	}

	@Override
	public Status configRewrite(){
		return execute(configureOperations, (ops)->ops.configRewrite());
	}

	@Override
	public Object sync(){
		return execute(internalOperations, (ops)->ops.sync());
	}

	@Override
	public Object pSync(final String masterRunId, final int offset){
		return execute(internalOperations, (ops)->ops.pSync(masterRunId, offset));
	}

	@Override
	public Object pSync(final byte[] masterRunId, final int offset){
		return execute(binaryInternalOperations, (ops)->ops.pSync(masterRunId, offset));
	}

	@Override
	public Object pSync(final String masterRunId, final long offset){
		return execute(internalOperations, (ops)->ops.pSync(masterRunId, offset));
	}

	@Override
	public Object pSync(final byte[] masterRunId, final long offset){
		return execute(binaryInternalOperations, (ops)->ops.pSync(masterRunId, offset));
	}

	@Override
	public Status ping(){
		return execute(debugOperations, (ops)->ops.ping());
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final String... arguments){
		return execute(debugOperations, (ops)->ops.slowLog(command, arguments));
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final byte[]... arguments){
		return execute(binaryDebugOperations, (ops)->ops.slowLog(command, arguments));
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		execute(debugOperations, new Executor<DebugRedisOperations, Void>() {

			@Override
			public Void execute(DebugRedisOperations ops){
				ops.monitor(redisMonitor);
				return null;
			}

		});
	}

	@Override
	public String debugObject(final String key){
		return execute(debugOperations, (ops)->ops.debugObject(key));
	}

	@Override
	public String debugSegfault(){
		return execute(debugOperations, (ops)->ops.debugSegfault());
	}

}