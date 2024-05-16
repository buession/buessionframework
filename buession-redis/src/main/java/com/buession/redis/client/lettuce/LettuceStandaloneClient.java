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
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.lettuce.LettuceConnection;
import com.buession.redis.client.lettuce.operations.*;

/**
 * Lettuce 单机模式客户端
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceStandaloneClient extends AbstractLettuceRedisClient implements RedisStandaloneClient {

	private LettuceConnection connection;

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
	public LettuceConnection getConnection() {
		return connection;
	}

	@Override
	public void setConnection(RedisConnection connection) {
		this.connection = (LettuceConnection) connection;
	}

	@Override
	public LettuceBitMapOperations bitMapOperations() {
		return new LettuceBitMapOperations(this);
	}

	@Override
	public LettuceClusterOperations clusterOperations() {
		return new LettuceClusterOperations(this);
	}

	@Override
	public LettuceConnectionOperations connectionOperations() {
		return new LettuceConnectionOperations(this);
	}

	@Override
	public LettuceGeoOperations geoOperations() {
		return new LettuceGeoOperations(this);
	}

	@Override
	public LettuceHashOperations hashOperations() {
		return new LettuceHashOperations(this);
	}

	@Override
	public LettuceHyperLogLogOperations hyperLogLogOperations() {
		return new LettuceHyperLogLogOperations(this);
	}

	@Override
	public LettuceKeyOperations keyOperations() {
		return new LettuceKeyOperations(this);
	}

	@Override
	public LettuceListOperations listOperations() {
		return new LettuceListOperations(this);
	}

	@Override
	public LettucePubSubOperations pubSubOperations() {
		return new LettucePubSubOperations(this);
	}

	@Override
	public LettuceScriptingOperations scriptingOperations() {
		return new LettuceScriptingOperations(this);
	}

	@Override
	public LettuceServerOperations serverOperations() {
		return new LettuceServerOperations(this);
	}

	@Override
	public LettuceSetOperations setOperations() {
		return new LettuceSetOperations(this);
	}

	@Override
	public LettuceSortedSetOperations sortedSetOperations() {
		return new LettuceSortedSetOperations(this);
	}

	@Override
	public LettuceStreamOperations streamOperations() {
		return new LettuceStreamOperations(this);
	}

	@Override
	public LettuceStringOperations stringOperations() {
		return new LettuceStringOperations(this);
	}

	@Override
	public LettuceTransactionOperations transactionOperations() {
		return new LettuceTransactionOperations(this);
	}

}