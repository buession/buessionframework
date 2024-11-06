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

/**
 * GETEX 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class GetExArgument {

	/**
	 * 过期时间方式
	 */
	private GetExType type;

	/**
	 * 过期时间
	 */
	private Long expires;

	/**
	 * 构造函数
	 */
	public GetExArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		过期时间方式
	 * @param expires
	 * 		过期时间
	 */
	public GetExArgument(final GetExType type, final Long expires) {
		this.type = type;
		this.expires = expires;
	}

	/**
	 * 返回过期时间方式
	 *
	 * @return 过期时间方式
	 */
	public GetExType getType() {
		return type;
	}

	/**
	 * 设置过期时间方式
	 *
	 * @param type
	 * 		过期时间方式
	 */
	public void seType(final GetExType type) {
		this.type = type;
	}

	/**
	 * 返回过期时间
	 *
	 * @return 过期时间
	 */
	public Long getExpires() {
		return expires;
	}

	/**
	 * 设置过期时间
	 *
	 * @param expires
	 * 		过期时间
	 */
	public void setExpires(final Long expires) {
		this.expires = expires;
	}

	@Override
	public String toString() {
		final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

		if(type != null){
			if(type == GetExType.PERSIST){
				builder.append("PERSIST");
			}else{
				builder.add(type.name(), expires);
			}
		}

		return builder.build();
	}

	/**
	 * 过期时间方式
	 */
	public enum GetExType {
		/**
		 * 设置指定的过期时间，以秒为单位
		 */
		EX,

		/**
		 * 设置指定的过期 Unix 时间戳，以秒为单位
		 */
		EXAT,

		/**
		 * 设置指定的过期时间，以毫秒为单位
		 */
		PX,

		/**
		 * 设置指定的过期 Unix 时间戳，以毫秒为单位
		 */
		PXAT,

		/**
		 * 设置的键永不过期
		 */
		PERSIST
	}

}
