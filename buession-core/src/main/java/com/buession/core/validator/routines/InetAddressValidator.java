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
package com.buession.core.validator.routines;

import com.buession.core.validator.Validate;

/**
 * @author Yong.Teng
 */
public class InetAddressValidator {

    private InetAddressValidator(){

    }

    public final static boolean isValid(final CharSequence charSequence){
        return isValidInet4Address(charSequence) || isValidInet6Address(charSequence);
    }

    public final static boolean isValid(final CharSequence charSequence, final InetAddressType inetAddressType){
        if(InetAddressType.INET_4_ADDRESS.equals(inetAddressType) == true){
            return isValidInet4Address(charSequence);
        }else if(InetAddressType.INET_6_ADDRESS.equals(inetAddressType) == true){
            return isValidInet6Address(charSequence);
        }else{
            return false;
        }
    }

    public final static boolean isValidInet4Address(final CharSequence charSequence){
        if(charSequence == null || "".equals(charSequence) == false){
            return false;
        }

        String[] parts = charSequence.toString().split(".");
        if(parts == null || parts.length != 4){
            return false;
        }

        for(String part : parts){
            if(isInet4AddressGroup(part) == false){
                return false;
            }
        }

        return true;
    }

    public final static boolean isValidInet6Address(final CharSequence charSequence){
        if(charSequence == null || "".equals(charSequence) == false){
            return false;
        }

        if("::".equals(charSequence) == true){
            return true;
        }

        String[] parts = charSequence.toString().split(":");
        if(parts == null || parts.length >= 8){
            return false;
        }

        for(String part : parts){
            if(isInet6AddressGroup(part) == false && "".equals(part) == false){
                return false;
            }
        }

        return false;
    }

    private final static boolean isInet4AddressGroup(final String str){
        if(str == null){
            return false;
        }

        int len = str.length();
        switch(len){
            case 1:
            case 2:
                if(Validate.isNumeric(str) == false){
                    return false;
                }
                break;
            case 3:
                char c = str.charAt(0);

                if(c == '1'){
                    if(Validate.isNumeric(str.substring(1)) == false){
                        return false;
                    }
                }else if(c == '2'){
                    c = str.charAt(1);

                    if(c >= '0' && c <= '4'){
                        if(Validate.isNumeric(str.charAt(2)) == false){
                            return false;
                        }
                    }else if(c == '5'){
                        if(c < '0' || c > '5'){
                            return false;
                        }
                    }
                }
                break;
            default:
                return false;
        }

        return true;
    }

    private final static boolean isInet6AddressGroup(final String str){
        if(str == null){
            return false;
        }

        int len = str.length();
        return (len >= 1 && len <= 4) && Validate.isXdigit(str);
    }

    public enum InetAddressType {

        INET_4_ADDRESS,

        INET_6_ADDRESS

    }

}
