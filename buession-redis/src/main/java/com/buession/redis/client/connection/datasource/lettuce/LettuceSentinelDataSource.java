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
package com.buession.redis.client.connection.datasource.lettuce;

import com.buession.core.validator.Validate;
import com.buession.redis.client.connection.PoolConfigConverter;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.datasource.SentinelDataSource;
import com.buession.redis.client.connection.lettuce.LettuceSentinelConnection;
import com.buession.redis.core.Constants;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.internal.jedis.JedisClientConfigBuilder;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Lettuce 哨兵模式数据源
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public class LettuceSentinelDataSource extends AbstractLettuceDataSource implements SentinelDataSource {

	/**
	 * 数据库
	 */
	private int database = RedisNode.DEFAULT_DATABASE;

	/**
	 * 哨兵节点连接超时（单位：毫秒）
	 */
	private int sentinelConnectTimeout = Constants.DEFAULT_CONNECT_TIMEOUT;

	/**
	 * 哨兵节点读取超时（单位：毫秒）
	 */
	private int sentinelSoTimeout = Constants.DEFAULT_SO_TIMEOUT;

	/**
	 * Sentinel Client Name
	 */
	private String sentinelClientName;

	/**
	 * Master 名称
	 */
	private String masterName;

	/**
	 * 哨兵节点
	 */
	private List<RedisNode> sentinels;

	private JedisSentinelPool pool;

	@Override
	public int getDatabase(){
		return database;
	}

	@Override
	public void setDatabase(int database){
		this.database = database;
	}

	@Override
	public int getSentinelConnectTimeout(){
		return sentinelConnectTimeout;
	}

	@Override
	public void setSentinelConnectTimeout(int sentinelConnectTimeout){
		this.sentinelConnectTimeout = sentinelConnectTimeout;
	}

	@Override
	public int getSentinelSoTimeout(){
		return sentinelSoTimeout;
	}

	@Override
	public void setSentinelSoTimeout(int sentinelSoTimeout){
		this.sentinelSoTimeout = sentinelSoTimeout;
	}

	@Override
	public String getSentinelClientName(){
		return sentinelClientName;
	}

	@Override
	public void setSentinelClientName(String sentinelClientName){
		this.sentinelClientName = sentinelClientName;
	}

	@Override
	public String getMasterName(){
		return masterName;
	}

	@Override
	public void setMasterName(String masterName){
		this.masterName = masterName;
	}

	@Override
	public List<RedisNode> getSentinels(){
		return sentinels;
	}

	@Override
	public void setSentinels(List<RedisNode> sentinels){
		this.sentinels = sentinels;
	}

	@Override
	public RedisConnection getConnection(){
		if(isUsePool()){
			if(pool == null){
				pool = createPool();
			}
		}

		return new LettuceSentinelConnection(this, pool, getConnectTimeout(), getSoTimeout(),
				getInfiniteSoTimeout(), getSentinelConnectTimeout(), getSentinelSoTimeout(), getSslConfiguration());
	}

	protected JedisSentinelPool createPool(){
		/*
		final Set<HostAndPort> sentinels = convertToJedisSentinelSet(getSentinels());
		final JedisClientConfigBuilder builder = JedisClientConfigBuilder.create(this, getSslConfiguration());
		final JedisClientConfigBuilder sentinelBuilder = JedisClientConfigBuilder.create(this,
				getSslConfiguration());
		final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		final PoolConfigConverter<Jedis> poolConfigConverter = new PoolConfigConverter<>(jedisPoolConfig);

		builder.database(getDatabase());

		poolConfigConverter.convert(getPoolConfig());

		return new JedisSentinelPool(getMasterName(), sentinels, jedisPoolConfig, builder.build(),
				sentinelBuilder.build());

		 */
		return null;
	}

	private Set<HostAndPort> convertToJedisSentinelSet(Collection<RedisNode> sentinelNodes){
		if(Validate.isEmpty(sentinelNodes)){
			return Collections.emptySet();
		}

		Set<HostAndPort> convertedNodes = new LinkedHashSet<>(sentinelNodes.size());

		for(RedisNode node : sentinelNodes){
			if(node != null){
				int port = node.getPort() == 0 ? RedisNode.DEFAULT_SENTINEL_PORT : node.getPort();
				convertedNodes.add(new HostAndPort(node.getHost(), port));
			}
		}

		return convertedNodes;
	}

}
