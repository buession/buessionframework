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

import com.buession.httpclient.core.RepeatableInputStreamRequestBody;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.ReleasableInputStreamEntity;

/**
 * @author Yong.Teng
 * @since 1.2.0
 */
public class RepeatableInputStreamRequestBodyConvert
		implements Apache5RequestBodyConverter<RepeatableInputStreamRequestBody> {

	@Override
	public HttpEntity convert(final RepeatableInputStreamRequestBody source) {
		if(source == null || source.getContent() == null){
			return null;
		}

		return new ReleasableInputStreamEntity(source.getContent(), source.getContentLength(),
				ContentType.create(source.getContentType().getMimeType(), source.getContentType().getCharset()));
	}

}
