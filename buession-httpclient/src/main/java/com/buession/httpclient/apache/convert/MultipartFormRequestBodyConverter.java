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
package com.buession.httpclient.apache.convert;

import com.buession.core.validator.Validate;
import com.buession.httpclient.core.MultipartFormRequestBody;
import com.buession.httpclient.core.MultipartInputStreamRequestBodyElement;
import com.buession.httpclient.core.MultipartRequestBodyElement;
import com.buession.io.MimeType;
import com.buession.io.file.File;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.nio.charset.Charset;

/**
 * @author Yong.Teng
 */
public class MultipartFormRequestBodyConverter implements ApacheRequestBodyConverter<MultipartFormRequestBody> {

	@Override
	public HttpEntity convert(MultipartFormRequestBody source){
		if(source == null || source.getContent() == null){
			return null;
		}

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();

		builder.setContentType(ContentType.MULTIPART_FORM_DATA);
		builder.setCharset(source.getContentType().getCharset());
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

		for(MultipartRequestBodyElement element : source.getContent()){
			addBody(source, builder, element);
		}

		return builder.build();
	}

	private static void addBody(final MultipartFormRequestBody requestBody, final MultipartEntityBuilder builder, final MultipartRequestBodyElement element){
		if(element instanceof MultipartInputStreamRequestBodyElement){
			MultipartInputStreamRequestBodyElement multipartInputStreamRequestBodyElement = (MultipartInputStreamRequestBodyElement) element;

			if(multipartInputStreamRequestBodyElement.getInputStream() != null){
				if(Validate.hasText(multipartInputStreamRequestBodyElement.getFileName())){
					File file = new File(multipartInputStreamRequestBodyElement.getFileName());
					ContentType contentType = parseMultipartElementMimeType(file, requestBody.getContentType().getCharset());

					builder.addBinaryBody(element.getName(), multipartInputStreamRequestBodyElement.getInputStream(), contentType, multipartInputStreamRequestBodyElement.getFileName());
				}else{
					builder.addBinaryBody(element.getName(), multipartInputStreamRequestBodyElement.getInputStream());
				}
			}else{
				builder.addTextBody(element.getName(), element.getValue());
			}
		}else{
			if(element.getFile() != null){
				File file = new File(element.getFile());
				ContentType contentType = parseMultipartElementMimeType(file, requestBody.getContentType().getCharset());

				builder.addBinaryBody(element.getName(), element.getFile(), contentType, file.getName());
			}else{
				builder.addTextBody(element.getName(), element.getValue());
			}
		}
	}

	private static ContentType parseMultipartElementMimeType(final File file, final Charset charset){
		MimeType mimeType = file.getMimeType();

		return mimeType == null ? ContentType.APPLICATION_OCTET_STREAM : ContentType.create(mimeType.toString(), charset);
	}

}
