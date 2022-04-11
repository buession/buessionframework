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
package com.buession.redis.core.internal.convert.jedis;

import com.buession.core.converter.Converter;
import com.buession.redis.core.command.SortedSetCommands;
import redis.clients.jedis.params.ZAddParams;

import java.util.Objects;

/**
 * {@link SortedSetCommands.ZAddArgument} 和 jedis {@link ZAddParams} 互转
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface ZAddArgumentConverter<S, T> extends Converter<S, T> {

	final class ZAddArgumentJedisConverter
			implements ZAddArgumentConverter<SortedSetCommands.ZAddArgument, ZAddParams> {

		@Override
		public ZAddParams convert(final SortedSetCommands.ZAddArgument source){
			final ZAddParams zAddParams = new ZAddParams();

			if(source.getNxXx() != null){
				switch(source.getNxXx()){
					case NX:
						zAddParams.nx();
						break;
					case XX:
						zAddParams.xx();
						break;
					default:
						break;
				}
			}

			if(source.getGtLt() != null){
				switch(source.getGtLt()){
					case GT:
						zAddParams.gt();
						break;
					case LT:
						zAddParams.lt();
						break;
					default:
						break;
				}
			}

			if(Objects.equals(source.getCh(), Boolean.TRUE)){
				zAddParams.ch();
			}

			return zAddParams;
		}

	}

}
