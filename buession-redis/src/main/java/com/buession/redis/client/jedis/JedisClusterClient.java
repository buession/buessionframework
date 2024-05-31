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

import com.buession.redis.client.RedisClusterClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.jedis.JedisClusterConnection;
import com.buession.redis.client.jedis.operations.JedisClusterBitMapOperations;
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
import com.buession.redis.client.jedis.operations.JedisClusterStreamOperations;
import com.buession.redis.client.jedis.operations.JedisClusterStringOperations;
import com.buession.redis.client.jedis.operations.JedisClusterTransactionOperations;
import com.buession.redis.client.operations.*;

/**
 * jedis 集群模式客户端
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class JedisClusterClient extends AbstractJedisRedisClient implements RedisClusterClient {

	private JedisClusterConnection connection;

	/**
	 * 构造函数
	 */
	public JedisClusterClient() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param connection
	 * 		Jedis Redis 集群连接对象 {@link JedisClusterConnection}
	 */
	public JedisClusterClient(final JedisClusterConnection connection) {
		super(connection);
	}

	@Override
	public JedisClusterConnection getConnection() {
		return connection;
	}

	@Override
	public void setConnection(RedisConnection connection) {
		this.connection = (JedisClusterConnection) connection;
	}

	@Override
	public BitMapOperations bitMapOperations() {
		if(bitMapOperations == null){
			bitMapOperations = new JedisClusterBitMapOperations(this);
		}

		return bitMapOperations;
	}

	@Override
	public ClusterOperations clusterOperations() {
		if(clusterOperations == null){
			clusterOperations = new JedisClusterClusterOperations(this);
		}

		return clusterOperations;
	}

	@Override
	public ConnectionOperations connectionOperations() {
		if(connectionOperations == null){
			connectionOperations = new JedisClusterConnectionOperations(this);
		}

		return connectionOperations;
	}

	@Override
	public GeoOperations geoOperations() {
		if(geoOperations == null){
			geoOperations = new JedisClusterGeoOperations(this);
		}

		return geoOperations;
	}

	@Override
	public HashOperations hashOperations() {
		if(hashOperations == null){
			hashOperations = new JedisClusterHashOperations(this);
		}

		return hashOperations;
	}

	@Override
	public HyperLogLogOperations hyperLogLogOperations() {
		if(hyperLogLogOperations == null){
			hyperLogLogOperations = new JedisClusterHyperLogLogOperations(this);
		}

		return hyperLogLogOperations;
	}

	@Override
	public KeyOperations keyOperations() {
		if(keyOperations == null){
			keyOperations = new JedisClusterKeyOperations(this);
		}

		return keyOperations;
	}

	@Override
	public ListOperations listOperations() {
		if(listOperations == null){
			listOperations = new JedisClusterListOperations(this);
		}

		return listOperations;
	}

	@Override
	public PubSubOperations pubSubOperations() {
		if(pubSubOperations == null){
			pubSubOperations = new JedisClusterPubSubOperations(this);
		}

		return pubSubOperations;
	}

	@Override
	public ScriptingOperations scriptingOperations() {
		if(scriptingOperations == null){
			scriptingOperations = new JedisClusterScriptingOperations(this);
		}

		return scriptingOperations;
	}

	@Override
	public ServerOperations serverOperations() {
		if(serverOperations == null){
			serverOperations = new JedisClusterServerOperations(this);
		}

		return serverOperations;
	}

	@Override
	public SetOperations setOperations() {
		if(setOperations == null){
			setOperations = new JedisClusterSetOperations(this);
		}

		return setOperations;
	}

	@Override
	public SortedSetOperations sortedSetOperations() {
		if(sortedSetOperations == null){
			sortedSetOperations = new JedisClusterSortedSetOperations(this);
		}

		return sortedSetOperations;
	}

	@Override
	public StreamOperations streamOperations() {
		if(streamOperations == null){
			streamOperations = new JedisClusterStreamOperations(this);
		}

		return streamOperations;
	}

	@Override
	public StringOperations stringOperations() {
		if(stringOperations == null){
			stringOperations = new JedisClusterStringOperations(this);
		}

		return stringOperations;
	}

	@Override
	public TransactionOperations transactionOperations() {
		if(transactionOperations == null){
			transactionOperations = new JedisClusterTransactionOperations(this);
		}

		return transactionOperations;
	}

}