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
package com.buession.redis.lettuce;

import com.buession.lang.Status;
import com.buession.redis.RedisTemplate;
import com.buession.redis.core.ScanResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceClusterTest extends AbstractLettuceRedisTest {

	@Test
	public void asking() {
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.asking());
	}

	@Test
	public void clusterBumpEpoch() {
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.clusterBumpEpoch());
	}

	@Test
	public void clusterSlots() {
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.clusterSlots());
	}

	@Test
	public void clusterShards() {
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.clusterShards());
	}

	@Test
	public void clusterNodes() {
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.clusterNodes());
	}

	@Test
	public void clusterMyShardId() {
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.clusterMyShardId());
	}

	@Test
	public void clusterMyId() {
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.clusterMyId());
	}

	@Test
	public void clusterLinks() {
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.clusterLinks());
	}

	@Test
	public void clusterKeySlot() {
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.clusterKeySlot("user"));
	}

	@Test
	public void clusterInfo() {
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.clusterInfo());
	}

	@Test
	public void clusterSlotStats() {
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.clusterSlotStats());
	}

	@Test
	public void clusterReplicas() {
		RedisTemplate redisTemplate = redisTemplate();
		System.out.println(redisTemplate.clusterReplicas("6c4b96dd2c27bf03b6e684445959eebf8c64bcea"));
	}

}
