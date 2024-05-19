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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command;

import com.buession.lang.Status;
import com.buession.redis.core.AclLog;
import com.buession.redis.core.FlushMode;
import com.buession.redis.core.Info;
import com.buession.redis.core.MemoryStats;
import com.buession.redis.core.Module;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.SlowLog;
import com.buession.redis.core.AclUser;

import java.util.List;

/**
 * 服务端命令
 *
 * <p>详情说明 <a href="http://www.redis.cn/commands.html#server" target="_blank">http://www.redis.cn/commands.html#server</a></p>
 *
 * @author Yong.Teng
 */
public interface ServerCommands extends RedisCommands {

	/**
	 * The command shows the available ACL categories
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-cat/" target="_blank">https://redis.io/commands/acl-cat/</a></p>
	 *
	 * @return A list of ACL categories or a list of commands inside a given category
	 */
	List<String> aclCat();

	/**
	 * The command shows all the Redis commands in the specified category
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-cat/" target="_blank">https://redis.io/commands/acl-cat/</a></p>
	 *
	 * @param categoryName
	 * 		Category Name
	 *
	 * @return A list of ACL categories or a list of commands inside a given category
	 */
	List<String> aclCat(final String categoryName);

	/**
	 * The command shows all the Redis commands in the specified category
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-cat/" target="_blank">https://redis.io/commands/acl-cat/</a></p>
	 *
	 * @param categoryName
	 * 		Category Name
	 *
	 * @return A list of ACL categories or a list of commands inside a given category
	 */
	List<byte[]> aclCat(final byte[] categoryName);

	/**
	 * Create an ACL user with the specified rules or modify the rules of an existing user
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-setuser/" target="_blank">https://redis.io/commands/acl-setuser/</a></p>
	 *
	 * @param username
	 * 		用户名
	 * @param rules
	 * 		the specified rules
	 *
	 * @return 操作成功，返回 Status.Success；否则，返回 Status.Failure
	 */
	Status aclSetUser(final String username, final String... rules);

	/**
	 * Create an ACL user with the specified rules or modify the rules of an existing user
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-setuser/" target="_blank">https://redis.io/commands/acl-setuser/</a></p>
	 *
	 * @param username
	 * 		用户名
	 * @param rules
	 * 		the specified rules
	 *
	 * @return 操作成功，返回 Status.Success；否则，返回 Status.Failure
	 */
	Status aclSetUser(final byte[] username, final byte[]... rules);

	/**
	 * The command returns all the rules defined for an existing ACL user
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-getuser/" target="_blank">https://redis.io/commands/acl-getuser/</a></p>
	 *
	 * @param username
	 * 		用户名
	 *
	 * @return A list of ACL rule definitions for the user
	 */
	AclUser aclGetUser(final String username);

	/**
	 * The command returns all the rules defined for an existing ACL user
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-getuser/" target="_blank">https://redis.io/commands/acl-getuser/</a></p>
	 *
	 * @param username
	 * 		用户名
	 *
	 * @return A list of ACL rule definitions for the user
	 */
	AclUser aclGetUser(final byte[] username);

	/**
	 * The command shows a list of all the usernames of the currently configured users in the Redis ACL system
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-users/" target="_blank">https://redis.io/commands/acl-users/</a></p>
	 *
	 * @return A list of all the usernames of the currently configured users in the Redis ACL system
	 */
	List<String> aclUsers();

	/**
	 * Return the username the current connection is authenticated with
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-whoami/" target="_blank">https://redis.io/commands/acl-whoami/</a></p>
	 *
	 * @return The username of the current connection
	 */
	String aclWhoAmI();

	/**
	 * Delete all the specified ACL users and terminate all the connections that are authenticated with such users
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-deluser/" target="_blank">https://redis.io/commands/acl-deluser/</a></p>
	 *
	 * @param usernames
	 * 		用户名
	 *
	 * @return 删除用户数量
	 */
	Long aclDelUser(final String... usernames);

	/**
	 * Delete all the specified ACL users and terminate all the connections that are authenticated with such users
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-deluser/" target="_blank">https://redis.io/commands/acl-deluser/</a></p>
	 *
	 * @param usernames
	 * 		用户名
	 *
	 * @return 删除用户数量
	 */
	Long aclDelUser(final byte[]... usernames);

	/**
	 * ACL users need a solid password in order to authenticate to the server without security risks
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-genpass/" target="_blank">https://redis.io/commands/acl-genpass/</a></p>
	 *
	 * @return By default 64 bytes string representing 256 bits of pseudorandom data
	 */
	String aclGenPass();

