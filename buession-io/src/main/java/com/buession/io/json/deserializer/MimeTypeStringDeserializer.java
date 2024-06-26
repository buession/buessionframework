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
package com.buession.io.json.deserializer;

import com.buession.io.MimeType;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

import java.io.IOException;

/**
 * 字符串反序列化为 {@link MimeType}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class MimeTypeStringDeserializer extends StdScalarDeserializer<MimeType> implements ContextualDeserializer {

	private final static long serialVersionUID = 2065725897002881112L;

	public MimeTypeStringDeserializer() {
		super(MimeType.class);
	}

	public MimeTypeStringDeserializer(Class<? extends MimeType> clazz) {
		super(clazz);
	}

	@Override
	public MimeType deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JacksonException {
		Object currentValue = jsonParser.getCurrentValue();
		Class<?> clazz = currentValue.getClass();

		if(clazz.isAssignableFrom(String.class)){
			try{
				return MimeType.parse(currentValue.toString());
			}catch(Exception e){
				throw new JsonParseException(jsonParser, e.getMessage(), jsonParser.getCurrentLocation(), e);
			}
		}

		throw new JsonParseException(jsonParser,
				clazz.getName() + " cloud not deserialize to: " + MimeType.class.getName(),
				jsonParser.getCurrentLocation());
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext context, BeanProperty property)
			throws JsonMappingException {
		return new MimeTypeStringDeserializer((Class<MimeType>) property.getType().getRawClass());
	}

}
