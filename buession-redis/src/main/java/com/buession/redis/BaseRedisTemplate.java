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

import com.buession.core.collect.Maps;
import com.buession.lang.Geo;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.datasource.DataSource;
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
import com.buession.redis.core.ClusterRedisNode;
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
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.SlowLog;
import com.buession.redis.core.Stream;
import com.buession.redis.core.StreamConsumer;
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.StreamFull;
import com.buession.redis.core.StreamGroup;
import com.buession.redis.core.StreamPending;
import com.buession.redis.core.StreamPendingSummary;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.Type;
import com.buession.redis.core.AclUser;
import com.buession.redis.core.ZRangeBy;
import com.buession.redis.pipeline.Pipeline;

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
	 * @param dataSource
	 * 		数据源
	 */
	public BaseRedisTemplate(DataSource dataSource){
		super(dataSource);
	}

	@Override
	public Long bitCount(final String key){
		return execute((client)->client.bitMapOperations().bitCount(rawKey(key)));
	}

	@Override
	public Long bitCount(final byte[] key){
		return execute((client)->client.bitMapOperations().bitCount(rawKey(key)));
	}

	@Override
	public Long bitCount(final String key, final long start, final long end){
		return execute((client)->client.bitMapOperations().bitCount(rawKey(key), start, end));
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end){
		return execute((client)->client.bitMapOperations().bitCount(rawKey(key), start, end));
	}

	@Override
	public Long bitCount(final String key, final long start, final long end, final BitCountOption bitCountOption){
		return execute((client)->client.bitMapOperations().bitCount(rawKey(key), start, end, bitCountOption));
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end, final BitCountOption bitCountOption){
		return execute((client)->client.bitMapOperations().bitCount(rawKey(key), start, end, bitCountOption));
	}

	@Override
	public List<Long> bitField(final String key, final String... arguments){
		return execute((client)->client.bitMapOperations().bitField(rawKey(key), arguments));
	}

	@Override
	public List<Long> bitField(final byte[] key, final byte[]... arguments){
		return execute((client)->client.bitMapOperations().bitField(rawKey(key), arguments));
	}

	@Override
	public List<Long> bitFieldRo(final String key, final String... arguments){
		return execute((client)->client.bitMapOperations().bitFieldRo(rawKey(key), arguments));
	}

	@Override
	public List<Long> bitFieldRo(final byte[] key, final byte[]... arguments){
		return execute((client)->client.bitMapOperations().bitFieldRo(rawKey(key), arguments));
	}

	@Override
	public Long bitOp(final BitOperation operation, final String destKey, final String... keys){
		return execute((client)->client.bitMapOperations().bitOp(operation, rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys){
		return execute((client)->client.bitMapOperations().bitOp(operation, rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long bitPos(final String key, final boolean value){
		return execute((client)->client.bitMapOperations().bitPos(rawKey(key), value));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value){
		return execute((client)->client.bitMapOperations().bitPos(rawKey(key), value));
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end){
		return execute((client)->client.bitMapOperations().bitPos(rawKey(key), value, start, end));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end){
		return execute((client)->client.bitMapOperations().bitPos(rawKey(key), value, start, end));
	}

	@Override
	public Boolean getBit(final String key, final long offset){
		return execute((client)->client.bitMapOperations().getBit(rawKey(key), offset));
	}

	@Override
	public Boolean getBit(final byte[] key, final long offset){
		return execute((client)->client.bitMapOperations().getBit(rawKey(key), offset));
	}

	@Override
	public Boolean setBit(final String key, final long offset, final boolean value){
		return execute((client)->client.bitMapOperations().setBit(rawKey(key), offset, value));
	}

	@Override
	public Boolean setBit(final byte[] key, final long offset, final boolean value){
		return execute((client)->client.bitMapOperations().setBit(rawKey(key), offset, value));
	}

	@Override
	public String clusterMyId(){
		return execute((client)->client.clusterOperations().clusterMyId());
	}

	@Override
	public Status clusterAddSlots(final int... slots){
		return execute((client)->client.clusterOperations().clusterAddSlots(slots));
	}

	@Override
	public List<ClusterSlot> clusterSlots(){
		return execute((client)->client.clusterOperations().clusterSlots());
	}

	@Override
	public Integer clusterCountFailureReports(final String nodeId){
		return execute((client)->client.clusterOperations().clusterCountFailureReports(nodeId));
	}

	@Override
	public Integer clusterCountFailureReports(final byte[] nodeId){
		return execute((client)->client.clusterOperations().clusterCountFailureReports(nodeId));
	}

	@Override
	public Long clusterCountKeysInSlot(final int slot){
		return execute((client)->client.clusterOperations().clusterCountKeysInSlot(slot));
	}

	@Override
	public Status clusterDelSlots(final int... slots){
		return execute((client)->client.clusterOperations().clusterDelSlots(slots));
	}

	@Override
	public Status clusterFlushSlots(){
		return execute((client)->client.clusterOperations().clusterFlushSlots());
	}

	@Override
	public Status clusterFailover(final ClusterFailoverOption clusterFailoverOption){
		return execute((client)->client.clusterOperations().clusterFailover(clusterFailoverOption));
	}

	@Override
	public Status clusterForget(final String nodeId){
		return execute((client)->client.clusterOperations().clusterForget(nodeId));
	}

	@Override
	public Status clusterForget(final byte[] nodeId){
		return execute((client)->client.clusterOperations().clusterForget(nodeId));
	}

	@Override
	public List<String> clusterGetKeysInSlot(final int slot, final long count){
		return execute((client)->client.clusterOperations().clusterGetKeysInSlot(slot, count));
	}

	@Override
	public Long clusterKeySlot(final String key){
		return execute((client)->client.clusterOperations().clusterKeySlot(rawKey(key)));
	}

	@Override
	public Long clusterKeySlot(final byte[] key){
		return execute((client)->client.clusterOperations().clusterKeySlot(rawKey(key)));
	}

	@Override
	public ClusterInfo clusterInfo(){
		return execute((client)->client.clusterOperations().clusterInfo());
	}

	@Override
	public Status clusterMeet(final String ip, final int port){
		return execute((client)->client.clusterOperations().clusterMeet(ip, port));
	}

	@Override
	public List<ClusterRedisNode> clusterNodes(){
		return execute((client)->client.clusterOperations().clusterNodes());
	}

	@Override
	public List<ClusterRedisNode> clusterSlaves(final String nodeId){
		return execute((client)->client.clusterOperations().clusterSlaves(nodeId));
	}

	@Override
	public List<ClusterRedisNode> clusterSlaves(final byte[] nodeId){
		return execute((client)->client.clusterOperations().clusterSlaves(nodeId));
	}

	@Override
	public List<ClusterRedisNode> clusterReplicas(final String nodeId){
		return execute((client)->client.clusterOperations().clusterReplicas(nodeId));
	}

	@Override
	public List<ClusterRedisNode> clusterReplicas(final byte[] nodeId){
		return execute((client)->client.clusterOperations().clusterReplicas(nodeId));
	}

	@Override
	public Status clusterReplicate(final String nodeId){
		return execute((client)->client.clusterOperations().clusterReplicate(nodeId));
	}

	@Override
	public Status clusterReplicate(final byte[] nodeId){
		return execute((client)->client.clusterOperations().clusterReplicate(nodeId));
	}

	@Override
	public Status clusterReset(){
		return execute((client)->client.clusterOperations().clusterReset());
	}

	@Override
	public Status clusterReset(final ClusterResetOption clusterResetOption){
		return execute((client)->client.clusterOperations().clusterReset(clusterResetOption));
	}

	@Override
	public Status clusterSaveConfig(){
		return execute((client)->client.clusterOperations().clusterSaveConfig());
	}

	@Override
	public Status clusterSetConfigEpoch(final long configEpoch){
		return execute((client)->client.clusterOperations().clusterSetConfigEpoch(configEpoch));
	}

	@Override
	public KeyValue<BumpEpoch, Integer> clusterBumpEpoch(){
		return execute((client)->client.clusterOperations().clusterBumpEpoch());
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final String nodeId){
		return execute((client)->client.clusterOperations().clusterSetSlot(slot, setSlotOption, nodeId));
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final byte[] nodeId){
		return execute((client)->client.clusterOperations().clusterSetSlot(slot, setSlotOption, nodeId));
	}

	@Override
	public Status asking(){
		return execute((client)->client.clusterOperations().asking());
	}

	@Override
	public Status readWrite(){
		return execute((client)->client.clusterOperations().readWrite());
	}

	@Override
	public Status readOnly(){
		return execute((client)->client.clusterOperations().readOnly());
	}

	@Override
	public Status auth(final String user, final String password){
		return execute((client)->client.connectionOperations().auth(user, password));
	}

	@Override
	public Status auth(final byte[] user, final byte[] password){
		return execute((client)->client.connectionOperations().auth(user, password));
	}

	@Override
	public Status auth(final String password){
		return execute((client)->client.connectionOperations().auth(password));
	}

	@Override
	public Status auth(final byte[] password){
		return execute((client)->client.connectionOperations().auth(password));
	}

	@Override
	public String echo(final String str){
		return execute((client)->client.connectionOperations().echo(str));
	}

	@Override
	public byte[] echo(final byte[] str){
		return execute((client)->client.connectionOperations().echo(str));
	}

	@Override
	public Status ping(){
		return execute((client)->client.connectionOperations().ping());
	}

	@Override
	public Status reset(){
		return execute((client)->client.connectionOperations().reset());
	}

	@Override
	public Status quit(){
		return execute((client)->client.connectionOperations().quit());
	}

	@Override
	public Status select(final int db){
		return execute((client)->client.connectionOperations().select(db));
	}

	@Override
	public Status clientCaching(final boolean isYes){
		return execute((client)->client.connectionOperations().clientCaching(isYes));
	}

	@Override
	public Long clientId(){
		return execute((client)->client.connectionOperations().clientId());
	}

	@Override
	public Status clientSetName(final String name){
		return execute((client)->client.connectionOperations().clientSetName(name));
	}

	@Override
	public Status clientSetName(final byte[] name){
		return execute((client)->client.connectionOperations().clientSetName(name));
	}

	@Override
	public String clientGetName(){
		return execute((client)->client.connectionOperations().clientGetName());
	}

	@Override
	public Integer clientGetRedir(){
		return execute((client)->client.connectionOperations().clientGetRedir());
	}

	@Override
	public List<Client> clientList(){
		return execute((client)->client.connectionOperations().clientList());
	}

	@Override
	public List<Client> clientList(final ClientType clientType){
		return execute((client)->client.connectionOperations().clientList(clientType));
	}

	@Override
	public Client clientInfo(){
		return execute((client)->client.connectionOperations().clientInfo());
	}

	@Override
	public Status clientPause(final int timeout){
		return execute((client)->client.connectionOperations().clientPause(timeout));
	}

	@Override
	public Status clientReply(final ClientReply option){
		return execute((client)->client.connectionOperations().clientReply(option));
	}

	@Override
	public Status clientKill(final String host, final int port){
		return execute((client)->client.connectionOperations().clientKill(host, port));
	}

	@Override
	public Status clientUnblock(final int clientId){
		return execute((client)->client.connectionOperations().clientUnblock(clientId));
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type){
		return execute((client)->client.connectionOperations().clientUnblock(clientId, type));
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude){
		return execute((client)->client.geoOperations().geoAdd(rawKey(key), member, longitude, latitude));
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
		return execute((client)->client.geoOperations().geoAdd(rawKey(key), member, longitude, latitude));
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates){
		return execute((client)->client.geoOperations().geoAdd(rawKey(key), memberCoordinates));
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates){
		return execute((client)->client.geoOperations().geoAdd(rawKey(key), memberCoordinates));
	}

	@Override
	public List<String> geoHash(final String key, final String... members){
		return execute((client)->client.geoOperations().geoHash(rawKey(key), members));
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members){
		return execute((client)->client.geoOperations().geoHash(rawKey(key), members));
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members){
		return execute((client)->client.geoOperations().geoPos(rawKey(key), members));
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members){
		return execute((client)->client.geoOperations().geoPos(rawKey(key), members));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2){
		return execute((client)->client.geoOperations().geoDist(rawKey(key), member1, member2));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
		return execute((client)->client.geoOperations().geoDist(rawKey(key), member1, member2));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit){
		return execute((client)->client.geoOperations().geoDist(rawKey(key), member1, member2, unit));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit){
		return execute((client)->client.geoOperations().geoDist(rawKey(key), member1, member2, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit){
		return execute((client)->client.geoOperations().geoRadius(rawKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit){
		return execute((client)->client.geoOperations().geoRadius(rawKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument){
		return execute((client)->client.geoOperations()
				.geoRadius(rawKey(key), longitude, latitude, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument){
		return execute((client)->client.geoOperations()
				.geoRadius(rawKey(key), longitude, latitude, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit){
		return execute((client)->client.geoOperations().geoRadiusRo(rawKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit){
		return execute((client)->client.geoOperations().geoRadiusRo(rawKey(key), longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument){
		return execute((client)->client.geoOperations()
				.geoRadiusRo(rawKey(key), longitude, latitude, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument){
		return execute((client)->client.geoOperations()
				.geoRadiusRo(rawKey(key), longitude, latitude, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit){
		return execute((client)->client.geoOperations().geoRadiusByMember(rawKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit){
		return execute((client)->client.geoOperations().geoRadiusByMember(rawKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		return execute((client)->client.geoOperations()
				.geoRadiusByMember(rawKey(key), member, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		return execute((client)->client.geoOperations()
				.geoRadiusByMember(rawKey(key), member, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit){
		return execute((client)->client.geoOperations().geoRadiusByMemberRo(rawKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit){
		return execute((client)->client.geoOperations().geoRadiusByMemberRo(rawKey(key), member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		return execute((client)->client.geoOperations()
				.geoRadiusByMemberRo(rawKey(key), member, radius, unit, geoRadiusArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument){
		return execute((client)->client.geoOperations()
				.geoRadiusByMemberRo(rawKey(key), member, radius, unit, geoRadiusArgument));
	}

	@Override
	public Long hDel(final String key, final String... fields){
		return execute((client)->client.hashOperations().hDel(rawKey(key), fields));
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields){
		return execute((client)->client.hashOperations().hDel(rawKey(key), fields));
	}

	@Override
	public Boolean hExists(final String key, final String field){
		return execute((client)->client.hashOperations().hExists(rawKey(key), field));
	}

	@Override
	public Boolean hExists(final byte[] key, final byte[] field){
		return execute((client)->client.hashOperations().hExists(rawKey(key), field));
	}

	@Override
	public String hGet(final String key, final String field){
		return execute((client)->client.hashOperations().hGet(rawKey(key), field));
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field){
		return execute((client)->client.hashOperations().hGet(rawKey(key), field));
	}

	@Override
	public Map<String, String> hGetAll(final String key){
		return execute((client)->client.hashOperations().hGetAll(rawKey(key)));
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key){
		return execute((client)->client.hashOperations().hGetAll(rawKey(key)));
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value){
		return execute((client)->client.hashOperations().hIncrBy(rawKey(key), field, value));
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value){
		return execute((client)->client.hashOperations().hIncrBy(rawKey(key), field, value));
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value){
		return execute((client)->client.hashOperations().hIncrByFloat(rawKey(key), field, value));
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value){
		return execute((client)->client.hashOperations().hIncrByFloat(rawKey(key), field, value));
	}

	@Override
	public Set<String> hKeys(final String key){
		return execute((client)->client.hashOperations().hKeys(rawKey(key)));
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key){
		return execute((client)->client.hashOperations().hKeys(rawKey(key)));
	}

	@Override
	public Long hLen(final String key){
		return execute((client)->client.hashOperations().hLen(rawKey(key)));
	}

	@Override
	public Long hLen(final byte[] key){
		return execute((client)->client.hashOperations().hLen(rawKey(key)));
	}

	@Override
	public List<String> hMGet(final String key, final String... fields){
		return execute((client)->client.hashOperations().hMGet(rawKey(key), fields));
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields){
		return execute((client)->client.hashOperations().hMGet(rawKey(key), fields));
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data){
		return execute((client)->client.hashOperations().hMSet(rawKey(key), data));
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data){
		return execute((client)->client.hashOperations().hMSet(rawKey(key), data));
	}

	@Override
	public String hRandField(final String key){
		return execute((client)->client.hashOperations().hRandField(rawKey(key)));
	}

	@Override
	public byte[] hRandField(final byte[] key){
		return execute((client)->client.hashOperations().hRandField(rawKey(key)));
	}

	@Override
	public List<String> hRandField(final String key, final long count){
		return execute((client)->client.hashOperations().hRandField(rawKey(key), count));
	}

	@Override
	public List<byte[]> hRandField(final byte[] key, final long count){
		return execute((client)->client.hashOperations().hRandField(rawKey(key), count));
	}

	@Override
	public Map<String, String> hRandFieldWithValues(final String key, final long count){
		return execute((client)->client.hashOperations().hRandFieldWithValues(rawKey(key), count));
	}

	@Override
	public Map<byte[], byte[]> hRandFieldWithValues(final byte[] key, final long count){
		return execute((client)->client.hashOperations().hRandFieldWithValues(rawKey(key), count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor){
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor){
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor){
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern){
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern){
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern){
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final long count){
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final long count){
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final long count){
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final long count){
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern,
												 final long count){
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern,
												 final long count){
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
												 final long count){
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
												 final long count){
		return execute((client)->client.hashOperations().hScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public Long hSet(final String key, final String field, final String value){
		return execute((client)->client.hashOperations().hSet(rawKey(key), field, value));
	}

	@Override
	public Long hSet(final byte[] key, final byte[] field, final byte[] value){
		return execute((client)->client.hashOperations().hSet(rawKey(key), field, value));
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value){
		return execute((client)->client.hashOperations().hSetNx(rawKey(key), field, value));
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value){
		return execute((client)->client.hashOperations().hSetNx(rawKey(key), field, value));
	}

	@Override
	public Long hStrLen(final String key, final String field){
		return execute((client)->client.hashOperations().hStrLen(rawKey(key), field));
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field){
		return execute((client)->client.hashOperations().hStrLen(rawKey(key), field));
	}

	@Override
	public List<String> hVals(final String key){
		return execute((client)->client.hashOperations().hVals(rawKey(key)));
	}

	@Override
	public List<byte[]> hVals(final byte[] key){
		return execute((client)->client.hashOperations().hVals(rawKey(key)));
	}

	@Override
	public Status pfAdd(final String key, final String... elements){
		return execute((client)->client.hyperLogLogOperations().pfAdd(rawKey(key), elements));
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements){
		return execute((client)->client.hyperLogLogOperations().pfAdd(rawKey(key), elements));
	}

	@Override
	public Status pfMerge(final String destKey, final String... keys){
		return execute((client)->client.hyperLogLogOperations().pfMerge(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys){
		return execute((client)->client.hyperLogLogOperations().pfMerge(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long pfCount(final String... keys){
		return execute((client)->client.hyperLogLogOperations().pfCount(rawKeys(keys)));
	}

	@Override
	public Long pfCount(final byte[]... keys){
		return execute((client)->client.hyperLogLogOperations().pfCount(rawKeys(keys)));
	}

	@Override
	public Long del(final String... keys){
		return execute((client)->client.keyOperations().del(rawKeys(keys)));
	}

	@Override
	public Long del(final byte[]... keys){
		return execute((client)->client.keyOperations().del(rawKeys(keys)));
	}

	@Override
	public String dump(final String key){
		return execute((client)->client.keyOperations().dump(rawKey(key)));
	}

	@Override
	public byte[] dump(final byte[] key){
		return execute((client)->client.keyOperations().dump(rawKey(key)));
	}

	@Override
	public Boolean exists(final String key){
		return execute((client)->client.keyOperations().exists(rawKey(key)));
	}

	@Override
	public Boolean exists(final byte[] key){
		return execute((client)->client.keyOperations().exists(rawKey(key)));
	}

	@Override
	public Long exists(final String... keys){
		return execute((client)->client.keyOperations().exists(rawKeys(keys)));
	}

	@Override
	public Long exists(final byte[]... keys){
		return execute((client)->client.keyOperations().exists(rawKeys(keys)));
	}

	@Override
	public Status expire(final String key, final int lifetime){
		return execute((client)->client.keyOperations().expire(rawKey(key), lifetime));
	}

	@Override
	public Status expire(final byte[] key, final int lifetime){
		return execute((client)->client.keyOperations().expire(rawKey(key), lifetime));
	}

	@Override
	public Status expire(final String key, final int lifetime, final ExpireOption expireOption){
		return execute((client)->client.keyOperations().expire(rawKey(key), lifetime, expireOption));
	}

	@Override
	public Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption){
		return execute((client)->client.keyOperations().expire(rawKey(key), lifetime, expireOption));
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp){
		return execute((client)->client.keyOperations().expireAt(rawKey(key), unixTimestamp));
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		return execute((client)->client.keyOperations().expireAt(rawKey(key), unixTimestamp));
	}

	@Override
	public Status pExpire(final String key, final int lifetime){
		return execute((client)->client.keyOperations().pExpire(rawKey(key), lifetime));
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		return execute((client)->client.keyOperations().pExpire(rawKey(key), lifetime));
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp){
		return execute((client)->client.keyOperations().pExpireAt(rawKey(key), unixTimestamp));
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		return execute((client)->client.keyOperations().pExpireAt(rawKey(key), unixTimestamp));
	}

	@Override
	public Status persist(final String key){
		return execute((client)->client.keyOperations().persist(rawKey(key)));
	}

	@Override
	public Status persist(final byte[] key){
		return execute((client)->client.keyOperations().persist(rawKey(key)));
	}

	@Override
	public Long ttl(final String key){
		return execute((client)->client.keyOperations().ttl(rawKey(key)));
	}

	@Override
	public Long ttl(final byte[] key){
		return execute((client)->client.keyOperations().ttl(rawKey(key)));
	}

	@Override
	public Long pTtl(final String key){
		return execute((client)->client.keyOperations().pTtl(rawKey(key)));
	}

	@Override
	public Long pTtl(final byte[] key){
		return execute((client)->client.keyOperations().pTtl(rawKey(key)));
	}

	@Override
	public Status copy(final String key, final String destKey){
		return execute((client)->client.keyOperations().copy(rawKey(key), rawKey(destKey)));
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey){
		return execute((client)->client.keyOperations().copy(rawKey(key), rawKey(destKey)));
	}

	@Override
	public Status copy(final String key, final String destKey, final int db){
		return execute((client)->client.keyOperations().copy(rawKey(key), rawKey(destKey), db));
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db){
		return execute((client)->client.keyOperations().copy(rawKey(key), rawKey(destKey), db));
	}

	@Override
	public Status copy(final String key, final String destKey, final boolean replace){
		return execute((client)->client.keyOperations().copy(rawKey(key), rawKey(destKey), replace));
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final boolean replace){
		return execute((client)->client.keyOperations().copy(rawKey(key), rawKey(destKey), replace));
	}

	@Override
	public Status copy(final String key, final String destKey, final int db, final boolean replace){
		return execute((client)->client.keyOperations().copy(rawKey(key), rawKey(destKey), db, replace));
	}

	@Override
	public Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace){
		return execute((client)->client.keyOperations().copy(rawKey(key), rawKey(destKey), db, replace));
	}

	@Override
	public Status move(final String key, final int db){
		return execute((client)->client.keyOperations().move(rawKey(key), db));
	}

	@Override
	public Status move(final byte[] key, final int db){
		return execute((client)->client.keyOperations().move(rawKey(key), db));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final String... keys){
		return execute((client)->client.keyOperations().migrate(host, port, db, timeout, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout, final byte[]... keys){
		return execute((client)->client.keyOperations().migrate(host, port, db, timeout, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final String... keys){
		return execute((client)->client.keyOperations().migrate(host, port, db, timeout, operation, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final int timeout,
						  final MigrateOperation operation, final byte[]... keys){
		return execute((client)->client.keyOperations().migrate(host, port, db, timeout, operation, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final String... keys){
		return execute((client)->client.keyOperations().migrate(host, port, db, password, timeout, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final byte[]... keys){
		return execute((client)->client.keyOperations().migrate(host, port, db, password, timeout, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String password, final int timeout,
						  final MigrateOperation operation, final String... keys){
		return execute(
				(client)->client.keyOperations().migrate(host, port, db, password, timeout, operation, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] password, final int timeout,
						  final MigrateOperation operation, final byte[]... keys){
		return execute(
				(client)->client.keyOperations().migrate(host, port, db, password, timeout, operation, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final String... keys){
		return execute(
				(client)->client.keyOperations().migrate(host, port, db, user, password, timeout, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final byte[]... keys){
		return execute(
				(client)->client.keyOperations().migrate(host, port, db, user, password, timeout, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final String user, final String password,
						  final int timeout, final MigrateOperation operation, final String... keys){
		return execute((client)->client.keyOperations()
				.migrate(host, port, db, user, password, timeout, operation, rawKeys(keys)));
	}

	@Override
	public Status migrate(final String host, final int port, final int db, final byte[] user, final byte[] password,
						  final int timeout, final MigrateOperation operation, final byte[]... keys){
		return execute((client)->client.keyOperations()
				.migrate(host, port, db, user, password, timeout, operation, rawKeys(keys)));
	}

	@Override
	public Set<String> keys(final String pattern){
		return execute((client)->client.keyOperations().keys(pattern));
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern){
		return execute((client)->client.keyOperations().keys(pattern));
	}

	@Override
	public String randomKey(){
		return execute((client)->client.keyOperations().randomKey());
	}

	@Override
	public Status rename(final String key, final String newKey){
		return execute((client)->client.keyOperations().rename(rawKey(key), rawKey(newKey)));
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
		return execute((client)->client.keyOperations().rename(rawKey(key), rawKey(newKey)));
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		return execute((client)->client.keyOperations().renameNx(rawKey(key), rawKey(newKey)));
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		return execute((client)->client.keyOperations().renameNx(rawKey(key), rawKey(newKey)));
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl){
		return execute((client)->client.keyOperations().restore(rawKey(key), serializedValue, ttl));
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		return execute((client)->client.keyOperations().restore(rawKey(key), serializedValue, ttl));
	}

	@Override
	public Status restore(final String key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument){
		return execute((client)->client.keyOperations().restore(rawKey(key), serializedValue, ttl, argument));
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
						  final RestoreArgument argument){
		return execute((client)->client.keyOperations().restore(rawKey(key), serializedValue, ttl, argument));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor){
		return execute((client)->client.keyOperations().scan(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor){
		return execute((client)->client.keyOperations().scan(cursor));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		return execute((client)->client.keyOperations().scan(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern){
		return execute((client)->client.keyOperations().scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern){
		return execute((client)->client.keyOperations().scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern){
		return execute((client)->client.keyOperations().scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		return execute((client)->client.keyOperations().scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final long count){
		return execute((client)->client.keyOperations().scan(cursor, count));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final long count){
		return execute((client)->client.keyOperations().scan(cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final long count){
		return execute((client)->client.keyOperations().scan(cursor, count));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern, final long count){
		return execute((client)->client.keyOperations().scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern, final long count){
		return execute((client)->client.keyOperations().scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final long count){
		return execute((client)->client.keyOperations().scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final long count){
		return execute((client)->client.keyOperations().scan(cursor, pattern, count));
	}

	@Override
	public List<String> sort(final String key){
		return execute((client)->client.keyOperations().sort(rawKey(key)));
	}

	@Override
	public List<byte[]> sort(final byte[] key){
		return execute((client)->client.keyOperations().sort(rawKey(key)));
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument){
		return execute((client)->client.keyOperations().sort(rawKey(key), sortArgument));
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument){
		return execute((client)->client.keyOperations().sort(rawKey(key), sortArgument));
	}

	@Override
	public Long sort(final String key, final String destKey){
		return execute((client)->client.keyOperations().sort(rawKey(key), rawKey(destKey)));
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey){
		return execute((client)->client.keyOperations().sort(rawKey(key), rawKey(destKey)));
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument){
		return execute((client)->client.keyOperations().sort(rawKey(key), rawKey(destKey), sortArgument));
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		return execute((client)->client.keyOperations().sort(rawKey(key), rawKey(destKey), sortArgument));
	}

	@Override
	public Long touch(final String... keys){
		return execute((client)->client.keyOperations().touch(rawKeys(keys)));
	}

	@Override
	public Long touch(final byte[]... keys){
		return execute((client)->client.keyOperations().touch(rawKeys(keys)));
	}

	@Override
	public Type type(final String key){
		return execute((client)->client.keyOperations().type(rawKey(key)));
	}

	@Override
	public Type type(final byte[] key){
		return execute((client)->client.keyOperations().type(rawKey(key)));
	}

	@Override
	public Long unlink(final String... keys){
		return execute((client)->client.keyOperations().unlink(rawKeys(keys)));
	}

	@Override
	public Long unlink(final byte[]... keys){
		return execute((client)->client.keyOperations().unlink(rawKeys(keys)));
	}

	@Override
	public Long wait(final int replicas, final int timeout){
		return execute((client)->client.keyOperations().wait(replicas, timeout));
	}

	@Override
	public ObjectEncoding objectEncoding(final String key){
		return execute((client)->client.keyOperations().objectEncoding(rawKey(key)));
	}

	@Override
	public ObjectEncoding objectEncoding(final byte[] key){
		return execute((client)->client.keyOperations().objectEncoding(rawKey(key)));
	}

	@Override
	public Long objectFreq(final String key){
		return execute((client)->client.keyOperations().objectFreq(rawKey(key)));
	}

	@Override
	public Long objectFreq(final byte[] key){
		return execute((client)->client.keyOperations().objectFreq(rawKey(key)));
	}

	@Override
	public Long objectIdleTime(final String key){
		return execute((client)->client.keyOperations().objectIdleTime(rawKey(key)));
	}

	@Override
	public Long objectIdleTime(final byte[] key){
		return execute((client)->client.keyOperations().objectIdleTime(rawKey(key)));
	}

	@Override
	public Long objectRefcount(final String key){
		return execute((client)->client.keyOperations().objectRefcount(rawKey(key)));
	}

	@Override
	public Long objectRefcount(final byte[] key){
		return execute((client)->client.keyOperations().objectRefcount(rawKey(key)));
	}

	@Override
	public String lIndex(final String key, final long index){
		return execute((client)->client.listOperations().lIndex(rawKey(key), index));
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index){
		return execute((client)->client.listOperations().lIndex(rawKey(key), index));
	}

	@Override
	public Long lInsert(final String key, final ListPosition position, final String pivot, final String value){
		return execute((client)->client.listOperations().lInsert(rawKey(key), position, pivot, value));
	}

	@Override
	public Long lInsert(final byte[] key, final ListPosition position, final byte[] pivot, final byte[] value){
		return execute((client)->client.listOperations().lInsert(rawKey(key), position, pivot, value));
	}

	@Override
	public Status lSet(final String key, final long index, final String value){
		return execute((client)->client.listOperations().lSet(rawKey(key), index, value));
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value){
		return execute((client)->client.listOperations().lSet(rawKey(key), index, value));
	}

	@Override
	public Long lLen(final String key){
		return execute((client)->client.listOperations().lLen(rawKey(key)));
	}

	@Override
	public Long lLen(final byte[] key){
		return execute((client)->client.listOperations().lLen(rawKey(key)));
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end){
		return execute((client)->client.listOperations().lRange(rawKey(key), start, end));
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end){
		return execute((client)->client.listOperations().lRange(rawKey(key), start, end));
	}

	@Override
	public Long lPos(final String key, final String element){
		return execute((client)->client.listOperations().lPos(rawKey(key), element));
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element){
		return execute((client)->client.listOperations().lPos(rawKey(key), element));
	}

	@Override
	public Long lPos(final String key, final String element, final LPosArgument lPosArgument){
		return execute((client)->client.listOperations().lPos(rawKey(key), element, lPosArgument));
	}

	@Override
	public Long lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument){
		return execute((client)->client.listOperations().lPos(rawKey(key), element, lPosArgument));
	}

	@Override
	public List<Long> lPos(final String key, String element, final LPosArgument lPosArgument, final long count){
		return execute((client)->client.listOperations().lPos(rawKey(key), element, lPosArgument, count));
	}

	@Override
	public List<Long> lPos(final byte[] key, final byte[] element, final LPosArgument lPosArgument, final long count){
		return execute((client)->client.listOperations().lPos(rawKey(key), element, lPosArgument, count));
	}

	@Override
	public Long lRem(final String key, final String value, final long count){
		return execute((client)->client.listOperations().lRem(rawKey(key), value, count));
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count){
		return execute((client)->client.listOperations().lRem(rawKey(key), value, count));
	}

	@Override
	public Status lTrim(final String key, final long start, final long end){
		return execute((client)->client.listOperations().lTrim(rawKey(key), start, end));
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end){
		return execute((client)->client.listOperations().lTrim(rawKey(key), start, end));
	}

	@Override
	public String lMove(final String key, final String destKey, final Direction from, final Direction to){
		return execute((client)->client.listOperations().lMove(rawKey(destKey), rawKey(destKey), from, to));
	}

	@Override
	public byte[] lMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to){
		return execute((client)->client.listOperations().lMove(rawKey(key), rawKey(destKey), from, to));
	}

	@Override
	public String blMove(final String key, final String destKey, final Direction from, final Direction to,
						 final int timeout){
		return execute((client)->client.listOperations().blMove(rawKey(destKey), rawKey(destKey), from, to, timeout));
	}

	@Override
	public byte[] blMove(final byte[] key, final byte[] destKey, final Direction from, final Direction to,
						 final int timeout){
		return execute((client)->client.listOperations().blMove(rawKey(key), rawKey(destKey), from, to, timeout));
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout){
		return execute((client)->client.listOperations().blPop(rawKeys(keys), timeout));
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout){
		return execute((client)->client.listOperations().blPop(rawKeys(keys), timeout));
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout){
		return execute((client)->client.listOperations().brPop(rawKeys(keys), timeout));
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout){
		return execute((client)->client.listOperations().brPop(rawKeys(keys), timeout));
	}

	@Override
	public String brPoplPush(final String key, final String destKey, final int timeout){
		return execute((client)->client.listOperations().brPoplPush(rawKey(key), rawKey(destKey), timeout));
	}

	@Override
	public byte[] brPoplPush(final byte[] key, final byte[] destKey, final int timeout){
		return execute((client)->client.listOperations().brPoplPush(rawKey(key), rawKey(destKey), timeout));
	}

	@Override
	public String lPop(final String key){
		return execute((client)->client.listOperations().lPop(rawKey(key)));
	}

	@Override
	public byte[] lPop(final byte[] key){
		return execute((client)->client.listOperations().lPop(rawKey(key)));
	}

	@Override
	public Long lPush(final String key, final String... values){
		return execute((client)->client.listOperations().lPush(rawKey(key), values));
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values){
		return execute((client)->client.listOperations().lPush(rawKey(key), values));
	}

	@Override
	public Long lPushX(final String key, final String... values){
		return execute((client)->client.listOperations().lPushX(rawKey(key), values));
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values){
		return execute((client)->client.listOperations().lPushX(rawKey(key), values));
	}

	@Override
	public String rPop(final String key){
		return execute((client)->client.listOperations().rPop(rawKey(key)));
	}

	@Override
	public byte[] rPop(final byte[] key){
		return execute((client)->client.listOperations().rPop(rawKey(key)));
	}

	@Override
	public String rPoplPush(final String key, final String destKey){
		return execute((client)->client.listOperations().rPoplPush(rawKey(key), rawKey(destKey)));
	}

	@Override
	public byte[] rPoplPush(final byte[] key, final byte[] destKey){
		return execute((client)->client.listOperations().rPoplPush(rawKey(key), rawKey(destKey)));
	}

	@Override
	public Long rPush(final String key, final String... values){
		return execute((client)->client.listOperations().rPush(rawKey(key), values));
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values){
		return execute((client)->client.listOperations().rPush(rawKey(key), values));
	}

	@Override
	public Long rPushX(final String key, final String... values){
		return execute((client)->client.listOperations().rPushX(rawKey(key), values));
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values){
		return execute((client)->client.listOperations().rPushX(rawKey(key), values));
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
		execute((client)->{
			client.pubSubOperations().pSubscribe(patterns, pubSubListener);
			return null;
		});
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
		execute((client)->{
			client.pubSubOperations().pSubscribe(patterns, pubSubListener);
			return null;
		});
	}

	@Override
	public Long publish(final String channel, final String message){
		return execute((client)->client.pubSubOperations().publish(channel, message));
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message){
		return execute((client)->client.pubSubOperations().publish(channel, message));
	}

	@Override
	public List<String> pubsubChannels(){
		return execute((client)->client.pubSubOperations().pubsubChannels());
	}

	@Override
	public List<String> pubsubChannels(final String pattern){
		return execute((client)->client.pubSubOperations().pubsubChannels(pattern));
	}

	@Override
	public List<byte[]> pubsubChannels(final byte[] pattern){
		return execute((client)->client.pubSubOperations().pubsubChannels(pattern));
	}

	@Override
	public Long pubsubNumPat(){
		return execute((client)->client.pubSubOperations().pubsubNumPat());
	}

	@Override
	public Map<String, Long> pubsubNumSub(final String... channels){
		return execute((client)->client.pubSubOperations().pubsubNumSub(channels));
	}

	@Override
	public Map<byte[], Long> pubsubNumSub(final byte[]... channels){
		return execute((client)->client.pubSubOperations().pubsubNumSub(channels));
	}

	@Override
	public Object pUnSubscribe(){
		return execute((client)->client.pubSubOperations().pUnSubscribe());
	}

	@Override
	public Object pUnSubscribe(final String... patterns){
		return execute((client)->client.pubSubOperations().pUnSubscribe(patterns));
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns){
		return execute((client)->client.pubSubOperations().pUnSubscribe(patterns));
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
		execute((client)->{
			client.pubSubOperations().subscribe(channels, pubSubListener);
			return null;
		});
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
		execute((client)->{
			client.pubSubOperations().subscribe(channels, pubSubListener);
			return null;
		});
	}

	@Override
	public Object unSubscribe(){
		return execute((client)->client.pubSubOperations().unSubscribe());
	}

	@Override
	public Object unSubscribe(final String... channels){
		return execute((client)->client.pubSubOperations().unSubscribe(channels));
	}

	@Override
	public Object unSubscribe(final byte[]... channels){
		return execute((client)->client.pubSubOperations().unSubscribe(channels));
	}

	@Override
	public Object eval(final String script){
		return execute((client)->client.scriptingOperations().eval(script));
	}

	@Override
	public Object eval(final byte[] script){
		return execute((client)->client.scriptingOperations().eval(script));
	}

	@Override
	public Object eval(final String script, final String... params){
		return execute((client)->client.scriptingOperations().eval(script, params));
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params){
		return execute((client)->client.scriptingOperations().eval(script, params));
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments){
		return execute((client)->client.scriptingOperations().eval(script, rawKeys(keys), arguments));
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments){
		return execute((client)->client.scriptingOperations().eval(script, rawKeys(keys), arguments));
	}

	@Override
	public Object evalSha(final String digest){
		return execute((client)->client.scriptingOperations().evalSha(digest));
	}

	@Override
	public Object evalSha(final byte[] digest){
		return execute((client)->client.scriptingOperations().evalSha(digest));
	}

	@Override
	public Object evalSha(final String digest, final String... params){
		return execute((client)->client.scriptingOperations().evalSha(digest, params));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... params){
		return execute((client)->client.scriptingOperations().evalSha(digest, params));
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments){
		return execute((client)->client.scriptingOperations().evalSha(digest, rawKeys(keys), arguments));
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments){
		return execute((client)->client.scriptingOperations().evalSha(digest, rawKeys(keys), arguments));
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1){
		return execute((client)->client.scriptingOperations().scriptExists(sha1));
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1){
		return execute((client)->client.scriptingOperations().scriptExists(sha1));
	}

	@Override
	public Status scriptFlush(){
		return execute((client)->client.scriptingOperations().scriptFlush());
	}

	@Override
	public Status scriptFlush(final FlushMode mode){
		return execute((client)->client.scriptingOperations().scriptFlush(mode));
	}

	@Override
	public String scriptLoad(final String script){
		return execute((client)->client.scriptingOperations().scriptLoad(script));
	}

	@Override
	public byte[] scriptLoad(final byte[] script){
		return execute((client)->client.scriptingOperations().scriptLoad(script));
	}

	@Override
	public Status scriptKill(){
		return execute((client)->client.scriptingOperations().scriptKill());
	}

	@Override
	public List<String> aclCat(){
		return execute((client)->client.serverOperations().aclCat());
	}

	@Override
	public List<String> aclCat(final String categoryName){
		return execute((client)->client.serverOperations().aclCat(categoryName));
	}

	@Override
	public List<byte[]> aclCat(final byte[] categoryName){
		return execute((client)->client.serverOperations().aclCat(categoryName));
	}

	@Override
	public Status aclSetUser(final String username, final String... rules){
		return execute((client)->client.serverOperations().aclSetUser(username, rules));
	}

	@Override
	public Status aclSetUser(final byte[] username, final byte[]... rules){
		return execute((client)->client.serverOperations().aclSetUser(username, rules));
	}

	@Override
	public AclUser aclGetUser(final String username){
		return execute((client)->client.serverOperations().aclGetUser(username));
	}

	@Override
	public AclUser aclGetUser(final byte[] username){
		return execute((client)->client.serverOperations().aclGetUser(username));
	}

	@Override
	public List<String> aclUsers(){
		return execute((client)->client.serverOperations().aclUsers());
	}

	@Override
	public String aclWhoAmI(){
		return execute((client)->client.serverOperations().aclWhoAmI());
	}

	@Override
	public Long aclDelUser(final String... usernames){
		return execute((client)->client.serverOperations().aclDelUser(usernames));
	}

	@Override
	public Long aclDelUser(final byte[]... username){
		return execute((client)->client.serverOperations().aclDelUser(username));
	}

	@Override
	public String aclGenPass(){
		return execute((client)->client.serverOperations().aclGenPass());
	}

	@Override
	public List<String> aclList(){
		return execute((client)->client.serverOperations().aclList());
	}

	@Override
	public Status aclLoad(){
		return execute((client)->client.serverOperations().aclLoad());
	}

	@Override
	public List<AclLog> aclLog(){
		return execute((client)->client.serverOperations().aclLog());
	}

	@Override
	public List<AclLog> aclLog(final long count){
		return execute((client)->client.serverOperations().aclLog(count));
	}

	@Override
	public Status aclLogReset(){
		return execute((client)->client.serverOperations().aclLogReset());
	}

	@Override
	public Status aclLogSave(){
		return execute((client)->client.serverOperations().aclLogSave());
	}

	@Override
	public String bgRewriteAof(){
		return execute((client)->client.serverOperations().bgRewriteAof());
	}

	@Override
	public String bgSave(){
		return execute((client)->client.serverOperations().bgSave());
	}

	@Override
	public Status configSet(final String parameter, final String value){
		return execute((client)->client.serverOperations().configSet(parameter, value));
	}

	@Override
	public Status configSet(final byte[] parameter, final byte[] value){
		return execute((client)->client.serverOperations().configSet(parameter, value));
	}

	@Override
	public List<String> configGet(final String parameter){
		return execute((client)->client.serverOperations().configGet(parameter));
	}

	@Override
	public List<byte[]> configGet(final byte[] parameter){
		return execute((client)->client.serverOperations().configGet(parameter));
	}

	@Override
	public Status configResetStat(){
		return execute((client)->client.serverOperations().configResetStat());
	}

	@Override
	public Status configRewrite(){
		return execute((client)->client.serverOperations().configRewrite());
	}

	@Override
	public Long dbSize(){
		return execute((client)->client.serverOperations().dbSize());
	}

	@Override
	public Status failover(){
		return execute((client)->client.serverOperations().failover());
	}

	@Override
	public Status failover(final String host, final int port){
		return execute((client)->client.serverOperations().failover(host, port));
	}

	@Override
	public Status failover(final String host, final int port, final int timeout){
		return execute((client)->client.serverOperations().failover(host, port, timeout));
	}

	@Override
	public Status failover(final String host, final int port, final boolean isForce, final int timeout){
		return execute((client)->client.serverOperations().failover(host, port, isForce, timeout));
	}

	@Override
	public Status failover(final int timeout){
		return execute((client)->client.serverOperations().failover(timeout));
	}

	@Override
	public Status flushAll(){
		return execute((client)->client.serverOperations().flushAll());
	}

	@Override
	public Status flushAll(final FlushMode mode){
		return execute((client)->client.serverOperations().flushAll(mode));
	}

	@Override
	public Status flushDb(){
		return execute((client)->client.serverOperations().flushDb());
	}

	@Override
	public Status flushDb(final FlushMode mode){
		return execute((client)->client.serverOperations().flushDb(mode));
	}

	@Override
	public Info info(){
		return execute((client)->client.serverOperations().info());
	}

	@Override
	public Info info(final Info.Section section){
		return execute((client)->client.serverOperations().info(section));
	}

	@Override
	public Long lastSave(){
		return execute((client)->client.serverOperations().lastSave());
	}

	@Override
	public String memoryDoctor(){
		return execute((client)->client.serverOperations().memoryDoctor());
	}

	@Override
	public Status memoryPurge(){
		return execute((client)->client.serverOperations().memoryPurge());
	}

	@Override
	public MemoryStats memoryStats(){
		return execute((client)->client.serverOperations().memoryStats());
	}

	@Override
	public Long memoryUsage(final String key){
		return execute((client)->client.serverOperations().memoryUsage(key));
	}

	@Override
	public Long memoryUsage(final byte[] key){
		return execute((client)->client.serverOperations().memoryUsage(key));
	}

	@Override
	public Long memoryUsage(final String key, final int samples){
		return execute((client)->client.serverOperations().memoryUsage(rawKey(key), samples));
	}

	@Override
	public Long memoryUsage(final byte[] key, final int samples){
		return execute((client)->client.serverOperations().memoryUsage(rawKey(key), samples));
	}

	@Override
	public List<Module> moduleList(){
		return execute((client)->client.serverOperations().moduleList());
	}

	@Override
	public Status moduleLoad(final String path, final String... arguments){
		return execute((client)->client.serverOperations().moduleLoad(path, arguments));
	}

	@Override
	public Status moduleLoad(final byte[] path, final byte[]... arguments){
		return execute((client)->client.serverOperations().moduleLoad(path, arguments));
	}

	@Override
	public Status moduleUnLoad(final String name){
		return execute((client)->client.serverOperations().moduleUnLoad(name));
	}

	@Override
	public Status moduleUnLoad(final byte[] name){
		return execute((client)->client.serverOperations().moduleUnLoad(name));
	}

	@Override
	public void monitor(final RedisMonitor redisMonitor){
		execute((client)->{
			client.serverOperations().monitor(redisMonitor);
			return null;
		});
	}

	@Override
	public Object pSync(final String replicationId, final long offset){
		return execute((client)->client.serverOperations().pSync(replicationId, offset));
	}

	@Override
	public Object pSync(final byte[] replicationId, final long offset){
		return execute((client)->client.serverOperations().pSync(replicationId, offset));
	}

	@Override
	public void sync(){
		execute((client)->{
			client.serverOperations().sync();
			return null;
		});
	}

	@Override
	public Status replicaOf(final String host, final int port){
		return execute((client)->client.serverOperations().replicaOf(host, port));
	}

	@Override
	public Status slaveOf(final String host, final int port){
		return execute((client)->client.serverOperations().slaveOf(host, port));
	}

	@Override
	public List<Role> role(){
		return execute((client)->client.serverOperations().role());
	}

	@Override
	public Status save(){
		return execute((client)->client.serverOperations().save());
	}

	@Override
	public void shutdown(){
		execute((client)->{
			client.serverOperations().shutdown();
			return null;
		});
	}

	@Override
	public void shutdown(final boolean save){
		execute((client)->{
			client.serverOperations().shutdown(save);
			return null;
		});
	}

	@Override
	public List<SlowLog> slowLogGet(){
		return execute((client)->client.serverOperations().slowLogGet());
	}

	@Override
	public List<SlowLog> slowLogGet(final long count){
		return execute((client)->client.serverOperations().slowLogGet(count));
	}

	@Override
	public Long slowLogLen(){
		return execute((client)->client.serverOperations().slowLogLen());
	}

	@Override
	public Status slowLogReset(){
		return execute((client)->client.serverOperations().slowLogReset());
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		return execute((client)->client.serverOperations().swapdb(db1, db2));
	}

	@Override
	public RedisServerTime time(){
		return execute((client)->client.serverOperations().time());
	}

	@Override
	public Long sAdd(final String key, final String... members){
		return execute((client)->client.setOperations().sAdd(rawKey(key), members));
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members){
		return execute((client)->client.setOperations().sAdd(rawKey(key), members));
	}

	@Override
	public Long sCard(final String key){
		return execute((client)->client.setOperations().sCard(rawKey(key)));
	}

	@Override
	public Long sCard(final byte[] key){
		return execute((client)->client.setOperations().sCard(rawKey(key)));
	}

	@Override
	public Set<String> sDiff(final String... keys){
		return execute((client)->client.setOperations().sDiff(rawKeys(keys)));
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys){
		return execute((client)->client.setOperations().sDiff(rawKeys(keys)));
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys){
		return execute((client)->client.setOperations().sDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys){
		return execute((client)->client.setOperations().sDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Set<String> sInter(final String... keys){
		return execute((client)->client.setOperations().sInter(rawKeys(keys)));
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys){
		return execute((client)->client.setOperations().sInter(rawKeys(keys)));
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys){
		return execute((client)->client.setOperations().sDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys){
		return execute((client)->client.setOperations().sDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Boolean sIsMember(final String key, final String member){
		return execute((client)->client.setOperations().sIsMember(rawKey(key), member));
	}

	@Override
	public Boolean sIsMember(final byte[] key, final byte[] member){
		return execute((client)->client.setOperations().sIsMember(rawKey(key), member));
	}

	@Override
	public List<Boolean> smIsMember(final String key, final String... members){
		return execute((client)->client.setOperations().smIsMember(rawKey(key), members));
	}

	@Override
	public List<Boolean> smIsMember(final byte[] key, final byte[]... members){
		return execute((client)->client.setOperations().smIsMember(rawKey(key), members));
	}

	@Override
	public Set<String> sMembers(final String key){
		return execute((client)->client.setOperations().sMembers(rawKey(key)));
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		return execute((client)->client.setOperations().sMembers(rawKey(key)));
	}

	@Override
	public Status sMove(final String key, final String destKey, final String member){
		return execute((client)->client.setOperations().sMove(rawKey(key), rawKey(destKey), member));
	}

	@Override
	public Status sMove(final byte[] key, final byte[] destKey, final byte[] member){
		return execute((client)->client.setOperations().sMove(rawKey(key), rawKey(destKey), member));
	}

	@Override
	public String sPop(final String key){
		return execute((client)->client.setOperations().sPop(rawKey(key)));
	}

	@Override
	public byte[] sPop(final byte[] key){
		return execute((client)->client.setOperations().sPop(rawKey(key)));
	}

	@Override
	public Set<String> sPop(final String key, final long count){
		return execute((client)->client.setOperations().sPop(rawKey(key), count));
	}

	@Override
	public Set<byte[]> sPop(final byte[] key, final long count){
		return execute((client)->client.setOperations().sPop(rawKey(key), count));
	}

	@Override
	public String sRandMember(final String key){
		return execute((client)->client.setOperations().sRandMember(rawKey(key)));
	}

	@Override
	public byte[] sRandMember(final byte[] key){
		return execute((client)->client.setOperations().sRandMember(rawKey(key)));
	}

	@Override
	public List<String> sRandMember(final String key, final long count){
		return execute((client)->client.setOperations().sRandMember(rawKey(key), count));
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final long count){
		return execute((client)->client.setOperations().sRandMember(rawKey(key), count));
	}

	@Override
	public Long sRem(final String key, final String... members){
		return execute((client)->client.setOperations().sRem(rawKey(key), members));
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members){
		return execute((client)->client.setOperations().sRem(rawKey(key), members));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor){
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor){
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor){
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern){
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern){
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final long count){
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final long count){
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final long count){
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final long count){
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern, final long count){
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern, final long count){
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
										  final long count){
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										  final long count){
		return execute((client)->client.setOperations().sScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public Set<String> sUnion(final String... keys){
		return execute((client)->client.setOperations().sUnion(rawKeys(keys)));
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys){
		return execute((client)->client.setOperations().sUnion(rawKeys(keys)));
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys){
		return execute((client)->client.setOperations().sUnionStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys){
		return execute((client)->client.setOperations().sUnionStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Tuple zPopMin(final String key){
		return execute((client)->client.sortedSetOperations().zPopMin(rawKey(key)));
	}

	@Override
	public Tuple zPopMin(final byte[] key){
		return execute((client)->client.sortedSetOperations().zPopMin(rawKey(key)));
	}

	@Override
	public List<Tuple> zPopMin(final String key, long count){
		return execute((client)->client.sortedSetOperations().zPopMin(rawKey(key), count));
	}

	@Override
	public List<Tuple> zPopMin(final byte[] key, long count){
		return execute((client)->client.sortedSetOperations().zPopMin(rawKey(key), count));
	}

	@Override
	public Tuple zPopMax(final String key){
		return execute((client)->client.sortedSetOperations().zPopMax(rawKey(key)));
	}

	@Override
	public Tuple zPopMax(final byte[] key){
		return execute((client)->client.sortedSetOperations().zPopMax(rawKey(key)));
	}

	@Override
	public List<Tuple> zPopMax(final String key, final long count){
		return execute((client)->client.sortedSetOperations().zPopMax(rawKey(key), count));
	}

	@Override
	public List<Tuple> zPopMax(final byte[] key, final long count){
		return execute((client)->client.sortedSetOperations().zPopMax(rawKey(key), count));
	}

	@Override
	public KeyedZSetElement bzPopMin(final String[] keys, final int timeout){
		return execute((client)->client.sortedSetOperations().bzPopMin(rawKeys(keys), timeout));
	}

	@Override
	public KeyedZSetElement bzPopMin(final byte[][] keys, final int timeout){
		return execute((client)->client.sortedSetOperations().bzPopMin(rawKeys(keys), timeout));
	}

	@Override
	public KeyedZSetElement bzPopMax(final String[] keys, final int timeout){
		return execute((client)->client.sortedSetOperations().bzPopMax(rawKeys(keys), timeout));
	}

	@Override
	public KeyedZSetElement bzPopMax(final byte[][] keys, final int timeout){
		return execute((client)->client.sortedSetOperations().bzPopMax(rawKeys(keys), timeout));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members){
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members){
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx){
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, nxXx));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx){
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, nxXx));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt){
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, gtLt));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt){
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, gtLt));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final boolean ch){
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, ch));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final boolean ch){
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, ch));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt){
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, nxXx, gtLt));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt){
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, nxXx, gtLt));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final boolean ch){
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, nxXx, ch));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final boolean ch){
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, nxXx, ch));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt, final boolean ch){
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, gtLt, ch));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt, final boolean ch){
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, gtLt, ch));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt,
					 final boolean ch){
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, nxXx, gtLt, ch));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt,
					 final boolean ch){
		return execute((client)->client.sortedSetOperations().zAdd(rawKey(key), members, nxXx, gtLt, ch));
	}

	@Override
	public Long zCard(final String key){
		return execute((client)->client.sortedSetOperations().zCard(rawKey(key)));
	}

	@Override
	public Long zCard(final byte[] key){
		return execute((client)->client.sortedSetOperations().zCard(rawKey(key)));
	}

	@Override
	public Long zCount(final String key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zCount(rawKey(key), min, max));
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zCount(rawKey(key), min, max));
	}

	@Override
	public Long zCount(final String key, final String min, final String max){
		return execute((client)->client.sortedSetOperations().zCount(rawKey(key), min, max));
	}

	@Override
	public Long zCount(final byte[] key, final byte[] min, final byte[] max){
		return execute((client)->client.sortedSetOperations().zCount(rawKey(key), min, max));
	}

	@Override
	public Set<String> zDiff(final String... keys){
		return execute((client)->client.sortedSetOperations().zDiff(rawKeys(keys)));
	}

	@Override
	public Set<byte[]> zDiff(final byte[]... keys){
		return execute((client)->client.sortedSetOperations().zDiff(rawKeys(keys)));
	}

	@Override
	public Set<Tuple> zDiffWithScores(final String... keys){
		return execute((client)->client.sortedSetOperations().zDiffWithScores(rawKeys(keys)));
	}

	@Override
	public Set<Tuple> zDiffWithScores(final byte[]... keys){
		return execute((client)->client.sortedSetOperations().zDiffWithScores(rawKeys(keys)));
	}

	@Override
	public Long zDiffStore(final String destKey, final String... keys){
		return execute((client)->client.sortedSetOperations().zDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long zDiffStore(final byte[] destKey, final byte[]... keys){
		return execute((client)->client.sortedSetOperations().zDiffStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Double zIncrBy(final String key, final double increment, final String member){
		return execute((client)->client.sortedSetOperations().zIncrBy(rawKey(key), increment, member));
	}

	@Override
	public Double zIncrBy(final byte[] key, final double increment, final byte[] member){
		return execute((client)->client.sortedSetOperations().zIncrBy(rawKey(key), increment, member));
	}

	@Override
	public Set<String> zInter(final String... keys){
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys)));
	}

	@Override
	public Set<byte[]> zInter(final byte[]... keys){
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys)));
	}

	@Override
	public Set<String> zInter(final String[] keys, final Aggregate aggregate){
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate));
	}

	@Override
	public Set<byte[]> zInter(final byte[][] keys, final Aggregate aggregate){
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate));
	}

	@Override
	public Set<String> zInter(final String[] keys, final double... weights){
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), weights));
	}

	@Override
	public Set<byte[]> zInter(final byte[][] keys, final double... weights){
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), weights));
	}

	@Override
	public Set<String> zInter(final String[] keys, final Aggregate aggregate, final double... weights){
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate, weights));
	}

	@Override
	public Set<byte[]> zInter(final byte[][] keys, final Aggregate aggregate, final double... weights){
		return execute((client)->client.sortedSetOperations().zInter(rawKeys(keys), aggregate, weights));
	}

	@Override
	public Set<Tuple> zInterWithScores(final String... keys){
		return execute((client)->client.sortedSetOperations().zInterWithScores(rawKeys(keys)));
	}

	@Override
	public Set<Tuple> zInterWithScores(final byte[]... keys){
		return execute((client)->client.sortedSetOperations().zInterWithScores(rawKeys(keys)));
	}

	@Override
	public Set<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate){
		return execute((client)->client.sortedSetOperations().zInterWithScores(rawKeys(keys), aggregate));
	}

	@Override
	public Set<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate){
		return execute((client)->client.sortedSetOperations().zInterWithScores(rawKeys(keys), aggregate));
	}

	@Override
	public Set<Tuple> zInterWithScores(final String[] keys, final double... weights){
		return execute((client)->client.sortedSetOperations().zInterWithScores(rawKeys(keys), weights));
	}

	@Override
	public Set<Tuple> zInterWithScores(final byte[][] keys, final double... weights){
		return execute((client)->client.sortedSetOperations().zInterWithScores(rawKeys(keys), weights));
	}

	@Override
	public Set<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate, final double... weights){
		return execute((client)->client.sortedSetOperations().zInterWithScores(rawKeys(keys), aggregate, weights));
	}

	@Override
	public Set<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights){
		return execute((client)->client.sortedSetOperations().zInterWithScores(rawKeys(keys), aggregate, weights));
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys){
		return execute((client)->client.sortedSetOperations().zInterStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys){
		return execute((client)->client.sortedSetOperations().zInterStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate){
		return execute((client)->client.sortedSetOperations().zInterStore(rawKey(destKey), rawKeys(keys), aggregate));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate){
		return execute((client)->client.sortedSetOperations().zInterStore(rawKey(destKey), rawKeys(keys), aggregate));
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final double... weights){
		return execute((client)->client.sortedSetOperations().zInterStore(rawKey(destKey), rawKeys(keys), weights));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final double... weights){
		return execute((client)->client.sortedSetOperations().zInterStore(rawKey(destKey), rawKeys(keys), weights));
	}

	@Override
	public Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights){
		return execute(
				(client)->client.sortedSetOperations().zInterStore(rawKey(destKey), rawKeys(keys), aggregate, weights));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights){
		return execute(
				(client)->client.sortedSetOperations().zInterStore(rawKey(destKey), rawKeys(keys), aggregate, weights));
	}

	@Override
	public Long zLexCount(final String key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zLexCount(rawKey(key), min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zLexCount(rawKey(key), min, max));
	}

	@Override
	public Long zLexCount(final String key, final String min, final String max){
		return execute((client)->client.sortedSetOperations().zLexCount(rawKey(key), min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
		return execute((client)->client.sortedSetOperations().zLexCount(rawKey(key), min, max));
	}

	@Override
	public List<Double> zMScore(final String key, final String... members){
		return execute((client)->client.sortedSetOperations().zMScore(rawKey(key), members));
	}

	@Override
	public List<Double> zMScore(final byte[] key, final byte[]... members){
		return execute((client)->client.sortedSetOperations().zMScore(rawKey(key), members));
	}

	@Override
	public String zRandMember(final String key){
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key)));
	}

	@Override
	public byte[] zRandMember(final byte[] key){
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key)));
	}

	@Override
	public List<String> zRandMember(final String key, final long count){
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key), count));
	}

	@Override
	public List<byte[]> zRandMember(final byte[] key, final long count){
		return execute((client)->client.sortedSetOperations().zRandMember(rawKey(key), count));
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final String key, final long count){
		return execute((client)->client.sortedSetOperations().zRandMemberWithScores(rawKey(key), count));
	}

	@Override
	public List<Tuple> zRandMemberWithScores(final byte[] key, final long count){
		return execute((client)->client.sortedSetOperations().zRandMemberWithScores(rawKey(key), count));
	}

	@Override
	public List<String> zRange(final String key, final long start, final long end){
		return execute((client)->client.sortedSetOperations().zRange(rawKey(key), start, end));
	}

	@Override
	public List<byte[]> zRange(final byte[] key, final long start, final long end){
		return execute((client)->client.sortedSetOperations().zRange(rawKey(key), start, end));
	}

	@Override
	public List<Tuple> zRangeWithScores(final String key, final long start, final long end){
		return execute((client)->client.sortedSetOperations().zRangeWithScores(rawKey(key), start, end));
	}

	@Override
	public List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
		return execute((client)->client.sortedSetOperations().zRangeWithScores(rawKey(key), start, end));
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<String> zRangeByLex(final String key, final String min, final String max){
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<String> zRangeByLex(final String key, final double min, final double max, final long offset,
									final long count){
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final long offset,
									final long count){
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<String> zRangeByLex(final String key, final String min, final String max, final long offset,
									final long count){
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									final long count){
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zRangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<String> zRangeByScore(final String key, final String min, final String max){
		return execute((client)->client.sortedSetOperations().zRangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute((client)->client.sortedSetOperations().zRangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<String> zRangeByScore(final String key, final double min, final double max, final long offset,
									  final long count){
		return execute((client)->client.sortedSetOperations().zRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final long offset,
									  final long count){
		return execute((client)->client.sortedSetOperations().zRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<String> zRangeByScore(final String key, String min, String max, long offset,
									  long count){
		return execute((client)->client.sortedSetOperations().zRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final long offset,
									  final long count){
		return execute((client)->client.sortedSetOperations().zRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max){
		return execute((client)->client.sortedSetOperations().zRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		return execute((client)->client.sortedSetOperations().zRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final long offset,
											   final long count){
		return execute(
				(client)->client.sortedSetOperations().zRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final long offset,
											   final long count){
		return execute(
				(client)->client.sortedSetOperations().zRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final long offset,
											   final long count){
		return execute(
				(client)->client.sortedSetOperations().zRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final long offset,
											   final long count){
		return execute(
				(client)->client.sortedSetOperations().zRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end){
		return execute((client)->client.sortedSetOperations().zRangeStore(rawKey(destKey), rawKey(key), start, end));
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end){
		return execute((client)->client.sortedSetOperations().zRangeStore(rawKey(destKey), rawKey(key), start, end));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final ZRangeBy by){
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(rawKey(destKey), rawKey(key), start, end, by));
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final ZRangeBy by){
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(rawKey(destKey), rawKey(key), start, end, by));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end,
							final boolean rev){
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(rawKey(destKey), rawKey(key), start, end, rev));
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							final boolean rev){
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(rawKey(destKey), rawKey(key), start, end, rev));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final long offset,
							final long count){
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(rawKey(destKey), rawKey(key), start, end, offset, count));
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final long offset,
							final long count){
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(rawKey(destKey), rawKey(key), start, end, offset, count));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by,
							final boolean rev){
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(rawKey(destKey), rawKey(key), start, end, by, rev));
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by,
							final boolean rev){
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(rawKey(destKey), rawKey(key), start, end, by, rev));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by,
							final long offset, final long count){
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(rawKey(destKey), rawKey(key), start, end, by, offset, count));
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by,
							final long offset, final long count){
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(rawKey(destKey), rawKey(key), start, end, by, offset, count));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final boolean rev,
							final long offset, final long count){
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(rawKey(destKey), rawKey(key), start, end, rev, offset, count));
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final boolean rev,
							final long offset, final long count){
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(rawKey(destKey), rawKey(key), start, end, rev, offset, count));
	}

	@Override
	public Long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by,
							final boolean rev, final long offset, final long count){
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(rawKey(destKey), rawKey(key), start, end, by, rev, offset, count));
	}

	@Override
	public Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by,
							final boolean rev, final long offset, final long count){
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(rawKey(destKey), rawKey(key), start, end, by, rev, offset, count));
	}

	@Override
	public Long zRank(final String key, final String member){
		return execute((client)->client.sortedSetOperations().zRank(rawKey(key), member));
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member){
		return execute((client)->client.sortedSetOperations().zRank(rawKey(key), member));
	}

	@Override
	public Long zRem(final String key, final String... members){
		return execute((client)->client.sortedSetOperations().zRem(rawKey(key), members));
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members){
		return execute((client)->client.sortedSetOperations().zRem(rawKey(key), members));
	}

	@Override
	public Long zRemRangeByLex(final String key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zRemRangeByLex(rawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zRemRangeByLex(rawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final String min, final String max){
		return execute((client)->client.sortedSetOperations().zRemRangeByLex(rawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute((client)->client.sortedSetOperations().zRemRangeByLex(rawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zRemRangeByScore(rawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zRemRangeByScore(rawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final String key, final String min, final String max){
		return execute((client)->client.sortedSetOperations().zRemRangeByScore(rawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute((client)->client.sortedSetOperations().zRemRangeByScore(rawKey(key), min, max));
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end){
		return execute((client)->client.sortedSetOperations().zRemRangeByRank(rawKey(key), start, end));
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end){
		return execute((client)->client.sortedSetOperations().zRemRangeByRank(rawKey(key), start, end));
	}

	@Override
	public List<String> zRevRange(final String key, final long start, final long end){
		return execute((client)->client.sortedSetOperations().zRevRange(rawKey(key), start, end));
	}

	@Override
	public List<byte[]> zRevRange(final byte[] key, final long start, final long end){
		return execute((client)->client.sortedSetOperations().zRevRange(rawKey(key), start, end));
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final String key, final long start, final long end){
		return execute((client)->client.sortedSetOperations().zRevRangeWithScores(rawKey(key), start, end));
	}

	@Override
	public List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
		return execute((client)->client.sortedSetOperations().zRevRangeWithScores(rawKey(key), start, end));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final String min, final String max){
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(rawKey(key), min, max));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final double min, final double max, final long offset,
									   final long count){
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final long offset,
									   final long count){
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<String> zRevRangeByLex(final String key, final String min, final String max, final long offset,
									   final long count){
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
									   final long count){
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final String min, final String max){
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(rawKey(key), min, max));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final double min, final double max, final long offset,
										 final long count){
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final long offset,
										 final long count){
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<String> zRevRangeByScore(final String key, final String min, final String max, final long offset,
										 final long count){
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final long offset,
										 final long count){
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zRevRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
		return execute((client)->client.sortedSetOperations().zRevRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max){
		return execute((client)->client.sortedSetOperations().zRevRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		return execute((client)->client.sortedSetOperations().zRevRangeByScoreWithScores(rawKey(key), min, max));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
												  final long offset, final long count){
		return execute((client)->client.sortedSetOperations()
				.zRevRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
												  final long offset, final long count){
		return execute((client)->client.sortedSetOperations()
				.zRevRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max,
												  final long offset, final long count){
		return execute((client)->client.sortedSetOperations()
				.zRevRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
												  final long offset, final long count){
		return execute((client)->client.sortedSetOperations()
				.zRevRangeByScoreWithScores(rawKey(key), min, max, offset, count));
	}

	@Override
	public Long zRevRank(final String key, final String member){
		return execute((client)->client.sortedSetOperations().zRevRank(rawKey(key), member));
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member){
		return execute((client)->client.sortedSetOperations().zRevRank(rawKey(key), member));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor){
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor){
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor){
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern){
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern){
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern){
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final long count){
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final long count){
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final long count){
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final long count){
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final long count){
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern, final long count){
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final long count){
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final long count){
		return execute((client)->client.sortedSetOperations().zScan(rawKey(key), cursor, pattern, count));
	}

	@Override
	public Double zScore(final String key, final String member){
		return execute((client)->client.sortedSetOperations().zScore(rawKey(key), member));
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member){
		return execute((client)->client.sortedSetOperations().zScore(rawKey(key), member));
	}

	@Override
	public Set<String> zUnion(final String... keys){
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys)));
	}

	@Override
	public Set<byte[]> zUnion(final byte[]... keys){
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys)));
	}

	@Override
	public Set<String> zUnion(final String[] keys, final Aggregate aggregate){
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate));
	}

	@Override
	public Set<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate){
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate));
	}

	@Override
	public Set<String> zUnion(final String[] keys, final double... weights){
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), weights));
	}

	@Override
	public Set<byte[]> zUnion(final byte[][] keys, final double... weights){
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), weights));
	}

	@Override
	public Set<String> zUnion(final String[] keys, final Aggregate aggregate, final double... weights){
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate, weights));
	}

	@Override
	public Set<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate, final double... weights){
		return execute((client)->client.sortedSetOperations().zUnion(rawKeys(keys), aggregate, weights));
	}

	@Override
	public Set<Tuple> zUnionWithScores(final String... keys){
		return execute((client)->client.sortedSetOperations().zUnionWithScores(rawKeys(keys)));
	}

	@Override
	public Set<Tuple> zUnionWithScores(final byte[]... keys){
		return execute((client)->client.sortedSetOperations().zUnionWithScores(rawKeys(keys)));
	}

	@Override
	public Set<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate){
		return execute((client)->client.sortedSetOperations().zUnionWithScores(rawKeys(keys), aggregate));
	}

	@Override
	public Set<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate){
		return execute((client)->client.sortedSetOperations().zUnionWithScores(rawKeys(keys), aggregate));
	}

	@Override
	public Set<Tuple> zUnionWithScores(final String[] keys, final double... weights){
		return execute((client)->client.sortedSetOperations().zUnionWithScores(rawKeys(keys), weights));
	}

	@Override
	public Set<Tuple> zUnionWithScores(final byte[][] keys, final double... weights){
		return execute((client)->client.sortedSetOperations().zUnionWithScores(rawKeys(keys), weights));
	}

	@Override
	public Set<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate, final double... weights){
		return execute((client)->client.sortedSetOperations().zUnionWithScores(rawKeys(keys), aggregate, weights));
	}

	@Override
	public Set<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights){
		return execute((client)->client.sortedSetOperations().zUnionWithScores(rawKeys(keys), aggregate, weights));
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys){
		return execute((client)->client.sortedSetOperations().zUnionStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys){
		return execute((client)->client.sortedSetOperations().zUnionStore(rawKey(destKey), rawKeys(keys)));
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate){
		return execute((client)->client.sortedSetOperations().zUnionStore(rawKey(destKey), rawKeys(keys), aggregate));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate){
		return execute((client)->client.sortedSetOperations().zUnionStore(rawKey(destKey), rawKeys(keys), aggregate));
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final double... weights){
		return execute((client)->client.sortedSetOperations().zUnionStore(rawKey(destKey), rawKeys(keys), weights));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final double... weights){
		return execute((client)->client.sortedSetOperations().zUnionStore(rawKey(destKey), rawKeys(keys), weights));
	}

	@Override
	public Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate,
							final double... weights){
		return execute(
				(client)->client.sortedSetOperations().zUnionStore(rawKey(destKey), rawKeys(keys), aggregate, weights));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							final double... weights){
		return execute(
				(client)->client.sortedSetOperations().zUnionStore(rawKey(destKey), rawKeys(keys), aggregate, weights));
	}

	@Override
	public Long xAck(final String key, final String groupName, final StreamEntryId... ids){
		return execute((client)->client.streamOperations().xAck(rawKey(key), groupName, ids));
	}

	@Override
	public Long xAck(final byte[] key, final byte[] groupName, final StreamEntryId... ids){
		return execute((client)->client.streamOperations().xAck(rawKey(key), groupName, ids));
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash){
		return execute((client)->client.streamOperations().xAdd(rawKey(key), id, hash));
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, Map<byte[], byte[]> hash){
		return execute((client)->client.streamOperations().xAdd(rawKey(key), id, hash));
	}

	@Override
	public StreamEntryId xAdd(final String key, final StreamEntryId id, final Map<String, String> hash,
							  final XAddArgument xAddArgument){
		return execute((client)->client.streamOperations().xAdd(rawKey(key), id, hash, xAddArgument));
	}

	@Override
	public StreamEntryId xAdd(final byte[] key, final StreamEntryId id, final Map<byte[], byte[]> hash,
							  final XAddArgument xAddArgument){
		return execute((client)->client.streamOperations().xAdd(rawKey(key), id, hash, xAddArgument));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															final String consumerName, final int minIdleTime,
															final StreamEntryId start){
		return execute((client)->client.streamOperations()
				.xAutoClaim(rawKey(key), groupName, consumerName, minIdleTime, start));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] groupName,
															final byte[] consumerName, final int minIdleTime,
															final StreamEntryId start){
		return execute((client)->client.streamOperations()
				.xAutoClaim(rawKey(key), groupName, consumerName, minIdleTime, start));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final String key, final String groupName,
															final String consumerName, final int minIdleTime,
															final StreamEntryId start, final long count){
		return execute((client)->client.streamOperations()
				.xAutoClaim(rawKey(key), groupName, consumerName, minIdleTime, start, count));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntry>> xAutoClaim(final byte[] key, final byte[] groupName,
															final byte[] consumerName, final int minIdleTime,
															final StreamEntryId start, final long count){
		return execute((client)->client.streamOperations()
				.xAutoClaim(rawKey(key), groupName, consumerName, minIdleTime, start, count));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	final String consumerName, final int minIdleTime,
																	final StreamEntryId start){
		return execute((client)->client.streamOperations()
				.xAutoClaimJustId(rawKey(key), groupName, consumerName, minIdleTime, start));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																	final byte[] consumerName, final int minIdleTime,
																	final StreamEntryId start){
		return execute((client)->client.streamOperations()
				.xAutoClaimJustId(rawKey(key), groupName, consumerName, minIdleTime, start));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final String key, final String groupName,
																	final String consumerName, final int minIdleTime,
																	final StreamEntryId start, final long count){
		return execute((client)->client.streamOperations()
				.xAutoClaimJustId(rawKey(key), groupName, consumerName, minIdleTime, start, count));
	}

	@Override
	public Map<StreamEntryId, List<StreamEntryId>> xAutoClaimJustId(final byte[] key, final byte[] groupName,
																	final byte[] consumerName, final int minIdleTime,
																	final StreamEntryId start, final long count){
		return execute((client)->client.streamOperations()
				.xAutoClaimJustId(rawKey(key), groupName, consumerName, minIdleTime, start, count));
	}

	@Override
	public List<StreamEntry> xClaim(final String key, final String groupName, final String consumerName,
									final int minIdleTime, final StreamEntryId... ids){
		return execute(
				(client)->client.streamOperations().xClaim(rawKey(key), groupName, consumerName, minIdleTime, ids));
	}

	@Override
	public List<StreamEntry> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
									final int minIdleTime, final StreamEntryId... ids){
		return execute(
				(client)->client.streamOperations().xClaim(rawKey(key), groupName, consumerName, minIdleTime, ids));
	}

	@Override
	public List<StreamEntry> xClaim(final String key, final String groupName, final String consumerName,
									final int minIdleTime, final StreamEntryId[] ids,
									final XClaimArgument xClaimArgument){
		return execute((client)->client.streamOperations()
				.xClaim(rawKey(key), groupName, consumerName, minIdleTime, ids, xClaimArgument));
	}

	@Override
	public List<StreamEntry> xClaim(final byte[] key, final byte[] groupName, final byte[] consumerName,
									final int minIdleTime, final StreamEntryId[] ids,
									final XClaimArgument xClaimArgument){
		return execute((client)->client.streamOperations()
				.xClaim(rawKey(key), groupName, consumerName, minIdleTime, ids, xClaimArgument));
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											final int minIdleTime, final StreamEntryId... ids){
		return execute((client)->client.streamOperations()
				.xClaimJustId(rawKey(key), groupName, consumerName, minIdleTime, ids));
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId... ids){
		return execute((client)->client.streamOperations()
				.xClaimJustId(rawKey(key), groupName, consumerName, minIdleTime, ids));
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final String key, final String groupName, final String consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument){
		return execute((client)->client.streamOperations()
				.xClaimJustId(rawKey(key), groupName, consumerName, minIdleTime, ids, xClaimArgument));
	}

	@Override
	public List<StreamEntryId> xClaimJustId(final byte[] key, final byte[] groupName, final byte[] consumerName,
											final int minIdleTime, final StreamEntryId[] ids,
											final XClaimArgument xClaimArgument){
		return execute((client)->client.streamOperations()
				.xClaimJustId(rawKey(key), groupName, consumerName, minIdleTime, ids, xClaimArgument));
	}

	@Override
	public Long xDel(final String key, final StreamEntryId... ids){
		return execute((client)->client.streamOperations().xDel(rawKey(key), ids));
	}

	@Override
	public Long xDel(final byte[] key, final StreamEntryId... ids){
		return execute((client)->client.streamOperations().xDel(rawKey(key), ids));
	}

	@Override
	public Status xGroupCreate(final String key, final String groupName, final StreamEntryId id,
							   final boolean makeStream){
		return execute((client)->client.streamOperations().xGroupCreate(rawKey(key), groupName, id, makeStream));
	}

	@Override
	public Status xGroupCreate(final byte[] key, final byte[] groupName, final StreamEntryId id,
							   final boolean makeStream){
		return execute((client)->client.streamOperations().xGroupCreate(rawKey(key), groupName, id, makeStream));
	}

	@Override
	public Status xGroupCreateConsumer(final String key, final String groupName, final String consumerName){
		return execute((client)->client.streamOperations().xGroupCreateConsumer(rawKey(key), groupName, consumerName));
	}

	@Override
	public Status xGroupCreateConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName){
		return execute((client)->client.streamOperations().xGroupCreateConsumer(rawKey(key), groupName, consumerName));
	}

	@Override
	public Long xGroupDelConsumer(final String key, final String groupName, final String consumerName){
		return execute((client)->client.streamOperations().xGroupDelConsumer(rawKey(key), groupName, consumerName));
	}

	@Override
	public Long xGroupDelConsumer(final byte[] key, final byte[] groupName, final byte[] consumerName){
		return execute((client)->client.streamOperations().xGroupDelConsumer(rawKey(key), groupName, consumerName));
	}

	@Override
	public Status xGroupDestroy(final String key, final String groupName){
		return execute((client)->client.streamOperations().xGroupDestroy(rawKey(key), groupName));
	}

	@Override
	public Status xGroupDestroy(final byte[] key, final byte[] groupName){
		return execute((client)->client.streamOperations().xGroupDestroy(rawKey(key), groupName));
	}

	@Override
	public Status xGroupSetId(final String key, final String groupName, final StreamEntryId id){
		return execute((client)->client.streamOperations().xGroupSetId(rawKey(key), groupName, id));
	}

	@Override
	public Status xGroupSetId(final byte[] key, final byte[] groupName, final StreamEntryId id){
		return execute((client)->client.streamOperations().xGroupSetId(rawKey(key), groupName, id));
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final String key, final String groupName){
		return execute((client)->client.streamOperations().xInfoConsumers(rawKey(key), groupName));
	}

	@Override
	public List<StreamConsumer> xInfoConsumers(final byte[] key, final byte[] groupName){
		return execute((client)->client.streamOperations().xInfoConsumers(rawKey(key), groupName));
	}

	@Override
	public List<StreamGroup> xInfoGroups(final String key){
		return execute((client)->client.streamOperations().xInfoGroups(rawKey(key)));
	}

	@Override
	public List<StreamGroup> xInfoGroups(final byte[] key){
		return execute((client)->client.streamOperations().xInfoGroups(rawKey(key)));
	}

	@Override
	public Stream xInfoStream(final String key){
		return execute((client)->client.streamOperations().xInfoStream(rawKey(key)));
	}

	@Override
	public Stream xInfoStream(final byte[] key){
		return execute((client)->client.streamOperations().xInfoStream(rawKey(key)));
	}

	@Override
	public StreamFull xInfoStream(final String key, final boolean full){
		return execute((client)->client.streamOperations().xInfoStream(rawKey(key), full));
	}

	@Override
	public StreamFull xInfoStream(final byte[] key, final boolean full){
		return execute((client)->client.streamOperations().xInfoStream(rawKey(key), full));
	}

	@Override
	public StreamFull xInfoStream(final String key, final boolean full, final long count){
		return execute((client)->client.streamOperations().xInfoStream(rawKey(key), full, count));
	}

	@Override
	public StreamFull xInfoStream(final byte[] key, final boolean full, final long count){
		return execute((client)->client.streamOperations().xInfoStream(rawKey(key), full, count));
	}

	@Override
	public Long xLen(final String key){
		return execute((client)->client.streamOperations().xLen(rawKey(key)));
	}

	@Override
	public Long xLen(final byte[] key){
		return execute((client)->client.streamOperations().xLen(rawKey(key)));
	}

	@Override
	public StreamPendingSummary xPending(final String key, final String groupName){
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName));
	}

	@Override
	public StreamPendingSummary xPending(final byte[] key, final byte[] groupName){
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName));
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime){
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName, minIdleTime));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime){
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName, minIdleTime));
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										final StreamEntryId end, final long count){
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName, start, end, count));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final long count){
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName, start, end, count));
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final String consumerName){
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName, consumerName));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final byte[] consumerName){
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName, consumerName));
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count){
		return execute(
				(client)->client.streamOperations().xPending(rawKey(key), groupName, minIdleTime, start, end, count));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count){
		return execute(
				(client)->client.streamOperations().xPending(rawKey(key), groupName, minIdleTime, start, end, count));
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final String consumerName){
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName, minIdleTime, consumerName));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final byte[] consumerName){
		return execute((client)->client.streamOperations().xPending(rawKey(key), groupName, minIdleTime, consumerName));
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final StreamEntryId start,
										final StreamEntryId end, final long count, final String consumerName){
		return execute(
				(client)->client.streamOperations().xPending(rawKey(key), groupName, start, end, count, consumerName));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final StreamEntryId start,
										final StreamEntryId end, final long count, final byte[] consumerName){
		return execute(
				(client)->client.streamOperations().xPending(rawKey(key), groupName, start, end, count, consumerName));
	}

	@Override
	public List<StreamPending> xPending(final String key, final String groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count,
										final String consumerName){
		return execute((client)->client.streamOperations()
				.xPending(rawKey(key), groupName, minIdleTime, start, end, count, consumerName));
	}

	@Override
	public List<StreamPending> xPending(final byte[] key, final byte[] groupName, final long minIdleTime,
										final StreamEntryId start, final StreamEntryId end, final long count,
										final byte[] consumerName){
		return execute((client)->client.streamOperations()
				.xPending(rawKey(key), groupName, minIdleTime, start, end, count, consumerName));
	}

	@Override
	public List<StreamEntry> xRange(final String key, final StreamEntryId start, final StreamEntryId end){
		return execute((client)->client.streamOperations().xRange(rawKey(key), start, end));
	}

	@Override
	public List<StreamEntry> xRange(final byte[] key, final StreamEntryId start, final StreamEntryId end){
		return execute((client)->client.streamOperations().xRange(rawKey(key), start, end));
	}

	@Override
	public List<StreamEntry> xRange(final String key, final StreamEntryId start, final StreamEntryId end,
									final long count){
		return execute((client)->client.streamOperations().xRange(rawKey(key), start, end));
	}

	@Override
	public List<StreamEntry> xRange(final byte[] key, final StreamEntryId start, final StreamEntryId end,
									final long count){
		return execute((client)->client.streamOperations().xRange(rawKey(key), start, end));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final Map<String, StreamEntryId> streams){
		return execute((client)->client.streamOperations().xRead(streams));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final long count, final Map<String, StreamEntryId> streams){
		return execute((client)->client.streamOperations().xRead(count, streams));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final int block, final Map<String, StreamEntryId> streams){
		return execute((client)->client.streamOperations().xRead(block, streams));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xRead(final long count, final int block,
													  final Map<String, StreamEntryId> streams){
		return execute((client)->client.streamOperations().xRead(count, block, streams));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final Map<String, StreamEntryId> streams){
		return execute((client)->client.streamOperations().xReadGroup(groupName, consumerName, streams));
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final Map<byte[], StreamEntryId> streams){
		return execute((client)->client.streamOperations().xReadGroup(groupName, consumerName, streams));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final Map<String, StreamEntryId> streams){
		return execute((client)->client.streamOperations().xReadGroup(groupName, consumerName, count, streams));
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final Map<byte[], StreamEntryId> streams){
		return execute((client)->client.streamOperations().xReadGroup(groupName, consumerName, count, streams));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final int block, final Map<String, StreamEntryId> streams){
		return execute((client)->client.streamOperations().xReadGroup(groupName, consumerName, block, streams));
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final int block, final Map<byte[], StreamEntryId> streams){
		return execute((client)->client.streamOperations().xReadGroup(groupName, consumerName, block, streams));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final boolean isNoAck,
														   final Map<String, StreamEntryId> streams){
		return execute((client)->client.streamOperations().xReadGroup(groupName, consumerName, isNoAck, streams));
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams){
		return execute((client)->client.streamOperations().xReadGroup(groupName, consumerName, isNoAck, streams));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final int block,
														   final Map<String, StreamEntryId> streams){
		return execute((client)->client.streamOperations().xReadGroup(groupName, consumerName, count, block, streams));
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final int block,
														   final Map<byte[], StreamEntryId> streams){
		return execute((client)->client.streamOperations().xReadGroup(groupName, consumerName, count, block, streams));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams){
		return execute(
				(client)->client.streamOperations().xReadGroup(groupName, consumerName, count, isNoAck, streams));
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams){
		return execute(
				(client)->client.streamOperations().xReadGroup(groupName, consumerName, count, isNoAck, streams));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final int block, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams){
		return execute(
				(client)->client.streamOperations().xReadGroup(groupName, consumerName, block, isNoAck, streams));
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final int block, final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams){
		return execute(
				(client)->client.streamOperations().xReadGroup(groupName, consumerName, block, isNoAck, streams));
	}

	@Override
	public List<Map<String, List<StreamEntry>>> xReadGroup(final String groupName, final String consumerName,
														   final long count, final int block, final boolean isNoAck,
														   final Map<String, StreamEntryId> streams){
		return execute((client)->client.streamOperations()
				.xReadGroup(groupName, consumerName, count, block, isNoAck, streams));
	}

	@Override
	public List<Map<byte[], List<StreamEntry>>> xReadGroup(final byte[] groupName, final byte[] consumerName,
														   final long count, final int block, final boolean isNoAck,
														   final Map<byte[], StreamEntryId> streams){
		return execute((client)->client.streamOperations()
				.xReadGroup(groupName, consumerName, count, block, isNoAck, streams));
	}

	@Override
	public List<StreamEntry> xRevRange(final String key, final StreamEntryId end, final StreamEntryId start){
		return execute((client)->client.streamOperations().xRevRange(rawKey(key), end, start));
	}

	@Override
	public List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start){
		return execute((client)->client.streamOperations().xRevRange(rawKey(key), end, start));
	}

	@Override
	public List<StreamEntry> xRevRange(final String key, final StreamEntryId end, final StreamEntryId start,
									   final long count){
		return execute((client)->client.streamOperations().xRevRange(rawKey(key), end, start, count));
	}

	@Override
	public List<StreamEntry> xRevRange(final byte[] key, final StreamEntryId end, final StreamEntryId start,
									   final long count){
		return execute((client)->client.streamOperations().xRevRange(rawKey(key), end, start, count));
	}

	@Override
	public Long xTrim(final String key, final XTrimArgument xTrimArgument){
		return execute((client)->client.streamOperations().xTrim(rawKey(key), xTrimArgument));
	}

	@Override
	public Long xTrim(final byte[] key, final XTrimArgument xTrimArgument){
		return execute((client)->client.streamOperations().xTrim(rawKey(key), xTrimArgument));
	}

	@Override
	public Long xTrim(final String key, final XTrimArgument xTrimArgument, final long limit){
		return execute((client)->client.streamOperations().xTrim(rawKey(key), xTrimArgument, limit));
	}

	@Override
	public Long xTrim(final byte[] key, final XTrimArgument xTrimArgument, final long limit){
		return execute((client)->client.streamOperations().xTrim(rawKey(key), xTrimArgument, limit));
	}

	@Override
	public Long append(final String key, final String value){
		return execute((client)->client.stringOperations().append(rawKey(key), value));
	}

	@Override
	public Long append(final byte[] key, final byte[] value){
		return execute((client)->client.stringOperations().append(rawKey(key), value));
	}

	@Override
	public Long incr(final String key){
		return execute((client)->client.stringOperations().incr(rawKey(key)));
	}

	@Override
	public Long incr(final byte[] key){
		return execute((client)->client.stringOperations().incr(rawKey(key)));
	}

	@Override
	public Long incrBy(final String key, final long value){
		return execute((client)->client.stringOperations().incrBy(rawKey(key), value));
	}

	@Override
	public Long incrBy(final byte[] key, final long value){
		return execute((client)->client.stringOperations().incrBy(rawKey(key), value));
	}

	@Override
	public Double incrByFloat(final String key, final double value){
		return execute((client)->client.stringOperations().incrByFloat(rawKey(key), value));
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value){
		return execute((client)->client.stringOperations().incrByFloat(rawKey(key), value));
	}

	@Override
	public Long decr(final String key){
		return execute((client)->client.stringOperations().decr(rawKey(key)));
	}

	@Override
	public Long decr(final byte[] key){
		return execute((client)->client.stringOperations().decr(rawKey(key)));
	}

	@Override
	public Long decrBy(final String key, final long value){
		return execute((client)->client.stringOperations().decrBy(rawKey(key), value));
	}

	@Override
	public Long decrBy(final byte[] key, final long value){
		return execute((client)->client.stringOperations().decrBy(rawKey(key), value));
	}

	@Override
	public String get(final String key){
		return execute((client)->client.stringOperations().get(rawKey(key)));
	}

	@Override
	public byte[] get(final byte[] key){
		return execute((client)->client.stringOperations().get(rawKey(key)));
	}

	@Override
	public String getEx(final String key, final GetExArgument getExArgument){
		return execute((client)->client.stringOperations().getEx(rawKey(key), getExArgument));
	}

	@Override
	public byte[] getEx(final byte[] key, final GetExArgument getExArgument){
		return execute((client)->client.stringOperations().getEx(rawKey(key), getExArgument));
	}

	@Override
	public String getSet(final String key, final String value){
		return execute((client)->client.stringOperations().getSet(rawKey(key), value));
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		return execute((client)->client.stringOperations().getSet(rawKey(key), value));
	}

	@Override
	public String getDel(final String key){
		return execute((client)->client.stringOperations().getDel(rawKey(key)));
	}

	@Override
	public byte[] getDel(final byte[] key){
		return execute((client)->client.stringOperations().getDel(rawKey(key)));
	}

	@Override
	public List<String> mGet(final String... keys){
		return execute((client)->client.stringOperations().mGet(rawKeys(keys)));
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys){
		return execute((client)->client.stringOperations().mGet(rawKeys(keys)));
	}

	@Override
	public Status mSet(final Map<String, String> values){
		final Map<String, String> newValues = Maps.map(values, this::rawKey, (value)->value,
				new LinkedHashMap<>(values.size()));
		return execute((client)->client.stringOperations().mSet(newValues));
	}

	@Override
	public Status mSetNx(final Map<String, String> values){
		final Map<String, String> newValues = Maps.map(values, this::rawKey, (value)->value,
				new LinkedHashMap<>(values.size()));
		return execute((client)->client.stringOperations().mSetNx(newValues));
	}

	@Override
	public Status pSetEx(final String key, String value, int lifetime){
		return execute((client)->client.stringOperations().pSetEx(rawKey(key), value, lifetime));
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		return execute((client)->client.stringOperations().pSetEx(rawKey(key), value, lifetime));
	}

	@Override
	public Status set(final String key, final String value){
		return execute((client)->client.stringOperations().set(rawKey(key), value));
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		return execute((client)->client.stringOperations().set(rawKey(key), value));
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument){
		return execute((client)->client.stringOperations().set(rawKey(key), value, setArgument));
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument){
		return execute((client)->client.stringOperations().set(rawKey(key), value, setArgument));
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime){
		return execute((client)->client.stringOperations().setEx(rawKey(key), value, lifetime));
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		return execute((client)->client.stringOperations().setEx(rawKey(key), value, lifetime));
	}

	@Override
	public Status setNx(final String key, final String value){
		return execute((client)->client.stringOperations().setNx(rawKey(key), value));
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		return execute((client)->client.stringOperations().setNx(rawKey(key), value));
	}

	@Override
	public Long setRange(final String key, final long offset, final String value){
		return execute((client)->client.stringOperations().setRange(rawKey(key), offset, value));
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value){
		return execute((client)->client.stringOperations().setRange(rawKey(key), offset, value));
	}

	@Override
	public String getRange(final String key, final long start, final long end){
		return execute((client)->client.stringOperations().getRange(rawKey(key), start, end));
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		return execute((client)->client.stringOperations().getRange(rawKey(key), start, end));
	}

	@Override
	public Long strlen(final String key){
		return execute((client)->client.stringOperations().strlen(rawKey(key)));
	}

	@Override
	public Long strlen(final byte[] key){
		return execute((client)->client.stringOperations().strlen(rawKey(key)));
	}

	@Override
	public String substr(final String key, final long start, final long end){
		return execute((client)->client.stringOperations().substr(rawKey(key), start, end));
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end){
		return execute((client)->client.stringOperations().substr(rawKey(key), start, end));
	}

	@Override
	public Status multi(){
		return execute((client)->client.transactionOperations().multi());
	}

	@Override
	public List<Object> exec(){
		/*
		RedisConnection connection = client.getConnection();

		if(connection.isPipeline()){
			final Pipeline pipeline = connection.openPipeline();
			try{
				return pipeline.syncAndReturnAll();
			}finally{
				connection.closePipeline();
			}
		}else{
			return execute((client)->client.getConnection().exec());
		}

		 */
		return null;
	}

	@Override
	public void discard(){
		execute((client)->{
			client.getConnection().discard();
			return null;
		});
	}

	@Override
	public Status watch(final String... keys){
		return execute((client)->client.transactionOperations().watch(rawKeys(keys)));
	}

	@Override
	public Status watch(final byte[]... keys){
		return execute((client)->client.transactionOperations().watch(rawKeys(keys)));
	}

	@Override
	public Status unwatch(){
		return execute((client)->client.transactionOperations().unwatch());
	}

}
