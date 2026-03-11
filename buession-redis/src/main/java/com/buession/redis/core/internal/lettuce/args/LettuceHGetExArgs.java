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
package com.buession.redis.core.internal.lettuce.args;

import com.buession.redis.core.command.args.GetExArgument;
import io.lettuce.core.HGetExArgs;

import java.time.Duration;
import java.time.Instant;

/**
 * Lettuce {@link HGetExArgs} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceHGetExArgs extends HGetExArgs {

	/**
	 * 构造函数
	 */
	public LettuceHGetExArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param getExArgument
	 *        {@link GetExArgument}
	 */
	public LettuceHGetExArgs(final GetExArgument getExArgument) {
		super();

		if(getExArgument != null){
			if(getExArgument.getType() != null && getExArgument.getExpires() != null){
				switch(getExArgument.getType()){
					case EX -> ex(Duration.ofSeconds(getExArgument.getExpires()));
					case EXAT -> exAt(Instant.ofEpochSecond(getExArgument.getExpires()));
					case PX -> px(Duration.ofMillis(getExArgument.getExpires()));
					case PXAT -> pxAt(Instant.ofEpochMilli(getExArgument.getExpires()));
					case PERSIST -> persist();
				}
			}
		}
	}

}
