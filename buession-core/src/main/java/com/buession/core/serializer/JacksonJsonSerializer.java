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
package com.buession.core.serializer;

import com.buession.core.utils.Assert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Jackson JSON 序列化
 *
 * @author Yong.Teng
 */
public class JacksonJsonSerializer extends AbstractJsonSerializer<ObjectMapper> {

	private final ObjectMapper objectMapper = new ObjectMapper();

	public JacksonJsonSerializer() {
		configure(objectMapper);
	}

	@Override
	public void configure(ObjectMapper objectMapper) {
		objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}

	@Override
	public <V> String serialize(final V object) throws SerializerException {
		Assert.isNull(object, "Object cloud not be null.");

		try{
			return objectMapper.writeValueAsString(object);
		}catch(JsonProcessingException e){
			throw new SerializerException(object.getClass().getName() + " json serialize failure.", e);
		}
	}

	@Override
	public <V> byte[] serializeAsBytes(final V object) throws SerializerException {
		Assert.isNull(object, "Object cloud not be null.");

		try{
			return objectMapper.writeValueAsBytes(object);
		}catch(JsonProcessingException e){
			throw new SerializerException(object.getClass().getName() + " json serialize failure.", e);
		}
	}

}
