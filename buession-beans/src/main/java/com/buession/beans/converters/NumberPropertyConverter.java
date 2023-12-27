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
package com.buession.beans.converters;

import com.buession.beans.AbstractPropertyConverter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

/**
 * {@link Number} 类型 Bean 属性转换器
 *
 * @author Yong.Teng
 * @since 2.3.1
 */
public abstract class NumberPropertyConverter<T extends Number> extends AbstractPropertyConverter<T> {

	private final static Integer ZERO = 0;

	private final static Integer ONE = 1;

	@Override
	protected T convertToType(final Class<?> sourceType, final Object value, final Class<T> targetType) {
		// Handle Number
		if(value instanceof Number){
			return toNumber(sourceType, targetType, (Number) value);
		}

		// Handle Boolean
		if(value instanceof Boolean){
			return toNumber(sourceType, targetType, (Boolean) value ? ONE : ZERO);
		}

		// Handle Calendar --> Long
		if(value instanceof Calendar && Long.class.equals(targetType)){
			return targetType.cast(((Calendar) value).getTime().getTime());
		}

		// Handle java.sql.Timestamp --> Long
		if(value instanceof java.sql.Timestamp && Long.class.equals(targetType)){
			final java.sql.Timestamp timestamp = (java.sql.Timestamp) value;
			long timeInMillis = (timestamp.getTime() / 1000) * 1000;
			timeInMillis += timestamp.getNanos() / 1000000;

			return targetType.cast(timeInMillis);
		}

		// Handle Date --> Long
		if(value instanceof Date && Long.class.equals(targetType)){
			return targetType.cast(((Date) value).getTime());
		}

		// Handle LocalDate --> Long
		if(value instanceof LocalDate && Long.class.equals(targetType)){
			return targetType.cast(((LocalDate) value).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli());
		}

		// Handle LocalDateTime --> Long
		if(value instanceof LocalDateTime && Long.class.equals(targetType)){
			return targetType.cast(
					((LocalDateTime) value).toInstant(ZoneOffset.of(ZoneOffset.systemDefault().getId()))
							.toEpochMilli());
		}

		// Handle Instant --> Long
		if(value instanceof Instant && Long.class.equals(targetType)){
			return targetType.cast(((Instant) value).toEpochMilli());
		}

		// Handle CharSequence --> Long
		if(value instanceof CharSequence){
			return targetType.cast(strToNumber(sourceType, targetType, value.toString()));
		}

		return null;
	}

	protected abstract T toNumber(final Class<?> sourceType, final Class<T> targetType, final Number value);

	protected abstract T strToNumber(final Class<?> sourceType, final Class<T> targetType, final String value);

}
