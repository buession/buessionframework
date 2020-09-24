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
package com.buession.beans.converters;

import com.buession.core.exception.ConversionException;
import com.buession.core.utils.Assert;
import com.buession.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于 {@link com.buession.beans.converters.Converter} 的实现，提供处理与指定类型之间的转换的结构。
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public abstract class AbstractConverter<T> implements Converter<T> {

	protected final static Logger logger = LoggerFactory.getLogger(AbstractConverter.class);

	/**
	 * 构造函数
	 */
	public AbstractConverter(){
	}

	@Override
	public T convert(final Class<?> type, final Object value) throws ConversionException{
		Assert.isNull(value, "Convert object cloud not be null.");

		Class<?> sourceType = value.getClass();
		Class<?> targetType = type;

		try{
			return convertToType(sourceType, targetType, value);
		}catch(Throwable t){
			return handleError(targetType, value, t);
		}
	}

	@Override
	public String toString(){
		return toString(getClass());
	}

	protected static String toString(final Class<?> type){
		String typeName;

		if(type == null){
			typeName = "null";
		}else if(type.isArray()){
			Class<?> elementType = type.getComponentType();

			int count = 1;
			while(elementType.isArray()){
				elementType = elementType.getComponentType();
				count++;
			}

			typeName = elementType.getName() + StringUtils.repeat("[]", count);
		}else{
			typeName = type.getName();
		}

		if(typeName.startsWith("java.lang.")){
			typeName = typeName.substring("java.lang.".length());
		}else if(typeName.startsWith("java.util.")){
			typeName = typeName.substring("java.util.".length());
		}else if(typeName.startsWith("java.math.")){
			typeName = typeName.substring("java.math.".length());
		}

		return typeName;
	}

	protected abstract T convertToType(final Class<?> sourceType, final Class<?> targetType, final Object value) throws Throwable;

	protected String convertToString(final Object value) throws Throwable{
		return value.toString();
	}

	protected T handleMissing(final Class<?> targetType){
		final ConversionException ex =
				new ConversionException("No value specified for '" + toString(targetType) + "'" + ".");

		if(logger.isDebugEnabled()){
			logger.debug("    Throwing ConversionException: {}.", ex.getMessage());
		}

		throw ex;
	}

	protected T handleError(final Class<?> targetType, final Object value, final Throwable cause){
		if(logger.isDebugEnabled()){
			if(cause instanceof ConversionException){
				logger.debug("    Conversion throw ConversionException: {}.", cause.getMessage());
			}else{
				logger.debug("    Conversion throw {}.", cause.getMessage());
			}
		}

		ConversionException ex;
		if(cause instanceof ConversionException){
			ex = (ConversionException) cause;
			if(logger.isDebugEnabled()){
				logger.debug("    Throwing ConversionException: {}.", ex.getMessage());
			}
		}else{
			final String msg =
					"Error converting from '" + toString(value.getClass()) + "' to '" + toString(targetType) + "' " + cause.getMessage();

			ex = new ConversionException(msg, cause);
			if(logger.isDebugEnabled()){
				logger.debug("    Throwing ConversionException: {}.", msg);
			}
		}

		throw ex;
	}

	protected ConversionException conversionException(final Class<?> type, final Object value){
		return new ConversionException("Can't convert value '" + value + "' to type " + type + ".");
	}

}
