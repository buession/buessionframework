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
package com.buession.redis.client.connection.jedis;

import com.buession.redis.client.connection.RedisClusterConnection;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.transaction.Transaction;

import java.io.IOException;
import java.util.List;

/**
 * Jedis 集群模式连接器
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class JedisClusterConnection extends AbstractJedisRedisConnection implements RedisClusterConnection {

	@Override
	protected void internalInit(){

	}

	@Override
	protected void doConnect() throws IOException{

	}

	@Override
	public int getMaxRetries(){
		return 0;
	}

	@Override
	public void setMaxRetries(int maxRetries){

	}

	@Override
	public Pipeline openPipeline(){
		return null;
	}

	@Override
	public void closePipeline(){

	}

	@Override
	public Transaction multi(){
		return null;
	}


	@Override
	public List<Object> exec() throws RedisException{
		if(transaction != null){
			final List<Object> result = transaction.exec();

			transaction.close();
			transaction = null;

			return result;
		}else{
			throw new RedisException("ERR EXEC without MULTI. Did you forget to call multi?");
		}
	}

	@Override
	public void discard() throws RedisException{
		if(transaction != null){
			transaction.discard();
			transaction = null;
		}else{
			throw new RedisException("ERR DISCARD without MULTI. Did you forget to call multi?");
		}
	}

	@Override
	public boolean isConnect(){
		return false;
	}

	@Override
	public boolean isClosed(){
		return false;
	}
}
