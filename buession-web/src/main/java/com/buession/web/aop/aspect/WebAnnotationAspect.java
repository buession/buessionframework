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
package com.buession.web.aop.aspect;

/**
 * @author Yong.Teng
 */
public interface WebAnnotationAspect {

    String CONTENT_TYPE_EXPRESSION = "execution(@com.buession.web.http.response.ContentType * *(..))";

    String DISABLE_HTTP_CACHE_EXPRESSION = "execution(@com.buession.web.http.response.DisableHttpCache * *(..))";

    String ENABLE_HTTP_CACHE_EXPRESSION = "execution(@com.buession.web.http.response.EnableHttpCache * *(..))";

    String PRIMITIVE_CROSS_ORIGIN_EXPRESSION = "execution(@com.buession.web.http.response.PrimitiveCrossOrigin * *" +
            "(..))";

    String RESPONSE_HEADER_EXPRESSION = "execution(@com.buession.web.http.response.ResponseHeader * *(..))";

    String RESPONSE_HEADERS_EXPRESSION = "execution(@com.buession.web.http.response.ResponseHeaders * *(..))";

    String DOCUMENT_META_DATA_EXPRESSION = "execution(@com.buession.web.mvc.view.document.DocumentMetaData * *(..))";

    String EXPRESSIONS = CONTENT_TYPE_EXPRESSION + " || " + DISABLE_HTTP_CACHE_EXPRESSION + " || " +
            ENABLE_HTTP_CACHE_EXPRESSION + " || " + PRIMITIVE_CROSS_ORIGIN_EXPRESSION + " || " +
            RESPONSE_HEADER_EXPRESSION + " || " + RESPONSE_HEADERS_EXPRESSION + " || " + DOCUMENT_META_DATA_EXPRESSION;

}
