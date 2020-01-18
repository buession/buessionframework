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
 * | Copyright @ 2013-2020 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.serializer;

import com.buession.core.serializer.DefaultByteArraySerializer;
import com.buession.core.serializer.SerializerException;
import com.buession.core.serializer.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yong.Teng
 */
public class ByteArraySerializer implements Serializer {

	private final static DefaultByteArraySerializer serializer = new DefaultByteArraySerializer();

	private final static Logger logger = LoggerFactory.getLogger(ByteArraySerializer.class);

	@Override
	public <V> String serialize(final V object){
		if(object == null){
			return null;
		}

		try{
			return serializer.serialize(object);
		}catch(SerializerException e){
			logger.error("{}", e);
			return null;
		}
	}

	@Override
	public <V> byte[] serializeAsBytes(final V object){
		if(object == null){
			return null;
		}

		try{
			return serializer.serializeAsBytes(object);
		}catch(SerializerException e){
			logger.error("{}", e);
			return null;
		}
	}

	@Override
	public <V> V deserialize(final String str){
		if(str == null){
			return null;
		}

		try{
			return serializer.deserialize(str);
		}catch(SerializerException e){
			logger.error("{}", e);
			return null;
		}
	}

	@Override
	public <V> V deserialize(final byte[] bytes){
		if(bytes == null){
			return null;
		}

		try{
			return serializer.deserialize(bytes);
		}catch(SerializerException e){
			logger.error("{}", e);
			return null;
		}
	}

	@Override
	public <V> V deserialize(final String str, final Class<V> clazz){
		return deserialize(str);
	}

	@Override
	public <V> V deserialize(final byte[] bytes, final Class<V> clazz){
		return deserialize(bytes);
	}

	@Override
	public <V> V deserialize(final String str, final TypeReference<V> type){
		return deserialize(str);
	}

	@Override
	public <V> V deserialize(final byte[] bytes, final TypeReference<V> type){
		return deserialize(bytes);
	}

}
