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

import org.apache.commons.beanutils.ConversionException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * {@link com.buession.beans.converters.Converter} 的数值对象的实现，处理 <b>{@link java.lang.Number}</b> 对象之间的转换的实现。
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public abstract class AbstractNumberConverter<T extends Number> extends AbstractConverter<T> {

	protected final static Integer ZERO = new Integer(0);

	protected final static Integer ONE = new Integer(1);

	private final boolean allowDecimals;

	private String pattern;

	private boolean useLocaleFormat;

	private Locale locale;

	public AbstractNumberConverter(final boolean allowDecimals){
		super();
		this.allowDecimals = allowDecimals;
	}

	public AbstractNumberConverter(final boolean allowDecimals, final T defaultValue){
		super(defaultValue);
		this.allowDecimals = allowDecimals;
	}

	/**
	 * 返回从 {@link java.lang.String} 转换为 {@link java.lang.Number} 或者从 {@link java.lang.Number} 转换为 {@link
	 * java.lang.String} 的数字格式模式
	 *
	 * @return {@link java.lang.String} 转换为 {@link java.lang.Number} 或者从 {@link java.lang.Number} 转换为 {@link
	 * java.lang.String} 的数字格式模式
	 */
	public String getPattern(){
		return pattern;
	}

	/**
	 * 设置从 {@link java.lang.String} 转换为 {@link java.lang.Number} 或者从 {@link java.lang.Number} 转换为 {@link
	 * java.lang.String} 的数字格式模式
	 *
	 * @param pattern
	 * 		从 {@link java.lang.String} 转换为 {@link java.lang.Number} 或者从 {@link java.lang.Number} 转换为 {@link
	 *        java.lang.String} 的数字格式模式
	 */
	public void setPattern(String pattern){
		this.pattern = pattern;
	}

	/**
	 * 返回是否使用格式转换数字
	 *
	 * @return 是否使用格式转换数字
	 */
	public boolean isUseLocaleFormat(){
		return useLocaleFormat;
	}

	/**
	 * 设置是否使用格式转换数字
	 *
	 * @param useLocaleFormat
	 * 		是否使用格式转换数字
	 */
	public void setUseLocaleFormat(boolean useLocaleFormat){
		this.useLocaleFormat = useLocaleFormat;
	}

	/**
	 * 返回数字转换本地化对象 {@link java.util.Locale}
	 *
	 * @return 数字转换本地化对象
	 */
	public Locale getLocale(){
		return locale;
	}

	/**
	 * 设置数字转换本地化对象 {@link java.util.Locale}
	 *
	 * @param locale
	 * 		数字转换本地化对象
	 */
	public void setLocale(Locale locale){
		this.locale = locale;
	}

	protected NumberFormat getFormat(){
		NumberFormat format;

		if(pattern != null){
			if(locale == null){
				format = new DecimalFormat(pattern);
				logger.debug("    Using pattern '{}'.", pattern);
			}else{
				final DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);

				format = new DecimalFormat(pattern, symbols);
				logger.debug("    Using pattern '{}' with Locale[{}].", pattern, locale);
			}
		}else{
			if(locale == null){
				format = NumberFormat.getInstance();
				logger.debug("    Using default Locale format.");
			}else{
				format = NumberFormat.getInstance(locale);
				logger.debug("    Using Locale[{}] format.", locale);
			}
		}

		if(allowDecimals == false){
			format.setParseIntegerOnly(true);
		}

		return format;
	}

	@Override
	protected String convertToString(final Object value) throws Throwable{
		String result;

		if(useLocaleFormat && value instanceof Number){
			final NumberFormat format = getFormat();

			format.setGroupingUsed(false);
			result = format.format(value);

			logger.debug("    Converted  to String using format '{}'.", result);
		}else{
			result = value.toString();
			logger.debug("    Converted  to String using toString() '{}'", result);
		}

		return result;
	}

	@Override
	protected T convertToType(final Class<T> targetType, final Object value) throws Throwable{
		final Class<?> sourceType = value.getClass();

		// Handle Number
		if(value instanceof Number){
			return toNumber(sourceType, targetType, (Number) value);
		}

		// Handle Boolean
		if(value instanceof Boolean){
			return toNumber(sourceType, targetType, ((Boolean) value).booleanValue() ? ONE : ZERO);
		}

		// Handle Date --> Long
		if(value instanceof Date && Long.class.equals(targetType)){
			return targetType.cast(new Long(((Date) value).getTime()));
		}

		// Handle Calendar --> Long
		if(value instanceof Calendar && Long.class.equals(targetType)){
			return targetType.cast(new Long(((Calendar) value).getTime().getTime()));
		}

		// Convert all other types to String & handle
		final String stringValue = value.toString().trim();
		if(stringValue.length() == 0){
			return handleMissing(targetType);
		}

		// Convert/Parse a String
		Number number = null;
		if(useLocaleFormat){
			final NumberFormat format = getFormat();
			number = parse(sourceType, targetType, stringValue, format);
		}else{
			logger.debug("    No NumberFormat, using default conversion.");
			number = toNumber(sourceType, targetType, stringValue);
		}

		// Ensure the correct number type is returned
		return toNumber(sourceType, targetType, number);
	}

	protected T toNumber(final Class<?> sourceType, final Class<T> targetType, final Number value) throws ConversionException{
		if(targetType.equals(value.getClass())){
			return targetType.cast(value);
		}

		return null;
	}

	protected T toNumber(final Class<?> sourceType, final Class<T> targetType, final String value) throws ConversionException{
		throw cannotHandleConversion(sourceType, targetType);
	}

	protected Number parse(final Class<?> sourceType, final Class<T> targetType, final String value,
			final NumberFormat format){
		final ParsePosition pos = new ParsePosition(0);
		final Number result = format.parse(value, pos);

		if(pos.getErrorIndex() >= 0 || pos.getIndex() != value.length() || result == null){
			String message = "Error converting from '" + toString(sourceType) + "' to '" + toString(targetType) + "'";

			if(format instanceof DecimalFormat){
				message += " using pattern '" + ((DecimalFormat) format).toPattern() + "'";
			}
			if(locale != null){
				message += " for locale=[" + locale + "]";
			}
			message += ".";

			logger.debug("    " + message);

			throw new ConversionException(message);
		}

		return result;
	}

}
