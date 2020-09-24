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

import com.buession.aop.resolver.SpringAnnotationResolver;
import com.buession.core.exception.ConversionException;
import com.buession.core.utils.ArrayUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * {@link com.buession.beans.converters.Converter} 的日历对象的实现，处理 <b>{@link java.util.Date}</b> 对象之间的转换的实现。
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public final class DateConverter extends AbstractDateTimeConverter<Date> {

	private final static String[] FORMATS = new String[]{"yyyy-MM-dd'T'HH:mm:ss.SSSX", "yyyy-MM-dd'T'HH:mm:ss.SSS",
			"EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss"};

	private SpringAnnotationResolver annotationResolver = new SpringAnnotationResolver();

	public DateConverter(){
		super();
	}

	@Override
	protected Date toDate(final Class<?> sourceType, final Class<?> targetType, final long value){
		return new Date(value);
	}

	@Override
	protected Date toDate(final Class<?> sourceType, final Class<?> targetType, final String value){
		Date result;

		for(String format : FORMATS){
			result = toDate(format, value);
			if(result != null){
				return result;
			}
		}

		throw new ConversionException("String must be in format ['" + ArrayUtils.toString(FORMATS, "', '") + "'] " +
				"to create a java.util.Date.");
	}

	private Date toDate(final String format, final String value){
		final SimpleDateFormat sdf = new SimpleDateFormat(format);

		try{
			return sdf.parse(value);
		}catch(ParseException e){
			return null;
		}
	}

}
