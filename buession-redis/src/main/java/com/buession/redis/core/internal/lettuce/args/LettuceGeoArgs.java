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
package com.buession.redis.core.internal.lettuce.args;

import com.buession.lang.Order;
import com.buession.redis.core.command.args.BaseGeoDistanceArgument;
import io.lettuce.core.GeoArgs;

/**
 * Lettuce {@link GeoArgs} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceGeoArgs extends GeoArgs {

	/**
	 * 构造函数
	 */
	public LettuceGeoArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param distanceArgument
	 *        {@link BaseGeoDistanceArgument}
	 */
	public LettuceGeoArgs(final BaseGeoDistanceArgument distanceArgument) {
		super();

		if(distanceArgument != null){
			if(Boolean.TRUE.equals(distanceArgument.isWithCoord())){
				withCoordinates();
			}
			if(Boolean.TRUE.equals(distanceArgument.isWithDist())){
				withDistance();
			}
			if(Boolean.TRUE.equals(distanceArgument.isWithHash())){
				withHash();
			}

			if(distanceArgument.getOrder() == Order.ASC){
				asc();
			}else if(distanceArgument.getOrder() == Order.DESC){
				desc();
			}
		}
	}

	/**
	 * 构造函数
	 *
	 * @param distanceArgument
	 *        {@link BaseGeoDistanceArgument}
	 * @param count
	 * 		返回数量
	 */
	public LettuceGeoArgs(final BaseGeoDistanceArgument distanceArgument, final Integer count) {
		this(distanceArgument);
		withCount(count);
	}

	/**
	 * 构造函数
	 *
	 * @param distanceArgument
	 *        {@link BaseGeoDistanceArgument}
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 */
	public LettuceGeoArgs(final BaseGeoDistanceArgument distanceArgument, final Integer count, final Boolean any) {
		this(distanceArgument);
		withCount(count, any);
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回数量
	 */
	public LettuceGeoArgs(final Integer count) {
		super();
		withCount(count);
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 */
	public LettuceGeoArgs(final Integer count, final Boolean any) {
		super();
		withCount(count, any);
	}

}
