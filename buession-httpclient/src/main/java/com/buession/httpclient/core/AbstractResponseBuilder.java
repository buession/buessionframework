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

import java.io.InputStream;
import java.util.List;

/**
 * 响应构建器抽象类
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public abstract class AbstractResponseBuilder implements ResponseBuilder {

	/**
	 * 响应
	 */
	protected Response response = new Response();

	@Override
	public ResponseBuilder setProtocolVersion(ProtocolVersion protocolVersion){
		response.setProtocolVersion(protocolVersion);
		return this;
	}

	@Override
	public ResponseBuilder setStatusCode(int statusCode){
		response.setStatusCode(statusCode);
		return this;
	}

	@Override
	public ResponseBuilder setStatusText(String statusText){
		response.setStatusText(statusText);
		return this;
	}

	@Override
	public ResponseBuilder setHeaders(List<Header> headers){
		response.setHeaders(headers);
		return this;
	}

	@Override
	public ResponseBuilder setInputStream(InputStream inputStream){
		response.setInputStream(inputStream);
		return this;
	}

	@Override
	public ResponseBuilder setBody(String body){
		response.setBody(body);
		return this;
	}

	@Override
	public ResponseBuilder setContentLength(long contentLength){
		response.setContentLength(contentLength);
		return this;
	}

	@Override
	public Response build(){
		response.setStatusLine(new StatusLine(response.getStatusCode(), response.getStatusText()));
		return response;
	}

}
