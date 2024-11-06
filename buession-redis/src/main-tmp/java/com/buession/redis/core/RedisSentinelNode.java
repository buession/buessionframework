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
package com.buession.redis.core;

import org.springframework.lang.Nullable;

/**
 * Redis 哨兵节点
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class RedisSentinelNode extends RedisNode {

	private final static long serialVersionUID = 7609068824843419481L;

	private int quorum;

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 */
	public RedisSentinelNode(@Nullable String host){
		super(host);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 * @param port
	 * 		端口
	 */
	public RedisSentinelNode(@Nullable String host, int port){
		super(host, port);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 * @param role
	 * 		主机角色
	 */
	public RedisSentinelNode(@Nullable String host, @Nullable Role role){
		super(host, role);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 * @param port
	 * 		端口
	 * @param role
	 * 		主机角色
	 */
	public RedisSentinelNode(@Nullable String host, int port, @Nullable Role role){
		super(host, port, role);
	}

	public int getQuorum(){
		return quorum;
	}

	public void setQuorum(int quorum){
		this.quorum = quorum;
	}

}
