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

import com.buession.core.utils.NumberUtils;
import com.buession.lang.Status;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.operations.ServerOperations;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.Role;
import com.buession.redis.core.command.ProtocolCommand;
import redis.clients.jedis.PipelineBase;
import redis.clients.jedis.commands.JedisCommands;

/**
 * @author Yong.Teng
 */
public abstract class AbstractServerOperations<C extends JedisCommands, P extends PipelineBase> extends AbstractJedisRedisClientOperations<C, P> implements ServerOperations {

	public AbstractServerOperations(final RedisClient client){
		super(client);
	}

	@Override
	public String clientId(){
		commandAllNotSupportedException(ProtocolCommand.CLIENT_ID);
		return null;
	}

	@Override
	public Status clientPause(final int timeout){
		return clientPause((long) timeout);
	}

	@Override
	public Status clientReply(final ClientReply option){
		commandAllNotSupportedException(ProtocolCommand.CLIENT_REPLY);
		return null;
	}

	@Override
	public Status clientUnblock(final int clientId){
		commandAllNotSupportedException(ProtocolCommand.CLIENT_UNBLOCK);
		return null;
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type){
		commandAllNotSupportedException(ProtocolCommand.CLIENT_UNBLOCK);
		return null;
	}

	@Override
	public Status configSet(final String parameter, final float value){
		return configSet(parameter, Float.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final float value){
		return configSet(parameter, NumberUtils.float2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final double value){
		return configSet(parameter, Double.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final double value){
		return configSet(parameter, NumberUtils.double2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final int value){
		return configSet(parameter, Integer.toString(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final int value){
		return configSet(parameter, NumberUtils.int2bytes(value));
	}

	@Override
	public Status configSet(final byte[] parameter, final long value){
		return configSet(parameter, NumberUtils.long2bytes(value));
	}

	@Override
	public Status configSet(final String parameter, final long value){
		return configSet(parameter, Long.toString(value));
	}

	@Override
	public Object pSync(final String masterRunId, final int offset){
		commandAllNotSupportedException(ProtocolCommand.PSYNC);
		return null;
	}

	@Override
	public Object pSync(final byte[] masterRunId, final int offset){
		commandAllNotSupportedException(ProtocolCommand.PSYNC);
		return null;
	}

	@Override
	public Object pSync(final String masterRunId, final long offset){
		commandAllNotSupportedException(ProtocolCommand.PSYNC);
		return null;
	}

	@Override
	public Object pSync(final byte[] masterRunId, final long offset){
		commandAllNotSupportedException(ProtocolCommand.PSYNC);
		return null;
	}

	@Override
	public Status replicaOf(final String host, final int port){
		commandAllNotSupportedException(ProtocolCommand.REPLICAOF);
		return null;
	}

	@Override
	public Role role(){
		commandAllNotSupportedException(ProtocolCommand.ROLE);
		return null;
	}

	@Override
	public void shutdown(final boolean save){
		shutdown();
	}

}
