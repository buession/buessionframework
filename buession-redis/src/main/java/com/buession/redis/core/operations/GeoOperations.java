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
package com.buession.redis.core.operations;

import com.buession.core.Geo;
import com.buession.redis.core.command.GeoCommands;

/**
 * 地理位置运算
 *
 * <p>详情说明 <a href="http://redisdoc.com/geo/index.html" target="_blank">http://redisdoc.com/geo/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface GeoOperations extends GeoCommands, RedisOperations {

    /**
     * 从键里面返回所有给定位置元素的位置（经度和纬度）
     *
     * @param key
     *         Key
     * @param member
     *         名字
     *
     * @return 经纬度
     */
    Geo geoPos(final String key, final String member);

    /**
     * 从键里面返回所有给定位置元素的位置（经度和纬度）
     *
     * @param key
     *         Key
     * @param member
     *         名字
     *
     * @return 经纬度
     */
    Geo geoPos(final byte[] key, final byte[] member);

    /**
     * 获取位置元素的 <a href="https://en.wikipedia.org/wiki/Geohashh" target="_blank">Geohash</a> 表示
     *
     * @param key
     *         Key
     * @param member
     *         位置元素
     *
     * @return Geohash 数组
     */
    String geoHash(final String key, final String member);

    /**
     * 获取位置元素的 <a href="https://en.wikipedia.org/wiki/Geohashh" target="_blank">Geohash</a> 表示
     *
     * @param key
     *         Key
     * @param member
     *         位置元素
     *
     * @return Geohash 数组
     */
    byte[] geoHash(final byte[] key, final byte[] member);

}