	/**
	 * The command shows the currently active ACL rules in the Redis server
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-list/" target="_blank">https://redis.io/commands/acl-list/</a></p>
	 *
	 * @return By default 64 bytes string representing 256 bits of pseudorandom data
	 */
	List<String> aclList();

	/**
	 * When Redis is configured to use an ACL file (with the aclfile configuration option),
	 * this command will reload the ACLs from the file, replacing all the current ACL rules with the ones defined in the file
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-load/" target="_blank">https://redis.io/commands/acl-load/</a></p>
	 *
	 * @return Status.SUCCESS
	 */
	Status aclLoad();

	/**
	 * The optional argument specifies how many entries to show. By default up to ten failures are returned
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-log/" target="_blank">https://redis.io/commands/acl-log/</a></p>
	 *
	 * @return A list of ACL security events
	 */
	List<AclLog> aclLog();

	/**
	 * The optional argument specifies how many entries to show. By default up to ten failures are returned
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-log/" target="_blank">https://redis.io/commands/acl-log/</a></p>
	 *
	 * @param count
	 * 		返回数量
	 *
	 * @return A list of ACL security events
	 */
	List<AclLog> aclLog(final long count);

	/**
	 * The optional argument specifies how many entries to show. By default up to ten failures are returned
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-log/" target="_blank">https://redis.io/commands/acl-log/</a></p>
	 *
	 * @return 日志重置成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status aclLogReset();

	/**
	 * When Redis is configured to use an ACL file (with the aclfile configuration option),
	 * this command will save the currently defined ACLs from the server memory to the ACL file
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/acl-save/" target="_blank">https://redis.io/commands/acl-save/</a></p>
	 *
	 * @return 保存成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status aclLogSave();

	/**
	 * 执行一个 AOF文件 重写操作；重写会创建一个当前 AOF 文件的体积优化版本
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/persistence/bgrewriteaof.html" target="_blank">http://redisdoc.com/persistence/bgrewriteaof.html</a></p>
	 *
	 * @return 反馈信息
	 */
	String bgRewriteAof();

	/**
	 * 在后台异步保存当前数据库的数据到磁盘
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/persistence/bgsave.html" target="_blank">http://redisdoc.com/persistence/bgsave.html</a></p>
	 *
	 * @return 反馈信息
	 */
	String bgSave();

	/**
	 * 动态地调整 Redis 服务器的配置
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc.com/configure/config_set.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 * @param value
	 * 		配置值
	 *
	 * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status configSet(final String parameter, final String value);

	/**
	 * 动态地调整 Redis 服务器的配置
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc.com/configure/config_set.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 * @param value
	 * 		配置值
	 *
	 * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status configSet(final byte[] parameter, final byte[] value);

	/**
	 * 获取 Redis 服务器的配置参数
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/configure/config_get.html" target="_blank">http://redisdoc.com/configure/config_get.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 *
	 * @return 给定配置参数的值
	 */
	List<String> configGet(final String parameter);

	/**
	 * 获取 Redis 服务器的配置参数
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/configure/config_get.html" target="_blank">http://redisdoc.com/configure/config_get.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 *
	 * @return 给定配置参数的值
	 */
	List<byte[]> configGet(final byte[] parameter);

	/**
	 * 重置 INFO 命令中的某些统计数据，包括：
	 * 1）Keyspace hits (键空间命中次数)
	 * 2）Keyspace misses (键空间不命中次数)
	 * 3）Number of commands processed (执行命令的次数)
	 * 4）Number of connections received (连接服务器的次数)
	 * 5）Number of expired keys (过期key的数量)
	 * 6）Number of rejected connections (被拒绝的连接数量)
	 * 7）Latest fork(2) time(最后执行 fork(2) 的时间)
	 * 8）The aof_delayed_fsync counter(aof_delayed_fsync 计数器的值)
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/configure/config_resetstat.html" target="_blank">http://redisdoc.com/configure/config_resetstat.html</a></p>
	 *
	 * @return 总是返回 Status.SUCCESS
	 */
	Status configResetStat();

	/**
	 * 对启动 Redis 服务器时所指定的 redis.conf 文件进行改写
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/configure/config_rewrite.html" target="_blank">http://redisdoc.com/configure/config_rewrite.html</a></p>
	 *
	 * @return 如果配置重写成功则，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status configRewrite();

	/**
	 * 获取数据库的 key 的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/dbsize.html" target="_blank">http://redisdoc.com/database/dbsize.html</a></p>
	 *
	 * @return 数据库的 key 的数量
	 */
	Long dbSize();

