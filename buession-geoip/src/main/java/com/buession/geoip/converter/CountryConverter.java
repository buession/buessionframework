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

import com.buession.geoip.model.Country;
import com.buession.geoip.resource.CountryResource;
import com.maxmind.geoip2.model.CountryResponse;

import java.util.Locale;

/**
 * 国家数据转换器
 *
 * @author Yong.Teng
 */
public class CountryConverter extends AbstractConverter<Country, com.maxmind.geoip2.record.Country, CountryResponse> {

	private final static CountryResource countryResource = new CountryResource();

	@Override
	public Country converter(com.maxmind.geoip2.record.Country country, CountryResponse response, Locale locale){
		if(country == null){
			return null;
		}

		final String name = getName(country.getNames(), locale);
		final String fullName = country.getIsoCode() == null ? null :
				countryResource.getData().get(country.getIsoCode());

		return new Country(country.getGeoNameId(), country.getConfidence(), country.getIsoCode(), country.getName(),
				name, fullName, country.isInEuropeanUnion());
	}

}
