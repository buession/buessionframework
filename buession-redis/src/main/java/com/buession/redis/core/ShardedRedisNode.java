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
package com.buession.redis.core;

import com.buession.core.utils.Assert;

import java.util.Objects;

/**
 * Redis 分片节点
 *
 * @author Yong.Teng
 */
public class ShardedRedisNode extends RedisNamedServer {

	private static final long serialVersionUID = 5161327009453777887L;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 数据库
	 */
	private int database = DEFAULT_DATABASE;

	/**
	 * 权重
	 */
	private int weight = 1;

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 节点端口
	 */
	public ShardedRedisNode(String host, int port){
		super(host, port);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 节点端口
	 * @param name
	 * 		节点名称
	 */
	public ShardedRedisNode(String host, int port, String name){
		super(host, port, name);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 节点端口
	 * @param password
	 * 		密码
	 * @param name
	 * 		节点名称
	 */
	public ShardedRedisNode(String host, int port, String password, String name){
		super(host, port, name);
		this.password = password;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 节点端口
	 * @param database
	 * 		数据库
	 */
	public ShardedRedisNode(String host, int port, int database){
		super(host, port);
		this.database = database;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 节点端口
	 * @param database
	 * 		数据库
	 * @param name
	 * 		节点名称
	 */
	public ShardedRedisNode(String host, int port, int database, String name){
		super(host, port, name);
		this.database = database;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 节点端口
	 * @param database
	 * 		数据库
	 * @param weight
	 * 		权重
	 */
	public ShardedRedisNode(String host, int port, int database, int weight){
		this(host, port, database);
		this.weight = weight;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 节点端口
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param name
	 * 		节点名称
	 */
	public ShardedRedisNode(String host, int port, String password, int database, String name){
		this(host, port, database, name);
		this.password = password;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 节点端口
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param weight
	 * 		权重
	 */
	public ShardedRedisNode(String host, int port, String password, int database, int weight){
		this(host, port, password);
		this.database = database;
		this.weight = weight;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 节点端口
	 * @param password
	 * 		密码
	 * @param database
	 * 		数据库
	 * @param name
	 * 		节点名称
	 * @param weight
	 * 		权重
	 */
	public ShardedRedisNode(String host, int port, String password, int database, String name, int weight){
		this(host, port, password, database, name);
		this.weight = weight;
	}

	/**
	 * 获取密码
	 *
	 * @return 密码
	 */
	public String getPassword(){
		return password;
	}

	/**
	 * 设置密码
	 *
	 * @param password
	 * 		密码
	 */
	public void setPassword(String password){
		this.password = password;
	}

	/**
	 * 获取数据库
	 *
	 * @return 数据库
	 */
	public int getDatabase(){
		return database;
	}

	/**
	 * 设置数据库
	 *
	 * @param database
	 * 		数据库
	 */
	public void setDatabase(int database){
		this.database = database;
	}

	/**
	 * 获取权重
	 *
	 * @return 权重
	 */
	public int getWeight(){
		return weight;
	}

	/**
	 * 设置权重
	 *
	 * @param weight
	 * 		权重
	 */
	public void setWeight(int weight){
		Assert.isNegative(weight, "Redis node weight cloud not be negative.");
		this.weight = weight;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = super.hashCode();

		result = prime * result + Objects.hashCode(weight);

		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if((obj instanceof ShardedRedisNode) == false){
			return false;
		}

		ShardedRedisNode that = (ShardedRedisNode) obj;

		return Objects.equals(this.weight, that.weight) && super.equals(obj);
	}

}
