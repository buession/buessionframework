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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.apache.nio.protocol;

import com.buession.httpclient.apache.ApacheResponseBuilder;
import com.buession.httpclient.core.Response;
import org.apache.http.ContentTooLongException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.ContentDecoder;
import org.apache.http.nio.IOControl;
import org.apache.http.nio.entity.ContentBufferEntity;
import org.apache.http.nio.protocol.AbstractAsyncResponseConsumer;
import org.apache.http.nio.util.HeapByteBufferAllocator;
import org.apache.http.nio.util.SimpleInputBuffer;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.Asserts;

import java.io.IOException;

/**
 * @author Yong.Teng
 * @since 2.3.0
 */
public class BasicAsyncResponseConsumer extends AbstractAsyncResponseConsumer<Response> {

	private static final int MAX_INITIAL_BUFFER_SIZE = 262144;

	private volatile HttpResponse response;

	private volatile SimpleInputBuffer buffer;

	public BasicAsyncResponseConsumer(){
	}

	@Override
	protected void onResponseReceived(HttpResponse response) throws IOException{
		this.response = response;
	}

	@Override
	protected void onEntityEnclosed(HttpEntity entity, ContentType contentType) throws IOException{
		long contentLength = entity.getContentLength();

		if(contentLength > 2147483647L){
			throw new ContentTooLongException("Entity content is too long: %,d", new Object[]{contentLength});
		}else{
			if(contentLength < 0L){
				contentLength = 4096L;
			}

			int initialBufferSize = Math.min((int) contentLength, MAX_INITIAL_BUFFER_SIZE);
			buffer = new SimpleInputBuffer(initialBufferSize, new HeapByteBufferAllocator());
			response.setEntity(new ContentBufferEntity(entity, buffer));
		}
	}

	@Override
	protected void onContentReceived(ContentDecoder decoder, IOControl ioControl) throws IOException{
		Asserts.notNull(buffer, "Content buffer");
		buffer.consumeContent(decoder);
	}

	@Override
	protected void releaseResources(){
		response = null;
		buffer = null;
	}

	@Override
	protected Response buildResult(HttpContext context){
		final ApacheResponseBuilder apacheResponseBuilder = new ApacheResponseBuilder();
		return apacheResponseBuilder.build(response);
	}

}
