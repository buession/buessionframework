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

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author Yong.Teng
 */
public final class ContentType {

    public final static ContentType APPLICATION_OBJECT_STREAM = new ContentType("application/octet-stream",
            StandardCharsets.ISO_8859_1);

    public final static ContentType APPLICATION_JSON = new ContentType("application/json", StandardCharsets.UTF_8);

    public final static ContentType TEXT_JSON = new ContentType("text/json", StandardCharsets.UTF_8);

    public final static ContentType TEXT_PLAIN = new ContentType("text/plain", Charset.defaultCharset());

    public final static ContentType APPLICATION_FORM_URLENCODED = new ContentType
            ("application/x-www-form-urlencoded", StandardCharsets.ISO_8859_1);

    public final static ContentType MULTIPART_FORM_DATA = new ContentType("multipart/form-data", StandardCharsets
            .ISO_8859_1);

    private String mimeType;

    private Charset charset;

    public ContentType(String mimeType){
        this.mimeType = mimeType;
    }

    public ContentType(String mimeType, Charset charset){
        this.mimeType = mimeType;
        this.charset = charset;
    }

    public String getMimeType(){
        return mimeType;
    }

    public Charset getCharset(){
        return charset;
    }

    public String valueOf(){
        return valueOf(this);
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer(mimeType.length() + 16);

        sb.append(mimeType);

        if(charset != null){
            sb.append("; charset=");
            sb.append(charset.name());
        }

        return sb.toString();
    }

    public final static String valueOf(ContentType contentType){
        if(contentType == null){
            return null;
        }

        StringBuffer sb = new StringBuffer(contentType.getMimeType().length() + 16);

        sb.append(contentType.getMimeType());

        if(contentType.getCharset() != null){
            sb.append("; charset=");
            sb.append(contentType.getCharset().name());
        }

        return sb.toString();
    }

}
