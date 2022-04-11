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
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.connection.jedis.JedisRedisConnection;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.ClusterOperations;
import com.buession.redis.core.ClusterResetOption;
import com.buession.redis.core.ClusterSetSlotOption;
import com.buession.redis.core.RedisClusterServer;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;

/**
 * Jedis 集群命令操作抽象类
 *
 * @param <C>
 * 		连接对象
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public abstract class AbstractClusterOperations<C extends JedisRedisConnection> extends AbstractJedisRedisOperations<C>
		implements ClusterOperations<C> {

	public AbstractClusterOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Status clusterForget(final byte[] nodeId){
		return clusterForget(SafeEncoder.encode(nodeId));
	}

	@Override
	public long clusterKeySlot(final byte[] key){
		return clusterKeySlot(SafeEncoder.encode(key));
	}

	@Override
	public Status clusterMeet(final byte[] ip, final int port){
		return clusterMeet(SafeEncoder.encode(ip), port);
	}

	@Override
	public List<RedisClusterServer> clusterSlaves(final byte[] nodeId){
		return clusterSlaves(SafeEncoder.encode(nodeId));
	}

	@Override
	public List<RedisClusterServer> clusterReplicas(final byte[] nodeId){
		return clusterReplicas(SafeEncoder.encode(nodeId));
	}

	@Override
	public Status clusterReplicate(final byte[] nodeId){
		return clusterReplicate(SafeEncoder.encode(nodeId));
	}

	@Override
	public Status clusterReset(){
		return clusterReset(ClusterResetOption.SOFT);
	}

	@Override
	public Status clusterSetSlot(final int slot, final ClusterSetSlotOption setSlotOption, final byte[] nodeId){
		return clusterSetSlot(slot, setSlotOption, SafeEncoder.encode(nodeId));
	}

}
