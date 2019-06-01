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

import com.buession.core.utils.Assert;

/**
 * @author Yong.Teng
 */
public class StatusLine {

    private int statusCode;

    private String statusText;

    public StatusLine(){
    }

    public StatusLine(int statusCode, String statusText){
        this.statusCode = statusCode;
        this.statusText = statusText;
    }

    public int getStatusCode(){
        return statusCode;
    }

    public void setStatusCode(final int statusCode){
        Assert.isNegative(statusCode, "HTTP response status code cloud not be negative.");
        this.statusCode = statusCode;
    }

    public String getStatusText(){
        return statusText;
    }

    public void setStatusText(final String statusText){
        Assert.isBlank(statusText, "HTTP response status text cloud not be null or empty.");
        this.statusText = statusText;
    }

}
