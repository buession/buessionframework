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

/**
 * {@link com.buession.beans.converters.Converter} 的布尔值对象的实现，处理 <b>{@link java.lang.Boolean}</b> 对象之间的转换的实现。
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public final class BooleanConverter extends AbstractConverter<Boolean> {

	private String[] trueStrings = {"true", "yes", "y", "on", "1"};

	private String[] falseStrings = {"false", "no", "n", "off", "0"};

	public BooleanConverter(){
		super();
	}

	public BooleanConverter(final Boolean defaultValue){
		super(defaultValue);
	}

	public BooleanConverter(final String[] trueStrings, final String[] falseStrings){
		super();
		this.trueStrings = copyStrings(trueStrings);
		this.falseStrings = copyStrings(falseStrings);
	}

	public BooleanConverter(final String[] trueStrings, final String[] falseStrings, final Boolean defaultValue){
		super(defaultValue);
		this.trueStrings = copyStrings(trueStrings);
		this.falseStrings = copyStrings(falseStrings);
	}

	@Override
	public Class<Boolean> getType(){
		return Boolean.class;
	}

	@Override
	protected Boolean convertToType(Class<Boolean> type, Object value) throws Throwable{
		final String stringValue = value.toString().toLowerCase();

		for(String trueString : trueStrings){
			if(trueString.equals(stringValue)){
				return Boolean.TRUE;
			}
		}

		for(String falseString : falseStrings){
			if(falseString.equals(stringValue)){
				return Boolean.FALSE;
			}
		}

		throw conversionException(type, value);
	}

	private final static String[] copyStrings(final String[] src){
		final String[] dest = new String[src.length];

		for(int i = 0; i < src.length; ++i){
			dest[i] = src[i].toLowerCase();
		}

		return dest;
	}

}
