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
 * | Copyright @ 2013-2017 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip;

import com.buession.geoip.model.District;
import com.buession.geoip.model.Country;
import com.buession.geoip.model.Location;
import com.maxmind.db.Metadata;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Locale;

/**
 * @author Yong.Teng
 */
public interface Resolver {

    /**
     * @param ipAddress
     *         String version of an IP address, i.e. "127.0.0.1" or IPv6 address, i.e. "::127.0.0.1".
     *
     * @return A Country model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    Country country(String ipAddress) throws IOException, GeoIp2Exception;

    /**
     * @param ipAddress
     *         String version of an IP address, i.e. "127.0.0.1" or IPv6 address, i.e. "::127.0.0.1".
     * @param locale
     *         The locale for which to retrieve the display country name.
     *
     * @return A Country model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    Country country(String ipAddress, Locale locale) throws IOException, GeoIp2Exception;

    /**
     * @param ipAddress
     *         the IP address in long format.
     *
     * @return A Country model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    Country country(long ipAddress) throws IOException, GeoIp2Exception;

    /**
     * @param ipAddress
     *         the IP address in long format.
     * @param locale
     *         The locale for which to retrieve the display country name.
     *
     * @return A Country model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    Country country(long ipAddress, Locale locale) throws IOException, GeoIp2Exception;

    /**
     * @param ipAddress
     *         the IP address as InetAddress or Inet6Address.
     *
     * @return A Country model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    Country country(InetAddress ipAddress) throws IOException, GeoIp2Exception;

    /**
     * @param ipAddress
     *         the IP address as InetAddress or Inet6Address.
     * @param locale
     *         The locale for which to retrieve the display country name.
     *
     * @return A Country model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    Country country(InetAddress ipAddress, Locale locale) throws IOException, GeoIp2Exception;

    /**
     * @param ipAddress
     *         String version of an IP address, i.e. "127.0.0.1" or IPv6 address, i.e. "::127.0.0.1".
     *
     * @return A District model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    District district(String ipAddress) throws IOException, GeoIp2Exception;

    /**
     * @param ipAddress
     *         String version of an IP address, i.e. "127.0.0.1" or IPv6 address, i.e. "::127.0.0.1".
     * @param locale
     *         The locale for which to retrieve the display city name.
     *
     * @return A District model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    District district(String ipAddress, Locale locale) throws IOException, GeoIp2Exception;

    /**
     * @param ipAddress
     *         the IP address in long format.
     *
     * @return A District model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    District district(long ipAddress) throws IOException, GeoIp2Exception;

    /**
     * @param ipAddress
     *         the IP address in long format.
     * @param locale
     *         The locale for which to retrieve the display city name.
     *
     * @return A District model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    District district(long ipAddress, Locale locale) throws IOException, GeoIp2Exception;

    /**
     * @param ipAddress
     *         the IP address as Inet4Address or Inet6Address.
     *
     * @return A District model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    District district(InetAddress ipAddress) throws IOException, GeoIp2Exception;

    /**
     * @param ipAddress
     *         the IP address as Inet4Address or Inet6Address.
     * @param locale
     *         The locale for which to retrieve the display city name.
     *
     * @return A District model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    District district(InetAddress ipAddress, Locale locale) throws IOException, GeoIp2Exception;

    /**
     * @param ipAddress
     *         String version of an IP address, i.e. "127.0.0.1" or IPv6 address, i.e. "::127.0.0.1".
     *
     * @return A Location model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    Location location(String ipAddress) throws IOException, GeoIp2Exception;

    /**
     * @param ipAddress
     *         String version of an IP address, i.e. "127.0.0.1" or IPv6 address, i.e. "::127.0.0.1".
     * @param locale
     *         The locale for which to retrieve the display name.
     *
     * @return A Location model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    Location location(String ipAddress, Locale locale) throws IOException, GeoIp2Exception;

    /**
     * @param ipAddress
     *         the IP address in long format.
     *
     * @return A Location model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    Location location(long ipAddress) throws IOException, GeoIp2Exception;

    /**
     * @param ipAddress
     *         the IP address in long format.
     * @param locale
     *         The locale for which to retrieve the display name.
     *
     * @return A Location model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    Location location(long ipAddress, Locale locale) throws IOException, GeoIp2Exception;

    /**
     * @param ipAddress
     *         the IP address as Inet4Address or Inet6Address.
     *
     * @return A Location model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    Location location(InetAddress ipAddress) throws IOException, GeoIp2Exception;

    /**
     * @param ipAddress
     *         the IP address as Inet4Address or Inet6Address.
     * @param locale
     *         The locale for which to retrieve the display name.
     *
     * @return A Location model for the requested IP address.
     *
     * @throws IOException
     *         if there is an IO error
     * @throws GeoIp2Exception
     *         if there is an error looking up the IP
     */
    Location location(InetAddress ipAddress, Locale locale) throws IOException, GeoIp2Exception;

    Metadata getMetadata();

    void close() throws IOException;

}
