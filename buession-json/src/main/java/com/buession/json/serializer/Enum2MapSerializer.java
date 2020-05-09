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
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													       |
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.json.serializer;

import com.buession.core.utils.EnumUtils;
import com.buession.core.utils.ReflectUtils;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author Yong.Teng
 */
public class Enum2MapSerializer extends JsonSerializer<Enum<?>> {

	private final static Logger logger = LoggerFactory.getLogger(Enum2MapSerializer.class);

	@Override
	public void serialize(Enum<?> en, JsonGenerator generator, SerializerProvider provider) throws IOException{
		Field[] fields = en.getClass().getDeclaredFields();
		generator.writeStartObject();

		for(Field field : fields){
			if(field.isEnumConstant() == false && EnumUtils.isEnumValuesField(field) == false){
				writeFieldValue(generator, en, field);
			}
		}

		generator.writeEndObject();
	}

	private final static void writeFieldValue(final JsonGenerator generator, final Enum<?> en, final Field field)
			throws IOException{
		ReflectUtils.setFieldAccessible(field);

		try{
			generator.writeFieldName(field.getName());
			generator.writeObject(field.get(en));
		}catch(IllegalAccessException e){
			logger.error("Read {}::{} failure: {}", field.getDeclaringClass().getName(), field.getName(), e.getMessage
					());
		}
	}

}
