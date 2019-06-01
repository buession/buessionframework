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

import static com.buession.core.validator.routines.ISBNValidator.isIsbn10;
import static java.io.File.separator;
import static java.io.File.separatorChar;

/**
 * IP 验证器
 *
 * @author Yong.Teng
 */
public class IpValidator {

    private final static int IPV4_MIN_LENGTH = 7;

    private final static int IPV4_MAX_LENGTH = 15;

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

        String[] temp = StringUtils.split(str, ".");
        if(temp == null || temp.length != 4){
            return false;
        }

        for(String s : temp){
            if(IPV4_group_valid(s) == false){
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
                    String s = str.substring(1);

                    for(int i = 0; i < s.length(); i++){
                        c = s.charAt(i);
                        if(c < '0' || c > '9'){
                            return false;
                        }
                    }

                    return true;
                }else if(c == '2'){
                    c = str.charAt(1);
                    if(c > '2'){
                        return false;
                    }else if(c == '2'){
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

        if("::".equals(str) == true){
            return true;
        }

        String[] temp = StringUtils.split(str, ":");
        if(temp == null || temp.length == 0 || temp.length > 8){
            return false;
        }

        for(String s : temp){
            if(IPV6_group_valid(s) == false){
                return false;
            }
        }

        return true;
    }

    private final static boolean IPV6_group_valid(final String str){
        if(str == null){
            return false;
        }

        return true;
    }

    public enum IpType {

        IP_V4,

        IP_V6

    }

}
