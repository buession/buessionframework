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
package com.buession.web.mvc.controller;

import com.buession.core.Pagination;
import com.buession.core.codec.MessageObject;
import com.buession.web.exception.PageNotFoundException;
import com.buession.web.mvc.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yong.Teng
 */
public abstract class AbstractController implements Controller {

    protected <E> Response<E> responseSuccess(){
        return responseSuccess("");
    }

    protected <E> Response<E> responseSuccess(final String message){
        return responseSuccess(message, (E) null);
    }

    protected <E> Response<E> responseSuccess(final E data){
        return responseSuccess("", data);
    }

    protected <E> Response<E> responseSuccess(final String message, final E data){
        return response(true, 0, message, data);
    }

    protected <E> Response<E> responseSuccess(final Pagination<E> pagination){
        return responseSuccess("", pagination);
    }

    @SuppressWarnings({"unchecked"})
    protected <E> Response<E> responseSuccess(final String message, final Pagination<E> pagination){
        final Response<E> response = new Response(true, 0, message, pagination.getData());
        final Pagination<E> paging = new Pagination<>(pagination.getPage(), pagination.getPagesize(), pagination
                .getTotalRecords());

        response.setPagination(paging);

        return response;
    }

    protected <E> Response<E> responseFailure(final MessageObject message){
        return responseFailure(message.getCode(), message.getText());
    }

    protected <E> Response<E> responseFailure(final MessageObject message, final Exception e){
        StringBuffer sb = new StringBuffer();

        sb.append(message.getText()).append(": ").append(e.getMessage());

        return responseFailure(message.getCode(), sb.toString());
    }

    protected <E> Response<E> responseFailureFormat(final MessageObject message, final Object... args){
        return responseFailure(message.getCode(), String.format(message.getText(), args));
    }

    protected <E> Response<E> responseFailure(final int code){
        return responseFailure(code, "");
    }

    protected <E> Response<E> responseFailure(final int code, final String message){
        return response(false, code, message);
    }

    protected <E> Response<E> response(final boolean state, final int code, final String message){
        return new Response<>(state, code, message);
    }

    protected <E> Response<E> response(final boolean state, final int code, final String message, final E data){
        return new Response<>(state, code, message, data);
    }

    protected void pageNotFound(final HttpServletRequest request) throws PageNotFoundException{
        pageNotFound(request, request.getRequestURI());
    }

    protected void pageNotFound(final HttpServletRequest request, final String uri) throws PageNotFoundException{
        throw new PageNotFoundException(uri);
    }

}
