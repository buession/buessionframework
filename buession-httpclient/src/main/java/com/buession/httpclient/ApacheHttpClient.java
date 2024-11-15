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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient;

import com.buession.core.utils.ClassUtils;
import com.buession.httpclient.apache.Apache4Client;
import com.buession.httpclient.apache.Apache5Client;
import com.buession.httpclient.apache.ApacheClient;
import com.buession.httpclient.conn.Apache5ClientConnectionManager;
import com.buession.httpclient.conn.ApacheClientConnectionManager;
import com.buession.httpclient.conn.ConnectionManager;
import com.buession.httpclient.core.Configuration;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.exception.RequestException;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Apache HttpComponents HttpClient
 *
 * @author Yong.Teng
 */
public class ApacheHttpClient extends AbstractHttpClient {

	private final static boolean V5 = ClassUtils.isPresent("org.apache.hc.client5.http.classic.HttpClient");

	private ApacheClient httpClient;

	/**
	 * 构造函数
	 */
	public ApacheHttpClient() {
		super();
		setConnectionManager(V5 ? new Apache5ClientConnectionManager() :
				new ApacheClientConnectionManager());
	}

	/**
	 * 构造函数
	 *
	 * @param connectionManager
	 * 		连接管理器
	 *
	 * @since 3.0.0
	 */
	public ApacheHttpClient(com.buession.httpclient.apache.ApacheClientConnectionManager connectionManager) {
		super(connectionManager);
	}

	/**
	 * 构造函数
	 *
	 * @param configuration
	 * 		配置
	 *
	 * @since 3.0.0
	 */
	public ApacheHttpClient(Configuration configuration) {
		super(V5 ? new Apache5ClientConnectionManager(configuration) : new ApacheClientConnectionManager(
				configuration));
	}

	/**
	 * 构造函数
	 *
	 * @param httpClient
	 *        {@link ApacheClient} 实例
	 *
	 * @since 3.0.0
	 */
	public ApacheHttpClient(ApacheClient httpClient) {
		this.httpClient = httpClient;
	}

	public ApacheClient getHttpClient() {
		if(httpClient == null){
			ConnectionManager connectionManager = getConnectionManager();
			httpClient = connectionManager instanceof Apache5ClientConnectionManager ?
					new Apache5Client((Apache5ClientConnectionManager) connectionManager, getHttpVersion()) :
					new Apache4Client((ApacheClientConnectionManager) connectionManager, getHttpVersion());
		}

		return httpClient;
	}

	public void setHttpClient(ApacheClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	public Response get(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().get(uri, parameters, headers);
	}

	@Override
	public Response get(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().get(uri, readTimeout, parameters, headers);
	}

	@Override
	public Response post(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().post(uri, parameters, headers, data);
	}

	@Override
	public Response post(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						 List<Header> headers) throws IOException, RequestException {
		return getHttpClient().post(uri, readTimeout, parameters, headers, data);
	}

	@Override
	public Response put(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().put(uri, parameters, headers, data);
	}

	@Override
	public Response put(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						List<Header> headers) throws IOException, RequestException {
		return getHttpClient().put(uri, readTimeout, parameters, headers, data);
	}

	@Override
	public Response patch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().patch(uri, parameters, headers, data);
	}

	@Override
	public Response patch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						  List<Header> headers) throws IOException, RequestException {
		return getHttpClient().patch(uri, readTimeout, parameters, headers, data);
	}

	@Override
	public Response delete(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().delete(uri, parameters, headers);
	}

	@Override
	public Response delete(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().delete(uri, readTimeout, parameters, headers);
	}

	@Override
	public Response connect(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().connect(uri, parameters, headers);
	}

	@Override
	public Response connect(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().connect(uri, readTimeout, parameters, headers);
	}

	@Override
	public Response trace(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().trace(uri, parameters, headers);
	}

	@Override
	public Response trace(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().trace(uri, readTimeout, parameters, headers);
	}

	@Override
	public Response copy(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().trace(uri, parameters, headers);
	}

	@Override
	public Response copy(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().trace(uri, readTimeout, parameters, headers);
	}

	@Override
	public Response move(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().move(uri, parameters, headers);
	}

	@Override
	public Response move(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().move(uri, readTimeout, parameters, headers);
	}

	@Override
	public Response head(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().head(uri, parameters, headers);
	}

	@Override
	public Response head(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().head(uri, readTimeout, parameters, headers);
	}

	@Override
	public Response options(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().options(uri, parameters, headers);
	}

	@Override
	public Response options(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().options(uri, readTimeout, parameters, headers);
	}

	@Override
	public Response link(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().link(uri, parameters, headers);
	}

	@Override
	public Response link(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().link(uri, readTimeout, parameters, headers);
	}

	@Override
	public Response unlink(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().unlink(uri, parameters, headers);
	}

	@Override
	public Response unlink(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().unlink(uri, readTimeout, parameters, headers);
	}

	@Override
	public Response purge(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().purge(uri, parameters, headers);
	}

	@Override
	public Response purge(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().purge(uri, readTimeout, parameters, headers);
	}

	@Override
	public Response lock(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().lock(uri, parameters, headers);
	}

	@Override
	public Response lock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().lock(uri, readTimeout, parameters, headers);
	}

	@Override
	public Response unlock(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().unlock(uri, parameters, headers);
	}

	@Override
	public Response unlock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().unlock(uri, readTimeout, parameters, headers);
	}

	@Override
	public Response propfind(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().propfind(uri, parameters, headers);
	}

	@Override
	public Response propfind(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().propfind(uri, readTimeout, parameters, headers);
	}

	@Override
	public Response proppatch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().proppatch(uri, parameters, headers, data);
	}

	@Override
	public Response proppatch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
							  List<Header> headers) throws IOException, RequestException {
		return getHttpClient().proppatch(uri, readTimeout, parameters, headers, data);
	}

	@Override
	public Response report(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().report(uri, parameters, headers, data);
	}

	@Override
	public Response report(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						   List<Header> headers) throws IOException, RequestException {
		return getHttpClient().report(uri, readTimeout, parameters, headers, data);
	}

	@Override
	public Response view(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().view(uri, parameters, headers);
	}

	@Override
	public Response view(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().view(uri, readTimeout, parameters, headers);
	}

	@Override
	public Response wrapped(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().wrapped(uri, parameters, headers);
	}

	@Override
	public Response wrapped(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return getHttpClient().wrapped(uri, readTimeout, parameters, headers);
	}

}
