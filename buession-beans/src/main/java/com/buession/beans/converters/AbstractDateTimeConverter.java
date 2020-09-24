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
import com.buession.core.utils.ArrayUtils;
import com.buession.core.validator.Validate;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * {@link com.buession.beans.converters.Converter} 的数值对象的实现，处理 <b>{@link java.util.Date}</b> 对象之间的转换的实现。
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public abstract class AbstractDateTimeConverter<T> extends AbstractConverter<T> {

	private boolean useLocaleFormat;

	private Locale locale;

	private TimeZone timeZone;

	private String[] patterns;

	public AbstractDateTimeConverter(){
		super();
	}

	/**
	 * 返回是否使用格式转换日期时间
	 *
	 * @return 是否使用格式转换日期时间
	 */
	public boolean isUseLocaleFormat(){
		return useLocaleFormat;
	}

	/**
	 * 设置是否使用格式转换日期时间
	 *
	 * @param useLocaleFormat
	 * 		是否使用格式转换日期时间
	 */
	public void setUseLocaleFormat(boolean useLocaleFormat){
		this.useLocaleFormat = useLocaleFormat;
	}

	/**
	 * 返回日期时间转换本地化对象 {@link java.util.Locale}
	 *
	 * @return 日期时间转换本地化对象
	 */
	public Locale getLocale(){
		return locale;
	}

	/**
	 * 设置日期时间转换本地化对象 {@link java.util.Locale}
	 *
	 * @param locale
	 * 		日期时间转换本地化对象
	 */
	public void setLocale(Locale locale){
		this.locale = locale;
		setUseLocaleFormat(true);
	}

	/**
	 * 返回日期时间转换时区
	 *
	 * @return 日期时间转换时区
	 */
	public TimeZone getTimeZone(){
		return timeZone;
	}

	/**
	 * 设置日期时间转换时区
	 *
	 * @param timeZone
	 * 		日期时间转换时区
	 */
	public void setTimeZone(TimeZone timeZone){
		this.timeZone = timeZone;
	}

	/**
	 * 返回日期时间格式模式
	 *
	 * @return 日期时间格式模式
	 */
	public String[] getPatterns(){
		return patterns;
	}

	/**
	 * 设置日期时间格式模式
	 *
	 * @param patterns
	 * 		日期时间格式模式
	 */
	public void setPatterns(String[] patterns){
		this.patterns = patterns;
		setUseLocaleFormat(true);
	}

	protected DateFormat getFormat(final Locale locale, final TimeZone timeZone){
		final DateFormat format = locale == null ? DateFormat.getDateInstance(DateFormat.SHORT) :
				DateFormat.getDateInstance(DateFormat.SHORT, locale);

		if(timeZone != null){
			format.setTimeZone(timeZone);
		}

		return format;
	}

	protected DateFormat getFormat(final String pattern){
		final DateFormat format = new SimpleDateFormat(pattern);

		if(timeZone != null){
			format.setTimeZone(timeZone);
		}

		return format;
	}

	@Override
	protected String convertToString(final Object value) throws Throwable{
		Date date = null;
		if(value instanceof Date){
			date = (Date) value;
		}else if(value instanceof Calendar){
			date = ((Calendar) value).getTime();
		}else if(value instanceof Long){
			date = new Date((Long) value);
		}

		String result;
		if(useLocaleFormat && date != null){
			final DateFormat format = Validate.isEmpty(patterns) ? getFormat(locale, timeZone) :
					getFormat(patterns[0]);

			result = format.format(date);

			logFormat("Formatting", format);
			if(logger.isDebugEnabled()){
				logger.debug("    Converted to String using format '{}'.", result);
			}
		}else{
			result = value.toString();
			if(logger.isDebugEnabled()){
				logger.debug("    Converted to String using toString() '{}'.", result);
			}
		}

		return result;
	}

	@Override
	protected T convertToType(final Class<?> sourceType, final Class<?> targetType, final Object value) throws Throwable{
		if(value instanceof java.sql.Timestamp){
			final java.sql.Timestamp timestamp = (java.sql.Timestamp) value;
			long timeInMillis = ((timestamp.getTime() / 1000) * 1000) + timestamp.getNanos() / 1000000;

			return toDate(sourceType, targetType, timeInMillis);
		}

		if(value instanceof Date){
			final Date date = (Date) value;
			return toDate(sourceType, targetType, date.getTime());
		}

		if(value instanceof Calendar){
			final Calendar calendar = (Calendar) value;
			return toDate(sourceType, targetType, calendar.getTime().getTime());
		}

		if(value instanceof Long){
			return toDate(sourceType, targetType, (Long) value);
		}

		final String stringValue = value.toString().trim();
		if(stringValue.length() == 0){
			return handleMissing(targetType);
		}

		if(useLocaleFormat){
			Calendar calendar = patterns != null && patterns.length > 0 ? parse(sourceType, targetType, stringValue) :
					parse(sourceType, targetType, stringValue, getFormat(locale, timeZone));

			return toDate(sourceType, targetType, calendar.getTime().getTime());
		}

		return toDate(sourceType, targetType, stringValue);
	}

	protected abstract T toDate(final Class<?> sourceType, final Class<?> targetType, final long value) throws ConversionException;

	protected abstract T toDate(final Class<?> sourceType, final Class<?> targetType, final String value);

	protected Calendar parse(final Class<?> sourceType, final Class<?> targetType, final String value) throws Exception{
		Exception firstEx = null;

		for(String pattern : patterns){
			try{
				return parse(sourceType, targetType, value, getFormat(pattern));
			}catch(final Exception ex){
				if(firstEx == null){
					firstEx = ex;
				}
			}
		}

		if(patterns.length > 1){
			throw new ConversionException("Error converting '" + toString(sourceType) + "' to '" + toString(targetType) + "' using patterns '" + ArrayUtils.toString(patterns) + "'.");
		}else{
			throw firstEx;
		}
	}

	protected Calendar parse(final Class<?> sourceType, final Class<?> targetType, final String value,
			final DateFormat format){
		logFormat("Parsing", format);
		format.setLenient(false);
		final ParsePosition pos = new ParsePosition(0);
		final Date parsedDate = format.parse(value, pos);

		if(pos.getErrorIndex() >= 0 || pos.getIndex() != value.length() || parsedDate == null){
			final StringBuilder sb = new StringBuilder(128);

			sb.append("Error converting '").append(toString(sourceType)).append("' to '").append(toString(targetType)).append('\'');

			if(format instanceof SimpleDateFormat){
				sb.append(" using pattern '").append(((SimpleDateFormat) format).toPattern()).append('\'');
			}

			sb.append('.');

			final String message = sb.toString();

			logger.debug("    {}", message);

			throw new ConversionException(message);
		}

		return format.getCalendar();
	}

	private void logFormat(final String action, final DateFormat format){
		if(logger.isDebugEnabled()){
			final StringBuilder sb = new StringBuilder(128);

			sb.append("    ").append(action).append(" with Format");

			if(format instanceof SimpleDateFormat){
				sb.append('[').append(((SimpleDateFormat) format).toPattern()).append(']');
			}

			sb.append(" for ");
			sb.append("locale[").append(locale == null ? "default" : locale).append(']');
			sb.append(", TimeZone[").append(timeZone == null ? "default" : timeZone).append(']');

			logger.debug(sb.toString());
		}
	}

}
