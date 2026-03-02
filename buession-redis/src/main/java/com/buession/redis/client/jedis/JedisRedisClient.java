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
import com.buession.redis.client.ClientOptions;
import com.buession.redis.client.connection.jedis.JedisRedisConnection;
import com.buession.redis.client.jedis.command.*;
import com.buession.redis.core.command.*;
import redis.clients.jedis.UnifiedJedis;

/**
 * Jedis Redis 客户端
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class JedisRedisClient extends AbstractRedisClient {

	/**
	 * 构造函数
	 */
	public JedisRedisClient() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param clientOptions
	 * 		客户端选项
	 * @param connection
	 * 		Jedis Redis 连接对象 {@link JedisRedisConnection}
	 *
	 * @since 4.0.0
	 */
	public JedisRedisClient(final ClientOptions clientOptions,
							final JedisRedisConnection<? extends UnifiedJedis> connection) {
		super(clientOptions, connection);
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
	public BloomFilterCommands bloomFilterCommands() {
		if(bloomFilterCommands == null){
			bloomFilterCommands = new JedisBloomFilterCommands(this);
		}

		return bloomFilterCommands;
	}

	@Override
	public BitMapCommands bitMapCommands() {
		if(bitMapCommands == null){
			bitMapCommands = new JedisBitMapCommands(this);
		}

		return bitMapCommands;
	}

	@Override
	public CuckooFilterCommands cuckooFilterCommands() {
		if(cuckooFilterCommands == null){
			cuckooFilterCommands = new JedisCuckooFilterCommands(this);
		}

		return cuckooFilterCommands;
	}

	@Override
	public ClusterCommands clusterCommands() {
		if(clusterCommands == null){
			clusterCommands = new JedisClusterCommands(this);
		}

		return clusterCommands;
	}

	@Override
	public CountMinSketchCommands countMinSketchCommands() {
		if(countMinSketchCommands == null){
			countMinSketchCommands = new JedisCountMinSketchCommands(this);
		}

		return countMinSketchCommands;
	}

	@Override
	public ConnectionCommands connectionCommands() {
		if(connectionCommands == null){
			connectionCommands = new JedisConnectionCommands(this);
		}

		return connectionCommands;
	}

	@Override
	public GenericCommands genericCommands() {
		if(genericCommands == null){
			genericCommands = new JedisGenericCommands(this);
		}

		return genericCommands;
	}

	@Override
	public GeoCommands geoCommands() {
		if(geoCommands == null){
			geoCommands = new JedisGeoCommands(this);
		}

		return geoCommands;
	}

	@Override
	public HashCommands hashCommands() {
		if(hashCommands == null){
			hashCommands = new JedisHashCommands(this);
		}

		return hashCommands;
	}

	@Override
	public HyperLogLogCommands hyperLogLogCommands() {
		if(hyperLogLogCommands == null){
			hyperLogLogCommands = new JedisHyperLogLogCommands(this);
		}

		return hyperLogLogCommands;
	}

	@Override
	public JsonCommands jsonCommands() {
		if(jsonCommands == null){
			jsonCommands = new JedisJsonCommands(this);
		}

		return jsonCommands;
	}

	@Override
	public KeyCommands keyCommands() {
		if(keyCommands == null){
			keyCommands = new JedisKeyCommands(this);
		}

		return keyCommands;
	}

	@Override
	public ListCommands listCommands() {
		if(listCommands == null){
			listCommands = new JedisListCommands(this);
		}

		return listCommands;
	}

	@Override
	public PubSubCommands pubSubCommands() {
		if(pubSubCommands == null){
			pubSubCommands = new JedisPubSubCommands(this);
		}

		return pubSubCommands;
	}

	@Override
	public ScriptingCommands scriptingCommands() {
		if(scriptingCommands == null){
			scriptingCommands = new JedisScriptingCommands(this);
		}

		return scriptingCommands;
	}

	/*
	@Override
	public SearchCommands searchCommands() {
		if(searchCommands == null){
			searchCommands = new JedisSearchCommands(this);
		}

		return searchCommands;
	}

	 */

	@Override
	public ServerCommands serverCommands() {
		if(serverCommands == null){
			serverCommands = new JedisServerCommands(this);
		}

		return serverCommands;
	}

	@Override
	public SetCommands setCommands() {
		if(setCommands == null){
			setCommands = new JedisSetCommands(this);
		}

		return setCommands;
	}

	@Override
	public SortedSetCommands sortedSetCommands() {
		if(sortedSetCommands == null){
			sortedSetCommands = new JedisSortedSetCommands(this);
		}

		return sortedSetCommands;
	}

	@Override
	public StreamCommands streamCommands() {
		if(streamCommands == null){
			streamCommands = new JedisStreamCommands(this);
		}

		return streamCommands;
	}

	@Override
	public StringCommands stringCommands() {
		if(stringCommands == null){
			stringCommands = new JedisStringCommands(this);
		}

		return stringCommands;
	}

	@Override
	public TransactionCommands transactionCommands() {
		if(transactionCommands == null){
			transactionCommands = new JedisTransactionCommands(this);
		}

		return transactionCommands;
	}

}
