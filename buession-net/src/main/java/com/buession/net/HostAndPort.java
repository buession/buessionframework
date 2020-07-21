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
	 * IP
	 */
	private String ip;

	/**
	 * 端口
	 */
	private int port;

	/**
	 * 构造函数
	 *
	 * @param ip
	 * 		IP 地址
	 */
	public HostAndPort(String ip){
		this.ip = ip;
	}

	/**
	 * 构造函数
	 *
	 * @param ip
	 * 		IP 地址
	 * @param port
	 * 		端口
	 */
	public HostAndPort(String ip, int port){
		this(ip);
		setPort(port);
	}

	/**
	 * 获取 IP 地址
	 *
	 * @return IP 地址
	 */
	public String getIp(){
		return ip;
	}

	/**
	 * 设置 IP 地址
	 *
	 * @param ip
	 * 		IP 地址
	 */
	public void setIp(String ip){
		this.ip = ip;
	}

	/**
	 * 获取端口
	 *
	 * @return 端口
	 */
	public int getPort(){
		return port;
	}

	/**
	 * 设置端口
	 *
	 * @param port
	 * 		端口
	 */
	public void setPort(int port){
		Assert.isTrue(Validate.isPort(port), String.format("Port out of range: %s", port));
		this.port = port;
	}

	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder(46);

		sb.append(ip).append(':').append(port);

		return sb.toString();
	}

	@Override
	public int hashCode(){
		return Objects.hash(ip, port);
	}

	@Override
	public boolean equals(Object o){
		if(this == o){
			return true;
		}

		if(o instanceof HostAndPort){
			HostAndPort that = (HostAndPort) o;
			return Objects.equals(ip, that.ip) && Objects.equals(port, that.port);
		}

		return false;
	}

}
