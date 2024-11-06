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
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisClientConfig;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Yong.Teng
 * @since 3.0.0
 */
public class JedisThreadTest extends AbstractJedisRedisTest {

	@Test
	public void test() {
		//RedisTemplate redisTemplate = redisTemplate();

		//拒绝策略
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
				4,
				8,
				10000,
				TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<>(8)
		);

		JedisClientConfig clientConfig = DefaultJedisClientConfig.builder().password("rds_PWD").build();
		for(int i = 0; i < 1; i++){
			threadPoolExecutor.execute(()->{
				try{
					Jedis jedis = new Jedis("192.168.0.231", 6379, clientConfig);
					//String result = redisTemplate.get("a");
					System.out.println(jedis);
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			});
		}
	}

}
