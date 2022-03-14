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
package com.buession.httpclient.okhttp.convert;

import com.buession.core.validator.Validate;
import com.buession.httpclient.core.MultipartFormRequestBody;
import com.buession.httpclient.core.MultipartInputStreamRequestBodyElement;
import com.buession.httpclient.core.MultipartRequestBodyElement;
import com.buession.io.MimeType;
import com.buession.io.file.File;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.nio.charset.StandardCharsets;

/**
 * @author Yong.Teng
 */
public class MultipartFormRequestBodyConverter implements OkHttpRequestBodyConverter<MultipartFormRequestBody> {

	@Override
	public RequestBody convert(MultipartFormRequestBody source){
		if(source == null || source.getContent() == null){
			return null;
		}

		final MultipartBody.Builder builder = new okhttp3.MultipartBody.Builder();

		builder.setType(MultipartBody.FORM);

		for(MultipartRequestBodyElement element : source.getContent()){
			addBody(source, builder, element);
		}

		return builder.build();
	}

	private static void addBody(final MultipartFormRequestBody requestBody, final MultipartBody.Builder builder, final MultipartRequestBodyElement element){
		if(element instanceof MultipartInputStreamRequestBodyElement){
			MultipartInputStreamRequestBodyElement multipartInputStreamRequestBodyElement = (MultipartInputStreamRequestBodyElement) element;

			if(multipartInputStreamRequestBodyElement.getInputStream() != null){
				okhttp3.RequestBody okRequestBody;
				MediaType mediaType;

				if(Validate.hasText(multipartInputStreamRequestBodyElement.getFileName())){
					File file = new File(multipartInputStreamRequestBodyElement.getFileName());
					mediaType = parseMultipartElementMimeType(file);
				}else{
					mediaType = MediaType.parse("application/octet-stream");
				}

				okRequestBody = okhttp3.RequestBody.create(multipartInputStreamRequestBodyElement.getFileName().getBytes(StandardCharsets.UTF_8), mediaType);
				builder.addFormDataPart(element.getName(), multipartInputStreamRequestBodyElement.getFileName(), okRequestBody);
			}else{
				builder.addFormDataPart(element.getName(), element.getValue());
			}
		}else{
			if(element.getFile() != null){
				File file = new File(element.getFile());
				MediaType mediaType = parseMultipartElementMimeType(file);

				builder.addFormDataPart(element.getName(), file.getName(), RequestBody.create(file, mediaType));
			}else{
				builder.addFormDataPart(element.getName(), element.getValue());
			}
		}
	}

	private static MediaType parseMultipartElementMimeType(final File file){
		MimeType mimeType = file.getMimeType();
		return mimeType == null ? MediaType.parse("application/octet-stream") : MediaType.parse(mimeType.toString());
	}

}
