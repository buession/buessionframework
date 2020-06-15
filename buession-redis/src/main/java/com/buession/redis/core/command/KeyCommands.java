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
import com.buession.redis.core.MigrateOperation;

/**
 * KEY 命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/database/index.html" target="_blank">http://redisdoc.com/database/index.html</a>
 * 和 <a href="http://redisdoc.com/expire/index.html" target="_blank">http://redisdoc.com/expire/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface KeyCommands extends RedisCommands {

	/**
	 * 删除给定的一个或多个 key
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/database/del.html" target="_blank">http://redisdoc.com/database/del.html</a>
	 * </p>
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
	 * <p>详情说明
	 * <a href="http://redisdoc.com/database/del.html" target="_blank">http://redisdoc.com/database/del.html</a>
	 * </p>
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
	 * <p>详情说明 <a href="http://redisdoc.com/internal/dump.html" target="_blank">http://redisdoc.com/internal/dump
	 * .html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 被序列化的值
	 */
	byte[] dump(final String key);

	/**
	 * 序列化给定 key ，并返回被序列化的值
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/internal/dump.html" target="_blank">http://redisdoc.com/internal/dump.html</a></p>
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
	 * <p>详情说明
	 * <a href="http://redisdoc.com/database/exists.html" target="_blank">http://redisdoc.com/database/exists.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 检测结果
	 */
	boolean exists(final String key);

	/**
	 * 检查给定 key 是否存在
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/database/exists.html" target="_blank">http://redisdoc.com/database/exists.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 检测结果
	 */
	boolean exists(final byte[] key);

	/**
	 * 为给定 key 设置生存时间，当 key 过期时(生存时间为 0)，它会被自动删除
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/expire/expire.html" target="_blank">http://redisdoc.com/expire/expire.html</a></p>
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
	 * <p>详情说明
	 * <a href="http://redisdoc.com/expire/expire.html" target="_blank">http://redisdoc.com/expire/expire.html</a></p>
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
	 * 为给定 key 设置过期时间，具体过期时间戳
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/expire/expireat.html" target="_blank">http://redisdoc.com/expire/expireat.html</a>
	 * </p>
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
	 * <p>详情说明 <a href="http://redisdoc.com/expire/expireat.html" target="_blank">http://redisdoc.com/expire/expireat
	 * .html</a></p>
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
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/internal/migrate.html" target="_blank">http://redisdoc.com/internal/migrate
	 * .html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 * @param host
	 * 		目标 Redis Server 主机地址
	 * @param port
	 * 		目标 Redis Server 端口
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 *
	 * @return 操作结果
	 */
	Status migrate(final String key, final String host, final int port, final int db, final int timeout);

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/internal/migrate.html" target="_blank">http://redisdoc.com/internal/migrate.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 * @param host
	 * 		目标 Redis Server 主机地址
	 * @param port
	 * 		目标 Redis Server 端口
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 *
	 * @return 操作结果
	 */
	Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout);

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/internal/migrate.html" target="_blank">http://redisdoc.com/internal/migrate.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 * @param host
	 * 		目标 Redis Server 主机地址
	 * @param port
	 * 		目标 Redis Server 端口
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 * @param operation
	 *        {@link MigrateOperation}
	 *
	 * @return 操作结果
	 */
	Status migrate(final String key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation operation);

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/internal/migrate.html" target="_blank">http://redisdoc.com/internal/migrate.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 * @param host
	 * 		目标 Redis Server 主机地址
	 * @param port
	 * 		目标 Redis Server 端口
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 * @param operation
	 *        {@link MigrateOperation}
	 *
	 * @return 操作结果
	 */
	Status migrate(final byte[] key, final String host, final int port, final int db, final int timeout,
			final MigrateOperation operation);

	/**
	 * 将当前数据库的 key 移动到给定的数据库 db 当中；
	 * 如果当前数据库(源数据库)和给定数据库(目标数据库)有相同名字的给定 key ，
	 * 或者 key 不存在于当前数据库，那么 MOVE 没有任何效果
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/database/move.html" target="_blank">http://redisdoc.com/database/move.html</a></p>
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
	 * <p>详情说明
	 * <a href="http://redisdoc.com/database/move.html" target="_blank">http://redisdoc.com/database/move.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param db
	 * 		目标数据库
	 *
	 * @return 移动成功返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	Status move(final byte[] key, final int db);

}
