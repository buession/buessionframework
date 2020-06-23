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

import com.buession.redis.core.Info;
import com.buession.redis.core.Server;
import com.buession.redis.transaction.Transaction;
import org.junit.Test;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class JedisClientTest extends AbstractJedisRedisTest {

	@Test
	public void info(){
		RedisTemplate redisTemplate = getRedisTemplate();
		System.out.println(redisTemplate.info());
	}

	@Test
	public void set(){
		Info info = new Info();
		RedisTemplate redisTemplate = getRedisTemplate();
		redisTemplate.set("info", info);
	}

	@Test
	public void get(){
		RedisTemplate redisTemplate = getRedisTemplate();
		System.out.println(redisTemplate.get("info"));
		System.out.println(redisTemplate.getObject("info", Info.class));
	}

	@Test
	public void transaction(){
		RedisTemplate redisTemplate = getRedisTemplate();

		Transaction transaction = redisTemplate.multi();
		redisTemplate.set("server", new Server());
		redisTemplate.set("t1", "T1222333");
		redisTemplate.type("test");
		redisTemplate.getObject("server", Server.class);
		List<Object> result = redisTemplate.exec();

		if(result != null){
			for(Object value : result){
				System.out.println(value);
			}
		}
	}

}
