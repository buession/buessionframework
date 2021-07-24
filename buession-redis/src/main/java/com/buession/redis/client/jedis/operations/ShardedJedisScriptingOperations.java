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
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.ShardedJedisClient;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.RedisExceptionUtils;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class ShardedJedisScriptingOperations extends AbstractScriptingOperations<ShardedJedis, ShardedJedisPipeline> {

	public ShardedJedisScriptingOperations(final ShardedJedisClient client){
		super(client);
	}

	@Override
	public Object eval(final String script){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.EVAL, client.getConnection());
		return null;
	}

	@Override
	public Object eval(final byte[] script){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.EVAL, client.getConnection());
		return null;
	}

	@Override
	public Object eval(final String script, final String... params){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.EVAL, client.getConnection());
		return null;
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.EVAL, client.getConnection());
		return null;
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] arguments){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.EVAL, client.getConnection());
		return null;
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.EVAL, client.getConnection());
		return null;
	}

	@Override
	public Object evalSha(final String digest){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.EVALSHA, client.getConnection());
		return null;
	}

	@Override
	public Object evalSha(final byte[] digest){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.EVALSHA, client.getConnection());
		return null;
	}

	@Override
	public Object evalSha(final String digest, final String... params){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.EVALSHA, client.getConnection());
		return null;
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... params){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.EVALSHA, client.getConnection());
		return null;
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] arguments){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.EVALSHA, client.getConnection());
		return null;
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.EVALSHA, client.getConnection());
		return null;
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.SCRIPT_EXISTS, client.getConnection());
		return null;
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.SCRIPT_EXISTS, client.getConnection());
		return null;
	}

	@Override
	public Status scriptFlush(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.SCRIPT_FLUSH, client.getConnection());
		return null;
	}

	@Override
	public Status scriptKill(){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.SCRIPT_KILL, client.getConnection());
		return null;
	}

	@Override
	public String scriptLoad(final String script){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.SCRIPT_LOAD, client.getConnection());
		return null;
	}

	@Override
	public byte[] scriptLoad(final byte[] script){
		RedisExceptionUtils.commandAllNotSupportedException(ProtocolCommand.SCRIPT_LOAD, client.getConnection());
		return null;
	}

}
