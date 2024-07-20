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
package com.buession.redis.core.operations;

import com.buession.core.converter.BinaryEnumConverter;
import com.buession.core.converter.EnumConverter;
import com.buession.core.utils.Assert;
import com.buession.lang.Status;
import com.buession.redis.core.AclCategory;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.command.ServerCommands;

import java.util.Date;
import java.util.List;

/**
 * 服务端运算
 *
 * <p>详情说明 <a href="http://www.redis.cn/commands.html#server" target="_blank">http://www.redis.cn/commands.html#server</a></p>
 *
 * @author Yong.Teng
 */
public interface ServerOperations extends ServerCommands, RedisOperations {

	/**
	 * The command shows all the Redis commands in the specified category
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-cat/" target="_blank">https://redis.io/commands/acl-cat/</a></p>
	 *
	 * @param categoryName
	 * 		Category Name
	 *
	 * @return A list of ACL categories or a list of commands inside a given category
	 *
	 * @since 3.0.0
	 */
	default List<ProtocolCommand> aclCat(final String categoryName) {
		final AclCategory aclCategory = (new EnumConverter<>(AclCategory.class)).convert(categoryName);
		return aclCat(aclCategory);
	}

	/**
	 * The command shows all the Redis commands in the specified category
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-cat/" target="_blank">https://redis.io/commands/acl-cat/</a></p>
	 *
	 * @param categoryName
	 * 		Category Name
	 *
	 * @return A list of ACL categories or a list of commands inside a given category
	 *
	 * @since 3.0.0
	 */
	default List<ProtocolCommand> aclCat(final byte[] categoryName) {
		final AclCategory aclCategory = (new BinaryEnumConverter<>(AclCategory.class)).convert(categoryName);
		return aclCat(aclCategory);
	}

	/**
	 * This command will start a coordinated failover between the currently-connected-to master and one of its replicas
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/failover/" target="_blank">https://redis.io/commands/failover/</a></p>
	 *
	 * @param server
	 * 		Redis 主机
	 *
	 * @return Status.SUCCESS if the command was accepted and a coordinated failover is in progress
	 */
	default Status failover(final RedisNode server) {
		Assert.isNull(server, "Redis server cloud not be null");
		return failover(server.getHost(), server.getPort());
	}

	/**
	 * This command will start a coordinated failover between the currently-connected-to master and one of its replicas
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/failover/" target="_blank">https://redis.io/commands/failover/</a></p>
	 *
	 * @param server
	 * 		Redis 主机
	 * @param timeout
	 * 		超时（单位：毫秒）
	 *
	 * @return Status.SUCCESS if the command was accepted and a coordinated failover is in progress
	 */
	default Status failover(final RedisNode server, final int timeout) {
		Assert.isNull(server, "Redis server cloud not be null");
		return failover(server.getHost(), server.getPort(), timeout);
	}

	/**
	 * This command will start a coordinated failover between the currently-connected-to master and one of its replicas
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/failover/" target="_blank">https://redis.io/commands/failover/</a></p>
	 *
	 * @param server
	 * 		Redis 主机
	 * @param isForce
	 * 		是否强制
	 * @param timeout
	 * 		超时（单位：毫秒）
	 *
	 * @return Status.SUCCESS if the command was accepted and a coordinated failover is in progress
	 */
	default Status failover(final RedisNode server, final boolean isForce, final int timeout) {
		Assert.isNull(server, "Redis server cloud not be null");
		return failover(server.getHost(), server.getPort(), isForce, timeout);
	}

	/**
	 * 获取最近一次 Redis 成功将数据保存到磁盘上的时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/persistence/lastsave.html" target="_blank">http://redisdoc.com/persistence/lastsave.html</a></p>
	 *
	 * @return 最近一次成功将数据保存到磁盘上的时间
	 */
	default Date lastSaveAt() {
		return new Date(lastSave());
	}

	/**
	 * 用于在 Redis 运行时动态地修改复制(replication)功能的行为；
	 * 可以将当前服务器转变为指定服务器的从属服务器(slave server)
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/replicaof/" target="_blank">https://redis.io/commands/replicaof/</a></p>
	 *
	 * @param host
	 * 		Redis Slave Server 主机地址
	 *
	 * @return 总是返回 Status.SUCCESS
	 */
	default Status replicaOf(final String host) {
		return replicaOf(host, RedisNode.DEFAULT_PORT);
	}

	/**
	 * 可以在线修改当前服务器的复制设置
	 * 如果当前服务器已经是副本服务器，会将当前服务器转变为某一服务器的副本服务器；
	 * 如果当前服务器已经是某个主服务器的副本服务器，那么将使当前服务器停止对原主服务器的同步，丢弃旧数据集，转而开始对新主服务器进行同步
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/replicaof/" target="_blank">https://redis.io/commands/replicaof/</a></p>
	 *
	 * @param server
	 * 		Redis 主机
	 *
	 * @return 操作结果
	 */
	default Status replicaOf(final RedisNode server) {
		Assert.isNull(server, "Redis server cloud not be null.");
		return replicaOf(server.getHost(), server.getPort());
	}

	/**
	 * 用于在 Redis 运行时动态地修改复制(replication)功能的行为；
	 * 可以将当前服务器转变为指定服务器的从属服务器(slave server)
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/replication/slaveof.html" target="_blank">http://redisdoc.com/replication/slaveof.html</a></p>
	 *
	 * @param host
	 * 		Redis Slave Server 主机地址
	 *
	 * @return 总是返回 Status.SUCCESS
	 */
	default Status slaveOf(final String host) {
		return slaveOf(host, RedisNode.DEFAULT_PORT);
	}

	/**
	 * 用于在 Redis 运行时动态地修改复制(replication)功能的行为；
	 * 可以将当前服务器转变为指定服务器的从属服务器(slave server)
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/replication/slaveof.html" target="_blank">http://redisdoc.com/replication/slaveof.html</a></p>
	 *
	 * @param server
	 * 		Redis Slave Server 主机地址
	 *
	 * @return 总是返回 Status.SUCCESS
	 */
	default Status slaveOf(final RedisNode server) {
		Assert.isNull(server, "Redis server cloud not be null.");
		return replicaOf(server.getHost(), server.getPort());
	}

}
