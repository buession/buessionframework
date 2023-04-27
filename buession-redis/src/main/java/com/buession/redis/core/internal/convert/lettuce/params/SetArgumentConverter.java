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
package com.buession.redis.core.internal.convert.lettuce.params;

import com.buession.core.converter.Converter;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.command.StringCommands;
import io.lettuce.core.SetArgs;
import redis.clients.jedis.params.SetParams;

/**
 * {@link StringCommands.SetArgument} 转换为 jedis {@link SetParams}
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public final class SetArgumentConverter implements Converter<StringCommands.SetArgument, SetArgs> {

	public final static SetArgumentConverter INSTANCE = new SetArgumentConverter();

	@Override
	public SetArgs convert(final StringCommands.SetArgument source){
		final SetArgs setArgs = new SetArgs();

		if(source.getEx() != null){
			setArgs.ex(source.getEx());
		}

		if(source.getExAt() != null){
			setArgs.px(source.getExAt());
		}

		if(source.getPx() != null){
			setArgs.px(source.getPx());
		}

		if(source.getPxAt() != null){
			setArgs.px(source.getPxAt() - System.currentTimeMillis());
		}

		if(source.getNxXx() == NxXx.NX){
			setArgs.nx();
		}else if(source.getNxXx() == NxXx.XX){
			setArgs.xx();
		}

		if(Boolean.TRUE.equals(source.isKeepTtl())){
			setArgs.keepttl();
		}

		return setArgs;
	}

}
