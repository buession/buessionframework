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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.convert.jedis;

import com.buession.core.converter.Converter;
import com.buession.core.utils.NumberUtils;
import com.buession.redis.core.command.KeyCommands;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.params.RestoreParams;

/**
 * {@link KeyCommands.RestoreArgument} 和 jedis {@link RestoreParams} 互转
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface RestoreArgumentConverter<S, T> extends Converter<S, T> {

	/**
	 * {@link KeyCommands.RestoreArgument} 转换为 jedis {@link RestoreParams}
	 *
	 * @author Yong.Teng
	 * @since 1.2.1
	 */
	final class RestoreArgumentJedisConverter
			implements RestoreArgumentConverter<KeyCommands.RestoreArgument, RestoreParams> {

		@Override
		public RestoreParams convert(final KeyCommands.RestoreArgument source){
			final RestoreParams restoreParams = RestoreParams.restoreParams();

			if(source.isReplace() != null){
				restoreParams.replace();
			}

			if(source.isAbsTtl() != null){
				restoreParams.absTtl();
			}

			if(source.getIdleTime() != null){
				restoreParams.idleTime(source.getIdleTime());
			}

			if(source.getFrequency() != null){
				restoreParams.frequency(source.getFrequency());
			}

			return restoreParams;
		}

	}

	/**
	 * jedis {@link RestoreParams} 转换为 {@link KeyCommands.RestoreArgument}
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class RestoreArgumentExposeConverter
			implements RestoreArgumentConverter<RestoreParams, KeyCommands.RestoreArgument> {

		@Override
		public KeyCommands.RestoreArgument convert(final RestoreParams source){
			final KeyCommands.RestoreArgument.Builder builder = KeyCommands.RestoreArgument.Builder.create();
			byte[][] params = source.getByteParams();

			for(int i = 0; i < params.length; i++){
				if(Protocol.Keyword.REPLACE.getRaw().equals(params[i])){
					builder.replace();
				}else if(Protocol.Keyword.ABSTTL.getRaw().equals(params[i])){
					builder.absTtl();
				}else if(Protocol.Keyword.IDLETIME.getRaw().equals(params[i])){
					builder.idleTime(NumberUtils.bytes2long(params[++i]));
				}else if(Protocol.Keyword.FREQ.getRaw().equals(params[i])){
					builder.frequency(NumberUtils.bytes2long(params[++i]));
				}
			}

			return builder.build();
		}

	}

}
