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

import java.util.Calendar;

/**
 * {@link Calendar} 类型 Bean 属性转换器
 *
 * @author Yong.Teng
 * @since 2.3.1
 */
public final class CalendarPropertyConverter extends DateTimePropertyConverter<Calendar> {

	@Override
	protected Calendar toDate(final Class<Calendar> targetType, final Class<?> sourceType, final long value) {
		Calendar calendar;

		if(getTimeZone() != null && getLocale() != null){
			calendar = Calendar.getInstance(getTimeZone(), getLocale());
		}else if(getTimeZone() != null){
			calendar = Calendar.getInstance(getTimeZone());
		}else if(getLocale() != null){
			calendar = Calendar.getInstance(getLocale());
		}else{
			calendar = Calendar.getInstance();
		}

		calendar.setTimeInMillis(value);
		calendar.setLenient(false);

		return calendar;
	}

}
