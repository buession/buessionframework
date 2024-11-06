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
import com.buession.httpclient.apache.Apache4AsyncClient;
import com.buession.httpclient.apache.Apache5AsyncClient;
import com.buession.httpclient.apache.ApacheAsyncClient;
import com.buession.httpclient.conn.Apache5NioClientConnectionManager;
import com.buession.httpclient.conn.ApacheNioClientConnectionManager;
import com.buession.httpclient.core.Configuration;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.concurrent.Callback;
import com.buession.httpclient.exception.RequestException;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * Apache HttpComponents 异步 HttpClient
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public class ApacheHttpAsyncClient extends AbstractHttpAsyncClient {

	private final static boolean V5 = ClassUtils.isPresent("org.apache.hc.client5.http.async.HttpAsyncClient");

	private ApacheAsyncClient httpAsyncClient;

	/**
	 * 构造函数
	 */
	public ApacheHttpAsyncClient() {
		super();
		setConnectionManager(V5 ? new Apache5NioClientConnectionManager() : new ApacheNioClientConnectionManager());
	}

	/**
	 * 构造函数
	 *
	 * @param connectionManager
	 * 		连接管理器
	 *
	 * @since 3.0.0
	 */
	public ApacheHttpAsyncClient(com.buession.httpclient.apache.ApacheNioClientConnectionManager connectionManager) {
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
	public ApacheHttpAsyncClient(Configuration configuration) {
		super(V5 ? new Apache5NioClientConnectionManager(configuration) : new ApacheNioClientConnectionManager(
				configuration));
	}

	/**
	 * 构造函数
	 *
	 * @param httpAsyncClient
	 *        {@link ApacheAsyncClient} 实例
	 *
	 * @since 3.0.0
	 */
	public ApacheHttpAsyncClient(ApacheAsyncClient httpAsyncClient) {
		this.httpAsyncClient = httpAsyncClient;
	}

	public ApacheAsyncClient getHttpClient() {
		if(httpAsyncClient == null){
			httpAsyncClient = V5 ? new Apache5AsyncClient((Apache5NioClientConnectionManager) getConnectionManager(),
					getHttpVersion()) : new Apache4AsyncClient(
					(ApacheNioClientConnectionManager) getConnectionManager(), getHttpVersion());
		}

		return httpAsyncClient;
	}

	public void setHttpClient(ApacheAsyncClient httpAsyncClient) {
		this.httpAsyncClient = httpAsyncClient;
	}

	@Override
	public void get(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		getHttpClient().get(uri, parameters, headers, callback);
	}

	@Override
	public void get(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					Callback callback) throws IOException, RequestException {
		getHttpClient().get(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public void post(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException {
		getHttpClient().post(uri, parameters, headers, data, callback);
	}

	@Override
	public void post(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					 List<Header> headers, Callback callback) throws IOException, RequestException {
		getHttpClient().post(uri, readTimeout, parameters, headers, data, callback);
	}

	@Override
	public void put(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
					Callback callback) throws IOException, RequestException {
		getHttpClient().put(uri, parameters, headers, data, callback);
	}

	@Override
	public void put(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					List<Header> headers, Callback callback) throws IOException, RequestException {
		getHttpClient().put(uri, readTimeout, parameters, headers, data, callback);
	}

	@Override
	public void patch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException {
		getHttpClient().patch(uri, parameters, headers, data, callback);
	}

	@Override
	public void patch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					  List<Header> headers, Callback callback) throws IOException, RequestException {
		getHttpClient().patch(uri, readTimeout, parameters, headers, data, callback);
	}

	@Override
	public void delete(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		getHttpClient().delete(uri, parameters, headers, callback);
	}

	@Override
	public void delete(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException {
		getHttpClient().delete(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public void connect(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		getHttpClient().connect(uri, parameters, headers, callback);
	}

	@Override
	public void connect(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						Callback callback) throws IOException, RequestException {
		getHttpClient().connect(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public void trace(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		getHttpClient().trace(uri, parameters, headers, callback);
	}

	@Override
	public void trace(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException {
		getHttpClient().trace(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public void copy(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		getHttpClient().copy(uri, parameters, headers, callback);
	}

	@Override
	public void copy(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException {
		getHttpClient().copy(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public void move(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		getHttpClient().move(uri, parameters, headers, callback);
	}

	@Override
	public void move(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException {
		getHttpClient().move(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public void head(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		getHttpClient().head(uri, parameters, headers, callback);
	}

	@Override
	public void head(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException {
		getHttpClient().head(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public void options(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		getHttpClient().options(uri, parameters, headers, callback);
	}

	@Override
	public void options(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						Callback callback) throws IOException, RequestException {
		getHttpClient().options(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public void link(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		getHttpClient().link(uri, parameters, headers, callback);
	}

	@Override
	public void link(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException {
		getHttpClient().link(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public void unlink(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		getHttpClient().unlink(uri, parameters, headers, callback);
	}

	@Override
	public void unlink(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException {
		getHttpClient().unlink(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public void purge(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		getHttpClient().purge(uri, parameters, headers, callback);
	}

	@Override
	public void purge(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException {
		getHttpClient().purge(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public void lock(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		getHttpClient().lock(uri, parameters, headers, callback);
	}

	@Override
	public void lock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException {
		getHttpClient().lock(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public void unlock(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		getHttpClient().unlock(uri, parameters, headers, callback);
	}

	@Override
	public void unlock(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException {
		getHttpClient().unlock(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public void propfind(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		getHttpClient().propfind(uri, parameters, headers, callback);
	}

	@Override
	public void propfind(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						 Callback callback) throws IOException, RequestException {
		getHttpClient().propfind(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public void proppatch(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
						  Callback callback) throws IOException, RequestException {
		getHttpClient().proppatch(uri, parameters, headers, data, callback);
	}

	@Override
	public void proppatch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						  List<Header> headers, Callback callback) throws IOException, RequestException {
		getHttpClient().proppatch(uri, readTimeout, parameters, headers, data, callback);
	}

	@Override
	public void report(URI uri, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException {
		getHttpClient().report(uri, parameters, headers, data, callback);
	}

	@Override
	public void report(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					   List<Header> headers, Callback callback) throws IOException, RequestException {
		getHttpClient().report(uri, readTimeout, parameters, headers, data, callback);
	}

	@Override
	public void view(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		getHttpClient().view(uri, parameters, headers, callback);
	}

	@Override
	public void view(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException {
		getHttpClient().view(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public void wrapped(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		getHttpClient().wrapped(uri, parameters, headers, callback);
	}

	@Override
	public void wrapped(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						Callback callback) throws IOException, RequestException {
		getHttpClient().wrapped(uri, readTimeout, parameters, headers, callback);
	}

}
