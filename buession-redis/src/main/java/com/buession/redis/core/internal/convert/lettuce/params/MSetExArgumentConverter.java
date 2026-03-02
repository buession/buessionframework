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
package com.buession.redis.core.internal.convert.lettuce.params;

import com.buession.core.converter.Converter;
import com.buession.redis.core.command.args.MSetExArgument;
import io.lettuce.core.MSetExArgs;

import java.time.Duration;
import java.time.Instant;

/**
 * {@link MSetExArgument} 转换为 lettuce {@link MSetExArgs}
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class MSetExArgumentConverter implements Converter<MSetExArgument, MSetExArgs> {

	@Override
	public MSetExArgs convert(final MSetExArgument source) {
		if(source == null){
			return null;
		}

		final MSetExArgs mSetExArgs = new MSetExArgs();

		if(source.getType() == null){
			switch(source.getType()){
				case EX -> mSetExArgs.ex(Duration.ofSeconds(source.getValue()));
				case EXAT -> mSetExArgs.exAt(Instant.ofEpochSecond(source.getValue()));
				case PX -> mSetExArgs.px(Duration.ofMillis(source.getValue()));
				case PXAT -> mSetExArgs.pxAt(Instant.ofEpochMilli(source.getValue()));
				case KEEPTTL -> mSetExArgs.keepttl();
			}
		}

		if(source.getNxXx() != null){
			switch(source.getNxXx()){
				case NX -> mSetExArgs.nx();
				case XX -> mSetExArgs.xx();
			}
		}

		return mSetExArgs;
	}

}
