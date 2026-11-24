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
package com.buession.httpclient.apache.convert.utils;

import com.buession.httpclient.apache.convert.*;
import com.buession.httpclient.apache.convert.h5.*;
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

	private static Map<Class<? extends RequestBody>, RequestBodyConverter> APACHE4_CLIENT_REQUEST_BODY_CONVERTER =
			null;

	private static Map<Class<? extends RequestBody>, RequestBodyConverter> APACHE5_CLIENT_REQUEST_BODY_CONVERTER = null;

	private ApacheRequestBodyConverterUtils() {

	}

	public static Map<Class<? extends RequestBody>, RequestBodyConverter> createApache4ClientRequestBodyConverter() {
		if(APACHE4_CLIENT_REQUEST_BODY_CONVERTER == null){
			APACHE4_CLIENT_REQUEST_BODY_CONVERTER = new HashMap<>(Math.max((int) (10 / 0.75F) + 1, 16));

			APACHE4_CLIENT_REQUEST_BODY_CONVERTER.put(ChunkedInputStreamRequestBody.class,
					new ApacheChunkedInputStreamRequestBodyConverter());
			APACHE4_CLIENT_REQUEST_BODY_CONVERTER.put(EncodedFormRequestBody.class,
					new ApacheEncodedFormRequestBodyConverter());
			APACHE4_CLIENT_REQUEST_BODY_CONVERTER.put(HtmlRawRequestBody.class,
					new ApacheHtmlRawRequestBodyConverter());
			APACHE4_CLIENT_REQUEST_BODY_CONVERTER.put(InputStreamRequestBody.class,
					new ApacheInputStreamRequestBodyConvert());
			APACHE4_CLIENT_REQUEST_BODY_CONVERTER.put(JavaScriptRawRequestBody.class,
					new ApacheJavaScriptRawRequestBodyConverter());
			APACHE4_CLIENT_REQUEST_BODY_CONVERTER.put(JsonRawRequestBody.class,
					new ApacheJsonRawRequestBodyConverter());
			APACHE4_CLIENT_REQUEST_BODY_CONVERTER.put(MultipartFormRequestBody.class,
					new ApacheMultipartFormRequestBodyConverter());
			APACHE4_CLIENT_REQUEST_BODY_CONVERTER.put(RepeatableInputStreamRequestBody.class,
					new ApacheRepeatableInputStreamRequestBodyConvert());
			APACHE4_CLIENT_REQUEST_BODY_CONVERTER.put(TextRawRequestBody.class,
					new ApacheTextRawRequestBodyConverter());
			APACHE4_CLIENT_REQUEST_BODY_CONVERTER.put(XmlRawRequestBody.class, new ApacheXmlRawRequestBodyConverter());
		}

		return APACHE4_CLIENT_REQUEST_BODY_CONVERTER;
	}

	public static Map<Class<? extends RequestBody>, RequestBodyConverter> createApache5ClientRequestBodyConverter() {
		if(APACHE5_CLIENT_REQUEST_BODY_CONVERTER == null){
			APACHE5_CLIENT_REQUEST_BODY_CONVERTER = new HashMap<>(Math.max((int) (10 / 0.75F) + 1, 16));

			APACHE5_CLIENT_REQUEST_BODY_CONVERTER.put(ChunkedInputStreamRequestBody.class,
					new Apache5ChunkedInputStreamRequestBodyConverter());
			APACHE5_CLIENT_REQUEST_BODY_CONVERTER.put(EncodedFormRequestBody.class,
					new Apache5EncodedFormRequestBodyConverter());
			APACHE5_CLIENT_REQUEST_BODY_CONVERTER.put(HtmlRawRequestBody.class,
					new Apache5HtmlRawRequestBodyConverter());
			APACHE5_CLIENT_REQUEST_BODY_CONVERTER.put(InputStreamRequestBody.class,
					new Apache5InputStreamRequestBodyConvert());
			APACHE5_CLIENT_REQUEST_BODY_CONVERTER.put(JavaScriptRawRequestBody.class,
					new Apache5JavaScriptRawRequestBodyConverter());
			APACHE5_CLIENT_REQUEST_BODY_CONVERTER.put(JsonRawRequestBody.class,
					new Apache5JsonRawRequestBodyConverter());
			APACHE5_CLIENT_REQUEST_BODY_CONVERTER.put(MultipartFormRequestBody.class,
					new Apache5MultipartFormRequestBodyConverter());
			APACHE5_CLIENT_REQUEST_BODY_CONVERTER.put(RepeatableInputStreamRequestBody.class,
					new Apache5RepeatableInputStreamRequestBodyConvert());
			APACHE5_CLIENT_REQUEST_BODY_CONVERTER.put(TextRawRequestBody.class,
					new Apache5TextRawRequestBodyConverter());
			APACHE5_CLIENT_REQUEST_BODY_CONVERTER.put(XmlRawRequestBody.class, new Apache5XmlRawRequestBodyConverter());
		}

		return APACHE5_CLIENT_REQUEST_BODY_CONVERTER;

	}

}
