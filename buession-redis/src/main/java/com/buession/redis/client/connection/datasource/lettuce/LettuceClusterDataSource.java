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
package com.buession.redis.client.connection.datasource.lettuce;

import com.buession.redis.client.connection.RedisNode;
import com.buession.redis.client.connection.datasource.ClusterDataSource;

import java.time.Duration;
import java.util.Set;

/**
 * Lettuce 集群模式数据源
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceClusterDataSource extends AbstractLettuceDataSource implements ClusterDataSource {

	/**
	 * 集群主机节点
	 */
	private Set<RedisNode> nodes;

	/**
	 * 最大重定向次数
	 */
	private int maxRedirects = DEFAULT_MAX_ATTEMPTS;

	/**
	 * 最大重数时长
	 */
	private Duration maxTotalRetriesDuration = Duration.ofMillis(maxRedirects * 3000);

	/**
	 * 定期主动刷新客户端本地缓存的 Redis 集群拓扑结构时长
	 *
	 * @since 4.0.0
	 */
	private Duration topologyRefreshPeriod = Duration.ofMillis(3000);

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

	@Override
	public Duration getMaxTotalRetriesDuration() {
		return maxTotalRetriesDuration;
	}

	@Override
	public void setMaxTotalRetriesDuration(Duration maxTotalRetriesDuration) {
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
	}

	@Override
	public Duration getTopologyRefreshPeriod() {
		return topologyRefreshPeriod;
	}

	@Override
	public void setTopologyRefreshPeriod(Duration topologyRefreshPeriod) {
		this.topologyRefreshPeriod = topologyRefreshPeriod;
	}

}
