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
public class EnhanceRedisNode extends GenericRedisNode implements NamedNode {

	private String id;

	private String name;

	private Role role;

	public EnhanceRedisNode(){
		super();
	}

	public EnhanceRedisNode(String host, int port){
		super(host, port);
	}

	public EnhanceRedisNode(String host, int port, String name){
		super(host, port);
		this.name = name;
	}

	public EnhanceRedisNode(String host, int port, Role role){
		super(host, port);
		this.role = role;
	}

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

	@Override
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public Role getRole(){
		return role;
	}

	public void setRole(Role role){
		this.role = role;
	}

	public boolean isMaster(){
		return ObjectUtils.nullSafeEquals(getRole(), Role.MASTER);
	}

	public boolean isSlave(){
		return ObjectUtils.nullSafeEquals(getRole(), Role.SLAVE);
	}

	public boolean isReplica(){
		return isSlave();
	}

	@Override
	public String asString(){
		return name + ", " + super.asString();
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
