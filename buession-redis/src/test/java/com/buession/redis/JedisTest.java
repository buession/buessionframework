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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis;

import com.buession.lang.Status;
import com.buession.redis.core.Info;
import com.buession.redis.pipeline.Pipeline;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class JedisClientTest extends AbstractJedisRedisTest {

	@Test
	public void ping(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());

		Assert.assertEquals(Status.SUCCESS, redisTemplate.ping());
	}

	@Test
	public void select(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());

		Assert.assertEquals(Status.SUCCESS, redisTemplate.select(61));
	}

	@Test
	public void echo(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());

		System.out.println(redisTemplate.echo("echo"));
		System.out.println(redisTemplate.echo("echo".getBytes(StandardCharsets.UTF_8)));
	}

	@Test
	public void set(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());

		Assert.assertEquals(Status.SUCCESS, redisTemplate.set("k", "value"));
		Assert.assertEquals(Status.SUCCESS, redisTemplate.set("l", Long.MAX_VALUE));
	}

	@Test
	public void hset(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());
		Assert.assertEquals(Status.SUCCESS, redisTemplate.hSet("member", "2", new User(2, "一")));
	}

	@Test
	public void get(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());

		Assert.assertEquals("value", redisTemplate.get("str"));
		Assert.assertEquals(Long.toString(Long.MAX_VALUE), redisTemplate.get("long"));
		Assert.assertEquals(new Long(Long.MAX_VALUE), redisTemplate.getObject("long", Long.class));
	}

	@Test
	public void hGet(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());

		String result = redisTemplate.hGet("member", "2");
		System.out.println(result);
	}

	@Test
	public void hMGet(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());

		List<String> result = redisTemplate.hMGet("user", "1");

		Assert.assertNotNull(result.get(0));
		Assert.assertNull(result.get(1));
	}

	@Test
	public void hMGetObject(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());

		List<User> result = redisTemplate.hMGetObject("user", new String[]{"1", "not_found"}, User.class);
		Assert.assertNotNull(result.get(0));
		Assert.assertNull(result.get(1));
	}

	@Test
	public void multi(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());

		redisTemplate.multi();
		//redisTemplate.set("m1", "M1");
		//redisTemplate.set("m2", "M2");
		//redisTemplate.hGet("user", "2");
		//redisTemplate.hGet("m", "a");
		redisTemplate.set("user_test", new User(2, "一"));
		redisTemplate.get("str");
		//redisTemplate.getObject("user_test", User.class);
		//redisTemplate.hGetObject("member", "2", User.class);
		List<Object> result = redisTemplate.exec();

		for(Object r : result){
			System.out.println(r);
		}
	}

	@Test
	public void pipeline(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());

		Pipeline pipeline = redisTemplate.pipeline();
		//redisTemplate.set("m1", "M1");
		//redisTemplate.set("m2", "M2");
		//redisTemplate.hGet("user", "2");
		//redisTemplate.hGet("m", "a");
		//redisTemplate.set("user_test", new User(2, "一"));
		redisTemplate.get("str");
		redisTemplate.getObject("user_test", User.class);
		redisTemplate.hGetObject("member", "2", User.class);
		List<Object> result = pipeline.syncAndReturnAll();

		for(Object r : result){
			System.out.println(r);
		}
	}

	@Test
	public void info(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());

		Info info = redisTemplate.info(Info.Section.REPLICATION);
		System.out.println(info.toPrettyString());
	}

}
