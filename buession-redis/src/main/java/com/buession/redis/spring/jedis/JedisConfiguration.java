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

import com.buession.core.utils.Assert;
import com.buession.redis.core.RedisNode;
import com.buession.redis.spring.StandaloneConfiguration;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis Redis 工厂配置
 *
 * @author Yong.Teng
 */
public class JedisConfiguration extends AbstractJedisRedisConfiguration implements StandaloneConfiguration {

	/**
	 * Redis 主机地址
	 */
	private String host = RedisNode.DEFAULT_HOST;

	/**
	 * Redis 端口
	 */
	private int port = RedisNode.DEFAULT_PORT;

	/**
	 * 数据库
	 */
	private int database = RedisNode.DEFAULT_DATABASE;

	/**
	 * 连接池配置
	 *
	 * @since 2.0.0
	 */
	private JedisPoolConfig poolConfig;

	@Override
	public String getHost(){
		return host;
	}

	@Override
	public void setHost(String host){
		this.host = host;
	}

	@Override
	public int getPort(){
		return port;
	}

	@Override
	public void setPort(int port){
		this.port = port;
	}

	@Override
	public int getDatabase(){
		return database;
	}

	@Override
	public void setDatabase(int database){
		Assert.isNegative(database, "invalid DB index (a positive index required)");
		this.database = database;
	}

	/**
	 * 获取连接池配置
	 *
	 * @return 连接池配置
	 */
	public JedisPoolConfig getPoolConfig(){
		return poolConfig;
	}

	/**
	 * 设置连接池配置
	 *
	 * @param poolConfig
	 * 		连接池配置
	 *
	 * @since 2.0.0
	 */
	public void setPoolConfig(JedisPoolConfig poolConfig){
		this.poolConfig = poolConfig;
	}

}
