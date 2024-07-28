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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core;

import com.buession.core.NamedNode;

/**
 * 带有名称的 Redis 节点
 *
 * @author Yong.Teng
 */
public class RedisNamedNode extends RedisNode implements NamedNode {

	private final static long serialVersionUID = -9142375093229773814L;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 */
	public RedisNamedNode(final String host) {
		super(host);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 * @param port
	 * 		端口
	 */
	public RedisNamedNode(final String host, final int port) {
		super(host, port);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 * @param name
	 * 		名称
	 */
	public RedisNamedNode(final String host, final String name) {
		super(host);
		this.name = name;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 * @param port
	 * 		端口
	 */
	public RedisNamedNode(final String host, final int port, final String name) {
		super(host, port);
		this.name = name;
	}

	/**
	 * 返回名称
	 *
	 * @return 名称
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 *
	 * @param name
	 * 		名称
	 */
	public void setName(String name) {
		this.name = name;
	}

}
