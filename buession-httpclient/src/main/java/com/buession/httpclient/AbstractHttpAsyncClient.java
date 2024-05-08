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

import com.buession.core.utils.Assert;
import com.buession.httpclient.conn.ConnectionManager;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.RequestMethod;
import com.buession.httpclient.core.concurrent.Callback;
import com.buession.httpclient.exception.RequestException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Http 异步客户端抽象类
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public abstract class AbstractHttpAsyncClient extends AbstractBaseHttpClient implements HttpAsyncClient {

	/**
	 * 构造函数
	 */
	public AbstractHttpAsyncClient() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param connectionManager
	 * 		连接管理器
	 */
	public AbstractHttpAsyncClient(ConnectionManager connectionManager) {
		super(connectionManager);
	}

	@Override
	public void get(URI uri, Callback callback) throws IOException, RequestException {
		get(uri, null, null, callback);
	}

	@Override
	public void get(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->get(url.toURI(), null, null, callback));
	}

	@Override
	public void get(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		get(uri, parameters, null, callback);
	}

	@Override
	public void get(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		asyncExecute(()->get(url.toURI(), parameters, null, callback));
	}

	@Override
	public void get(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		get(uri, null, headers, callback);
	}

	@Override
	public void get(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->get(url.toURI(), null, headers, callback));
	}

	@Override
	public void get(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->get(url.toURI(), parameters, headers, callback));
	}

	@Override
	public void get(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		get(uri, readTimeout, null, null, callback);
	}

	@Override
	public void get(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->get(url.toURI(), readTimeout, null, null, callback));
	}

	@Override
	public void get(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		get(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public void get(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->get(url.toURI(), readTimeout, parameters, null, callback));
	}

	@Override
	public void get(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		get(uri, readTimeout, null, headers, callback);
	}

	@Override
	public void get(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->get(url.toURI(), readTimeout, null, headers, callback));
	}

	@Override
	public void get(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					Callback callback) throws IOException, RequestException {
		asyncExecute(()->get(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void post(URI uri, Callback callback) throws IOException, RequestException {
		post(uri, null, null, null, callback);
	}

	@Override
	public void post(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->post(url.toURI(), null, null, null, callback));
	}

	@Override
	public void post(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		post(uri, null, parameters, null, callback);
	}

	@Override
	public void post(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		asyncExecute(()->post(url.toURI(), null, parameters, null, callback));
	}

	@Override
	public void post(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		post(uri, null, null, headers, callback);
	}

	@Override
	public void post(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->post(url.toURI(), null, null, headers, callback));
	}

	@Override
	public void post(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		post(uri, null, parameters, headers, callback);
	}

	@Override
	public void post(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->post(url.toURI(), null, parameters, headers, callback));
	}

	@Override
	public void post(URI uri, RequestBody<?> data, Callback callback) throws IOException, RequestException {
		post(uri, data, null, null, callback);
	}

	@Override
	public void post(URL url, RequestBody<?> data, Callback callback) throws IOException, RequestException {
		asyncExecute(()->post(url.toURI(), data, null, null, callback));
	}

	@Override
	public void post(URI uri, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		post(uri, data, parameters, null, callback);
	}

	@Override
	public void post(URL url, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->post(url.toURI(), data, parameters, null, callback));
	}

	@Override
	public void post(URI uri, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		post(uri, data, null, headers, callback);
	}

	@Override
	public void post(URL url, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->post(url.toURI(), data, null, headers, callback));
	}

	@Override
	public void post(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException {
		asyncExecute(()->post(url.toURI(), data, parameters, headers, callback));
	}

	@Override
	public void post(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		post(uri, readTimeout, null, null, null, callback);
	}

	@Override
	public void post(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->post(url.toURI(), readTimeout, null, null, null, callback));
	}

	@Override
	public void post(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		post(uri, readTimeout, null, parameters, null, callback);
	}

	@Override
	public void post(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->post(url.toURI(), readTimeout, null, parameters, null, callback));
	}

	@Override
	public void post(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		post(uri, readTimeout, null, null, headers, callback);
	}

	@Override
	public void post(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->post(url.toURI(), readTimeout, null, null, headers, callback));
	}

	@Override
	public void post(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException {
		post(uri, readTimeout, null, parameters, headers, callback);
	}

	@Override
	public void post(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException {
		asyncExecute(()->post(url.toURI(), readTimeout, null, parameters, headers, callback));
	}

	@Override
	public void post(URI uri, int readTimeout, RequestBody<?> data, Callback callback) throws IOException,
			RequestException {
		post(uri, readTimeout, data, null, null, callback);
	}

	@Override
	public void post(URL url, int readTimeout, RequestBody<?> data, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->post(url.toURI(), readTimeout, data, null, null, callback));
	}

	@Override
	public void post(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					 Callback callback) throws IOException, RequestException {
		post(uri, readTimeout, data, parameters, null, callback);
	}

	@Override
	public void post(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					 Callback callback) throws IOException, RequestException {
		asyncExecute(()->post(url.toURI(), readTimeout, data, parameters, null, callback));
	}

	@Override
	public void post(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		post(uri, readTimeout, data, null, headers, callback);
	}

	@Override
	public void post(URL url, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->post(url.toURI(), readTimeout, data, null, headers, callback));
	}

	@Override
	public void post(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					 List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->post(url.toURI(), readTimeout, data, parameters, headers, callback));
	}

	@Override
	public void put(URI uri, Callback callback) throws IOException, RequestException {
		put(uri, null, null, null, callback);
	}

	@Override
	public void put(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->put(url.toURI(), null, null, null, callback));
	}

	@Override
	public void put(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		put(uri, null, parameters, null, callback);
	}

	@Override
	public void put(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		asyncExecute(()->put(url.toURI(), null, parameters, null, callback));
	}

	@Override
	public void put(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		put(uri, null, null, headers, callback);
	}

	@Override
	public void put(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->put(url.toURI(), null, null, headers, callback));
	}

	@Override
	public void put(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		put(uri, null, parameters, headers, callback);
	}

	@Override
	public void put(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->put(url.toURI(), null, parameters, headers, callback));
	}

	@Override
	public void put(URI uri, RequestBody<?> data, Callback callback) throws IOException, RequestException {
		put(uri, data, null, null, callback);
	}

	@Override
	public void put(URL url, RequestBody<?> data, Callback callback) throws IOException, RequestException {
		asyncExecute(()->put(url.toURI(), data, null, null, callback));
	}

	@Override
	public void put(URI uri, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		put(uri, data, parameters, null, callback);
	}

	@Override
	public void put(URL url, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->put(url.toURI(), data, parameters, null, callback));
	}

	@Override
	public void put(URI uri, RequestBody<?> data, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		put(uri, data, null, headers, callback);
	}

	@Override
	public void put(URL url, RequestBody<?> data, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->put(url.toURI(), data, null, headers, callback));
	}

	@Override
	public void put(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
					Callback callback) throws IOException, RequestException {
		asyncExecute(()->put(url.toURI(), data, parameters, headers, callback));
	}

	@Override
	public void put(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		put(uri, readTimeout, null, null, null, callback);
	}

	@Override
	public void put(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->put(url.toURI(), readTimeout, null, null, null, callback));
	}

	@Override
	public void put(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		put(uri, readTimeout, null, parameters, null, callback);
	}

	@Override
	public void put(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->put(url.toURI(), readTimeout, null, parameters, null, callback));
	}

	@Override
	public void put(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		put(uri, readTimeout, null, null, headers, callback);
	}

	@Override
	public void put(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->put(url.toURI(), readTimeout, null, null, headers, callback));
	}

	@Override
	public void put(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					Callback callback) throws IOException, RequestException {
		put(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public void put(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					Callback callback) throws IOException, RequestException {
		asyncExecute(()->put(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void put(URI uri, int readTimeout, RequestBody<?> data, Callback callback) throws IOException,
			RequestException {
		put(uri, readTimeout, data, null, null, callback);
	}

	@Override
	public void put(URL url, int readTimeout, RequestBody<?> data, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->put(url.toURI(), readTimeout, data, null, null, callback));
	}

	@Override
	public void put(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					Callback callback) throws IOException, RequestException {
		put(uri, readTimeout, data, parameters, null, callback);
	}

	@Override
	public void put(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					Callback callback) throws IOException, RequestException {
		asyncExecute(()->put(url.toURI(), readTimeout, data, parameters, null, callback));
	}

	@Override
	public void put(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		put(uri, readTimeout, data, null, headers, callback);
	}

	@Override
	public void put(URL url, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->put(url.toURI(), readTimeout, data, null, headers, callback));
	}

	@Override
	public void put(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->put(url.toURI(), readTimeout, data, parameters, headers, callback));
	}

	@Override
	public void patch(URI uri, Callback callback) throws IOException, RequestException {
		patch(uri, null, null, null, callback);
	}

	@Override
	public void patch(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->patch(url.toURI(), null, null, null, callback));
	}

	@Override
	public void patch(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		patch(uri, null, parameters, null, callback);
	}

	@Override
	public void patch(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		asyncExecute(()->patch(url.toURI(), null, parameters, null, callback));
	}

	@Override
	public void patch(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		patch(uri, null, null, headers, callback);
	}

	@Override
	public void patch(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->patch(url.toURI(), null, null, headers, callback));
	}

	@Override
	public void patch(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		patch(uri, null, parameters, headers, callback);
	}

	@Override
	public void patch(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->patch(url.toURI(), null, parameters, headers, callback));
	}

	@Override
	public void patch(URI uri, RequestBody<?> data, Callback callback) throws IOException, RequestException {
		patch(uri, data, null, null, callback);
	}

	@Override
	public void patch(URL url, RequestBody<?> data, Callback callback) throws IOException, RequestException {
		asyncExecute(()->patch(url.toURI(), data, null, null, callback));
	}

	@Override
	public void patch(URI uri, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		patch(uri, data, parameters, null, callback);
	}

	@Override
	public void patch(URL url, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->patch(url.toURI(), data, parameters, null, callback));
	}

	@Override
	public void patch(URI uri, RequestBody<?> data, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		patch(uri, data, null, headers, callback);
	}

	@Override
	public void patch(URL url, RequestBody<?> data, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->patch(url.toURI(), data, null, headers, callback));
	}

	@Override
	public void patch(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException {
		asyncExecute(()->patch(url.toURI(), data, parameters, headers, callback));
	}

	@Override
	public void patch(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		patch(uri, readTimeout, null, null, null, callback);
	}

	@Override
	public void patch(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->patch(url.toURI(), readTimeout, null, null, null, callback));
	}

	@Override
	public void patch(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		patch(uri, readTimeout, null, parameters, null, callback);
	}

	@Override
	public void patch(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->patch(url.toURI(), readTimeout, null, parameters, null, callback));
	}

	@Override
	public void patch(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		patch(uri, readTimeout, null, null, headers, callback);
	}

	@Override
	public void patch(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->patch(url.toURI(), readTimeout, null, null, headers, callback));
	}

	@Override
	public void patch(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException {
		patch(uri, readTimeout, null, parameters, headers, callback);
	}

	@Override
	public void patch(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException {
		asyncExecute(()->patch(url.toURI(), readTimeout, null, parameters, headers, callback));
	}

	@Override
	public void patch(URI uri, int readTimeout, RequestBody<?> data, Callback callback)
			throws IOException, RequestException {
		patch(uri, readTimeout, data, null, null, callback);
	}

	@Override
	public void patch(URL url, int readTimeout, RequestBody<?> data, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->patch(url.toURI(), readTimeout, data, null, null, callback));
	}

	@Override
	public void patch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					  Callback callback) throws IOException, RequestException {
		patch(uri, readTimeout, data, parameters, null, callback);
	}

	@Override
	public void patch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					  Callback callback) throws IOException, RequestException {
		asyncExecute(()->patch(url.toURI(), readTimeout, data, parameters, null, callback));
	}

	@Override
	public void patch(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		patch(uri, readTimeout, data, null, headers, callback);
	}

	@Override
	public void patch(URL url, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->patch(url.toURI(), readTimeout, data, null, headers, callback));
	}

	@Override
	public void patch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					  List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->patch(url.toURI(), readTimeout, data, parameters, headers, callback));
	}

	@Override
	public void delete(URI uri, Callback callback) throws IOException, RequestException {
		delete(uri, null, null, callback);
	}

	@Override
	public void delete(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->delete(url.toURI(), null, null, callback));
	}

	@Override
	public void delete(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		delete(uri, parameters, null, callback);
	}

	@Override
	public void delete(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->delete(url.toURI(), parameters, null, callback));
	}

	@Override
	public void delete(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		delete(uri, null, headers, callback);
	}

	@Override
	public void delete(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->delete(url.toURI(), null, headers, callback));
	}

	@Override
	public void delete(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->delete(url.toURI(), parameters, headers, callback));
	}

	@Override
	public void delete(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		delete(uri, readTimeout, null, null, callback);
	}

	@Override
	public void delete(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->delete(url.toURI(), readTimeout, null, null, callback));
	}

	@Override
	public void delete(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		delete(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public void delete(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->delete(url.toURI(), readTimeout, parameters, null, callback));
	}

	@Override
	public void delete(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		delete(uri, readTimeout, null, headers, callback);
	}

	@Override
	public void delete(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->delete(url.toURI(), readTimeout, null, headers, callback));
	}

	@Override
	public void delete(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException {
		asyncExecute(()->delete(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void connect(URI uri, Callback callback) throws IOException, RequestException {
		connect(uri, null, null, callback);
	}

	@Override
	public void connect(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->connect(url.toURI(), null, null, callback));
	}

	@Override
	public void connect(URI uri, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException {
		connect(uri, parameters, null, callback);
	}

	@Override
	public void connect(URL url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->connect(url.toURI(), parameters, null, callback));
	}

	@Override
	public void connect(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		connect(uri, null, headers, callback);
	}

	@Override
	public void connect(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->connect(url.toURI(), null, headers, callback));
	}

	@Override
	public void connect(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->connect(url.toURI(), parameters, headers, callback));
	}

	@Override
	public void connect(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		connect(uri, readTimeout, null, null, callback);
	}

	@Override
	public void connect(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->connect(url.toURI(), readTimeout, null, null, callback));
	}

	@Override
	public void connect(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		connect(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public void connect(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->connect(url.toURI(), readTimeout, parameters, null, callback));
	}

	@Override
	public void connect(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		connect(uri, readTimeout, null, headers, callback);
	}

	@Override
	public void connect(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->connect(url.toURI(), readTimeout, null, headers, callback));
	}

	@Override
	public void connect(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						Callback callback) throws IOException, RequestException {
		asyncExecute(()->connect(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void trace(URI uri, Callback callback) throws IOException, RequestException {
		trace(uri, null, null, callback);
	}

	@Override
	public void trace(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->trace(url.toURI(), null, null, callback));
	}

	@Override
	public void trace(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		trace(uri, parameters, null, callback);
	}

	@Override
	public void trace(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		asyncExecute(()->trace(url.toURI(), parameters, null, callback));
	}

	@Override
	public void trace(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		trace(uri, null, headers, callback);
	}

	@Override
	public void trace(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->trace(url.toURI(), null, headers, callback));
	}

	@Override
	public void trace(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->trace(url.toURI(), parameters, headers, callback));
	}

	@Override
	public void trace(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		trace(uri, readTimeout, null, null, callback);
	}

	@Override
	public void trace(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->trace(url.toURI(), readTimeout, null, null, callback));
	}

	@Override
	public void trace(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		trace(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public void trace(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->trace(url.toURI(), readTimeout, parameters, null, callback));
	}

	@Override
	public void trace(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		trace(uri, readTimeout, null, headers, callback);
	}

	@Override
	public void trace(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->trace(url.toURI(), readTimeout, null, headers, callback));
	}

	@Override
	public void trace(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException {
		asyncExecute(()->trace(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void copy(URI uri, Callback callback) throws IOException, RequestException {
		copy(uri, null, null, callback);
	}

	@Override
	public void copy(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->copy(url.toURI(), null, null, callback));
	}

	@Override
	public void copy(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		copy(uri, parameters, null, callback);
	}

	@Override
	public void copy(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		asyncExecute(()->copy(url.toURI(), parameters, null, callback));
	}

	@Override
	public void copy(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		copy(uri, null, headers, callback);
	}

	@Override
	public void copy(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->copy(url.toURI(), null, headers, callback));
	}

	@Override
	public void copy(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->copy(url.toURI(), parameters, headers, callback));
	}

	@Override
	public void copy(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		copy(uri, readTimeout, null, null, callback);
	}

	@Override
	public void copy(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->copy(url.toURI(), readTimeout, null, null, callback));
	}

	@Override
	public void copy(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		copy(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public void copy(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->copy(url.toURI(), readTimeout, parameters, null, callback));
	}

	@Override
	public void copy(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		copy(uri, readTimeout, null, headers, callback);
	}

	@Override
	public void copy(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->copy(url.toURI(), readTimeout, null, headers, callback));
	}

	@Override
	public void copy(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException {
		asyncExecute(()->copy(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void move(URI uri, Callback callback) throws IOException, RequestException {
		move(uri, null, null, callback);
	}

	@Override
	public void move(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->move(url.toURI(), null, null, callback));
	}

	@Override
	public void move(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		move(uri, parameters, null, callback);
	}

	@Override
	public void move(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		asyncExecute(()->move(url.toURI(), parameters, null, callback));
	}

	@Override
	public void move(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		move(uri, null, headers, callback);
	}

	@Override
	public void move(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->move(url.toURI(), null, headers, callback));
	}

	@Override
	public void move(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->move(url.toURI(), parameters, headers, callback));
	}

	@Override
	public void move(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		move(uri, readTimeout, null, null, callback);
	}

	@Override
	public void move(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->move(url.toURI(), readTimeout, null, null, callback));
	}

	@Override
	public void move(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		move(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public void move(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->move(url.toURI(), readTimeout, parameters, null, callback));
	}

	@Override
	public void move(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		move(uri, readTimeout, null, headers, callback);
	}

	@Override
	public void move(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->move(url.toURI(), readTimeout, null, headers, callback));
	}

	@Override
	public void move(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException {
		asyncExecute(()->move(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void head(URI uri, Callback callback) throws IOException, RequestException {
		head(uri, null, null, callback);
	}

	@Override
	public void head(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->head(url.toURI(), null, null, callback));
	}

	@Override
	public void head(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		head(uri, parameters, null, callback);
	}

	@Override
	public void head(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		asyncExecute(()->head(url.toURI(), parameters, null, callback));
	}

	@Override
	public void head(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		head(uri, null, headers, callback);
	}

	@Override
	public void head(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->head(url.toURI(), null, headers, callback));
	}

	@Override
	public void head(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->head(url.toURI(), parameters, headers, callback));
	}

	@Override
	public void head(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		head(uri, readTimeout, null, null, callback);
	}

	@Override
	public void head(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->head(url.toURI(), readTimeout, null, null, callback));
	}

	@Override
	public void head(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		head(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public void head(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->head(url.toURI(), readTimeout, parameters, null, callback));
	}

	@Override
	public void head(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		head(uri, readTimeout, null, headers, callback);
	}

	@Override
	public void head(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->head(url.toURI(), readTimeout, null, headers, callback));
	}

	@Override
	public void head(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException {
		asyncExecute(()->head(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void options(URI uri, Callback callback) throws IOException, RequestException {
		options(uri, null, null, callback);
	}

	@Override
	public void options(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->options(url.toURI(), null, null, callback));
	}

	@Override
	public void options(URI uri, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException {
		options(uri, parameters, null, callback);
	}

	@Override
	public void options(URL url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->options(url.toURI(), parameters, null, callback));
	}

	@Override
	public void options(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		options(uri, null, headers, callback);
	}

	@Override
	public void options(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->options(url.toURI(), null, headers, callback));
	}

	@Override
	public void options(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->options(url.toURI(), parameters, headers, callback));
	}

	@Override
	public void options(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		options(uri, readTimeout, null, null, callback);
	}

	@Override
	public void options(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->options(url.toURI(), readTimeout, null, null, callback));
	}

	@Override
	public void options(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		options(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public void options(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->options(url.toURI(), readTimeout, parameters, null, callback));
	}

	@Override
	public void options(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		options(uri, readTimeout, null, headers, callback);
	}

	@Override
	public void options(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->options(url.toURI(), readTimeout, null, headers, callback));
	}

	@Override
	public void options(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						Callback callback) throws IOException, RequestException {
		asyncExecute(()->options(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void link(URI uri, Callback callback) throws IOException, RequestException {
		link(uri, null, null, callback);
	}

	@Override
	public void link(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->link(url.toURI(), null, null, callback));
	}

	@Override
	public void link(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		link(uri, parameters, null, callback);
	}

	@Override
	public void link(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		asyncExecute(()->link(url.toURI(), parameters, null, callback));
	}

	@Override
	public void link(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		link(uri, null, headers, callback);
	}

	@Override
	public void link(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->link(url.toURI(), null, headers, callback));
	}

	@Override
	public void link(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->link(url.toURI(), parameters, headers, callback));
	}

	@Override
	public void link(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		link(uri, readTimeout, null, null, callback);
	}

	@Override
	public void link(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->link(url.toURI(), readTimeout, null, null, callback));
	}

	@Override
	public void link(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		link(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public void link(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->link(url.toURI(), readTimeout, parameters, null, callback));
	}

	@Override
	public void link(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		link(uri, readTimeout, null, headers, callback);
	}

	@Override
	public void link(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->link(url.toURI(), readTimeout, null, headers, callback));
	}

	@Override
	public void link(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException {
		asyncExecute(()->link(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void unlink(URI uri, Callback callback) throws IOException, RequestException {
		unlink(uri, null, null, callback);
	}

	@Override
	public void unlink(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->unlink(url.toURI(), null, null, callback));
	}

	@Override
	public void unlink(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		unlink(uri, parameters, null, callback);
	}

	@Override
	public void unlink(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->unlink(url.toURI(), parameters, null, callback));
	}

	@Override
	public void unlink(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		unlink(uri, null, headers, callback);
	}

	@Override
	public void unlink(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->unlink(url.toURI(), null, headers, callback));
	}

	@Override
	public void unlink(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->unlink(url.toURI(), parameters, headers, callback));
	}

	@Override
	public void unlink(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		unlink(uri, readTimeout, null, null, callback);
	}

	@Override
	public void unlink(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->unlink(url.toURI(), readTimeout, null, null, callback));
	}

	@Override
	public void unlink(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		unlink(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public void unlink(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->unlink(url.toURI(), readTimeout, parameters, null, callback));
	}

	@Override
	public void unlink(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		unlink(uri, readTimeout, null, headers, callback);
	}

	@Override
	public void unlink(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->unlink(url.toURI(), readTimeout, null, headers, callback));
	}

	@Override
	public void unlink(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException {
		asyncExecute(()->unlink(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void purge(URI uri, Callback callback) throws IOException, RequestException {
		purge(uri, null, null, callback);
	}

	@Override
	public void purge(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->purge(url.toURI(), null, null, callback));
	}

	@Override
	public void purge(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		purge(uri, parameters, null, callback);
	}

	@Override
	public void purge(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		asyncExecute(()->purge(url.toURI(), parameters, null, callback));
	}

	@Override
	public void purge(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		purge(uri, null, headers, callback);
	}

	@Override
	public void purge(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->purge(url.toURI(), null, headers, callback));
	}

	@Override
	public void purge(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->purge(url.toURI(), parameters, headers, callback));
	}

	@Override
	public void purge(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		purge(uri, readTimeout, null, null, callback);
	}

	@Override
	public void purge(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->purge(url.toURI(), readTimeout, null, null, callback));
	}

	@Override
	public void purge(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		purge(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public void purge(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->purge(url.toURI(), readTimeout, parameters, null, callback));
	}

	@Override
	public void purge(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		purge(uri, readTimeout, null, headers, callback);
	}

	@Override
	public void purge(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->purge(url.toURI(), readTimeout, null, headers, callback));
	}

	@Override
	public void purge(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					  Callback callback) throws IOException, RequestException {
		asyncExecute(()->purge(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void lock(URI uri, Callback callback) throws IOException, RequestException {
		lock(uri, null, null, callback);
	}

	@Override
	public void lock(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->lock(url.toURI(), null, null, callback));
	}

	@Override
	public void lock(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		lock(uri, parameters, null, callback);
	}

	@Override
	public void lock(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		asyncExecute(()->lock(url.toURI(), parameters, null, callback));
	}

	@Override
	public void lock(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		lock(uri, null, headers, callback);
	}

	@Override
	public void lock(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->lock(url.toURI(), null, headers, callback));
	}

	@Override
	public void lock(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->lock(url.toURI(), parameters, headers, callback));
	}

	@Override
	public void lock(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		lock(uri, readTimeout, null, null, callback);
	}

	@Override
	public void lock(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->lock(url.toURI(), readTimeout, null, null, callback));
	}

	@Override
	public void lock(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		lock(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public void lock(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->lock(url.toURI(), readTimeout, parameters, null, callback));
	}

	@Override
	public void lock(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		lock(uri, readTimeout, null, headers, callback);
	}

	@Override
	public void lock(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->lock(url.toURI(), readTimeout, null, headers, callback));
	}

	@Override
	public void lock(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException {
		asyncExecute(()->lock(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void unlock(URI uri, Callback callback) throws IOException, RequestException {
		unlock(uri, null, null, callback);
	}

	@Override
	public void unlock(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->unlock(url.toURI(), null, null, callback));
	}

	@Override
	public void unlock(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		unlock(uri, parameters, null, callback);
	}

	@Override
	public void unlock(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->unlock(url.toURI(), parameters, null, callback));
	}

	@Override
	public void unlock(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		unlock(uri, null, headers, callback);
	}

	@Override
	public void unlock(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->unlock(url.toURI(), null, headers, callback));
	}

	@Override
	public void unlock(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->unlock(url.toURI(), parameters, headers, callback));
	}

	@Override
	public void unlock(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		unlock(uri, readTimeout, null, null, callback);
	}

	@Override
	public void unlock(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->unlock(url.toURI(), readTimeout, null, null, callback));
	}

	@Override
	public void unlock(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		unlock(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public void unlock(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->unlock(url.toURI(), readTimeout, parameters, null, callback));
	}

	@Override
	public void unlock(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		unlock(uri, readTimeout, null, headers, callback);
	}

	@Override
	public void unlock(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->unlock(url.toURI(), readTimeout, null, headers, callback));
	}

	@Override
	public void unlock(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException {
		asyncExecute(()->unlock(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void propfind(URI uri, Callback callback) throws IOException, RequestException {
		propfind(uri, null, null, callback);
	}

	@Override
	public void propfind(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->propfind(url.toURI(), null, null, callback));
	}

	@Override
	public void propfind(URI uri, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException {
		propfind(uri, parameters, null, callback);
	}

	@Override
	public void propfind(URL url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->propfind(url.toURI(), parameters, null, callback));
	}

	@Override
	public void propfind(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		propfind(uri, null, headers, callback);
	}

	@Override
	public void propfind(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->propfind(url.toURI(), null, headers, callback));
	}

	@Override
	public void propfind(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->propfind(url.toURI(), parameters, headers, callback));
	}

	@Override
	public void propfind(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		propfind(uri, readTimeout, null, null, callback);
	}

	@Override
	public void propfind(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->propfind(url.toURI(), readTimeout, null, null, callback));
	}

	@Override
	public void propfind(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		propfind(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public void propfind(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->propfind(url.toURI(), readTimeout, parameters, null, callback));
	}

	@Override
	public void propfind(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		propfind(uri, readTimeout, null, headers, callback);
	}

	@Override
	public void propfind(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->propfind(url.toURI(), readTimeout, null, headers, callback));
	}

	@Override
	public void propfind(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						 Callback callback) throws IOException, RequestException {
		asyncExecute(()->propfind(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void proppatch(URI uri, Callback callback) throws IOException, RequestException {
		proppatch(uri, null, null, null, callback);
	}

	@Override
	public void proppatch(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->proppatch(url.toURI(), null, null, null, callback));
	}

	@Override
	public void proppatch(URI uri, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException {
		proppatch(uri, null, parameters, null, callback);
	}

	@Override
	public void proppatch(URL url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->proppatch(url.toURI(), null, parameters, null, callback));
	}

	@Override
	public void proppatch(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		proppatch(uri, null, null, headers, callback);
	}

	@Override
	public void proppatch(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->proppatch(url.toURI(), null, null, headers, callback));
	}

	@Override
	public void proppatch(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		proppatch(uri, null, parameters, headers, callback);
	}

	@Override
	public void proppatch(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->proppatch(url.toURI(), null, parameters, headers, callback));
	}

	@Override
	public void proppatch(URI uri, RequestBody<?> data, Callback callback) throws IOException, RequestException {
		proppatch(uri, data, null, null, callback);
	}

	@Override
	public void proppatch(URL url, RequestBody<?> data, Callback callback) throws IOException, RequestException {
		asyncExecute(()->proppatch(url.toURI(), data, null, null, callback));
	}

	@Override
	public void proppatch(URI uri, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		proppatch(uri, data, parameters, null, callback);
	}

	@Override
	public void proppatch(URL url, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->proppatch(url.toURI(), data, parameters, null, callback));
	}

	@Override
	public void proppatch(URI uri, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		proppatch(uri, data, null, headers, callback);
	}

	@Override
	public void proppatch(URL url, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->proppatch(url.toURI(), data, null, headers, callback));
	}

	@Override
	public void proppatch(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
						  Callback callback) throws IOException, RequestException {
		asyncExecute(()->proppatch(url.toURI(), data, parameters, headers, callback));
	}

	@Override
	public void proppatch(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		proppatch(uri, readTimeout, null, null, null, callback);
	}

	@Override
	public void proppatch(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->proppatch(url.toURI(), readTimeout, null, null, null, callback));
	}

	@Override
	public void proppatch(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		proppatch(uri, readTimeout, null, parameters, null, callback);
	}

	@Override
	public void proppatch(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->proppatch(url.toURI(), readTimeout, null, parameters, null, callback));
	}

	@Override
	public void proppatch(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		proppatch(uri, readTimeout, null, null, headers, callback);
	}

	@Override
	public void proppatch(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->proppatch(url.toURI(), readTimeout, null, null, headers, callback));
	}

	@Override
	public void proppatch(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						  Callback callback) throws IOException, RequestException {
		proppatch(uri, readTimeout, null, parameters, headers, callback);
	}

	@Override
	public void proppatch(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						  Callback callback) throws IOException, RequestException {
		asyncExecute(()->proppatch(url.toURI(), readTimeout, null, parameters, headers, callback));
	}

	@Override
	public void proppatch(URI uri, int readTimeout, RequestBody<?> data, Callback callback) throws IOException,
			RequestException {
		proppatch(uri, readTimeout, data, null, null, callback);
	}

	@Override
	public void proppatch(URL url, int readTimeout, RequestBody<?> data, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->proppatch(url.toURI(), readTimeout, data, null, null, callback));
	}

	@Override
	public void proppatch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						  Callback callback) throws IOException, RequestException {
		proppatch(uri, readTimeout, data, parameters, null, callback);
	}

	@Override
	public void proppatch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						  Callback callback) throws IOException, RequestException {
		asyncExecute(()->proppatch(url.toURI(), readTimeout, data, parameters, null, callback));
	}

	@Override
	public void proppatch(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		proppatch(uri, readTimeout, data, null, headers, callback);
	}

	@Override
	public void proppatch(URL url, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->proppatch(url.toURI(), readTimeout, data, null, headers, callback));
	}

	@Override
	public void proppatch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						  List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->proppatch(url.toURI(), readTimeout, data, parameters, headers, callback));
	}

	@Override
	public void report(URI uri, Callback callback) throws IOException, RequestException {
		report(uri, null, null, null, callback);
	}

	@Override
	public void report(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->report(url.toURI(), null, null, null, callback));
	}

	@Override
	public void report(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		report(uri, null, parameters, null, callback);
	}

	@Override
	public void report(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->report(url.toURI(), null, parameters, null, callback));
	}

	@Override
	public void report(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		report(uri, null, null, headers, callback);
	}

	@Override
	public void report(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->report(url.toURI(), null, null, headers, callback));
	}

	@Override
	public void report(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		report(uri, null, parameters, headers, callback);
	}

	@Override
	public void report(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->report(url.toURI(), null, parameters, headers, callback));
	}

	@Override
	public void report(URI uri, RequestBody<?> data, Callback callback) throws IOException, RequestException {
		report(uri, data, null, null, callback);
	}

	@Override
	public void report(URL url, RequestBody<?> data, Callback callback) throws IOException, RequestException {
		asyncExecute(()->report(url.toURI(), data, null, null, callback));
	}

	@Override
	public void report(URI uri, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		report(uri, data, parameters, null, callback);
	}

	@Override
	public void report(URL url, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->report(url.toURI(), data, parameters, null, callback));
	}

	@Override
	public void report(URI uri, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		report(uri, data, null, headers, callback);
	}

	@Override
	public void report(URL url, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->report(url.toURI(), data, null, headers, callback));
	}

	@Override
	public void report(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException {
		asyncExecute(()->report(url.toURI(), data, parameters, headers, callback));
	}

	@Override
	public void report(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		report(uri, readTimeout, null, null, null, callback);
	}

	@Override
	public void report(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->report(url.toURI(), readTimeout, null, null, null, callback));
	}

	@Override
	public void report(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		report(uri, readTimeout, null, parameters, null, callback);
	}

	@Override
	public void report(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->report(url.toURI(), readTimeout, null, parameters, null, callback));
	}

	@Override
	public void report(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		report(uri, readTimeout, null, null, headers, callback);
	}

	@Override
	public void report(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->report(url.toURI(), readTimeout, null, null, headers, callback));
	}

	@Override
	public void report(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException {
		report(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public void report(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					   Callback callback) throws IOException, RequestException {
		asyncExecute(()->report(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void report(URI uri, int readTimeout, RequestBody<?> data, Callback callback) throws IOException,
			RequestException {
		report(uri, readTimeout, data, null, null, callback);
	}

	@Override
	public void report(URL url, int readTimeout, RequestBody<?> data, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->report(url.toURI(), readTimeout, data, null, null, callback));
	}

	@Override
	public void report(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					   Callback callback) throws IOException, RequestException {
		report(uri, readTimeout, data, parameters, null, callback);
	}

	@Override
	public void report(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					   Callback callback) throws IOException, RequestException {
		asyncExecute(()->report(url.toURI(), readTimeout, data, parameters, null, callback));
	}

	@Override
	public void report(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		report(uri, readTimeout, data, null, headers, callback);
	}

	@Override
	public void report(URL url, int readTimeout, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->report(url.toURI(), readTimeout, data, null, headers, callback));
	}

	@Override
	public void report(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
					   List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->report(url.toURI(), readTimeout, data, parameters, headers, callback));
	}

	@Override
	public void view(URI uri, Callback callback) throws IOException, RequestException {
		view(uri, null, null, callback);
	}

	@Override
	public void view(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->view(url.toURI(), null, null, callback));
	}

	@Override
	public void view(URI uri, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		view(uri, parameters, null, callback);
	}

	@Override
	public void view(URL url, Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		asyncExecute(()->view(url.toURI(), parameters, null, callback));
	}

	@Override
	public void view(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		view(uri, null, headers, callback);
	}

	@Override
	public void view(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->view(url.toURI(), null, headers, callback));
	}

	@Override
	public void view(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->view(url.toURI(), parameters, headers, callback));
	}

	@Override
	public void view(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		view(uri, readTimeout, null, null, callback);
	}

	@Override
	public void view(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->view(url.toURI(), readTimeout, null, null, callback));
	}

	@Override
	public void view(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		view(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public void view(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->view(url.toURI(), readTimeout, parameters, null, callback));
	}

	@Override
	public void view(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		view(uri, readTimeout, null, headers, callback);
	}

	@Override
	public void view(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->view(url.toURI(), readTimeout, null, headers, callback));
	}

	@Override
	public void view(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
					 Callback callback) throws IOException, RequestException {
		asyncExecute(()->view(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void wrapped(URI uri, Callback callback) throws IOException, RequestException {
		wrapped(uri, null, null, callback);
	}

	@Override
	public void wrapped(URL url, Callback callback) throws IOException, RequestException {
		asyncExecute(()->wrapped(url.toURI(), null, null, callback));
	}

	@Override
	public void wrapped(URI uri, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException {
		wrapped(uri, parameters, null, callback);
	}

	@Override
	public void wrapped(URL url, Map<String, Object> parameters, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->wrapped(url.toURI(), parameters, null, callback));
	}

	@Override
	public void wrapped(URI uri, List<Header> headers, Callback callback) throws IOException, RequestException {
		wrapped(uri, null, headers, callback);
	}

	@Override
	public void wrapped(URL url, List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->wrapped(url.toURI(), null, headers, callback));
	}

	@Override
	public void wrapped(URL url, Map<String, Object> parameters, List<Header> headers,
						Callback callback) throws IOException, RequestException {
		asyncExecute(()->wrapped(url.toURI(), parameters, headers, callback));
	}

	@Override
	public void wrapped(URI uri, int readTimeout, Callback callback) throws IOException, RequestException {
		wrapped(uri, readTimeout, null, null, callback);
	}

	@Override
	public void wrapped(URL url, int readTimeout, Callback callback) throws IOException, RequestException {
		asyncExecute(()->wrapped(url.toURI(), readTimeout, null, null, callback));
	}

	@Override
	public void wrapped(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		wrapped(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public void wrapped(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->wrapped(url.toURI(), readTimeout, parameters, null, callback));
	}

	@Override
	public void wrapped(URI uri, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		wrapped(uri, readTimeout, null, headers, callback);
	}

	@Override
	public void wrapped(URL url, int readTimeout, List<Header> headers, Callback callback) throws IOException,
			RequestException {
		asyncExecute(()->wrapped(url.toURI(), readTimeout, null, headers, callback));
	}

	@Override
	public void wrapped(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
						Callback callback) throws IOException, RequestException {
		asyncExecute(()->wrapped(url.toURI(), readTimeout, parameters, headers, callback));
	}

	@Override
	public void request(URI uri, RequestMethod requestMethod, Callback callback) throws IOException, RequestException {
		request(uri, requestMethod, null, null, null, callback);
	}

	@Override
	public void request(URL url, RequestMethod requestMethod, Callback callback) throws IOException, RequestException {
		asyncExecute(()->request(url.toURI(), requestMethod, callback));
	}

	@Override
	public void request(URI uri, RequestMethod requestMethod, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		request(uri, requestMethod, null, parameters, null, callback);
	}

	@Override
	public void request(URL url, RequestMethod requestMethod, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->request(url.toURI(), requestMethod, parameters, callback));
	}

	@Override
	public void request(URI uri, RequestMethod requestMethod, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		request(uri, requestMethod, null, null, headers, callback);
	}

	@Override
	public void request(URL url, RequestMethod requestMethod, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->request(url.toURI(), requestMethod, headers, callback));
	}

	@Override
	public void request(URI uri, RequestMethod requestMethod, Map<String, Object> parameters, List<Header> headers,
						Callback callback) throws IOException, RequestException {
		request(uri, requestMethod, null, parameters, headers, callback);
	}

	@Override
	public void request(URL url, RequestMethod requestMethod, Map<String, Object> parameters, List<Header> headers,
						Callback callback) throws IOException, RequestException {
		asyncExecute(()->request(url.toURI(), requestMethod, parameters, headers, callback));
	}

	@Override
	public void request(URI uri, RequestMethod requestMethod, RequestBody<?> data, Callback callback)
			throws IOException, RequestException {
		request(uri, requestMethod, data, null, null, callback);
	}

	@Override
	public void request(URL url, RequestMethod requestMethod, RequestBody<?> data, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->request(url.toURI(), requestMethod, data, callback));
	}

	@Override
	public void request(URI uri, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters,
						Callback callback) throws IOException, RequestException {
		request(uri, requestMethod, data, parameters, null, callback);
	}

	@Override
	public void request(URL url, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters,
						Callback callback) throws IOException, RequestException {
		asyncExecute(()->request(url.toURI(), requestMethod, data, parameters, callback));
	}

	@Override
	public void request(URI uri, RequestMethod requestMethod, RequestBody<?> data, List<Header> headers,
						Callback callback) throws IOException, RequestException {
		request(uri, requestMethod, data, null, headers, callback);
	}

	@Override
	public void request(URL url, RequestMethod requestMethod, RequestBody<?> data, List<Header> headers,
						Callback callback) throws IOException, RequestException {
		asyncExecute(()->request(url.toURI(), requestMethod, data, headers, callback));
	}

	@Override
	public void request(URI uri, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters,
						List<Header> headers, Callback callback) throws IOException, RequestException {
		Assert.isNull(requestMethod, "Request method could not be null.");

		switch(requestMethod){
			case POST:
				post(uri, data, parameters, headers, callback);
			case PUT:
				put(uri, data, parameters, headers, callback);
			case PATCH:
				patch(uri, data, parameters, headers, callback);
			case DELETE:
				delete(uri, parameters, headers, callback);
			case CONNECT:
				connect(uri, parameters, headers, callback);
			case TRACE:
				trace(uri, parameters, headers, callback);
			case COPY:
				copy(uri, parameters, headers, callback);
			case MOVE:
				move(uri, parameters, headers, callback);
			case HEAD:
				head(uri, parameters, headers, callback);
			case OPTIONS:
				options(uri, parameters, headers, callback);
			case LINK:
				link(uri, parameters, headers, callback);
			case UNLINK:
				unlink(uri, parameters, headers, callback);
			case PURGE:
				purge(uri, parameters, headers, callback);
			case LOCK:
				lock(uri, parameters, headers, callback);
			case UNLOCK:
				unlock(uri, parameters, headers, callback);
			case PROPFIND:
				propfind(uri, parameters, headers, callback);
			case PROPPATCH:
				proppatch(uri, data, parameters, headers, callback);
			case REPORT:
				report(uri, data, parameters, headers, callback);
			case VIEW:
				view(uri, parameters, headers, callback);
			case WRAPPED:
				wrapped(uri, parameters, headers, callback);
			default:
				get(uri, parameters, headers, callback);
		}
	}

	@Override
	public void request(URL url, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters,
						List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->request(url.toURI(), requestMethod, data, parameters, headers, callback));
	}

	@Override
	public void request(URI uri, RequestMethod requestMethod, int readTimeout, Callback callback)
			throws IOException, RequestException {
		request(uri, requestMethod, readTimeout, null, null, null, callback);
	}

	@Override
	public void request(URL url, RequestMethod requestMethod, int readTimeout, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->request(url.toURI(), requestMethod, readTimeout, callback));
	}

	@Override
	public void request(URI uri, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters,
						Callback callback) throws IOException, RequestException {
		request(uri, requestMethod, readTimeout, null, parameters, null, callback);
	}

	@Override
	public void request(URL url, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters,
						Callback callback) throws IOException, RequestException {
		asyncExecute(()->request(url.toURI(), requestMethod, readTimeout, parameters, callback));
	}

	@Override
	public void request(URI uri, RequestMethod requestMethod, int readTimeout, List<Header> headers,
						Callback callback) throws IOException, RequestException {
		request(uri, requestMethod, readTimeout, null, null, headers, callback);
	}

	@Override
	public void request(URL url, RequestMethod requestMethod, int readTimeout, List<Header> headers,
						Callback callback) throws IOException, RequestException {
		asyncExecute(()->request(url.toURI(), requestMethod, readTimeout, headers, callback));
	}

	@Override
	public void request(URI uri, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters,
						List<Header> headers, Callback callback) throws IOException, RequestException {
		request(uri, requestMethod, readTimeout, null, parameters, headers, callback);
	}

	@Override
	public void request(URL url, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters,
						List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->request(url.toURI(), requestMethod, readTimeout, parameters, headers, callback));
	}

	@Override
	public void request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
						Callback callback) throws IOException, RequestException {
		request(uri, requestMethod, readTimeout, data, null, null, callback);
	}

	@Override
	public void request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
						Callback callback) throws IOException, RequestException {
		asyncExecute(()->request(url.toURI(), requestMethod, readTimeout, data, callback));
	}

	@Override
	public void request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
						Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		request(uri, requestMethod, readTimeout, data, parameters, null, callback);
	}

	@Override
	public void request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
						Map<String, Object> parameters, Callback callback) throws IOException, RequestException {
		asyncExecute(()->request(url.toURI(), requestMethod, readTimeout, data, parameters, callback));
	}

	@Override
	public void request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
						List<Header> headers, Callback callback) throws IOException, RequestException {
		request(uri, requestMethod, readTimeout, data, null, headers, callback);
	}

	@Override
	public void request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
						List<Header> headers, Callback callback) throws IOException, RequestException {
		asyncExecute(()->request(url.toURI(), requestMethod, readTimeout, data, headers, callback));
	}

	@Override
	public void request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
						Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		Assert.isNull(requestMethod, "Request method could not be null.");

		switch(requestMethod){
			case POST:
				post(uri, readTimeout, data, parameters, headers, callback);
			case PUT:
				put(uri, readTimeout, data, parameters, headers, callback);
			case PATCH:
				patch(uri, readTimeout, data, parameters, headers, callback);
			case DELETE:
				delete(uri, readTimeout, parameters, headers, callback);
			case CONNECT:
				connect(uri, readTimeout, parameters, headers, callback);
			case TRACE:
				trace(uri, readTimeout, parameters, headers, callback);
			case COPY:
				copy(uri, readTimeout, parameters, headers, callback);
			case MOVE:
				move(uri, readTimeout, parameters, headers, callback);
			case HEAD:
				head(uri, readTimeout, parameters, headers, callback);
			case OPTIONS:
				options(uri, readTimeout, parameters, headers, callback);
			case LINK:
				link(uri, readTimeout, parameters, headers, callback);
			case UNLINK:
				unlink(uri, readTimeout, parameters, headers, callback);
			case PURGE:
				purge(uri, readTimeout, parameters, headers, callback);
			case LOCK:
				lock(uri, readTimeout, parameters, headers, callback);
			case UNLOCK:
				unlock(uri, readTimeout, parameters, headers, callback);
			case PROPFIND:
				propfind(uri, readTimeout, parameters, headers, callback);
			case PROPPATCH:
				proppatch(uri, readTimeout, data, parameters, headers, callback);
			case REPORT:
				report(uri, readTimeout, data, parameters, headers, callback);
			case VIEW:
				view(uri, readTimeout, parameters, headers, callback);
			case WRAPPED:
				wrapped(uri, readTimeout, parameters, headers, callback);
			default:
				get(uri, readTimeout, parameters, headers, callback);
		}
	}

	@Override
	public void request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
						Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException {
		asyncExecute(()->request(url.toURI(), requestMethod, readTimeout, data, parameters, headers, callback));
	}

	protected static void asyncExecute(final AsyncExecute execute) throws IOException, RequestException {
		try{
			execute.exec();
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	@FunctionalInterface
	protected interface AsyncExecute {

		void exec() throws URISyntaxException, IOException, RequestException;

	}

}
