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

import com.buession.redis.core.command.args.NxXx;
import com.buession.redis.core.command.args.string.SetArgument;
import io.lettuce.core.SetArgs;

import java.time.Duration;
import java.time.Instant;

/**
 * Lettuce {@link SetArgs} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceSetArgs extends SetArgs {

	/**
	 * 构造函数
	 */
	public LettuceSetArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param setArgument
	 *        {@link SetArgument}
	 */
	public LettuceSetArgs(final SetArgument setArgument) {
		super();

		if(setArgument != null){
			if(setArgument.getType() == null && setArgument.getExpires() != null){
				switch(setArgument.getType()){
					case EX -> ex(Duration.ofSeconds(setArgument.getExpires()));
					case EXAT -> exAt(Instant.ofEpochSecond(setArgument.getExpires()));
					case PX -> px(Duration.ofMillis(setArgument.getExpires()));
					case PXAT -> pxAt(Instant.ofEpochMilli(setArgument.getExpires()));
					case KEEPTTL -> keepttl();
				}
			}

			if(setArgument.getNxXx() == NxXx.NX){
				nx();
			}else if(setArgument.getNxXx() == NxXx.XX){
				xx();
			}
		}
	}

}
