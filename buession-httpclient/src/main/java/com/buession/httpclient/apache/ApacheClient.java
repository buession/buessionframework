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
package com.buession.httpclient.apache;

import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.exception.RequestException;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 * @since 2.4.0
 */
public interface ApacheClient {

	Response get(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException;

	Response get(final URI uri, final int readTimeout, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException;

	Response post(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
				  final RequestBody<?> body) throws IOException, RequestException;

	Response post(final URI uri, final int readTimeout, final Map<String, Object> parameters,
				  final List<Header> headers, final RequestBody<?> body) throws IOException, RequestException;

	Response patch(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
				   final RequestBody<?> body) throws IOException, RequestException;

	Response patch(final URI uri, final int readTimeout, final Map<String, Object> parameters,
				   final List<Header> headers, final RequestBody<?> body) throws IOException, RequestException;

	Response put(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
				 final RequestBody<?> body) throws IOException, RequestException;

	Response put(final URI uri, final int readTimeout, final Map<String, Object> parameters, final List<Header> headers,
				 final RequestBody<?> body) throws IOException, RequestException;

	Response delete(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException;

	Response delete(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					final List<Header> headers) throws IOException, RequestException;

	Response connect(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException;

	Response connect(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					 final List<Header> headers) throws IOException, RequestException;

	Response trace(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException;

	Response trace(final URI uri, final int readTimeout, final Map<String, Object> parameters,
				   final List<Header> headers) throws IOException, RequestException;

	Response copy(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException;

	Response copy(final URI uri, final int readTimeout, final Map<String, Object> parameters,
				  final List<Header> headers) throws IOException, RequestException;

	Response move(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException;

	Response move(final URI uri, final int readTimeout, final Map<String, Object> parameters,
				  final List<Header> headers) throws IOException, RequestException;

	Response head(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException;

	Response head(final URI uri, final int readTimeout, final Map<String, Object> parameters,
				  final List<Header> headers) throws IOException, RequestException;

	Response options(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException;

	Response options(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					 final List<Header> headers) throws IOException, RequestException;

	Response link(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException;

	Response link(final URI uri, final int readTimeout, final Map<String, Object> parameters,
				  final List<Header> headers) throws IOException, RequestException;

	Response unlink(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException;

	Response unlink(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					final List<Header> headers) throws IOException, RequestException;

	Response purge(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException;

	Response purge(final URI uri, final int readTimeout, final Map<String, Object> parameters,
				   final List<Header> headers) throws IOException, RequestException;

	Response lock(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException;

	Response lock(final URI uri, final int readTimeout, final Map<String, Object> parameters,
				  final List<Header> headers) throws IOException, RequestException;

	Response unlock(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException;

	Response unlock(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					final List<Header> headers) throws IOException, RequestException;

	Response propfind(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException;

	Response propfind(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					  final List<Header> headers) throws IOException, RequestException;

	Response proppatch(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					   final RequestBody<?> body) throws IOException, RequestException;

	Response proppatch(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					   final List<Header> headers, final RequestBody<?> body) throws IOException, RequestException;

	Response report(final URI uri, final Map<String, Object> parameters, final List<Header> headers,
					final RequestBody<?> body) throws IOException, RequestException;

	Response report(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					final List<Header> headers, final RequestBody<?> body) throws IOException, RequestException;

	Response view(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException;

	Response view(final URI uri, final int readTimeout, final Map<String, Object> parameters,
				  final List<Header> headers) throws IOException, RequestException;

	Response wrapped(final URI uri, final Map<String, Object> parameters, final List<Header> headers)
			throws IOException, RequestException;

	Response wrapped(final URI uri, final int readTimeout, final Map<String, Object> parameters,
					 final List<Header> headers) throws IOException, RequestException;

}
