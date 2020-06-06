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
import com.buession.lang.Status;
import com.buession.redis.client.operations.BinaryPubSubRedisOperations;
import com.buession.redis.client.GenericRedisClient;
import com.buession.redis.client.operations.ClientAndServerRedisOperations;
import com.buession.redis.client.operations.DebugRedisOperations;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.operations.PubSubRedisOperations;
import com.buession.redis.client.operations.TransactionRedisOperations;
import com.buession.redis.core.Client;
import com.buession.redis.core.Info;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.ScanResult;
import com.buession.redis.transaction.Transaction;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public Status rename(final String key, final String newKey){
		return execute(keyOperations, (ops)->ops.rename(key, newKey));
	}

	@Override
	public Status rename(final byte[] key, final byte[] newKey){
		return execute(binaryKeyOperations, (ops)->ops.rename(key, newKey));
	}

	@Override
	public Status renameNx(final String key, final String newKey){
		return execute(keyOperations, (ops)->ops.renameNx(key, newKey));
	}

	@Override
	public Status renameNx(final byte[] key, final byte[] newKey){
		return execute(binaryKeyOperations, (ops)->ops.renameNx(key, newKey));
	}

	@Override
	public String randomKey(){
		return execute(keyOperations, (ops)->ops.randomKey());
	}

	@Override
	public Set<String> keys(final String pattern){
		return execute(keyOperations, (ops)->ops.keys(pattern));
	}

	@Override
	public Set<byte[]> keys(final byte[] pattern){
		return execute(binaryKeyOperations, (ops)->ops.keys(pattern));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor){
		return execute(keyOperations, (ops)->ops.scan(cursor));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor){
		return execute(binaryKeyOperations, (ops)->ops.scan(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern){
		return execute(keyOperations, (ops)->ops.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern){
		return execute(binaryKeyOperations, (ops)->ops.scan(cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final int count){
		return execute(keyOperations, (ops)->ops.scan(cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final int count){
		return execute(binaryKeyOperations, (ops)->ops.scan(cursor, count));
	}

	@Override
	public ScanResult<List<String>> scan(final String cursor, final String pattern, final int count){
		return execute(keyOperations, (ops)->ops.scan(cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> scan(final byte[] cursor, final byte[] pattern, final int count){
		return execute(binaryKeyOperations, (ops)->ops.scan(cursor, pattern, count));
	}

	@Override
	public Long sort(final String key, final String destKey){
		return execute(keyOperations, (ops)->ops.sort(key, destKey));
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey){
		return execute(binaryKeyOperations, (ops)->ops.sort(key, destKey));
	}

	@Override
	public Long sort(final String key, final String destKey, final SortArgument sortArgument){
		return execute(keyOperations, (ops)->ops.sort(key, destKey, sortArgument));
	}

	@Override
	public Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument){
		return execute(binaryKeyOperations, (ops)->ops.sort(key, destKey, sortArgument));
	}

	@Override
	public Status mSet(final Map<String, String> values){
		return execute(stringOperations, (ops)->ops.mSet(values));
	}

	@Override
	public Status mSetNx(final Map<String, String> values){
		return execute(stringOperations, (ops)->ops.mSetNx(values));
	}

	@Override
	public List<String> mGet(final String... keys){
		return execute(stringOperations, (ops)->ops.mGet());
	}

	@Override
	public List<byte[]> mGet(final byte[]... keys){
		return execute(binaryStringOperations, (ops)->ops.mGet());
	}

	@Override
	public String rPoplPush(final String source, final String destKey){
		return execute(listOperations, (ops)->ops.rPoplPush(source, destKey));
	}

	@Override
	public byte[] rPoplPush(final byte[] source, final byte[] destKey){
		return execute(binaryListOperations, (ops)->ops.rPoplPush(source, destKey));
	}

	@Override
	public String brPoplPush(final String source, final String destKey, final int timeout){
		return execute(listOperations, (ops)->ops.brPoplPush(source, destKey, timeout));
	}

	@Override
	public byte[] brPoplPush(final byte[] source, final byte[] destKey, final int timeout){
		return execute(binaryListOperations, (ops)->ops.brPoplPush(source, destKey, timeout));
	}

	@Override
	public Set<String> sDiff(final String... keys){
		return execute(setOperations, (ops)->ops.sDiff(keys));
	}

	@Override
	public Set<byte[]> sDiff(final byte[]... keys){
		return execute(binarySetOperations, (ops)->ops.sDiff(keys));
	}

	@Override
	public Long sDiffStore(final String destKey, final String... keys){
		return execute(setOperations, (ops)->ops.sDiffStore(destKey, keys));
	}

	@Override
	public Long sDiffStore(final byte[] destKey, final byte[]... keys){
		return execute(binarySetOperations, (ops)->ops.sDiffStore(destKey, keys));
	}

	@Override
	public Set<String> sInter(final String... keys){
		return execute(setOperations, (ops)->ops.sInter(keys));
	}

	@Override
	public Set<byte[]> sInter(final byte[]... keys){
		return execute(binarySetOperations, (ops)->ops.sInter(keys));
	}

	@Override
	public Long sInterStore(final String destKey, final String... keys){
		return execute(setOperations, (ops)->ops.sInterStore(destKey, keys));
	}

	@Override
	public Long sInterStore(final byte[] destKey, final byte[]... keys){
		return execute(binarySetOperations, (ops)->ops.sInterStore(destKey, keys));
	}

	@Override
	public Set<String> sUnion(final String... keys){
		return execute(setOperations, (ops)->ops.sUnion(keys));
	}

	@Override
	public Set<byte[]> sUnion(final byte[]... keys){
		return execute(binarySetOperations, (ops)->ops.sUnion(keys));
	}

	@Override
	public Long sUnionStore(final String destKey, final String... keys){
		return execute(setOperations, (ops)->ops.sUnionStore(destKey, keys));
	}

	@Override
	public Long sUnionStore(final byte[] destKey, final byte[]... keys){
		return execute(binarySetOperations, (ops)->ops.sUnionStore(destKey, keys));
	}

	@Override
	public Status sMove(final String source, final String destKey, final String member){
		return execute(setOperations, (ops)->ops.sMove(source, destKey, member));
	}

	@Override
	public Status sMove(final byte[] source, final byte[] destKey, final byte[] member){
		return execute(binarySetOperations, (ops)->ops.sMove(source, destKey, member));
	}

	@Override
	public Long zInterStore(final String destKey, final String... keys){
		return execute(sortedSetOperations, (ops)->ops.zInterStore(destKey, keys));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final byte[]... keys){
		return execute(binarySortedSetOperations, (ops)->ops.zInterStore(destKey, keys));
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final String... keys){
		return execute(sortedSetOperations, (ops)->ops.zInterStore(destKey, aggregate, keys));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		return execute(binarySortedSetOperations, (ops)->ops.zInterStore(destKey, aggregate, keys));
	}

	@Override
	public Long zInterStore(final String destKey, final double[] weights, final String... keys){
		return execute(sortedSetOperations, (ops)->ops.zInterStore(destKey, weights, keys));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		return execute(binarySortedSetOperations, (ops)->ops.zInterStore(destKey, weights, keys));
	}

	@Override
	public Long zInterStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		return execute(sortedSetOperations, (ops)->ops.zInterStore(destKey, aggregate, weights, keys));
	}

	@Override
	public Long zInterStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		return execute(binarySortedSetOperations, (ops)->ops.zInterStore(destKey, aggregate, weights, keys));
	}

	@Override
	public Long zUnionStore(final String destKey, final String... keys){
		return execute(sortedSetOperations, (ops)->ops.zUnionStore(destKey, keys));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final byte[]... keys){
		return execute(binarySortedSetOperations, (ops)->ops.zUnionStore(destKey, keys));
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final String... keys){
		return execute(sortedSetOperations, (ops)->ops.zUnionStore(destKey, aggregate, keys));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final byte[]... keys){
		return execute(binarySortedSetOperations, (ops)->ops.zUnionStore(destKey, aggregate, keys));
	}

	@Override
	public Long zUnionStore(final String destKey, final double[] weights, final String... keys){
		return execute(sortedSetOperations, (ops)->ops.zUnionStore(destKey, weights, keys));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final double[] weights, final byte[]... keys){
		return execute(binarySortedSetOperations, (ops)->ops.zUnionStore(destKey, weights, keys));
	}

	@Override
	public Long zUnionStore(final String destKey, final Aggregate aggregate, final double[] weights,
			final String... keys){
		return execute(sortedSetOperations, (ops)->ops.zUnionStore(destKey, aggregate, weights, keys));
	}

	@Override
	public Long zUnionStore(final byte[] destKey, final Aggregate aggregate, final double[] weights,
			final byte[]... keys){
		return execute(binarySortedSetOperations, (ops)->ops.zUnionStore(destKey, aggregate, weights, keys));
	}

	@Override
	public Status pfMerge(final String destKey, final String... keys){
		return execute(hyperLogLogOperations, (ops)->ops.pfMerge(destKey, keys));
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys){
		return execute(binaryHyperLogLogOperations, (ops)->ops.pfMerge(destKey, keys));
	}

	@Override
	public Long pfCount(final String... keys){
		return execute(hyperLogLogOperations, (ops)->ops.pfCount(keys));
	}

	@Override
	public Long pfCount(final byte[]... keys){
		return execute(binaryHyperLogLogOperations, (ops)->ops.pfCount(keys));
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
