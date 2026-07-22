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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.uri;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * URI 构建器
 *
 * @param <T>
 * 		URI 类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class AbstractURIBuilder<T extends AbstractURI> {

	/**
	 * URI Scheme
	 */
	protected String scheme;

	/**
	 * URI 主机地址
	 */
	protected String host;

	/**
	 * URI 端口
	 */
	protected int port;

	/**
	 * 构造函数
	 */
	protected AbstractURIBuilder() {
	}

	/**
	 * 设置 URI Scheme
	 *
	 * @param scheme
	 * 		URI Scheme
	 *
	 * @return {@link AbstractURIBuilder} 实例
	 */
	public AbstractURIBuilder scheme(final String scheme) {
		Assert.isBlank(scheme, "Scheme must not be null or empty.");
		this.scheme = scheme;
		return this;
	}

	/**
	 * 设置 URI 主机地址
	 *
	 * @param host
	 * 		URI 主机地址
	 *
	 * @return {@link AbstractURIBuilder} 实例
	 */
	public AbstractURIBuilder host(final String host) {
		Assert.isBlank(host, "Host must not be null or empty.");
		this.host = host;
		return this;
	}

	/**
	 * 设置 URI 端口
	 *
	 * @param port
	 * 		URI 端口
	 *
	 * @return {@link AbstractURIBuilder} 实例
	 */
	public AbstractURIBuilder port(final int port) {
		Assert.isTrue(Validate.isPort(port), "Port out of range: " + port + ".");
		this.port = port;
		return this;
	}

	/**
	 * 设置 URI 主机地址和端口
	 *
	 * @param host
	 * 		URI 主机地址
	 * @param port
	 * 		URI 端口
	 *
	 * @return {@link AbstractURIBuilder} 实例
	 */
	public AbstractURIBuilder hostAndPort(final String host, final int port) {
		Assert.isBlank(host, "Host must not be null or empty.");
		Assert.isTrue(Validate.isPort(port), "Port out of range: " + port + ".");

		this.host = host;
		this.port = port;

		return this;
	}

	/**
	 * 构建 URI 实例
	 *
	 * @return URI 实例
	 */
	public abstract T build();

	protected static Map<String, String> parseParameters(final String queryString) {
		if(Validate.isBlank(queryString)){
			return null;
		}

		StringTokenizer tokenizer = new StringTokenizer(queryString, "&");
		Map<String, String> result = new LinkedHashMap<>(16, 0.8F);

		while(tokenizer.hasMoreTokens()){
			String queryParam = tokenizer.nextToken();
			int ei = queryParam.indexOf('=');

			result.put(queryParam.substring(0, ei), queryParam.substring(ei));
		}

		return result;
	}

}
