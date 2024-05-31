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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
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
import com.buession.redis.client.operations.*;

/**
 * Jedis 单机模式客户端
 *
 * @author Yong.Teng
 */
public class JedisStandaloneClient extends AbstractJedisRedisClient implements RedisStandaloneClient {

	private JedisConnection connection;

	/**
	 * 构造函数
	 */
	public JedisStandaloneClient() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param connection
	 * 		Jedis Redis 单机连接对象 {@link JedisConnection}
	 */
	public JedisStandaloneClient(final JedisConnection connection) {
		super(connection);
	}

	@Override
	public JedisConnection getConnection() {
		return connection;
	}

	@Override
	public void setConnection(RedisConnection connection) {
		this.connection = (JedisConnection) connection;
	}

	@Override
	public BitMapOperations bitMapOperations() {
		if(bitMapOperations == null){
			bitMapOperations = new JedisBitMapOperations(this);
		}

		return bitMapOperations;
	}

	@Override
	public ClusterOperations clusterOperations() {
		if(clusterOperations == null){
			clusterOperations = new JedisClusterOperations(this);
		}

		return clusterOperations;
	}

	@Override
	public ConnectionOperations connectionOperations() {
		if(connectionOperations == null){
			connectionOperations = new JedisConnectionOperations(this);
		}

		return connectionOperations;
	}

	@Override
	public GeoOperations geoOperations() {
		if(geoOperations == null){
			geoOperations = new JedisGeoOperations(this);
		}

		return geoOperations;
	}

	@Override
	public HashOperations hashOperations() {
		if(hashOperations == null){
			hashOperations = new JedisHashOperations(this);
		}

		return hashOperations;
	}

	@Override
	public HyperLogLogOperations hyperLogLogOperations() {
		if(hyperLogLogOperations == null){
			hyperLogLogOperations = new JedisHyperLogLogOperations(this);
		}

		return hyperLogLogOperations;
	}

	@Override
	public KeyOperations keyOperations() {
		if(keyOperations == null){
			keyOperations = new JedisKeyOperations(this);
		}

		return keyOperations;
	}

	@Override
	public ListOperations listOperations() {
		if(listOperations == null){
			listOperations = new JedisListOperations(this);
		}

		return listOperations;
	}

	@Override
	public PubSubOperations pubSubOperations() {
		if(pubSubOperations == null){
			pubSubOperations = new JedisPubSubOperations(this);
		}

		return pubSubOperations;
	}

	@Override
	public ScriptingOperations scriptingOperations() {
		if(scriptingOperations == null){
			scriptingOperations = new JedisScriptingOperations(this);
		}

		return scriptingOperations;
	}

	@Override
	public ServerOperations serverOperations() {
		if(serverOperations == null){
			serverOperations = new JedisServerOperations(this);
		}

		return serverOperations;
	}

	@Override
	public SetOperations setOperations() {
		if(setOperations == null){
			setOperations = new JedisSetOperations(this);
		}

		return setOperations;
	}

	@Override
	public SortedSetOperations sortedSetOperations() {
		if(sortedSetOperations == null){
			sortedSetOperations = new JedisSortedSetOperations(this);
		}

		return sortedSetOperations;
	}

	@Override
	public StreamOperations streamOperations() {
		if(streamOperations == null){
			streamOperations = new JedisStreamOperations(this);
		}

		return streamOperations;
	}

	@Override
	public StringOperations stringOperations() {
		if(stringOperations == null){
			stringOperations = new JedisStringOperations(this);
		}

		return stringOperations;
	}

	@Override
	public TransactionOperations transactionOperations() {
		if(transactionOperations == null){
			transactionOperations = new JedisTransactionOperations(this);
		}

		return transactionOperations;
	}

}