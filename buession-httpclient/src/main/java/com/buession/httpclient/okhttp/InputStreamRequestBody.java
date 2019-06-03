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
package com.buession.httpclient.okhttp;

import com.buession.core.utils.Assert;
import com.buession.httpclient.core.ContentType;
import okhttp3.MediaType;
import okio.BufferedSink;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Yong.Teng
 */
public class InputStreamRequestBody extends okhttp3.RequestBody {

    protected static final int OUTPUT_BUFFER_SIZE = 4096;

    private InputStream content;

    private ContentType contentType;

    private long contentLength;

    private boolean firstAttempt = true;

    InputStreamRequestBody(InputStream content, long contentLength, ContentType contentType){
        this.content = content;
        this.contentType = contentType;
        this.contentLength = contentLength;
    }

    public boolean isFirstAttempt(){
        return firstAttempt;
    }

    public void setFirstAttempt(boolean firstAttempt){
        this.firstAttempt = firstAttempt;
    }

    @Nullable
    @Override
    public MediaType contentType(){
        return MediaType.parse(contentType.toString());
    }

    public InputStream getContent(){
        return content;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException{
        Assert.isNull(sink, "Buffered sink cloud not be null");

        final InputStream inStream = content;
        try{
            final byte[] tmp = new byte[OUTPUT_BUFFER_SIZE];
            int l;

            while((l = inStream.read(tmp)) != -1){
                sink.outputStream().write(tmp, 0, l);
            }
        }finally{
            inStream.close();
        }
    }

    public boolean isChunked(){
        return false;
    }

    public boolean isRepeatable(){
        return content.markSupported();
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

        public InputStreamRequestBody build(){
            return new InputStreamRequestBody(content, contentLength, contentType);
        }

    }

}
