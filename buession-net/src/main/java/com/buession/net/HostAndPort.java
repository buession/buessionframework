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
package com.buession.net;

import com.buession.core.utils.Assert;
import com.buession.core.validator.Validate;

import java.io.Serializable;
import java.util.Objects;

/**
 * 网络服务地址
 *
 * @author Yong.Teng
 */
public class HostAndPort implements Serializable {

	private final static long serialVersionUID = 856989636316530989L;

	/**
	 * 主机地址
	 */
	private String host;

	/**
	 * 端口
	 */
	private int port;

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 */
	public HostAndPort(String host) {
		this.host = host;
	}

	/**
	 * 构造函数
	 *
	 * @param host
	 * 		主机地址
	 * @param port
	 * 		端口
	 */
	public HostAndPort(String host, int port) {
		this(host);
		setPort(port);
	}

	/**
	 * 获取主机地址
	 *
	 * @return 主机地址
	 */
	public String getHost() {
		return host;
	}

	/**
	 * 设置主机地址
	 *
	 * @param host
	 * 		主机地址
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * 获取端口
	 *
	 * @return 端口
	 */
	public int getPort() {
		return port;
	}

	/**
	 * 设置端口
	 *
	 * @param port
	 * 		端口
	 */
	public void setPort(int port) {
		Assert.isTrue(Validate.isPort(port), String.format("Port out of range: %s", port));
		this.port = port;
	}

	@Override
	public String toString() {
		return host + ':' + port;
	}

	@Override
	public int hashCode() {
		return Objects.hash(host, port);
	}

	@Override
	public boolean equals(Object o) {
		if(this == o){
			return true;
		}

		if(o instanceof HostAndPort){
			HostAndPort that = (HostAndPort) o;
			return Objects.equals(host, that.host) && Objects.equals(port, that.port);
		}

		return false;
	}

}
