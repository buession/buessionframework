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

import java.sql.Time;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

/**
 * {@link com.buession.beans.converters.Converter} 的 java.sql.Time 对象的实现，处理 <b>{@link java.sql.Time}</b> 对象之间的转换的实现。
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public final class SqlTimeConverter extends AbstractDateTimeConverter<Time> {

	public SqlTimeConverter(){
		super();
	}

	public SqlTimeConverter(final Time defaultValue){
		super(defaultValue);
	}

	@Override
	public Class<Time> getType(){
		return Time.class;
	}

	@Override
	protected DateFormat getFormat(final Locale locale, final TimeZone timeZone){
		DateFormat format = locale == null ? DateFormat.getTimeInstance(DateFormat.SHORT) :
				DateFormat.getTimeInstance(DateFormat.SHORT, locale);

		if(timeZone != null){
			format.setTimeZone(timeZone);
		}

		return format;
	}

	@Override
	protected Time toDate(final Class<?> sourceType, Class<Time> targetType, long value){
		if(targetType.equals(Time.class)){
			return targetType.cast(new Time(value));
		}

		throw cannotHandleConversion(sourceType, targetType);
	}

	@Override
	protected Time toDate(final Class<?> sourceType, final Class<Time> targetType, final String value){
		if(targetType.equals(Time.class)){
			try{
				return targetType.cast(Time.valueOf(value));
			}catch(final IllegalArgumentException e){
				throw new ConversionException("String must be in JDBC format [HH:mm:ss] to create a java.sql.Time.");
			}
		}

		throw cannotHandleConversion(sourceType, targetType);
	}

}
