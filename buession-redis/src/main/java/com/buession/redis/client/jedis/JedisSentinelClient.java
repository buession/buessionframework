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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis;

import com.buession.lang.Geo;
import com.buession.lang.Status;
import com.buession.redis.client.RedisStandaloneClient;
import com.buession.redis.client.connection.jedis.JedisSentinelConnection;
import com.buession.redis.client.jedis.operations.JedisSentinelClusterOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelConnectionOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelGeoOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelHashOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelHyperLogLogOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelKeyOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelListOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelPubSubOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelScriptingOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelServerOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelSetOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelSortedSetOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelStringOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelTransactionOperations;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.Direction;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.ObjectCommand;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.SlowLogCommand;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * jedis 哨兵模式客户端
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class JedisSentinelStandaloneClient extends AbstractJedisRedisClient implements RedisStandaloneClient {

	public JedisSentinelStandaloneClient(){
		super();
	}

	public JedisSentinelStandaloneClient(JedisSentinelConnection connection){
		super(connection);
	}

	@Override
	public Status auth(final byte[] password){
		return connectionOperations.auth(password);
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
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument){
		return geoOperations.geoRadius(key, longitude, latitude, radius, geoRadiusArgument);
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
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor){
		return hashOperations.hScan(key, cursor);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
		return hashOperations.hScan(key, cursor);
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
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final long count){
		return hashOperations.hScan(key, cursor, count);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final long count){
		return hashOperations.hScan(key, cursor, count);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern,
												 final long count){
		return hashOperations.hScan(key, cursor, pattern, count);
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
												 final long count){
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
	public byte[] hrandfield(final byte[] key){
		return hashOperations.hrandfield(key);
	}

	@Override
	public List<byte[]> hrandfield(final byte[] key, final long count){
		return hashOperations.hrandfield(key, count);
	}

	@Override
	public Map<byte[], byte[]> hrandfieldWithValues(final byte[] key, final long count){
		return hashOperations.hrandfieldWithValues(key, count);
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements){
		return hyperLogLogOperations.pfAdd(key, elements);
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys){
		return hyperLogLogOperations.pfMerge(destKey, keys);
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
	public Set<byte[]> keys(final byte[] pattern){
		return keyOperations.keys(pattern);
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
	public Status rename(final byte[] key, final byte[] newKey){
		return keyOperations.rename(key, newKey);
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		return keyOperations.renameNx(key, newKey);
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		return keyOperations.restore(key, serializedValue, ttl);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		return keyOperations.scan(cursor);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern){
		return keyOperations.scan(cursor, pattern);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		return keyOperations.scan(cursor, pattern);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count){
		return keyOperations.scan(cursor, count);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern, final int count){
		return keyOperations.scan(cursor, pattern, count);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count){
		return keyOperations.scan(cursor, pattern, count);
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
	public Long sort(final byte[] key, final byte[] destKey){
		return keyOperations.sort(key, destKey);
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		return keyOperations.sort(key, destKey, sortArgument);
	}

	@Override
	public Long ttl(final byte[] key){
		return keyOperations.ttl(key);
	}

	@Override
	public Long touch(final byte[]... keys){
		return keyOperations.touch(keys);
	}

	@Override
	public Type type(final byte[] key){
		return keyOperations.type(key);
	}

	@Override
	public Long unlink(final byte[]... keys){
		return keyOperations.unlink(keys);
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout){
		return listOperations.blPop(keys, timeout);
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout){
		return listOperations.brPop(keys, timeout);
	}

	@Override
	public byte[] brPoplPush(final byte[] key, final byte[] destKey, final int timeout){
		return listOperations.brPoplPush(key, destKey, timeout);
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
	public List<byte[]> lRange(final byte[] key, final long start, final long end){
		return listOperations.lRange(key, start, end);
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count){
		return listOperations.lRem(key, value, count);
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value){
		return listOperations.lSet(key, index, value);
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
	public byte[] rPoplPush(final byte[] key, final byte[] destKey){
		return listOperations.rPoplPush(key, destKey);
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
	public byte[] lMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to){
		return listOperations.lMove(key, destKey, from, to);
	}

	@Override
	public byte[] blMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
						 final int timeout){
		return listOperations.blMove(key, destKey, from, to, timeout);
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
		pubSubOperations.pSubscribe(patterns, pubSubListener);
	}

	@Override
	public List<byte[]> pubsubChannels(final byte[] pattern){
		return pubSubOperations.pubsubChannels(pattern);
	}

	@Override
	public Map<byte[], byte[]> pubsubNumSub(final byte[]... channels){
		return pubSubOperations.pubsubNumSub(channels);
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message){
		return pubSubOperations.publish(channel, message);
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns){
		return pubSubOperations.pUnSubscribe(patterns);
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
		pubSubOperations.subscribe(channels, pubSubListener);
	}

	@Override
	public Object unSubscribe(final byte[]... channels){
		return pubSubOperations.unSubscribe(channels);
	}

	@Override
	public Object eval(final byte[] script){
		return scriptingOperations.eval(script);
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params){
		return scriptingOperations.eval(script, params);
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments){
		return scriptingOperations.eval(script, keys, arguments);
	}

	@Override
	public Object evalSha(final byte[] digest){
		return scriptingOperations.evalSha(digest);
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... params){
		return scriptingOperations.evalSha(digest, params);
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments){
		return scriptingOperations.evalSha(digest, keys, arguments);
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1){
		return scriptingOperations.scriptExists(sha1);
	}

	@Override
	public byte[] scriptLoad(final byte[] script){
		return scriptingOperations.scriptLoad(script);
	}

	@Override
	public Status clientSetName(final byte[] name){
		return serverOperations.clientSetName(name);
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter){
		return serverOperations.configGet(parameter);
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		return serverOperations.configSet(parameter, value);
	}

	@Override
	public byte[] debugObject(final byte[] key){
		return serverOperations.debugObject(key);
	}

	@Override
	public Object object(final ObjectCommand command, final byte[] key){
		return serverOperations.object(command, key);
	}

	@Override
	public Object pSync(final byte[] masterRunId, final long offset){
		return serverOperations.pSync(masterRunId, offset);
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final byte[]... arguments){
		return serverOperations.slowLog(command, arguments);
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members){
		return setOperations.sAdd(key, members);
	}

	@Override
	public Long sCard(final byte[] key){
		return setOperations.sCard(key);
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys){
		return setOperations.sDiff(keys);
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys){
		return setOperations.sDiffStore(destKey, keys);
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys){
		return setOperations.sInter(keys);
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys){
		return setOperations.sInterStore(destKey, keys);
	}

	@Override
	public boolean sisMember(final byte[] key, final byte[] member){
		return setOperations.sisMember(key, member);
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		return setOperations.sMembers(key);
	}

	@Override
	public Status sMove(final byte[] key, final byte[] destKey, final byte[] member){
		return setOperations.sMove(key, destKey, member);
	}

	@Override
	public byte[] sPop(final byte[] key){
		return setOperations.sPop(key);
	}

	@Override
	public byte[] sRandMember(final byte[] key){
		return setOperations.sRandMember(key);
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final long count){
		return setOperations.sRandMember(key, count);
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members){
		return setOperations.sRem(key, members);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor){
		return setOperations.sScan(key, cursor);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		return setOperations.sScan(key, cursor);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern){
		return setOperations.sScan(key, cursor, pattern);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return setOperations.sScan(key, cursor, pattern);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final long count){
		return setOperations.sScan(key, cursor, count);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final long count){
		return setOperations.sScan(key, cursor, count);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern, final long count){
		return setOperations.sScan(key, cursor, pattern, count);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										  final long count){
		return setOperations.sScan(key, cursor, pattern, count);
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys){
		return setOperations.sUnion(keys);
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys){
		return setOperations.sUnionStore(destKey, keys);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Number> members){
		return sortedSetOperations.zAdd(key, members);
	}

	@Override
	public Long zCard(final byte[] key){
		return sortedSetOperations.zCard(key);
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max){
		return sortedSetOperations.zCount(key, min, max);
	}

	@Override
	public Long zCount(final byte[] key, final long min, final long max){
		return sortedSetOperations.zCount(key, min, max);
	}

	@Override
	public Long zCount(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOperations.zCount(key, min, max);
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final double increment){
		return sortedSetOperations.zIncrBy(key, member, increment);
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final long increment){
		return sortedSetOperations.zIncrBy(key, member, increment);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys){
		return sortedSetOperations.zInterStore(destKey, keys);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		return sortedSetOperations.zInterStore(destKey, aggregate, keys);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		return sortedSetOperations.zInterStore(destKey, weights, keys);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
							final byte[]... keys){
		return sortedSetOperations.zInterStore(destKey, aggregate, weights, keys);
	}

	@Override
	public Long zLexCount(final byte[] key, final double min, final double max){
		return sortedSetOperations.zLexCount(key, min, max);
	}

	@Override
	public Long zLexCount(final byte[] key, final long min, final long max){
		return sortedSetOperations.zLexCount(key, min, max);
	}

	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOperations.zLexCount(key, min, max);
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final long start, final long end){
		return sortedSetOperations.zRange(key, start, end);
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
		return sortedSetOperations.zRangeWithScores(key, start, end);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max){
		return sortedSetOperations.zRangeByLex(key, min, max);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max){
		return sortedSetOperations.zRangeByLex(key, min, max);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOperations.zRangeByLex(key, min, max);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final long offset,
								   final long count){
		return sortedSetOperations.zRangeByLex(key, min, max, offset, count);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max, final long offset,
								   final long count){
		return sortedSetOperations.zRangeByLex(key, min, max, offset, count);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
								   final long count){
		return sortedSetOperations.zRangeByLex(key, min, max, offset, count);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
		return sortedSetOperations.zRangeByScore(key, min, max);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max){
		return sortedSetOperations.zRangeByScore(key, min, max);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOperations.zRangeByScore(key, min, max);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final long offset,
									 final long count){
		return sortedSetOperations.zRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max, final long offset,
									 final long count){
		return sortedSetOperations.zRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final long offset,
									 final long count){
		return sortedSetOperations.zRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
		return sortedSetOperations.zRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max){
		return sortedSetOperations.zRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOperations.zRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final long offset,
											  final long count){
		return sortedSetOperations.zRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max, final long offset,
											  final long count){
		return sortedSetOperations.zRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final long offset,
											  final long count){
		return sortedSetOperations.zRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member){
		return sortedSetOperations.zRank(key, member);
	}

	@Override
	public Tuple zPopMax(final byte[] key){
		return sortedSetOperations.zPopMax(key);
	}

	@Override
	public Set<Tuple> zPopMax(final byte[] key, final long count){
		return sortedSetOperations.zPopMax(key, count);
	}

	@Override
	public Tuple zPopMin(final byte[] key){
		return sortedSetOperations.zPopMin(key);
	}

	@Override
	public Set<Tuple> zPopMin(final byte[] key, final long count){
		return sortedSetOperations.zPopMin(key, count);
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members){
		return sortedSetOperations.zRem(key, members);
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final double min, final double max){
		return sortedSetOperations.zRemRangeByLex(key, min, max);
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final long min, final long max){
		return sortedSetOperations.zRemRangeByLex(key, min, max);
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOperations.zRemRangeByLex(key, min, max);
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max){
		return sortedSetOperations.zRemRangeByScore(key, min, max);
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final long min, final long max){
		return sortedSetOperations.zRemRangeByScore(key, min, max);
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOperations.zRemRangeByScore(key, min, max);
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end){
		return sortedSetOperations.zRemRangeByRank(key, start, end);
	}

	@Override
	public Set<byte[]> zRevRange(final byte[] key, final long start, final long end){
		return sortedSetOperations.zRevRange(key, start, end);
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
		return sortedSetOperations.zRevRangeWithScores(key, start, end);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(byte[] key, double min, double max){
		return sortedSetOperations.zRevRangeByLex(key, min, max);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(byte[] key, long min, long max){
		return sortedSetOperations.zRevRangeByLex(key, min, max);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOperations.zRevRangeByLex(key, min, max);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(byte[] key, double min, double max, long offset, long count){
		return sortedSetOperations.zRevRangeByLex(key, min, max, offset, count);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(byte[] key, long min, long max, long offset, long count){
		return sortedSetOperations.zRevRangeByLex(key, min, max, offset, count);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									  final long count){
		return sortedSetOperations.zRevRangeByLex(key, min, max, offset, count);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
		return sortedSetOperations.zRevRangeByScore(key, min, max);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max){
		return sortedSetOperations.zRevRangeByScore(key, min, max);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOperations.zRevRangeByScore(key, min, max);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final long offset,
										final long count){
		return sortedSetOperations.zRevRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max, final long offset,
										final long count){
		return sortedSetOperations.zRevRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final long offset,
										final long count){
		return sortedSetOperations.zRevRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
		return sortedSetOperations.zRevRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max){
		return sortedSetOperations.zRevRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOperations.zRevRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
												 final long offset,
												 final long count){
		return sortedSetOperations.zRevRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max, final long offset,
												 final long count){
		return sortedSetOperations.zRevRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
												 final long offset, final long count){
		return sortedSetOperations.zRevRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member){
		return sortedSetOperations.zRevRank(key, member);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor){
		return sortedSetOperations.zScan(key, cursor);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
		return sortedSetOperations.zScan(key, cursor);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern){
		return sortedSetOperations.zScan(key, cursor, pattern);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return sortedSetOperations.zScan(key, cursor, pattern);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final long count){
		return sortedSetOperations.zScan(key, cursor, count);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final long count){
		return sortedSetOperations.zScan(key, cursor, count);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern, final long count){
		return sortedSetOperations.zScan(key, cursor, pattern, count);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final long count){
		return sortedSetOperations.zScan(key, cursor, pattern, count);
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member){
		return sortedSetOperations.zScore(key, member);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys){
		return sortedSetOperations.zUnionStore(destKey, keys);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		return sortedSetOperations.zUnionStore(destKey, aggregate, keys);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		return sortedSetOperations.zUnionStore(destKey, weights, keys);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
							final byte[]... keys){
		return sortedSetOperations.zUnionStore(destKey, aggregate, weights, keys);
	}

	@Override
	public Long append(final byte[] key, final byte[] value){
		return stringOperations.append(key, value);
	}

	@Override
	public Long bitCount(final byte[] key){
		return stringOperations.bitCount(key);
	}

	@Override
	public Long bitCount(final byte[] key, final int start, final int end){
		return stringOperations.bitCount(key, start, end);
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end){
		return stringOperations.bitCount(key, start, end);
	}

	@Override
	public List<Long> bitField(final byte[] key, final byte[]... arguments){
		return stringOperations.bitField(key, arguments);
	}

	@Override
	public Long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys){
		return stringOperations.bitOp(operation, destKey, keys);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value){
		return stringOperations.bitPos(key, value);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final int start, final int end){
		return stringOperations.bitPos(key, value, start, end);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end){
		return stringOperations.bitPos(key, value, start, end);
	}

	@Override
	public Long decr(final byte[] key){
		return stringOperations.decr(key);
	}

	@Override
	public Long decrBy(final byte[] key, final int value){
		return stringOperations.decrBy(key, value);
	}

	@Override
	public Long decrBy(final byte[] key, final long value){
		return stringOperations.decrBy(key, value);
	}

	@Override
	public byte[] get(final byte[] key){
		return stringOperations.get(key);
	}

	@Override
	public Status getBit(final byte[] key, final int offset){
		return stringOperations.getBit(key, offset);
	}

	@Override
	public Status getBit(final byte[] key, final long offset){
		return stringOperations.getBit(key, offset);
	}

	@Override
	public byte[] getRange(final byte[] key, final int start, final int end){
		return stringOperations.getRange(key, start, end);
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		return stringOperations.getRange(key, start, end);
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		return stringOperations.getSet(key, value);
	}

	@Override
	public byte[] getEx(final byte[] key, final GetExArgument getExArgument){
		return stringOperations.getEx(key, getExArgument);
	}

	@Override
	public byte[] getDel(final byte[] key){
		return stringOperations.getDel(key);
	}

	@Override
	public Long incr(final byte[] key){
		return stringOperations.incr(key);
	}

	@Override
	public Long incrBy(final byte[] key, final int value){
		return stringOperations.incrBy(key, value);
	}

	@Override
	public Long incrBy(final byte[] key, final long value){
		return stringOperations.incrBy(key, value);
	}

	@Override
	public Double incrByFloat(final byte[] key, final float value){
		return stringOperations.incrByFloat(key, value);
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value){
		return stringOperations.incrByFloat(key, value);
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys){
		return stringOperations.mGet(keys);
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		return stringOperations.pSetEx(key, value, lifetime);
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		return stringOperations.set(key, value);
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument){
		return stringOperations.set(key, value, setArgument);
	}

	@Override
	public Status setBit(final byte[] key, final int offset, final byte[] value){
		return stringOperations.setBit(key, offset, value);
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final byte[] value){
		return stringOperations.setBit(key, offset, value);
	}

	@Override
	public Status setBit(final byte[] key, final int offset, final boolean value){
		return stringOperations.setBit(key, offset, value);
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final boolean value){
		return stringOperations.setBit(key, offset, value);
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		return stringOperations.setEx(key, value, lifetime);
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		return stringOperations.setNx(key, value);
	}

	@Override
	public Long setRange(final byte[] key, final int offset, final byte[] value){
		return stringOperations.setRange(key, offset, value);
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value){
		return stringOperations.setRange(key, offset, value);
	}

	@Override
	public Long strlen(final byte[] key){
		return stringOperations.strlen(key);
	}

	@Override
	public byte[] substr(final byte[] key, final int start, final int end){
		return stringOperations.substr(key, start, end);
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end){
		return stringOperations.substr(key, start, end);
	}

	@Override
	public Status watch(final byte[]... keys){
		return transactionOperations.watch(keys);
	}

	@Override
	protected JedisSentinelConnectionOperations createConnectionOperations(){
		return new JedisSentinelConnectionOperations(this);
	}

	@Override
	protected JedisSentinelGeoOperations createGeoOperations(){
		return new JedisSentinelGeoOperations(this);
	}

	@Override
	protected JedisSentinelHashOperations createHashOperations(){
		return new JedisSentinelHashOperations(this);
	}

	@Override
	protected JedisSentinelHyperLogLogOperations createHyperLogLogOperations(){
		return new JedisSentinelHyperLogLogOperations(this);
	}

	@Override
	protected JedisSentinelKeyOperations createKeyOperations(){
		return new JedisSentinelKeyOperations(this);
	}

	@Override
	protected JedisSentinelListOperations createListOperations(){
		return new JedisSentinelListOperations(this);
	}

	@Override
	protected JedisSentinelPubSubOperations createPubSubOperations(){
		return new JedisSentinelPubSubOperations(this);
	}

	@Override
	protected JedisSentinelScriptingOperations createScriptingOperations(){
		return new JedisSentinelScriptingOperations(this);
	}

	@Override
	protected JedisSentinelServerOperations createServerOperations(){
		return new JedisSentinelServerOperations(this);
	}

	@Override
	protected JedisSentinelSetOperations createSetOperations(){
		return new JedisSentinelSetOperations(this);
	}

	@Override
	protected JedisSentinelSortedSetOperations createSortedSetOperations(){
		return new JedisSentinelSortedSetOperations(this);
	}

	@Override
	protected JedisSentinelStringOperations createStringOperations(){
		return new JedisSentinelStringOperations(this);
	}

	@Override
	protected JedisSentinelTransactionOperations createTransactionOperations(){
		return new JedisSentinelTransactionOperations(this);
	}

	@Override
	protected JedisSentinelClusterOperations createClusterOperations(){
		return new JedisSentinelClusterOperations(this);
	}

}