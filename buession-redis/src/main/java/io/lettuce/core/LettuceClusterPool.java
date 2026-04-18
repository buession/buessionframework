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
package io.lettuce.core;

import com.buession.net.HostAndPort;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Lettuce 集群连接池
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceClusterPool extends Pool<StatefulRedisClusterConnection<byte[], byte[]>> {

	private final static Logger logger = LoggerFactory.getLogger(LettuceClusterPool.class);

	/**
	 * 构造函数
	 *
	 * @param nodes
	 * 		集群节点
	 */
	public LettuceClusterPool(final Set<HostAndPort> nodes) {
		this(nodes, DefaultLettuceClientConfig.builder().build());
	}

	/**
	 * 构造函数
	 *
	 * @param nodes
	 * 		集群节点
	 * @param lettuceClientConfig
	 * 		客户端配置
	 */
	public LettuceClusterPool(final Set<HostAndPort> nodes, final LettuceClientConfig lettuceClientConfig) {
		super(new LettuceClusterFactory(nodes, lettuceClientConfig));
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		线程池配置
	 * @param nodes
	 * 		集群节点
	 */
	public LettuceClusterPool(final GenericObjectPoolConfig<StatefulRedisClusterConnection<byte[], byte[]>> poolConfig,
	                          final Set<HostAndPort> nodes) {
		this(nodes, poolConfig, DefaultLettuceClientConfig.builder().build());
	}

	/**
	 * 构造函数
	 *
	 * @param nodes
	 * 		集群节点
	 * @param poolConfig
	 * 		哨兵节点
	 * @param lettuceClientConfig
	 * 		客户端配置
	 */
	public LettuceClusterPool(final Set<HostAndPort> nodes,
	                          final GenericObjectPoolConfig<StatefulRedisClusterConnection<byte[], byte[]>> poolConfig,
	                          final LettuceClientConfig lettuceClientConfig) {
		this(poolConfig, new LettuceClusterFactory(nodes, lettuceClientConfig));
	}

	/**
	 * 构造函数
	 *
	 * @param poolConfig
	 * 		线程池配置
	 * @param factory
	 * 		连接线对象工厂
	 */
	public LettuceClusterPool(final GenericObjectPoolConfig<StatefulRedisClusterConnection<byte[], byte[]>> poolConfig,
	                          final PooledObjectFactory<StatefulRedisClusterConnection<byte[], byte[]>> factory) {
		super(factory, poolConfig);
	}

	@Override
	public StatefulRedisClusterConnection<byte[], byte[]> getResource() {
		StatefulRedisClusterConnection<byte[], byte[]> connection = super.getResource();
		//jedis.setDataSource(this);
		return connection;
	}

	@Override
	public void returnResource(final StatefulRedisClusterConnection<byte[], byte[]> resource) {
		if(resource != null){
			try{
				super.returnResource(resource);
			}catch(RuntimeException e){
				super.returnBrokenResource(resource);
				logger.warn("Resource is returned to the pool as broken", e);
			}
		}
	}

}
