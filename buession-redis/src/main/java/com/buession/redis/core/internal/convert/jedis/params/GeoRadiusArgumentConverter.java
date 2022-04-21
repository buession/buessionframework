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
package com.buession.redis.core.internal.convert.jedis.params;

import com.buession.core.converter.Converter;
import com.buession.lang.Order;
import com.buession.redis.core.command.GeoCommands;
import redis.clients.jedis.params.GeoRadiusParam;

/**
 * {@link GeoCommands.GeoRadiusArgument} 转换为 jedis {@link GeoRadiusParam}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class GeoRadiusArgumentConverter implements Converter<GeoCommands.GeoRadiusArgument, GeoRadiusParam> {

	public final static GeoRadiusArgumentConverter INSTANCE = new GeoRadiusArgumentConverter();

	@Override
	public GeoRadiusParam convert(final GeoCommands.GeoRadiusArgument source){
		final GeoRadiusParam geoRadiusParam = new GeoRadiusParam();

		if(Boolean.TRUE.equals(source.isWithCoord())){
			geoRadiusParam.withCoord();
		}

		if(Boolean.TRUE.equals(source.isWithDist())){
			geoRadiusParam.withDist();
		}

		if(Boolean.TRUE.equals(source.isWithHash())){
			geoRadiusParam.withHash();
		}

		if(source.getOrder() == Order.ASC){
			geoRadiusParam.sortAscending();
		}else if(source.getOrder() == Order.DESC){
			geoRadiusParam.sortDescending();
		}

		if(source.getCount() != null){
			geoRadiusParam.count(source.getCount());
		}

		return geoRadiusParam;
	}

}
