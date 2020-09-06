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
package com.buession.beans;

import com.buession.beans.converters.*;
import com.buession.core.utils.Assert;
import com.buession.lang.Status;
import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 默认转换解析器
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public class DefaultConvertResolver implements ConvertResolver {

	private Map<Class<?>, Converter> converters = new HashMap<>(25);

	private final static Logger logger = LoggerFactory.getLogger(DefaultConvertResolver.class);

	public DefaultConvertResolver(){
		register(Boolean.class, new BooleanConverter());
		register(Character.class, new CharacterConverter());
		register(String.class, new StringConverter());
		register(Status.class, new StatusConverter());

		register(Class.class, new ClassConverter());

		register(Byte.class, new ByteConverter());
		register(Float.class, new FloatConverter());
		register(Double.class, new DoubleConverter());
		register(Short.class, new ShortConverter());
		register(Integer.class, new IntegerConverter());
		register(Long.class, new LongConverter());
		register(BigDecimal.class, new BigDecimalConverter());
		register(BigInteger.class, new BigIntegerConverter());

		register(Calendar.class, new CalendarConverter());
		register(java.util.Date.class, new DateConverter());
		register(java.sql.Time.class, new SqlTimeConverter());
		register(java.sql.Date.class, new SqlDateConverter());
		register(java.sql.Timestamp.class, new SqlTimestampConverter());

		register(File.class, new FileConverter());
		register(Path.class, new PathConverter());

		register(URI.class, new URIConverter());
		register(URL.class, new URLConverter());
	}

	@Override
	public <T> void register(Class<T> clazz, Converter<T> converter){
		converters.put(clazz, converter);
	}

	@Override
	public <T> void unregister(Class<T> clazz){
		converters.remove(clazz);
	}

	@Override
	public String convert(final Object value){
		if(value == null){
			return null;
		}

		Object val = value;
		if(val.getClass().isArray()){
			if(Array.getLength(val) < 1){
				return null;
			}

			val = Array.get(value, 0);
			if(val == null){
				return null;
			}
		}

		final Converter<String> converter = lookup(String.class);
		return converter.convert(val);
	}

	@Override
	public Object convert(final Class<?> clazz, final String value){
		if(logger.isDebugEnabled()){
			logger.debug("Convert string '{}' to class '{}'.", value, clazz.getName());
		}

		Converter converter = lookup(clazz);
		if(converter == null){
			converter = lookup(String.class);
		}

		logger.trace("  Using converter {}.", converter);
		return converter.convert(value);

	}

	@Override
	public Object convert(final Class<?> clazz, final String[] values){
		Class<?> type = clazz;

		if(clazz.isArray()){
			type = clazz.getComponentType();
		}

		logger.debug("Convert String[{}] to class '{}[]'.", values.length, type.getName());

		Converter converter = lookup(type);
		if(converter == null){
			converter = lookup(String.class);
		}

		logger.trace("  Using converter {}.", converter);

		final Object array = Array.newInstance(type, values.length);

		for(int i = 0; i < values.length; i++){
			Array.set(array, i, converter.convert(values[i]));
		}

		return array;
	}

	@Override
	public Object convert(final Class<?> targetType, final Object value){
		final Class<?> sourceType = value == null ? null : value.getClass();

		if(value == null){
			logger.debug("Convert null value to type '{}'.", targetType.getName());
		}else{
			logger.debug("Convert type '{}' value '{}' to type '{}'.", sourceType.getName(), value,
					targetType.getName());
		}

		Object converted = value;
		Converter converter = lookup(sourceType, targetType);
		if(converter != null){
			logger.trace("  Using converter {}", converter);
			converted = converter.convert(value);
		}

		if(String.class.equals(targetType) && converted != null && !(converted instanceof String)){
			converter = lookup(String.class);

			if(converter != null){
				logger.trace("  Using converter {}", converter);
				converted = converter.convert(converted);
			}

			if(converted != null && !(converted instanceof String)){
				converted = converted.toString();
			}
		}

		return converted;
	}

	private <T> Converter<T> lookup(final Class<T> clazz){
		Converter<T> converter = converters.get(clazz);

		if(converter == null){
			final Class<T> targetType = ConvertUtils.primitiveToWrapper(clazz);
			converter = converters.get(targetType);
		}

		return converter;
	}

	public Converter lookup(final Class<?> sourceType, final Class<?> targetType){
		Assert.isNull(targetType, "Target type is missing");

		if(sourceType == null){
			return lookup(targetType);
		}

		Converter converter = null;
		if(targetType == String.class){
			converter = lookup(sourceType);

			if(converter == null && (sourceType.isArray() || Collection.class.isAssignableFrom(sourceType))){
				converter = lookup(String[].class);
			}

			if(converter == null){
				converter = lookup(String.class);
			}

			return converter;
		}else if(targetType == String[].class){
			if(sourceType.isArray() || Collection.class.isAssignableFrom(sourceType)){
				converter = lookup(sourceType);
			}

			if(converter == null){
				converter = lookup(String[].class);
			}

			return converter;
		}else{
			return lookup(targetType);
		}
	}

}
