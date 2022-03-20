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

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClient;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.convert.OkStatusConverter;
import com.buession.redis.core.convert.PingResultConverter;
import com.buession.redis.exception.RedisExceptionUtils;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * @author Yong.Teng
 */
public class JedisClusterConnectionOperations extends AbstractConnectionOperations<Jedis, Pipeline> {

	public JedisClusterConnectionOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public Status auth(final String password){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.AUTH,
				client.getConnection());

		final OkStatusConverter converter = new OkStatusConverter();
		return execute((cmd)->cmd.auth(password), converter);
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
		final PingResultConverter converter = new PingResultConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().ping(), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().ping(), converter));
		}else{
			return execute((cmd)->cmd.ping(), converter);
		}
	}

	@Override
	public Status quit(){
		RedisExceptionUtils.pipelineAndTransactionCommandNotSupportedException(ProtocolCommand.QUIT,
				client.getConnection());

		final OkStatusConverter converter = new OkStatusConverter();
		return execute((cmd)->cmd.quit(), converter);
	}

	@Override
	public Status select(final int db){
		final OkStatusConverter converter = new OkStatusConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().select(db), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().select(db), converter));
		}else{
			return execute((cmd)->cmd.select(db), converter);
		}
	}

	@Override
	public Status swapdb(final int db1, final int db2){
		final OkStatusConverter converter = new OkStatusConverter();

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().swapDB(db1, db2), converter));
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().swapDB(db1, db2), converter));
		}else{
			return execute((cmd)->cmd.swapDB(db1, db2), converter);
		}
	}

}
