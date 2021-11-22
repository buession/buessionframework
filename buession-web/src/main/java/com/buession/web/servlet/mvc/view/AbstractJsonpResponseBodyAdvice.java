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
package com.buession.web.servlet.mvc.view;

import com.buession.core.validator.Validate;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJsonpResponseBodyAdvice extends AbstractMappingJacksonResponseBodyAdvice {

	protected final static Pattern CALLBACK_PARAM_PATTERN = Pattern.compile("[\\dA-Za-z_\\.]*");

	protected final static MediaType CONTENT_TYPE = new MediaType("application", "javascript");

	private MediaType mediaType = CONTENT_TYPE;

	private String[] jsonpQueryParamNames = {"callback"};

	public AbstractJsonpResponseBodyAdvice(String... queryParamNames){
		super();

		if(Validate.isNotEmpty(queryParamNames)){
			this.jsonpQueryParamNames = queryParamNames;
		}
	}

	public MediaType getMediaType(){
		return mediaType;
	}

	public void setMediaType(final MediaType mediaType){
		this.mediaType = mediaType;
	}

	public String[] getJsonpQueryParamNames(){
		return jsonpQueryParamNames;
	}

	public void setJsonpQueryParamNames(final String[] jsonpQueryParamNames){
		this.jsonpQueryParamNames = jsonpQueryParamNames;
	}

	@Override
	protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType, MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response){
		HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();

		for(String name : getJsonpQueryParamNames()){
			String value = servletRequest.getParameter(name);

			if(Validate.hasText(value) && isValidJsonpQueryParam(value)){
				response.getHeaders().setContentType(getMediaType());
				bodyContainer.setValue(value);
				break;
			}
		}
	}

	protected boolean isValidJsonpQueryParam(String value){
		return CALLBACK_PARAM_PATTERN.matcher(value).matches();
	}

}
