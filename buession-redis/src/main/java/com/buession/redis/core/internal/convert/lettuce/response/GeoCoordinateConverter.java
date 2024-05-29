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
package com.buession.redis.core.internal.convert.lettuce.response;

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.lang.Geo;
import io.lettuce.core.GeoCoordinates;

import java.util.List;

/**
 * Lettuce {@link GeoCoordinates} 转换为 {@link Geo}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class GeoCoordinateConverter implements Converter<GeoCoordinates, Geo> {

	@Override
	public Geo convert(final GeoCoordinates source) {
		return new Geo(source.getX().doubleValue(), source.getY().doubleValue());
	}

	public static ListConverter<GeoCoordinates, Geo> listConverter() {
		return new ListConverter<>(new GeoCoordinateConverter());
	}

}
