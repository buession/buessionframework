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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.core;

import java.net.URI;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class Request {

	private RequestMethod method;

	@Deprecated
	private String url;

	private URI uri;

	private List<Header> headers;

	private RequestBody<?> requestBody;

	public RequestMethod getMethod(){
		return method;
	}

	public void setMethod(RequestMethod method){
		this.method = method;
	}

	public String getUrl(){
		return url;
	}

	@Deprecated
	public void setUrl(String url){
		this.url = url;
		this.uri = URI.create(url);
	}

	public URI getUri(){
		return uri;
	}

	public void setUri(URI uri){
		this.uri = uri;
		this.url = uri.toString();
	}

	public List<Header> getHeaders(){
		return headers;
	}

	public void setHeaders(List<Header> headers){
		this.headers = headers;
	}

	public RequestBody<?> getRequestBody(){
		return requestBody;
	}

	public void setRequestBody(RequestBody<?> requestBody){
		this.requestBody = requestBody;
	}

	@Override
	public String toString(){
		return "Request{" + "method=" + method + ", url='" + uri + '\'' + ", headers=" + headers + ", requestBody=" +
				requestBody + '}';
	}

}
