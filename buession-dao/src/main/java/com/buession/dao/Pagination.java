/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * =================================================================================================
 *
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 * +------------------------------------------------------------------------------------------------+
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2019 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.dao;

/**
 * 分页类
 *
 * @author Yong.Teng
 */
public class Pagination<E> extends com.buession.core.Pagination<E> {

    /**
     * 偏移量
     */
    private Integer offset;

    /**
     * Constructs with default configuration.
     */
    public Pagination(){
    }

    /**
     * Constructs with page and pagesize.
     *
     * @param page
     *         当前页码
     * @param pagesize
     *         每页大小
     */
    public Pagination(int page, int pagesize){
        super(page, pagesize);
    }

    /**
     * Constructs with page, pagesize and totalRecords.
     *
     * @param page
     *         当前页码
     * @param pagesize
     *         每页大小
     * @param totalRecords
     *         总记录数
     */
    public Pagination(int page, int pagesize, long totalRecords){
        super(page, pagesize, totalRecords);
    }

    /**
     * 返回查询偏移量
     *
     * @return 查询偏移量
     */
    public int getOffset(){
        if(offset == null){
            if(getPage() > 1){
                setOffset((getPage() - 1) * getPagesize());
            }else{
                offset = 0;
            }
        }

        return offset;
    }

    /**
     * 设置查询偏移量
     *
     * @param offset
     *         查询偏移量
     */
    public void setOffset(int offset){
        this.offset = offset < 0 ? 0 : offset;
    }

}
