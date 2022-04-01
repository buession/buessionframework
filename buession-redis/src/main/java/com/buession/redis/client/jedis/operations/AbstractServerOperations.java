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
package com.buession.redis.client.jedis.operations;

import com.buession.core.converter.ListConverter;
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.ServerOperations;
import com.buession.redis.core.AclLog;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.Role;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.AclLogConverter;
import com.buession.redis.core.internal.convert.jedis.AclUserConverter;
import com.buession.redis.exception.RedisExceptionUtils;
import redis.clients.jedis.AccessControlLogEntry;

/**
 * Jedis 服务端命令操作抽象类
 *
 * @param <CMD>
 * 		Jedis 原始命令对象
 *
 * @author Yong.Teng
 */
public abstract class AbstractServerOperations<CMD> extends AbstractJedisRedisOperations<CMD>
		implements ServerOperations<CMD> {

	protected final static AclUserConverter.AclUserExposeConverter USER_EXPOSE_CONVERTER = new AclUserConverter.AclUserExposeConverter();

	protected final static ListConverter<AccessControlLogEntry, AclLog> LIST_ACL_LOG_EXPOSE_CONVERTER = new ListConverter<>(
			new AclLogConverter.AclLogExposeConverter());

	public AbstractServerOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public String clientId(){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.CLIENT_ID, CommandNotSupported.ALL,
				client.getConnection());
		return null;
	}

	@Override
	public Status clientReply(final ClientReply option){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.CLIENT_REPLY, CommandNotSupported.ALL,
				client.getConnection());
		return null;
	}

	@Override
	public Status clientUnblock(final int clientId){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.CLIENT_UNBLOCK, CommandNotSupported.ALL,
				client.getConnection());
		return null;
	}

	@Override
	public Status clientUnblock(final int clientId, final ClientUnblockType type){
		return clientUnblock(clientId);
	}

	@Override
	public Object pSync(final String masterRunId, final long offset){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.PSYNC, CommandNotSupported.ALL,
				client.getConnection());
		return null;
	}

	@Override
	public Object pSync(final byte[] masterRunId, final long offset){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.PSYNC, CommandNotSupported.ALL,
				client.getConnection());
		return null;
	}

	@Override
	public Status replicaOf(final String host, final int port){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.REPLICAOF, CommandNotSupported.ALL,
				client.getConnection());
		return null;
	}

	@Override
	public Role role(){
		RedisExceptionUtils.commandNotSupportedException(ProtocolCommand.ROLE, CommandNotSupported.ALL,
				client.getConnection());
		return null;
	}

	@Override
	public void shutdown(final boolean save){
		shutdown();
	}

}
