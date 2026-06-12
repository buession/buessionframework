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
 * | Copyright @ 2013-2025 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.apache.convert.utils;

import com.buession.httpclient.apache.convert.ApacheChunkedInputStreamRequestBodyConverter;
import com.buession.httpclient.apache.convert.ApacheEncodedFormRequestBodyConverter;
import com.buession.httpclient.apache.convert.ApacheHtmlRawRequestBodyConverter;
import com.buession.httpclient.apache.convert.ApacheInputStreamRequestBodyConvert;
import com.buession.httpclient.apache.convert.ApacheJavaScriptRawRequestBodyConverter;
import com.buession.httpclient.apache.convert.ApacheJsonRawRequestBodyConverter;
import com.buession.httpclient.apache.convert.ApacheMultipartFormRequestBodyConverter;
import com.buession.httpclient.apache.convert.ApacheRepeatableInputStreamRequestBodyConvert;
import com.buession.httpclient.apache.convert.ApacheTextRawRequestBodyConverter;
import com.buession.httpclient.apache.convert.ApacheXmlRawRequestBodyConverter;
import com.buession.httpclient.apache.convert.h5.Apache5ChunkedInputStreamRequestBodyConverter;
import com.buession.httpclient.apache.convert.h5.Apache5EncodedFormRequestBodyConverter;
import com.buession.httpclient.apache.convert.h5.Apache5HtmlRawRequestBodyConverter;
import com.buession.httpclient.apache.convert.h5.Apache5InputStreamRequestBodyConvert;
import com.buession.httpclient.apache.convert.h5.Apache5JavaScriptRawRequestBodyConverter;
import com.buession.httpclient.apache.convert.h5.Apache5JsonRawRequestBodyConverter;
import com.buession.httpclient.apache.convert.h5.Apache5MultipartFormRequestBodyConverter;
import com.buession.httpclient.apache.convert.h5.Apache5RepeatableInputStreamRequestBodyConvert;
import com.buession.httpclient.apache.convert.h5.Apache5TextRawRequestBodyConverter;
import com.buession.httpclient.apache.convert.h5.Apache5XmlRawRequestBodyConverter;
import com.buession.httpclient.core.ChunkedInputStreamRequestBody;
import com.buession.httpclient.core.EncodedFormRequestBody;
import com.buession.httpclient.core.HtmlRawRequestBody;
import com.buession.httpclient.core.InputStreamRequestBody;
import com.buession.httpclient.core.JavaScriptRawRequestBody;
import com.buession.httpclient.core.JsonRawRequestBody;
import com.buession.httpclient.core.MultipartFormRequestBody;
import com.buession.httpclient.core.RepeatableInputStreamRequestBody;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.core.internal.convert.RequestBodyConverter;
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
				new ApacheChunkedInputStreamRequestBodyConverter());
		result.put(EncodedFormRequestBody.class,
				new ApacheEncodedFormRequestBodyConverter());
		result.put(HtmlRawRequestBody.class, new ApacheHtmlRawRequestBodyConverter());
		result.put(InputStreamRequestBody.class,
				new ApacheInputStreamRequestBodyConvert());
		result.put(JavaScriptRawRequestBody.class,
				new ApacheJavaScriptRawRequestBodyConverter());
		result.put(JsonRawRequestBody.class, new ApacheJsonRawRequestBodyConverter());
		result.put(MultipartFormRequestBody.class,
				new ApacheMultipartFormRequestBodyConverter());
		result.put(RepeatableInputStreamRequestBody.class,
				new ApacheRepeatableInputStreamRequestBodyConvert());
		result.put(TextRawRequestBody.class, new ApacheTextRawRequestBodyConverter());
		result.put(XmlRawRequestBody.class, new ApacheXmlRawRequestBodyConverter());

		return result;
	}

	public static Map<Class<? extends RequestBody>, RequestBodyConverter> createApache5ClientRequestBodyConverter() {
		final Map<Class<? extends RequestBody>, RequestBodyConverter> result = new HashMap<>(10);

		result.put(ChunkedInputStreamRequestBody.class,
				new Apache5ChunkedInputStreamRequestBodyConverter());
		result.put(EncodedFormRequestBody.class,
				new Apache5EncodedFormRequestBodyConverter());
		result.put(HtmlRawRequestBody.class,
				new Apache5HtmlRawRequestBodyConverter());
		result.put(InputStreamRequestBody.class,
				new Apache5InputStreamRequestBodyConvert());
		result.put(JavaScriptRawRequestBody.class,
				new Apache5JavaScriptRawRequestBodyConverter());
		result.put(JsonRawRequestBody.class,
				new Apache5JsonRawRequestBodyConverter());
		result.put(MultipartFormRequestBody.class,
				new Apache5MultipartFormRequestBodyConverter());
		result.put(RepeatableInputStreamRequestBody.class,
				new Apache5RepeatableInputStreamRequestBodyConvert());
		result.put(TextRawRequestBody.class,
				new Apache5TextRawRequestBodyConverter());
		result.put(XmlRawRequestBody.class,
				new Apache5XmlRawRequestBodyConverter());

		return result;

	}

}
