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
package com.buession.httpclient.helper;

import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.core.Request;
import com.buession.net.HttpURI;

import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public abstract class AbstractRequestBuilder<T extends AbstractRequestBuilder, R extends Request> implements RequestBuilder<T, R> {

	protected R request;

	protected ProtocolVersion protocolVersion;

	protected String url;

	protected List<Header> headers;

	protected Map<String, Object> parameters;

	@Override
	public T setProtocolVersion(ProtocolVersion protocolVersion){
		return null;
	}

	@Override
	public T setUrl(String url){
		return null;
	}

	@Override
	public T setHeaders(List<Header> headers){
		return null;
	}

	@Override
	public T setParameters(Map<String, Object> parameters){
		return null;
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

	protected final static String determineRequestUrl(final String url, final Map<String, Object> parameters){
		final StringBuilder sb = new StringBuilder(url.length());

		sb.append(url);

		if(Validate.isNotEmpty(parameters)){
			final String queryString = HttpURI.toQueryString(parameters, false);

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
