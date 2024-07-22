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

import com.buession.redis.core.command.args.GetExArgument;
import io.lettuce.core.GetExArgs;

/**
 * Lettuce {@link GetExArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceGetExArgs extends GetExArgs {

	/**
	 * 构造函数
	 */
	public LettuceGetExArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param type
	 * 		键过期设置方式
	 * @param value
	 * 		键过期时间
	 */
	public LettuceGetExArgs(final GetExArgument.GetExType type, final Long value) {
		super();

		if(type != null && value != null){
			switch(type){
				case EX:
					ex(value);
					break;
				case EXAT:
					exAt(value);
					break;
				case PX:
					px(value);
					break;
				case PXAT:
					pxAt(value);
					break;
				case PERSIST:
					persist();
					break;
				default:
					break;
			}
		}
	}

	/**
	 * 构造函数
	 *
	 * @param persist
	 * 		设置键是否持久化
	 */
	public LettuceGetExArgs(final boolean persist) {
		super();
		if(Boolean.TRUE.equals(persist)){
			persist();
		}
	}

	/**
	 * 从 {@link GetExArgument} 创建 {@link GetExArgs} 实例
	 *
	 * @param getExArgument
	 *        {@link GetExArgument}
	 *
	 * @return {@link LettuceGetExArgs} 实例
	 */
	public static LettuceGetExArgs from(final GetExArgument getExArgument) {
		return getExArgument != null && getExArgument.getType() != null && getExArgument.getExpires() != null ?
				new LettuceGetExArgs(getExArgument.getType(), getExArgument.getExpires()) :
				new LettuceGetExArgs();
	}

}
