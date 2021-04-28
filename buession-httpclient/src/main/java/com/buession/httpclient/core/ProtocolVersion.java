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
package com.buession.httpclient.core;

/**
 * @author Yong.Teng
 */
public enum ProtocolVersion {

	HTTP_0_9("http", 0, 9),

	HTTP_1_0("http", 1, 0),

	HTTP_1_1("http", 1, 1),

	HTTP_2_0("http", 2, 0);

	protected String protocol;

	protected int major;

	protected int minor;

	ProtocolVersion(String protocol, int major, int minor){
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

	public final static ProtocolVersion createInstance(String protocol, int major, int minor){
		if("http".equalsIgnoreCase(protocol) == false){
			throw new IllegalArgumentException("Unknown protocol: " + protocol);
		}

		switch(major){
			case 0:
				return createProtocolVersion0(minor);
			case 1:
				return createProtocolVersion1(minor);
			case 2:
				return createProtocolVersion2(minor);
			default:
				throw new IllegalArgumentException("Unknown protocol major: " + major);
		}
	}

	@Override
	public String toString(){
		final StringBuilder sb = new StringBuilder();

		sb.append(protocol).append('/').append(major).append('.').append(minor);

		return sb.toString();
	}

	private static ProtocolVersion createProtocolVersion0(final int minor){
		switch(minor){
			case 9:
				return HTTP_0_9;
			default:
				throw new IllegalArgumentException("Unknown protocol minor: " + minor);
		}
	}

	private static ProtocolVersion createProtocolVersion1(final int minor){
		switch(minor){
			case 0:
				return HTTP_1_0;
			case 1:
				return HTTP_1_1;
			default:
				throw new IllegalArgumentException("Unknown protocol minor: " + minor);
		}
	}

	private static ProtocolVersion createProtocolVersion2(final int minor){
		switch(minor){
			case 0:
				return HTTP_2_0;
			default:
				throw new IllegalArgumentException("Unknown protocol minor: " + minor);
		}
	}

}
