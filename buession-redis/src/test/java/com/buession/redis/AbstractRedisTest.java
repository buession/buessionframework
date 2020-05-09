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

import com.buession.redis.spring.JedisRedisConnectionFactoryBean;
import com.buession.redis.spring.RedisConnectionFactoryBean;
import org.apache.commons.pool2.impl.BaseObjectPoolConfig;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Yong.Teng
 */
public abstract class AbstractRedisTest {

	protected JedisPoolConfig jedisPoolConfig(){
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

	protected RedisConnectionFactoryBean connectionFactory(){
		JedisRedisConnectionFactoryBean connectionFactory = new JedisRedisConnectionFactoryBean();

		connectionFactory.setPoolConfig(jedisPoolConfig());
		connectionFactory.setDatabase(10);
		//connectionFactory.setHost("10.101.0.36");
		connectionFactory.setHost("10.101.0.45");
		connectionFactory.setPort(6379);
		//connectionFactory.setPort(16379);
		//connectionFactory.setPassword("tQP!Vf7JxL-nrH-x");
		//connectionFactory.setPassword("passwd");
		connectionFactory.setUsePool(false);

		try{
			connectionFactory.afterPropertiesSet();
		}catch(Exception e){
			e.printStackTrace();
		}

		return connectionFactory;
	}

}
