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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.jedis.standalone;

import com.buession.redis.RedisTemplate;
import com.buession.redis.core.ClusterSlot;
import com.buession.redis.jedis.AbstractJedisRedisTest;
import org.junit.Test;

import java.util.List;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ClusterTest extends AbstractJedisRedisTest {

	@Test
	public void clusterMyId(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());
		System.out.println(redisTemplate.clusterMyId());
	}

	@Test
	public void clusterSlots(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());
		List<ClusterSlot> clusterSlots = redisTemplate.clusterSlots();

		if(clusterSlots != null){
			for(ClusterSlot clusterSlot : clusterSlots){
				System.out.println(clusterSlot);
			}
		}
	}

	@Test
	public void clusterInfo(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());
		System.out.println(redisTemplate.clusterInfo());
	}

	@Test
	public void clusterNodes(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());
		System.out.println(redisTemplate.clusterNodes());
	}

	@Test
	public void clusterSlaves(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());
		System.out.println(redisTemplate.clusterSlaves("ea693713bafd2e17963b361e269183a30b43a4d1"));
	}

	@Test
	public void clusterReplicas(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());
		System.out.println(redisTemplate.clusterReplicas("ea693713bafd2e17963b361e269183a30b43a4d1"));
	}

	@Test
	public void clusterBumpEpoch(){
		RedisTemplate redisTemplate = getRedisTemplate(createJedisConnection());
		System.out.println(redisTemplate.clusterBumpEpoch());
	}

}
