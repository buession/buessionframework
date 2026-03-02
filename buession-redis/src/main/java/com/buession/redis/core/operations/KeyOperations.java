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
package com.buession.redis.core.operations;

import com.buession.core.utils.Assert;
import com.buession.core.utils.NumberUtils;
import com.buession.lang.Status;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.command.args.MigrateArgument;
import com.buession.redis.core.command.args.RestoreArgument;
import com.buession.redis.core.command.args.SortArgument;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * KEY 运算
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=generic" target="_blank">https://redis.io/docs/latest/commands/?group=generic</a></p>
 *
 * @author Yong.Teng
 */
public interface KeyOperations extends KeyCommands, RedisOperations {

	@Override
	default Status copy(final String key, final String destKey) {
		return execute((client)->client.keyOperations().copy(key, destKey));
	}

	@Override
	default Status copy(final byte[] key, final byte[] destKey) {
		return execute((client)->client.keyOperations().copy(key, destKey));
	}

	@Override
	default Status copy(final String key, final String destKey, final int db) {
		return execute((client)->client.keyOperations().copy(key, destKey, db));
	}

	@Override
	default Status copy(final byte[] key, final byte[] destKey, final int db) {
		return execute((client)->client.keyOperations().copy(key, destKey, db));
	}

	@Override
	default Status copy(final String key, final String destKey, final boolean replace) {
		return execute((client)->client.keyOperations().copy(key, destKey, replace));
	}

	@Override
	default Status copy(final byte[] key, final byte[] destKey, final boolean replace) {
		return execute((client)->client.keyOperations().copy(key, destKey, replace));
	}

	@Override
	default Status copy(final String key, final String destKey, final int db, final boolean replace) {
		return execute((client)->client.keyOperations().copy(key, destKey, db, replace));
	}

	@Override
	default Status copy(final byte[] key, final byte[] destKey, final int db, final boolean replace) {
		return execute((client)->client.keyOperations().copy(key, destKey, db, replace));
	}

	@Override
	default Long del(final String... keys) {
		return execute((client)->client.keyOperations().del(keys));
	}

	@Override
	default Long del(final byte[]... keys) {
		return execute((client)->client.keyOperations().del(keys));
	}

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
	default Long delete(final String... keys) {
		return del(keys);
	}

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
	default Long delete(final byte[]... keys) {
		return del(keys);
	}

	@Override
	default String dump(final String key) {
		return execute((client)->client.keyOperations().dump(key));
	}

	@Override
	default byte[] dump(final byte[] key) {
		return execute((client)->client.keyOperations().dump(key));
	}

	@Override
	default Boolean exists(final String key) {
		return execute((client)->client.keyOperations().exists(key));
	}

	@Override
	default Boolean exists(final byte[] key) {
		return execute((client)->client.keyOperations().exists(key));
	}

	@Override
	default Long exists(final String... keys) {
		return execute((client)->client.keyOperations().exists(keys));
	}

	@Override
	default Long exists(final byte[]... keys) {
		return execute((client)->client.keyOperations().exists(keys));
	}

	@Override
	default Status expire(final String key, final int lifetime) {
		return execute((client)->client.keyOperations().expire(key, lifetime));
	}

	@Override
	default Status expire(final byte[] key, final int lifetime) {
		return execute((client)->client.keyOperations().expire(key, lifetime));
	}

	@Override
	default Status expire(final String key, final int lifetime, final ExpireOption expireOption) {
		return execute((client)->client.keyOperations().expire(key, lifetime, expireOption));
	}

