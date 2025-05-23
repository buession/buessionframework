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

import com.buession.redis.RedisTemplate;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author Yong.Teng
 * @since 3.0.0
 */
public class JedisTransactionTest extends AbstractJedisRedisTest {

	@Test
	public void exec() {
		RedisTemplate redisTemplate = redisTemplate();
		redisTemplate.multi();
		redisTemplate.set("a", "Jedis");
		redisTemplate.exists("a");
		redisTemplate.expire("a", 60);
		String ret = redisTemplate.get("a");
		//redisTemplate.role();
		List<Object> result = redisTemplate.exec();
		System.out.println(result);
	}

	@Test
	public void discard() {
		RedisTemplate redisTemplate = redisTemplate();
		redisTemplate.multi();
		redisTemplate.set("b", "B");
		redisTemplate.discard();
	}

}
