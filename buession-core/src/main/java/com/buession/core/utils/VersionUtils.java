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
package com.buession.core.utils;

import com.buession.core.validator.Validate;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class VersionUtils {

    private final static String NAN = "#N#";

    private final static char ZERO = '0';

    private final static Map<String, Integer> SPECIAL_VERSIONS = new LinkedHashMap<>(10);

    static{
        SPECIAL_VERSIONS.put("dev", 0);
        SPECIAL_VERSIONS.put("alpha", 1);
        SPECIAL_VERSIONS.put("a", 1);
        SPECIAL_VERSIONS.put("beta", 2);
        SPECIAL_VERSIONS.put("b", 2);
        SPECIAL_VERSIONS.put("rc", 3);
        SPECIAL_VERSIONS.put("#", 4);
        SPECIAL_VERSIONS.put("release", 4);
        SPECIAL_VERSIONS.put("pl", 5);
        SPECIAL_VERSIONS.put("p", 5);
    }

    /**
     * 版本号比较
     *
     * @param version1
     *         第一个版本号
     * @param version2
     *         第二个版本号
     *
     * @return 当 version1 &lt; version2 时，返回 -1；当 version1 = version2 时，返回 0；当 version1 &gt; version2 时返回 1
     */
    public final static int compare(final String version1, final String version2){
        char first1 = version1.charAt(0);
        char first2 = version2.charAt(0);

        if(first1 == ZERO || first2 == ZERO){
            return first1 == ZERO && first2 == ZERO ? 0 : (first2 != ZERO ? 1 : -1);
        }

        String ver1 = first1 == '#' ? version1 : canonicalizeVersion(version1);
        String ver2 = first2 == '#' ? version2 : canonicalizeVersion(version2);
        String p1 = null;
        String p2 = null;
        int result = 0;

        while(ver1 != null && ver2 != null){
            int nd1 = ver1.indexOf('.');
            int nd2 = ver2.indexOf('.');

            p1 = nd1 == -1 ? ver1 : ver1.substring(0, nd1);
            p2 = nd2 == -1 ? ver2 : ver2.substring(0, nd2);

            if(Validate.isDigit(p1) && Validate.isDigit(p2)){
                result = normalize(Integer.parseInt(p1) - Integer.parseInt(p2));
            }else if(Validate.isDigit(p1) == false && Validate.isDigit(p2) == false){
                result = compareSpecialVersion(p1, p2);
            }else{
                result = Validate.isDigit(p1.charAt(0)) ? compareSpecialVersion(NAN, p2) : compareSpecialVersion(p1,
                        NAN);
            }

            if(result != 0){
                break;
            }

            ver1 = nd1 == -1 ? null : ver1.substring(nd1 + 1);
            ver2 = nd2 == -1 ? null : ver2.substring(nd2 + 1);
        }

        if(result == 0){
            if(ver1 != null){
                result = Validate.isDigit(ver1.charAt(0)) ? 1 : compare(ver1, NAN);
            }else if(ver2 != null){
                result = Validate.isDigit(ver2.charAt(0)) ? -1 : compare(NAN, ver2);
            }
        }

        return result;
    }

    private final static String canonicalizeVersion(final String version){
        if(version == null || version.length() == 0){
            return version;
        }

        int length = version.length();
        StringBuffer sb = new StringBuffer(length + 4);

        for(int i = 0; i < length; i++){
            char c = version.charAt(i);
            char next = i + 1 < length ? version.charAt(i + 1) : '\0';

            if(isSpecialVer(c)){ // replace "-","_","+" to "."
                sb.append('.');
            }else if(next != '\0' && (Validate.isDigit(c) && Validate.isDigit(next) == false || Validate.isDigit(c)
                    == false && Validate.isDigit(next))){ // Insert '.' before and after a non-digit
                sb.append(c);

                if(c != '.' && next != '.'){
                    sb.append('.');
                }
            }else if(Validate.isAlnum(c) == false){ // Non-letters and numbers
                sb.append('.');
            }else{
                sb.append(c);
            }
        }

        return sb.toString();
    }

    private final static int compareSpecialVersion(final String version1, final String version2){
        int found1 = -1;
        int found2 = -1;

        for(Map.Entry<String, Integer> e : SPECIAL_VERSIONS.entrySet()){
            String ver = e.getKey();
            int order = e.getValue();

            if(StringUtils.equals(ver, version1, ver.length())){
                found1 = order;
            }

            if(StringUtils.equals(ver, version2, ver.length())){
                found2 = order;
            }
        }

        return normalize(found1 - found2);
    }

    private final static boolean isSpecialVer(final char c){
        return c == '-' || c == '_' || c == '+';
    }

    private final static int normalize(final int value){
        return value == 0 ? 0 : (value < 0 ? -1 : 1);
    }

}
