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
 * | License: http://buession.buession.com.cn/LICENSE 												|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2019 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.validator.routines;

import com.buession.core.utils.StringUtils;
import com.buession.core.validator.Validate;
import com.buession.lang.IpType;

/**
 * IP 验证器
 *
 * @author Yong.Teng
 */
public class IpValidator {

    private final static int IPV4_MIN_LENGTH = 7;

    private final static int IPV4_MAX_LENGTH = 15;

    private final static int IPV6_MIN_GROUP_SIZE = 2;

    private final static int IPV6_MAX_GROUP_SIZE = 8;

    private IpValidator(){

    }

    public final static boolean isValid(final CharSequence charSequence){
        if(charSequence == null){
            return false;
        }

        String str = charSequence.toString();
        return isIpv4(str) || isIpv6(str);
    }

    public final static boolean isValid(final CharSequence charSequence, final IpType type){
        if(charSequence == null){
            return false;
        }

        if(IpType.IP_V4.equals(type) == true){
            return isIpv4(charSequence.toString());
        }else if(IpType.IP_V6.equals(type) == true){
            return isIpv6(charSequence.toString());
        }else{
            return false;
        }
    }

    private final static boolean isIpv4(final String str){
        if(str == null){
            return false;
        }

        int len = str.length();
        if(len < IPV4_MIN_LENGTH || len > IPV4_MAX_LENGTH){
            return false;
        }

        if("0.0.0.0".equals(str)){
            return true;
        }

        String[] groups = StringUtils.split(str, '.');
        if(groups == null || groups.length != 4){
            return false;
        }

        for(String group : groups){
            if(IPV4_group_valid(group) == false){
                return false;
            }
        }

        return true;
    }

    private final static boolean IPV4_group_valid(final String str){
        if(str == null){
            return false;
        }

        char c;
        switch(str.length()){
            case 1:
                c = str.charAt(0);
                return c >= '0' && c <= '9';
            case 2:
                c = str.charAt(0);
                if(c < '1' || c > '9'){
                    return false;
                }

                c = str.charAt(1);
                return c >= '0' && c <= '9';
            case 3:
                c = str.charAt(0);
                if(c == '1'){
                    return Validate.isNumeric(str.substring(1));
                }else if(c == '2'){
                    c = str.charAt(1);
                    if(c > '5'){
                        return false;
                    }else if(c == '5'){
                        c = str.charAt(2);
                        if(c < '0' || c > '5'){
                            return false;
                        }
                    }else{
                        c = str.charAt(2);
                        if(c < '0' || c > '9'){
                            return false;
                        }
                    }

                    return true;
                }else{
                    return false;
                }
            default:
                return false;
        }
    }

    private final static boolean isIpv6(final String str){
        if(str == null){
            return false;
        }

        if("::".equals(str) == true || "::1".equals(str) == true){
            return true;
        }

        // 首选格式：xxxx.xxxx.xxxx.xxxx.xxxx.xxxx.xxxx.xxxx
        // 压缩格式："::" 只能出现一次
        // 内嵌 IPV4：X:X:X:X:X:X:d.d.d.d
        int dotIndex = str.indexOf('.');
        if(dotIndex > -1){
            int colonIndex = str.lastIndexOf(':');

            return isIpv6(str.substring(0, colonIndex + 1), 1, 6) && isIpv4(str.substring(colonIndex + 1));
        }else{
            return isIpv6(str, IPV6_MIN_GROUP_SIZE, IPV6_MAX_GROUP_SIZE);
        }
    }

    private final static boolean isIpv6(final String str, final int minGroup, final int maxGroup){
        if("::".equals(str)){
            return true;
        }

        String[] groups = StringUtils.splitByWholeSeparatorPreserveAllTokens(str, ":");
        if(groups == null || groups.length < minGroup || groups.length > maxGroup){
            return false;
        }

        // 判断是否有多组 ::
        int strLen = str.length();
        int sc = 0;
        for(int j = 0; j < strLen; j++){
            if(sc > 1){
                return false;
            }

            char c = str.charAt(j);
            if(c == ':'){
                int nj = j + 1;
                if(nj < strLen && str.charAt(nj) == ':'){
                    sc++;
                }
            }
        }

        for(int i = 0; i < groups.length; i++){
            if("".equals(groups[i])){
                continue;
            }

            if(IPV6_group_valid(groups[i]) == false){
                return false;
            }
        }

        return true;
    }

    private final static boolean IPV6_group_valid(final String str){
        if(str == null){
            return false;
        }

        int length = str.length();
        return length >= 1 && length <= 4 && Validate.isXdigit(str);
    }

}
