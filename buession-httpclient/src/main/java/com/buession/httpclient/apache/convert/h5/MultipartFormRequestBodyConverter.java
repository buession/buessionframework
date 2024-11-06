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

import com.buession.httpclient.core.MultipartFormRequestBody;
import com.buession.httpclient.core.MultipartRequestBodyElement;
import com.buession.io.MimeType;
import com.buession.io.file.File;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;

import java.nio.charset.Charset;

/**
 * @author Yong.Teng
 */
public class MultipartFormRequestBodyConverter implements Apache5RequestBodyConverter<MultipartFormRequestBody> {

	@Override
	public HttpEntity convert(final MultipartFormRequestBody source) {
		if(source == null || source.getContent() == null){
			return null;
		}

		final MultipartEntityBuilder builder = MultipartEntityBuilder.create();

		builder.setContentType(ContentType.MULTIPART_FORM_DATA);
		builder.setCharset(source.getContentType().getCharset());
		builder.setMode(HttpMultipartMode.LEGACY);

		for(MultipartRequestBodyElement element : source.getContent()){
			addBody(source, builder, element);
		}

		return builder.build();
	}

	private static void addBody(final MultipartFormRequestBody requestBody, final MultipartEntityBuilder builder,
								final MultipartRequestBodyElement element) {
		if(element.getFile() != null){
			File file = new File(element.getFile());
			ContentType contentType = parseMultipartElementMimeType(file,
					requestBody.getContentType().getCharset());

			builder.addBinaryBody(element.getName(), element.getFile(), contentType, file.getName());
		}else if(element.getInputStream() != null){
			builder.addBinaryBody(element.getName(), element.getInputStream(), ContentType.DEFAULT_BINARY,
					element.getFileName());
		}else{
			builder.addTextBody(element.getName(), element.getValue());
		}
	}

	private static ContentType parseMultipartElementMimeType(final File file, final Charset charset) {
		MimeType mimeType = file.getMimeType();

		return mimeType == null ? ContentType.DEFAULT_BINARY : ContentType.create(mimeType.toString(),
				charset);
	}

}
