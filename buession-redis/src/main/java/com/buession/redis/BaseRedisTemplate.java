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
package com.buession.redis;

import com.buession.lang.Geo;
import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.BumpEpoch;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.ClusterFailoverOption;
import com.buession.redis.core.ClusterInfo;
import com.buession.redis.core.ClusterResetOption;
import com.buession.redis.core.ClusterSetSlotOption;
import com.buession.redis.core.Direction;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.Info;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.RedisClusterServer;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.SlowLogCommand;
import com.buession.redis.core.Tuple;
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
	public String clusterMyId(){
		return execute((client)->client.clusterMyId());
	}

	@Override
	public Status clusterAddSlots(final int... slots){
		return execute((client)->client.clusterAddSlots(slots));
	}

	@Override
	public Map<Integer, RedisClusterServer> clusterSlots(){
		return execute((client)->client.clusterSlots());
	}

	@Override
	public int clusterCountFailureReports(final String nodeId){
		return execute((client)->client.clusterCountFailureReports(nodeId));
	}

	@Override
	public int clusterCountFailureReports(final byte[] nodeId){
		return execute((client)->client.clusterCountFailureReports(nodeId));
	}

	@Override
	public int clusterCountKeysInSlot(final int slot){
		return execute((client)->client.clusterCountKeysInSlot(slot));
	}

	@Override
	public Status clusterDelSlots(final int... slots){
		return execute((client)->client.clusterDelSlots(slots));
	}

	@Override
	public Status clusterFlushSlots(){
		return execute((client)->client.clusterFlushSlots());
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption){
		return execute((client)->client.clusterFailover(clusterFailoverOption));
	}

	@Override
	public Status clusterForget(final String nodeId){
		return execute((client)->client.clusterForget(nodeId));
	}

	@Override
	public Status clusterForget(final byte[] nodeId){
		return execute((client)->client.clusterForget(nodeId));
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final long count){
		return execute((client)->client.clusterGetKeysInSlot(slot, count));
	}

	@Override
	public ClusterInfo clusterInfo(){
		return execute((client)->client.clusterInfo());
	}

	@Override
	public long clusterKeySlot(final String key){
		return execute((client)->client.clusterKeySlot(key));
	}

	@Override
	public long clusterKeySlot(final byte[] key){
		return execute((client)->client.clusterKeySlot(key));
	}

	@Override
	public Status clusterMeet(final String ip, final int port){
		return execute((client)->client.clusterMeet(ip, port));
	}

	@Override
	public Status clusterMeet(final byte[] ip, final int port){
		return execute((client)->client.clusterMeet(ip, port));
	}

	@Override
	public List<RedisClusterServer> clusterNodes(){
		return execute((client)->client.clusterNodes());
	}

	@Override
	public List<RedisClusterServer> clusterSlaves(final String nodeId){
		return execute((client)->client.clusterSlaves(nodeId));
	}

	@Override
	public List<RedisClusterServer> clusterSlaves(final byte[] nodeId){
		return execute((client)->client.clusterSlaves(nodeId));
	}

	@Override
	public List<RedisClusterServer> clusterReplicas(final String nodeId){
		return execute((client)->client.clusterReplicas(nodeId));
	}

	@Override
	public List<RedisClusterServer> clusterReplicas(final byte[] nodeId){
		return execute((client)->client.clusterReplicas(nodeId));
	}

	@Override
	public Status clusterReplicate(final String nodeId){
		return execute((client)->client.clusterReplicate(nodeId));
	}

	@Override
	public Status clusterReplicate(final byte[] nodeId){
		return execute((client)->client.clusterReplicate(nodeId));
	}

	@Override
	public Status clusterReset(){
		return execute((client)->client.clusterReset());
	}

	@Override
	public Status clusterReset(final ClusterResetOption clusterResetOption){
		return execute((client)->client.clusterReset(clusterResetOption));
	}

	@Override
	public Status clusterSaveConfig(){
		return execute((client)->client.clusterSaveConfig());
	}

	@Override
	public Status clusterSetConfigEpoch(final String configEpoch){
		return execute((client)->client.clusterSetConfigEpoch(configEpoch));
	}

	@Override
	public Status clusterSetConfigEpoch(final byte[] configEpoch){
		return execute((client)->client.clusterSetConfigEpoch(configEpoch));
	}

	@Override
	public BumpEpoch clusterBumpEpoch(){
		return execute((client)->client.clusterBumpEpoch());
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId){
		return execute((client)->client.clusterSetSlot(slot, setSlotOption, nodeId));
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final byte[] nodeId){
		return execute((client)->client.clusterSetSlot(slot, setSlotOption, nodeId));
	}

	@Override
	public Status asking(){
		return execute((client)->client.asking());
	}

	@Override
	public Status readWrite(){
		return execute((client)->client.readWrite());
	}

	@Override
	public Status readOnly(){
		return execute((client)->client.readOnly());
	}

	@Override
	public Status auth(final String user, final String password){
		return execute((client)->client.auth(user, password));
	}

	@Override
	public Status auth(final byte[] user, final byte[] password){
		return execute((client)->client.auth(user, password));
	}

	@Override
	public Status auth(final String password){
		return execute((client)->client.auth(password));
	}

	@Override
	public Status auth(final byte[] password){
		return execute((client)->client.auth(password));
	}

	@Override
	public String echo(final String str){
		return execute((client)->client.echo(str));
	}

	@Override
	public byte[] echo(final byte[] str){
		return execute((client)->client.echo(str));
	}

	@Override
	public Status ping(){
		return execute((client)->client.ping());
	}

	@Override
	public Status reset(){
		return execute((client)->client.reset());
	}

	@Override
	public Status quit(){
		return execute((client)->client.quit());
	}

	@Override
	public Status select(final int db){
		return execute((client)->client.select(db));
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		return execute((client)->client.swapdb(db1, db2));
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude){
		return execute((client)->client.geoAdd(makeRawKey(key), member, longitude, latitude));
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
		return execute((client)->client.geoAdd(makeByteKey(key), member, longitude, latitude));
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates){
		return execute((client)->client.geoAdd(makeRawKey(key), memberCoordinates));
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates){
		return execute((client)->client.geoAdd(makeByteKey(key), memberCoordinates));
	}

	@Override
	public List<String> geoHash(final String key, final String... members){
		return execute((client)->client.geoHash(makeRawKey(key), members));
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members){
		return execute((client)->client.geoHash(makeByteKey(key), members));
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members){
		return execute((client)->client.geoPos(makeRawKey(key), members));
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members){
		return execute((client)->client.geoPos(makeByteKey(key), members));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2){
		return execute((client)->client.geoDist(makeRawKey(key), member1, member2));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
		return execute((client)->client.geoDist(makeByteKey(key), member1, member2));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit){
		return execute((client)->client.geoDist(makeRawKey(key), member1, member2, unit));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit){
		return execute((client)->client.geoDist(makeByteKey(key), member1, member2, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit){
		return execute((client)->client.geoRadius(makeRawKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit){
		return execute((client)->client.geoRadius(makeByteKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument){
		return execute(
				(client)->client.geoRadius(makeRawKey(key), longitude, latitude, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument){
		return execute(
				(client)->client.geoRadius(makeByteKey(key), longitude, latitude, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit){
		return execute((client)->client.geoRadiusRo(makeRawKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit){
		return execute((client)->client.geoRadiusRo(makeByteKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument){
		return execute(
				(client)->client.geoRadiusRo(makeRawKey(key), longitude, latitude, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument){
		return execute(
				(client)->client.geoRadiusRo(makeByteKey(key), longitude, latitude, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit){
		return execute((client)->client.geoRadiusByMember(makeRawKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit){
		return execute((client)->client.geoRadiusByMember(makeByteKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		return execute((client)->client.geoRadiusByMember(makeRawKey(key), member, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		return execute((client)->client.geoRadiusByMember(makeByteKey(key), member, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit){
		return execute((client)->client.geoRadiusByMemberRo(makeRawKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit){
		return execute((client)->client.geoRadiusByMemberRo(makeByteKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		return execute((client)->client.geoRadiusByMemberRo(makeRawKey(key), member, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		return execute((client)->client.geoRadiusByMemberRo(makeByteKey(key), member, radius, unit, geoRadiusArgument));
	}

	@Override
	public Long hDel(final String key, final String... fields){
		return execute((client)->client.hDel(makeRawKey(key), fields));
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields){
		return execute((client)->client.hDel(makeByteKey(key), fields));
	}

	@Override
	public boolean hExists(final String key, final String field){
		return execute((client)->client.hExists(makeRawKey(key), field));
	}

	@Override
	public boolean hExists(final byte[] key, final byte[] field){
		return execute((client)->client.hExists(makeByteKey(key), field));
	}

	@Override
	public String hGet(final String key, final String field){
		return execute((client)->client.hGet(makeRawKey(key), field));
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field){
		return execute((client)->client.hGet(makeByteKey(key), field));
	}

	@Override
	public Map<String, String> hGetAll(final String key){
		return execute((client)->client.hGetAll(makeRawKey(key)));
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key){
		return execute((client)->client.hGetAll(makeByteKey(key)));
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value){
		return execute((client)->client.hIncrBy(makeRawKey(key), field, value));
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value){
		return execute((client)->client.hIncrBy(makeByteKey(key), field, value));
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value){
		return execute((client)->client.hIncrByFloat(makeRawKey(key), field, value));
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value){
		return execute((client)->client.hIncrByFloat(makeByteKey(key), field, value));
	}

	@Override
	public Set<String> hKeys(final String key){
		return execute((client)->client.hKeys(makeRawKey(key)));
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key){
		return execute((client)->client.hKeys(makeByteKey(key)));
	}

	@Override
	public Long hLen(final String key){
		return execute((client)->client.hLen(makeRawKey(key)));
	}

	@Override
	public Long hLen(final byte[] key){
		return execute((client)->client.hLen(makeByteKey(key)));
	}

	@Override
	public List<String> hMGet(final String key, final String... fields){
		return execute((client)->client.hMGet(makeRawKey(key), fields));
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields){
		return execute((client)->client.hMGet(makeByteKey(key), fields));
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data){
		return execute((client)->client.hMSet(makeRawKey(key), data));
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data){
		return execute((client)->client.hMSet(key, data));
	}

	@Override
	public String hRandField(final String key){
		return execute((client)->client.hRandField(key));
	}

	@Override
	public byte[] hRandField(final byte[] key){
		return execute((client)->client.hRandField(key));
	}

	@Override
	public List<String> hRandField(final String key, final long count){
		return execute((client)->client.hRandField(key, count));
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final long count){
		return execute((client)->client.hRandField(key, count));
	}

	@Override
	public Map<String, String> hRandFieldWithValues(final String key, final long count){
		return execute((client)->client.hRandFieldWithValues(key, count));
	}

	@Override
	public Map<byte[], byte[]> hRandFieldWithValues(final byte[] key, final long count){
		return execute((client)->client.hRandFieldWithValues(key, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor){
		return execute((client)->client.hScan(makeRawKey(key), cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor){
		return execute((client)->client.hScan(makeByteKey(key), cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor){
		return execute((client)->client.hScan(makeRawKey(key), cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
		return execute((client)->client.hScan(makeByteKey(key), cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern){
		return execute((client)->client.hScan(makeRawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern){
		return execute((client)->client.hScan(makeByteKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern){
		return execute((client)->client.hScan(makeRawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute((client)->client.hScan(makeByteKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final long count){
		return execute((client)->client.hScan(makeRawKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final long count){
		return execute((client)->client.hScan(makeByteKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final long count){
		return execute((client)->client.hScan(makeRawKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final long count){
		return execute((client)->client.hScan(makeByteKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern,
												 final long count){
		return execute((client)->client.hScan(makeRawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern,
												 final long count){
		return execute((client)->client.hScan(makeByteKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
												 final long count){
		return execute((client)->client.hScan(makeRawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
												 final long count){
		return execute((client)->client.hScan(makeByteKey(key), cursor, pattern, count));
	}

	@Override
	public Status hSet(final String key, final String field, final String value){
		return execute((client)->client.hSet(makeRawKey(key), field, value));
	}

	@Override
	public Status hSet(final byte[] key, final byte[] field, final byte[] value){
		return execute((client)->client.hSet(makeByteKey(key), field, value));
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value){
		return execute((client)->client.hSetNx(makeRawKey(key), field, value));
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value){
		return execute((client)->client.hSetNx(makeByteKey(key), field, value));
	}

	@Override
	public Long hStrLen(final String key, final String field){
		return execute((client)->client.hStrLen(makeRawKey(key), field));
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field){
		return execute((client)->client.hStrLen(makeByteKey(key), field));
	}

	@Override
	public List<String> hVals(final String key){
		return execute((client)->client.hVals(makeRawKey(key)));
	}

	@Override
	public List<byte[]> hVals(final byte[] key){
		return execute((client)->client.hVals(makeByteKey(key)));
	}

	@Override
	public Status pfAdd(final String key, final String... elements){
		return execute((client)->client.pfAdd(makeRawKey(key), elements));
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements){
		return execute((client)->client.pfAdd(makeByteKey(key), elements));
	}

	@Override
	public Status pfMerge(final String destKey, final String... keys){
		return execute((client)->client.pfMerge(makeRawKey(destKey), makeRawKeys(keys)));
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys){
		return execute((client)->client.pfMerge(makeByteKey(destKey), makeByteKeys(keys)));
	}

	@Override
	public Long pfCount(final String... keys){
		return execute((client)->client.pfCount(makeRawKeys(keys)));
	}

	@Override
	public Long pfCount(final byte[]... keys){
		return execute((client)->client.pfCount(makeByteKeys(keys)));
	}

	@Override
	public Long del(final String... keys){
		return execute((client)->client.del(makeRawKeys(keys)));
	}

	@Override
	public Long del(final byte[]... keys){
		return execute((client)->client.del(makeByteKeys(keys)));
	}

	@Override
	public String dump(final String key){
		return execute((client)->client.dump(makeRawKey(key)));
	}

	@Override
	public byte[] dump(final byte[] key){
		return execute((client)->client.dump(makeByteKey(key)));
	}

	@Override
	public boolean exists(final String key){
		return execute((client)->client.exists(makeRawKey(key)));
	}

	@Override
	public boolean exists(final byte[] key){
		return execute((client)->client.exists(makeByteKey(key)));
	}

	@Override
	public long exists(final String... keys){
		return execute((client)->client.exists(makeRawKeys(keys)));
	}

	@Override
	public long exists(final byte[]... keys){
		return execute((client)->client.exists(makeByteKeys(keys)));
	}

	@Override
	public Status expire(final String key, final int lifetime){
		return execute((client)->client.expire(makeRawKey(key), lifetime));
	}

	@Override
	public Status expire(final byte[] key, final int lifetime){
		return execute((client)->client.expire(makeByteKey(key), lifetime));
	}

	@Override
	public Status expire(final String key, final int lifetime, final ExpireOption expireOption){
		return execute((client)->client.expire(makeRawKey(key), lifetime, expireOption));
	}

	@Override
	public Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption){
		return execute((client)->client.expire(makeByteKey(key), lifetime, expireOption));
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp){
		return execute((client)->client.expireAt(makeRawKey(key), unixTimestamp));
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		return execute((client)->client.expireAt(makeByteKey(key), unixTimestamp));
	}

	@Override
	public Status pExpire(final String key, final int lifetime){
		return execute((client)->client.pExpire(makeRawKey(key), lifetime));
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		return execute((client)->client.pExpire(makeByteKey(key), lifetime));
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp){
		return execute((client)->client.pExpireAt(makeRawKey(key), unixTimestamp));
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		return execute((client)->client.pExpireAt(makeByteKey(key), unixTimestamp));
	}

	@Override
	public Status persist(final String key){
		return execute((client)->client.persist(makeRawKey(key)));
	}

	@Override
	public Status persist(final byte[] key){
		return execute((client)->client.persist(makeByteKey(key)));
	}

	@Override
	public Long ttl(final String key){
		return execute((client)->client.ttl(makeRawKey(key)));
	}

	@Override
	public Long ttl(final byte[] key){
		return execute((client)->client.ttl(makeByteKey(key)));
	}

	@Override
	public Long pTtl(final String key){
		return execute((client)->client.pTtl(makeRawKey(key)));
	}

	@Override
	public Long pTtl(final byte[] key){
		return execute((client)->client.pTtl(makeByteKey(key)));
	}

	@Override
	public Status copy(final String key, final String destKey){
		return execute((client)->client.copy(makeRawKey(key), makeRawKey(destKey)));
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey){
		return execute((client)->client.copy(makeByteKey(key), makeByteKey(destKey)));
	}

	@Override
	public Status copy(final String key, final String destKey, final int db){
		return execute((client)->client.copy(makeRawKey(key), makeRawKey(destKey), db));
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db){
		return execute((client)->client.copy(makeByteKey(key), makeByteKey(destKey), db));
	}

	@Override
	public Status copy(final String key, final String destKey, final boolean replace){
		return execute((client)->client.copy(makeRawKey(key), makeRawKey(destKey), replace));
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final boolean replace){
		return execute((client)->client.copy(makeByteKey(key), makeByteKey(destKey), replace));
	}

	@Override
	public Status copy(final String key, final String destKey, final int db, final boolean replace){
		return execute((client)->client.copy(makeRawKey(key), makeRawKey(destKey), db, replace));
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace){
		return execute((client)->client.copy(makeByteKey(key), makeByteKey(destKey), db, replace));
	}

	@Override
	public Status move(final String key, final int db){
		return execute((client)->client.move(makeRawKey(key), db));
	}

	@Override
	public Status move(final byte[] key, final int db){
		return execute((client)->client.move(makeByteKey(key), db));
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
		return execute((client)->client.migrate(makeRawKey(key), host, port, db, timeout));
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
		return execute((client)->client.migrate(makeByteKey(key), host, port, db, timeout));
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout,
						  MigrateOperation operation){
		return execute((client)->client.migrate(makeRawKey(key), host, port, db, timeout, operation));
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation){
		return execute((client)->client.migrate(makeByteKey(key), host, port, db, timeout, operation));
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final String password,
						  final int timeout){
		return execute((client)->client.migrate(makeRawKey(key), host, port, db, password, timeout));
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final byte[] password,
						  final int timeout){
		return execute((client)->client.migrate(makeByteKey(key), host, port, db, password, timeout));
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final String password,
						  final int timeout, final MigrateOperation operation){
		return execute((client)->client.migrate(makeRawKey(key), host, port, db, password, timeout, operation));
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final byte[] password,
						  final int timeout, final MigrateOperation operation){
		return execute((client)->client.migrate(makeByteKey(key), host, port, db, password, timeout, operation));
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final String user,
						  final String password, final int timeout){
		return execute((client)->client.migrate(makeRawKey(key), host, port, db, user, password, timeout));
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final byte[] user,
						  final byte[] password, final int timeout){
		return execute((client)->client.migrate(makeByteKey(key), host, port, db, user, password, timeout));
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final String user,
						  final String password, final int timeout, final MigrateOperation operation){
		return execute((client)->client.migrate(makeRawKey(key), host, port, db, user, password, timeout, operation));
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final byte[] user,
						  final byte[] password, final int timeout, final MigrateOperation operation){
		return execute((client)->client.migrate(makeByteKey(key), host, port, db, user, password, timeout, operation));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final String... keys){
		return execute((client)->client.migrate(host, port, db, timeout, makeRawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final byte[]... keys){
		return execute((client)->client.migrate(host, port, db, timeout, makeByteKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final String... keys){
		return execute((client)->client.migrate(host, port, db, timeout, operation, makeRawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final byte[]... keys){
		return execute((client)->client.migrate(host, port, db, timeout, operation, makeByteKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final String... keys){
		return execute((client)->client.migrate(host, port, db, password, timeout, makeRawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final byte[]... keys){
		return execute((client)->client.migrate(host, port, db, password, timeout, makeByteKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final MigrateOperation operation, final String... keys){
		return execute((client)->client.migrate(host, port, db, password, timeout, operation, makeRawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final MigrateOperation operation, final byte[]... keys){
		return execute((client)->client.migrate(host, port, db, password, timeout, operation, makeByteKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final String... keys){
		return execute((client)->client.migrate(host, port, db, user, password, timeout, makeRawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final byte[]... keys){
		return execute((client)->client.migrate(host, port, db, user, password, timeout, makeByteKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final MigrateOperation operation, final String... keys){
		return execute((client)->client.migrate(host, port, db, user, password, timeout, operation, makeRawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final MigrateOperation operation, final byte[]... keys){
		return execute(
				(client)->client.migrate(host, port, db, user, password, timeout, operation, makeByteKeys(keys)));
	}

	@Override
	public Set<String> keys(final String pattern){
		return execute((client)->client.keys(pattern));
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern){
		return execute((client)->client.keys(pattern));
	}

	@Override
	public String randomKey(){
		return execute((client)->client.randomKey());
	}

	@Override
	public Status rename(final String key, final String newKey){
		return execute((client)->client.rename(makeRawKey(key), makeRawKey(newKey)));
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
		return execute((client)->client.rename(makeByteKey(key), makeByteKey(newKey)));
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		return execute((client)->client.renameNx(makeRawKey(key), makeRawKey(newKey)));
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		return execute((client)->client.renameNx(makeByteKey(key), makeByteKey(newKey)));
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl){
		return execute((client)->client.restore(makeRawKey(key), serializedValue, ttl));
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		return execute((client)->client.restore(makeByteKey(key), serializedValue, ttl));
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument){
		return execute((client)->client.restore(makeRawKey(key), serializedValue, ttl, argument));
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument){
		return execute((client)->client.restore(makeByteKey(key), serializedValue, ttl, argument));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor){
		return execute((client)->client.scan(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor){
		return execute((client)->client.scan(cursor));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		return execute((client)->client.scan(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern){
		return execute((client)->client.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern){
		return execute((client)->client.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern){
		return execute((client)->client.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		return execute((client)->client.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final long count){
		return execute((client)->client.scan(cursor, count));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final long count){
		return execute((client)->client.scan(cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final long count){
		return execute((client)->client.scan(cursor, count));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern, final long count){
		return execute((client)->client.scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern, final long count){
		return execute((client)->client.scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final long count){
		return execute((client)->client.scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final long count){
		return execute((client)->client.scan(cursor, pattern, count));
	}

	@Override
	public List<String> sort(final String key){
		return execute((client)->client.sort(makeRawKey(key)));
	}

	@Override
	public List<byte[]> sort(final byte[] key){
		return execute((client)->client.sort(makeByteKey(key)));
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument){
		return execute((client)->client.sort(makeRawKey(key), sortArgument));
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument){
		return execute((client)->client.sort(makeByteKey(key), sortArgument));
	}

	@Override
	public Long sort(final String key, final String destKey){
		return execute((client)->client.sort(makeRawKey(key), makeRawKey(destKey)));
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey){
		return execute((client)->client.sort(makeByteKey(key), makeByteKey(destKey)));
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument){
		return execute((client)->client.sort(makeRawKey(key), makeRawKey(destKey), sortArgument));
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		return execute((client)->client.sort(makeByteKey(key), makeByteKey(destKey), sortArgument));
	}

	@Override
	public Long touch(final String... keys){
		return execute((client)->client.touch(makeRawKeys(keys)));
	}

	@Override
	public Long touch(final byte[]... keys){
		return execute((client)->client.touch(makeByteKeys(keys)));
	}

	@Override
	public Type type(final String key){
		return execute((client)->client.type(makeRawKey(key)));
	}

	@Override
	public Type type(final byte[] key){
		return execute((client)->client.type(makeByteKey(key)));
	}

	@Override
	public Long unlink(final String... keys){
		return execute((client)->client.unlink(makeRawKeys(keys)));
	}

	@Override
	public Long unlink(final byte[]... keys){
		return execute((client)->client.unlink(makeByteKeys(keys)));
	}

	@Override
	public Long wait(final int replicas, final int timeout){
		return execute((client)->client.wait(replicas, timeout));
	}

	@Override
	public ObjectEncoding objectEncoding(final String key){
		return execute((client)->client.objectEncoding(makeRawKey(key)));
	}

	@Override
	public ObjectEncoding objectEncoding(final byte[] key){
		return execute((client)->client.objectEncoding(makeByteKey(key)));
	}

	@Override
	public Long objectFreq(final String key){
		return execute((client)->client.objectFreq(makeRawKey(key)));
	}

	@Override
	public Long objectFreq(final byte[] key){
		return execute((client)->client.objectFreq(makeByteKey(key)));
	}

	@Override
	public Long objectIdleTime(final String key){
		return execute((client)->client.objectIdleTime(makeRawKey(key)));
	}

	@Override
	public Long objectIdleTime(final byte[] key){
		return execute((client)->client.objectIdleTime(makeByteKey(key)));
	}

	@Override
	public Long objectRefcount(final String key){
		return execute((client)->client.objectRefcount(makeRawKey(key)));
	}

	@Override
	public Long objectRefcount(final byte[] key){
		return execute((client)->client.objectRefcount(makeByteKey(key)));
	}

	@Override
	public String lIndex(final String key, final long index){
		return execute((client)->client.lIndex(makeRawKey(key), index));
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index){
		return execute((client)->client.lIndex(makeByteKey(key), index));
	}

	@Override
	public Long lInsert(final String key, final ListPosition position, final String pivot, final String value){
		return execute((client)->client.lInsert(makeRawKey(key), position, pivot, value));
	}

	@Override
	public Long lInsert(final byte[] key, final ListPosition position, final byte[] pivot, final byte[] value){
		return execute((client)->client.lInsert(makeByteKey(key), position, pivot, value));
	}

	@Override
	public Status lSet(final String key, final long index, final String value){
		return execute((client)->client.lSet(makeRawKey(key), index, value));
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value){
		return execute((client)->client.lSet(makeByteKey(key), index, value));
	}

	@Override
	public Long lLen(final String key){
		return execute((client)->client.lLen(makeRawKey(key)));
	}

	@Override
	public Long lLen(final byte[] key){
		return execute((client)->client.lLen(makeByteKey(key)));
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end){
		return execute((client)->client.lRange(makeRawKey(key), start, end));
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end){
		return execute((client)->client.lRange(makeByteKey(key), start, end));
	}

	@Override
	public Long lPos(final String key, final String element){
		return execute((client)->client.lPos(makeRawKey(key), element));
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element){
		return execute((client)->client.lPos(makeByteKey(key), element));
	}

	@Override
	public Long lPos(final String key, final String element, final LPosArgument lPosArgument){
		return execute((client)->client.lPos(makeRawKey(key), element, lPosArgument));
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument){
		return execute((client)->client.lPos(makeByteKey(key), element, lPosArgument));
	}

	@Override
	public List<Long> lPos(final String key, String element, final LPosArgument lPosArgument, final long count){
		return execute((client)->client.lPos(makeRawKey(key), element, lPosArgument, count));
	}

	@Override
	public List<Long> lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument, final long count){
		return execute((client)->client.lPos(makeByteKey(key), element, lPosArgument, count));
	}

	@Override
	public Long lRem(final String key, final String value, final long count){
		return execute((client)->client.lRem(makeRawKey(key), value, count));
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count){
		return execute((client)->client.lRem(makeByteKey(key), value, count));
	}

	@Override
	public Status lTrim(final String key, final long start, final long end){
		return execute((client)->client.lTrim(makeRawKey(key), start, end));
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end){
		return execute((client)->client.lTrim(makeByteKey(key), start, end));
	}

	@Override
	public String lMove(final String key, final String destKey, final Direction from, final Direction to){
		return execute((client)->client.lMove(makeRawKey(destKey), makeRawKey(destKey), from, to));
	}

	@Override
	public byte[] lMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to){
		return execute((client)->client.lMove(makeByteKey(key), makeByteKey(destKey), from, to));
	}

	@Override
	public String blMove(final String key, final String destKey, final Direction from, final Direction to,
						 final int timeout){
		return execute((client)->client.blMove(makeRawKey(destKey), makeRawKey(destKey), from, to, timeout));
	}

	@Override
	public byte[] blMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
						 final int timeout){
		return execute((client)->client.blMove(makeByteKey(key), makeByteKey(destKey), from, to, timeout));
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout){
		return execute((client)->client.blPop(makeRawKeys(keys), timeout));
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout){
		return execute((client)->client.blPop(makeByteKeys(keys), timeout));
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout){
		return execute((client)->client.brPop(makeRawKeys(keys), timeout));
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout){
		return execute((client)->client.brPop(makeByteKeys(keys), timeout));
	}

	@Override
	public String brPoplPush(final String key, final String destKey, final int timeout){
		return execute((client)->client.brPoplPush(makeRawKey(key), makeRawKey(destKey), timeout));
	}

	@Override
	public byte[] brPoplPush(final byte[] key, final byte[] destKey, final int timeout){
		return execute((client)->client.brPoplPush(makeByteKey(key), makeByteKey(destKey), timeout));
	}

	@Override
	public String lPop(final String key){
		return execute((client)->client.lPop(makeRawKey(key)));
	}

	@Override
	public byte[] lPop(final byte[] key){
		return execute((client)->client.lPop(makeByteKey(key)));
	}

	@Override
	public Long lPush(final String key, final String... values){
		return execute((client)->client.lPush(makeRawKey(key), values));
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values){
		return execute((client)->client.lPush(makeByteKey(key), values));
	}

	@Override
	public Long lPushX(final String key, final String... values){
		return execute((client)->client.lPushX(makeRawKey(key), values));
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values){
		return execute((client)->client.lPushX(makeByteKey(key), values));
	}

	@Override
	public String rPop(final String key){
		return execute((client)->client.rPop(makeRawKey(key)));
	}

	@Override
	public byte[] rPop(final byte[] key){
		return execute((client)->client.rPop(makeByteKey(key)));
	}

	@Override
	public String rPoplPush(final String key, final String destKey){
		return execute((client)->client.rPoplPush(makeRawKey(key), makeRawKey(destKey)));
	}

	@Override
	public byte[] rPoplPush(final byte[] key, final byte[] destKey){
		return execute((client)->client.rPoplPush(makeByteKey(key), makeByteKey(destKey)));
	}

	@Override
	public Long rPush(final String key, final String... values){
		return execute((client)->client.rPush(makeRawKey(key), values));
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values){
		return execute((client)->client.rPush(makeByteKey(key), values));
	}

	@Override
	public Long rPushX(final String key, final String... values){
		return execute((client)->client.rPushX(makeRawKey(key), values));
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values){
		return execute((client)->client.rPushX(makeByteKey(key), values));
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
		final CommandArguments args = CommandArguments.create("patterns", patterns)
				.put("pubSubListener", pubSubListener);
		execute((client)->{
			client.pSubscribe(patterns, pubSubListener);
			return null;
		}, ProtocolCommand.PSUBSCRIBE, args);
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
		final CommandArguments args = CommandArguments.create("patterns", patterns)
				.put("pubSubListener", pubSubListener);
		execute((client)->{
			client.pSubscribe(patterns, pubSubListener);
			return null;
		}, ProtocolCommand.PSUBSCRIBE, args);
	}

	@Override
	public List<String> pubsubChannels(){
		return execute((client)->client.pubsubChannels(), ProtocolCommand.PUBSUB);
	}

	@Override
	public List<String> pubsubChannels(final String pattern){
		return execute((client)->client.pubsubChannels(pattern), ProtocolCommand.PUBSUB,
				new CommandArguments("pattern", pattern));
	}

	@Override
	public List<byte[]> pubsubChannels(final byte[] pattern){
		return execute((client)->client.pubsubChannels(pattern), ProtocolCommand.PUBSUB,
				new CommandArguments("pattern", pattern));
	}

	@Override
	public Long pubsubNumPat(){
		return execute((client)->client.pubsubNumPat(), ProtocolCommand.PUBSUB);
	}

	@Override
	public Map<String, String> pubsubNumSub(final String... channels){
		return execute((client)->client.pubsubNumSub(channels), ProtocolCommand.PUBSUB,
				new CommandArguments("channels", channels));
	}

	@Override
	public Map<byte[], byte[]> pubsubNumSub(final byte[]... channels){
		return execute((client)->client.pubsubNumSub(channels), ProtocolCommand.PUBSUB,
				new CommandArguments("channels", channels));
	}

	@Override
	public Long publish(final String channel, final String message){
		final CommandArguments args = CommandArguments.create("channel", channel).put("message", message);
		return execute((client)->client.publish(channel, message), ProtocolCommand.PUBLISH, args);
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message){
		final CommandArguments args = CommandArguments.create("channel", channel).put("message", message);
		return execute((client)->client.publish(channel, message), ProtocolCommand.PUBLISH, args);
	}

	@Override
	public Object pUnSubscribe(){
		return execute((client)->client.pUnSubscribe(), ProtocolCommand.PUNSUBSCRIBE);
	}

	@Override
	public Object pUnSubscribe(final String... patterns){
		return execute((client)->client.pUnSubscribe(patterns), ProtocolCommand.PUNSUBSCRIBE,
				new CommandArguments("patterns", patterns));
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns){
		return execute((client)->client.pUnSubscribe(patterns), ProtocolCommand.PUNSUBSCRIBE,
				new CommandArguments("patterns", patterns));
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
		final CommandArguments args = CommandArguments.create("channels", channels)
				.put("pubSubListener", pubSubListener);
		execute((client)->{
			client.subscribe(channels, pubSubListener);
			return null;
		}, ProtocolCommand.SUBSCRIBE, args);
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
		final CommandArguments args = CommandArguments.create("channels", channels)
				.put("pubSubListener", pubSubListener);
		execute((client)->{
			client.subscribe(channels, pubSubListener);
			return null;
		}, ProtocolCommand.SUBSCRIBE, args);
	}

	@Override
	public Object unSubscribe(){
		return execute((client)->client.unSubscribe(), ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	public Object unSubscribe(final String... channels){
		return execute((client)->client.unSubscribe(channels), ProtocolCommand.UNSUBSCRIBE,
				new CommandArguments("channels", channels));
	}

	@Override
	public Object unSubscribe(final byte[]... channels){
		return execute((client)->client.unSubscribe(channels), ProtocolCommand.UNSUBSCRIBE,
				new CommandArguments("channels", channels));
	}

	@Override
	public Object eval(final String script){
		return execute((client)->client.eval(script), ProtocolCommand.EVAL, new CommandArguments("script", script));
	}

	@Override
	public Object eval(final byte[] script){
		return execute((client)->client.eval(script), ProtocolCommand.EVAL, new CommandArguments("script", script));
	}

	@Override
	public Object eval(final String script, final String... params){
		final CommandArguments args = CommandArguments.create("script", script).put("params", params);
		return execute((client)->client.eval(script, params), ProtocolCommand.EVAL, args);
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params){
		final CommandArguments args = CommandArguments.create("script", script).put("params", params);
		return execute((client)->client.eval(script, params), ProtocolCommand.EVAL, args);
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments){
		final CommandArguments args = CommandArguments.create("script", script).put("keys", keys)
				.put("arguments", arguments);
		return execute((client)->client.eval(script, keys, arguments), ProtocolCommand.EVAL, args);
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments){
		final CommandArguments args = CommandArguments.create("script", script).put("keys", keys)
				.put("arguments", arguments);
		return execute((client)->client.eval(script, keys, arguments), ProtocolCommand.EVAL, args);
	}

	@Override
	public Object evalSha(final String digest){
		return execute((client)->client.evalSha(digest), ProtocolCommand.EVALSHA,
				new CommandArguments("digest", digest));
	}

	@Override
	public Object evalSha(final byte[] digest){
		return execute((client)->client.evalSha(digest), ProtocolCommand.EVALSHA,
				new CommandArguments("digest", digest));
	}

	@Override
	public Object evalSha(final String digest, final String... params){
		final CommandArguments args = CommandArguments.create("digest", digest).put("params", params);
		return execute((client)->client.evalSha(digest, params), ProtocolCommand.EVALSHA, args);
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... params){
		final CommandArguments args = CommandArguments.create("digest", digest).put("params", params);
		return execute((client)->client.evalSha(digest, params), ProtocolCommand.EVALSHA, args);
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments){
		final CommandArguments args = CommandArguments.create("digest", digest).put("keys", keys)
				.put("arguments", arguments);
		return execute((client)->client.evalSha(digest, keys, arguments), ProtocolCommand.EVALSHA, args);
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments){
		final CommandArguments args = CommandArguments.create("digest", digest).put("keys", keys)
				.put("arguments", arguments);
		return execute((client)->client.evalSha(digest, keys, arguments), ProtocolCommand.EVALSHA, args);
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1){
		return execute((client)->client.scriptExists(sha1), ProtocolCommand.SCRIPT_EXISTS,
				new CommandArguments("sha1", sha1));
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1){
		return execute((client)->client.scriptExists(sha1), ProtocolCommand.SCRIPT_EXISTS,
				new CommandArguments("sha1", sha1));
	}

	@Override
	public Status scriptFlush(){
		return execute((client)->client.scriptFlush(), ProtocolCommand.SCRIPT_FLUSH);
	}

	@Override
	public Status scriptKill(){
		return execute((client)->client.scriptKill(), ProtocolCommand.SCRIPT_KILL);
	}

	@Override
	public String scriptLoad(final String script){
		return execute((client)->client.scriptLoad(script), ProtocolCommand.SCRIPT_LOAD,
				new CommandArguments("script", script));
	}

	@Override
	public byte[] scriptLoad(final byte[] script){
		return execute((client)->client.scriptLoad(script), ProtocolCommand.SCRIPT_LOAD,
				new CommandArguments("script", script));
	}

	@Override
	public String bgRewriteAof(){
		return execute((client)->client.bgSave(), ProtocolCommand.BGREWRITEAOF);
	}

	@Override
	public String bgSave(){
		return execute((client)->client.bgSave(), ProtocolCommand.BGSAVE);
	}

	@Override
	public Status clientKill(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return execute((client)->client.clientKill(host, port), ProtocolCommand.CLIENT_KILL, args);
	}

	@Override
	public String clientGetName(){
		return execute((client)->client.clientGetName(), ProtocolCommand.CLIENT_GETNAME);
	}

	@Override
	public String clientId(){
		return execute((client)->client.clientId(), ProtocolCommand.CLIENT_ID);
	}

	@Override
	public List<Client> clientList(){
		return execute((client)->client.clientList(), ProtocolCommand.CLIENT_LIST);
	}

	@Override
	public Status clientPause(final int timeout){
		return execute((client)->client.clientPause(timeout), ProtocolCommand.CLIENT_PAUSE,
				new CommandArguments("timeout", timeout));
	}

	@Override
	public Status clientReply(final ClientReply option){
		return execute((client)->client.clientReply(option), ProtocolCommand.CLIENT_REPLY,
				new CommandArguments("option", option));
	}

	@Override
	public Status clientSetName(final String name){
		return execute((client)->client.clientSetName(name), ProtocolCommand.CLIENT_SETNAME,
				new CommandArguments("name", name));
	}

	@Override
	public Status clientSetName(final byte[] name){
		return execute((client)->client.clientSetName(name), ProtocolCommand.CLIENT_SETNAME,
				new CommandArguments("name", name));
	}

	@Override
	public Status clientUnblock(final int clientId){
		return execute((client)->client.clientUnblock(clientId), ProtocolCommand.CLIENT_UNBLOCK,
				new CommandArguments("clientId", clientId));
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type){
		final CommandArguments args = CommandArguments.create("clientId", clientId).put("type", type);
		return execute((client)->client.clientUnblock(clientId, type), ProtocolCommand.CLIENT_UNBLOCK, args);
	}

	@Override
	public List<String> configGet(final String parameter){
		return execute((client)->client.configGet(parameter), ProtocolCommand.CONFIG_GET,
				new CommandArguments("parameter", parameter));
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter){
		return execute((client)->client.configGet(parameter), ProtocolCommand.CONFIG_GET,
				new CommandArguments("parameter", parameter));
	}

	@Override
	public Status configResetStat(){
		return execute((client)->client.configResetStat(), ProtocolCommand.CONFIG_RESETSTAT);
	}

	@Override
	public Status configRewrite(){
		return execute((client)->client.configRewrite(), ProtocolCommand.CONFIG_REWRITE);
	}

	@Override
	public Status configSet(final String parameter, final String value){
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);
		return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		final CommandArguments args = CommandArguments.create("parameter", parameter).put("value", value);
		return execute((client)->client.configSet(parameter, value), ProtocolCommand.CONFIG_SET, args);
	}

	@Override
	public Long dbSize(){
		return execute((client)->client.dbSize(), ProtocolCommand.DBSIZE);
	}

	@Override
	public Status flushAll(){
		return execute((client)->client.flushAll(), ProtocolCommand.FLUSHALL);
	}

	@Override
	public Status flushDb(){
		return execute((client)->client.flushDb(), ProtocolCommand.FLUSHDB);
	}

	@Override
	public Info info(final Info.Section section){
		return execute((client)->client.info(section), ProtocolCommand.INFO, new CommandArguments("section", section));
	}

	@Override
	public Info info(){
		return execute((client)->client.info(), ProtocolCommand.INFO);
	}

	@Override
	public Long lastSave(){
		return execute((client)->client.lastSave(), ProtocolCommand.LASTSAVE);
	}

	@Override
	public String memoryDoctor(){
		return execute((client)->client.memoryDoctor(), ProtocolCommand.MEMORY_DOCTOR);
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		execute((client)->{
			client.monitor(redisMonitor);
			return null;
		}, ProtocolCommand.MEMORY_DOCTOR, new CommandArguments("redisMonitor", redisMonitor));
	}

	@Override
	public Status replicaOf(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return execute((client)->client.replicaOf(host, port), ProtocolCommand.REPLICAOF, args);
	}

	@Override
	public Role role(){
		return execute((client)->client.role(), ProtocolCommand.ROLE);
	}

	@Override
	public Status save(){
		return execute((client)->client.save(), ProtocolCommand.SAVE);
	}

	@Override
	public void shutdown(){
		execute((client)->{
			client.shutdown();
			return null;
		}, ProtocolCommand.SHUTDOWN);
	}

	@Override
	public void shutdown(final boolean save){
		execute((client)->{
			client.shutdown(save);
			return null;
		}, ProtocolCommand.SHUTDOWN, new CommandArguments("save", save));
	}

	@Override
	public Status slaveOf(final String host, final int port){
		final CommandArguments args = CommandArguments.create("host", host).put("port", port);
		return execute((client)->client.slaveOf(host, port), ProtocolCommand.SLAVEOF, args);
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final String... arguments){
		final CommandArguments args = CommandArguments.create("command", command).put("arguments", arguments);
		return execute((client)->client.slowLog(command, arguments), ProtocolCommand.SLOWLOG, args);
	}

	@Override
	public Object slowLog(final SlowLogCommand command, final byte[]... arguments){
		final CommandArguments args = CommandArguments.create("command", command).put("arguments", arguments);
		return execute((client)->client.slowLog(command, arguments), ProtocolCommand.SLOWLOG, args);
	}

	@Override
	public Object sync(){
		return execute((client)->client.sync(), ProtocolCommand.SYNC);
	}

	@Override
	public Object pSync(final String masterRunId, final long offset){
		final CommandArguments args = CommandArguments.create("masterRunId", masterRunId).put("offset", offset);
		return execute((client)->client.pSync(masterRunId, offset), ProtocolCommand.PSYNC, args);
	}

	@Override
	public Object pSync(final byte[] masterRunId, final long offset){
		final CommandArguments args = CommandArguments.create("masterRunId", masterRunId).put("offset", offset);
		return execute((client)->client.pSync(masterRunId, offset), ProtocolCommand.PSYNC, args);
	}

	@Override
	public RedisServerTime time(){
		return execute((client)->client.time(), ProtocolCommand.TIME);
	}

	@Override
	public Long sAdd(final String key, final String... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		return execute((client)->client.sAdd(makeRawKey(key), members), ProtocolCommand.SADD, args);
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		return execute((client)->client.sAdd(makeByteKey(key), members), ProtocolCommand.SADD, args);
	}

	@Override
	public Long sCard(final String key){
		return execute((client)->client.sCard(makeRawKey(key)), ProtocolCommand.SCARD,
				new CommandArguments("key", key));
	}

	@Override
	public Long sCard(final byte[] key){
		return execute((client)->client.sCard(makeByteKey(key)), ProtocolCommand.SCARD,
				new CommandArguments("key", key));
	}

	@Override
	public Set<String> sDiff(final String... keys){
		return execute((client)->client.sDiff(makeRawKeys(keys)), ProtocolCommand.SDIFF,
				new CommandArguments("keys", keys));
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys){
		return execute((client)->client.sDiff(makeByteKeys(keys)), ProtocolCommand.SDIFF,
				new CommandArguments("keys", keys));
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		return execute((client)->client.sDiffStore(makeRawKey(destKey), makeRawKeys(keys)), ProtocolCommand.SDIFFSTORE
				, args);
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		return execute((client)->client.sDiffStore(makeByteKey(destKey), makeByteKeys(keys)),
				ProtocolCommand.SDIFFSTORE, args);
	}

	@Override
	public Set<String> sInter(final String... keys){
		return execute((client)->client.sInter(makeRawKeys(keys)), ProtocolCommand.SINTER,
				new CommandArguments("keys", keys));
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys){
		return execute((client)->client.sInter(makeByteKeys(keys)), ProtocolCommand.SINTER,
				new CommandArguments("keys", keys));
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		return execute((client)->client.sDiffStore(makeRawKey(destKey), makeRawKeys(keys)),
				ProtocolCommand.SINTERSTORE, args);
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		return execute((client)->client.sDiffStore(makeByteKey(destKey), makeByteKeys(keys)),
				ProtocolCommand.SINTERSTORE, args);
	}

	@Override
	public boolean sisMember(final String key, final String member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		return execute((client)->client.sisMember(makeRawKey(key), member), ProtocolCommand.SISMEMBER, args);
	}

	@Override
	public boolean sisMember(final byte[] key, final byte[] member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		return execute((client)->client.sisMember(makeByteKey(key), member), ProtocolCommand.SISMEMBER, args);
	}

	@Override
	public Set<String> sMembers(final String key){
		return execute((client)->client.sMembers(makeRawKey(key)), ProtocolCommand.SMEMBERS,
				new CommandArguments("key", key));
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		return execute((client)->client.sMembers(makeByteKey(key)), ProtocolCommand.SMEMBERS,
				new CommandArguments("key", key));
	}

	@Override
	public Status sMove(final String key, final String destKey, final String member){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("member", member);
		return execute((client)->client.sMove(makeRawKey(key), makeRawKey(destKey), member), ProtocolCommand.SMOVE,
				args);
	}

	@Override
	public Status sMove(final byte[] key, final byte[] destKey, final byte[] member){
		final CommandArguments args = CommandArguments.create("key", key).put("destKey", destKey).put("member", member);
		return execute((client)->client.sMove(makeByteKey(key), makeByteKey(destKey), member), ProtocolCommand.SMOVE,
				args);
	}

	@Override
	public String sPop(final String key){
		return execute((client)->client.sPop(makeRawKey(key)), ProtocolCommand.SMEMBERS,
				new CommandArguments("key", key));
	}

	@Override
	public byte[] sPop(final byte[] key){
		return execute((client)->client.sPop(makeByteKey(key)), ProtocolCommand.SMEMBERS,
				new CommandArguments("key", key));
	}

	@Override
	public String sRandMember(final String key){
		return execute((client)->client.sRandMember(makeRawKey(key)), ProtocolCommand.SRANDMEMBER,
				new CommandArguments("key", key));
	}

	@Override
	public byte[] sRandMember(final byte[] key){
		return execute((client)->client.sRandMember(makeByteKey(key)), ProtocolCommand.SRANDMEMBER,
				new CommandArguments("key", key));
	}

	@Override
	public List<String> sRandMember(final String key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return execute((client)->client.sRandMember(makeRawKey(key), count), ProtocolCommand.SRANDMEMBER, args);
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return execute((client)->client.sRandMember(makeByteKey(key), count), ProtocolCommand.SRANDMEMBER, args);
	}

	@Override
	public Long sRem(final String key, final String... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		return execute((client)->client.sRem(makeRawKey(key), members), ProtocolCommand.SREM, args);
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		return execute((client)->client.sRem(makeByteKey(key), members), ProtocolCommand.SREM, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return execute((client)->client.sScan(makeRawKey(key), cursor), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return execute((client)->client.sScan(makeByteKey(key), cursor), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return execute((client)->client.sScan(makeRawKey(key), cursor), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return execute((client)->client.sScan(makeByteKey(key), cursor), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.sScan(makeRawKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.sScan(makeByteKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.sScan(makeRawKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.sScan(makeByteKey(key), cursor, pattern), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.sScan(makeRawKey(key), cursor, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.sScan(makeByteKey(key), cursor, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.sScan(makeRawKey(key), cursor, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.sScan(makeByteKey(key), cursor, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		return execute((client)->client.sScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		return execute((client)->client.sScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
										  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		return execute((client)->client.sScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		return execute((client)->client.sScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.SSCAN, args);
	}

	@Override
	public Set<String> sUnion(final String... keys){
		return execute((client)->client.sUnion(makeRawKeys(keys)), ProtocolCommand.SUNION,
				new CommandArguments("keys", keys));
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys){
		return execute((client)->client.sUnion(makeByteKeys(keys)), ProtocolCommand.SUNION,
				new CommandArguments("keys", keys));
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		return execute((client)->client.sUnionStore(makeRawKey(destKey), makeRawKeys(keys)),
				ProtocolCommand.SUNIONSTORE, args);
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		return execute((client)->client.sUnionStore(makeByteKey(destKey), makeByteKeys(keys)),
				ProtocolCommand.SUNIONSTORE, args);
	}

	@Override
	public Long zAdd(final String key, final Map<String, Number> members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		return execute((client)->client.zAdd(makeRawKey(key), members), ProtocolCommand.ZADD, args);
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Number> members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		return execute((client)->client.zAdd(makeByteKey(key), members), ProtocolCommand.ZADD, args);
	}

	@Override
	public Long zCard(final String key){
		return execute((client)->client.zCard(makeRawKey(key)), ProtocolCommand.ZCARD,
				new CommandArguments("key", key));
	}

	@Override
	public Long zCard(final byte[] key){
		return execute((client)->client.zCard(makeByteKey(key)), ProtocolCommand.ZCARD,
				new CommandArguments("key", key));
	}

	@Override
	public Long zCount(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zCount(makeRawKey(key), min, max), ProtocolCommand.ZCOUNT, args);
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zCount(makeByteKey(key), min, max), ProtocolCommand.ZCOUNT, args);
	}

	@Override
	public Long zCount(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zCount(makeRawKey(key), min, max), ProtocolCommand.ZCOUNT, args);
	}

	@Override
	public Long zCount(final byte[] key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zCount(makeByteKey(key), min, max), ProtocolCommand.ZCOUNT, args);
	}

	@Override
	public Long zCount(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zCount(makeRawKey(key), min, max), ProtocolCommand.ZCOUNT, args);
	}

	@Override
	public Long zCount(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zCount(makeByteKey(key), min, max), ProtocolCommand.ZCOUNT, args);
	}

	@Override
	public Double zIncrBy(final String key, final String member, final double increment){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member)
				.put("increment", increment);
		return execute((client)->client.zIncrBy(makeRawKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final double increment){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member)
				.put("increment", increment);
		return execute((client)->client.zIncrBy(makeByteKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
	}

	@Override
	public Double zIncrBy(final String key, final String member, final long increment){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member)
				.put("increment", increment);
		return execute((client)->client.zIncrBy(makeRawKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final long increment){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member)
				.put("increment", increment);
		return execute((client)->client.zIncrBy(makeByteKey(key), member, increment), ProtocolCommand.ZINCRBY, args);
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		return execute((client)->client.zInterStore(makeRawKey(destKey), makeRawKeys(keys)),
				ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		return execute((client)->client.zInterStore(makeByteKey(destKey), makeByteKeys(keys)),
				ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("aggregate", aggregate)
				.put("keys", keys);
		return execute((client)->client.zInterStore(makeRawKey(destKey), aggregate, makeRawKeys(keys)),
				ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("aggregate", aggregate)
				.put("keys", keys);
		return execute((client)->client.zInterStore(makeByteKey(destKey), aggregate, makeByteKeys(keys)),
				ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zInterStore(final String destKey, final double[] weights, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("weights", weights)
				.put("keys", keys);
		return execute((client)->client.zInterStore(makeRawKey(destKey), weights, makeRawKeys(keys)),
				ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("weights", weights)
				.put("keys", keys);
		return execute((client)->client.zInterStore(makeByteKey(destKey), weights, makeByteKeys(keys)),
				ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final double[] weights,
							final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("aggregate", aggregate)
				.put("weights", weights).put("keys", keys);
		return execute((client)->client.zInterStore(makeRawKey(destKey), aggregate, weights, makeRawKeys(keys)),
				ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
							final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("aggregate", aggregate)
				.put("weights", weights).put("keys", keys);
		return execute((client)->client.zInterStore(makeByteKey(destKey), aggregate, weights, makeByteKeys(keys)),
				ProtocolCommand.ZINTERSTORE, args);
	}

	@Override
	public Long zLexCount(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zLexCount(makeRawKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
	}

	@Override
	public Long zLexCount(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zLexCount(makeByteKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
	}

	@Override
	public Long zLexCount(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zLexCount(makeRawKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
	}

	@Override
	public Long zLexCount(final byte[] key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zLexCount(makeByteKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
	}

	@Override
	public Long zLexCount(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zLexCount(makeRawKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
	}

	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zLexCount(makeByteKey(key), min, max), ProtocolCommand.ZLEXCOUNT, args);
	}

	@Override
	public Set<String> zRange(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRange(makeRawKey(key), start, end), ProtocolCommand.ZRANGE, args);
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRange(makeByteKey(key), start, end), ProtocolCommand.ZRANGE, args);
	}

	@Override
	public Set<Tuple> zRangeWithScores(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRangeWithScores(makeRawKey(key), start, end), ProtocolCommand.ZRANGE, args);
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRangeWithScores(makeByteKey(key), start, end), ProtocolCommand.ZRANGE, args);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final double min, final double max, final long offset,
								   final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final long offset,
								   final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final long min, final long max, final long offset,
								   final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max, final long offset,
								   final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max, final long offset,
								   final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByLex(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
								   final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByLex(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE,
				args);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE,
				args);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZRANGEBYSCORE,
				args);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max, final long offset,
									 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final long offset,
									 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final long min, final long max, final long offset,
									 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max, final long offset,
									 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max, final long offset,
									 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScore(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final long offset,
									 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScore(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final long offset,
											  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final long offset,
											  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max, final long offset,
											  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max, final long offset,
											  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final long offset,
											  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScoreWithScores(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final long offset,
											  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRangeByScoreWithScores(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZRANGEBYSCORE, args);
	}

	@Override
	public Long zRank(final String key, final String member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		return execute((client)->client.zRank(makeRawKey(key), member), ProtocolCommand.ZRANK, args);
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		return execute((client)->client.zRank(makeByteKey(key), member), ProtocolCommand.ZRANK, args);
	}

	@Override
	public Tuple zPopMax(final String key){
		return execute((client)->client.zPopMax(makeRawKey(key)), ProtocolCommand.ZPOPMAX,
				new CommandArguments("key", key));
	}

	@Override
	public Tuple zPopMax(final byte[] key){
		return execute((client)->client.zPopMax(makeByteKey(key)), ProtocolCommand.ZPOPMAX,
				new CommandArguments("key", key));
	}

	@Override
	public Set<Tuple> zPopMax(final String key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return execute((client)->client.zPopMax(makeRawKey(key), count), ProtocolCommand.ZPOPMAX, args);
	}

	@Override
	public Set<Tuple> zPopMax(final byte[] key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return execute((client)->client.zPopMax(makeByteKey(key), count), ProtocolCommand.ZPOPMAX, args);
	}

	@Override
	public Tuple zPopMin(final String key){
		return execute((client)->client.zPopMin(makeRawKey(key)), ProtocolCommand.ZPOPMIN,
				new CommandArguments("key", key));
	}

	@Override
	public Tuple zPopMin(final byte[] key){
		return execute((client)->client.zPopMin(makeByteKey(key)), ProtocolCommand.ZPOPMIN,
				new CommandArguments("key", key));
	}

	@Override
	public Set<Tuple> zPopMin(final String key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return execute((client)->client.zPopMin(makeRawKey(key), count), ProtocolCommand.ZPOPMIN, args);
	}

	@Override
	public Set<Tuple> zPopMin(final byte[] key, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("count", count);
		return execute((client)->client.zPopMin(makeByteKey(key), count), ProtocolCommand.ZPOPMIN, args);
	}

	@Override
	public Long zRem(final String key, final String... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		return execute((client)->client.zRem(makeRawKey(key), members), ProtocolCommand.ZREM, args);
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members){
		final CommandArguments args = CommandArguments.create("key", key).put("members", members);
		return execute((client)->client.zRem(makeByteKey(key), members), ProtocolCommand.ZREM, args);
	}

	@Override
	public Long zRemRangeByLex(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREMRANGEBYLEX,
				args);
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREMRANGEBYLEX,
				args);
	}

	@Override
	public Long zRemRangeByLex(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREMRANGEBYLEX,
				args);
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREMRANGEBYLEX,
				args);
	}

	@Override
	public Long zRemRangeByLex(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREMRANGEBYLEX,
				args);
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREMRANGEBYLEX,
				args);
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREMRANGEBYSCORE,
				args);
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREMRANGEBYSCORE
				, args);
	}

	@Override
	public Long zRemRangeByScore(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREMRANGEBYSCORE,
				args);
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREMRANGEBYSCORE
				, args);
	}

	@Override
	public Long zRemRangeByScore(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREMRANGEBYSCORE,
				args);
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRemRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREMRANGEBYSCORE
				, args);
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRemRangeByRank(makeRawKey(key), start, end), ProtocolCommand.ZREMRANGEBYRANK,
				args);
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRemRangeByRank(makeByteKey(key), start, end), ProtocolCommand.ZREMRANGEBYRANK
				, args);
	}

	@Override
	public Set<String> zRevRange(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRevRange(makeRawKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
	}

	@Override
	public Set<byte[]> zRevRange(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRevRange(makeByteKey(key), start, end), ProtocolCommand.ZREVRANGE, args);
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRevRangeWithScores(makeRawKey(key), start, end), ProtocolCommand.ZREVRANGE,
				args);
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.zRevRangeWithScores(makeByteKey(key), start, end), ProtocolCommand.ZREVRANGE,
				args);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX,
				args);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX,
				args);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX,
				args);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX,
				args);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX,
				args);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYLEX,
				args);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final double min, final double max, final long offset,
									  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final long offset,
									  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final long min, final long max, final long offset,
									  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max, final long offset,
									  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max, final long offset,
									  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByLex(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									  final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByLex(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYLEX, args);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE,
				args);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE
				, args);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE,
				args);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE
				, args);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE,
				args);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max), ProtocolCommand.ZREVRANGEBYSCORE
				, args);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max, final long offset,
										final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final long offset,
										final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final long min, final long max, final long offset,
										final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max, final long offset,
										final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max, final long offset,
										final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScore(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final long offset,
										final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScore(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max);
		return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double max, final double min,
												 final long offset,
												 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
												 final long offset,
												 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max, final long offset,
												 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max, final long offset,
												 final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max,
												 final long offset, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScoreWithScores(makeRawKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
												 final long offset, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("min", min).put("max", max)
				.put("offset", offset).put("count", count);
		return execute((client)->client.zRevRangeByScoreWithScores(makeByteKey(key), min, max, offset, count),
				ProtocolCommand.ZREVRANGEBYSCORE, args);
	}

	@Override
	public Long zRevRank(final String key, final String member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		return execute((client)->client.zRevRank(makeRawKey(key), member), ProtocolCommand.ZREVRANK, args);
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		return execute((client)->client.zRevRank(makeByteKey(key), member), ProtocolCommand.ZREVRANK, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return execute((client)->client.zScan(makeRawKey(key), cursor), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return execute((client)->client.zScan(makeByteKey(key), cursor), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return execute((client)->client.zScan(makeRawKey(key), cursor), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor);
		return execute((client)->client.zScan(makeByteKey(key), cursor), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.zScan(makeRawKey(key), cursor, pattern), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.zScan(makeByteKey(key), cursor, pattern), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.zScan(makeRawKey(key), cursor, pattern), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern);
		return execute((client)->client.zScan(makeByteKey(key), cursor, pattern), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.zScan(makeRawKey(key), cursor, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.zScan(makeByteKey(key), cursor, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.zScan(makeRawKey(key), cursor, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("count", count);
		return execute((client)->client.zScan(makeByteKey(key), cursor, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		return execute((client)->client.zScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		return execute((client)->client.zScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put("pattern", pattern)
				.put("count", count);
		return execute((client)->client.zScan(makeRawKey(key), cursor, pattern, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final long count){
		final CommandArguments args = CommandArguments.create("key", key).put("cursor", cursor).put(
				"pattern", pattern).put("count", count);
		return execute((client)->client.zScan(makeByteKey(key), cursor, pattern, count), ProtocolCommand.ZSCAN, args);
	}

	@Override
	public Double zScore(final String key, final String member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		return execute((client)->client.zScore(makeRawKey(key), member), ProtocolCommand.ZSCORE, args);
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member){
		final CommandArguments args = CommandArguments.create("key", key).put("member", member);
		return execute((client)->client.zScore(makeByteKey(key), member), ProtocolCommand.ZSCORE, args);
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		return execute((client)->client.zUnionStore(makeRawKey(destKey), makeRawKeys(keys)),
				ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		return execute((client)->client.zUnionStore(makeByteKey(destKey), makeByteKeys(keys)),
				ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("aggregate", aggregate)
				.put("keys", keys);
		return execute((client)->client.zUnionStore(makeRawKey(destKey), aggregate, makeRawKeys(keys)),
				ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("aggregate", aggregate)
				.put("keys", keys);
		return execute((client)->client.zUnionStore(makeByteKey(destKey), aggregate, makeByteKeys(keys)),
				ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long zUnionStore(final String destKey, final double[] weights, final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		return execute((client)->client.zUnionStore(makeRawKey(destKey), weights, makeRawKeys(keys)),
				ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", keys);
		return execute((client)->client.zUnionStore(makeByteKey(destKey), weights, makeByteKeys(keys)),
				ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final double[] weights,
							final String... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("aggregate", aggregate)
				.put("keys", keys);
		return execute((client)->client.zUnionStore(makeRawKey(destKey), aggregate, weights, makeRawKeys(keys)),
				ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
							final byte[]... keys){
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("aggregate", aggregate)
				.put("keys", keys);
		return execute((client)->client.zUnionStore(makeByteKey(destKey), aggregate, weights, makeByteKeys(keys)),
				ProtocolCommand.ZUNIONSTORE, args);
	}

	@Override
	public Long append(final String key, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.append(makeRawKey(key), value), ProtocolCommand.APPEND, args);
	}

	@Override
	public Long append(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.append(makeByteKey(key), value), ProtocolCommand.APPEND, args);
	}

	@Override
	public Long bitCount(final String key){
		return execute((client)->client.bitCount(makeRawKey(key)), ProtocolCommand.BITCOUNT,
				new CommandArguments("key", key));
	}

	@Override
	public Long bitCount(final byte[] key){
		return execute((client)->client.bitCount(makeByteKey(key)), ProtocolCommand.BITCOUNT,
				new CommandArguments("key", key));
	}

	@Override
	public Long bitCount(final String key, final int start, final int end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.bitCount(makeRawKey(key), start, end), ProtocolCommand.BITCOUNT, args);
	}

	@Override
	public Long bitCount(final byte[] key, final int start, final int end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.bitCount(makeByteKey(key), start, end), ProtocolCommand.BITCOUNT, args);
	}

	@Override
	public Long bitCount(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.bitCount(makeRawKey(key), start, end), ProtocolCommand.BITCOUNT, args);
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.bitCount(makeByteKey(key), start, end), ProtocolCommand.BITCOUNT, args);
	}

	@Override
	public List<Long> bitField(final String key, final String... arguments){
		final CommandArguments args = CommandArguments.create("key", key).put("arguments", arguments);
		return execute((client)->client.bitField(makeRawKey(key), arguments), ProtocolCommand.BITFIELD, args);
	}

	@Override
	public List<Long> bitField(final byte[] key, final byte[]... arguments){
		final CommandArguments args = CommandArguments.create("key", key).put("arguments", arguments);
		return execute((client)->client.bitField(makeByteKey(key), arguments), ProtocolCommand.BITFIELD, args);
	}

	@Override
	public Long bitOp(final BitOperation operation, final String destKey, final String... keys){
		final CommandArguments args = CommandArguments.create("operation", operation).put("destKey", destKey)
				.put("keys", keys);
		return execute((client)->client.bitOp(operation, makeRawKey(destKey), makeRawKeys(keys)),
				ProtocolCommand.BITOP, args);
	}

	@Override
	public Long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys){
		final CommandArguments args = CommandArguments.create("operation", operation).put("destKey", destKey)
				.put("keys", keys);
		return execute((client)->client.bitOp(operation, makeByteKey(destKey), makeByteKeys(keys)),
				ProtocolCommand.BITOP, args);
	}

	@Override
	public Long bitPos(final String key, final boolean value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.bitPos(makeRawKey(key), value), ProtocolCommand.BITPOS, args);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.bitPos(makeByteKey(key), value), ProtocolCommand.BITPOS, args);
	}

	@Override
	public Long bitPos(final String key, final boolean value, final int start, final int end){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("start", start)
				.put("end", end);
		return execute((client)->client.bitPos(makeRawKey(key), value, start, end), ProtocolCommand.BITPOS, args);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final int start, final int end){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("start", start)
				.put("end", end);
		return execute((client)->client.bitPos(makeByteKey(key), value, start, end), ProtocolCommand.BITPOS, args);
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("start", start)
				.put("end", end);
		return execute((client)->client.bitPos(makeRawKey(key), value, start, end), ProtocolCommand.BITPOS, args);
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("start", start)
				.put("end", end);
		return execute((client)->client.bitPos(makeByteKey(key), value, start, end), ProtocolCommand.BITPOS, args);
	}

	@Override
	public Long decr(final String key){
		return execute((client)->client.decr(makeRawKey(key)), ProtocolCommand.DECR, new CommandArguments("key", key));
	}

	@Override
	public Long decr(final byte[] key){
		return execute((client)->client.decr(makeByteKey(key)), ProtocolCommand.DECR, new CommandArguments("key", key));
	}

	@Override
	public Long decrBy(final String key, final int value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.decrBy(makeRawKey(key), value), ProtocolCommand.DECRBY, args);
	}

	@Override
	public Long decrBy(final byte[] key, final int value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.decrBy(makeByteKey(key), value), ProtocolCommand.DECRBY, args);
	}

	@Override
	public Long decrBy(final String key, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.decrBy(makeRawKey(key), value), ProtocolCommand.DECRBY, args);
	}

	@Override
	public Long decrBy(final byte[] key, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.decrBy(makeByteKey(key), value), ProtocolCommand.DECRBY, args);
	}

	@Override
	public String get(final String key){
		return execute((client)->client.get(makeRawKey(key)), ProtocolCommand.GET, new CommandArguments("key", key));
	}

	@Override
	public byte[] get(final byte[] key){
		return execute((client)->client.get(makeByteKey(key)), ProtocolCommand.GET, new CommandArguments("key", key));
	}

	@Override
	public Status getBit(final String key, final int offset){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset);
		return execute((client)->client.getBit(makeRawKey(key), offset), ProtocolCommand.GETBIT, args);
	}

	@Override
	public Status getBit(final byte[] key, final int offset){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset);
		return execute((client)->client.getBit(makeByteKey(key), offset), ProtocolCommand.GETBIT, args);
	}

	@Override
	public Status getBit(final String key, final long offset){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset);
		return execute((client)->client.getBit(makeRawKey(key), offset), ProtocolCommand.GETBIT, args);
	}

	@Override
	public Status getBit(final byte[] key, final long offset){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset);
		return execute((client)->client.getBit(makeByteKey(key), offset), ProtocolCommand.GETBIT, args);
	}

	@Override
	public String getRange(final String key, final int start, final int end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.getRange(makeRawKey(key), start, end), ProtocolCommand.GETRANGE, args);
	}

	@Override
	public byte[] getRange(final byte[] key, final int start, final int end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.getRange(makeByteKey(key), start, end), ProtocolCommand.GETRANGE, args);
	}

	@Override
	public String getRange(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.getRange(makeRawKey(key), start, end), ProtocolCommand.GETRANGE, args);
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.getRange(makeByteKey(key), start, end), ProtocolCommand.GETRANGE, args);
	}

	@Override
	public String getSet(final String key, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.getSet(makeRawKey(key), value), ProtocolCommand.GETSET, args);
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.getSet(makeByteKey(key), value), ProtocolCommand.GETSET, args);
	}

	@Override
	public String getEx(final String key, final GetExArgument getExArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("getExArgument", getExArgument);
		return execute((client)->client.getEx(makeRawKey(key), getExArgument), ProtocolCommand.GETEX, args);
	}

	@Override
	public byte[] getEx(byte[] key, GetExArgument getExArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("getExArgument", getExArgument);
		return execute((client)->client.getEx(makeByteKey(key), getExArgument), ProtocolCommand.GETEX, args);
	}

	@Override
	public String getDel(final String key){
		return execute((client)->client.getDel(makeRawKey(key)), ProtocolCommand.GETDEL,
				new CommandArguments("key", key));
	}

	@Override
	public byte[] getDel(byte[] key){
		return execute((client)->client.getDel(makeByteKey(key)), ProtocolCommand.GETDEL,
				new CommandArguments("key", key));
	}

	@Override
	public Long incr(final String key){
		return execute((client)->client.incr(makeRawKey(key)), ProtocolCommand.INCR, new CommandArguments("key", key));
	}

	@Override
	public Long incr(final byte[] key){
		return execute((client)->client.incr(makeByteKey(key)), ProtocolCommand.INCR, new CommandArguments("key", key));
	}

	@Override
	public Long incrBy(final String key, final int value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.incrBy(makeRawKey(key), value), ProtocolCommand.INCRBY, args);
	}

	@Override
	public Long incrBy(final byte[] key, final int value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.incrBy(makeByteKey(key), value), ProtocolCommand.INCRBY, args);
	}

	@Override
	public Long incrBy(final String key, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.incrBy(makeRawKey(key), value), ProtocolCommand.INCRBY, args);
	}

	@Override
	public Long incrBy(final byte[] key, final long value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.incrBy(makeByteKey(key), value), ProtocolCommand.INCRBY, args);
	}

	@Override
	public Double incrByFloat(final String key, final float value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.incrByFloat(makeRawKey(key), value), ProtocolCommand.INCRBYFLOAT, args);
	}

	@Override
	public Double incrByFloat(final byte[] key, final float value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.incrByFloat(makeByteKey(key), value), ProtocolCommand.INCRBYFLOAT, args);
	}

	@Override
	public Double incrByFloat(final String key, final double value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.incrByFloat(makeRawKey(key), value), ProtocolCommand.INCRBYFLOAT, args);
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.incrByFloat(makeByteKey(key), value), ProtocolCommand.INCRBYFLOAT, args);
	}

	@Override
	public List<String> mGet(final String... keys){
		return execute((client)->client.mGet(makeRawKeys(keys)), ProtocolCommand.MGET,
				new CommandArguments("keys", keys));
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys){
		return execute((client)->client.mGet(makeByteKeys(keys)), ProtocolCommand.MGET,
				new CommandArguments("keys", keys));
	}

	@Override
	public Status mSet(final Map<String, String> values){
		return execute((client)->client.mSet(values), ProtocolCommand.MSET, new CommandArguments("values", values));
	}

	@Override
	public Status mSetNx(final Map<String, String> values){
		return execute((client)->client.mSetNx(values), ProtocolCommand.MSET, new CommandArguments("values", values));
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		return execute((client)->client.pSetEx(makeRawKey(key), value, lifetime), ProtocolCommand.PSETEX, args);
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		return execute((client)->client.pSetEx(makeByteKey(key), value, lifetime), ProtocolCommand.PSETEX, args);
	}

	@Override
	public Status set(final String key, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.set(makeRawKey(key), value), ProtocolCommand.SET, args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.set(makeByteKey(key), value), ProtocolCommand.SET, args);
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value)
				.put("setArgument", setArgument);
		return execute((client)->client.set(makeRawKey(key), value, setArgument), ProtocolCommand.SET, args);
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value)
				.put("setArgument", setArgument);
		return execute((client)->client.set(makeByteKey(key), value, setArgument), ProtocolCommand.SET, args);
	}

	@Override
	public Status setBit(final String key, final int offset, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setBit(makeRawKey(key), offset, value), ProtocolCommand.SETBIT, args);
	}

	@Override
	public Status setBit(final byte[] key, final int offset, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setBit(makeByteKey(key), offset, value), ProtocolCommand.SETBIT, args);
	}

	@Override
	public Status setBit(final String key, final long offset, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setBit(makeRawKey(key), offset, value), ProtocolCommand.SETBIT, args);
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setBit(makeByteKey(key), offset, value), ProtocolCommand.SETBIT, args);
	}

	@Override
	public Status setBit(final String key, final int offset, final boolean value){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setBit(makeRawKey(key), offset, value), ProtocolCommand.SETBIT, args);
	}

	@Override
	public Status setBit(final byte[] key, final int offset, final boolean value){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setBit(makeByteKey(key), offset, value), ProtocolCommand.SETBIT, args);
	}

	@Override
	public Status setBit(final String key, final long offset, final boolean value){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setBit(makeRawKey(key), offset, value), ProtocolCommand.SETBIT, args);
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final boolean value){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setBit(makeByteKey(key), offset, value), ProtocolCommand.SETBIT, args);
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		return execute((client)->client.setEx(makeRawKey(key), value, lifetime), ProtocolCommand.SETEX, args);
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value).put("lifetime", lifetime);
		return execute((client)->client.setEx(makeByteKey(key), value, lifetime), ProtocolCommand.SETEX, args);
	}

	@Override
	public Status setNx(final String key, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.setNx(makeRawKey(key), value), ProtocolCommand.SETNX, args);
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("value", value);
		return execute((client)->client.setNx(makeByteKey(key), value), ProtocolCommand.SETNX, args);
	}

	@Override
	public Long setRange(final String key, final int offset, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setRange(makeRawKey(key), offset, value), ProtocolCommand.SETRANGE, args);
	}

	@Override
	public Long setRange(final byte[] key, final int offset, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setRange(makeByteKey(key), offset, value), ProtocolCommand.SETRANGE, args);
	}

	@Override
	public Long setRange(final String key, final long offset, final String value){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setRange(makeRawKey(key), offset, value), ProtocolCommand.SETRANGE, args);
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value){
		final CommandArguments args = CommandArguments.create("key", key).put("offset", offset).put("value", value);
		return execute((client)->client.setRange(makeByteKey(key), offset, value), ProtocolCommand.SETRANGE, args);
	}

	@Override
	public Long strlen(final String key){
		return execute((client)->client.strlen(makeRawKey(key)), ProtocolCommand.STRLEN,
				new CommandArguments("key", key));
	}

	@Override
	public Long strlen(final byte[] key){
		return execute((client)->client.strlen(makeByteKey(key)), ProtocolCommand.STRLEN,
				new CommandArguments("key", key));
	}

	@Override
	public String substr(final String key, final int start, final int end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.substr(makeRawKey(key), start, end), ProtocolCommand.SUBSTR, args);
	}

	@Override
	public byte[] substr(final byte[] key, final int start, final int end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.substr(makeByteKey(key), start, end), ProtocolCommand.SUBSTR, args);
	}

	@Override
	public String substr(final String key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.substr(makeRawKey(key), start, end), ProtocolCommand.SUBSTR, args);
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end){
		final CommandArguments args = CommandArguments.create("key", key).put("start", start).put("end", end);
		return execute((client)->client.substr(makeByteKey(key), start, end), ProtocolCommand.SUBSTR, args);
	}

	@Override
	public void discard(){
		execute((client)->{
			client.discard();
			return null;
		}, ProtocolCommand.DISCARD);
	}

	@Override
	public List<Object> exec(){
		return execute((client)->client.exec(), ProtocolCommand.EXEC);
	}

	@Override
	public Status multi(){
		return execute((client)->client.multi(), ProtocolCommand.MULTI);
	}

	@Override
	public Status unwatch(){
		return execute((client)->client.unwatch(), ProtocolCommand.UNWATCH);
	}

	@Override
	public Status watch(final String... keys){
		return execute((client)->client.watch(makeRawKeys(keys)), ProtocolCommand.WATCH);
	}

	@Override
	public Status watch(final byte[]... keys){
		return execute((client)->client.watch(makeByteKeys(keys)), ProtocolCommand.WATCH);
	}

}
