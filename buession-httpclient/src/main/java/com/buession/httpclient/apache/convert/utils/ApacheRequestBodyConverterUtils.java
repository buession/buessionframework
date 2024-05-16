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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.apache.convert.utils;

import com.buession.httpclient.core.ChunkedInputStreamRequestBody;
import com.buession.httpclient.core.EncodedFormRequestBody;
import com.buession.httpclient.core.HtmlRawRequestBody;
import com.buession.httpclient.core.InputStreamRequestBody;
import com.buession.httpclient.core.JavaScriptRawRequestBody;
import com.buession.httpclient.core.JsonRawRequestBody;
import com.buession.httpclient.core.MultipartFormRequestBody;
import com.buession.httpclient.core.RepeatableInputStreamRequestBody;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.RequestBodyConverter;
import com.buession.httpclient.core.TextRawRequestBody;
import com.buession.httpclient.core.XmlRawRequestBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 * @since 3.0.0
 */
public class ApacheRequestBodyConverterUtils {

	private ApacheRequestBodyConverterUtils() {

	}

	public static Map<Class<? extends RequestBody>, RequestBodyConverter> createApache4ClientRequestBodyConverter() {
		final Map<Class<? extends RequestBody>, RequestBodyConverter> result = new HashMap<>(10);

		result.put(ChunkedInputStreamRequestBody.class,
				new com.buession.httpclient.apache.convert.ChunkedInputStreamRequestBodyConverter());
		result.put(EncodedFormRequestBody.class,
				new com.buession.httpclient.apache.convert.EncodedFormRequestBodyConverter());
		result.put(HtmlRawRequestBody.class, new com.buession.httpclient.apache.convert.HtmlRawRequestBodyConverter());
		result.put(InputStreamRequestBody.class,
				new com.buession.httpclient.apache.convert.InputStreamRequestBodyConvert());
		result.put(JavaScriptRawRequestBody.class,
				new com.buession.httpclient.apache.convert.JavaScriptRawRequestBodyConverter());
		result.put(JsonRawRequestBody.class, new com.buession.httpclient.apache.convert.JsonRawRequestBodyConverter());
		result.put(MultipartFormRequestBody.class,
				new com.buession.httpclient.apache.convert.MultipartFormRequestBodyConverter());
		result.put(RepeatableInputStreamRequestBody.class,
				new com.buession.httpclient.apache.convert.RepeatableInputStreamRequestBodyConvert());
		result.put(TextRawRequestBody.class, new com.buession.httpclient.apache.convert.TextRawRequestBodyConverter());
		result.put(XmlRawRequestBody.class, new com.buession.httpclient.apache.convert.XmlRawRequestBodyConverter());

		return result;
	}

	public static Map<Class<? extends RequestBody>, RequestBodyConverter> createApache5ClientRequestBodyConverter() {
		final Map<Class<? extends RequestBody>, RequestBodyConverter> result = new HashMap<>(10);

		result.put(ChunkedInputStreamRequestBody.class,
				new com.buession.httpclient.apache.convert.h5.ChunkedInputStreamRequestBodyConverter());
		result.put(EncodedFormRequestBody.class,
				new com.buession.httpclient.apache.convert.h5.EncodedFormRequestBodyConverter());
		result.put(HtmlRawRequestBody.class,
				new com.buession.httpclient.apache.convert.h5.HtmlRawRequestBodyConverter());
		result.put(InputStreamRequestBody.class,
				new com.buession.httpclient.apache.convert.h5.InputStreamRequestBodyConvert());
		result.put(JavaScriptRawRequestBody.class,
				new com.buession.httpclient.apache.convert.h5.JavaScriptRawRequestBodyConverter());
		result.put(JsonRawRequestBody.class,
				new com.buession.httpclient.apache.convert.h5.JsonRawRequestBodyConverter());
		result.put(MultipartFormRequestBody.class,
				new com.buession.httpclient.apache.convert.h5.MultipartFormRequestBodyConverter());
		result.put(RepeatableInputStreamRequestBody.class,
				new com.buession.httpclient.apache.convert.h5.RepeatableInputStreamRequestBodyConvert());
		result.put(TextRawRequestBody.class,
				new com.buession.httpclient.apache.convert.h5.TextRawRequestBodyConverter());
		result.put(XmlRawRequestBody.class,
				new com.buession.httpclient.apache.convert.h5.XmlRawRequestBodyConverter());

		return result;

	}

}
