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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.helper;

import com.buession.httpclient.core.Header;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 响应头解析器抽象类
 *
 * @param <T>
 * 		原始响应头类型
 *
 * @author Yong.Teng
 * @since 1.2.1
 */
public abstract class AbstractResponseHeaderParse<T> implements ResponseHeaderParse<T> {

	protected final T headers;

	/**
	 * 构造函数
	 *
	 * @param headers
	 * 		原始响应头
	 */
	public AbstractResponseHeaderParse(final T headers){
		this.headers = headers;
	}

	protected static Map<String, String> parseHeaders(final Map<String, String> headers, final String name,
													  final String oldValue, final String newValue){
		headers.put(name, oldValue == null ? newValue : oldValue + ", " + newValue);
		return headers;
	}

	protected static List<Header> convertList(final Map<String, String> headers){
		if(headers == null){
			return null;
		}

		return headers.entrySet().stream().map((e)->new Header(e.getKey(), e.getValue())).collect(Collectors.toList());
	}

}
