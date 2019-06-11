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
package com.buession.httpclient.exception;

/**
 * @author Yong.Teng
 */
public class HttpPageNotFoundException extends RequestException {

    public HttpPageNotFoundException(){
        super();
    }

    public HttpPageNotFoundException(String url){
        super("The requested URL " + url + " was not found on this server.");
    }

    public HttpPageNotFoundException(String url, Throwable cause){
        super("The requested URL " + url + " was not found on this server.", cause);
    }

    public HttpPageNotFoundException(Throwable cause){
        super(cause);
    }

    public HttpPageNotFoundException(String url, Throwable cause, boolean enableSuppression, boolean
            writableStackTrace){
        super("The requested URL " + url + " was not found on this server.", cause, enableSuppression,
                writableStackTrace);
    }
}
