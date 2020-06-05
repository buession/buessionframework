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

import com.buession.core.Executor;
import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.RedisConnectionFactory;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.client.operations.BinaryBitMapRedisOperations;
import com.buession.redis.client.operations.BinaryClientAndServerRedisOperations;
import com.buession.redis.client.operations.BinaryConfigureRedisOperations;
import com.buession.redis.client.operations.BinaryDatabaseRedisOperations;
import com.buession.redis.client.operations.BinaryDebugRedisOperations;
import com.buession.redis.client.operations.BinaryGeoRedisOperations;
import com.buession.redis.client.operations.BinaryHashRedisOperations;
import com.buession.redis.client.operations.BinaryHyperLogLogRedisOperations;
import com.buession.redis.client.operations.BinaryInternalRedisOperations;
import com.buession.redis.client.operations.BinaryKeyRedisOperations;
import com.buession.redis.client.operations.BinaryListRedisOperations;
import com.buession.redis.client.operations.BinaryLuaRedisOperations;
import com.buession.redis.client.operations.BinaryPersistenceRedisOperations;
import com.buession.redis.client.operations.BinaryPubSubRedisOperations;
import com.buession.redis.client.operations.BinaryReplicationRedisOperations;
import com.buession.redis.client.operations.BinarySetRedisOperations;
import com.buession.redis.client.operations.BinarySortedSetRedisOperations;
import com.buession.redis.client.operations.BinaryStringRedisOperations;
import com.buession.redis.client.operations.BinaryTransactionRedisOperations;
import com.buession.redis.client.operations.BitMapRedisOperations;
import com.buession.redis.client.operations.ClientAndServerRedisOperations;
import com.buession.redis.client.operations.ConfigureRedisOperations;
import com.buession.redis.client.operations.DatabaseRedisOperations;
import com.buession.redis.client.operations.DebugRedisOperations;
import com.buession.redis.client.operations.GeoRedisOperations;
import com.buession.redis.client.operations.HashRedisOperations;
import com.buession.redis.client.operations.HyperLogLogRedisOperations;
import com.buession.redis.client.operations.InternalRedisOperations;
import com.buession.redis.client.operations.KeyRedisOperations;
import com.buession.redis.client.operations.ListRedisOperations;
import com.buession.redis.client.operations.LuaRedisOperations;
import com.buession.redis.client.operations.PersistenceRedisOperations;
import com.buession.redis.client.operations.PubSubRedisOperations;
import com.buession.redis.client.operations.RedisOperations;
import com.buession.redis.client.operations.ReplicationRedisOperations;
import com.buession.redis.client.operations.SetRedisOperations;
import com.buession.redis.client.operations.SortedSetRedisOperations;
import com.buession.redis.client.operations.StringRedisOperations;
import com.buession.redis.client.operations.TransactionRedisOperations;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.exception.RedisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public abstract class AbstractRedisClient implements RedisClient {

	private RedisConnectionFactory connectionFactory;

	private RedisConnection connection;

	private boolean enableTransactionSupport = false;

	protected final KeyRedisOperations keyOperations = createKeyRedisOperations();

	protected final BinaryKeyRedisOperations binaryKeyOperations = createBinaryKeyRedisOperations();

	protected final StringRedisOperations stringOperations = createStringRedisOperations();

	protected final BinaryStringRedisOperations binaryStringOperations = createBinaryStringRedisOperations();

	protected final HashRedisOperations hashOperations = createHashRedisOperations();

	protected final BinaryHashRedisOperations binaryHashOperations = createBinaryHashRedisOperations();

	protected final ListRedisOperations listOperations = createListRedisOperations();

	protected final BinaryListRedisOperations binaryListOperations = createBinaryListRedisOperations();

	protected final SetRedisOperations setOperations = createSetRedisOperations();

	protected final BinarySetRedisOperations binarySetOperations = createBinarySetRedisOperations();

	protected final SortedSetRedisOperations sortedSetOperations = createSortedSetRedisOperations();

	protected final BinarySortedSetRedisOperations binarySortedSetOperations = createBinarySortedSetRedisOperations();

	protected final HyperLogLogRedisOperations hyperLogLogOperations = createHyperLogLogRedisOperations();

	protected final BinaryHyperLogLogRedisOperations binaryHyperLogLogOperations =
			createBinaryHyperLogLogRedisOperations();

	protected final GeoRedisOperations geoOperations = createGeoRedisOperations();

	protected final BinaryGeoRedisOperations binaryGeoOperations = createBinaryGeoRedisOperations();

	protected final BitMapRedisOperations bitMapOperations = createBitMapRedisOperations();

	protected final BinaryBitMapRedisOperations binaryBitMapOperations = createBinaryBitMapRedisOperations();

	protected final TransactionRedisOperations transactionOperations = createTransactionRedisOperations();

	protected final BinaryTransactionRedisOperations binaryTransactionOperations =
			createBinaryTransactionRedisOperations();

	protected final PubSubRedisOperations pubSubOperations = createPubSubRedisOperations();

	protected final BinaryPubSubRedisOperations binaryPubSubOperations = createBinaryPubSubRedisOperations();

	protected final DatabaseRedisOperations databaseOperations = createDatabaseRedisOperations();

	protected final BinaryDatabaseRedisOperations binaryDatabaseOperations = createBinaryDatabaseRedisOperations();

	protected final LuaRedisOperations luaOperations = createLuaRedisOperations();

	protected final BinaryLuaRedisOperations binaryLuaOperations = createBinaryLuaRedisOperations();

	protected final PersistenceRedisOperations persistenceOperations = createPersistenceRedisOperations();

	protected final BinaryPersistenceRedisOperations binaryPersistenceOperations =
			createBinaryPersistenceRedisOperations();

	protected final ReplicationRedisOperations replicationOperations = createReplicationRedisOperations();

	protected final BinaryReplicationRedisOperations binaryReplicationOperations =
			createBinaryReplicationRedisOperations();

	protected final ClientAndServerRedisOperations clientAndServerOperations = createClientAndServerRedisOperations();

	protected final BinaryClientAndServerRedisOperations binaryClientAndServerOperations =
			createBinaryClientAndServerRedisOperations();

	protected final ConfigureRedisOperations configureOperations = createConfigureRedisOperations();

	protected final BinaryConfigureRedisOperations binaryConfigureOperations = createBinaryConfigureRedisOperations();

	protected final InternalRedisOperations internalOperations = createInternalRedisOperations();

	protected final BinaryInternalRedisOperations binaryInternalOperations = createBinaryInternalRedisOperations();

	protected final DebugRedisOperations debugOperations = createDebugRedisOperations();

	protected final BinaryDebugRedisOperations binaryDebugOperations = createBinaryDebugRedisOperations();

	private final static Logger logger = LoggerFactory.getLogger(AbstractRedisClient.class);

	public AbstractRedisClient(){
		super();
	}

	public AbstractRedisClient(RedisConnection connection){
		setConnection(connection);
	}

	@Override
	public RedisConnection getConnection(){
		return connection;
	}

	@Override
	public void setConnection(RedisConnection connection){
		this.connection = connection;
		connectionFactory = new RedisConnectionFactory(connection);
	}

	public boolean isEnableTransactionSupport(){
		return getEnableTransactionSupport();
	}

	public boolean getEnableTransactionSupport(){
		return enableTransactionSupport;
	}

	public void setEnableTransactionSupport(boolean enableTransactionSupport){
		this.enableTransactionSupport = enableTransactionSupport;
	}

	@Override
	public boolean exists(final String key){
		return execute(keyOperations, (ops)->ops.exists(key));
	}

	@Override
	public boolean exists(final byte[] key){
		return execute(binaryKeyOperations, (ops)->ops.exists(key));
	}

	@Override
	public Type type(final String key){
		return execute(keyOperations, (ops)->ops.type(key));
	}

	@Override
	public Type type(final byte[] key){
		return execute(binaryKeyOperations, (ops)->ops.type(key));
	}

	@Override
	public Status expire(final String key, final int lifetime){
		return execute(keyOperations, (ops)->ops.expire(key, lifetime));
	}

	@Override
	public Status expire(final byte[] key, final int lifetime){
		return execute(binaryKeyOperations, (ops)->ops.expire(key, lifetime));
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp){
		return execute(keyOperations, (ops)->ops.expireAt(key, unixTimestamp));
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		return execute(binaryKeyOperations, (ops)->ops.expireAt(key, unixTimestamp));
	}

	@Override
	public Status pExpire(final String key, final int lifetime){
		return execute(keyOperations, (ops)->ops.pExpire(key, lifetime));
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		return execute(binaryKeyOperations, (ops)->ops.pExpire(key, lifetime));
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp){
		return execute(keyOperations, (ops)->ops.pExpireAt(key, unixTimestamp));
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		return execute(binaryKeyOperations, (ops)->ops.pExpireAt(key, unixTimestamp));
	}

	@Override
	public Long ttl(final String key){
		return execute(keyOperations, (ops)->ops.ttl(key));
	}

	@Override
	public Long ttl(final byte[] key){
		return execute(binaryKeyOperations, (ops)->ops.ttl(key));
	}

	@Override
	public Long pTtl(final String key){
		return execute(keyOperations, (ops)->ops.pTtl(key));
	}

	@Override
	public Long pTtl(final byte[] key){
		return execute(binaryKeyOperations, (ops)->ops.pTtl(key));
	}

	@Override
	public Status persist(final String key){
		return execute(keyOperations, (ops)->ops.persist(key));
	}

	@Override
	public Status persist(final byte[] key){
		return execute(binaryKeyOperations, (ops)->ops.persist(key));
	}

	@Override
	public List<String> sort(final String key){
		return execute(keyOperations, (ops)->ops.sort(key));
	}

	@Override
	public List<byte[]> sort(final byte[] key){
		return execute(binaryKeyOperations, (ops)->ops.sort(key));
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument){
		return execute(keyOperations, (ops)->ops.sort(key, sortArgument));
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument){
		return execute(binaryKeyOperations, (ops)->ops.sort(key, sortArgument));
	}

	@Override
	public byte[] dump(final String key){
		return execute(keyOperations, (ops)->ops.dump(key));
	}

	@Override
	public byte[] dump(final byte[] key){
		return execute(binaryKeyOperations, (ops)->ops.dump(key));
	}

	@Override
	public Status restore(final String key, final String serializedValue, final int ttl){
		return execute(keyOperations, (ops)->ops.restore(key, serializedValue, ttl));
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		return execute(binaryKeyOperations, (ops)->ops.restore(key, serializedValue, ttl));
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
		return execute(keyOperations, (ops)->ops.migrate(key, host, port, db, timeout));
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
		return execute(binaryKeyOperations, (ops)->ops.migrate(key, host, port, db, timeout));
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation migrateOperation){
		return execute(keyOperations, (ops)->ops.migrate(key, host, port, db, timeout, migrateOperation));
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation migrateOperation){
		return execute(binaryKeyOperations, (ops)->ops.migrate(key, host, port, db, timeout, migrateOperation));
	}

	@Override
	public Long del(final String... keys){
		return execute(keyOperations, (ops)->ops.del(keys));
	}

	@Override
	public Long del(final byte[]... keys){
		return execute(binaryKeyOperations, (ops)->ops.del(keys));
	}

	@Override
	public Status move(final String key, final int db){
		return execute(keyOperations, (ops)->ops.move(key, db));
	}

	@Override
	public Status move(final byte[] key, final int db){
		return execute(binaryKeyOperations, (ops)->ops.move(key, db));
	}

	@Override
	public Status set(final String key, final String value){
		return execute(stringOperations, (ops)->ops.set(key, value));
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		return execute(binaryStringOperations, (ops)->ops.set(key, value));
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument){
		return execute(stringOperations, (ops)->ops.set(key, value, setArgument));
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument){
		return execute(binaryStringOperations, (ops)->ops.set(key, value, setArgument));
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime){
		return execute(stringOperations, (ops)->ops.setEx(key, value, lifetime));
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		return execute(binaryStringOperations, (ops)->ops.setEx(key, value, lifetime));
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime){
		return execute(stringOperations, (ops)->ops.pSetEx(key, value, lifetime));
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		return execute(binaryStringOperations, (ops)->ops.pSetEx(key, value, lifetime));
	}

	@Override
	public Status setNx(final String key, final String value){
		return execute(stringOperations, (ops)->ops.setNx(key, value));
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		return execute(binaryStringOperations, (ops)->ops.setNx(key, value));
	}

	@Override
	public Long append(final String key, final String value){
		return execute(stringOperations, (ops)->ops.append(key, value));
	}

	@Override
	public Long append(final byte[] key, final byte[] value){
		return execute(binaryStringOperations, (ops)->ops.append(key, value));
	}

	@Override
	public String get(final String key){
		return execute(stringOperations, (ops)->ops.get(key));
	}

	@Override
	public byte[] get(final byte[] key){
		return execute(binaryStringOperations, (ops)->ops.get(key));
	}

	@Override
	public String getSet(final String key, final String value){
		return execute(stringOperations, (ops)->ops.getSet(key, value));
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		return execute(binaryStringOperations, (ops)->ops.getSet(key, value));
	}


	@Override
	public Long incr(final String key){
		return execute(stringOperations, (ops)->ops.incr(key));
	}

	@Override
	public Long incr(final byte[] key){
		return execute(binaryStringOperations, (ops)->ops.incr(key));
	}

	@Override
	public Long incrBy(final String key, final int value){
		return execute(stringOperations, (ops)->ops.incrBy(key, value));
	}

	@Override
	public Long incrBy(final byte[] key, final int value){
		return execute(binaryStringOperations, (ops)->ops.incrBy(key, value));
	}

	@Override
	public Long incrBy(final String key, final long value){
		return execute(stringOperations, (ops)->ops.incrBy(key, value));
	}

	@Override
	public Long incrBy(final byte[] key, final long value){
		return execute(binaryStringOperations, (ops)->ops.incrBy(key, value));
	}

	@Override
	public Double incrByFloat(final String key, final float value){
		return execute(stringOperations, (ops)->ops.incrByFloat(key, value));
	}

	@Override
	public Double incrByFloat(final byte[] key, final float value){
		return execute(binaryStringOperations, (ops)->ops.incrByFloat(key, value));
	}

	@Override
	public Double incrByFloat(final String key, final double value){
		return execute(stringOperations, (ops)->ops.incrByFloat(key, value));
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value){
		return execute(binaryStringOperations, (ops)->ops.incrByFloat(key, value));
	}

	@Override
	public Long decr(final String key){
		return execute(stringOperations, (ops)->ops.decr(key));
	}

	@Override
	public Long decr(final byte[] key){
		return execute(binaryStringOperations, (ops)->ops.decr(key));
	}

	@Override
	public Long decrBy(final String key, final int value){
		return execute(stringOperations, (ops)->ops.decrBy(key, value));
	}

	@Override
	public Long decrBy(final byte[] key, final int value){
		return execute(binaryStringOperations, (ops)->ops.decrBy(key, value));
	}

	@Override
	public Long decrBy(final String key, final long value){
		return execute(stringOperations, (ops)->ops.decrBy(key, value));
	}

	@Override
	public Long decrBy(final byte[] key, final long value){
		return execute(binaryStringOperations, (ops)->ops.decrBy(key, value));
	}

	@Override
	public Long setRange(final String key, final int offset, final String value){
		return execute(stringOperations, (ops)->ops.setRange(key, offset, value));
	}

	@Override
	public Long setRange(final byte[] key, final int offset, final byte[] value){
		return execute(binaryStringOperations, (ops)->ops.setRange(key, offset, value));
	}

	@Override
	public Long setRange(final String key, final long offset, final String value){
		return execute(stringOperations, (ops)->ops.setRange(key, offset, value));
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value){
		return execute(binaryStringOperations, (ops)->ops.setRange(key, offset, value));
	}

	@Override
	public String getRange(final String key, final int start, final int end){
		return execute(stringOperations, (ops)->ops.getRange(key, start, end));
	}

	@Override
	public byte[] getRange(final byte[] key, final int start, final int end){
		return execute(binaryStringOperations, (ops)->ops.getRange(key, start, end));
	}

	@Override
	public String getRange(final String key, final long start, final long end){
		return execute(stringOperations, (ops)->ops.getRange(key, start, end));
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		return execute(binaryStringOperations, (ops)->ops.getRange(key, start, end));
	}

	@Override
	public String substr(final String key, final int start, final int end){
		return execute(stringOperations, (ops)->ops.substr(key, start, end));
	}

	@Override
	public byte[] substr(final byte[] key, final int start, final int end){
		return execute(binaryStringOperations, (ops)->ops.substr(key, start, end));
	}

	@Override
	public String substr(final String key, final long start, final long end){
		return execute(stringOperations, (ops)->ops.substr(key, start, end));
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end){
		return execute(binaryStringOperations, (ops)->ops.substr(key, start, end));
	}

	@Override
	public Long strlen(final String key){
		return execute(stringOperations, (ops)->ops.strlen(key));
	}

	@Override
	public Long strlen(final byte[] key){
		return execute(binaryStringOperations, (ops)->ops.strlen(key));
	}

	@Override
	public boolean hExists(final String key, final String field){
		return execute(hashOperations, (ops)->ops.hExists(key, field));
	}

	@Override
	public boolean hExists(final byte[] key, final byte[] field){
		return execute(binaryHashOperations, (ops)->ops.hExists(key, field));
	}

	@Override
	public Set<String> hKeys(final String key){
		return execute(hashOperations, (ops)->ops.hKeys(key));
	}

	@Override
	public Set<byte[]> hKeys(final byte[] key){
		return execute(binaryHashOperations, (ops)->ops.hKeys(key));
	}

	@Override
	public List<String> hVals(final String key){
		return execute(hashOperations, (ops)->ops.hVals(key));
	}

	@Override
	public List<byte[]> hVals(final byte[] key){
		return execute(binaryHashOperations, (ops)->ops.hVals(key));
	}

	@Override
	public Status hSet(final String key, final String field, final String value){
		return execute(hashOperations, (ops)->ops.hSet(key, field, value));
	}

	@Override
	public Status hSet(final byte[] key, final byte[] field, final byte[] value){
		return execute(binaryHashOperations, (ops)->ops.hSet(key, field, value));
	}

	@Override
	public Status hSetNx(final String key, final String field, final String value){
		return execute(hashOperations, (ops)->ops.hSetNx(key, field, value));
	}

	@Override
	public Status hSetNx(final byte[] key, final byte[] field, final byte[] value){
		return execute(binaryHashOperations, (ops)->ops.hSet(key, field, value));
	}

	@Override
	public String hGet(final String key, final String field){
		return execute(hashOperations, (ops)->ops.hGet(key, field));
	}

	@Override
	public byte[] hGet(final byte[] key, final byte[] field){
		return execute(binaryHashOperations, (ops)->ops.hGet(key, field));
	}

	@Override
	public Status hMSet(final String key, final Map<String, String> data){
		return execute(hashOperations, (ops)->ops.hMSet(key, data));
	}

	@Override
	public Status hMSet(final byte[] key, final Map<byte[], byte[]> data){
		return execute(binaryHashOperations, (ops)->ops.hMSet(key, data));
	}

	@Override
	public List<String> hMGet(final String key, final String... fields){
		return execute(hashOperations, (ops)->ops.hMGet(key, fields));
	}

	@Override
	public List<byte[]> hMGet(final byte[] key, final byte[]... fields){
		return execute(binaryHashOperations, (ops)->ops.hMGet(key, fields));
	}

	@Override
	public Map<String, String> hGetAll(final String key){
		return execute(hashOperations, (ops)->ops.hGetAll(key));
	}

	@Override
	public Map<byte[], byte[]> hGetAll(final byte[] key){
		return execute(binaryHashOperations, (ops)->ops.hGetAll(key));
	}

	@Override
	public Long hStrLen(final String key, final String field){
		return execute(hashOperations, (ops)->ops.hStrLen(key, field));
	}

	@Override
	public Long hStrLen(final byte[] key, final byte[] field){
		return execute(binaryHashOperations, (ops)->ops.hStrLen(key, field));
	}

	@Override
	public Long hLen(final String key){
		return execute(hashOperations, (ops)->ops.hLen(key));
	}

	@Override
	public Long hLen(final byte[] key){
		return execute(binaryHashOperations, (ops)->ops.hLen(key));
	}

	@Override
	public Long hIncrBy(final String key, final String field, final int value){
		return execute(hashOperations, (ops)->ops.hIncrBy(key, field, value));
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final int value){
		return execute(binaryHashOperations, (ops)->ops.hIncrBy(key, field, value));
	}

	@Override
	public Long hIncrBy(final String key, final String field, final long value){
		return execute(hashOperations, (ops)->ops.hIncrBy(key, field, value));
	}

	@Override
	public Long hIncrBy(final byte[] key, final byte[] field, final long value){
		return execute(binaryHashOperations, (ops)->ops.hIncrBy(key, field, value));
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final float value){
		return execute(hashOperations, (ops)->ops.hIncrByFloat(key, field, value));
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final float value){
		return execute(binaryHashOperations, (ops)->ops.hIncrByFloat(key, field, value));
	}

	@Override
	public Double hIncrByFloat(final String key, final String field, final double value){
		return execute(hashOperations, (ops)->ops.hIncrByFloat(key, field, value));
	}

	@Override
	public Double hIncrByFloat(final byte[] key, final byte[] field, final double value){
		return execute(binaryHashOperations, (ops)->ops.hIncrByFloat(key, field, value));
	}

	@Override
	public Long hDecrBy(final String key, final String field, final int value){
		return execute(hashOperations, (ops)->ops.hDecrBy(key, field, value));
	}

	@Override
	public Long hDecrBy(final byte[] key, final byte[] field, final int value){
		return execute(binaryHashOperations, (ops)->ops.hDecrBy(key, field, value));
	}

	@Override
	public Long hDecrBy(final String key, final String field, final long value){
		return execute(hashOperations, (ops)->ops.hDecrBy(key, field, value));
	}

	@Override
	public Long hDecrBy(final byte[] key, final byte[] field, final long value){
		return execute(binaryHashOperations, (ops)->ops.hDecrBy(key, field, value));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor){
		return execute(hashOperations, (ops)->ops.hScan(key, cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor){
		return execute(binaryHashOperations, (ops)->ops.hScan(key, cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor){
		return execute(hashOperations, (ops)->ops.hScan(key, cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor){
		return execute(binaryHashOperations, (ops)->ops.hScan(key, cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor){
		return execute(hashOperations, (ops)->ops.hScan(key, cursor));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor){
		return execute(binaryHashOperations, (ops)->ops.hScan(key, cursor));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final String pattern){
		return execute(hashOperations, (ops)->ops.hScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern){
		return execute(binaryHashOperations, (ops)->ops.hScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern){
		return execute(hashOperations, (ops)->ops.hScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern){
		return execute(binaryHashOperations, (ops)->ops.hScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern){
		return execute(hashOperations, (ops)->ops.hScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute(binaryHashOperations, (ops)->ops.hScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final int count){
		return execute(hashOperations, (ops)->ops.hScan(key, cursor, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final int count){
		return execute(binaryHashOperations, (ops)->ops.hScan(key, cursor, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final int count){
		return execute(hashOperations, (ops)->ops.hScan(key, cursor, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final int count){
		return execute(binaryHashOperations, (ops)->ops.hScan(key, cursor, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final int count){
		return execute(hashOperations, (ops)->ops.hScan(key, cursor, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final int count){
		return execute(binaryHashOperations, (ops)->ops.hScan(key, cursor, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final int cursor, final String pattern,
			final int count){
		return execute(hashOperations, (ops)->ops.hScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern,
			final int count){
		return execute(binaryHashOperations, (ops)->ops.hScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern,
			final int count){
		return execute(hashOperations, (ops)->ops.hScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final int cursor, final byte[] pattern,
			final int count){
		return execute(binaryHashOperations, (ops)->ops.hScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
			final int count){
		return execute(hashOperations, (ops)->ops.hScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		return execute(binaryHashOperations, (ops)->ops.hScan(key, cursor, pattern, count));
	}

	@Override
	public Long hDel(final String key, final String... fields){
		return execute(hashOperations, (ops)->ops.hDel(key, fields));
	}

	@Override
	public Long hDel(final byte[] key, final byte[]... fields){
		return execute(binaryHashOperations, (ops)->ops.hDel(key, fields));
	}

	@Override
	public Long lPush(final String key, final String... values){
		return execute(listOperations, (ops)->ops.lPush(key, values));
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values){
		return execute(binaryListOperations, (ops)->ops.lPush(key, values));
	}

	@Override
	public Long lPushX(final String key, final String... values){
		return execute(listOperations, (ops)->ops.lPushX(key, values));
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values){
		return execute(binaryListOperations, (ops)->ops.lPushX(key, values));
	}

	@Override
	public Long lInsert(final String key, final String value, final ListPosition position, final String pivot){
		return execute(listOperations, (ops)->ops.lInsert(key, value, position, pivot));
	}

	@Override
	public Long lInsert(final byte[] key, final byte[] value, final ListPosition position, final byte[] pivot){
		return execute(binaryListOperations, (ops)->ops.lInsert(key, value, position, pivot));
	}

	@Override
	public Status lSet(final String key, final int index, final String value){
		return execute(listOperations, (ops)->ops.lSet(key, index, value));
	}

	@Override
	public Status lSet(final byte[] key, final int index, final byte[] value){
		return execute(binaryListOperations, (ops)->ops.lSet(key, index, value));
	}

	@Override
	public Status lSet(final String key, final long index, final String value){
		return execute(listOperations, (ops)->ops.lSet(key, index, value));
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value){
		return execute(binaryListOperations, (ops)->ops.lSet(key, index, value));
	}

	@Override
	public String lIndex(final String key, final int index){
		return execute(listOperations, (ops)->ops.lIndex(key, index));
	}

	@Override
	public byte[] lIndex(final byte[] key, final int index){
		return execute(binaryListOperations, (ops)->ops.lIndex(key, index));
	}

	@Override
	public String lIndex(final String key, final long index){
		return execute(listOperations, (ops)->ops.lIndex(key, index));
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index){
		return execute(binaryListOperations, (ops)->ops.lIndex(key, index));
	}

	@Override
	public String lPop(final String key){
		return execute(listOperations, (ops)->ops.lPop(key));
	}

	@Override
	public byte[] lPop(final byte[] key){
		return execute(binaryListOperations, (ops)->ops.lPop(key));
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout){
		return execute(listOperations, (ops)->ops.blPop(keys, timeout));
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout){
		return execute(binaryListOperations, (ops)->ops.blPop(keys, timeout));
	}

	@Override
	public String rPop(final String key){
		return execute(listOperations, (ops)->ops.rPop(key));
	}

	@Override
	public byte[] rPop(final byte[] key){
		return execute(binaryListOperations, (ops)->ops.rPop(key));
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout){
		return execute(listOperations, (ops)->ops.brPop(keys, timeout));
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout){
		return execute(binaryListOperations, (ops)->ops.brPop(keys, timeout));
	}

	@Override
	public Long rPush(final String key, final String... values){
		return execute(listOperations, (ops)->ops.rPush(key, values));
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values){
		return execute(binaryListOperations, (ops)->ops.rPush(key, values));
	}

	@Override
	public Long rPushX(final String key, final String... values){
		return execute(listOperations, (ops)->ops.rPushX(key, values));
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values){
		return execute(binaryListOperations, (ops)->ops.rPushX(key, values));
	}

	@Override
	public Status lTrim(final String key, final int start, final int end){
		return execute(listOperations, (ops)->ops.lTrim(key, start, end));
	}

	@Override
	public Status lTrim(final byte[] key, final int start, final int end){
		return execute(binaryListOperations, (ops)->ops.lTrim(key, start, end));
	}

	@Override
	public Status lTrim(final String key, final long start, final long end){
		return execute(listOperations, (ops)->ops.lTrim(key, start, end));
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end){
		return execute(binaryListOperations, (ops)->ops.lTrim(key, start, end));
	}

	@Override
	public Long lRem(final String key, final String value, final int count){
		return execute(listOperations, (ops)->ops.lRem(key, value, count));
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final int count){
		return execute(binaryListOperations, (ops)->ops.lRem(key, value, count));
	}

	@Override
	public Long lRem(final String key, final String value, final long count){
		return execute(listOperations, (ops)->ops.lRem(key, value, count));
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count){
		return execute(binaryListOperations, (ops)->ops.lRem(key, value, count));
	}

	@Override
	public List<String> lRange(final String key, final int start, final int end){
		return execute(listOperations, (ops)->ops.lRange(key, start, end));
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final int start, final int end){
		return execute(binaryListOperations, (ops)->ops.lRange(key, start, end));
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end){
		return execute(listOperations, (ops)->ops.lRange(key, start, end));
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end){
		return execute(binaryListOperations, (ops)->ops.lRange(key, start, end));
	}

	@Override
	public Long lLen(final String key){
		return execute(listOperations, (ops)->ops.lLen(key));
	}

	@Override
	public Long lLen(final byte[] key){
		return execute(binaryListOperations, (ops)->ops.lLen(key));
	}

	@Override
	public Long sAdd(final String key, final String... members){
		return execute(setOperations, (ops)->ops.sAdd(key, members));
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members){
		return execute(binarySetOperations, (ops)->ops.sAdd(key, members));
	}

	@Override
	public Long sCard(final String key){
		return execute(setOperations, (ops)->ops.sCard(key));
	}

	@Override
	public Long sCard(final byte[] key){
		return execute(binarySetOperations, (ops)->ops.sCard(key));
	}

	@Override
	public boolean sisMember(final String key, final String member){
		return execute(setOperations, (ops)->ops.sisMember(key, member));
	}

	@Override
	public boolean sisMember(final byte[] key, final byte[] member){
		return execute(binarySetOperations, (ops)->ops.sisMember(key, member));
	}

	@Override
	public Set<String> sMembers(final String key){
		return execute(setOperations, (ops)->ops.sMembers(key));
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		return execute(binarySetOperations, (ops)->ops.sMembers(key));
	}

	@Override
	public String sPop(final String key){
		return execute(setOperations, (ops)->ops.sPop(key));
	}

	@Override
	public byte[] sPop(final byte[] key){
		return execute(binarySetOperations, (ops)->ops.sPop(key));
	}

	@Override
	public String sRandMember(final String key){
		return execute(setOperations, (ops)->ops.sRandMember(key));
	}

	@Override
	public byte[] sRandMember(final byte[] key){
		return execute(binarySetOperations, (ops)->ops.sRandMember(key));
	}

	@Override
	public List<String> sRandMember(final String key, final int count){
		return execute(setOperations, (ops)->ops.sRandMember(key, count));
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final int count){
		return execute(binarySetOperations, (ops)->ops.sRandMember(key, count));
	}

	@Override
	public List<String> sRandMember(final String key, final long count){
		return execute(setOperations, (ops)->ops.sRandMember(key, count));
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final long count){
		return execute(binarySetOperations, (ops)->ops.sRandMember(key, count));
	}

	@Override
	public Long sRem(final String key, final String... members){
		return execute(setOperations, (ops)->ops.sRem(key, members));
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members){
		return execute(binarySetOperations, (ops)->ops.sRem(key, members));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor){
		return execute(setOperations, (ops)->ops.sScan(key, cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor){
		return execute(setOperations, (ops)->ops.sScan(key, cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor){
		return execute(setOperations, (ops)->ops.sScan(key, cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final int count){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final int count){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final int count){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final int count){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final int count){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern, final int count){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern, final int count){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern, final int count){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
			final int count){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, pattern, count));
	}

	protected <O extends RedisOperations, R> R execute(final O operations, final Executor<O, R> executor){
		RedisConnection connection;

		if(enableTransactionSupport){
			// only bind resources in case of potential transaction synchronization
			connection = RedisConnectionUtils.bindConnection(connectionFactory, true);
		}else{
			connection = RedisConnectionUtils.getConnection(connectionFactory);
		}

		try{
			return executor.execute(operations);
		}catch(RedisException e){
			throw e;
		}finally{
			RedisConnectionUtils.releaseConnection(connectionFactory, connection, enableTransactionSupport);
		}
	}

	protected abstract KeyRedisOperations createKeyRedisOperations();

	protected abstract BinaryKeyRedisOperations createBinaryKeyRedisOperations();

	protected abstract StringRedisOperations createStringRedisOperations();

	protected abstract BinaryStringRedisOperations createBinaryStringRedisOperations();

	protected abstract HashRedisOperations createHashRedisOperations();

	protected abstract BinaryHashRedisOperations createBinaryHashRedisOperations();

	protected abstract ListRedisOperations createListRedisOperations();

	protected abstract BinaryListRedisOperations createBinaryListRedisOperations();

	protected abstract SetRedisOperations createSetRedisOperations();

	protected abstract BinarySetRedisOperations createBinarySetRedisOperations();

	protected abstract SortedSetRedisOperations createSortedSetRedisOperations();

	protected abstract BinarySortedSetRedisOperations createBinarySortedSetRedisOperations();

	protected abstract HyperLogLogRedisOperations createHyperLogLogRedisOperations();

	protected abstract BinaryHyperLogLogRedisOperations createBinaryHyperLogLogRedisOperations();

	protected abstract GeoRedisOperations createGeoRedisOperations();

	protected abstract BinaryGeoRedisOperations createBinaryGeoRedisOperations();

	protected abstract BitMapRedisOperations createBitMapRedisOperations();

	protected abstract BinaryBitMapRedisOperations createBinaryBitMapRedisOperations();

	protected abstract TransactionRedisOperations createTransactionRedisOperations();

	protected abstract BinaryTransactionRedisOperations createBinaryTransactionRedisOperations();

	protected abstract PubSubRedisOperations createPubSubRedisOperations();

	protected abstract BinaryPubSubRedisOperations createBinaryPubSubRedisOperations();

	protected abstract DatabaseRedisOperations createDatabaseRedisOperations();

	protected abstract BinaryDatabaseRedisOperations createBinaryDatabaseRedisOperations();

	protected abstract LuaRedisOperations createLuaRedisOperations();

	protected abstract BinaryLuaRedisOperations createBinaryLuaRedisOperations();

	protected abstract PersistenceRedisOperations createPersistenceRedisOperations();

	protected abstract BinaryPersistenceRedisOperations createBinaryPersistenceRedisOperations();

	protected abstract ReplicationRedisOperations createReplicationRedisOperations();

	protected abstract BinaryReplicationRedisOperations createBinaryReplicationRedisOperations();

	protected abstract ClientAndServerRedisOperations createClientAndServerRedisOperations();

	protected abstract BinaryClientAndServerRedisOperations createBinaryClientAndServerRedisOperations();

	protected abstract ConfigureRedisOperations createConfigureRedisOperations();

	protected abstract BinaryConfigureRedisOperations createBinaryConfigureRedisOperations();

	protected abstract InternalRedisOperations createInternalRedisOperations();

	protected abstract BinaryInternalRedisOperations createBinaryInternalRedisOperations();

	protected abstract DebugRedisOperations createDebugRedisOperations();

	protected abstract BinaryDebugRedisOperations createBinaryDebugRedisOperations();

	protected void close(){
		try{
			connection.close();
		}catch(IOException e){
			logger.error("RedisConnection close error: {}", e.getMessage());
		}
	}

}
