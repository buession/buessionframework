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

import java.util.Calendar;
import java.util.Date;

/**
 * {@link com.buession.beans.converters.Converter} 的 Long 对象的实现，处理 <b>{@link java.lang.Long}</b> 对象之间的转换的实现。
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public final class LongConverter extends AbstractNumberConverter<Long> {

	public LongConverter(){
		super(false);
	}

	@Override
	protected Long toNumber(final Class<?> sourceType, final Class<?> targetType, final Number value) throws ConversionException{
		return value.longValue();
	}

	@Override
	protected Long toNumber(final Class<?> sourceType, final Class<?> targetType, final String value) throws ConversionException{
		return new Long(value);
	}

	@Override
	protected Long parseDate(final Class<?> targetType, final Date value){
		return value.getTime();
	}

	@Override
	protected Long parseCalendar(final Class<?> targetType, final Calendar calendar){
		return calendar.getTime().getTime();
	}

}
