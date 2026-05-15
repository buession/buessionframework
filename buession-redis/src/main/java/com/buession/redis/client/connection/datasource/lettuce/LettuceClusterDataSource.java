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
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;

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
	 * timeout for rate-limit adaptive topology updates（单位：毫秒）
	 */
	private int adaptiveRefreshTimeout = (int) ClusterTopologyRefreshOptions.DEFAULT_ADAPTIVE_REFRESH_TIMEOUT;

	/**
	 * 最大重数时长（单位：毫秒）
	 */
	private int maxTotalRetries = maxRedirects * (int) ClusterTopologyRefreshOptions.DEFAULT_REFRESH_PERIOD;

	/**
	 * 定期主动刷新客户端本地缓存的 Redis 集群拓扑结构时长（单位：毫秒）
	 *
	 * @since 4.0.0
	 */
	private int topologyRefreshPeriod = (int) ClusterTopologyRefreshOptions.DEFAULT_REFRESH_PERIOD;

	/**
	 * Whether adaptive topology refreshing using all available refresh triggers should be used.
	 *
	 * @since 4.0.0
	 */
	private boolean adaptive;

	/**
	 * Whether to discover and query all cluster nodes for obtaining the cluster topology.
	 * When set to false, only the initial seed nodes are used as sources for topology discovery.
	 *
	 * @since 4.0.0
	 */
	private boolean dynamicRefreshSources = ClusterTopologyRefreshOptions.DEFAULT_DYNAMIC_REFRESH_SOURCES;

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
	 * Returns the timeout for adaptive topology updates. This timeout is to rate-limit topology updates initiated by refresh
	 * triggers to one topology refresh per timeout. Defaults to {@literal 30 SECONDS}. See
	 * {@link io.lettuce.core.cluster.ClusterTopologyRefreshOptions#DEFAULT_REFRESH_PERIOD}
	 * and {@link io.lettuce.core.cluster.ClusterTopologyRefreshOptions#DEFAULT_REFRESH_PERIOD_UNIT}.
	 *
	 * @return The imeout for rate-limit adaptive topology updates
	 *
	 * @since 4.0.0
	 */
	public int getAdaptiveRefreshTimeout() {
		return adaptiveRefreshTimeout;
	}

	/**
	 * Set the timeout for adaptive topology updates. This timeout is to rate-limit topology updates initiated by refresh
	 * triggers to one topology refresh per timeout. Defaults to {@literal 30 SECONDS}. See
	 * {@link io.lettuce.core.cluster.ClusterTopologyRefreshOptions#DEFAULT_REFRESH_PERIOD}
	 * and {@link io.lettuce.core.cluster.ClusterTopologyRefreshOptions#DEFAULT_REFRESH_PERIOD_UNIT}.
	 *
	 * @param adaptiveRefreshTimeout
	 * 		timeout for rate-limit adaptive topology updates, must be greater than {@literal 0}.
	 *
	 * @since 4.0.0
	 */
	public void setAdaptiveRefreshTimeout(int adaptiveRefreshTimeout) {
		this.adaptiveRefreshTimeout = adaptiveRefreshTimeout;
	}

	@Override
	public int getTopologyRefreshPeriod() {
		return topologyRefreshPeriod;
	}

	@Override
	public void setTopologyRefreshPeriod(int topologyRefreshPeriod) {
		this.topologyRefreshPeriod = topologyRefreshPeriod;
	}

	public boolean isAdaptive() {
		return getAdaptive();
	}

	public boolean getAdaptive() {
		return adaptive;
	}

	public void setAdaptive(boolean adaptive) {
		this.adaptive = adaptive;
	}

	public boolean isDynamicRefreshSources() {
		return getDynamicRefreshSources();
	}

	public boolean getDynamicRefreshSources() {
		return dynamicRefreshSources;
	}

	public void setDynamicRefreshSources(boolean dynamicRefreshSources) {
		this.dynamicRefreshSources = dynamicRefreshSources;
	}

}
