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
package com.buession.redis.client.operations;

import com.buession.core.Executor;
import com.buession.core.utils.ArrayUtils;
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.RedisException;
import com.buession.redis.utils.ReturnUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

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

					sb.append(name).append(" => ");

					if(value != null){
						if(value.getClass().isArray()){
							sb.append(ArrayUtils.toString((Object[]) value));
						}else{
							sb.append(value);
						}
					}else{
						sb.append(value);
					}
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

	protected static Status statusForBool(final boolean b){
		return ReturnUtils.statusForBool(b);
	}

	protected static Status statusForOK(final String str){
		return ReturnUtils.statusForOK(str);
	}

	protected static Status statusForOK(final byte[] str){
		return ReturnUtils.statusForOK(str);
	}

	protected static RedisServerTime redisServerTime(final List<String> ret){
		return ReturnUtils.redisServerTime(ret);
	}

	protected static <V extends Enum<V>> V enumValueOf(final String str, final Class<V> enumType){
		return ReturnUtils.enumValueOf(str, enumType);
	}

}
