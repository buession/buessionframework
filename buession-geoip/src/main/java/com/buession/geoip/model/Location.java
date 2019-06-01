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
package com.buession.geoip.model;

import java.io.Serializable;
import java.util.TimeZone;

/**
 * @author Yong.Teng
 */
public final class Location implements Serializable {

    private static final long serialVersionUID = 4865138617078561823L;

    private final Continent continent;

    private final Country country;

    private final District district;

    private final Traits traits;

    private final Geo geo;

    private final TimeZone timeZone;

    public Location(final Continent continent, final Country country, final District district, final Traits traits,
                    final Geo geo, final TimeZone timeZone){
        this.continent = continent;
        this.country = country;
        this.district = district;
        this.traits = traits;
        this.geo = geo;
        this.timeZone = timeZone;
    }

    public Continent getContinent(){
        return continent;
    }

    public Country getCountry(){
        return country;
    }

    public District getDistrict(){
        return district;
    }

    public Traits getTraits(){
        return traits;
    }

    public Geo getGeo(){
        return geo;
    }

    public TimeZone getTimeZone(){
        return timeZone;
    }

    @Override
    public String toString(){
        return "Location{" + "continent=" + continent + ", country=" + country + ", district=" + district + ", " +
                "traits=" + traits + ", geo=" + geo + ", timeZone=" + timeZone + '}';
    }
}
