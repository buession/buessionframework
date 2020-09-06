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

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * {@link com.buession.beans.converters.Converter} 的 Byte 对象的实现，处理 <b>{@link Integer}</b> 对象之间的转换的实现。
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public final class BigDecimalConverter extends AbstractNumberConverter<java.math.BigDecimal> {

	public BigDecimalConverter(){
		super(false);
	}

	public BigDecimalConverter(final BigDecimal defaultValue){
		super(false, defaultValue);
	}

	@Override
	public Class<BigDecimal> getType(){
		return BigDecimal.class;
	}

	@Override
	protected BigDecimal toNumber(final Class<?> sourceType, final Class<BigDecimal> targetType, final Number value) throws ConversionException{
		BigDecimal result = super.toNumber(sourceType, targetType, value);

		if(result == null){
			if(targetType.equals(BigDecimal.class)){
				if(value instanceof Float || value instanceof Double){
					return targetType.cast(new BigDecimal(value.toString()));
				}else if(value instanceof BigInteger){
					return targetType.cast(new BigDecimal((BigInteger) value));
				}else if(value instanceof BigDecimal){
					return targetType.cast(new BigDecimal(value.toString()));
				}else{
					return targetType.cast(BigDecimal.valueOf(value.longValue()));
				}
			}
		}

		final String message = toString(getClass()) + " cannot handle conversion to '" + toString(targetType) + "'";
		logger.warn("    " + message);
		throw new ConversionException(message);
	}

}
