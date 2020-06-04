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
import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.utils.ReturnUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.JedisCommands;

import java.util.Arrays;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class DefaultLuaRedisOperations<C extends JedisCommands> extends AbstractJedisRedisOperations implements JedisLuaRedisOperations {

	public DefaultLuaRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Object eval(final String script){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("script", script);

		return execute(new Executor<C, Object>() {

			@Override
			public Object execute(C jc){
				if(isTransaction()){
					return getTransaction().eval(script).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).eval(script);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.EVAL);
					}
				}
			}

		}, ProtocolCommand.EVAL, arguments);
	}

	@Override
	public Object eval(final String script, final String... params){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("script", script).put("params", params);

		return execute(new Executor<C, Object>() {

			@Override
			public Object execute(C jc){
				if(isTransaction()){
					return getTransaction().eval(script, params == null ? 0 : params.length, params).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).eval(script, params == null ? 0 : params.length, params);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.EVAL);
					}
				}
			}

		}, ProtocolCommand.EVAL, arguments);
	}

	@Override
	public Object eval(final String script, final String[] keys, final String[] args){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("script", script).put("keys", keys).put("args", args);

		return execute(new Executor<C, Object>() {

			@Override
			public Object execute(C jc){
				if(isTransaction()){
					return getTransaction().eval(script, Arrays.asList(keys), Arrays.asList(args)).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).eval(script, Arrays.asList(keys), Arrays.asList(args));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.EVAL);
					}
				}
			}

		}, ProtocolCommand.EVAL, arguments);
	}

	@Override
	public Object evalSha(final String digest){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("digest", digest);

		return execute(new Executor<C, Object>() {

			@Override
			public Object execute(C jc){
				if(isTransaction()){
					return getTransaction().evalsha(digest).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).evalsha(digest);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
					}
				}
			}

		}, ProtocolCommand.EVALSHA, arguments);
	}

	@Override
	public Object evalSha(final String digest, final String... params){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("digest", digest).put("params", params);

		return execute(new Executor<C, Object>() {

			@Override
			public Object execute(C jc){
				if(isTransaction()){
					return getTransaction().evalsha(digest, params == null ? 0 : params.length, params).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).evalsha(digest, params == null ? 0 : params.length, params);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
					}
				}
			}

		}, ProtocolCommand.EVALSHA, arguments);
	}

	@Override
	public Object evalSha(final String digest, final String[] keys, final String[] args){
		final OperationsCommandArguments arguments =
				OperationsCommandArguments.getInstance().put("digest", digest).put("keys", keys).put("args", args);

		return execute(new Executor<C, Object>() {

			@Override
			public Object execute(C jc){
				if(isTransaction()){
					return getTransaction().evalsha(digest, Arrays.asList(keys), Arrays.asList(args)).get();
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).evalsha(digest, Arrays.asList(keys), Arrays.asList(args));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.EVALSHA);
					}
				}
			}

		}, ProtocolCommand.EVALSHA, arguments);
	}

	@Override
	public List<Boolean> scriptExists(final String... sha1){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("sha1", sha1);

		return execute(new Executor<C, List<Boolean>>() {

			@Override
			public List<Boolean> execute(C jc){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SCRIPT_EXISTS);
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).scriptExists(sha1);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_EXISTS);
					}
				}
			}

		}, ProtocolCommand.SCRIPT_EXISTS, arguments);
	}

	@Override
	public String scriptLoad(final String script){
		final OperationsCommandArguments arguments = OperationsCommandArguments.getInstance().put("script", script);

		return execute(new Executor<C, String>() {

			@Override
			public String execute(C jc){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SCRIPT_LOAD);
				}else{
					if(jc instanceof Jedis){
						return ((Jedis) jc).scriptLoad(script);
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_LOAD);
					}
				}
			}

		}, ProtocolCommand.SCRIPT_LOAD, arguments);
	}

	@Override
	public Status scriptKill(){
		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C jc){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SCRIPT_KILL);
				}else{
					if(jc instanceof Jedis){
						return ReturnUtils.statusForOK(((Jedis) jc).scriptKill());
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_KILL);
					}
				}
			}

		}, ProtocolCommand.SCRIPT_KILL);
	}

	@Override
	public Status scriptFlush(){
		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C jc){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SCRIPT_FLUSH);
				}else{
					if(jc instanceof Jedis){
						return ReturnUtils.statusForOK(((Jedis) jc).scriptFlush());
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SCRIPT_FLUSH);
					}
				}
			}

		}, ProtocolCommand.SCRIPT_FLUSH);
	}

}
