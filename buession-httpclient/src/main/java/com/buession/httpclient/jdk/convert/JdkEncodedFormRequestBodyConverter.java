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
package com.buession.httpclient.jdk.convert;

import com.buession.httpclient.core.EncodedFormRequestBody;
import com.buession.httpclient.core.internal.convert.EncodedFormRequestBodyConverter;

import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * JDK {@link java.net.http.HttpClient} application/x-www-form-urlencoded 表单请求体转换器
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class JdkEncodedFormRequestBodyConverter implements JdkHttpClientRequestBodyConverter<EncodedFormRequestBody>,
		EncodedFormRequestBodyConverter<HttpRequest.BodyPublisher> {

	@Override
	public HttpRequest.BodyPublisher convert(final EncodedFormRequestBody source) {
		if(source == null || source.getContent() == null){
			return null;
		}

		Charset charset = Optional.ofNullable(source.getContentType().getCharset()).orElse(StandardCharsets.ISO_8859_1);
		String formBody = source.getContent().stream().map(entry->URLEncoder.encode(entry.getName(), charset) + "=" +
				URLEncoder.encode(entry.getValue(), charset)).collect(Collectors.joining("&"));

		return HttpRequest.BodyPublishers.ofString(formBody);
	}

}
