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
package com.buession.redis.spring;

import com.buession.core.utils.Assert;
import com.buession.redis.client.connection.datasource.jedis.GenericJedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisPoolDataSource;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.core.RedisNode;
import com.buession.redis.exception.PoolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Yong.Teng
 */
public class JedisRedisConnectionFactoryBean extends RedisConnectionFactoryBean<JedisConnection> {

	private String host = RedisNode.DEFAULT_HOST;

	private int port = RedisNode.DEFAULT_PORT;

	private String password;

	private int database = RedisNode.DEFAULT_DATABASE;

	private String clientName;

	private JedisPoolConfig poolConfig = new JedisPoolConfig();

	private JedisPool pool;

	private final static Logger logger = LoggerFactory.getLogger(JedisRedisConnectionFactoryBean.class);

	public String getHost(){
		return host;
	}

	public void setHost(String host){
		this.host = host;
	}

	public int getPort(){
		return port;
	}

	public void setPort(int port){
		this.port = port;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public int getDatabase(){
		return database;
	}

	public void setDatabase(int database){
		Assert.isNegative(database, "invalid DB index (a positive index required)");
		this.database = database;
	}

	public int getDb(){
		return getDatabase();
	}

	public void setDb(int database){
		setDatabase(database);
	}

	public String getClientName(){
		return clientName;
	}

	public void setClientName(String clientName){
		this.clientName = clientName;
	}

	public JedisPoolConfig getPoolConfig(){
		return poolConfig;
	}

	public void setPoolConfig(JedisPoolConfig poolConfig){
		this.poolConfig = poolConfig;
	}

	public JedisPool getPool(){
		return pool;
	}

	public void setPool(JedisPool pool){
		this.pool = pool;
	}

	@Override
	public Class<? extends JedisConnection> getObjectType(){
		return JedisConnection.class;
	}

	@Override
	public void afterPropertiesSet() throws Exception{
		if(isUsePool()){
			final JedisPoolDataSource dataSource = createJedisPoolDataSource();

			setPool(dataSource.getPool());
			setConnection(new JedisConnection(dataSource));
			logger.debug("Initialize connection for pool.");
		}else{
			final GenericJedisDataSource dataSource = createGenericJedisDataSource();

			setConnection(new JedisConnection(dataSource));
			logger.debug("Initialize connection for generic.");
		}
	}

	protected GenericJedisDataSource createGenericJedisDataSource(){
		return new GenericJedisDataSource(getHost(), getPort(), getPassword(), getDatabase(), getClientName(),
				getConnectTimeout(), getSoTimeout(), isUseSsl(), getSslSocketFactory(), getSslParameters(),
				getHostnameVerifier());
	}

	protected JedisPoolDataSource createJedisPoolDataSource(){
		return new JedisPoolDataSource(getHost(), getPort(), getPassword(), getDatabase(), getClientName(),
				getConnectTimeout(), getSoTimeout(), isUseSsl(), getSslSocketFactory(), getSslParameters(),
				getHostnameVerifier(), getPoolConfig());
	}

	@Override
	protected void afterDestroy(JedisConnection connection){
		if(isUsePool() && getPool() != null){
			try{
				getPool().destroy();
			}catch(Exception e){
				logger.warn("Cannot properly close Jedis pool", e);
				throw new PoolException(e.getMessage(), e);
			}
			setPool(null);
		}
	}

}
