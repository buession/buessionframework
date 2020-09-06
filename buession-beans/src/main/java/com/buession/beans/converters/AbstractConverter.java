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

	protected static final String DEFAULT_CONFIG_MESSAGE = "N.B. Converters can be configured to use default values " +
			"to" + " avoid throwing exceptions";

	protected boolean useDefault = false;

	protected T defaultValue = null;

	protected final static Logger logger = LoggerFactory.getLogger(AbstractConverter.class);

	/**
	 * 构造函数
	 */
	public AbstractConverter(){
	}

	/**
	 * 构造函数
	 *
	 * @param defaultValue
	 * 		默认值
	 */
	public AbstractConverter(final T defaultValue){
		setDefaultValue(defaultValue);
	}

	/**
	 * 是否使用了默认值
	 *
	 * @return 当使用了默认值时，返回 true；否则，返回 false
	 */
	public boolean isUseDefault(){
		return useDefault;
	}

	/**
	 * 返回当前转换器所转换的类型
	 *
	 * @return 当前转换器所转换的类型
	 */
	public abstract Class<T> getType();

	@Override
	public T convert(final Object value) throws ConversionException{
		Assert.isNull(value, "Convert object cloud not be null.");

		Class<?> sourceType = value.getClass();
		final Class<T> targetType = getType();

		if(logger.isDebugEnabled()){
			logger.debug("Converting{} value '" + value + "' to type '{}'.", value == null ? "" :
					" '" + toString(sourceType) + "'", toString(targetType));
		}

		try{
			if(targetType.equals(String.class)){
				return targetType.cast(convertToString(value));
			}else if(targetType.equals(sourceType)){
				if(logger.isDebugEnabled()){
					logger.debug("    No conversion required, value is already a {}.", toString(targetType));
				}

				return targetType.cast(value);
			}else{
				final Object result = convertToType(targetType, value);

				if(logger.isDebugEnabled()){
					logger.debug("    Converted to {} value '{}'.", toString(targetType), result);
				}

				return targetType.cast(result);
			}
		}catch(Throwable t){
			return handleError(targetType, value, t);
		}
	}

	protected abstract T convertToType(Class<T> type, Object value) throws Throwable;

	protected String convertToString(final Object value) throws Throwable{
		return value.toString();
	}

	@Override
	public String toString(){
		return toString(getClass()) + "[UseDefault=" + useDefault + "]";
	}

	protected T getDefaultValue(){
		return defaultValue;
	}

	protected void setDefaultValue(final T defaultValue){
		logger.debug("Setting default value: {}", defaultValue);
		this.defaultValue = defaultValue == null ? null : defaultValue;
		useDefault = true;
	}

	protected T handleMissing(final Class<T> type){
		if(useDefault || type.equals(String.class)){
			Object value = getDefaultValue();

			if(useDefault && value != null && type.equals(value.getClass()) == false){
				try{
					value = convertToType(type, defaultValue);
				}catch(final Throwable t){
					throw new ConversionException("Default conversion to " + toString(type) + " failed.", t);
				}
			}

			if(logger.isDebugEnabled()){
				logger.debug("    Using default {}value '{}'.", value == null ? "" : toString(value.getClass()) + " ",
						defaultValue);
			}

			return type.cast(value);
		}

		final ConversionException cex = new ConversionException("No value specified for '" + toString(type) + "'");

		if(logger.isDebugEnabled()){
			logger.debug("    Throwing ConversionException: {}.", cex.getMessage());
			logger.debug("    {}.", DEFAULT_CONFIG_MESSAGE);
		}

		throw cex;

	}

	protected T handleError(final Class<T> type, final Object value, final Throwable cause){
		if(logger.isDebugEnabled()){
			if(cause instanceof ConversionException){
				logger.debug("    Conversion throw ConversionException: {}.", cause.getMessage());
			}else{
				logger.debug("    Conversion throw {}.", cause.getMessage());
			}
		}

		if(useDefault){
			return handleMissing(type);
		}

		ConversionException cex;
		if(cause instanceof ConversionException){
			cex = (ConversionException) cause;
			if(logger.isDebugEnabled()){
				logger.debug("    Re-throwing ConversionException: {}.", cex.getMessage());
				logger.debug("    {}.", DEFAULT_CONFIG_MESSAGE);
			}
		}else{
			final String msg = "Error converting from '" + toString(value.getClass()) + "' to '" + toString(type) +
					"'" + " " + cause.getMessage();

			cex = new ConversionException(msg, cause);
			if(logger.isDebugEnabled()){
				logger.debug("    Throwing ConversionException: {}.", msg);
				logger.debug("    {}.", DEFAULT_CONFIG_MESSAGE);
			}
		}

		throw cex;

	}

	protected ConversionException cannotHandleConversion(final Class<?> sourceType, final Class<T> targetType){
		final String message =
				toString(getClass()) + " cannot handle conversion to '" + toString(targetType) + "' " + "from " + toString(sourceType) + ".";

		if(logger.isWarnEnabled()){
			logger.warn("    {}", message);
		}

		return new ConversionException(message);
	}

	protected ConversionException conversionException(final Class<?> type, final Object value){
		return new ConversionException("Can't convert value '" + value + "' to type " + type);
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

}
