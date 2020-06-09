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

import com.buession.core.Executor;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.BinaryJedisCommands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class DefaultJedisBinaryLuaRedisOperations<C extends BinaryJedisCommands> extends AbstractJedisBinaryRedisOperations implements JedisBinaryLuaRedisOperations {

	public DefaultJedisBinaryLuaRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Object eval(final byte[] script){
		final CommandArguments args = CommandArguments.getInstance().put("script", script);

		return execute(new Executor<C, Object>() {

			@Override
			public Object execute(C cmd){
				if(isTransaction()){
					return getTransaction().eval(script).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).eval(script);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.EVAL);
					}
				}
			}

		}, ProtocolCommand.EVAL, args);
	}

	@Override
	public Object eval(final byte[] script, final byte[]... params){
		final CommandArguments args = CommandArguments.getInstance().put("script", script).put("params", params);

		return execute(new Executor<C, Object>() {

			@Override
			public Object execute(C cmd){
				if(isTransaction()){
					return getTransaction().eval(script, params == null ? 0 : params.length, params).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).eval(script, params == null ? 0 : params.length, params);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.EVAL);
					}
				}
			}

		}, ProtocolCommand.EVAL, args);
	}

	@Override
	public Object eval(final byte[] script, final byte[][] keys, final byte[][] arguments){
		final CommandArguments args = CommandArguments.getInstance().put("script", script).put("keys", keys).put(
				"arguments", arguments);

		return execute(new Executor<C, Object>() {

			@Override
			public Object execute(C cmd){
				if(isTransaction()){
					return getTransaction().eval(script, Arrays.asList(keys), Arrays.asList(arguments)).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).eval(script, Arrays.asList(keys), Arrays.asList(arguments));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.EVAL);
					}
				}
			}

		}, ProtocolCommand.EVAL, args);
	}

	@Override
	public Object evalSha(final byte[] digest){
		final CommandArguments args = CommandArguments.getInstance().put("digest", digest);

		return execute(new Executor<C, Object>() {

			@Override
			public Object execute(C cmd){
				if(isTransaction()){
					return getTransaction().evalsha(digest).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).evalsha(digest);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
					}
				}
			}

		}, ProtocolCommand.EVALSHA, args);
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[]... params){
		final CommandArguments args = CommandArguments.getInstance().put("digest", digest).put("params", params);

		return execute(new Executor<C, Object>() {

			@Override
			public Object execute(C cmd){
				if(isTransaction()){
					return getTransaction().evalsha(digest, params == null ? 0 : params.length, params).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).evalsha(digest, params == null ? 0 : params.length, params);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
					}
				}
			}

		}, ProtocolCommand.EVALSHA, args);
	}

	@Override
	public Object evalSha(final byte[] digest, final byte[][] keys, final byte[][] arguments){
		final CommandArguments args = CommandArguments.getInstance().put("digest", digest).put("keys", keys).put(
				"arguments", arguments);

		return execute(new Executor<C, Object>() {

			@Override
			public Object execute(C cmd){
				if(isTransaction()){
					return getTransaction().evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments)).get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).evalsha(digest, Arrays.asList(keys), Arrays.asList(arguments));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
					}
				}
			}

		}, ProtocolCommand.EVALSHA, args);
	}

	@Override
	public List<Boolean> scriptExists(final byte[]... sha1){
		final CommandArguments args = CommandArguments.getInstance().put("sha1", sha1);

		return execute(new Executor<C, List<Boolean>>() {

			@Override
			public List<Boolean> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SCRIPT_EXISTS);
				}else{
					if(cmd instanceof Jedis){
						List<Long> ret = ((Jedis) cmd).scriptExists(sha1);

						if(ret == null){
							return null;
						}else{
							List<Boolean> result = new ArrayList<>();

							for(Long v : ret){
								result.add(v == 1);
							}

							return result;
						}
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_EXISTS);
					}
				}
			}

		}, ProtocolCommand.SCRIPT_EXISTS, args);
	}

	@Override
	public byte[] scriptLoad(final byte[] script){
		final CommandArguments args = CommandArguments.getInstance().put("script", script);

		return execute(new Executor<C, byte[]>() {

			@Override
			public byte[] execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SCRIPT_LOAD);
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).scriptLoad(script);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_LOAD);
					}
				}
			}

		}, ProtocolCommand.SCRIPT_LOAD, args);
	}

}
