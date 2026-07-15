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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection;

import com.buession.net.HostAndPort;

/**
 * Redis 节点
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class RedisNode extends HostAndPort {

	private final static long serialVersionUID = 5460272093066382186L;

	public final static String DEFAULT_HOST = com.buession.redis.core.RedisNode.DEFAULT_HOST;

	public final static int DEFAULT_PORT = com.buession.redis.core.RedisNode.DEFAULT_PORT;

	/**
	 * 构造函数，设置 Redis 默认地址和默认端口
	 */
	public RedisNode() {
		super(DEFAULT_HOST, DEFAULT_PORT);
	}

	/**
	 * 构造函数，设置默认端口
	 *
	 * @param host
	 * 		Redis 地址
	 */
	public RedisNode(String host) {
		super(host, DEFAULT_PORT);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Redis 地址
	 * @param port
	 * 		Redis 端口
	 */
	public RedisNode(String host, int port) {
		super(host, port);
	}

}
