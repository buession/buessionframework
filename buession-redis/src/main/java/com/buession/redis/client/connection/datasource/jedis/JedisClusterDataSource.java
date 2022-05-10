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
package com.buession.redis.client.connection.datasource.jedis;

import com.buession.core.validator.Validate;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.datasource.ClusterDataSource;
import com.buession.redis.client.connection.jedis.JedisClusterConnection;
import com.buession.redis.core.RedisNode;
import com.buession.redis.utils.PoolConfigUtils;
import redis.clients.jedis.ConnectionPoolConfig;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Jedis 集群模式数据源
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class JedisClusterDataSource extends AbstractJedisDataSource implements ClusterDataSource {

	/**
	 * 集群主机节点
	 */
	private List<RedisNode> nodes;

	/**
	 * 最大重定向次数
	 */
	private int maxRedirects = DEFAULT_MAX_REDIRECTS;

	/**
	 * 最大重数时长（单位：秒）
	 */
	private int maxTotalRetriesDuration = -1;

	/**
	 * JedisCluster 对象
	 */
	private JedisCluster cluster;

	@Override
	public List<RedisNode> getNodes(){
		return nodes;
	}

	@Override
	public void setNodes(List<RedisNode> nodes){
		this.nodes = nodes;
	}

	@Override
	public int getMaxRedirects(){
		return maxRedirects;
	}

	@Override
	public void setMaxRedirects(int maxRedirects){
		this.maxRedirects = maxRedirects;
	}

	@Override
	public int getMaxTotalRetriesDuration(){
		return maxTotalRetriesDuration;
	}

	@Override
	public void setMaxTotalRetriesDuration(int maxTotalRetriesDuration){
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
	}

	@Override
	public RedisConnection getConnection(){
		if(cluster == null){
			cluster = createJedisCluster();
		}

		return new JedisClusterConnection(this, cluster, getConnectTimeout(), getSoTimeout(), getInfiniteSoTimeout(),
				getMaxRedirects(), getMaxTotalRetriesDuration(), getSslConfiguration());
	}

	protected JedisCluster createJedisCluster(){
		Set<HostAndPort> hostAndPorts = new HashSet<>();

		for(RedisNode node : getNodes()){
			int port = node.getPort() == 0 ? RedisNode.DEFAULT_PORT : node.getPort();
			hostAndPorts.add(new HostAndPort(node.getHost(), port));
		}

		int maxRedirects = getMaxRedirects() < 0 ? DEFAULT_MAX_REDIRECTS : getMaxRedirects();
		final DefaultJedisClientConfig.Builder builder = createJedisClientConfigBuilder();

		if(Validate.hasText(getPassword())){
			if(Validate.hasText(getUsername())){
				builder.user(getUsername());
			}

			builder.password(getPassword());
		}

		if(Validate.hasText(getClientName())){
			builder.clientName(getClientName());
		}

		if(isUsePool()){
			final ConnectionPoolConfig connectionPoolConfig = new ConnectionPoolConfig();

			PoolConfigUtils.convert(getPoolConfig(), connectionPoolConfig);

			if(maxTotalRetriesDuration > 0){
				cluster = new JedisCluster(hostAndPorts, builder.build(), maxRedirects,
						createMaxTotalRetriesDuration(), connectionPoolConfig);
			}else{
				cluster = new JedisCluster(hostAndPorts, builder.build(), maxRedirects, connectionPoolConfig);
			}
		}else{
			if(maxTotalRetriesDuration > 0){
				cluster = new JedisCluster(hostAndPorts, builder.build(), maxRedirects,
						createMaxTotalRetriesDuration());
			}else{
				cluster = new JedisCluster(hostAndPorts, builder.build(), maxRedirects);
			}
		}

		return cluster;
	}

	private Duration createMaxTotalRetriesDuration(){
		return Duration.ofMillis(maxTotalRetriesDuration * 1000L);
	}

}