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
package com.buession.redis.core.internal.lettuce.utils;

import com.buession.lang.Geo;
import com.buession.redis.core.internal.lettuce.LettuceGeoValue;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.GeoValue;

import java.util.Map;

/**
 * Lettuce {@link GeoValue} 工具类
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class GeoValueUtils {

	protected GeoValueUtils() {

	}

	public static GeoValue<byte[]>[] fromStringMap(final Map<String, Geo> values) {
		if(values == null){
			return null;
		}else{
			final GeoValue<byte[]>[] result = new GeoValue[values.size()];
			int i = 0;

			for(Map.Entry<String, Geo> e : values.entrySet()){
				result[i++] = LettuceGeoValue.from(SafeEncoder.encode(e.getKey()), e.getValue());
			}

			return result;
		}
	}

	public static GeoValue<byte[]>[] fromBinaryMap(final Map<byte[], Geo> values) {
		if(values == null){
			return null;
		}else{
			final GeoValue<byte[]>[] result = new GeoValue[values.size()];
			int i = 0;

			for(Map.Entry<byte[], Geo> e : values.entrySet()){
				result[i++] = LettuceGeoValue.from(e.getKey(), e.getValue());
			}

			return result;
		}
	}

}
