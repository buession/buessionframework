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
import com.buession.redis.core.AclLog;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.BitCountOption;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.BumpEpoch;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.ClusterFailoverOption;
import com.buession.redis.core.ClusterInfo;
import com.buession.redis.core.ClusterResetOption;
import com.buession.redis.core.ClusterSetSlotOption;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.core.Direction;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.GtLt;
import com.buession.redis.core.Info;
import com.buession.redis.core.KeyedZSetElement;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.MemoryStats;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.Module;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.RedisClusterServer;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.SlowLog;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;
import com.buession.redis.core.AclUser;
import com.buession.redis.core.ZRangeBy;

import java.util.LinkedHashMap;
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
	public long bitCount(final String key){
		return bitMapOpsExecute((ops)->ops.bitCount(key));
	}

	@Override
	public long bitCount(final byte[] key){
		return bitMapOpsExecute((ops)->ops.bitCount(key));
	}

	@Override
	public long bitCount(final String key, final long start, final long end){
		return bitMapOpsExecute((ops)->ops.bitCount(key, start, end));
	}

	@Override
	public long bitCount(final byte[] key, final long start, final long end){
		return bitMapOpsExecute((ops)->ops.bitCount(key, start, end));
	}

	@Override
	public long bitCount(final String key, final long start, final long end, final BitCountOption bitCountOption){
		return bitMapOpsExecute((ops)->ops.bitCount(key, start, end, bitCountOption));
	}

	@Override
	public long bitCount(final byte[] key, final long start, final long end, final BitCountOption bitCountOption){
		return bitMapOpsExecute((ops)->ops.bitCount(key, start, end, bitCountOption));
	}

	@Override
	public List<Long> bitField(final String key, final String... arguments){
		return bitMapOpsExecute((ops)->ops.bitField(key, arguments));
	}

	@Override
	public List<Long> bitField(final byte[] key, final byte[]... arguments){
		return bitMapOpsExecute((ops)->ops.bitField(key, arguments));
	}

	@Override
	public List<Long> bitFieldRo(final String key, final String... arguments){
		return bitMapOpsExecute((ops)->ops.bitFieldRo(key, arguments));
	}

	@Override
	public List<Long> bitFieldRo(final byte[] key, final byte[]... arguments){
		return bitMapOpsExecute((ops)->ops.bitFieldRo(key, arguments));
	}

	@Override
	public long bitOp(final BitOperation operation, final String destKey, final String... keys){
		return bitMapOpsExecute((ops)->ops.bitOp(key, destKey, keys));
	}

	@Override
	public long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys){
		return bitMapOpsExecute((ops)->ops.bitOp(key, destKey, keys));
	}

	@Override
	public long bitPos(final String key, final boolean value){
		return bitMapOpsExecute((ops)->ops.bitPos(key, value));
	}

	@Override
	public long bitPos(final byte[] key, final boolean value){
		return bitMapOpsExecute((ops)->ops.bitPos(key, value));
	}

	@Override
	public long bitPos(final String key, final boolean value, final long start, final long end){
		return bitMapOpsExecute((ops)->ops.bitPos(key, value, start, end));
	}

	@Override
	public long bitPos(final byte[] key, final boolean value, final long start, final long end){
		return bitMapOpsExecute((ops)->ops.bitPos(key, value, start, end));
	}

	@Override
	public boolean getBit(final String key, final long offset){
		return bitMapOpsExecute((ops)->ops.getBit(key, offset));
	}

	@Override
	public boolean getBit(final byte[] key, final long offset){
		return bitMapOpsExecute((ops)->ops.getBit(key, offset));
	}

	@Override
	public boolean setBit(final String key, final long offset, final boolean value){
		return bitMapOpsExecute((ops)->ops.setBit(key, offset, value));
	}

	@Override
	public boolean setBit(final byte[] key, final long offset, final boolean value){
		return bitMapOpsExecute((ops)->ops.setBit(key, offset, value));
	}

	@Override
	public String clusterMyId(){
		return clusterOpsExecute((ops)->ops.clusterMyId());
	}

	@Override
	public Status clusterAddSlots(final int... slots){
		return clusterOpsExecute((ops)->ops.clusterAddSlots(slots));
	}

	@Override
	public List<ClusterSlot> clusterSlots(){
		return clusterOpsExecute((ops)->ops.clusterSlots());
	}

	@Override
	public int clusterCountFailureReports(final String nodeId){
		return clusterOpsExecute((ops)->ops.clusterCountFailureReports(nodeId));
	}

	@Override
	public int clusterCountFailureReports(final byte[] nodeId){
		return clusterOpsExecute((ops)->ops.clusterCountFailureReports(nodeId));
	}

	@Override
	public long clusterCountKeysInSlot(final int slot){
		return clusterOpsExecute((ops)->ops.clusterCountKeysInSlot(slot));
	}

	@Override
	public Status clusterDelSlots(final int... slots){
		return clusterOpsExecute((ops)->ops.clusterDelSlots(slots));
	}

	@Override
	public Status clusterFlushSlots(){
		return clusterOpsExecute((ops)->ops.clusterFlushSlots());
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption){
		return clusterOpsExecute((ops)->ops.clusterFailover(clusterFailoverOption));
	}

	@Override
	public Status clusterForget(final String nodeId){
		return clusterOpsExecute((ops)->ops.clusterForget(nodeId));
	}

	@Override
	public Status clusterForget(final byte[] nodeId){
		return clusterOpsExecute((ops)->ops.clusterForget(nodeId));
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final long count){
		return clusterOpsExecute((ops)->ops.clusterGetKeysInSlot(slot, count));
	}

	@Override
	public long clusterKeySlot(final String key){
		return clusterOpsExecute((ops)->ops.clusterKeySlot(key));
	}

	@Override
	public long clusterKeySlot(final byte[] key){
		return clusterOpsExecute((ops)->ops.clusterKeySlot(key));
	}

	@Override
	public ClusterInfo clusterInfo(){
		return clusterOpsExecute((ops)->ops.clusterInfo());
	}

	@Override
	public Status clusterMeet(final String ip, final int port){
		return clusterOpsExecute((ops)->ops.clusterMeet(ip, port));
	}

	@Override
	public List<RedisClusterServer> clusterNodes(){
		return clusterOpsExecute((ops)->ops.clusterNodes());
	}

	@Override
	public List<RedisClusterServer> clusterSlaves(final String nodeId){
		return clusterOpsExecute((ops)->ops.clusterSlaves(nodeId));
	}

	@Override
	public List<RedisClusterServer> clusterSlaves(final byte[] nodeId){
		return clusterOpsExecute((ops)->ops.clusterSlaves(nodeId));
	}

	@Override
	public List<RedisClusterServer> clusterReplicas(final String nodeId){
		return clusterOpsExecute((ops)->ops.clusterReplicas(nodeId));
	}

	@Override
	public List<RedisClusterServer> clusterReplicas(final byte[] nodeId){
		return clusterOpsExecute((ops)->ops.clusterReplicas(nodeId));
	}

	@Override
	public Status clusterReplicate(final String nodeId){
		return clusterOpsExecute((ops)->ops.clusterReplicate(nodeId));
	}

	@Override
	public Status clusterReplicate(final byte[] nodeId){
		return clusterOpsExecute((ops)->ops.clusterReplicate(nodeId));
	}

	@Override
	public Status clusterReset(){
		return clusterOpsExecute((ops)->ops.clusterReset());
	}

	@Override
	public Status clusterReset(final ClusterResetOption clusterResetOption){
		return clusterOpsExecute((ops)->ops.clusterReset(clusterResetOption));
	}

	@Override
	public Status clusterSaveConfig(){
		return clusterOpsExecute((ops)->ops.clusterSaveConfig());
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch){
		return clusterOpsExecute((ops)->ops.clusterSetConfigEpoch(configEpoch));
	}

	@Override
	public BumpEpoch clusterBumpEpoch(){
		return clusterOpsExecute((ops)->ops.clusterBumpEpoch());
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId){
		return clusterOpsExecute((ops)->ops.clusterSetSlot(slot, setSlotOption, nodeId));
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final byte[] nodeId){
		return clusterOpsExecute((ops)->ops.clusterSetSlot(slot, setSlotOption, nodeId));
	}

	@Override
	public Status asking(){
		return clusterOpsExecute((ops)->ops.asking());
	}

	@Override
	public Status readWrite(){
		return clusterOpsExecute((ops)->ops.readWrite());
	}

	@Override
	public Status readOnly(){
		return clusterOpsExecute((ops)->ops.readOnly());
	}

	@Override
	public Status auth(final String user, final String password){
		return connectionOpsExecute((ops)->ops.auth(user, password));
	}

	@Override
	public Status auth(final byte[] user, final byte[] password){
		return connectionOpsExecute((ops)->ops.auth(user, password));
	}

	@Override
	public Status auth(final String password){
		return connectionOpsExecute((ops)->ops.auth(password));
	}

	@Override
	public Status auth(final byte[] password){
		return connectionOpsExecute((ops)->ops.auth(password));
	}

	@Override
	public String echo(final String str){
		return connectionOpsExecute((ops)->ops.echo(str));
	}

	@Override
	public byte[] echo(final byte[] str){
		return connectionOpsExecute((ops)->ops.echo(str));
	}

	@Override
	public Status ping(){
		return connectionOpsExecute((ops)->ops.ping());
	}

	@Override
	public Status reset(){
		return connectionOpsExecute((ops)->ops.reset());
	}

	@Override
	public Status quit(){
		return connectionOpsExecute((ops)->ops.quit());
	}

	@Override
	public Status select(final int db){
		return connectionOpsExecute((ops)->ops.select(db));
	}

	@Override
	public Status clientCaching(final boolean isYes){
		return connectionOpsExecute((ops)->ops.clientCaching(isYes));
	}

	@Override
	public long clientId(){
		return connectionOpsExecute((ops)->ops.clientId());
	}

	@Override
	public Status clientSetName(final String name){
		return connectionOpsExecute((ops)->ops.clientSetName(name));
	}

	@Override
	public Status clientSetName(final byte[] name){
		return connectionOpsExecute((ops)->ops.clientSetName(name));
	}

	@Override
	public String clientGetName(){
		return connectionOpsExecute((ops)->ops.clientGetName());
	}

	@Override
	public Integer clientGetRedir(){
		return connectionOpsExecute((ops)->ops.clientGetRedir());
	}

	@Override
	public List<Client> clientList(){
		return connectionOpsExecute((ops)->ops.clientList());
	}

	@Override
	public List<Client> clientList(final ClientType clientType){
		return connectionOpsExecute((ops)->ops.clientList(clientType));
	}

	@Override
	public Client clientInfo(){
		return connectionOpsExecute((ops)->ops.clientInfo());
	}

	@Override
	public Status clientPause(final int timeout){
		return connectionOpsExecute((ops)->ops.clientPause(timeout));
	}

	@Override
	public Status clientReply(final ClientReply option){
		return connectionOpsExecute((ops)->ops.clientReply(option));
	}

	@Override
	public Status clientKill(final String host, final int port){
		return connectionOpsExecute((ops)->ops.clientKill(host, port));
	}

	@Override
	public Status clientUnblock(final int clientId){
		return connectionOpsExecute((ops)->ops.clientUnblock(clientId));
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type){
		return connectionOpsExecute((ops)->ops.clientUnblock(clientId, type));
	}

	@Override
	public long geoAdd(final String key, final String member, final double longitude, final double latitude){
		return geoOpsExecute((ops)->ops.geoAdd(rawKey(key), member, longitude, latitude));
	}

	@Override
	public long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
		return geoOpsExecute((ops)->ops.geoAdd(rawKey(key), member, longitude, latitude));
	}

	@Override
	public long geoAdd(final String key, final Map<String, Geo> memberCoordinates){
		return geoOpsExecute((ops)->ops.geoAdd(rawKey(key), memberCoordinates));
	}

	@Override
	public long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates){
		return geoOpsExecute((ops)->ops.geoAdd(rawKey(key), memberCoordinates));
	}

	@Override
	public List<String> geoHash(final String key, final String... members){
		return geoOpsExecute((ops)->ops.geoHash(rawKey(key), members));
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members){
		return geoOpsExecute((ops)->ops.geoHash(rawKey(key), members));
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members){
		return geoOpsExecute((ops)->ops.geoPos(rawKey(key), members));
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members){
		return geoOpsExecute((ops)->ops.geoPos(rawKey(key), members));
	}

	@Override
	public double geoDist(final String key, final String member1, final String member2){
		return geoOpsExecute((ops)->ops.geoDist(rawKey(key), member1, member2));
	}

	@Override
	public double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
		return geoOpsExecute((ops)->ops.geoDist(rawKey(key), member1, member2));
	}

	@Override
	public double geoDist(final String key, final String member1, final String member2, final GeoUnit unit){
		return geoOpsExecute((ops)->ops.geoDist(rawKey(key), member1, member2, unit));
	}

	@Override
	public double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit){
		return geoOpsExecute((ops)->ops.geoDist(rawKey(key), member1, member2, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit){
		return geoOpsExecute((ops)->ops.geoRadius(rawKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit){
		return geoOpsExecute((ops)->ops.geoRadius(rawKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument){
		return geoOpsExecute((ops)->ops.geoRadius(rawKey(key), longitude, latitude, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument){
		return geoOpsExecute((ops)->ops.geoRadius(rawKey(key), longitude, latitude, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit){
		return geoOpsExecute((ops)->ops.geoRadiusRo(rawKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit){
		return geoOpsExecute((ops)->ops.geoRadiusRo(rawKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument){
		return geoOpsExecute((ops)->ops.geoRadiusRo(rawKey(key), longitude, latitude, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument){
		return geoOpsExecute((ops)->ops.geoRadiusRo(rawKey(key), longitude, latitude, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit){
		return geoOpsExecute((ops)->ops.geoRadiusByMember(rawKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit){
		return geoOpsExecute((ops)->ops.geoRadiusByMember(rawKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		return geoOpsExecute((ops)->ops.geoRadiusByMember(rawKey(key), member, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		return geoOpsExecute((ops)->ops.geoRadiusByMember(rawKey(key), member, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit){
		return geoOpsExecute((ops)->ops.geoRadiusByMemberRo(rawKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit){
		return geoOpsExecute((ops)->ops.geoRadiusByMemberRo(rawKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		return geoOpsExecute((ops)->ops.geoRadiusByMemberRo(rawKey(key), member, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		return geoOpsExecute((ops)->ops.geoRadiusByMemberRo(rawKey(key), member, radius, unit, geoRadiusArgument));
	}

	@Override
	public long hDel(final String key, final String... fields){
		return hashOpsExecute((ops)->ops.hDel(rawKey(key), fields));
	}

	@Override
	public long hDel(final byte[] key, final byte[]... fields){
		return hashOpsExecute((ops)->ops.hDel(rawKey(key), fields));
	}

	@Override
	public boolean hExists(final String key, final String field){
		return hashOpsExecute((ops)->ops.hExists(rawKey(key), field));
	}

	@Override
	public boolean hExists(final byte[] key, final byte[] field){
		return hashOpsExecute((ops)->ops.hExists(rawKey(key), field));
	}

	@Override
	public String hGet(final String key, final String field){
		return hashOpsExecute((ops)->ops.hGet(rawKey(key), field));
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field){
		return hashOpsExecute((ops)->ops.hGet(rawKey(key), field));
	}

	@Override
	public Map<String, String> hGetAll(final String key){
		return hashOpsExecute((ops)->ops.hGetAll(rawKey(key)));
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key){
		return hashOpsExecute((ops)->ops.hGetAll(rawKey(key)));
	}

	@Override
	public long hIncrBy(final String key, final String field, final long value){
		return hashOpsExecute((ops)->ops.hIncrBy(rawKey(key), field, value));
	}

	@Override
	public long hIncrBy(final byte[] key, final byte[] field, final long value){
		return hashOpsExecute((ops)->ops.hIncrBy(rawKey(key), field, value));
	}

	@Override
	public double hIncrByFloat(final String key, final String field, final double value){
		return hashOpsExecute((ops)->ops.hIncrByFloat(rawKey(key), field, value));
	}

	@Override
	public double hIncrByFloat(final byte[] key, final byte[] field, final double value){
		return hashOpsExecute((ops)->ops.hIncrByFloat(rawKey(key), field, value));
	}

	@Override
	public Set<String> hKeys(final String key){
		return hashOpsExecute((ops)->ops.hKeys(rawKey(key)));
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key){
		return hashOpsExecute((ops)->ops.hKeys(rawKey(key)));
	}

	@Override
	public long hLen(final String key){
		return hashOpsExecute((ops)->ops.hLen(rawKey(key)));
	}

	@Override
	public long hLen(final byte[] key){
		return hashOpsExecute((ops)->ops.hLen(rawKey(key)));
	}

	@Override
	public List<String> hMGet(final String key, final String... fields){
		return hashOpsExecute((ops)->ops.hMGet(rawKey(key), fields));
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields){
		return hashOpsExecute((ops)->ops.hMGet(rawKey(key), fields));
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data){
		return hashOpsExecute((ops)->ops.hMSet(rawKey(key), data));
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data){
		return hashOpsExecute((ops)->ops.hMSet(rawKey(key), data));
	}

	@Override
	public String hRandField(final String key){
		return hashOpsExecute((ops)->ops.hRandField(rawKey(key)));
	}

	@Override
	public byte[] hRandField(final byte[] key){
		return hashOpsExecute((ops)->ops.hRandField(rawKey(key)));
	}

	@Override
	public List<String> hRandField(final String key, final long count){
		return hashOpsExecute((ops)->ops.hRandField(rawKey(key), count));
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final long count){
		return hashOpsExecute((ops)->ops.hRandField(rawKey(key), count));
	}

	@Override
	public Map<String, String> hRandFieldWithValues(final String key, final long count){
		return hashOpsExecute((ops)->ops.hRandFieldWithValues(rawKey(key), count));
	}

	@Override
	public Map<byte[], byte[]> hRandFieldWithValues(final byte[] key, final long count){
		return hashOpsExecute((ops)->ops.hRandFieldWithValues(rawKey(key), count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor){
		return hashOpsExecute((ops)->ops.hScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor){
		return hashOpsExecute((ops)->ops.hScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor){
		return hashOpsExecute((ops)->ops.hScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
		return hashOpsExecute((ops)->ops.hScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern){
		return hashOpsExecute((ops)->ops.hScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern){
		return hashOpsExecute((ops)->ops.hScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern){
		return hashOpsExecute((ops)->ops.hScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return hashOpsExecute((ops)->ops.hScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final long count){
		return hashOpsExecute((ops)->ops.hScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final long count){
		return hashOpsExecute((ops)->ops.hScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final long count){
		return hashOpsExecute((ops)->ops.hScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final long count){
		return hashOpsExecute((ops)->ops.hScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern,
												 final long count){
		return hashOpsExecute((ops)->ops.hScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern,
												 final long count){
		return hashOpsExecute((ops)->ops.hScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
												 final long count){
		return hashOpsExecute((ops)->ops.hScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
												 final long count){
		return hashOpsExecute((ops)->ops.hScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public long hSet(final String key, final String field, final String value){
		return hashOpsExecute((ops)->ops.hSet(rawKey(key), field, value));
	}

	@Override
	public long hSet(final byte[] key, final byte[] field, final byte[] value){
		return hashOpsExecute((ops)->ops.hSet(rawKey(key), field, value));
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value){
		return hashOpsExecute((ops)->ops.hSetNx(rawKey(key), field, value));
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value){
		return hashOpsExecute((ops)->ops.hSetNx(rawKey(key), field, value));
	}

	@Override
	public long hStrLen(final String key, final String field){
		return hashOpsExecute((ops)->ops.hStrLen(rawKey(key), field));
	}

	@Override
	public long hStrLen(final byte[] key, final byte[] field){
		return hashOpsExecute((ops)->ops.hStrLen(rawKey(key), field));
	}

	@Override
	public List<String> hVals(final String key){
		return hashOpsExecute((ops)->ops.hVals(rawKey(key)));
	}

	@Override
	public List<byte[]> hVals(final byte[] key){
		return hashOpsExecute((ops)->ops.hVals(rawKey(key)));
	}

	@Override
	public Status pfAdd(final String key, final String... elements){
		return hyperLogLogOpsExecute((ops)->ops.pfAdd(rawKey(key), elements));
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements){
		return hyperLogLogOpsExecute((ops)->ops.pfAdd(rawKey(key), elements));
	}

	@Override
	public Status pfMerge(final String destKey, final String... keys){
		return hyperLogLogOpsExecute((ops)->ops.pfMerge(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys){
		return hyperLogLogOpsExecute((ops)->ops.pfMerge(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public long pfCount(final String... keys){
		return hyperLogLogOpsExecute((ops)->ops.pfCount(rawKeys(keys)));
	}

	@Override
	public long pfCount(final byte[]... keys){
		return hyperLogLogOpsExecute((ops)->ops.pfCount(rawKeys(keys)));
	}

	@Override
	public long del(final String... keys){
		return keyOpsExecute((ops)->ops.del(rawKeys(keys)));
	}

	@Override
	public long del(final byte[]... keys){
		return keyOpsExecute((ops)->ops.del(rawKeys(keys)));
	}

	@Override
	public String dump(final String key){
		return keyOpsExecute((ops)->ops.dump(rawKey(key)));
	}

	@Override
	public byte[] dump(final byte[] key){
		return keyOpsExecute((ops)->ops.dump(rawKey(key)));
	}

	@Override
	public boolean exists(final String key){
		return keyOpsExecute((ops)->ops.exists(rawKey(key)));
	}

	@Override
	public boolean exists(final byte[] key){
		return keyOpsExecute((ops)->ops.exists(rawKey(key)));
	}

	@Override
	public long exists(final String... keys){
		return keyOpsExecute((ops)->ops.exists(rawKeys(keys)));
	}

	@Override
	public long exists(final byte[]... keys){
		return keyOpsExecute((ops)->ops.exists(rawKeys(keys)));
	}

	@Override
	public Status expire(final String key, final int lifetime){
		return keyOpsExecute((ops)->ops.expire(rawKey(key), lifetime));
	}

	@Override
	public Status expire(final byte[] key, final int lifetime){
		return keyOpsExecute((ops)->ops.expire(rawKey(key), lifetime));
	}

	@Override
	public Status expire(final String key, final int lifetime, final ExpireOption expireOption){
		return keyOpsExecute((ops)->ops.expire(rawKey(key), lifetime, expireOption));
	}

	@Override
	public Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption){
		return keyOpsExecute((ops)->ops.expire(rawKey(key), lifetime, expireOption));
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp){
		return keyOpsExecute((ops)->ops.expireAt(rawKey(key), unixTimestamp));
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		return keyOpsExecute((ops)->ops.expireAt(rawKey(key), unixTimestamp));
	}

	@Override
	public Status pExpire(final String key, final int lifetime){
		return keyOpsExecute((ops)->ops.pExpire(rawKey(key), lifetime));
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		return keyOpsExecute((ops)->ops.pExpire(rawKey(key), lifetime));
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp){
		return keyOpsExecute((ops)->ops.pExpireAt(rawKey(key), unixTimestamp));
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		return keyOpsExecute((ops)->ops.pExpireAt(rawKey(key), unixTimestamp));
	}

	@Override
	public Status persist(final String key){
		return keyOpsExecute((ops)->ops.persist(rawKey(key)));
	}

	@Override
	public Status persist(final byte[] key){
		return keyOpsExecute((ops)->ops.persist(rawKey(key)));
	}

	@Override
	public long ttl(final String key){
		return keyOpsExecute((ops)->ops.ttl(rawKey(key)));
	}

	@Override
	public long ttl(final byte[] key){
		return keyOpsExecute((ops)->ops.ttl(rawKey(key)));
	}

	@Override
	public long pTtl(final String key){
		return keyOpsExecute((ops)->ops.pTtl(rawKey(key)));
	}

	@Override
	public long pTtl(final byte[] key){
		return keyOpsExecute((ops)->ops.pTtl(rawKey(key)));
	}

	@Override
	public Status copy(final String key, final String destKey){
		return keyOpsExecute((ops)->ops.copy(rawKey(key), rawKey(destKey)));
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey){
		return keyOpsExecute((ops)->ops.copy(rawKey(key), rawKey(destKey)));
	}

	@Override
	public Status copy(final String key, final String destKey, final int db){
		return keyOpsExecute((ops)->ops.copy(rawKey(key), rawKey(destKey), db));
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db){
		return keyOpsExecute((ops)->ops.copy(rawKey(key), rawKey(destKey), db));
	}

	@Override
	public Status copy(final String key, final String destKey, final boolean replace){
		return keyOpsExecute((ops)->ops.copy(rawKey(key), rawKey(destKey), replace));
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final boolean replace){
		return keyOpsExecute((ops)->ops.copy(rawKey(key), rawKey(destKey), replace));
	}

	@Override
	public Status copy(final String key, final String destKey, final int db, final boolean replace){
		return keyOpsExecute((ops)->ops.copy(rawKey(key), rawKey(destKey), db, replace));
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace){
		return keyOpsExecute((ops)->ops.copy(rawKey(key), rawKey(destKey), db, replace));
	}

	@Override
	public Status move(final String key, final int db){
		return keyOpsExecute((ops)->ops.move(rawKey(key), db));
	}

	@Override
	public Status move(final byte[] key, final int db){
		return keyOpsExecute((ops)->ops.move(rawKey(key), db));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final String... keys){
		return keyOpsExecute((ops)->ops.migrate(host, port, db, timeout, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final byte[]... keys){
		return keyOpsExecute((ops)->ops.migrate(host, port, db, timeout, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final String... keys){
		return keyOpsExecute((ops)->ops.migrate(host, port, db, timeout, operation, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final byte[]... keys){
		return keyOpsExecute((ops)->ops.migrate(host, port, db, timeout, operation, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final String... keys){
		return keyOpsExecute((ops)->ops.migrate(host, port, db, password, timeout, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final byte[]... keys){
		return keyOpsExecute((ops)->ops.migrate(host, port, db, password, timeout, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final MigrateOperation operation, final String... keys){
		return keyOpsExecute((ops)->ops.migrate(host, port, db, password, timeout, operation, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final MigrateOperation operation, final byte[]... keys){
		return keyOpsExecute((ops)->ops.migrate(host, port, db, password, timeout, operation, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final String... keys){
		return keyOpsExecute((ops)->ops.migrate(host, port, db, user, password, timeout, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final byte[]... keys){
		return keyOpsExecute((ops)->ops.migrate(host, port, db, user, password, timeout, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final MigrateOperation operation, final String... keys){
		return keyOpsExecute((ops)->ops.migrate(host, port, db, user, password, timeout, operation, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final MigrateOperation operation, final byte[]... keys){
		return keyOpsExecute((ops)->ops.migrate(host, port, db, user, password, timeout, operation, rawKeys(keys)));
	}

	@Override
	public Set<String> keys(final String pattern){
		return keyOpsExecute((ops)->ops.keys(pattern));
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern){
		return keyOpsExecute((ops)->ops.keys(pattern));
	}

	@Override
	public String randomKey(){
		return keyOpsExecute((ops)->ops.randomKey());
	}

	@Override
	public Status rename(final String key, final String newKey){
		return keyOpsExecute((ops)->ops.rename(rawKey(key), rawKey(newKey)));
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
		return keyOpsExecute((ops)->ops.rename(rawKey(key), rawKey(newKey)));
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		return keyOpsExecute((ops)->ops.renameNx(rawKey(key), rawKey(newKey)));
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		return keyOpsExecute((ops)->ops.renameNx(rawKey(key), rawKey(newKey)));
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl){
		return keyOpsExecute((ops)->ops.restore(rawKey(key), serializedValue, ttl));
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		return keyOpsExecute((ops)->ops.restore(rawKey(key), serializedValue, ttl));
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument){
		return keyOpsExecute((ops)->ops.restore(rawKey(key), serializedValue, ttl, argument));
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument){
		return keyOpsExecute((ops)->ops.restore(rawKey(key), serializedValue, ttl, argument));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor){
		return keyOpsExecute((ops)->ops.scan(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor){
		return keyOpsExecute((ops)->ops.scan(cursor));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		return keyOpsExecute((ops)->ops.scan(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern){
		return keyOpsExecute((ops)->ops.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern){
		return keyOpsExecute((ops)->ops.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern){
		return keyOpsExecute((ops)->ops.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		return keyOpsExecute((ops)->ops.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final long count){
		return keyOpsExecute((ops)->ops.scan(cursor, count));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final long count){
		return keyOpsExecute((ops)->ops.scan(cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final long count){
		return keyOpsExecute((ops)->ops.scan(cursor, count));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern, final long count){
		return keyOpsExecute((ops)->ops.scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern, final long count){
		return keyOpsExecute((ops)->ops.scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final long count){
		return keyOpsExecute((ops)->ops.scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final long count){
		return keyOpsExecute((ops)->ops.scan(cursor, pattern, count));
	}

	@Override
	public List<String> sort(final String key){
		return keyOpsExecute((ops)->ops.sort(rawKey(key)));
	}

	@Override
	public List<byte[]> sort(final byte[] key){
		return keyOpsExecute((ops)->ops.sort(rawKey(key)));
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument){
		return keyOpsExecute((ops)->ops.sort(rawKey(key), sortArgument));
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument){
		return keyOpsExecute((ops)->ops.sort(rawKey(key), sortArgument));
	}

	@Override
	public long sort(final String key, final String destKey){
		return keyOpsExecute((ops)->ops.sort(rawKey(key), rawKey(destKey)));
	}

	@Override
	public long sort(final byte[] key, final byte[] destKey){
		return keyOpsExecute((ops)->ops.sort(rawKey(key), rawKey(destKey)));
	}

	@Override
	public long sort(final String key, final String destKey, final SortArgument sortArgument){
		return keyOpsExecute((ops)->ops.sort(rawKey(key), rawKey(destKey), sortArgument));
	}

	@Override
	public long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		return keyOpsExecute((ops)->ops.sort(rawKey(key), rawKey(destKey), sortArgument));
	}

	@Override
	public long touch(final String... keys){
		return keyOpsExecute((ops)->ops.touch(rawKeys(keys)));
	}

	@Override
	public long touch(final byte[]... keys){
		return keyOpsExecute((ops)->ops.touch(rawKeys(keys)));
	}

	@Override
	public Type type(final String key){
		return keyOpsExecute((ops)->ops.type(rawKey(key)));
	}

	@Override
	public Type type(final byte[] key){
		return keyOpsExecute((ops)->ops.type(rawKey(key)));
	}

	@Override
	public long unlink(final String... keys){
		return keyOpsExecute((ops)->ops.unlink(rawKeys(keys)));
	}

	@Override
	public long unlink(final byte[]... keys){
		return keyOpsExecute((ops)->ops.unlink(rawKeys(keys)));
	}

	@Override
	public long wait(final int replicas, final int timeout){
		return keyOpsExecute((ops)->ops.wait(replicas, timeout));
	}

	@Override
	public ObjectEncoding objectEncoding(final String key){
		return keyOpsExecute((ops)->ops.objectEncoding(rawKey(key)));
	}

	@Override
	public ObjectEncoding objectEncoding(final byte[] key){
		return keyOpsExecute((ops)->ops.objectEncoding(rawKey(key)));
	}

	@Override
	public long objectFreq(final String key){
		return keyOpsExecute((ops)->ops.objectFreq(rawKey(key)));
	}

	@Override
	public long objectFreq(final byte[] key){
		return keyOpsExecute((ops)->ops.objectFreq(rawKey(key)));
	}

	@Override
	public long objectIdleTime(final String key){
		return keyOpsExecute((ops)->ops.objectIdleTime(rawKey(key)));
	}

	@Override
	public long objectIdleTime(final byte[] key){
		return keyOpsExecute((ops)->ops.objectIdleTime(rawKey(key)));
	}

	@Override
	public long objectRefcount(final String key){
		return keyOpsExecute((ops)->ops.objectRefcount(rawKey(key)));
	}

	@Override
	public long objectRefcount(final byte[] key){
		return keyOpsExecute((ops)->ops.objectRefcount(rawKey(key)));
	}

	@Override
	public String lIndex(final String key, final long index){
		return listOpsExecute((ops)->ops.lIndex(rawKey(key), index));
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index){
		return listOpsExecute((ops)->ops.lIndex(rawKey(key), index));
	}

	@Override
	public long lInsert(final String key, final ListPosition position, final String pivot, final String value){
		return listOpsExecute((ops)->ops.lInsert(rawKey(key), position, pivot, value));
	}

	@Override
	public long lInsert(final byte[] key, final ListPosition position, final byte[] pivot, final byte[] value){
		return listOpsExecute((ops)->ops.lInsert(rawKey(key), position, pivot, value));
	}

	@Override
	public Status lSet(final String key, final long index, final String value){
		return listOpsExecute((ops)->ops.lSet(rawKey(key), index, value));
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value){
		return listOpsExecute((ops)->ops.lSet(rawKey(key), index, value));
	}

	@Override
	public long lLen(final String key){
		return listOpsExecute((ops)->ops.lLen(rawKey(key)));
	}

	@Override
	public long lLen(final byte[] key){
		return listOpsExecute((ops)->ops.lLen(rawKey(key)));
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end){
		return listOpsExecute((ops)->ops.lRange(rawKey(key), start, end));
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end){
		return listOpsExecute((ops)->ops.lRange(rawKey(key), start, end));
	}

	@Override
	public long lPos(final String key, final String element){
		return listOpsExecute((ops)->ops.lPos(rawKey(key), element));
	}

	@Override
	public long lPos(final byte[] key, final byte[] element){
		return listOpsExecute((ops)->ops.lPos(rawKey(key), element));
	}

	@Override
	public long lPos(final String key, final String element, final LPosArgument lPosArgument){
		return listOpsExecute((ops)->ops.lPos(rawKey(key), element, lPosArgument));
	}

	@Override
	public long lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument){
		return listOpsExecute((ops)->ops.lPos(rawKey(key), element, lPosArgument));
	}

	@Override
	public List<Long> lPos(final String key, String element, final LPosArgument lPosArgument, final long count){
		return listOpsExecute((ops)->ops.lPos(rawKey(key), element, lPosArgument, count));
	}

	@Override
	public List<Long> lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument, final long count){
		return listOpsExecute((ops)->ops.lPos(rawKey(key), element, lPosArgument, count));
	}

	@Override
	public long lRem(final String key, final String value, final long count){
		return listOpsExecute((ops)->ops.lRem(rawKey(key), value, count));
	}

	@Override
	public long lRem(final byte[] key, final byte[] value, final long count){
		return listOpsExecute((ops)->ops.lRem(rawKey(key), value, count));
	}

	@Override
	public Status lTrim(final String key, final long start, final long end){
		return listOpsExecute((ops)->ops.lTrim(rawKey(key), start, end));
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end){
		return listOpsExecute((ops)->ops.lTrim(rawKey(key), start, end));
	}

	@Override
	public String lMove(final String key, final String destKey, final Direction from, final Direction to){
		return listOpsExecute((ops)->ops.lMove(rawKey(destKey), rawKey(destKey), from, to));
	}

	@Override
	public byte[] lMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to){
		return listOpsExecute((ops)->ops.lMove(rawKey(key), rawKey(destKey), from, to));
	}

	@Override
	public String blMove(final String key, final String destKey, final Direction from, final Direction to,
						 final int timeout){
		return listOpsExecute((ops)->ops.blMove(rawKey(destKey), rawKey(destKey), from, to, timeout));
	}

	@Override
	public byte[] blMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
						 final int timeout){
		return listOpsExecute((ops)->ops.blMove(rawKey(key), rawKey(destKey), from, to, timeout));
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout){
		return listOpsExecute((ops)->ops.blPop(rawKeys(keys), timeout));
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout){
		return listOpsExecute((ops)->ops.blPop(rawKeys(keys), timeout));
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout){
		return listOpsExecute((ops)->ops.brPop(rawKeys(keys), timeout));
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout){
		return listOpsExecute((ops)->ops.brPop(rawKeys(keys), timeout));
	}

	@Override
	public String brPoplPush(final String key, final String destKey, final int timeout){
		return listOpsExecute((ops)->ops.brPoplPush(rawKey(key), rawKey(destKey), timeout));
	}

	@Override
	public byte[] brPoplPush(final byte[] key, final byte[] destKey, final int timeout){
		return listOpsExecute((ops)->ops.brPoplPush(rawKey(key), rawKey(destKey), timeout));
	}

	@Override
	public String lPop(final String key){
		return listOpsExecute((ops)->ops.lPop(rawKey(key)));
	}

	@Override
	public byte[] lPop(final byte[] key){
		return listOpsExecute((ops)->ops.lPop(rawKey(key)));
	}

	@Override
	public long lPush(final String key, final String... values){
		return listOpsExecute((ops)->ops.lPush(rawKey(key), values));
	}

	@Override
	public long lPush(final byte[] key, final byte[]... values){
		return listOpsExecute((ops)->ops.lPush(rawKey(key), values));
	}

	@Override
	public long lPushX(final String key, final String... values){
		return listOpsExecute((ops)->ops.lPushX(rawKey(key), values));
	}

	@Override
	public long lPushX(final byte[] key, final byte[]... values){
		return listOpsExecute((ops)->ops.lPushX(rawKey(key), values));
	}

	@Override
	public String rPop(final String key){
		return listOpsExecute((ops)->ops.rPop(rawKey(key)));
	}

	@Override
	public byte[] rPop(final byte[] key){
		return listOpsExecute((ops)->ops.rPop(rawKey(key)));
	}

	@Override
	public String rPoplPush(final String key, final String destKey){
		return listOpsExecute((ops)->ops.rPoplPush(rawKey(key), rawKey(destKey)));
	}

	@Override
	public byte[] rPoplPush(final byte[] key, final byte[] destKey){
		return listOpsExecute((ops)->ops.rPoplPush(rawKey(key), rawKey(destKey)));
	}

	@Override
	public long rPush(final String key, final String... values){
		return listOpsExecute((ops)->ops.rPush(rawKey(key), values));
	}

	@Override
	public long rPush(final byte[] key, final byte[]... values){
		return listOpsExecute((ops)->ops.rPush(rawKey(key), values));
	}

	@Override
	public long rPushX(final String key, final String... values){
		return listOpsExecute((ops)->ops.rPushX(rawKey(key), values));
	}

	@Override
	public long rPushX(final byte[] key, final byte[]... values){
		return listOpsExecute((ops)->ops.rPushX(rawKey(key), values));
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
		pubSubOpsExecute((ops)->{
			ops.pSubscribe(patterns, pubSubListener);
			return null;
		});
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
		pubSubOpsExecute((ops)->{
			ops.pSubscribe(patterns, pubSubListener);
			return null;
		});
	}

	@Override
	public long publish(final String channel, final String message){
		return pubSubOpsExecute((ops)->ops.publish(channel, message));
	}

	@Override
	public long publish(final byte[] channel, final byte[] message){
		return pubSubOpsExecute((ops)->ops.publish(channel, message));
	}

	@Override
	public List<String> pubsubChannels(){
		return pubSubOpsExecute((ops)->ops.pubsubChannels());
	}

	@Override
	public List<String> pubsubChannels(final String pattern){
		return pubSubOpsExecute((ops)->ops.pubsubChannels(pattern));
	}

	@Override
	public List<byte[]> pubsubChannels(final byte[] pattern){
		return pubSubOpsExecute((ops)->ops.pubsubChannels(pattern));
	}

	@Override
	public long pubsubNumPat(){
		return pubSubOpsExecute((ops)->ops.pubsubNumPat());
	}

	@Override
	public Map<String, Long> pubsubNumSub(final String... channels){
		return pubSubOpsExecute((ops)->ops.pubsubNumSub(channels));
	}

	@Override
	public Map<byte[], Long> pubsubNumSub(final byte[]... channels){
		return pubSubOpsExecute((ops)->ops.pubsubNumSub(channels));
	}

	@Override
	public Object pUnSubscribe(){
		return pubSubOpsExecute((ops)->ops.pUnSubscribe());
	}

	@Override
	public Object pUnSubscribe(final String... patterns){
		return pubSubOpsExecute((ops)->ops.pUnSubscribe(patterns));
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns){
		return pubSubOpsExecute((ops)->ops.pUnSubscribe(patterns));
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
		pubSubOpsExecute((ops)->{
			ops.subscribe(channels, pubSubListener);
			return null;
		});
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
		pubSubOpsExecute((ops)->{
			ops.subscribe(channels, pubSubListener);
			return null;
		});
	}

	@Override
	public Object unSubscribe(){
		return pubSubOpsExecute((ops)->ops.unSubscribe());
	}

	@Override
	public Object unSubscribe(final String... channels){
		return pubSubOpsExecute((ops)->ops.unSubscribe(channels));
	}

	@Override
	public Object unSubscribe(final byte[]... channels){
		return pubSubOpsExecute((ops)->ops.unSubscribe(channels));
	}

	@Override
	public Object eval(final String script){
		return scriptingOpsExecute((ops)->ops.eval(script));
	}

	@Override
	public Object eval(final byte[] script){
		return scriptingOpsExecute((ops)->ops.eval(script));
	}

	@Override
	public Object eval(final String script, final String... params){
		return scriptingOpsExecute((ops)->ops.eval(script, params));
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params){
		return scriptingOpsExecute((ops)->ops.eval(script, params));
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments){
		return scriptingOpsExecute((ops)->ops.eval(script, keys, arguments));
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments){
		return scriptingOpsExecute((ops)->ops.eval(script, keys, arguments));
	}

	@Override
	public Object evalSha(final String digest){
		return scriptingOpsExecute((ops)->ops.evalSha(digest));
	}

	@Override
	public Object evalSha(final byte[] digest){
		return scriptingOpsExecute((ops)->ops.evalSha(digest));
	}

	@Override
	public Object evalSha(final String digest, final String... params){
		return scriptingOpsExecute((ops)->ops.evalSha(digest, params));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... params){
		return scriptingOpsExecute((ops)->ops.evalSha(digest, params));
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments){
		return scriptingOpsExecute((ops)->ops.evalSha(digest, keys, arguments));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments){
		return scriptingOpsExecute((ops)->ops.evalSha(digest, keys, arguments));
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1){
		return scriptingOpsExecute((ops)->ops.scriptExists(sha1));
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1){
		return scriptingOpsExecute((ops)->ops.scriptExists(sha1));
	}

	@Override
	public Status scriptFlush(){
		return scriptingOpsExecute((ops)->ops.scriptFlush());
	}

	@Override
	public Status scriptFlush(final FlushMode mode){
		return scriptingOpsExecute((ops)->ops.scriptFlush(mode));
	}

	@Override
	public String scriptLoad(final String script){
		return scriptingOpsExecute((ops)->ops.scriptLoad(script));
	}

	@Override
	public byte[] scriptLoad(final byte[] script){
		return scriptingOpsExecute((ops)->ops.scriptLoad(script));
	}

	@Override
	public Status scriptKill(){
		return scriptingOpsExecute((ops)->ops.scriptKill());
	}

	@Override
	public List<String> aclCat(){
		return serverOpsExecute((ops)->ops.aclCat());
	}

	@Override
	public List<String> aclCat(final String categoryName){
		return serverOpsExecute((ops)->ops.aclCat(categoryName));
	}

	@Override
	public List<byte[]> aclCat(final byte[] categoryName){
		return serverOpsExecute((ops)->ops.aclCat(categoryName));
	}

	@Override
	public Status aclSetUser(final String username, final String... rules){
		return serverOpsExecute((ops)->ops.aclSetUser(username, rules));
	}

	@Override
	public Status aclSetUser(final byte[] username, final byte[]... rules){
		return serverOpsExecute((ops)->ops.aclSetUser(username, rules));
	}

	@Override
	public AclUser aclGetUser(final String username){
		return serverOpsExecute((ops)->ops.aclGetUser(username));
	}

	@Override
	public AclUser aclGetUser(final byte[] username){
		return serverOpsExecute((ops)->ops.aclGetUser(username));
	}

	@Override
	public List<String> aclUsers(){
		return serverOpsExecute((ops)->ops.aclUsers());
	}

	@Override
	public String aclWhoAmI(){
		return serverOpsExecute((ops)->ops.aclWhoAmI());
	}

	@Override
	public long aclDelUser(final String... usernames){
		return serverOpsExecute((ops)->ops.aclDelUser(usernames));
	}

	@Override
	public long aclDelUser(final byte[]... username){
		return serverOpsExecute((ops)->ops.aclDelUser(username));
	}

	@Override
	public String aclGenPass(){
		return serverOpsExecute((ops)->ops.aclGenPass());
	}

	@Override
	public List<String> aclList(){
		return serverOpsExecute((ops)->ops.aclList());
	}

	@Override
	public Status aclLoad(){
		return serverOpsExecute((ops)->ops.aclLoad());
	}

	@Override
	public List<AclLog> aclLog(){
		return serverOpsExecute((ops)->ops.aclLog());
	}

	@Override
	public List<AclLog> aclLog(final long count){
		return serverOpsExecute((ops)->ops.aclLog(count));
	}

	@Override
	public Status aclLogReset(){
		return serverOpsExecute((ops)->ops.aclLogReset());
	}

	@Override
	public Status aclLogSave(){
		return serverOpsExecute((ops)->ops.aclLogSave());
	}

	@Override
	public String bgRewriteAof(){
		return serverOpsExecute((ops)->ops.bgRewriteAof());
	}

	@Override
	public String bgSave(){
		return serverOpsExecute((ops)->ops.bgSave());
	}

	@Override
	public Status configSet(final String parameter, final String value){
		return serverOpsExecute((ops)->ops.configSet(parameter, value));
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		return serverOpsExecute((ops)->ops.configSet(parameter, value));
	}

	@Override
	public List<String> configGet(final String parameter){
		return serverOpsExecute((ops)->ops.configGet(parameter));
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter){
		return serverOpsExecute((ops)->ops.configGet(parameter));
	}

	@Override
	public Status configResetStat(){
		return serverOpsExecute((ops)->ops.configResetStat());
	}

	@Override
	public Status configRewrite(){
		return serverOpsExecute((ops)->ops.configRewrite());
	}

	@Override
	public long dbSize(){
		return serverOpsExecute((ops)->ops.dbSize());
	}

	@Override
	public Status failover(){
		return serverOpsExecute((ops)->ops.failover());
	}

	@Override
	public Status failover(final String host, final int port){
		return serverOpsExecute((ops)->ops.failover(host, port));
	}

	@Override
	public Status failover(final String host, final int port, final int timeout){
		return serverOpsExecute((ops)->ops.failover(host, port, timeout));
	}

	@Override
	public Status failover(final String host, final int port, final boolean isForce, final int timeout){
		return serverOpsExecute((ops)->ops.failover(host, port, isForce, timeout));
	}

	@Override
	public Status failover(final int timeout){
		return serverOpsExecute((ops)->ops.failover(timeout));
	}

	@Override
	public Status flushAll(){
		return serverOpsExecute((ops)->ops.flushAll());
	}

	@Override
	public Status flushAll(final FlushMode mode){
		return serverOpsExecute((ops)->ops.flushAll(mode));
	}

	@Override
	public Status flushDb(){
		return serverOpsExecute((ops)->ops.flushDb());
	}

	@Override
	public Status flushDb(final FlushMode mode){
		return serverOpsExecute((ops)->ops.flushDb(mode));
	}

	@Override
	public Info info(){
		return serverOpsExecute((ops)->ops.info());
	}

	@Override
	public Info info(final Info.Section section){
		return serverOpsExecute((ops)->ops.info(section));
	}

	@Override
	public long lastSave(){
		return serverOpsExecute((ops)->ops.lastSave());
	}

	@Override
	public String memoryDoctor(){
		return serverOpsExecute((ops)->ops.memoryDoctor());
	}

	@Override
	public Status memoryPurge(){
		return serverOpsExecute((ops)->ops.memoryPurge());
	}

	@Override
	public MemoryStats memoryStats(){
		return serverOpsExecute((ops)->ops.memoryStats());
	}

	@Override
	public long memoryUsage(final String key){
		return serverOpsExecute((ops)->ops.memoryUsage(key));
	}

	@Override
	public long memoryUsage(final byte[] key){
		return serverOpsExecute((ops)->ops.memoryUsage(key));
	}

	@Override
	public long memoryUsage(final String key, final int samples){
		return serverOpsExecute((ops)->ops.memoryUsage(key, samples));
	}

	@Override
	public long memoryUsage(final byte[] key, final int samples){
		return serverOpsExecute((ops)->ops.memoryUsage(key, samples));
	}

	@Override
	public List<Module> moduleList(){
		return serverOpsExecute((ops)->ops.moduleList());
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments){
		return serverOpsExecute((ops)->ops.moduleLoad(path, arguments));
	}

	@Override
	public Status moduleLoad(final byte[] path, final byte[]... arguments){
		return serverOpsExecute((ops)->ops.moduleLoad(path, arguments));
	}

	@Override
	public Status moduleUnLoad(final String name){
		return serverOpsExecute((ops)->ops.moduleUnLoad(name));
	}

	@Override
	public Status moduleUnLoad(final byte[] name){
		return serverOpsExecute((ops)->ops.moduleUnLoad(name));
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		serverOpsExecute((ops)->{
			ops.monitor(redisMonitor);
			return null;
		});
	}

	@Override
	public Object pSync(final String replicationId, final long offset){
		return serverOpsExecute((ops)->ops.pSync(replicationId, offset));
	}

	@Override
	public Object pSync(final byte[] replicationId, final long offset){
		return serverOpsExecute((ops)->ops.pSync(replicationId, offset));
	}

	@Override
	public void sync(){
		serverOpsExecute((ops)->{
			ops.sync();
			return null;
		});
	}

	@Override
	public Status replicaOf(final String host, final int port){
		return serverOpsExecute((ops)->ops.replicaOf(host, port));
	}

	@Override
	public Status slaveOf(final String host, final int port){
		return serverOpsExecute((ops)->ops.slaveOf(host, port));
	}

	@Override
	public List<Role> role(){
		return serverOpsExecute((ops)->ops.role());
	}

	@Override
	public Status save(){
		return serverOpsExecute((ops)->ops.save());
	}

	@Override
	public void shutdown(){
		serverOpsExecute((ops)->{
			ops.shutdown();
			return null;
		});
	}

	@Override
	public void shutdown(final boolean save){
		serverOpsExecute((ops)->{
			ops.shutdown(save);
			return null;
		});
	}

	@Override
	public List<SlowLog> slowLogGet(){
		return serverOpsExecute((ops)->ops.slowLogGet());
	}

	@Override
	public List<SlowLog> slowLogGet(final long count){
		return serverOpsExecute((ops)->ops.slowLogGet(count));
	}

	@Override
	public long slowLogLen(){
		return serverOpsExecute((ops)->ops.slowLogLen());
	}

	@Override
	public Status slowLogReset(){
		return serverOpsExecute((ops)->ops.slowLogReset());
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		return serverOpsExecute((ops)->ops.swapdb(db1, db2));
	}

	@Override
	public RedisServerTime time(){
		return serverOpsExecute((ops)->ops.time());
	}

	@Override
	public long sAdd(final String key, final String... members){
		return setOpsExecute((ops)->ops.sAdd(rawKey(key), members));
	}

	@Override
	public long sAdd(final byte[] key, final byte[]... members){
		return setOpsExecute((ops)->ops.sAdd(rawKey(key), members));
	}

	@Override
	public long sCard(final String key){
		return setOpsExecute((ops)->ops.sCard(rawKey(key)));
	}

	@Override
	public long sCard(final byte[] key){
		return setOpsExecute((ops)->ops.sCard(rawKey(key)));
	}

	@Override
	public Set<String> sDiff(final String... keys){
		return setOpsExecute((ops)->ops.sDiff(rawKeys(keys)));
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys){
		return setOpsExecute((ops)->ops.sDiff(rawKeys(keys)));
	}

	@Override
	public long sDiffStore(final String destKey, final String... keys){
		return setOpsExecute((ops)->ops.sDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public long sDiffStore(final byte[] destKey, final byte[]... keys){
		return setOpsExecute((ops)->ops.sDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Set<String> sInter(final String... keys){
		return setOpsExecute((ops)->ops.sInter(rawKeys(keys)));
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys){
		return setOpsExecute((ops)->ops.sInter(rawKeys(keys)));
	}

	@Override
	public long sInterStore(final String destKey, final String... keys){
		return setOpsExecute((ops)->ops.sDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public long sInterStore(final byte[] destKey, final byte[]... keys){
		return setOpsExecute((ops)->ops.sDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public boolean sIsMember(final String key, final String member){
		return setOpsExecute((ops)->ops.sIsMember(rawKey(key), member));
	}

	@Override
	public boolean sIsMember(final byte[] key, final byte[] member){
		return setOpsExecute((ops)->ops.sIsMember(rawKey(key), member));
	}

	@Override
	public Set<String> sMembers(final String key){
		return setOpsExecute((ops)->ops.sMembers(rawKey(key)));
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		return setOpsExecute((ops)->ops.sMembers(rawKey(key)));
	}

	@Override
	public Status sMove(final String key, final String destKey, final String member){
		return setOpsExecute((ops)->ops.sMove(rawKey(key), rawKey(destKey), member));
	}

	@Override
	public Status sMove(final byte[] key, final byte[] destKey, final byte[] member){
		return setOpsExecute((ops)->ops.sMove(rawKey(key), rawKey(destKey), member));
	}

	@Override
	public String sPop(final String key){
		return setOpsExecute((ops)->ops.sPop(rawKey(key)));
	}

	@Override
	public byte[] sPop(final byte[] key){
		return setOpsExecute((ops)->ops.sPop(rawKey(key)));
	}

	@Override
	public Set<String> sPop(final String key, final long count){
		return setOpsExecute((ops)->ops.sPop(rawKey(key), count));
	}

	@Override
	public Set<byte[]> sPop(final byte[] key, final long count){
		return setOpsExecute((ops)->ops.sPop(rawKey(key), count));
	}

	@Override
	public String sRandMember(final String key){
		return setOpsExecute((ops)->ops.sRandMember(rawKey(key)));
	}

	@Override
	public byte[] sRandMember(final byte[] key){
		return setOpsExecute((ops)->ops.sRandMember(rawKey(key)));
	}

	@Override
	public List<String> sRandMember(final String key, final long count){
		return setOpsExecute((ops)->ops.sRandMember(rawKey(key), count));
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final long count){
		return setOpsExecute((ops)->ops.sRandMember(rawKey(key), count));
	}

	@Override
	public long sRem(final String key, final String... members){
		return setOpsExecute((ops)->ops.sRem(rawKey(key), members));
	}

	@Override
	public long sRem(final byte[] key, final byte[]... members){
		return setOpsExecute((ops)->ops.sRem(rawKey(key), members));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor){
		return setOpsExecute((ops)->ops.sScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor){
		return setOpsExecute((ops)->ops.sScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor){
		return setOpsExecute((ops)->ops.sScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		return setOpsExecute((ops)->ops.sScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern){
		return setOpsExecute((ops)->ops.sScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern){
		return setOpsExecute((ops)->ops.sScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
		return setOpsExecute((ops)->ops.sScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return setOpsExecute((ops)->ops.sScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final long count){
		return setOpsExecute((ops)->ops.sScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final long count){
		return setOpsExecute((ops)->ops.sScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final long count){
		return setOpsExecute((ops)->ops.sScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final long count){
		return setOpsExecute((ops)->ops.sScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern, final long count){
		return setOpsExecute((ops)->ops.sScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern, final long count){
		return setOpsExecute((ops)->ops.sScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
										  final long count){
		return setOpsExecute((ops)->ops.sScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										  final long count){
		return setOpsExecute((ops)->ops.sScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public Set<String> sUnion(final String... keys){
		return setOpsExecute((ops)->ops.sUnion(rawKeys(keys)));
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys){
		return setOpsExecute((ops)->ops.sUnion(rawKeys(keys)));
	}

	@Override
	public long sUnionStore(final String destKey, final String... keys){
		return setOpsExecute((ops)->ops.sUnionStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public long sUnionStore(final byte[] destKey, final byte[]... keys){
		return setOpsExecute((ops)->ops.sUnionStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Tuple zPopMin(final String key){
		return sortedSetOpsExecute((ops)->ops.zPopMin(rawKey(key)));
	}

	@Override
	public Tuple zPopMin(final byte[] key){
		return sortedSetOpsExecute((ops)->ops.zPopMin(rawKey(key)));
	}

	@Override
	public List<Tuple> zPopMin(final String key, long count){
		return sortedSetOpsExecute((ops)->ops.zPopMin(rawKey(key), count));
	}

	@Override
	public List<Tuple> zPopMin(final byte[] key, long count){
		return sortedSetOpsExecute((ops)->ops.zPopMin(rawKey(key), count));
	}

	@Override
	public Tuple zPopMax(final String key){
		return sortedSetOpsExecute((ops)->ops.zPopMax(rawKey(key)));
	}

	@Override
	public Tuple zPopMax(final byte[] key){
		return sortedSetOpsExecute((ops)->ops.zPopMax(rawKey(key)));
	}

	@Override
	public List<Tuple> zPopMax(final String key, final long count){
		return sortedSetOpsExecute((ops)->ops.zPopMax(rawKey(key), count));
	}

	@Override
	public List<Tuple> zPopMax(final byte[] key, final long count){
		return sortedSetOpsExecute((ops)->ops.zPopMax(rawKey(key), count));
	}

	@Override
	public KeyedZSetElement bzPopMin(final String[] keys, final int timeout){
		return sortedSetOpsExecute((ops)->ops.bzPopMin(rawKeys(keys), timeout));
	}

	@Override
	public KeyedZSetElement bzPopMin(final byte[][] keys, final int timeout){
		return sortedSetOpsExecute((ops)->ops.bzPopMin(rawKeys(keys), timeout));
	}

	@Override
	public KeyedZSetElement bzPopMax(final String[] keys, final int timeout){
		return sortedSetOpsExecute((ops)->ops.bzPopMax(rawKeys(keys), timeout));
	}

	@Override
	public KeyedZSetElement bzPopMax(final byte[][] keys, final int timeout){
		return sortedSetOpsExecute((ops)->ops.bzPopMax(rawKeys(keys), timeout));
	}

	@Override
	public long zAdd(final String key, final Map<String, Double> members){
		return sortedSetOpsExecute((ops)->ops.zAdd(rawKey(key), members));
	}

	@Override
	public long zAdd(final byte[] key, final Map<byte[], Double> members){
		return sortedSetOpsExecute((ops)->ops.zAdd(rawKey(key), members));
	}

	@Override
	public long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx){
		return sortedSetOpsExecute((ops)->ops.zAdd(rawKey(key), members, nxXx));
	}

	@Override
	public long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx){
		return sortedSetOpsExecute((ops)->ops.zAdd(rawKey(key), members, nxXx));
	}

	@Override
	public long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt){
		return sortedSetOpsExecute((ops)->ops.zAdd(rawKey(key), members, gtLt));
	}

	@Override
	public long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt){
		return sortedSetOpsExecute((ops)->ops.zAdd(rawKey(key), members, gtLt));
	}

	@Override
	public long zAdd(final String key, final Map<String, Double> members, final boolean ch){
		return sortedSetOpsExecute((ops)->ops.zAdd(rawKey(key), members, ch));
	}

	@Override
	public long zAdd(final byte[] key, final Map<byte[], Double> members, final boolean ch){
		return sortedSetOpsExecute((ops)->ops.zAdd(rawKey(key), members, ch));
	}

	@Override
	public long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt){
		return sortedSetOpsExecute((ops)->ops.zAdd(rawKey(key), members, nxXx, gtLt));
	}

	@Override
	public long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt){
		return sortedSetOpsExecute((ops)->ops.zAdd(rawKey(key), members, nxXx, gtLt));
	}

	@Override
	public long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final boolean ch){
		return sortedSetOpsExecute((ops)->ops.zAdd(rawKey(key), members, nxXx, ch));
	}

	@Override
	public long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final boolean ch){
		return sortedSetOpsExecute((ops)->ops.zAdd(rawKey(key), members, nxXx, ch));
	}

	@Override
	public long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt, final boolean ch){
		return sortedSetOpsExecute((ops)->ops.zAdd(rawKey(key), members, gtLt, ch));
	}

	@Override
	public long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt, final boolean ch){
		return sortedSetOpsExecute((ops)->ops.zAdd(rawKey(key), members, gtLt, ch));
	}

	@Override
	public long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt,
					 final boolean ch){
		return sortedSetOpsExecute((ops)->ops.zAdd(rawKey(key), members, nxXx, gtLt, ch));
	}

	@Override
	public long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt,
					 final boolean ch){
		return sortedSetOpsExecute((ops)->ops.zAdd(rawKey(key), members, nxXx, gtLt, ch));
	}

	@Override
	public long zCard(final String key){
		return sortedSetOpsExecute((ops)->ops.zCard(rawKey(key)));
	}

	@Override
	public long zCard(final byte[] key){
		return sortedSetOpsExecute((ops)->ops.zCard(rawKey(key)));
	}

	@Override
	public long zCount(final String key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zCount(rawKey(key), min, max));
	}

	@Override
	public long zCount(final byte[] key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zCount(rawKey(key), min, max));
	}

	@Override
	public long zCount(final String key, final String min, final String max){
		return sortedSetOpsExecute((ops)->ops.zCount(rawKey(key), min, max));
	}

	@Override
	public long zCount(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOpsExecute((ops)->ops.zCount(rawKey(key), min, max));
	}

	@Override
	public Set<String> zDiff(final String... keys){
		return sortedSetOpsExecute((ops)->ops.zDiff(rawKeys(keys)));
	}

	@Override
	public Set<byte[]> zDiff(final byte[]... keys){
		return sortedSetOpsExecute((ops)->ops.zDiff(rawKeys(keys)));
	}

	@Override
	public Set<Tuple> zDiffWithScores(final String... keys){
		return sortedSetOpsExecute((ops)->ops.zDiffWithScores(rawKeys(keys)));
	}

	@Override
	public Set<Tuple> zDiffWithScores(final byte[]... keys){
		return sortedSetOpsExecute((ops)->ops.zDiffWithScores(rawKeys(keys)));
	}

	@Override
	public long zDiffStore(final String destKey, final String... keys){
		return sortedSetOpsExecute((ops)->ops.zDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public long zDiffStore(final byte[] destKey, final byte[]... keys){
		return sortedSetOpsExecute((ops)->ops.zDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public double zIncrBy(final String key, final double increment, final String member){
		return sortedSetOpsExecute((ops)->ops.zIncrBy(rawKey(key), increment, member));
	}

	@Override
	public double zIncrBy(final byte[] key, final double increment, final byte[] member){
		return sortedSetOpsExecute((ops)->ops.zIncrBy(rawKey(key), increment, member));
	}

	@Override
	public Set<String> zInter(final String... keys){
		return sortedSetOpsExecute((ops)->ops.zInter(rawKeys(keys)));
	}

	@Override
	public Set<byte[]> zInter(final byte[]... keys){
		return sortedSetOpsExecute((ops)->ops.zInter(rawKeys(keys)));
	}

	@Override
	public Set<String> zInter(final String[] keys, final Aggregate aggregate){
		return sortedSetOpsExecute((ops)->ops.zInter(rawKeys(keys), aggregate));
	}

	@Override
	public Set<byte[]> zInter(final byte[][] keys, final Aggregate aggregate){
		return sortedSetOpsExecute((ops)->ops.zInter(rawKeys(keys), aggregate));
	}

	@Override
	public Set<String> zInter(final String[] keys, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zInter(rawKeys(keys), weights));
	}

	@Override
	public Set<byte[]> zInter(final byte[][] keys, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zInter(rawKeys(keys), weights));
	}

	@Override
	public Set<String> zInter(final String[] keys, final Aggregate aggregate, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zInter(rawKeys(keys), aggregate, weights));
	}

	@Override
	public Set<byte[]> zInter(final byte[][] keys, final Aggregate aggregate, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zInter(rawKeys(keys), aggregate, weights));
	}

	@Override
	public Set<Tuple> zInterWithScores(final String... keys){
		return sortedSetOpsExecute((ops)->ops.zInterWithScores(rawKeys(keys)));
	}

	@Override
	public Set<Tuple> zInterWithScores(final byte[]... keys){
		return sortedSetOpsExecute((ops)->ops.zInterWithScores(rawKeys(keys)));
	}

	@Override
	public Set<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate){
		return sortedSetOpsExecute((ops)->ops.zInterWithScores(rawKeys(keys), aggregate));
	}

	@Override
	public Set<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate){
		return sortedSetOpsExecute((ops)->ops.zInterWithScores(rawKeys(keys), aggregate));
	}

	@Override
	public Set<Tuple> zInterWithScores(final String[] keys, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zInterWithScores(rawKeys(keys), weights));
	}

	@Override
	public Set<Tuple> zInterWithScores(final byte[][] keys, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zInterWithScores(rawKeys(keys), weights));
	}

	@Override
	public Set<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zInterWithScores(rawKeys(keys), aggregate, weights));
	}

	@Override
	public Set<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zInterWithScores(rawKeys(keys), aggregate, weights));
	}

	@Override
	public long zInterStore(final String destKey, final String... keys){
		return sortedSetOpsExecute((ops)->ops.zInterStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public long zInterStore(final byte[] destKey, final byte[]... keys){
		return sortedSetOpsExecute((ops)->ops.zInterStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate){
		return sortedSetOpsExecute((ops)->ops.zInterStore(rawKey(destKey), rawKeys(keys), aggregate));
	}

	@Override
	public long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate){
		return sortedSetOpsExecute((ops)->ops.zInterStore(rawKey(destKey), rawKeys(keys), aggregate));
	}

	@Override
	public long zInterStore(final String destKey, final String[] keys, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zInterStore(rawKey(destKey), rawKeys(keys), weights));
	}

	@Override
	public long zInterStore(final byte[] destKey, final byte[][] keys, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zInterStore(rawKey(destKey), rawKeys(keys), weights));
	}

	@Override
	public long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights){
		return sortedSetOpsExecute((ops)->ops.zInterStore(rawKey(destKey), rawKeys(keys), aggregate, weights));
	}

	@Override
	public long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights){
		return sortedSetOpsExecute((ops)->ops.zInterStore(rawKey(destKey), rawKeys(keys), aggregate, weights));
	}

	@Override
	public long zLexCount(final String key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zLexCount(rawKey(key), min, max));
	}

	@Override
	public long zLexCount(final byte[] key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zLexCount(rawKey(key), min, max));
	}

	@Override
	public long zLexCount(final String key, final String min, final String max){
		return sortedSetOpsExecute((ops)->ops.zLexCount(rawKey(key), min, max));
	}

	@Override
	public long zLexCount(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOpsExecute((ops)->ops.zLexCount(rawKey(key), min, max));
	}

	@Override
	public List<Double> zMScore(final String key, final String... members){
		return sortedSetOpsExecute((ops)->ops.zMScore(rawKey(key), members));
	}

	@Override
	public List<Double> zMScore(final byte[] key, final byte[]... members){
		return sortedSetOpsExecute((ops)->ops.zMScore(rawKey(key), members));
	}

	@Override
	public String zRandMember(final String key){
		return sortedSetOpsExecute((ops)->ops.zRandMember(rawKey(key)));
	}

	@Override
	public byte[] zRandMember(final byte[] key){
		return sortedSetOpsExecute((ops)->ops.zRandMember(rawKey(key)));
	}

	@Override
	public List<String> zRandMember(final String key, final long count){
		return sortedSetOpsExecute((ops)->ops.zRandMember(rawKey(key), count));
	}

	@Override
	public List<byte[]> zRandMember(final byte[] key, final long count){
		return sortedSetOpsExecute((ops)->ops.zRandMember(rawKey(key), count));
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final String key, final long count){
		return sortedSetOpsExecute((ops)->ops.zRandMemberWithScores(rawKey(key), count));
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final byte[] key, final long count){
		return sortedSetOpsExecute((ops)->ops.zRandMemberWithScores(rawKey(key), count));
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end){
		return sortedSetOpsExecute((ops)->ops.zRange(rawKey(key), start, end));
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end){
		return sortedSetOpsExecute((ops)->ops.zRange(rawKey(key), start, end));
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end){
		return sortedSetOpsExecute((ops)->ops.zRangeWithScores(rawKey(key), start, end));
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
		return sortedSetOpsExecute((ops)->ops.zRangeWithScores(rawKey(key), start, end));
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<String> zRangeByLex(final String key, final String min, final String max){
		return sortedSetOpsExecute((ops)->ops.zRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOpsExecute((ops)->ops.zRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max, final long offset,
									final long count){
		return sortedSetOpsExecute((ops)->ops.zRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final long offset,
									final long count){
		return sortedSetOpsExecute((ops)->ops.zRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<String> zRangeByLex(final String key, final String min, final String max, final long offset,
									final long count){
		return sortedSetOpsExecute((ops)->ops.zRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									final long count){
		return sortedSetOpsExecute((ops)->ops.zRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zRangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<String> zRangeByScore(final String key, final String min, final String max){
		return sortedSetOpsExecute((ops)->ops.zRangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOpsExecute((ops)->ops.zRangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max, final long offset,
									  final long count){
		return sortedSetOpsExecute((ops)->ops.zRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final long offset,
									  final long count){
		return sortedSetOpsExecute((ops)->ops.zRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<String> zRangeByScore(final String key, String min, String max, long offset,
									  long count){
		return sortedSetOpsExecute((ops)->ops.zRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final long offset,
									  final long count){
		return sortedSetOpsExecute((ops)->ops.zRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max){
		return sortedSetOpsExecute((ops)->ops.zRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOpsExecute((ops)->ops.zRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final long offset,
											   final long count){
		return sortedSetOpsExecute((ops)->ops.zRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final long offset,
											   final long count){
		return sortedSetOpsExecute((ops)->ops.zRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final long offset,
											   final long count){
		return sortedSetOpsExecute((ops)->ops.zRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final long offset,
											   final long count){
		return sortedSetOpsExecute((ops)->ops.zRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public long zRangeStore(final String destKey, final String key, final long start, final long end){
		return sortedSetOpsExecute((ops)->ops.zRangeStore(rawKey(destKey), rawKey(key), start, end));
	}

	@Override
	public long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end){
		return sortedSetOpsExecute((ops)->ops.zRangeStore(rawKey(destKey), rawKey(key), start, end));
	}

	@Override
	public long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by){
		return sortedSetOpsExecute((ops)->ops.zRangeStore(rawKey(destKey), rawKey(key), start, end, by));
	}

	@Override
	public long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by){
		return sortedSetOpsExecute((ops)->ops.zRangeStore(rawKey(destKey), rawKey(key), start, end, by));
	}

	@Override
	public long zRangeStore(final String destKey, final String key, final long start, final long end,
							final boolean rev){
		return sortedSetOpsExecute((ops)->ops.zRangeStore(rawKey(destKey), rawKey(key), start, end, rev));
	}

	@Override
	public long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final boolean rev){
		return sortedSetOpsExecute((ops)->ops.zRangeStore(rawKey(destKey), rawKey(key), start, end, rev));
	}

	@Override
	public long zRangeStore(final String destKey, final String key, final long start, final long end, final long offset,
							final long count){
		return sortedSetOpsExecute((ops)->ops.zRangeStore(rawKey(destKey), rawKey(key), start, end, offset, count));
	}

	@Override
	public long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final long offset,
							final long count){
		return sortedSetOpsExecute((ops)->ops.zRangeStore(rawKey(destKey), rawKey(key), start, end, offset, count));
	}

	@Override
	public long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by,
							final boolean rev){
		return sortedSetOpsExecute((ops)->ops.zRangeStore(rawKey(destKey), rawKey(key), start, end, by, rev));
	}

	@Override
	public long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by,
							final boolean rev){
		return sortedSetOpsExecute((ops)->ops.zRangeStore(rawKey(destKey), rawKey(key), start, end, by, rev));
	}

	@Override
	public long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by,
							final long offset, final long count){
		return sortedSetOpsExecute((ops)->ops.zRangeStore(rawKey(destKey), rawKey(key), start, end, by, offset, count));
	}

	@Override
	public long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by,
							final long offset, final long count){
		return sortedSetOpsExecute((ops)->ops.zRangeStore(rawKey(destKey), rawKey(key), start, end, by, offset, count));
	}

	@Override
	public long zRangeStore(final String destKey, final String key, final long start, final long end, final boolean rev,
							final long offset, final long count){
		return sortedSetOpsExecute(
				(ops)->ops.zRangeStore(rawKey(destKey), rawKey(key), start, end, rev, offset, count));
	}

	@Override
	public long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final boolean rev,
							final long offset, final long count){
		return sortedSetOpsExecute(
				(ops)->ops.zRangeStore(rawKey(destKey), rawKey(key), start, end, rev, offset, count));
	}

	@Override
	public long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by,
							final boolean rev, final long offset, final long count){
		return sortedSetOpsExecute(
				(ops)->ops.zRangeStore(rawKey(destKey), rawKey(key), start, end, by, rev, offset, count));
	}

	@Override
	public long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by,
							final boolean rev, final long offset, final long count){
		return sortedSetOpsExecute(
				(ops)->ops.zRangeStore(rawKey(destKey), rawKey(key), start, end, by, rev, offset, count));
	}

	@Override
	public Long zRank(final String key, final String member){
		return sortedSetOpsExecute((ops)->ops.zRank(rawKey(key), member));
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member){
		return sortedSetOpsExecute((ops)->ops.zRank(rawKey(key), member));
	}

	@Override
	public long zRem(final String key, final String... members){
		return sortedSetOpsExecute((ops)->ops.zRem(rawKey(key), members));
	}

	@Override
	public long zRem(final byte[] key, final byte[]... members){
		return sortedSetOpsExecute((ops)->ops.zRem(rawKey(key), members));
	}

	@Override
	public long zRemRangeByLex(final String key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zRemRangeByLex(rawKey(key), min, max));
	}

	@Override
	public long zRemRangeByLex(final byte[] key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zRemRangeByLex(rawKey(key), min, max));
	}

	@Override
	public long zRemRangeByLex(final String key, final String min, final String max){
		return sortedSetOpsExecute((ops)->ops.zRemRangeByLex(rawKey(key), min, max));
	}

	@Override
	public long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOpsExecute((ops)->ops.zRemRangeByLex(rawKey(key), min, max));
	}

	@Override
	public long zRemRangeByScore(final String key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zRemRangeByScore(rawKey(key), min, max));
	}

	@Override
	public long zRemRangeByScore(final byte[] key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zRemRangeByScore(rawKey(key), min, max));
	}

	@Override
	public long zRemRangeByScore(final String key, final String min, final String max){
		return sortedSetOpsExecute((ops)->ops.zRemRangeByScore(rawKey(key), min, max));
	}

	@Override
	public long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOpsExecute((ops)->ops.zRemRangeByScore(rawKey(key), min, max));
	}

	@Override
	public long zRemRangeByRank(final String key, final long start, final long end){
		return sortedSetOpsExecute((ops)->ops.zRemRangeByRank(rawKey(key), start, end));
	}

	@Override
	public long zRemRangeByRank(final byte[] key, final long start, final long end){
		return sortedSetOpsExecute((ops)->ops.zRemRangeByRank(rawKey(key), start, end));
	}

	@Override
	public List<String> zRevRange(final String key, final long start, final long end){
		return sortedSetOpsExecute((ops)->ops.zRevRange(rawKey(key), start, end));
	}

	@Override
	public List<byte[]> zRevRange(final byte[] key, final long start, final long end){
		return sortedSetOpsExecute((ops)->ops.zRevRange(rawKey(key), start, end));
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final String key, final long start, final long end){
		return sortedSetOpsExecute((ops)->ops.zRevRangeWithScores(rawKey(key), start, end));
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
		return sortedSetOpsExecute((ops)->ops.zRevRangeWithScores(rawKey(key), start, end));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final String min, final String max){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max, final long offset,
									   final long count){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final long offset,
									   final long count){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final String min, final String max, final long offset,
									   final long count){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									   final long count){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final String min, final String max){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max, final long offset,
										 final long count){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final long offset,
										 final long count){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final String min, final String max, final long offset,
										 final long count){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final long offset,
										 final long count){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
												  final long offset, final long count){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
												  final long offset, final long count){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max,
												  final long offset, final long count){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
												  final long offset, final long count){
		return sortedSetOpsExecute((ops)->ops.zRevRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public long zRevRank(final String key, final String member){
		return sortedSetOpsExecute((ops)->ops.zRevRank(rawKey(key), member));
	}

	@Override
	public long zRevRank(final byte[] key, final byte[] member){
		return sortedSetOpsExecute((ops)->ops.zRevRank(rawKey(key), member));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor){
		return sortedSetOpsExecute((ops)->ops.zScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor){
		return sortedSetOpsExecute((ops)->ops.zScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor){
		return sortedSetOpsExecute((ops)->ops.zScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
		return sortedSetOpsExecute((ops)->ops.zScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern){
		return sortedSetOpsExecute((ops)->ops.zScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern){
		return sortedSetOpsExecute((ops)->ops.zScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern){
		return sortedSetOpsExecute((ops)->ops.zScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return sortedSetOpsExecute((ops)->ops.zScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final long count){
		return sortedSetOpsExecute((ops)->ops.zScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final long count){
		return sortedSetOpsExecute((ops)->ops.zScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final long count){
		return sortedSetOpsExecute((ops)->ops.zScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final long count){
		return sortedSetOpsExecute((ops)->ops.zScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final long count){
		return sortedSetOpsExecute((ops)->ops.zScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern, final long count){
		return sortedSetOpsExecute((ops)->ops.zScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final long count){
		return sortedSetOpsExecute((ops)->ops.zScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final long count){
		return sortedSetOpsExecute((ops)->ops.zScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public Double zScore(final String key, final String member){
		return sortedSetOpsExecute((ops)->ops.zScore(rawKey(key), member));
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member){
		return sortedSetOpsExecute((ops)->ops.zScore(rawKey(key), member));
	}

	@Override
	public Set<String> zUnion(final String... keys){
		return sortedSetOpsExecute((ops)->ops.zUnion(rawKeys(keys)));
	}

	@Override
	public Set<byte[]> zUnion(final byte[]... keys){
		return sortedSetOpsExecute((ops)->ops.zUnion(rawKeys(keys)));
	}

	@Override
	public Set<String> zUnion(final String[] keys, final Aggregate aggregate){
		return sortedSetOpsExecute((ops)->ops.zUnion(rawKeys(keys), aggregate));
	}

	@Override
	public Set<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate){
		return sortedSetOpsExecute((ops)->ops.zUnion(rawKeys(keys), aggregate));
	}

	@Override
	public Set<String> zUnion(final String[] keys, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zUnion(rawKeys(keys), weights));
	}

	@Override
	public Set<byte[]> zUnion(final byte[][] keys, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zUnion(rawKeys(keys), weights));
	}

	@Override
	public Set<String> zUnion(final String[] keys, final Aggregate aggregate, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zUnion(rawKeys(keys), aggregate, weights));
	}

	@Override
	public Set<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zUnion(rawKeys(keys), aggregate, weights));
	}

	@Override
	public Set<Tuple> zUnionWithScores(final String... keys){
		return sortedSetOpsExecute((ops)->ops.zUnionWithScores(rawKeys(keys)));
	}

	@Override
	public Set<Tuple> zUnionWithScores(final byte[]... keys){
		return sortedSetOpsExecute((ops)->ops.zUnionWithScores(rawKeys(keys)));
	}

	@Override
	public Set<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate){
		return sortedSetOpsExecute((ops)->ops.zUnionWithScores(rawKeys(keys), aggregate));
	}

	@Override
	public Set<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate){
		return sortedSetOpsExecute((ops)->ops.zUnionWithScores(rawKeys(keys), aggregate));
	}

	@Override
	public Set<Tuple> zUnionWithScores(final String[] keys, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zUnionWithScores(rawKeys(keys), weights));
	}

	@Override
	public Set<Tuple> zUnionWithScores(final byte[][] keys, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zUnionWithScores(rawKeys(keys), weights));
	}

	@Override
	public Set<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zUnionWithScores(rawKeys(keys), aggregate, weights));
	}

	@Override
	public Set<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zUnionWithScores(rawKeys(keys), aggregate, weights));
	}

	@Override
	public long zUnionStore(final String destKey, final String... keys){
		return sortedSetOpsExecute((ops)->ops.zUnionStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public long zUnionStore(final byte[] destKey, final byte[]... keys){
		return sortedSetOpsExecute((ops)->ops.zUnionStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate){
		return sortedSetOpsExecute((ops)->ops.zUnionStore(rawKey(destKey), rawKeys(keys), aggregate));
	}

	@Override
	public long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate){
		return sortedSetOpsExecute((ops)->ops.zUnionStore(rawKey(destKey), rawKeys(keys), aggregate));
	}

	@Override
	public long zUnionStore(final String destKey, final String[] keys, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zUnionStore(rawKey(destKey), rawKeys(keys), weights));
	}

	@Override
	public long zUnionStore(final byte[] destKey, final byte[][] keys, final double... weights){
		return sortedSetOpsExecute((ops)->ops.zUnionStore(rawKey(destKey), rawKeys(keys), weights));
	}

	@Override
	public long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights){
		return sortedSetOpsExecute((ops)->ops.zUnionStore(rawKey(destKey), rawKeys(keys), aggregate, weights));
	}

	@Override
	public long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights){
		return sortedSetOpsExecute((ops)->ops.zUnionStore(rawKey(destKey), rawKeys(keys), aggregate, weights));
	}

	@Override
	public long append(final String key, final String value){
		return stringOpsOpsExecute((ops)->ops.append(rawKey(key), value));
	}

	@Override
	public long append(final byte[] key, final byte[] value){
		return stringOpsOpsExecute((ops)->ops.append(rawKey(key), value));
	}

	@Override
	public long incr(final String key){
		return stringOpsOpsExecute((ops)->ops.incr(rawKey(key)));
	}

	@Override
	public long incr(final byte[] key){
		return stringOpsOpsExecute((ops)->ops.incr(rawKey(key)));
	}

	@Override
	public long incrBy(final String key, final long value){
		return stringOpsOpsExecute((ops)->ops.incrBy(rawKey(key), value));
	}

	@Override
	public long incrBy(final byte[] key, final long value){
		return stringOpsOpsExecute((ops)->ops.incrBy(rawKey(key), value));
	}

	@Override
	public double incrByFloat(final String key, final double value){
		return stringOpsOpsExecute((ops)->ops.incrByFloat(rawKey(key), value));
	}

	@Override
	public double incrByFloat(final byte[] key, final double value){
		return stringOpsOpsExecute((ops)->ops.incrByFloat(rawKey(key), value));
	}

	@Override
	public long decr(final String key){
		return stringOpsOpsExecute((ops)->ops.decr(rawKey(key)));
	}

	@Override
	public long decr(final byte[] key){
		return stringOpsOpsExecute((ops)->ops.decr(rawKey(key)));
	}

	@Override
	public long decrBy(final String key, final long value){
		return stringOpsOpsExecute((ops)->ops.decrBy(rawKey(key), value));
	}

	@Override
	public long decrBy(final byte[] key, final long value){
		return stringOpsOpsExecute((ops)->ops.decrBy(rawKey(key), value));
	}

	@Override
	public String get(final String key){
		return stringOpsOpsExecute((ops)->ops.get(rawKey(key)));
	}

	@Override
	public byte[] get(final byte[] key){
		return stringOpsOpsExecute((ops)->ops.get(rawKey(key)));
	}

	@Override
	public String getEx(final String key, final GetExArgument getExArgument){
		return stringOpsOpsExecute((ops)->ops.getEx(rawKey(key), getExArgument));
	}

	@Override
	public byte[] getEx(final byte[] key, final GetExArgument getExArgument){
		return stringOpsOpsExecute((ops)->ops.getEx(rawKey(key), getExArgument));
	}

	@Override
	public String getSet(final String key, final String value){
		return stringOpsOpsExecute((ops)->ops.getSet(rawKey(key), value));
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		return stringOpsOpsExecute((ops)->ops.getSet(rawKey(key), value));
	}

	@Override
	public String getDel(final String key){
		return stringOpsOpsExecute((ops)->ops.getDel(rawKey(key)));
	}

	@Override
	public byte[] getDel(final byte[] key){
		return stringOpsOpsExecute((ops)->ops.getDel(rawKey(key)));
	}

	@Override
	public List<String> mGet(final String... keys){
		return stringOpsOpsExecute((ops)->ops.mGet(rawKeys(keys)));
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys){
		return stringOpsOpsExecute((ops)->ops.mGet(rawKeys(keys)));
	}

	@Override
	public Status mSet(final Map<String, String> values){
		final Map<String, String> newValues = new LinkedHashMap<>(values.size());

		values.forEach((key, value)->{
			newValues.put(rawKey(key), value);
		});

		return stringOpsOpsExecute((ops)->ops.mSet(newValues));
	}

	@Override
	public Status mSetNx(final Map<String, String> values){
		final Map<String, String> newValues = new LinkedHashMap<>(values.size());

		values.forEach((key, value)->{
			newValues.put(rawKey(key), value);
		});

		return stringOpsOpsExecute((ops)->ops.mSetNx(newValues));
	}

	@Override
	public Status pSetEx(final String key, String value, int lifetime){
		return stringOpsOpsExecute((ops)->ops.pSetEx(key, value, lifetime));
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		return stringOpsOpsExecute((ops)->ops.pSetEx(key, value, lifetime));
	}

	@Override
	public Status set(final String key, final String value){
		return stringOpsOpsExecute((ops)->ops.set(key, value));
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		return stringOpsOpsExecute((ops)->ops.set(key, value));
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument){
		return stringOpsOpsExecute((ops)->ops.set(key, value, setArgument));
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument){
		return stringOpsOpsExecute((ops)->ops.set(key, value, setArgument));
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime){
		return stringOpsOpsExecute((ops)->ops.setEx(key, value, lifetime));
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		return stringOpsOpsExecute((ops)->ops.setEx(key, value, lifetime));
	}

	@Override
	public Status setNx(final String key, final String value){
		return stringOpsOpsExecute((ops)->ops.setNx(key, value));
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		return stringOpsOpsExecute((ops)->ops.setNx(key, value));
	}

	@Override
	public long setRange(final String key, final long offset, final String value){
		return stringOpsOpsExecute((ops)->ops.setRange(key, offset, value));
	}

	@Override
	public long setRange(final byte[] key, final long offset, final byte[] value){
		return stringOpsOpsExecute((ops)->ops.setRange(key, offset, value));
	}

	@Override
	public String getRange(final String key, final long start, final long end){
		return stringOpsOpsExecute((ops)->ops.getRange(key, start, end));
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		return stringOpsOpsExecute((ops)->ops.getRange(key, start, end));
	}

	@Override
	public long strlen(final String key){
		return stringOpsOpsExecute((ops)->ops.strlen(key));
	}

	@Override
	public long strlen(final byte[] key){
		return stringOpsOpsExecute((ops)->ops.strlen(key));
	}

	@Override
	public String substr(final String key, final long start, final long end){
		return stringOpsOpsExecute((ops)->ops.substr(key, start, end));
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end){
		return stringOpsOpsExecute((ops)->ops.substr(key, start, end));
	}

	@Override
	public Status multi(){
		return transactionOpsExecute((ops)->ops.multi());
	}

	@Override
	public List<Object> exec(){
		return transactionOpsExecute((ops)->ops.exec());
	}

	@Override
	public void discard(){
		transactionOpsExecute((ops)->{
			ops.discard();
			return null;
		});
	}

	@Override
	public Status watch(final String... keys){
		return transactionOpsExecute((ops)->ops.watch(keys));
	}

	@Override
	public Status watch(final byte[]... keys){
		return transactionOpsExecute((ops)->ops.watch(keys));
	}

	@Override
	public Status unwatch(){
		return transactionOpsExecute((ops)->ops.unwatch());
	}

}
