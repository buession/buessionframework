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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class RequestBody {

    private List<RequestBodyElement> data = new ArrayList<>();

    public final static RequestBody create(){
        return new RequestBody();
    }

    public RequestBody addRequestBodyElement(RequestBodyElement requestBodyElement){
        data.add(requestBodyElement);
        return this;
    }

    public RequestBody addRequestBodyElement(String name, String value){
        data.add(new RequestBodyElement(name, value));
        return this;
    }

    public List<RequestBodyElement> build(){
        return data;
    }

    public Map<String, Object> toMap(){
        if(data == null){
            return null;
        }

        Map<String, Object> result = new LinkedHashMap<>(data.size());

        for(RequestBodyElement requestBodyElement : data){
            result.put(requestBodyElement.getName(), requestBodyElement.getValue());
        }

        return result;
    }
}
