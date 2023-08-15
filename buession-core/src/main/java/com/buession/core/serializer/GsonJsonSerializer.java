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
package com.buession.core.serializer;

import com.buession.core.deserializer.DeserializerException;
import com.buession.core.deserializer.GsonJsonDeserializer;
import com.buession.core.type.TypeReference;
import com.buession.core.utils.Assert;
import com.google.gson.Gson;

import java.nio.charset.Charset;

/**
 * Gson JSON 反序列化
 *
 * @author Yong.Teng
 */
public class GsonJsonSerializer extends AbstractJsonSerializer {

	@Override
	public <V> String serialize(final V object) throws SerializerException{
		Assert.isNull(object, "Object cloud not be null.");

		Gson gson = new Gson();
		return gson.toJson(object);
	}

	@Override
	public <V> byte[] serializeAsBytes(final V object) throws SerializerException{
		return serializeAsBytes(object, Charset.defaultCharset());
	}

	@Override
	public <V> byte[] serializeAsBytes(final V object, final Charset charset) throws SerializerException{
		return serialize(object).getBytes(charset);
	}

	@Deprecated
	@Override
	public <V> V deserialize(final String str) throws SerializerException{
		GsonJsonDeserializer deserializer = new GsonJsonDeserializer();
		try{
			return deserializer.deserialize(str);
		}catch(DeserializerException e){
			throw new SerializerException(e.getMessage(), e);
		}
	}

	@Deprecated
	@Override
	public <V> V deserialize(final String str, final Class<V> clazz) throws SerializerException{
		GsonJsonDeserializer deserializer = new GsonJsonDeserializer();
		try{
			return deserializer.deserialize(str, clazz);
		}catch(DeserializerException e){
			throw new SerializerException(e.getMessage(), e);
		}
	}

	@Deprecated
	@Override
	public <V> V deserialize(String str, TypeReference<V> type) throws SerializerException{
		GsonJsonDeserializer deserializer = new GsonJsonDeserializer();
		try{
			return deserializer.deserialize(str, type);
		}catch(DeserializerException e){
			throw new SerializerException(e.getMessage(), e);
		}
	}

	@Deprecated
	@Override
	public <V> V deserialize(final byte[] bytes) throws SerializerException{
		GsonJsonDeserializer deserializer = new GsonJsonDeserializer();
		try{
			return deserializer.deserialize(bytes);
		}catch(DeserializerException e){
			throw new SerializerException(e.getMessage(), e);
		}
	}

	@Deprecated
	@Override
	public <V> V deserialize(byte[] bytes, Class<V> clazz) throws SerializerException{
		GsonJsonDeserializer deserializer = new GsonJsonDeserializer();
		try{
			return deserializer.deserialize(bytes, clazz);
		}catch(DeserializerException e){
			throw new SerializerException(e.getMessage(), e);
		}
	}

	@Deprecated
	@Override
	public <V> V deserialize(byte[] bytes, TypeReference<V> type) throws SerializerException{
		GsonJsonDeserializer deserializer = new GsonJsonDeserializer();
		try{
			return deserializer.deserialize(bytes, type);
		}catch(DeserializerException e){
			throw new SerializerException(e.getMessage(), e);
		}
	}

}
