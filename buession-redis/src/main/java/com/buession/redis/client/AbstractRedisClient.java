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
import com.buession.core.utils.EnumUtils;
import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.RedisConnectionFactory;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.RedisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public abstract class AbstractRedisClient implements RedisClient {

	private RedisConnectionFactory connectionFactory;

	private RedisConnection connection;

	private final static Logger logger = LoggerFactory.getLogger(AbstractRedisClient.class);

	public AbstractRedisClient(){
		super();
	}

	public AbstractRedisClient(RedisConnection connection){
		setConnection(connection);
	}

	@Override
	public RedisConnection getConnection(){
		return connection;
	}

	@Override
	public void setConnection(RedisConnection connection){
		this.connection = connection;
		connectionFactory = new RedisConnectionFactory(connection);
	}

	protected <C, R> R doExecute(final ProtocolCommand command, final Executor<C, R> executor) throws RedisException{
		boolean enableTransactionSupport = false;
		RedisConnection connection = processConnection(enableTransactionSupport);

		try{
			logger.debug("Execute command '{}'", command);
			return connection.execute(command, executor);
		}catch(RedisException e){
			logger.error("Execute command '{}', failure: {}", command, e.getMessage());
			throw e;
		}finally{
			RedisConnectionUtils.releaseConnection(connectionFactory, connection, enableTransactionSupport);
		}
	}

	protected <C, R> R doExecute(final ProtocolCommand command, final Executor<C, R> executor,
								 final OperationsCommandArguments arguments){
		boolean enableTransactionSupport = false;
		RedisConnection connection = processConnection(enableTransactionSupport);

		try{
			logger.debug("Execute command '{}'<{}>", command, commandParametersToSting(arguments));
			return connection.execute(command, executor);
		}catch(RedisException e){
			logger.error("Execute command '{}'<{}>, failure: {}", command, commandParametersToSting(arguments),
					e.getMessage());
			throw e;
		}finally{
			RedisConnectionUtils.releaseConnection(connectionFactory, connection, enableTransactionSupport);
		}
	}

	protected RedisConnection processConnection(boolean enableTransactionSupport){
		if(enableTransactionSupport){
			// only bind resources in case of potential transaction synchronization
			return RedisConnectionUtils.bindConnection(connectionFactory, enableTransactionSupport);
		}else{
			return RedisConnectionUtils.getConnection(connectionFactory);
		}
	}

	protected void close(){
		try{
			connection.close();
		}catch(IOException e){
			logger.error("RedisConnection close error: {}", e.getMessage());
		}
	}

	private final static String commandParametersToSting(final OperationsCommandArguments arguments){
		boolean isEmpty = Validate.isEmpty(arguments.getParameters());
		StringBuilder sb = isEmpty ? new StringBuilder() : new StringBuilder(arguments.getParameters().size() * 16);

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

		return sb.toString();
	}

}
