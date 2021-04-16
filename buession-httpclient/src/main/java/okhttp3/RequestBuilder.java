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
package okhttp3;

import com.buession.httpclient.core.RequestMethod;

/**
 * OKHTTP Request Builder
 *
 * @author Yong.Teng
 * @since 1.2.1
 */
public class RequestBuilder extends okhttp3.Request.Builder {

	public RequestBuilder(){
		super();
	}

	public RequestBuilder(String url){
		this();
		url(url);
	}

	public RequestBuilder(Request request){
		super(request);
	}

	public Request.Builder post(){
		return method(RequestMethod.POST);
	}

	public Request.Builder patch(){
		return method(RequestMethod.PATCH);
	}

	public Request.Builder put(){
		return method(RequestMethod.PUT);
	}

	public Request.Builder connect(){
		return method(RequestMethod.CONNECT);
	}

	public Request.Builder trace(){
		return method(RequestMethod.TRACE);
	}

	public Request.Builder copy(){
		return method(RequestMethod.COPY);
	}

	public Request.Builder move(){
		return method(RequestMethod.MOVE);
	}

	public Request.Builder options(){
		return method(RequestMethod.OPTIONS);
	}

	public Request.Builder link(){
		return method(RequestMethod.LINK);
	}

	public Request.Builder unlink(){
		return method(RequestMethod.UNLINK);
	}

	public Request.Builder purge(){
		return method(RequestMethod.PURGE);
	}

	public Request.Builder lock(){
		return method(RequestMethod.LOCK);
	}

	public Request.Builder unlock(){
		return method(RequestMethod.UNLOCK);
	}

	public Request.Builder propfind(){
		return method(RequestMethod.PROPFIND);
	}

	public Request.Builder proppatch(){
		return method(RequestMethod.PROPPATCH);
	}

	public Request.Builder proppatch(okhttp3.RequestBody body){
		return method(RequestMethod.PROPPATCH, body);
	}

	public Request.Builder report(){
		return method(RequestMethod.REPORT);
	}

	public Request.Builder report(okhttp3.RequestBody body){
		return method(RequestMethod.REPORT, body);
	}

	public Request.Builder view(){
		return method(RequestMethod.VIEW);
	}

	public Request.Builder wrapped(){
		return method(RequestMethod.WRAPPED);
	}

	private Request.Builder method(final RequestMethod method){
		return this.method(method.name(), null);
	}

	private Request.Builder method(final RequestMethod method, final okhttp3.RequestBody body){
		return this.method(method.name(), body);
	}

}
