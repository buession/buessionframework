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
package com.buession.redis.core.command;

import com.buession.lang.Status;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.Info;
import com.buession.redis.core.ObjectCommand;
import com.buession.redis.core.RedisMonitor;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.Role;
import com.buession.redis.core.SlowLogCommand;

import java.util.List;

/**
 * 服务端命令
 *
 * <p>详情说明
 * <a href="http://www.redis.cn/commands.html#server" target="_blank">http://www.redis.cn/commands.html#server</a></p>
 *
 * @author Yong.Teng
 */
public interface ServerCommands extends RedisCommands {

	/**
	 * 执行一个 AOF文件 重写操作；重写会创建一个当前 AOF 文件的体积优化版本
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/persistence/bgrewriteaof.html" target="_blank">http://redisdoc.com/persistence/bgrewriteaof.html</a></p>
	 *
	 * @return 反馈信息
	 */
	String bgRewriteAof();

	/**
	 * 在后台异步保存当前数据库的数据到磁盘
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/persistence/bgsave.html" target="_blank">http://redisdoc
	 * .com/persistence/bgsave.html</a></p>
	 *
	 * @return 反馈信息
	 */
	String bgSave();

	/**
	 * 关闭地址为 host:port 的客户端
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/client_and_server/client_kill.html" target="_blank">http://redisdoc
	 * .com/client_and_server/client_kill.html</a></p>
	 *
	 * @param host
	 * 		客户端地址
	 * @param port
	 * 		客户端端口
	 *
	 * @return 当指定的客户端存在，且被成功关闭时，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status clientKill(final String host, final int port);

	/**
	 * 获取连接时设置的名字
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_getname.html" target="_blank">http://redisdoc
	 * .com/client_and_server/client_getname.html</a></p>
	 *
	 * @return 连接时设置的名字
	 */
	String clientGetName();

	/**
	 * 返回当前连接的 ID
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/client-id.html" target="_blank">http://www.redis
	 * .cn/commands/client-id.html</a></p>
	 *
	 * @return 当前连接的 ID
	 */
	String clientId();

	/**
	 * 获取所有连接到服务器的客户端信息和统计数据
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_list.html" target="_blank">http://redisdoc
	 * .com/client_and_server/client_list.html</a></p>
	 *
	 * @return 所有连接到服务器的客户端信息和统计数据
	 */
	List<Client> clientList();

	/**
	 * 将所有客户端的访问暂停给定的毫秒数
	 *
	 * <p>详情说明
	 * <a href="http://www.redis.cn/commands/client-pause.html" target="_blank">http://www.redis
	 * .cn/commands/client-pause.html</a></p>
	 *
	 * @param timeout
	 * 		暂停时间（单位：毫秒）
	 *
	 * @return 操作结果
	 */
	Status clientPause(final int timeout);

	/**
	 * 将所有客户端的访问暂停给定的毫秒数
	 *
	 * <p>详情说明
	 * <a href="http://www.redis.cn/commands/client-pause.html" target="_blank">http://www.redis
	 * .cn/commands/client-pause.html</a></p>
	 *
	 * @param timeout
	 * 		暂停时间（单位：毫秒）
	 *
	 * @return 操作结果
	 */
	Status clientPause(final long timeout);

	/**
	 * 当需要完全禁用redis服务器对当前客户端的回复时可使用该命令
	 *
	 * <p>详情说明
	 * <a href="http://www.redis.cn/commands/client-reply.html" target="_blank">http://www.redis
	 * .cn/commands/client-reply.html</a></p>
	 *
	 * @param option
	 * 		选项
	 *
	 * @return 当执行命令设置为 OFF 或 SKIP，设置命令收不到任何回复，当设置为 ON 时，返回OK
	 */
	Status clientReply(final ClientReply option);

	/**
	 * 为当前连接分配一个名字
	 *
	 * @param name
	 * 		名字
	 *
	 * @return 设置成功时返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/client_and_server/client_setname.html" target="_blank">http://redisdoc
	 * .com/client_and_server/client_setname.html</a></p>
	 */
	Status clientSetName(final String name);

	/**
	 * 为当前连接分配一个名字
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/client_and_server/client_setname.html" target="_blank">http://redisdoc.com/client_and_server/client_setname.html</a></p>
	 *
	 * @param name
	 * 		名字
	 *
	 * @return 设置成功时返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status clientSetName(final byte[] name);

	/**
	 * 该命令可以通过其他连接解除客户端的阻塞
	 *
	 * <p>详情说明
	 * <a href="http://www.redis.cn/commands/client-unblock.html" target="_blank">http://www.redis
	 * .cn/commands/client-unblock.html</a></p>
	 *
	 * @param clientId
	 * 		客户端 ID
	 *
	 * @return 当执行命令设置为 OFF 或 SKIP，设置命令收不到任何回复，当设置为 ON 时，返回OK
	 */
	Status clientUnblock(final int clientId);

