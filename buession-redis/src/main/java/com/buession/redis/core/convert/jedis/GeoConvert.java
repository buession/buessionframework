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

import com.buession.lang.Geo;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.command.GeoCommands;
import com.buession.redis.core.convert.Convert;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class GeoConvert implements Convert<Geo, GeoCoordinate> {

	@Override
	public GeoCoordinate convert(final Geo source){
		return source == null ? null : new GeoCoordinate(source.getLongitude(), source.getLatitude());
	}

	@Override
	public Geo deconvert(final GeoCoordinate target){
		return target == null ? null : new Geo(target.getLongitude(), target.getLatitude());
	}

	public static class GeoMapConvert<K> implements Convert<Map<K, Geo>, Map<K, GeoCoordinate>> {

		@Override
		public Map<K, GeoCoordinate> convert(final Map<K, Geo> source){
			if(source == null){
				return null;
			}

			final Map<K, GeoCoordinate> result = new LinkedHashMap<>(source.size());

			source.forEach((key, value)->result.put(key, new GeoCoordinate(value.getLongitude(), value.getLatitude()
			)));

			return result;
		}

		@Override
		public Map<K, Geo> deconvert(final Map<K, GeoCoordinate> target){
			if(target == null){
				return null;
			}

			final Map<K, Geo> result = new LinkedHashMap<>();

			target.forEach((key, value)->result.put(key, new Geo(value.getLongitude(), value.getLatitude())));

			return result;
		}
	}

	public static class ListMapConvert implements Convert<List<Geo>, List<GeoCoordinate>> {

		@Override
		public List<GeoCoordinate> convert(final List<Geo> source){
			if(source == null){
				return null;
			}

			final List<GeoCoordinate> result = new ArrayList<>(source.size());

			for(Geo geo : source){
				result.add(new GeoCoordinate(geo.getLongitude(), geo.getLatitude()));
			}

			return result;
		}

		@Override
		public List<Geo> deconvert(final List<GeoCoordinate> target){
			if(target == null){
				return null;
			}

			final List<Geo> result = new ArrayList<>(target.size());

			for(GeoCoordinate geoCoordinate : target){
				result.add(new Geo(geoCoordinate.getLongitude(), geoCoordinate.getLatitude()));
			}

			return result;
		}
	}

	public static class GeoUnitConvert implements Convert<GeoCommands.GeoUnit, GeoUnit> {

		@Override
		public GeoUnit convert(final GeoCommands.GeoUnit source){
			switch(source){
				case M:
					return GeoUnit.M;
				case KM:
					return GeoUnit.KM;
				case MI:
					return GeoUnit.MI;
				case FT:
					return GeoUnit.FT;
				default:
					return null;
			}
		}

		@Override
		public GeoCommands.GeoUnit deconvert(final GeoUnit target){
			switch(target){
				case M:
					return GeoCommands.GeoUnit.M;
				case KM:
					return GeoCommands.GeoUnit.KM;
				case MI:
					return GeoCommands.GeoUnit.MI;
				case FT:
					return GeoCommands.GeoUnit.FT;
				default:
					return null;
			}
		}

	}

	public static class GeoRadiusConvert implements Convert<GeoRadius, GeoRadiusResponse> {

		@Override
		public GeoRadiusResponse convert(final GeoRadius source){
			if(source == null){
				return null;
			}

			final GeoRadiusResponse geoRadiusResponse = new GeoRadiusResponse(source.getMember());

			geoRadiusResponse.setDistance(source.getDistance());
			geoRadiusResponse.setCoordinate((new GeoConvert()).convert(source.getGeo()));

			return geoRadiusResponse;
		}

		@Override
		public GeoRadius deconvert(final GeoRadiusResponse target){
			if(target == null){
				return null;
			}

			final GeoRadius geoRadius = new GeoRadius();

			geoRadius.setMember(target.getMember());
			geoRadius.setDistance(target.getDistance());
			geoRadius.setGeo((new GeoConvert()).deconvert(target.getCoordinate()));

			return geoRadius;
		}

		public static class ListGeoRadiusConvert implements Convert<List<GeoRadius>, List<GeoRadiusResponse>> {

			@Override
			public List<GeoRadiusResponse> convert(final List<GeoRadius> source){
				if(source == null){
					return null;
				}

				final List<GeoRadiusResponse> geoRadiusResponses = new ArrayList<>(source.size());
				final GeoConvert geoConvert = new GeoConvert();

				for(GeoRadius geoRadius : source){
					GeoRadiusResponse geoRadiusResponse = new GeoRadiusResponse(geoRadius.getMember());

					geoRadiusResponse.setDistance(geoRadius.getDistance());
					geoRadiusResponse.setCoordinate(geoConvert.convert(geoRadius.getGeo()));

					geoRadiusResponses.add(geoRadiusResponse);
				}

				return geoRadiusResponses;
			}

			@Override
			public List<GeoRadius> deconvert(final List<GeoRadiusResponse> target){
				if(target == null){
					return null;
				}

				final List<GeoRadius> result = new ArrayList<>(target.size());
				final GeoConvert geoConvert = new GeoConvert();

				for(GeoRadiusResponse geoRadiusResponse : target){
					GeoRadius geoRadius = new GeoRadius();

					geoRadius.setMember(geoRadiusResponse.getMember());
					geoRadius.setDistance(geoRadiusResponse.getDistance());
					geoRadius.setGeo(geoConvert.deconvert(geoRadiusResponse.getCoordinate()));

					result.add(geoRadius);
				}

				return result;
			}
		}

	}

}
