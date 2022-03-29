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
package com.buession.redis.exception;

import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.core.RedisMode;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @author Yong.Teng
 * @since 1.2.1
 */
public class RedisExceptionUtils {

	/**
	 * 不支持命令异常
	 *
	 * @param command
	 * 		命令
	 * @param commandNotSupported
	 * 		运行模式
	 * @param connection
	 * 		连接对象
	 */
	public static void commandNotSupportedException(final ProtocolCommand command,
													final CommandNotSupported commandNotSupported,
													final RedisConnection connection){
		final int code = commandNotSupported.getCode();
		if((1 & code) == 0){
			if(connection.isPipeline()){
				throw new NotSupportedPipelineCommandException(commandNotSupportedMessage(command,
						RedisConnectionUtils.getRedisMode(connection), "pipeline"));
			}else if(connection.isTransaction()){
				throw new NotSupportedTransactionCommandException(commandNotSupportedMessage(command,
						RedisConnectionUtils.getRedisMode(connection), "transaction"));
			}
		}else if((1 & code) == 1){
			throw new NotSupportedCommandException(commandNotSupportedMessage(command,
					RedisConnectionUtils.getRedisMode(connection), null));
		}
	}

	public static RedisException convert(final Exception e){
		if(e instanceof JedisConnectionException){
			if(e.getMessage().contains("pool")){
				return new PoolException(e.getMessage(), e);
			}else{
				return new RedisConnectionFailureException(e.getMessage(), e);
			}
		}else if(e instanceof NotSupportedCommandException){
			return (NotSupportedCommandException) e;
		}else{
			return new RedisException(e.getMessage(), e);
		}
	}

	private static String commandNotSupportedMessage(final ProtocolCommand command, final RedisMode mode,
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
