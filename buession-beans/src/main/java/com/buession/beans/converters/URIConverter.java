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

import java.net.URI;
import java.net.URL;

/**
 * {@link com.buession.beans.converters.Converter} 的 URL 对象的实现，处理 <b>{@link java.net.URI}</b> 对象之间的转换的实现。
 *
 * @author Yong.Teng
 * @since 1.2.0
 */
public class URIConverter extends AbstractConverter<URI> {

	public URIConverter(){
		super();
	}

	public URIConverter(URI defaultValue){
		super(defaultValue);
	}

	@Override
	public Class<URI> getType(){
		return URI.class;
	}

	@Override
	protected URI convertToType(final Class<URI> type, final Object value) throws Throwable{
		if(URI.class.equals(type)){
			return type.cast(new URI(value.toString()));
		}

		throw conversionException(type, value);
	}

}
