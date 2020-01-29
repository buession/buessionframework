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
package com.buession.web.http;

/**
 * @author Yong.Teng
 */
public enum HttpHeader {

	ACCEPT("Accept"),

	ACCEPT_CHARSET("Accept-Charset"),

	ACCEPT_ENCODING("Accept-Encoding"),

	ACCEPT_LANGUAGE("Accept-Language"),

	ACCEPT_RANGES("Accept-Ranges"),

	ACCESS_CONTROL_ALLOW_ORIGIN("Access-Control-Allow-Origin"),

	AGE("Age"),

	ALLOW("Allow"),

	AUTHORIZATION("Authorization"),

	CACHE_CONTROL("Cache-Control"),

	CONNECTION("Connection"),

	CONTENT_LENGTH("Content-Length"),

	CONTENT_TYPE("Content-Type"),

	COOKIE("Cookie"),

	ETAG("ETag"),

	EXPIRES("Expires"),

	HOST("Host"),

	IF_MATCH("If-Match"),

	IF_MODIFIED_SINCE("If-Modified-Since"),

	IF_NONE_MATCH("If-None-Match"),

	IF_RANGE("If-Range"),

	IF_UNMODIFIED_SINCE("If-Unmodified-Since"),

	LOCATION("Location"),

	LAST_MODIFIED("Last-Modified"),

	ORIGIN("Origin"),

	PRAGMA("Pragma"),

	RANGE("Range"),

	REFERER("Referer"),

	SERVER("Server"),

	SET_COOKIE("Set-Cookie"),

	TRANSFER_ENCODING("Transfer-Encoding"),

	USER_AGENT("User-Agent"),

	VARY("Vary"),

	VIA("Via");

	private String value;

	HttpHeader(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	@Override
	public String toString(){
		return value;
	}

}
