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
package com.buession.redis.core.internal.convert.jedis.params;

import com.buession.core.converter.Converter;
import com.buession.redis.core.GeoUnit;

/**
 * {@link GeoUnit} 转换为 jedis {@link redis.clients.jedis.args.GeoUnit}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class GeoUnitConverter implements Converter<GeoUnit, redis.clients.jedis.args.GeoUnit> {

	public final static GeoUnitConverter INSTANCE = new GeoUnitConverter();

	@Override
	public redis.clients.jedis.args.GeoUnit convert(final GeoUnit source){
		switch(source){
			case M:
				return redis.clients.jedis.args.GeoUnit.M;
			case KM:
				return redis.clients.jedis.args.GeoUnit.KM;
			case MI:
				return redis.clients.jedis.args.GeoUnit.MI;
			case FT:
				return redis.clients.jedis.args.GeoUnit.FT;
			default:
				return null;
		}
	}

}
