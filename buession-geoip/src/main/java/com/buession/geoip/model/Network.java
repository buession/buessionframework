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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip.model;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * GeoIP 网络
 *
 * @author Yong.Teng
 * @since 1.3.0
 */
public final class Network implements Serializable {

	private final static long serialVersionUID = -8419205256683389392L;

	/**
	 * IP 地址
	 */
	private final InetAddress ipAddress;

	/**
	 * 掩码位
	 */
	private final int prefixLength;

	/**
	 * CDIR 网络地址
	 */
	private final InetAddress networkAddress;

	/**
	 * 构造函数
	 *
	 * @param ipAddress
	 * 		IP 地址
	 * @param prefixLength
	 * 		掩码位
	 * @param networkAddress
	 * 		CDIR 网络地址
	 */
	public Network(final InetAddress ipAddress, final int prefixLength, final InetAddress networkAddress){
		this.ipAddress = ipAddress;
		this.prefixLength = prefixLength;
		this.networkAddress = networkAddress;
	}

	/**
	 * 构造函数
	 *
	 * @param ipAddress
	 * 		IP 地址
	 * @param prefixLength
	 * 		掩码位
	 * @param networkAddress
	 * 		CDIR 网络地址
	 *
	 * @throws UnknownHostException
	 * 		IP 的字符串形式转换为 {@link InetAddress} 出错时抛出
	 */
	public Network(final String ipAddress, final int prefixLength, final InetAddress networkAddress)
			throws UnknownHostException{
		this.ipAddress = InetAddress.getByName(ipAddress);
		this.prefixLength = prefixLength;
		this.networkAddress = networkAddress;
	}

	/**
	 * 返回 IP 地址
	 *
	 * @return IP 地址
	 */
	public InetAddress getIpAddress(){
		return ipAddress;
	}

	/**
	 * 返回掩码位
	 *
	 * @return 掩码位
	 */
	public int getPrefixLength(){
		return prefixLength;
	}

	/**
	 * 返回 CDIR 网络地址
	 *
	 * @return CDIR 网络地址
	 */
	public InetAddress getNetworkAddress(){
		return networkAddress;
	}

	@Override
	public int hashCode(){
		return Objects.hash(ipAddress, prefixLength, networkAddress);
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof Network){
			Network that = (Network) obj;
			return prefixLength == that.prefixLength && Objects.equals(ipAddress, that.ipAddress) &&
					Objects.equals(networkAddress, that.networkAddress);
		}

		return false;
	}

	@Override
	public String toString(){
		return networkAddress.getHostAddress() + "/" + prefixLength;
	}

}
