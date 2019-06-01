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
package com.buession.httpclient.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class URLUtils {

    private final static Logger logger = LoggerFactory.getLogger(URLUtils.class);

    private URLUtils(){

    }

    public final static String urlEncode(String value, Charset charset) throws UnsupportedEncodingException{
        return urlEncode(value, charset.name());
    }

    public final static String urlEncode(String value, String encoding) throws UnsupportedEncodingException{
        if(value == null){
            return "";
        }

        String encoded = URLEncoder.encode(value, encoding);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < encoded.length(); i++){
            char c = encoded.charAt(i);

            if(c == '+'){
                sb.append("%20");
            }else if(c == '*'){
                sb.append("%2A");
            }else if(c == '~'){
                sb.append("%7E");
            }else if(c == '/'){
                sb.append("%2F");
            }else{
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static String parametersToQueryString(Map<String, Object> parameters, Charset charset) throws
            UnsupportedEncodingException{
        return parametersToQueryString(parameters, charset.name());
    }

    public static String parametersToQueryString(Map<String, Object> parameters, String encoding) throws
            UnsupportedEncodingException{
        if(parameters == null || parameters.isEmpty()){
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for(Map.Entry<String, Object> parameter : parameters.entrySet()){
            String key = parameter.getKey();
            Object value = parameter.getValue();

            if(first == false){
                sb.append('&');
            }

            sb.append(urlEncode(key, encoding));
            sb.append('=');

            if(value == null){
            }else if(value instanceof Character){
                sb.append(value);
            }else if(value instanceof Number){
                sb.append(value);
            }else{
                sb.append(urlEncode(value.toString(), encoding));
            }

            first = false;
        }

        return sb.toString();
    }

    public final static String determineRequestUrl(final String url, final Map<String, Object> parameters){
        final StringBuilder sb = new StringBuilder();

        sb.append(url);

        if(parameters != null && parameters.isEmpty() == false){
            try{
                final String queryString = parametersToQueryString(parameters, StandardCharsets.UTF_8);

                if(url.contains("?")){
                    if(url.endsWith("&") == false){
                        sb.append('&');
                    }
                }else{
                    sb.append('?');
                }

                sb.append(queryString);
            }catch(UnsupportedEncodingException e){
                logger.warn("Determine request url error.", e);
            }
        }

        return sb.toString();
    }

}