	/**
	 * This command will start a coordinated failover between the currently-connected-to master and one of its replicas
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/failover/" target="_blank">https://redis.io/commands/failover/</a></p>
	 *
	 * @return Status.SUCCESS if the command was accepted and a coordinated failover is in progress
	 */
	Status failover();

	/**
	 * This command will start a coordinated failover between the currently-connected-to master and one of its replicas
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/failover/" target="_blank">https://redis.io/commands/failover/</a></p>
	 *
	 * @param host
	 * 		主机地址
	 * @param port
	 * 		端口
	 *
	 * @return Status.SUCCESS if the command was accepted and a coordinated failover is in progress
	 */
	Status failover(final String host, final int port);

	/**
	 * This command will start a coordinated failover between the currently-connected-to master and one of its replicas
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/failover/" target="_blank">https://redis.io/commands/failover/</a></p>
	 *
	 * @param host
	 * 		主机地址
	 * @param port
	 * 		端口
	 * @param timeout
	 * 		超时（单位：毫秒）
	 *
	 * @return Status.SUCCESS if the command was accepted and a coordinated failover is in progress
	 */
	Status failover(final String host, final int port, final int timeout);

	/**
	 * This command will start a coordinated failover between the currently-connected-to master and one of its replicas
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/failover/" target="_blank">https://redis.io/commands/failover/</a></p>
	 *
	 * @param host
	 * 		主机地址
	 * @param port
	 * 		端口
	 * @param isForce
	 * 		是否强制
	 * @param timeout
	 * 		超时（单位：毫秒）
	 *
	 * @return Status.SUCCESS if the command was accepted and a coordinated failover is in progress
	 */
	Status failover(final String host, final int port, final boolean isForce, final int timeout);

	/**
	 * This command will start a coordinated failover between the currently-connected-to master and one of its replicas
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/failover/" target="_blank">https://redis.io/commands/failover/</a></p>
	 *
	 * @param timeout
	 * 		超时（单位：毫秒）
	 *
	 * @return Status.SUCCESS if the command was accepted and a coordinated failover is in progress
	 */
	Status failover(final int timeout);

	/**
	 * 清空整个 Redis 服务器的数据（删除所有数据库的所有 key ）
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/flushall.html" target="_blank">http://redisdoc.com/database/flushallhtml</a></p>
	 *
	 * @return 始终返回 Status.SUCCESS
	 */
	Status flushAll();

	/**
	 * 清空整个 Redis 服务器的数据（删除所有数据库的所有 key ）
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/flushall.html" target="_blank">http://redisdoc.com/database/flushallhtml</a></p>
	 *
	 * @param mode
	 * 		刷新模式
	 *
	 * @return 始终返回 Status.SUCCESS
	 */
	Status flushAll(final FlushMode mode);

	/**
	 * 清空当前数据库中的所有 key
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/flushdb.html" target="_blank">http://redisdoc.com/database/flushdb.html</a></p>
	 *
	 * @return 始终返回 Status.SUCCESS
	 */
	Status flushDb();

	/**
	 * 清空当前数据库中的所有 key
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/flushdb.html" target="_blank">http://redisdoc.com/database/flushdb.html</a></p>
	 *
	 * @param mode
	 * 		刷新模式
	 *
	 * @return 始终返回 Status.SUCCESS
	 */
	Status flushDb(final FlushMode mode);

	/**
	 * 获取关于 Redis 服务器的各种信息和统计数值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/info.html" target="_blank">http://redisdoc.com/client_and_server/info.html</a></p>
	 *
	 * @return 关于 Redis 服务器的各种信息和统计数值
	 */
	Info info();

	/**
	 * 获取关于 Redis 服务器通过 section 指定的部分信息
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/info.html" target="_blank">http://redisdoc.com/client_and_server/info.html</a></p>
	 *
	 * @param section
	 * 		InfoSection
	 *
	 * @return 关于 Redis 服务器的各种信息和统计数值
	 */
	Info info(final Info.Section section);

	/**
	 * 获取最近一次 Redis 成功将数据保存到磁盘上的时间，以 UNIX 时间戳格式表示
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/persistence/lastsave.html" target="_blank">http://redisdoc.com/persistence/lastsave.html</a></p>
	 *
	 * @return 最近一次成功将数据保存到磁盘上的时间
	 */
	Long lastSave();

