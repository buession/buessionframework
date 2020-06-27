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

import org.springframework.util.ObjectUtils;

/**
 * 增强版 Redis 节点
 *
 * @author Yong.Teng
 */
public class EnhanceRedisNode extends GenericRedisNode implements NamedNode {

	/**
	 * 节点 ID
	 */
	private String id;

	/**
	 * 节点名称
	 */
	private String name;

	/**
	 * 节点角色
	 */
	private Role role;

	/**
	 * 构造函数
	 */
	public EnhanceRedisNode(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 节点端口
	 */
	public EnhanceRedisNode(String host, int port){
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
	public EnhanceRedisNode(String host, int port, String name){
		super(host, port);
		this.name = name;
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
	public EnhanceRedisNode(String host, int port, Role role){
		super(host, port);
		this.role = role;
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
	public EnhanceRedisNode(String host, int port, String id, String name, Role role){
		this(host, port, name);
		this.id = id;
		this.role = role;
	}

	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id = id;
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

	/**
	 * 设置节点名称
	 *
	 * @param name
	 * 		节点名称
	 */
	public void setName(String name){
		this.name = name;
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
	 * 设置节点角色
	 *
	 * @param role
	 * 		节点角色
	 */
	public void setRole(Role role){
		this.role = role;
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
		sb.append(name).append("m ").append(super.asString());
		return sb.toString();
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = super.hashCode();

		result = prime * result + ObjectUtils.nullSafeHashCode(name);

		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj == null || (obj instanceof EnhanceRedisNode) == false){
			return false;
		}

		EnhanceRedisNode that = (EnhanceRedisNode) obj;

		return super.equals(obj) && ObjectUtils.nullSafeEquals(this.name, that.name);
	}

}
