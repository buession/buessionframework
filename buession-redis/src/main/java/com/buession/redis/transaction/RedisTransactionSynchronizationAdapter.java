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
package com.buession.redis.transaction;

import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.RedisConnectionFactory;
import com.buession.redis.client.connection.RedisConnectionHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public class RedisTransactionSynchronizationAdapter extends TransactionSynchronizationAdapter {

    private final RedisConnectionFactory factory;

    private final RedisConnectionHolder connectionHolder;

    private final RedisConnection connection;

    private final static Logger logger = LoggerFactory.getLogger(RedisTransactionSynchronizationAdapter.class);

    public RedisTransactionSynchronizationAdapter(final RedisConnectionFactory factory, final RedisConnectionHolder
            connectionHolder, final RedisConnection connection){
        this.factory = factory;
        this.connectionHolder = connectionHolder;
        this.connection = connection;
    }

    @Override
    public void afterCompletion(final int status){
        try{
            switch(status){
                case TransactionSynchronization.STATUS_COMMITTED:
                    /*connection.execute(Protocol.Command.EXEC, new Executor<T, Object>() {

                        @Override
                        public Object execute(T client){
                            return null;
                        }

                    });*/
                    break;
                case TransactionSynchronization.STATUS_ROLLED_BACK:
                case TransactionSynchronization.STATUS_UNKNOWN:
                default:
                    /*connection.execute(Protocol.Command.DISCARD, new Executor<T, Object>() {

                        @Override
                        public Object execute(T client){
                            return null;
                        }

                    });*/
                    break;
            }
        }finally{
            logger.debug("Closing bound redisConnection after transaction completed with {}", status);

            connectionHolder.setTransactionSyncronisationActive(false);
            try{
                connection.close();
            }catch(IOException e){
            }
            TransactionSynchronizationManager.unbindResource(factory);
        }
    }
}
