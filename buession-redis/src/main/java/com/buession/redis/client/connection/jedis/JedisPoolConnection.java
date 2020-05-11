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

import com.buession.redis.client.connection.datasource.RedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisPoolDataSource;
import com.buession.redis.client.jedis.JedisTransaction;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public class JedisPoolConnection extends AbstractJedisRedisConnection<Jedis> implements JedisConnection {

	public JedisPoolConnection(){
		super();
	}

	public JedisPoolConnection(JedisPoolDataSource dataSource){
		super(dataSource);
	}

	@Override
	public void multi(){
		Jedis delegate = getDelegate();
		transaction = new JedisTransaction(delegate.multi());
	}

	@Override
	public void exec(){
		if(transaction != null){
			transaction.exec();
		}
	}

	@Override
	public void discard(){
		if(transaction != null){
			transaction.discard();
		}
	}

	@Override
	protected Jedis getDelegate(RedisDataSource dataSource){
		if(dataSource != null && dataSource instanceof JedisPoolDataSource){
			JedisPool pool = ((JedisPoolDataSource) dataSource).getPool();

			if(pool != null){
				return pool.getResource();
			}
		}

		return null;
	}

	@Override
	protected void doConnect() throws IOException{
		if(getDataSource() != null && getDataSource() instanceof JedisPoolDataSource){
			((JedisPoolDataSource) getDataSource()).getPool();
		}
	}

	@Override
	protected boolean checkConnect(){
		return getDelegate() != null && getDelegate().isConnected();
	}

	@Override
	protected boolean checkClosed(){
		return getDelegate() == null || getDelegate().isConnected() == false;
	}

	@Override
	protected void doDisconnect() throws IOException{
		if(getDelegate() != null){
			getDelegate().disconnect();
		}
	}

	@Override
	protected void doClose() throws IOException{
		if(getDelegate() != null){
			getDelegate().close();
		}
	}

}
