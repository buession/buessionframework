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
package com.buession.redis.core.internal.lettuce;

import com.buession.lang.Order;
import com.buession.redis.core.command.GeoCommands;
import io.lettuce.core.GeoArgs;

import java.util.Optional;

/**
 * Lettuce {@link GeoArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceGeoArgs extends GeoArgs {

	public LettuceGeoArgs() {
		super();
	}

	public LettuceGeoArgs(final boolean withCoord, final boolean withDist, final boolean withHash) {
		super();
		withCoordinates(this, withCoord);
		withDistance(this, withDist);
		withHash(this, withHash);
	}

	public LettuceGeoArgs(final Order order) {
		super();
		sort(this, order);
	}

	public LettuceGeoArgs(final int count) {
		super();
		withCount(count);
	}

	public LettuceGeoArgs(final Order order, final int count) {
		this(order);
		withCount(count);
	}

	public LettuceGeoArgs(final boolean withCoord, final boolean withDist, final boolean withHash,
						  final Order order) {
		this(withCoord, withDist, withHash);
		sort(this, order);
	}

	public LettuceGeoArgs(final boolean withCoord, final boolean withDist, final boolean withHash,
						  final int count) {
		this(withCoord, withDist, withHash);
		withCount(count);
	}

	public LettuceGeoArgs(final boolean withCoord, final boolean withDist, final boolean withHash,
						  final Order order, final int count) {
		this(withCoord, withDist, withHash, order);
		withCount(count);
	}

	public static LettuceGeoArgs from(final GeoCommands.GeoRadiusArgument geoRadiusArgument) {
		final LettuceGeoArgs geoArgs = new LettuceGeoArgs();

		if(geoRadiusArgument != null){
			withCoordinates(geoArgs, geoRadiusArgument.isWithCoord());
			withDistance(geoArgs, geoRadiusArgument.isWithDist());
			withHash(geoArgs, geoRadiusArgument.isWithHash());
			sort(geoArgs, geoRadiusArgument.getOrder());
			Optional.ofNullable(geoRadiusArgument.getCount()).ifPresent(geoArgs::withCount);
		}

		return geoArgs;
	}

	private static void withCoordinates(final LettuceGeoArgs geoArgs, final Boolean withCoord) {
		if(Boolean.TRUE.equals(withCoord)){
			geoArgs.withCoordinates();
		}
	}

	private static void withDistance(final LettuceGeoArgs geoArgs, final Boolean withDist) {
		if(Boolean.TRUE.equals(withDist)){
			geoArgs.withDistance();
		}
	}

	private static void withHash(final LettuceGeoArgs geoArgs, final Boolean withHash) {
		if(Boolean.TRUE.equals(withHash)){
			geoArgs.withHash();
		}
	}

	private static void sort(final LettuceGeoArgs geoArgs, final Order order) {
		if(order == Order.ASC){
			geoArgs.sort(Sort.asc);
		}else if(order == Order.DESC){
			geoArgs.sort(Sort.desc);
		}
	}

}
