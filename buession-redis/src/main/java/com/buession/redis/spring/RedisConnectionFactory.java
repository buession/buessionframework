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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.spring;

/**
 * Redis 连接工厂
 *
 * @author Yong.Teng
 */
public class RedisConnectionFactory implements AutoCloseable {

	/**
	 * Redis 连接配置
	 */
	private RedisConfiguration configuration;

	/**
	 * 是否是单机模式连接
	 */
	private boolean isGenericConnection = true;

	/**
	 * 是否是分片模式连接
	 */
	private boolean isShardedConnection = false;

	/**
	 * 构造函数
	 *
	 * @param configuration
	 * 		连接配置
	 */
	public RedisConnectionFactory(final RedisConfiguration configuration){
		setConfiguration(configuration);
	}

	/**
	 * 获取连接配置
	 *
	 * @return 连接配置
	 */
	public RedisConfiguration getConfiguration(){
		return configuration;
	}

	/**
	 * 设置连接配置
	 *
	 * @param configuration
	 * 		连接配置
	 */
	public void setConfiguration(RedisConfiguration configuration){
		this.configuration = configuration;
		this.isShardedConnection = configuration instanceof ShardedConfiguration;
	}

	@Override
	public void close() throws Exception{

	}

	protected boolean isGenericConnection(){
		return isGenericConnection;
	}

	protected boolean isShardedConnection(){
		return isShardedConnection;
	}

}
