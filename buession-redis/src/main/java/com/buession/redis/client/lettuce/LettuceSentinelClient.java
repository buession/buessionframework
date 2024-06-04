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
package com.buession.redis.client.lettuce;

import com.buession.redis.client.RedisSentinelClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.lettuce.LettuceConnection;
import com.buession.redis.client.lettuce.operations.LettuceSentinelBitMapOperations;
import com.buession.redis.client.lettuce.operations.LettuceSentinelClusterOperations;
import com.buession.redis.client.lettuce.operations.LettuceSentinelConnectionOperations;
import com.buession.redis.client.lettuce.operations.LettuceSentinelGeoOperations;
import com.buession.redis.client.lettuce.operations.LettuceSentinelHashOperations;
import com.buession.redis.client.lettuce.operations.LettuceSentinelHyperLogLogOperations;
import com.buession.redis.client.lettuce.operations.LettuceSentinelKeyOperations;
import com.buession.redis.client.lettuce.operations.LettuceSentinelListOperations;
import com.buession.redis.client.lettuce.operations.LettuceSentinelPubSubOperations;
import com.buession.redis.client.lettuce.operations.LettuceSentinelScriptingOperations;
import com.buession.redis.client.lettuce.operations.LettuceSentinelServerOperations;
import com.buession.redis.client.lettuce.operations.LettuceSentinelSetOperations;
import com.buession.redis.client.lettuce.operations.LettuceSentinelSortedSetOperations;
import com.buession.redis.client.lettuce.operations.LettuceSentinelStreamOperations;
import com.buession.redis.client.lettuce.operations.LettuceSentinelStringOperations;
import com.buession.redis.client.lettuce.operations.LettuceSentinelTransactionOperations;
import com.buession.redis.client.operations.*;

/**
 * Lettuce 哨兵模式客户端
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceSentinelClient extends AbstractLettuceRedisClient implements RedisSentinelClient {

	private LettuceConnection connection;

	/**
	 * 构造函数
	 */
	public LettuceSentinelClient() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param connection
	 * 		Lettuce Redis 单机连接对象 {@link LettuceConnection}
	 */
	public LettuceSentinelClient(final LettuceConnection connection) {
		super(connection);
	}

	@Override
	public LettuceConnection getConnection() {
		return connection;
	}

	@Override
	public void setConnection(RedisConnection connection) {
		this.connection = (LettuceConnection) connection;
	}

	@Override
	public BitMapOperations bitMapOperations() {
		if(bitMapOperations == null){
			bitMapOperations = new LettuceSentinelBitMapOperations(this);
		}

		return bitMapOperations;
	}

	@Override
	public ClusterOperations clusterOperations() {
		if(clusterOperations == null){
			clusterOperations = new LettuceSentinelClusterOperations(this);
		}

		return clusterOperations;
	}

	@Override
	public ConnectionOperations connectionOperations() {
		if(connectionOperations == null){
			connectionOperations = new LettuceSentinelConnectionOperations(this);
		}

		return connectionOperations;
	}

	@Override
	public GeoOperations geoOperations() {
		if(geoOperations == null){
			geoOperations = new LettuceSentinelGeoOperations(this);
		}

		return geoOperations;
	}

	@Override
	public HashOperations hashOperations() {
		if(hashOperations == null){
			hashOperations = new LettuceSentinelHashOperations(this);
		}

		return hashOperations;
	}

	@Override
	public HyperLogLogOperations hyperLogLogOperations() {
		if(hyperLogLogOperations == null){
			hyperLogLogOperations = new LettuceSentinelHyperLogLogOperations(this);
		}

		return hyperLogLogOperations;
	}

	@Override
	public KeyOperations keyOperations() {
		if(keyOperations == null){
			keyOperations = new LettuceSentinelKeyOperations(this);
		}

		return keyOperations;
	}

	@Override
	public ListOperations listOperations() {
		if(listOperations == null){
			listOperations = new LettuceSentinelListOperations(this);
		}

		return listOperations;
	}

	@Override
	public PubSubOperations pubSubOperations() {
		if(pubSubOperations == null){
			pubSubOperations = new LettuceSentinelPubSubOperations(this);
		}

		return pubSubOperations;
	}

	@Override
	public ScriptingOperations scriptingOperations() {
		if(scriptingOperations == null){
			scriptingOperations = new LettuceSentinelScriptingOperations(this);
		}

		return scriptingOperations;
	}

	@Override
	public ServerOperations serverOperations() {
		if(serverOperations == null){
			serverOperations = new LettuceSentinelServerOperations(this);
		}

		return serverOperations;
	}

	@Override
	public SetOperations setOperations() {
		if(setOperations == null){
			setOperations = new LettuceSentinelSetOperations(this);
		}

		return setOperations;
	}

	@Override
	public SortedSetOperations sortedSetOperations() {
		if(sortedSetOperations == null){
			sortedSetOperations = new LettuceSentinelSortedSetOperations(this);
		}

		return sortedSetOperations;
	}

	@Override
	public StreamOperations streamOperations() {
		if(streamOperations == null){
			streamOperations = new LettuceSentinelStreamOperations(this);
		}

		return streamOperations;
	}

	@Override
	public StringOperations stringOperations() {
		if(stringOperations == null){
			stringOperations = new LettuceSentinelStringOperations(this);
		}

		return stringOperations;
	}

	@Override
	public TransactionOperations transactionOperations() {
		if(transactionOperations == null){
			transactionOperations = new LettuceSentinelTransactionOperations(this);
		}

		return transactionOperations;
	}

}