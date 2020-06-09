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
import com.buession.lang.Geo;
import com.buession.lang.Status;
import com.buession.redis.client.GenericRedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.JedisScanParams;
import com.buession.redis.core.JedisZParams;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.BitMapCommands;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.command.ListCommands;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.command.SortedSetCommands;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.pubsub.jedis.DefaultBinaryJedisPubSub;
import com.buession.redis.pubsub.jedis.DefaultJedisPubSub;
import com.buession.redis.transaction.Transaction;
import com.buession.redis.transaction.jedis.JedisTransaction;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.BitPosParams;
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
		if(isTransaction()){
			return execute((cmd)->getTransaction().exists(key).get());
		}else{
			return execute((cmd)->cmd.exists(key));
		}
	}

	@Override
	public Type type(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.enumValueOf(getTransaction().type(key).get(), Type.class));
		}else{
			return execute((cmd)->ReturnUtils.enumValueOf(cmd.type(key), Type.class));
		}
	}

	@Override
	public Status rename(final String key, final String newKey){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().rename(key, newKey).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.rename(key, newKey)));
		}
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().rename(key, newKey).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.rename(key, newKey)));
		}
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().renamenx(key, newKey).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.renamenx(key, newKey) > 0));
		}
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().renamenx(key, newKey).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.renamenx(key, newKey) > 0));
		}
	}

	@Override
	public String randomKey(){
		if(isTransaction()){
			return execute((cmd)->getTransaction().randomKey().get());
		}else{
			return execute((cmd)->cmd.randomKey());
		}
	}

	@Override
	public Set<String> keys(final String pattern){
		if(isTransaction()){
			return execute((cmd)->getTransaction().keys(pattern).get());
		}else{
			return execute((cmd)->cmd.keys(pattern));
		}
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern){
		if(isTransaction()){
			return execute((cmd)->getTransaction().keys(pattern).get());
		}else{
			return execute((cmd)->cmd.keys(pattern));
		}
	}

	@Override
	public Status expire(final byte[] key, final int lifetime){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().expire(key, lifetime).get() == 1));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.expire(key, lifetime) == 1));
		}
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().expireAt(key, unixTimestamp).get() == 1));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.expireAt(key, unixTimestamp) == 1));
		}
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().pexpire(key, lifetime).get() == 1));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pexpire(key, lifetime) == 1));
		}
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().pexpireAt(key, unixTimestamp).get() == 1));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pexpireAt(key, unixTimestamp) == 1));
		}
	}

	@Override
	public Long ttl(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().ttl(key).get());
		}else{
			return execute((cmd)->cmd.ttl(key));
		}
	}

	@Override
	public Long pTtl(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().pttl(key).get());
		}else{
			return execute((cmd)->cmd.pttl(key));
		}
	}

	@Override
	public Status persist(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().persist(key).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.persist(key) > 0));
		}
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.scan(cursor)));
		}
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.scan(cursor)));
		}
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.scan(cursor,
					new JedisScanParams(pattern))));
		}
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.scan(cursor,
					new JedisScanParams(pattern))));
		}
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.scan(cursor,
					new JedisScanParams(count))));
		}
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.scan(cursor,
					new JedisScanParams(count))));
		}
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.scan(cursor,
					new JedisScanParams(pattern, count))));
		}
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.scan(cursor,
					new JedisScanParams(pattern, count))));
		}
	}

	@Override
	public List<byte[]> sort(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key).get());
		}else{
			return execute((cmd)->cmd.sort(key));
		}
	}

	@Override
	public List<byte[]> sort(final byte[] key, final KeyCommands.SortArgument sortArgument){
		final SortingParams soringParams = JedisClientUtils.sortArgumentConvert(sortArgument);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key, soringParams).get());
		}else{
			return execute((cmd)->cmd.sort(key, soringParams));
		}
	}

	@Override
	public Long sort(final String key, final String destKey){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key, destKey).get());
		}else{
			return execute((cmd)->cmd.sort(key, destKey));
		}
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key, destKey).get());
		}else{
			return execute((cmd)->cmd.sort(key, destKey));
		}
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument){
		final SortingParams soringParams = JedisClientUtils.sortArgumentConvert(sortArgument);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key, soringParams, destKey).get());
		}else{
			return execute((cmd)->cmd.sort(key, soringParams, destKey));
		}
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		final SortingParams soringParams = JedisClientUtils.sortArgumentConvert(sortArgument);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sort(key, soringParams, destKey).get());
		}else{
			return execute((cmd)->cmd.sort(key, soringParams, destKey));
		}
	}

	@Override
	public byte[] dump(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().dump(key).get());
		}else{
			return execute((cmd)->cmd.dump(key));
		}
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().restore(key, ttl, serializedValue).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.restore(key, ttl, serializedValue)));
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().migrate(host, port, key, db, timeout).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.migrate(host, port, key, db, timeout)));
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().migrate(host, port, key, db, timeout).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.migrate(host, port, key, db, timeout)));
		}
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation migrateOperation){
		final MigrateParams migrateParams = JedisClientUtils.migrateOperationConvert(migrateOperation);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().migrate(host, port, db, timeout,
					migrateParams, key).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.migrate(host, port, db, timeout, migrateParams, key)));
		}
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation migrateOperation){
		final MigrateParams migrateParams = JedisClientUtils.migrateOperationConvert(migrateOperation);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().migrate(host, port, db, timeout,
					migrateParams, key).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.migrate(host, port, db, timeout, migrateParams, key)));
		}
	}

	@Override
	public Long del(final String... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().del(keys).get());
		}else{
			return execute((cmd)->cmd.del(keys));
		}
	}

	@Override
	public Long del(final byte[]... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().del(keys).get());
		}else{
			return execute((cmd)->cmd.del(keys));
		}
	}

	@Override
	public Status move(final byte[] key, final int db){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().move(key, db).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.move(key, db) > 0));
		}
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("value", value);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().set(key, value).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.set(key, value)));
		}
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final StringCommands.SetArgument setArgument){
		final SetParams setParams = JedisClientUtils.setArgumentConvert(setArgument);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().set(key, value, setParams).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.set(key, value, setParams)));
		}
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().setex(key, lifetime, value).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.setex(key, lifetime, value)));
		}
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().psetex(key, lifetime, value).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.psetex(key, lifetime, value)));
		}
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().setnx(key, value).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setnx(key, value) > 0));
		}
	}

	@Override
	public Long append(final byte[] key, final byte[] value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().append(key, value).get());
		}else{
			return execute((cmd)->cmd.append(key, value));
		}
	}

	@Override
	public byte[] get(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().get(key).get());
		}else{
			return execute((cmd)->cmd.get(key));
		}
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().getSet(key, value).get());
		}else{
			return execute((cmd)->cmd.getSet(key, value));
		}
	}

	@Override
	public Status mSet(final Map<String, String> values){
		final List<String> temp = new ArrayList<>(values.size() * 2);

		values.forEach((key, value)->{
			temp.add(key);
			temp.add(value);
		});

		final String[] keysValues = temp.stream().toArray(String[]::new);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().mset(keysValues).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.mset(keysValues)));
		}
	}

	@Override
	public Status mSetNx(final Map<String, String> values){
		final List<String> temp = new ArrayList<>(values.size() * 2);

		values.forEach((key, value)->{
			temp.add(key);
			temp.add(value);
		});

		final String[] keysValues = temp.stream().toArray(String[]::new);

		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().msetnx(keysValues).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.msetnx(keysValues) > 0));
		}
	}

	@Override
	public List<String> mGet(final String... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().mget(keys).get());
		}else{
			return execute((cmd)->cmd.mget(keys));
		}
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().mget(keys).get());
		}else{
			return execute((cmd)->cmd.mget(keys));
		}
	}

	@Override
	public Long incr(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().incr(key).get());
		}else{
			return execute((cmd)->cmd.incr(key));
		}
	}

	@Override
	public Long incrBy(final byte[] key, final long value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().incrBy(key, value).get());
		}else{
			return execute((cmd)->cmd.incrBy(key, value));
		}
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().incrByFloat(key, value).get());
		}else{
			return execute((cmd)->cmd.incrByFloat(key, value));
		}
	}

	@Override
	public Long decr(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().decr(key).get());
		}else{
			return execute((cmd)->cmd.decr(key));
		}
	}

	@Override
	public Long decrBy(final byte[] key, final long value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().decrBy(key, value).get());
		}else{
			return execute((cmd)->cmd.decrBy(key, value));
		}
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().setrange(key, offset, value).get());
		}else{
			return execute((cmd)->cmd.setrange(key, offset, value));
		}
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().getrange(key, start, end).get());
		}else{
			return execute((cmd)->cmd.getrange(key, start, end));
		}
	}

	@Override
	public byte[] substr(final byte[] key, final int start, final int end){
		if(isTransaction()){
			return execute((cmd)->SafeEncoder.encode(getTransaction().substr(key, start, end).get()));
		}else{
			return execute((cmd)->cmd.substr(key, start, end));
		}
	}

	@Override
	public Long strlen(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().strlen(key).get());
		}else{
			return execute((cmd)->cmd.strlen(key));
		}
	}

	@Override
	public boolean hExists(final byte[] key, final byte[] field){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hexists(key, field).get());
		}else{
			return execute((cmd)->cmd.hexists(key, field));
		}
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hkeys(key).get());
		}else{
			return execute((cmd)->cmd.hkeys(key));
		}
	}

	@Override
	public List<byte[]> hVals(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hvals(key).get());
		}else{
			return execute((cmd)->cmd.hvals(key));
		}
	}

	@Override
	public Status hSet(final byte[] key, final byte[] field, final byte[] value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().hset(key, field, value).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.hset(key, field, value) > 0));
		}
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().hsetnx(key, field, value).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.hsetnx(key, field, value) > 0));
		}
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hget(key, field).get());
		}else{
			return execute((cmd)->cmd.hget(key, field));
		}
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().hmset(key, data).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.hmset(key, data)));
		}
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hmget(key, fields).get());
		}else{
			return execute((cmd)->cmd.hmget(key, fields));
		}
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hgetAll(key).get());
		}else{
			return execute((cmd)->cmd.hgetAll(key));
		}
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hstrlen(key, field).get());
		}else{
			return execute((cmd)->cmd.hstrlen(key, field));
		}
	}

	@Override
	public Long hLen(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hlen(key).get());
		}else{
			return execute((cmd)->cmd.hlen(key));
		}
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hincrBy(key, field, value).get());
		}else{
			return execute((cmd)->cmd.hincrBy(key, field, value));
		}
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hincrByFloat(key, field, value).get());
		}else{
			return execute((cmd)->cmd.hincrByFloat(key, field, value));
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor)));
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor,
					new JedisScanParams(pattern))));
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor,
					new JedisScanParams(count))));
		}
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.HSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.mapScanResultConvert(cmd.hscan(key, cursor,
					new JedisScanParams(pattern, count))));
		}
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields){
		if(isTransaction()){
			return execute((cmd)->getTransaction().hdel(key, fields).get());
		}else{
			return execute((cmd)->cmd.hdel(key, fields));
		}
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lpush(key, values).get());
		}else{
			return execute((cmd)->cmd.lpush(key, values));
		}
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lpushx(key, values).get());
		}else{
			return execute((cmd)->cmd.lpushx(key, values));
		}
	}

	@Override
	public Long lInsert(final byte[] key, final byte[] value, final ListCommands.ListPosition position,
			final byte[] pivot){
		final redis.clients.jedis.ListPosition pos = JedisClientUtils.listPositionConvert(position);

		if(isTransaction()){
			return execute((cmd)->cmd.linsert(key, pos, pivot, value));
		}else{
			return execute((cmd)->cmd.linsert(key, pos, pivot, value));
		}
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().lset(key, index, value).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.lset(key, index, value)));
		}
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lindex(key, index).get());
		}else{
			return execute((cmd)->cmd.lindex(key, index));
		}
	}

	@Override
	public byte[] lPop(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lpop(key).get());
		}else{
			return execute((cmd)->cmd.lpop(key));
		}
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout){
		if(isTransaction()){
			return execute((cmd)->getTransaction().blpop(timeout, keys).get());
		}else{
			return execute((cmd)->cmd.blpop(timeout, keys));
		}
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout){
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

		});
	}

	@Override
	public byte[] rPop(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().rpop(key).get());
		}else{
			return execute((cmd)->cmd.rpop(key));
		}
	}

	@Override
	public String rPoplPush(final String source, final String destKey){
		if(isTransaction()){
			return execute((cmd)->getTransaction().rpoplpush(source, destKey).get());
		}else{
			return execute((cmd)->cmd.rpoplpush(source, destKey));
		}
	}

	@Override
	public byte[] rPoplPush(final byte[] source, final byte[] destKey){
		if(isTransaction()){
			return execute((cmd)->getTransaction().rpoplpush(source, destKey).get());
		}else{
			return execute((cmd)->cmd.rpoplpush(source, destKey));
		}
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout){
		if(isTransaction()){
			return execute((cmd)->getTransaction().brpop(timeout, keys).get());
		}else{
			return execute((cmd)->cmd.brpop(timeout, keys));
		}
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout){
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

		});
	}

	@Override
	public String brPoplPush(final String source, final String destKey, final int timeout){
		if(isTransaction()){
			return execute((cmd)->getTransaction().brpoplpush(source, destKey, timeout).get());
		}else{
			return execute((cmd)->cmd.brpoplpush(source, destKey, timeout));
		}
	}

	@Override
	public byte[] brPoplPush(final byte[] source, final byte[] destKey, final int timeout){
		if(isTransaction()){
			return execute((cmd)->getTransaction().brpoplpush(source, destKey, timeout).get());
		}else{
			return execute((cmd)->cmd.brpoplpush(source, destKey, timeout));
		}
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values){
		if(isTransaction()){
			return execute((cmd)->getTransaction().rpush(key, values).get());
		}else{
			return execute((cmd)->cmd.rpush(key, values));
		}
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values){
		if(isTransaction()){
			return execute((cmd)->getTransaction().rpushx(key, values).get());
		}else{
			return execute((cmd)->cmd.rpushx(key, values));
		}
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().ltrim(key, start, end).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.ltrim(key, start, end)));
		}
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lrem(key, count, value).get());
		}else{
			return execute((cmd)->cmd.lrem(key, count, value));
		}
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().lrange(key, start, end).get());
		}else{
			return execute((cmd)->cmd.lrange(key, start, end));
		}
	}

	@Override
	public Long lLen(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().llen(key).get());
		}else{
			return execute((cmd)->cmd.llen(key));
		}
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sadd(key, members).get());
		}else{
			return execute((cmd)->cmd.sadd(key, members));
		}
	}

	@Override
	public Long sCard(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().scard(key).get());
		}else{
			return execute((cmd)->cmd.scard(key));
		}
	}

	@Override
	public boolean sisMember(final byte[] key, final byte[] member){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sismember(key, member).get());
		}else{
			return execute((cmd)->cmd.sismember(key, member));
		}
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().smembers(key).get());
		}else{
			return execute((cmd)->cmd.smembers(key));
		}
	}

	@Override
	public byte[] sPop(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().spop(key).get());
		}else{
			return execute((cmd)->cmd.spop(key));
		}
	}

	@Override
	public byte[] sRandMember(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().srandmember(key).get());
		}else{
			return execute((cmd)->cmd.srandmember(key));
		}
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().srandmember(key, count).get());
		}else{
			return execute((cmd)->cmd.srandmember(key, count));
		}
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members){
		if(isTransaction()){
			return execute((cmd)->getTransaction().srem(key, members).get());
		}else{
			return execute((cmd)->cmd.srem(key, members));
		}
	}

	@Override
	public Set<String> sDiff(final String... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sdiff(keys).get());
		}else{
			return execute((cmd)->cmd.sdiff(keys));
		}
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sdiff(keys).get());
		}else{
			return execute((cmd)->cmd.sdiff(keys));
		}
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sdiffstore(destKey, keys).get());
		}else{
			return execute((cmd)->cmd.sdiffstore(destKey, keys));
		}
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sdiffstore(destKey, keys).get());
		}else{
			return execute((cmd)->cmd.sdiffstore(destKey, keys));
		}
	}

	@Override
	public Set<String> sInter(final String... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sinter(keys).get());
		}else{
			return execute((cmd)->cmd.sinter(keys));
		}
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sinter(keys).get());
		}else{
			return execute((cmd)->cmd.sinter(keys));
		}
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sinterstore(destKey, keys).get());
		}else{
			return execute((cmd)->cmd.sinterstore(destKey, keys));
		}
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sinterstore(destKey, keys).get());
		}else{
			return execute((cmd)->cmd.sinterstore(destKey, keys));
		}
	}

	@Override
	public Set<String> sUnion(final String... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sunion(keys).get());
		}else{
			return execute((cmd)->cmd.sunion(keys));
		}
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys){
		final CommandArguments args = CommandArguments.getInstance().put("keys", keys);

		if(isTransaction()){
			return execute((cmd)->getTransaction().sunion(keys).get());
		}else{
			return execute((cmd)->cmd.sunion(keys));
		}
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sunionstore(destKey, keys).get());
		}else{
			return execute((cmd)->cmd.sunionstore(destKey, keys));
		}
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().sunionstore(destKey, keys).get());
		}else{
			return execute((cmd)->cmd.sunionstore(destKey, keys));
		}
	}

	@Override
	public Status sMove(final String source, final String destKey, final String member){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().smove(source, destKey, member).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.smove(source, destKey, member) > 0));
		}
	}

	@Override
	public Status sMove(final byte[] source, final byte[] destKey, final byte[] member){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().smove(source, destKey, member).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.smove(source, destKey, member) > 0));
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.sscan(key, cursor)));
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.sscan(key, cursor,
					new JedisScanParams(pattern))));
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.sscan(key, cursor,
					new JedisScanParams(count))));
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listScanResultDeconvert(cmd.sscan(key, cursor,
					new JedisScanParams(pattern, count))));
		}
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Number> members){
		final Map<byte[], Double> data = new LinkedHashMap<>(members.size());

		members.forEach((k, v)->data.put(k, v.doubleValue()));

		if(isTransaction()){
			return execute((cmd)->getTransaction().zadd(key, data).get());
		}else{
			return execute((cmd)->cmd.zadd(key, data));
		}
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zscore(key, member).get());
		}else{
			return execute((cmd)->cmd.zscore(key, member));
		}
	}

	@Override
	public Long zCard(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zcard(key).get());
		}else{
			return execute((cmd)->cmd.zcard(key));
		}
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final double increment){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zincrby(key, increment, member).get());
		}else{
			return execute((cmd)->cmd.zincrby(key, increment, member));
		}
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zcount(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zcount(key, min, max));
		}
	}

	@Override
	public Long zCount(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.getInstance().put("key", key).put("min", min).put("max", max);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zcount(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zcount(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrange(key, start, end).get());
		}else{
			return execute((cmd)->cmd.zrange(key, start, end));
		}
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeWithScores(key, start,
					end).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeWithScores(key, start, end)));
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByScore(key, min, max, offset, count).get());
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key, min
					, max).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key, min
					, max).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrangeByScoreWithScores(key, min
					, max, offset, count).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrangeByScoreWithScores(key, min, max, offset
					, count)));
		}
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByLex(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrangeByLex(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrangeByLex(key, min, max, offset, count).get());
		}else{
			return execute((cmd)->cmd.zrangeByLex(key, min, max, offset, count));
		}
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrank(key, member).get());
		}else{
			return execute((cmd)->cmd.zrank(key, member));
		}
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrank(key, member).get());
		}else{
			return execute((cmd)->cmd.zrevrank(key, member));
		}
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrem(key, members).get());
		}else{
			return execute((cmd)->cmd.zrem(key, members));
		}
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zremrangeByRank(key, start, end).get());
		}else{
			return execute((cmd)->cmd.zremrangeByRank(key, start, end));
		}
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zremrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zremrangeByScore(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zremrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zremrangeByScore(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zremrangeByLex(key, min, max).get());
		}else{
			return execute((cmd)->getTransaction().zremrangeByLex(key, min, max).get());
		}
	}

	@Override
	public Set<byte[]> zRevRange(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrange(key, start, end).get());
		}else{
			return execute((cmd)->cmd.zrevrange(key, start, end));
		}
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeWithScores(key, start,
					end).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeWithScores(key, start, end)));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByScore(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByScore(key, min, max, offset, count).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByScore(key, min, max, offset, count).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
			final int offset, final int count){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max, offset, count).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeByScoreWithScores(key, min, max,
					offset, count)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
			final int offset, final int count){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(getTransaction().zrevrangeByScoreWithScores(key,
					min, max, offset, count).get()));
		}else{
			return execute((cmd)->JedisClientUtils.setTupleDeconvert(cmd.zrevrangeByScoreWithScores(key, min, max,
					offset, count)));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByLex(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByLex(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zrevrangeByLex(key, min, max, offset, count).get());
		}else{
			return execute((cmd)->cmd.zrevrangeByLex(key, min, max, offset, count));
		}
	}

	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zlexcount(key, min, max).get());
		}else{
			return execute((cmd)->cmd.zlexcount(key, min, max));
		}
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zunionstore(destKey, keys).get());
		}else{
			return execute((cmd)->cmd.zunionstore(destKey, keys));
		}
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zunionstore(destKey, keys).get());
		}else{
			return execute((cmd)->cmd.zunionstore(destKey, keys));
		}
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final String... keys){
		final ZParams zParams = new JedisZParams(JedisClientUtils.aggregateConvert(aggregate));

		if(isTransaction()){
			return execute((cmd)->getTransaction().zunionstore(destKey, zParams, keys).get());
		}else{
			return execute((cmd)->cmd.zunionstore(destKey, zParams, keys));
		}
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final SortedSetCommands.Aggregate aggregate, final byte[]... keys){
		final ZParams zParams = new JedisZParams(JedisClientUtils.aggregateConvert(aggregate));

		if(isTransaction()){
			return execute((cmd)->getTransaction().zunionstore(destKey, zParams, keys).get());
		}else{
			return execute((cmd)->cmd.zunionstore(destKey, zParams, keys));
		}
	}

	@Override
	public Long zUnionStore(final String destKey, final double[] weights, final String... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zunionstore(destKey, new JedisZParams(weights), keys).get());
		}else{
			return execute((cmd)->cmd.zunionstore(destKey, new JedisZParams(weights), keys));
		}
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().zunionstore(destKey, new JedisZParams(weights), keys).get());
		}else{
			return execute((cmd)->cmd.zunionstore(destKey, new JedisZParams(weights), keys));
		}
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		final ZParams zParams = new JedisZParams(JedisClientUtils.aggregateConvert(aggregate), weights);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zunionstore(destKey, zParams, keys).get());
		}else{
			return execute((cmd)->cmd.zunionstore(destKey, zParams, keys));
		}
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final SortedSetCommands.Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		final ZParams zParams = new JedisZParams(JedisClientUtils.aggregateConvert(aggregate), weights);

		if(isTransaction()){
			return execute((cmd)->getTransaction().zunionstore(destKey, zParams, keys).get());
		}else{
			return execute((cmd)->cmd.zunionstore(destKey, zParams, keys));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listTupleScanResultDeconvert(cmd.zscan(key, cursor)));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listTupleScanResultDeconvert(cmd.zscan(key, cursor,
					new JedisScanParams(pattern))));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listTupleScanResultDeconvert(cmd.zscan(key, cursor,
					new JedisScanParams(count))));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->JedisClientUtils.listTupleScanResultDeconvert(cmd.zscan(key, cursor,
					new JedisScanParams(pattern, count))));
		}
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().pfadd(key, elements).get() > 0));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.pfadd(key, elements) > 0));
		}
	}

	@Override
	public Status pfMerge(final String destKey, final String... keys){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().pfmerge(destKey, keys).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.pfmerge(destKey, keys)));
		}
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().pfmerge(destKey, keys).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.pfmerge(destKey, keys)));
		}
	}

	@Override
	public Long pfCount(final String... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().pfcount(keys).get());
		}else{
			return execute((cmd)->cmd.pfcount(keys));
		}
	}

	@Override
	public Long pfCount(final byte[]... keys){
		if(isTransaction()){
			return execute((cmd)->getTransaction().pfcount(keys).get());
		}else{
			return execute((cmd)->cmd.pfcount(keys));
		}
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
		if(isTransaction()){
			return execute((cmd)->getTransaction().geoadd(key, longitude, latitude, member).get());
		}else{
			return execute((cmd)->cmd.geoadd(key, longitude, latitude, member));
		}
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates){
		final Map<byte[], GeoCoordinate> memberCoordinateMap = JedisClientUtils.geoMapConvert(memberCoordinates);

		if(isTransaction()){
			return execute((cmd)->getTransaction().geoadd(key, memberCoordinateMap).get());
		}else{
			return execute((cmd)->cmd.geoadd(key, memberCoordinateMap));
		}
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members){
		if(isTransaction()){
			return execute((cmd)->JedisClientUtils.geoListDeconvert(getTransaction().geopos(key, members).get()));
		}else{
			return execute((cmd)->JedisClientUtils.geoListDeconvert(cmd.geopos(key, members)));
		}
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
		if(isTransaction()){
			return execute((cmd)->getTransaction().geodist(key, member1, member2).get());
		}else{
			return execute((cmd)->cmd.geodist(key, member1, member2));
		}
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final byte[] value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().setbit(key, offset, value).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setbit(key, offset, value)));
		}
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final boolean value){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().setbit(SafeEncoder.encode(key), offset,
					value).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setbit(key, offset, value)));
		}
	}

	@Override
	public Status getBit(final byte[] key, final long offset){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForBool(getTransaction().getbit(key, offset).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.getbit(key, offset)));
		}
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().bitpos(key, value).get());
		}else{
			return execute((cmd)->cmd.bitpos(key, value));
		}
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final int start, final int end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().bitpos(key, value, new BitPosParams(start, end)).get());
		}else{
			return execute((cmd)->cmd.bitpos(key, value, new BitPosParams(start, end)));
		}
	}

	@Override
	public Long bitOp(final BitMapCommands.Operation operation, final String destKey, final String... keys){
		final BitOP bitOP = JedisClientUtils.bitOperationConvert(operation);

		if(isTransaction()){
			return execute((cmd)->getTransaction().bitop(bitOP, destKey, keys).get());
		}else{
			return execute((cmd)->cmd.bitop(bitOP, destKey, keys));
		}
	}

	@Override
	public Long bitOp(final BitMapCommands.Operation operation, final byte[] destKey, final byte[]... keys){
		final BitOP bitOP = JedisClientUtils.bitOperationConvert(operation);

		if(isTransaction()){
			return execute((cmd)->getTransaction().bitop(bitOP, destKey, keys).get());
		}else{
			return execute((cmd)->cmd.bitop(bitOP, destKey, keys));
		}
	}

	@Override
	public List<Long> bitField(final byte[] key, final byte[]... arguments){
		if(isTransaction()){
			return execute((cmd)->getTransaction().bitfield(key, arguments).get());
		}else{
			return execute((cmd)->cmd.bitfield(key, arguments));
		}
	}

	@Override
	public Long bitCount(final byte[] key){
		if(isTransaction()){
			return execute((cmd)->getTransaction().bitcount(key).get());
		}else{
			return execute((cmd)->cmd.bitcount(key));
		}
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return execute((cmd)->getTransaction().bitcount(key, start, end).get());
		}else{
			return execute((cmd)->cmd.bitcount(key, start, end));
		}
	}

	@Override
	public Transaction multi(){
		return execute((cmd)->new JedisTransaction(cmd.multi()));
	}

	@Override
	public void exec(final Transaction transaction){
		execute(new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis cmd){
				transaction.exec();
				return null;
			}

		});
	}

	@Override
	public void discard(final Transaction transaction){
		execute(new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis cmd){
				transaction.discard();
				return null;
			}

		});
	}

	@Override
	public Status watch(final String... keys){
		return execute((cmd)->ReturnUtils.statusForOK(cmd.watch(keys)));
	}

	@Override
	public Status watch(final byte[]... keys){
		return execute((cmd)->ReturnUtils.statusForOK(cmd.watch(keys)));
	}

	@Override
	public Status unwatch(){
		return execute((cmd)->ReturnUtils.statusForOK(cmd.unwatch()));
	}

	@Override
	public Long publish(final String channel, final String message){
		if(isTransaction()){
			return execute((cmd)->getTransaction().publish(channel, message).get());
		}else{
			return execute((cmd)->cmd.publish(channel, message));
		}
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message){
		if(isTransaction()){
			return execute((cmd)->getTransaction().publish(channel, message).get());
		}else{
			return execute((cmd)->cmd.publish(channel, message));
		}
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
		execute(new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SUBSCRIBE);
				}else{
					cmd.subscribe(new DefaultJedisPubSub(pubSubListener), channels);
					return null;
				}
			}

		});
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
		execute(new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SUBSCRIBE);
				}else{
					cmd.subscribe(new DefaultBinaryJedisPubSub(pubSubListener), channels);
					return null;
				}
			}

		});
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
		execute(new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.PSUBSCRIBE);
				}else{
					cmd.psubscribe(new DefaultJedisPubSub(pubSubListener), patterns);
					return null;
				}
			}

		});
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
		execute(new Executor<Jedis, Void>() {

			@Override
			public Void execute(Jedis cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.PSUBSCRIBE);
				}else{
					cmd.psubscribe(new DefaultBinaryJedisPubSub(pubSubListener), patterns);
					return null;
				}
			}

		});
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
		throw new NotSupportedCommandException(ProtocolCommand.PUNSUBSCRIBE);
	}

	@Override
	public Object pUnSubscribe(final String... patterns){
		throw new NotSupportedCommandException(ProtocolCommand.PUNSUBSCRIBE);
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns){
		throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	public Status select(final int db){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().select(db).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.select(db)));
		}
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().swapDB(db1, db2).get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.swapDB(db1, db2)));
		}
	}

	@Override
	public Long dbSize(){
		if(isTransaction()){
			return execute((cmd)->getTransaction().dbSize().get());
		}else{
			return execute((cmd)->cmd.dbSize());
		}
	}

	@Override
	public Status flushDb(){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().flushDB().get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.flushDB()));
		}
	}

	@Override
	public Status flushAll(){
		if(isTransaction()){
			return execute((cmd)->ReturnUtils.statusForOK(getTransaction().flushDB().get()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.flushDB()));
		}
	}

	@Override
	public Object eval(final String script){
		if(isTransaction()){
			return execute((cmd)->getTransaction().eval(script).get());
		}else{
			return execute((cmd)->cmd.eval(script));
		}
	}

	@Override
	public Object eval(final byte[] script){
		if(isTransaction()){
			return execute((cmd)->getTransaction().eval(script).get());
		}else{
			return execute((cmd)->cmd.eval(script));
		}
	}

	@Override
	public Object eval(final String script, final String... params){
		final int paramsSize = params == null ? 0 : params.length;

		if(isTransaction()){
			return execute((cmd)->getTransaction().eval(script, paramsSize, params).get());
		}else{
			return execute((cmd)->cmd.eval(script, paramsSize, params));
		}
	}

}