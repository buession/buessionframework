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
package com.buession.redis.lettuce;

import com.buession.core.builder.ListBuilder;
import com.buession.redis.RedisTemplate;
import com.buession.redis.client.connection.datasource.lettuce.LettuceClusterDataSource;
import com.buession.redis.client.connection.datasource.lettuce.LettuceDataSource;
import com.buession.redis.core.Options;
import com.buession.redis.core.PoolConfig;
import com.buession.redis.core.RedisNode;

import java.util.List;

/**
 * @author Yong.Teng
 */
public abstract class AbstractLettuceRedisTest {

	protected LettuceDataSource dataSource() {
		LettuceDataSource dataSource = new LettuceDataSource();

		dataSource.setHost("192.168.0.161");
		dataSource.setPort(30341);
		dataSource.setDatabase(1);
		dataSource.setPassword("abc123456");
		dataSource.setPoolConfig(new PoolConfig());

		return dataSource;
	}

	protected LettuceClusterDataSource clusterDataSource() {
		LettuceClusterDataSource dataSource = new LettuceClusterDataSource();
		List<RedisNode> redisNodes = ListBuilder.<RedisNode>create()
				//.add(new RedisNode("192.168.0.162", 30943))
				.add(new RedisNode("192.168.0.231", 6371))
				.add(new RedisNode("192.168.0.231", 6372))
				.add(new RedisNode("192.168.0.231", 6373))
				.add(new RedisNode("192.168.0.231", 6374))
				.add(new RedisNode("192.168.0.231", 6375))
				.add(new RedisNode("192.168.0.231", 6376))
				.build();

		dataSource.setNodes(redisNodes);
		dataSource.setPassword("rds_PWD");
		//dataSource.setPassword("abc123456");
		dataSource.setPoolConfig(new PoolConfig());

		return dataSource;
	}

	protected RedisTemplate redisTemplate() {
		Options options = new Options();
		options.setPrefix("test:");
		RedisTemplate redisTemplate = new RedisTemplate(dataSource(), options);

		redisTemplate.afterPropertiesSet();

		return redisTemplate;
	}

}
