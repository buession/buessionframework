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
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;

/**
 * @author Yong.Teng
 */
public class GenericJedisBinaryClientAndServerRedisOperations extends AbstractJedisBinaryClientAndServerRedisOperations<Jedis> {

	public GenericJedisBinaryClientAndServerRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Status auth(final byte[] password){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("password", "******");

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.AUTH);
		}else{
			return execute((Jedis cmd)->statusForOK(cmd.auth(SafeEncoder.encode(password))), ProtocolCommand.AUTH,
					args);
		}
	}

	@Override
	public Status clientSetName(final byte[] name){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("name", name);

		if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.CLIENT_SETNAME);
		}else{
			return execute((Jedis cmd)->statusForOK(cmd.clientSetname(name)), ProtocolCommand.CLIENT_SETNAME, args);
		}
	}

}
