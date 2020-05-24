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
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Yong.Teng
 */
public class SpringDataRedisTest {

	private JedisPoolConfig createJedisPoolConfig(){
		JedisPoolConfig config = new JedisPoolConfig();

		config.setLifo(BaseObjectPoolConfig.DEFAULT_LIFO);
		config.setMaxWaitMillis(2 * 1000);
		config.setMinEvictableIdleTimeMillis(2 * 1000);
		config.setSoftMinEvictableIdleTimeMillis(BaseObjectPoolConfig.DEFAULT_MIN_EVICTABLE_IDLE_TIME_MILLIS);
		config.setNumTestsPerEvictionRun(BaseObjectPoolConfig.DEFAULT_NUM_TESTS_PER_EVICTION_RUN);
		config.setEvictionPolicyClassName(BaseObjectPoolConfig.DEFAULT_EVICTION_POLICY_CLASS_NAME);
		config.setTestOnBorrow(true);
		//  config.setTestOnReturn(true);
		// config.setTestWhileIdle(true);
		config.setTimeBetweenEvictionRunsMillis(2 * 1000);
		config.setBlockWhenExhausted(BaseObjectPoolConfig.DEFAULT_BLOCK_WHEN_EXHAUSTED);
		config.setJmxEnabled(BaseObjectPoolConfig.DEFAULT_JMX_ENABLE);
		config.setJmxNamePrefix(BaseObjectPoolConfig.DEFAULT_JMX_NAME_PREFIX);
		config.setMaxTotal(10);
		config.setMinIdle(3);
		config.setMaxIdle(5);

		return config;
	}

	private JedisConnectionFactory createJedisConnectionFactory(){
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration("10.101.0.45", 6379);
		configuration.setDatabase(10);

		JedisClientConfiguration jedisClientConfiguration =
				JedisClientConfiguration.builder().usePooling().poolConfig(createJedisPoolConfig()).build();

		JedisConnectionFactory factory = new JedisConnectionFactory(configuration, jedisClientConfiguration);

		factory.afterPropertiesSet();

		return factory;
	}

	private RedisTemplate redisTemplate(){
		RedisTemplate redisTemplate = new RedisTemplate();

		redisTemplate.setConnectionFactory(createJedisConnectionFactory());

		redisTemplate.afterPropertiesSet();

		return redisTemplate;
	}

	@Test
	public void get(){
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.hasKey("user"));
		System.out.println(redisTemplate.hasKey("user"));
	}

}
