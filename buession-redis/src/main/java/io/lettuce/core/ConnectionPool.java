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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package io.lettuce.core;

import io.lettuce.core.api.StatefulConnection;
import io.lettuce.core.codec.RedisCodec;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * 连接池
 *
 * @param <K>
 * 		Key 类型
 * @param <V>
 * 		值类型
 * @param <CONN>
 * 		连接类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class ConnectionPool<K, V, CONN extends StatefulConnection<K, V>> extends GenericObjectPool<CONN> {

	public ConnectionPool(final PooledObjectFactory<CONN> factory) {
		super(factory);
	}

	public ConnectionPool(final PooledObjectFactory<CONN> factory, final GenericObjectPoolConfig<CONN> poolConfig) {
		super(factory, poolConfig);
	}

	public ConnectionPool(final AbstractRedisClient redisClient, final RedisCodec<K, V> redisCodec) {
		this(new ConnectionFactory<>(redisClient, redisCodec));
	}

	public ConnectionPool(final AbstractRedisClient redisClient, final GenericObjectPoolConfig<CONN> poolConfig,
	                      final RedisCodec<K, V> redisCodec) {
		this(new ConnectionFactory<>(redisClient, redisCodec), poolConfig);
	}

	public CONN getResource() {
		return borrowObject();
	}

	@Override
	public CONN borrowObject() {
		try{
			return super.borrowObject();
		}catch(RedisException re){
			throw re;
		}catch(Exception e){
			throw new RedisException("Could not get a resource from the pool", e);
		}
	}

	public void returnResource(final CONN resource) {
		returnObject(resource);
	}

	@Override
	public void returnObject(final CONN resource) {
		if(resource == null){
			return;
		}

		try{
			super.returnObject(resource);
		}catch(RuntimeException e){
			throw new RedisException("Could not return the resource to the pool", e);
		}
	}

	public void returnBrokenResource(final CONN resource) {
		if(resource == null){
			return;
		}

		try{
			super.invalidateObject(resource);
		}catch(Exception e){
			throw new RedisException("Could not return the broken resource to the pool", e);
		}
	}

	@Override
	public void addObjects(int count) {
		try{
			for(int i = 0; i < count; i++){
				addObject();
			}
		}catch(Exception e){
			throw new RedisException("Error trying to add idle objects", e);
		}
	}

	@Override
	public void close() {
		try{
			super.close();
		}catch(RuntimeException e){
			throw new RedisException("Could not destroy the pool", e);
		}
	}

	public void destroy() {
		close();
	}

}
