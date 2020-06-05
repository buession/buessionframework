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
import com.buession.redis.core.Type;
import com.buession.redis.exception.RedisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

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
		return execute(keyOperations, (KeyRedisOperations ops)->ops.exists(key));
	}

	@Override
	public boolean exists(final byte[] key){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.exists(key));
	}

	@Override
	public Type type(final String key){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.type(key));
	}

	@Override
	public Type type(final byte[] key){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.type(key));
	}

	@Override
	public Status expire(final String key, final int lifetime){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.expire(key, lifetime));
	}

	@Override
	public Status expire(final byte[] key, final int lifetime){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.expire(key, lifetime));
	}

	@Override
	public Status expireAt(final String key, final long unixTimestamp){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.expireAt(key, unixTimestamp));
	}

	@Override
	public Status expireAt(final byte[] key, final long unixTimestamp){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.expireAt(key, unixTimestamp));
	}

	@Override
	public Status pExpire(final String key, final int lifetime){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.pExpire(key, lifetime));
	}

	@Override
	public Status pExpire(final byte[] key, final int lifetime){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.pExpire(key, lifetime));
	}

	@Override
	public Status pExpireAt(final String key, final long unixTimestamp){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.pExpireAt(key, unixTimestamp));
	}

	@Override
	public Status pExpireAt(final byte[] key, final long unixTimestamp){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.pExpireAt(key, unixTimestamp));
	}

	@Override
	public Long ttl(final String key){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.ttl(key));
	}

	@Override
	public Long ttl(final byte[] key){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.ttl(key));
	}

	@Override
	public Long pTtl(final String key){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.pTtl(key));
	}

	@Override
	public Long pTtl(final byte[] key){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.pTtl(key));
	}

	@Override
	public Status persist(final String key){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.persist(key));
	}

	@Override
	public Status persist(final byte[] key){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.persist(key));
	}

	@Override
	public List<String> sort(final String key){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.sort(key));
	}

	@Override
	public List<byte[]> sort(final byte[] key){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.sort(key));
	}

	@Override
	public List<String> sort(final String key, final SortArgument sortArgument){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.sort(key, sortArgument));
	}

	@Override
	public List<byte[]> sort(final byte[] key, final SortArgument sortArgument){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.sort(key, sortArgument));
	}

	@Override
	public byte[] dump(final String key){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.dump(key));
	}

	@Override
	public byte[] dump(final byte[] key){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.dump(key));
	}

	@Override
	public Status restore(final String key, final String serializedValue, final int ttl){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.restore(key, serializedValue, ttl));
	}

	@Override
	public Status restore(final byte[] key, final byte[] serializedValue, final int ttl){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.restore(key, serializedValue, ttl));
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.migrate(key, host, port, db, timeout));
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.migrate(key, host, port, db, timeout));
	}

	@Override
	public Status migrate(final String key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation migrateOperation){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.migrate(key, host, port, db, timeout,
				migrateOperation));
	}

	@Override
	public Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation migrateOperation){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.migrate(key, host, port, db, timeout,
				migrateOperation));
	}

	@Override
	public Long del(final String... keys){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.del(keys));
	}

	@Override
	public Long del(final byte[]... keys){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.del(keys));
	}

	@Override
	public Status move(final String key, final int db){
		return execute(keyOperations, (KeyRedisOperations ops)->ops.move(key, db));
	}

	@Override
	public Status move(final byte[] key, final int db){
		return execute(binaryKeyOperations, (BinaryKeyRedisOperations ops)->ops.move(key, db));
	}

	@Override
	public Status set(final String key, final String value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.set(key, value));
	}

	@Override
	public Status set(final byte[] key, final byte[] value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.set(key, value));
	}

	@Override
	public Status set(final String key, final String value, final SetArgument setArgument){
		return execute(stringOperations, (StringRedisOperations ops)->ops.set(key, value, setArgument));
	}

	@Override
	public Status set(final byte[] key, final byte[] value, final SetArgument setArgument){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.set(key, value, setArgument));
	}

	@Override
	public Status setEx(final String key, final String value, final int lifetime){
		return execute(stringOperations, (StringRedisOperations ops)->ops.setEx(key, value, lifetime));
	}

	@Override
	public Status setEx(final byte[] key, final byte[] value, final int lifetime){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.setEx(key, value, lifetime));
	}

	@Override
	public Status pSetEx(final String key, final String value, final int lifetime){
		return execute(stringOperations, (StringRedisOperations ops)->ops.pSetEx(key, value, lifetime));
	}

	@Override
	public Status pSetEx(final byte[] key, final byte[] value, final int lifetime){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.pSetEx(key, value, lifetime));
	}

	@Override
	public Status setNx(final String key, final String value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.setNx(key, value));
	}

	@Override
	public Status setNx(final byte[] key, final byte[] value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.setNx(key, value));
	}

	@Override
	public Long append(final String key, final String value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.append(key, value));
	}

	@Override
	public Long append(final byte[] key, final byte[] value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.append(key, value));
	}

	@Override
	public String get(final String key){
		return execute(stringOperations, (StringRedisOperations ops)->ops.get(key));
	}

	@Override
	public byte[] get(final byte[] key){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.get(key));
	}

	@Override
	public String getSet(final String key, final String value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.getSet(key, value));
	}

	@Override
	public byte[] getSet(final byte[] key, final byte[] value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.getSet(key, value));
	}


	@Override
	public Long incr(final String key){
		return execute(stringOperations, (StringRedisOperations ops)->ops.incr(key));
	}

	@Override
	public Long incr(final byte[] key){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.incr(key));
	}

	@Override
	public Long incrBy(final String key, final int value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.incrBy(key, value));
	}

	@Override
	public Long incrBy(final byte[] key, final int value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.incrBy(key, value));
	}

	@Override
	public Long incrBy(final String key, final long value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.incrBy(key, value));
	}

	@Override
	public Long incrBy(final byte[] key, final long value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.incrBy(key, value));
	}

	@Override
	public Double incrByFloat(final String key, final float value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.incrByFloat(key, value));
	}

	@Override
	public Double incrByFloat(final byte[] key, final float value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.incrByFloat(key, value));
	}

	@Override
	public Double incrByFloat(final String key, final double value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.incrByFloat(key, value));
	}

	@Override
	public Double incrByFloat(final byte[] key, final double value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.incrByFloat(key, value));
	}

	@Override
	public Long decr(final String key){
		return execute(stringOperations, (StringRedisOperations ops)->ops.decr(key));
	}

	@Override
	public Long decr(final byte[] key){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.decr(key));
	}

	@Override
	public Long decrBy(final String key, final int value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.decrBy(key, value));
	}

	@Override
	public Long decrBy(final byte[] key, final int value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.decrBy(key, value));
	}

	@Override
	public Long decrBy(final String key, final long value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.decrBy(key, value));
	}

	@Override
	public Long decrBy(final byte[] key, final long value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.decrBy(key, value));
	}

	@Override
	public Long setRange(final String key, final int offset, final String value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.setRange(key, offset, value));
	}

	@Override
	public Long setRange(final byte[] key, final int offset, final byte[] value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.setRange(key, offset, value));
	}

	@Override
	public Long setRange(final String key, final long offset, final String value){
		return execute(stringOperations, (StringRedisOperations ops)->ops.setRange(key, offset, value));
	}

	@Override
	public Long setRange(final byte[] key, final long offset, final byte[] value){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.setRange(key, offset, value));
	}

	@Override
	public String getRange(final String key, final int start, final int end){
		return execute(stringOperations, (StringRedisOperations ops)->ops.getRange(key, start, end));
	}

	@Override
	public byte[] getRange(final byte[] key, final int start, final int end){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.getRange(key, start, end));
	}

	@Override
	public String getRange(final String key, final long start, final long end){
		return execute(stringOperations, (StringRedisOperations ops)->ops.getRange(key, start, end));
	}

	@Override
	public byte[] getRange(final byte[] key, final long start, final long end){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.getRange(key, start, end));
	}

	@Override
	public String substr(final String key, final int start, final int end){
		return execute(stringOperations, (StringRedisOperations ops)->ops.substr(key, start, end));
	}

	@Override
	public byte[] substr(final byte[] key, final int start, final int end){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.substr(key, start, end));
	}

	@Override
	public String substr(final String key, final long start, final long end){
		return execute(stringOperations, (StringRedisOperations ops)->ops.substr(key, start, end));
	}

	@Override
	public byte[] substr(final byte[] key, final long start, final long end){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.substr(key, start, end));
	}

	@Override
	public Long strlen(final String key){
		return execute(stringOperations, (StringRedisOperations ops)->ops.strlen(key));
	}

	@Override
	public Long strlen(final byte[] key){
		return execute(binaryStringOperations, (BinaryStringRedisOperations ops)->ops.strlen(key));
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
