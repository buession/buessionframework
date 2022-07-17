/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2022 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip.converter;

import com.buession.geoip.model.District;
import com.buession.geoip.model.Postal;
import com.maxmind.geoip2.model.CityResponse;

import java.util.Locale;

/**
 * 城市数据转换器
 *
 * @author Yong.Teng
 */
public class CityConverter extends AbstractConverter<District, com.maxmind.geoip2.record.City, CityResponse> {

	private final static SubdivisionConverter subdivisionConverter = new SubdivisionConverter();

	private final static PostalConverter postalConverter = new PostalConverter();

	@Override
	public District converter(com.maxmind.geoip2.record.City city, CityResponse response, Locale locale){
		if(city == null){
			return null;
		}

		final String name = getName(city.getNames(), locale);
		final Postal postal = postalConverter.converter(response.getPostal());
		final District parent = subdivisionConverter.converter(response.getLeastSpecificSubdivision(), response,
				locale);

		return new District(city.getGeoNameId(), city.getConfidence(), city.getName(), name, postal, parent);
	}

}