	/**
	 * 该命令可以通过其他连接解除客户端的阻塞
	 *
	 * <p>详情说明
	 * <a href="http://www.redis.cn/commands/client-unblock.html" target="_blank">http://www.redis
	 * .cn/commands/client-unblock.html</a></p>
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param type
	 * 		type
	 *
	 * @return 当执行命令设置为 OFF 或 SKIP，设置命令收不到任何回复，当设置为 ON 时，返回OK
	 */
	Status clientUnblock(final int clientId, final ClientUnblockType type);

	/**
	 * 获取 Redis 服务器的配置参数
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/configure/config_get.html" target="_blank">http://redisdoc
	 * .com/configure/config_get.html</a></p>
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
	 * <p>详情说明
	 * <a href="http://redisdoc.com/configure/config_get.html" target="_blank">http://redisdoc.com/configure
	 * /config_get.html</a></p>
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
	 * <p>详情说明
	 * <a href="http://redisdoc.com/configure/config_resetstat.html" target="_blank">http://redisdoc
	 * .com/configure/config_resetstat.html</a></p>
	 *
	 * @return 总是返回 Status.SUCCESS
	 */
	Status configResetStat();

	/**
	 * 对启动 Redis 服务器时所指定的 redis.conf 文件进行改写
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/configure/config_rewrite.html" target="_blank">http://redisdoc.com/configure/config_rewrite.html</a></p>
	 *
	 * @return 如果配置重写成功则，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status configRewrite();

	/**
	 * 动态地调整 Redis 服务器的配置
	 *
	 * <p>详情说明
	 *
	 * <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc
	 * .com/configure/config_set.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 * @param value
	 * 		配置值
	 *
	 * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status configSet(final String parameter, final float value);

	/**
	 * 动态地调整 Redis 服务器的配置
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc
	 * .com/configure/config_set.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 * @param value
	 * 		配置值
	 *
	 * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status configSet(final byte[] parameter, final float value);

	/**
	 * 动态地调整 Redis 服务器的配置
	 *
	 * <p>详情说明
	 *
	 * <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc.com/configure
	 * /config_set.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 * @param value
	 * 		配置值
	 *
	 * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status configSet(final String parameter, final double value);

	/**
	 * 动态地调整 Redis 服务器的配置
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc.com/configure/config_set.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 * @param value
	 * 		配置值
	 *
	 * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status configSet(final byte[] parameter, final double value);

	/**
	 * 动态地调整 Redis 服务器的配置
	 *
	 * <p>详情说明
	 *
	 * <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc.com/configure/config_set.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 * @param value
	 * 		配置值
	 *
	 * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status configSet(final String parameter, final int value);

	/**
	 * 动态地调整 Redis 服务器的配置
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc.com/configure/config_set.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 * @param value
	 * 		配置值
	 *
	 * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status configSet(final byte[] parameter, final int value);

	/**
	 * 动态地调整 Redis 服务器的配置
	 *
	 * <p>详情说明
	 *
	 * <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc.com/configure/config_set.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 * @param value
	 * 		配置值
	 *
	 * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status configSet(final String parameter, final long value);

	/**
	 * 动态地调整 Redis 服务器的配置
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc
	 * .com/configure/config_set.html</a></p>
	 *
	 * @param parameter
	 * 		配置项
	 * @param value
	 * 		配置值
	 *
	 * @return 设置成功时返回，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status configSet(final byte[] parameter, final long value);

	/**
	 * 动态地调整 Redis 服务器的配置
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc.com/configure/config_set.html</a></p>
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
	 * <p>详情说明
	 * <a href="http://redisdoc.com/configure/config_set.html" target="_blank">http://redisdoc.com/configure
	 * /config_set.html</a></p>
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
	 * 获取数据库的 key 的数量
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/database/dbsize.html" target="_blank">http://redisdoc.com/database/dbsize.html</a>
	 * </p>
	 *
	 * @return 数据库的 key 的数量
	 */
	Long dbSize();

	/**
	 * 是一个调试命令，它不应被客户端所使用
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/debug/debug_object.html" target="_blank">http://redisdoc.com/debug/debug_object.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 有关信息
	 */
	String debugObject(final String key);

	/**
	 * 是一个调试命令，它不应被客户端所使用
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/debug/debug_object.html" target="_blank">http://redisdoc.com/debug/debug_object.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 有关信息
	 */
	byte[] debugObject(final byte[] key);

