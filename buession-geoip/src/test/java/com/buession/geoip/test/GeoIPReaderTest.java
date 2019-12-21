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
import com.buession.geoip.spring.GeoIPResolverFactoryBean;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public class GeoIPReaderTest {

    @Test
    public void location() throws IOException, GeoIp2Exception{
        GeoIPResolverFactoryBean geoIPResolverFactoryBean = new GeoIPResolverFactoryBean();

        try{
            geoIPResolverFactoryBean.afterPropertiesSet();

            DatabaseResolver resolver = geoIPResolverFactoryBean.getObject();

            System.out.println(resolver.location("180.118.86.115").getDistrict().getName());
            System.out.println(resolver.location("180.118.86.115").getDistrict().getName());
        }catch(Exception e){

        }
    }

}
