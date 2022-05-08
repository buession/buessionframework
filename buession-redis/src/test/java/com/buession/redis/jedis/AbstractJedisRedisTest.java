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
package com.buession.redis.jedis;

import com.buession.core.utils.StringUtils;
import com.buession.redis.AbstractRedisTest;
import com.buession.redis.RedisTemplate;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisClusterDataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisSentinelDataSource;
import com.buession.redis.core.Options;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.core.RedisNode;

import java.util.Arrays;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisTest extends AbstractRedisTest {

	protected JedisDataSource createJedisDataSource(){
		JedisDataSource dataSource = new JedisDataSource();
		PoolConfig poolConfig = new PoolConfig();

		poolConfig.setMaxIdle(3);

		dataSource.setHost("test.redis.server");
		dataSource.setPort(6379);
		dataSource.setPassword("rds_PWD");
		dataSource.setDatabase(60);
		dataSource.setClientName(StringUtils.random(6));
		dataSource.setPoolConfig(poolConfig);

		return dataSource;
	}

	protected JedisSentinelDataSource createJedisSentinelDataSource(){
		JedisSentinelDataSource dataSource = new JedisSentinelDataSource();
		PoolConfig poolConfig = new PoolConfig();

		poolConfig.setMaxIdle(3);

		dataSource.setSentinels(Arrays.asList(new RedisNode("127.0.0.1", 26379), new RedisNode("127.0.0.1", 36379),
				new RedisNode("127.0.0.1", 36379)));
		dataSource.setSentinelClientName("SentinelClientName");
		dataSource.setClientName(StringUtils.random(6));
		dataSource.setMasterName("test_master");
		dataSource.setPassword("passwd");
		dataSource.setDatabase(6);
		//configuration.setPoolConfig(poolConfig);

		return dataSource;
	}

	protected JedisClusterDataSource createJedisClusterDataSource(){
		JedisClusterDataSource dataSource = new JedisClusterDataSource();
		PoolConfig poolConfig = new PoolConfig();

		poolConfig.setMaxIdle(3);

		dataSource.setNodes(
				Arrays.asList(new RedisNode("test.redis.server", 36379), new RedisNode("test.redis.server", 36380),
						new RedisNode("test.redis.server", 36381), new RedisNode("test.redis.server", 36382),
						new RedisNode("test.redis.server", 36383), new RedisNode("test.redis.server", 36384)));
		dataSource.setClientName(StringUtils.random(6));
		dataSource.setPassword("rds_PWD");
		dataSource.setPoolConfig(poolConfig);
		dataSource.setMaxRedirects(1);

		return dataSource;
	}

	protected RedisTemplate getRedisTemplate(DataSource dataSource){
		RedisTemplate redisTemplate = new RedisTemplate(dataSource);

		Options options = new Options();

		options.setPrefix("test:");
		options.setEnableTransactionSupport(true);

		redisTemplate.setOptions(options);

		redisTemplate.afterPropertiesSet();

		return redisTemplate;
	}

}
