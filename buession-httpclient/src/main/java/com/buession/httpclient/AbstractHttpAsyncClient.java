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
package com.buession.httpclient;

import com.buession.core.utils.Assert;
import com.buession.httpclient.conn.ConnectionManager;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.RequestMethod;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.core.concurrent.Callback;
import com.buession.httpclient.exception.RequestException;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

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
	public AbstractHttpAsyncClient(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param connectionManager
	 * 		连接管理器
	 */
	public AbstractHttpAsyncClient(ConnectionManager connectionManager){
		super(connectionManager);
	}

	@Override
	public Future<Response> get(URI uri, Callback callback) throws IOException, RequestException{
		return get(uri, null, null, callback);
	}

	@Override
	public Future<Response> get(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->get(URL2URI(url), null, null, callback));
	}

	@Override
	public Future<Response> get(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return get(uri, parameters, null, callback);
	}

	@Override
	public Future<Response> get(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->get(URL2URI(url), parameters, null, callback));
	}

	@Override
	public Future<Response> get(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return get(uri, null, headers, callback);
	}

	@Override
	public Future<Response> get(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->get(URL2URI(url), null, headers, callback));
	}

	@Override
	public Future<Response> get(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->get(URL2URI(url), parameters, headers, callback));
	}

	@Override
	public Future<Response> get(URI uri, int readTimeout, Callback callback) throws IOException, RequestException{
		return get(uri, readTimeout, null, null, callback);
	}

	@Override
	public Future<Response> get(URL url, int readTimeout, Callback callback) throws IOException, RequestException{
		return execute(()->get(URL2URI(url), readTimeout, null, null, callback));
	}

	@Override
	public Future<Response> get(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return get(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public Future<Response> get(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->get(URL2URI(url), readTimeout, parameters, null, callback));
	}

	@Override
	public Future<Response> get(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return get(uri, readTimeout, null, headers, callback);
	}

	@Override
	public Future<Response> get(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->get(URL2URI(url), readTimeout, null, headers, callback));
	}

	@Override
	public Future<Response> get(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								Callback callback) throws IOException, RequestException{
		return execute(()->get(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> post(URI uri, Callback callback) throws IOException, RequestException{
		return post(uri, null, null, null, callback);
	}

	@Override
	public Future<Response> post(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->post(URL2URI(url), null, null, null, callback));
	}

	@Override
	public Future<Response> post(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return post(uri, null, parameters, null, callback);
	}

	@Override
	public Future<Response> post(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->post(URL2URI(url), null, parameters, null, callback));
	}

	@Override
	public Future<Response> post(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return post(uri, null, null, headers, callback);
	}

	@Override
	public Future<Response> post(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->post(URL2URI(url), null, null, headers, callback));
	}

	@Override
	public Future<Response> post(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return post(uri, null, parameters, headers, callback);
	}

	@Override
	public Future<Response> post(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->post(URL2URI(url), null, parameters, headers, callback));
	}

	@Override
	public Future<Response> post(URI uri, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return post(uri, data, null, null, callback);
	}

	@Override
	public Future<Response> post(URL url, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return execute(()->post(URL2URI(url), data, null, null, callback));
	}

	@Override
	public Future<Response> post(URI uri, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return post(uri, data, parameters, null, callback);
	}

	@Override
	public Future<Response> post(URL url, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->post(URL2URI(url), data, parameters, null, callback));
	}

	@Override
	public Future<Response> post(URI uri, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return post(uri, data, null, headers, callback);
	}

	@Override
	public Future<Response> post(URL url, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->post(URL2URI(url), data, null, headers, callback));
	}

	@Override
	public Future<Response> post(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return execute(()->post(URL2URI(url), data, parameters, headers, callback));
	}

	@Override
	public Future<Response> post(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return post(uri, readTimeout, null, null, null, callback);
	}

	@Override
	public Future<Response> post(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->post(URL2URI(url), readTimeout, null, null, null, callback));
	}

	@Override
	public Future<Response> post(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return post(uri, readTimeout, null, parameters, null, callback);
	}

	@Override
	public Future<Response> post(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->post(URL2URI(url), readTimeout, null, parameters, null, callback));
	}

	@Override
	public Future<Response> post(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return post(uri, readTimeout, null, null, headers, callback);
	}

	@Override
	public Future<Response> post(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->post(URL2URI(url), readTimeout, null, null, headers, callback));
	}

	@Override
	public Future<Response> post(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return post(uri, readTimeout, null, parameters, headers, callback);
	}

	@Override
	public Future<Response> post(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return execute(()->post(URL2URI(url), readTimeout, null, parameters, headers, callback));
	}

	@Override
	public Future<Response> post(URI uri, int readTimeout, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return post(uri, readTimeout, data, null, null, callback);
	}

	@Override
	public Future<Response> post(URL url, int readTimeout, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return execute(()->post(URL2URI(url), readTimeout, data, null, null, callback));
	}

	@Override
	public Future<Response> post(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								 Callback callback) throws IOException, RequestException{
		return post(uri, readTimeout, data, parameters, null, callback);
	}

	@Override
	public Future<Response> post(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								 Callback callback) throws IOException, RequestException{
		return execute(()->post(URL2URI(url), readTimeout, data, parameters, null, callback));
	}

	@Override
	public Future<Response> post(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return post(uri, readTimeout, data, null, headers, callback);
	}

	@Override
	public Future<Response> post(URL url, int readTimeout, RequestBody<?> data, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return execute(()->post(URL2URI(url), readTimeout, data, null, headers, callback));
	}

	@Override
	public Future<Response> post(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								 List<Header> headers, Callback callback) throws IOException, RequestException{
		return execute(()->post(URL2URI(url), readTimeout, data, parameters, headers, callback));
	}

	@Override
	public Future<Response> put(URI uri, Callback callback) throws IOException, RequestException{
		return put(uri, null, null, null, callback);
	}

	@Override
	public Future<Response> put(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->put(URL2URI(url), null, null, null, callback));
	}

	@Override
	public Future<Response> put(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return put(uri, null, parameters, null, callback);
	}

	@Override
	public Future<Response> put(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->put(URL2URI(url), null, parameters, null, callback));
	}

	@Override
	public Future<Response> put(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return put(uri, null, null, headers, callback);
	}

	@Override
	public Future<Response> put(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->put(URL2URI(url), null, null, headers, callback));
	}

	@Override
	public Future<Response> put(URI uri, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return put(uri, null, parameters, headers, callback);
	}

	@Override
	public Future<Response> put(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->put(URL2URI(url), null, parameters, headers, callback));
	}

	@Override
	public Future<Response> put(URI uri, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return put(uri, data, null, null, callback);
	}

	@Override
	public Future<Response> put(URL url, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return execute(()->put(URL2URI(url), data, null, null, callback));
	}

	@Override
	public Future<Response> put(URI uri, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return put(uri, data, parameters, null, callback);
	}

	@Override
	public Future<Response> put(URL url, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->put(URL2URI(url), data, parameters, null, callback));
	}

	@Override
	public Future<Response> put(URI uri, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return put(uri, data, null, headers, callback);
	}

	@Override
	public Future<Response> put(URL url, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->put(URL2URI(url), data, null, headers, callback));
	}

	@Override
	public Future<Response> put(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
								Callback callback) throws IOException, RequestException{
		return execute(()->put(URL2URI(url), data, parameters, headers, callback));
	}

	@Override
	public Future<Response> put(URI uri, int readTimeout, Callback callback) throws IOException, RequestException{
		return put(uri, readTimeout, null, null, null, callback);
	}

	@Override
	public Future<Response> put(URL url, int readTimeout, Callback callback) throws IOException, RequestException{
		return execute(()->put(URL2URI(url), readTimeout, null, null, null, callback));
	}

	@Override
	public Future<Response> put(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return put(uri, readTimeout, null, parameters, null, callback);
	}

	@Override
	public Future<Response> put(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->put(URL2URI(url), readTimeout, null, parameters, null, callback));
	}

	@Override
	public Future<Response> put(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return put(uri, readTimeout, null, null, headers, callback);
	}

	@Override
	public Future<Response> put(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->put(URL2URI(url), readTimeout, null, null, headers, callback));
	}

	@Override
	public Future<Response> put(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								Callback callback) throws IOException, RequestException{
		return put(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public Future<Response> put(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								Callback callback) throws IOException, RequestException{
		return execute(()->put(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> put(URI uri, int readTimeout, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return put(uri, readTimeout, data, null, null, callback);
	}

	@Override
	public Future<Response> put(URL url, int readTimeout, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return execute(()->put(URL2URI(url), readTimeout, data, null, null, callback));
	}

	@Override
	public Future<Response> put(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								Callback callback) throws IOException, RequestException{
		return put(uri, readTimeout, data, parameters, null, callback);
	}

	@Override
	public Future<Response> put(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								Callback callback) throws IOException, RequestException{
		return execute(()->put(URL2URI(url), readTimeout, data, parameters, null, callback));
	}

	@Override
	public Future<Response> put(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers,
								Callback callback) throws IOException, RequestException{
		return put(uri, readTimeout, data, null, headers, callback);
	}

	@Override
	public Future<Response> put(URL url, int readTimeout, RequestBody<?> data, List<Header> headers,
								Callback callback) throws IOException, RequestException{
		return execute(()->put(URL2URI(url), readTimeout, data, null, headers, callback));
	}

	@Override
	public Future<Response> put(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								List<Header> headers, Callback callback) throws IOException, RequestException{
		return execute(()->put(URL2URI(url), readTimeout, data, parameters, headers, callback));
	}

	@Override
	public Future<Response> patch(URI uri, Callback callback) throws IOException, RequestException{
		return patch(uri, null, null, null, callback);
	}

	@Override
	public Future<Response> patch(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->patch(URL2URI(url), null, null, null, callback));
	}

	@Override
	public Future<Response> patch(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return patch(uri, null, parameters, null, callback);
	}

	@Override
	public Future<Response> patch(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->patch(URL2URI(url), null, parameters, null, callback));
	}

	@Override
	public Future<Response> patch(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return patch(uri, null, null, headers, callback);
	}

	@Override
	public Future<Response> patch(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->patch(URL2URI(url), null, null, headers, callback));
	}

	@Override
	public Future<Response> patch(URI uri, Map<String, Object> parameters, List<Header> headers,
								  Callback callback) throws IOException, RequestException{
		return patch(uri, null, parameters, headers, callback);
	}

	@Override
	public Future<Response> patch(URL url, Map<String, Object> parameters, List<Header> headers,
								  Callback callback) throws IOException, RequestException{
		return execute(()->patch(URL2URI(url), null, parameters, headers, callback));
	}

	@Override
	public Future<Response> patch(URI uri, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return patch(uri, data, null, null, callback);
	}

	@Override
	public Future<Response> patch(URL url, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return execute(()->patch(URL2URI(url), data, null, null, callback));
	}

	@Override
	public Future<Response> patch(URI uri, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return patch(uri, data, parameters, null, callback);
	}

	@Override
	public Future<Response> patch(URL url, RequestBody<?> data, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->patch(URL2URI(url), data, parameters, null, callback));
	}

	@Override
	public Future<Response> patch(URI uri, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return patch(uri, data, null, headers, callback);
	}

	@Override
	public Future<Response> patch(URL url, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->patch(URL2URI(url), data, null, headers, callback));
	}

	@Override
	public Future<Response> patch(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
								  Callback callback) throws IOException, RequestException{
		return execute(()->patch(URL2URI(url), data, parameters, headers, callback));
	}

	@Override
	public Future<Response> patch(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return patch(uri, readTimeout, null, null, null, callback);
	}

	@Override
	public Future<Response> patch(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->patch(URL2URI(url), readTimeout, null, null, null, callback));
	}

	@Override
	public Future<Response> patch(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return patch(uri, readTimeout, null, parameters, null, callback);
	}

	@Override
	public Future<Response> patch(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->patch(URL2URI(url), readTimeout, null, parameters, null, callback));
	}

	@Override
	public Future<Response> patch(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return patch(uri, readTimeout, null, null, headers, callback);
	}

	@Override
	public Future<Response> patch(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->patch(URL2URI(url), readTimeout, null, null, headers, callback));
	}

	@Override
	public Future<Response> patch(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								  Callback callback) throws IOException, RequestException{
		return patch(uri, readTimeout, null, parameters, headers, callback);
	}

	@Override
	public Future<Response> patch(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								  Callback callback) throws IOException, RequestException{
		return execute(()->patch(URL2URI(url), readTimeout, null, parameters, headers, callback));
	}

	@Override
	public Future<Response> patch(URI uri, int readTimeout, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return patch(uri, readTimeout, data, null, null, callback);
	}

	@Override
	public Future<Response> patch(URL url, int readTimeout, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return execute(()->patch(URL2URI(url), readTimeout, data, null, null, callback));
	}

	@Override
	public Future<Response> patch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								  Callback callback) throws IOException, RequestException{
		return patch(uri, readTimeout, data, parameters, null, callback);
	}

	@Override
	public Future<Response> patch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								  Callback callback) throws IOException, RequestException{
		return execute(()->patch(URL2URI(url), readTimeout, data, parameters, null, callback));
	}

	@Override
	public Future<Response> patch(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers,
								  Callback callback) throws IOException, RequestException{
		return patch(uri, readTimeout, data, null, headers, callback);
	}

	@Override
	public Future<Response> patch(URL url, int readTimeout, RequestBody<?> data, List<Header> headers,
								  Callback callback) throws IOException, RequestException{
		return execute(()->patch(URL2URI(url), readTimeout, data, null, headers, callback));
	}

	@Override
	public Future<Response> patch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								  List<Header> headers, Callback callback) throws IOException, RequestException{
		return execute(()->patch(URL2URI(url), readTimeout, data, parameters, headers, callback));
	}

	@Override
	public Future<Response> delete(URI uri, Callback callback) throws IOException, RequestException{
		return delete(uri, null, null, callback);
	}

	@Override
	public Future<Response> delete(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->delete(URL2URI(url), null, null, callback));
	}

	@Override
	public Future<Response> delete(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return delete(uri, parameters, null, callback);
	}

	@Override
	public Future<Response> delete(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->delete(URL2URI(url), parameters, null, callback));
	}

	@Override
	public Future<Response> delete(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return delete(uri, null, headers, callback);
	}

	@Override
	public Future<Response> delete(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->delete(URL2URI(url), null, headers, callback));
	}

	@Override
	public Future<Response> delete(URL url, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return execute(()->delete(URL2URI(url), parameters, headers, callback));
	}

	@Override
	public Future<Response> delete(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return delete(uri, readTimeout, null, null, callback);
	}

	@Override
	public Future<Response> delete(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->delete(URL2URI(url), readTimeout, null, null, callback));
	}

	@Override
	public Future<Response> delete(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return delete(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public Future<Response> delete(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->delete(URL2URI(url), readTimeout, parameters, null, callback));
	}

	@Override
	public Future<Response> delete(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return delete(uri, readTimeout, null, headers, callback);
	}

	@Override
	public Future<Response> delete(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->delete(URL2URI(url), readTimeout, null, headers, callback));
	}

	@Override
	public Future<Response> delete(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return execute(()->delete(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> connect(URI uri, Callback callback) throws IOException, RequestException{
		return connect(uri, null, null, callback);
	}

	@Override
	public Future<Response> connect(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->connect(URL2URI(url), null, null, callback));
	}

	@Override
	public Future<Response> connect(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return connect(uri, parameters, null, callback);
	}

	@Override
	public Future<Response> connect(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->connect(URL2URI(url), parameters, null, callback));
	}

	@Override
	public Future<Response> connect(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return connect(uri, null, headers, callback);
	}

	@Override
	public Future<Response> connect(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->connect(URL2URI(url), null, headers, callback));
	}

	@Override
	public Future<Response> connect(URL url, Map<String, Object> parameters, List<Header> headers,
									Callback callback) throws IOException, RequestException{
		return execute(()->connect(URL2URI(url), parameters, headers, callback));
	}

	@Override
	public Future<Response> connect(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return connect(uri, readTimeout, null, null, callback);
	}

	@Override
	public Future<Response> connect(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->connect(URL2URI(url), readTimeout, null, null, callback));
	}

	@Override
	public Future<Response> connect(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return connect(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public Future<Response> connect(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->connect(URL2URI(url), readTimeout, parameters, null, callback));
	}

	@Override
	public Future<Response> connect(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return connect(uri, readTimeout, null, headers, callback);
	}

	@Override
	public Future<Response> connect(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->connect(URL2URI(url), readTimeout, null, headers, callback));
	}

	@Override
	public Future<Response> connect(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
									Callback callback) throws IOException, RequestException{
		return execute(()->connect(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> trace(URI uri, Callback callback) throws IOException, RequestException{
		return trace(uri, null, null, callback);
	}

	@Override
	public Future<Response> trace(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->trace(URL2URI(url), null, null, callback));
	}

	@Override
	public Future<Response> trace(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return trace(uri, parameters, null, callback);
	}

	@Override
	public Future<Response> trace(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->trace(URL2URI(url), parameters, null, callback));
	}

	@Override
	public Future<Response> trace(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return trace(uri, null, headers, callback);
	}

	@Override
	public Future<Response> trace(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->trace(URL2URI(url), null, headers, callback));
	}

	@Override
	public Future<Response> trace(URL url, Map<String, Object> parameters, List<Header> headers,
								  Callback callback) throws IOException, RequestException{
		return execute(()->trace(URL2URI(url), parameters, headers, callback));
	}

	@Override
	public Future<Response> trace(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return trace(uri, readTimeout, null, null, callback);
	}

	@Override
	public Future<Response> trace(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->trace(URL2URI(url), readTimeout, null, null, callback));
	}

	@Override
	public Future<Response> trace(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return trace(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public Future<Response> trace(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->trace(URL2URI(url), readTimeout, parameters, null, callback));
	}

	@Override
	public Future<Response> trace(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return trace(uri, readTimeout, null, headers, callback);
	}

	@Override
	public Future<Response> trace(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->trace(URL2URI(url), readTimeout, null, headers, callback));
	}

	@Override
	public Future<Response> trace(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								  Callback callback) throws IOException, RequestException{
		return execute(()->trace(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> copy(URI uri, Callback callback) throws IOException, RequestException{
		return copy(uri, null, null, callback);
	}

	@Override
	public Future<Response> copy(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->copy(URL2URI(url), null, null, callback));
	}

	@Override
	public Future<Response> copy(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return copy(uri, parameters, null, callback);
	}

	@Override
	public Future<Response> copy(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->copy(URL2URI(url), parameters, null, callback));
	}

	@Override
	public Future<Response> copy(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return copy(uri, null, headers, callback);
	}

	@Override
	public Future<Response> copy(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->copy(URL2URI(url), null, headers, callback));
	}

	@Override
	public Future<Response> copy(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->copy(URL2URI(url), parameters, headers, callback));
	}

	@Override
	public Future<Response> copy(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return copy(uri, readTimeout, null, null, callback);
	}

	@Override
	public Future<Response> copy(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->copy(URL2URI(url), readTimeout, null, null, callback));
	}

	@Override
	public Future<Response> copy(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return copy(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public Future<Response> copy(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->copy(URL2URI(url), readTimeout, parameters, null, callback));
	}

	@Override
	public Future<Response> copy(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return copy(uri, readTimeout, null, headers, callback);
	}

	@Override
	public Future<Response> copy(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->copy(URL2URI(url), readTimeout, null, headers, callback));
	}

	@Override
	public Future<Response> copy(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return execute(()->copy(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> move(URI uri, Callback callback) throws IOException, RequestException{
		return move(uri, null, null, callback);
	}

	@Override
	public Future<Response> move(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->move(URL2URI(url), null, null, callback));
	}

	@Override
	public Future<Response> move(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return move(uri, parameters, null, callback);
	}

	@Override
	public Future<Response> move(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->move(URL2URI(url), parameters, null, callback));
	}

	@Override
	public Future<Response> move(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return move(uri, null, headers, callback);
	}

	@Override
	public Future<Response> move(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->move(URL2URI(url), null, headers, callback));
	}

	@Override
	public Future<Response> move(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->move(URL2URI(url), parameters, headers, callback));
	}

	@Override
	public Future<Response> move(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return move(uri, readTimeout, null, null, callback);
	}

	@Override
	public Future<Response> move(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->move(URL2URI(url), readTimeout, null, null, callback));
	}

	@Override
	public Future<Response> move(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return move(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public Future<Response> move(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->move(URL2URI(url), readTimeout, parameters, null, callback));
	}

	@Override
	public Future<Response> move(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return move(uri, readTimeout, null, headers, callback);
	}

	@Override
	public Future<Response> move(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->move(URL2URI(url), readTimeout, null, headers, callback));
	}

	@Override
	public Future<Response> move(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return execute(()->move(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> head(URI uri, Callback callback) throws IOException, RequestException{
		return head(uri, null, null, callback);
	}

	@Override
	public Future<Response> head(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->head(URL2URI(url), null, null, callback));
	}

	@Override
	public Future<Response> head(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return head(uri, parameters, null, callback);
	}

	@Override
	public Future<Response> head(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->head(URL2URI(url), parameters, null, callback));
	}

	@Override
	public Future<Response> head(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return head(uri, null, headers, callback);
	}

	@Override
	public Future<Response> head(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->head(URL2URI(url), null, headers, callback));
	}

	@Override
	public Future<Response> head(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->head(URL2URI(url), parameters, headers, callback));
	}

	@Override
	public Future<Response> head(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return head(uri, readTimeout, null, null, callback);
	}

	@Override
	public Future<Response> head(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->head(URL2URI(url), readTimeout, null, null, callback));
	}

	@Override
	public Future<Response> head(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return head(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public Future<Response> head(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->head(URL2URI(url), readTimeout, parameters, null, callback));
	}

	@Override
	public Future<Response> head(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return head(uri, readTimeout, null, headers, callback);
	}

	@Override
	public Future<Response> head(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->head(URL2URI(url), readTimeout, null, headers, callback));
	}

	@Override
	public Future<Response> head(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return execute(()->head(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> options(URI uri, Callback callback) throws IOException, RequestException{
		return options(uri, null, null, callback);
	}

	@Override
	public Future<Response> options(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->options(URL2URI(url), null, null, callback));
	}

	@Override
	public Future<Response> options(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return options(uri, parameters, null, callback);
	}

	@Override
	public Future<Response> options(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->options(URL2URI(url), parameters, null, callback));
	}

	@Override
	public Future<Response> options(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return options(uri, null, headers, callback);
	}

	@Override
	public Future<Response> options(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->options(URL2URI(url), null, headers, callback));
	}

	@Override
	public Future<Response> options(URL url, Map<String, Object> parameters, List<Header> headers,
									Callback callback) throws IOException, RequestException{
		return execute(()->options(URL2URI(url), parameters, headers, callback));
	}

	@Override
	public Future<Response> options(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return options(uri, readTimeout, null, null, callback);
	}

	@Override
	public Future<Response> options(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->options(URL2URI(url), readTimeout, null, null, callback));
	}

	@Override
	public Future<Response> options(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return options(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public Future<Response> options(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->options(URL2URI(url), readTimeout, parameters, null, callback));
	}

	@Override
	public Future<Response> options(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return options(uri, readTimeout, null, headers, callback);
	}

	@Override
	public Future<Response> options(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->options(URL2URI(url), readTimeout, null, headers, callback));
	}

	@Override
	public Future<Response> options(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
									Callback callback) throws IOException, RequestException{
		return execute(()->options(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> link(URI uri, Callback callback) throws IOException, RequestException{
		return link(uri, null, null, callback);
	}

	@Override
	public Future<Response> link(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->link(URL2URI(url), null, null, callback));
	}

	@Override
	public Future<Response> link(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return link(uri, parameters, null, callback);
	}

	@Override
	public Future<Response> link(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->link(URL2URI(url), parameters, null, callback));
	}

	@Override
	public Future<Response> link(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return link(uri, null, headers, callback);
	}

	@Override
	public Future<Response> link(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->link(URL2URI(url), null, headers, callback));
	}

	@Override
	public Future<Response> link(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->link(URL2URI(url), parameters, headers, callback));
	}

	@Override
	public Future<Response> link(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return link(uri, readTimeout, null, null, callback);
	}

	@Override
	public Future<Response> link(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->link(URL2URI(url), readTimeout, null, null, callback));
	}

	@Override
	public Future<Response> link(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return link(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public Future<Response> link(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->link(URL2URI(url), readTimeout, parameters, null, callback));
	}

	@Override
	public Future<Response> link(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return link(uri, readTimeout, null, headers, callback);
	}

	@Override
	public Future<Response> link(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->link(URL2URI(url), readTimeout, null, headers, callback));
	}

	@Override
	public Future<Response> link(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return execute(()->link(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> unlink(URI uri, Callback callback) throws IOException, RequestException{
		return unlink(uri, null, null, callback);
	}

	@Override
	public Future<Response> unlink(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->unlink(URL2URI(url), null, null, callback));
	}

	@Override
	public Future<Response> unlink(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return unlink(uri, parameters, null, callback);
	}

	@Override
	public Future<Response> unlink(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->unlink(URL2URI(url), parameters, null, callback));
	}

	@Override
	public Future<Response> unlink(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return unlink(uri, null, headers, callback);
	}

	@Override
	public Future<Response> unlink(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->unlink(URL2URI(url), null, headers, callback));
	}

	@Override
	public Future<Response> unlink(URL url, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return execute(()->unlink(URL2URI(url), parameters, headers, callback));
	}

	@Override
	public Future<Response> unlink(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return unlink(uri, readTimeout, null, null, callback);
	}

	@Override
	public Future<Response> unlink(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->unlink(URL2URI(url), readTimeout, null, null, callback));
	}

	@Override
	public Future<Response> unlink(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return unlink(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public Future<Response> unlink(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->unlink(URL2URI(url), readTimeout, parameters, null, callback));
	}

	@Override
	public Future<Response> unlink(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return unlink(uri, readTimeout, null, headers, callback);
	}

	@Override
	public Future<Response> unlink(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->unlink(URL2URI(url), readTimeout, null, headers, callback));
	}

	@Override
	public Future<Response> unlink(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return execute(()->unlink(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> purge(URI uri, Callback callback) throws IOException, RequestException{
		return purge(uri, null, null, callback);
	}

	@Override
	public Future<Response> purge(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->purge(URL2URI(url), null, null, callback));
	}

	@Override
	public Future<Response> purge(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return purge(uri, parameters, null, callback);
	}

	@Override
	public Future<Response> purge(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->purge(URL2URI(url), parameters, null, callback));
	}

	@Override
	public Future<Response> purge(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return purge(uri, null, headers, callback);
	}

	@Override
	public Future<Response> purge(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->purge(URL2URI(url), null, headers, callback));
	}

	@Override
	public Future<Response> purge(URL url, Map<String, Object> parameters, List<Header> headers,
								  Callback callback) throws IOException, RequestException{
		return execute(()->purge(URL2URI(url), parameters, headers, callback));
	}

	@Override
	public Future<Response> purge(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return purge(uri, readTimeout, null, null, callback);
	}

	@Override
	public Future<Response> purge(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->purge(URL2URI(url), readTimeout, null, null, callback));
	}

	@Override
	public Future<Response> purge(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return purge(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public Future<Response> purge(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->purge(URL2URI(url), readTimeout, parameters, null, callback));
	}

	@Override
	public Future<Response> purge(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return purge(uri, readTimeout, null, headers, callback);
	}

	@Override
	public Future<Response> purge(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->purge(URL2URI(url), readTimeout, null, headers, callback));
	}

	@Override
	public Future<Response> purge(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								  Callback callback) throws IOException, RequestException{
		return execute(()->purge(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> lock(URI uri, Callback callback) throws IOException, RequestException{
		return lock(uri, null, null, callback);
	}

	@Override
	public Future<Response> lock(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->lock(URL2URI(url), null, null, callback));
	}

	@Override
	public Future<Response> lock(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return lock(uri, parameters, null, callback);
	}

	@Override
	public Future<Response> lock(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->lock(URL2URI(url), parameters, null, callback));
	}

	@Override
	public Future<Response> lock(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return lock(uri, null, headers, callback);
	}

	@Override
	public Future<Response> lock(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->lock(URL2URI(url), null, headers, callback));
	}

	@Override
	public Future<Response> lock(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->lock(URL2URI(url), parameters, headers, callback));
	}

	@Override
	public Future<Response> lock(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return lock(uri, readTimeout, null, null, callback);
	}

	@Override
	public Future<Response> lock(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->lock(URL2URI(url), readTimeout, null, null, callback));
	}

	@Override
	public Future<Response> lock(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return lock(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public Future<Response> lock(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->lock(URL2URI(url), readTimeout, parameters, null, callback));
	}

	@Override
	public Future<Response> lock(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return lock(uri, readTimeout, null, headers, callback);
	}

	@Override
	public Future<Response> lock(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->lock(URL2URI(url), readTimeout, null, headers, callback));
	}

	@Override
	public Future<Response> lock(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return execute(()->lock(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> unlock(URI uri, Callback callback) throws IOException, RequestException{
		return unlock(uri, null, null, callback);
	}

	@Override
	public Future<Response> unlock(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->unlock(URL2URI(url), null, null, callback));
	}

	@Override
	public Future<Response> unlock(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return unlock(uri, parameters, null, callback);
	}

	@Override
	public Future<Response> unlock(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->unlock(URL2URI(url), parameters, null, callback));
	}

	@Override
	public Future<Response> unlock(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return unlock(uri, null, headers, callback);
	}

	@Override
	public Future<Response> unlock(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->unlock(URL2URI(url), null, headers, callback));
	}

	@Override
	public Future<Response> unlock(URL url, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return execute(()->unlock(URL2URI(url), parameters, headers, callback));
	}

	@Override
	public Future<Response> unlock(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return unlock(uri, readTimeout, null, null, callback);
	}

	@Override
	public Future<Response> unlock(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->unlock(URL2URI(url), readTimeout, null, null, callback));
	}

	@Override
	public Future<Response> unlock(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return unlock(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public Future<Response> unlock(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->unlock(URL2URI(url), readTimeout, parameters, null, callback));
	}

	@Override
	public Future<Response> unlock(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return unlock(uri, readTimeout, null, headers, callback);
	}

	@Override
	public Future<Response> unlock(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->unlock(URL2URI(url), readTimeout, null, headers, callback));
	}

	@Override
	public Future<Response> unlock(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return execute(()->unlock(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> propfind(URI uri, Callback callback) throws IOException, RequestException{
		return propfind(uri, null, null, callback);
	}

	@Override
	public Future<Response> propfind(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->propfind(URL2URI(url), null, null, callback));
	}

	@Override
	public Future<Response> propfind(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return propfind(uri, parameters, null, callback);
	}

	@Override
	public Future<Response> propfind(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->propfind(URL2URI(url), parameters, null, callback));
	}

	@Override
	public Future<Response> propfind(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return propfind(uri, null, headers, callback);
	}

	@Override
	public Future<Response> propfind(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->propfind(URL2URI(url), null, headers, callback));
	}

	@Override
	public Future<Response> propfind(URL url, Map<String, Object> parameters, List<Header> headers,
									 Callback callback) throws IOException, RequestException{
		return execute(()->propfind(URL2URI(url), parameters, headers, callback));
	}

	@Override
	public Future<Response> propfind(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return propfind(uri, readTimeout, null, null, callback);
	}

	@Override
	public Future<Response> propfind(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->propfind(URL2URI(url), readTimeout, null, null, callback));
	}

	@Override
	public Future<Response> propfind(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return propfind(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public Future<Response> propfind(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->propfind(URL2URI(url), readTimeout, parameters, null, callback));
	}

	@Override
	public Future<Response> propfind(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return propfind(uri, readTimeout, null, headers, callback);
	}

	@Override
	public Future<Response> propfind(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->propfind(URL2URI(url), readTimeout, null, headers, callback));
	}

	@Override
	public Future<Response> propfind(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
									 Callback callback) throws IOException, RequestException{
		return execute(()->propfind(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> proppatch(URI uri, Callback callback) throws IOException, RequestException{
		return proppatch(uri, null, null, null, callback);
	}

	@Override
	public Future<Response> proppatch(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->proppatch(URL2URI(url), null, null, null, callback));
	}

	@Override
	public Future<Response> proppatch(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return proppatch(uri, null, parameters, null, callback);
	}

	@Override
	public Future<Response> proppatch(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->proppatch(URL2URI(url), null, parameters, null, callback));
	}

	@Override
	public Future<Response> proppatch(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return proppatch(uri, null, null, headers, callback);
	}

	@Override
	public Future<Response> proppatch(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->proppatch(URL2URI(url), null, null, headers, callback));
	}

	@Override
	public Future<Response> proppatch(URI uri, Map<String, Object> parameters, List<Header> headers,
									  Callback callback) throws IOException, RequestException{
		return proppatch(uri, null, parameters, headers, callback);
	}

	@Override
	public Future<Response> proppatch(URL url, Map<String, Object> parameters, List<Header> headers,
									  Callback callback) throws IOException, RequestException{
		return execute(()->proppatch(URL2URI(url), null, parameters, headers, callback));
	}

	@Override
	public Future<Response> proppatch(URI uri, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return proppatch(uri, data, null, null, callback);
	}

	@Override
	public Future<Response> proppatch(URL url, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return execute(()->proppatch(URL2URI(url), data, null, null, callback));
	}

	@Override
	public Future<Response> proppatch(URI uri, RequestBody<?> data, Map<String, Object> parameters,
									  Callback callback) throws IOException, RequestException{
		return proppatch(uri, data, parameters, null, callback);
	}

	@Override
	public Future<Response> proppatch(URL url, RequestBody<?> data, Map<String, Object> parameters,
									  Callback callback) throws IOException, RequestException{
		return execute(()->proppatch(URL2URI(url), data, parameters, null, callback));
	}

	@Override
	public Future<Response> proppatch(URI uri, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return proppatch(uri, data, null, headers, callback);
	}

	@Override
	public Future<Response> proppatch(URL url, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->proppatch(URL2URI(url), data, null, headers, callback));
	}

	@Override
	public Future<Response> proppatch(URL url, RequestBody<?> data, Map<String, Object> parameters,
									  List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->proppatch(URL2URI(url), data, parameters, headers, callback));
	}

	@Override
	public Future<Response> proppatch(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return proppatch(uri, readTimeout, null, null, null, callback);
	}

	@Override
	public Future<Response> proppatch(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->proppatch(URL2URI(url), readTimeout, null, null, null, callback));
	}

	@Override
	public Future<Response> proppatch(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return proppatch(uri, readTimeout, null, parameters, null, callback);
	}

	@Override
	public Future<Response> proppatch(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->proppatch(URL2URI(url), readTimeout, null, parameters, null, callback));
	}

	@Override
	public Future<Response> proppatch(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return proppatch(uri, readTimeout, null, null, headers, callback);
	}

	@Override
	public Future<Response> proppatch(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->proppatch(URL2URI(url), readTimeout, null, null, headers, callback));
	}

	@Override
	public Future<Response> proppatch(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
									  Callback callback) throws IOException, RequestException{
		return proppatch(uri, readTimeout, null, parameters, headers, callback);
	}

	@Override
	public Future<Response> proppatch(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
									  Callback callback) throws IOException, RequestException{
		return execute(()->proppatch(URL2URI(url), readTimeout, null, parameters, headers, callback));
	}

	@Override
	public Future<Response> proppatch(URI uri, int readTimeout, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return proppatch(uri, readTimeout, data, null, null, callback);
	}

	@Override
	public Future<Response> proppatch(URL url, int readTimeout, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return execute(()->proppatch(URL2URI(url), readTimeout, data, null, null, callback));
	}

	@Override
	public Future<Response> proppatch(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
									  Callback callback) throws IOException, RequestException{
		return proppatch(uri, readTimeout, data, parameters, null, callback);
	}

	@Override
	public Future<Response> proppatch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
									  Callback callback) throws IOException, RequestException{
		return execute(()->proppatch(URL2URI(url), readTimeout, data, parameters, null, callback));
	}

	@Override
	public Future<Response> proppatch(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers,
									  Callback callback) throws IOException, RequestException{
		return proppatch(uri, readTimeout, data, null, headers, callback);
	}

	@Override
	public Future<Response> proppatch(URL url, int readTimeout, RequestBody<?> data, List<Header> headers,
									  Callback callback) throws IOException, RequestException{
		return execute(()->proppatch(URL2URI(url), readTimeout, data, null, headers, callback));
	}

	@Override
	public Future<Response> proppatch(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
									  List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->proppatch(URL2URI(url), readTimeout, data, parameters, headers, callback));
	}

	@Override
	public Future<Response> report(URI uri, Callback callback) throws IOException, RequestException{
		return report(uri, null, null, null, callback);
	}

	@Override
	public Future<Response> report(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->report(URL2URI(url), null, null, null, callback));
	}

	@Override
	public Future<Response> report(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return report(uri, null, parameters, null, callback);
	}

	@Override
	public Future<Response> report(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->report(URL2URI(url), null, parameters, null, callback));
	}

	@Override
	public Future<Response> report(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return report(uri, null, null, headers, callback);
	}

	@Override
	public Future<Response> report(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->report(URL2URI(url), null, null, headers, callback));
	}

	@Override
	public Future<Response> report(URI uri, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return report(uri, null, parameters, headers, callback);
	}

	@Override
	public Future<Response> report(URL url, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return execute(()->report(URL2URI(url), null, parameters, headers, callback));
	}

	@Override
	public Future<Response> report(URI uri, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return report(uri, data, null, null, callback);
	}

	@Override
	public Future<Response> report(URL url, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return execute(()->report(URL2URI(url), data, null, null, callback));
	}

	@Override
	public Future<Response> report(URI uri, RequestBody<?> data, Map<String, Object> parameters,
								   Callback callback) throws IOException, RequestException{
		return report(uri, data, parameters, null, callback);
	}

	@Override
	public Future<Response> report(URL url, RequestBody<?> data, Map<String, Object> parameters,
								   Callback callback) throws IOException, RequestException{
		return execute(()->report(URL2URI(url), data, parameters, null, callback));
	}

	@Override
	public Future<Response> report(URI uri, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return report(uri, data, null, headers, callback);
	}

	@Override
	public Future<Response> report(URL url, RequestBody<?> data, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->report(URL2URI(url), data, null, headers, callback));
	}

	@Override
	public Future<Response> report(URL url, RequestBody<?> data, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return execute(()->report(URL2URI(url), data, parameters, headers, callback));
	}

	@Override
	public Future<Response> report(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return report(uri, readTimeout, null, null, null, callback);
	}

	@Override
	public Future<Response> report(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->report(URL2URI(url), readTimeout, null, null, null, callback));
	}

	@Override
	public Future<Response> report(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return report(uri, readTimeout, null, parameters, null, callback);
	}

	@Override
	public Future<Response> report(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->report(URL2URI(url), readTimeout, null, parameters, null, callback));
	}

	@Override
	public Future<Response> report(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return report(uri, readTimeout, null, null, headers, callback);
	}

	@Override
	public Future<Response> report(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->report(URL2URI(url), readTimeout, null, null, headers, callback));
	}

	@Override
	public Future<Response> report(URI uri, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return report(uri, readTimeout, parameters, headers, callback);
	}

	@Override
	public Future<Response> report(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return execute(()->report(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> report(URI uri, int readTimeout, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return report(uri, readTimeout, data, null, null, callback);
	}

	@Override
	public Future<Response> report(URL url, int readTimeout, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return execute(()->report(URL2URI(url), readTimeout, data, null, null, callback));
	}

	@Override
	public Future<Response> report(URI uri, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								   Callback callback) throws IOException, RequestException{
		return report(uri, readTimeout, data, parameters, null, callback);
	}

	@Override
	public Future<Response> report(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								   Callback callback) throws IOException, RequestException{
		return execute(()->report(URL2URI(url), readTimeout, data, parameters, null, callback));
	}

	@Override
	public Future<Response> report(URI uri, int readTimeout, RequestBody<?> data, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return report(uri, readTimeout, data, null, headers, callback);
	}

	@Override
	public Future<Response> report(URL url, int readTimeout, RequestBody<?> data, List<Header> headers,
								   Callback callback) throws IOException, RequestException{
		return execute(()->report(URL2URI(url), readTimeout, data, null, headers, callback));
	}

	@Override
	public Future<Response> report(URL url, int readTimeout, RequestBody<?> data, Map<String, Object> parameters,
								   List<Header> headers, Callback callback) throws IOException, RequestException{
		return execute(()->report(URL2URI(url), readTimeout, data, parameters, headers, callback));
	}

	@Override
	public Future<Response> view(URI uri, Callback callback) throws IOException, RequestException{
		return view(uri, null, null, callback);
	}

	@Override
	public Future<Response> view(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->view(URL2URI(url), null, null, callback));
	}

	@Override
	public Future<Response> view(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return view(uri, parameters, null, callback);
	}

	@Override
	public Future<Response> view(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->view(URL2URI(url), parameters, null, callback));
	}

	@Override
	public Future<Response> view(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return view(uri, null, headers, callback);
	}

	@Override
	public Future<Response> view(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->view(URL2URI(url), null, headers, callback));
	}

	@Override
	public Future<Response> view(URL url, Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->view(URL2URI(url), parameters, headers, callback));
	}

	@Override
	public Future<Response> view(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return view(uri, readTimeout, null, null, callback);
	}

	@Override
	public Future<Response> view(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->view(URL2URI(url), readTimeout, null, null, callback));
	}

	@Override
	public Future<Response> view(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return view(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public Future<Response> view(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->view(URL2URI(url), readTimeout, parameters, null, callback));
	}

	@Override
	public Future<Response> view(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return view(uri, readTimeout, null, headers, callback);
	}

	@Override
	public Future<Response> view(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->view(URL2URI(url), readTimeout, null, headers, callback));
	}

	@Override
	public Future<Response> view(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
								 Callback callback) throws IOException, RequestException{
		return execute(()->view(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> wrapped(URI uri, Callback callback) throws IOException, RequestException{
		return wrapped(uri, null, null, callback);
	}

	@Override
	public Future<Response> wrapped(URL url, Callback callback) throws IOException, RequestException{
		return execute(()->wrapped(URL2URI(url), null, null, callback));
	}

	@Override
	public Future<Response> wrapped(URI uri, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return wrapped(uri, parameters, null, callback);
	}

	@Override
	public Future<Response> wrapped(URL url, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->wrapped(URL2URI(url), parameters, null, callback));
	}

	@Override
	public Future<Response> wrapped(URI uri, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return wrapped(uri, null, headers, callback);
	}

	@Override
	public Future<Response> wrapped(URL url, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->wrapped(URL2URI(url), null, headers, callback));
	}

	@Override
	public Future<Response> wrapped(URL url, Map<String, Object> parameters, List<Header> headers,
									Callback callback) throws IOException, RequestException{
		return execute(()->wrapped(URL2URI(url), parameters, headers, callback));
	}

	@Override
	public Future<Response> wrapped(URI uri, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return wrapped(uri, readTimeout, null, null, callback);
	}

	@Override
	public Future<Response> wrapped(URL url, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->wrapped(URL2URI(url), readTimeout, null, null, callback));
	}

	@Override
	public Future<Response> wrapped(URI uri, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return wrapped(uri, readTimeout, parameters, null, callback);
	}

	@Override
	public Future<Response> wrapped(URL url, int readTimeout, Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->wrapped(URL2URI(url), readTimeout, parameters, null, callback));
	}

	@Override
	public Future<Response> wrapped(URI uri, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return wrapped(uri, readTimeout, null, headers, callback);
	}

	@Override
	public Future<Response> wrapped(URL url, int readTimeout, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->wrapped(URL2URI(url), readTimeout, null, headers, callback));
	}

	@Override
	public Future<Response> wrapped(URL url, int readTimeout, Map<String, Object> parameters, List<Header> headers,
									Callback callback) throws IOException, RequestException{
		return execute(()->wrapped(URL2URI(url), readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> request(URI uri, RequestMethod requestMethod, Callback callback)
			throws IOException, RequestException{
		return request(uri, requestMethod, null, null, null, callback);
	}

	@Override
	public Future<Response> request(URL url, RequestMethod requestMethod, Callback callback)
			throws IOException, RequestException{
		return execute(()->request(URL2URI(url), requestMethod, callback));
	}

	@Override
	public Future<Response> request(URI uri, RequestMethod requestMethod, Map<String, Object> parameters,
									Callback callback) throws IOException, RequestException{
		return request(uri, requestMethod, null, parameters, null, callback);
	}

	@Override
	public Future<Response> request(URL url, RequestMethod requestMethod, Map<String, Object> parameters,
									Callback callback) throws IOException, RequestException{
		return execute(()->request(URL2URI(url), requestMethod, parameters, callback));
	}

	@Override
	public Future<Response> request(URI uri, RequestMethod requestMethod, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return request(uri, requestMethod, null, null, headers, callback);
	}

	@Override
	public Future<Response> request(URL url, RequestMethod requestMethod, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->request(URL2URI(url), requestMethod, headers, callback));
	}

	@Override
	public Future<Response> request(URI uri, RequestMethod requestMethod, Map<String, Object> parameters,
									List<Header> headers, Callback callback) throws IOException, RequestException{
		return request(uri, requestMethod, null, parameters, headers, callback);
	}

	@Override
	public Future<Response> request(URL url, RequestMethod requestMethod, Map<String, Object> parameters,
									List<Header> headers, Callback callback) throws IOException, RequestException{
		return execute(()->request(URL2URI(url), requestMethod, parameters, headers, callback));
	}

	@Override
	public Future<Response> request(URI uri, RequestMethod requestMethod, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return request(uri, requestMethod, data, null, null, callback);
	}

	@Override
	public Future<Response> request(URL url, RequestMethod requestMethod, RequestBody<?> data, Callback callback)
			throws IOException, RequestException{
		return execute(()->request(URL2URI(url), requestMethod, data, callback));
	}

	@Override
	public Future<Response> request(URI uri, RequestMethod requestMethod, RequestBody<?> data,
									Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return request(uri, requestMethod, data, parameters, null, callback);
	}

	@Override
	public Future<Response> request(URL url, RequestMethod requestMethod, RequestBody<?> data,
									Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->request(URL2URI(url), requestMethod, data, parameters, callback));
	}

	@Override
	public Future<Response> request(URI uri, RequestMethod requestMethod, RequestBody<?> data, List<Header> headers,
									Callback callback)
			throws IOException, RequestException{
		return request(uri, requestMethod, data, null, headers, callback);
	}

	@Override
	public Future<Response> request(URL url, RequestMethod requestMethod, RequestBody<?> data, List<Header> headers,
									Callback callback)
			throws IOException, RequestException{
		return execute(()->request(URL2URI(url), requestMethod, data, headers, callback));
	}

	@Override
	public Future<Response> request(URI uri, RequestMethod requestMethod, RequestBody<?> data,
									Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		Assert.isNull(requestMethod, "Request method could not be null.");

		switch(requestMethod){
			case POST:
				return post(uri, data, parameters, headers, callback);
			case PUT:
				return put(uri, data, parameters, headers, callback);
			case PATCH:
				return patch(uri, data, parameters, headers, callback);
			case DELETE:
				return delete(uri, parameters, headers, callback);
			case CONNECT:
				return connect(uri, parameters, headers, callback);
			case TRACE:
				return trace(uri, parameters, headers, callback);
			case COPY:
				return copy(uri, parameters, headers, callback);
			case MOVE:
				return move(uri, parameters, headers, callback);
			case HEAD:
				return head(uri, parameters, headers, callback);
			case OPTIONS:
				return options(uri, parameters, headers, callback);
			case LINK:
				return link(uri, parameters, headers, callback);
			case UNLINK:
				return unlink(uri, parameters, headers, callback);
			case PURGE:
				return purge(uri, parameters, headers, callback);
			case LOCK:
				return lock(uri, parameters, headers, callback);
			case UNLOCK:
				return unlock(uri, parameters, headers, callback);
			case PROPFIND:
				return propfind(uri, parameters, headers, callback);
			case PROPPATCH:
				return proppatch(uri, data, parameters, headers, callback);
			case REPORT:
				return report(uri, data, parameters, headers, callback);
			case VIEW:
				return view(uri, parameters, headers, callback);
			case WRAPPED:
				return wrapped(uri, parameters, headers, callback);
			default:
				return get(uri, parameters, headers, callback);
		}
	}

	@Override
	public Future<Response> request(URL url, RequestMethod requestMethod, RequestBody<?> data,
									Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->request(URL2URI(url), requestMethod, data, parameters, headers, callback));
	}

	@Override
	public Future<Response> request(URI uri, RequestMethod requestMethod, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return request(uri, requestMethod, readTimeout, null, null, null, callback);
	}

	@Override
	public Future<Response> request(URL url, RequestMethod requestMethod, int readTimeout, Callback callback)
			throws IOException, RequestException{
		return execute(()->request(URL2URI(url), requestMethod, readTimeout, callback));
	}

	@Override
	public Future<Response> request(URI uri, RequestMethod requestMethod, int readTimeout,
									Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return request(uri, requestMethod, readTimeout, null, parameters, null, callback);
	}

	@Override
	public Future<Response> request(URL url, RequestMethod requestMethod, int readTimeout,
									Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->request(URL2URI(url), requestMethod, readTimeout, parameters, callback));
	}

	@Override
	public Future<Response> request(URI uri, RequestMethod requestMethod, int readTimeout, List<Header> headers,
									Callback callback) throws IOException, RequestException{
		return request(uri, requestMethod, readTimeout, null, null, headers, callback);
	}

	@Override
	public Future<Response> request(URL url, RequestMethod requestMethod, int readTimeout, List<Header> headers,
									Callback callback) throws IOException, RequestException{
		return execute(()->request(URL2URI(url), requestMethod, readTimeout, headers, callback));
	}

	@Override
	public Future<Response> request(URI uri, RequestMethod requestMethod, int readTimeout,
									Map<String, Object> parameters,
									List<Header> headers, Callback callback) throws IOException, RequestException{
		return request(uri, requestMethod, readTimeout, null, parameters, headers, callback);
	}

	@Override
	public Future<Response> request(URL url, RequestMethod requestMethod, int readTimeout,
									Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->request(URL2URI(url), requestMethod, readTimeout, parameters, headers, callback));
	}

	@Override
	public Future<Response> request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
									Callback callback) throws IOException, RequestException{
		return request(uri, requestMethod, readTimeout, data, null, null, callback);
	}

	@Override
	public Future<Response> request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
									Callback callback) throws IOException, RequestException{
		return execute(()->request(URL2URI(url), requestMethod, readTimeout, data, callback));
	}

	@Override
	public Future<Response> request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
									Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return request(uri, requestMethod, readTimeout, data, parameters, null, callback);
	}

	@Override
	public Future<Response> request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
									Map<String, Object> parameters, Callback callback)
			throws IOException, RequestException{
		return execute(()->request(URL2URI(url), requestMethod, readTimeout, data, parameters, callback));
	}

	@Override
	public Future<Response> request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
									List<Header> headers, Callback callback) throws IOException, RequestException{
		return request(uri, requestMethod, readTimeout, data, null, headers, callback);
	}

	@Override
	public Future<Response> request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
									List<Header> headers, Callback callback) throws IOException, RequestException{
		return execute(()->request(URL2URI(url), requestMethod, readTimeout, data, headers, callback));
	}

	@Override
	public Future<Response> request(URI uri, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
									Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		Assert.isNull(requestMethod, "Request method could not be null.");

		switch(requestMethod){
			case POST:
				return post(uri, readTimeout, data, parameters, headers, callback);
			case PUT:
				return put(uri, readTimeout, data, parameters, headers, callback);
			case PATCH:
				return patch(uri, readTimeout, data, parameters, headers, callback);
			case DELETE:
				return delete(uri, readTimeout, parameters, headers, callback);
			case CONNECT:
				return connect(uri, readTimeout, parameters, headers, callback);
			case TRACE:
				return trace(uri, readTimeout, parameters, headers, callback);
			case COPY:
				return copy(uri, readTimeout, parameters, headers, callback);
			case MOVE:
				return move(uri, readTimeout, parameters, headers, callback);
			case HEAD:
				return head(uri, readTimeout, parameters, headers, callback);
			case OPTIONS:
				return options(uri, readTimeout, parameters, headers, callback);
			case LINK:
				return link(uri, readTimeout, parameters, headers, callback);
			case UNLINK:
				return unlink(uri, readTimeout, parameters, headers, callback);
			case PURGE:
				return purge(uri, readTimeout, parameters, headers, callback);
			case LOCK:
				return lock(uri, readTimeout, parameters, headers, callback);
			case UNLOCK:
				return unlock(uri, readTimeout, parameters, headers, callback);
			case PROPFIND:
				return propfind(uri, readTimeout, parameters, headers, callback);
			case PROPPATCH:
				return proppatch(uri, readTimeout, data, parameters, headers, callback);
			case REPORT:
				return report(uri, readTimeout, data, parameters, headers, callback);
			case VIEW:
				return view(uri, readTimeout, parameters, headers, callback);
			case WRAPPED:
				return wrapped(uri, readTimeout, parameters, headers, callback);
			default:
				return get(uri, readTimeout, parameters, headers, callback);
		}
	}

	@Override
	public Future<Response> request(URL url, RequestMethod requestMethod, int readTimeout, RequestBody<?> data,
									Map<String, Object> parameters, List<Header> headers, Callback callback)
			throws IOException, RequestException{
		return execute(()->request(URL2URI(url), requestMethod, readTimeout, data, parameters, headers, callback));
	}

}
