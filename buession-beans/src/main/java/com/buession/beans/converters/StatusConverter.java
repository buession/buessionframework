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

import com.buession.lang.Status;

/**
 * {@link com.buession.beans.converters.Converter} 的 com.buession.lang.Status 对象的实现，处理 <b>{@link
 * com.buession.lang.Status}</b> 对象之间的转换的实现。
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public final class StatusConverter extends AbstractConverter<Status> {

	private String[] successStrings = {"success", "1"};

	private String[] failureStrings = {"failure", "0"};

	public StatusConverter(){
		super();
	}

	public StatusConverter(final Status defaultValue){
		super(defaultValue);
	}

	public StatusConverter(final String[] successStrings, final String[] falseStrings){
		super();
		this.successStrings = copyStrings(successStrings);
		this.failureStrings = copyStrings(falseStrings);
	}

	public StatusConverter(final String[] successStrings, final String[] falseStrings, final Status defaultValue){
		super(defaultValue);
		this.successStrings = copyStrings(successStrings);
		this.failureStrings = copyStrings(falseStrings);
	}

	@Override
	public Class<Status> getType(){
		return Status.class;
	}

	@Override
	protected Status convertToType(Class<Status> type, Object value) throws Throwable{
		final String stringValue = value.toString().toLowerCase();

		for(String successString : successStrings){
			if(successString.equals(stringValue)){
				return Status.SUCCESS;
			}
		}

		for(String failureString : failureStrings){
			if(failureString.equals(stringValue)){
				return Status.FAILURE;
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
