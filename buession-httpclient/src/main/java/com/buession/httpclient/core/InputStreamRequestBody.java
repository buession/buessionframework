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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.core;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 二进制请求体
 *
 * @author Yong.Teng
 */
public class InputStreamRequestBody extends AbstractRequestBody<InputStream> {

	/**
	 * 构造函数，ContentType 为 application/octet-stream
	 */
	public InputStreamRequestBody(){
		this(null);
	}

	/**
	 * 构造函数，ContentType 为 application/octet-stream
	 *
	 * @param content
	 * 		请求流
	 */
	public InputStreamRequestBody(InputStream content){
		this(ContentType.APPLICATION_OBJECT_STREAM, content);
	}

	/**
	 * 构造函数，ContentType 为 application/octet-stream
	 *
	 * @param content
	 * 		请求流
	 * @param contentLength
	 * 		请求体大小
	 */
	public InputStreamRequestBody(InputStream content, long contentLength){
		super(ContentType.APPLICATION_OBJECT_STREAM, content, contentLength);
	}

	/**
	 * 构造函数，ContentType 为 application/octet-stream
	 *
	 * @param content
	 * 		请求流
	 * @param charset
	 * 		请求体字符集
	 */
	public InputStreamRequestBody(InputStream content, Charset charset){
		super(new ContentType(ContentType.APPLICATION_OBJECT_STREAM.getMimeType(), charset), content);
		setContentLength(content);
	}

	/**
	 * 构造函数，ContentType 为 application/octet-stream
	 *
	 * @param content
	 * 		请求流
	 * @param contentLength
	 * 		请求体大小
	 * @param charset
	 * 		请求体字符集
	 */
	public InputStreamRequestBody(InputStream content, long contentLength, Charset charset){
		super(new ContentType(ContentType.APPLICATION_OBJECT_STREAM.getMimeType(), charset), content);
		setContentLength(contentLength);
	}

	/**
	 * 构造函数
	 *
	 * @param contentType
	 * 		ContentType
	 * @param content
	 * 		请求流
	 */
	public InputStreamRequestBody(ContentType contentType, InputStream content){
		super(contentType, content);
		setContentLength(content);
	}

	/**
	 * 构造函数
	 *
	 * @param contentType
	 * 		ContentType
	 * @param content
	 * 		请求流
	 * @param contentLength
	 * 		请求体大小
	 */
	public InputStreamRequestBody(ContentType contentType, InputStream content, long contentLength){
		super(contentType, content, contentLength);
	}

	/**
	 * 构造函数，ContentType 为 application/octet-stream
	 *
	 * @param contentType
	 * 		ContentType
	 * @param content
	 * 		请求流
	 * @param contentLength
	 * 		请求体大小
	 * @param charset
	 * 		请求体字符集
	 */
	public InputStreamRequestBody(ContentType contentType, InputStream content, long contentLength, Charset charset){
		super(new ContentType(contentType.getMimeType(), charset), content);
		setContentLength(contentLength);
	}

	protected void setContentLength(InputStream content){
		try{
			setContentLength(content.available());
		}catch(IOException e){
		}
	}

}
