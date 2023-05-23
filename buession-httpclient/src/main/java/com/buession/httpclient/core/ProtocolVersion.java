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
package com.buession.httpclient.core;

import com.buession.core.utils.Assert;

import java.util.Objects;

/**
 * 协议及版本
 *
 * @author Yong.Teng
 */
public enum ProtocolVersion {

	/**
	 * HTTP/0.9
	 */
	HTTP_0_9("http", 0, 9),

	/**
	 * HTTP/1.0
	 */
	HTTP_1_0("http", 1, 0),

	/**
	 * HTTP/1.1
	 */
	HTTP_1_1("http", 1, 1),

	/**
	 * HTTP/2.0
	 */
	HTTP_2_0("http", 2, 0),

	/**
	 * SPDY/3.1
	 */
	SPDY_3_1("spdy", 3, 1),

	/**
	 * QUIC
	 */
	QUIC("quic", -1, -1);

	private final String protocol;

	private final int major;

	private final int minor;

	ProtocolVersion(final String protocol, final int major, final int minor){
		this.protocol = protocol;
		this.major = major;
		this.minor = minor;
	}

	public String getProtocol(){
		return protocol;
	}

	public int getMajor(){
		return major;
	}

	public int getMinor(){
		return minor;
	}

	public static ProtocolVersion createInstance(String protocol, int major, int minor){
		Assert.isBlank(protocol, "Protocol cloud not be empty or null.");

		boolean legalProtocol = false;
		Boolean illegalMinorVersion = null;
		Boolean illegalMajorVersion = null;

		for(ProtocolVersion protocolVersion : ProtocolVersion.values()){
			if(protocolVersion.getProtocol().equalsIgnoreCase(protocol)){
				if(protocolVersion.getMajor() == major){
					if(protocolVersion.getMinor() == minor){
						return protocolVersion;
					}else{
						illegalMinorVersion = true;
					}
				}else{
					illegalMajorVersion = true;
				}
				legalProtocol = true;
			}else{
				if(legalProtocol == true){
					break;
				}

				illegalMinorVersion = null;
				illegalMajorVersion = null;
			}
		}

		if(legalProtocol){
			if(Objects.equals(illegalMajorVersion, true)){
				throw new IllegalArgumentException("Unknown " + protocol + " protocol major version: " + major + ".");
			}

			if(Objects.equals(illegalMinorVersion, true)){
				throw new IllegalArgumentException("Unknown " + protocol + " protocol minor version: " + minor + ".");
			}
		}

		throw new IllegalArgumentException("Unknown protocol: " + protocol);
	}

	@Override
	public String toString(){
		return protocol + '/' + major + '.' + minor;
	}

}
