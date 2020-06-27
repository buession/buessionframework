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
import com.buession.redis.core.convert.JedisConverters;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * @author Yong.Teng
 */
public class JedisConnectionOperations extends AbstractConnectionOperations<Jedis, Pipeline> {

	public JedisConnectionOperations(final JedisRedisClient<Jedis> client){
		super(client, null);
	}

	@Override
	public Status auth(final String password){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.AUTH);
		return execute((cmd)->ReturnUtils.statusForOK(cmd.auth(password)));
	}

	@Override
	public Status auth(final byte[] password){
		return auth(SafeEncoder.encode(password));
	}

	@Override
	public byte[] echo(final byte[] str){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().echo(str)));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().echo(str)));
		}else{
			return execute((cmd)->cmd.echo(str));
		}
	}

	@Override
	public Status ping(){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().ping(), JedisConverters.pingResultConvert()));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().ping(),
					JedisConverters.pingResultConvert()));
		}else{
			return execute((cmd)->JedisConverters.pingResult(cmd.ping()));
		}
	}

	@Override
	public Status quit(){
		pipelineAndTransactionNotSupportedException(ProtocolCommand.QUIT);
		return execute((cmd)->ReturnUtils.statusForOK(cmd.quit()));
	}

	@Override
	public Status select(final int db){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().select(db), OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().select(db), OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.select(db)));
		}
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().swapDB(db1, db2), OK_TO_STATUS_CONVERTER));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().swapDB(db1, db2),
					OK_TO_STATUS_CONVERTER));
		}else{
			return execute((cmd)->ReturnUtils.statusForOK(cmd.swapDB(db1, db2)));
		}
	}

}
