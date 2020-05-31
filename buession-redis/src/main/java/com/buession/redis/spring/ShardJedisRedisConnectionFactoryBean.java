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
import com.buession.redis.client.connection.datasource.jedis.GenericShardedJedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.ShardedJedisPoolDataSource;
import com.buession.redis.client.connection.jedis.ShardedJedisConnection;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.ShardedRedisNode;
import com.buession.redis.exception.PoolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedisPool;

import java.util.Set;

/**
 * @author Yong.Teng
 */
public class ShardJedisRedisConnectionFactoryBean extends RedisConnectionFactoryBean<ShardedJedisConnection> {

	private Set<ShardedRedisNode> redisNodes;

	private int database = RedisNode.DEFAULT_DATABASE;

	private JedisPoolConfig poolConfig = new JedisPoolConfig();

	private ShardedJedisPool pool;

	private final static Logger logger = LoggerFactory.getLogger(ShardJedisRedisConnectionFactoryBean.class);

	public Set<ShardedRedisNode> getRedisNodes(){
		return redisNodes;
	}

	public void setRedisNodes(Set<ShardedRedisNode> redisNodes){
		this.redisNodes = redisNodes;
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

	public JedisPoolConfig getPoolConfig(){
		return poolConfig;
	}

	public void setPoolConfig(JedisPoolConfig poolConfig){
		this.poolConfig = poolConfig;
	}

	public ShardedJedisPool getPool(){
		return pool;
	}

	public void setPool(ShardedJedisPool pool){
		this.pool = pool;
	}

	@Override
	public Class<? extends ShardedJedisConnection> getObjectType(){
		return ShardedJedisConnection.class;
	}

	@Override
	public void afterPropertiesSet() throws Exception{
		if(isUsePool()){
			final ShardedJedisPoolDataSource dataSource = createShardedJedisPoolDataSource();

			setPool(dataSource.getPool());
			setConnection(new ShardedJedisConnection(dataSource));
			logger.debug("Initialize sharded connection for pool.");
		}else{
			final GenericShardedJedisDataSource dataSource = createGenericShardedJedisDataSource();

			setConnection(new ShardedJedisConnection(dataSource));
			logger.debug("Initialize sharded connection for generic.");
		}
	}

	protected GenericShardedJedisDataSource createGenericShardedJedisDataSource(){
		return new GenericShardedJedisDataSource(getRedisNodes(), getDatabase(), getConnectTimeout(), getSoTimeout(),
				isUseSsl(), getSslSocketFactory(), getSslParameters(), getHostnameVerifier());
	}

	protected ShardedJedisPoolDataSource createShardedJedisPoolDataSource(){
		return new ShardedJedisPoolDataSource(getRedisNodes(), getDatabase(), getConnectTimeout(), getSoTimeout(),
				isUseSsl(), getSslSocketFactory(), getSslParameters(), getHostnameVerifier(), getPoolConfig());
	}

	@Override
	protected void afterDestroy(ShardedJedisConnection connection){
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
