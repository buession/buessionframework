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
package com.buession.web.reactive.mvc.controller;

import com.buession.web.mvc.controller.AbstractRestController;
import com.buession.web.mvc.controller.Response;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Yong.Teng
 */
public abstract class AbstractBasicRestController<P, E> extends AbstractRestController {

    @RequestMapping(path = "", method = RequestMethod.POST)
    public com.buession.web.mvc.Response add(ServerHttpRequest request, ServerHttpResponse response, @RequestBody E e){
        return pageNotFound(request, response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public com.buession.web.mvc.Response edit(ServerHttpRequest request, ServerHttpResponse response, @PathVariable
            (name = "id") P id, @RequestBody E e){
        return pageNotFound(request, response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public com.buession.web.mvc.Response detail(ServerHttpRequest request, ServerHttpResponse response, @PathVariable
            (name = "id") P id){
        return pageNotFound(request, response);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public com.buession.web.mvc.Response delete(ServerHttpRequest request, ServerHttpResponse response, @PathVariable
            (name = "id") P id){
        return pageNotFound(request, response);
    }

    protected com.buession.web.mvc.Response pageNotFound(final ServerHttpRequest request, final ServerHttpResponse
            response){
        return new Response(false, PAGE_NOT_FOUND_ERROR_CODE, request.getPath().toString());
    }

}
