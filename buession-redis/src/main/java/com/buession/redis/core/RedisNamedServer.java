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

import java.util.Objects;

/**
 * @author Yong.Teng
 */
public class RedisNamedServer extends RedisServer implements RedisNamedNode {

	private final static long serialVersionUID = -4695061230031566288L;

	/**
	 * 节点名称
	 */
	private String name;

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 */
	public RedisNamedServer(final String host){
		super(host);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 端口
	 */
	public RedisNamedServer(final String host, final int port){
		super(host, port);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param name
	 * 		节点名称
	 */
	public RedisNamedServer(final String host, final String name){
		super(host);
		this.name = name;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 端口
	 * @param name
	 * 		节点名称
	 */
	public RedisNamedServer(final String host, final int port, final String name){
		super(host, port);
		this.name = name;
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
	public RedisNamedServer(final String host, final Role role){
		super(host, role);
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
	 *
	 * @since 1.3.0
	 */
	public RedisNamedServer(final String host, final int port, final Role role){
		super(host, port, role);
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
	public RedisNamedServer(final String host, final String name, final Role role){
		super(host, role);
		this.name = name;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 端口
	 * @param name
	 * 		节点名称
	 * @param role
	 * 		节点角色
	 *
	 * @since 1.3.0
	 */
	public RedisNamedServer(final String host, final int port, final String name, final Role role){
		super(host, port, role);
		this.name = name;
	}

	/**
	 * 获取节点名称
	 *
	 * @return 节点名称
	 */
	@Override
	public String getName(){
		return name;
	}

	@Override
	public String asString(){
		final StringBuilder sb = new StringBuilder(20);

		sb.append(name).append("m ").append(super.asString());

		return sb.toString();
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = super.hashCode();

		result = prime * result + Objects.hashCode(name);

		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof RedisNamedServer){
			if(super.equals(obj)){
				RedisNamedServer that = (RedisNamedServer) obj;
				return Objects.equals(name, that.name);
			}
		}

		return false;
	}

}
