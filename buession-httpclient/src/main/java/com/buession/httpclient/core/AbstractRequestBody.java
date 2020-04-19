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

/**
 * @author Yong.Teng
 */
public abstract class AbstractRequestBody<T> implements RequestBody<T> {

	private ContentType contentType;

	private Header contentEncoding;

	private T content;

	private long contentLength;

	protected boolean chunked;

	public AbstractRequestBody(){
		this.contentLength = -1;
	}

	public AbstractRequestBody(ContentType contentType, T content){
		this.contentType = contentType;
		this.content = content;
	}

	public AbstractRequestBody(ContentType contentType, T content, long contentLength){
		this(contentType, content);
		this.contentLength = contentLength;
	}

	public AbstractRequestBody(ContentType contentType, Header contentEncoding, T content){
		this(contentType, content);
		this.contentEncoding = contentEncoding;
	}

	public AbstractRequestBody(ContentType contentType, Header contentEncoding, T content, long contentLength){
		this(contentType, content, contentLength);
		this.contentEncoding = contentEncoding;
	}

	public AbstractRequestBody(T content, Charset charset){
		this(new ContentType(ContentType.TEXT_PLAIN.getMimeType(), charset), content);
	}

	public AbstractRequestBody(T content, long contentLength, Charset charset){
		this(new ContentType(ContentType.TEXT_PLAIN.getMimeType(), charset), content, contentLength);
	}

	public AbstractRequestBody(Header contentEncoding, T content, Charset charset){
		this(new ContentType(ContentType.TEXT_PLAIN.getMimeType(), charset), contentEncoding, content);
	}

	public AbstractRequestBody(Header contentEncoding, T content, long contentLength, Charset charset){
		this(new ContentType(ContentType.TEXT_PLAIN.getMimeType(), charset), contentEncoding, content, contentLength);
	}

	@Override
	public ContentType getContentType(){
		return contentType;
	}

	public void setContentType(ContentType contentType){
		this.contentType = contentType;
	}

	@Override
	public Header getContentEncoding(){
		return contentEncoding;
	}

	public void setContentEncoding(Header contentEncoding){
		this.contentEncoding = contentEncoding;
	}

	public void setContentEncoding(String contentEncoding){
		if(contentEncoding != null){
			setContentEncoding(new Header(HttpHeader.CONTENT_ENCODING, contentEncoding));
		}
	}

	@Override
	public T getContent(){
		return content;
	}

	public void setContent(final T content){
		this.content = content;
	}

	@Override
	public long getContentLength(){
		return contentLength;
	}

	public void setContentLength(final long contentLength){
		this.contentLength = contentLength;
	}

	@Override
	public boolean isRepeatable(){
		return false;
	}

	public void setChunked(boolean chunked){
		this.chunked = chunked;
	}

	@Override
	public boolean isChunked(){
		return chunked;
	}

	@Override
	public boolean isStreaming(){
		return false;
	}

}
