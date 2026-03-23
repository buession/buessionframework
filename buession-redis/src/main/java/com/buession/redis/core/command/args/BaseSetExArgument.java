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
package com.buession.redis.core.command.args;

import com.buession.redis.utils.ArgStringBuilder;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class BaseSetExArgument<T> implements ExArgument<T> {

	/**
	 * 过期时间类型
	 */
	private SetExType type;

	/**
	 * 过期时间(戳)
	 */
	private Long expires;

	/**
	 * 构造函数
	 */
	public BaseSetExArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		过期时间类型
	 * @param expires
	 * 		过期时间(戳)
	 */
	public BaseSetExArgument(final SetExType type, final long expires) {
		this.type = type;
		this.expires = expires;
	}

	/**
	 * 获取过期时间类型
	 *
	 * @return 过期时间类型
	 */
	public SetExType getType() {
		return type;
	}

	/**
	 * 设置过期时间类型
	 *
	 * @param type
	 * 		过期时间类型
	 *
	 * @return {@link BaseSetExArgument}
	 */
	public BaseSetExArgument<T> setType(SetExType type) {
		this.type = type;
		return this;
	}

	/**
	 * 获取设置的键过期时间(戳)
	 *
	 * @return 设置的键过期时间(戳)
	 */
	public Long getExpires() {
		return expires;
	}

	/**
	 * 设置过期时间戳
	 *
	 * @param expires
	 * 		过期时间(戳)
	 *
	 * @return {@link BaseSetExArgument}
	 */
	public BaseSetExArgument<T> setExpires(long expires) {
		this.expires = expires;
		return this;
	}

	@Override
	public String toString() {
		final ArgStringBuilder builder = ArgStringBuilder.create();

		if(getType() == SetExType.KEEPTTL){
			builder.append(getType());
		}else{
			builder.add(getType().name(), getExpires());
		}

		return builder.build();
	}

}
