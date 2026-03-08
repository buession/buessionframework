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

import com.buession.redis.core.Keyword;
import com.buession.redis.utils.ObjectStringBuilder;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
abstract class BaseSetExArgument {

	/**
	 * 过期时间类型
	 */
	private SetExType type;

	/**
	 * 过期时间戳
	 */
	private Long value;

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
	 * @param value
	 * 		过期时间戳
	 */
	public BaseSetExArgument(final SetExType type, final long value) {
		this.type = type;
		this.value = value;
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
	public BaseSetExArgument setType(SetExType type) {
		this.type = type;
		return this;
	}

	/**
	 * 获取设置的键过期时间戳
	 *
	 * @return 设置的键过期时间戳
	 */
	public Long getValue() {
		return value;
	}

	/**
	 * 设置过期时间戳
	 *
	 * @param value
	 * 		过期时间戳
	 *
	 * @return {@link BaseSetExArgument}
	 */
	public BaseSetExArgument setValue(long value) {
		this.value = value;
		return this;
	}

	@Override
	public String toString() {
		return ObjectStringBuilder.create().add(type.name(), type == SetExType.KEEPTTL ? null : value)
				.build();
	}

	/**
	 * 过期时间类型
	 */
	public enum SetExType implements Keyword {
		EX,

		EXAT,

		PX,

		PXAT,

		KEEPTTL;

		@Override
		public String getValue() {
			return name();
		}

		@Override
		public String toString() {
			return getValue();
		}

	}

}
