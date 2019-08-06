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
 * | Copyright @ 2013-2018 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Yong.Teng
 */
public class GlobalUtils {

    public final static String long2ip(long l){
        long mask[] = {0x000000FF, 0x0000FF00, 0x00FF0000, 0xFF000000};
        long j = 0;
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < 4; i++){
            j = (l & mask[i]) >> (i * 8);

            if(i > 0){
                sb.insert(0, ".");
            }

            sb.insert(0, Long.toString(j, 10));
        }

        return sb.toString();
    }

    public final static InetAddress long2InetAddress(long l){
        String ip = long2ip(l);

        try{
            return InetAddress.getByName(ip);
        }catch(UnknownHostException e){
            return null;
        }
    }

    public final static int getInteger(final Integer v){
        return v == null ? 0 : v;
    }

}
