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

import com.buession.core.type.TypeReference;
import com.buession.core.utils.NumberUtils;
import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.command.HashCommands;
import com.buession.redis.core.command.args.GetExArgument;
import com.buession.redis.core.command.args.HSetExArgument;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 哈希表运算
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands//?group=hash" target="_blank">https://redis.io/docs/latest/commands//?group=hash</a></p>
 *
 * @author Yong.Teng
 */
public interface HashOperations extends HashCommands, RedisOperations {

	@Override
	default Long hDel(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hDel(key, fields));
	}

	@Override
	default Long hDel(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hDel(key, fields));
	}

	/**
	 * 删除哈希表 key 中的一个或多个指定域
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hdel.html" target="_blank">http://redisdoc.com/hash/hdel.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return 被成功删除的域的数量
	 */
	default Long hDelete(final String key, final String... fields) {
		return hDel(key, fields);
	}

	/**
	 * 删除哈希表 key 中的一个或多个指定域
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hdel.html" target="_blank">http://redisdoc.com/hash/hdel.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return 被成功删除的域的数量
	 */
	default Long hDelete(final byte[] key, final byte[]... fields) {
		return hDel(key, fields);
	}

	@Override
	default Boolean hExists(final String key, final String field) {
		return execute((client)->client.hashOperations().hExists(key, field));
	}

	@Override
	default Boolean hExists(final byte[] key, final byte[] field) {
		return execute((client)->client.hashOperations().hExists(key, field));
	}

	@Override
	default List<Long> hExpire(final String key, final long ttl, final String... fields) {
		return execute((client)->client.hashOperations().hExpire(key, ttl, fields));
	}

	@Override
	default List<Long> hExpire(final byte[] key, final long ttl, final byte[]... fields) {
		return execute((client)->client.hashOperations().hExpire(key, ttl, fields));
	}

	@Override
	default List<Long> hExpire(final String key, final long ttl, final ExpireOption option, final String... fields) {
		return execute((client)->client.hashOperations().hExpire(key, ttl, option, fields));
	}

	@Override
	default List<Long> hExpire(final byte[] key, final long ttl, final ExpireOption option, final byte[]... fields) {
		return execute((client)->client.hashOperations().hExpire(key, ttl, option, fields));
	}

	@Override
	default List<Long> hExpireAt(final String key, final long unixTimestamp, final String... fields) {
		return execute((client)->client.hashOperations().hExpireAt(key, unixTimestamp, fields));
	}

	@Override
	default List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		return execute((client)->client.hashOperations().hExpireAt(key, unixTimestamp, fields));
	}

	@Override
	default List<Long> hExpireAt(final String key, final long unixTimestamp, final ExpireOption option,
								 final String... fields) {
		return execute((client)->client.hashOperations().hExpireAt(key, unixTimestamp, option, fields));
	}

	@Override
	default List<Long> hExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption option,
								 final byte[]... fields) {
		return execute((client)->client.hashOperations().hExpireAt(key, unixTimestamp, option, fields));
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hExpireAt(final String key, final Date date, final String... fields) {
		return hExpireAt(key, date.getTime() / 1000, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hExpireAt(final byte[] key, final Date date, final byte[]... fields) {
		return hExpireAt(key, date.getTime() / 1000, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hExpireAt(final String key, final LocalDateTime dateTime, final String... fields) {
		return hExpireAt(key, dateTime.atZone(ZoneId.systemDefault()).toInstant(), fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hExpireAt(final byte[] key, final LocalDateTime dateTime, final byte[]... fields) {
		return hExpireAt(key, dateTime.atZone(ZoneId.systemDefault()).toInstant(), fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hExpireAt(final String key, final ZonedDateTime dateTime, final String... fields) {
		return hExpireAt(key, dateTime.toEpochSecond(), fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hExpireAt(final byte[] key, final ZonedDateTime dateTime, final byte[]... fields) {
		return hExpireAt(key, dateTime.toEpochSecond(), fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		过期时间
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hExpireAt(final String key, final Instant instant, final String... fields) {
		return hExpireAt(key, instant.toEpochMilli() / 1000, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		过期时间
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hExpireAt(final byte[] key, final Instant instant, final byte[]... fields) {
		return hExpireAt(key, instant.toEpochMilli() / 1000, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 * @param option
	 * 		选项
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hExpireAt(final String key, final Date date, final ExpireOption option, final String... fields) {
		return hExpireAt(key, date.getTime(), option, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param option
	 * 		选项
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hExpireAt(final String key, final LocalDateTime dateTime, final ExpireOption option,
								 final String... fields) {
		return hExpireAt(key, dateTime.atZone(ZoneId.systemDefault()).toInstant(), option, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param option
	 * 		选项
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hExpireAt(final String key, final ZonedDateTime dateTime, final ExpireOption option,
								 final String... fields) {
		return hExpireAt(key, dateTime.toEpochSecond(), option, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		过期时间
	 * @param option
	 * 		选项
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hExpireAt(final String key, final Instant instant, final ExpireOption option,
								 final String... fields) {
		return hExpireAt(key, instant.toEpochMilli() / 1000, option, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 * @param option
	 * 		选项
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hExpireAt(final byte[] key, final Date date, final ExpireOption option, final byte[]... fields) {
		return hExpireAt(key, date.getTime(), option, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param option
	 * 		选项
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hExpireAt(final byte[] key, final LocalDateTime dateTime, final ExpireOption option,
								 final byte[]... fields) {
		return hExpireAt(key, dateTime.atZone(ZoneId.systemDefault()).toInstant(), option, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		过期时间
	 * @param option
	 * 		选项
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hExpireAt(final byte[] key, final Instant instant, final ExpireOption option,
								 final byte[]... fields) {
		return hExpireAt(key, instant.toEpochMilli() / 1000, option, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param option
	 * 		选项
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hExpireAt(final byte[] key, final ZonedDateTime dateTime, final ExpireOption option,
								 final byte[]... fields) {
		return hExpireAt(key, dateTime.toEpochSecond(), option, fields);
	}

	@Override
	default List<Long> hExpireTime(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hExpireTime(key, fields));
	}

	@Override
	default List<Long> hExpireTime(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hExpireTime(key, fields));
	}

	@Override
	default String hGet(final String key, final String field) {
		return execute((client)->client.hashOperations().hGet(key, field));
	}

	@Override
	default byte[] hGet(final byte[] key, final byte[] field) {
		return execute((client)->client.hashOperations().hGet(key, field));
	}

	/**
	 * 获取哈希表中给定域的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hget.html" target="_blank">http://redisdoc.com/hash/hget.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表中给定域的值反序列化后的对象，如果给定域不存在于哈希表中，又或者给定的哈希表并不存在，则返回 null
	 */
	<V> V hGetObject(final String key, final String field);

	/**
	 * 获取哈希表中给定域的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hget.html" target="_blank">http://redisdoc.com/hash/hget.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表中给定域的值反序列化后的对象，如果给定域不存在于哈希表中，又或者给定的哈希表并不存在，则返回 null
	 */
	<V> V hGetObject(final byte[] key, final byte[] field);

	/**
	 * 获取哈希表中给定域的值，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hget.html" target="_blank">http://redisdoc.com/hash/hget.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表中给定域的值反序列化后的对象，如果给定域不存在于哈希表中，又或者给定的哈希表并不存在，则返回 null
	 */
	<V> V hGetObject(final String key, final String field, final Class<V> clazz);

	/**
	 * 获取哈希表中给定域的值，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hget.html" target="_blank">http://redisdoc.com/hash/hget.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表中给定域的值反序列化后的对象，如果给定域不存在于哈希表中，又或者给定的哈希表并不存在，则返回 null
	 */
	<V> V hGetObject(final byte[] key, final byte[] field, final Class<V> clazz);

	/**
	 * 获取哈希表中给定域的值，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hget.html" target="_blank">http://redisdoc.com/hash/hget.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表中给定域的值反序列化后的对象，如果给定域不存在于哈希表中，又或者给定的哈希表并不存在，则返回 null
	 */
	<V> V hGetObject(final String key, final String field, final TypeReference<V> type);

	/**
	 * 获取哈希表中给定域的值，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hget.html" target="_blank">http://redisdoc.com/hash/hget.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表中给定域的值反序列化后的对象，如果给定域不存在于哈希表中，又或者给定的哈希表并不存在，则返回 null
	 */
	<V> V hGetObject(final byte[] key, final byte[] field, final TypeReference<V> type);

	@Override
	default Map<String, String> hGetAll(final String key) {
		return execute((client)->client.hashOperations().hGetAll(key));
	}

	@Override
	default Map<byte[], byte[]> hGetAll(final byte[] key) {
		return execute((client)->client.hashOperations().hGetAll(key));
	}

	/**
	 * 获取哈希表 key 中，所有的域和值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hgetall.html" target="_blank">http://redisdoc.com/hash/hgetall.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中，所有的域和值
	 */
	<V> Map<String, V> hGetAllObject(final String key);

	/**
	 * 获取哈希表 key 中，所有的域和值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hgetall.html" target="_blank">http://redisdoc.com/hash/hgetall.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中，所有的域和值
	 */
	<V> Map<byte[], V> hGetAllObject(final byte[] key);

	/**
	 * 获取哈希表 key 中，所有的域和值，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hgetall.html" target="_blank">http://redisdoc.com/hash/hgetall.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中，所有的域和值
	 */
	<V> Map<String, V> hGetAllObject(final String key, final Class<V> clazz);

	/**
	 * 获取哈希表 key 中，所有的域和值，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hgetall.html" target="_blank">http://redisdoc.com/hash/hgetall.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中，所有的域和值
	 */
	<V> Map<byte[], V> hGetAllObject(final byte[] key, final Class<V> clazz);

	/**
	 * 获取哈希表 key 中，所有的域和值，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hgetall.html" target="_blank">http://redisdoc.com/hash/hgetall.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中，所有的域和值
	 */
	<V> Map<String, V> hGetAllObject(final String key, final TypeReference<V> type);

	/**
	 * 获取哈希表 key 中，所有的域和值，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hgetall.html" target="_blank">http://redisdoc.com/hash/hgetall.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中，所有的域和值
	 */
	<V> Map<byte[], V> hGetAllObject(final byte[] key, final TypeReference<V> type);

	@Override
	default List<String> hGetDel(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hGetDel(key, fields));
	}

	@Override
	default List<byte[]> hGetDel(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hGetDel(key, fields));
	}

	/**
	 * 从哈希（Hash）中获取一个字段的值，并同时删除该字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中获取并删除域的值
	 */
	<V> List<V> hGetDelObject(final String key, final String... fields);

	/**
	 * 获取哈希表 key 中，所有的域和值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中获取并删除域的值
	 */
	<V> List<V> hGetDelObject(final byte[] key, final byte[]... fields);

	/**
	 * 获取哈希表 key 中，所有的域和值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中，所有的域和值
	 */
	<V> List<V> hGetDelObject(final String key, final String[] fields, final Class<V> clazz);

	/**
	 * 获取哈希表 key 中，所有的域和值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中，所有的域和值
	 */
	<V> List<V> hGetDelObject(final byte[] key, final byte[][] fields, final Class<V> clazz);

	/**
	 * 获取哈希表 key 中，所有的域和值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中，所有的域和值
	 */
	<V> List<V> hGetDelObject(final String key, final String[] fields, final TypeReference<V> type);

	/**
	 * 获取哈希表 key 中，所有的域和值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中，所有的域和值
	 */
	<V> List<V> hGetDelObject(final byte[] key, final byte[][] fields, final TypeReference<V> type);

	@Override
	default List<String> hGetEx(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hGetEx(key, fields));
	}

	@Override
	default List<byte[]> hGetEx(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hGetEx(key, fields));
	}

	@Override
	default List<String> hGetEx(final String key, final GetExArgument argument, final String... fields) {
		return execute((client)->client.hashOperations().hGetEx(key, argument, fields));
	}

	@Override
	default List<byte[]> hGetEx(final byte[] key, final GetExArgument argument, final byte[]... fields) {
		return execute((client)->client.hashOperations().hGetEx(key, argument, fields));
	}

	/**
	 * 从哈希（Hash）中获取一个字段的值，并将值反序列化为对象，并同时删除该字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中获取并删除域的值
	 */
	<V> List<V> hGetExObject(final String key, final String... fields);

	/**
	 * 从哈希（Hash）中获取一个字段的值，并将值反序列化为对象，并同时删除该字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中获取并删除域的值
	 */
	<V> List<V> hGetExObject(final byte[] key, final byte[]... fields);

	/**
	 * 从哈希（Hash）中获取一个字段的值，并将值反序列化为对象，并同时删除该字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中获取并删除域的值
	 */
	<V> List<V> hGetExObject(final String key, final String[] fields, final Class<V> clazz);

	/**
	 * 从哈希（Hash）中获取一个字段的值，并将值反序列化为对象，并同时删除该字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中获取并删除域的值
	 */
	<V> List<V> hGetExObject(final byte[] key, final byte[][] fields, final Class<V> clazz);

	/**
	 * 从哈希（Hash）中获取一个字段的值，并将值反序列化为对象，并同时删除该字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中获取并删除域的值
	 */
	<V> List<V> hGetExObject(final String key, final String[] fields, final TypeReference<V> type);

	/**
	 * 从哈希（Hash）中获取一个字段的值，并将值反序列化为对象，并同时删除该字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中获取并删除域的值
	 */
	<V> List<V> hGetExObject(final byte[] key, final byte[][] fields, final TypeReference<V> type);

	/**
	 * 从哈希（Hash）中获取一个字段的值，并将值反序列化为对象，并同时删除该字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 * @param fields
	 * 		一个或多个域
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中获取并删除域的值
	 */
	<V> List<V> hGetExObject(final String key, final GetExArgument argument, final String... fields);

	/**
	 * 从哈希（Hash）中获取一个字段的值，并将值反序列化为对象，并同时删除该字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 * @param fields
	 * 		一个或多个域
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中获取并删除域的值
	 */
	<V> List<V> hGetExObject(final byte[] key, final GetExArgument argument, final byte[]... fields);

	/**
	 * 从哈希（Hash）中获取一个字段的值，并将值反序列化为对象，并同时删除该字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中获取并删除域的值
	 */
	<V> List<V> hGetExObject(final String key, final GetExArgument argument, final String[] fields,
							 final Class<V> clazz);

	/**
	 * 从哈希（Hash）中获取一个字段的值，并将值反序列化为对象，并同时删除该字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 * @param fields
	 * 		一个或多个域
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中获取并删除域的值
	 */
	<V> List<V> hGetExObject(final byte[] key, final GetExArgument argument, final byte[][] fields,
							 final Class<V> clazz);

	/**
	 * 从哈希（Hash）中获取一个字段的值，并将值反序列化为对象，并同时删除该字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 * @param fields
	 * 		一个或多个域
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中获取并删除域的值
	 */
	<V> List<V> hGetExObject(final String key, final GetExArgument argument, final String[] fields,
							 final TypeReference<V> type);

	/**
	 * 从哈希（Hash）中获取一个字段的值，并将值反序列化为对象，并同时删除该字段
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hgetdel/" target="_blank">https://redis.io/docs/latest/commands/hgetdel/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		参数
	 * @param fields
	 * 		一个或多个域
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中获取并删除域的值
	 */
	<V> List<V> hGetExObject(final byte[] key, final GetExArgument argument, final byte[][] fields,
							 final TypeReference<V> type);

	@Override
	default Long hIncrBy(final String key, final String field, final long value) {
		return execute((client)->client.hashOperations().hIncrBy(key, field, value));
	}

	@Override
	default Long hIncrBy(final byte[] key, final byte[] field, final long value) {
		return execute((client)->client.hashOperations().hIncrBy(key, field, value));
	}

	/**
	 * 为哈希表 key 中的域 field 的值加上减量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hincrby.html" target="_blank">http://redisdoc.com/hash/hincrby.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 *
	 * @return 哈希表 key 中域 field 减量 increment 后的值
	 */
	default Long hDecrBy(final String key, final String field, final long value) {
		return hIncrBy(key, field, value > 0 ? value * -1 : value);
	}

	/**
	 * 为哈希表 key 中的域 field 的值加上减量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hincrby.html" target="_blank">http://redisdoc.com/hash/hincrby.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 *
	 * @return 哈希表 key 中域 field 减量 increment 后的值
	 */
	default Long hDecrBy(final byte[] key, final byte[] field, final long value) {
		return hIncrBy(key, field, value > 0 ? value * -1 : value);
	}

	@Override
	default Double hIncrByFloat(final String key, final String field, final double value) {
		return execute((client)->client.hashOperations().hIncrByFloat(key, field, value));
	}

	@Override
	default Double hIncrByFloat(final byte[] key, final byte[] field, final double value) {
		return execute((client)->client.hashOperations().hIncrByFloat(key, field, value));
	}

	/**
	 * 为哈希表 key 中的域 field 加上浮点数减量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hincrbyfloat.html" target="_blank">http://redisdoc.com/hash/hincrbyfloat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 *
	 * @return 哈希表 key 中域 field 减量 increment 后的值
	 */
	default Double hDecrByFloat(final String key, final String field, final double value) {
		return hIncrByFloat(key, field, value > 0 ? value * -1 : value);
	}

	/**
	 * 为哈希表 key 中的域 field 加上浮点数减量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hincrbyfloat.html" target="_blank">http://redisdoc.com/hash/hincrbyfloat.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 *
	 * @return 哈希表 key 中域 field 减量 increment 后的值
	 */
	default Double hDecrByFloat(final byte[] key, final byte[] field, final double value) {
		return hIncrByFloat(key, field, value > 0 ? value * -1 : value);
	}

	@Override
	default Set<String> hKeys(final String key) {
		return execute((client)->client.hashOperations().hKeys(key));
	}

	@Override
	default Set<byte[]> hKeys(final byte[] key) {
		return execute((client)->client.hashOperations().hKeys(key));
	}

	@Override
	default Long hLen(final String key) {
		return execute((client)->client.hashOperations().hLen(key));
	}

	@Override
	default Long hLen(final byte[] key) {
		return execute((client)->client.hashOperations().hLen(key));
	}

	@Override
	default List<String> hMGet(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hMGet(key, fields));
	}

	@Override
	default List<byte[]> hMGet(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hMGet(key, fields));
	}

	/**
	 * 获取哈希表 key 中，一个或多个给定域的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hmget.html" target="_blank">http://redisdoc.com/hash/hmget.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含多个给定域的关联值的表，值的排列顺序和给定域参数的请求顺序一样
	 */
	<V> List<V> hMGetObject(final String key, final String... fields);

	/**
	 * 获取哈希表 key 中，一个或多个给定域的值，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hmget.html" target="_blank">http://redisdoc.com/hash/hmget.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含多个给定域的关联值的表，值的排列顺序和给定域参数的请求顺序一样
	 */
	<V> List<V> hMGetObject(final byte[] key, final byte[]... fields);

	/**
	 * 获取哈希表 key 中，一个或多个给定域的值，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hmget.html" target="_blank">http://redisdoc.com/hash/hmget.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含多个给定域的关联值的表，值的排列顺序和给定域参数的请求顺序一样
	 */
	<V> List<V> hMGetObject(final String key, final String[] fields, final Class<V> clazz);

	/**
	 * 获取哈希表 key 中，一个或多个给定域的值，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hmget.html" target="_blank">http://redisdoc.com/hash/hmget.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含多个给定域的关联值的表，值的排列顺序和给定域参数的请求顺序一样
	 */
	<V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final Class<V> clazz);

	/**
	 * 获取哈希表 key 中，一个或多个给定域的值，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hmget.html" target="_blank">http://redisdoc.com/hash/hmget.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含多个给定域的关联值的表，值的排列顺序和给定域参数的请求顺序一样
	 */
	<V> List<V> hMGetObject(final String key, final String[] fields, final TypeReference<V> type);

	/**
	 * 获取哈希表 key 中，一个或多个给定域的值，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hmget.html" target="_blank">http://redisdoc.com/hash/hmget.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param fields
	 * 		一个或多个域
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 一个包含多个给定域的关联值的表，值的排列顺序和给定域参数的请求顺序一样
	 */
	<V> List<V> hMGetObject(final byte[] key, final byte[][] fields, final TypeReference<V> type);

	@Override
	default Status hMSet(final String key, final Map<String, String> data) {
		return execute((client)->client.hashOperations().hMSet(key, data));
	}

	@Override
	default Status hMSet(final byte[] key, final Map<byte[], byte[]> data) {
		return execute((client)->client.hashOperations().hMSet(key, data));
	}

	/**
	 * 批量将多个 field =&gt; value (域-值)对设置到哈希表 key 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hmset.html" target="_blank">http://redisdoc.com/hash/hmset.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 执行成功返回 Status.Success，否则返回 Status.FAILURE
	 */
	<V> Status hMSet(final String key, final List<KeyValue<String, V>> data);

	/**
	 * 批量将多个 field =&gt; value (域-值)对设置到哈希表 key 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hmset.html" target="_blank">http://redisdoc.com/hash/hmset.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 执行成功返回 Status.Success，否则返回 Status.FAILURE
	 */
	<V> Status hMSet(final byte[] key, final List<KeyValue<byte[], V>> data);

	/**
	 * 批量将多个 field =&gt; value (域-值)对设置到哈希表 key 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hmset.html" target="_blank">http://redisdoc.com/hash/hmset.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 执行成功返回 Status.Success，否则返回 Status.FAILURE
	 *
	 * @since 3.0.0
	 */
	@SuppressWarnings({"unchecked"})
	default <V> Status hMSet(final String key, final KeyValue<String, V>... data) {
		return hMSet(key, Arrays.asList(data));
	}

	/**
	 * 批量将多个 field =&gt; value (域-值)对设置到哈希表 key 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hmset.html" target="_blank">http://redisdoc.com/hash/hmset.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 执行成功返回 Status.Success，否则返回 Status.FAILURE
	 *
	 * @since 3.0.0
	 */
	@SuppressWarnings({"unchecked"})
	default <V> Status hMSet(final byte[] key, final KeyValue<byte[], V>... data) {
		return hMSet(key, Arrays.asList(data));
	}

	@Override
	default List<Long> hPersist(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hPersist(key, fields));
	}

	@Override
	default List<Long> hPersist(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hPersist(key, fields));
	}

	@Override
	default List<Long> hPExpire(final String key, final long ttl, final String... fields) {
		return execute((client)->client.hashOperations().hPExpire(key, ttl, fields));
	}

	@Override
	default List<Long> hPExpire(final byte[] key, final long ttl, final byte[]... fields) {
		return execute((client)->client.hashOperations().hPExpire(key, ttl, fields));
	}

	@Override
	default List<Long> hPExpire(final String key, final long ttl, final ExpireOption option, final String... fields) {
		return execute((client)->client.hashOperations().hPExpire(key, ttl, option, fields));
	}

	@Override
	default List<Long> hPExpire(final byte[] key, final long ttl, final ExpireOption option, final byte[]... fields) {
		return execute((client)->client.hashOperations().hPExpire(key, ttl, option, fields));
	}

	@Override
	default List<Long> hPExpireAt(final String key, final long unixTimestamp, final String... fields) {
		return execute((client)->client.hashOperations().hPExpireAt(key, unixTimestamp, fields));
	}

	@Override
	default List<Long> hPExpireAt(final byte[] key, final long unixTimestamp, final byte[]... fields) {
		return execute((client)->client.hashOperations().hPExpireAt(key, unixTimestamp, fields));
	}

	@Override
	default List<Long> hPExpireAt(final String key, final long unixTimestamp, final ExpireOption option,
								  final String... fields) {
		return execute((client)->client.hashOperations().hPExpireAt(key, unixTimestamp, option, fields));
	}

	@Override
	default List<Long> hPExpireAt(final byte[] key, final long unixTimestamp, final ExpireOption option,
								  final byte[]... fields) {
		return execute((client)->client.hashOperations().hPExpireAt(key, unixTimestamp, option, fields));
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hPExpireAt(final String key, final Date date, final String... fields) {
		return hPExpireAt(key, date.getTime(), fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hPExpireAt(final byte[] key, final Date date, final byte[]... fields) {
		return hPExpireAt(key, date.getTime(), fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hPExpireAt(final String key, final LocalDateTime dateTime, final String... fields) {
		return hPExpireAt(key, dateTime.atZone(ZoneId.systemDefault()).toInstant(), fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hPExpireAt(final byte[] key, final LocalDateTime dateTime, final byte[]... fields) {
		return hPExpireAt(key, dateTime.atZone(ZoneId.systemDefault()).toInstant(), fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hPExpireAt(final String key, final ZonedDateTime dateTime, final String... fields) {
		return hPExpireAt(key, dateTime.toInstant(), fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hPExpireAt(final byte[] key, final ZonedDateTime dateTime, final byte[]... fields) {
		return hPExpireAt(key, dateTime.toInstant(), fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		过期时间
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hPExpireAt(final String key, final Instant instant, final String... fields) {
		return hPExpireAt(key, instant.toEpochMilli(), fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		过期时间
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hPExpireAt(final byte[] key, final Instant instant, final byte[]... fields) {
		return hPExpireAt(key, instant.toEpochMilli(), fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 * @param option
	 * 		选项
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hPExpireAt(final String key, final Date date, final ExpireOption option,
								  final String... fields) {
		return hPExpireAt(key, date.getTime(), option, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param option
	 * 		选项
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hPExpireAt(final String key, final LocalDateTime dateTime, final ExpireOption option,
								  final String... fields) {
		return hPExpireAt(key, dateTime.atZone(ZoneId.systemDefault()).toInstant(), option, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param option
	 * 		选项
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hPExpireAt(final String key, final ZonedDateTime dateTime, final ExpireOption option,
								  final String... fields) {
		return hPExpireAt(key, dateTime.toInstant(), option, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		过期时间
	 * @param option
	 * 		选项
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hPExpireAt(final String key, final Instant instant, final ExpireOption option,
								  final String... fields) {
		return hPExpireAt(key, instant.toEpochMilli(), option, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 * @param option
	 * 		选项
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hPExpireAt(final byte[] key, final Date date, final ExpireOption option,
								  final byte[]... fields) {
		return hPExpireAt(key, date.getTime(), option, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param option
	 * 		选项
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hPExpireAt(final byte[] key, final LocalDateTime dateTime, final ExpireOption option,
								  final byte[]... fields) {
		return hPExpireAt(key, dateTime.atZone(ZoneId.systemDefault()).toInstant(), option, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param instant
	 * 		过期时间
	 * @param option
	 * 		选项
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hPExpireAt(final byte[] key, final Instant instant, final ExpireOption option,
								  final byte[]... fields) {
		return hPExpireAt(key, instant.toEpochMilli(), option, fields);
	}

	/**
	 * 为哈希中的一个或多个字段设置过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hexpireat/" target="_blank">https://redis.io/docs/latest/commands/hexpireat/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param dateTime
	 * 		过期时间
	 * @param option
	 * 		选项
	 * @param fields
	 * 		一个或多个域
	 *
	 * @return -2 if no such field exists in the provided hash key, or the provided key does not exist;0 if the
	 * specified NX, XX, GT, or LT condition has not been met;1 if the expiration time was set/updated;
	 * 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
	 */
	default List<Long> hPExpireAt(final byte[] key, final ZonedDateTime dateTime, final ExpireOption option,
								  final byte[]... fields) {
		return hPExpireAt(key, dateTime.toInstant(), option, fields);
	}

	@Override
	default List<Long> hPExpireTime(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hPExpireTime(key, fields));
	}

	@Override
	default List<Long> hPExpireTime(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hPExpireTime(key, fields));
	}

	@Override
	default String hRandField(final String key) {
		return execute((client)->client.hashOperations().hRandField(key));
	}

	@Override
	default byte[] hRandField(final byte[] key) {
		return execute((client)->client.hashOperations().hRandField(key));
	}

	@Override
	default List<String> hRandField(final String key, final long count) {
		return execute((client)->client.hashOperations().hRandField(key, count));
	}

	@Override
	default List<byte[]> hRandField(final byte[] key, final long count) {
		return execute((client)->client.hashOperations().hRandField(key, count));
	}

	@Override
	default Map<String, String> hRandFieldWithValues(final String key, final long count) {
		return execute((client)->client.hashOperations().hRandFieldWithValues(key, count));
	}

	@Override
	default Map<byte[], byte[]> hRandFieldWithValues(final byte[] key, final long count) {
		return execute((client)->client.hashOperations().hRandFieldWithValues(key, count));
	}

	/**
	 * When called with just the key argument, return a random field from the hash value stored at key.
	 * If the provided count argument is positive, return an array of distinct fields.
	 * The array’s length is either count or the hash’s number of fields (HLEN), whichever is lower.
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/hrandfield/" target="_blank">https://redis.io/commands/hrandfield/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		返回数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return A list fields and their values from the hash
	 */
	<V> Map<String, V> hRandFieldWithValuesObject(final String key, final long count);

	/**
	 * When called with just the key argument, return a random field from the hash value stored at key.
	 * If the provided count argument is positive, return an array of distinct fields.
	 * The array’s length is either count or the hash’s number of fields (HLEN), whichever is lower.
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/hrandfield/" target="_blank">https://redis.io/commands/hrandfield/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		返回数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return A list fields and their values from the hash
	 */
	<V> Map<byte[], V> hRandFieldWithValuesObject(final byte[] key, final long count);

	/**
	 * When called with just the key argument, return a random field from the hash value stored at key.
	 * If the provided count argument is positive, return an array of distinct fields.
	 * The array’s length is either count or the hash’s number of fields (HLEN), whichever is lower.
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/hrandfield/" target="_blank">https://redis.io/commands/hrandfield/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		返回数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return A list fields and their values from the hash
	 */
	<V> Map<String, V> hRandFieldWithValuesObject(final String key, final long count, final Class<V> clazz);

	/**
	 * When called with just the key argument, return a random field from the hash value stored at key.
	 * If the provided count argument is positive, return an array of distinct fields.
	 * The array’s length is either count or the hash’s number of fields (HLEN), whichever is lower.
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/hrandfield/" target="_blank">https://redis.io/commands/hrandfield/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		返回数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return A list fields and their values from the hash
	 */
	<V> Map<byte[], V> hRandFieldWithValuesObject(final byte[] key, final long count, final Class<V> clazz);

	/**
	 * When called with just the key argument, return a random field from the hash value stored at key.
	 * If the provided count argument is positive, return an array of distinct fields.
	 * The array’s length is either count or the hash’s number of fields (HLEN), whichever is lower.
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/hrandfield/" target="_blank">https://redis.io/commands/hrandfield/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		返回数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return A list fields and their values from the hash
	 */
	<V> Map<String, V> hRandFieldWithValuesObject(final String key, final long count, final TypeReference<V> type);

	/**
	 * When called with just the key argument, return a random field from the hash value stored at key.
	 * If the provided count argument is positive, return an array of distinct fields.
	 * The array’s length is either count or the hash’s number of fields (HLEN), whichever is lower.
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/hrandfield/" target="_blank">https://redis.io/commands/hrandfield/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		返回数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return A list fields and their values from the hash
	 */
	<V> Map<byte[], V> hRandFieldWithValuesObject(final byte[] key, final long count, final TypeReference<V> type);

	@Override
	default ScanResult<Map<String, String>> hScan(final String key, final String cursor) {
		return execute((client)->client.hashOperations().hScan(key, cursor));
	}

	@Override
	default ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor) {
		return execute((client)->client.hashOperations().hScan(key, cursor));
	}

	@Override
	default ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern) {
		return execute((client)->client.hashOperations().hScan(key, cursor, pattern));
	}

	@Override
	default ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		return execute((client)->client.hashOperations().hScan(key, cursor, pattern));
	}

	@Override
	default ScanResult<Map<String, String>> hScan(final String key, final String cursor, final long count) {
		return execute((client)->client.hashOperations().hScan(key, cursor, count));
	}

	@Override
	default ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final long count) {
		return execute((client)->client.hashOperations().hScan(key, cursor, count));
	}

	@Override
	default ScanResult<Map<String, String>> hScan(final String key, final String cursor, final String pattern,
												  final long count) {
		return execute((client)->client.hashOperations().hScan(key, cursor, pattern, count));
	}

	@Override
	default ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final byte[] cursor, final byte[] pattern,
												  final long count) {
		return execute((client)->client.hashOperations().hScan(key, cursor, pattern, count));
	}

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default ScanResult<Map<String, String>> hScan(final String key, final long cursor) {
		return hScan(key, Long.toString(cursor));
	}

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor) {
		return hScan(key, NumberUtils.long2bytes(cursor));
	}

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 *
	 * @return 返回和给定模式相匹配的元素
	 */
	default ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern) {
		return hScan(key, Long.toString(cursor), pattern);
	}

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 *
	 * @return 返回和给定模式相匹配的元素
	 */
	default ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern) {
		return hScan(key, NumberUtils.long2bytes(cursor), pattern);
	}

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 *
	 * @return 返回的指定数量的键值对
	 */
	default ScanResult<Map<String, String>> hScan(final String key, final long cursor, final long count) {
		return hScan(key, Long.toString(cursor), count);
	}

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 *
	 * @return 返回的指定数量的键值对
	 */
	default ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final long count) {
		return hScan(key, NumberUtils.long2bytes(cursor), count);
	}

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 *
	 * @return 返回和给定模式相匹配指定数量的元素
	 */
	default ScanResult<Map<String, String>> hScan(final String key, final long cursor, final String pattern,
												  final long count) {
		return hScan(key, Long.toString(cursor), pattern, count);
	}

	/**
	 * 迭代哈希键 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 *
	 * @return 返回和给定模式相匹配指定数量的元素
	 */
	default ScanResult<Map<byte[], byte[]>> hScan(final byte[] key, final long cursor, final byte[] pattern,
												  final long count) {
		return hScan(key, NumberUtils.long2bytes(cursor), pattern, count);
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor) {
		return hScanObject(key, Long.toString(cursor));
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor) {
		return hScanObject(key, NumberUtils.long2bytes(cursor));
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a>
	 * </p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final Class<V> clazz) {
		return hScanObject(key, Long.toString(cursor), clazz);
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final Class<V> clazz) {
		return hScanObject(key, NumberUtils.long2bytes(cursor), clazz);
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor,
													   final TypeReference<V> type) {
		return hScanObject(key, Long.toString(cursor), type);
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor,
													   final TypeReference<V> type) {
		return hScanObject(key, NumberUtils.long2bytes(cursor), type);
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final Class<V> clazz);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final Class<V> clazz);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final TypeReference<V> type);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final TypeReference<V> type);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final String pattern) {
		return hScanObject(key, Long.toString(cursor), pattern);
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final byte[] pattern) {
		return hScanObject(key, NumberUtils.long2bytes(cursor), pattern);
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final String pattern,
													   final Class<V> clazz) {
		return hScanObject(key, Long.toString(cursor), pattern, clazz);
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final byte[] pattern,
													   final Class<V> clazz) {
		return hScanObject(key, NumberUtils.long2bytes(cursor), pattern, clazz);
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final String pattern,
													   final TypeReference<V> type) {
		return hScanObject(key, Long.toString(cursor), pattern, type);
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final byte[] pattern,
													   final TypeReference<V> type) {
		return hScanObject(key, NumberUtils.long2bytes(cursor), pattern, type);
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
											   final Class<V> clazz);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
											   final Class<V> clazz);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
											   final TypeReference<V> type);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
											   final TypeReference<V> type);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final long count) {
		return hScanObject(key, Long.toString(cursor), count);
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final long count) {
		return hScanObject(key, NumberUtils.long2bytes(cursor), count);
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final long count,
													   final Class<V> clazz) {
		return hScanObject(key, Long.toString(cursor), count, clazz);
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	default <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final long count,
													   final Class<V> clazz) {
		return hScanObject(key, NumberUtils.long2bytes(cursor), count, clazz);
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	default <V> ScanResult<Map<String, V>> hScanObject(final String key, final long cursor, final long count,
													   final TypeReference<V> type) {
		return hScanObject(key, Long.toString(cursor), count, type);
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 *
	 * @see TypeReference
	 */
	default <V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final long cursor, final long count,
													   final TypeReference<V> type) {
		return hScanObject(key, NumberUtils.long2bytes(cursor), count, type);
	}

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final long count);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final long count);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final long count,
											   final Class<V> clazz);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final long count,
											   final Class<V> clazz);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final long count,
											   final TypeReference<V> type);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final long count,
											   final TypeReference<V> type);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
											   final long count);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
											   final long count);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
											   final long count, final Class<V> clazz);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
											   final long count, final Class<V> clazz);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<String, V>> hScanObject(final String key, final String cursor, final String pattern,
											   final long count, final TypeReference<V> type);

	/**
	 * 迭代哈希键 key 中的键值对，并将值反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/hash/hscan.html" target="_blank">http://redisdoc.com/hash/hscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	<V> ScanResult<Map<byte[], V>> hScanObject(final byte[] key, final byte[] cursor, final byte[] pattern,
											   final long count, final TypeReference<V> type);

	@Override
	default Long hSet(final String key, final Map<String, String> data) {
		return execute((client)->client.hashOperations().hSet(key, data));
	}

	@Override
	default Long hSet(final byte[] key, final Map<byte[], byte[]> data) {
		return execute((client)->client.hashOperations().hSet(key, data));
	}

	/**
	 * 批量将多个 field =&gt; value (域-值)对设置到哈希表 key 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hset/" target="_blank">https://redis.io/docs/latest/commands/hset/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被修改或增加的 field 个数
	 */
	<V> Long hSet(final String key, final List<KeyValue<String, V>> data);

	/**
	 * 批量将多个 field =&gt; value (域-值)对设置到哈希表 key 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hset/" target="_blank">https://redis.io/docs/latest/commands/hset/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被修改或增加的 field 个数
	 */
	<V> Long hSet(final byte[] key, final List<KeyValue<byte[], V>> data);

	/**
	 * 批量将多个 field =&gt; value (域-值)对设置到哈希表 key 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hset/" target="_blank">https://redis.io/docs/latest/commands/hset/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被修改或增加的 field 个数
	 */
	@SuppressWarnings({"unchecked"})
	default <V> Long hSet(final String key, final KeyValue<String, V>... data) {
		return hSet(key, Arrays.asList(data));
	}

	/**
	 * 批量将多个 field =&gt; value (域-值)对设置到哈希表 key 中
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hset/" target="_blank">https://redis.io/docs/latest/commands/hset/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被修改或增加的 field 个数
	 */
	@SuppressWarnings({"unchecked"})
	default <V> Long hSet(final byte[] key, final KeyValue<byte[], V>... data) {
		return hSet(key, Arrays.asList(data));
	}

	@Override
	default Status hSetEx(final String key, final Map<String, String> data) {
		return execute((client)->client.hashOperations().hSetEx(key, data));
	}

	@Override
	default Status hSetEx(final byte[] key, final Map<byte[], byte[]> data) {
		return execute((client)->client.hashOperations().hSetEx(key, data));
	}

	@Override
	default Status hSetEx(final String key, final Map<String, String> data, final HSetExArgument argument) {
		return execute((client)->client.hashOperations().hSetEx(key, data, argument));
	}

	@Override
	default Status hSetEx(final byte[] key, final Map<byte[], byte[]> data, final HSetExArgument argument) {
		return execute((client)->client.hashOperations().hSetEx(key, data, argument));
	}

	/**
	 * 哈希中设置一个或多个字段的值，并同时为这些字段指定过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hsetex/" target="_blank">https://redis.io/docs/latest/commands/hsetex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 执行成功返回 Status.Success，否则返回 Status.FAILURE
	 */
	<V> Status hSetEx(final String key, final List<KeyValue<String, V>> data);

	/**
	 * 哈希中设置一个或多个字段的值，并同时为这些字段指定过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hsetex/" target="_blank">https://redis.io/docs/latest/commands/hsetex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 执行成功返回 Status.Success，否则返回 Status.FAILURE
	 */
	<V> Status hSetEx(final byte[] key, final List<KeyValue<byte[], V>> data);

	/**
	 * 哈希中设置一个或多个字段的值，并同时为这些字段指定过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hsetex/" target="_blank">https://redis.io/docs/latest/commands/hsetex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 执行成功返回 Status.Success，否则返回 Status.FAILURE
	 */
	@SuppressWarnings({"unchecked"})
	default <V> Status hSetEx(final String key, final KeyValue<String, V>... data) {
		return hSetEx(key, Arrays.asList(data));
	}

	/**
	 * 哈希中设置一个或多个字段的值，并同时为这些字段指定过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hsetex/" target="_blank">https://redis.io/docs/latest/commands/hsetex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 执行成功返回 Status.Success，否则返回 Status.FAILURE
	 */
	@SuppressWarnings({"unchecked"})
	default <V> Status hSetEx(final byte[] key, final KeyValue<byte[], V>... data) {
		return hSetEx(key, Arrays.asList(data));
	}

	/**
	 * 哈希中设置一个或多个字段的值，并同时为这些字段指定过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hsetex/" target="_blank">https://redis.io/docs/latest/commands/hsetex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param argument
	 * 		参数
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 执行成功返回 Status.Success，否则返回 Status.FAILURE
	 */
	<V> Status hSetEx(final String key, final List<KeyValue<String, V>> data, final HSetExArgument argument);

	/**
	 * 哈希中设置一个或多个字段的值，并同时为这些字段指定过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hsetex/" target="_blank">https://redis.io/docs/latest/commands/hsetex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param argument
	 * 		参数
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 执行成功返回 Status.Success，否则返回 Status.FAILURE
	 */
	<V> Status hSetEx(final byte[] key, final List<KeyValue<byte[], V>> data, final HSetExArgument argument);

	/**
	 * 哈希中设置一个或多个字段的值，并同时为这些字段指定过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hsetex/" target="_blank">https://redis.io/docs/latest/commands/hsetex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param argument
	 * 		参数
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 执行成功返回 Status.Success，否则返回 Status.FAILURE
	 */
	default <V> Status hSetEx(final String key, final KeyValue<String, V>[] data, final HSetExArgument argument) {
		return hSetEx(key, Arrays.asList(data), argument);
	}

	/**
	 * 哈希中设置一个或多个字段的值，并同时为这些字段指定过期时间
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hsetex/" target="_blank">https://redis.io/docs/latest/commands/hsetex/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param data
	 * 		field =&gt; value (域-值)对
	 * @param argument
	 * 		参数
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 执行成功返回 Status.Success，否则返回 Status.FAILURE
	 */
	default <V> Status hSetEx(final byte[] key, final KeyValue<byte[], V>[] data, final HSetExArgument argument) {
		return hSetEx(key, Arrays.asList(data), argument);
	}

	@Override
	default Status hSetNx(final String key, final String field, final String value) {
		return execute((client)->client.hashOperations().hSetNx(key, field, value));
	}

	@Override
	default Status hSetNx(final byte[] key, final byte[] field, final byte[] value) {
		return execute((client)->client.hashOperations().hSetNx(key, field, value));
	}

	/**
	 * 当且仅当域 field 尚未存在于哈希表 key 中的情况下，将它的值设置为 value
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 操作结果；设置成功时返回 Status.Success，在给定域已经存在而放弃执行设置操作时返回 Status.FAILURE
	 */
	<V> Status hSetNx(final String key, final String field, final V value);

	/**
	 * 当且仅当域 field 尚未存在于哈希表 key 中的情况下，将它的值设置为 value
	 *
	 * @param key
	 * 		Key
	 * @param field
	 * 		域
	 * @param value
	 * 		值
	 * @param <V>
	 * 		值类型
	 *
	 * @return 操作结果；设置成功时返回 Status.Success，在给定域已经存在而放弃执行设置操作时返回 Status.FAILURE
	 */
	<V> Status hSetNx(final byte[] key, final byte[] field, final V value);

	@Override
	default Long hStrLen(final String key, final String field) {
		return execute((client)->client.hashOperations().hStrLen(key, field));
	}

	@Override
	default Long hStrLen(final byte[] key, final byte[] field) {
		return execute((client)->client.hashOperations().hStrLen(key, field));
	}

	@Override
	default List<Long> hTtl(final String key, final String... fields) {
		return execute((client)->client.hashOperations().hTtl(key, fields));
	}

	@Override
	default List<Long> hTtl(final byte[] key, final byte[]... fields) {
		return execute((client)->client.hashOperations().hTtl(key, fields));
	}

	@Override
	default List<String> hVals(final String key) {
		return execute((client)->client.hashOperations().hVals(key));
	}

	@Override
	default List<byte[]> hVals(final byte[] key) {
		return execute((client)->client.hashOperations().hVals(key));
	}

	/**
	 * 获取哈希表 key 中所有域的值，并将值反序列化为对象
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中所有域的值反序列化后的对象
	 */
	<V> List<V> hValsObject(final String key);

	/**
	 * 获取哈希表 key 中所有域的值，并将值反序列化为对象
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中所有域的值反序列化后的对象
	 */
	<V> List<V> hValsObject(final byte[] key);

	/**
	 * 获取哈希表 key 中所有域的值，并将值反序列化为 clazz 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中所有域的值反序列化后的对象
	 */
	<V> List<V> hValsObject(final String key, final Class<V> clazz);

	/**
	 * 获取哈希表 key 中所有域的值，并将值反序列化为 clazz 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中所有域的值反序列化后的对象
	 */
	<V> List<V> hValsObject(final byte[] key, final Class<V> clazz);

	/**
	 * 获取哈希表 key 中所有域的值，并将值反序列化为 type 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中所有域的值反序列化后的对象
	 */
	<V> List<V> hValsObject(final String key, final TypeReference<V> type);

	/**
	 * 获取哈希表 key 中所有域的值，并将值反序列化为 type 指定的对象
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 哈希表 key 中所有域的值反序列化后的对象
	 */
	<V> List<V> hValsObject(final byte[] key, final TypeReference<V> type);

}
