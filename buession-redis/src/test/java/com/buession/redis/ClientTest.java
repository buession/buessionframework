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

import com.buession.redis.client.RedisClient;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.core.Client;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class ClientTest extends AbstractRedisTest {

	private RedisTemplate redisTemplate(){
		RedisTemplate redisClientTemplate = null;

		try{
			redisClientTemplate = new RedisTemplate(connectionFactory().getObject());
			redisClientTemplate.afterPropertiesSet();
		}catch(Exception e){
			e.printStackTrace();
		}

		return redisClientTemplate;
	}

	@Test
	public void set(){
		RedisTemplate redisClientTemplate = redisTemplate();
		redisClientTemplate.set("a", "A");
	}

	@Test
	public void setNx(){
		RedisTemplate redisClientTemplate = redisTemplate();
		redisClientTemplate.setNx("a", "A");
	}

	@Test
	public void get(){
		RedisTemplate redisClientTemplate = redisTemplate();

		String a = redisClientTemplate.get("a");
		System.out.println(a);


		String b = redisClientTemplate.get("a");
		System.out.println(b);
	}

	@Test
	public void info(){
		RedisTemplate redisTemplate = redisTemplate();
		RedisClient client = null;

		try{
			client = redisTemplate.getClient();
			System.out.println(client.info());


			List<Client> clients = redisTemplate.getClient().clientList();

			System.out.println(clients);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void clientList(){
		RedisTemplate redisTemplate = redisTemplate();

		List<Client> clients = redisTemplate.getClient().clientList();

		System.out.println(clients);

        /*JedisRedisClientTemplate redisClientTemplate = redisClientTemplate();
        RedisClient<Jedis> client = null;

        try{
            client = redisClientTemplate.getExposeRedisClient();

            if(client instanceof SimpleRedisClient){
                List<Client> clients = ((SimpleRedisClient) client).clientList();

                if(clients == null){
                    System.out.println("clients is null");
                }else if(clients.size() == 0){
                    System.out.println("clients is empty");
                }else{
                    for(Client c : clients){
                        System.out.println(c);
                    }
                }
            }

            RedisConnectionUtils.releaseConnection(redisClientTemplate.getConnectionFactory(), client
                    .getRedisConnection());
        }catch(Exception e){
            e.printStackTrace();
        }*/
	}

}
