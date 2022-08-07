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
package com.buession.web.reactive.context.request;

import com.buession.core.utils.Assert;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.util.NumberUtils;
import org.springframework.web.context.request.AbstractRequestAttributes;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Yong.Teng
 * @since 2.1.0
 */
public class ReactiveRequestAttributes extends AbstractRequestAttributes {

	/**
	 * Constant identifying the {@link String} prefixed to the name of a
	 * destruction callback when it is stored in a {@link WebSession}.
	 */
	public static final String DESTRUCTION_CALLBACK_NAME_PREFIX =
			ReactiveRequestAttributes.class.getName() + ".DESTRUCTION_CALLBACK.";

	protected static final Set<Class<?>> immutableValueTypes = new HashSet<>(16);

	private final ServerWebExchange exchange;

	private final ServerHttpRequest request;

	private final ServerHttpResponse response;

	@Nullable
	private volatile WebSession session;

	static{
		immutableValueTypes.addAll(NumberUtils.STANDARD_NUMBER_TYPES);
		immutableValueTypes.add(Boolean.class);
		immutableValueTypes.add(Character.class);
		immutableValueTypes.add(String.class);
	}

	/**
	 * Create a new ReactiveRequestAttributes instance for the given ServerWebExchange.
	 *
	 * @param exchange
	 * 		current ServerWebExchange
	 */
	public ReactiveRequestAttributes(ServerWebExchange exchange){
		Assert.isNull(exchange, "ServerWebExchange must not be null");
		this.exchange = exchange;
		this.request = exchange.getRequest();
		this.response = exchange.getResponse();
	}

	/**
	 * Exposes the native {@link ServerHttpRequest} that we're wrapping.
	 *
	 * @return The native {@link ServerHttpRequest}
	 */
	public final ServerHttpRequest getRequest(){
		return request;
	}

	/**
	 * Exposes the native {@link ServerHttpResponse} that we're wrapping (if any).
	 *
	 * @return The native {@link ServerHttpResponse}
	 */
	@Nullable
	public final ServerHttpResponse getResponse(){
		return response;
	}

	@Override
	public Object getAttribute(String name, int scope){
		if(scope == SCOPE_REQUEST){
			if(isRequestActive() == false){
				throw new IllegalStateException("Cannot ask for request attribute - request is not active anymore!");
			}

			return exchange.getAttribute(name);
		}else{
			return null;
		}
	}

	@Override
	public void setAttribute(String name, Object value, int scope){
		if(scope == SCOPE_REQUEST){
			if(isRequestActive() == false){
				throw new IllegalStateException("Cannot set request attribute - request is not active anymore!");
			}

			exchange.getAttributes().put(name, value);
		}else{
		}
	}

	@Override
	public void removeAttribute(String name, int scope){
		if(scope == SCOPE_REQUEST){
			if(isRequestActive()){
				removeRequestDestructionCallback(name);
				exchange.getAttributes().remove(name);
			}
		}else{
		}
	}

	@Override
	public String[] getAttributeNames(int scope){
		if(scope == SCOPE_REQUEST){
			if(isRequestActive() == false){
				throw new IllegalStateException("Cannot ask for request attributes - request is not active anymore!");
			}

			return exchange.getAttributes().keySet().toArray(new String[]{});
		}else{
			return new String[0];
		}
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback, int scope){
	}

	@Override
	public Object resolveReference(String key){
		if(REFERENCE_REQUEST.equals(key)){
			return request;
		}else if(REFERENCE_SESSION.equals(key)){
			return null;
		}else{
			return null;
		}
	}

	@Override
	public String getSessionId(){
		throw new IllegalStateException("Cloud not invoke in webflux");
	}

	@Override
	public Object getSessionMutex(){
		throw new IllegalStateException("Cloud not invoke in webflux");
	}

	/**
	 * Update all accessed session attributes through {@code session.setAttribute}
	 * calls, explicitly indicating to the container that they might have been modified.
	 */
	@Override
	protected void updateAccessedSessionAttributes(){
	}

	@Override
	public String toString(){
		return request.toString();
	}

}
