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
 * | Copyright @ 2013-2018 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.web.mvc.view.document;

import java.io.Serializable;

/**
 * 页面元数据
 *
 * @author Yong.Teng
 */
public class MeteData implements Serializable {

    private static final long serialVersionUID = -2362098929099645692L;

    /**
     * 标题
     */
    private String title;

    /**
     * 编码
     */
    private String charset;

    /**
     * 关键字
     */
    private String keywords;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 作者
     */
    private String author;

    /**
     * 版权
     */
    private String copyright;

    /**
     * 返回页面标题
     *
     * @return 页面标题
     */
    public String getTitle(){
        return title;
    }

    /**
     * 设置页面标题
     *
     * @param title
     *         标题
     */
    public void setTitle(String title){
        this.title = title;
    }

    /**
     * 返回页面编码
     *
     * @return 页面编码
     */
    public String getCharset(){
        return charset;
    }

    /**
     * 设置页面编码
     *
     * @param charset
     *         编码
     */
    public void setCharset(String charset){
        this.charset = charset;
    }

    /**
     * 返回页面关键字
     *
     * @return 页面关键字
     */
    public String getKeywords(){
        return keywords;
    }

    /**
     * 设置页面关键字
     *
     * @param keywords
     *         关键字
     */
    public void setKeywords(String keywords){
        this.keywords = keywords;
    }

    /**
     * 返回页面描述信息
     *
     * @return 页面描述信息
     */
    public String getDescription(){
        return description;
    }

    /**
     * 设置页面描述信息
     *
     * @param description
     *         描述信息
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     * 返回页面作者
     *
     * @return 页面作者
     */
    public String getAuthor(){
        return author;
    }

    /**
     * 设置页面作者
     *
     * @param author
     *         作者
     */
    public void setAuthor(String author){
        this.author = author;
    }

    /**
     * 返回页面版权
     *
     * @return 页面版权
     */
    public String getCopyright(){
        return copyright;
    }

    /**
     * 设置页面版权信息
     *
     * @param copyright
     *         版权信息
     */
    public void setCopyright(String copyright){
        this.copyright = copyright;
    }
}
