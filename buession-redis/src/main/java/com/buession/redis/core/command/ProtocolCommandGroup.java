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
package com.buession.redis.core.command;

/**
 * Redis 协议命令分组
 *
 * @author Yong.Teng
 */
public enum ProtocolCommandGroup {

	/**
	 * 权限命令
	 */
	ACL("Acl"),

	/**
	 * 位图命令
	 */
	BITMAP("BitMap"),

	/**
	 * 集群命令
	 */
	CLUSTER("Cluster"),

	/**
	 * 连接命令
	 */
	CONNECTION("Connection"),

	/**
	 * 地理位置命令
	 */
	GEO("Geo"),

	/**
	 * 哈希命令
	 */
	HASH("Hash"),

	/**
	 * HyperLogLog 命令
	 */
	HYPERLOGLOG("HyperLogLog"),

	/**
	 * 键命令
	 */
	KEY("Key"),

	/**
	 * 列表命令
	 */
	LIST("List"),

	/**
	 * 发布订阅命令
	 */
	PUBSUB("PubSub"),

	/**
	 * 脚本命令
	 */
	SCRIPTING("Scripting"),

	/**
	 * 服务器命令
	 */
	SERVER("Server"),

	/**
	 * 集合命令
	 */
	SET("Set"),

	/**
	 * 有序集合命令
	 */
	SORTEDSET("Sorted Set"),

	/**
	 * 流命令
	 */
	STREAM("Stream"),

	/**
	 * 字符串命令
	 */
	STRING("String"),

	/**
	 * 事务命令
	 */
	TRANSACTION("Transaction");

	private final String name;

	ProtocolCommandGroup(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Deprecated
	public String getValue() {
		return getName();
	}

	@Override
	public String toString() {
		return getName();
	}

}
