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
import com.buession.redis.core.ScanResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Yong.Teng
 * @since 3.0.0
 */
public class JedisKeyTest extends AbstractJedisRedisTest {

	@Test
	public void exists() {
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.exists("a"));
	}

	@Test
	public void scan() {
		RedisTemplate redisTemplate = redisTemplate();
		ScanResult<String> result = redisTemplate.scan(0L);
		System.out.println(result.getCursorAsString());
		result.getResults().forEach(System.out::println);
	}

	@Test
	public void testSentinel() {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(10);
		jedisPoolConfig.setMaxIdle(5);
		jedisPoolConfig.setMinIdle(5);
		// 哨兵信息
		Set<String> sentinels = new HashSet<>(Arrays.asList("192.168.0.231:26371",
				"192.168.0.231:26372", "192.168.0.231:26373"));
		// 创建连接池
		JedisSentinelPool pool = new JedisSentinelPool("redis-master", sentinels, jedisPoolConfig, "rds_PWD");
		// 获取客户端
		Jedis jedis = pool.getResource();
		// 执行两个命令
		jedis.set("mykey", "myvalue");
		String value = jedis.get("mykey");
		System.out.println(value);
	}

}
