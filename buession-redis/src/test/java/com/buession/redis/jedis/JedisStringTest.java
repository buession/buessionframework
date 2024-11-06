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
package com.buession.redis.jedis;

import com.buession.lang.Status;
import com.buession.redis.RedisTemplate;
import com.buession.redis.core.Options;
import com.buession.redis.core.ScanResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Yong.Teng
 * @since 3.0.0
 */
public class JedisStringTest extends AbstractJedisRedisTest {

	@Test
	public void set() {
		RedisTemplate redisTemplate = redisTemplate();
		Assertions.assertTrue(redisTemplate.set("jedis", new Options()) == Status.SUCCESS);
	}

	@Test
	public void get() {
		RedisTemplate redisTemplate = redisTemplate();
		String result = redisTemplate.get("jedis");
		System.out.println(result);
	}

	@Test
	public void getObject() {
		RedisTemplate redisTemplate = redisTemplate();
		Options result = redisTemplate.getObject("jedis", Options.class);
		System.out.println(result);
	}

}
