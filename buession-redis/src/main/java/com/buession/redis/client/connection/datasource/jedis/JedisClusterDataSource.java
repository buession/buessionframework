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
package com.buession.redis.client.connection.datasource.jedis;

import com.buession.redis.client.connection.RedisNode;
import com.buession.redis.client.connection.datasource.ClusterDataSource;
import redis.clients.jedis.RedisClusterClient;

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
	private Set<RedisNode> nodes;

	/**
	 * 最大重定向次数
	 */
	private int maxRedirects = DEFAULT_MAX_ATTEMPTS;

	/**
	 * 最大重数时长（单位：毫秒）
	 */
	private int maxTotalRetries = maxRedirects * RedisClusterClient.DEFAULT_TIMEOUT;

	/**
	 * 定期主动刷新客户端本地缓存的 Redis 集群拓扑结构时长（单位：毫秒）
	 *
	 * @since 4.0.0
	 */
	private int topologyRefreshPeriod = RedisClusterClient.DEFAULT_TIMEOUT;

	@Override
	public Set<RedisNode> getNodes() {
		return nodes;
	}

	@Override
	public void setNodes(Set<RedisNode> nodes) {
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

	/**
	 * 返回最大重试持续时长（单位：毫秒）
	 *
	 * @return 最大重试持续时长
	 */
	public int getMaxTotalRetries() {
		return maxTotalRetries;
	}

	/**
	 * 设置最大重试持续时长
	 *
	 * @param maxTotalRetries
	 * 		最大重试持续时长（单位：毫秒）
	 */
	public void setMaxTotalRetries(int maxTotalRetries) {
		this.maxTotalRetries = maxTotalRetries;
	}

	@Override
	public int getTopologyRefreshPeriod() {
		return topologyRefreshPeriod;
	}

	@Override
	public void setTopologyRefreshPeriod(int topologyRefreshPeriod) {
		this.topologyRefreshPeriod = topologyRefreshPeriod;
	}

}