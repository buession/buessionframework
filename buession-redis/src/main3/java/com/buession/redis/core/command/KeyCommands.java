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

import com.buession.lang.Status;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.args.MigrateArgument;
import com.buession.redis.core.command.args.RestoreArgument;
import com.buession.redis.core.command.args.ScanArgument;
import com.buession.redis.core.command.args.SortArgument;

import java.util.List;
import java.util.Set;

/**
 * KEY 命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=generic" target="_blank">https://redis.io/docs/latest/commands/?group=generic</a></p>
 *
 * @author Yong.Teng
 */
public interface KeyCommands extends RedisCommands {

	/**
	 * Copy the value stored at the source key to the destination key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/copy/" target="_blank">https://redis.io/commands/copy/</a></p>
	 *
	 * @param key
	 * 		待复制 key
	 * @param destKey
	 * 		目标 key
	 *
	 * @return 复制成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status copy(final String key, final String destKey);

	/**
	 * Copy the value stored at the source key to the destination key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/copy/" target="_blank">https://redis.io/commands/copy/</a></p>
	 *
	 * @param key
	 * 		待复制 key
	 * @param destKey
	 * 		目标 key
	 *
	 * @return 复制成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status copy(final byte[] key, final byte[] destKey);

	/**
	 * Copy the value stored at the source key to the destination key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/copy/" target="_blank">https://redis.io/commands/copy/</a></p>
	 *
	 * @param key
	 * 		待复制 key
	 * @param destKey
	 * 		目标 key
	 * @param db
	 * 		目标 DB
	 *
	 * @return 复制成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status copy(final String key, final String destKey, final int db);

	/**
	 * Copy the value stored at the source key to the destination key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/copy/" target="_blank">https://redis.io/commands/copy/</a></p>
	 *
	 * @param key
	 * 		待复制 key
	 * @param destKey
	 * 		目标 key
	 * @param db
	 * 		目标 DB
	 *
	 * @return 复制成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status copy(final byte[] key, final byte[] destKey, final int db);

	/**
	 * Copy the value stored at the source key to the destination key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/copy/" target="_blank">https://redis.io/commands/copy/</a></p>
	 *
	 * @param key
	 * 		待复制 key
	 * @param destKey
	 * 		目标 key
	 * @param replace
	 * 		是否替换已存在 Key
	 *
	 * @return 复制成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status copy(final String key, final String destKey, final boolean replace);

	/**
	 * Copy the value stored at the source key to the destination key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/copy/" target="_blank">https://redis.io/commands/copy/</a></p>
	 *
	 * @param key
	 * 		待复制 key
	 * @param destKey
	 * 		目标 key
	 * @param replace
	 * 		是否替换已存在 Key
	 *
	 * @return 复制成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status copy(final byte[] key, final byte[] destKey, final boolean replace);

	/**
	 * Copy the value stored at the source key to the destination key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/copy/" target="_blank">https://redis.io/commands/copy/</a></p>
	 *
	 * @param key
	 * 		待复制 key
	 * @param destKey
	 * 		目标 key
	 * @param db
	 * 		目标 DB
	 * @param replace
	 * 		是否替换已存在 Key
	 *
	 * @return 复制成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status copy(final String key, final String destKey, final int db, final boolean replace);

	/**
	 * Copy the value stored at the source key to the destination key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/copy/" target="_blank">https://redis.io/commands/copy/</a></p>
	 *
	 * @param key
	 * 		待复制 key
	 * @param destKey
	 * 		目标 key
	 * @param db
	 * 		目标 DB
	 * @param replace
	 * 		是否替换已存在 Key
	 *
	 * @return 复制成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace);

	/**
	 * 删除给定的一个或多个 key
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/del.html" target="_blank">http://redisdoc.com/database/del.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 *
	 * @return 被删除 key 的数量
	 */
	Long del(final String... keys);

	/**
	 * 删除给定的一个或多个 key
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/del.html" target="_blank">http://redisdoc.com/database/del.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 *
	 * @return 被删除 key 的数量
	 */
	Long del(final byte[]... keys);

	/**
	 * 序列化给定 key ，并返回被序列化的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/dump.html" target="_blank">http://redisdoc.com/internal/dump.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 被序列化的值
	 */
	String dump(final String key);

