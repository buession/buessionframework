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
package com.buession.httpclient.okhttp;

import com.buession.httpclient.core.ContentType;
import com.buession.httpclient.core.io.Releasable;
import okio.BufferedSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Yong.Teng
 */
public class ChunkedInputStreamRequestBody extends InputStreamRequestBody implements Releasable {

	private final static Logger logger = LoggerFactory.getLogger(ChunkedInputStreamRequestBody.class);

	ChunkedInputStreamRequestBody(InputStream content, long contentLength, ContentType contentType){
		super(content, contentLength, contentType);
	}

	@Override
	public boolean isChunked(){
		return true;
	}

	@Override
	public void writeTo(BufferedSink sink) throws IOException{
		if(isFirstAttempt() == false && isRepeatable()){
			getContent().reset();
		}

		setFirstAttempt(false);
		super.writeTo(sink);
	}

	@Override
	public void release(){
		try{
			getContent().close();
		}catch(IOException e){
			logger.error("Unexpected io exception when trying to close input stream", e);
		}
	}

	public static final class Builder {

		private InputStream content;

		private ContentType contentType;

		private long contentLength;

		public Builder(){
			this.contentType = ContentType.APPLICATION_OBJECT_STREAM;
			this.contentLength = -1;
		}

		public Builder(InputStream content, ContentType contentType){
			this.content = content;
			this.contentType = contentType;

			try{
				this.contentLength = content == null ? -1 : content.available();
			}catch(IOException e){
				this.contentLength = -1;
			}
		}

		public Builder(InputStream content, long contentLength){
			this.content = content;
			this.contentType = ContentType.APPLICATION_OBJECT_STREAM;
			this.contentLength = contentLength;
		}

		public Builder(InputStream content, long contentLength, ContentType contentType){
			this.content = content;
			this.contentType = contentType;
			this.contentLength = contentLength;
		}

		public ChunkedInputStreamRequestBody build(){
			return new ChunkedInputStreamRequestBody(content, contentLength, contentType);
		}

	}

}
