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
package com.buession.io.json.serializer;

import com.buession.io.MimeType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;

/**
 * {@link MimeType} 序列化为字符串
 *
 * @author Yong.Teng
 * @since 1.3.2
 */
public class MimeTypeStringSerializer extends StdScalarSerializer<MimeType> implements ContextualSerializer {

	private final static long serialVersionUID = 6096763941152821826L;

	public MimeTypeStringSerializer() {
		super(MimeType.class, false);
	}

	public MimeTypeStringSerializer(Class<? extends MimeType> clazz) {
		super(clazz, false);
	}

	@Override
	public void serialize(MimeType value, JsonGenerator jsonGenerator, SerializerProvider serializers)
			throws IOException {
		if(value == null){
			jsonGenerator.writeNull();
		}else{
			jsonGenerator.writeString(value.toString());
		}
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property)
			throws JsonMappingException {
		JsonFormat.Value format = findFormatOverrides(provider, property, handledType());

		if(format != null){
			return new MimeTypeStringSerializer((Class<MimeType>) property.getType().getRawClass());
		}

		return this;
	}

}
