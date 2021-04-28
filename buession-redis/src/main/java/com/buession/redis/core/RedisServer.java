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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core;

import org.springframework.util.ObjectUtils;

/**
 * @author Yong.Teng
 */
public class RedisServer extends RedisNode {

	private final static long serialVersionUID = 4843502163987630437L;

	/**
	 * Redis 服务器主机 IP 地址
	 */
	private String ip;

	/**
	 * 节点角色
	 */
	private Role role;

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 */
	public RedisServer(String host){
		super(host);
		this.ip = host;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 端口
	 */
	public RedisServer(String host, int port){
		super(host, port);
		this.ip = host;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param role
	 * 		节点角色
	 */
	public RedisServer(String host, Role role){
		this(host);
		this.role = role;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 端口
	 * @param role
	 * 		节点角色
	 */
	public RedisServer(String host, int port, Role role){
		this(host, port);
		this.role = role;
	}

	/**
	 * 获取 Redis 服务器主机 IP 地址
	 *
	 * @return Redis 服务器主机 IP 地址
	 */
	public String getIp(){
		return ip;
	}

	/**
	 * 获取节点角色
	 *
	 * @return 节点角色
	 */
	public Role getRole(){
		return role;
	}

	/**
	 * 判断节点是否为 Master 节点
	 *
	 * @return 节点为 Master 节点，返回 true；否则，返回 false
	 */
	public boolean isMaster(){
		return ObjectUtils.nullSafeEquals(getRole(), Role.MASTER);
	}

	/**
	 * 判断节点是否为 Slave 节点
	 *
	 * @return 节点为 Slave 节点，返回 true；否则，返回 false
	 */
	public boolean isSlave(){
		return ObjectUtils.nullSafeEquals(getRole(), Role.SLAVE);
	}

	/**
	 * 判断节点是否为副本节点
	 *
	 * @return 节点为副本节点，返回 true；否则，返回 false
	 */
	public boolean isReplica(){
		return isSlave();
	}

	@Override
	public String asString(){
		final StringBuilder sb = new StringBuilder(20);

		sb.append(super.asString()).append(", role: ").append(role);

		return sb.toString();
	}

	@Override
	public int hashCode(){
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj == null || (obj instanceof RedisServer) == false){
			return false;
		}

		return super.equals(obj);
	}

}
