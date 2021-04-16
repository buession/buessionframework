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

import java.nio.charset.Charset;

/**
 * 请求体抽象类
 *
 * @param <V>
 * 		请求体类型
 *
 * @author Yong.Teng
 */
public abstract class AbstractRequestBody<V> implements RequestBody<V> {

	/**
	 * 请求体 Content-Type
	 */
	private ContentType contentType;

	/**
	 * 请求体
	 */
	private V content;

	/**
	 * 请求体大小
	 */
	private long contentLength;

	/**
	 * 构造函数
	 */
	public AbstractRequestBody(){
		this.contentLength = -1;
	}

	/**
	 * 构造函数
	 *
	 * @param contentType
	 * 		请求体 Content-Type
	 * @param content
	 * 		请求体
	 */
	public AbstractRequestBody(ContentType contentType, V content){
		this();
		this.contentType = contentType;
		this.content = content;
	}

	/**
	 * 构造函数
	 *
	 * @param contentType
	 * 		请求体 Content-Type
	 * @param content
	 * 		请求体
	 * @param contentLength
	 * 		请求体大小
	 */
	public AbstractRequestBody(ContentType contentType, V content, long contentLength){
		this(contentType, content);
		this.contentLength = contentLength;
	}

	/**
	 * 构造函数，ContentType 为 text/plain
	 *
	 * @param content
	 * 		请求体
	 * @param charset
	 * 		请求体编码
	 */
	public AbstractRequestBody(V content, Charset charset){
		this(new ContentType(ContentType.TEXT_PLAIN.getMimeType(), charset), content);
	}

	/**
	 * 构造函数，ContentType 为 text/plain
	 *
	 * @param content
	 * 		请求体
	 * @param contentLength
	 * 		请求体大小
	 * @param charset
	 * 		请求体编码
	 */
	public AbstractRequestBody(V content, long contentLength, Charset charset){
		this(new ContentType(ContentType.TEXT_PLAIN.getMimeType(), charset), content, contentLength);
	}

	@Override
	public ContentType getContentType(){
		return contentType;
	}

	public void setContentType(ContentType contentType){
		this.contentType = contentType;
	}

	@Override
	public V getContent(){
		return content;
	}

	public void setContent(final V content){
		this.content = content;
	}

	@Override
	public long getContentLength(){
		return contentLength;
	}

	public void setContentLength(final long contentLength){
		this.contentLength = contentLength;
	}

}
