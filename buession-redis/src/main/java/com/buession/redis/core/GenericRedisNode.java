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
 * @author Yong.Teng
 */
public class GenericRedisNode implements RedisNode {

	/**
	 * Redis 服务器主机地址
	 */
	private String host;

	/**
	 * Redis 服务器主机端口
	 */
	private int port;

	public GenericRedisNode(){
	}

	public GenericRedisNode(String host, int port){
		this.host = host;
		this.port = port;
	}

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

	public String asString(){
		return host + ":" + port;
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

		if(obj == null || (obj instanceof GenericRedisNode) == false){
			return false;
		}

		GenericRedisNode that = (GenericRedisNode) obj;

		if(ObjectUtils.nullSafeEquals(this.host, that.host) == false){
			return false;
		}

		if(ObjectUtils.nullSafeEquals(this.port, that.port) == false){
			return false;
		}

		return true;
	}

}
