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

import com.buession.core.validator.Validate;
import com.buession.redis.core.Keyword;

/**
 * {@code MIGRATE} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class MigrateArgument {

	/**
	 * 迁移模式
	 */
	private Mode mode;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 返回迁移模式
	 *
	 * @return 迁移模式
	 */
	public Mode getMode() {
		return mode;
	}

	/**
	 * 设置迁移模式
	 *
	 * @param mode
	 * 		迁移模式
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
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
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

		if(mode != null){
			builder.append(mode);
		}

		if(Validate.hasText(password)){
			if(Validate.hasText(username)){
				builder.add(Keyword.Common.AUTH2, username);
			}else{
				builder.append(Keyword.Common.AUTH);
			}
			builder.append(password);
		}

		return builder.build();
	}

	/**
	 * 迁移模式
	 */
	public enum Mode {
		/**
		 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，不移除源实例上的 key
		 */
		COPY,

		/**
		 * 将 key 原子性地从当前实例传送到目标实例的指定数据库上，替换目标实例上已存在的 key
		 */
		REPLACE
	}

}
