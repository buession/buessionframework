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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client;

import com.buession.core.Executor;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.RedisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Redis 客户端抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractRedisClient implements RedisClient {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 构造函数
	 */
	public AbstractRedisClient(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param connection
	 * 		Redis 连接对象 {@link RedisConnection}
	 */
	public AbstractRedisClient(final RedisConnection connection){
		setConnection(connection);
	}

	@Override
	public <R> R execute(final Command<R> command, final CommandArguments arguments){
		return doExecute((conn)->command.execute(), command.getCommand(), arguments);
	}

	private <R> R doExecute(final Executor<RedisConnection, R> executor, final ProtocolCommand command,
							final CommandArguments arguments){
		long startTime = 0;
		if(logger.isDebugEnabled()){
			startTime = System.nanoTime();
		}

		if(logger.isDebugEnabled()){
			if(arguments != null){
				logger.debug("Execute command '{}' with arguments: {}", command, arguments);
			}else{
				logger.debug("Execute command '{}'", command);
			}
		}

		try{
			return getConnection().execute(executor);
		}catch(RedisException e){
			if(logger.isErrorEnabled()){
				if(arguments != null){
					logger.error("Execute command '{}' with arguments: {}, failure: {}", command,
							arguments, e.getMessage(), e);
				}else{
					logger.error("Execute command '{}', failure: {}", command, e.getMessage(), e);
				}
			}
			throw e;
		}finally{
			if(logger.isDebugEnabled()){
				long finishTime = System.nanoTime();
				logger.debug("Command execution time: {}", finishTime - startTime);
			}
		}
	}

}