	/**
	 * 列出 Redis 服务器遇到的不同类型的内存相关问题，并提供相应的解决建议
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/memory-doctor/" target="_blank">https://redis.io/commands/memory-doctor/</a></p>
	 *
	 * @return Redis 服务器遇到的不同类型的内存相关问题，以及解决建议
	 */
	String memoryDoctor();

	/**
	 * The MEMORY PURGE command attempts to purge dirty pages so these can be reclaimed by the allocator
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/memory-purge/" target="_blank">https://redis.io/commands/memory-purge/</a></p>
	 *
	 * @return 操作成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status memoryPurge();

	/**
	 * The MEMORY STATS command returns an Array reply about the memory usage of the server
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/memory-stats/" target="_blank">https://redis.io/commands/memory-stats/</a></p>
	 *
	 * @return nested list of memory usage metrics and their values
	 */
	MemoryStats memoryStats();

	/**
	 * The MEMORY USAGE command reports the number of bytes that a key and its value require to be stored in RAM
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/memory-usage/" target="_blank">https://redis.io/commands/memory-usage/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return the memory usage in bytes
	 */
	Long memoryUsage(final String key);

	/**
	 * The MEMORY USAGE command reports the number of bytes that a key and its value require to be stored in RAM
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/memory-usage/" target="_blank">https://redis.io/commands/memory-usage/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return the memory usage in bytes
	 */
	Long memoryUsage(final byte[] key);

	/**
	 * The MEMORY USAGE command reports the number of bytes that a key and its value require to be stored in RAM
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/memory-usage/" target="_blank">https://redis.io/commands/memory-usage/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param samples
	 * 		Samples count
	 *
	 * @return the memory usage in bytes
	 */
	Long memoryUsage(final String key, final int samples);

	/**
	 * The MEMORY USAGE command reports the number of bytes that a key and its value require to be stored in RAM
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/memory-usage/" target="_blank">https://redis.io/commands/memory-usage/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param samples
	 * 		Samples count
	 *
	 * @return the memory usage in bytes
	 */
	Long memoryUsage(final byte[] key, final int samples);

	/**
	 * Returns information about the modules loaded to the server
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/module-list/" target="_blank">https://redis.io/commands/module-list/</a></p>
	 *
	 * @return A list of loaded modules
	 */
	List<Module> moduleList();

	/**
	 * Returns information about the modules loaded to the server
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/module-load/" target="_blank">https://redis.io/commands/module-load/</a></p>
	 *
	 * @param path
	 * 		Module Path
	 * @param arguments
	 * 		Arguments
	 *
	 * @return A list of loaded modules
	 */
	Status moduleLoad(final String path, final String... arguments);

	/**
	 * Returns information about the modules loaded to the server
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/module-load/" target="_blank">https://redis.io/commands/module-load/</a></p>
	 *
	 * @param path
	 * 		Module Path
	 * @param arguments
	 * 		Arguments
	 *
	 * @return A list of loaded modules
	 */
	Status moduleLoad(final byte[] path, final byte[]... arguments);

	/**
	 * This command unloads the module specified by name
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/module-unload/" target="_blank">https://redis.io/commands/module-unload/</a></p>
	 *
	 * @param name
	 * 		Module Name
	 *
	 * @return 操作成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status moduleUnLoad(final String name);

	/**
	 * This command unloads the module specified by name
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/module-unload/" target="_blank">https://redis.io/commands/module-unload/</a></p>
	 *
	 * @param name
	 * 		Module Name
	 *
	 * @return 操作成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status moduleUnLoad(final byte[] name);

	/**
	 * 实时打印出 Redis 服务器接收到的命令
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/debug/monitor.html" target="_blank">http://redisdoc.com/debug/monitor.html</a></p>
	 *
	 * @param redisMonitor
	 * 		Redis Monitor
	 */
	void monitor(final RedisMonitor redisMonitor);

	/**
	 * 用于复制功能(replication)的内部命令
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/psync.html" target="_blank">http://redisdoc.com/internal/psync.html</a></p>
	 *
	 * @param replicationId
	 * 		Master Run Id
	 * @param offset
	 * 		偏移量
	 *
	 * @return 序列化数据
	 */
	Object pSync(final String replicationId, final long offset);

	/**
	 * 用于复制功能(replication)的内部命令
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/psync.html" target="_blank">http://redisdoc.com/internal/psync.html</a></p>
	 *
	 * @param replicationId
	 * 		Master Run Id
	 * @param offset
	 * 		偏移量
	 *
	 * @return 序列化数据
	 */
	Object pSync(final byte[] replicationId, final long offset);

