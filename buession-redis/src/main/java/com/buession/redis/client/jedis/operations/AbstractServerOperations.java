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
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.ServerOperations;
import com.buession.redis.utils.SafeEncoder;

/**
 * Jedis 服务端命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link JedisRedisClient}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public abstract class AbstractServerOperations<C extends JedisRedisClient> extends AbstractJedisRedisOperations<C>
		implements ServerOperations {

	public AbstractServerOperations(final C client){
		super(client);
	}

	@Override
	public Status moduleLoad(final byte[] path, final byte[]... arguments){
		if(arguments == null){
			return moduleLoad(SafeEncoder.encode(path));
		}else{
			final String[] args = new String[arguments.length];

			for(int i = 0; i < arguments.length; i++){
				args[i] = SafeEncoder.encode(arguments[i]);
			}

			return moduleLoad(SafeEncoder.encode(path), args);
		}
	}

	@Override
	public Status moduleUnLoad(final byte[] name){
		return moduleUnLoad(SafeEncoder.encode(name));
	}

}
