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

import com.buession.redis.client.AbstractRedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.jedis.operations.DefaultJedisBinaryBitMapRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinaryClientAndServerRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinaryConfigureRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinaryDatabaseRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinaryDebugRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinaryGeoRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinaryHashRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinaryHyperLogLogRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinaryInternalRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinaryKeyRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinaryListRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinaryLuaRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinaryPersistenceRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinaryPubSubRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinaryReplicationRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinarySetRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinarySortedSetRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinaryStringRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBinaryTransactionRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisBitMapRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisClientAndServerRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisConfigureRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisDatabaseRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisDebugRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisGeoRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisHashRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisHyperLogLogRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisInternalRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisKeyRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisListRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisLuaRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisPersistenceRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisPubSubRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisReplicationRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisSetRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisSortedSetRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisStringRedisOperations;
import com.buession.redis.client.jedis.operations.DefaultJedisTransactionRedisOperations;
import redis.clients.jedis.commands.JedisCommands;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisClient<C extends JedisCommands> extends AbstractRedisClient implements JedisRedisClient<C> {

	public AbstractJedisRedisClient(){
		super();
	}

	public AbstractJedisRedisClient(RedisConnection connection){
		super(connection);
	}

	@Override
	protected DefaultJedisKeyRedisOperations createKeyRedisOperations(){
		return new DefaultJedisKeyRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinaryKeyRedisOperations createBinaryKeyRedisOperations(){
		return new DefaultJedisBinaryKeyRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisStringRedisOperations createStringRedisOperations(){
		return new DefaultJedisStringRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinaryStringRedisOperations createBinaryStringRedisOperations(){
		return new DefaultJedisBinaryStringRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisHashRedisOperations createHashRedisOperations(){
		return new DefaultJedisHashRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinaryHashRedisOperations createBinaryHashRedisOperations(){
		return new DefaultJedisBinaryHashRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisListRedisOperations createListRedisOperations(){
		return new DefaultJedisListRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinaryListRedisOperations createBinaryListRedisOperations(){
		return new DefaultJedisBinaryListRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisSetRedisOperations createSetRedisOperations(){
		return new DefaultJedisSetRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinarySetRedisOperations createBinarySetRedisOperations(){
		return new DefaultJedisBinarySetRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisSortedSetRedisOperations createSortedSetRedisOperations(){
		return new DefaultJedisSortedSetRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinarySortedSetRedisOperations createBinarySortedSetRedisOperations(){
		return new DefaultJedisBinarySortedSetRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisHyperLogLogRedisOperations createHyperLogLogRedisOperations(){
		return new DefaultJedisHyperLogLogRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinaryHyperLogLogRedisOperations createBinaryHyperLogLogRedisOperations(){
		return new DefaultJedisBinaryHyperLogLogRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisGeoRedisOperations createGeoRedisOperations(){
		return new DefaultJedisGeoRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinaryGeoRedisOperations createBinaryGeoRedisOperations(){
		return new DefaultJedisBinaryGeoRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBitMapRedisOperations createBitMapRedisOperations(){
		return new DefaultJedisBitMapRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinaryBitMapRedisOperations createBinaryBitMapRedisOperations(){
		return new DefaultJedisBinaryBitMapRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisTransactionRedisOperations createTransactionRedisOperations(){
		return new DefaultJedisTransactionRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinaryTransactionRedisOperations createBinaryTransactionRedisOperations(){
		return new DefaultJedisBinaryTransactionRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisPubSubRedisOperations createPubSubRedisOperations(){
		return new DefaultJedisPubSubRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinaryPubSubRedisOperations createBinaryPubSubRedisOperations(){
		return new DefaultJedisBinaryPubSubRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisDatabaseRedisOperations createDatabaseRedisOperations(){
		return new DefaultJedisDatabaseRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinaryDatabaseRedisOperations createBinaryDatabaseRedisOperations(){
		return new DefaultJedisBinaryDatabaseRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisLuaRedisOperations createLuaRedisOperations(){
		return new DefaultJedisLuaRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinaryLuaRedisOperations createBinaryLuaRedisOperations(){
		return new DefaultJedisBinaryLuaRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisPersistenceRedisOperations createPersistenceRedisOperations(){
		return new DefaultJedisPersistenceRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinaryPersistenceRedisOperations createBinaryPersistenceRedisOperations(){
		return new DefaultJedisBinaryPersistenceRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisReplicationRedisOperations createReplicationRedisOperations(){
		return new DefaultJedisReplicationRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinaryReplicationRedisOperations createBinaryReplicationRedisOperations(){
		return new DefaultJedisBinaryReplicationRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisClientAndServerRedisOperations createClientAndServerRedisOperations(){
		return new DefaultJedisClientAndServerRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinaryClientAndServerRedisOperations createBinaryClientAndServerRedisOperations(){
		return new DefaultJedisBinaryClientAndServerRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisConfigureRedisOperations createConfigureRedisOperations(){
		return new DefaultJedisConfigureRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinaryConfigureRedisOperations createBinaryConfigureRedisOperations(){
		return new DefaultJedisBinaryConfigureRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisInternalRedisOperations createInternalRedisOperations(){
		return new DefaultJedisInternalRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinaryInternalRedisOperations createBinaryInternalRedisOperations(){
		return new DefaultJedisBinaryInternalRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisDebugRedisOperations createDebugRedisOperations(){
		return new DefaultJedisDebugRedisOperations<>(this);
	}

	@Override
	protected DefaultJedisBinaryDebugRedisOperations createBinaryDebugRedisOperations(){
		return new DefaultJedisBinaryDebugRedisOperations<>(this);
	}

}