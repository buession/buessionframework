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

import com.buession.core.utils.Assert;
import com.buession.lang.Status;
import com.buession.redis.core.AclLog;
import com.buession.redis.core.AclUser;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.Info;
import com.buession.redis.core.MemoryStats;
import com.buession.redis.core.Module;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.SlowLog;
import com.buession.redis.core.command.ServerCommands;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 服务端运算
 *
 * <p>详情说明 <a href="http://www.redis.cn/commands.html#server" target="_blank">http://www.redis.cn/commands.html#server</a></p>
 *
 * @author Yong.Teng
 */
public interface ServerOperations extends ServerCommands, RedisOperations {

	@Override
	default List<String> aclCat() {
		return execute((client)->client.serverOperations().aclCat());
	}

	@Override
	default List<String> aclCat(final String categoryName) {
		return execute((client)->client.serverOperations().aclCat(categoryName));
	}

	@Override
	default List<byte[]> aclCat(final byte[] categoryName) {
		return execute((client)->client.serverOperations().aclCat(categoryName));
	}

	@Override
	default Status aclSetUser(final String username, final String... rules) {
		return execute((client)->client.serverOperations().aclSetUser(username, rules));
	}

	@Override
	default Status aclSetUser(final byte[] username, final byte[]... rules) {
		return execute((client)->client.serverOperations().aclSetUser(username, rules));
	}

	@Override
	default AclUser aclGetUser(final String username) {
		return execute((client)->client.serverOperations().aclGetUser(username));
	}

	@Override
	default AclUser aclGetUser(final byte[] username) {
		return execute((client)->client.serverOperations().aclGetUser(username));
	}

	@Override
	default List<String> aclUsers() {
		return execute((client)->client.serverOperations().aclUsers());
	}

	@Override
	default String aclWhoAmI() {
		return execute((client)->client.serverOperations().aclWhoAmI());
	}

	@Override
	default Long aclDelUser(final String... usernames) {
		return execute((client)->client.serverOperations().aclDelUser(usernames));
	}

	@Override
	default Long aclDelUser(final byte[]... username) {
		return execute((client)->client.serverOperations().aclDelUser(username));
	}

	@Override
	default String aclGenPass() {
		return execute((client)->client.serverOperations().aclGenPass());
	}

	@Override
	default List<String> aclList() {
		return execute((client)->client.serverOperations().aclList());
	}

	@Override
	default Status aclLoad() {
		return execute((client)->client.serverOperations().aclLoad());
	}

	@Override
	default List<AclLog> aclLog() {
		return execute((client)->client.serverOperations().aclLog());
	}

	@Override
	default List<AclLog> aclLog(final long count) {
		return execute((client)->client.serverOperations().aclLog(count));
	}

	@Override
	default Status aclLogReset() {
		return execute((client)->client.serverOperations().aclLogReset());
	}

	@Override
	default Status aclLogSave() {
		return execute((client)->client.serverOperations().aclLogSave());
	}

	@Override
	default String bgRewriteAof() {
		return execute((client)->client.serverOperations().bgRewriteAof());
	}

	@Override
	default String bgSave() {
		return execute((client)->client.serverOperations().bgSave());
	}

	@Override
	default Status configSet(final String parameter, final String value) {
		return execute((client)->client.serverOperations().configSet(parameter, value));
	}

	@Override
	default Status configSet(final byte[] parameter, final byte[] value) {
		return execute((client)->client.serverOperations().configSet(parameter, value));
	}

	@Override
	default Status configSet(final Map<String, String> configs) {
		return execute((client)->client.serverOperations().configSet(configs));
	}

	@Override
	default Map<String, String> configGet(final String pattern) {
		return execute((client)->client.serverOperations().configGet(pattern));
	}

	@Override
	default Map<byte[], byte[]> configGet(final byte[] pattern) {
		return execute((client)->client.serverOperations().configGet(pattern));
	}

	@Override
	default Status configResetStat() {
		return execute((client)->client.serverOperations().configResetStat());
	}

	@Override
	default Status configRewrite() {
		return execute((client)->client.serverOperations().configRewrite());
	}

	@Override
	default Long dbSize() {
		return execute((client)->client.serverOperations().dbSize());
	}

	@Override
	default Status failover() {
		return execute((client)->client.serverOperations().failover());
	}

	@Override
	default Status failover(final String host, final int port) {
		return execute((client)->client.serverOperations().failover(host, port));
	}

