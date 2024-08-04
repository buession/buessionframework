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

/**
 * The details about which cluster slots map to which Redis instances.
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class ClusterRedisShard extends RedisNode {

	private final static long serialVersionUID = 6399444050542251476L;

	/**
	 * 主机名称
	 */
	private String hostname;

	/**
	 * 插槽范围
	 */
	private SlotRange slots;

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 */
	public ClusterRedisShard(String host) {
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
	public ClusterRedisShard(String host, int port) {
		super(host, port);
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 * @param hostname
	 * 		主机名称
	 */
	public ClusterRedisShard(String host, String hostname) {
		super(host);
		this.hostname = hostname;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 * @param slots
	 * 		插槽范围
	 */
	public ClusterRedisShard(String host, SlotRange slots) {
		super(host);
		this.slots = slots;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 * @param hostname
	 * 		主机名称
	 * @param slots
	 * 		插槽范围
	 */
	public ClusterRedisShard(String host, String hostname, SlotRange slots) {
		this(host, hostname);
		this.slots = slots;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 * @param port
	 * 		端口
	 * @param hostname
	 * 		主机名称
	 */
	public ClusterRedisShard(String host, int port, String hostname) {
		super(host, port);
		this.hostname = hostname;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 * @param port
	 * 		端口
	 * @param slots
	 * 		插槽范围
	 */
	public ClusterRedisShard(String host, int port, SlotRange slots) {
		super(host, port);
		this.slots = slots;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 * @param port
	 * 		端口
	 * @param hostname
	 * 		主机名称
	 * @param slots
	 * 		插槽范围
	 */
	public ClusterRedisShard(String host, int port, String hostname, SlotRange slots) {
		this(host, port, hostname);
		this.slots = slots;
	}

	/**
	 * 返回主机名称
	 *
	 * @return 主机名称
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * 设置主机名称
	 *
	 * @param hostname
	 * 		主机名称
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	/**
	 * 返回插槽范围
	 *
	 * @return 插槽范围
	 */
	public SlotRange getSlots() {
		return slots;
	}

	/**
	 * 设置插槽范围
	 *
	 * @param slots
	 * 		插槽范围
	 */
	public void setSlots(SlotRange slots) {
		this.slots = slots;
	}

}
