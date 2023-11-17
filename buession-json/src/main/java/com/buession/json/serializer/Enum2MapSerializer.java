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
package com.buession.json.serializer;

import com.buession.core.utils.FieldUtils;
import com.buession.core.utils.EnumUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;

/**
 * 枚举序列化为 {@link Map}
 *
 * @author Yong.Teng
 */
@JacksonStdImpl
public class Enum2MapSerializer extends StdScalarSerializer<Enum<?>> implements ContextualSerializer {

	private final static long serialVersionUID = 8436605332881634259L;

	private final static Logger logger = LoggerFactory.getLogger(Enum2MapSerializer.class);

	public Enum2MapSerializer() {
		super(Enum.class, false);
	}

	public Enum2MapSerializer(Class<Enum<?>> v) {
		super(v, false);
	}

	@Override
	public void serialize(Enum en, JsonGenerator generator, SerializerProvider provider) throws IOException {
		Field[] fields = en.getClass().getDeclaredFields();
		generator.writeStartObject();

		for(Field field : fields){
			if(field.isEnumConstant() == false && EnumUtils.isEnumValuesField(field) == false){
				writeFieldValue(generator, en, field);
			}
		}

		generator.writeEndObject();
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property)
			throws JsonMappingException {
		JsonFormat.Value format = findFormatOverrides(provider, property, handledType());

		if(format != null){
			return new Enum2MapSerializer((Class<Enum<?>>) property.getType().getRawClass());
		}

		return this;
	}

	private static void writeFieldValue(final JsonGenerator generator, final Enum<?> en, final Field field)
			throws IOException {
		FieldUtils.setAccessible(field);

		try{
			generator.writeFieldName(field.getName());
			generator.writeObject(field.get(en));
		}catch(IllegalAccessException e){
			logger.error("Read {}::{} failure: {}", field.getDeclaringClass().getName(), field.getName(),
					e.getMessage());
		}
	}

}
