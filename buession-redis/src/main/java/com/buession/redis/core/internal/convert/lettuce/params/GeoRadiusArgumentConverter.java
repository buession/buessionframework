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
import com.buession.lang.Order;
import com.buession.redis.core.command.args.GeoRadiusArgument;
import io.lettuce.core.GeoArgs;
import org.springframework.lang.Nullable;

import java.util.Optional;

/**
 * {@link GeoRadiusArgument} 转换为 lettuce {@link GeoArgs}
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class GeoRadiusArgumentConverter implements Converter<GeoRadiusArgument, GeoArgs> {

	@Nullable
	@Override
	public GeoArgs convert(final GeoRadiusArgument source) {
		if(source == null){
			return null;
		}

		final GeoArgs geoArgs = new GeoArgs();

		if(Boolean.TRUE.equals(source.isWithCoord())){
			geoArgs.withCoordinates();
		}
		if(Boolean.TRUE.equals(source.isWithDist())){
			geoArgs.withDistance();
		}
		if(Boolean.TRUE.equals(source.isWithHash())){
			geoArgs.withHash();
		}

		if(source.getOrder() == Order.ASC){
			geoArgs.sort(GeoArgs.Sort.asc);
		}else if(source.getOrder() == Order.DESC){
			geoArgs.sort(GeoArgs.Sort.desc);
		}

		Optional.ofNullable(source.getCount()).ifPresent(geoArgs::withCount);

		if(source.getCount() != null){
			geoArgs.withCount(source.getCount(), Boolean.TRUE.equals(source.isAny()));
		}

		return geoArgs;
	}

}
