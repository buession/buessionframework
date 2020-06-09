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
import com.buession.redis.core.Client;
import com.buession.redis.core.Info;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.utils.ClientUtil;
import com.buession.redis.utils.InfoUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.JedisCommands;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class DefaultJedisClientAndServerRedisOperations<C extends JedisCommands> extends AbstractJedisRedisOperations implements JedisClientAndServerRedisOperations {

	public DefaultJedisClientAndServerRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Status auth(final String password){
		final CommandArguments args = CommandArguments.getInstance().put("password", "******");

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.AUTH);
				}else{
					if(cmd instanceof Jedis){
						return statusForOK(((Jedis) cmd).auth(password));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.AUTH);
					}
				}
			}

		}, ProtocolCommand.AUTH, args);
	}

	@Override
	public Info info(final InfoSection section){
		final CommandArguments args = CommandArguments.getInstance().put("section", section);

		return execute(new Executor<C, Info>() {

			@Override
			public Info execute(C cmd){
				if(isTransaction()){
					return InfoUtil.convert(getTransaction().info(section.name().toLowerCase()).get());
				}else{
					if(cmd instanceof Jedis){
						return InfoUtil.convert(((Jedis) cmd).info(section.name().toLowerCase()));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.INFO);
					}
				}
			}

		}, ProtocolCommand.INFO, args);
	}

	@Override
	public Info info(){
		return execute(new Executor<C, Info>() {

			@Override
			public Info execute(C cmd){
				if(isTransaction()){
					return InfoUtil.convert(getTransaction().info().get());
				}else{
					if(cmd instanceof Jedis){
						return InfoUtil.convert(((Jedis) cmd).info());
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.INFO);
					}
				}
			}

		}, ProtocolCommand.INFO);
	}

	@Override
	public RedisServerTime time(){
		return execute(new Executor<C, RedisServerTime>() {

			@Override
			public RedisServerTime execute(C cmd){
				if(isTransaction()){
					return redisServerTime(getTransaction().time().get());
				}else{
					if(cmd instanceof Jedis){
						return redisServerTime(((Jedis) cmd).time());
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.TIME);
					}
				}
			}

		}, ProtocolCommand.TIME);
	}

	@Override
	public Status clientSetName(final String name){
		final CommandArguments args = CommandArguments.getInstance().put("name", name);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.CLIENT_SETNAME);
				}else{
					if(cmd instanceof Jedis){
						return statusForOK(((Jedis) cmd).clientSetname(name));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.CLIENT_SETNAME);
					}
				}
			}

		}, ProtocolCommand.CLIENT_SETNAME, args);
	}

	@Override
	public String clientGetName(){
		return execute(new Executor<C, String>() {

			@Override
			public String execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.CLIENT_GETNAME);
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).clientGetname();
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.CLIENT_GETNAME);
					}
				}
			}

		}, ProtocolCommand.CLIENT_GETNAME);
	}

	@Override
	public List<Client> clientList(){
		return execute(new Executor<C, List<Client>>() {

			@Override
			public List<Client> execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.CLIENT_LIST);
				}else{
					if(cmd instanceof Jedis){
						return ClientUtil.parse(((Jedis) cmd).clientList());
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.CLIENT_LIST);
					}
				}
			}

		}, ProtocolCommand.CLIENT_LIST);
	}

	@Override
	public Status clientKill(final String host, final int port){
		final CommandArguments args = CommandArguments.getInstance().put("host", host).put("port", port);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.CLIENT_KILL);
				}else{
					if(cmd instanceof Jedis){
						return statusForOK(((Jedis) cmd).clientKill(host + ":" + port));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.CLIENT_KILL);
					}
				}
			}

		}, ProtocolCommand.CLIENT_KILL, args);
	}

	@Override
	public Status quit(){
		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.QUIT);
				}else{
					if(cmd instanceof Jedis){
						return statusForOK(((Jedis) cmd).quit());
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.QUIT);
					}
				}
			}

		}, ProtocolCommand.QUIT);
	}

	@Override
	public void shutdown(){
		execute(new Executor<C, Void>() {

			@Override
			public Void execute(C cmd){
				if(isTransaction()){
					throw new NotSupportedTransactionCommandException(ProtocolCommand.SHUTDOWN);
				}else{
					if(cmd instanceof Jedis){
						((Jedis) cmd).shutdown();
						return null;
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SHUTDOWN);
					}
				}
			}

		}, ProtocolCommand.SHUTDOWN);
	}

	@Override
	public void shutdown(final boolean save){
		shutdown();
	}

}
