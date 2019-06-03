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
package com.buession.httpclient.okhttp.convert;

import com.buession.httpclient.core.EncodedFormRequestBody;
import com.buession.httpclient.core.RequestBodyConvert;
import com.buession.httpclient.core.RequestBodyElement;
import okhttp3.FormBody;

/**
 * @author Yong.Teng
 */
public class EncodedFormRequestBodyConvert implements RequestBodyConvert<EncodedFormRequestBody, FormBody> {

    @Override
    public FormBody convert(EncodedFormRequestBody source){
        if(source.getContent() == null){
            return null;
        }

        final FormBody.Builder formBodyBuilder = new FormBody.Builder();

        for(RequestBodyElement requestBodyElement : source.getContent()){
            String value = requestBodyElement.getValue() == null ? "" : requestBodyElement.getValue().toString();

            formBodyBuilder.add(requestBodyElement.getName(), value);
        }

        return formBodyBuilder.build();
    }
}
