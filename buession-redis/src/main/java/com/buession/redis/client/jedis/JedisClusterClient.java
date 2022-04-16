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

import com.buession.redis.client.RedisClusterClient;
import com.buession.redis.client.connection.jedis.JedisClusterConnection;
import com.buession.redis.client.jedis.operations.JedisClusterClusterOperations;
import com.buession.redis.client.jedis.operations.JedisClusterConnectionOperations;
import com.buession.redis.client.jedis.operations.JedisClusterGeoOperations;
import com.buession.redis.client.jedis.operations.JedisClusterHashOperations;
import com.buession.redis.client.jedis.operations.JedisClusterHyperLogLogOperations;
import com.buession.redis.client.jedis.operations.JedisClusterKeyOperations;
import com.buession.redis.client.jedis.operations.JedisClusterListOperations;
import com.buession.redis.client.jedis.operations.JedisClusterPubSubOperations;
import com.buession.redis.client.jedis.operations.JedisClusterScriptingOperations;
import com.buession.redis.client.jedis.operations.JedisClusterServerOperations;
import com.buession.redis.client.jedis.operations.JedisClusterSetOperations;
import com.buession.redis.client.jedis.operations.JedisClusterSortedSetOperations;
import com.buession.redis.client.jedis.operations.JedisClusterStringOperations;
import com.buession.redis.client.jedis.operations.JedisClusterTransactionOperations;

/**
 * jedis 集群模式客户端
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class JedisClusterClient extends AbstractJedisRedisClient implements RedisClusterClient {

	public JedisClusterClient(){
		super();
	}

	public JedisClusterClient(final JedisClusterConnection connection){
		super(connection);
	}

	@Override
	public JedisClusterClusterOperations clusterOperations(){
		return new JedisClusterClusterOperations(this);
	}

	@Override
	public JedisClusterConnectionOperations connectionOperations(){
		return new JedisClusterConnectionOperations(this);
	}

	@Override
	public JedisClusterGeoOperations geoOperations(){
		return new JedisClusterGeoOperations(this);
	}

	@Override
	public JedisClusterHashOperations hashOperations(){
		return new JedisClusterHashOperations(this);
	}

	@Override
	public JedisClusterHyperLogLogOperations hyperLogLogOperations(){
		return new JedisClusterHyperLogLogOperations(this);
	}

	@Override
	public JedisClusterKeyOperations keyOperations(){
		return new JedisClusterKeyOperations(this);
	}

	@Override
	public JedisClusterListOperations listOperations(){
		return new JedisClusterListOperations(this);
	}

	@Override
	public JedisClusterPubSubOperations pubSubOperations(){
		return new JedisClusterPubSubOperations(this);
	}

	@Override
	public JedisClusterScriptingOperations scriptingOperations(){
		return new JedisClusterScriptingOperations(this);
	}

	@Override
	public JedisClusterServerOperations serverOperations(){
		return new JedisClusterServerOperations(this);
	}

	@Override
	public JedisClusterSetOperations setOperations(){
		return new JedisClusterSetOperations(this);
	}

	@Override
	public JedisClusterSortedSetOperations sortedSetOperations(){
		return new JedisClusterSortedSetOperations(this);
	}

	@Override
	public JedisClusterStringOperations stringOperations(){
		return new JedisClusterStringOperations(this);
	}

	@Override
	public JedisClusterTransactionOperations transactionOperations(){
		return new JedisClusterTransactionOperations(this);
	}

}