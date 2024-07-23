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

import com.buession.lang.Geo;
import io.lettuce.core.GeoValue;

/**
 * Lettuce {@link GeoValue} 扩展
 *
 * @param <V>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceGeoValue<V> extends GeoValue<V> {

	private final static long serialVersionUID = -818577420137908972L;

	/**
	 * 构造函数
	 */
	public LettuceGeoValue() {
		super();
	}

	/**
	 * 从 {@link Geo} 创建 {@link GeoValue} 实例
	 *
	 * @param member
	 * 		成员
	 * @param geo
	 *        {@link Geo}
	 * @param <V>
	 * 		值类型
	 *
	 * @return {@link LettuceGeoValue} 实例
	 */
	public static <V> GeoValue<V> from(final V member, final Geo geo) {
		return just(geo.getLongitude(), geo.getLatitude(), member);
	}

}