	/**
	 * 执行一个不合法的内存访问从而让 Redis 崩溃，仅在开发时用于 BUG 模拟
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/debug/debug_segfault.html" target="_blank">http://redisdoc.com/debug
	 * /debug_segfault.html</a></p>
	 *
	 * @return 调试信息
	 */
	String debugSegfault();

	/**
	 * 清空整个 Redis 服务器的数据（删除所有数据库的所有 key ）
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/database/flushall.html" target="_blank">http://redisdoc
	 * .com/database/flushallhtml</a></p>
	 *
	 * @return 始终返回 Status.SUCCESS
	 */
	Status flushAll();

	/**
	 * 清空当前数据库中的所有 key
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/database/flushdb.html" target="_blank">http://redisdoc.com/database/flushdb.html</a>
	 * </p>
	 *
	 * @return 始终返回 Status.SUCCESS
	 */
	Status flushDb();

	/**
	 * 获取关于 Redis 服务器通过 section 指定的部分信息
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/client_and_server/info.html" target="_blank">http://redisdoc.com/client_and_server/info.html</a></p>
	 *
	 * @param section
	 * 		InfoSection
	 *
	 * @return 关于 Redis 服务器的各种信息和统计数值
	 */
	Info info(Info.Section section);

	/**
	 * 获取关于 Redis 服务器的各种信息和统计数值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/info.html" target="_blank">http://redisdoc
	 * .com/client_and_server/info.html</a></p>
	 *
	 * @return 关于 Redis 服务器的各种信息和统计数值
	 */
	Info info();

	/**
	 * 获取最近一次 Redis 成功将数据保存到磁盘上的时间，以 UNIX 时间戳格式表示
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/persistence/lastsave.html" target="_blank">http://redisdoc
	 * .com/persistence/lastsave.html</a></p>
	 *
	 * @return 最近一次成功将数据保存到磁盘上的时间
	 */
	Long lastSave();

	/**
	 * 列出 Redis 服务器遇到的不同类型的内存相关问题，并提供相应的解决建议
	 *
	 * @return edis 服务器遇到的不同类型的内存相关问题，以及解决建议
	 */
	String memoryDoctor();

	/**
	 * 实时打印出 Redis 服务器接收到的命令
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/debug/monitor.html" target="_blank">http://redisdoc.com/debug/monitor.html</a></p>
	 *
	 * @param redisMonitor
	 * 		Redis Monitor
	 */
	void monitor(final RedisMonitor redisMonitor);

	/**
	 * 命令允许从内部察看给定 key 的 Redis 对象，它通常用在除错(debugging)
	 * 或者了解为了节省空间而对 key 使用特殊编码的情况；
	 * 当将Redis用作缓存程序时，你也可以通过 OBJECT 命令中的信息，决定 key 的驱逐策略(eviction policies)
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/debug/object.html" target="_blank">http://redisdoc.com/debug/object.html</a>
	 * </p>
	 *
	 * @param command
	 * 		子命令
	 * @param key
	 * 		Key
	 *
	 * @return 当 objectCommand 为 ObjectCommand.REFCOUNT 时，返回给定 key 引用所储存的值的次数
	 * 当 objectCommand 为 ObjectCommand.ENCODING 时，返回给定 key 锁储存的值所使用的内部表示
	 * 当 objectCommand 为 ObjectCommand.IDLETIME 时，自储存以来的空闲时间（单位：秒）
	 */
	Object object(final ObjectCommand command, final String key);

	/**
	 * 命令允许从内部察看给定 key 的 Redis 对象，它通常用在除错(debugging)
	 * 或者了解为了节省空间而对 key 使用特殊编码的情况；
	 * 当将Redis用作缓存程序时，你也可以通过 OBJECT 命令中的信息，决定 key 的驱逐策略(eviction policies)
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/debug/object.html" target="_blank">http://redisdoc.com/debug/object.html</a>
	 * </p>
	 *
	 * @param command
	 * 		子命令
	 * @param key
	 * 		Key
	 *
	 * @return 当 objectCommand 为 ObjectCommand.REFCOUNT 时，返回给定 key 引用所储存的值的次数
	 * 当 objectCommand 为 ObjectCommand.ENCODING 时，返回给定 key 锁储存的值所使用的内部表示
	 * 当 objectCommand 为 ObjectCommand.IDLETIME 时，自储存以来的空闲时间（单位：秒）
	 */
	Object object(final ObjectCommand command, final byte[] key);

	/**
	 * 用于复制功能(replication)的内部命令
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/internal/psync.html" target="_blank">http://redisdoc.com/internal/psync.html</a>
	 * </p>
	 *
	 * @param masterRunId
	 * 		Master Run Id
	 * @param offset
	 * 		偏移量
	 *
	 * @return 序列化数据
	 */
	Object pSync(final String masterRunId, final int offset);

