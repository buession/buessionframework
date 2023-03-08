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
 * | Copyright @ 2013-2023 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip;

import com.buession.geoip.model.District;
import com.buession.geoip.model.Country;
import com.buession.geoip.model.Location;
import com.buession.net.utils.InetAddressUtils;
import com.maxmind.db.Metadata;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Locale;

/**
 * Maxmind Geoip 解析器接口
 *
 * @author Yong.Teng
 */
public interface Resolver extends Closeable {

	String DEFAULT_COUNTRY_DB = "/maxmind/Country.mmdb";

	String DEFAULT_CITY_DB = "/maxmind/City.mmdb";

	String DEFAULT_ASN_DB = "/maxmind/ASN.mmdb";

	/**
	 * 根据 IP 地址返回国家数据
	 *
	 * @param ipAddress
	 * 		IP 地址
	 *
	 * @return 根据 IP 地址获取的国家数据
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	default Country country(String ipAddress) throws IOException, GeoIp2Exception{
		return country(ipAddress, Locale.getDefault());
	}

	/**
	 * 根据 IP 地址返回国家数据
	 *
	 * @param ipAddress
	 * 		IP 地址
	 * @param locale
	 * 		The locale for which to retrieve the display country name.
	 *
	 * @return 根据 IP 地址获取的国家数据
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	Country country(String ipAddress, Locale locale) throws IOException, GeoIp2Exception;

	/**
	 * 根据数字型 IP 地址返回国家数据
	 *
	 * @param ipAddress
	 * 		数字型 IP 地址
	 *
	 * @return 根据 IP 地址获取的国家数据
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	default Country country(long ipAddress) throws IOException, GeoIp2Exception{
		return country(ipAddress, Locale.getDefault());
	}

	/**
	 * 根据数字型 IP 地址返回国家数据
	 *
	 * @param ipAddress
	 * 		数字型 IP 地址
	 * @param locale
	 * 		The locale for which to retrieve the display country name.
	 *
	 * @return 根据 IP 地址获取的国家数据
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	default Country country(long ipAddress, Locale locale) throws IOException, GeoIp2Exception{
		return country(InetAddressUtils.long2InetAddress(ipAddress), locale);
	}

	/**
	 * 根据 {@link InetAddress} 返回国家数据
	 *
	 * @param ipAddress
	 *        {@link InetAddress} 实例
	 *
	 * @return 根据 {@link InetAddress} 获取的国家数据
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	default Country country(InetAddress ipAddress) throws IOException, GeoIp2Exception{
		return country(ipAddress, Locale.getDefault());
	}

	/**
	 * 根据 {@link InetAddress} 返回国家数据
	 *
	 * @param ipAddress
	 *        {@link InetAddress} 实例
	 * @param locale
	 * 		The locale for which to retrieve the display country name.
	 *
	 * @return 根据 {@link InetAddress} 获取的国家数据
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	Country country(InetAddress ipAddress, Locale locale) throws IOException, GeoIp2Exception;

	/**
	 * 根据 IP 地址返回地区数据
	 *
	 * @param ipAddress
	 * 		IP 地址
	 *
	 * @return 根据 IP 地址获取的地区数据
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	default District district(String ipAddress) throws IOException, GeoIp2Exception{
		return district(ipAddress, Locale.getDefault());
	}

	/**
	 * 根据 IP 地址返回地区数据
	 *
	 * @param ipAddress
	 * 		IP 地址
	 * @param locale
	 * 		The locale for which to retrieve the display city name.
	 *
	 * @return 根据 IP 地址获取的地区数据
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	District district(String ipAddress, Locale locale) throws IOException, GeoIp2Exception;

	/**
	 * 根据数字型 IP 地址返回地区数据
	 *
	 * @param ipAddress
	 * 		数字型 IP 地址
	 *
	 * @return 根据 IP 地址获取的地区数据
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	default District district(long ipAddress) throws IOException, GeoIp2Exception{
		return district(ipAddress, Locale.getDefault());
	}

	/**
	 * 根据数字型 IP 地址返回地区数据
	 *
	 * @param ipAddress
	 * 		数字型 IP 地址
	 * @param locale
	 * 		The locale for which to retrieve the display city name.
	 *
	 * @return 根据 IP 地址获取的地区数据
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	default District district(long ipAddress, Locale locale) throws IOException, GeoIp2Exception{
		return district(InetAddressUtils.long2InetAddress(ipAddress), locale);
	}

	/**
	 * 根据 {@link InetAddress} 返回地区数据
	 *
	 * @param ipAddress
	 *        {@link InetAddress} 实例
	 *
	 * @return 根据 {@link InetAddress} 获取的地区数据
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	default District district(InetAddress ipAddress) throws IOException, GeoIp2Exception{
		return district(ipAddress, Locale.getDefault());
	}

	/**
	 * 根据 {@link InetAddress} 返回地区数据
	 *
	 * @param ipAddress
	 *        {@link InetAddress} 实例
	 * @param locale
	 * 		The locale for which to retrieve the display city name.
	 *
	 * @return 根据 {@link InetAddress} 获取的地区数据
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	District district(InetAddress ipAddress, Locale locale) throws IOException, GeoIp2Exception;

	/**
	 * 根据 IP 地址返回位置数据，包括：国家、城市、所属机构等等
	 *
	 * @param ipAddress
	 * 		IP 地址
	 *
	 * @return 根据 IP 地址获取的位置数据，包括：国家、城市、所属机构等
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	default Location location(String ipAddress) throws IOException, GeoIp2Exception{
		return location(ipAddress, Locale.getDefault());
	}

	/**
	 * 根据 IP 地址返回位置数据，包括：国家、城市、所属机构等等
	 *
	 * @param ipAddress
	 * 		IP 地址
	 * @param locale
	 * 		The locale for which to retrieve the display name.
	 *
	 * @return 根据 IP 地址获取的位置数据，包括：国家、城市、所属机构等
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	Location location(String ipAddress, Locale locale) throws IOException, GeoIp2Exception;

	/**
	 * 根据数值型 IP 地址返回位置数据，包括：国家、城市、所属机构等等
	 *
	 * @param ipAddress
	 * 		数值型 IP 地址
	 *
	 * @return 根据 IP 地址获取的位置数据，包括：国家、城市、所属机构等
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	default Location location(long ipAddress) throws IOException, GeoIp2Exception{
		return location(ipAddress, Locale.getDefault());
	}

	/**
	 * 根据数值型 IP 地址返回位置数据，包括：国家、城市、所属机构等等
	 *
	 * @param ipAddress
	 * 		数值型 IP 地址
	 * @param locale
	 * 		The locale for which to retrieve the display name.
	 *
	 * @return 根据 IP 地址获取的位置数据，包括：国家、城市、所属机构等
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	default Location location(long ipAddress, Locale locale) throws IOException, GeoIp2Exception{
		return location(InetAddressUtils.long2InetAddress(ipAddress), locale);
	}

	/**
	 * 根据 {@link InetAddress} 返回位置数据，包括：国家、城市、所属机构等等
	 *
	 * @param ipAddress
	 *        {@link InetAddress} 实例
	 *
	 * @return 根据 {@link InetAddress} 获取的位置数据，包括：国家、城市、所属机构等
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	default Location location(InetAddress ipAddress) throws IOException, GeoIp2Exception{
		return location(ipAddress, Locale.getDefault());
	}

	/**
	 * 根据 {@link InetAddress} 返回位置数据，包括：国家、城市、所属机构等等
	 *
	 * @param ipAddress
	 *        {@link InetAddress} 实例
	 * @param locale
	 * 		The locale for which to retrieve the display name.
	 *
	 * @return 根据 {@link InetAddress} 获取的位置数据，包括：国家、城市、所属机构等
	 *
	 * @throws IOException
	 * 		if there is an IO error
	 * @throws GeoIp2Exception
	 * 		if there is an error looking up the IP
	 */
	Location location(InetAddress ipAddress, Locale locale) throws IOException, GeoIp2Exception;

	Metadata getMetadata();

}
