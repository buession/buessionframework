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

import com.buession.httpclient.core.ContentType;
import com.buession.httpclient.core.Header;
import com.buession.httpclient.core.RequestBodyElement;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.helper.AbstractRequestBuilder;
import com.buession.httpclient.helper.RequestBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class HttpComponentsRequestBuilder extends AbstractRequestBuilder {

    public final static RequestBuilder create(){
        return new HttpComponentsRequestBuilder();
    }

    public final static RequestBuilder create(final HttpRequestBase httpRequest){
        RequestBuilder requestBuilder = new HttpComponentsRequestBuilder();
        return requestBuilder;
    }

    public final static HttpEntity buildRequestBody(List<Header> headers, RequestBody data){
        String contentType = null;

        if(headers != null){
            for(Header header : headers){
                if(HTTP.CONTENT_TYPE.equalsIgnoreCase(header.getName())){
                    contentType = header.getValue();
                }
            }
        }

        if(data != null){
            if(ContentType.APPLICATION_JSON.getMimeType().equalsIgnoreCase(contentType) || ContentType.TEXT_JSON
                    .getMimeType().equalsIgnoreCase(contentType)){
                return new StringEntity(nameValuePair2JSONString(data), org.apache.http.entity.ContentType
                        .APPLICATION_JSON);
            }else if(ContentType.TEXT_PLAIN.getMimeType().equalsIgnoreCase(contentType)){
                return new StringEntity(nameValuePair2JSONString(data), org.apache.http.entity.ContentType.TEXT_PLAIN);
            }else{
                return new UrlEncodedFormEntity(parseBasicNameValuePair(data), Charset.defaultCharset());
            }
        }

        return null;
    }

    private final static List<org.apache.http.NameValuePair> parseBasicNameValuePair(final RequestBody requestBody){
        if(requestBody == null){
            return null;
        }

        final List<RequestBodyElement> data = requestBody.build();
        final List<org.apache.http.NameValuePair> result = new ArrayList<>(data.size());

        for(RequestBodyElement requestBodyElement : data){
            String value = requestBodyElement.getValue() == null ? "" : String.valueOf(requestBodyElement.getValue());

            result.add(new BasicNameValuePair(requestBodyElement.getName(), value));
        }

        return result;
    }

}