	/**
	 * 用于复制功能(replication)的内部命令
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/psync.html" target="_blank">http://redisdoc.com/internal/psync
	 * .html</a></p>
	 *
	 * @param masterRunId
	 * 		Master Run Id
	 * @param offset
	 * 		偏移量
	 *
	 * @return 序列化数据
	 */
	Object pSync(final byte[] masterRunId, final int offset);

	/**
	 * 用于复制功能(replication)的内部命令
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/internal/psync.html" target="_blank">http://redisdoc.com/internal/psync.html</a>
	 * </p>
	 *
	 * @param masterRunId
	 * 		Master Run Id
	 * @param offset
	 * 		偏移量
	 *
	 * @return 序列化数据
	 */
	Object pSync(final String masterRunId, final long offset);

	/**
	 * 用于复制功能(replication)的内部命令
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/psync.html" target="_blank">http://redisdoc.com/internal/psync
	 * .html</a></p>
	 *
	 * @param masterRunId
	 * 		Master Run Id
	 * @param offset
	 * 		偏移量
	 *
	 * @return 序列化数据
	 */
	Object pSync(final byte[] masterRunId, final long offset);

	/**
	 * 可以在线修改当前服务器的复制设置
	 * 如果当前服务器已经是副本服务器，会将当前服务器转变为某一服务器的副本服务器；
	 * 如果当前服务器已经是某个主服务器的副本服务器，那么将使当前服务器停止对原主服务器的同步，丢弃旧数据集，转而开始对新主服务器进行同步
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
	 * 获取实例在复制中担任的角色信息
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/replication/role.html" target="_blank">http://redisdoc.com/replication/role
	 * .html</a>
	 * </p>
	 *
	 * @return 实例在复制中担任的角色信息
	 */
	Role role();

	/**
	 * 执行一个同步保存操作，将当前 Redis 实例的所有数据快照(snapshot)以 RDB 文件的形式保存到硬盘；
	 * 该操作，因为它会阻塞所有客户端
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/persistence/save.html" target="_blank">http://redisdoc
	 * .com/persistence/save.html</a></p>
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
	 * <p>详情说明
	 * <a href="http://redisdoc.com/client_and_server/shutdown.html" target="_blank">http://redisdoc
	 * .com/client_and_server/shutdown.html</a></p>
	 */
	void shutdown();

	/**
	 * SHUTDOWN 命令执行以下操作：
	 * 1）停止所有客户端
	 * 2）如果有至少一个保存点在等待，执行 SAVE 命令
	 * 3）如果 AOF 选项被打开，更新 AOF 文件
	 * 4）关闭 redis 服务器(server)
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/client_and_server/shutdown.html" target="_blank">http://redisdoc
	 * .com/client_and_server/shutdown.html</a></p>
	 *
	 * @param save
	 * 		是否强制让数据库执行保存操作
	 */
	void shutdown(final boolean save);

	/**
	 * 用于在 Redis 运行时动态地修改复制(replication)功能的行为；
	 * 可以将当前服务器转变为指定服务器的从属服务器(slave server)
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/replication/slaveof.html" target="_blank">http://redisdoc.com/replication/slaveof
	 * .html</a></p>
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
	 * 查询执行时间指的是不包括像客户端响应(talking)、发送回复等 IO 操作，而单单是执行一个查询命令所耗费的时间
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/debug/slowlog.html" target="_blank">http://redisdoc.com/debug/slowlog.html</a></p>
	 *
	 * @param command
	 * 		子命令
	 * @param arguments
	 * 		命令参数
	 *
	 * @return 取决于不同命令，返回不同的值
	 */
	Object slowLog(final SlowLogCommand command, final String... arguments);

	/**
	 * 查询执行时间指的是不包括像客户端响应(talking)、发送回复等 IO 操作，而单单是执行一个查询命令所耗费的时间
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/debug/slowlog.html" target="_blank">http://redisdoc.com/debug/slowlog.html</a></p>
	 *
	 * @param command
	 * 		子命令
	 * @param arguments
	 * 		命令参数
	 *
	 * @return 取决于不同命令，返回不同的值
	 */
	Object slowLog(final SlowLogCommand command, final byte[]... arguments);

	/**
	 * 用于复制功能(replication)的内部命令
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/internal/sync.html" target="_blank">http://redisdoc.com/internal/sync.html</a></p>
	 *
	 * @return 序列化数据
	 */
	Object sync();

	/**
	 * 获取当前服务器时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/time.html" target="_blank">http://redisdoc
	 * .com/client_and_server/time.html</a></p>
	 *
	 * @return 当前服务器时间
	 */
	RedisServerTime time();

}
