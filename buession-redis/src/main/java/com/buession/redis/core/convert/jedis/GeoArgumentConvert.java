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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.convert.jedis;

import com.buession.core.convert.Convert;
import com.buession.lang.Order;
import com.buession.redis.core.command.GeoCommands;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.util.SafeEncoder;

/**
 * @author Yong.Teng
 */
public class GeoArgumentConvert implements Convert<GeoCommands.GeoArgument, GeoRadiusParam> {

	@Override
	public GeoRadiusParam encode(final GeoCommands.GeoArgument source){
		if(source == null){
			return null;
		}

		final GeoRadiusParam geoRadiusParam = new GeoRadiusParam();

		if(source.isWithCoord()){
			geoRadiusParam.withCoord();
		}

		if(source.isWithDist()){
			geoRadiusParam.withDist();
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

	@Override
	public GeoCommands.GeoArgument decode(final GeoRadiusParam target){
		if(target == null){
			return null;
		}

		final GeoCommands.GeoArgument.Builder geoArgumentBuilder = GeoCommands.GeoArgument.Builder.create();

		for(byte[] v : target.getByteParams()){
			String s = SafeEncoder.encode(v);

			if("withcoord".equals(s)){
				geoArgumentBuilder.withCoord();
			}else if("withdist".equals(s)){
				geoArgumentBuilder.withDist();
			}else if("asc".equals(s)){
				geoArgumentBuilder.order(Order.ASC);
			}else if("desc".equals(s)){
				geoArgumentBuilder.order(Order.DESC);
			}else if("count".equals(s)){
				geoArgumentBuilder.count(target.getParam("count"));
			}
		}

		return geoArgumentBuilder.build();
	}

}
