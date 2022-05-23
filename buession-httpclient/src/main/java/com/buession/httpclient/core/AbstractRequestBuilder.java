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
 * | Copyright @ 2013-2022 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.core;

import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import com.buession.net.HttpURI;

import java.util.List;
import java.util.Map;

/**
 * 请求构建器抽象类
 *
 * @param <R>
 *        {@link Request} 具体实现
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public abstract class AbstractRequestBuilder<R extends Request> implements RequestBuilder<R> {

	protected R request;

	protected ProtocolVersion protocolVersion;

	protected String url;

	protected List<Header> headers;

	protected Map<String, Object> parameters;

	@Override
	public RequestBuilder<R> setProtocolVersion(ProtocolVersion protocolVersion){
		return this;
	}

	@Override
	public RequestBuilder<R> setUrl(String url){
		return this;
	}

	@Override
	public RequestBuilder<R> setHeaders(List<Header> headers){
		return this;
	}

	@Override
	public RequestBuilder<R> setParameters(Map<String, Object> parameters){
		return this;
	}

	@Override
	public R build(){
		if(Validate.isNotEmpty(parameters)){
			request.setUrl(determineRequestUrl(url, parameters));
		}else{
			request.setUrl(url);
		}

		request.setHeaders(headers);

		return request;
	}

	protected static String determineRequestUrl(final String url, final Map<String, Object> parameters){
		final StringBuilder sb = new StringBuilder(url.length());

		sb.append(url);

		if(Validate.isNotEmpty(parameters)){
			final String queryString = HttpURI.toQueryString(parameters, true);

			if(url.contains("?")){
				if(StringUtils.endsWith(url, '&') == false){
					sb.append('&');
				}
			}else{
				sb.append('?');
			}

			sb.append(queryString);
		}

		return sb.toString();
	}

}
