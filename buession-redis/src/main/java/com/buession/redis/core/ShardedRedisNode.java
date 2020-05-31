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

import com.buession.core.utils.Assert;

/**
 * @author Yong.Teng
 */
public class ShardedRedisNode extends EnhanceRedisNode {

	private String password;

	private int weight;

	public ShardedRedisNode(String host, int port){
		super(host, port);
	}

	public ShardedRedisNode(String host, int port, int weight){
		super(host, port);
		setWeight(weight);
	}

	public ShardedRedisNode(String host, int port, String password){
		super(host, port);
		setPassword(password);
	}

	public ShardedRedisNode(String host, int port, int weight, String password){
		this(host, port, weight);
		setPassword(password);
	}

	public ShardedRedisNode(String host, int port, Role role){
		super(host, port, role);
	}

	public ShardedRedisNode(String host, int port, Role role, int weight){
		super(host, port, role);
		setWeight(weight);
	}

	public ShardedRedisNode(String host, int port, Role role, String password){
		super(host, port, role);
		setPassword(password);
	}

	public ShardedRedisNode(String host, int port, Role role, int weight, String password){
		this(host, port, role, weight);
		setPassword(password);
	}

	public ShardedRedisNode(String host, int port, String id, String name, Role role){
		super(host, port, id, name, role);
	}

	public ShardedRedisNode(String host, int port, String id, String name, Role role, int weight){
		super(host, port, id, name, role);
		setWeight(weight);
	}

	public ShardedRedisNode(String host, int port, String password, String id, String name, Role role){
		super(host, port, id, name, role);
		setPassword(password);
	}

	public ShardedRedisNode(String host, int port, String password, String id, String name, Role role, int weight){
		this(host, port, id, name, role, weight);
		setPassword(password);
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public int getWeight(){
		return weight;
	}

	public void setWeight(int weight){
		Assert.isNegative(weight, "Redis node weight cloud not be negative.");
		this.weight = weight;
	}

}
