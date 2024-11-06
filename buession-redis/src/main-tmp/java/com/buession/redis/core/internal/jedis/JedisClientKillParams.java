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
package com.buession.redis.core.internal.jedis;

import com.buession.redis.core.ClientType;
import com.buession.redis.core.command.args.ClientKillArgument;
import com.buession.redis.core.internal.convert.jedis.params.ClientTypeConverter;
import redis.clients.jedis.params.ClientKillParams;

import java.util.Optional;

/**
 * Jedis {@link ClientKillParams} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisClientKillParams extends ClientKillParams {

	/**
	 * 构造函数
	 */
	public JedisClientKillParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 */
	public JedisClientKillParams(final long clientId) {
		super();
		id(clientId);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 */
	public JedisClientKillParams(final String clientId) {
		super();
		id(clientId);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 */
	public JedisClientKillParams(final byte[] clientId) {
		super();
		id(clientId);
	}

	/**
	 * 构造函数
	 *
	 * @param clientType
	 * 		客户端类型
	 */
	public JedisClientKillParams(final ClientType clientType) {
		super();
		type(clientType);
	}

	/**
	 * 构造函数
	 *
	 * @param clientType
	 * 		客户端类型
	 */
	public JedisClientKillParams(final redis.clients.jedis.args.ClientType clientType) {
		super();
		type(clientType);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final long clientId, final boolean skipMe, final long maxAge) {
		this(clientId);
		skipMe(skipMe ? SkipMe.YES : SkipMe.NO);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final String clientId, final boolean skipMe, final long maxAge) {
		this(clientId);
		skipMe(skipMe ? SkipMe.YES : SkipMe.NO);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final byte[] clientId, final boolean skipMe, final long maxAge) {
		this(clientId);
		skipMe(skipMe ? SkipMe.YES : SkipMe.NO);
	}

	/**
	 * 构造函数
	 *
	 * @param clientType
	 * 		客户端类型
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final ClientType clientType, final boolean skipMe, final long maxAge) {
		this(clientType);
		skipMe(skipMe ? SkipMe.YES : SkipMe.NO);
	}

	/**
	 * 构造函数
	 *
	 * @param clientType
	 * 		客户端类型
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final redis.clients.jedis.args.ClientType clientType, final boolean skipMe,
								 final long maxAge) {
		this(clientType);
		skipMe(skipMe ? SkipMe.YES : SkipMe.NO);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final long clientId, final SkipMe skipMe, final long maxAge) {
		this(clientId);
		skipMe(skipMe);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final String clientId, final SkipMe skipMe, final long maxAge) {
		this(clientId);
		skipMe(skipMe);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final byte[] clientId, final SkipMe skipMe, final long maxAge) {
		this(clientId);
		skipMe(skipMe);
	}

	/**
	 * 构造函数
	 *
	 * @param clientType
	 * 		客户端类型
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final ClientType clientType, final SkipMe skipMe, final long maxAge) {
		this(clientType);
		skipMe(skipMe);
	}

	/**
	 * 构造函数
	 *
	 * @param clientType
	 * 		客户端类型
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final redis.clients.jedis.args.ClientType clientType, final SkipMe skipMe,
								 final long maxAge) {
		this(clientType);
		skipMe(skipMe);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final long clientId, final String addr, final String laddr, final boolean skipMe,
								 final long maxAge) {
		this(clientId, skipMe, maxAge);
		addr(addr);
		laddr(laddr);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final String clientId, final String addr, final String laddr, final boolean skipMe,
								 final long maxAge) {
		this(clientId, skipMe, maxAge);
		addr(addr);
		laddr(laddr);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final byte[] clientId, final String addr, final String laddr, final boolean skipMe,
								 final long maxAge) {
		this(clientId, skipMe, maxAge);
		addr(addr);
		laddr(laddr);
	}

	/**
	 * 构造函数
	 *
	 * @param clientType
	 * 		客户端类型
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final ClientType clientType, final String addr, final String laddr,
								 final boolean skipMe, final long maxAge) {
		this(clientType, skipMe, maxAge);
		addr(addr);
		laddr(laddr);
	}

	/**
	 * 构造函数
	 *
	 * @param clientType
	 * 		客户端类型
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final redis.clients.jedis.args.ClientType clientType, final String addr,
								 final String laddr, final boolean skipMe, final long maxAge) {
		this(clientType, skipMe, maxAge);
		addr(addr);
		laddr(laddr);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final long clientId, final String addr, final String laddr, final SkipMe skipMe,
								 final long maxAge) {
		this(clientId, skipMe, maxAge);
		addr(addr);
		laddr(laddr);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final String clientId, final String addr, final String laddr, final SkipMe skipMe,
								 final long maxAge) {
		this(clientId, skipMe, maxAge);
		addr(addr);
		laddr(laddr);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final byte[] clientId, final String addr, final String laddr, final SkipMe skipMe,
								 final long maxAge) {
		this(clientId, skipMe, maxAge);
		addr(addr);
		laddr(laddr);
	}

	/**
	 * 构造函数
	 *
	 * @param clientType
	 * 		客户端类型
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final ClientType clientType, final String addr, final String laddr,
								 final SkipMe skipMe, final long maxAge) {
		this(clientType, skipMe, maxAge);
		addr(addr);
		laddr(laddr);
	}

	/**
	 * 构造函数
	 *
	 * @param clientType
	 * 		客户端类型
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final redis.clients.jedis.args.ClientType clientType, final String addr,
								 final String laddr, final SkipMe skipMe, final long maxAge) {
		this(clientType, skipMe, maxAge);
		addr(addr);
		laddr(laddr);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param username
	 * 		用户名
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final long clientId, final String username, final String addr, final String laddr,
								 final boolean skipMe, final long maxAge) {
		this(clientId, addr, laddr, skipMe, maxAge);
		user(username);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param username
	 * 		用户名
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final String clientId, final String username, final String addr, final String laddr,
								 final boolean skipMe, final long maxAge) {
		this(clientId, addr, laddr, skipMe, maxAge);
		user(username);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param username
	 * 		用户名
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final byte[] clientId, final String username, final String addr, final String laddr,
								 final boolean skipMe, final long maxAge) {
		this(clientId, addr, laddr, skipMe, maxAge);
		user(username);
	}

	/**
	 * 构造函数
	 *
	 * @param clientType
	 * 		客户端类型
	 * @param username
	 * 		用户名
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final ClientType clientType, final String username, final String addr,
								 final String laddr, final boolean skipMe, final long maxAge) {
		this(clientType, addr, laddr, skipMe, maxAge);
		user(username);
	}

	/**
	 * 构造函数
	 *
	 * @param clientType
	 * 		客户端类型
	 * @param username
	 * 		用户名
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final redis.clients.jedis.args.ClientType clientType, final String username,
								 final String addr, final String laddr, final boolean skipMe, final long maxAge) {
		this(clientType, addr, laddr, skipMe, maxAge);
		user(username);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param username
	 * 		用户名
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final long clientId, final String username, final String addr, final String laddr,
								 final SkipMe skipMe, final long maxAge) {
		this(clientId, addr, laddr, skipMe, maxAge);
		user(username);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param username
	 * 		用户名
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final String clientId, final String username, final String addr, final String laddr,
								 final SkipMe skipMe, final long maxAge) {
		this(clientId, addr, laddr, skipMe, maxAge);
		user(username);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param username
	 * 		用户名
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final byte[] clientId, final String username, final String addr, final String laddr,
								 final SkipMe skipMe, final long maxAge) {
		this(clientId, addr, laddr, skipMe, maxAge);
		user(username);
	}

	/**
	 * 构造函数
	 *
	 * @param clientType
	 * 		客户端类型
	 * @param username
	 * 		用户名
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final ClientType clientType, final String username, final String addr,
								 final String laddr, final SkipMe skipMe, final long maxAge) {
		this(clientType, addr, laddr, skipMe, maxAge);
		user(username);
	}

	/**
	 * 构造函数
	 *
	 * @param clientType
	 * 		客户端类型
	 * @param username
	 * 		用户名
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final redis.clients.jedis.args.ClientType clientType, final String username,
								 final String addr, final String laddr, final SkipMe skipMe, final long maxAge) {
		this(clientType, addr, laddr, skipMe, maxAge);
		user(username);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param clientType
	 * 		客户端类型
	 * @param username
	 * 		用户名
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final long clientId, final ClientType clientType, final String username,
								 final String addr, final String laddr, final boolean skipMe, final long maxAge) {
		this(clientType, username, addr, laddr, skipMe, maxAge);
		id(Long.toString(clientId));
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param clientType
	 * 		客户端类型
	 * @param username
	 * 		用户名
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final String clientId, final ClientType clientType, final String username,
								 final String addr, final String laddr, final boolean skipMe, final long maxAge) {
		this(clientType, username, addr, laddr, skipMe, maxAge);
		id(clientId);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param clientType
	 * 		客户端类型
	 * @param username
	 * 		用户名
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final byte[] clientId, final ClientType clientType, final String username,
								 final String addr, final String laddr, final boolean skipMe, final long maxAge) {
		this(clientType, username, addr, laddr, skipMe, maxAge);
		id(clientId);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param clientType
	 * 		客户端类型
	 * @param username
	 * 		用户名
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final long clientId, final redis.clients.jedis.args.ClientType clientType,
								 final String username, final String addr, final String laddr, final boolean skipMe,
								 final long maxAge) {
		this(clientType, username, addr, laddr, skipMe, maxAge);
		id(Long.toString(clientId));
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param clientType
	 * 		客户端类型
	 * @param username
	 * 		用户名
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final String clientId, final redis.clients.jedis.args.ClientType clientType,
								 final String username, final String addr, final String laddr, final boolean skipMe,
								 final long maxAge) {
		this(clientType, username, addr, laddr, skipMe, maxAge);
		id(clientId);
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param clientType
	 * 		客户端类型
	 * @param username
	 * 		用户名
	 * @param addr
	 * 		-
	 * @param laddr
	 * 		-
	 * @param skipMe
	 * 		是否跳过自身
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public JedisClientKillParams(final byte[] clientId, final redis.clients.jedis.args.ClientType clientType,
								 final String username, final String addr, final String laddr, final boolean skipMe,
								 final long maxAge) {
		this(clientType, username, addr, laddr, skipMe, maxAge);
		id(clientId);
	}

	public JedisClientKillParams id(final long clientId) {
		id(Long.toString(clientId));
		return this;
	}

	public JedisClientKillParams type(final ClientType clientType) {
		type((new ClientTypeConverter()).convert(clientType));
		return this;
	}

	public JedisClientKillParams skipMe(final boolean skipMe) {
		skipMe(skipMe ? SkipMe.YES : SkipMe.NO);
		return this;
	}

	/**
	 * 从 {@link ClientKillArgument} 创建 {@link ClientKillParams} 实例
	 *
	 * @param clientKillArgument
	 *        {@link ClientKillArgument}
	 *
	 * @return {@link JedisClientKillParams} 实例
	 */
	public static JedisClientKillParams from(final ClientKillArgument clientKillArgument) {
		final JedisClientKillParams clientKillParams = new JedisClientKillParams();

		Optional.ofNullable(clientKillArgument.getClientId()).ifPresent(clientKillParams::id);
		Optional.ofNullable(clientKillArgument.getClientType()).ifPresent(clientKillParams::type);
		Optional.ofNullable(clientKillArgument.getUsername()).ifPresent(clientKillParams::user);
		Optional.ofNullable(clientKillArgument.getAddr()).ifPresent(clientKillParams::addr);
		Optional.ofNullable(clientKillArgument.getLaddr()).ifPresent(clientKillParams::laddr);
		Optional.ofNullable(clientKillArgument.getSkipMe()).ifPresent(clientKillParams::skipMe);
		
		return clientKillParams;
	}

}
