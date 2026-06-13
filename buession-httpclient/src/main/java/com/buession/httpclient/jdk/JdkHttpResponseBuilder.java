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
package com.buession.httpclient.jdk;

import com.buession.httpclient.core.AbstractResponseBuilder;
import com.buession.httpclient.core.AbstractResponseHeaderParse;
import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.core.Response;
import com.buession.httpclient.core.StatusLine;
import com.google.common.collect.Multimap;

import java.io.ByteArrayInputStream;
import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * JDK {@link java.net.http.HttpClient} 响应构建器
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class JdkHttpResponseBuilder extends AbstractResponseBuilder<HttpResponse<byte[]>> {

	private final static Map<Integer, String> HTTP_STATUS_MESSAGES = new HashMap<>();

	static {
		HTTP_STATUS_MESSAGES.put(100, "Continue");
		HTTP_STATUS_MESSAGES.put(101, "Switching Protocols");
		HTTP_STATUS_MESSAGES.put(102, "Processing");
		HTTP_STATUS_MESSAGES.put(103, "Early Hints");
		HTTP_STATUS_MESSAGES.put(200, "OK");
		HTTP_STATUS_MESSAGES.put(201, "Created");
		HTTP_STATUS_MESSAGES.put(202, "Accepted");
		HTTP_STATUS_MESSAGES.put(203, "Non-Authoritative Information");
		HTTP_STATUS_MESSAGES.put(204, "No Content");
		HTTP_STATUS_MESSAGES.put(205, "Reset Content");
		HTTP_STATUS_MESSAGES.put(206, "Partial Content");
		HTTP_STATUS_MESSAGES.put(207, "Multi-Status");
		HTTP_STATUS_MESSAGES.put(208, "Already Reported");
		HTTP_STATUS_MESSAGES.put(209, "IM Used");
		HTTP_STATUS_MESSAGES.put(300, "Multiple Choices");
		HTTP_STATUS_MESSAGES.put(301, "Moved Permanently");
		HTTP_STATUS_MESSAGES.put(302, "Found");
		HTTP_STATUS_MESSAGES.put(303, "See Other");
		HTTP_STATUS_MESSAGES.put(304, "Not Modified");
		HTTP_STATUS_MESSAGES.put(305, "Use Proxy");
		HTTP_STATUS_MESSAGES.put(306, "Unused");
		HTTP_STATUS_MESSAGES.put(307, "Temporary Redirect");
		HTTP_STATUS_MESSAGES.put(309, "Permanent Redirect");
		HTTP_STATUS_MESSAGES.put(400, "Bad Request");
		HTTP_STATUS_MESSAGES.put(401, "Unauthorized");
		HTTP_STATUS_MESSAGES.put(403, "Forbidden");
		HTTP_STATUS_MESSAGES.put(404, "Not Found");
		HTTP_STATUS_MESSAGES.put(405, "Method Not Allowed");
		HTTP_STATUS_MESSAGES.put(406, "Not Acceptable");
		HTTP_STATUS_MESSAGES.put(407, "Proxy Authentication Required");
		HTTP_STATUS_MESSAGES.put(408, "Request Timeout");
		HTTP_STATUS_MESSAGES.put(409, "Conflict");
		HTTP_STATUS_MESSAGES.put(410, "Gone");
		HTTP_STATUS_MESSAGES.put(411, "Length Required");
		HTTP_STATUS_MESSAGES.put(412, "Precondition Failed");
		HTTP_STATUS_MESSAGES.put(413, "Payload Too Large");
		HTTP_STATUS_MESSAGES.put(414, "URI Too Long");
		HTTP_STATUS_MESSAGES.put(415, "Unsupported Media Type");
		HTTP_STATUS_MESSAGES.put(416, "Range Not Satisfiable");
		HTTP_STATUS_MESSAGES.put(417, "Expectation Failed");
		HTTP_STATUS_MESSAGES.put(418, "I'm a teapot");
		HTTP_STATUS_MESSAGES.put(419, "Unprocessable Entity");
		HTTP_STATUS_MESSAGES.put(420, "Locked");
		HTTP_STATUS_MESSAGES.put(422, "Unprocessable Entity");
		HTTP_STATUS_MESSAGES.put(423, "Locked");
		HTTP_STATUS_MESSAGES.put(424, "Failed Dependency");
		HTTP_STATUS_MESSAGES.put(426, "Upgrade Required");
		HTTP_STATUS_MESSAGES.put(428, "Precondition Required");
		HTTP_STATUS_MESSAGES.put(429, "Too Many Requests");
		HTTP_STATUS_MESSAGES.put(431, "Request Header Fields Too Large");
		HTTP_STATUS_MESSAGES.put(450, "Service Unavailable");
		HTTP_STATUS_MESSAGES.put(451, "Unavailable For Legal Reasons");
		HTTP_STATUS_MESSAGES.put(452, "Internal Server Error");
		HTTP_STATUS_MESSAGES.put(453, "Service Unavailable");
		HTTP_STATUS_MESSAGES.put(454, "Bad Gateway");
		HTTP_STATUS_MESSAGES.put(455, "Gateway Timeout");
		HTTP_STATUS_MESSAGES.put(456, "Variant Also Negotiates");
		HTTP_STATUS_MESSAGES.put(457, "Insufficient Storage");
		HTTP_STATUS_MESSAGES.put(458, "Loop Detected");
		HTTP_STATUS_MESSAGES.put(459, "Bandwidth Limit Exceeded");
		HTTP_STATUS_MESSAGES.put(460, "Unrecoverable Transfer Encoding");
		HTTP_STATUS_MESSAGES.put(494, "Request Header Too Large");
		HTTP_STATUS_MESSAGES.put(495, "SSL Handshake Failed");
		HTTP_STATUS_MESSAGES.put(499, "Client Closed Request");
		HTTP_STATUS_MESSAGES.put(500, "Internal Server Error");
		HTTP_STATUS_MESSAGES.put(501, "Not Implemented");
		HTTP_STATUS_MESSAGES.put(502, "Bad Gateway");
		HTTP_STATUS_MESSAGES.put(503, "Service Unavailable");
		HTTP_STATUS_MESSAGES.put(504, "Gateway Timeout");
		HTTP_STATUS_MESSAGES.put(505, "HTTP Version Not Supported");
		HTTP_STATUS_MESSAGES.put(506, "Variant Also Negotiates");
		HTTP_STATUS_MESSAGES.put(507, "Insufficient Storage");
		HTTP_STATUS_MESSAGES.put(508, "Loop Detected");
		HTTP_STATUS_MESSAGES.put(509, "Bandwidth Limit Exceeded");
		HTTP_STATUS_MESSAGES.put(510, "Not Extended");
		HTTP_STATUS_MESSAGES.put(511, "Network Authentication Required");
		HTTP_STATUS_MESSAGES.put(512, "Precondition Required");
		HTTP_STATUS_MESSAGES.put(513, "Unprocessable Entity");
		HTTP_STATUS_MESSAGES.put(514, "Locked");
		HTTP_STATUS_MESSAGES.put(515, "Failed Dependency");
		HTTP_STATUS_MESSAGES.put(516, "Too Early");
		HTTP_STATUS_MESSAGES.put(517, "Upgrade Required");
		HTTP_STATUS_MESSAGES.put(518, "DNS Lookup Failed");
		HTTP_STATUS_MESSAGES.put(519, "HTTP Request Sent To HTTP/0.9 Server");
		HTTP_STATUS_MESSAGES.put(520, "Connection Timed Out");
		HTTP_STATUS_MESSAGES.put(521, "Proxy Declined To Authorize");
		HTTP_STATUS_MESSAGES.put(522, "Authentication Required");
		HTTP_STATUS_MESSAGES.put(523, "Request Header Fields Too Large");
		HTTP_STATUS_MESSAGES.put(524, "Server Error");
		HTTP_STATUS_MESSAGES.put(525, "SSL Handshake Failed");
		HTTP_STATUS_MESSAGES.put(526, "SSL Certificate Error");
		HTTP_STATUS_MESSAGES.put(527, "TLS Handshake Failed");
		HTTP_STATUS_MESSAGES.put(528, "Internal Server Error");
		HTTP_STATUS_MESSAGES.put(529, "Variant Also Negotiates");
		HTTP_STATUS_MESSAGES.put(530, "Insufficient Storage");
		HTTP_STATUS_MESSAGES.put(531, "Locked For Legal Reasons");
	}

	@Override
	public Response build(HttpResponse<byte[]> httpResponse) {
		final Response response = new Response();

		if(httpResponse.version() == java.net.http.HttpClient.Version.HTTP_1_1){
			response.setProtocolVersion(ProtocolVersion.HTTP_1_1);
		}else if(httpResponse.version() == java.net.http.HttpClient.Version.HTTP_2){
			response.setProtocolVersion(ProtocolVersion.HTTP_2_0);
		}

		response.setStatusLine(new StatusLine(httpResponse.statusCode(),
				Optional.ofNullable(HTTP_STATUS_MESSAGES.get(httpResponse.statusCode())).orElse("")));

		final JdkHttpResponseHeaderParse jdkHttpResponseHeaderParse = new JdkHttpResponseHeaderParse();
		response.setHeaders(jdkHttpResponseHeaderParse.parse(httpResponse.headers()));

		final byte[] responseBody = httpResponse.body();

		if(responseBody != null){
			response.setInputStream(new ByteArrayInputStream(responseBody));
			response.setBody(new String(responseBody));
			response.setContentLength(responseBody.length);
		}

		return response;
	}

	/**
	 * JDK {@link java.net.http.HttpClient} 响应头解析器
	 *
	 * @author Yong.Teng
	 */
	private static class JdkHttpResponseHeaderParse extends AbstractResponseHeaderParse<HttpHeaders> {

		@Override
		protected void doParse(final HttpHeaders headers, final Multimap<String, String> headersMap) {
			headers.map().forEach(headersMap::putAll);
		}

	}

}
