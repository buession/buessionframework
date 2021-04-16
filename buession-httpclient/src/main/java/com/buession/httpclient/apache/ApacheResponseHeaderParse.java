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
package com.buession.httpclient.apache;

import com.buession.httpclient.core.Header;
import com.buession.httpclient.helper.AbstractResponseHeaderParse;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Apache HttpClient 响应头解析器
 *
 * @author Yong.Teng
 * @since 1.2.1
 */
public class ApacheResponseHeaderParse extends AbstractResponseHeaderParse<org.apache.http.Header[]> {

	/**
	 * 构造函数
	 *
	 * @param headers
	 * 		原始响应头
	 */
	public ApacheResponseHeaderParse(final org.apache.http.Header[] headers){
		super(headers);
	}

	@Override
	public List<Header> parse(){
		if(headers == null){
			return null;
		}else if(headers.length == 0){
			return Collections.emptyList();
		}

		Map<String, String> temp = new LinkedHashMap<>(headers.length);

		for(org.apache.http.Header header : headers){
			if(header.getElements() != null){
				String oldValue = temp.get(header.getName());
				parseHeaders(temp, header.getName(), oldValue, header.getValue());
			}
		}

		return convertList(temp);
	}
}
