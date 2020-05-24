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
package com.buession.redis.client.connection.jedis;

import com.buession.core.Executor;
import com.buession.redis.client.connection.AbstractRedisConnection;
import com.buession.redis.client.connection.datasource.RedisDataSource;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.RedisConnectionFailureException;
import com.buession.redis.exception.RedisException;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public abstract class AbstractJedisRedisConnection<T extends JedisCommands> extends AbstractRedisConnection implements JedisRedisConnection<T> {

	private T delegate;

	public AbstractJedisRedisConnection(){
		super();
	}

	public <D extends RedisDataSource> AbstractJedisRedisConnection(D dataSource){
		super(dataSource);
	}

	@Override
	public <C, R> R execute(final ProtocolCommand command, final Executor<C, R> executor) throws RedisException{
		try{
			C delegate = (C) getDelegate();
			return executor.execute(delegate);
		}catch(JedisConnectionException e){
			throw new RedisConnectionFailureException(e.getMessage(), e);
		}
	}

	@Override
	protected void doDisconnect() throws IOException{
		delegate = null;
	}

	@Override
	protected void doClose() throws IOException{
		delegate = null;
	}

	protected T getDelegate(){
		if(delegate == null && getDataSource() != null){
			delegate = getDelegate(getDataSource());
		}

		return delegate;
	}

	protected abstract T getDelegate(RedisDataSource dataSource);

}
