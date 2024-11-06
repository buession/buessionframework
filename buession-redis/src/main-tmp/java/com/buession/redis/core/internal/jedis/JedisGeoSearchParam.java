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
package com.buession.redis.core.internal.jedis;

import com.buession.lang.Geo;
import com.buession.redis.core.command.args.GeoSearchArgument;
import com.buession.redis.core.internal.convert.jedis.params.GeoUnitConverter;
import com.buession.redis.core.internal.convert.jedis.params.OrderConverter;
import redis.clients.jedis.params.GeoSearchParam;

/**
 * Redis {@link GeoSearchParam} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisGeoSearchParam extends GeoSearchParam {

	private final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();

	private final OrderConverter orderConverter = new OrderConverter();

	/**
	 * 从 {@link GeoSearchArgument} 创建 {@link GeoSearchParam} 实例
	 *
	 * @param geoSearchArgument
	 *        {@link GeoSearchArgument}
	 *
	 * @return {@link JedisGeoSearchParam} 实例
	 */
	public static JedisGeoSearchParam from(final GeoSearchArgument geoSearchArgument) {
		final JedisGeoSearchParam geoSearchParam = new JedisGeoSearchParam();

		if(geoSearchArgument.getFromMode() instanceof GeoSearchArgument.FromMember){
			geoSearchParam.fromMember(((GeoSearchArgument.FromMember) geoSearchArgument.getFromMode()).getMember());
		}else if(geoSearchArgument.getFromMode() instanceof GeoSearchArgument.FromLonLat){
			final Geo geo = ((GeoSearchArgument.FromLonLat) geoSearchArgument.getFromMode()).getGeo();
			geoSearchParam.fromLonLat(geo.getLongitude(), geo.getLatitude());
		}

		if(geoSearchArgument.getPredicate() instanceof GeoSearchArgument.RadiusPredicate){
			final GeoSearchArgument.RadiusPredicate radius =
					(GeoSearchArgument.RadiusPredicate) geoSearchArgument.getPredicate();
			geoSearchParam.byRadius(radius.getRadius(), geoSearchParam.geoUnitConverter.convert(radius.getUnit()));
		}else if(geoSearchArgument.getPredicate() instanceof GeoSearchArgument.BoxPredicate){
			final GeoSearchArgument.BoxPredicate box =
					(GeoSearchArgument.BoxPredicate) geoSearchArgument.getPredicate();
			geoSearchParam.byBox(box.getWidth(), box.getHeight(),
					geoSearchParam.geoUnitConverter.convert(box.getUnit()));
		}

		if(geoSearchArgument.getOrder() != null){
			geoSearchParam.sortingOrder(geoSearchParam.orderConverter.convert(geoSearchArgument.getOrder()));
		}

		if(geoSearchArgument.getCount() != null){
			if(geoSearchArgument.isAny() != null){
				geoSearchParam.count(geoSearchArgument.getCount(), geoSearchArgument.isAny());
			}else{
				geoSearchParam.count(geoSearchArgument.getCount());
			}
		}

		if(Boolean.TRUE.equals(geoSearchArgument.getWithCoord())){
			geoSearchParam.withCoord();
		}

		if(Boolean.TRUE.equals(geoSearchArgument.isWithHash())){
			geoSearchParam.withDist();
		}

		if(Boolean.TRUE.equals(geoSearchArgument.isWithHash())){
			geoSearchParam.withHash();
		}

		return geoSearchParam;
	}

}
