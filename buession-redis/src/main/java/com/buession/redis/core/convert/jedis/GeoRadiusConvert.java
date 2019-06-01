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
package com.buession.redis.core.convert.jedis;

import com.buession.core.Geo;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.convert.Convert;
import redis.clients.jedis.GeoRadiusResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class GeoRadiusConvert implements Convert<GeoRadius, GeoRadiusResponse> {

    @Override
    public GeoRadiusResponse convert(final GeoRadius source){
        return null;
    }

    @Override
    public GeoRadius deconvert(final GeoRadiusResponse target){
        if(target == null){
            return null;
        }else{
            final GeoRadius geoRadius = new GeoRadius();

            geoRadius.setMember(target.getMember());
            geoRadius.setDistance(target.getDistance());
            geoRadius.setGeo(new Geo(target.getCoordinate().getLongitude(), target.getCoordinate().getLatitude()));

            return geoRadius;
        }
    }

    public final static class ListGeoRadiusConvert implements Convert<List<GeoRadius>, List<GeoRadiusResponse>> {

        @Override
        public List<GeoRadiusResponse> convert(final List<GeoRadius> source){
            return null;
        }

        @Override
        public List<GeoRadius> deconvert(final List<GeoRadiusResponse> target){
            if(target == null){
                return null;
            }else{
                final List<GeoRadius> result = new ArrayList<>();

                for(GeoRadiusResponse geoRadiusResponse : target){
                    final GeoRadius geoRadius = new GeoRadius();

                    geoRadius.setMember(geoRadiusResponse.getMember());
                    geoRadius.setDistance(geoRadiusResponse.getDistance());
                    geoRadius.setGeo(new Geo(geoRadiusResponse.getCoordinate().getLongitude(), geoRadiusResponse
                            .getCoordinate().getLatitude()));

                    result.add(geoRadius);
                }

                return result;
            }
        }
    }
}
