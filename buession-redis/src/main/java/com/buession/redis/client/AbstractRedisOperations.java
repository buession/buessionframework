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
package com.buession.redis.client;

import com.buession.core.Executor;
import com.buession.core.validator.Validate;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.RedisException;
import com.buession.redis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Yong.Teng
 */
public abstract class AbstractRedisOperations<C extends RedisClient> implements RedisOperations {

	protected final C client;

	private final static Logger logger = LoggerFactory.getLogger(AbstractRedisOperations.class);

	public AbstractRedisOperations(final C client){
		this.client = client;
	}

	protected <C, R> R execute(final Executor<C, R> executor, final ProtocolCommand command){
		return execute(executor, command, null);
	}

	protected <C, R> R execute(final Executor<C, R> executor, final ProtocolCommand command,
			final OperationsCommandArguments arguments) throws RedisException{
		String argumentsString = null;

		if(logger.isDebugEnabled() && arguments != null){
			boolean isEmpty = Validate.isEmpty(arguments.getParameters());
			StringBuilder sb = isEmpty ? new StringBuilder() :
					new StringBuilder(arguments.getParameters().size() * 16);

			if(isEmpty == false){
				arguments.getParameters().forEach((name, value)->{
					if(sb.length() > 0){
						sb.append(", ");
					}

					sb.append(name);
					sb.append(" => ");
					sb.append(value);
				});
			}

			argumentsString = sb.toString();
		}

		if(logger.isDebugEnabled()){
			if(arguments != null){
				logger.debug("Execute command '{}' width arguments: {}", command, argumentsString);
			}else{
				logger.debug("Execute command '{}'", command);
			}
		}

		RedisConnection connection = client.getConnection();
		try{
			return connection.execute(executor);
		}catch(RedisException e){
			if(logger.isDebugEnabled()){
				if(arguments != null){
					logger.error("Execute command '{}' width arguments: {}, failure: {}", command, argumentsString,
							e.getMessage());
				}else{
					logger.error("Execute command '{}', failure: {}", command, e.getMessage());
				}
			}

			throw e;
		}
	}

	protected boolean isTransaction(){
		return client.getConnection().isTransaction();
	}

}
