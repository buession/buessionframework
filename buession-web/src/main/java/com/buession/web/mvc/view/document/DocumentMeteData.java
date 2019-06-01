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

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Yong.Teng
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DocumentMeteData {

    /**
     * 返回注册 Model attribute 名字
     *
     * @return 注册 Model attribute 名字
     */
    String attrName() default "";

    /**
     * 返回页面标题
     *
     * @return 页面标题
     */
    String title() default "";

    /**
     * 返回页面编码
     *
     * @return 页面编码
     */
    String charset() default "";

    /**
     * 返回页面关键字
     *
     * @return 页面关键字
     */
    String keywords() default "";

    /**
     * 返回页面描述信息
     *
     * @return 页面描述信息
     */
    String description() default "";

    /**
     * 返回页面作者
     *
     * @return 页面作者
     */
    String author() default "";

    /**
     * 返回页面版权
     *
     * @return 页面版权
     */
    String copyright() default "";

}
