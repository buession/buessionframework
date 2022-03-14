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

import com.buession.httpclient.core.RepeatableInputStreamRequestBody;
import com.buession.httpclient.core.io.Releasable;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.util.Args;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Yong.Teng
 * @since 1.2.0
 */
public class RepeatableInputStreamRequestBodyConvert
		extends BaseInputStreamRequestBodyConverter<RepeatableInputStreamRequestBody> {

	@Override
	public HttpEntity convert(final RepeatableInputStreamRequestBody source){
		if(source == null || source.getContent() == null){
			return null;
		}

		InputStreamEntity streamEntity = new ReleasableInputStreamEntity(source.getContent(), source.getContentLength(),
				ContentType.create(source.getContentType().getMimeType(), source.getContentType().getCharset()));
		return afterConvert(streamEntity);
	}

	@Override
	protected HttpEntity afterConvert(final InputStreamEntity streamEntity){
		return streamEntity;
	}

	public static class ReleasableInputStreamEntity extends InputStreamEntity implements Releasable {

		private boolean closeDisabled;

		public ReleasableInputStreamEntity(InputStream inputStream){
			super(inputStream);
		}

		public ReleasableInputStreamEntity(InputStream inputStream, long length){
			super(inputStream, length);
		}

		public ReleasableInputStreamEntity(InputStream inputStream, ContentType contentType){
			super(inputStream, contentType);
		}

		public ReleasableInputStreamEntity(InputStream inputStream, long length, ContentType contentType){
			super(inputStream, length, contentType);
			this.closeDisabled = false;
		}

		public boolean isCloseDisabled(){
			return this.closeDisabled;
		}

		public void setCloseDisabled(boolean closeDisabled){
			this.closeDisabled = closeDisabled;
		}

		@Override
		public boolean isRepeatable(){
			try{
				return getContent().markSupported();
			}catch(IOException e){
				return false;
			}
		}

		@Override
		public void writeTo(OutputStream outStream) throws IOException{
			Args.notNull(outStream, "Output stream");
			InputStream inputStream = getContent();

			try{
				byte[] buffer = new byte[4096];
				int l;

				if(getContentLength() < 0L){
					while((l = inputStream.read(buffer)) != -1){
						outStream.write(buffer, 0, l);
					}
				}else{
					for(long remaining = getContentLength(); remaining > 0L; remaining -= l){
						l = inputStream.read(buffer, 0, (int) Math.min(4096L, remaining));
						if(l == -1){
							break;
						}

						outStream.write(buffer, 0, l);
					}
				}
			}finally{
				this.close();
			}
		}

		public void close() throws IOException{
			if(closeDisabled == false){
				this.doRelease();
			}
		}

		@Override
		public void release(){
			try{
				this.doRelease();
			}catch(IOException e){

			}
		}

		private void doRelease() throws IOException{
			try{
				getContent().close();
			}catch(Exception e){
				throw new IOException("Unexpected io exception when trying to close input stream");
			}
		}
	}

}
