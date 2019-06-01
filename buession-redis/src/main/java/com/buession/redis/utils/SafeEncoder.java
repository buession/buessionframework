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
package com.buession.redis.utils;

import com.buession.core.utils.Assert;
import com.buession.redis.Constants;

/**
 * @author Yong.Teng
 */
public class SafeEncoder {

    private SafeEncoder(){

    }

    public static byte[] encode(final String str){
        Assert.isNull(str, "Value cloud not be null.");
        return str.getBytes(Constants.CHARSET);
    }

    public static String encode(final byte[] data){
        return new String(data, Constants.CHARSET);
    }

    public static byte[][] encode(final String... strs){
        byte[][] result = new byte[strs.length][];

        for(int i = 0; i < strs.length; i++){
            result[i] = encode(strs[i]);
        }

        return result;
    }

    public static String[] encode(final byte[]... data){
        String[] result = new String[data.length];

        for(int i = 0; i < data.length; i++){
            result[i] = encode(data[i]);
        }

        return result;
    }

}
