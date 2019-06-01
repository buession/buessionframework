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
 * | Copyright @ 2013-2017 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils;

import java.util.Random;

/**
 * 字符串工具
 *
 * @author Yong.Teng
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private final static char[] CHARS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
            'p', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
            'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', '0'};

    /**
     * 截取字符串
     *
     * @param str
     *         原始字符串
     * @param beginIndex
     *         开始位置
     *
     * @return 字符串的子串
     */
    public static String substr(final String str, int beginIndex){
        Assert.isNull(str, "String could not be null.");

        if(beginIndex < 0){
            beginIndex = str.length() + beginIndex;
        }

        return str.substring(beginIndex);
    }

    /**
     * 截取字符串
     *
     * @param str
     *         原始字符串
     * @param beginIndex
     *         开始位置
     * @param length
     *         截取长度
     *
     * @return 字符串的子串
     */
    public static String substr(final String str, int beginIndex, final int length){
        Assert.isNull(str, "String could not be null.");

        if(length < 0){
            throw new IllegalArgumentException("Length could not be negative.");
        }

        if(beginIndex < 0){
            beginIndex = str.length() + beginIndex;
        }

        return length == 0 ? "" : new String(str.toCharArray(), beginIndex, length);
    }

    /**
     * 检测字符串是否为布尔 True
     *
     * @param str
     *         检测字符串
     *
     * @return 否为布尔 True
     */
    public static boolean isTrue(final String str){
        return Boolean.parseBoolean(str) || "1".equals(str) || "yes".equalsIgnoreCase(str);
    }

    /**
     * 检测字符串是否为布尔 False
     *
     * @param str
     *         检测字符串
     *
     * @return 否为布尔 False
     */
    public static boolean isFalse(final String str){
        return Boolean.parseBoolean(str) == false || "".equals(str) || "0".equals(str) || "no".equalsIgnoreCase(str);
    }

    /**
     * 生成随机字符串
     *
     * @param length
     *         随机字符串长度
     *
     * @return 生成的随机字符串
     */
    public static String random(final int length){
        if(length < 0){
            throw new IllegalArgumentException("length could not be negative.");
        }else if(length == 0){
            return "";
        }else{
            StringBuffer sb = new StringBuffer(length);
            Random random = new Random();

            for(int i = 0; i < length; i++){
                int j = random.nextInt(CHARS.length);

                sb.append(CHARS[j]);
            }

            return sb.toString();
        }
    }

}