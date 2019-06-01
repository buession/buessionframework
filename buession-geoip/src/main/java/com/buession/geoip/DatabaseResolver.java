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
 * | Copyright @ 2013-2019 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip;

import com.buession.core.utils.Assert;
import com.buession.geoip.model.District;
import com.buession.geoip.model.Continent;
import com.buession.geoip.model.Country;
import com.buession.geoip.model.Geo;
import com.buession.geoip.model.Location;
import com.buession.geoip.model.Traits;
import com.buession.geoip.utils.GlobalUtils;
import com.maxmind.db.Reader;
import com.maxmind.db.Metadata;
import com.maxmind.db.NodeCache;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.file.Path;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author Yong.Teng
 */
public class DatabaseResolver extends AbstractResolver {

    private final DatabaseReader reader;

    public DatabaseResolver(final String database) throws IOException{
        this(database == null ? null : new File(database));
    }

    public DatabaseResolver(final String database, final NodeCache cache) throws IOException{
        this(database == null ? null : new File(database), cache);
    }

    public DatabaseResolver(final String database, final Reader.FileMode fileMode) throws IOException{
        this(database == null ? null : new File(database), fileMode);
    }

    public DatabaseResolver(final String database, final Reader.FileMode fileMode, final NodeCache cache) throws
            IOException{
        this(database == null ? null : new File(database), fileMode, cache);
    }

    public DatabaseResolver(final File database) throws IOException{
        Assert.isNull(database, "Database could not be null.");
        this.reader = databaseReaderBuilder(database).build();
    }

    public DatabaseResolver(final File database, final NodeCache cache) throws IOException{
        Assert.isNull(database, "Database could not be null.");
        this.reader = databaseReaderBuilder(database).withCache(cache).build();
    }

    public DatabaseResolver(final File database, final Reader.FileMode fileMode) throws IOException{
        Assert.isNull(database, "Database could not be null.");
        this.reader = databaseReaderBuilder(database).fileMode(fileMode).build();
    }

    public DatabaseResolver(final File database, final Reader.FileMode fileMode, final NodeCache cache) throws
            IOException{
        Assert.isNull(database, "Database could not be null.");
        this.reader = databaseReaderBuilder(database).fileMode(fileMode).withCache(cache).build();
    }

    public DatabaseResolver(final Path database) throws IOException{
        this(null == null ? null : database.toFile());
    }

    public DatabaseResolver(final Path database, final NodeCache cache) throws IOException{
        this(database == null ? null : database.toFile(), cache);
    }

    public DatabaseResolver(final Path database, final Reader.FileMode fileMode) throws IOException{
        this(database == null ? null : database.toFile(), fileMode);
    }

    public DatabaseResolver(final Path database, final Reader.FileMode fileMode, final NodeCache cache) throws
            IOException{
        this(database == null ? null : database.toFile(), fileMode, cache);
    }

    public DatabaseResolver(final InputStream source) throws IOException{
        Assert.isNull(source, "Database stream could not be null.");
        this.reader = databaseReaderBuilder(source).build();
    }

    public DatabaseResolver(final InputStream source, final NodeCache cache) throws IOException{
        Assert.isNull(source, "Database stream could not be null.");
        this.reader = databaseReaderBuilder(source).withCache(cache).build();
    }

    public DatabaseResolver(final InputStream source, final Reader.FileMode fileMode) throws IOException{
        Assert.isNull(source, "Database stream could not be null.");
        this.reader = databaseReaderBuilder(source).fileMode(fileMode).build();
    }

    public DatabaseResolver(final InputStream source, final Reader.FileMode fileMode, final NodeCache cache) throws
            IOException{
        Assert.isNull(source, "Database stream could not be null.");
        this.reader = databaseReaderBuilder(source).fileMode(fileMode).withCache(cache).build();
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
        CountryResponse response = reader.country(ipAddress);
        return countryConverter.converter(response.getCountry(), response, locale);
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
        CityResponse response = reader.city(ipAddress);
        return cityConverter.converter(response.getCity(), response, locale);
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
        CityResponse response = reader.city(ipAddress);
        com.maxmind.geoip2.record.Location location = response.getLocation();

        final Continent continent = continentConverter.converter(response.getContinent(), locale);
        final Country country = countryConverter.converter(response.getCountry(), locale);
        final District district = cityConverter.converter(response.getCity(), response, locale);
        final Traits traits = traitsConverter.converter(response.getTraits(), locale);
        final Geo geo = new Geo(location.getLatitude(), location.getLongitude(), GlobalUtils.getInteger(location
                .getAccuracyRadius()));
        final TimeZone timeZone = TimeZone.getTimeZone(location.getTimeZone());

        return new Location(continent, country, district, traits, geo, timeZone);
    }

    @Override
    public Metadata getMetadata(){
        return reader.getMetadata();
    }

    @Override
    public void close() throws IOException{
        if(reader != null){
            reader.close();
        }
    }

    private final static DatabaseReader.Builder databaseReaderBuilder(final File database){
        return new DatabaseReader.Builder(database);
    }

    private final static DatabaseReader.Builder databaseReaderBuilder(final InputStream source){
        return new DatabaseReader.Builder(source);
    }

}
