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
package com.buession.redis.client.jedis;

import com.buession.redis.client.RedisStandaloneClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.jedis.operations.JedisBitMapOperations;
import com.buession.redis.client.jedis.operations.JedisClusterOperations;
import com.buession.redis.client.jedis.operations.JedisConnectionOperations;
import com.buession.redis.client.jedis.operations.JedisGeoOperations;
import com.buession.redis.client.jedis.operations.JedisHashOperations;
import com.buession.redis.client.jedis.operations.JedisHyperLogLogOperations;
import com.buession.redis.client.jedis.operations.JedisKeyOperations;
import com.buession.redis.client.jedis.operations.JedisListOperations;
import com.buession.redis.client.jedis.operations.JedisPubSubOperations;
import com.buession.redis.client.jedis.operations.JedisScriptingOperations;
import com.buession.redis.client.jedis.operations.JedisServerOperations;
import com.buession.redis.client.jedis.operations.JedisSetOperations;
import com.buession.redis.client.jedis.operations.JedisSortedSetOperations;
import com.buession.redis.client.jedis.operations.JedisStreamOperations;
import com.buession.redis.client.jedis.operations.JedisStringOperations;
import com.buession.redis.client.jedis.operations.JedisTransactionOperations;

/**
 * jedis 单机模式客户端
 *
 * @author Yong.Teng
 */
public class JedisStandaloneClient extends AbstractJedisRedisClient implements RedisStandaloneClient {

	private JedisConnection connection;

	public JedisStandaloneClient(){
		super();
	}

	public JedisStandaloneClient(final JedisConnection connection){
		super(connection);
	}

	@Override
	public JedisConnection getConnection(){
		return connection;
	}

	@Override
	public void setConnection(RedisConnection connection){
		this.connection = (JedisConnection) connection;
	}

	@Override
	public JedisBitMapOperations bitMapOperations(){
		return new JedisBitMapOperations(this);
	}

	@Override
	public JedisClusterOperations clusterOperations(){
		return new JedisClusterOperations(this);
	}

	@Override
	public JedisConnectionOperations connectionOperations(){
		return new JedisConnectionOperations(this);
	}

	@Override
	public JedisGeoOperations geoOperations(){
		return new JedisGeoOperations(this);
	}

	@Override
	public JedisHashOperations hashOperations(){
		return new JedisHashOperations(this);
	}

	@Override
	public JedisHyperLogLogOperations hyperLogLogOperations(){
		return new JedisHyperLogLogOperations(this);
	}

	@Override
	public JedisKeyOperations keyOperations(){
		return new JedisKeyOperations(this);
	}

	@Override
	public JedisListOperations listOperations(){
		return new JedisListOperations(this);
	}

	@Override
	public JedisPubSubOperations pubSubOperations(){
		return new JedisPubSubOperations(this);
	}

	@Override
	public JedisScriptingOperations scriptingOperations(){
		return new JedisScriptingOperations(this);
	}

	@Override
	public JedisServerOperations serverOperations(){
		return new JedisServerOperations(this);
	}

	@Override
	public JedisSetOperations setOperations(){
		return new JedisSetOperations(this);
	}

	@Override
	public JedisSortedSetOperations sortedSetOperations(){
		return new JedisSortedSetOperations(this);
	}

	@Override
	public JedisStreamOperations streamOperations(){
		return new JedisStreamOperations(this);
	}

	@Override
	public JedisStringOperations stringOperations(){
		return new JedisStringOperations(this);
	}

	@Override
	public JedisTransactionOperations transactionOperations(){
		return new JedisTransactionOperations(this);
	}

}