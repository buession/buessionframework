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

/**
 * {@link com.buession.beans.converters.Converter} 的 Byte 对象的实现，处理 <b>{@link java.lang.Integer}</b> 对象之间的转换的实现。
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public final class IntegerConverter extends AbstractNumberConverter<Integer> {

	public IntegerConverter(){
		super(false);
	}

	public IntegerConverter(final Integer defaultValue){
		super(false, defaultValue);
	}

	@Override
	public Class<Integer> getType(){
		return Integer.class;
	}

	@Override
	protected Integer toNumber(final Class<?> sourceType, final Class<Integer> targetType, final Number value) throws ConversionException{
		Integer result = super.toNumber(sourceType, targetType, value);

		if(result == null){
			if(targetType.equals(Integer.class)){
				final long longValue = value.longValue();

				if(longValue > Integer.MAX_VALUE){
					throw new ConversionException(toString(sourceType) + " value '" + value + "' is too large for " + toString(targetType));
				}

				if(longValue < Integer.MIN_VALUE){
					throw new ConversionException(toString(sourceType) + " value '" + value + "' is too small " + toString(targetType));
				}

				return targetType.cast(new Integer(value.intValue()));
			}
		}

		final String message = toString(getClass()) + " cannot handle conversion to '" + toString(targetType) + "'";
		logger.warn("    " + message);
		throw new ConversionException(message);
	}

}
