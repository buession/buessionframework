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

import com.buession.lang.Geo;
import com.buession.lang.Status;
import com.buession.redis.client.ShardedRedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.jedis.operations.ShardedJedisGeoOperations;
import com.buession.redis.client.jedis.operations.ShardedJedisConnectionOperations;
import com.buession.redis.client.jedis.operations.ShardedJedisHashOperations;
import com.buession.redis.client.jedis.operations.ShardedJedisHyperLogLogOperations;
import com.buession.redis.client.jedis.operations.ShardedJedisKeyOperations;
import com.buession.redis.client.jedis.operations.ShardedJedisListOperations;
import com.buession.redis.client.jedis.operations.ShardedJedisPubSubOperations;
import com.buession.redis.client.operations.ConnectionOperations;
import com.buession.redis.client.operations.GeoOperations;
import com.buession.redis.client.operations.HashOperations;
import com.buession.redis.client.operations.HyperLogLogOperations;
import com.buession.redis.client.operations.KeyOperations;
import com.buession.redis.client.operations.ListOperations;
import com.buession.redis.client.operations.PubSubOperations;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.jedis.JedisScanParams;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.ObjectCommand;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.JedisConverters;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.params.SetParams;

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
	public byte[] echo(final byte[] str){
		return connectionOperations.echo(str);
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
		return geoOperations.geoAdd(key, member, longitude, latitude);
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates){
		return geoOperations.geoAdd(key, memberCoordinates);
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members){
		return geoOperations.geoHash(key, members);
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members){
		return geoOperations.geoPos(key, members);
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
		return geoOperations.geoDist(key, member1, member2);
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit){
		return geoOperations.geoDist(key, member1, member2, unit);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius){
		return geoOperations.geoRadius(key, longitude, longitude, radius);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit){
		return geoOperations.geoRadius(key, longitude, latitude, radius, unit);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoRadiusArgument geoRadiusArgument){
		return geoOperations.geoRadius(key, longitude, latitude, radius, geoRadiusArgument);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		return geoOperations.geoRadius(key, longitude, latitude, radius, unit, geoRadiusArgument);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius){
		return geoOperations.geoRadiusByMember(key, member, radius);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit){
		return geoOperations.geoRadiusByMember(key, member, radius, unit);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoRadiusArgument geoRadiusArgument){
		return geoOperations.geoRadiusByMember(key, member, radius, geoRadiusArgument);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		return geoOperations.geoRadiusByMember(key, member, radius, unit, geoRadiusArgument);
	}

	@Override
	public Long hDecrBy(final byte[] key, final byte[] field, final int value){
		return hashOperations.hDecrBy(key, field, value);
	}

	@Override
	public Long hDecrBy(final byte[] key, final byte[] field, final long value){
		return hashOperations.hDecrBy(key, field, value);
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields){
		return hashOperations.hDel(key, fields);
	}

	@Override
	public boolean hExists(final byte[] key, final byte[] field){
		return hashOperations.hExists(key, field);
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field){
		return hashOperations.hGet(key, field);
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key){
		return hashOperations.hGetAll(key);
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final int value){
		return hashOperations.hIncrBy(key, field, value);
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value){
		return hashOperations.hIncrBy(key, field, value);
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final float value){
		return hashOperations.hIncrByFloat(key, field, value);
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value){
		return hashOperations.hIncrByFloat(key, field, value);
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key){
		return hashOperations.hKeys(key);
	}

	@Override
	public Long hLen(final byte[] key){
		return hashOperations.hLen(key);
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields){
		return hashOperations.hMGet(key, fields);
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data){
		return hashOperations.hMSet(key, data);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor){
		return hashOperations.hScan(key, cursor);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor){
		return hashOperations.hScan(key, cursor);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
		return hashOperations.hScan(key, cursor);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern){
		return hashOperations.hScan(key, cursor, pattern);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern){
		return hashOperations.hScan(key, cursor, pattern);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return hashOperations.hScan(key, cursor, pattern);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final int count){
		return hashOperations.hScan(key, cursor, count);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final int count){
		return hashOperations.hScan(key, cursor, count);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final int count){
		return hashOperations.hScan(key, cursor, count);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern,
			final int count){
		return hashOperations.hScan(key, cursor, pattern, count);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern,
			final int count){
		return hashOperations.hScan(key, cursor, pattern, count);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		return hashOperations.hScan(key, cursor, pattern, count);
	}

	@Override
	public Status hSet(final byte[] key, final byte[] field, final byte[] value){
		return hashOperations.hSet(key, field, value);
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value){
		return hashOperations.hSetNx(key, field, value);
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field){
		return hashOperations.hStrLen(key, field);
	}

	@Override
	public List<byte[]> hVals(final byte[] key){
		return hashOperations.hVals(key);
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements){
		return hyperLogLogOperations.pfAdd(key, elements);
	}

	@Override
	public Long pfCount(final String... keys){
		return hyperLogLogOperations.pfCount(keys);
	}

	@Override
	public Long pfCount(final byte[]... keys){
		return hyperLogLogOperations.pfCount(keys);
	}

	@Override
	public Long del(final String... keys){
		return keyOperations.del(keys);
	}

	@Override
	public Long del(final byte[]... keys){
		return keyOperations.del(keys);
	}

	@Override
	public byte[] dump(final byte[] key){
		return keyOperations.dump(key);
	}

	@Override
	public boolean exists(final byte[] key){
		return keyOperations.exists(key);
	}

	@Override
	public Status expire(final byte[] key, final int lifetime){
		return keyOperations.expire(key, lifetime);
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		return keyOperations.expireAt(key, unixTimestamp);
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
		return keyOperations.migrate(key, host, port, db, timeout);
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
		return keyOperations.migrate(key, host, port, db, timeout);
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation operation){
		return keyOperations.migrate(key, host, port, db, timeout, operation);
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation operation){
		return keyOperations.migrate(key, host, port, db, timeout, operation);
	}

	@Override
	public Status move(final byte[] key, final int db){
		return keyOperations.move(key, db);
	}

	@Override
	public Status persist(final byte[] key){
		return keyOperations.persist(key);
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		return keyOperations.pExpire(key, lifetime);
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		return keyOperations.pExpireAt(key, unixTimestamp);
	}

	@Override
	public Long pTtl(final byte[] key){
		return keyOperations.pTtl(key);
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		return keyOperations.restore(key, serializedValue, ttl);
	}

	@Override
	public List<byte[]> sort(final byte[] key){
		return keyOperations.sort(key);
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument){
		return keyOperations.sort(key, sortArgument);
	}

	@Override
	public Long ttl(final byte[] key){
		return keyOperations.ttl(key);
	}

	@Override
	public Type type(final byte[] key){
		return keyOperations.type(key);
	}

	@Override
	public Long touch(final String... keys){
		return keyOperations.touch(keys);
	}

	@Override
	public Long touch(final byte[]... keys){
		return keyOperations.touch(keys);
	}

	@Override
	public Long unlink(final String... keys){
		return keyOperations.unlink(keys);
	}

	@Override
	public Long unlink(final byte[]... keys){
		return keyOperations.unlink(keys);
	}

	@Override
	public byte[] lIndex(final byte[] key, final int index){
		return listOperations.lIndex(key, index);
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index){
		return listOperations.lIndex(key, index);
	}

	@Override
	public Long lInsert(final byte[] key, final byte[] value, final ListPosition position, final byte[] pivot){
		return listOperations.lInsert(key, value, position, pivot);
	}

	@Override
	public Long lLen(final byte[] key){
		return listOperations.lLen(key);
	}

	@Override
	public byte[] lPop(final byte[] key){
		return listOperations.lPop(key);
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values){
		return listOperations.lPush(key, values);
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values){
		return listOperations.lPushX(key, values);
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final int start, final int end){
		return listOperations.lRange(key, start, end);
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end){
		return listOperations.lRange(key, start, end);
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final int count){
		return listOperations.lRem(key, value, count);
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count){
		return listOperations.lRem(key, value, count);
	}

	@Override
	public Status lSet(final byte[] key, final int index, final byte[] value){
		return listOperations.lSet(key, index, value);
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value){
		return listOperations.lSet(key, index, value);
	}

	@Override
	public Status lTrim(final byte[] key, final int start, final int end){
		return listOperations.lTrim(key, start, end);
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end){
		return listOperations.lTrim(key, start, end);
	}

	@Override
	public byte[] rPop(final byte[] key){
		return listOperations.rPop(key);
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values){
		return listOperations.rPush(key, values);
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values){
		return listOperations.rPushX(key, values);
	}

	@Override
	public Object object(final ObjectCommand command, final String key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(JedisClientUtils.objectDebug(command, getTransaction(),
					key)));
		}else{
			return execute((cmd)->JedisClientUtils.objectDebug(command, getShard(cmd, key), key));
		}
	}

	@Override
	public Object object(final ObjectCommand command, final byte[] key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(JedisClientUtils.objectDebug(command, getTransaction(),
					key)));
		}else{
			return execute((cmd)->JedisClientUtils.objectDebug(command, getShard(cmd, key), key));
		}
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sadd(key, members)));
		}else{
			return execute((cmd)->cmd.sadd(key, members));
		}
	}

	@Override
	public Long sCard(final byte[] key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().scard(key)));
		}else{
			return execute((cmd)->cmd.scard(key));
		}
	}

	@Override
	public boolean sisMember(final byte[] key, final byte[] member){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sismember(key, member)));
		}else{
			return execute((cmd)->cmd.sismember(key, member));
		}
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().smembers(key)));
		}else{
			return execute((cmd)->cmd.smembers(key));
		}
	}

	@Override
	public byte[] sPop(final byte[] key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().spop(key)));
		}else{
			return execute((cmd)->cmd.spop(key));
		}
	}

	@Override
	public byte[] sRandMember(final byte[] key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srandmember(key)));
		}else{
			return execute((cmd)->cmd.srandmember(key));
		}
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srandmember(key, count)));
		}else{
			return execute((cmd)->cmd.srandmember(key, count));
		}
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srem(key, members)));
		}else{
			return execute((cmd)->cmd.srem(key, members));
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->BINARY_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor)));
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->BINARY_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor,
					new JedisScanParams(pattern))));
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->BINARY_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor,
					new JedisScanParams(count))));
		}
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->BINARY_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor,
					new JedisScanParams(pattern, count))));
		}
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Number> members){
		final Map<byte[], Double> data = new LinkedHashMap<>(members.size());

		members.forEach((k, v)->data.put(k, v.doubleValue()));

		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zadd(key, data)));
		}else{
			return execute((cmd)->cmd.zadd(key, data));
		}
	}

	@Override
	public Long zCard(final byte[] key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zcard(key)));
		}else{
			return execute((cmd)->cmd.zcard(key));
		}
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zcount(key, min, max)));
		}else{
			return execute((cmd)->cmd.zcount(key, min, max));
		}
	}

	@Override
	public Long zCount(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zcount(key, min, max)));
		}else{
			return execute((cmd)->cmd.zcount(key, min, max));
		}
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final double increment){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zincrby(key, increment, member)));
		}else{
			return execute((cmd)->cmd.zincrby(key, increment, member));
		}
	}

	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zlexcount(key, min, max)));
		}else{
			return execute((cmd)->cmd.zlexcount(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrange(key, start, end)));
		}else{
			return execute((cmd)->cmd.zrange(key, start, end));
		}
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeWithScores(key, start, end),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeWithScores(key, start, end)));
		}
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByLex(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrangeByLex(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByLex(key, min, max, offset,
					count)));
		}else{
			return execute((cmd)->cmd.zrangeByLex(key, min, max, offset, count));
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScore(key, min, max, offset,
					count)));
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScoreWithScores(key, min, max),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScoreWithScores(key, min, max),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
			final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScoreWithScores(key, min, max,
					offset, count), SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeByScoreWithScores(key, min, max, offset
					, count)));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScoreWithScores(key, min, max,
					offset, count), SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeByScoreWithScores(key, min, max, offset
					, count)));
		}
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrank(key, member)));
		}else{
			return execute((cmd)->cmd.zrank(key, member));
		}
	}

	@Override
	public Tuple zPopMax(final byte[] key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zpopmax(key), TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->TUPLE_EXPOSE_CONVERTER.convert(cmd.zpopmax(key)));
		}
	}

	@Override
	public Set<Tuple> zPopMax(final byte[] key, final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zpopmax(key, count),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zpopmax(key, count)));
		}
	}

	@Override
	public Tuple zPopMin(final byte[] key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zpopmin(key), TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->TUPLE_EXPOSE_CONVERTER.convert(cmd.zpopmin(key)));
		}
	}

	@Override
	public Set<Tuple> zPopMin(final byte[] key, final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zpopmin(key, count),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zpopmin(key, count)));
		}
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrem(key, members)));
		}else{
			return execute((cmd)->cmd.zrem(key, members));
		}
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zremrangeByLex(key, min, max)));
		}else{
			return execute((cmd)->cmd.zremrangeByLex(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zremrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zremrangeByScore(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zremrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zremrangeByScore(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zremrangeByRank(key, start, end)));
		}else{
			return execute((cmd)->cmd.zremrangeByRank(key, start, end));
		}
	}

	@Override
	public Set<byte[]> zRevRange(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrange(key, start, end)));
		}else{
			return execute((cmd)->cmd.zrevrange(key, start, end));
		}
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeWithScores(key, start, end),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeWithScores(key, start, end)));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByLex(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrevrangeByLex(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByLex(key, min, max, offset,
					count)));
		}else{
			return execute((cmd)->cmd.zrevrangeByLex(key, min, max, offset, count));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScore(key, min, max, offset,
					count)));
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScore(key, min, max, offset,
					count)));
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScoreWithScores(key, min,
					max)));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScoreWithScores(key, min,
					max)));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
			final int offset, final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScoreWithScores(key, min, max,
					offset, count)));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeByScoreWithScores(key, min, max,
					offset, count)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
			final int offset, final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScoreWithScores(key, min, max,
					offset, count)));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeByScoreWithScores(key, min, max,
					offset, count)));
		}
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrank(key, member)));
		}else{
			return execute((cmd)->cmd.zrevrank(key, member));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.zscan(key, cursor)));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.zscan(key, cursor,
					new JedisScanParams(pattern))));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.zscan(key, cursor,
					new JedisScanParams(count))));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.zscan(key, cursor,
					new JedisScanParams(pattern, count))));
		}
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zscore(key, member)));
		}else{
			return execute((cmd)->cmd.zscore(key, member));
		}
	}

	@Override
	public Long append(final byte[] key, final byte[] value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().append(key, value)));
		}else{
			return execute((cmd)->cmd.append(key, value));
		}
	}

	@Override
	public Long bitCount(final byte[] key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitcount(key)));
		}else{
			return execute((cmd)->cmd.bitcount(key));
		}
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitcount(key, start, end)));
		}else{
			return execute((cmd)->cmd.bitcount(key, start, end));
		}
	}

	@Override
	public List<Long> bitField(final byte[] key, final byte[]... arguments){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitfield(key, arguments)));
		}else{
			return execute((cmd)->cmd.bitfield(key, arguments));
		}
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitpos(key, value)));
		}else{
			return execute((cmd)->cmd.bitpos(SafeEncoder.encode(key), value));
		}
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final int start, final int end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitpos(key, value, new BitPosParams(start
					, end))));
		}else{
			return execute((cmd)->cmd.bitpos(SafeEncoder.encode(key), value, new BitPosParams(start, end)));
		}
	}

	@Override
	public Long decr(final byte[] key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().decr(key)));
		}else{
			return execute((cmd)->cmd.decr(key));
		}
	}

	@Override
	public Long decrBy(final byte[] key, final long value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().decrBy(key, value)));
		}else{
			return execute((cmd)->cmd.decrBy(key, value));
		}
	}

	@Override
	public byte[] get(final byte[] key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().get(key)));
		}else{
			return execute((cmd)->cmd.get(key));
		}
	}

	@Override
	public Status getBit(final byte[] key, final long offset){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().getbit(key, offset),
					JedisConverters.booleanToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.getbit(key, offset)));
		}
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().getrange(key, start, end)));
		}else{
			return execute((cmd)->cmd.getrange(key, start, end));
		}
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().getSet(key, value)));
		}else{
			return execute((cmd)->cmd.getSet(key, value));
		}
	}

	@Override
	public Long incr(final byte[] key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().incr(key)));
		}else{
			return execute((cmd)->cmd.incr(key));
		}
	}

	@Override
	public Long incrBy(final byte[] key, final long value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().incrBy(key, value)));
		}else{
			return execute((cmd)->cmd.incrBy(key, value));
		}
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().incrByFloat(key, value)));
		}else{
			return execute((cmd)->cmd.incrByFloat(key, value));
		}
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().psetex(key, lifetime, value),
					JedisConverters.okToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.psetex(key, lifetime, value)));
		}
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().set(key, value),
					JedisConverters.okToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.set(key, value)));
		}
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument){
		final SetParams setParams = SET_ARGUMENT_JEDIS_CONVERTER.convert(setArgument);

		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().set(key, value, setParams),
					JedisConverters.okToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.set(key, value, setParams)));
		}
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final byte[] value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setbit(key, offset, value),
					JedisConverters.booleanToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setbit(key, offset, value)));
		}
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final boolean value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setbit(SafeEncoder.encode(key), offset,
					value), JedisConverters.booleanToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setbit(key, offset, value)));
		}
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setex(key, lifetime, value),
					JedisConverters.okToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.setex(key, lifetime, value)));
		}
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setnx(key, value),
					JedisConverters.positiveLongNumberToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setnx(key, value) > 0));
		}
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setrange(key, offset, value)));
		}else{
			return execute((cmd)->cmd.setrange(key, offset, value));
		}
	}

	@Override
	public Long strlen(final byte[] key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().strlen(key)));
		}else{
			return execute((cmd)->cmd.strlen(key));
		}
	}

	@Override
	public byte[] substr(final byte[] key, final int start, final int end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().substr(key, start, end),
					JedisConverters.stringToBinaryConverter()));
		}else{
			return execute((cmd)->cmd.substr(key, start, end));
		}
	}

	@Override
	protected ConnectionOperations createConnectionOperations(){
		return new ShardedJedisConnectionOperations(this);
	}

	@Override
	protected GeoOperations createGeoOperations(){
		return new ShardedJedisGeoOperations(this);
	}

	@Override
	protected HashOperations createHashOperations(){
		return new ShardedJedisHashOperations(this);
	}

	@Override
	protected HyperLogLogOperations createHyperLogLogOperations(){
		return new ShardedJedisHyperLogLogOperations(this);
	}

	@Override
	protected KeyOperations createKeyOperations(){
		return new ShardedJedisKeyOperations(this);
	}

	@Override
	protected ListOperations createListOperations(){
		return new ShardedJedisListOperations(this);
	}

	@Override
	protected PubSubOperations createPubSubOperations(){
		return new ShardedJedisPubSubOperations(this);
	}

	protected final static Jedis getShard(final ShardedJedis shardedJedis, final String key){
		return shardedJedis.getShard(key);
	}

	protected final Jedis getShard(final ShardedJedis shardedJedis, final byte[] key){
		return shardedJedis.getShard(key);
	}

}
