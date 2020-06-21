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
import com.buession.core.converter.SetConverter;
import com.buession.lang.Geo;
import com.buession.lang.Status;
import com.buession.redis.client.AbstractRedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.jedis.operations.JedisExecutor;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.FutureResult;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.Info;
import com.buession.redis.core.InfoSection;
import com.buession.redis.core.ObjectCommand;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.SlowLogCommand;
import com.buession.redis.core.jedis.JedisResult;
import com.buession.redis.core.jedis.JedisScanParams;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.JedisConverters;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.jedis.JedisPipeline;
import com.buession.redis.transaction.jedis.JedisTransaction;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import org.springframework.core.convert.converter.Converter;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.BitPosParams;
import redis.clients.jedis.Response;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.params.SetParams;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisClient<C extends JedisCommands> extends AbstractRedisClient implements JedisRedisClient<C> {

	protected final static Converter<Aggregate, ZParams.Aggregate> AGGREGATE_JEDIS_CONVERTER =
			JedisConverters.aggregateJedisConverter();

	protected final static Converter<BitOperation, BitOP> BIT_OPERATION_JEDIS_CONVERTER =
			JedisConverters.bitOperationJedisConverter();

	protected final static Converter<redis.clients.jedis.Tuple, Tuple> TUPLE_EXPOSE_CONVERTER =
			JedisConverters.tupleExposeConverter();

	protected final static SetConverter<redis.clients.jedis.Tuple, Tuple> SET_TUPLE_EXPOSE_CONVERTER =
			JedisConverters.setTupleExposeConverter();

	protected final static Converter<SetArgument, SetParams> SET_ARGUMENT_JEDIS_CONVERTER =
			JedisConverters.setArgumentJedisConverter();

	protected final static Converter<redis.clients.jedis.ScanResult<String>, ScanResult<List<String>>> STRING_LIST_SCANRESULT_EXPOSE_CONVERTER = JedisConverters.listScanResultExposeConverter();

	protected final static Converter<redis.clients.jedis.ScanResult<byte[]>, ScanResult<List<byte[]>>> BINARY_LIST_SCANRESULT_EXPOSE_CONVERTER = JedisConverters.listScanResultExposeConverter();

	protected final static Converter<redis.clients.jedis.ScanResult<redis.clients.jedis.Tuple>,
			ScanResult<List<Tuple>>> LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER =
			JedisConverters.listTupleScanResultExposeConverter();

	protected Queue<FutureResult<Response<?>>> txResults = new LinkedList<>();

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
			final double radius, final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
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
	public Info info(final InfoSection section){
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
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sadd(key, members)));
		}else{
			return execute((cmd)->cmd.sadd(key, members));
		}
	}

	@Override
	public Long sCard(final String key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().scard(key)));
		}else{
			return execute((cmd)->cmd.scard(key));
		}
	}

	@Override
	public boolean sisMember(final String key, final String member){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().sismember(key, member)));
		}else{
			return execute((cmd)->cmd.sismember(key, member));
		}
	}

	@Override
	public Set<String> sMembers(final String key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().smembers(key)));
		}else{
			return execute((cmd)->cmd.smembers(key));
		}
	}

	@Override
	public String sPop(final String key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().spop(key)));
		}else{
			return execute((cmd)->cmd.spop(key));
		}
	}

	@Override
	public String sRandMember(final String key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srandmember(key)));
		}else{
			return execute((cmd)->cmd.srandmember(key));
		}
	}

	@Override
	public List<String> sRandMember(final String key, final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srandmember(key, count)));
		}else{
			return execute((cmd)->cmd.srandmember(key, count));
		}
	}

	@Override
	public Long sRem(final String key, final String... members){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().srem(key, members)));
		}else{
			return execute((cmd)->cmd.srem(key, members));
		}
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->STRING_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor)));
		}
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->STRING_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor,
					new JedisScanParams(pattern))));
		}
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->STRING_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor,
					new JedisScanParams(count))));
		}
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
			final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SSCAN);
		}else{
			return execute((cmd)->STRING_LIST_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.sscan(key, cursor,
					new JedisScanParams(pattern, count))));
		}
	}

	@Override
	public Long zAdd(final String key, final Map<String, Number> members){
		final Map<String, Double> data = new LinkedHashMap<>(members.size());

		members.forEach((k, v)->data.put(k, v.doubleValue()));

		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zadd(key, data)));
		}else{
			return execute((cmd)->cmd.zadd(key, data));
		}
	}

	@Override
	public Long zCard(final String key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zcard(key)));
		}else{
			return execute((C cmd)->cmd.zcard(key));
		}
	}

	@Override
	public Long zCount(final String key, final double min, final double max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zcount(key, min, max)));
		}else{
			return execute((cmd)->cmd.zcount(key, min, max));
		}
	}

	@Override
	public Long zCount(final String key, final String min, final String max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zcount(key, min, max)));
		}else{
			return execute((cmd)->cmd.zcount(key, min, max));
		}
	}

	@Override
	public Double zIncrBy(final String key, final String member, final double increment){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zincrby(key, increment, member)));
		}else{
			return execute((cmd)->cmd.zincrby(key, increment, member));
		}
	}

	@Override
	public Long zLexCount(final String key, final String min, final String max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zlexcount(key, min, max)));
		}else{
			return execute((cmd)->cmd.zlexcount(key, min, max));
		}
	}

	@Override
	public Set<String> zRange(final String key, final long start, final long end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrange(key, start, end)));
		}else{
			return execute((cmd)->cmd.zrange(key, start, end));
		}
	}

	@Override
	public Set<Tuple> zRangeWithScores(final String key, final long start, final long end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeWithScores(key, start, end),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeWithScores(key, start, end)));
		}
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByLex(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrangeByLex(key, min, max));
		}
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max, final int offset,
			final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByLex(key, min, max, offset,
					count)));
		}else{
			return execute((cmd)->cmd.zrangeByLex(key, min, max, offset, count));
		}
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max, final int offset,
			final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScore(key, min, max, offset,
					count)));
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max, final int offset,
			final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScore(key, min, max, offset,
					count)));
		}else{
			return execute((cmd)->cmd.zrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScoreWithScores(key, min, max),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrangeByScoreWithScores(key, min, max),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final int offset,
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
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final int offset,
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
	public Long zRank(final String key, final String member){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrank(key, member)));
		}else{
			return execute((cmd)->cmd.zrank(key, member));
		}
	}

	@Override
	public Tuple zPopMax(final String key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zpopmax(key), TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->TUPLE_EXPOSE_CONVERTER.convert(cmd.zpopmax(key)));
		}
	}

	@Override
	public Set<Tuple> zPopMax(final String key, final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zpopmax(key, count),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zpopmax(key, count)));
		}
	}

	@Override
	public Tuple zPopMin(final String key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zpopmin(key), TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->TUPLE_EXPOSE_CONVERTER.convert(cmd.zpopmin(key)));
		}
	}

	@Override
	public Set<Tuple> zPopMin(final String key, final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zpopmin(key, count),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zpopmin(key, count)));
		}
	}

	@Override
	public Long zRem(final String key, final String... members){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrem(key, members)));
		}else{
			return execute((cmd)->cmd.zrem(key, members));
		}
	}

	@Override
	public Long zRemRangeByLex(final String key, final String min, final String max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zremrangeByLex(key, min, max)));
		}else{
			return execute((cmd)->cmd.zremrangeByLex(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zremrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zremrangeByScore(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByScore(final String key, final String min, final String max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zremrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zremrangeByScore(key, min, max));
		}
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zremrangeByRank(key, start, end)));
		}else{
			return execute((cmd)->cmd.zremrangeByRank(key, start, end));
		}
	}

	@Override
	public Set<String> zRevRange(final String key, final long start, final long end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrange(key, start, end)));
		}else{
			return execute((cmd)->cmd.zrevrange(key, start, end));
		}
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final String key, final long start, final long end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeWithScores(key, start, end),
					SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeWithScores(key, start, end)));
		}
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByLex(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrevrangeByLex(key, min, max));
		}
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max, final int offset,
			final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByLex(key, min, max, offset,
					count)));
		}else{
			return execute((cmd)->cmd.zrevrangeByLex(key, min, max, offset, count));
		}
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScore(key, min, max)));
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max));
		}
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max, final int offset,
			final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScore(key, min, max, offset,
					count)));
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max, final int offset,
			final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScore(key, min, max, offset,
					count)));
		}else{
			return execute((cmd)->cmd.zrevrangeByScore(key, min, max, offset, count));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScoreWithScores(key, min, max)
					, SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScoreWithScores(key, min, max)
					, SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeByScoreWithScores(key, min, max)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
			final int offset, final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScoreWithScores(key, min, max,
					offset, count), SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeByScoreWithScores(key, min, max,
					offset, count)));
		}
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max,
			final int offset, final int count){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrangeByScoreWithScores(key, min, max,
					offset, count), SET_TUPLE_EXPOSE_CONVERTER));
		}else{
			return execute((cmd)->SET_TUPLE_EXPOSE_CONVERTER.convert(cmd.zrevrangeByScoreWithScores(key, min, max,
					offset, count)));
		}
	}

	@Override
	public Long zRevRank(final String key, final String member){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zrevrank(key, member)));
		}else{
			return execute((cmd)->cmd.zrevrank(key, member));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.zscan(key, cursor)));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.zscan(key, cursor,
					new JedisScanParams(pattern))));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.zscan(key, cursor,
					new JedisScanParams(count))));
		}
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final int count){
		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.ZSCAN);
		}else{
			return execute((cmd)->LIST_TUPLE_SCANRESULT_EXPOSE_CONVERTER.convert(cmd.zscan(key, cursor,
					new JedisScanParams(pattern, count))));
		}
	}

	@Override
	public Double zScore(final String key, final String member){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().zscore(key, member)));
		}else{
			return execute((cmd)->cmd.zscore(key, member));
		}
	}

	@Override
	public Long append(final String key, final String value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().append(key, value)));
		}else{
			return execute((cmd)->cmd.append(key, value));
		}
	}

	@Override
	public Long bitCount(final String key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitcount(key)));
		}else{
			return execute((cmd)->cmd.bitcount(key));
		}
	}

	@Override
	public Long bitCount(final String key, final long start, final long end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitcount(key, start, end)));
		}else{
			return execute((cmd)->cmd.bitcount(key, start, end));
		}
	}

	@Override
	public List<Long> bitField(final String key, final String... arguments){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitfield(key, arguments)));
		}else{
			return execute((cmd)->cmd.bitfield(key, arguments));
		}
	}

	@Override
	public Long bitPos(final String key, final boolean value){
		if(isTransaction()){
			return execute((cmd)->getTransaction().bitpos(key, value).get());
		}else{
			return execute((cmd)->cmd.bitpos(key, value));
		}
	}

	@Override
	public Long bitPos(final String key, final boolean value, final int start, final int end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().bitpos(key, value, new BitPosParams(start
					, end))));
		}else{
			return execute((cmd)->cmd.bitpos(key, value, new BitPosParams(start, end)));
		}
	}

	@Override
	public Long decr(final String key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().decr(key)));
		}else{
			return execute((cmd)->cmd.decr(key));
		}
	}

	@Override
	public Long decrBy(final String key, final long value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().decrBy(key, value)));
		}else{
			return execute((cmd)->cmd.decrBy(key, value));
		}
	}

	@Override
	public String get(final String key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().get(key)));
		}else{
			return execute((cmd)->cmd.get(key));
		}
	}

	@Override
	public Status getBit(final String key, final long offset){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().getbit(key, offset),
					JedisConverters.booleanToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.getbit(key, offset)));
		}
	}

	@Override
	public String getRange(final String key, final long start, final long end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().getrange(key, start, end)));
		}else{
			return execute((cmd)->cmd.getrange(key, start, end));
		}
	}

	@Override
	public String getSet(final String key, final String value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().getSet(key, value)));
		}else{
			return execute((cmd)->cmd.getSet(key, value));
		}
	}

	@Override
	public Long incr(final String key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().incr(key)));
		}else{
			return execute((cmd)->cmd.incr(key));
		}
	}

	@Override
	public Long incrBy(final String key, final long value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().incrBy(key, value)));
		}else{
			return execute((cmd)->cmd.incrBy(key, value));
		}
	}

	@Override
	public Double incrByFloat(final String key, final double value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().incrByFloat(key, value)));
		}else{
			return execute((cmd)->cmd.incrByFloat(key, value));
		}
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().psetex(key, lifetime, value),
					JedisConverters.okToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.psetex(key, lifetime, value)));
		}
	}

	@Override
	public Status set(final String key, final String value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().set(key, value),
					JedisConverters.okToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.set(key, value)));
		}
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument){
		final SetParams setParams = SET_ARGUMENT_JEDIS_CONVERTER.convert(setArgument);

		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().set(key, value, setParams),
					JedisConverters.okToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.set(key, value, setParams)));
		}
	}

	@Override
	public Status setBit(final String key, final long offset, final String value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setbit(SafeEncoder.encode(key), offset,
					SafeEncoder.encode(value)), JedisConverters.booleanToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setbit(key, offset, value)));
		}
	}

	@Override
	public Status setBit(final String key, final long offset, final boolean value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setbit(key, offset, value),
					JedisConverters.booleanToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setbit(key, offset, value)));
		}
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime){

		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setex(key, lifetime, value),
					JedisConverters.okToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.setex(key, lifetime, value)));
		}
	}

	@Override
	public Status setNx(final String key, final String value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setnx(key, value),
					JedisConverters.positiveLongNumberToStatusConverter()));
		}else{
			return execute((cmd)->ReturnUtils.statusForBool(cmd.setnx(key, value) > 0));
		}
	}

	@Override
	public Long setRange(final String key, final long offset, final String value){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().setrange(key, offset, value)));
		}else{
			return execute((cmd)->cmd.setrange(key, offset, value));
		}
	}

	@Override
	public Long strlen(final String key){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().strlen(key)));
		}else{
			return execute((cmd)->cmd.strlen(key));
		}
	}

	@Override
	public String substr(final String key, final int start, final int end){
		if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().substr(key, start, end)));
		}else{
			return execute((cmd)->cmd.substr(key, start, end));
		}
	}

	protected redis.clients.jedis.Transaction getTransaction(){
		JedisTransaction jedisTransaction = (JedisTransaction) getConnection().getTransaction();
		return jedisTransaction.primitive();
	}

	protected redis.clients.jedis.PipelineBase getPipeline(){
		JedisPipeline jedisPipeline = (JedisPipeline) getConnection().getPipeline();
		return jedisPipeline.primitive();
	}

	@Override
	public <R> R execute(final JedisExecutor<C, R> executor) throws RedisException{
		return super.execute(executor);
	}

	protected <R> R transactionExecute(final Executor<C, JedisResult> executor) throws RedisException{
		txResults.add(super.execute(executor));
		return null;
	}

	protected <R> R pipelineExecute(final Executor<C, JedisResult> executor) throws RedisException{
		txResults.add(super.execute(executor));
		return null;
	}

	protected JedisResult newJedisResult(final Response<?> response){
		return JedisResult.JedisResultBuilder.forResponse(response).build();
	}

	protected <T, R> JedisResult newJedisResult(final Response<T> response, final Converter<T, R> converter){
		return JedisResult.JedisResultBuilder.<T, R>forResponse(response).mappedWith(converter).build();
	}

}