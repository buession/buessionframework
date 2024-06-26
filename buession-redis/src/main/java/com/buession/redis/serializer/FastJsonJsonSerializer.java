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
 * | Copyright @ 2013-2023 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.serializer;

import com.buession.core.deserializer.DeserializerException;
import com.buession.core.type.TypeReference;

/**
 * FastJSON 序列化和反序列化
 *
 * @author Yong.Teng
 */
public class FastJsonJsonSerializer extends AbstractSerializer<com.buession.core.serializer.FastJsonJsonSerializer,
		com.buession.core.deserializer.FastJsonJsonDeserializer> {

	/**
	 * 构造函数
	 */
	public FastJsonJsonSerializer(){
		super(new com.buession.core.serializer.FastJsonJsonSerializer(),
				new com.buession.core.deserializer.FastJsonJsonDeserializer());
	}

	@Override
	public <V> V deserialize(final String str, final Class<V> clazz){
		if(str != null){
			try{
				return deserializer.deserialize(str, clazz);
			}catch(DeserializerException e){
				logger.error("{} deserialize to {} error.", str, clazz.getName(), e);
			}
		}

		return null;
	}

	@Override
	public <V> V deserializeBytes(final byte[] bytes, final Class<V> clazz){
		if(bytes != null){
			try{
				return deserializer.deserialize(bytes, clazz);
			}catch(DeserializerException e){
				logger.error("{} deserialize to {} error.", bytes, clazz.getName(), e);
			}
		}

		return null;
	}

	@Override
	public <V> V deserialize(final String str, final TypeReference<V> type){
		if(str != null){
			try{
				return deserializer.deserialize(str, type);
			}catch(DeserializerException e){
				logger.error("{} deserialize to {} error.", str, type.getType().getTypeName(), e);
			}
		}

		return null;
	}

	@Override
	public <V> V deserializeBytes(final byte[] bytes, final TypeReference<V> type){
		if(bytes != null){
			try{
				return deserializer.deserialize(bytes, type);
			}catch(DeserializerException e){
				logger.error("{} deserialize to {} error.", bytes, type.getType().getTypeName(), e);
			}
		}

		return null;
	}

}
