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

import com.buession.httpclient.core.ProtocolVersion;
import com.buession.httpclient.helper.AbstractResponseBuilder;
import com.buession.httpclient.helper.ResponseBuilder;
import okhttp3.Headers;
import okhttp3.Protocol;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class OkHttpResponseBuilder extends AbstractResponseBuilder {

    private final static Logger logger = LoggerFactory.getLogger(OkHttpResponseBuilder.class);

    public final static ResponseBuilder create(){
        return new OkHttpResponseBuilder();
    }

    public final static ResponseBuilder create(okhttp3.Response httpResponse){
        final ResponseBuilder responseBuilder = new OkHttpResponseBuilder().setStatusCode(httpResponse.code())
                .setStatusText(httpResponse.message());

        String protocol = httpResponse.protocol().toString();
        String[] temp = protocol.split("/");
        String protocolName = temp[0];
        int majorVersion = 0;
        int minorVersion = 0;

        if(temp.length > 2){
            String[] versionTemp = temp[1].split(".");

            majorVersion = Integer.parseInt(versionTemp[0]);
            if(versionTemp.length > 2){
                minorVersion = Integer.parseInt(versionTemp[1]);
            }
        }

        responseBuilder.setProtocolVersion(ProtocolVersion.createInstance(protocolName, majorVersion, minorVersion));

        Headers responseHeaders = httpResponse.headers();
        if(responseHeaders != null){
            final Map<String, String> headersMap = new LinkedHashMap<>();

            for(String name : responseHeaders.names()){
                String value = headersMap.get(name);

                if(value == null){
                    headersMap.put(name, responseHeaders.get(name));
                }else{
                    headersMap.put(name, value + ", " + responseHeaders.get(name));
                }
            }

            responseBuilder.setHeaders(headersMap2List(headersMap));
        }


        final ResponseBody responseBody = httpResponse.body();

        responseBuilder.setContentLength(responseBody.contentLength());
        responseBuilder.setInputStream(responseBody.byteStream());
        try{
            responseBuilder.setBody(responseBody.string());
        }catch(IOException e){
            logger.error("Response entity to body error.", e);
        }

        httpResponse.close();

        return responseBuilder;
    }

}
