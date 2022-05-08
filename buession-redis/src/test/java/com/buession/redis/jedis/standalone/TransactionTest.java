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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.jedis.standalone;

import com.buession.lang.Geo;
import com.buession.redis.RedisTemplate;
import com.buession.redis.User;
import com.buession.redis.jedis.AbstractJedisRedisTest;
import org.junit.Test;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class TransactionTest extends AbstractJedisRedisTest {

	@Test
	public void exec(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisDataSource());

		redisTemplate.multi();
		redisTemplate.bitCount("str");
		redisTemplate.get("user");
		redisTemplate.get("a");
		redisTemplate.getObject("user", User.class);
		redisTemplate.zAdd("transaction", 1.0, "1");
		redisTemplate.geoAdd("sichuan", "zigong", new Geo(53.2D, 44D));
		System.out.println(redisTemplate.exec());
	}

	@Test
	public void discard(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisDataSource());

		redisTemplate.multi();
		redisTemplate.zAdd("transaction", 3.0, "3");
		redisTemplate.geoAdd("sichuan", "zigong", new Geo(53.2, 44));
		redisTemplate.discard();
	}

}
