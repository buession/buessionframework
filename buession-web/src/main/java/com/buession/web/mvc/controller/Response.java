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

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class Response<E> extends com.buession.web.mvc.Response<E> {

    public Response(){
        super();
    }

    public Response(boolean state){
        super(state);
    }

    public Response(boolean state, int code){
        super(state, code);
    }

    public Response(boolean state, int code, String message){
        super(state, code, message);
    }

    public Response(boolean state, String message){
        super(state, message);
    }

    public Response(boolean state, int code, String message, E data){
        super(state, code, message, data);
    }

    public Response(boolean state, String message, E data){
        super(state, message, data);
    }

    public Response(boolean state, int code, String message, E data, Response.Pagination<E> pagination){
        super(state, code, message, data);
        setPagination(pagination);
    }

    public Response(boolean state, String message, E data, Response.Pagination<E> pagination){
        super(state, message, data);
        setPagination(pagination);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Override
    public E getData(){
        return super.getData();
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Override
    public com.buession.core.Pagination<E> getPagination(){
        return super.getPagination();
    }

    public final static class Pagination<E> extends com.buession.core.Pagination<E> {

        public Pagination(){
            super();
        }

        public Pagination(int page, int pagesize){
            super(page, pagesize);
        }

        public Pagination(int page, int pagesize, long totalRecords){
            super(page, pagesize, totalRecords);
        }

        @JsonInclude(JsonInclude.Include.NON_NULL)
        @Override
        public List<E> getData(){
            return super.getData();
        }

    }

}
