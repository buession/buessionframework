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

import java.io.Serializable;
import java.util.Objects;

/**
 * Redis 节点
 *
 * @author Yong.Teng
 */
public class RedisNode implements Serializable, RedisNamedNode {

	private final static long serialVersionUID = -2212702986712034274L;

	public final static String DEFAULT_HOST = "localhost";

	public final static int DEFAULT_PORT = 6379;

	public final static int DEFAULT_SENTINEL_PORT = 26379;

	public final static int DEFAULT_DATABASE = 0;

	/**
	 * 主机 ID
	 */
	@Nullable
	private String id;

	/**
	 * 主机名称
	 */
	@Nullable
	private String name;

	/**
	 * 主机地址
	 */
	@Nullable
	private String host;

	/**
	 * 主机端口
	 */
	private int port;

	/**
	 * 主机角色
	 */
	@Nullable
	private Role role;

	/**
	 * Master 主机 ID
	 */
	@Nullable
	private String masterId;

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 */
	public RedisNode(@Nullable final String host){
		this.host = host;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 * @param port
	 * 		端口
	 */
	public RedisNode(@Nullable final String host, final int port){
		this.host = host;
		this.port = port;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 * @param role
	 * 		主机角色
	 */
	public RedisNode(@Nullable final String host, @Nullable final Role role){
		this(host);
		this.role = role;
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
	public RedisNode(@Nullable final String host, final int port, @Nullable final Role role){
		this(host, port);
		this.role = role;
	}

	/**
	 * 返回主机 ID
	 *
	 * @return 主机 ID
	 */
	@Nullable
	public String getId(){
		return id;
	}

	/**
	 * 设置主机 ID
	 *
	 * @param id
	 * 		主机 ID
	 */
	public void setId(@Nullable String id){
		this.id = id;
	}

	/**
	 * 返回主机名称
	 *
	 * @return 主机名称
	 */
	@Override
	@Nullable
	public String getName(){
		return name;
	}

	/**
	 * 设置主机名称
	 *
	 * @param name
	 * 		主机名称
	 */
	public void setName(@Nullable String name){
		this.name = name;
	}

	/**
	 * 返回主机地址
	 *
	 * @return 主机地址
	 */
	@Nullable
	public String getHost(){
		return host;
	}

	/**
	 * 设置主机地址
	 *
	 * @param host
	 * 		主机地址
	 */
	public void setHost(@Nullable String host){
		this.host = host;
	}

	/**
	 * 返回主机端口
	 *
	 * @return 主机地址
	 */
	public int getPort(){
		return port;
	}

	/**
	 * 设置主机端口
	 *
	 * @param port
	 * 		主机端口
	 */
	public void setPort(int port){
		this.port = port;
	}

	/**
	 * 返回主机角色
	 *
	 * @return 主机角色
	 */
	@Nullable
	public Role getRole(){
		return role;
	}

	/**
	 * 设置主机角色
	 *
	 * @param role
	 * 		主机角色
	 */
	public void setRole(@Nullable Role role){
		this.role = role;
	}

	/**
	 * 返回主机 Master Id
	 *
	 * @return 主机 Master Id
	 */
	@Nullable
	public String getMasterId(){
		return masterId;
	}

	/**
	 * 设置主机 Master Id
	 *
	 * @param masterId
	 * 		主机 Master Id
	 */
	public void setMasterId(@Nullable String masterId){
		this.masterId = masterId;
	}

	/**
	 * 返回是否为 Master 节点
	 *
	 * @return 是否为 Master 节点
	 */
	public boolean isMaster(){
		return Role.MASTER.equals(getRole());
	}

	/**
	 * 返回是否为 Slave 节点
	 *
	 * @return 是否为 Slave 节点
	 */
	public boolean isSlave(){
		return Role.SLAVE.equals(getRole());
	}

	/**
	 * 返回是否为 Slave 节点
	 *
	 * @return 是否为 Slave 节点
	 */
	public boolean isReplica(){
		return isSlave();
	}

	public String asString(){
		final StringBuilder sb = new StringBuilder(host);

		sb.append(':').append(port);
		if(id != null){
			sb.append("[id: ").append(id).append(']');
		}

		return sb.toString();
	}

	@Override
	public String toString(){
		return asString();
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;

		result = prime * result + Objects.hashCode(host);
		result = prime * result + Objects.hashCode(port);

		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof RedisNode){
			RedisNode that = (RedisNode) obj;
			return port == that.port && Objects.equals(host, that.host) && Objects.equals(name, that.name);
		}

		return false;
	}


}
