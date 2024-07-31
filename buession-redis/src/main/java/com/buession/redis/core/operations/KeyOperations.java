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
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.command.args.MigrateArgument;

import java.util.Date;
import java.util.Set;

/**
 * KEY 运算
 *
 * <p>详情说明 <a href="http://redisdoc.com/database/index.html" target="_blank">http://redisdoc.com/database/index.html</a>
 * 和 <a href="http://redisdoc.com/expire/index.html" target="_blank">http://redisdoc.com/expire/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface KeyOperations extends KeyCommands, RedisOperations {

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

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/expireat.html" target="_blank">http://redisdoc.com/expire/expireat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final String key, final Date date) {
		Assert.isNull(date, "Expire date could not be null");
		return expireAt(key, date.getTime() / 1000L);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/expireat.html" target="_blank">http://redisdoc.com/expire/expireat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final byte[] key, final Date date) {
		Assert.isNull(date, "Expire date could not be null");
		return expireAt(key, date.getTime() / 1000L);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/expireat.html" target="_blank">http://redisdoc.com/expire/expireat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 *
	 * @since 3.0.0
	 */
	default Status expireAt(final String key, final Date date, final ExpireOption expireOption) {
		Assert.isNull(date, "Expire date could not be null");
		return expireAt(key, date.getTime() / 1000L, expireOption);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/expireat.html" target="_blank">http://redisdoc.com/expire/expireat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 * @param expireOption
	 * 		过期选项
	 *
	 * @return 操作结果
	 *
	 * @since 3.0.0
	 */
	default Status expireAt(final byte[] key, final Date date, final ExpireOption expireOption) {
		Assert.isNull(date, "Expire date could not be null");
		return expireAt(key, date.getTime() / 1000L, expireOption);
	}

	/**
	 * 获取给定 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expiretime/" target="_blank">https://redis.io/docs/latest/commands/expiretime/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当 key 不存在时，或没有设置剩余生存时间时，返回 null；否则返回过期时间
	 *
	 * @since 3.0.0
	 */
	default Date expireTimeAt(final String key) {
		Long ttl = expireTime(key);
		return ttl != null && ttl >= 0 ? new Date(System.currentTimeMillis() + ttl) : null;
	}

	/**
	 * 获取给定 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expiretime/" target="_blank">https://redis.io/docs/latest/commands/expiretime/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当 key 不存在时，或没有设置剩余生存时间时，返回 null；否则返回过期时间
	 *
	 * @since 3.0.0
	 */
	default Date expireTimeAt(final byte[] key) {
		Long ttl = expireTime(key);
		return ttl != null && ttl >= 0 ? new Date(System.currentTimeMillis() + ttl) : null;
	}

	@Override
	default Set<String> keys(final String pattern) {
		return execute((client)->client.keyOperations().keys(pattern));
	}

	@Override
	default Set<byte[]> keys(final byte[] pattern) {
		return execute((client)->client.keyOperations().keys(pattern));
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
	 * @param host
	 * 		目标 Redis Server 主机地址
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
	default Status migrate(final String host, final int db, final int timeout, final MigrateArgument migrateArgument,
						   final String... keys) {
		return migrate(host, RedisNode.DEFAULT_PORT, db, timeout, migrateArgument, keys);
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
	 * @param migrateArgument
	 * 		迁移参数
	 * @param keys
	 * 		Keys
	 *
	 * @return 操作结果
	 */
	default Status migrate(final String host, final int db, final int timeout, final MigrateArgument migrateArgument,
						   final byte[]... keys) {
		return migrate(host, RedisNode.DEFAULT_PORT, db, timeout, migrateArgument, keys);
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
	 * @param migrateArgument
	 * 		迁移参数
	 * @param keys
	 * 		Keys
	 *
	 * @return 操作结果
	 */
	default Status migrate(final RedisNode server, final int db, final int timeout,
						   final MigrateArgument migrateArgument, final String... keys) {
		Assert.isNull(server, "Destination redis node cloud not be null");
		Assert.isBlank(server.getHost(), "Destination redis host cloud not be null or empty");

		return migrate(server.getHost(), server.getPort(), db, timeout, migrateArgument, keys);
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
	 * @param migrateArgument
	 * 		迁移参数
	 * @param keys
	 * 		Keys
	 *
	 * @return 操作结果
	 */
	default Status migrate(final RedisNode server, final int db, final int timeout,
						   final MigrateArgument migrateArgument, final byte[]... keys) {
		Assert.isNull(server, "Destination redis node cloud not be null");
		Assert.isBlank(server.getHost(), "Destination redis host cloud not be null or empty");

		return migrate(server.getHost(), server.getPort(), db, timeout, migrateArgument, keys);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/pexpireat.html" target="_blank">http://redisdoc.com/expire/pexpireat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final String key, final Date date) {
		Assert.isNull(date, "Expire date could not be null");
		return pExpireAt(key, date.getTime());
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/expire/pexpireat.html" target="_blank">http://redisdoc.com/expire/pexpireat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final byte[] key, final Date date) {
		Assert.isNull(date, "Expire date could not be null");
		return pExpireAt(key, date.getTime());
	}

	/**
	 * 获取给定 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expiretime/" target="_blank">https://redis.io/docs/latest/commands/expiretime/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当 key 不存在时，或没有设置剩余生存时间时，返回 null；否则返回过期时间
	 *
	 * @since 3.0.0
	 */
	default Date pExpireTimeAt(final String key) {
		Long ttl = pExpireTime(key);
		return ttl != null && ttl >= 0 ? new Date(ttl) : null;
	}

	/**
	 * 获取给定 key 的过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/expiretime/" target="_blank">https://redis.io/docs/latest/commands/expiretime/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当 key 不存在时，或没有设置剩余生存时间时，返回 null；否则返回过期时间
	 *
	 * @since 3.0.0
	 */
	default Date pExpireTimeAt(final byte[] key) {
		Long ttl = pExpireTime(key);
		return ttl != null && ttl >= 0 ? new Date(ttl) : null;
	}

	@Override
	default String randomKey() {
		return execute((client)->client.keyOperations().randomKey());
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

	@Override
	default Long wait(final int replicas, final int timeout) {
		return execute((client)->client.keyOperations().wait(replicas, timeout));
	}

	@Override
	default KeyValue<Long, Long> waitOf(final int locals, final int replicas, final int timeout) {
		return execute((client)->client.keyOperations().waitOf(locals, replicas, timeout));
	}

}
