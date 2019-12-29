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

import com.buession.redis.core.convert.Convert;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public interface MapNumberConvert<K, T extends Number> extends Convert<Map<K, Number>, Map<K, T>> {

    class MapNumberFloatConvert<K> implements MapNumberConvert<K, Float> {

        @Override
        public Map<K, Float> convert(final Map<K, Number> source){
            if(source == null){
                return null;
            }

            final Map<K, Float> result = new LinkedHashMap<>(source.size());

            source.forEach((key, value)->result.put(key, value.floatValue()));

            return result;
        }

        @Override
        public Map<K, Number> deconvert(final Map<K, Float> target){
            if(target == null){
                return null;
            }

            final Map<K, Number> result = new LinkedHashMap<>(target.size());

            target.forEach((key, value)->result.put(key, value.floatValue()));

            return result;
        }
    }

    class MapNumberDoubleConvert<K> implements MapNumberConvert<K, Double> {

        @Override
        public Map<K, Double> convert(final Map<K, Number> source){
            if(source == null){
                return null;
            }

            final Map<K, Double> result = new LinkedHashMap<>(source.size());

            source.forEach((key, value)->result.put(key, value.doubleValue()));

            return result;
        }

        @Override
        public Map<K, Number> deconvert(final Map<K, Double> target){
            if(target == null){
                return null;
            }

            final Map<K, Number> result = new LinkedHashMap<>(target.size());

            target.forEach((key, value)->result.put(key, value.doubleValue()));

            return result;
        }
    }

    class MapNumberIntegerConvert<K> implements MapNumberConvert<K, Integer> {

        @Override
        public Map<K, Integer> convert(final Map<K, Number> source){
            if(source == null){
                return null;
            }

            final Map<K, Integer> result = new LinkedHashMap<>(source.size());

            source.forEach((key, value)->result.put(key, value.intValue()));

            return result;
        }

        @Override
        public Map<K, Number> deconvert(final Map<K, Integer> target){
            if(target == null){
                return null;
            }

            final Map<K, Number> result = new LinkedHashMap<>(target.size());

            target.forEach((key, value)->result.put(key, value.intValue()));

            return result;
        }
    }

    class MapNumberLongConvert<K> implements MapNumberConvert<K, Long> {

        @Override
        public Map<K, Long> convert(final Map<K, Number> source){
            if(source == null){
                return null;
            }

            final Map<K, Long> result = new LinkedHashMap<>(source.size());

            source.forEach((key, value)->result.put(key, value.longValue()));

            return result;
        }

        @Override
        public Map<K, Number> deconvert(final Map<K, Long> target){
            if(target == null){
                return null;
            }

            final Map<K, Number> result = new LinkedHashMap<>(target.size());

            target.forEach((key, value)->result.put(key, value.longValue()));

            return result;
        }
    }

}
