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

import com.buession.redis.client.RedisSentinelClient;
import com.buession.redis.client.connection.jedis.JedisSentinelConnection;
import com.buession.redis.client.jedis.operations.JedisSentinelBitMapOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelClusterOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelConnectionOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelGeoOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelHashOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelHyperLogLogOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelKeyOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelListOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelPubSubOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelScriptingOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelServerOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelSetOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelSortedSetOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelStreamOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelStringOperations;
import com.buession.redis.client.jedis.operations.JedisSentinelTransactionOperations;
import com.buession.redis.client.operations.*;

/**
 * jedis 哨兵模式客户端
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class JedisSentinelClient extends AbstractJedisRedisClient<JedisSentinelConnection>
		implements RedisSentinelClient {

	/**
	 * 构造函数
	 */
	public JedisSentinelClient() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param connection
	 * 		Jedis Redis 哨兵连接对象 {@link JedisSentinelConnection}
	 */
	public JedisSentinelClient(final JedisSentinelConnection connection) {
		super(connection);
	}

	@Override
	public BitMapOperations bitMapOperations() {
		if(bitMapOperations == null){
			bitMapOperations = new JedisSentinelBitMapOperations(this);
		}

		return bitMapOperations;
	}

	@Override
	public ClusterOperations clusterOperations() {
		if(clusterOperations == null){
			clusterOperations = new JedisSentinelClusterOperations(this);
		}

		return clusterOperations;
	}

	@Override
	public ConnectionOperations connectionOperations() {
		if(connectionOperations == null){
			connectionOperations = new JedisSentinelConnectionOperations(this);
		}

		return connectionOperations;
	}

	@Override
	public GeoOperations geoOperations() {
		if(geoOperations == null){
			geoOperations = new JedisSentinelGeoOperations(this);
		}

		return geoOperations;
	}

	@Override
	public HashOperations hashOperations() {
		if(hashOperations == null){
			hashOperations = new JedisSentinelHashOperations(this);
		}

		return hashOperations;
	}

	@Override
	public HyperLogLogOperations hyperLogLogOperations() {
		if(hyperLogLogOperations == null){
			hyperLogLogOperations = new JedisSentinelHyperLogLogOperations(this);
		}

		return hyperLogLogOperations;
	}

	@Override
	public KeyOperations keyOperations() {
		if(keyOperations == null){
			keyOperations = new JedisSentinelKeyOperations(this);
		}

		return keyOperations;
	}

	@Override
	public ListOperations listOperations() {
		if(listOperations == null){
			listOperations = new JedisSentinelListOperations(this);
		}

		return listOperations;
	}

	@Override
	public PubSubOperations pubSubOperations() {
		if(pubSubOperations == null){
			pubSubOperations = new JedisSentinelPubSubOperations(this);
		}

		return pubSubOperations;
	}

	@Override
	public ScriptingOperations scriptingOperations() {
		if(scriptingOperations == null){
			scriptingOperations = new JedisSentinelScriptingOperations(this);
		}

		return scriptingOperations;
	}

	@Override
	public ServerOperations serverOperations() {
		if(serverOperations == null){
			serverOperations = new JedisSentinelServerOperations(this);
		}

		return serverOperations;
	}

	@Override
	public SetOperations setOperations() {
		if(setOperations == null){
			setOperations = new JedisSentinelSetOperations(this);
		}

		return setOperations;
	}

	@Override
	public SortedSetOperations sortedSetOperations() {
		if(sortedSetOperations == null){
			sortedSetOperations = new JedisSentinelSortedSetOperations(this);
		}

		return sortedSetOperations;
	}

	@Override
	public StreamOperations streamOperations() {
		if(streamOperations == null){
			streamOperations = new JedisSentinelStreamOperations(this);
		}

		return streamOperations;
	}

	@Override
	public StringOperations stringOperations() {
		if(stringOperations == null){
			stringOperations = new JedisSentinelStringOperations(this);
		}

		return stringOperations;
	}

	@Override
	public TransactionOperations transactionOperations() {
		if(transactionOperations == null){
			transactionOperations = new JedisSentinelTransactionOperations(this);
		}

		return transactionOperations;
	}

}