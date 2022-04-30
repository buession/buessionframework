/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2022 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.serializer;

import com.buession.core.collect.Arrays;
import com.buession.core.serializer.SerializerException;
import com.buession.core.utils.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 序列化和反序列化抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractSerializer<T extends com.buession.core.serializer.Serializer> implements Serializer {

	/**
	 * 序列化器
	 */
	protected final T serializer;

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 构造函数
	 *
	 * @param serializer
	 * 		序列化器
	 */
	protected AbstractSerializer(final T serializer){
		Assert.isNull(serializer, "original serializer object cloud not be null.");
		this.serializer = serializer;
	}

	@Override
	public <V> String serialize(final V object){
		if(object != null){
			try{
				return serializer.serialize(object);
			}catch(SerializerException e){
				logger.error("{} serializer error.", object, e);
			}
		}

		return null;
	}

	@Override
	public <V> String[] serialize(final V... objects){
		return objects == null ? null : Arrays.map(objects, this::serialize);
	}

	@Override
	public <V> byte[] serializeAsBytes(final V object){
		if(object != null){
			try{
				return serializer.serializeAsBytes(object);
			}catch(SerializerException e){
				logger.error("{} serializer error.", object, e);
			}
		}

		return null;
	}

	@Override
	public <V> byte[][] serializeAsBytes(final V... objects){
		return objects == null ? null : Arrays.map(objects, this::serializeAsBytes);
	}

	@Override
	public <V> V deserialize(final String str){
		if(str != null){
			try{
				return serializer.deserialize(str);
			}catch(SerializerException e){
				logger.error("{} serializer error.", str, e);
			}
		}

		return null;
	}

	@Override
	public <V> V deserializeBytes(final byte[] bytes){
		if(bytes != null){
			try{
				return serializer.deserialize(bytes);
			}catch(SerializerException e){
				logger.error("{} serializer error.", bytes, e);
			}
		}

		return null;
	}

	@Override
	public <V> V deserialize(final String str, final Class<V> clazz){
		return deserialize(str);
	}

	@Override
	public <V> V deserializeBytes(final byte[] bytes, final Class<V> clazz){
		return deserializeBytes(bytes);
	}

}