	@Override
	default Status failover(final String host, final int port, final int timeout) {
		return execute((client)->client.serverOperations().failover(host, port, timeout));
	}

	@Override
	default Status failover(final String host, final int port, final boolean isForce, final int timeout) {
		return execute((client)->client.serverOperations().failover(host, port, isForce, timeout));
	}

	@Override
	default Status failover(final int timeout) {
		return execute((client)->client.serverOperations().failover(timeout));
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

	@Override
	default Status flushAll() {
		return execute((client)->client.serverOperations().flushAll());
	}

	@Override
	default Status flushAll(final FlushMode mode) {
		return execute((client)->client.serverOperations().flushAll(mode));
	}

	@Override
	default Status flushDb() {
		return execute((client)->client.serverOperations().flushDb());
	}

	@Override
	default Status flushDb(final FlushMode mode) {
		return execute((client)->client.serverOperations().flushDb(mode));
	}

	@Override
	default Info info() {
		return execute((client)->client.serverOperations().info());
	}

	@Override
	default Info info(final Info.Section section) {
		return execute((client)->client.serverOperations().info(section));
	}

	@Override
	default Long lastSave() {
		return execute((client)->client.serverOperations().lastSave());
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

	@Override
	default String memoryDoctor() {
		return execute((client)->client.serverOperations().memoryDoctor());
	}

	@Override
	default Status memoryPurge() {
		return execute((client)->client.serverOperations().memoryPurge());
	}

	@Override
	default MemoryStats memoryStats() {
		return execute((client)->client.serverOperations().memoryStats());
	}

	@Override
	default List<Module> moduleList() {
		return execute((client)->client.serverOperations().moduleList());
	}

	@Override
	default Status moduleLoad(final String path, final String... arguments) {
		return execute((client)->client.serverOperations().moduleLoad(path, arguments));
	}

	@Override
	default Status moduleLoad(final byte[] path, final byte[]... arguments) {
		return execute((client)->client.serverOperations().moduleLoad(path, arguments));
	}

	@Override
	default Status moduleUnLoad(final String name) {
		return execute((client)->client.serverOperations().moduleUnLoad(name));
	}

	@Override
	default Status moduleUnLoad(final byte[] name) {
		return execute((client)->client.serverOperations().moduleUnLoad(name));
	}

	@Override
	default void monitor(final RedisMonitor redisMonitor) {
		execute((client)->{
			client.serverOperations().monitor(redisMonitor);
			return null;
		});
	}

	@Override
	default Object pSync(final String replicationId, final long offset) {
		return execute((client)->client.serverOperations().pSync(replicationId, offset));
	}

	@Override
	default Object pSync(final byte[] replicationId, final long offset) {
		return execute((client)->client.serverOperations().pSync(replicationId, offset));
	}

	@Override
	default void sync() {
		execute((client)->{
			client.serverOperations().sync();
			return null;
		});
	}

	@Override
	default Status replicaOf(final String host, final int port) {
		return execute((client)->client.serverOperations().replicaOf(host, port));
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

	@Override
	default Status slaveOf(final String host, final int port) {
		return execute((client)->client.serverOperations().slaveOf(host, port));
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

	@Override
	default Role role() {
		return execute((client)->client.serverOperations().role());
	}

	@Override
	default Status save() {
		return execute((client)->client.serverOperations().save());
	}

	@Override
	default void shutdown() {
		execute((client)->{
			client.serverOperations().shutdown();
			return null;
		});
	}

	@Override
	default void shutdown(final boolean save) {
		execute((client)->{
			client.serverOperations().shutdown(save);
			return null;
		});
	}

	@Override
	default List<SlowLog> slowLogGet() {
		return execute((client)->client.serverOperations().slowLogGet());
	}

	@Override
	default List<SlowLog> slowLogGet(final long count) {
		return execute((client)->client.serverOperations().slowLogGet(count));
	}

	@Override
	default Long slowLogLen() {
		return execute((client)->client.serverOperations().slowLogLen());
	}

	@Override
	default Status slowLogReset() {
		return execute((client)->client.serverOperations().slowLogReset());
	}

	@Override
	default Status swapdb(final int db1, final int db2) {
		return execute((client)->client.serverOperations().swapdb(db1, db2));
	}

	@Override
	default RedisServerTime time() {
		return execute((client)->client.serverOperations().time());
	}

}
