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
package com.buession.httpclient.apache.convert.h5;

import com.buession.httpclient.core.JsonRawRequestBody;
import com.buession.httpclient.core.RequestBodyConverters;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;

/**
 * @author Yong.Teng
 */
public class JsonRawRequestBodyConverter implements Apache5RequestBodyConverter<JsonRawRequestBody<?>> {

	@Override
	public StringEntity convert(final JsonRawRequestBody<?> source) {
		RequestBodyConverters.JsonRawRequestBodyConverter<StringEntity> jsonRawRequestBodyConverter = new RequestBodyConverters.JsonRawRequestBodyConverter<>(
				(str)->new StringEntity(str, ContentType.create(source.getContentType().getMimeType(),
						source.getContentType().getCharset())));
		return jsonRawRequestBodyConverter.convert(source);
	}

}
