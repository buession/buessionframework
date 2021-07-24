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
package com.buession.session;

import org.apache.commons.pool2.impl.BaseObjectPoolConfig;
import org.junit.Test;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;

/**
 * @author Yong.Teng
 */
public class SpringDataRedisTest {

	private JedisPoolConfig createJedisPoolConfig(){
		JedisPoolConfig config = new JedisPoolConfig();

		return config;
	}

	private JedisConnectionFactory createJedisConnectionFactory(){
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("192.168.0.231", 6379);
		configuration.setPassword(RedisPassword.of("rds_PWD"));
		configuration.setDatabase(0);

		JedisClientConfiguration jedisClientConfiguration =
				JedisClientConfiguration.builder().usePooling().poolConfig(createJedisPoolConfig()).build();

		JedisConnectionFactory factory = new JedisConnectionFactory(configuration, jedisClientConfiguration);

		factory.afterPropertiesSet();

		return factory;
	}

	private JedisConnectionFactory createJedisSentinelConnectionFactory(){
		RedisSentinelConfiguration configuration = new RedisSentinelConfiguration();

		ArrayList<RedisNode> sentinels = new ArrayList<>(1);

		sentinels.add(new RedisNode("10.101.0.131", 31127));

		configuration.setSentinels(sentinels);
		configuration.setMaster("master-server");

		JedisConnectionFactory factory = new JedisConnectionFactory(configuration, createJedisPoolConfig());

		factory.afterPropertiesSet();

		return factory;
	}

	private RedisTemplate redisTemplate(){
		RedisTemplate redisTemplate = new StringRedisTemplate();

		redisTemplate.setConnectionFactory(createJedisConnectionFactory());

		redisTemplate.afterPropertiesSet();

		return redisTemplate;
	}

	@Test
	public void transaction(){
		RedisTemplate redisTemplate = redisTemplate();

		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.multi();
		redisTemplate.opsForValue().set("test_tx1", "A");
		redisTemplate.exec();
	}

	@Test
	public void set(){
		RedisTemplate redisTemplate = redisTemplate();
		redisTemplate.opsForValue().set("test_tx1", "A");
		redisTemplate.opsForValue().set("test_tx2", "B");
	}

	@Test
	public void get(){
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.opsForValue().get("cms_demo:shiro_session:380ac518-bd56-4c2b-b7f3" +
				"-f5c29296ee44"));
	}

}
