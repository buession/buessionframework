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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.transaction;

import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.RedisConnectionFactory;
import com.buession.redis.client.connection.RedisConnectionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author Yong.Teng
 */
public class TransactionUtils {

	private TransactionUtils(){

	}

	public static boolean isActualNonReadonlyTransactionActive(){
		return TransactionSynchronizationManager.isActualTransactionActive() && !TransactionSynchronizationManager.isCurrentTransactionReadOnly();
	}

	public static void bindResource(final RedisConnectionFactory factory,
									final RedisConnectionHolder connectionHolder){
		TransactionSynchronizationManager.bindResource(factory, connectionHolder);
	}

	public static RedisConnectionHolder getResource(final RedisConnectionFactory factory){
		return (RedisConnectionHolder) TransactionSynchronizationManager.getResource(factory);
	}

	public static RedisConnectionHolder unbindResourceIfPossible(final RedisConnectionFactory factory){
		return (RedisConnectionHolder) TransactionSynchronizationManager.unbindResourceIfPossible(factory);
	}

	public static void registerSynchronization(final RedisConnectionFactory factory,
											   final RedisConnectionHolder connectionHolder,
											   final RedisConnection connection){
		TransactionSynchronizationManager.registerSynchronization(new RedisTransactionSynchronizationAdapter(factory,
				connectionHolder, connection));
	}

	public static boolean isCurrentTransactionReadOnly(){
		return TransactionSynchronizationManager.isCurrentTransactionReadOnly();
	}

}