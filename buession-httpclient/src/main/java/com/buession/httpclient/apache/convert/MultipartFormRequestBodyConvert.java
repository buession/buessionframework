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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.apache.convert;

import com.buession.httpclient.core.MultipartFormRequestBody;
import com.buession.httpclient.core.MultipartRequestBodyElement;
import com.buession.io.MimeType;
import com.buession.io.file.File;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

/**
 * @author Yong.Teng
 */
public class MultipartFormRequestBodyConvert implements ApacheRequestBodyConvert<MultipartFormRequestBody> {

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
			if(element.getFile() != null){
				File file = new File(element.getFile());
				MimeType mimeType = file.getMimeType();
				ContentType contentType = mimeType == null ? ContentType.APPLICATION_OCTET_STREAM :
						ContentType.create(mimeType.toString(), source.getContentType().getCharset());

				builder.addBinaryBody(element.getName(), file, contentType, file.getName());
			}else{
				builder.addTextBody(element.getName(), element.getOptionalValue());
			}
		}

		return builder.build();
	}

}
