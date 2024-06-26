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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.core;

import com.buession.core.utils.StringUtils;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 响应头解析器抽象类
 *
 * @param <T>
 * 		原始响应头类型
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public abstract class AbstractResponseHeaderParse<T> implements ResponseHeaderParse<T> {

	@Override
	public List<Header> parse(final T headers){
		if(headers == null){
			return null;
		}

		final Multimap<String, String> headersMap = HashMultimap.create();

		doParse(headers, headersMap);

		return Collections.unmodifiableList(
				headersMap.keySet().stream().map((name)->new Header(name, StringUtils.join(headersMap.get(name), ", ")))
						.collect(Collectors.toList()));
	}

	protected abstract void doParse(final T headers, final Multimap<String, String> headersMap);

}
