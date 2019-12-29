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
import com.buession.lang.ISBNType;

/**
 * ISBN 验证器
 *
 * @author Yong.Teng
 */
public class ISBNValidator {

    private ISBNValidator(){

    }

    public final static boolean isValid(final CharSequence charSequence, final char separator){
        return isIsbn10(charSequence, separator) || isIsbn13(charSequence, separator);
    }

    public final static boolean isValid(final CharSequence charSequence, final char separator, final ISBNType type){
        if(ISBNType.ISBN_TYPE_10.equals(type) == true){
            return isIsbn10(charSequence, separator);
        }else if(ISBNType.ISBN_TYPE_13.equals(type) == true){
            return isIsbn13(charSequence, separator);
        }else{
            return false;
        }
    }

    public final static boolean isIsbn10(final CharSequence charSequence, final char separator){
        if(charSequence == null || validSeparator(separator) == false){
            return false;
        }

        int len = charSequence.length();
        char lash_ch = charSequence.charAt(len - 1);

        if(separator == '\0'){
            if(len != 10){
                return false;
            }

            if(Validate.isNumeric(lash_ch) == false && lash_ch != 'X'){
                return false;
            }

            if(Validate.isNumeric(charSequence.subSequence(0, 9)) == false){
                return false;
            }
        }else{
            if(len != 13){
                return false;
            }

            if(Validate.isNumeric(charSequence.charAt(0)) == false || (Validate.isNumeric(lash_ch) == false &&
                    lash_ch != 'X')){
                return false;
            }

            int sl = 0;
            int gl = 0;

            for(int i = 1; i < 12; i++){
                if(sl > 3 || gl > 7){
                    return false;
                }

                char c = charSequence.charAt(i);
                if(c == separator){
                    if(charSequence.charAt(i - 1) == separator){
                        return false;
                    }

                    ++sl;
                    gl = 0;
                    continue;
                }else if(Validate.isNumeric(c) == false){
                    return false;
                }else{
                    ++gl;
                }
            }

            if(sl != 3){
                return false;
            }
        }

        int sum = 0;
        int checksum = 0;
        char ch;
        int j = 0;

        for(int i = 0; i < len; i++){
            if(j == 9){
                break;
            }

            char c = charSequence.charAt(i);
            if(c == separator){
                continue;
            }

            sum += (10 - j++) * (c - '0');
        }

        checksum = 11 - (sum % 11);
        switch(checksum){
            case 11:
                ch = '0';
                break;
            case 10:
                ch = 'X';
                break;
            default:
                ch = (char) (checksum + '0');
                break;
        }

        return ch == lash_ch;
    }

    public final static boolean isIsbn13(final CharSequence charSequence, final char separator){
        if(charSequence == null || validSeparator(separator) == false){
            return false;
        }

        int len = charSequence.length();
        char lash_ch = charSequence.charAt(len - 1);

        if(separator == '\0'){
            if(len != 13){
                return false;
            }

            if(Validate.isNumeric(charSequence.subSequence(0, 12)) == false){
                return false;
            }
        }else{
            if(len != 17){
                return false;
            }

            if(Validate.isNumeric(charSequence.charAt(0)) == false){
                return false;
            }

            int sl = 0;
            int gl = 0;

            for(int i = 1; i < len; i++){
                if(sl > 4 || (sl == 1 && gl > 5) || gl > 9){
                    return false;
                }

                char c = charSequence.charAt(i);
                if(c == separator){
                    if(charSequence.charAt(i - 1) == separator){
                        return false;
                    }

                    ++sl;
                    gl = 0;
                    continue;
                }else if(Validate.isNumeric(c) == false){
                    return false;
                }else{
                    ++gl;
                }
            }

            if(sl != 4){
                return false;
            }
        }

        int j = 0;
        int sum = 0;

        for(int i = 0; i < len; i++){
            if(j == 12){
                break;
            }

            char c = charSequence.charAt(i);
            if(c == separator){
                continue;
            }

            sum += j++ % 2 == 0 ? (c - '0') : 3 * (c - '0');
        }

        int checksum = 10 - (sum % 10);

        return (checksum == 10 ? '0' : checksum + '0') == lash_ch;
    }

    private final static boolean validSeparator(final char separator){
        return separator == '\0' || separator == '-' || separator == ' ';
    }

}