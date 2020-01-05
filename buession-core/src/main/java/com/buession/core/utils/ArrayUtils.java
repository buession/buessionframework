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
package com.buession.core.utils;

import com.buession.lang.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 数组工具类
 *
 * @author Yong.Teng
 */
public class ArrayUtils {

    public final static String DEFAULT_GLUE = ", ";

    private ArrayUtils(){

    }

    /**
     * 将 O 型数组拼接成字符串
     *
     * @param a
     *         需要拼接的数组
     * @param <O>
     *         类
     *
     * @return 拼接后的字符串
     */
    public static <O> String toString(O[] a){
        return toString(a, DEFAULT_GLUE);
    }

    /**
     * 将 O 型数组拼接成字符串
     *
     * @param a
     *         需要拼接的数组
     * @param glue
     *         拼接字符串
     * @param <O>
     *         类
     *
     * @return 拼接后的字符串
     */
    public static <O> String toString(final O[] a, final String glue){
        if(a == null){
            return null;
        }

        return a.length == 0 ? Constants.EMPTY_STRING : Arrays.asList(a).stream().map(v->v.toString()).collect
                (Collectors.joining(glue));
    }

    /**
     * 将数组转换为 List
     *
     * @param a
     *         需要转换的数组
     * @param <O>
     *         类
     *
     * @return 转换结果
     */
    public final static <O> List<O> toList(O[] a){
        return a == null ? null : Arrays.asList(a);
    }

    /**
     * 将数组转换为 Set
     *
     * @param a
     *         需要转换的数组
     * @param <O>
     *         类
     *
     * @return 转换结果
     */
    public final static <O> Set<O> toSet(O[] a){
        return a == null ? null : Arrays.asList(a).stream().collect(Collectors.toSet());
    }

}