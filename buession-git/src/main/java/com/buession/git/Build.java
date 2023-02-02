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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.git;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Build 信息
 *
 * @author Yong.Teng
 * @since 2.2.0
 */
public final class Build implements Info {

	/**
	 * Build 主机
	 */
	private String host;

	/**
	 * Build 时间
	 */
	private ZonedDateTime time;

	/**
	 * Build 用户
	 */
	private User user;

	/**
	 * Build 版本
	 */
	private String version;

	/**
	 * 构造函数
	 */
	public Build(){
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		Build 主机
	 * @param time
	 * 		Build 时间
	 * @param user
	 * 		Build 用户
	 * @param version
	 * 		uild 版本
	 */
	public Build(String host, ZonedDateTime time, User user, String version){
		this.host = host;
		this.time = time;
		this.user = user;
		this.version = version;
	}

	/**
	 * 返回 Build 主机
	 *
	 * @return Build 主机
	 */
	public String getHost(){
		return host;
	}

	/**
	 * 设置 Build 主机
	 *
	 * @param host
	 * 		Build 主机
	 */
	public void setHost(String host){
		this.host = host;
	}

	/**
	 * 返回 Build 时间
	 *
	 * @return Build 时间
	 */
	public ZonedDateTime getTime(){
		return time;
	}

	/**
	 * 设置 Build 时间
	 *
	 * @param time
	 * 		Build 时间
	 */
	public void setTime(ZonedDateTime time){
		this.time = time;
	}

	/**
	 * 返回 Build 用户
	 *
	 * @return Build 用户
	 */
	public User getUser(){
		return user;
	}

	/**
	 * 设置 Build 用户
	 *
	 * @param user
	 * 		Build 用户
	 */
	public void setUser(User user){
		this.user = user;
	}

	/**
	 * 返回 Build 版本
	 *
	 * @return Build 版本
	 */
	public String getVersion(){
		return version;
	}

	/**
	 * 设置 Build 版本
	 *
	 * @param version
	 * 		Build 版本
	 */
	public void setVersion(String version){
		this.version = version;
	}

	@Override
	public String toString(){
		final GitInfoBuilder builder = GitInfoBuilder.getInstance("build")
				.append("host", host)
				.append("time", time)
				.append("user", Optional.ofNullable(user).orElse(new User()))
				.append("version", version);

		return builder.build();
	}

	public final static class User extends BaseUser {

		/**
		 * 构造函数
		 */
		public User(){
			super();
		}

		/**
		 * 构造函数
		 *
		 * @param name
		 * 		用户名称
		 * @param email
		 * 		用户 E-mail
		 */
		public User(String name, String email){
			super(name, email);
		}

		@Override
		public String toString(){
			final GitInfoBuilder builder = GitInfoBuilder.getInstance("build.user")
					.append("name", getName())
					.append("email", getEmail());

			return builder.build();
		}

	}

}
