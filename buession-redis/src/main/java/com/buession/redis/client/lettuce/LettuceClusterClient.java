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

import com.buession.redis.client.RedisClusterClient;
import com.buession.redis.client.connection.lettuce.LettuceClusterConnection;
import com.buession.redis.client.lettuce.operations.LettuceClusterBitMapOperations;
import com.buession.redis.client.lettuce.operations.LettuceClusterClusterOperations;
import com.buession.redis.client.lettuce.operations.LettuceClusterConnectionOperations;
import com.buession.redis.client.lettuce.operations.LettuceClusterGeoOperations;
import com.buession.redis.client.lettuce.operations.LettuceClusterHashOperations;
import com.buession.redis.client.lettuce.operations.LettuceClusterHyperLogLogOperations;
import com.buession.redis.client.lettuce.operations.LettuceClusterKeyOperations;
import com.buession.redis.client.lettuce.operations.LettuceClusterListOperations;
import com.buession.redis.client.lettuce.operations.LettuceClusterPubSubOperations;
import com.buession.redis.client.lettuce.operations.LettuceClusterScriptingOperations;
import com.buession.redis.client.lettuce.operations.LettuceClusterServerOperations;
import com.buession.redis.client.lettuce.operations.LettuceClusterSetOperations;
import com.buession.redis.client.lettuce.operations.LettuceClusterSortedSetOperations;
import com.buession.redis.client.lettuce.operations.LettuceClusterStreamOperations;
import com.buession.redis.client.lettuce.operations.LettuceClusterStringOperations;
import com.buession.redis.client.lettuce.operations.LettuceClusterTransactionOperations;
import com.buession.redis.client.operations.*;

/**
 * Lettuce 集群模式客户端
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceClusterClient extends AbstractLettuceRedisClient<LettuceClusterConnection>
		implements RedisClusterClient {

	/**
	 * 构造函数
	 */
	public LettuceClusterClient() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param connection
	 * 		Lettuce Redis 集群连接对象 {@link LettuceClusterConnection}
	 */
	public LettuceClusterClient(final LettuceClusterConnection connection) {
		super(connection);
	}

	@Override
	public BitMapOperations bitMapOperations() {
		if(bitMapOperations == null){
			bitMapOperations = new LettuceClusterBitMapOperations(this);
		}

		return bitMapOperations;
	}

	@Override
	public ClusterOperations clusterOperations() {
		if(clusterOperations == null){
			clusterOperations = new LettuceClusterClusterOperations(this);
		}

		return clusterOperations;
	}

	@Override
	public ConnectionOperations connectionOperations() {
		if(connectionOperations == null){
			connectionOperations = new LettuceClusterConnectionOperations(this);
		}

		return connectionOperations;
	}

	@Override
	public GeoOperations geoOperations() {
		if(geoOperations == null){
			geoOperations = new LettuceClusterGeoOperations(this);
		}

		return geoOperations;
	}

	@Override
	public HashOperations hashOperations() {
		if(hashOperations == null){
			hashOperations = new LettuceClusterHashOperations(this);
		}

		return hashOperations;
	}

	@Override
	public HyperLogLogOperations hyperLogLogOperations() {
		if(hyperLogLogOperations == null){
			hyperLogLogOperations = new LettuceClusterHyperLogLogOperations(this);
		}

		return hyperLogLogOperations;
	}

	@Override
	public KeyOperations keyOperations() {
		if(keyOperations == null){
			keyOperations = new LettuceClusterKeyOperations(this);
		}

		return keyOperations;
	}

	@Override
	public ListOperations listOperations() {
		if(listOperations == null){
			listOperations = new LettuceClusterListOperations(this);
		}

		return listOperations;
	}

	@Override
	public PubSubOperations pubSubOperations() {
		if(pubSubOperations == null){
			pubSubOperations = new LettuceClusterPubSubOperations(this);
		}

		return pubSubOperations;
	}

	@Override
	public ScriptingOperations scriptingOperations() {
		if(scriptingOperations == null){
			scriptingOperations = new LettuceClusterScriptingOperations(this);
		}

		return scriptingOperations;
	}

	@Override
	public ServerOperations serverOperations() {
		if(serverOperations == null){
			serverOperations = new LettuceClusterServerOperations(this);
		}

		return serverOperations;
	}

	@Override
	public SetOperations setOperations() {
		if(setOperations == null){
			setOperations = new LettuceClusterSetOperations(this);
		}

		return setOperations;
	}

	@Override
	public SortedSetOperations sortedSetOperations() {
		if(sortedSetOperations == null){
			sortedSetOperations = new LettuceClusterSortedSetOperations(this);
		}

		return sortedSetOperations;
	}

	@Override
	public StreamOperations streamOperations() {
		if(streamOperations == null){
			streamOperations = new LettuceClusterStreamOperations(this);
		}

		return streamOperations;
	}

	@Override
	public StringOperations stringOperations() {
		if(stringOperations == null){
			stringOperations = new LettuceClusterStringOperations(this);
		}

		return stringOperations;
	}

	@Override
	public TransactionOperations transactionOperations() {
		if(transactionOperations == null){
			transactionOperations = new LettuceClusterTransactionOperations(this);
		}

		return transactionOperations;
	}

}