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
import org.junit.Test;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class JedisPoolClientTemplateTest extends AbstractRedisTest {

	private RedisTemplate redisTemplate(){
		RedisTemplate redisTemplate = new RedisTemplate();

		try{
			redisTemplate.setConnection(connectionFactory().getObject());
		}catch(Exception e){
		}

		redisTemplate.afterPropertiesSet();

		return redisTemplate;
	}

	@Test
	public void set(){
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.set("test", "2"));

		try{
			System.out.println(redisTemplate.get("test"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void get(){
		User user1 = new User();

		user1.setId(1);
		user1.setUsername("A");

		User user2 = new User();

		user2.setId(2);
		user2.setUsername("B");

		RedisTemplate redisTemplate = redisTemplate();

		redisTemplate.set("user_1", user1);
		redisTemplate.set("user_2", user2);

		User userRet1 = redisTemplate.getObject("user_1", User.class);
		User userRet2 = redisTemplate.getObject("user_1", new TypeReference<User>() {

		});

		System.out.println("user 1: " + userRet1);
		System.out.println("user 2: " + userRet2);

		redisTemplate.lPush("userl", user1, user2);

		List<User> result = redisTemplate.lRangeObject("userl", 0, 1, User.class);
		System.out.println(result.get(0).getId());

		redisTemplate.lIndex("userl", 1);
		redisTemplate.lIndex("userl", 1);
		redisTemplate.lIndex("userl", 1);
		redisTemplate.lIndex("userl", 1);
		redisTemplate.lIndex("userl", 1);
		redisTemplate.lIndex("userl", 1);
		redisTemplate.lIndex("userl", 1);
		redisTemplate.lIndex("userl", 1);
		redisTemplate.lIndex("userl", 1);
		redisTemplate.lIndex("userl", 1);
	}

	@Test
	public void lPush(){
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.lPush("list", "List1"));
		System.out.println(redisTemplate.lPush("list", "List2"));
	}

	@Test
	public void lPushX(){
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.lPushX("list", "List1"));
		System.out.println(redisTemplate.lPushX("list", "List2"));
	}

	//  @Test
	public void randomKey(){
		RedisTemplate redisClientTemplate = redisTemplate();
		System.out.println(redisClientTemplate.randomKey());
	}

	@Test
	public void keys(){
		RedisTemplate redisTemplate = redisTemplate();
		for(int i = 0; i < 10; i++){
			System.out.println(i + ": ");
			System.out.println(redisTemplate.keys("*"));
			System.out.println(redisTemplate.keys("*"));
		}
	}

	private final static class User {

		private int id;

		private String username;

		public int getId(){
			return id;
		}

		public void setId(int id){
			this.id = id;
		}

		public String getUsername(){
			return username;
		}

		public void setUsername(String username){
			this.username = username;
		}

		@Override
		public String toString(){
			return "User{" + "id=" + id + ", username='" + username + '\'' + '}';
		}
	}

}
