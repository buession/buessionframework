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
package com.buession.httpclient.core;

import java.io.InputStream;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class Response {

    private ProtocolVersion protocolVersion;

    private int statusCode;

    private String statusText;

    private StatusLine statusLine;

    private List<Header> headers;

    private InputStream inputStream;

    private String body;

    private long contentLength;

    public ProtocolVersion getProtocolVersion(){
        return protocolVersion;
    }

    public void setProtocolVersion(ProtocolVersion protocolVersion){
        this.protocolVersion = protocolVersion;
    }

    public int getStatusCode(){
        return statusCode;
    }

    public void setStatusCode(final int statusCode){
        this.statusCode = statusCode;
    }

    public String getStatusText(){
        return statusText;
    }

    public void setStatusText(final String statusText){
        this.statusText = statusText;
    }

    public StatusLine getStatusLine(){
        return statusLine;
    }

    public void setStatusLine(final StatusLine statusLine){
        this.statusLine = statusLine;
    }

    public List<Header> getHeaders(){
        return headers;
    }

    public void setHeaders(final List<Header> headers){
        this.headers = headers;
    }

    public InputStream getInputStream(){
        return inputStream;
    }

    public void setInputStream(final InputStream inputStream){
        this.inputStream = inputStream;
    }

    public String getBody(){
        return body;
    }

    public void setBody(final String body){
        this.body = body;
    }

    public long getContentLength(){
        return contentLength;
    }

    public void setContentLength(long contentLength){
        this.contentLength = contentLength;
    }

    public boolean isSuccessful(){
        return statusCode >= 200 && statusCode < 300;
    }

    @Override
    public String toString(){
        return "Response{" + "protocolVersion=" + protocolVersion + ", statusCode=" + statusCode + ", statusText='" +
                statusText + '\'' + ", statusLine=" + statusLine + ", headers=" + headers + ", inputStream=" +
                inputStream + ", body='" + body + '\'' + ", contentLength=" + contentLength + '}';
    }
}
