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

import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.datasource.jedis.JedisPoolDataSource;
import com.buession.redis.client.connection.datasource.jedis.ShardedJedisPoolDataSource;
import com.buession.redis.client.connection.datasource.jedis.SimpleJedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.SimpleShardedJedisDataSource;
import com.buession.redis.client.connection.jedis.JedisPoolConnection;
import com.buession.redis.client.connection.jedis.JedisRedisConnection;
import com.buession.redis.client.connection.jedis.ShardedJedisPoolConnection;
import com.buession.redis.client.connection.jedis.SimpleJedisConnection;
import com.buession.redis.client.connection.jedis.SimpleShardedJedisConnection;
import com.buession.redis.exception.PoolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.commands.JedisCommands;
import redis.clients.jedis.util.Pool;

/**
 * @author Yong.Teng
 */
public class JedisRedisConnectionFactoryBean extends RedisConnectionFactoryBean<JedisRedisConnection> {

	private JedisPoolConfig poolConfig = new JedisPoolConfig();

	private Pool<? extends JedisCommands> pool;

	private boolean useShardInfo;

	private final static Logger logger = LoggerFactory.getLogger(JedisRedisConnectionFactoryBean.class);

	public JedisPoolConfig getPoolConfig(){
		return poolConfig;
	}

	public void setPoolConfig(JedisPoolConfig poolConfig){
		this.poolConfig = poolConfig;
	}

	public Pool<? extends JedisCommands> getPool(){
		return pool;
	}

	public void setPool(Pool<? extends JedisCommands> pool){
		this.pool = pool;
	}

	public boolean isUseShardInfo(){
		return getUseShardInfo();
	}

	public boolean getUseShardInfo(){
		return useShardInfo;
	}

	public void setUseShardInfo(boolean useShardInfo){
		this.useShardInfo = useShardInfo;
	}

	@Override
	public Class<? extends RedisConnection> getObjectType(){
		return JedisRedisConnection.class;
	}

	@Override
	public void afterPropertiesSet() throws Exception{
		if(isUsePool()){
			if(isUseShardInfo()){
				final ShardedJedisPoolDataSource dataSource = new ShardedJedisPoolDataSource(getHost(), getPort(),
						getPassword(), getDatabase(), getClientName(), getConnectTimeout(), getSoTimeout(), isUseSsl()
						, getSslSocketFactory(), getSslParameters(), getHostnameVerifier(), poolConfig);

				dataSource.setWeight(getWeight());

				setPool(dataSource.getPool());
				setConnection(new ShardedJedisPoolConnection(dataSource));
				logger.debug("Initialize connection for pool and shard info.");
			}else{
				final JedisPoolDataSource dataSource = new JedisPoolDataSource(getHost(), getPort(), getPassword(),
						getDatabase(), getClientName(), getConnectTimeout(), getSoTimeout(), isUseSsl(),
						getSslSocketFactory(), getSslParameters(), getHostnameVerifier(), poolConfig);

				setPool(dataSource.getPool());
				setConnection(new JedisPoolConnection(dataSource));
				logger.debug("Initialize connection for pool.");
			}
		}else{
			if(isUseShardInfo()){
				final SimpleShardedJedisDataSource dataSource = new SimpleShardedJedisDataSource(getHost(), getPort(),
						getPassword(), getDatabase(), getClientName(), getConnectTimeout(), getSoTimeout(), isUseSsl()
						, getSslSocketFactory(), getSslParameters(), getHostnameVerifier());

				dataSource.setWeight(getWeight());
				setConnection(new SimpleShardedJedisConnection(dataSource));
				logger.debug("Initialize connection for shard info.");
			}else{
				final SimpleJedisDataSource dataSource = new SimpleJedisDataSource(getHost(), getPort(), getPassword()
						, getDatabase(), getClientName(), getConnectTimeout(), getSoTimeout(), isUseSsl(),
						getSslSocketFactory(), getSslParameters(), getHostnameVerifier());

				setConnection(new SimpleJedisConnection(dataSource));
				logger.debug("Initialize connection simple.");
			}
		}
	}

	@Override
	protected void afterDestroy(JedisRedisConnection connection){
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
