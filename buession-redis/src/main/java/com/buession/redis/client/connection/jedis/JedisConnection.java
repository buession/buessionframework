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
import com.buession.redis.client.connection.GenericConnection;
import com.buession.redis.client.connection.SslConfiguration;
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
import redis.clients.jedis.util.Pool;

import java.io.IOException;

/**
 * @author Yong.Teng
 */
public class JedisConnection extends AbstractJedisRedisConnection<Jedis> implements JedisRedisConnection<Jedis>,
		GenericConnection {

	private JedisPool pool;

	private Jedis jedis;

	private static Logger logger = LoggerFactory.getLogger(JedisConnection.class);

	public JedisConnection(){
		super();
	}

	public JedisConnection(JedisDataSource dataSource){
		super(dataSource);
	}

	public JedisConnection(JedisDataSource dataSource, SslConfiguration sslConfiguration){
		super(dataSource, sslConfiguration);
	}

	public JedisConnection(JedisDataSource dataSource, String clientName){
		super(dataSource, clientName);
	}

	public JedisConnection(JedisDataSource dataSource, String clientName, SslConfiguration sslConfiguration){
		super(dataSource, clientName, sslConfiguration);
	}

	public JedisConnection(JedisDataSource dataSource, boolean useSsl){
		super(dataSource, useSsl);
	}

	public JedisConnection(JedisDataSource dataSource, boolean useSsl, SslConfiguration sslConfiguration){
		super(dataSource, useSsl, sslConfiguration);
	}

	public JedisConnection(JedisDataSource dataSource, String clientName, boolean useSsl){
		super(dataSource, clientName, useSsl);
	}

	public JedisConnection(JedisDataSource dataSource, String clientName, boolean useSsl,
			SslConfiguration sslConfiguration){
		super(dataSource, clientName, useSsl, sslConfiguration);
	}

	@Override
	public JedisPool getPool(){
		return pool;
	}

	@Override
	public boolean isTransaction(){
		return jedis == null ? false : JedisClientUtils.isInMulti(jedis);
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

	protected JedisPool createPool(final JedisPoolDataSource dataSource){
		final SslConfiguration sslConfiguration = getSslConfiguration();
		final JedisPool pool = new JedisPool(dataSource.getPoolConfig(), dataSource.getHost(), dataSource.getPort(),
				dataSource.getConnectTimeout(), dataSource.getSoTimeout(), dataSource.getPassword(),
				dataSource.getDatabase(), getClientName(), isUseSsl(), sslConfiguration.getSslSocketFactory(),
				sslConfiguration.getSslParameters(), sslConfiguration.getHostnameVerifier());

		return pool;
	}

	@Override
	protected void doConnect() throws IOException{
		DataSource dataSource = getDataSource();
		if(dataSource == null){
			return;
		}

		if(dataSource instanceof JedisPoolDataSource){
			JedisPoolDataSource jedisPoolDataSource = ((JedisPoolDataSource) dataSource);
			pool = createPool(jedisPoolDataSource);
			jedis = pool.getResource();

			logger.info("Jedis initialize with pool success.");
		}else{
			JedisDataSource jedisDataSource = (JedisDataSource) dataSource;
			SslConfiguration sslConfiguration = getSslConfiguration();

			jedis = new Jedis(jedisDataSource.getHost(), jedisDataSource.getPort(),
					jedisDataSource.getConnectTimeout(), jedisDataSource.getSoTimeout(), isUseSsl(),
					sslConfiguration.getSslSocketFactory(), sslConfiguration.getSslParameters(),
					sslConfiguration.getHostnameVerifier());

			Client client = jedis.getClient();

			if(Validate.hasText(jedisDataSource.getPassword())){
				client.setPassword(jedisDataSource.getPassword());
			}
			client.setDb(jedisDataSource.getDatabase());

			jedis.connect();

			if(Validate.hasText(getClientName())){
				client.clientSetname(getClientName());
			}

			logger.info("Jedis initialize success.");
		}
	}

	@Override
	protected <C, R> R doExecute(Executor<C, R> executor) throws RedisException{
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
