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

import com.buession.redis.client.RedisStandaloneClient;
import com.buession.redis.client.connection.lettuce.LettuceConnection;
import com.buession.redis.client.lettuce.operations.LettuceAclOperations;
import com.buession.redis.client.lettuce.operations.LettuceBitMapOperations;
import com.buession.redis.client.lettuce.operations.LettuceClusterOperations;
import com.buession.redis.client.lettuce.operations.LettuceConnectionOperations;
import com.buession.redis.client.lettuce.operations.LettuceGenericOperations;
import com.buession.redis.client.lettuce.operations.LettuceGeoOperations;
import com.buession.redis.client.lettuce.operations.LettuceHashOperations;
import com.buession.redis.client.lettuce.operations.LettuceHyperLogLogOperations;
import com.buession.redis.client.lettuce.operations.LettuceKeyOperations;
import com.buession.redis.client.lettuce.operations.LettuceListOperations;
import com.buession.redis.client.lettuce.operations.LettucePubSubOperations;
import com.buession.redis.client.lettuce.operations.LettuceScriptingOperations;
import com.buession.redis.client.lettuce.operations.LettuceServerOperations;
import com.buession.redis.client.lettuce.operations.LettuceSetOperations;
import com.buession.redis.client.lettuce.operations.LettuceSortedSetOperations;
import com.buession.redis.client.lettuce.operations.LettuceStreamOperations;
import com.buession.redis.client.lettuce.operations.LettuceStringOperations;
import com.buession.redis.client.lettuce.operations.LettuceTransactionOperations;
import com.buession.redis.client.operations.*;

/**
 * Lettuce 单机模式客户端
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceStandaloneClient extends AbstractLettuceRedisClient<LettuceConnection>
		implements RedisStandaloneClient {

	/**
	 * 构造函数
	 */
	public LettuceStandaloneClient() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param connection
	 * 		Lettuce Redis 单机连接对象 {@link LettuceConnection}
	 */
	public LettuceStandaloneClient(final LettuceConnection connection) {
		super(connection);
	}

	@Override
	public AclOperations aclOperations() {
		if(aclOperations == null){
			aclOperations = new LettuceAclOperations(this);
		}

		return aclOperations;
	}

	@Override
	public BitMapOperations bitMapOperations() {
		if(bitMapOperations == null){
			bitMapOperations = new LettuceBitMapOperations(this);
		}

		return bitMapOperations;
	}

	@Override
	public ClusterOperations clusterOperations() {
		if(clusterOperations == null){
			clusterOperations = new LettuceClusterOperations(this);
		}

		return clusterOperations;
	}

	@Override
	public ConnectionOperations connectionOperations() {
		if(connectionOperations == null){
			connectionOperations = new LettuceConnectionOperations(this);
		}

		return connectionOperations;
	}

	@Override
	public GenericOperations genericOperations() {
		if(genericOperations == null){
			genericOperations = new LettuceGenericOperations(this);
		}

		return genericOperations;
	}

	@Override
	public GeoOperations geoOperations() {
		if(geoOperations == null){
			geoOperations = new LettuceGeoOperations(this);
		}

		return geoOperations;
	}

	@Override
	public HashOperations hashOperations() {
		if(hashOperations == null){
			hashOperations = new LettuceHashOperations(this);
		}

		return hashOperations;
	}

	@Override
	public HyperLogLogOperations hyperLogLogOperations() {
		if(hyperLogLogOperations == null){
			hyperLogLogOperations = new LettuceHyperLogLogOperations(this);
		}

		return hyperLogLogOperations;
	}

	@Override
	public KeyOperations keyOperations() {
		if(keyOperations == null){
			keyOperations = new LettuceKeyOperations(this);
		}

		return keyOperations;
	}

	@Override
	public ListOperations listOperations() {
		if(listOperations == null){
			listOperations = new LettuceListOperations(this);
		}

		return listOperations;
	}

	@Override
	public PubSubOperations pubSubOperations() {
		if(pubSubOperations == null){
			pubSubOperations = new LettucePubSubOperations(this);
		}

		return pubSubOperations;
	}

	@Override
	public ScriptingOperations scriptingOperations() {
		if(scriptingOperations == null){
			scriptingOperations = new LettuceScriptingOperations(this);
		}

		return scriptingOperations;
	}

	@Override
	public ServerOperations serverOperations() {
		if(serverOperations == null){
			serverOperations = new LettuceServerOperations(this);
		}

		return serverOperations;
	}

	@Override
	public SetOperations setOperations() {
		if(setOperations == null){
			setOperations = new LettuceSetOperations(this);
		}

		return setOperations;
	}

	@Override
	public SortedSetOperations sortedSetOperations() {
		if(sortedSetOperations == null){
			sortedSetOperations = new LettuceSortedSetOperations(this);
		}

		return sortedSetOperations;
	}

	@Override
	public StreamOperations streamOperations() {
		if(streamOperations == null){
			streamOperations = new LettuceStreamOperations(this);
		}

		return streamOperations;
	}

	@Override
	public StringOperations stringOperations() {
		if(stringOperations == null){
			stringOperations = new LettuceStringOperations(this);
		}

		return stringOperations;
	}

	@Override
	public TransactionOperations transactionOperations() {
		if(transactionOperations == null){
			transactionOperations = new LettuceTransactionOperations(this);
		}

		return transactionOperations;
	}

}