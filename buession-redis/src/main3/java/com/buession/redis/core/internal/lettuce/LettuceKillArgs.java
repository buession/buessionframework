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
package com.buession.redis.core.internal.lettuce;

import com.buession.core.utils.FieldUtils;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.command.args.ClientKillArgument;
import io.lettuce.core.KillArgs;

import java.util.Optional;

/**
 * Lettuce {@link KillArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceKillArgs extends KillArgs {

	/**
	 * 构造函数
	 */
	public LettuceKillArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 */
	public LettuceKillArgs(final long clientId) {
		super();
		id(clientId);
	}

	/**
	 * 构造函数
	 *
	 * @param clientType
	 * 		客户端类型
	 */
	public LettuceKillArgs(final ClientType clientType) {
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
	public LettuceKillArgs(final long clientId, final boolean skipMe, final long maxAge) {
		this(clientId);
		skipme(skipMe);
		maxAge(maxAge);
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
	public LettuceKillArgs(final ClientType clientType, final boolean skipMe, final long maxAge) {
		this(clientType);
		skipme(skipMe);
		maxAge(maxAge);
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
	public LettuceKillArgs(final long clientId, final String addr, final String laddr, final boolean skipMe,
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
	public LettuceKillArgs(final ClientType clientType, final String addr, final String laddr,
						   final boolean skipMe, final long maxAge) {
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
	public LettuceKillArgs(final long clientId, final String username, final String addr, final String laddr,
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
	public LettuceKillArgs(final ClientType clientType, final String username, final String addr,
						   final String laddr, final boolean skipMe, final long maxAge) {
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
	public LettuceKillArgs(final long clientId, final ClientType clientType, final String username,
						   final String addr, final String laddr, final boolean skipMe, final long maxAge) {
		this(clientType, username, addr, laddr, skipMe, maxAge);
		id(clientId);
	}

	public LettuceKillArgs type(final ClientType clientType) {
		if(clientType != null){
			KillArgs killArgs = null;
			switch(clientType){
				case NORMAL:
					killArgs = Builder.typeNormal();
					break;
				case MASTER:
					killArgs = Builder.typeMaster();
					break;
				case SLAVE:
					killArgs = Builder.typeSlave();
					break;
				case REPLICA:
					break;
				case PUBSUB:
					killArgs = Builder.typePubsub();
					break;
				default:
					break;
			}

			if(killArgs != null){
				try{
					Object type = FieldUtils.readField(killArgs, "type");
					if(type != null){
						FieldUtils.writeField(this, "type", type);
					}
				}catch(IllegalAccessException e){
					throw new RuntimeException(e);
				}
			}
		}

		return this;
	}

	/**
	 * 从 {@link ClientKillArgument} 创建 {@link KillArgs} 实例
	 *
	 * @param clientKillArgument
	 *        {@link ClientKillArgument}
	 *
	 * @return {@link LettuceKillArgs} 实例
	 */
	public static LettuceKillArgs from(final ClientKillArgument clientKillArgument) {
		final LettuceKillArgs killArgs = new LettuceKillArgs();

		Optional.ofNullable(clientKillArgument.getClientId()).ifPresent(killArgs::id);
		Optional.ofNullable(clientKillArgument.getClientType()).ifPresent(killArgs::type);
		Optional.ofNullable(clientKillArgument.getUsername()).ifPresent(killArgs::user);
		Optional.ofNullable(clientKillArgument.getAddr()).ifPresent(killArgs::addr);
		Optional.ofNullable(clientKillArgument.getLaddr()).ifPresent(killArgs::laddr);
		Optional.ofNullable(clientKillArgument.getSkipMe()).ifPresent(killArgs::skipme);
		Optional.ofNullable(clientKillArgument.getMaxAge()).ifPresent(killArgs::maxAge);

		return killArgs;
	}

}
