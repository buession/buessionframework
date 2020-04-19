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
package com.buession.httpclient.httpcomponents;

import com.buession.httpclient.core.ContentType;
import com.buession.httpclient.core.io.Releasable;
import org.apache.http.entity.AbstractHttpEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Yong.Teng
 */
public class ChunkedInputStreamEntity extends InputStreamEntity {

	private ReleasableInputStreamEntity notClosableRequestEntity;

	private final static Logger logger = LoggerFactory.getLogger(ChunkedInputStreamEntity.class);

	public ChunkedInputStreamEntity(InputStream content, long contentLength, ContentType contentType){
		super(content, contentLength, contentType);

		setChunked(true);

		notClosableRequestEntity = new ReleasableInputStreamEntity(content, contentLength);

		notClosableRequestEntity.setCloseDisabled(true);
		notClosableRequestEntity.setContentType(contentType.toString());
	}

	@Override
	public boolean isChunked(){
		return true;
	}

	@Override
	public boolean isRepeatable(){
		return getContent().markSupported() || notClosableRequestEntity.isRepeatable();
	}

	@Override
	public void writeTo(OutputStream output) throws IOException{
		if(isFirstAttempt() == false && isRepeatable()){
			getContent().reset();
		}

		setFirstAttempt(false);
		notClosableRequestEntity.writeTo(output);
	}

	/**
	 * A releaseable HTTP entity that can control its inner inputstream's
	 * auto-close functionality on/off, and it will try to close its inner
	 * inputstream by default when the inputstream reaches EOF.
	 */
	public static class ReleasableInputStreamEntity extends AbstractHttpEntity implements Releasable {

		private final InputStream content;

		private final long length;

		private boolean closeDisabled;

		public ReleasableInputStreamEntity(final InputStream inputStream){
			this(inputStream, -1);
		}

		public ReleasableInputStreamEntity(final InputStream inputStream, final long length){
			this(inputStream, length, null);
		}

		public ReleasableInputStreamEntity(final InputStream inputStream, final ContentType contentType){
			this(inputStream, -1, contentType);
		}

		public ReleasableInputStreamEntity(final InputStream inputStream, final long length, final ContentType
				contentType){
			super();

			Assert.notNull(inputStream, "Source input stream");

			this.content = inputStream;
			this.length = length;

			if(contentType != null){
				setContentType(contentType.toString());
			}

			closeDisabled = false;
		}

		@Override
		public boolean isRepeatable(){
			return content.markSupported();
		}

		@Override
		public InputStream getContent() throws IOException{
			return content;
		}

		@Override
		public long getContentLength(){
			return length;
		}

		@Override
		public void writeTo(final OutputStream outputStream) throws IOException{
			Assert.notNull(outputStream, "Output stream");

			final InputStream inputStream = content;

			try{
				final byte[] buffer = new byte[OUTPUT_BUFFER_SIZE];
				int l;

				if(length < 0){
					// consume until EOF
					while((l = inputStream.read(buffer)) != -1){
						outputStream.write(buffer, 0, l);
					}
				}else{
					// consume no more than length
					long remaining = length;

					while(remaining > 0){
						l = inputStream.read(buffer, 0, (int) Math.min(OUTPUT_BUFFER_SIZE, remaining));
						if(l == -1){
							break;
						}

						outputStream.write(buffer, 0, l);
						remaining -= l;
					}
				}
			}finally{
				close();
			}
		}

		@Override
		public boolean isStreaming(){
			return true;
		}

		public boolean isCloseDisabled(){
			return getCloseDisabled();
		}

		public boolean getCloseDisabled(){
			return closeDisabled;
		}

		public void setCloseDisabled(boolean closeDisabled){
			this.closeDisabled = closeDisabled;
		}

		@Override
		public void release(){
			doRelease();
		}

		public void close(){
			if(closeDisabled == false){
				doRelease();
			}
		}

		private void doRelease(){
			try{
				content.close();
			}catch(Exception e){
				logger.error("Unexpected io exception when trying to close input stream", e);
			}
		}
	}

}
