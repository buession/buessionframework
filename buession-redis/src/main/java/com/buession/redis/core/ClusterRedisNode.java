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

/**
 * @author Yong.Teng
 */
public class ClusterRedisNode extends RedisNamedServer {

	private static final long serialVersionUID = -1193116310442835296L;

	private String masterId;

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 *
	 * @since 1.3.0
	 */
	public ClusterRedisNode(final String host){
		super(host);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 节点端口
	 */
	public ClusterRedisNode(final String host, final int port){
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
	public ClusterRedisNode(final String host, final int port, final String name){
		super(host, port, name);
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
	 * @param masterId
	 * 		Master Id
	 *
	 * @since 1.3.0
	 */
	public ClusterRedisNode(final String host, final int port, final String name, final String masterId){
		super(host, port, name);
		this.masterId = masterId;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 节点端口
	 * @param role
	 * 		节点角色
	 * @param masterId
	 * 		Master Id
	 *
	 * @since 1.3.0
	 */
	public ClusterRedisNode(final String host, final int port, final Role role, final String masterId){
		super(host, port, role);
		this.masterId = masterId;
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
	 * @param role
	 * 		节点角色
	 * @param masterId
	 * 		Master Id
	 *
	 * @since 1.3.0
	 */
	public ClusterRedisNode(final String host, final int port, final String name, final Role role,
							final String masterId){
		super(host, port, name, role);
		this.masterId = masterId;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 节点端口
	 * @param role
	 * 		节点角色
	 */
	public ClusterRedisNode(final String host, final int port, final Role role){
		super(host, port, role);
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
	 * @param role
	 * 		节点角色
	 */
	public ClusterRedisNode(final String host, final int port, final String name, final Role role){
		super(host, port, name, role);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param name
	 * 		节点名称
	 *
	 * @since 1.3.0
	 */
	public ClusterRedisNode(final String host, final String name){
		super(host, name);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param name
	 * 		节点名称
	 * @param masterId
	 * 		Master Id
	 *
	 * @since 1.3.0
	 */
	public ClusterRedisNode(final String host, final String name, final String masterId){
		super(host, name);
		this.masterId = masterId;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param role
	 * 		节点角色
	 * @param masterId
	 * 		Master Id
	 *
	 * @since 1.3.0
	 */
	public ClusterRedisNode(final String host, final Role role, final String masterId){
		super(host, role);
		this.masterId = masterId;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param name
	 * 		节点名称
	 * @param role
	 * 		节点角色
	 * @param masterId
	 * 		Master Id
	 *
	 * @since 1.3.0
	 */
	public ClusterRedisNode(final String host, final String name, final Role role, final String masterId){
		super(host, name, role);
		this.masterId = masterId;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param role
	 * 		节点角色
	 *
	 * @since 1.3.0
	 */
	public ClusterRedisNode(final String host, final Role role){
		super(host, role);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param name
	 * 		节点名称
	 * @param role
	 * 		节点角色
	 *
	 * @since 1.3.0
	 */
	public ClusterRedisNode(final String host, final String name, final Role role){
		super(host, name, role);
	}

	public Role getNodeType(){
		return getRole();
	}

	public String getMasterId(){
		return masterId;
	}

	public void setMasterId(String masterId){
		this.masterId = masterId;
	}

}
