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

import com.buession.core.Executor;
import com.buession.redis.client.connection.RedisClusterConnection;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;

import java.io.IOException;
import java.util.List;

/**
 * Jedis 集群模式连接器
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class ClusterJedisConnection extends AbstractJedisRedisConnection implements RedisClusterConnection {

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
	public <C, R> R execute(Executor<C, R> executor) throws RedisException{
		return null;
	}

	@Override
	public boolean isTransaction(){
		return false;
	}

	@Override
	public Pipeline getPipeline(){
		return null;
	}

	@Override
	public void multi(){

	}

	@Override
	public List<Object> exec(){
		return null;
	}

	@Override
	public void discard(){

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
