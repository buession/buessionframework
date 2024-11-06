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
package org.apache.hc.client5.http.classic.methods;

import com.buession.httpclient.core.RequestMethod;

import java.net.URI;

/**
 * Unlink 请求
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class HttpUnlink extends HttpUriRequestBase {

	private final static long serialVersionUID = 9144467318123192891L;

	public final static String METHOD_NAME = RequestMethod.UNLINK.name();

	/**
	 * 构造函数
	 *
	 * @param uri
	 * 		URL {@link URI}
	 */
	public HttpUnlink(final URI uri) {
		super(METHOD_NAME, uri);
	}

	/**
	 * 构造函数
	 *
	 * @param url
	 * 		URL {@link URI}
	 *
	 * @throws IllegalArgumentException
	 * 		if the uri is invalid.
	 */
	public HttpUnlink(final String url) {
		this(URI.create(url));
	}

}
