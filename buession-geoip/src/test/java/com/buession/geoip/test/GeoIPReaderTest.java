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
 * | Copyright @ 2013-2017 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.geoip.test;

import com.buession.geoip.DatabaseResolver;
import com.maxmind.geoip2.exception.GeoIp2Exception;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public class GeoIPReaderTest {

    public final static void main(String[] args) throws IOException, GeoIp2Exception{
        String path = DatabaseResolver.class.getResource("/maxmind/City.mmdb").getPath();
        DatabaseResolver geoIPReader = new DatabaseResolver(path);

        //for(int i = 0; i < CountryDict.COUNTRY_CODE.length; i++){
        //System.out.println("COUNTRIES_NAME.put(\"" + CountryDict.COUNTRY_CODE[i] + "\", \"" + CountryDict
        //.COUNTRY_NAME[i] + "\");");
        //}

        System.out.println(path);
        System.out.println(geoIPReader.location("173.194.201.95"));
        //System.out.println(geoIPReader.location("171.217.18.111"));
        //System.out.println(geoIPReader.country("119.6.7.98"));
        //System.out.println(geoIPReader.country("104.20.59.194"));
        //System.out.println(geoIPReader.country("182.140.147.104"));
        //System.out.println(geoIPReader.country("127.0.0.1"));
        //System.out.println(geoIPReader.country("255.255.255.255"));
        //System.out.println(geoIPReader.country("59.110.251.4"));
        // System.out.println(geoIPReader.getMetadata());

    }

}
