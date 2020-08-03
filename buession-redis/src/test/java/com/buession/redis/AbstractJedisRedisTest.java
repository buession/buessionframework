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

import com.buession.core.utils.StringUtils;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.Options;
import com.buession.redis.core.ShardedRedisNode;
import com.buession.redis.spring.JedisRedisConnectionFactoryBean;
import com.buession.redis.spring.jedis.JedisConfiguration;
import com.buession.redis.spring.jedis.ShardedRedisConfiguration;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisTest extends AbstractRedisTest {

	protected RedisConnection createJedisConnection(){
		JedisConfiguration configuration = new JedisConfiguration();

		configuration.setHost("redis.host");
		configuration.setPort(6379);
		configuration.setPassword("tQP!Vf7JxL-nrH-x");
		configuration.setDatabase(10);
		configuration.setClientName(StringUtils.random(6));

		JedisRedisConnectionFactoryBean factoryBean = new JedisRedisConnectionFactoryBean(configuration, null);

		try{
			factoryBean.afterPropertiesSet();
			return factoryBean.getObject();
		}catch(Exception e){
			return null;
		}
	}

	protected RedisConnection createShardedJedisConnection(){
		Set<ShardedRedisNode> nodes = new LinkedHashSet<>(2);

		nodes.add(new ShardedRedisNode("10.101.0.230", 6379, 10));
		nodes.add(new ShardedRedisNode("10.101.0.45", 6379, 9));

		ShardedRedisConfiguration configuration = new ShardedRedisConfiguration();
		configuration.setNodes(nodes);

		JedisRedisConnectionFactoryBean factoryBean = new JedisRedisConnectionFactoryBean(configuration);

		try{
			factoryBean.afterPropertiesSet();
			return factoryBean.getObject();
		}catch(Exception e){
			return null;
		}
	}

	protected RedisTemplate getRedisTemplate(RedisConnection connection){
		RedisTemplate redisTemplate = new RedisTemplate(connection);

		Options options = new Options();

		options.setEnableTransactionSupport(true);

		redisTemplate.setOptions(options);

		redisTemplate.afterPropertiesSet();

		return redisTemplate;
	}

}
