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
 * | Copyright @ 2013-2023 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.core;

import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import com.buession.net.HttpURI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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

	protected URI uri;

	protected Map<String, Object> parameters;

	private final static Logger logger = LoggerFactory.getLogger(AbstractRequestBuilder.class);

	@Override
	public RequestBuilder<R> setProtocolVersion(ProtocolVersion protocolVersion){
		return this;
	}

	@Override
	public RequestBuilder<R> setUrl(String url){
		return setUri(URI.create(url));
	}

	@Override
	public RequestBuilder<R> setUrl(URL url){
		try{
			uri = url.toURI();
		}catch(URISyntaxException e){
			if(logger.isErrorEnabled()){
				logger.error("URL {} convert to URI syntax: {}, reason: {}", url, e.getMessage(), e.getReason());
			}
			throw new IllegalArgumentException(e.getMessage(), e);
		}

		return this;
	}

	@Override
	public RequestBuilder<R> setUri(URI uri){
		this.uri = uri;
		return this;
	}

	@Override
	public RequestBuilder<R> setHeaders(List<Header> headers){
		request.setHeaders(headers);
		return this;
	}

	@Override
	public RequestBuilder<R> setParameters(Map<String, Object> parameters){
		this.parameters = parameters;
		return this;
	}

	@Override
	public R build(){
		if(Validate.isNotEmpty(parameters)){
			request.setUri(determineRequestUri(uri, parameters));
		}else{
			request.setUri(uri);
		}

		return request;
	}

	private static URI determineRequestUri(final URI uri, final Map<String, Object> parameters){
		if(Validate.isEmpty(uri.getRawQuery())){
			return uri;
		}

		final StringBuilder newQuery = new StringBuilder(uri.getRawQuery().length());

		newQuery.append(uri.getRawQuery());

		if(StringUtils.endsWith(uri.getRawQuery(), '&') == false){
			newQuery.append('&');
		}

		newQuery.append(HttpURI.toQueryString(parameters, false));

		try{
			return new URI(uri.getScheme(), uri.getAuthority(), uri.getHost(), uri.getPort(),
					uri.getPath(), newQuery.toString(), uri.getFragment());
		}catch(URISyntaxException e){
			if(logger.isErrorEnabled()){
				logger.error("URL {} add parameters syntax: {}, reason: {}", uri, e.getMessage(), e.getReason());
			}
			return uri;
		}
	}

	protected static <S, T> RequestBodyConverter<S, T> findBodyConverter(
			final Map<Class<? extends RequestBody>, RequestBodyConverter> converters,
			final RequestBody<?> body){
		RequestBodyConverter<S, T> converter;
		Class<?> clazz = body.getClass();

		while(clazz != null){
			converter = converters.get(clazz);
			if(converter != null){
				return converter;
			}

			clazz = clazz.getSuperclass();
		}

		return null;
	}

}
