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
package com.buession.redis.core.operations;

import com.buession.core.utils.Assert;
import com.buession.lang.Status;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.utils.ReturnUtils;

import java.util.Date;

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
	 * 删除给定的 key
	 *
	 * @param key
	 * 		key
	 *
	 * @return 当有 key 被删除时返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status del(final String key){
		return ReturnUtils.statusForBool(del(new String[]{key}) > 0);
	}

	/**
	 * 删除给定的 key
	 *
	 * @param key
	 * 		key
	 *
	 * @return 当有 key 被删除时返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status del(final byte[] key){
		return ReturnUtils.statusForBool(del(new byte[][]{key}) > 0);
	}

	/**
	 * 删除给定的 key
	 *
	 * @param key
	 * 		key
	 *
	 * @return 当有 key 被删除时返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status delete(final String key){
		return del(key);
	}

	/**
	 * 删除给定的 key
	 *
	 * @param key
	 * 		key
	 *
	 * @return 当有 key 被删除时返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status delete(final byte[] key){
		return del(key);
	}

	/**
	 * 删除给定的一个或多个 key
	 *
	 * @param keys
	 * 		一个或多个 key
	 *
	 * @return 被删除 key 的数量
	 */
	default Long delete(final String... keys){
		return del(keys);
	}

	/**
	 * 删除给定的一个或多个 key
	 *
	 * @param keys
	 * 		一个或多个 key
	 *
	 * @return 被删除 key 的数量
	 */
	default Long delete(final byte[]... keys){
		return del(keys);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final String key, final Date date){
		Assert.isNull(date, "Expire date could not be null");
		return expireAt(key, date.getTime() / 1000L);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status expireAt(final byte[] key, final Date date){
		Assert.isNull(date, "Expire date could not be null");
		return expireAt(key, date.getTime() / 1000L);
	}

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * @param key
	 * 		Key
	 * @param host
	 * 		目标 Redis Server 主机地址
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 *
	 * @return 操作结果
	 */
	default Status migrate(final String key, final String host, final int db, final int timeout){
		return migrate(key, host, RedisNode.DEFAULT_PORT, db, timeout);
	}

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * @param key
	 * 		Key
	 * @param host
	 * 		目标 Redis Server 主机地址
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 *
	 * @return 操作结果
	 */
	default Status migrate(final byte[] key, final String host, final int db, final int timeout){
		return migrate(key, host, RedisNode.DEFAULT_PORT, db, timeout);
	}

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * @param key
	 * 		Key
	 * @param host
	 * 		目标 Redis Server 主机地址
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 * @param operation
	 *        {@link MigrateOperation}
	 *
	 * @return 操作结果
	 */
	default Status migrate(final String key, final String host, final int db, final int timeout,
			final MigrateOperation operation){
		return migrate(key, host, RedisNode.DEFAULT_PORT, db, timeout, operation);
	}

	/**
	 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，
	 * 一旦传送成功，key 保证会出现在目标实例上，而当前实例上的 key 会被删除
	 *
	 * @param key
	 * 		Key
	 * @param host
	 * 		目标 Redis Server 主机地址
	 * @param db
	 * 		目标 Redis DB
	 * @param timeout
	 * 		当前实例和目标实例进行沟通的最大间隔时间，只是说数据传送的时间不能超过这个值（单位：毫秒）
	 * @param operation
	 *        {@link MigrateOperation}
	 *
	 * @return 操作结果
	 */
	default Status migrate(final byte[] key, final String host, final int db, final int timeout,
			final MigrateOperation operation){
		return migrate(key, host, RedisNode.DEFAULT_PORT, db, timeout, operation);
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final String key, final Date date){
		Assert.isNull(date, "Expire date could not be null");
		return pExpireAt(key, date.getTime());
	}

	/**
	 * 获取给定 key 的过期时间
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当 key 不存在时，或没有设置剩余生存时间时，返回 null；否则返回过期时间
	 */
	default Date pTtlAt(final String key){
		return new Date(System.currentTimeMillis() + pTtl(key));
	}

	/**
	 * 获取给定 key 的过期时间
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当 key 不存在时，或没有设置剩余生存时间时，返回 null；否则返回过期时间
	 */
	default Date pTtlAt(final byte[] key){
		return new Date(System.currentTimeMillis() + pTtl(key));
	}

	/**
	 * 为给定 key 设置过期时间
	 *
	 * @param key
	 * 		Key
	 * @param date
	 * 		过期时间
	 *
	 * @return 操作结果
	 */
	default Status pExpireAt(final byte[] key, final Date date){
		Assert.isNull(date, "Expire date could not be null");
		return pExpireAt(key, date.getTime());
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
	default Status restore(final String key, final String serializedValue, final Date ttl){
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
	default Status restore(final byte[] key, final byte[] serializedValue, final Date ttl){
		Assert.isNull(ttl, "Ttl date could not be null");
		return restore(key, serializedValue, (int) (ttl.getTime() - System.currentTimeMillis()));
	}

	/**
	 * 修改指定一个或多个 key 最后访问时间
	 *
	 * @param key
	 * 		key
	 *
	 * @return 操作成功时返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status touch(final String key){
		return ReturnUtils.statusForBool(touch(new String[]{key}) > 0);
	}

	/**
	 * 修改指定一个或多个 key 最后访问时间
	 *
	 * @param key
	 * 		key
	 *
	 * @return 操作成功时返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status touch(final byte[] key){
		return ReturnUtils.statusForBool(touch(new byte[][]{key}) > 0);
	}

	/**
	 * 获取给定 key 的过期时间
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当 key 不存在时，或没有设置剩余生存时间时，返回 null；否则返回过期时间
	 */
	default Date ttlAt(final String key){
		return new Date(System.currentTimeMillis() + ttl(key) * 1000L);
	}

	/**
	 * 获取给定 key 的过期时间
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 当 key 不存在时，或没有设置剩余生存时间时，返回 null；否则返回过期时间
	 */
	default Date ttlAt(final byte[] key){
		return new Date(System.currentTimeMillis() + ttl(key) * 1000L);
	}

	/**
	 * 删除给定的 key，该命令会在另一个线程中回收内存，因此它是非阻塞的。
	 * 仅将 keys 从 keyspace 元数据中删除，真正的删除会在后续异步操作。
	 *
	 * @param key
	 * 		key
	 *
	 * @return 当有 key 被删除时返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status unlink(final String key){
		return ReturnUtils.statusForBool(unlink(new String[]{key}) > 0);
	}

	/**
	 * 删除给定的 key，该命令会在另一个线程中回收内存，因此它是非阻塞的。
	 * 仅将 keys 从 keyspace 元数据中删除，真正的删除会在后续异步操作。
	 *
	 * @param key
	 * 		key
	 *
	 * @return 当有 key 被删除时返回 Status.SUCCESS；否则返回 Status.FAILURE
	 */
	default Status unlink(final byte[] key){
		return ReturnUtils.statusForBool(unlink(new byte[][]{key}) > 0);
	}

}
