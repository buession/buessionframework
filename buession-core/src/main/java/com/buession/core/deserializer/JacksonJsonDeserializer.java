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
package com.buession.core.deserializer;

import com.buession.core.type.TypeReference;
import com.buession.core.utils.Assert;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * Jackson JSON 反序列化
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public class JacksonJsonDeserializer extends AbstractJsonDeserializer<ObjectMapper> {

	private final ObjectMapper objectMapper = new ObjectMapper();

	public JacksonJsonDeserializer() {
		configure(objectMapper);
	}

	@Override
	public void configure(ObjectMapper object) {
		objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
				.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}

	@Override
	public <V> V deserialize(final String str) throws DeserializerException {
		Assert.isNull(str, "String cloud not be null.");

		try{
			return objectMapper.readValue(str, new com.fasterxml.jackson.core.type.TypeReference<V>() {

			});
		}catch(IOException e){
			throw new DeserializerException(str + " json deserialize to Object failure.", e);
		}
	}

	@Override
	public <V> V deserialize(final String str, final Class<V> clazz) throws DeserializerException {
		Assert.isNull(str, "String cloud not be null.");

		try{
			return objectMapper.readValue(str, clazz);
		}catch(IOException e){
			final String className = clazz == null ? "null" : clazz.getName();
			throw new DeserializerException(str + " json deserialize to " + className + " failure.", e);
		}
	}

	@Override
	public <V> V deserialize(final String str, final TypeReference<V> type) throws DeserializerException {
		Assert.isNull(str, "String cloud not be null.");

		try{
			return objectMapper.readValue(str, new com.fasterxml.jackson.core.type.TypeReference<V>() {

				@Override
				public Type getType() {
					return type.getType();
				}

			});
		}catch(IOException e){
			final String typeName = type == null ? "null" : type.getType().getTypeName();
			throw new DeserializerException(str + " json deserialize to " + typeName + " failure.", e);
		}
	}

	@Override
	public <V> V deserialize(final byte[] bytes) throws DeserializerException {
		Assert.isNull(bytes, "Bytes cloud not be null.");

		try{
			return objectMapper.readValue(bytes, new com.fasterxml.jackson.core.type.TypeReference<V>() {

			});
		}catch(IOException e){
			throw new DeserializerException(Arrays.toString(bytes) + " json deserialize to Object failure.", e);
		}
	}

	@Override
	public <V> V deserialize(final byte[] bytes, final Class<V> clazz) throws DeserializerException {
		Assert.isNull(bytes, "Bytes cloud not be null.");

		try{
			return objectMapper.readValue(bytes, clazz);
		}catch(IOException e){
			final String className = clazz == null ? "null" : clazz.getName();
			throw new DeserializerException(Arrays.toString(bytes) + " json deserialize to " + className + " failure.",
					e);
		}
	}

	@Override
	public <V> V deserialize(final byte[] bytes, final TypeReference<V> type) throws DeserializerException {
		Assert.isNull(bytes, "Bytes cloud not be null.");

		try{
			return objectMapper.readValue(bytes, new com.fasterxml.jackson.core.type.TypeReference<V>() {

				@Override
				public Type getType() {
					return type.getType();
				}

			});
		}catch(IOException e){
			final String typeName = type == null ? "null" : type.getType().getTypeName();
			throw new DeserializerException(Arrays.toString(bytes) + " json deserialize to " + typeName + " failure.",
					e);
		}
	}

}
