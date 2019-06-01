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
package com.buession.redis.client.connection;

import com.buession.core.utils.Assert;
import com.buession.redis.transaction.RedisTransactionSynchronizationAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.lang.Nullable;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public final class RedisConnectionUtils {

    private final static Logger logger = LoggerFactory.getLogger(RedisConnectionUtils.class);

    private RedisConnectionUtils(){

    }

    public final static RedisConnection bindConnection(RedisConnectionFactory factory){
        return bindConnection(factory, false);
    }

    public final static RedisConnection bindConnection(RedisConnectionFactory factory, boolean
            enableTransactionSupport){
        return doGetConnection(factory, true, true, enableTransactionSupport);
    }

    public final static RedisConnection getConnection(RedisConnectionFactory factory){
        return getConnection(factory, false);
    }

    public final static RedisConnection getConnection(RedisConnectionFactory factory, boolean enableTransactionSupport){
        return doGetConnection(factory, true, false, enableTransactionSupport);
    }

    public final static boolean isConnectionTransactional(RedisConnectionFactory factory, RedisConnection connection){
        Assert.isNull(factory, "No RedisConnectionFactory specified");

        RedisConnectionHolder connHolder = (RedisConnectionHolder) TransactionSynchronizationManager.getResource
                (factory);

        return (connHolder != null && connection == connHolder.getConnection());
    }

    public final static void releaseConnection(RedisConnectionFactory factory, @Nullable RedisConnection connection){
        if(connection == null){
            return;
        }

        RedisConnectionHolder connectionHolder = (RedisConnectionHolder) TransactionSynchronizationManager
                .getResource(factory);

        if(connectionHolder != null && connectionHolder.isTransactionSyncronisationActive()){
            logger.debug("Redis Connection will be closed when transaction finished.");
            return;
        }

        if(isConnectionTransactional(factory, connection) && TransactionSynchronizationManager
                .isCurrentTransactionReadOnly()){
            unbindConnection(factory);
        }else if(!isConnectionTransactional(factory, connection)){
            logger.debug("Closing Redis Connection");
            try{
                connection.close();
            }catch(IOException e){
                logger.error("Closing redis connection error.", e);
            }
        }
    }

    public final static void unbindConnection(RedisConnectionFactory factory){
        RedisConnectionHolder connectionHolder = (RedisConnectionHolder) TransactionSynchronizationManager
                .unbindResourceIfPossible(factory);

        if(connectionHolder == null){
            return;
        }

        if(connectionHolder.isTransactionSyncronisationActive()){
            logger.debug("Redis Connection will be closed when outer transaction finished.");
        }else{
            logger.debug("Closing bound connection.");
            RedisConnection connection = connectionHolder.getConnection();
            try{
                connection.close();
            }catch(IOException e){
                logger.error("Closing redis connection error.", e);
            }
        }
    }

    private final static RedisConnection doGetConnection(RedisConnectionFactory factory, boolean allowCreate, boolean
            bind, boolean enableTransactionSupport){
        Assert.isNull(factory, "No RedisConnectionFactory specified");

        RedisConnectionHolder connectionHolder = (RedisConnectionHolder) TransactionSynchronizationManager
                .getResource(factory);
        if(connectionHolder != null){
            if(enableTransactionSupport){
                potentiallyRegisterTransactionSynchronisation(factory, connectionHolder);
            }

            return connectionHolder.getConnection();
        }

        if(!allowCreate){
            throw new IllegalArgumentException("No redisConnection found and allowCreate = false");
        }

        logger.debug("Opening Redis RedisConnection.");

        RedisConnection connection = factory.getConnection();

        if(bind){
            RedisConnection redisConnectionToBind = connection;

            if(enableTransactionSupport && isActualNonReadonlyTransactionActive()){
                redisConnectionToBind = createConnectionProxy(factory, connection);
            }

            connectionHolder = new RedisConnectionHolder(redisConnectionToBind);

            TransactionSynchronizationManager.bindResource(factory, connectionHolder);
            if(enableTransactionSupport){
                potentiallyRegisterTransactionSynchronisation(factory, connectionHolder);
            }

            return connectionHolder.getConnection();
        }

        return connection;
    }

    private static void potentiallyRegisterTransactionSynchronisation(final RedisConnectionFactory factory, final
    RedisConnectionHolder connectionHolder){
        if(isActualNonReadonlyTransactionActive() && connectionHolder.isTransactionSyncronisationActive() == false){
            connectionHolder.setTransactionSyncronisationActive(true);

            RedisConnection connection = connectionHolder.getConnection();
            //connection.multi();

            TransactionSynchronizationManager.registerSynchronization(new RedisTransactionSynchronizationAdapter
                    (factory, connectionHolder, connection));
        }
    }

    private final static boolean isActualNonReadonlyTransactionActive(){
        return TransactionSynchronizationManager.isActualTransactionActive() && TransactionSynchronizationManager
                .isCurrentTransactionReadOnly() == false;
    }

    private static RedisConnection createConnectionProxy(RedisConnectionFactory factory, RedisConnection connection){
        ProxyFactory proxyFactory = new ProxyFactory(connection);
        proxyFactory.addAdvice(new ConnectionSplittingInterceptor(factory));

        return RedisConnection.class.cast(proxyFactory.getProxy());
    }

}
