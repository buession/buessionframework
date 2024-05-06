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
package com.buession.json.serializer;

import com.buession.core.utils.ClassUtils;
import com.buession.core.validator.Validate;
import com.buession.json.annotation.Sensitive;
import com.buession.json.strategy.ISensitiveStrategy;
import com.buession.json.strategy.NoneSensitiveStrategy;
import com.buession.lang.Constants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yong.Teng
 * @since 1.3.1
 */
@JacksonStdImpl
public class SensitiveSerializer extends StdScalarSerializer<CharSequence> implements ContextualSerializer {

	private final static long serialVersionUID = 5197972718452230362L;

	private ISensitiveStrategy strategy;

	private String format;

	private String replacement;

	private final static Map<String, ISensitiveStrategy> SENSITIVE_STRATEGY_CACHE = new ConcurrentHashMap<>(
			8);

	public SensitiveSerializer() {
		super(CharSequence.class);
	}

	@Override
	public void serialize(CharSequence value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException {
		if(value == null){
			jsonGenerator.writeNull();
		}else if(value.length() == 0){
			jsonGenerator.writeString(Constants.EMPTY_STRING);
		}else{
			String str;

			if(Validate.hasText(format)){
				str = value.toString()
						.replaceAll(format, Optional.ofNullable(replacement).orElse(Sensitive.DEFAULT_REPLACEMENT));
			}else{
				str = strategy.getFunction().apply(value.toString());
			}

			jsonGenerator.writeString(str);
		}
	}

	@Override
	public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property)
			throws JsonMappingException {
		Sensitive annotation = property.getAnnotation(Sensitive.class);

		if(Objects.nonNull(annotation) && CharSequence.class.isAssignableFrom(property.getType().getRawClass())){
			this.format = annotation.format();
			this.replacement = annotation.replacement();

			if(Validate.isEmpty(this.format)){
				if(annotation.strategyType() != NoneSensitiveStrategy.class){
					this.strategy =
							SENSITIVE_STRATEGY_CACHE.computeIfAbsent(
									annotation.strategyType().getName() + '@' + replacement,
									(key)->ClassUtils.instantiate(annotation.strategyType(), replacement));
				}else{
					this.strategy =
							SENSITIVE_STRATEGY_CACHE.computeIfAbsent(
									annotation.strategy().getStrategy().getName() + '@' + replacement,
									(key)->ClassUtils.instantiate(annotation.strategy().getStrategy(), replacement));
				}
			}

			return this;
		}

		return provider.findValueSerializer(property.getType(), property);
	}

}
