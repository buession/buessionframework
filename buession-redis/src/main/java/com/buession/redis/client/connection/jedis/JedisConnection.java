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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection.jedis;

import com.buession.core.Executor;
import com.buession.core.validator.Validate;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisPoolDataSource;
import com.buession.redis.client.jedis.JedisClientUtils;
import com.buession.redis.exception.RedisException;
import com.buession.redis.transaction.jedis.JedisTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public class JedisConnection extends AbstractJedisRedisConnection<Jedis> implements JedisRedisConnection<Jedis> {

	private JedisPool pool;

	private Jedis jedis;

	private final static Logger logger = LoggerFactory.getLogger(JedisConnection.class);

	public JedisConnection(){
		super();
	}

	public JedisConnection(JedisDataSource dataSource){
		super(dataSource);
	}

	@Override
	public boolean isTransaction(){
		return JedisClientUtils.isInMulti(jedis);
	}

	@Override
	public void multi(){
		transaction = new JedisTransaction(jedis.multi());
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
	protected void doConnect() throws IOException{
		DataSource dataSource = getDataSource();
		if(dataSource == null){
			return;
		}

		if(dataSource instanceof JedisPoolDataSource){
			JedisPoolDataSource jedisPoolDataSource = ((JedisPoolDataSource) dataSource);
			pool = jedisPoolDataSource.getPool();
			jedis = pool.getResource();

			logger.info("Jedis initialize with pool, db {} success, name: {}.", jedisPoolDataSource.getDatabase(),
					jedisPoolDataSource.getClientName());
		}else{
			JedisDataSource jedisDataSource = (JedisDataSource) dataSource;
			jedis = new Jedis(jedisDataSource.getHost(), jedisDataSource.getPort(),
					jedisDataSource.getConnectTimeout(), jedisDataSource.getSoTimeout(), jedisDataSource.isUseSsl(),
					jedisDataSource.getSslSocketFactory(), jedisDataSource.getSslParameters(),
					jedisDataSource.getHostnameVerifier());

			Client client = jedis.getClient();

			if(Validate.hasText(jedisDataSource.getPassword())){
				client.setPassword(jedisDataSource.getPassword());
			}
			client.setDb(jedisDataSource.getDatabase());

			jedis.connect();

			if(Validate.hasText(jedisDataSource.getClientName())){
				client.clientSetname(jedisDataSource.getClientName());
			}

			logger.info("Jedis initialize with db {} success, name: {}.", jedisDataSource.getDatabase(),
					jedisDataSource.getClientName());
		}
	}

	@Override
	protected <C, R> R doExecute(final Executor<C, R> executor) throws RedisException{
		return executor.execute((C) jedis);
	}

	@Override
	protected boolean checkConnect(){
		return jedis != null && jedis.isConnected();
	}

	@Override
	protected boolean checkClosed(){
		return jedis == null || jedis.isConnected() == false;
	}

	@Override
	protected void doDisconnect() throws IOException{
		super.doDisconnect();
		if(jedis != null){
			jedis.disconnect();
		}
	}

	@Override
	protected void doClose() throws IOException{
		super.doClose();
		if(jedis != null){
			if(pool != null){
				jedis.close();
			}else{
				jedis.quit();
				disconnect();
			}
		}
	}

}
