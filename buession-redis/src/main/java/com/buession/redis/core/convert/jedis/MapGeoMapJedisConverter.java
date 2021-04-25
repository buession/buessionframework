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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.convert.jedis;

import com.buession.core.converter.MapConverter;
import com.buession.lang.Geo;
import redis.clients.jedis.GeoCoordinate;

/**
 * {@link java.util.Map}&lt;K, Geo&gt; 转换为 {@link java.util.Map}&lt;K, GeoCoordinate&gt;
 *
 * @param <K>
 *        {@link java.util.Map} key 类型
 *
 * @author Yong.Teng
 * @since 1.2.1
 */
final public class MapGeoMapJedisConverter<K> extends MapConverter<K, Geo, K, GeoCoordinate> {

	public MapGeoMapJedisConverter(){
		super((source)->source, (value)->new GeoCoordinate(value.getLongitude(), value.getLatitude()));
	}

}
