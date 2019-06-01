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

import com.buession.httpclient.core.ContentType;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.HttpHeader;
import com.buession.httpclient.core.RequestBodyElement;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.helper.AbstractRequestBuilder;
import com.buession.httpclient.helper.RequestBuilder;
import okhttp3.FormBody;
import okhttp3.MediaType;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class OkHttpRequestBuilder extends AbstractRequestBuilder {

    public final static RequestBuilder create(){
        return new OkHttpRequestBuilder();
    }

    public final static RequestBuilder create(okhttp3.Request request){
        final RequestBuilder requestBuilder = new OkHttpRequestBuilder();
        return requestBuilder;
    }

    public final static okhttp3.RequestBody buildRequestBody(List<Header> headers, RequestBody data){
        String contentType = null;

        if(headers != null){
            for(Header header : headers){
                if(HttpHeader.CONTENT_TYPE.equalsIgnoreCase(header.getName())){
                    contentType = header.getValue();
                }
            }
        }

        if(data != null){
            if(ContentType.APPLICATION_JSON.getMimeType().equalsIgnoreCase(contentType) || ContentType.TEXT_JSON
                    .getMimeType().equalsIgnoreCase(contentType)){
                return okhttp3.RequestBody.create(MediaType.parse(ContentType.APPLICATION_JSON.valueOf()),
                        nameValuePair2JSONString(data));
            }else if(ContentType.TEXT_PLAIN.getMimeType().equalsIgnoreCase(contentType)){
                return okhttp3.RequestBody.create(MediaType.parse(ContentType.TEXT_PLAIN.valueOf()),
                        nameValuePair2JSONString(data));
            }else{
                return parseFormBody(data);
            }
        }else{
            return new FormBody.Builder().build();
        }
    }

    private static FormBody parseFormBody(final RequestBody requestBody){
        if(requestBody == null){
            return null;
        }

        final List<RequestBodyElement> data = requestBody.build();
        final FormBody.Builder formBodyBuilder = new FormBody.Builder();

        for(RequestBodyElement requestBodyElement : data){
            String value = requestBodyElement.getValue() == null ? "" : String.valueOf(requestBodyElement.getValue());

            formBodyBuilder.add(requestBodyElement.getName(), value);
        }

        return formBodyBuilder.build();
    }

}
