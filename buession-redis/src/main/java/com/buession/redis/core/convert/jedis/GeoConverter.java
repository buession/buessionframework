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
package com.buession.redis.core.convert.jedis;

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapConverter;
import com.buession.lang.Geo;
import redis.clients.jedis.GeoCoordinate;

import java.util.List;
import java.util.Map;

/**
 * {@link Geo} 和 jedis {@link GeoCoordinate} 互转
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface GeoConverter<S, T> extends Converter<S, T> {

	/**
	 * {@link Geo} 转换为 jedis {@link GeoCoordinate}
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class GeoJedisConverter implements GeoConverter<Geo, GeoCoordinate> {

		@Override
		public GeoCoordinate convert(final Geo source){
			return new GeoCoordinate(source.getLongitude(), source.getLatitude());
		}

	}

	/**
	 * jedis {@link GeoCoordinate} 转换为 {@link Geo}
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class GeoExposeConverter implements GeoConverter<GeoCoordinate, Geo> {

		@Override
		public Geo convert(final GeoCoordinate source){
			return new Geo(source.getLongitude(), source.getLatitude());
		}

	}

	/**
	 * {@link java.util.List}&lt;Geo&gt; 转换为 jedis {@link java.util.List}&lt;GeoCoordinate&gt;
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class ListGeoJedisConverter extends ListConverter<Geo, GeoCoordinate>
			implements GeoConverter<List<Geo>, List<GeoCoordinate>> {

		public ListGeoJedisConverter(){
			super((source)->new GeoCoordinate(source.getLongitude(), source.getLatitude()));
		}

	}

	/**
	 * jedis {@link java.util.List}&lt;GeoCoordinate&gt; 转换为 {@link java.util.List}&lt;Geo&gt;
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class ListGeoExposeConverter extends ListConverter<GeoCoordinate, Geo>
			implements GeoConverter<List<GeoCoordinate>, List<Geo>> {

		public ListGeoExposeConverter(){
			super((source)->new Geo(source.getLongitude(), source.getLatitude()));
		}

	}

	/**
	 * {@link java.util.Map}&lt;K, Geo&gt; 转换为 jeis {@link java.util.Map}&lt;K, GeoCoordinate&gt;
	 *
	 * @param <K>
	 *        {@link java.util.Map} key 类型
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class MapGeoMapJedisConverter<K> extends MapConverter<K, Geo, K, GeoCoordinate>
			implements GeoConverter<Map<K, Geo>, Map<K, GeoCoordinate>> {

		public MapGeoMapJedisConverter(){
			super((source)->source, (value)->new GeoCoordinate(value.getLongitude(), value.getLatitude()));
		}

	}

	/**
	 * jedis {@link java.util.Map}&lt;K, GeoCoordinate&gt; 转换为 {@link java.util.Map}&lt;K, Geo&gt;
	 *
	 * @param <K>
	 *        {@link java.util.Map} key 类型
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class MapGeoMapExposeConverter<K> extends MapConverter<K, GeoCoordinate, K, Geo>
			implements GeoConverter<Map<K, GeoCoordinate>, Map<K, Geo>> {

		public MapGeoMapExposeConverter(){
			super((source)->source, (value)->new Geo(value.getLongitude(), value.getLatitude()));
		}

	}

}
