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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis;

import com.buession.lang.Geo;
import com.buession.lang.Status;
import com.buession.redis.client.AbstractRedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.FutureResult;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.Info;
import com.buession.redis.core.ObjectCommand;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.SlowLogCommand;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;
import com.buession.redis.pipeline.jedis.JedisPipeline;
import com.buession.redis.transaction.Transaction;
import com.buession.redis.transaction.jedis.JedisTransaction;
import redis.clients.jedis.Response;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisClient extends AbstractRedisClient implements JedisRedisClient {

	private Queue<FutureResult<Response<Object>, Object, Object>> txResults = new LinkedList<>();

	public AbstractJedisRedisClient(){
		super();
	}

	public AbstractJedisRedisClient(RedisConnection connection){
		super(connection);
	}

	@Override
	public Status auth(final String password){
		return connectionOperations.auth(password);
	}

	@Override
	public String echo(final String str){
		return connectionOperations.echo(str);
	}

	@Override
	public Status ping(){
		return connectionOperations.ping();
	}

	@Override
	public Status quit(){
		return connectionOperations.quit();
	}

	@Override
	public Status select(final int db){
		return connectionOperations.select(db);
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		return connectionOperations.swapdb(db1, db2);
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude){
		return geoOperations.geoAdd(key, member, longitude, latitude);
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates){
		return geoOperations.geoAdd(key, memberCoordinates);
	}

	@Override
	public List<String> geoHash(final String key, final String... members){
		return geoOperations.geoHash(key, members);
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members){
		return geoOperations.geoPos(key, members);
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2){
		return geoOperations.geoDist(key, member1, member2);
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit){
		return geoOperations.geoDist(key, member1, member2, unit);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius){
		return geoOperations.geoRadius(key, longitude, longitude, radius);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoRadiusArgument geoRadiusArgument){
		return geoOperations.geoRadius(key, longitude, latitude, radius, geoRadiusArgument);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit){
		return geoOperations.geoRadius(key, longitude, latitude, radius, unit);
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument){
		return geoOperations.geoRadius(key, longitude, latitude, radius, unit, geoRadiusArgument);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius){
		return geoOperations.geoRadiusByMember(key, member, radius);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoRadiusArgument geoRadiusArgument){
		return geoOperations.geoRadiusByMember(key, member, radius, geoRadiusArgument);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit){
		return geoOperations.geoRadiusByMember(key, member, radius, unit);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		return geoOperations.geoRadiusByMember(key, member, radius, unit, geoRadiusArgument);
	}

	@Override
	public Long hDecrBy(final String key, final String field, final int value){
		return hashOperations.hDecrBy(key, field, value);
	}

	@Override
	public Long hDecrBy(final String key, final String field, final long value){
		return hashOperations.hDecrBy(key, field, value);
	}

	@Override
	public Long hDel(final String key, final String... fields){
		return hashOperations.hDel(key, fields);
	}

	@Override
	public boolean hExists(final String key, final String field){
		return hashOperations.hExists(key, field);
	}

	@Override
	public String hGet(final String key, final String field){
		return hashOperations.hGet(key, field);
	}

	@Override
	public Map<String, String> hGetAll(final String key){
		return hashOperations.hGetAll(key);
	}

	@Override
	public Long hIncrBy(final String key, final String field, final int value){
		return hashOperations.hIncrBy(key, field, value);
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value){
		return hashOperations.hIncrBy(key, field, value);
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final float value){
		return hashOperations.hIncrByFloat(key, field, value);
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value){
		return hashOperations.hIncrByFloat(key, field, value);
	}

	@Override
	public Set<String> hKeys(final String key){
		return hashOperations.hKeys(key);
	}

	@Override
	public Long hLen(final String key){
		return hashOperations.hLen(key);
	}

	@Override
	public List<String> hMGet(final String key, final String... fields){
		return hashOperations.hMGet(key, fields);
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data){
		return hashOperations.hMSet(key, data);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor){
		return hashOperations.hScan(key, cursor);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor){
		return hashOperations.hScan(key, cursor);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor){
		return hashOperations.hScan(key, cursor);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final String pattern){
		return hashOperations.hScan(key, cursor, pattern);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern){
		return hashOperations.hScan(key, cursor, pattern);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern){
		return hashOperations.hScan(key, cursor, pattern);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final int count){
		return hashOperations.hScan(key, cursor, count);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final int count){
		return hashOperations.hScan(key, cursor, count);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final int count){
		return hashOperations.hScan(key, cursor, count);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final String pattern,
												 final int count){
		return hashOperations.hScan(key, cursor, pattern, count);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern,
												 final int count){
		return hashOperations.hScan(key, cursor, pattern, count);
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
												 final int count){
		return hashOperations.hScan(key, cursor, pattern, count);
	}

	@Override
	public Status hSet(final String key, final String field, final String value){
		return hashOperations.hSet(key, field, value);
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value){
		return hashOperations.hSetNx(key, field, value);
	}

	@Override
	public Long hStrLen(final String key, final String field){
		return hashOperations.hStrLen(key, field);
	}

	@Override
	public List<String> hVals(final String key){
		return hashOperations.hVals(key);
	}

	@Override
	public Status pfAdd(final String key, final String... elements){
		return hyperLogLogOperations.pfAdd(key, elements);
	}

	@Override
	public Status pfMerge(final String destKey, final String... keys){
		return hyperLogLogOperations.pfMerge(destKey, keys);
	}

	@Override
	public Long pfCount(final String... keys){
		return hyperLogLogOperations.pfCount(keys);
	}

	@Override
	public byte[] dump(final String key){
		return keyOperations.dump(key);
	}

	@Override
	public boolean exists(final String key){
		return keyOperations.exists(key);
	}

	@Override
	public Status expire(final String key, final int lifetime){
		return keyOperations.expire(key, lifetime);
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp){
		return keyOperations.expireAt(key, unixTimestamp);
	}

	@Override
	public Status move(final String key, final int db){
		return keyOperations.move(key, db);
	}

	@Override
	public Set<String> keys(final String pattern){
		return keyOperations.keys(pattern);
	}

	@Override
	public Status persist(final String key){
		return keyOperations.persist(key);
	}

	@Override
	public Status pExpire(final String key, final int lifetime){
		return keyOperations.pExpire(key, lifetime);
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp){
		return keyOperations.pExpireAt(key, unixTimestamp);
	}

	@Override
	public Long pTtl(final String key){
		return keyOperations.pTtl(key);
	}

	@Override
	public String randomKey(){
		return keyOperations.randomKey();
	}

	@Override
	public Status rename(final String key, final String newKey){
		return keyOperations.rename(key, newKey);
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		return keyOperations.renameNx(key, newKey);
	}

	@Override
	public Status restore(final String key, final String serializedValue, final int ttl){
		return keyOperations.restore(key, serializedValue, ttl);
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor){
		return keyOperations.scan(cursor);
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor){
		return keyOperations.scan(cursor);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor){
		return keyOperations.scan(cursor);
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor, final String pattern){
		return keyOperations.scan(cursor, pattern);
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern){
		return keyOperations.scan(cursor, pattern);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern){
		return keyOperations.scan(cursor, pattern);
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor, final int count){
		return keyOperations.scan(cursor, count);
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final int count){
		return keyOperations.scan(cursor, count);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final int count){
		return keyOperations.scan(cursor, count);
	}

	@Override
	public ScanResult<List<String>> scan(final int cursor, final String pattern, final int count){
		return keyOperations.scan(cursor, pattern, count);
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern, final int count){
		return keyOperations.scan(cursor, pattern, count);
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count){
		return keyOperations.scan(cursor, pattern, count);
	}

	@Override
	public List<String> sort(final String key){
		return keyOperations.sort(key);
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument){
		return keyOperations.sort(key, sortArgument);
	}

	@Override
	public Long sort(final String key, final String destKey){
		return keyOperations.sort(key, destKey);
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument){
		return keyOperations.sort(key, destKey, sortArgument);
	}

	@Override
	public Long touch(final String... keys){
		return keyOperations.touch(keys);
	}

	@Override
	public Long ttl(final String key){
		return keyOperations.ttl(key);
	}

	@Override
	public Type type(final String key){
		return keyOperations.type(key);
	}

	@Override
	public Long unlink(final String... keys){
		return keyOperations.unlink(keys);
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout){
		return listOperations.blPop(keys, timeout);
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout){
		return listOperations.brPop(keys, timeout);
	}

	@Override
	public String brPoplPush(final String key, final String destKey, final int timeout){
		return listOperations.brPoplPush(key, destKey, timeout);
	}

	@Override
	public String lIndex(final String key, final int index){
		return listOperations.lIndex(key, index);
	}

	@Override
	public String lIndex(final String key, final long index){
		return listOperations.lIndex(key, index);
	}

	@Override
	public Long lInsert(final String key, final String value, final ListPosition position, final String pivot){
		return listOperations.lInsert(key, value, position, pivot);
	}

	@Override
	public Long lLen(final String key){
		return listOperations.lLen(key);
	}

	@Override
	public String lPop(final String key){
		return listOperations.lPop(key);
	}

	@Override
	public Long lPush(final String key, final String... values){
		return listOperations.lPush(key, values);
	}

	@Override
	public Long lPushX(final String key, final String... values){
		return listOperations.lPushX(key, values);
	}

	@Override
	public List<String> lRange(final String key, final int start, final int end){
		return listOperations.lRange(key, start, end);
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end){
		return listOperations.lRange(key, start, end);
	}

	@Override
	public Long lRem(final String key, final String value, final int count){
		return listOperations.lRem(key, value, count);
	}

	@Override
	public Long lRem(final String key, final String value, final long count){
		return listOperations.lRem(key, value, count);
	}

	@Override
	public Status lSet(final String key, final int index, final String value){
		return listOperations.lSet(key, index, value);
	}

	@Override
	public Status lSet(final String key, final long index, final String value){
		return listOperations.lSet(key, index, value);
	}

	@Override
	public Status lTrim(final String key, final int start, final int end){
		return listOperations.lTrim(key, start, end);
	}

	@Override
	public Status lTrim(final String key, final long start, final long end){
		return listOperations.lTrim(key, start, end);
	}

	@Override
	public String rPop(final String key){
		return listOperations.rPop(key);
	}

	@Override
	public String rPoplPush(final String key, final String destKey){
		return listOperations.rPoplPush(key, destKey);
	}

	@Override
	public Long rPush(final String key, final String... values){
		return listOperations.rPush(key, values);
	}

	@Override
	public Long rPushX(final String key, final String... values){
		return listOperations.rPushX(key, values);
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
		pubSubOperations.pSubscribe(patterns, pubSubListener);
	}

	@Override
	public List<String> pubsubChannels(){
		return pubSubOperations.pubsubChannels();
	}

	@Override
	public List<String> pubsubChannels(final String pattern){
		return pubSubOperations.pubsubChannels(pattern);
	}

	@Override
	public Long pubsubNumPat(){
		return pubSubOperations.pubsubNumPat();
	}

	@Override
	public Map<String, String> pubsubNumSub(final String... channels){
		return pubSubOperations.pubsubNumSub(channels);
	}

	@Override
	public Long publish(final String channel, final String message){
		return pubSubOperations.publish(channel, message);
	}

	@Override
	public Object pUnSubscribe(){
		return pubSubOperations.pUnSubscribe();
	}

	@Override
	public Object pUnSubscribe(final String... patterns){
		return pubSubOperations.pUnSubscribe(patterns);
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
		pubSubOperations.subscribe(channels, pubSubListener);
	}

	@Override
	public Object unSubscribe(){
		return pubSubOperations.unSubscribe();
	}

	@Override
	public Object unSubscribe(final String... channels){
		return pubSubOperations.unSubscribe(channels);
	}

	@Override
	public Object eval(final String script){
		return scriptingOperations.eval(script);
	}

	@Override
	public Object eval(final String script, final String... params){
		return scriptingOperations.eval(script, params);
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments){
		return scriptingOperations.eval(script, keys, arguments);
	}

	@Override
	public Object evalSha(final String digest){
		return scriptingOperations.evalSha(digest);
	}

	@Override
	public Object evalSha(final String digest, final String... params){
		return scriptingOperations.evalSha(digest, params);
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments){
		return scriptingOperations.evalSha(digest, keys, arguments);
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1){
		return scriptingOperations.scriptExists(sha1);
	}

	@Override
	public Status scriptFlush(){
		return scriptingOperations.scriptFlush();
	}

	@Override
	public Status scriptKill(){
		return scriptingOperations.scriptKill();
	}

	@Override
	public String scriptLoad(final String script){
		return scriptingOperations.scriptLoad(script);
	}

	@Override
	public String bgRewriteAof(){
		return serverOperations.bgRewriteAof();
	}

	@Override
	public String bgSave(){
		return serverOperations.bgSave();
	}

	@Override
	public Status clientKill(final String host, final int port){
		return serverOperations.clientKill(host, port);
	}

	@Override
	public String clientGetName(){
		return serverOperations.clientGetName();
	}

	@Override
	public String clientId(){
		return serverOperations.clientId();
	}

	@Override
	public List<Client> clientList(){
		return serverOperations.clientList();
	}

	@Override
	public Status clientPause(final int timeout){
		return serverOperations.clientPause(timeout);
	}

	@Override
	public Status clientPause(final long timeout){
		return serverOperations.clientPause(timeout);
	}

	@Override
	public Status clientReply(final ClientReply option){
		return serverOperations.clientReply(option);
	}

	@Override
	public Status clientSetName(final String name){
		return serverOperations.clientSetName(name);
	}

	@Override
	public Status clientUnblock(final int clientId){
		return serverOperations.clientUnblock(clientId);
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type){
		return serverOperations.clientUnblock(clientId, type);
	}

	@Override
	public List<String> configGet(final String parameter){
		return serverOperations.configGet(parameter);
	}

	@Override
	public Status configResetStat(){
		return serverOperations.configResetStat();
	}

	@Override
	public Status configRewrite(){
		return serverOperations.configRewrite();
	}

	@Override
	public Status configSet(final String parameter, final float value){
		return serverOperations.configSet(parameter, value);
	}

	@Override
	public Status configSet(final String parameter, final double value){
		return serverOperations.configSet(parameter, value);
	}

	@Override
	public Status configSet(final String parameter, final int value){
		return serverOperations.configSet(parameter, value);
	}

	@Override
	public Status configSet(final String parameter, final long value){
		return serverOperations.configSet(parameter, value);
	}

	@Override
	public Status configSet(final String parameter, final String value){
		return serverOperations.configSet(parameter, value);
	}

	@Override
	public Long dbSize(){
		return serverOperations.dbSize();
	}

	@Override
	public String debugObject(final String key){
		return serverOperations.debugObject(key);
	}

	@Override
	public String debugSegfault(){
		return serverOperations.debugSegfault();
	}

	@Override
	public Status flushAll(){
		return serverOperations.flushAll();
	}

	@Override
	public Status flushDb(){
		return serverOperations.flushDb();
	}

	@Override
	public Info info(final Info.Section section){
		return serverOperations.info(section);
	}

	@Override
	public Info info(){
		return serverOperations.info();
	}

	@Override
	public Long lastSave(){
		return serverOperations.lastSave();
	}

	@Override
	public String memoryDoctor(){
		return serverOperations.memoryDoctor();
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		serverOperations.monitor(redisMonitor);
	}

	@Override
	public Object object(final ObjectCommand command, final String key){
		return serverOperations.object(command, key);
	}

	@Override
	public Object pSync(final String masterRunId, final int offset){
		return serverOperations.pSync(masterRunId, offset);
	}

	@Override
	public Object pSync(final String masterRunId, final long offset){
		return serverOperations.pSync(masterRunId, offset);
	}

	@Override
	public Status replicaOf(final String host, final int port){
		return serverOperations.replicaOf(host, port);
	}

	@Override
	public Role role(){
		return serverOperations.role();
	}

	@Override
	public Status save(){
		return serverOperations.save();
	}

	@Override
	public void shutdown(){
		serverOperations.shutdown();
	}

	@Override
	public void shutdown(final boolean save){
		serverOperations.shutdown(save);
	}

	@Override
	public Status slaveOf(final String host, final int port){
		return serverOperations.slaveOf(host, port);
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final String... arguments){
		return serverOperations.slowLog(command, arguments);
	}

	@Override
	public Object sync(){
		return serverOperations.sync();
	}

	@Override
	public RedisServerTime time(){
		return serverOperations.time();
	}

	@Override
	public Long sAdd(final String key, final String... members){
		return setOperations.sAdd(key, members);
	}

	@Override
	public Long sCard(final String key){
		return setOperations.sCard(key);
	}

	@Override
	public Set<String> sDiff(final String... keys){
		return setOperations.sDiff(keys);
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys){
		return setOperations.sDiffStore(destKey, keys);
	}

	@Override
	public Set<String> sInter(final String... keys){
		return setOperations.sInter(keys);
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys){
		return setOperations.sInterStore(destKey, keys);
	}

	@Override
	public boolean sisMember(final String key, final String member){
		return setOperations.sisMember(key, member);
	}

	@Override
	public Set<String> sMembers(final String key){
		return setOperations.sMembers(key);
	}

	@Override
	public Status sMove(final String key, final String destKey, final String member){
		return setOperations.sMove(key, destKey, member);
	}

	@Override
	public String sPop(final String key){
		return setOperations.sPop(key);
	}

	@Override
	public String sRandMember(final String key){
		return setOperations.sRandMember(key);
	}

	@Override
	public List<String> sRandMember(final String key, final int count){
		return setOperations.sRandMember(key, count);
	}

	@Override
	public List<String> sRandMember(final String key, final long count){
		return setOperations.sRandMember(key, count);
	}

	@Override
	public Long sRem(final String key, final String... members){
		return setOperations.sRem(key, members);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor){
		return setOperations.sScan(key, cursor);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor){
		return setOperations.sScan(key, cursor);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor){
		return setOperations.sScan(key, cursor);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern){
		return setOperations.sScan(key, cursor, pattern);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern){
		return setOperations.sScan(key, cursor, pattern);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
		return setOperations.sScan(key, cursor, pattern);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final int count){
		return setOperations.sScan(key, cursor, count);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final int count){
		return setOperations.sScan(key, cursor, count);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final int count){
		return setOperations.sScan(key, cursor, count);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern, final int count){
		return setOperations.sScan(key, cursor, pattern, count);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern, final int count){
		return setOperations.sScan(key, cursor, pattern, count);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
										  final int count){
		return setOperations.sScan(key, cursor, pattern, count);
	}

	@Override
	public Set<String> sUnion(final String... keys){
		return setOperations.sUnion(keys);
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys){
		return setOperations.sUnionStore(destKey, keys);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Number> members){
		return sortedSetOperations.zAdd(key, members);
	}

	@Override
	public Long zCard(final String key){
		return sortedSetOperations.zCard(key);
	}

	@Override
	public Long zCount(final String key, final float min, final float max){
		return sortedSetOperations.zCount(key, min, max);
	}

	@Override
	public Long zCount(final String key, final double min, final double max){
		return sortedSetOperations.zCount(key, min, max);
	}

	@Override
	public Long zCount(final String key, final int min, final int max){
		return sortedSetOperations.zCount(key, min, max);
	}

	@Override
	public Long zCount(final String key, final long min, final long max){
		return sortedSetOperations.zCount(key, min, max);
	}

	@Override
	public Long zCount(final String key, final String min, final String max){
		return sortedSetOperations.zCount(key, min, max);
	}

	@Override
	public Double zIncrBy(final String key, final String member, final float increment){
		return sortedSetOperations.zIncrBy(key, member, increment);
	}

	@Override
	public Double zIncrBy(final String key, final String member, final double increment){
		return sortedSetOperations.zIncrBy(key, member, increment);
	}

	@Override
	public Double zIncrBy(final String key, final String member, final int increment){
		return sortedSetOperations.zIncrBy(key, member, increment);
	}

	@Override
	public Double zIncrBy(final String key, final String member, final long increment){
		return sortedSetOperations.zIncrBy(key, member, increment);
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys){
		return sortedSetOperations.zInterStore(destKey, keys);
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final String... keys){
		return sortedSetOperations.zInterStore(destKey, aggregate, keys);
	}

	@Override
	public Long zInterStore(final String destKey, final double[] weights, final String... keys){
		return sortedSetOperations.zInterStore(destKey, weights, keys);
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final double[] weights,
							final String... keys){
		return sortedSetOperations.zInterStore(destKey, aggregate, weights, keys);
	}

	@Override
	public Long zLexCount(final String key, final float min, final float max){
		return sortedSetOperations.zLexCount(key, min, max);
	}

	@Override
	public Long zLexCount(final String key, final double min, final double max){
		return sortedSetOperations.zLexCount(key, min, max);
	}

	@Override
	public Long zLexCount(final String key, final int min, final int max){
		return sortedSetOperations.zLexCount(key, min, max);
	}

	@Override
	public Long zLexCount(final String key, final long min, final long max){
		return sortedSetOperations.zLexCount(key, min, max);
	}

	@Override
	public Long zLexCount(final String key, final String min, final String max){
		return sortedSetOperations.zLexCount(key, min, max);
	}

	@Override
	public Set<String> zRange(final String key, final int start, final int end){
		return sortedSetOperations.zRange(key, start, end);
	}

	@Override
	public Set<String> zRange(final String key, final long start, final long end){
		return sortedSetOperations.zRange(key, start, end);
	}

	@Override
	public Set<Tuple> zRangeWithScores(final String key, final int start, final int end){
		return sortedSetOperations.zRangeWithScores(key, start, end);
	}

	@Override
	public Set<Tuple> zRangeWithScores(final String key, final long start, final long end){
		return sortedSetOperations.zRangeWithScores(key, start, end);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final float min, final float max){
		return sortedSetOperations.zRangeByLex(key, min, max);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final double min, final double max){
		return sortedSetOperations.zRangeByLex(key, min, max);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final int min, final int max){
		return sortedSetOperations.zRangeByLex(key, min, max);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final long min, final long max){
		return sortedSetOperations.zRangeByLex(key, min, max);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max){
		return sortedSetOperations.zRangeByLex(key, min, max);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final float min, final float max, final int offset,
								   final int count){
		return zRangeByLex(key, Float.toString(min), Float.toString(max), offset, count);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final double min, final double max, final int offset,
								   final int count){
		return zRangeByLex(key, Double.toString(min), Double.toString(max), offset, count);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final int min, final int max, final int offset, final int count){
		return zRangeByLex(key, Integer.toString(min), Integer.toString(max), offset, count);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final long min, final long max, final int offset,
								   final int count){
		return zRangeByLex(key, Long.toString(min), Long.toString(max), offset, count);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max, final int offset,
								   final int count){
		return sortedSetOperations.zRangeByLex(key, min, max, offset, count);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final float min, final float max){
		return sortedSetOperations.zRangeByScore(key, min, max);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max){
		return sortedSetOperations.zRangeByScore(key, min, max);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final int min, final int max){
		return sortedSetOperations.zRangeByScore(key, min, max);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final long min, final long max){
		return sortedSetOperations.zRangeByScore(key, min, max);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max){
		return sortedSetOperations.zRangeByScore(key, min, max);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final float min, final float max, final int offset,
									 final int count){
		return sortedSetOperations.zRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max, final int offset,
									 final int count){
		return sortedSetOperations.zRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final int min, final int max, final int offset,
									 final int count){
		return sortedSetOperations.zRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final long min, final long max, final int offset,
									 final int count){
		return sortedSetOperations.zRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max, final int offset,
									 final int count){
		return sortedSetOperations.zRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max){
		return sortedSetOperations.zRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max){
		return sortedSetOperations.zRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max){
		return sortedSetOperations.zRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max){
		return sortedSetOperations.zRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max){
		return sortedSetOperations.zRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max, final int offset,
											  final int count){
		return sortedSetOperations.zRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final int offset,
											  final int count){
		return sortedSetOperations.zRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max, final int offset,
											  final int count){
		return sortedSetOperations.zRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max, final int offset,
											  final int count){
		return sortedSetOperations.zRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final int offset,
											  final int count){
		return sortedSetOperations.zRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Long zRank(final String key, final String member){
		return sortedSetOperations.zRank(key, member);
	}

	@Override
	public Tuple zPopMax(final String key){
		return sortedSetOperations.zPopMax(key);
	}

	@Override
	public Set<Tuple> zPopMax(final String key, final int count){
		return sortedSetOperations.zPopMax(key, count);
	}

	@Override
	public Set<Tuple> zPopMax(final String key, final long count){
		return sortedSetOperations.zPopMax(key, count);
	}

	@Override
	public Tuple zPopMin(final String key){
		return sortedSetOperations.zPopMin(key);
	}

	@Override
	public Set<Tuple> zPopMin(final String key, final int count){
		return sortedSetOperations.zPopMin(key, count);
	}

	@Override
	public Set<Tuple> zPopMin(final String key, final long count){
		return sortedSetOperations.zPopMin(key, count);
	}

	@Override
	public Long zRem(final String key, final String... members){
		return sortedSetOperations.zRem(key, members);
	}

	@Override
	public Long zRemRangeByLex(final String key, final float min, final float max){
		return sortedSetOperations.zRemRangeByLex(key, min, max);
	}

	@Override
	public Long zRemRangeByLex(final String key, final double min, final double max){
		return sortedSetOperations.zRemRangeByLex(key, min, max);
	}

	@Override
	public Long zRemRangeByLex(final String key, final int min, final int max){
		return sortedSetOperations.zRemRangeByLex(key, min, max);
	}

	@Override
	public Long zRemRangeByLex(final String key, final long min, final long max){
		return sortedSetOperations.zRemRangeByLex(key, min, max);
	}

	@Override
	public Long zRemRangeByLex(final String key, final String min, final String max){
		return sortedSetOperations.zRemRangeByLex(key, min, max);
	}

	@Override
	public Long zRemRangeByScore(final String key, final float min, final float max){
		return sortedSetOperations.zRemRangeByScore(key, min, max);
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max){
		return sortedSetOperations.zRemRangeByScore(key, min, max);
	}

	@Override
	public Long zRemRangeByScore(final String key, final int min, final int max){
		return sortedSetOperations.zRemRangeByScore(key, min, max);
	}

	@Override
	public Long zRemRangeByScore(final String key, final long min, final long max){
		return sortedSetOperations.zRemRangeByScore(key, min, max);
	}

	@Override
	public Long zRemRangeByScore(final String key, final String min, final String max){
		return sortedSetOperations.zRemRangeByScore(key, min, max);
	}

	@Override
	public Long zRemRangeByRank(final String key, final int start, final int end){
		return sortedSetOperations.zRemRangeByRank(key, start, end);
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end){
		return sortedSetOperations.zRemRangeByRank(key, start, end);
	}

	@Override
	public Set<String> zRevRange(final String key, final int start, final int end){
		return sortedSetOperations.zRevRange(key, start, end);
	}

	@Override
	public Set<String> zRevRange(final String key, final long start, final long end){
		return sortedSetOperations.zRevRange(key, start, end);
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final String key, final int start, final int end){
		return sortedSetOperations.zRevRangeWithScores(key, start, end);
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final String key, final long start, final long end){
		return sortedSetOperations.zRevRangeWithScores(key, start, end);
	}

	@Override
	public Set<String> zRevRangeByLex(String key, float min, float max){
		return sortedSetOperations.zRevRangeByLex(key, min, max);
	}

	@Override
	public Set<String> zRevRangeByLex(String key, double min, double max){
		return sortedSetOperations.zRevRangeByLex(key, min, max);
	}

	@Override
	public Set<String> zRevRangeByLex(String key, int min, int max){
		return sortedSetOperations.zRevRangeByLex(key, min, max);
	}

	@Override
	public Set<String> zRevRangeByLex(String key, long min, long max){
		return sortedSetOperations.zRevRangeByLex(key, min, max);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max){
		return sortedSetOperations.zRevRangeByLex(key, min, max);
	}

	@Override
	public Set<String> zRevRangeByLex(String key, float min, float max, int offset, int count){
		return sortedSetOperations.zRevRangeByLex(key, min, max, offset, count);
	}

	@Override
	public Set<String> zRevRangeByLex(String key, double min, double max, int offset, int count){
		return sortedSetOperations.zRevRangeByLex(key, min, max, offset, count);
	}

	@Override
	public Set<String> zRevRangeByLex(String key, int min, int max, int offset, int count){
		return sortedSetOperations.zRevRangeByLex(key, min, max, offset, count);
	}

	@Override
	public Set<String> zRevRangeByLex(String key, long min, long max, int offset, int count){
		return sortedSetOperations.zRevRangeByLex(key, min, max, offset, count);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max, final int offset,
									  final int count){
		return sortedSetOperations.zRevRangeByLex(key, min, max, offset, count);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final float min, final float max){
		return sortedSetOperations.zRevRangeByScore(key, min, max);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max){
		return sortedSetOperations.zRevRangeByScore(key, min, max);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final int min, final int max){
		return sortedSetOperations.zRevRangeByScore(key, min, max);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final long min, final long max){
		return sortedSetOperations.zRevRangeByScore(key, min, max);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max){
		return sortedSetOperations.zRevRangeByScore(key, min, max);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final float min, final float max, final int offset,
										final int count){
		return sortedSetOperations.zRevRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max, final int offset,
										final int count){
		return sortedSetOperations.zRevRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final int min, final int max, final int offset,
										final int count){
		return sortedSetOperations.zRevRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final long min, final long max, final int offset,
										final int count){
		return sortedSetOperations.zRevRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max, final int offset,
										final int count){
		return sortedSetOperations.zRevRangeByScore(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final float min, final float max){
		return sortedSetOperations.zRevRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max){
		return sortedSetOperations.zRevRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max){
		return sortedSetOperations.zRevRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max){
		return sortedSetOperations.zRevRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max){
		return sortedSetOperations.zRevRangeByScoreWithScores(key, min, max);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final float min, final float max, final int offset,
												 final int count){
		return sortedSetOperations.zRevRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
												 final int offset, final int count){
		return sortedSetOperations.zRevRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max, final int offset,
												 final int count){
		return sortedSetOperations.zRevRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max, final int offset,
												 final int count){
		return sortedSetOperations.zRevRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max,
												 final int offset, final int count){
		return sortedSetOperations.zRevRangeByScoreWithScores(key, min, max, offset, count);
	}

	@Override
	public Long zRevRank(final String key, final String member){
		return sortedSetOperations.zRevRank(key, member);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor){
		return sortedSetOperations.zScan(key, cursor);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor){
		return sortedSetOperations.zScan(key, cursor);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor){
		return sortedSetOperations.zScan(key, cursor);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern){
		return sortedSetOperations.zScan(key, cursor, pattern);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern){
		return sortedSetOperations.zScan(key, cursor, pattern);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern){
		return sortedSetOperations.zScan(key, cursor, pattern);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final int count){
		return sortedSetOperations.zScan(key, cursor, count);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final int count){
		return sortedSetOperations.zScan(key, cursor, count);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final int count){
		return sortedSetOperations.zScan(key, cursor, count);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern, final int count){
		return sortedSetOperations.zScan(key, cursor, pattern, count);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final int count){
		return sortedSetOperations.zScan(key, cursor, pattern, count);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final int count){
		return sortedSetOperations.zScan(key, cursor, pattern, count);
	}

	@Override
	public Double zScore(final String key, final String member){
		return sortedSetOperations.zScore(key, member);
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys){
		return sortedSetOperations.zUnionStore(destKey, keys);
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final String... keys){
		return sortedSetOperations.zUnionStore(destKey, aggregate, keys);
	}

	@Override
	public Long zUnionStore(final String destKey, final double[] weights, final String... keys){
		return sortedSetOperations.zUnionStore(destKey, weights, keys);
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final double[] weights,
							final String... keys){
		return sortedSetOperations.zUnionStore(destKey, aggregate, weights, keys);
	}

	@Override
	public Long append(final String key, final String value){
		return stringOperations.append(key, value);
	}

	@Override
	public Long bitCount(final String key){
		return stringOperations.bitCount(key);
	}

	@Override
	public Long bitCount(final String key, final int start, final int end){
		return stringOperations.bitCount(key, start, end);
	}

	@Override
	public Long bitCount(final String key, final long start, final long end){
		return stringOperations.bitCount(key, start, end);
	}

	@Override
	public List<Long> bitField(final String key, final String... arguments){
		return stringOperations.bitField(key, arguments);
	}

	@Override
	public Long bitOp(final BitOperation operation, final String destKey, final String... keys){
		return stringOperations.bitOp(operation, destKey, keys);
	}

	@Override
	public Long bitPos(final String key, final boolean value){
		return stringOperations.bitPos(key, value);
	}

	@Override
	public Long bitPos(final String key, final boolean value, final int start, final int end){
		return stringOperations.bitPos(key, value, start, end);
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end){
		return stringOperations.bitPos(key, value, start, end);
	}

	@Override
	public Long decr(final String key){
		return stringOperations.decr(key);
	}

	@Override
	public Long decrBy(final String key, final int value){
		return stringOperations.decrBy(key, value);
	}

	@Override
	public Long decrBy(final String key, final long value){
		return stringOperations.decrBy(key, value);
	}

	@Override
	public String get(final String key){
		return stringOperations.get(key);
	}

	@Override
	public Status getBit(final String key, final int offset){
		return stringOperations.getBit(key, offset);
	}

	@Override
	public Status getBit(final String key, final long offset){
		return stringOperations.getBit(key, offset);
	}

	@Override
	public String getRange(final String key, final int start, final int end){
		return stringOperations.getRange(key, start, end);
	}

	@Override
	public String getRange(final String key, final long start, final long end){
		return stringOperations.getRange(key, start, end);
	}

	@Override
	public String getSet(final String key, final String value){
		return stringOperations.getSet(key, value);
	}

	@Override
	public Long incr(final String key){
		return stringOperations.incr(key);
	}

	@Override
	public Long incrBy(final String key, final int value){
		return stringOperations.incrBy(key, value);
	}

	@Override
	public Long incrBy(final String key, final long value){
		return stringOperations.incrBy(key, value);
	}

	@Override
	public Double incrByFloat(final String key, final float value){
		return stringOperations.incrByFloat(key, value);
	}

	@Override
	public Double incrByFloat(final String key, final double value){
		return stringOperations.incrByFloat(key, value);
	}

	@Override
	public List<String> mGet(final String... keys){
		return stringOperations.mGet(keys);
	}

	@Override
	public Status mSet(final Map<String, String> values){
		return stringOperations.mSet(values);
	}

	@Override
	public Status mSetNx(final Map<String, String> values){
		return stringOperations.mSetNx(values);
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime){
		return stringOperations.pSetEx(key, value, lifetime);
	}

	@Override
	public Status set(final String key, final String value){
		return stringOperations.set(key, value);
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument){
		return stringOperations.set(key, value, setArgument);
	}

	@Override
	public Status setBit(final String key, final int offset, final String value){
		return stringOperations.setBit(key, offset, value);
	}

	@Override
	public Status setBit(final String key, final long offset, final String value){
		return stringOperations.setBit(key, offset, value);
	}

	@Override
	public Status setBit(final String key, final int offset, final boolean value){
		return stringOperations.setBit(key, offset, value);
	}

	@Override
	public Status setBit(final String key, final long offset, final boolean value){
		return stringOperations.setBit(key, offset, value);
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime){
		return stringOperations.setEx(key, value, lifetime);
	}

	@Override
	public Status setNx(final String key, final String value){
		return stringOperations.setNx(key, value);
	}

	@Override
	public Long setRange(final String key, final int offset, final String value){
		return stringOperations.setRange(key, offset, value);
	}

	@Override
	public Long setRange(final String key, final long offset, final String value){
		return stringOperations.setRange(key, offset, value);
	}

	@Override
	public Long strlen(final String key){
		return stringOperations.strlen(key);
	}

	@Override
	public String substr(final String key, final int start, final int end){
		return stringOperations.substr(key, start, end);
	}

	@Override
	public String substr(final String key, final long start, final long end){
		return stringOperations.substr(key, start, end);
	}

	@Override
	public void discard(){
		try{
			transactionOperations.discard();
		}catch(Exception e){
			throw e;
		}finally{
			txResults.clear();
		}
	}

	@Override
	public List<Object> exec(){
		try{
			return transactionOperations.exec();
		}catch(Exception e){
			throw e;
		}finally{
			txResults.clear();
		}
	}

	@Override
	public Transaction multi(){
		return transactionOperations.multi();
	}

	@Override
	public Status watch(final String... keys){
		return transactionOperations.watch(keys);
	}

	@Override
	public Status unwatch(){
		return transactionOperations.unwatch();
	}

	@Override
	public Queue<FutureResult<Response<Object>, Object, Object>> getTxResults(){
		return txResults;
	}

	protected redis.clients.jedis.Transaction getTransaction(){
		JedisTransaction jedisTransaction = (JedisTransaction) getConnection().getTransaction();
		return jedisTransaction.primitive();
	}

	protected redis.clients.jedis.PipelineBase getPipeline(){
		JedisPipeline jedisPipeline = (JedisPipeline) getConnection().getPipeline();
		return jedisPipeline.primitive();
	}

}