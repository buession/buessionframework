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
package com.buession.redis.client.operations;

import com.buession.redis.core.RedisMode;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedPipelineCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;

/**
 * @author Yong.Teng
 */
public abstract class AbstractRedisClientOperations<T> implements RedisClientOperations<T> {

	private RedisMode redisMode;

	public RedisMode getRedisMode(){
		return redisMode;
	}

	public void setRedisMode(RedisMode redisMode){
		this.redisMode = redisMode;
	}

	protected abstract boolean isTransaction();

	protected abstract boolean isPipeline();

	protected void pipelineAndTransactionNotSupportedException(final ProtocolCommand command){
		if(getRedisMode() == null){
			if(isPipeline()){
				throw new NotSupportedPipelineCommandException(command);
			}else if(isTransaction()){
				throw new NotSupportedTransactionCommandException(command);
			}
		}else{
			if(isPipeline()){
				throw new NotSupportedPipelineCommandException(commandNotSupportedMessage(command, getRedisMode(),
						"pipeline"));
			}else if(isTransaction()){
				throw new NotSupportedTransactionCommandException(commandNotSupportedMessage(command, getRedisMode(),
						"transaction"));
			}
		}
	}

	protected void commandAllNotSupportedException(final ProtocolCommand command){
		if(getRedisMode() == null){
			if(isPipeline()){
				throw new NotSupportedPipelineCommandException(command);
			}else if(isTransaction()){
				throw new NotSupportedTransactionCommandException(command);
			}else{
				throw new NotSupportedCommandException(command);
			}
		}else{
			if(isPipeline()){
				throw new NotSupportedPipelineCommandException(commandNotSupportedMessage(command, getRedisMode(),
						"pipeline"));
			}else if(isTransaction()){
				throw new NotSupportedTransactionCommandException(commandNotSupportedMessage(command, getRedisMode(),
						"transaction"));
			}else{
				throw new NotSupportedCommandException(commandNotSupportedMessage(command, getRedisMode(), null));
			}
		}
	}

	private final static String commandNotSupportedMessage(final ProtocolCommand command, final RedisMode mode,
														   final String s){
		final StringBuilder sb = new StringBuilder(64);

		sb.append("Not supported command: ").append(command);

		if(s != null){
			sb.append(" in ").append(s);
		}

		sb.append(" with ").append(mode).append(" mode.");

		return sb.toString();
	}

}