	@Override
	default Status expire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		return execute((client)->client.keyOperations().expire(key, lifetime, expireOption));
	}

	@Override
	default Status expireAt(final String key, final long unixTimestamp) {
		return execute((client)->client.keyOperations().expireAt(key, unixTimestamp));
	}

	@Override
	default Status expireAt(final byte[] key, final long unixTimestamp) {
		return execute((client)->client.keyOperations().expireAt(key, unixTimestamp));
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expireat/" target="_blank">https://redis.io/docs/latest/commands/expireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final String key, final Date date) {
		return expireAt(key, date.getTime() / 1000L);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expireat/" target="_blank">https://redis.io/docs/latest/commands/expireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final byte[] key, final Date date) {
		return expireAt(key, date.getTime() / 1000L);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expireat/" target="_blank">https://redis.io/docs/latest/commands/expireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final String key, final LocalDateTime dateTime) {
		return expireAt(key, dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expireat/" target="_blank">https://redis.io/docs/latest/commands/expireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final byte[] key, final LocalDateTime dateTime) {
		return expireAt(key, dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expireat/" target="_blank">https://redis.io/docs/latest/commands/expireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final String key, final ZonedDateTime dateTime) {
		return expireAt(key, dateTime.toEpochSecond());
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expireat/" target="_blank">https://redis.io/docs/latest/commands/expireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final byte[] key, final ZonedDateTime dateTime) {
		return expireAt(key, dateTime.toEpochSecond());
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expireat/" target="_blank">https://redis.io/docs/latest/commands/expireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final String key, final Instant instant) {
		return expireAt(key, instant.toEpochMilli() / 1000);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expireat/" target="_blank">https://redis.io/docs/latest/commands/expireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final byte[] key, final Instant instant) {
		return expireAt(key, instant.toEpochMilli() / 1000);
	}

	@Override
	default Status expireAt(final String key, final long unixTimestamp, final ExpireOption expireOption) {
		return execute((client)->client.keyOperations().expireAt(key, unixTimestamp, expireOption));
	}

	@Override
	default Status expireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption) {
		return execute((client)->client.keyOperations().expireAt(key, unixTimestamp, expireOption));
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expireat/" target="_blank">https://redis.io/docs/latest/commands/expireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final String key, final Date date, final ExpireOption expireOption) {
		return expireAt(key, date.getTime() / 1000L, expireOption);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expireat/" target="_blank">https://redis.io/docs/latest/commands/expireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final byte[] key, final Date date, final ExpireOption expireOption) {
		return expireAt(key, date.getTime() / 1000L, expireOption);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expireat/" target="_blank">https://redis.io/docs/latest/commands/expireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final String key, final LocalDateTime dateTime, final ExpireOption expireOption) {
		return expireAt(key, dateTime.atZone(ZoneId.systemDefault()).toInstant(), expireOption);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expireat/" target="_blank">https://redis.io/docs/latest/commands/expireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final byte[] key, final LocalDateTime dateTime, final ExpireOption expireOption) {
		return expireAt(key, dateTime.atZone(ZoneId.systemDefault()).toInstant(), expireOption);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expireat/" target="_blank">https://redis.io/docs/latest/commands/expireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final String key, final ZonedDateTime dateTime, final ExpireOption expireOption) {
		return expireAt(key, dateTime.toEpochSecond(), expireOption);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expireat/" target="_blank">https://redis.io/docs/latest/commands/expireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final byte[] key, final ZonedDateTime dateTime, final ExpireOption expireOption) {
		return expireAt(key, dateTime.toEpochSecond(), expireOption);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expireat/" target="_blank">https://redis.io/docs/latest/commands/expireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final String key, final Instant instant, final ExpireOption expireOption) {
		return expireAt(key, instant.toEpochMilli() / 1000, expireOption);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expireat/" target="_blank">https://redis.io/docs/latest/commands/expireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final byte[] key, final Instant instant, final ExpireOption expireOption) {
		return expireAt(key, instant.toEpochMilli() / 1000, expireOption);
	}

	@Override
	default Long expireTime(final String key) {
		return execute((client)->client.keyOperations().expireTime(key));
	}

	@Override
	default Long expireTime(final byte[] key) {
		return execute((client)->client.keyOperations().expireTime(key));
	}

	@Override
	default Set<String> keys(final String pattern) {
		return execute((client)->client.keyOperations().keys(pattern));
	}

	@Override
	default Set<byte[]> keys(final byte[] pattern) {
		return execute((client)->client.keyOperations().keys(pattern));
	}

	@Override
	default Status migrate(final String host, final int port, final int db, final int timeout, final String... keys) {
		return execute((client)->client.keyOperations().migrate(host, port, db, timeout, keys));
	}

	@Override
	default Status migrate(final String host, final int port, final int db, final int timeout, final byte[]... keys) {
		return execute((client)->client.keyOperations().migrate(host, port, db, timeout, keys));
	}

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/migrate.html" target="_blank">http://redisdoc.com/internal/migrate.html</a></p>
	 *
	 * @param host
	 * 		目标 Redis Server 主机地址
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 * @param keys
	 * 		Keys
	 *
	 * @return 操作结果
	 */
	default Status migrate(final String host, final int db, final int timeout, final String... keys) {
		return migrate(host, RedisNode.DEFAULT_PORT, db, timeout, keys);
	}

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/migrate.html" target="_blank">http://redisdoc.com/internal/migrate.html</a></p>
	 *
	 * @param host
	 * 		目标 Redis Server 主机地址
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 * @param keys
	 * 		Keys
	 *
	 * @return 操作结果
	 */
	default Status migrate(final String host, final int db, final int timeout, final byte[]... keys) {
		return migrate(host, RedisNode.DEFAULT_PORT, db, timeout, keys);
	}

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/migrate.html" target="_blank">http://redisdoc.com/internal/migrate.html</a></p>
	 *
	 * @param server
	 * 		目标 Redis Server
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 * @param keys
	 * 		Keys
	 *
	 * @return 操作结果
	 */
	default Status migrate(final RedisNode server, final int db, final int timeout, final String... keys) {
		Assert.isNull(server, "Destination redis node cloud not be null");
		Assert.isBlank(server.getHost(), "Destination redis host cloud not be null or empty");

		return migrate(server.getHost(), server.getPort(), db, timeout, keys);
	}

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/migrate.html" target="_blank">http://redisdoc.com/internal/migrate.html</a></p>
	 *
	 * @param server
	 * 		目标 Redis Server
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 * @param keys
	 * 		Keys
	 *
	 * @return 操作结果
	 */
	default Status migrate(final RedisNode server, final int db, final int timeout, final byte[]... keys) {
		Assert.isNull(server, "Destination redis node cloud not be null");
		Assert.isBlank(server.getHost(), "Destination redis host cloud not be null or empty");

		return migrate(server.getHost(), server.getPort(), db, timeout, keys);
	}

	@Override
	default Status migrate(final String host, final int port, final int db, final int timeout,
						   final MigrateArgument argument, final String... keys) {
		return execute((client)->client.keyOperations().migrate(host, port, db, timeout, argument, keys));
	}

	@Override
	default Status migrate(final String host, final int port, final int db, final int timeout,
						   final MigrateArgument argument, final byte[]... keys) {
		return execute((client)->client.keyOperations().migrate(host, port, db, timeout, argument, keys));
	}

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/migrate.html" target="_blank">http://redisdoc.com/internal/migrate.html</a></p>
	 *
	 * @param host
	 * 		目标 Redis Server 主机地址
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 * @param argument
	 * 		参数
	 * @param keys
	 * 		Keys
	 *
	 * @return 操作结果
	 */
	default Status migrate(final String host, final int db, final int timeout, final MigrateArgument argument,
						   final String... keys) {
		return migrate(host, RedisNode.DEFAULT_PORT, db, timeout, argument, keys);
	}

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/migrate.html" target="_blank">http://redisdoc.com/internal/migrate.html</a></p>
	 *
	 * @param host
	 * 		目标 Redis Server 主机地址
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 * @param argument
	 * 		参数
	 * @param keys
	 * 		Keys
	 *
	 * @return 操作结果
	 */
	default Status migrate(final String host, final int db, final int timeout, final MigrateArgument argument,
						   final byte[]... keys) {
		return migrate(host, RedisNode.DEFAULT_PORT, db, timeout, argument, keys);
	}

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/migrate.html" target="_blank">http://redisdoc.com/internal/migrate.html</a></p>
	 *
	 * @param server
	 * 		目标 Redis Server
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 * @param argument
	 * 		参数
	 * @param keys
	 * 		Keys
	 *
	 * @return 操作结果
	 */
	default Status migrate(final RedisNode server, final int db, final int timeout, final MigrateArgument argument,
						   final String... keys) {
		Assert.isNull(server, "Destination redis node cloud not be null");
		Assert.isBlank(server.getHost(), "Destination redis host cloud not be null or empty");

		return migrate(server.getHost(), server.getPort(), db, timeout, argument, keys);
	}

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/internal/migrate.html" target="_blank">http://redisdoc.com/internal/migrate.html</a></p>
	 *
	 * @param server
	 * 		目标 Redis Server
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 * @param argument
	 * 		参数
	 * @param keys
	 * 		Keys
	 *
	 * @return 操作结果
	 */
	default Status migrate(final RedisNode server, final int db, final int timeout, final MigrateArgument argument,
						   final byte[]... keys) {
		Assert.isNull(server, "Destination redis node cloud not be null");
		Assert.isBlank(server.getHost(), "Destination redis host cloud not be null or empty");

		return migrate(server.getHost(), server.getPort(), db, timeout, argument, keys);
	}

	@Override
	default Status move(final String key, final int db) {
		return execute((client)->client.keyOperations().move(key, db));
	}

	@Override
	default Status move(final byte[] key, final int db) {
		return execute((client)->client.keyOperations().move(key, db));
	}

	@Override
	default ObjectEncoding objectEncoding(final String key) {
		return execute((client)->client.keyOperations().objectEncoding(key));
	}

	@Override
	default ObjectEncoding objectEncoding(final byte[] key) {
		return execute((client)->client.keyOperations().objectEncoding(key));
	}

	@Override
	default Long objectFreq(final String key) {
		return execute((client)->client.keyOperations().objectFreq(key));
	}

	@Override
	default Long objectFreq(final byte[] key) {
		return execute((client)->client.keyOperations().objectFreq(key));
	}

	@Override
	default Long objectIdleTime(final String key) {
		return execute((client)->client.keyOperations().objectIdleTime(key));
	}

	@Override
	default Long objectIdleTime(final byte[] key) {
		return execute((client)->client.keyOperations().objectIdleTime(key));
	}

	@Override
	default Long objectRefcount(final String key) {
		return execute((client)->client.keyOperations().objectRefcount(key));
	}

	@Override
	default Long objectRefcount(final byte[] key) {
		return execute((client)->client.keyOperations().objectRefcount(key));
	}

	@Override
	default Status persist(final String key) {
		return execute((client)->client.keyOperations().persist(key));
	}

	@Override
	default Status persist(final byte[] key) {
		return execute((client)->client.keyOperations().persist(key));
	}

	@Override
	default Status pExpire(final String key, final int lifetime) {
		return execute((client)->client.keyOperations().pExpire(key, lifetime));
	}

	@Override
	default Status pExpire(final byte[] key, final int lifetime) {
		return execute((client)->client.keyOperations().pExpire(key, lifetime));
	}

	@Override
	default Status pExpire(final String key, final int lifetime, final ExpireOption expireOption) {
		return execute((client)->client.keyOperations().pExpire(key, lifetime, expireOption));
	}

	@Override
	default Status pExpire(final byte[] key, final int lifetime, final ExpireOption expireOption) {
		return execute((client)->client.keyOperations().pExpire(key, lifetime, expireOption));
	}

	@Override
	default Status pExpireAt(final String key, final long unixTimestamp) {
		return execute((client)->client.keyOperations().pExpireAt(key, unixTimestamp));
	}

	@Override
	default Status pExpireAt(final byte[] key, final long unixTimestamp) {
		return execute((client)->client.keyOperations().pExpireAt(key, unixTimestamp));
	}

	@Override
	default Status pExpireAt(final String key, final long unixTimestamp, final ExpireOption expireOption) {
		return execute((client)->client.keyOperations().pExpireAt(key, unixTimestamp, expireOption));
	}

	@Override
	default Status pExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption expireOption) {
		return execute((client)->client.keyOperations().pExpireAt(key, unixTimestamp, expireOption));
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpireat/" target="_blank">https://redis.io/docs/latest/commands/pexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final String key, final Date date) {
		return pExpireAt(key, date.getTime());
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpireat/" target="_blank">https://redis.io/docs/latest/commands/pexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final byte[] key, final Date date) {
		return pExpireAt(key, date.getTime());
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpireat/" target="_blank">https://redis.io/docs/latest/commands/pexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final String key, final LocalDateTime dateTime) {
		return pExpireAt(key, dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpireat/" target="_blank">https://redis.io/docs/latest/commands/pexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final byte[] key, final LocalDateTime dateTime) {
		return pExpireAt(key, dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpireat/" target="_blank">https://redis.io/docs/latest/commands/pexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final String key, final ZonedDateTime dateTime) {
		return pExpireAt(key, dateTime.toInstant());
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpireat/" target="_blank">https://redis.io/docs/latest/commands/pexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final byte[] key, final ZonedDateTime dateTime) {
		return pExpireAt(key, dateTime.toInstant());
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpireat/" target="_blank">https://redis.io/docs/latest/commands/pexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final String key, final Instant instant) {
		return pExpireAt(key, instant.toEpochMilli());
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpireat/" target="_blank">https://redis.io/docs/latest/commands/pexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final byte[] key, final Instant instant) {
		return pExpireAt(key, instant.toEpochMilli());
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpireat/" target="_blank">https://redis.io/docs/latest/commands/pexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final String key, final Date date, final ExpireOption expireOption) {
		return pExpireAt(key, date.getTime(), expireOption);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpireat/" target="_blank">https://redis.io/docs/latest/commands/pexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final byte[] key, final Date date, final ExpireOption expireOption) {
		return pExpireAt(key, date.getTime(), expireOption);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpireat/" target="_blank">https://redis.io/docs/latest/commands/pexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final String key, final LocalDateTime dateTime, final ExpireOption expireOption) {
		return pExpireAt(key, dateTime.atZone(ZoneId.systemDefault()).toInstant(), expireOption);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpireat/" target="_blank">https://redis.io/docs/latest/commands/pexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final byte[] key, final LocalDateTime dateTime, final ExpireOption expireOption) {
		return pExpireAt(key, dateTime.atZone(ZoneId.systemDefault()).toInstant(), expireOption);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpireat/" target="_blank">https://redis.io/docs/latest/commands/pexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final String key, final ZonedDateTime dateTime, final ExpireOption expireOption) {
		return pExpireAt(key, dateTime.toInstant(), expireOption);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpireat/" target="_blank">https://redis.io/docs/latest/commands/pexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final byte[] key, final ZonedDateTime dateTime, final ExpireOption expireOption) {
		return pExpireAt(key, dateTime.toInstant(), expireOption);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpireat/" target="_blank">https://redis.io/docs/latest/commands/pexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final String key, final Instant instant, final ExpireOption expireOption) {
		return pExpireAt(key, instant.toEpochMilli(), expireOption);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pexpireat/" target="_blank">https://redis.io/docs/latest/commands/pexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final byte[] key, final Instant instant, final ExpireOption expireOption) {
		return pExpireAt(key, instant.toEpochMilli(), expireOption);
	}

	@Override
	default Long pExpireTime(final String key) {
		return execute((client)->client.keyOperations().pExpireTime(key));
	}

	@Override
	default Long pExpireTime(final byte[] key) {
		return execute((client)->client.keyOperations().pExpireTime(key));
	}

	@Override
	default Long pTtl(final String key) {
		return execute((client)->client.keyOperations().pTtl(key));
	}

	@Override
	default Long pTtl(final byte[] key) {
		return execute((client)->client.keyOperations().pTtl(key));
	}

	/**
	 * 获取给定 key 的过期时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/pttl.html" target="_blank">http://redisdoc.com/expire/pttl.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当 key 不存在时，或没有设置剩余生存时间时，返回 null；否则返回过期时间
	 */
	default Date pTtlAt(final String key) {
		Long ttl = pTtl(key);
		return ttl != null && ttl >= 0 ? new Date(System.currentTimeMillis() + pTtl(key)) : null;
	}

	/**
	 * 获取给定 key 的过期时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/pttl.html" target="_blank">http://redisdoc.com/expire/pttl.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当 key 不存在时，或没有设置剩余生存时间时，返回 null；否则返回过期时间
	 */
	default Date pTtlAt(final byte[] key) {
		Long ttl = pTtl(key);
		return ttl != null && ttl >= 0 ? new Date(System.currentTimeMillis() + pTtl(key)) : null;
	}

	@Override
	default String randomKey() {
		return execute((client)->client.keyOperations().randomKey());
	}

	@Override
	default Status rename(final String key, final String newKey) {
		return execute((client)->client.keyOperations().rename(key, newKey));
	}

	@Override
	default Status rename(final byte[] key, final byte[] newKey) {
		return execute((client)->client.keyOperations().rename(key, newKey));
	}

	@Override
	default Status renameNx(final String key, final String newKey) {
		return execute((client)->client.keyOperations().renameNx(key, newKey));
	}

	@Override
	default Status renameNx(final byte[] key, final byte[] newKey) {
		return execute((client)->client.keyOperations().renameNx(key, newKey));
	}

	@Override
	default Status restore(final String key, final byte[] serializedValue, final int ttl) {
		return execute((client)->client.keyOperations().restore(key, serializedValue, ttl));
	}

	@Override
	default Status restore(final byte[] key, final byte[] serializedValue, final int ttl) {
		return execute((client)->client.keyOperations().restore(key, serializedValue, ttl));
	}

	@Override
	default Status restore(final String key, final byte[] serializedValue, final int ttl,
						   final RestoreArgument argument) {
		return execute((client)->client.keyOperations().restore(key, serializedValue, ttl, argument));
	}

	@Override
	default Status restore(final byte[] key, final byte[] serializedValue, final int ttl,
						   final RestoreArgument argument) {
		return execute((client)->client.keyOperations().restore(key, serializedValue, ttl, argument));
	}

	/**
	 * 反序列化给定的序列化值，并将它和给定的 key 关联
	 *
	 * @param key
	 * 		Key
	 * @param serializedValue
	 * 		序列化值
	 * @param ttl
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status restore(final String key, final byte[] serializedValue, final Date ttl) {
		Assert.isNull(ttl, "Ttl date could not be null");
		return restore(key, serializedValue, (int) (ttl.getTime() - System.currentTimeMillis()));
	}

	/**
	 * 反序列化给定的序列化值，并将它和给定的 key 关联
	 *
	 * @param key
	 * 		Key
	 * @param serializedValue
	 * 		序列化值
	 * @param ttl
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status restore(final byte[] key, final byte[] serializedValue, final Date ttl) {
		Assert.isNull(ttl, "Ttl date could not be null");
		return restore(key, serializedValue, (int) (ttl.getTime() - System.currentTimeMillis()));
	}

	/**
	 * 反序列化给定的序列化值，并将它和给定的 key 关联
	 *
	 * @param key
	 * 		Key
	 * @param serializedValue
	 * 		序列化值
	 * @param dateTime
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status restore(final String key, final byte[] serializedValue, final LocalDateTime dateTime) {
		Assert.isNull(dateTime, "Ttl date could not be null");
		return restore(key, serializedValue, dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 反序列化给定的序列化值，并将它和给定的 key 关联
	 *
	 * @param key
	 * 		Key
	 * @param serializedValue
	 * 		序列化值
	 * @param dateTime
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status restore(final byte[] key, final byte[] serializedValue, final LocalDateTime dateTime) {
		Assert.isNull(dateTime, "Ttl date could not be null");
		return restore(key, serializedValue, dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 反序列化给定的序列化值，并将它和给定的 key 关联
	 *
	 * @param key
	 * 		Key
	 * @param serializedValue
	 * 		序列化值
	 * @param dateTime
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status restore(final String key, final byte[] serializedValue, final ZonedDateTime dateTime) {
		Assert.isNull(dateTime, "Ttl date could not be null");
		return restore(key, serializedValue, dateTime.toInstant());
	}

	/**
	 * 反序列化给定的序列化值，并将它和给定的 key 关联
	 *
	 * @param key
	 * 		Key
	 * @param serializedValue
	 * 		序列化值
	 * @param dateTime
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status restore(final byte[] key, final byte[] serializedValue, final ZonedDateTime dateTime) {
		Assert.isNull(dateTime, "Ttl date could not be null");
		return restore(key, serializedValue, dateTime.toInstant());
	}

	/**
	 * 反序列化给定的序列化值，并将它和给定的 key 关联
	 *
	 * @param key
	 * 		Key
	 * @param serializedValue
	 * 		序列化值
	 * @param instant
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status restore(final String key, final byte[] serializedValue, final Instant instant) {
		Assert.isNull(instant, "Ttl date could not be null");
		return restore(key, serializedValue, (int) (instant.toEpochMilli() - System.currentTimeMillis()));
	}

	/**
	 * 反序列化给定的序列化值，并将它和给定的 key 关联
	 *
	 * @param key
	 * 		Key
	 * @param serializedValue
	 * 		序列化值
	 * @param instant
	 * 		instant
	 *
	 * @return 操作结果
	 */
	default Status restore(final byte[] key, final byte[] serializedValue, final Instant instant) {
		Assert.isNull(instant, "Ttl date could not be null");
		return restore(key, serializedValue, (int) (instant.toEpochMilli() - System.currentTimeMillis()));
	}

	@Override
	default ScanResult<String> scan(final String cursor) {
		return execute((client)->client.keyOperations().scan(cursor));
	}

	@Override
	default ScanResult<byte[]> scan(final byte[] cursor) {
		return execute((client)->client.keyOperations().scan(cursor));
	}

	default ScanResult<String> scan(final long cursor) {
		return scan(Long.toString(cursor));
	}

	@Override
	default ScanResult<String> scan(final String cursor, final String pattern) {
		return execute((client)->client.keyOperations().scan(cursor, pattern));
	}

	@Override
	default ScanResult<byte[]> scan(final byte[] cursor, final byte[] pattern) {
		return execute((client)->client.keyOperations().scan(cursor, pattern));
	}

	default ScanResult<String> scan(final long cursor, final String pattern) {
		return scan(Long.toString(cursor), pattern);
	}

	default ScanResult<byte[]> scan(final long cursor, final byte[] pattern) {
		return scan(NumberUtils.long2bytes(cursor), pattern);
	}

	@Override
	default ScanResult<String> scan(final String cursor, final int count) {
		return execute((client)->client.keyOperations().scan(cursor, count));
	}

	@Override
	default ScanResult<byte[]> scan(final byte[] cursor, final int count) {
		return execute((client)->client.keyOperations().scan(cursor, count));
	}

	default ScanResult<String> scan(final long cursor, final int count) {
		return scan(Long.toString(cursor), count);
	}

	@Override
	default ScanResult<String> scan(final String cursor, final String pattern, final int count) {
		return execute((client)->client.keyOperations().scan(cursor, pattern, count));
	}

	@Override
	default ScanResult<byte[]> scan(final byte[] cursor, final byte[] pattern, final int count) {
		return execute((client)->client.keyOperations().scan(cursor, pattern, count));
	}

	default ScanResult<String> scan(final long cursor, final String pattern, final int count) {
		return scan(Long.toString(cursor), pattern, count);
	}

	default ScanResult<byte[]> scan(final long cursor, final byte[] pattern, final int count) {
		return scan(NumberUtils.long2bytes(cursor), pattern, count);
	}

	@Override
	default List<String> sort(final String key) {
		return execute((client)->client.keyOperations().sort(key));
	}

	@Override
	default List<byte[]> sort(final byte[] key) {
		return execute((client)->client.keyOperations().sort(key));
	}

	@Override
	default List<String> sort(final String key, final SortArgument argument) {
		return execute((client)->client.keyOperations().sort(key, argument));
	}

	@Override
	default List<byte[]> sort(final byte[] key, final SortArgument argument) {
		return execute((client)->client.keyOperations().sort(key, argument));
	}

	@Override
	default Long sort(final String key, final String destKey) {
		return execute((client)->client.keyOperations().sort(key, destKey));
	}

	@Override
	default Long sort(final byte[] key, final byte[] destKey) {
		return execute((client)->client.keyOperations().sort(key, destKey));
	}

	@Override
	default Long sort(final String key, final String destKey, final SortArgument sortArgument) {
		return execute((client)->client.keyOperations().sort(key, destKey, sortArgument));
	}

	@Override
	default Long sort(final byte[] key, final byte[] destKey, final SortArgument sortArgument) {
		return execute((client)->client.keyOperations().sort(key, destKey, sortArgument));
	}

	@Override
	default List<String> sortRo(final String key) {
		return execute((client)->client.keyOperations().sortRo(key));
	}

	@Override
	default List<byte[]> sortRo(final byte[] key) {
		return execute((client)->client.keyOperations().sortRo(key));
	}

	@Override
	default List<String> sortRo(final String key, final SortArgument argument) {
		return execute((client)->client.keyOperations().sortRo(key, argument));
	}

	@Override
	default List<byte[]> sortRo(final byte[] key, final SortArgument argument) {
		return execute((client)->client.keyOperations().sortRo(key, argument));
	}

	@Override
	default Long touch(final String... keys) {
		return execute((client)->client.keyOperations().touch(keys));
	}

	@Override
	default Long touch(final byte[]... keys) {
		return execute((client)->client.keyOperations().touch(keys));
	}

	@Override
	default Long ttl(final String key) {
		return execute((client)->client.keyOperations().ttl(key));
	}

	@Override
	default Long ttl(final byte[] key) {
		return execute((client)->client.keyOperations().ttl(key));
	}

	/**
	 * 获取给定 key 的过期时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/ttl.html" target="_blank">http://redisdoc.com/expire/ttl.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当 key 不存在时，或没有设置剩余生存时间时，返回 null；否则返回过期时间
	 */
	default Date ttlAt(final String key) {
		Long ttl = ttl(key);
		return ttl != null && ttl >= 0 ? new Date(System.currentTimeMillis() + ttl * 1000L) : null;
	}

	/**
	 * 获取给定 key 的过期时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/ttl.html" target="_blank">http://redisdoc.com/expire/ttl.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当 key 不存在时，或没有设置剩余生存时间时，返回 null；否则返回过期时间
	 */
	default Date ttlAt(final byte[] key) {
		Long ttl = ttl(key);
		return ttl != null && ttl >= 0 ? new Date(System.currentTimeMillis() + ttl * 1000L) : null;
	}

	@Override
	default Type type(final String key) {
		return execute((client)->client.keyOperations().type(key));
	}

	@Override
	default Type type(final byte[] key) {
		return execute((client)->client.keyOperations().type(key));
	}

	@Override
	default Long unlink(final String... keys) {
		return execute((client)->client.keyOperations().unlink(keys));
	}

	@Override
	default Long unlink(final byte[]... keys) {
		return execute((client)->client.keyOperations().unlink(keys));
	}

}
