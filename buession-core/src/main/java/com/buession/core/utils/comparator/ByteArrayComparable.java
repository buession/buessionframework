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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.core.utils.comparator;

/**
 * @author Yong.Teng
 */
public class ByteArrayComparable implements Comparable<byte[]> {

    private final byte[] value;

    public ByteArrayComparable(final byte[] value){
        this.value = value;
    }

    @Override
    public int compareTo(byte[] other){
        int len1 = this.value.length;
        int len2 = other.length;

        for(int i = 0, j = Math.min(len1, len2); i < j; i++){
            if(this.value[i] < other[i]){
                return -1;
            }else if(this.value[i] > other[i]){
                return 1;
            }
        }

        if(len1 < len2){
            return -1;
        }else if(len1 > len2){
            return 1;
        }

        return 0;
    }

}