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
import com.buession.redis.core.convert.Convert;
import redis.clients.jedis.GeoCoordinate;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class GeoConvert implements Convert<Geo, GeoCoordinate> {

    @Override
    public GeoCoordinate convert(Geo source){
        return source == null ? null : new GeoCoordinate(source.getLongitude(), source.getLatitude());
    }

    @Override
    public Geo deconvert(GeoCoordinate target){
        return target == null ? null : new Geo(target.getLongitude(), target.getLatitude());
    }

    public final static class GeoMapConvert<K> implements Convert<Map<K, Geo>, Map<K, GeoCoordinate>> {

        @Override
        public Map<K, GeoCoordinate> convert(Map<K, Geo> source){
            if(source == null){
                return null;
            }else{
                final Map<K, GeoCoordinate> result = new LinkedHashMap<>(source.size());

                source.forEach((key, value)->{
                    result.put(key, new GeoCoordinate(value.getLongitude(), value.getLatitude()));
                });

                return result;
            }
        }

        @Override
        public Map<K, Geo> deconvert(Map<K, GeoCoordinate> target){
            if(target == null){
                return null;
            }else{
                final Map<K, Geo> result = new LinkedHashMap<>();

                target.forEach((key, value)->{
                    result.put(key, new Geo(value.getLongitude(), value.getLatitude()));
                });

                return result;
            }
        }
    }

    public final static class ListMapConvert implements Convert<List<Geo>, List<GeoCoordinate>> {

        @Override
        public List<GeoCoordinate> convert(List<Geo> source){
            if(source == null){
                return null;
            }else{
                final List<GeoCoordinate> result = new ArrayList<>(source.size());

                for(Geo geo : source){
                    result.add(new GeoCoordinate(geo.getLongitude(), geo.getLatitude()));
                }

                return result;
            }
        }

        @Override
        public List<Geo> deconvert(List<GeoCoordinate> target){
            if(target == null){
                return null;
            }else{
                final List<Geo> result = new ArrayList<>(target.size());

                for(GeoCoordinate geoCoordinate : target){
                    result.add(new Geo(geoCoordinate.getLongitude(), geoCoordinate.getLatitude()));
                }

                return result;
            }
        }
    }

}
