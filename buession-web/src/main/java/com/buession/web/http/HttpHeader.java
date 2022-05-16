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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.web.http;

/**
 * 标准 HTTP 头
 *
 * @author Yong.Teng
 */
public enum HttpHeader {

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Accept"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Accept</a>
	 */
	ACCEPT("Accept"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Accept-CH"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Accept-CH</a>
	 */
	ACCEPT_CH("Accept-CH"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Accept-CH-Lifetime"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Accept-CH-Lifetime</a>
	 */
	ACCEPT_CH_LIFETIME("Accept-CH-Lifetime"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Accept-Charset"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Accept-Charset</a>
	 */
	ACCEPT_CHARSET("Accept-Charset"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Accept-Encoding"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Accept-Encoding</a>
	 */
	ACCEPT_ENCODING("Accept-Encoding"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Accept-Language"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Accept-Language</a>
	 */
	ACCEPT_LANGUAGE("Accept-Language"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Accept-Patch"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Accept-Patch</a>
	 */
	ACCEPT_PATCH("Accept-Patch"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Accept-Ranges"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Accept-Ranges</a>
	 */
	ACCEPT_RANGES("Accept-Ranges"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Allow-Credentials"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Allow-Credentials</a>
	 */
	ACCESS_CONTROL_ALLOW_CREDENTIALS("Access-Control-Allow-Credentials"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Allow-Headers"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Allow-Headers</a>
	 */
	ACCESS_CONTROL_ALLOW_HEADERS("Access-Control-Allow-Headers"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Allow-Methods"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Allow-Methods</a>
	 */
	ACCESS_CONTROL_ALLOW_METHODS("Access-Control-Allow-Methods"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Allow-Origin"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Allow-Origin</a>
	 */
	ACCESS_CONTROL_ALLOW_ORIGIN("Access-Control-Allow-Origin"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Expose-Headers"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Expose-Headers</a>
	 */
	ACCESS_CONTROL_EXPOSE_HEADERS("Access-Control-Expose-Headers"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Max-Age"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Max-Age</a>
	 */
	ACCESS_CONTROL_MAX_AGE("Access-Control-Max-Age"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Request-Headers"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Request-Headers</a>
	 */
	ACCESS_CONTROL_REQUEST_HEADERS("Access-Control-Request-Headers"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Request-Method"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Request-Method</a>
	 */
	ACCESS_CONTROL_REQUEST_METHOD("Access-Control-Request-Method"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Age"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Age</a>
	 */
	AGE("Age"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Allow"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Allow</a>
	 */
	ALLOW("Allow"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Authorization"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Authorization</a>
	 */
	AUTHORIZATION("Authorization"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cache-Control"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cache-Control</a>
	 */
	CACHE_CONTROL("Cache-Control"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Clear-Site-Data"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Clear-Site-Data</a>
	 */
	CLEAR_SITE_DATA("Clear-Site-Data"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Connection"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Connection</a>
	 */
	CONNECTION("Connection"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Disposition"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Disposition</a>
	 */
	CONTENT_DISPOSITION("Content-Disposition"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Encoding"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Encoding</a>
	 */
	CONTENT_ENCODING("Content-Encoding"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Language"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Language</a>
	 */
	CONTENT_LANGUAGE("Content-Language"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Length"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Length</a>
	 */
	CONTENT_LENGTH("Content-Length"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Location"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Location</a>
	 */
	CONTENT_LOCATION("Content-Location"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Range"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Range</a>
	 */
	CONTENT_RANGE("Content-Range"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Security-Policy"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Security-Policy</a>
	 */
	CONTENT_SECURITY_POLICY("Content-Security-Policy"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Security-Policy-Report-Only"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Security-Policy-Report-Only
	 * </a>
	 */
	CONTENT_SECURITY_POLICY_REPORT_ONLY("Content-Security-Policy-Report-Only"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Type"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Content-Type</a>
	 */
	CONTENT_TYPE("Content-Type"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cookie"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cookie</a>
	 */
	COOKIE("Cookie"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cross-Origin-Embedder-Policy"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cross-Origin-Embedder-Policy</a>
	 */
	CROSS_ORIGIN_EMBEDDER_POLICY("Cross-Origin-Embedder-Policy"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cross-Origin-Opener-Policy"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cross-Origin-Opener-Policy</a>
	 */
	CROSS_ORIGIN_OPENER_POLICY("Cross-Origin-Opener-Policy"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cross-Origin-Resource-Policy"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Cross-Origin-Resource-Policy</a>
	 */
	CROSS_ORIGIN_RESOURCE_POLICY("Cross-Origin-Resource-Policy"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/DNT"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/DNT</a>
	 */
	DNT("DNT"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/DPR"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/DPR</a>
	 */
	DPR("DPR"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Date"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Date</a>
	 */
	DATE("Date"),


	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Device-Memory"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Device-Memory</a>
	 */
	DEVICE_MEMORY("Device-Memory"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Digest"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Digest</a>
	 */
	DIGEST("Digest"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/ETag"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/ETag</a>
	 */
	ETAG("ETag"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Early-Data"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Early-Data</a>
	 */
	EARLY_DATA("Early-Data"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Expect"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Expect</a>
	 */
	EXPECT("Expect"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Expect-CT"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Expect-CT</a>
	 */
	EXPECT_CT("Expect-CT"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Expires"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Expires</a>
	 */
	EXPIRES("Expires"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Feature-Policy"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Feature-Policy</a>
	 */
	FEATURE_POLICY("Feature-Policy"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Forwarded"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Forwarded</a>
	 */
	FORWARDED("Forwarded"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/From"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/From</a>
	 */
	FROM("From"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Host"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Host</a>
	 */
	HOST("Host"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/If-Match"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/If-Match</a>
	 */
	IF_MATCH("If-Match"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/If-Modified-Since"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/If-Modified-Since</a>
	 */
	IF_MODIFIED_SINCE("If-Modified-Since"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/If-None-Match"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/If-None-Match</a>
	 */
	IF_NONE_MATCH("If-None-Match"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/If-Range"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/If-Range</a>
	 */
	IF_RANGE("If-Range"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/If-Unmodified-Since"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/If-Unmodified-Since</a>
	 */
	IF_UNMODIFIED_SINCE("If-Unmodified-Since"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Index"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Index</a>
	 */
	INDEX("Index"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Keep-Alive"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Keep-Alive</a>
	 */
	KEEP_ALIVE("Keep-Alive"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Large-Allocation"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Large-Allocation</a>
	 */
	LARGE_ALLOCATION("Large-Allocation"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Last-Modified"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Last-Modified</a>
	 */
	LAST_MODIFIED("Last-Modified"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Link"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Link</a>
	 */
	LINK("Link"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Location"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Location</a>
	 */
	LOCATION("Location"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/NEL"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/NEL</a>
	 */
	NEL("NEL"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Origin"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Origin</a>
	 */
	ORIGIN("Origin"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Pragma"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Pragma</a>
	 */
	PRAGMA("Pragma"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Proxy-Authenticate"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Proxy-Authenticate</a>
	 */
	PROXY_AUTHENTICATE("Proxy-Authenticate"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Proxy-Authorization"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Proxy-Authorization</a>
	 */
	PROXY_AUTHORIZATION("Proxy-Authorization"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Range"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Range</a>
	 */
	RANGE("Range"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Referer"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Referer</a>
	 */
	REFERER("Referer"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Referrer-Policy"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Referrer-Policy</a>
	 */
	REFERRER_POLICY("Referrer-Policy"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Retry-After"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Retry-After</a>
	 */
	RETRY_AFTER("Retry-After"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Save-Data"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Save-Data</a>
	 */
	SAVE_DATA("Save-Data"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Sec-Fetch-Dest"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Sec-Fetch-Dest</a>
	 */
	SEC_FETCH_DEST("Sec-Fetch-Dest"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Sec-Fetch-Mode"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Sec-Fetch-Mode</a>
	 */
	SEC_FETCH_MODE("Sec-Fetch-Mode"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Sec-Fetch-Site"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Sec-Fetch-Site</a>
	 */
	SEC_FETCH_SITE("Sec-Fetch-Site"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Sec-Fetch-User"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Sec-Fetch-User</a>
	 */
	SEC_FETCH_USER("Sec-Fetch-User"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Sec-WebSocket-Accept"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Sec-WebSocket-Accept</a>
	 */
	SEC_WEBSOCKET_ACCEPT("Sec-WebSocket-Accept"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Server"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Server</a>
	 */
	SERVER("Server"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Server-Timing"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Server-Timing</a>
	 */
	SERVER_TIMING("Server-Timing"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Set-Cookie"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Set-Cookie</a>
	 */
	SET_COOKIE("Set-Cookie"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/SourceMap"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/SourceMap</a>
	 */
	SOURCEMAP("SourceMap"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Strict-Transport-Security"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Strict-Transport-Security</a>
	 */
	STRICT_TRANSPORT_SECURITY("Strict-Transport-Security"),

	/**
	 * 详细说明：
	 * <a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/TE" target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/TE</a>
	 */
	TE("TE"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Timing-Allow-Origin"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Timing-Allow-Origin</a>
	 */
	TIMING_ALLOW_ORIGIN("Timing-Allow-Origin"),

	/**
	 * 详细说明：
	 * <a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Tk" target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Tk</a>
	 */
	TK("Tk"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Trailer"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Trailer</a>
	 */
	TRAILER("Trailer"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Transfer-Encoding"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Transfer-Encoding</a>
	 */
	TRANSFER_ENCODING("Transfer-Encoding"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Upgrade-Insecure-Requests"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Upgrade-Insecure-Requests</a>
	 */
	UPGRADE_INSECURE_REQUESTS("Upgrade-Insecure-Requests"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/User-Agent"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/User-Agent</a>
	 */
	USER_AGENT("User-Agent"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Vary"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Vary</a>
	 */
	VARY("Vary"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Via"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Via</a>
	 */
	VIA("Via"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/WWW-Authenticate"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/WWW-Authenticate</a>
	 */
	WWW_AUTHENTICATE("WWW-Authenticate"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Want-Digest"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Want-Digest</a>
	 */
	WANT_DIGEST("Want-Digest"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Warning"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Warning</a>
	 */
	WARNING("Warning"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-Content-Type-Options"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-Content-Type-Options</a>
	 */
	X_CONTENT_TYPE_OPTIONS("X-Content-Type-Options"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-DNS-Prefetch-Control"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-DNS-Prefetch-Control</a>
	 */
	X_DNS_PREFETCH_CONTROL("X-DNS-Prefetch-Control"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-Forwarded-For"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-Forwarded-For</a>
	 */
	X_FORWARDED_FOR("X-Forwarded-For"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-Forwarded-Host"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-Forwarded-Host</a>
	 */
	X_FORWARDED_HOST("X-Forwarded-Host"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-Forwarded-Proto"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-Forwarded-Proto</a>
	 */
	X_FORWARDED_PROTO("X-Forwarded-Proto"),

	X_FORWARDED_PROTOCOL("X-Forwarded-Protocol"),

	X_FORWARDED_PORT("X-Forwarded-Port"),

	X_REQUESTED_WITH("X-Requested-With"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-Frame-Options"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-Frame-Options</a>
	 */
	X_FRAME_OPTIONS("X-Frame-Options"),

	/**
	 * 详细说明：<a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-XSS-Protection"
	 * target="_blank">https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/X-XSS-Protection</a>
	 */
	X_XSS_PROTECTION("X-XSS-Protection");

	private final String value;

	HttpHeader(final String value){
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
