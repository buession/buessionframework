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

import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.convert.TransactionResultConverter;
import com.buession.redis.exception.RedisException;
import com.buession.redis.transaction.Transaction;
import com.buession.redis.utils.ReturnUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * @author Yong.Teng
 */
public class JedisTransactionOperations extends AbstractTransactionOperations<Jedis, Pipeline> {

	public JedisTransactionOperations(final RedisClient client){
		super(client);
	}

	@Override
	public void discard(){
		try{
			execute((cmd)->{
				client.getConnection().discard();
				return null;
			});
		}catch(Exception e){
			throw e;
		}finally{
			txResults.clear();
		}
	}

	@Override
	public List<Object> exec(){
		try{
			if(isTransaction() == false){
				throw new RedisException("No ongoing transaction. Did you forget to call multi?");
			}

			List<Object> results = execute((cmd)->client.getConnection().exec());
			return Validate.isEmpty(results) ? results : new TransactionResultConverter(txResults).convert(results);
		}catch(Exception e){
			throw e;
		}finally{
			txResults.clear();
		}
	}

	@Override
	public Transaction multi(){
		return execute((cmd)->{
			RedisConnection connection = client.getConnection();
			connection.multi();
			return connection.getTransaction();
		});
	}

	@Override
	public Status watch(final String... keys){
		return execute((cmd)->ReturnUtils.statusForOK(cmd.watch(keys)));
	}

	@Override
	public Status watch(final byte[]... keys){
		return execute((cmd)->ReturnUtils.statusForOK(cmd.watch(keys)));
	}

	@Override
	public Status unwatch(){
		return execute((cmd)->ReturnUtils.statusForOK(cmd.unwatch()));
	}

}
