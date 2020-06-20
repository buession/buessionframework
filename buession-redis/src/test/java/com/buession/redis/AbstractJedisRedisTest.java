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

import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.Options;
import com.buession.redis.spring.JedisRedisConnectionFactoryBean;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisTest extends AbstractRedisTest {

	protected RedisConnection createRedisConnection(){
		JedisRedisConnectionFactoryBean factoryBean = new JedisRedisConnectionFactoryBean("redis.host", 6379, "tQP" +
				"!Vf7JxL-nrH-x", 10, true);

		try{
			factoryBean.afterPropertiesSet();
			return factoryBean.getObject();
		}catch(Exception e){
			return null;
		}
	}

	protected RedisTemplate getRedisTemplate(){
		RedisTemplate redisTemplate = new RedisTemplate(createRedisConnection());

		Options options = new Options();

		options.setEnableTransactionSupport(true);

		redisTemplate.setOptions(options);

		redisTemplate.afterPropertiesSet();

		return redisTemplate;
	}

}
