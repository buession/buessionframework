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
 * | License: http://buession.buession.com.cn/LICENSE 												       |
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis;

import com.buession.core.serializer.type.TypeReference;
import com.buession.redis.core.Options;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class JedisPoolClientTemplateTest extends AbstractJedisRedisTest {

	private RedisTemplate redisTemplate(){
		Options options = new Options();

		RedisTemplate redisTemplate = new RedisTemplate();

		redisTemplate.setOptions(options);

		try{
			redisTemplate.setConnection(connectionFactory().getObject());
		}catch(Exception e){
		}

		redisTemplate.afterPropertiesSet();

		return redisTemplate;
	}

	@Test
	public void exists(){
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.exists("A"));
		System.out.println(redisTemplate.exists("A".getBytes()));
		System.out.println(redisTemplate.exists("a".getBytes()));
		boolean result = redisTemplate.exists("a");
		System.out.println(result);
		System.out.println(redisTemplate.exists("user_1"));
		System.out.println(redisTemplate.exists("user_1"));
		System.out.println(redisTemplate.exists("user_2"));
	}

	@Test
	public void type(){
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.type("A"));
		System.out.println(redisTemplate.type("A".getBytes()));
		System.out.println(redisTemplate.type("a".getBytes()));
		System.out.println(redisTemplate.type("user_1"));
		System.out.println(redisTemplate.type("list"));
	}

	@Test
	public void rename(){
		RedisTemplate redisTemplate = redisTemplate();
		//System.out.println(redisTemplate.rename("A", "a"));
		//System.out.println(redisTemplate.rename("a", "A"));
		//System.out.println(redisTemplate.rename("b".getBytes(), "B".getBytes()));
		System.out.println(redisTemplate.rename("transaction".getBytes(), "A".getBytes()));
	}

	@Test
	public void renameNx(){
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.renameNx("A".getBytes(), "B".getBytes()));
		System.out.println(redisTemplate.renameNx("A", "B"));
		System.out.println(redisTemplate.renameNx("user".getBytes(), "user_3".getBytes()));
		System.out.println(redisTemplate.renameNx("list", "list_1"));
	}

	@Test
	public void randomKey(){
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.randomKey());
	}

	@Test
	public void keys(){
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.keys("user*"));
		System.out.println(redisTemplate.keys("test".getBytes()));
	}

}
