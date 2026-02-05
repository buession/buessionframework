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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis;

import com.buession.redis.client.AbstractRedisClient;
import com.buession.redis.client.connection.jedis.JedisRedisConnection;
import com.buession.redis.client.jedis.operations.*;
import com.buession.redis.client.operations.*;
import redis.clients.jedis.UnifiedJedis;

/**
 * Jedis Redis 客户端
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class JedisRedisClient extends AbstractRedisClient<JedisRedisConnection<? extends UnifiedJedis>> {

	/**
	 * 构造函数
	 */
	public JedisRedisClient() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param connection
	 * 		Jedis Redis 连接对象 {@link JedisRedisConnection}
	 */
	public JedisRedisClient(final JedisRedisConnection<? extends UnifiedJedis> connection) {
		super(connection);
	}

	@Override
	public BloomFilterOperations bloomFilterOperations() {
		if(bloomFilterOperations == null){
			bloomFilterOperations = new JedisBloomFilterOperations(this);
		}

		return bloomFilterOperations;
	}

	@Override
	public BitMapOperations bitMapOperations() {
		if(bitMapOperations == null){
			bitMapOperations = new JedisBitMapOperations(this);
		}

		return bitMapOperations;
	}

	@Override
	public CuckooFilterOperations cuckooFilterOperations() {
		if(cuckooFilterOperations == null){
			cuckooFilterOperations = new JedisCuckooFilterOperations(this);
		}

		return cuckooFilterOperations;
	}

	@Override
	public ClusterOperations clusterOperations() {
		if(clusterOperations == null){
			clusterOperations = new JedisClusterOperations(this);
		}

		return clusterOperations;
	}

	@Override
	public CountMinSketchOperations countMinSketchOperations() {
		if(countMinSketchOperations == null){
			countMinSketchOperations = new JedisCountMinSketchOperations(this);
		}

		return countMinSketchOperations;
	}

	@Override
	public ConnectionOperations connectionOperations() {
		if(connectionOperations == null){
			connectionOperations = new JedisConnectionOperations(this);
		}

		return connectionOperations;
	}

	@Override
	public GenericOperations genericOperations() {
		if(genericOperations == null){
			genericOperations = new JedisGenericOperations(this);
		}

		return genericOperations;
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
	public JsonOperations jsonOperations() {
		if(jsonOperations == null){
			hyperLogLogOperations = new JedisJsonOperations(this);
		}

		return jsonOperations;
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
