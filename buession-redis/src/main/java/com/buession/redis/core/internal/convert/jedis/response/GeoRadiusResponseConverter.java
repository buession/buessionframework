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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.internal.convert.jedis.response;

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.redis.core.GeoRadius;
import redis.clients.jedis.resps.GeoRadiusResponse;

import java.util.List;

/**
 * Jedis {@link GeoRadiusResponse} 转换为 {@link GeoRadius}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class GeoRadiusResponseConverter implements Converter<GeoRadiusResponse, GeoRadius> {

	private final GeoCoordinateConverter geoCoordinateConverter = new GeoCoordinateConverter();

	@Override
	public GeoRadius convert(final GeoRadiusResponse source) {
		return new GeoRadius(source.getMember(), source.getDistance(),
				source.getCoordinate() == null ? null : geoCoordinateConverter.convert(source.getCoordinate()));
	}

	public static ListConverter<GeoRadiusResponse, GeoRadius> listConverter() {
		return new ListConverter<>(new GeoRadiusResponseConverter());
	}

}
