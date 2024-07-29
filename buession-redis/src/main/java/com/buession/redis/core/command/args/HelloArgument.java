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

/**
 * {@code HELLO} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class HelloArgument {

	/**
	 * 协议版本
	 */
	private Integer protoVer;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 客户端名称
	 */
	private String clientName;

	/**
	 * 构造函数
	 */
	public HelloArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param protoVer
	 * 		协议版本
	 */
	public HelloArgument(final Integer protoVer) {
		this.protoVer = protoVer;
	}

	/**
	 * 构造函数
	 *
	 * @param password
	 * 		密码
	 */
	public HelloArgument(final String password) {
		this.password = password;
	}

	/**
	 * 构造函数
	 *
	 * @param username
	 * 		用户名
	 * @param password
	 * 		密码
	 */
	public HelloArgument(final String username, final String password) {
		this(password);
		this.username = username;
	}

	/**
	 * 构造函数
	 *
	 * @param protoVer
	 * 		协议版本
	 * @param password
	 * 		密码
	 */
	public HelloArgument(final Integer protoVer, final String password) {
		this(protoVer);
		this.password = password;
	}

	/**
	 * 构造函数
	 *
	 * @param protoVer
	 * 		协议版本
	 * @param username
	 * 		用户名
	 * @param password
	 * 		密码
	 */
	public HelloArgument(final Integer protoVer, final String username, final String password) {
		this(protoVer);
		setPassword(username, password);
	}

	/**
	 * 构造函数
	 *
	 * @param protoVer
	 * 		协议版本
	 * @param username
	 * 		用户名
	 * @param password
	 * 		密码
	 * @param clientName
	 * 		客户端名称
	 */
	public HelloArgument(final Integer protoVer, final String username, final String password,
						 final String clientName) {
		this(protoVer, password, username);
		this.clientName = clientName;
	}

	/**
	 * 返回协议版本
	 *
	 * @return 协议版本
	 */
	public Integer getProtoVer() {
		return protoVer;
	}

	/**
	 * 设置协议版本
	 *
	 * @param protoVer
	 * 		协议版本
	 */
	public void setProtoVer(final Integer protoVer) {
		this.protoVer = protoVer;
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
	 * 返回密码
	 *
	 * @return 密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码
	 *
	 * @param password
	 * 		密码
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * 设置密码
	 *
	 * @param username
	 * 		用户名
	 * @param password
	 * 		密码
	 */
	public void setPassword(final String username, String password) {
		this.password = password;
		if(Validate.hasText(password)){
			this.username = username;
		}
	}

	/**
	 * 返回客户端名称
	 *
	 * @return 客户端名称
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * 设置
	 *
	 * @param clientName
	 * 		客户端名称
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@Override
	public String toString() {
		final StringJoiner joiner = new StringJoiner(" ");

		if(protoVer != null){
			joiner.add(protoVer);
		}

		if(Validate.hasText(password)){
			joiner.add("AUTH");
			if(Validate.hasText(username)){
				joiner.add(username);
			}
			joiner.add(password);
		}

		if(Validate.hasText(clientName)){
			joiner.add("SETNAME").add(clientName);
		}

		return joiner.toString();
	}

}
