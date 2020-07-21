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

import java.io.Serializable;

/**
 * Redis 节点
 *
 * @author Yong.Teng
 */
public class RedisNode implements Serializable {

	private final static long serialVersionUID = -2212702986712034274L;

	public final static String DEFAULT_HOST = "localhost";

	public final static int DEFAULT_PORT = 6379;

	public final static int DEFAULT_SENTINEL_PORT = 26379;

	public final static int DEFAULT_DATABASE = 0;

	/**
	 * Redis 服务器主机地址
	 */
	private String host = DEFAULT_HOST;

	/**
	 * Redis 服务器主机端口
	 */
	private int port = DEFAULT_PORT;

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 */
	public RedisNode(String host){
		this.host = host;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 节点地址
	 * @param port
	 * 		Redis 节点端口
	 */
	public RedisNode(String host, int port){
		this.host = host;
		this.port = port;
	}

	/**
	 * 获取 Redis 服务器主机地址
	 *
	 * @return Redis 服务器主机地址
	 */
	public String getHost(){
		return host;
	}

	/**
	 * 获取 Redis 服务器主机端口
	 *
	 * @return Redis 服务器主机端口
	 */
	public int getPort(){
		return port;
	}

	public String asString(){
		final StringBuilder sb = new StringBuilder(16);

		sb.append(host).append(':').append(port);

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

		result = prime * result + ObjectUtils.nullSafeHashCode(host);
		result = prime * result + ObjectUtils.nullSafeHashCode(port);

		return result;
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj == null || (obj instanceof RedisNode) == false){
			return false;
		}

		RedisNode that = (RedisNode) obj;

		if(ObjectUtils.nullSafeEquals(this.host, that.host) == false){
			return false;
		}

		if(ObjectUtils.nullSafeEquals(this.port, that.port) == false){
			return false;
		}

		return true;
	}

}
