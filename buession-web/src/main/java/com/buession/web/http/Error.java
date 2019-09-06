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
package com.buession.web.http;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class Error implements Serializable {

    private final static long serialVersionUID = -552295330575205966L;

    private HttpStatus httpStatus;

    private int code;

    private String message;

    public Error(){
    }

    public Error(HttpStatus httpStatus, int code){
        this(httpStatus, code, httpStatus.getReasonPhrase());
    }

    public Error(HttpStatus httpStatus, int code, String message){
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getHttpStatus(){
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus){
        this.httpStatus = httpStatus;
    }

    public int getCode(){
        return code;
    }

    public void setCode(int code){
        this.code = code;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> result = new HashMap<>(4);

        result.put("state", false);
        result.put("code", getCode());
        result.put("message", getMessage());
        result.put("status", getHttpStatus());

        return result;
    }

    public final static Map<HttpStatus.Series, String> seriesViews(){
        Map<HttpStatus.Series, String> views = new EnumMap<>(HttpStatus.Series.class);

        views.put(HttpStatus.Series.CLIENT_ERROR, "4xx");
        views.put(HttpStatus.Series.SERVER_ERROR, "5xx");

        return Collections.unmodifiableMap(views);
    }

}
