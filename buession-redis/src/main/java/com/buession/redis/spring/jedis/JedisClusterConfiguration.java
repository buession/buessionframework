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
package com.buession.redis.spring.jedis;

import com.buession.redis.client.connection.RedisClusterConnection;
import com.buession.redis.core.RedisNode;
import com.buession.redis.spring.ClusterConfiguration;
import redis.clients.jedis.ConnectionPoolConfig;

import java.util.List;

/**
 * Jedis Redis 集群工厂配置
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class JedisClusterConfiguration extends AbstractJedisRedisConfiguration implements ClusterConfiguration {

	/**
	 * 集群主机节点
	 */
	private List<RedisNode> nodes;

	/**
	 * 最大重定向次数
	 */
	private int maxRedirects = RedisClusterConnection.DEFAULT_MAX_REDIRECTS;

	/**
	 * 最大重数时长（单位：秒）
	 */
	private int maxTotalRetriesDuration = -1;

	/**
	 * 连接池配置
	 */
	private ConnectionPoolConfig poolConfig;

	/**
	 * 获取集群主机节点
	 *
	 * @return 集群主机节点
	 */
	public List<RedisNode> getNodes(){
		return nodes;
	}

	/**
	 * 设置集群主机节点
	 *
	 * @param nodes
	 * 		集群主机节点
	 */
	public void setNodes(List<RedisNode> nodes){
		this.nodes = nodes;
	}

	/**
	 * 返回最大重定向次数
	 *
	 * @return 最大重定向次数
	 */
	public int getMaxRedirects(){
		return maxRedirects;
	}

	/**
	 * 设置最大重定向次数
	 *
	 * @param maxRedirects
	 * 		最大重定向次数
	 */
	public void setMaxRedirects(int maxRedirects){
		this.maxRedirects = maxRedirects;
	}

	/**
	 * 返回最大重试持续时长（单位：秒）
	 *
	 * @return 最大重试持续时长
	 */
	public int getMaxTotalRetriesDuration(){
		return maxTotalRetriesDuration;
	}

	/**
	 * 设置最大重试持续时长（单位：秒）
	 *
	 * @param maxTotalRetriesDuration
	 * 		最大重试持续时长
	 */
	public void setMaxTotalRetriesDuration(int maxTotalRetriesDuration){
		this.maxTotalRetriesDuration = maxTotalRetriesDuration;
	}

	/**
	 * 返回连接池配置 {@link ConnectionPoolConfig}
	 *
	 * @return 连接池配置
	 */
	public ConnectionPoolConfig getPoolConfig(){
		return poolConfig;
	}

	/**
	 * 设置连接池配置 {@link ConnectionPoolConfig}
	 *
	 * @param poolConfig
	 * 		连接池配置
	 */
	public void setPoolConfig(ConnectionPoolConfig poolConfig){
		this.poolConfig = poolConfig;
	}

}
