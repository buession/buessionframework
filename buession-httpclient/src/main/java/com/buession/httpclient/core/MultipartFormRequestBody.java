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
package com.buession.httpclient.core;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class MultipartFormRequestBody extends AbstractRequestBody<List<MultipartRequestBodyElement>> {

	public final static ContentType CONTENT_TYPE = ContentType.MULTIPART_FORM_DATA;

	public MultipartFormRequestBody(){
		super(CONTENT_TYPE, null);
	}

	public MultipartFormRequestBody(List<MultipartRequestBodyElement> content){
		super(CONTENT_TYPE, content);
	}

	public MultipartFormRequestBody(List<MultipartRequestBodyElement> content, long contentLength){
		super(CONTENT_TYPE, content, contentLength);
	}

	public MultipartFormRequestBody(Header contentEncoding, List<MultipartRequestBodyElement> content){
		super(CONTENT_TYPE, contentEncoding, content);
	}

	public MultipartFormRequestBody(Header contentEncoding, List<MultipartRequestBodyElement> content,
									long contentLength){
		super(CONTENT_TYPE, contentEncoding, content, contentLength);
	}

	public MultipartFormRequestBody(List<MultipartRequestBodyElement> content, Charset charset){
		this(content, -1, charset);
	}

	public MultipartFormRequestBody(List<MultipartRequestBodyElement> content, long contentLength, Charset charset){
		super(new ContentType(CONTENT_TYPE.getMimeType(), charset), content, contentLength);
	}

	public MultipartFormRequestBody(Header contentEncoding, List<MultipartRequestBodyElement> content,
									Charset charset){
		this(contentEncoding, content, -1, charset);
	}

	public MultipartFormRequestBody(Header contentEncoding, List<MultipartRequestBodyElement> content,
									long contentLength, Charset charset){
		super(new ContentType(CONTENT_TYPE.getMimeType(), charset), contentEncoding, content, contentLength);
	}

	public void addRequestBodyElement(MultipartRequestBodyElement requestBodyElement){
		if(getContent() == null){
			setContent(new ArrayList<>());
		}

		getContent().add(requestBodyElement);
	}

}
