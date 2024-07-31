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
import com.buession.redis.core.command.args.GeoSearchStoreArgument;
import com.buession.redis.core.internal.convert.jedis.params.GeoUnitConverter;
import com.buession.redis.core.internal.convert.jedis.params.OrderConverter;
import redis.clients.jedis.params.GeoSearchParam;

/**
 * Redis {@link GeoSearchParam} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisGeoSearchStoreParam extends GeoSearchParam {

	private final GeoUnitConverter geoUnitConverter = new GeoUnitConverter();

	private final OrderConverter orderConverter = new OrderConverter();

	/**
	 * 从 {@link GeoSearchStoreArgument} 创建 {@link GeoSearchParam} 实例
	 *
	 * @param geoSearchStoreArgument
	 *        {@link GeoSearchStoreArgument}
	 *
	 * @return {@link JedisGeoSearchStoreParam} 实例
	 */
	public static JedisGeoSearchStoreParam from(final GeoSearchStoreArgument geoSearchStoreArgument) {
		final JedisGeoSearchStoreParam geoSearchStoreParam = new JedisGeoSearchStoreParam();

		if(geoSearchStoreArgument.getFromMode() instanceof GeoSearchStoreArgument.FromMember){
			geoSearchStoreParam.fromMember(
					((GeoSearchStoreArgument.FromMember) geoSearchStoreArgument.getFromMode()).getMember());
		}else if(geoSearchStoreArgument.getFromMode() instanceof GeoSearchStoreArgument.FromLonLat){
			final Geo geo = ((GeoSearchStoreArgument.FromLonLat) geoSearchStoreArgument.getFromMode()).getGeo();
			geoSearchStoreParam.fromLonLat(geo.getLongitude(), geo.getLatitude());
		}

		if(geoSearchStoreArgument.getPredicate() instanceof GeoSearchArgument.RadiusPredicate){
			final GeoSearchArgument.RadiusPredicate radius =
					(GeoSearchArgument.RadiusPredicate) geoSearchStoreArgument.getPredicate();
			geoSearchStoreParam.byRadius(radius.getRadius(),
					geoSearchStoreParam.geoUnitConverter.convert(radius.getUnit()));
		}else if(geoSearchStoreArgument.getPredicate() instanceof GeoSearchArgument.BoxPredicate){
			final GeoSearchArgument.BoxPredicate box =
					(GeoSearchArgument.BoxPredicate) geoSearchStoreArgument.getPredicate();
			geoSearchStoreParam.byBox(box.getWidth(), box.getHeight(),
					geoSearchStoreParam.geoUnitConverter.convert(box.getUnit()));
		}

		if(geoSearchStoreArgument.getOrder() != null){
			geoSearchStoreParam.sortingOrder(
					geoSearchStoreParam.orderConverter.convert(geoSearchStoreArgument.getOrder()));
		}

		if(geoSearchStoreArgument.getCount() != null){
			if(geoSearchStoreArgument.isAny() != null){
				geoSearchStoreParam.count(geoSearchStoreArgument.getCount(), geoSearchStoreArgument.isAny());
			}else{
				geoSearchStoreParam.count(geoSearchStoreArgument.getCount());
			}
		}

		return geoSearchStoreParam;
	}

}