	/**
	 * 序列化给定 key ，并返回被序列化的值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/dump.html" target="_blank">http://redisdoc.com/internal/dump.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 被序列化的值
	 */
	byte[] dump(final byte[] key);

	/**
	 * 检查给定 key 是否存在
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/exists.html" target="_blank">http://redisdoc.com/database/exists.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 检测结果
	 */
	Boolean exists(final String key);

	/**
	 * 检查给定 key 是否存在
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/exists.html" target="_blank">http://redisdoc.com/database/exists.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 检测结果
	 */
	Boolean exists(final byte[] key);

	/**
	 * 检查给定 key 是否存在
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/exists.html" target="_blank">http://redisdoc.com/database/exists.html</a></p>
	 *
	 * @param keys
	 * 		Key
	 *
	 * @return 检查给定 key 存在的数量
	 */
	Long exists(final String... keys);

	/**
	 * 检查给定 key 是否存在
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/exists.html" target="_blank">http://redisdoc.com/database/exists.html</a></p>
	 *
	 * @param keys
	 * 		Key
	 *
	 * @return 检查给定 key 存在的数量
	 */
	Long exists(final byte[]... keys);

	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0)，它会被自动删除
	 *
	 * <p>详情说明<a href="http://redisdoc.com/expire/expire.html" target="_blank">http://redisdoc.com/expire/expire.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param lifetime
	 * 		生存时间（单位：秒）
	 *
	 * @return 操作结果
	 */
	Status expire(final String key, final int lifetime);

	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0)，它会被自动删除
	 *
	 * <p>详情说明<a href="http://redisdoc.com/expire/expire.html" target="_blank">http://redisdoc.com/expire/expire.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param lifetime
	 * 		生存时间（单位：秒）
	 *
	 * @return 操作结果
	 */
	Status expire(final byte[] key, final int lifetime);

	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0)，它会被自动删除
	 *
	 * <p>详情说明<a href="http://redisdoc.com/expire/expire.html" target="_blank">http://redisdoc.com/expire/expire.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param lifetime
	 * 		生存时间（单位：秒）
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	Status expire(final String key, final int lifetime, final ExpireOption expireOption);

	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0)，它会被自动删除
	 *
	 * <p>详情说明<a href="http://redisdoc.com/expire/expire.html" target="_blank">http://redisdoc.com/expire/expire.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param lifetime
	 * 		生存时间（单位：秒）
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption);

	/**
	 * 为给定 key 设置过期时间，具体过期时间戳
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/expireat.html" target="_blank">http://redisdoc.com/expire/expireat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param unixTimestamp
	 * 		过期时间戳（单位：秒）
	 *
	 * @return 操作结果
	 */
	Status expireAt(final String key, final long unixTimestamp);

	/**
	 * 为给定 key 设置过期时间，具体过期时间戳
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/expireat.html" target="_blank">http://redisdoc.com/expire/expireat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param unixTimestamp
	 * 		过期时间戳（单位：秒）
	 *
	 * @return 操作结果
	 */
	Status expireAt(final byte[] key, final long unixTimestamp);

	/**
	 * 为给定 key 设置过期时间，具体过期时间戳
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/expireat.html" target="_blank">http://redisdoc.com/expire/expireat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param unixTimestamp
	 * 		过期时间戳（单位：秒）
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 *
	 * @since 3.0.0
	 */
	Status expireAt(final String key, final long unixTimestamp, final ExpireOption expireOption);

	/**
	 * 为给定 key 设置过期时间，具体过期时间戳
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/expireat.html" target="_blank">http://redisdoc.com/expire/expireat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param unixTimestamp
	 * 		过期时间戳（单位：秒）
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 *
	 * @since 3.0.0
	 */
	Status expireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption);

	/**
	 * Returns the absolute Unix timestamp (since January 1, 1970) in seconds at which the given key will expire.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expiretime/" target="_blank">https://redis.io/docs/latest/commands/expiretime/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The expiration Unix timestamp in seconds;
	 * -1 if the key exists but has no associated expiration time;
	 * -2 if the key does not exist;
	 *
	 * @since 3.0.0
	 */
	Long expireTime(final String key);

	/**
	 * Returns the absolute Unix timestamp (since January 1, 1970) in seconds at which the given key will expire.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expiretime/" target="_blank">https://redis.io/docs/latest/commands/expiretime/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The expiration Unix timestamp in seconds;
	 * -1 if the key exists but has no associated expiration time;
	 * -2 if the key does not exist;
	 *
	 * @since 3.0.0
	 */
	Long expireTime(final byte[] key);

	/**
	 * 查找所有符合给定模式 pattern 的 key
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/keys.html" target="_blank">http://redisdoc.com/database/keys.html</a></p>
	 *
	 * @param pattern
	 * 		模式
	 *
	 * @return 符合给定模式的 key 列表
	 */
	Set<String> keys(final String pattern);

	/**
	 * 查找所有符合给定模式 pattern 的 key
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/keys.html" target="_blank">http://redisdoc.com/database/keys.html</a></p>
	 *
	 * @param pattern
	 * 		模式
	 *
	 * @return 符合给定模式的 key 列表
	 */
	Set<byte[]> keys(final byte[] pattern);

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/migrate.html" target="_blank">http://redisdoc.com/internal/migrate.html</a></p>
	 *
	 * @param host
	 * 		目标 Redis Server 主机地址
	 * @param port
	 * 		目标 Redis Server 端口
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 * @param keys
	 * 		Keys
	 *
	 * @return 操作结果
	 */
	Status migrate(final String host, final int port, final int db, final int timeout, final String... keys);

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/migrate.html" target="_blank">http://redisdoc.com/internal/migrate.html</a></p>
	 *
	 * @param host
	 * 		目标 Redis Server 主机地址
	 * @param port
	 * 		目标 Redis Server 端口
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 * @param keys
	 * 		Keys
	 *
	 * @return 操作结果
	 */
	Status migrate(final String host, final int port, final int db, final int timeout, final byte[]... keys);

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/migrate.html" target="_blank">http://redisdoc.com/internal/migrate.html</a></p>
	 *
	 * @param host
	 * 		目标 Redis Server 主机地址
	 * @param port
	 * 		目标 Redis Server 端口
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 * @param migrateArgument
	 * 		迁移参数
	 * @param keys
	 * 		Keys
	 *
	 * @return 操作结果
	 */
	Status migrate(final String host, final int port, final int db, final int timeout,
				   final MigrateArgument migrateArgument, final String... keys);

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/migrate.html" target="_blank">http://redisdoc.com/internal/migrate.html</a></p>
	 *
	 * @param host
	 * 		目标 Redis Server 主机地址
	 * @param port
	 * 		目标 Redis Server 端口
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 * @param migrateArgument
	 * 		迁移参数
	 * @param keys
	 * 		Keys
	 *
	 * @return 操作结果
	 */
	Status migrate(final String host, final int port, final int db, final int timeout,
				   final MigrateArgument migrateArgument, final byte[]... keys);

	/**
	 * 将当前数据库的 key 移动到给定的数据库 db 当中；
	 * 如果当前数据库(源数据库)和给定数据库(目标数据库)有相同名字的给定 key ，
	 * 或者 key 不存在于当前数据库，那么 MOVE 没有任何效果
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/move.html" target="_blank">http://redisdoc.com/database/move.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param db
	 * 		目标数据库
	 *
	 * @return 移动成功返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status move(final String key, final int db);

	/**
	 * 将当前数据库的 key 移动到给定的数据库 db 当中；
	 * 如果当前数据库(源数据库)和给定数据库(目标数据库)有相同名字的给定 key ，
	 * 或者 key 不存在于当前数据库，那么 MOVE 没有任何效果
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/move.html" target="_blank">http://redisdoc.com/database/move.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param db
	 * 		目标数据库
	 *
	 * @return 移动成功返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status move(final byte[] key, final int db);

	/**
	 * 返回指定 key 对应 value 所使用的内部表示
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/object.html" target="_blank">http://www.redis.cn/commands/object.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return key 对应 value 所使用的内部表示
	 */
	ObjectEncoding objectEncoding(final String key);

	/**
	 * 返回指定 key 对应 value 所使用的内部表示
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/object.html" target="_blank">http://www.redis.cn/commands/object.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return key 对应 value 所使用的内部表示
	 */
	ObjectEncoding objectEncoding(final byte[] key);

	/**
	 * This command returns the logarithmic access frequency counter of a Redis object stored a key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/object-freq/" target="_blank">https://redis.io/commands/object-freq/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The counter’s value
	 */
	Long objectFreq(final String key);

	/**
	 * This command returns the logarithmic access frequency counter of a Redis object stored a key
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/object-freq/" target="_blank">https://redis.io/commands/object-freq/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The counter’s value
	 */
	Long objectFreq(final byte[] key);

	/**
	 * 返回指定 key 对应的 value 自被存储之后空闲的时间（单位：秒）
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/object.html" target="_blank">http://www.redis.cn/commands/object.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return key 对应的 value 自被存储之后空闲的时间（单位：秒）
	 */
	Long objectIdleTime(final String key);

	/**
	 * 返回指定 key 对应的 value 自被存储之后空闲的时间（单位：秒）
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/object.html" target="_blank">http://www.redis.cn/commands/object.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return key 对应的 value 自被存储之后空闲的时间（单位：秒）
	 */
	Long objectIdleTime(final byte[] key);

	/**
	 * 返回指定 key 所对应 value 被引用的次数
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/object.html" target="_blank">http://www.redis.cn/commands/object.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return key 所对应 value 被引用的次数
	 */
	Long objectRefcount(final String key);

	/**
	 * 返回指定 key 所对应 value 被引用的次数
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/object.html" target="_blank">http://www.redis.cn/commands/object.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return key 所对应 value 被引用的次数
	 */
	Long objectRefcount(final byte[] key);

	/**
	 * 将 key 设置为持久性的 Key
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/persist.html" target="_blank">http://redisdoc.com/expire/persist.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 操作结果
	 */
	Status persist(final String key);

	/**
	 * 将 key 设置为持久性的 Key
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/persist.html" target="_blank">http://redisdoc.com/expire/persist.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 操作结果
	 */
	Status persist(final byte[] key);

	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0)，它会被自动删除
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/pexpire.html" target="_blank">http://redisdoc.com/expire/pexpire.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param lifetime
	 * 		生存时间（单位：毫秒）
	 *
	 * @return 操作结果
	 */
	Status pExpire(final String key, final int lifetime);

	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0)，它会被自动删除
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/pexpire.html" target="_blank">http://redisdoc.com/expire/pexpire.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param lifetime
	 * 		生存时间（单位：毫秒）
	 *
	 * @return 操作结果
	 */
	Status pExpire(final byte[] key, final int lifetime);

	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0)，它会被自动删除
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/pexpire.html" target="_blank">http://redisdoc.com/expire/pexpire.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param lifetime
	 * 		生存时间（单位：毫秒）
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 *
	 * @since 3.0.0
	 */
	Status pExpire(final String key, final int lifetime, final ExpireOption expireOption);

	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0)，它会被自动删除
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/pexpire.html" target="_blank">http://redisdoc.com/expire/pexpire.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param lifetime
	 * 		生存时间（单位：毫秒）
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 *
	 * @since 3.0.0
	 */
	Status pExpire(final byte[] key, final int lifetime, final ExpireOption expireOption);

	/**
	 * 为给定 key 设置过期时间，具体过期时间戳
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/pexpireat.html" target="_blank">http://redisdoc.com/expire/pexpireat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param unixTimestamp
	 * 		过期时间戳（单位：毫秒）
	 *
	 * @return 操作结果
	 */
	Status pExpireAt(final String key, final long unixTimestamp);

	/**
	 * 为给定 key 设置过期时间，具体过期时间戳
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/pexpireat.html" target="_blank">http://redisdoc.com/expire/pexpireat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param unixTimestamp
	 * 		过期时间戳（单位：毫秒）
	 *
	 * @return 操作结果
	 */
	Status pExpireAt(final byte[] key, final long unixTimestamp);

	/**
	 * 为给定 key 设置过期时间，具体过期时间戳
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/pexpireat.html" target="_blank">http://redisdoc.com/expire/pexpireat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param unixTimestamp
	 * 		过期时间戳（单位：毫秒）
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 *
	 * @since 3.0.0
	 */
	Status pExpireAt(final String key, final long unixTimestamp, final ExpireOption expireOption);

	/**
	 * 为给定 key 设置过期时间，具体过期时间戳
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/pexpireat.html" target="_blank">http://redisdoc.com/expire/pexpireat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param unixTimestamp
	 * 		过期时间戳（单位：毫秒）
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 *
	 * @since 3.0.0
	 */
	Status pExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption);

	/**
	 * Returns the absolute Unix timestamp (since January 1, 1970) in milliseconds at which the given key will expire.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpiretime/" target="_blank">https://redis.io/docs/latest/commands/pexpiretime/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The expiration Unix timestamp in milliseconds;
	 * -1 if the key exists but has no associated expiration time;
	 * -2 if the key does not exist;
	 *
	 * @since 3.0.0
	 */
	Long pExpireTime(final String key);

	/**
	 * Returns the absolute Unix timestamp (since January 1, 1970) in milliseconds at which the given key will expire.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpiretime/" target="_blank">https://redis.io/docs/latest/commands/pexpiretime/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return The expiration Unix timestamp in milliseconds;
	 * -1 if the key exists but has no associated expiration time;
	 * -2 if the key does not exist;
	 *
	 * @since 3.0.0
	 */
	Long pExpireTime(final byte[] key);

	/**
	 * 获取给定 key 的剩余生存时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/pttl.html" target="_blank">http://redisdoc.com/expire/pttl.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当 key 不存在时，返回 -2 ；
	 * 当 key 存在但没有设置剩余生存时间时，返回 -1 。
	 * 否则，以毫秒为单位，返回 key 的剩余生存时间
	 */
	Long pTtl(final String key);

	/**
	 * 获取给定 key 的剩余生存时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/pttl.html" target="_blank">http://redisdoc.com/expire/pttl.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当 key 不存在时，返回 -2 ；
	 * 当 key 存在但没有设置剩余生存时间时，返回 -1 。
	 * 否则，以毫秒为单位，返回 key 的剩余生存时间
	 */
	Long pTtl(final byte[] key);

	/**
	 * 从当前数据库中随机返回一个 key
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/randomkey.html" target="_blank">http://redisdoc.com/database/randomkey.html</a></p>
	 *
	 * @return 一个随机的 key
	 */
	String randomKey();

	/**
	 * 将 key 改名为 newkey
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/rename.html" target="_blank">http://redisdoc.com/database/renamehtml</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param newKey
	 * 		新 Key
	 *
	 * @return 当改名成功时返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status rename(final String key, final String newKey);

	/**
	 * 将 key 改名为 newkey
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/rename.html" target="_blank">http://redisdoc.com/database/rename.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param newKey
	 * 		新 Key
	 *
	 * @return 当改名成功时返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status rename(final byte[] key, final byte[] newKey);

	/**
	 * 且仅当 newkey 不存在时，将 key 改名为 newkey
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/renamenx.html" target="_blank">http://redisdoc.com/database/renamenx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param newKey
	 * 		新 Key
	 *
	 * @return 当改名成功时返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status renameNx(final String key, final String newKey);

	/**
	 * 且仅当 newkey 不存在时，将 key 改名为 newkey
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/renamenx.html" target="_blank">http://redisdoc.com/database/renamenx.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param newKey
	 * 		新 Key
	 *
	 * @return 当改名成功时返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status renameNx(final byte[] key, final byte[] newKey);

	/**
	 * 反序列化给定的序列化值，并将它和给定的 key 关联
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/restore/" target="_blank">https://redis.io/commands/restore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param serializedValue
	 * 		序列化值
	 * @param ttl
	 * 		生存时间（单位：毫秒）
	 *
	 * @return 操作结果
	 */
	Status restore(final String key, final byte[] serializedValue, final int ttl);

	/**
	 * 反序列化给定的序列化值，并将它和给定的 key 关联
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/restore/" target="_blank">https://redis.io/commands/restore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param serializedValue
	 * 		序列化值
	 * @param ttl
	 * 		生存时间（单位：毫秒）
	 *
	 * @return 操作结果
	 */
	Status restore(final byte[] key, final byte[] serializedValue, final int ttl);

	/**
	 * 反序列化给定的序列化值，并将它和给定的 key 关联
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/restore/" target="_blank">https://redis.io/commands/restore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param serializedValue
	 * 		序列化值
	 * @param ttl
	 * 		生存时间（单位：毫秒）
	 * @param restoreArgument
	 *        {@link RestoreArgument}
	 *
	 * @return 操作结果
	 */
	Status restore(final String key, final byte[] serializedValue, final int ttl,
				   final RestoreArgument restoreArgument);

	/**
	 * 反序列化给定的序列化值，并将它和给定的 key 关联
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/restore/" target="_blank">https://redis.io/commands/restore/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param serializedValue
	 * 		序列化值
	 * @param ttl
	 * 		生存时间（单位：毫秒）
	 * @param restoreArgument
	 *        {@link RestoreArgument}
	 *
	 * @return 操作结果
	 */
	Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
				   final RestoreArgument restoreArgument);

	/**
	 * 迭代当前数据库中的数据库键
	 *
	 * <p>详情说明<a href="http://redisdoc.com/database/scan.html" target="_blank">http://redisdoc.com/database/scan.html</a></p>
	 *
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回每个元素都是一个数据库键
	 */
	ScanResult<List<String>> scan(final long cursor);

	/**
	 * 迭代当前数据库中的数据库键
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/scan.html" target="_blank">http://redisdoc.com/database/scan.html</a></p>
	 *
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回每个元素都是一个数据库键
	 */
	ScanResult<List<String>> scan(final String cursor);

	/**
	 * 迭代当前数据库中的数据库键
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/scan.html" target="_blank">http://redisdoc.com/database/scan.html</a></p>
	 *
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回每个元素都是一个数据库键
	 */
	ScanResult<List<byte[]>> scan(final byte[] cursor);

	/**
	 * 迭代当前数据库中的数据库键
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/scan.html" target="_blank">http://redisdoc.com/database/scan.html</a></p>
	 *
	 * @param cursor
	 * 		游标
	 * @param scanArgument
	 *        {@link ScanArgument}
	 *
	 * @return 返回和给定模式相匹配的数据库键
	 *
	 * @since 3.0.0
	 */
	ScanResult<List<String>> scan(final long cursor, final ScanArgument.StringScanArgument scanArgument);

	/**
	 * 迭代当前数据库中的数据库键
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/scan.html" target="_blank">http://redisdoc.com/database/scan.html</a></p>
	 *
	 * @param cursor
	 * 		游标
	 * @param scanArgument
	 *        {@link ScanArgument}
	 *
	 * @return 返回和给定模式相匹配的数据库键
	 *
	 * @since 3.0.0
	 */
	ScanResult<List<byte[]>> scan(final long cursor, final ScanArgument.ByteScanArgument scanArgument);

	/**
	 * 返回给定列表、集合、有序集合 key 中经过排序的元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/sort.html" target="_blank">http://redisdoc.com/database/sort.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 列表形式的排序结果
	 */
	List<String> sort(final String key);

	/**
	 * 返回给定列表、集合、有序集合 key 中经过排序的元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/sort.html" target="_blank">http://redisdoc.com/database/sort.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 列表形式的排序结果
	 */
	List<byte[]> sort(final byte[] key);

	/**
	 * 返回给定列表、集合、有序集合 key 中经过排序的元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/sort.html" target="_blank">http://redisdoc.com/database/sort.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param sortArgument
	 * 		排序参数
	 *
	 * @return 列表形式的排序结果
	 */
	List<String> sort(final String key, final SortArgument sortArgument);

	/**
	 * 返回给定列表、集合、有序集合 key 中经过排序的元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/sort.html" target="_blank">http://redisdoc.com/database/sort.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param sortArgument
	 * 		排序参数
	 *
	 * @return 列表形式的排序结果
	 */
	List<byte[]> sort(final byte[] key, final SortArgument sortArgument);

	/**
	 * 保存给定列表、集合、有序集合 key 中经过排序的元素到 destKey；
	 * 如果被指定的 key 已存在，那么原有的值将被排序结果覆盖
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/sort.html" target="_blank">http://redisdoc.com/database/sort.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 key
	 *
	 * @return 排序结果的元素数量
	 */
	Long sort(final String key, final String destKey);

	/**
	 * 保存给定列表、集合、有序集合 key 中经过排序的元素到 destKey；
	 * 如果被指定的 key 已存在，那么原有的值将被排序结果覆盖
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/sort.html" target="_blank">http://redisdoc.com/database/sort.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 key
	 *
	 * @return 排序结果的元素数量
	 */
	Long sort(final byte[] key, final byte[] destKey);

	/**
	 * 保存给定列表、集合、有序集合 key 中经过排序的元素到 destKey；
	 * 如果被指定的 key 已存在，那么原有的值将被排序结果覆盖
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/sort.html" target="_blank">http://redisdoc.com/database/sort.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 key
	 * @param sortArgument
	 * 		排序参数
	 *
	 * @return 排序结果的元素数量
	 */
	Long sort(final String key, final String destKey, final SortArgument sortArgument);

	/**
	 * 保存给定列表、集合、有序集合 key 中经过排序的元素到 destKey；
	 * 如果被指定的 key 已存在，那么原有的值将被排序结果覆盖
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/sort.html" target="_blank">http://redisdoc.com/database/sort.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param destKey
	 * 		目标 key
	 * @param sortArgument
	 * 		排序参数
	 *
	 * @return 排序结果的元素数量
	 */
	Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument);

	/**
	 * 返回给定列表、集合、有序集合 key 中经过排序的元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/sort_ro/" target="_blank">https://redis.io/docs/latest/commands/sort_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 列表形式的排序结果
	 *
	 * @since 3.0.0
	 */
	List<String> sortRo(final String key);

	/**
	 * 返回给定列表、集合、有序集合 key 中经过排序的元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/sort_ro/" target="_blank">https://redis.io/docs/latest/commands/sort_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 列表形式的排序结果
	 *
	 * @since 3.0.0
	 */
	List<byte[]> sortRo(final byte[] key);

	/**
	 * 返回给定列表、集合、有序集合 key 中经过排序的元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/sort_ro/" target="_blank">https://redis.io/docs/latest/commands/sort_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param sortArgument
	 * 		排序参数
	 *
	 * @return 列表形式的排序结果
	 *
	 * @since 3.0.0
	 */
	List<String> sortRo(final String key, final SortArgument sortArgument);

	/**
	 * 返回给定列表、集合、有序集合 key 中经过排序的元素
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/sort_ro/" target="_blank">https://redis.io/docs/latest/commands/sort_ro/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param sortArgument
	 * 		排序参数
	 *
	 * @return 列表形式的排序结果
	 *
	 * @since 3.0.0
	 */
	List<byte[]> sortRo(final byte[] key, final SortArgument sortArgument);

	/**
	 * 修改指定一个或多个 key 最后访问时间
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/touch.html" target="_blank">http://www.redis.cn/commands/touch.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 *
	 * @return 操作的 key 的数量
	 */
	Long touch(final String... keys);

	/**
	 * 修改指定一个或多个 key 最后访问时间
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/touch.html" target="_blank">http://www.redis.cn/commands/touch.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 *
	 * @return 操作的 key 的数量
	 */
	Long touch(final byte[]... keys);

	/**
	 * 获取给定 key 的剩余生存时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/ttl.html" target="_blank">http://redisdoc.com/expire/ttl.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当 key 不存在时，返回 -2 ；
	 * 当 key 存在但没有设置剩余生存时间时，返回 -1 。
	 * 否则，以秒为单位，返回 key 的剩余生存时间
	 */
	Long ttl(final String key);

	/**
	 * 获取给定 key 的剩余生存时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/ttl.html" target="_blank">http://redisdoc.com/expire/ttl.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当 key 不存在时，返回 -2 ；
	 * 当 key 存在但没有设置剩余生存时间时，返回 -1 。
	 * 否则，以秒为单位，返回 key 的剩余生存时间
	 */
	Long ttl(final byte[] key);

	/**
	 * 获取 key 所储存的值的类型
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/type.html" target="_blank">http://redisdoc.com/database/type.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return key 所储存的值的类型
	 */
	Type type(final String key);

	/**
	 * 获取 key 所储存的值的类型
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/type.html" target="_blank">http://redisdoc.com/database/type.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return key 所储存的值的类型
	 */
	Type type(final byte[] key);

	/**
	 * 删除给定的一个或多个 key，该命令会在另一个线程中回收内存，因此它是非阻塞的。
	 * 仅将 keys 从 keyspace 元数据中删除，真正的删除会在后续异步操作。
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/unlink.html" target="_blank">http://www.redis.cn/commands/unlink.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 *
	 * @return 被删除 key 的数量
	 */
	Long unlink(final String... keys);

	/**
	 * 删除给定的一个或多个 key，该命令会在另一个线程中回收内存，因此它是非阻塞的。
	 * 仅将 keys 从 keyspace 元数据中删除，真正的删除会在后续异步操作。
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/unlink.html" target="_blank">http://www.redis.cn/commands/unlink.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 key
	 *
	 * @return 被删除 key 的数量
	 */
	Long unlink(final byte[]... keys);

}