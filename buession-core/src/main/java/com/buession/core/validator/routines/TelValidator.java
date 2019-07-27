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

/**
 * @author Yong.Teng
 */
public class TelValidator {

    private TelValidator(){

    }

    public final static boolean isValid(final CharSequence charSequence, final AreaCodeType areaCodeType){
        if(charSequence == null || charSequence.length() < 7){
            return false;
        }

        switch(areaCodeType){
            case NEED:
                return validHasAreaCode(charSequence);
            case NOT_NEED:
                return validNotHasAreaCode(charSequence);
            default:
                return validHasAreaCode(charSequence) == true || validNotHasAreaCode(charSequence) == true;
        }
    }

    private static boolean validHasAreaCode(final CharSequence charSequence){
        char c = charSequence.charAt(0);
        int nextIndex = 0;
        CharSequence symbol = charSequence.subSequence(0, 1);

        if(symbol == "(" || symbol == "（"){
            nextIndex = 1;
        }

        c = charSequence.charAt(nextIndex);
        if(c == '0'){
            nextIndex++;
        }else if(c == '8' && charSequence.charAt(nextIndex + 1) == '6'){
            nextIndex += 2;
        }else{
            return false;
        }

        c = charSequence.charAt(nextIndex++);
        if(c < '1' || c > '9'){
            return false;
        }

        c = charSequence.charAt(nextIndex++);
        if(c < '0' || c > '9'){
            return false;
        }

        c = charSequence.charAt(nextIndex++);
        if(c >= '0' && c <= '9'){

        }else if(symbol == "("){
            if(c != ')'){
                return false;
            }
        }else if(symbol == "（"){
            if(c != '）'){
                return false;
            }
        }else if(c == '-'){

        }else{
            return false;
        }

        return validNotHasAreaCode(charSequence.subSequence(nextIndex, charSequence.length() - 1));
    }

    private static boolean validNotHasAreaCode(final CharSequence charSequence){
        if(charSequence == null){
            return false;
        }

        int len = charSequence.length();
        if(len != 7 && len != 8){
            return false;
        }

        return NumberValidateUtil.type2(charSequence);
    }

    public enum AreaCodeType {
        NEED,
        NOT_NEED,
        BOTH
    }

}