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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package io.lettuce.core;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class Pool<T> extends GenericObjectPool<T> {

	public Pool(GenericObjectPoolConfig<T> poolConfig, PooledObjectFactory<T> factory){
		this(factory, poolConfig);
	}

	public Pool(final PooledObjectFactory<T> factory, final GenericObjectPoolConfig<T> poolConfig){
		super(factory, poolConfig);
	}

	public T getResource(){
		try{
			return super.borrowObject();
		}catch(Exception e){
			throw new RedisConnectionException("Could not get a resource from the pool", e);
		}
	}

	public void returnResource(final T resource){
		if(resource == null){
			return;
		}

		try{
			super.returnObject(resource);
		}catch(RuntimeException e){
			throw new RedisConnectionException("Could not return the resource to the pool", e);
		}
	}

	public void returnBrokenResource(final T resource){
		if(resource == null){
			return;
		}
		try{
			super.invalidateObject(resource);
		}catch(Exception e){
			throw new RedisConnectionException("Could not return the broken resource to the pool", e);
		}
	}

	@Override
	public void addObjects(int count){
		try{
			for(int i = 0; i < count; i++){
				addObject();
			}
		}catch(Exception e){
			throw new RedisException("Error trying to add idle objects", e);
		}
	}

	public void destroy(){
		try{
			super.close();
		}catch(RuntimeException e){
			throw new RedisConnectionException("Could not destroy the pool", e);
		}
	}

	@Override
	public void close(){
		destroy();
	}

}
