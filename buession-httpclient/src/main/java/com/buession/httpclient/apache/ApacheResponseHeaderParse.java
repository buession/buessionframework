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
package com.buession.httpclient.apache.response;

import com.buession.httpclient.core.Header;
import com.buession.httpclient.response.AbstractResponseHeaderParse;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.Collections;
import java.util.List;

/**
 * Apache HttpClient 响应头解析器
 *
 * @author Yong.Teng
 * @since 1.2.1
 */
class ApacheResponseHeaderParse extends AbstractResponseHeaderParse<org.apache.http.Header[]> {

	@Override
	public List<Header> parse(final org.apache.http.Header[] headers){
		if(headers == null){
			return null;
		}else if(headers.length == 0){
			return Collections.emptyList();
		}

		final Multimap<String, String> headerMaps = HashMultimap.create();

		for(org.apache.http.Header header : headers){
			if(header.getElements() != null){
				headerMaps.put(header.getName(), header.getValue());
			}
		}

		return convertList(headerMaps);
	}

}
