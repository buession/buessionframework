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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip;

import com.buession.core.validator.Validate;
import com.buession.geoip.model.Country;
import com.buession.geoip.model.District;
import com.buession.geoip.model.Location;
import com.maxmind.db.NodeCache;
import com.maxmind.db.Reader;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/**
 * @author Yong.Teng
 */
public class CacheDatabaseResolver extends DatabaseResolver {

    private final static int CACHE_INITIAL_CAPACITY = 32;

    private final static Map<String, Country> COUNTRY_CACHE_DATA = new HashMap<>(CACHE_INITIAL_CAPACITY);

    private final static Map<String, District> DISTRICT_CACHE_DATA = new HashMap<>(CACHE_INITIAL_CAPACITY);

    private final static Map<String, Location> LOCATION_CACHE_DATA = new HashMap<>(CACHE_INITIAL_CAPACITY);

    private final static int CLEAN_CACHE_DIVISOR = 15;

    private final static Random RANDOM = new Random();

    public CacheDatabaseResolver(final String database) throws IOException{
        super(database);
    }

    public CacheDatabaseResolver(final String database, final NodeCache cache) throws IOException{
        super(database, cache);
    }

    public CacheDatabaseResolver(final String database, final Reader.FileMode fileMode) throws IOException{
        super(database, fileMode);
    }

    public CacheDatabaseResolver(final String database, final Reader.FileMode fileMode, final NodeCache cache) throws
            IOException{
        super(database, fileMode, cache);
    }

    public CacheDatabaseResolver(final File database) throws IOException{
        super(database);
    }

    public CacheDatabaseResolver(final File database, final NodeCache cache) throws IOException{
        super(database, cache);
    }

    public CacheDatabaseResolver(final File database, final Reader.FileMode fileMode) throws IOException{
        super(database, fileMode);
    }

    public CacheDatabaseResolver(final File database, final Reader.FileMode fileMode, final NodeCache cache) throws
            IOException{
        super(database, fileMode, cache);
    }

    public CacheDatabaseResolver(final Path database) throws IOException{
        super(database);
    }

    public CacheDatabaseResolver(final Path database, final NodeCache cache) throws IOException{
        super(database, cache);
    }

    public CacheDatabaseResolver(final Path database, final Reader.FileMode fileMode) throws IOException{
        super(database, fileMode);
    }

    public CacheDatabaseResolver(final Path database, final Reader.FileMode fileMode, final NodeCache cache) throws
            IOException{
        super(database, fileMode, cache);
    }

    public CacheDatabaseResolver(final InputStream source) throws IOException{
        super(source);
    }

    public CacheDatabaseResolver(final InputStream source, final NodeCache cache) throws IOException{
        super(source, cache);
    }

    public CacheDatabaseResolver(final InputStream source, final Reader.FileMode fileMode) throws IOException{
        super(source, fileMode);
    }

    public CacheDatabaseResolver(final InputStream source, final Reader.FileMode fileMode, final NodeCache cache)
            throws IOException{
        super(source, fileMode, cache);
    }

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
    @Override
    public Country country(InetAddress ipAddress, Locale locale) throws IOException, GeoIp2Exception{
        String address = ipAddress.getHostAddress();
        Country country = COUNTRY_CACHE_DATA.get(address);

        if(country == null){
            country = super.country(ipAddress, locale);

            if(country != null){
                COUNTRY_CACHE_DATA.put(address, country);
            }
        }else{
            cleanCache(COUNTRY_CACHE_DATA);
        }

        return country;
    }

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
    @Override
    public District district(InetAddress ipAddress, Locale locale) throws IOException, GeoIp2Exception{
        String address = ipAddress.getHostAddress();
        District district = DISTRICT_CACHE_DATA.get(address);

        if(district == null){
            district = super.district(ipAddress, locale);

            if(district != null){
                DISTRICT_CACHE_DATA.put(address, district);
            }
        }else{
            cleanCache(COUNTRY_CACHE_DATA);
        }

        return district;
    }

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
    @Override
    public Location location(InetAddress ipAddress, Locale locale) throws IOException, GeoIp2Exception{
        String address = ipAddress.getHostAddress();
        Location location = LOCATION_CACHE_DATA.get(address);

        if(location == null){
            location = super.location(ipAddress, locale);

            if(location != null){
                LOCATION_CACHE_DATA.put(address, location);
            }
        }else{
            cleanCache(LOCATION_CACHE_DATA);
        }

        return location;
    }

    private final static <O> void cleanCache(Map<String, O> cacheData){
        if(Validate.isEmpty(cacheData) == false){
            /**
             * 随机清理
             */
            if(RANDOM.nextInt(cacheData.size()) % CLEAN_CACHE_DIVISOR == 0){
                cacheData.clear();
            }
        }
    }

}