	/**
	 * 用于复制功能(replication)的内部命令
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/sync.html" target="_blank">http://redisdoc.com/internal/sync.html</a></p>
	 */
	void sync();

	/**
	 * 可以在线修改当前服务器的复制设置
	 * 如果当前服务器已经是副本服务器，会将当前服务器转变为某一服务器的副本服务器；
	 * 如果当前服务器已经是某个主服务器的副本服务器，那么将使当前服务器停止对原主服务器的同步，丢弃旧数据集，转而开始对新主服务器进行同步
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/replicaof/" target="_blank">https://redis.io/commands/replicaof/</a></p>
	 *
	 * @param host
	 * 		Redis 主机地址
	 * @param port
	 * 		Redis 端口
	 *
	 * @return 操作结果
	 */
	Status replicaOf(final String host, final int port);

	/**
	 * 用于在 Redis 运行时动态地修改复制(replication)功能的行为；
	 * 可以将当前服务器转变为指定服务器的从属服务器(slave server)
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/replication/slaveof.html" target="_blank">http://redisdoc.com/replication/slaveof.html</a></p>
	 *
	 * @param host
	 * 		Redis Slave Server 主机地址
	 * @param port
	 * 		Redis Slave Server 端口
	 *
	 * @return 总是返回 Status.SUCCESS
	 */
	Status slaveOf(final String host, final int port);

	/**
	 * 获取实例在复制中担任的角色信息
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/replication/role.html" target="_blank">http://redisdoc.com/replication/role.html</a></p>
	 *
	 * @return 实例在复制中担任的角色信息
	 */
	Role role();

	/**
	 * 执行一个同步保存操作，将当前 Redis 实例的所有数据快照(snapshot)以 RDB 文件的形式保存到硬盘；
	 * 该操作，因为它会阻塞所有客户端
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/persistence/save.html" target="_blank">http://redisdoc.com/persistence/save.html</a></p>
	 *
	 * @return 保存成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status save();

	/**
	 * SHUTDOWN 命令执行以下操作：
	 * 1）停止所有客户端
	 * 2）如果有至少一个保存点在等待，执行 SAVE 命令
	 * 3）如果 AOF 选项被打开，更新 AOF 文件
	 * 4）关闭 redis 服务器
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/shutdown.html" target="_blank">http://redisdoc.com/client_and_server/shutdown.html</a></p>
	 */
	void shutdown();

	/**
	 * SHUTDOWN 命令执行以下操作：
	 * 1）停止所有客户端
	 * 2）如果有至少一个保存点在等待，执行 SAVE 命令
	 * 3）如果 AOF 选项被打开，更新 AOF 文件
	 * 4）关闭 redis 服务器(server)
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/shutdown.html" target="_blank">http://redisdoc.com/client_and_server/shutdown.html</a></p>
	 *
	 * @param save
	 * 		是否强制让数据库执行保存操作
	 */
	void shutdown(final boolean save);

	/**
	 * The SLOWLOG GET command returns entries from the slow log in chronological order
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/slowlog-get/" target="_blank">https://redis.io/commands/slowlog-get/</a></p>
	 *
	 * @return A list of slow log entries
	 */
	List<SlowLog> slowLogGet();

	/**
	 * The SLOWLOG GET command returns entries from the slow log in chronological order
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/slowlog-get/" target="_blank">https://redis.io/commands/slowlog-get/</a></p>
	 *
	 * @param count
	 * 		返回条数
	 *
	 * @return A list of slow log entries
	 */
	List<SlowLog> slowLogGet(final long count);

	/**
	 * This command returns the current number of entries in the slow log
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/slowlog-len/" target="_blank">https://redis.io/commands/slowlog-len/</a></p>
	 *
	 * @return The number of entries in the slow log
	 */
	Long slowLogLen();

	/**
	 * This command resets the slow log, clearing all entries in it
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/slowlog-reset/" target="_blank">https://redis.io/commands/slowlog-reset/</a></p>
	 *
	 * @return 操作成功，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status slowLogReset();

	/**
	 * 对换指定的两个数据库，使得两个数据库的数据立即互换
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/swapdb.html" target="_blank">http://redisdoc.com/database/swapdb.html</a></p>
	 *
	 * @param db1
	 * 		数据库 1 索引号
	 * @param db2
	 * 		数据库 2 索引号
	 *
	 * @return 对换成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status swapdb(final int db1, final int db2);

	/**
	 * 获取当前服务器时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/time.html" target="_blank">http://redisdoc.com/client_and_server/time.html</a></p>
	 *
	 * @return 当前服务器时间
	 */
	RedisServerTime time();

}
