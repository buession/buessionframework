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

import com.buession.httpclient.core.ChunkedInputStreamRequestBody;
import com.buession.httpclient.core.EncodedFormRequestBody;
import com.buession.httpclient.core.ObjectFormRequestBody;
import com.buession.httpclient.core.RepeatableInputStreamRequestBody;
import com.buession.httpclient.core.RequestBody;
import com.buession.httpclient.helper.AbstractRequestBuilder;
import com.buession.httpclient.helper.RequestBuilder;
import com.buession.httpclient.httpcomponents.convert.ChunkedInputStreamRequestBodyConvert;
import com.buession.httpclient.httpcomponents.convert.EncodedFormRequestBodyConvert;
import com.buession.httpclient.httpcomponents.convert.ObjectRequestBodyConvert;
import com.buession.httpclient.httpcomponents.convert.RepeatableInputStreamRequestBodyConvert;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpRequestBase;

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

    public final static HttpEntity buildRequestBody(RequestBody data){
        if(data == null){
            return null;
        }

        if(data instanceof EncodedFormRequestBody){
            EncodedFormRequestBodyConvert convert = new EncodedFormRequestBodyConvert();
            return convert.convert((EncodedFormRequestBody) data);
        }else if(data instanceof ChunkedInputStreamRequestBody){
            ChunkedInputStreamRequestBodyConvert convert = new ChunkedInputStreamRequestBodyConvert();
            return convert.convert((ChunkedInputStreamRequestBody) data);
        }else if(data instanceof RepeatableInputStreamRequestBody){
            RepeatableInputStreamRequestBodyConvert convert = new RepeatableInputStreamRequestBodyConvert();
            return convert.convert((RepeatableInputStreamRequestBody) data);
        }else if(data instanceof ObjectFormRequestBody){
            ObjectRequestBodyConvert convert = new ObjectRequestBodyConvert();
            return convert.convert((ObjectFormRequestBody) data);
        }

        return null;
    }

}
