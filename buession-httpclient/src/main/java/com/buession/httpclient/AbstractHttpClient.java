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
import com.buession.httpclient.core.Response;
import com.buession.httpclient.exception.RequestException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Http 客户端抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractHttpClient extends AbstractBaseHttpClient<ConnectionManager> implements HttpClient {

	/**
	 * 构造函数
	 */
	public AbstractHttpClient() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param connectionManager
	 * 		连接管理器
	 */
	public AbstractHttpClient(ConnectionManager connectionManager) {
		super(connectionManager);
	}

	@Override
	public Response get(URI uri) throws IOException, RequestException {
		return get(uri, null, null);
	}

	@Override
	public Response get(URL url) throws IOException, RequestException {
		return execute(()->get(url.toURI()));
	}

	@Override
	public Response get(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return get(uri, parameters, null);
	}

	@Override
	public Response get(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->get(url.toURI(), parameters));
	}

	@Override
	public Response get(URI uri, List<Header> headers) throws IOException, RequestException {
		return get(uri, null, headers);
	}

	@Override
	public Response get(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->get(url.toURI(), headers));
	}

	@Override
	public Response get(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->get(url.toURI(), parameters, headers));
	}

	@Override
	public Response get(URI uri, int readTimeout) throws IOException, RequestException {
		return get(uri, readTimeout, null, null);
	}

	@Override
	public Response get(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->get(url.toURI(), readTimeout));
	}

	@Override
	public Response get(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException {
		return get(uri, readTimeout, parameters, null);
	}

	@Override
	public Response get(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->get(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response get(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return get(uri, readTimeout, null, headers);
	}

	@Override
	public Response get(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->get(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response get(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->get(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response post(URI uri) throws IOException, RequestException {
		return post(uri, null, null, null);
	}

	@Override
	public Response post(URL url) throws IOException, RequestException {
		return execute(()->post(url.toURI()));
	}

	@Override
	public Response post(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return post(uri, null, parameters, null);
	}

	@Override
	public Response post(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->post(url.toURI(), parameters));
	}

	@Override
	public Response post(URI uri, List<Header> headers) throws IOException, RequestException {
		return post(uri, null, null, headers);
	}

	@Override
	public Response post(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->post(url.toURI(), headers));
	}

	@Override
	public Response post(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return post(uri, null, parameters, headers);
	}

	@Override
	public Response post(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->post(url.toURI(), parameters, headers));
	}

	@Override
	public Response post(URI uri, RequestBody<?> data) throws IOException, RequestException {
		return post(uri, data, null, null);
	}

	@Override
	public Response post(URL url, RequestBody<?> data) throws IOException, RequestException {
		return execute(()->post(url.toURI(), data));
	}

	@Override
	public Response post(URI uri, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return post(uri, data, parameters, null);
	}

	@Override
	public Response post(URL url, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->post(url.toURI(), data, parameters));
	}

	@Override
	public Response post(URI uri, RequestBody<?> data, List<Header> headers) throws IOException, RequestException {
		return post(uri, data, null, headers);
	}

	@Override
	public Response post(URL url, RequestBody<?> data, List<Header> headers) throws IOException, RequestException {
		return execute(()->post(url.toURI(), data, headers));
	}

	@Override
	public Response post(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->post(url.toURI(), data, parameters, headers));
	}

	@Override
	public Response post(URI uri, int readTimeout) throws IOException, RequestException {
		return post(uri, readTimeout, null, null, null);
	}

	@Override
	public Response post(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->post(url.toURI(), readTimeout));
	}

	@Override
	public Response post(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return post(uri, readTimeout, null, parameters, null);
	}

	@Override
	public Response post(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->post(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response post(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return post(uri, readTimeout, null, null, headers);
	}

	@Override
	public Response post(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->post(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response post(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return post(uri, readTimeout, null, parameters, headers);
	}

	@Override
	public Response post(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->post(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response post(URI uri, int readTimeout, RequestBody<?> data) throws IOException, RequestException {
		return post(uri, readTimeout, data, null, null);
	}

	@Override
	public Response post(URL url, int readTimeout, RequestBody<?> data) throws IOException, RequestException {
		return execute(()->post(url.toURI(), readTimeout, data));
	}

	@Override
	public Response post(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return post(uri, readTimeout, data, parameters, null);
	}

	@Override
	public Response post(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->post(url.toURI(), readTimeout, data, parameters));
	}

	@Override
	public Response post(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return post(uri, readTimeout, data, null, headers);
	}

	@Override
	public Response post(URL url, int readTimeout, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->post(url.toURI(), readTimeout, data, headers));
	}

	@Override
	public Response post(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						 List<Header> headers) throws IOException, RequestException {
		return execute(()->post(url.toURI(), readTimeout, data, parameters, headers));
	}

	@Override
	public Response put(URI uri) throws IOException, RequestException {
		return put(uri, null, null, null);
	}

	@Override
	public Response put(URL url) throws IOException, RequestException {
		return execute(()->put(url.toURI()));
	}

	@Override
	public Response put(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return put(uri, null, parameters, null);
	}

	@Override
	public Response put(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->put(url.toURI(), parameters));
	}

	@Override
	public Response put(URI uri, List<Header> headers) throws IOException, RequestException {
		return put(uri, null, null, headers);
	}

	@Override
	public Response put(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->put(url.toURI(), headers));
	}

	@Override
	public Response put(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return put(uri, null, parameters, headers);
	}

	@Override
	public Response put(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->put(url.toURI(), parameters, headers));
	}

	@Override
	public Response put(URI uri, RequestBody<?> data) throws IOException, RequestException {
		return put(uri, data, null, null);
	}

	@Override
	public Response put(URL url, RequestBody<?> data) throws IOException, RequestException {
		return execute(()->put(url.toURI(), data));
	}

	@Override
	public Response put(URI uri, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return put(uri, data, parameters, null);
	}

	@Override
	public Response put(URL url, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->put(url.toURI(), data, parameters));
	}

	@Override
	public Response put(URI uri, RequestBody<?> data, List<Header> headers) throws IOException, RequestException {
		return put(uri, data, null, headers);
	}

	@Override
	public Response put(URL url, RequestBody<?> data, List<Header> headers) throws IOException, RequestException {
		return execute(()->put(url.toURI(), data, headers));
	}

	@Override
	public Response put(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->put(url.toURI(), data, parameters, headers));
	}

	@Override
	public Response put(URI uri, int readTimeout) throws IOException, RequestException {
		return put(uri, readTimeout, null, null, null);
	}

	@Override
	public Response put(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->put(url.toURI(), readTimeout));
	}

	@Override
	public Response put(URI uri, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException {
		return put(uri, readTimeout, null, parameters, null);
	}

	@Override
	public Response put(URL url, int readTimeout, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->put(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response put(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return put(uri, readTimeout, null, null, headers);
	}

	@Override
	public Response put(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->put(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response put(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return put(uri, readTimeout, null, parameters, headers);
	}

	@Override
	public Response put(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->put(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response put(URI uri, int readTimeout, RequestBody<?> data) throws IOException, RequestException {
		return put(uri, readTimeout, data, null, null);
	}

	@Override
	public Response put(URL url, int readTimeout, RequestBody<?> data) throws IOException, RequestException {
		return execute(()->put(url.toURI(), readTimeout, data));
	}

	@Override
	public Response put(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return put(uri, readTimeout, data, parameters, null);
	}

	@Override
	public Response put(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->put(url.toURI(), readTimeout, data, parameters));
	}

	@Override
	public Response put(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return put(uri, readTimeout, data, null, headers);
	}

	@Override
	public Response put(URL url, int readTimeout, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->put(url.toURI(), readTimeout, data, headers));
	}

	@Override
	public Response put(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						List<Header> headers) throws IOException, RequestException {
		return execute(()->put(url.toURI(), readTimeout, data, parameters, headers));
	}

	@Override
	public Response patch(URI uri) throws IOException, RequestException {
		return patch(uri, null, null, null);
	}

	@Override
	public Response patch(URL url) throws IOException, RequestException {
		return execute(()->patch(url.toURI()));
	}

	@Override
	public Response patch(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return patch(uri, null, parameters, null);
	}

	@Override
	public Response patch(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->patch(url.toURI(), parameters));
	}

	@Override
	public Response patch(URI uri, List<Header> headers) throws IOException, RequestException {
		return patch(uri, null, null, headers);
	}

	@Override
	public Response patch(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->patch(url.toURI(), headers));
	}

	@Override
	public Response patch(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return patch(uri, null, parameters, headers);
	}

	@Override
	public Response patch(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->patch(url.toURI(), parameters, headers));
	}

	@Override
	public Response patch(URI uri, RequestBody<?> data) throws IOException, RequestException {
		return patch(uri, data, null, null);
	}

	@Override
	public Response patch(URL url, RequestBody<?> data) throws IOException, RequestException {
		return execute(()->patch(url.toURI(), data));
	}

	@Override
	public Response patch(URI uri, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return patch(uri, data, parameters, null);
	}

	@Override
	public Response patch(URL url, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->patch(url.toURI(), data, parameters));
	}

	@Override
	public Response patch(URI uri, RequestBody<?> data, List<Header> headers) throws IOException, RequestException {
		return patch(uri, data, null, headers);
	}

	@Override
	public Response patch(URL url, RequestBody<?> data, List<Header> headers) throws IOException, RequestException {
		return execute(()->patch(url.toURI(), data, headers));
	}

	@Override
	public Response patch(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->patch(url.toURI(), data, parameters, headers));
	}

	@Override
	public Response patch(URI uri, int readTimeout) throws IOException, RequestException {
		return patch(uri, readTimeout, null, null, null);
	}

	@Override
	public Response patch(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->patch(url.toURI(), readTimeout));
	}

	@Override
	public Response patch(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return patch(uri, readTimeout, null, parameters, null);
	}

	@Override
	public Response patch(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->patch(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response patch(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return patch(uri, readTimeout, null, null, headers);
	}

	@Override
	public Response patch(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->patch(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response patch(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return patch(uri, readTimeout, null, parameters, headers);
	}

	@Override
	public Response patch(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->patch(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response patch(URI uri, int readTimeout, RequestBody<?> data) throws IOException, RequestException {
		return patch(uri, readTimeout, data, null, null);
	}

	@Override
	public Response patch(URL url, int readTimeout, RequestBody<?> data) throws IOException, RequestException {
		return execute(()->patch(url.toURI(), readTimeout, data));
	}

	@Override
	public Response patch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return patch(uri, readTimeout, data, parameters, null);
	}

	@Override
	public Response patch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->patch(url.toURI(), readTimeout, data, parameters));
	}

	@Override
	public Response patch(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return patch(uri, readTimeout, data, null, headers);
	}

	@Override
	public Response patch(URL url, int readTimeout, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->patch(url.toURI(), readTimeout, data, headers));
	}

	@Override
	public Response patch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						  List<Header> headers) throws IOException, RequestException {
		return execute(()->patch(url.toURI(), readTimeout, data, parameters, headers));
	}

	@Override
	public Response delete(URI uri) throws IOException, RequestException {
		return delete(uri, null, null);
	}

	@Override
	public Response delete(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return delete(uri, parameters, null);
	}

	@Override
	public Response delete(URL url) throws IOException, RequestException {
		return execute(()->delete(url.toURI()));
	}

	@Override
	public Response delete(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->delete(url.toURI(), parameters));
	}

	@Override
	public Response delete(URI uri, List<Header> headers) throws IOException, RequestException {
		return delete(uri, null, headers);
	}

	@Override
	public Response delete(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->delete(url.toURI(), headers));
	}

	@Override
	public Response delete(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->delete(url.toURI(), parameters, headers));
	}

	@Override
	public Response delete(URI uri, int readTimeout) throws IOException, RequestException {
		return delete(uri, readTimeout, null, null);
	}

	@Override
	public Response delete(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return delete(uri, readTimeout, parameters, null);
	}

	@Override
	public Response delete(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->delete(url.toURI(), readTimeout));
	}

	@Override
	public Response delete(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->delete(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response delete(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return delete(uri, readTimeout, null, headers);
	}

	@Override
	public Response delete(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->delete(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response delete(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->delete(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response connect(URI uri) throws IOException, RequestException {
		return connect(uri, null, null);
	}

	@Override
	public Response connect(URL url) throws IOException, RequestException {
		return execute(()->connect(url.toURI()));
	}

	@Override
	public Response connect(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return connect(uri, parameters, null);
	}

	@Override
	public Response connect(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->connect(url.toURI(), parameters));
	}

	@Override
	public Response connect(URI uri, List<Header> headers) throws IOException, RequestException {
		return connect(uri, null, headers);
	}

	@Override
	public Response connect(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->connect(url.toURI(), headers));
	}

	@Override
	public Response connect(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->connect(url.toURI(), parameters, headers));
	}

	@Override
	public Response connect(URI uri, int readTimeout) throws IOException, RequestException {
		return connect(uri, readTimeout, null, null);
	}

	@Override
	public Response connect(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->connect(url.toURI(), readTimeout));
	}

	@Override
	public Response connect(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return connect(uri, readTimeout, parameters, null);
	}

	@Override
	public Response connect(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->connect(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response connect(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return connect(uri, readTimeout, null, headers);
	}

	@Override
	public Response connect(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->connect(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response connect(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->connect(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response trace(URI uri) throws IOException, RequestException {
		return trace(uri, null, null);
	}

	@Override
	public Response trace(URL url) throws IOException, RequestException {
		return execute(()->trace(url.toURI()));
	}

	@Override
	public Response trace(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return trace(uri, parameters, null);
	}

	@Override
	public Response trace(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->trace(url.toURI(), parameters));
	}

	@Override
	public Response trace(URI uri, List<Header> headers) throws IOException, RequestException {
		return trace(uri, null, headers);
	}

	@Override
	public Response trace(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->trace(url.toURI(), headers));
	}

	@Override
	public Response trace(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->trace(url.toURI(), parameters, headers));
	}

	@Override
	public Response trace(URI uri, int readTimeout) throws IOException, RequestException {
		return trace(uri, readTimeout, null, null);
	}

	@Override
	public Response trace(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->trace(url.toURI(), readTimeout));
	}

	@Override
	public Response trace(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return trace(uri, readTimeout, parameters, null);
	}

	@Override
	public Response trace(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->trace(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response trace(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return trace(uri, readTimeout, null, headers);
	}

	@Override
	public Response trace(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->trace(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response trace(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->trace(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response copy(URI uri) throws IOException, RequestException {
		return copy(uri, null, null);
	}

	@Override
	public Response copy(URL url) throws IOException, RequestException {
		return execute(()->copy(url.toURI()));
	}

	@Override
	public Response copy(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return copy(uri, parameters, null);
	}

	@Override
	public Response copy(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->copy(url.toURI(), parameters));
	}

	@Override
	public Response copy(URI uri, List<Header> headers) throws IOException, RequestException {
		return copy(uri, null, headers);
	}

	@Override
	public Response copy(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->copy(url.toURI(), headers));
	}

	@Override
	public Response copy(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->copy(url.toURI(), parameters, headers));
	}

	@Override
	public Response copy(URI uri, int readTimeout) throws IOException, RequestException {
		return copy(uri, readTimeout, null, null);
	}

	@Override
	public Response copy(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->copy(url.toURI(), readTimeout));
	}

	@Override
	public Response copy(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return copy(uri, readTimeout, parameters, null);
	}

	@Override
	public Response copy(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->copy(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response copy(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return copy(uri, readTimeout, null, headers);
	}

	@Override
	public Response copy(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->copy(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response copy(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->copy(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response move(URI uri) throws IOException, RequestException {
		return move(uri, null, null);
	}

	@Override
	public Response move(URL url) throws IOException, RequestException {
		return execute(()->move(url.toURI()));
	}

	@Override
	public Response move(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return move(uri, parameters, null);
	}

	@Override
	public Response move(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->move(url.toURI(), parameters));
	}

	@Override
	public Response move(URI uri, List<Header> headers) throws IOException, RequestException {
		return move(uri, null, headers);
	}

	@Override
	public Response move(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->move(url.toURI(), headers));
	}

	@Override
	public Response move(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->move(url.toURI(), parameters, headers));
	}

	@Override
	public Response move(URI uri, int readTimeout) throws IOException, RequestException {
		return move(uri, readTimeout, null, null);
	}

	@Override
	public Response move(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->move(url.toURI(), readTimeout));
	}

	@Override
	public Response move(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return move(uri, readTimeout, parameters, null);
	}

	@Override
	public Response move(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->move(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response move(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return move(uri, readTimeout, null, headers);
	}

	@Override
	public Response move(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->move(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response move(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->move(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response head(URI uri) throws IOException, RequestException {
		return head(uri, null, null);
	}

	@Override
	public Response head(URL url) throws IOException, RequestException {
		return execute(()->head(url.toURI()));
	}

	@Override
	public Response head(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return head(uri, parameters, null);
	}

	@Override
	public Response head(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->head(url.toURI(), parameters));
	}

	@Override
	public Response head(URI uri, List<Header> headers) throws IOException, RequestException {
		return head(uri, null, headers);
	}

	@Override
	public Response head(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->head(url.toURI(), headers));
	}

	@Override
	public Response head(URL url, Map<String, Object> parameters, List<Header> headers) throws IOException,
			RequestException {
		return execute(()->head(url.toURI(), parameters, headers));
	}

	@Override
	public Response head(URI uri, int readTimeout) throws IOException, RequestException {
		return head(uri, readTimeout, null, null);
	}

	@Override
	public Response head(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->head(url.toURI(), readTimeout));
	}

	@Override
	public Response head(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return head(uri, readTimeout, parameters, null);
	}

	@Override
	public Response head(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->head(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response head(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return head(uri, readTimeout, null, headers);
	}

	@Override
	public Response head(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->head(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response head(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->head(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response options(URI uri) throws IOException, RequestException {
		return options(uri, null, null);
	}

	@Override
	public Response options(URL url) throws IOException, RequestException {
		return execute(()->options(url.toURI()));
	}

	@Override
	public Response options(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return options(uri, parameters, null);
	}

	@Override
	public Response options(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->options(url.toURI(), parameters));
	}

	@Override
	public Response options(URI uri, List<Header> headers) throws IOException, RequestException {
		return options(uri, null, headers);
	}

	@Override
	public Response options(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->options(url.toURI(), headers));
	}

	@Override
	public Response options(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->options(url.toURI(), parameters, headers));
	}

	@Override
	public Response options(URI uri, int readTimeout) throws IOException, RequestException {
		return options(uri, null, null);
	}

	@Override
	public Response options(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->options(url.toURI(), readTimeout));
	}

	@Override
	public Response options(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return options(uri, readTimeout, parameters, null);
	}

	@Override
	public Response options(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->options(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response options(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return options(uri, readTimeout, null, headers);
	}

	@Override
	public Response options(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->options(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response options(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->options(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response link(URI uri) throws IOException, RequestException {
		return link(uri, null, null);
	}

	@Override
	public Response link(URL url) throws IOException, RequestException {
		return execute(()->link(url.toURI()));
	}

	@Override
	public Response link(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return link(uri, parameters, null);
	}

	@Override
	public Response link(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->link(url.toURI(), parameters));
	}

	@Override
	public Response link(URI uri, List<Header> headers) throws IOException, RequestException {
		return link(uri, null, headers);
	}

	@Override
	public Response link(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->link(url.toURI(), headers));
	}

	@Override
	public Response link(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->link(url.toURI(), parameters, headers));
	}

	@Override
	public Response link(URI uri, int readTimeout) throws IOException, RequestException {
		return link(uri, readTimeout, null, null);
	}

	@Override
	public Response link(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->link(url.toURI(), readTimeout));
	}

	@Override
	public Response link(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return link(uri, readTimeout, parameters, null);
	}

	@Override
	public Response link(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->link(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response link(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return link(uri, readTimeout, null, headers);
	}

	@Override
	public Response link(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->link(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response link(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->link(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response unlink(URI uri) throws IOException, RequestException {
		return unlink(uri, null, null);
	}

	@Override
	public Response unlink(URL url) throws IOException, RequestException {
		return execute(()->unlink(url.toURI()));
	}

	@Override
	public Response unlink(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return unlink(uri, parameters, null);
	}

	@Override
	public Response unlink(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->unlink(url.toURI(), parameters));
	}

	@Override
	public Response unlink(URI uri, List<Header> headers) throws IOException, RequestException {
		return unlink(uri, null, headers);
	}

	@Override
	public Response unlink(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->unlink(url.toURI(), headers));
	}

	@Override
	public Response unlink(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->unlink(url.toURI(), parameters, headers));
	}

	@Override
	public Response unlink(URI uri, int readTimeout) throws IOException, RequestException {
		return unlink(uri, readTimeout, null, null);
	}

	@Override
	public Response unlink(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->unlink(url.toURI(), readTimeout));
	}

	@Override
	public Response unlink(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return unlink(uri, readTimeout, parameters, null);
	}

	@Override
	public Response unlink(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->unlink(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response unlink(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return unlink(uri, readTimeout, null, headers);
	}

	@Override
	public Response unlink(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->unlink(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response unlink(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->unlink(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response purge(URI uri) throws IOException, RequestException {
		return purge(uri, null, null);
	}

	@Override
	public Response purge(URL url) throws IOException, RequestException {
		return execute(()->purge(url.toURI()));
	}

	@Override
	public Response purge(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return purge(uri, parameters, null);
	}

	@Override
	public Response purge(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->purge(url.toURI(), parameters));
	}

	@Override
	public Response purge(URI uri, List<Header> headers) throws IOException, RequestException {
		return purge(uri, null, headers);
	}

	@Override
	public Response purge(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->purge(url.toURI(), headers));
	}

	@Override
	public Response purge(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->purge(url.toURI(), parameters, headers));
	}

	@Override
	public Response purge(URI uri, int readTimeout) throws IOException, RequestException {
		return purge(uri, null, null);
	}

	@Override
	public Response purge(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->purge(url.toURI(), readTimeout));
	}

	@Override
	public Response purge(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return purge(uri, readTimeout, parameters, null);
	}

	@Override
	public Response purge(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->purge(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response purge(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return purge(uri, readTimeout, null, headers);
	}

	@Override
	public Response purge(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->purge(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response purge(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->purge(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response lock(URI uri) throws IOException, RequestException {
		return lock(uri, null, null);
	}

	@Override
	public Response lock(URL url) throws IOException, RequestException {
		return execute(()->lock(url.toURI()));
	}

	@Override
	public Response lock(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return lock(uri, parameters, null);
	}

	@Override
	public Response lock(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->lock(url.toURI(), parameters));
	}

	@Override
	public Response lock(URI uri, List<Header> headers) throws IOException, RequestException {
		return lock(uri, null, headers);
	}

	@Override
	public Response lock(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->lock(url.toURI(), headers));
	}

	@Override
	public Response lock(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->lock(url.toURI(), parameters, headers));
	}

	@Override
	public Response lock(URI uri, int readTimeout) throws IOException, RequestException {
		return lock(uri, readTimeout, null, null);
	}

	@Override
	public Response lock(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->lock(url.toURI(), readTimeout));
	}

	@Override
	public Response lock(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return lock(uri, readTimeout, parameters, null);
	}

	@Override
	public Response lock(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->lock(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response lock(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return lock(uri, readTimeout, null, headers);
	}

	@Override
	public Response lock(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->lock(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response lock(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->lock(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response unlock(URI uri) throws IOException, RequestException {
		return unlock(uri, null, null);
	}

	@Override
	public Response unlock(URL url) throws IOException, RequestException {
		return execute(()->unlock(url.toURI()));
	}

	@Override
	public Response unlock(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return unlock(uri, parameters, null);
	}

	@Override
	public Response unlock(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->unlock(url.toURI(), parameters));
	}

	@Override
	public Response unlock(URI uri, List<Header> headers) throws IOException, RequestException {
		return unlock(uri, null, headers);
	}

	@Override
	public Response unlock(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->unlock(url.toURI(), headers));
	}

	@Override
	public Response unlock(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->unlock(url.toURI(), parameters, headers));
	}

	@Override
	public Response unlock(URI uri, int readTimeout) throws IOException, RequestException {
		return unlock(uri, readTimeout, null, null);
	}

	@Override
	public Response unlock(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->unlock(url.toURI(), readTimeout));
	}

	@Override
	public Response unlock(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return unlock(uri, readTimeout, parameters, null);
	}

	@Override
	public Response unlock(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->unlock(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response unlock(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return unlock(uri, readTimeout, null, headers);
	}

	@Override
	public Response unlock(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->unlock(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response unlock(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->unlock(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response propfind(URI uri) throws IOException, RequestException {
		return propfind(uri, null, null);
	}

	@Override
	public Response propfind(URL url) throws IOException, RequestException {
		return execute(()->propfind(url.toURI()));
	}

	@Override
	public Response propfind(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return propfind(uri, parameters, null);
	}

	@Override
	public Response propfind(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->propfind(url.toURI(), parameters));
	}

	@Override
	public Response propfind(URI uri, List<Header> headers) throws IOException, RequestException {
		return propfind(uri, null, headers);
	}

	@Override
	public Response propfind(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->propfind(url.toURI(), headers));
	}

	@Override
	public Response propfind(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->propfind(url.toURI(), parameters, headers));
	}

	@Override
	public Response propfind(URI uri, int readTimeout) throws IOException, RequestException {
		return propfind(uri, readTimeout, null, null);
	}

	@Override
	public Response propfind(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->propfind(url.toURI(), readTimeout));
	}

	@Override
	public Response propfind(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return propfind(uri, readTimeout, parameters, null);
	}

	@Override
	public Response propfind(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->propfind(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response propfind(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return propfind(uri, readTimeout, null, headers);
	}

	@Override
	public Response propfind(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->propfind(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response propfind(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->propfind(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response proppatch(URI uri) throws IOException, RequestException {
		return proppatch(uri, null, null, null);
	}

	@Override
	public Response proppatch(URL url) throws IOException, RequestException {
		return execute(()->proppatch(url.toURI()));
	}

	@Override
	public Response proppatch(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return proppatch(uri, null, parameters, null);
	}

	@Override
	public Response proppatch(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->proppatch(url.toURI(), parameters));
	}

	@Override
	public Response proppatch(URI uri, List<Header> headers) throws IOException, RequestException {
		return proppatch(uri, null, null, headers);
	}

	@Override
	public Response proppatch(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->proppatch(url.toURI(), headers));
	}

	@Override
	public Response proppatch(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return proppatch(uri, null, parameters, headers);
	}

	@Override
	public Response proppatch(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->proppatch(url.toURI(), parameters, headers));
	}

	@Override
	public Response proppatch(URI uri, RequestBody<?> data) throws IOException, RequestException {
		return proppatch(uri, data, null, null);
	}

	@Override
	public Response proppatch(URL url, RequestBody<?> data) throws IOException, RequestException {
		return execute(()->proppatch(url.toURI(), data));
	}

	@Override
	public Response proppatch(URI uri, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return proppatch(uri, data, parameters, null);
	}

	@Override
	public Response proppatch(URL url, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->proppatch(url.toURI(), data, parameters));
	}

	@Override
	public Response proppatch(URI uri, RequestBody<?> data, List<Header> headers) throws IOException, RequestException {
		return proppatch(uri, data, null, headers);
	}

	@Override
	public Response proppatch(URL url, RequestBody<?> data, List<Header> headers) throws IOException, RequestException {
		return execute(()->proppatch(url.toURI(), data, headers));
	}

	@Override
	public Response proppatch(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->proppatch(url.toURI(), data, parameters, headers));
	}

	@Override
	public Response proppatch(URI uri, int readTimeout) throws IOException, RequestException {
		return proppatch(uri, readTimeout, null, null, null);
	}

	@Override
	public Response proppatch(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->proppatch(url.toURI(), readTimeout));
	}

	@Override
	public Response proppatch(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return proppatch(uri, readTimeout, null, parameters, null);
	}

	@Override
	public Response proppatch(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->proppatch(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response proppatch(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return proppatch(uri, readTimeout, null, null, headers);
	}

	@Override
	public Response proppatch(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->proppatch(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response proppatch(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return proppatch(uri, readTimeout, null, parameters, headers);
	}

	@Override
	public Response proppatch(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->proppatch(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response proppatch(URI uri, int readTimeout, RequestBody<?> data) throws IOException, RequestException {
		return proppatch(uri, readTimeout, data, null, null);
	}

	@Override
	public Response proppatch(URL url, int readTimeout, RequestBody<?> data) throws IOException, RequestException {
		return execute(()->proppatch(url.toURI(), readTimeout, data));
	}

	@Override
	public Response proppatch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return proppatch(uri, readTimeout, data, parameters, null);
	}

	@Override
	public Response proppatch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->proppatch(url.toURI(), readTimeout, data, parameters));
	}

	@Override
	public Response proppatch(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return proppatch(uri, readTimeout, data, null, headers);
	}

	@Override
	public Response proppatch(URL url, int readTimeout, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->proppatch(url.toURI(), readTimeout, data, headers));
	}

	@Override
	public Response proppatch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
							  List<Header> headers) throws IOException, RequestException {
		return execute(()->proppatch(url.toURI(), readTimeout, data, parameters, headers));
	}

	@Override
	public Response report(URI uri) throws IOException, RequestException {
		return report(uri, null, null, null);
	}

	@Override
	public Response report(URL url) throws IOException, RequestException {
		return execute(()->report(url.toURI()));
	}

	@Override
	public Response report(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return report(uri, null, parameters, null);
	}

	@Override
	public Response report(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->report(url.toURI(), parameters));
	}

	@Override
	public Response report(URI uri, List<Header> headers) throws IOException, RequestException {
		return report(uri, null, null, headers);
	}

	@Override
	public Response report(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->report(url.toURI(), headers));
	}

	@Override
	public Response report(URI uri, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return report(uri, null, parameters, headers);
	}

	@Override
	public Response report(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->report(url.toURI(), parameters, headers));
	}

	@Override
	public Response report(URI uri, RequestBody<?> data) throws IOException, RequestException {
		return report(uri, data, null, null);
	}

	@Override
	public Response report(URL url, RequestBody<?> data) throws IOException, RequestException {
		return execute(()->report(url.toURI(), data));
	}

	@Override
	public Response report(URI uri, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return report(uri, data, parameters, null);
	}

	@Override
	public Response report(URL url, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->report(url.toURI(), data, parameters));
	}

	@Override
	public Response report(URI uri, RequestBody<?> data, List<Header> headers) throws IOException, RequestException {
		return report(uri, data, null, headers);
	}

	@Override
	public Response report(URL url, RequestBody<?> data, List<Header> headers) throws IOException, RequestException {
		return execute(()->report(url.toURI(), data, headers));
	}

	@Override
	public Response report(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->report(url.toURI(), data, parameters, headers));
	}

	@Override
	public Response report(URI uri, int readTimeout) throws IOException, RequestException {
		return report(uri, readTimeout, null, null, null);
	}

	@Override
	public Response report(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->report(url.toURI(), readTimeout));
	}

	@Override
	public Response report(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return report(uri, readTimeout, null, parameters, null);
	}

	@Override
	public Response report(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->report(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response report(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return report(uri, readTimeout, null, null, headers);
	}

	@Override
	public Response report(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->report(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response report(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return report(uri, readTimeout, null, parameters, headers);
	}

	@Override
	public Response report(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->report(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response report(URI uri, int readTimeout, RequestBody<?> data) throws IOException, RequestException {
		return report(uri, readTimeout, data, null, null);
	}

	@Override
	public Response report(URL url, int readTimeout, RequestBody<?> data) throws IOException, RequestException {
		return execute(()->report(url.toURI(), readTimeout, data));
	}

	@Override
	public Response report(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return report(uri, readTimeout, data, parameters, null);
	}

	@Override
	public Response report(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->report(url.toURI(), readTimeout, data, parameters));
	}

	@Override
	public Response report(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return report(uri, readTimeout, data, null, headers);
	}

	@Override
	public Response report(URL url, int readTimeout, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->report(url.toURI(), readTimeout, data, headers));
	}

	@Override
	public Response report(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
						   List<Header> headers) throws IOException, RequestException {
		return execute(()->report(url.toURI(), readTimeout, data, parameters, headers));
	}

	@Override
	public Response view(URI uri) throws IOException, RequestException {
		return view(uri, null, null);
	}

	@Override
	public Response view(URL url) throws IOException, RequestException {
		return execute(()->view(url.toURI()));
	}

	@Override
	public Response view(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return view(uri, parameters, null);
	}

	@Override
	public Response view(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->view(url.toURI(), parameters));
	}

	@Override
	public Response view(URI uri, List<Header> headers) throws IOException, RequestException {
		return view(uri, null, headers);
	}

	@Override
	public Response view(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->view(url.toURI(), headers));
	}

	@Override
	public Response view(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->view(url.toURI(), parameters, headers));
	}

	@Override
	public Response view(URI uri, int readTimeout) throws IOException, RequestException {
		return view(uri, readTimeout, null, null);
	}

	@Override
	public Response view(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->view(url.toURI(), readTimeout));
	}

	@Override
	public Response view(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return view(uri, readTimeout, parameters, null);
	}

	@Override
	public Response view(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->view(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response view(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return view(uri, readTimeout, null, headers);
	}

	@Override
	public Response view(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->view(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response view(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->view(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response wrapped(URI uri) throws IOException, RequestException {
		return wrapped(uri, null, null);
	}

	@Override
	public Response wrapped(URL url) throws IOException, RequestException {
		return execute(()->wrapped(url.toURI()));
	}

	@Override
	public Response wrapped(URI uri, Map<String, Object> parameters) throws IOException, RequestException {
		return wrapped(uri, parameters, null);
	}

	@Override
	public Response wrapped(URL url, Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->wrapped(url.toURI(), parameters));
	}

	@Override
	public Response wrapped(URI uri, List<Header> headers) throws IOException, RequestException {
		return wrapped(uri, null, headers);
	}

	@Override
	public Response wrapped(URL url, List<Header> headers) throws IOException, RequestException {
		return execute(()->wrapped(url.toURI(), headers));
	}

	@Override
	public Response wrapped(URL url, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->wrapped(url.toURI(), parameters, headers));
	}

	@Override
	public Response wrapped(URI uri, int readTimeout) throws IOException, RequestException {
		return wrapped(uri, readTimeout, null, null);
	}

	@Override
	public Response wrapped(URL url, int readTimeout) throws IOException, RequestException {
		return execute(()->wrapped(url.toURI(), readTimeout));
	}

	@Override
	public Response wrapped(URI uri, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return wrapped(uri, readTimeout, parameters, null);
	}

	@Override
	public Response wrapped(URL url, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->wrapped(url.toURI(), readTimeout, parameters));
	}

	@Override
	public Response wrapped(URI uri, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return wrapped(uri, readTimeout, null, headers);
	}

	@Override
	public Response wrapped(URL url, int readTimeout, List<Header> headers) throws IOException, RequestException {
		return execute(()->wrapped(url.toURI(), readTimeout, headers));
	}

	@Override
	public Response wrapped(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->wrapped(url.toURI(), readTimeout, parameters, headers));
	}

	@Override
	public Response request(URI uri, RequestMethod requestMethod) throws IOException, RequestException {
		return request(uri, requestMethod, null, null, null);
	}

	@Override
	public Response request(URL url, RequestMethod requestMethod) throws IOException, RequestException {
		return execute(()->request(url.toURI(), requestMethod));
	}

	@Override
	public Response request(URI uri, RequestMethod requestMethod, Map<String, Object> parameters)
			throws IOException, RequestException {
		return request(uri, requestMethod, null, parameters, null);
	}

	@Override
	public Response request(URL url, RequestMethod requestMethod, Map<String, Object> parameters) throws
			IOException, RequestException {
		return execute(()->request(url.toURI(), requestMethod, parameters));
	}

	@Override
	public Response request(URI uri, RequestMethod requestMethod, List<Header> headers)
			throws IOException, RequestException {
		return request(uri, requestMethod, null, null, headers);
	}

	@Override
	public Response request(URL url, RequestMethod requestMethod, List<Header> headers) throws IOException,
			RequestException {
		return execute(()->request(url.toURI(), requestMethod, headers));
	}

	@Override
	public Response request(URI uri, RequestMethod requestMethod, Map<String, Object> parameters,
							List<Header> headers) throws IOException, RequestException {
		return request(uri, requestMethod, null, parameters, headers);
	}

	@Override
	public Response request(URL url, RequestMethod requestMethod, Map<String, Object> parameters, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->request(url.toURI(), requestMethod, parameters, headers));
	}

	@Override
	public Response request(URI uri, RequestMethod requestMethod, RequestBody<?> data)
			throws IOException, RequestException {
		return request(uri, requestMethod, data, null, null);
	}

	@Override
	public Response request(URL url, RequestMethod requestMethod, RequestBody<?> data) throws IOException,
			RequestException {
		return execute(()->request(url.toURI(), requestMethod, data));
	}

	@Override
	public Response request(URI uri, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return request(uri, requestMethod, data, parameters, null);
	}

	@Override
	public Response request(URL url, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->request(url.toURI(), requestMethod, data, parameters));
	}

	@Override
	public Response request(URI uri, RequestMethod requestMethod, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return request(uri, requestMethod, data, null, headers);
	}

	@Override
	public Response request(URL url, RequestMethod requestMethod, RequestBody<?> data, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->request(url.toURI(), requestMethod, data, headers));
	}

	@Override
	public Response request(URI uri, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters,
							List<Header> headers) throws IOException, RequestException {
		Assert.isNull(requestMethod, "Request method could not be null.");

		switch(requestMethod){
			case POST:
				return post(uri, data, parameters, headers);
			case PUT:
				return put(uri, data, parameters, headers);
			case PATCH:
				return patch(uri, data, parameters, headers);
			case DELETE:
				return delete(uri, parameters, headers);
			case CONNECT:
				return connect(uri, parameters, headers);
			case TRACE:
				return trace(uri, parameters, headers);
			case COPY:
				return copy(uri, parameters, headers);
			case MOVE:
				return move(uri, parameters, headers);
			case HEAD:
				return head(uri, parameters, headers);
			case OPTIONS:
				return options(uri, parameters, headers);
			case LINK:
				return link(uri, parameters, headers);
			case UNLINK:
				return unlink(uri, parameters, headers);
			case PURGE:
				return purge(uri, parameters, headers);
			case LOCK:
				return lock(uri, parameters, headers);
			case UNLOCK:
				return unlock(uri, parameters, headers);
			case PROPFIND:
				return propfind(uri, parameters, headers);
			case PROPPATCH:
				return proppatch(uri, data, parameters, headers);
			case REPORT:
				return report(uri, data, parameters, headers);
			case VIEW:
				return view(uri, parameters, headers);
			case WRAPPED:
				return wrapped(uri, parameters, headers);
			default:
				return get(uri, parameters, headers);
		}
	}

	@Override
	public Response request(URL url, RequestMethod requestMethod, RequestBody<?> data, Map<String, Object> parameters,
							List<Header> headers) throws IOException, RequestException {
		return execute(()->request(url.toURI(), requestMethod, data, parameters, headers));
	}

	@Override
	public Response request(URI uri, RequestMethod requestMethod, int readTimeout)
			throws IOException, RequestException {
		return request(uri, requestMethod, readTimeout, null, null, null);
	}

	@Override
	public Response request(URL url, RequestMethod requestMethod, int readTimeout)
			throws IOException, RequestException {
		return execute(()->request(url.toURI(), requestMethod, readTimeout));
	}

	@Override
	public Response request(URI uri, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return request(uri, requestMethod, readTimeout, null, parameters, null);
	}

	@Override
	public Response request(URL url, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters)
			throws IOException, RequestException {
		return execute(()->request(url.toURI(), requestMethod, readTimeout, parameters));
	}

	@Override
	public Response request(URI uri, RequestMethod requestMethod, int readTimeout, List<Header> headers)
			throws IOException, RequestException {
		return request(uri, requestMethod, readTimeout, null, null, headers);
	}

	@Override
	public Response request(URL url, RequestMethod requestMethod, int readTimeout, List<Header> headers)
			throws IOException, RequestException {
		return execute(()->request(url.toURI(), requestMethod, readTimeout, headers));
	}

	@Override
	public Response request(URI uri, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters,
							List<Header> headers) throws IOException, RequestException {
		return request(uri, requestMethod, readTimeout, null, parameters, headers);
	}

	@Override
	public Response request(URL url, RequestMethod requestMethod, int readTimeout, Map<String, Object> parameters,
							List<Header> headers) throws IOException, RequestException {
		return execute(()->request(url.toURI(), requestMethod, readTimeout, parameters, headers));
	}

	@Override
	public Response request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data)
			throws IOException, RequestException {
		return request(uri, requestMethod, readTimeout, data, null, null);
	}

	@Override
	public Response request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data)
			throws IOException, RequestException {
		return execute(()->request(url.toURI(), requestMethod, readTimeout, data));
	}

	@Override
	public Response request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
							Map<String, Object> parameters)
			throws IOException, RequestException {
		return request(uri, requestMethod, readTimeout, data, parameters, null);
	}

	@Override
	public Response request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
							Map<String, Object> parameters) throws IOException, RequestException {
		return execute(()->request(url.toURI(), requestMethod, readTimeout, data, parameters));
	}

	@Override
	public Response request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
							List<Header> headers) throws IOException, RequestException {
		return request(uri, requestMethod, readTimeout, data, null, headers);
	}

	@Override
	public Response request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
							List<Header> headers) throws IOException, RequestException {
		return execute(()->request(url.toURI(), requestMethod, readTimeout, data, headers));
	}

	@Override
	public Response request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
							Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException {
		Assert.isNull(requestMethod, "Request method could not be null.");

		switch(requestMethod){
			case POST:
				return post(uri, readTimeout, data, parameters, headers);
			case PUT:
				return put(uri, readTimeout, data, parameters, headers);
			case PATCH:
				return patch(uri, readTimeout, data, parameters, headers);
			case DELETE:
				return delete(uri, readTimeout, parameters, headers);
			case CONNECT:
				return connect(uri, readTimeout, parameters, headers);
			case TRACE:
				return trace(uri, readTimeout, parameters, headers);
			case COPY:
				return copy(uri, readTimeout, parameters, headers);
			case MOVE:
				return move(uri, readTimeout, parameters, headers);
			case HEAD:
				return head(uri, readTimeout, parameters, headers);
			case OPTIONS:
				return options(uri, readTimeout, parameters, headers);
			case LINK:
				return link(uri, readTimeout, parameters, headers);
			case UNLINK:
				return unlink(uri, readTimeout, parameters, headers);
			case PURGE:
				return purge(uri, readTimeout, parameters, headers);
			case LOCK:
				return lock(uri, readTimeout, parameters, headers);
			case UNLOCK:
				return unlock(uri, readTimeout, parameters, headers);
			case PROPFIND:
				return propfind(uri, readTimeout, parameters, headers);
			case PROPPATCH:
				return proppatch(uri, readTimeout, data, parameters, headers);
			case REPORT:
				return report(uri, readTimeout, data, parameters, headers);
			case VIEW:
				return view(uri, readTimeout, parameters, headers);
			case WRAPPED:
				return wrapped(uri, readTimeout, parameters, headers);
			default:
				return get(uri, readTimeout, parameters, headers);
		}
	}

	@Override
	public Response request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
							Map<String, Object> parameters, List<Header> headers) throws IOException, RequestException {
		return execute(()->request(url.toURI(), requestMethod, readTimeout, data, parameters, headers));
	}

	protected static <T> T execute(final Execute<T> execute) throws IOException, RequestException {
		try{
			return execute.exec();
		}catch(URISyntaxException e){
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	@FunctionalInterface
	protected interface Execute<T> {

		T exec() throws URISyntaxException, IOException, RequestException;

	}

}
