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
package com.buession.redis.jedis.sentinel;

import com.buession.lang.Status;
import com.buession.redis.RedisTemplate;
import com.buession.redis.User;
import com.buession.redis.jedis.AbstractJedisRedisTest;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class StringTest extends AbstractJedisRedisTest {

	@Test
	public void set(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisSentinelDataSource());

		User user = new User();
		user.setId(1);
		user.setUsername("test");

		Assert.assertSame(redisTemplate.setEx("a", "A", 5 * 60), Status.SUCCESS);
		Assert.assertSame(redisTemplate.setEx("user", user, 30), Status.SUCCESS);
	}

	@Test
	public void get(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisSentinelDataSource());

		Assert.assertEquals("A", redisTemplate.get("a"));
		System.out.println(redisTemplate.get("user"));
	}

	@Test
	public void getObject(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisSentinelDataSource());

		System.out.println(redisTemplate.getObject("user", User.class));
		System.out.println(redisTemplate.getObject("user".getBytes(StandardCharsets.UTF_8), User.class));
	}

}
