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
package com.buession.redis.client;

import com.buession.lang.Status;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.Info;
import com.buession.redis.core.InfoSection;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.SlowLogCommand;
import com.buession.redis.core.SortArgument;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.transaction.Transaction;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public interface ShardedRedisClient extends RedisClient {

	@Override
	default Status auth(final String password){
		throw new NotSupportedCommandException(ProtocolCommand.AUTH);
	}

	@Override
	default Status auth(final byte[] password){
		throw new NotSupportedCommandException(ProtocolCommand.AUTH);
	}

	@Override
	default Status ping(){
		throw new NotSupportedCommandException(ProtocolCommand.PING);
	}

	@Override
	default Status quit(){
		throw new NotSupportedCommandException(ProtocolCommand.QUIT);
	}

	@Override
	default Status select(final int db){
		throw new NotSupportedCommandException(ProtocolCommand.SELECT);
	}

	@Override
	default Status swapdb(final int db1, final int db2){
		throw new NotSupportedCommandException(ProtocolCommand.SWAPDB);
	}

	@Override
	default Status pfMerge(final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.PFMERGE);
	}

	@Override
	default Status pfMerge(final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.PFMERGE);
	}

	@Override
	default Set<String> keys(final String pattern){
		throw new NotSupportedCommandException(ProtocolCommand.KEYS);
	}

	@Override
	default Set<byte[]> keys(final byte[] pattern){
		throw new NotSupportedCommandException(ProtocolCommand.KEYS);
	}

	@Override
	default String randomKey(){
		throw new NotSupportedCommandException(ProtocolCommand.RANDOMKEY);
	}

	@Override
	default Status rename(final String key, final String newKey){
		throw new NotSupportedCommandException(ProtocolCommand.RENAME);
	}

	@Override
	default Status rename(final byte[] key, final byte[] newKey){
		throw new NotSupportedCommandException(ProtocolCommand.RENAME);
	}

	@Override
	default Status renameNx(final String key, final String newKey){
		throw new NotSupportedCommandException(ProtocolCommand.RENAMENX);
	}

	@Override
	default Status renameNx(final byte[] key, final byte[] newKey){
		throw new NotSupportedCommandException(ProtocolCommand.RENAMENX);
	}

	@Override
	default ScanResult<List<String>> scan(final int cursor){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final long cursor){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final String cursor){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final byte[] cursor){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final int cursor, final String pattern){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final int cursor, final byte[] pattern){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final long cursor, final String pattern){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final String cursor, final String pattern){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final int cursor, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final long cursor, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final String cursor, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final byte[] cursor, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final int cursor, final String pattern, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final int cursor, final byte[] pattern, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final long cursor, final String pattern, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<String>> scan(final String cursor, final String pattern, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count){
		throw new NotSupportedCommandException(ProtocolCommand.SCAN);
	}

	@Override
	default Long sort(final String key, final String destKey){
		throw new NotSupportedCommandException(ProtocolCommand.SORT);
	}

	@Override
	default Long sort(final byte[] key, final byte[] destKey){
		throw new NotSupportedCommandException(ProtocolCommand.SORT);
	}

	@Override
	default Long sort(final String key, final String destKey, final SortArgument sortArgument){
		throw new NotSupportedCommandException(ProtocolCommand.SORT);
	}

	@Override
	default Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		throw new NotSupportedCommandException(ProtocolCommand.SORT);
	}

	@Override
	default List<String> blPop(final String[] keys, final int timeout){
		throw new NotSupportedCommandException(ProtocolCommand.BLPOP);
	}

	@Override
	default List<byte[]> blPop(final byte[][] keys, final int timeout){
		throw new NotSupportedCommandException(ProtocolCommand.BLPOP);
	}

	@Override
	default List<String> brPop(final String[] keys, final int timeout){
		throw new NotSupportedCommandException(ProtocolCommand.BRPOP);
	}

	@Override
	default List<byte[]> brPop(final byte[][] keys, final int timeout){
		throw new NotSupportedCommandException(ProtocolCommand.BRPOP);
	}

	@Override
	default String brPoplPush(final String key, final String destKey, final int timeout){
		throw new NotSupportedCommandException(ProtocolCommand.BRPOPLPUSH);
	}

	@Override
	default byte[] brPoplPush(final byte[] key, final byte[] destKey, final int timeout){
		throw new NotSupportedCommandException(ProtocolCommand.BRPOPLPUSH);
	}

	@Override
	default String rPoplPush(final String source, final String destKey){
		throw new NotSupportedCommandException(ProtocolCommand.RPOPLPUSH);
	}

	@Override
	default byte[] rPoplPush(final byte[] key, final byte[] destKey){
		throw new NotSupportedCommandException(ProtocolCommand.RPOPLPUSH);
	}

	@Override
	default void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
		throw new NotSupportedCommandException(ProtocolCommand.PSUBSCRIBE);
	}

	@Override
	default void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
		throw new NotSupportedCommandException(ProtocolCommand.PSUBSCRIBE);
	}

	@Override
	default List<String> pubsubChannels(final String pattern){
		throw new NotSupportedCommandException(ProtocolCommand.PUBSUB);
	}

	@Override
	default List<byte[]> pubsubChannels(final byte[] pattern){
		throw new NotSupportedCommandException(ProtocolCommand.PUBSUB);
	}

	@Override
	default Long pubsubNumPat(){
		throw new NotSupportedCommandException(ProtocolCommand.PUBSUB);
	}

	@Override
	default Map<String, String> pubsubNumSub(final String... channels){
		throw new NotSupportedCommandException(ProtocolCommand.PUBSUB);
	}

	@Override
	default Map<byte[], byte[]> pubsubNumSub(final byte[]... channels){
		throw new NotSupportedCommandException(ProtocolCommand.PUBSUB);
	}

	@Override
	default Long publish(final String channel, final String message){
		throw new NotSupportedCommandException(ProtocolCommand.PUBLISH);
	}

	@Override
	default Long publish(final byte[] channel, final byte[] message){
		throw new NotSupportedCommandException(ProtocolCommand.PUBLISH);
	}

	@Override
	default Object pUnSubscribe(){
		throw new NotSupportedCommandException(ProtocolCommand.PUNSUBSCRIBE);
	}

	@Override
	default Object pUnSubscribe(final String... patterns){
		throw new NotSupportedCommandException(ProtocolCommand.PUNSUBSCRIBE);
	}

	@Override
	default Object pUnSubscribe(final byte[]... patterns){
		throw new NotSupportedCommandException(ProtocolCommand.PUNSUBSCRIBE);
	}

	@Override
	default void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
		throw new NotSupportedCommandException(ProtocolCommand.SUBSCRIBE);
	}

	@Override
	default void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
		throw new NotSupportedCommandException(ProtocolCommand.SUBSCRIBE);
	}

	@Override
	default Object unSubscribe(){
		throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	default Object unSubscribe(final String... channels){
		throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	default Object unSubscribe(final byte[]... channels){
		throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	default Object eval(final String script){
		throw new NotSupportedCommandException(ProtocolCommand.EVAL);
	}

	@Override
	default Object eval(final byte[] script){
		throw new NotSupportedCommandException(ProtocolCommand.EVAL);
	}

	@Override
	default Object eval(final String script, final String... params){
		throw new NotSupportedCommandException(ProtocolCommand.EVAL);
	}

	@Override
	default Object eval(final byte[] script, final byte[]... params){
		throw new NotSupportedCommandException(ProtocolCommand.EVAL);
	}

	@Override
	default Object eval(final String script, final String[] keys, final String[] args){
		throw new NotSupportedCommandException(ProtocolCommand.EVAL);
	}

	@Override
	default Object eval(final byte[] script, final byte[][] keys, final byte[][] args){
		throw new NotSupportedCommandException(ProtocolCommand.EVAL);
	}

	@Override
	default Object evalSha(final String digest){
		throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
	}

	@Override
	default Object evalSha(final byte[] digest){
		throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
	}

	@Override
	default Object evalSha(final String digest, final String... params){
		throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
	}

	@Override
	default Object evalSha(final byte[] digest, final byte[]... params){
		throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
	}

	@Override
	default Object evalSha(final String digest, final String[] keys, final String[] arguments){
		throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
	}

	@Override
	default Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments){
		throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
	}

	@Override
	default List<Boolean> scriptExists(final String... sha1){
		throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_EXISTS);
	}

	@Override
	default List<Boolean> scriptExists(final byte[]... sha1){
		throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_EXISTS);
	}

	@Override
	default Status scriptFlush(){
		throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_FLUSH);
	}

	@Override
	default Status scriptKill(){
		throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_KILL);
	}

	@Override
	default String scriptLoad(final String script){
		throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_LOAD);
	}

	@Override
	default byte[] scriptLoad(final byte[] script){
		throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_LOAD);
	}

	@Override
	default String bgRewriteAof(){
		throw new NotSupportedCommandException(ProtocolCommand.BGREWRITEAOF);
	}

	@Override
	default String bgSave(){
		throw new NotSupportedCommandException(ProtocolCommand.BGSAVE);
	}

	@Override
	default Status clientKill(final String host, final int port){
		throw new NotSupportedCommandException(ProtocolCommand.CLIENT_KILL);
	}

	@Override
	default String clientGetName(){
		throw new NotSupportedCommandException(ProtocolCommand.CLIENT_GETNAME);
	}

	@Override
	default String clientId(){
		throw new NotSupportedCommandException(ProtocolCommand.CLIENT_ID);
	}

	@Override
	default List<Client> clientList(){
		throw new NotSupportedCommandException(ProtocolCommand.CLIENT_LIST);
	}

	@Override
	default Status clientPause(final long timeout){
		throw new NotSupportedCommandException(ProtocolCommand.CLIENT_PAUSE);
	}

	@Override
	default Status clientReply(final ClientReply option){
		throw new NotSupportedCommandException(ProtocolCommand.CLIENT_REPLY);
	}

	@Override
	default Status clientSetName(final String name){
		throw new NotSupportedCommandException(ProtocolCommand.CLIENT_SETNAME);
	}

	@Override
	default Status clientSetName(final byte[] name){
		throw new NotSupportedCommandException(ProtocolCommand.CLIENT_SETNAME);
	}

	@Override
	default Status clientUnblock(final int clientId){
		throw new NotSupportedCommandException(ProtocolCommand.CLIENT_UNBLOCK);
	}

	@Override
	default Status clientUnblock(final int clientId, final ClientUnblockType type){
		throw new NotSupportedCommandException(ProtocolCommand.CLIENT_UNBLOCK);
	}

	@Override
	default List<String> configGet(final String parameter){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_GET);
	}

	@Override
	default List<byte[]> configGet(final byte[] parameter){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_GET);
	}

	@Override
	default Status configResetStat(){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_RESETSTAT);
	}

	@Override
	default Status configRewrite(){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_REWRITE);
	}

	@Override
	default Status configSet(final String parameter, final String value){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_SET);
	}

	@Override
	default Status configSet(final byte[] parameter, final byte[] value){
		throw new NotSupportedCommandException(ProtocolCommand.CONFIG_SET);
	}

	@Override
	default Long dbSize(){
		throw new NotSupportedCommandException(ProtocolCommand.DBSIZE);
	}

	@Override
	default String debugObject(final String key){
		throw new NotSupportedCommandException(ProtocolCommand.DEBUG_OBJECT);
	}

	@Override
	default String debugSegfault(){
		throw new NotSupportedCommandException(ProtocolCommand.DEBUG_SEGFAULT);
	}

	@Override
	default Status flushAll(){
		throw new NotSupportedCommandException(ProtocolCommand.FLUSHALL);
	}

	@Override
	default Status flushDb(){
		throw new NotSupportedCommandException(ProtocolCommand.FLUSHDB);
	}

	@Override
	default Info info(final InfoSection section){
		throw new NotSupportedCommandException(ProtocolCommand.INFO);
	}

	@Override
	default Info info(){
		throw new NotSupportedCommandException(ProtocolCommand.INFO);
	}

	@Override
	default Long lastSave(){
		throw new NotSupportedCommandException(ProtocolCommand.LASTSAVE);
	}

	@Override
	default String memoryDoctor(){
		throw new NotSupportedCommandException(ProtocolCommand.MEMORY_DOCTOR);
	}

	@Override
	default void monitor(final RedisMonitor redisMonitor){
		throw new NotSupportedCommandException(ProtocolCommand.MONITOR);
	}

	@Override
	default Object pSync(final String masterRunId, final int offset){
		throw new NotSupportedCommandException(ProtocolCommand.PSYNC);
	}

	@Override
	default Object pSync(final byte[] masterRunId, final int offset){
		throw new NotSupportedCommandException(ProtocolCommand.PSYNC);
	}

	@Override
	default Object pSync(final String masterRunId, final long offset){
		throw new NotSupportedCommandException(ProtocolCommand.PSYNC);
	}

	@Override
	default Object pSync(final byte[] masterRunId, final long offset){
		throw new NotSupportedCommandException(ProtocolCommand.PSYNC);
	}

	@Override
	default Status replicaOf(final String host, final int port){
		throw new NotSupportedCommandException(ProtocolCommand.REPLICAOF);
	}

	@Override
	default Role role(){
		throw new NotSupportedCommandException(ProtocolCommand.ROLE);
	}

	@Override
	default Status save(){
		throw new NotSupportedCommandException(ProtocolCommand.SAVE);
	}

	@Override
	default void shutdown(){
		throw new NotSupportedCommandException(ProtocolCommand.SHUTDOWN);
	}

	@Override
	default void shutdown(final boolean save){
		throw new NotSupportedCommandException(ProtocolCommand.SHUTDOWN);
	}

	@Override
	default Status slaveOf(final String host, final int port){
		throw new NotSupportedCommandException(ProtocolCommand.SLAVEOF);
	}

	@Override
	default Object slowLog(final SlowLogCommand command, final String... args){
		throw new NotSupportedCommandException(ProtocolCommand.SLOWLOG);
	}

	@Override
	default Object slowLog(final SlowLogCommand command, final byte[]... args){
		throw new NotSupportedCommandException(ProtocolCommand.SLOWLOG);
	}

	@Override
	default RedisServerTime time(){
		throw new NotSupportedCommandException(ProtocolCommand.TIME);
	}

	@Override
	default Set<String> sDiff(final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SDIFF);
	}

	@Override
	default Set<byte[]> sDiff(byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SDIFF);
	}

	@Override
	default Long sDiffStore(final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SDIFFSTORE);
	}

	@Override
	default Long sDiffStore(final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SDIFFSTORE);
	}

	@Override
	default Set<String> sInter(final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SINTER);
	}

	@Override
	default Set<byte[]> sInter(final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SINTER);
	}

	@Override
	default Long sInterStore(final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SINTERSTORE);
	}

	@Override
	default Long sInterStore(final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SINTERSTORE);
	}

	@Override
	default Status sMove(final String key, final String destKey, final String member){
		throw new NotSupportedCommandException(ProtocolCommand.SMOVE);
	}

	@Override
	default Status sMove(final byte[] key, final byte[] destKey, final byte[] member){
		throw new NotSupportedCommandException(ProtocolCommand.SMOVE);
	}

	@Override
	default Set<String> sUnion(final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SUNION);
	}

	@Override
	default Set<byte[]> sUnion(final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SUNION);
	}

	@Override
	default Long sUnionStore(final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SUNIONSTORE);
	}

	@Override
	default Long sUnionStore(final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.SUNIONSTORE);
	}
	/*

	@Override
	default Status mSet(final Map<String, String> values){
		throw new NotSupportedCommandException(ProtocolCommand.MGET);
	}

	@Override
	default Status mSetNx(final Map<String, String> values){
		throw new NotSupportedCommandException(ProtocolCommand.MGET);
	}

	@Override
	default List<String> mGet(final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.MGET);
	}

	@Override
	default List<byte[]> mGet(final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.MGET);
	}

	@Override
	default Long zInterStore(final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final String destKey, final Aggregate aggregate, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final String destKey, final double[] weights, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZINTERSTORE);
	}

	@Override
	default Long zUnionStore(final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final String destKey, final Aggregate aggregate, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final String destKey, final double[] weights, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.ZUNIONSTORE);
	}

	@Override
	default Long bitOp(final Operation operation, final String destKey, final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.BITOP);
	}

	@Override
	default Long bitOp(final Operation operation, final byte[] destKey, final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.BITOP);
	}

	@Override
	default Transaction multi(){
		throw new NotSupportedCommandException(ProtocolCommand.MULTI);
	}

	@Override
	default void exec(final Transaction transaction){
		throw new NotSupportedCommandException(ProtocolCommand.EXEC);
	}

	@Override
	default void discard(final Transaction transaction){
		throw new NotSupportedCommandException(ProtocolCommand.DISCARD);
	}

	@Override
	default Status watch(final String... keys){
		throw new NotSupportedCommandException(ProtocolCommand.WATCH);
	}

	@Override
	default Status watch(final byte[]... keys){
		throw new NotSupportedCommandException(ProtocolCommand.WATCH);
	}

	@Override
	default Status unwatch(){
		throw new NotSupportedCommandException(ProtocolCommand.UNWATCH);
	}

	@Override
	default Object sync(){
		throw new NotSupportedCommandException(ProtocolCommand.SYNC);
	}*/

}
