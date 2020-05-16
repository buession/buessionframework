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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.helper;

import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.Request;

import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public interface RequestBuilder<T extends RequestBuilder, R extends Request> {

	T setProtocolVersion(ProtocolVersion protocolVersion);

	T setUrl(String url);

	T setHeaders(List<Header> headers);

	T setParameters(Map<String, Object> parameters);

	T get();

	T post();

	T post(RequestBody body);

	T patch();

	T patch(RequestBody body);

	T put();

	T put(RequestBody body);

	T delete();

	T connect();

	T trace();

	T copy();

	T move();

	T head();

	T options();

	T link();

	T unlink();

	T purge();

	T lock();

	T unlock();

	T propfind();

	T proppatch();

	T proppatch(RequestBody body);

	T report();

	T report(RequestBody body);

	T view();

	T wrapped();

	R build();

}
