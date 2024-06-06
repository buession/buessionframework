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
package com.buession.redis.client.connection.datasource.jedis;

import com.buession.redis.client.connection.datasource.ClusterDataSource;
import com.buession.redis.client.connection.jedis.JedisClusterConnection;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.internal.jedis.JedisClientConfigBuilder;
import redis.clients.jedis.ConnectionPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.providers.ClusterConnectionProvider;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
	 * 连接提供者
	 *
	 * @since 3.0.0
	 */
	private ClusterConnectionProvider connectionProvider;

	@Override
	public List<RedisNode> getNodes() {
		return nodes;
	}

	@Override
	public void setNodes(List<RedisNode> nodes) {
		this.nodes = nodes;
	}

	@Override
	public int getMaxRedirects() {
		return maxRedirects;
	}

	@Override
	public void setMaxRedirects(int maxRedirects) {
		this.maxRedirects = maxRedirects;
	}

	@Override
	public int getMaxTotalRetriesDuration() {
		return maxTotalRetriesDuration;
	}

	@Override
	public void setMaxTotalRetriesDuration(int maxTotalRetriesDuration) {
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
	}

	@Override
	public JedisClusterConnection getConnection() {
		if(connectionProvider == null){
			connectionProvider = createClusterConnectionProvider();
		}

		return new JedisClusterConnection(this, connectionProvider, getConnectTimeout(), getSoTimeout(),
				getInfiniteSoTimeout(), getMaxRedirects(), getMaxTotalRetriesDuration(), getSslConfiguration());
	}

	protected ClusterConnectionProvider createClusterConnectionProvider() {
		final JedisClientConfig clientConfig = JedisClientConfigBuilder.create(this, getSslConfiguration()).build();

		if(isUsePool()){
			final ConnectionPoolConfig connectionPoolConfig = new ConnectionPoolConfig();

			getPoolConfig().toGenericObjectPoolConfig(connectionPoolConfig);

			return new ClusterConnectionProvider(createHostAndPorts(), clientConfig, connectionPoolConfig);
		}else{
			return new ClusterConnectionProvider(createHostAndPorts(), clientConfig);
		}
	}

	private Set<HostAndPort> createHostAndPorts() {
		return getNodes().stream().map((node)->{
			int port = node.getPort() == 0 ? RedisNode.DEFAULT_PORT : node.getPort();
			return new HostAndPort(node.getHost(), port);
		}).collect(Collectors.toSet());
	}

}