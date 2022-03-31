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
import com.buession.redis.core.command.GeoCommands;
import redis.clients.jedis.params.GeoRadiusParam;

/**
 * {@link GeoCommands.GeoRadiusArgument} 和 jedis {@link GeoRadiusParam} 互转
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface GeoRadiusArgumentConverter<S, T> extends Converter<S, T> {

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

			if(source.isWithHash()){
				geoRadiusParam.withHash();
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
