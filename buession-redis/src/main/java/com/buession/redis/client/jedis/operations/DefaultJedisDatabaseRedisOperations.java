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
import redis.clients.jedis.Jedis;
import redis.clients.jedis.commands.JedisCommands;

/**
 * @author Yong.Teng
 */
public class DefaultJedisDatabaseRedisOperations<C extends JedisCommands> extends AbstractJedisRedisOperations implements JedisDatabaseRedisOperations {

	public DefaultJedisDatabaseRedisOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public Status select(final int db){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("db", db);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				if(isTransaction()){
					return statusForOK(getTransaction().select(db).get());
				}else{
					if(cmd instanceof Jedis){
						return statusForOK(((Jedis) cmd).select(db));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SELECT);
					}
				}

			}
		}, ProtocolCommand.SELECT, args);
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		final OperationsCommandArguments args = OperationsCommandArguments.getInstance().put("db1", db1).put("db2",
				db2);

		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				if(isTransaction()){
					return statusForOK(getTransaction().swapDB(db1, db2).get());
				}else{
					if(cmd instanceof Jedis){
						return statusForOK(((Jedis) cmd).swapDB(db1, db2));
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.SWAPDB);
					}
				}

			}
		}, ProtocolCommand.SWAPDB, args);
	}

	@Override
	public Long dbSize(){
		return execute(new Executor<C, Long>() {

			@Override
			public Long execute(C cmd){
				if(isTransaction()){
					return getTransaction().dbSize().get();
				}else{
					if(cmd instanceof Jedis){
						return ((Jedis) cmd).dbSize();
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.DBSIZE);
					}
				}

			}
		}, ProtocolCommand.DBSIZE);
	}

	@Override
	public Status flushDb(){
		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				if(isTransaction()){
					return statusForOK(getTransaction().flushDB().get());
				}else{
					if(cmd instanceof Jedis){
						return statusForOK(((Jedis) cmd).flushDB());
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.FLUSHDB);
					}
				}

			}
		}, ProtocolCommand.FLUSHDB);
	}

	@Override
	public Status flushAll(){
		return execute(new Executor<C, Status>() {

			@Override
			public Status execute(C cmd){
				if(isTransaction()){
					return statusForOK(getTransaction().flushAll().get());
				}else{
					if(cmd instanceof Jedis){
						return statusForOK(((Jedis) cmd).flushAll());
					}else{
						throw new NotSupportedCommandException(ProtocolCommand.FLUSHALL);
					}
				}

			}
		}, ProtocolCommand.FLUSHALL);
	}

}
