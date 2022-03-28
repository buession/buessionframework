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
import com.buession.lang.Order;
import com.buession.redis.core.command.GeoCommands;
import redis.clients.jedis.params.GeoRadiusParam;

/**
 * {@link GeoRadiusParam} 和 {@link GeoCommands.GeoRadiusArgument} 互转
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface GeoRadiusArgumentConverter<S, T> extends Converter<S, T> {

	/**
	 * {@link GeoRadiusParam} 转换为 jedis {@link GeoCommands.GeoRadiusArgument}
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	class GeoRadiusArgumentExposeConverter
			implements GeoRadiusArgumentConverter<GeoRadiusParam, GeoCommands.GeoRadiusArgument> {

		@Override
		public GeoCommands.GeoRadiusArgument convert(final GeoRadiusParam source){
			final GeoCommands.GeoRadiusArgument.Builder builder = GeoCommands.GeoRadiusArgument.Builder.create();

			for(byte[] v : source.getByteParams()){
				String s = redis.clients.jedis.util.SafeEncoder.encode(v);

				if("withcoord".equals(s)){
					builder.withCoord();
				}else if("withdist".equals(s)){
					builder.withDist();
				}else if("asc".equals(s)){
					builder.order(Order.ASC);
				}else if("desc".equals(s)){
					builder.order(Order.DESC);
				}else if("count".equals(s)){
					builder.count(source.getParam("count"));
				}
			}

			return builder.build();
		}

	}

	/**
	 * jedis {@link GeoCommands.GeoRadiusArgument} 转换为 {@link GeoRadiusParam}
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class GeoRadiusArgumentJedisConverter
			implements GeoRadiusArgumentConverter<GeoCommands.GeoRadiusArgument, GeoRadiusParam> {

		@Override
		public GeoRadiusParam convert(final GeoCommands.GeoRadiusArgument source){
			final GeoRadiusParam geoRadiusParam = new GeoRadiusParam();

			if(source.isWithCoord()){
				geoRadiusParam.withCoord();
			}

			if(source.isWithDist()){
				geoRadiusParam.withDist();
			}

			switch(source.getOrder()){
				case ASC:
					geoRadiusParam.sortAscending();
					break;
				case DESC:
					geoRadiusParam.sortDescending();
					break;
				default:
					break;
			}

			if(source.getCount() != null){
				geoRadiusParam.count(source.getCount());
			}

			return geoRadiusParam;
		}

	}

}
