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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.lettuce;

import com.buession.lang.Geo;
import com.buession.redis.RedisTemplate;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.command.GeoCommands;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceGeoTest extends AbstractLettuceRedisTest {

	@Test
	public void geoAdd() {
		RedisTemplate redisTemplate = redisTemplate();
		Assertions.assertEquals(redisTemplate.geoAdd("beijing", "gugong", 116.405706, 39.921797), 1L);
		Assertions.assertEquals(redisTemplate.geoAdd("beijing", "tiantan", 116.416629, 39.885709), 1L);
	}

	@Test
	public void geoHash() {
		RedisTemplate redisTemplate = redisTemplate();
		List<String> result = redisTemplate.geoHash("beijing", "gugong", "tiantan");
		result.forEach(System.out::println);
	}

	@Test
	public void geoPos() {
		RedisTemplate redisTemplate = redisTemplate();
		List<Geo> result = redisTemplate.geoPos("beijing", "gugong", "tiantan");
		result.forEach(System.out::println);
	}

	@Test
	public void geoDist() {
		RedisTemplate redisTemplate = redisTemplate();
		Double result = redisTemplate.geoDist("beijing", "gugong", "tiantan");
		System.out.println(result);
	}

	@Test
	public void geoRadius() {
		RedisTemplate redisTemplate = redisTemplate();
		List<GeoRadius> result = redisTemplate.geoRadius("beijing", 116.405706, 39.921797, 5.0, GeoUnit.KM);
		result.forEach(System.out::println);
	}

	@Test
	public void geoRadiusWithadiusArgument() {
		RedisTemplate redisTemplate = redisTemplate();
		List<GeoRadius> result = redisTemplate.geoRadius("beijing", 116.405706, 39.921797, 5.0, GeoUnit.KM,
				GeoCommands.GeoRadiusArgument.Builder.create().withCoord().withDist().withHash().build());
		result.forEach(System.out::println);
	}

}
