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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis;

import com.buession.redis.client.RedisSentinelClient;
import com.buession.redis.client.connection.RedisConnection;
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

/**
 * jedis 哨兵模式客户端
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class JedisSentinelClient extends AbstractJedisRedisClient implements RedisSentinelClient {

	private JedisSentinelConnection connection;

	/**
	 * 构造函数
	 */
	public JedisSentinelClient(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param connection
	 * 		Jedis Redis 哨兵连接对象 {@link JedisSentinelConnection}
	 */
	public JedisSentinelClient(final JedisSentinelConnection connection){
		super(connection);
	}

	@Override
	public JedisSentinelConnection getConnection(){
		return connection;
	}

	@Override
	public void setConnection(RedisConnection connection){
		this.connection = (JedisSentinelConnection) connection;
	}

	@Override
	public JedisSentinelBitMapOperations bitMapOperations(){
		return new JedisSentinelBitMapOperations(this);
	}

	@Override
	public JedisSentinelClusterOperations clusterOperations(){
		return new JedisSentinelClusterOperations(this);
	}

	@Override
	public JedisSentinelConnectionOperations connectionOperations(){
		return new JedisSentinelConnectionOperations(this);
	}

	@Override
	public JedisSentinelGeoOperations geoOperations(){
		return new JedisSentinelGeoOperations(this);
	}

	@Override
	public JedisSentinelHashOperations hashOperations(){
		return new JedisSentinelHashOperations(this);
	}

	@Override
	public JedisSentinelHyperLogLogOperations hyperLogLogOperations(){
		return new JedisSentinelHyperLogLogOperations(this);
	}

	@Override
	public JedisSentinelKeyOperations keyOperations(){
		return new JedisSentinelKeyOperations(this);
	}

	@Override
	public JedisSentinelListOperations listOperations(){
		return new JedisSentinelListOperations(this);
	}

	@Override
	public JedisSentinelPubSubOperations pubSubOperations(){
		return new JedisSentinelPubSubOperations(this);
	}

	@Override
	public JedisSentinelScriptingOperations scriptingOperations(){
		return new JedisSentinelScriptingOperations(this);
	}

	@Override
	public JedisSentinelServerOperations serverOperations(){
		return new JedisSentinelServerOperations(this);
	}

	@Override
	public JedisSentinelSetOperations setOperations(){
		return new JedisSentinelSetOperations(this);
	}

	@Override
	public JedisSentinelSortedSetOperations sortedSetOperations(){
		return new JedisSentinelSortedSetOperations(this);
	}

	@Override
	public JedisSentinelStreamOperations streamOperations(){
		return new JedisSentinelStreamOperations(this);
	}

	@Override
	public JedisSentinelStringOperations stringOperations(){
		return new JedisSentinelStringOperations(this);
	}

	@Override
	public JedisSentinelTransactionOperations transactionOperations(){
		return new JedisSentinelTransactionOperations(this);
	}

}