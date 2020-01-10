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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.operations;

import com.buession.core.utils.ArrayUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class OperationsCommandArguments {

    private Map<String, Object> parameters = new LinkedHashMap<>();

    private OperationsCommandArguments(){

    }

    public final static OperationsCommandArguments getInstance(){
        return new OperationsCommandArguments();
    }

    public OperationsCommandArguments put(final String key, final Object value){
        parameters.put(key, value);
        return this;
    }

    public OperationsCommandArguments put(final String key, final Object... value){
        parameters.put(key, ArrayUtils.toString(value));
        return this;
    }

    public OperationsCommandArguments putAll(final OperationsCommandArguments parameters){
        if(parameters != null){
            this.parameters.putAll(parameters.getParameters());
        }

        return this;
    }

    public OperationsCommandArguments putAll(final Map<String, Object> parameters){
        if(parameters != null){
            this.parameters.putAll(parameters);
        }

        return this;
    }

    public Map<String, Object> getParameters(){
        return parameters;
    }

    public Map<String, Object> build(){
        return getParameters();
    }

}
