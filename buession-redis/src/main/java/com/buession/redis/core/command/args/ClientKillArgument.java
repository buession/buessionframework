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
package com.buession.redis.core.command.args;

import com.buession.core.utils.StringJoiner;
import com.buession.core.validator.Validate;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.Keyword;

/**
 * {@code CLIENT KILL} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class ClientKillArgument {

	/**
	 * 客户端 ID
	 */
	private Long clientId;

	/**
	 * 客户端类型
	 */
	private ClientType clientType;

	/**
	 * 用户名
	 */
	private String username;

	private String addr;

	private String laddr;

	/**
	 * 是否跳过自身
	 */
	private Boolean skipMe;

	/**
	 * Closes all the connections that are older than the specified age, in seconds.
	 */
	private Long maxAge;

	/**
	 * 构造函数
	 */
	public ClientKillArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param clientId
	 * 		客户端 ID
	 */
	public ClientKillArgument(final Long clientId) {
		this.clientId = clientId;
	}

	/**
	 * 构造函数
	 *
	 * @param clientType
	 * 		客户端类型
	 */
	public ClientKillArgument(final ClientType clientType) {
		this.clientType = clientType;
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
	public ClientKillArgument(final Long clientId, final Boolean skipMe, final Long maxAge) {
		this(clientId);
		this.skipMe = skipMe;
		this.maxAge = maxAge;
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
	public ClientKillArgument(final ClientType clientType, final Boolean skipMe, final Long maxAge) {
		this(clientType);
		this.skipMe = skipMe;
		this.maxAge = maxAge;
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
	public ClientKillArgument(final Long clientId, final String addr, final String laddr, final Boolean skipMe,
							  final Long maxAge) {
		this(clientId, skipMe, maxAge);
		this.addr = addr;
		this.laddr = laddr;
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
	public ClientKillArgument(final ClientType clientType, final String addr, final String laddr, final Boolean skipMe,
							  final Long maxAge) {
		this(clientType, skipMe, maxAge);
		this.addr = addr;
		this.laddr = laddr;
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
	public ClientKillArgument(final Long clientId, final String username, final String addr, final String laddr,
							  final Boolean skipMe, final Long maxAge) {
		this(clientId, addr, laddr, skipMe, maxAge);
		this.username = username;
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
	public ClientKillArgument(final ClientType clientType, final String username, final String addr, final String laddr,
							  final Boolean skipMe, final Long maxAge) {
		this(clientType, addr, laddr, skipMe, maxAge);
		this.username = username;
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
	public ClientKillArgument(final Long clientId, final ClientType clientType, final String username,
							  final String addr, final String laddr, final Boolean skipMe, final Long maxAge) {
		this(clientId, username, addr, laddr, skipMe, maxAge);
		this.clientType = clientType;
	}

	/**
	 * 返回客户端 ID
	 *
	 * @return 客户端 ID
	 */
	public Long getClientId() {
		return clientId;
	}

	/**
	 * 设置客户端 ID
	 *
	 * @param clientId
	 * 		客户端 ID
	 */
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	/**
	 * 返回客户端类型
	 *
	 * @return 客户端类型
	 */
	public ClientType getClientType() {
		return clientType;
	}

	/**
	 * 设置客户端类型
	 *
	 * @param clientType
	 * 		客户端类型
	 */
	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	/**
	 * 返回用户名
	 *
	 * @return 用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置用户名
	 *
	 * @param username
	 * 		用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getLaddr() {
		return laddr;
	}

	public void setLaddr(String laddr) {
		this.laddr = laddr;
	}

	/**
	 * 返回是否跳过自身
	 *
	 * @return 是否跳过自身
	 */
	public Boolean isSkipMe() {
		return getSkipMe();
	}

	/**
	 * 返回是否跳过自身
	 *
	 * @return 是否跳过自身
	 */
	public Boolean getSkipMe() {
		return skipMe;
	}

	/**
	 * 设置跳过自身
	 */
	public void skipMe() {
		this.skipMe = true;
	}

	/**
	 * 设置是否跳过自身
	 *
	 * @param skipMe
	 * 		是否跳过自身
	 */
	public void setSkipMe(Boolean skipMe) {
		this.skipMe = skipMe;
	}

	/**
	 * Return closes all the connections that are older than the specified age, in seconds.
	 *
	 * @return Closes all the connections that are older than the specified age.
	 */
	public Long getMaxAge() {
		return maxAge;
	}

	/**
	 * Sets closes all the connections that are older than the specified age, in seconds.
	 *
	 * @param maxAge
	 * 		Closes all the connections that are older than the specified age, in seconds.
	 */
	public void setMaxAge(Long maxAge) {
		this.maxAge = maxAge;
	}

	@Override
	public String toString() {
		final StringJoiner joiner = new StringJoiner(" ");

		if(clientId != null){
			joiner.add(Keyword.Common.ID).add(clientId);
		}
		if(clientType != null){
			joiner.add(Keyword.Common.TYPE).add(clientType);
		}
		if(Validate.hasText(username)){
			joiner.add(Keyword.Common.USER).add(username);
		}
		if(Validate.hasText(addr)){
			joiner.add(Keyword.Common.ADDR).add(addr);
		}
		if(Validate.hasText(laddr)){
			joiner.add(Keyword.Common.LADDR).add(addr);
		}
		if(skipMe != null){
			joiner.add(Keyword.Common.SKIPME).add(skipMe ? Keyword.Common.YES : Keyword.Common.NO);
		}
		if(maxAge != null){
			joiner.add(Keyword.Common.MAXAGE).add(maxAge);
		}

		return joiner.toString();
	}

}
