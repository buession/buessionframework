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

import com.buession.redis.core.command.args.hash.HSetExArgument;
import io.lettuce.core.HSetExArgs;

import java.time.Duration;
import java.time.Instant;

/**
 * Lettuce {@link HSetExArgs} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceHSetExArgs extends HSetExArgs {

	/**
	 * 构造函数
	 */
	public LettuceHSetExArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param hSetExArgument
	 *        {@link HSetExArgument}
	 */
	public LettuceHSetExArgs(final HSetExArgument hSetExArgument) {
		super();

		if(hSetExArgument != null){
			if(hSetExArgument.getType() != null && hSetExArgument.getExpires() != null){
				switch(hSetExArgument.getType()){
					case EX -> ex(Duration.ofSeconds(hSetExArgument.getExpires()));
					case EXAT -> exAt(Instant.ofEpochSecond(hSetExArgument.getExpires()));
					case PX -> px(Duration.ofMillis(hSetExArgument.getExpires()));
					case PXAT -> pxAt(Instant.ofEpochMilli(hSetExArgument.getExpires()));
					case KEEPTTL -> keepttl();
				}
			}

			if(hSetExArgument.getFnxFxx() == HSetExArgument.FnxFxx.FNX){
				fnx();
			}else if(hSetExArgument.getFnxFxx() == HSetExArgument.FnxFxx.FXX){
				fxx();
			}
		}
	}

}
