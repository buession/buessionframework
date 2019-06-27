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

import javax.servlet.http.HttpServletRequest;

/**
 * @author Yong.Teng
 */
public abstract class AbstractController implements Controller {

    protected final static <E> com.buession.web.mvc.Response<E> responseSuccess(){
        return responseSuccess("操作成功");
    }

    protected final static <E> com.buession.web.mvc.Response<E> responseSuccess(final String message){
        return responseSuccess(message, (E) null);
    }

    protected final static <E> com.buession.web.mvc.Response<E> responseSuccess(final E data){
        return responseSuccess("操作成功", data);
    }

    protected final static <E> com.buession.web.mvc.Response<E> responseSuccess(final String message, final E data){
        return response(true, 0, message, data);
    }

    public static <E> com.buession.web.mvc.Response<E> responseSuccess(final Pagination<E> pagination){
        return responseSuccess("数据获取成功", pagination);
    }

    @SuppressWarnings({"unchecked"})
    public static <E> com.buession.web.mvc.Response<E> responseSuccess(final String message, final Pagination<E>
            pagination){
        final Response<E> response = new Response(true, 0, message, pagination.getData());
        final Response.Pagination<E> paging = new Response.Pagination<>(pagination.getPage(), pagination.getPagesize
                (), pagination.getTotalRecords());

        pagination.setData(null);
        response.setPagination(paging);

        return response;
    }

    protected final static <E> com.buession.web.mvc.Response<E> responseFailure(final MessageObject message){
        return responseFailure(message.getCode(), message.getText());
    }

    protected static <E> com.buession.web.mvc.Response<E> responseFailure(final MessageObject message, final
    Exception e){
        return responseFailure(message.getCode(), message.getText() + "：" + e.getMessage());
    }

    protected static <E> com.buession.web.mvc.Response<E> responseFailureFormat(final MessageObject message, final
    Object... args){
        return responseFailure(message.getCode(), String.format(message.getText(), args));
    }

    protected final static <E> com.buession.web.mvc.Response<E> responseFailure(final int code){
        return responseFailure(code, "操作失败");
    }

    protected final static <E> com.buession.web.mvc.Response<E> responseFailure(final int code, final String message){
        return response(false, code, message);
    }

    protected final static <E> com.buession.web.mvc.Response<E> response(final boolean state, final int code, final
    String message){
        return new Response<>(state, code, message);
    }

    protected final static <E> com.buession.web.mvc.Response<E> response(final boolean state, final int code, final
    String message, final E data){
        return new Response<>(state, code, message, data);
    }

    protected static void pageNotFound(final HttpServletRequest request) throws PageNotFoundException{
        pageNotFound(request, request.getRequestURI());
    }

    protected static void pageNotFound(final HttpServletRequest request, final String uri) throws PageNotFoundException{
        throw new PageNotFoundException(uri);
    }

}
