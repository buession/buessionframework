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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.httpcomponents;

import com.buession.core.utils.Assert;
import com.buession.httpclient.core.ContentType;
import org.apache.http.entity.AbstractHttpEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Yong.Teng
 */
public class RepeatableInputStreamEntity extends InputStreamEntity {

    private NoAutoClosedInputStreamEntity innerEntity;

    public RepeatableInputStreamEntity(InputStream content, long contentLength, ContentType contentType){
        super(content, contentLength, contentType);

        setChunked(false);

        innerEntity = new NoAutoClosedInputStreamEntity(content, contentLength);
        innerEntity.setContentType(contentType.toString());
    }

    @Override
    public boolean isChunked(){
        return false;
    }

    @Override
    public boolean isRepeatable(){
        return getContent().markSupported() || innerEntity.isRepeatable();
    }

    @Override
    public void writeTo(OutputStream output) throws IOException{
        if(isFirstAttempt() == false && isRepeatable()){
            getContent().reset();
        }

        setFirstAttempt(false);
        innerEntity.writeTo(output);
    }

    public static class NoAutoClosedInputStreamEntity extends AbstractHttpEntity {

        private final static int BUFFER_SIZE = 2048;

        private final long length;

        private final InputStream content;

        public NoAutoClosedInputStreamEntity(final InputStream inputStream, long length){
            super();

            Assert.isNull(inputStream, "Source input stream may not be null");
            this.content = inputStream;
            this.length = length;
        }

        @Override
        public boolean isRepeatable(){
            return false;
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
        public void writeTo(final OutputStream outStream) throws IOException{
            Assert.isNull(outStream, "Output stream may not be null.");

            InputStream inStream = content;
            byte[] buffer = new byte[BUFFER_SIZE];
            int l;

            if(this.length < 0){
                // consume until EOF
                while((l = inStream.read(buffer)) != -1){
                    outStream.write(buffer, 0, l);
                }
            }else{
                // consume no more than length
                long remaining = this.length;

                while(remaining > 0){
                    l = inStream.read(buffer, 0, (int) Math.min(BUFFER_SIZE, remaining));
                    if(l == -1){
                        break;
                    }

                    outStream.write(buffer, 0, l);
                    remaining -= l;
                }
            }
        }

        @Override
        public boolean isStreaming(){
            return true;
        }
    }
}
