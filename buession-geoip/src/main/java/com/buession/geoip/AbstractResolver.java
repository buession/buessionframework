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
 * | Copyright @ 2013-2021 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip;

import com.buession.geoip.model.District;
import com.buession.geoip.model.Country;
import com.buession.geoip.model.Location;
import com.buession.geoip.converter.CityConverter;
import com.buession.geoip.converter.ContinentConverter;
import com.buession.geoip.converter.CountryConverter;
import com.buession.geoip.converter.TraitsConverter;
import com.buession.net.utils.InetAddressUtils;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

/**
 * @author Yong.Teng
 */
public abstract class AbstractResolver implements Resolver {

	protected final static Locale locale = Locale.getDefault();

	protected final static ContinentConverter continentConverter = new ContinentConverter();

	protected final static CountryConverter countryConverter = new CountryConverter();

	protected final static CityConverter cityConverter = new CityConverter();

	protected final static TraitsConverter traitsConverter = new TraitsConverter();

	private final static Logger logger = LoggerFactory.getLogger(AbstractResolver.class);

	@Override
	public Country country(String ipAddress) throws IOException, GeoIp2Exception{
		return country(ipAddress, locale);
	}

	@Override
	public Country country(String ipAddress, Locale locale) throws IOException, GeoIp2Exception{
		InetAddress addr;

		try{
			addr = InetAddress.getByName(ipAddress);
		}catch(UnknownHostException e){
			logger.error("InetAddress.getByName({}) failure: {}", ipAddress, e.getMessage());
			return null;
		}

		return country(addr, locale);
	}

	@Override
	public Country country(long ipAddress) throws IOException, GeoIp2Exception{
		return country(ipAddress, locale);
	}

	@Override
	public Country country(long ipAddress, Locale locale) throws IOException, GeoIp2Exception{
		return country(InetAddressUtils.long2InetAddress(ipAddress), locale);
	}

	@Override
	public Country country(InetAddress ipAddress) throws IOException, GeoIp2Exception{
		return country(ipAddress, locale);
	}

	@Override
	public District district(String ipAddress) throws IOException, GeoIp2Exception{
		return district(ipAddress, locale);
	}

	@Override
	public District district(String ipAddress, Locale locale) throws IOException, GeoIp2Exception{
		InetAddress addr;

		try{
			addr = InetAddress.getByName(ipAddress);
		}catch(UnknownHostException e){
			logger.error("InetAddress.getByName({}) failure: {}", ipAddress, e.getMessage());
			return null;
		}

		return district(addr, locale);
	}

	@Override
	public District district(long ipAddress) throws IOException, GeoIp2Exception{
		return district(ipAddress, locale);
	}

	@Override
	public District district(long ipAddress, Locale locale) throws IOException, GeoIp2Exception{
		return district(InetAddressUtils.long2InetAddress(ipAddress), locale);
	}

	@Override
	public District district(InetAddress ipAddress) throws IOException, GeoIp2Exception{
		return district(ipAddress, locale);
	}

	@Override
	public Location location(String ipAddress) throws IOException, GeoIp2Exception{
		return location(ipAddress, locale);
	}

	@Override
	public Location location(String ipAddress, Locale locale) throws IOException, GeoIp2Exception{
		InetAddress addr;

		try{
			addr = InetAddress.getByName(ipAddress);
		}catch(UnknownHostException e){
			logger.error("InetAddress.getByName({}) failure: {}", ipAddress, e.getMessage());
			return null;
		}

		return location(addr, locale);
	}

	@Override
	public Location location(long ipAddress) throws IOException, GeoIp2Exception{
		return location(ipAddress, locale);
	}

	@Override
	public Location location(long ipAddress, Locale locale) throws IOException, GeoIp2Exception{
		return location(InetAddressUtils.long2InetAddress(ipAddress), locale);
	}

	@Override
	public Location location(InetAddress ipAddress) throws IOException, GeoIp2Exception{
		return location(ipAddress, locale);
	}

}
