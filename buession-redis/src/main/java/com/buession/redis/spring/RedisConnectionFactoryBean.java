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
package com.buession.redis.spring;

import com.buession.redis.client.connection.RedisConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public abstract class RedisConnectionFactoryBean<C extends RedisConnection> extends RedisConnectionFactory implements
        FactoryBean<C>, InitializingBean, DisposableBean {

    private C connection;

    private final static Logger logger = LoggerFactory.getLogger(RedisConnectionFactoryBean.class);

    @Override
    public C getObject() throws Exception{
        return connection;
    }

    @Override
    public Class<? extends RedisConnection> getObjectType(){
        return connection.getClass();
    }

    @Override
    public boolean isSingleton(){
        return true;
    }

    @Override
    public void destroy() throws Exception{
        try{
            connection.close();
        }catch(IOException e){
            logger.error("Redis connection close error.", e);
        }

        doDestroy(connection);
    }

    protected void setConnection(C connection){
        this.connection = connection;
    }

    protected void doDestroy(C connection){

    }
}
