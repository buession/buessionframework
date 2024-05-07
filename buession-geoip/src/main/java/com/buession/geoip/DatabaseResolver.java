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
 * | Copyright @ 2013-2024 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip;

import com.buession.core.utils.Assert;
import com.buession.geoip.converter.CityConverter;
import com.buession.geoip.converter.ContinentConverter;
import com.buession.geoip.converter.CountryConverter;
import com.buession.geoip.converter.TraitsConverter;
import com.buession.geoip.model.District;
import com.buession.geoip.model.Continent;
import com.buession.geoip.model.Country;
import com.buession.geoip.model.Geo;
import com.buession.geoip.model.Location;
import com.buession.geoip.model.Traits;
import com.maxmind.db.Reader;
import com.maxmind.db.Metadata;
import com.maxmind.db.NodeCache;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.AsnResponse;
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
 * 默认 Maxmind Geoip 解析器
 *
 * @author Yong.Teng
 */
public class DatabaseResolver extends AbstractResolver {

	private final DatabaseReader reader;

	private DatabaseReader asnReader;

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件路径
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public DatabaseResolver(final String database) throws IOException {
		this(database == null ? null : new File(database));
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件路径
	 * @param cache
	 * 		缓存模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public DatabaseResolver(final String database, final NodeCache cache) throws IOException {
		this(database == null ? null : new File(database), cache);
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件路径
	 * @param fileMode
	 * 		文件模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public DatabaseResolver(final String database, final Reader.FileMode fileMode) throws IOException {
		this(database == null ? null : new File(database), fileMode);
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件路径
	 * @param fileMode
	 * 		文件模式
	 * @param cache
	 * 		缓存模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public DatabaseResolver(final String database, final Reader.FileMode fileMode, final NodeCache cache)
			throws IOException {
		this(database == null ? null : new File(database), fileMode, cache);
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件文件
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public DatabaseResolver(final File database) throws IOException {
		Assert.isNull(database, "Database could not be null.");
		this.reader = new DatabaseReader.Builder(database).build();
		this.asnReader = getDefaultAsnReaderBuilder().build();
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件
	 * @param cache
	 * 		缓存模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public DatabaseResolver(final File database, final NodeCache cache) throws IOException {
		Assert.isNull(database, "Database could not be null.");
		this.reader = new DatabaseReader.Builder(database).withCache(cache).build();
		this.asnReader = getDefaultAsnReaderBuilder().withCache(cache).build();
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件
	 * @param fileMode
	 * 		文件模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public DatabaseResolver(final File database, final Reader.FileMode fileMode) throws IOException {
		Assert.isNull(database, "Database could not be null.");
		this.reader = new DatabaseReader.Builder(database).fileMode(fileMode).build();
		this.asnReader = getDefaultAsnReaderBuilder().fileMode(fileMode).build();
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件
	 * @param fileMode
	 * 		文件模式
	 * @param cache
	 * 		缓存模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public DatabaseResolver(final File database, final Reader.FileMode fileMode, final NodeCache cache)
			throws IOException {
		Assert.isNull(database, "Database could not be null.");
		this.reader = new DatabaseReader.Builder(database).fileMode(fileMode).withCache(cache).build();
		this.asnReader = getDefaultAsnReaderBuilder().fileMode(fileMode).withCache(cache)
				.build();
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件路径
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public DatabaseResolver(final Path database) throws IOException {
		this(database == null ? null : database.toFile());
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件路径
	 * @param cache
	 * 		缓存模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public DatabaseResolver(final Path database, final NodeCache cache) throws IOException {
		this(database == null ? null : database.toFile(), cache);
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件路径
	 * @param fileMode
	 * 		文件模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public DatabaseResolver(final Path database, final Reader.FileMode fileMode) throws IOException {
		this(database == null ? null : database.toFile(), fileMode);
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件路径
	 * @param fileMode
	 * 		文件模式
	 * @param cache
	 * 		缓存模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public DatabaseResolver(final Path database, final Reader.FileMode fileMode, final NodeCache cache)
			throws IOException {
		this(database == null ? null : database.toFile(), fileMode, cache);
	}

	/**
	 * 构造函数
	 *
	 * @param source
	 * 		IP 库文件流
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public DatabaseResolver(final InputStream source) throws IOException {
		Assert.isNull(source, "Database stream could not be null.");
		this.reader = new DatabaseReader.Builder(source).build();
		this.asnReader = getDefaultAsnReaderBuilder().build();
	}

	/**
	 * 构造函数
	 *
	 * @param source
	 * 		IP 库文件流
	 * @param cache
	 * 		缓存模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public DatabaseResolver(final InputStream source, final NodeCache cache) throws IOException {
		Assert.isNull(source, "Database stream could not be null.");
		this.reader = new DatabaseReader.Builder(source).withCache(cache).build();
		this.asnReader = getDefaultAsnReaderBuilder().withCache(cache).build();
	}

	/**
	 * 构造函数
	 *
	 * @param source
	 * 		IP 库文件流
	 * @param fileMode
	 * 		文件模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public DatabaseResolver(final InputStream source, final Reader.FileMode fileMode) throws IOException {
		Assert.isNull(source, "Database stream could not be null.");
		this.reader = new DatabaseReader.Builder(source).fileMode(fileMode).build();
		this.asnReader = getDefaultAsnReaderBuilder().fileMode(fileMode).build();
	}

	/**
	 * 构造函数
	 *
	 * @param source
	 * 		IP 库文件流
	 * @param fileMode
	 * 		文件模式
	 * @param cache
	 * 		缓存模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 */
	public DatabaseResolver(final InputStream source, final Reader.FileMode fileMode, final NodeCache cache)
			throws IOException {
		Assert.isNull(source, "Database stream could not be null.");
		this.reader = new DatabaseReader.Builder(source).fileMode(fileMode).withCache(cache).build();
		this.asnReader = getDefaultAsnReaderBuilder().fileMode(fileMode).withCache(cache).build();
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件路径
	 * @param asnDatabase
	 * 		ASN 库文件路径
	 *
	 * @throws IOException
	 * 		IO 错误
	 * @since 2.2.0
	 */
	public DatabaseResolver(final String database, final String asnDatabase) throws IOException {
		this(database == null ? null : new File(database), asnDatabase == null ? null : new File(asnDatabase));
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件路径
	 * @param asnDatabase
	 * 		ASN 库文件路径
	 * @param cache
	 * 		缓存模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 * @since 2.2.0
	 */
	public DatabaseResolver(final String database, final String asnDatabase, final NodeCache cache) throws IOException {
		this(database == null ? null : new File(database), asnDatabase == null ? null : new File(asnDatabase), cache);
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件路径
	 * @param asnDatabase
	 * 		ASN 库文件路径
	 * @param fileMode
	 * 		文件模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 * @since 2.2.0
	 */
	public DatabaseResolver(final String database, final String asnDatabase, final Reader.FileMode fileMode)
			throws IOException {
		this(database == null ? null : new File(database), asnDatabase == null ? null : new File(asnDatabase),
				fileMode);
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件路径
	 * @param asnDatabase
	 * 		ASN 库文件路径
	 * @param fileMode
	 * 		文件模式
	 * @param cache
	 * 		缓存模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 * @since 2.2.0
	 */
	public DatabaseResolver(final String database, final String asnDatabase, final Reader.FileMode fileMode,
							final NodeCache cache)
			throws IOException {
		this(database == null ? null : new File(database), asnDatabase == null ? null : new File(asnDatabase),
				fileMode, cache);
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件文件
	 * @param asnDatabase
	 * 		ASN 库文件
	 *
	 * @throws IOException
	 * 		IO 错误
	 * @since 2.2.0
	 */
	public DatabaseResolver(final File database, final File asnDatabase) throws IOException {
		Assert.isNull(database, "Database could not be null.");
		this.reader = new DatabaseReader.Builder(database).build();
		this.asnReader = new DatabaseReader.Builder(asnDatabase).build();
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件
	 * @param asnDatabase
	 * 		ASN 库文件
	 * @param cache
	 * 		缓存模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 * @since 2.2.0
	 */
	public DatabaseResolver(final File database, final File asnDatabase, final NodeCache cache) throws IOException {
		Assert.isNull(database, "Database could not be null.");
		Assert.isNull(asnDatabase, "ASN database could not be null.");
		this.reader = new DatabaseReader.Builder(database).withCache(cache).build();
		this.asnReader = new DatabaseReader.Builder(asnDatabase).withCache(cache).build();
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件
	 * @param asnDatabase
	 * 		ASN 库文件
	 * @param fileMode
	 * 		文件模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 * @since 2.2.0
	 */
	public DatabaseResolver(final File database, final File asnDatabase, final Reader.FileMode fileMode)
			throws IOException {
		Assert.isNull(database, "Database could not be null.");
		Assert.isNull(asnDatabase, "ASN database could not be null.");
		this.reader = new DatabaseReader.Builder(database).fileMode(fileMode).build();
		this.asnReader = new DatabaseReader.Builder(asnDatabase).fileMode(fileMode).build();
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件
	 * @param asnDatabase
	 * 		ASN 库文件
	 * @param fileMode
	 * 		文件模式
	 * @param cache
	 * 		缓存模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 * @since 2.2.0
	 */
	public DatabaseResolver(final File database, final File asnDatabase, final Reader.FileMode fileMode,
							final NodeCache cache)
			throws IOException {
		Assert.isNull(database, "Database could not be null.");
		Assert.isNull(asnDatabase, "ASN database could not be null.");
		this.reader = new DatabaseReader.Builder(database).fileMode(fileMode).withCache(cache).build();
		this.asnReader = new DatabaseReader.Builder(asnDatabase).fileMode(fileMode).withCache(cache).build();
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件路径
	 * @param asnDatabase
	 * 		ASN 库文件路径
	 *
	 * @throws IOException
	 * 		IO 错误
	 * @since 2.2.0
	 */
	public DatabaseResolver(final Path database, final Path asnDatabase) throws IOException {
		this(database == null ? null : database.toFile(), asnDatabase == null ? null : asnDatabase.toFile());
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件路径
	 * @param asnDatabase
	 * 		ASN 库文件路径
	 * @param cache
	 * 		缓存模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 * @since 2.2.0
	 */
	public DatabaseResolver(final Path database, final Path asnDatabase, final NodeCache cache) throws IOException {
		this(database == null ? null : database.toFile(), asnDatabase == null ? null : asnDatabase.toFile(), cache);
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件路径
	 * @param asnDatabase
	 * 		ASN 库文件路径
	 * @param fileMode
	 * 		文件模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 * @since 2.2.0
	 */
	public DatabaseResolver(final Path database, final Path asnDatabase, final Reader.FileMode fileMode)
			throws IOException {
		this(database == null ? null : database.toFile(), asnDatabase == null ? null : asnDatabase.toFile(), fileMode);
	}

	/**
	 * 构造函数
	 *
	 * @param database
	 * 		IP 库文件路径
	 * @param asnDatabase
	 * 		ASN 库文件路径
	 * @param fileMode
	 * 		文件模式
	 * @param cache
	 * 		缓存模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 * @since 2.2.0
	 */
	public DatabaseResolver(final Path database, final Path asnDatabase, final Reader.FileMode fileMode,
							final NodeCache cache)
			throws IOException {
		this(database == null ? null : database.toFile(), asnDatabase == null ? null : asnDatabase.toFile(), fileMode,
				cache);
	}

	/**
	 * 构造函数
	 *
	 * @param source
	 * 		IP 库文件流
	 * @param asnSource
	 * 		ASN 库文件流
	 *
	 * @throws IOException
	 * 		IO 错误
	 * @since 2.2.0
	 */
	public DatabaseResolver(final InputStream source, final InputStream asnSource) throws IOException {
		this(source);
		Assert.isNull(asnSource, "ASN database stream could not be null.");
		this.asnReader = new DatabaseReader.Builder(asnSource).build();
	}

	/**
	 * 构造函数
	 *
	 * @param source
	 * 		IP 库文件流
	 * @param asnSource
	 * 		ASN 库文件流
	 * @param cache
	 * 		缓存模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 * @since 2.2.0
	 */
	public DatabaseResolver(final InputStream source, final InputStream asnSource, final NodeCache cache)
			throws IOException {
		this(source, cache);
		Assert.isNull(asnSource, "ASN database stream could not be null.");
		this.asnReader = new DatabaseReader.Builder(asnSource).withCache(cache).build();
	}

	/**
	 * 构造函数
	 *
	 * @param source
	 * 		IP 库文件流
	 * @param asnSource
	 * 		ASN 库文件流
	 * @param fileMode
	 * 		文件模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 * @since 2.2.0
	 */
	public DatabaseResolver(final InputStream source, final InputStream asnSource, final Reader.FileMode fileMode)
			throws IOException {
		this(source, fileMode);
		Assert.isNull(asnSource, "ASN database stream could not be null.");
		this.asnReader = new DatabaseReader.Builder(asnSource).fileMode(fileMode).build();
	}

	/**
	 * 构造函数
	 *
	 * @param source
	 * 		IP 库文件流
	 * @param asnSource
	 * 		ASN 库文件流
	 * @param fileMode
	 * 		文件模式
	 * @param cache
	 * 		缓存模式
	 *
	 * @throws IOException
	 * 		IO 错误
	 * @since 2.2.0
	 */
	public DatabaseResolver(final InputStream source, final InputStream asnSource, final Reader.FileMode fileMode,
							final NodeCache cache)
			throws IOException {
		this(source, fileMode, cache);
		Assert.isNull(asnSource, "ASN database stream could not be null.");
		this.asnReader = new DatabaseReader.Builder(asnSource).fileMode(fileMode).withCache(cache).build();
	}

	@Override
	public Country country(InetAddress ipAddress, Locale locale) throws IOException, GeoIp2Exception {
		final CountryConverter countryConverter = new CountryConverter();
		final CountryResponse response = reader.country(ipAddress);

		return countryConverter.converter(response.getCountry(), response, locale);
	}

	@Override
	public District district(InetAddress ipAddress, Locale locale) throws IOException, GeoIp2Exception {
		final CityConverter cityConverter = new CityConverter();
		final CityResponse response = reader.city(ipAddress);

		return cityConverter.converter(response.getCity(), response, locale);
	}

	@Override
	public Location location(InetAddress ipAddress, Locale locale) throws IOException, GeoIp2Exception {
		final CountryConverter countryConverter = new CountryConverter();
		final CityConverter cityConverter = new CityConverter();
		final ContinentConverter continentConverter = new ContinentConverter();
		final TraitsConverter traitsConverter = new TraitsConverter();

		final CityResponse response = reader.city(ipAddress);
		final AsnResponse asnResponse = asnReader == null ? null : asnReader.asn(ipAddress);
		final com.maxmind.geoip2.record.Location location = response.getLocation();

		traitsConverter.setAsnResponse(asnResponse);

		final Continent continent = continentConverter.converter(response.getContinent(), locale);
		final Country country = countryConverter.converter(response.getCountry(), locale);
		final District district = cityConverter.converter(response.getCity(), response, locale);
		final Traits traits = traitsConverter.converter(response.getTraits(), locale);
		final Geo geo = new Geo(location.getLatitude(), location.getLongitude(), location.getAccuracyRadius());
		final TimeZone timeZone = location.getTimeZone() == null ? null : TimeZone.getTimeZone(location.getTimeZone());

		return new Location(continent, country, district, traits, geo, timeZone);
	}

	@Override
	public Metadata getMetadata() {
		return reader.getMetadata();
	}

	@Override
	public void close() throws IOException {
		if(reader != null){
			reader.close();
		}
	}

	protected static DatabaseReader.Builder getDefaultAsnReaderBuilder() {
		return new DatabaseReader.Builder(DatabaseResolver.class.getResourceAsStream(DEFAULT_ASN_DB));
	}

}
