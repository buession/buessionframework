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

import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

/**
 * @author Yong.Teng
 */
public class RedisNode implements NamedNode {

	@Nullable
	private String id;

	@Nullable
	private String name;

	@Nullable
	private String host;

	private int port;

	@Nullable
	private Role type;

	@Nullable
	private String masterId;

	public RedisNode(@Nullable String host, int port){
		this.host = host;
		this.port = port;
	}

	public RedisNode(@Nullable String id, @Nullable String name, @Nullable String host, int port, @Nullable Role type){
		this(host, port);
		this.id = id;
		this.name = name;
		this.type = type;
	}

	@Nullable
	public String getId(){
		return id;
	}

	public void setId(@Nullable String id){
		this.id = id;
	}

	@Override
	@Nullable
	public String getName(){
		return name;
	}

	public void setName(@Nullable String name){
		this.name = name;
	}

	@Nullable
	public String getHost(){
		return host;
	}

	public void setHost(@Nullable String host){
		this.host = host;
	}

	public int getPort(){
		return port;
	}

	public void setPort(int port){
		this.port = port;
	}

	@Nullable
	public Role getType(){
		return type;
	}

	public void setType(@Nullable Role type){
		this.type = type;
	}

	@Nullable
	public String getMasterId(){
		return masterId;
	}

	public void setMasterId(@Nullable String masterId){
		this.masterId = masterId;
	}

	public boolean isMaster(){
		return ObjectUtils.nullSafeEquals(getType(), Role.MASTER);
	}

	public boolean isSlave(){
		return ObjectUtils.nullSafeEquals(getType(), Role.SLAVE);
	}

	public boolean isReplica(){
		return isSlave();
	}

	@Override
	public String toString(){
		return host + ":" + port;
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

		if(ObjectUtils.nullSafeEquals(this.name, that.name) == false){
			return false;
		}

		return true;
	}

}
