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
package com.buession.geoip.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.TimeZone;

/**
 * IP 地理位置信息
 *
 * @author Yong.Teng
 */
public final class Location implements Serializable {

	private final static long serialVersionUID = 4865138617078561823L;

	/**
	 * IP 地址关联的洲
	 */
	private final Continent continent;

	/**
	 * IP 地址关联的国家
	 */
	private final Country country;

	/**
	 * IP 地址关联的城市
	 */
	private final District district;

	/**
	 * IP 地址关联的特征记录
	 */
	private final Traits traits;

	/**
	 * IP 地址关联的经纬度
	 */
	private final Geo geo;

	/**
	 * IP 地址关联的时区
	 */
	private final TimeZone timeZone;

	/**
	 * 构造函数
	 *
	 * @param continent
	 * 		IP 地址关联的洲
	 * @param country
	 * 		IP 地址关联的国家
	 * @param district
	 * 		IP 地址关联的城市
	 * @param traits
	 * 		IP 地址关联的特征记录
	 * @param geo
	 * 		IP 地址关联的经纬度
	 * @param timeZone
	 * 		IP 地址关联的时区
	 */
	public Location(final Continent continent, final Country country, final District district, final Traits traits,
					final Geo geo, final TimeZone timeZone){
		this.continent = continent;
		this.country = country;
		this.district = district;
		this.traits = traits;
		this.geo = geo;
		this.timeZone = timeZone;
	}

	/**
	 * 返回 IP 地址关联的洲
	 *
	 * @return IP 地址关联的洲
	 */
	public Continent getContinent(){
		return continent;
	}

	/**
	 * 返回 IP 地址关联的国家
	 *
	 * @return IP 地址关联的国家
	 */
	public Country getCountry(){
		return country;
	}

	/**
	 * 返回 IP 地址关联的城市
	 *
	 * @return IP 地址关联的城市
	 */
	public District getDistrict(){
		return district;
	}

	/**
	 * 返回 IP 地址关联的特征记录
	 *
	 * @return IP 地址关联的特征记录
	 */
	public Traits getTraits(){
		return traits;
	}

	/**
	 * 返回 IP 地址关联的经纬度
	 *
	 * @return IP 地址关联的经纬度
	 */
	public Geo getGeo(){
		return geo;
	}

	/**
	 * 返回 IP 地址关联的时区
	 *
	 * @return IP 地址关联的时区
	 */
	public TimeZone getTimeZone(){
		return timeZone;
	}

	@Override
	public int hashCode(){
		return Objects.hash(continent, country, district, traits, geo, timeZone);
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof Location){
			Location that = (Location) obj;
			return Objects.equals(continent, that.continent) && Objects.equals(country, that.country) &&
					Objects.equals(district, that.district) && Objects.equals(traits, that.traits) &&
					Objects.equals(geo, that.geo) && Objects.equals(timeZone, that.timeZone);
		}

		return false;
	}

	@Override
	public String toString(){
		return "Location{" + "continent=" + continent + ", country=" + country + ", district=" + district + ", " +
				"traits=" + traits + ", geo=" + geo + ", timeZone=" + timeZone + '}';
	}

}
