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
 * | Copyright @ 2013-2025 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.net;

import java.io.Serializable;
import java.net.URI;

/**
 * @author Yong.Teng
 */
public abstract class AbstractURI implements Serializable {

	private final static long serialVersionUID = -1695251022185424158L;

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
	 * 返回 URI Scheme
	 *
	 * @return URI Scheme
	 */
	public String getScheme() {
		return scheme;
	}

	/**
	 * 设置 URI Scheme
	 *
	 * @param scheme
	 * 		URI Scheme
	 */
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	/**
	 * 返回 URI 主机地址
	 *
	 * @return URI 主机地址
	 */
	public String getHost() {
		return host;
	}

	/**
	 * 设置 URI 主机地址
	 *
	 * @param host
	 * 		URI 主机地址
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * 返回 URI 端口
	 *
	 * @return URI 端口
	 */
	public int getPort() {
		return port;
	}

	/**
	 * 设置 URI 端口
	 *
	 * @param port
	 * 		URI 端口
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * 返回是否为 SSL
	 *
	 * @return true/false
	 */
	public boolean isSsl() {
		return false;
	}

	/**
	 * 转换为 {@link URI}
	 *
	 * @return {@link URI} 实例
	 */
	public abstract URI toURI();

}
