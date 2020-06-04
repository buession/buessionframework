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

import com.buession.redis.client.connection.datasource.jedis.GenericJedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.GenericShardedJedisDataSource;
import com.buession.redis.client.connection.datasource.jedis.JedisPoolDataSource;
import com.buession.redis.client.connection.datasource.jedis.ShardedJedisPoolDataSource;
import com.buession.redis.client.connection.jedis.JedisConnection;
import com.buession.redis.client.connection.jedis.JedisRedisConnection;
import com.buession.redis.client.connection.jedis.ShardedJedisConnection;
import com.buession.redis.core.ShardedRedisNode;
import com.buession.redis.exception.PoolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedisPool;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class JedisRedisConnectionFactoryBean extends RedisConnectionFactoryBean<JedisRedisConnection> {

	private JedisPoolConfig poolConfig = new JedisPoolConfig();

	private JedisPool jedisPool = null;

	private ShardedJedisPool shardedJedisPool = null;

	private final static Logger logger = LoggerFactory.getLogger(JedisRedisConnectionFactoryBean.class);

	public JedisRedisConnectionFactoryBean(){
		super();
	}

	public JedisRedisConnectionFactoryBean(final String host){
		super(host);
	}

	public JedisRedisConnectionFactoryBean(final String host, final String password){
		super(host, password);
	}

	public JedisRedisConnectionFactoryBean(final String host, final String password, final int database){
		super(host, password, database);
	}

	public JedisRedisConnectionFactoryBean(final String host, final String password, final int database,
			final boolean usePool){
		super(host, password, database, usePool);
	}

	public JedisRedisConnectionFactoryBean(final String host, final String password, final int database,
			final int connectTimeout, final int soTimeout){
		super(host, password, database, connectTimeout, soTimeout);
	}

	public JedisRedisConnectionFactoryBean(final String host, final String password, final boolean usePool){
		super(host, password, usePool);
	}

	public JedisRedisConnectionFactoryBean(final String host, final String password, final int connectTimeout,
			final int soTimeout){
		super(host, password, connectTimeout, soTimeout);
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port){
		super(host, port);
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final int database){
		super(host, port, database);
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password){
		super(host, port, password);
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database){
		super(host, port, password, database);
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final boolean usePool){
		super(host, port, password, database, usePool);
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final int connectTimeout, final int soTimeout){
		super(host, port, password, database, connectTimeout, soTimeout);
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final int connectTimeout, final int soTimeout, final boolean usePool){
		super(host, port, password, database, connectTimeout, soTimeout, usePool);
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final String clientName, final int connectTimeout, final int soTimeout){
		super(host, port, password, database, clientName, connectTimeout, soTimeout);
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final String clientName, final boolean usePool){
		super(host, port, password, database, clientName, usePool);
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final String clientName, final int connectTimeout, final int soTimeout,
			final boolean usePool){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, usePool);
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final String clientName, final int connectTimeout, final int soTimeout,
			final boolean usePool, final boolean useSsl){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, usePool, useSsl);
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final String clientName, final int connectTimeout, final int soTimeout,
			final boolean usePool, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
			final HostnameVerifier hostnameVerifier){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, usePool, sslSocketFactory,
				sslParameters, hostnameVerifier);
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final String clientName, final int connectTimeout, final int soTimeout,
			final boolean usePool, final boolean useSsl, final SSLSocketFactory sslSocketFactory,
			final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, usePool, useSsl, sslSocketFactory
				, sslParameters, hostnameVerifier);
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes){
		super(redisNodes);
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database){
		super(redisNodes, database);
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database,
			final boolean usePool){
		super(redisNodes, database, usePool);
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final boolean usePool){
		super(redisNodes, usePool);
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int connectTimeout,
			final int soTimeout){
		super(redisNodes, connectTimeout, soTimeout);
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database,
			final int connectTimeout, final int soTimeout){
		super(redisNodes, database, connectTimeout, soTimeout);
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int connectTimeout,
			final int soTimeout, final boolean usePool){
		super(redisNodes, connectTimeout, soTimeout, usePool);
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database,
			final int connectTimeout, final int soTimeout, final boolean usePool){
		super(redisNodes, database, connectTimeout, soTimeout, usePool);
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database,
			final int connectTimeout, final int soTimeout, final boolean usePool,
			final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
			final HostnameVerifier hostnameVerifier){
		super(redisNodes, database, connectTimeout, soTimeout, usePool, sslSocketFactory, sslParameters,
				hostnameVerifier);
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database,
			final int connectTimeout, final int soTimeout, final boolean usePool, final boolean useSsl,
			final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
			final HostnameVerifier hostnameVerifier){
		super(redisNodes, database, connectTimeout, soTimeout, usePool, useSsl, sslSocketFactory, sslParameters,
				hostnameVerifier);
	}

	public JedisRedisConnectionFactoryBean(final JedisPoolConfig poolConfig){
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final JedisPoolConfig poolConfig){
		super(host);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final String password, final JedisPoolConfig poolConfig){
		super(host, password);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final String password, final int database,
			final JedisPoolConfig poolConfig){
		super(host, password, database);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final String password, final int database,
			final boolean usePool, final JedisPoolConfig poolConfig){
		super(host, password, database, usePool);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final String password, final int database,
			final int connectTimeout, final int soTimeout, final JedisPoolConfig poolConfig){
		super(host, password, database, connectTimeout, soTimeout);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final String password, final boolean usePool,
			final JedisPoolConfig poolConfig){
		super(host, password, usePool);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final String password, final int connectTimeout,
			final int soTimeout, final JedisPoolConfig poolConfig){
		super(host, password, connectTimeout, soTimeout);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final JedisPoolConfig poolConfig){
		super(host, port);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final int database,
			final JedisPoolConfig poolConfig){
		super(host, port, database);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final JedisPoolConfig poolConfig){
		super(host, port, password);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final JedisPoolConfig poolConfig){
		super(host, port, password, database);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final boolean usePool, final JedisPoolConfig poolConfig){
		super(host, port, password, database, usePool);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final int connectTimeout, final int soTimeout, final JedisPoolConfig poolConfig){
		super(host, port, password, database, connectTimeout, soTimeout);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final int connectTimeout, final int soTimeout, final boolean usePool,
			final JedisPoolConfig poolConfig){
		super(host, port, password, database, connectTimeout, soTimeout, usePool);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final String clientName, final int connectTimeout, final int soTimeout,
			final JedisPoolConfig poolConfig){
		super(host, port, password, database, clientName, connectTimeout, soTimeout);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final String clientName, final boolean usePool, final JedisPoolConfig poolConfig){
		super(host, port, password, database, clientName, usePool);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final String clientName, final int connectTimeout, final int soTimeout,
			final boolean usePool, final JedisPoolConfig poolConfig){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, usePool);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final String clientName, final int connectTimeout, final int soTimeout,
			final boolean usePool, final boolean useSsl, final JedisPoolConfig poolConfig){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, usePool, useSsl);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final String clientName, final int connectTimeout, final int soTimeout,
			final boolean usePool, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
			final HostnameVerifier hostnameVerifier, final JedisPoolConfig poolConfig){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, usePool, sslSocketFactory,
				sslParameters, hostnameVerifier);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final String host, final int port, final String password,
			final int database, final String clientName, final int connectTimeout, final int soTimeout,
			final boolean usePool, final boolean useSsl, final SSLSocketFactory sslSocketFactory,
			final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier,
			final JedisPoolConfig poolConfig){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, usePool, useSsl, sslSocketFactory
				, sslParameters, hostnameVerifier);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final JedisPoolConfig poolConfig){
		super(redisNodes);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database,
			final JedisPoolConfig poolConfig){
		super(redisNodes, database);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database,
			final boolean usePool, final JedisPoolConfig poolConfig){
		super(redisNodes, database, usePool);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final boolean usePool,
			final JedisPoolConfig poolConfig){
		super(redisNodes, usePool);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int connectTimeout,
			final int soTimeout, final JedisPoolConfig poolConfig){
		super(redisNodes, connectTimeout, soTimeout);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database,
			final int connectTimeout, final int soTimeout, final JedisPoolConfig poolConfig){
		super(redisNodes, database, connectTimeout, soTimeout);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int connectTimeout,
			final int soTimeout, final boolean usePool, final JedisPoolConfig poolConfig){
		super(redisNodes, connectTimeout, soTimeout, usePool);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database,
			final int connectTimeout, final int soTimeout, final boolean usePool, final JedisPoolConfig poolConfig){
		super(redisNodes, database, connectTimeout, soTimeout, usePool);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database,
			final int connectTimeout, final int soTimeout, final boolean usePool,
			final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
			final HostnameVerifier hostnameVerifier, final JedisPoolConfig poolConfig){
		super(redisNodes, database, connectTimeout, soTimeout, usePool, sslSocketFactory, sslParameters,
				hostnameVerifier);
		this.poolConfig = poolConfig;
	}

	public JedisRedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database,
			final int connectTimeout, final int soTimeout, final boolean usePool, final boolean useSsl,
			final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
			final HostnameVerifier hostnameVerifier, final JedisPoolConfig poolConfig){
		super(redisNodes, database, connectTimeout, soTimeout, usePool, useSsl, sslSocketFactory, sslParameters,
				hostnameVerifier);
		this.poolConfig = poolConfig;
	}

	public JedisPoolConfig getPoolConfig(){
		return poolConfig;
	}

	@Override
	public void afterPropertiesSet() throws Exception{
		if(isUsePool()){
			if(isShardedConnection()){
				final ShardedJedisPoolDataSource dataSource = createShardedJedisPoolDataSource();

				shardedJedisPool = dataSource.getPool();
				setConnection(new ShardedJedisConnection(dataSource));
				logger.debug("Initialize sharded connection for pool.");
			}else{
				final JedisPoolDataSource dataSource = createJedisPoolDataSource();

				jedisPool = dataSource.getPool();
				setConnection(new JedisConnection(dataSource));
				logger.debug("Initialize connection for pool.");
			}
		}else{
			if(isShardedConnection()){
				final GenericJedisDataSource dataSource = createGenericJedisDataSource();

				setConnection(new JedisConnection(dataSource));
				logger.debug("Initialize connection for generic.");
			}else{
				final GenericShardedJedisDataSource dataSource = createGenericShardedJedisDataSource();

				setConnection(new ShardedJedisConnection(dataSource));
				logger.debug("Initialize sharded connection for generic.");
			}
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

	protected GenericShardedJedisDataSource createGenericShardedJedisDataSource(){
		return new GenericShardedJedisDataSource(getRedisNodes(), getDatabase(), getConnectTimeout(), getSoTimeout(),
				isUseSsl(), getSslSocketFactory(), getSslParameters(), getHostnameVerifier());
	}

	protected ShardedJedisPoolDataSource createShardedJedisPoolDataSource(){
		return new ShardedJedisPoolDataSource(getRedisNodes(), getDatabase(), getConnectTimeout(), getSoTimeout(),
				isUseSsl(), getSslSocketFactory(), getSslParameters(), getHostnameVerifier(), getPoolConfig());
	}

	@Override
	protected void afterDestroy(JedisRedisConnection connection){
		if(isUsePool() == false){
			return;
		}

		if(shardedJedisPool != null){
			try{
				shardedJedisPool.destroy();
			}catch(Exception e){
				logger.warn("Cannot properly close ShardedJedis pool", e);
				throw new PoolException(e.getMessage(), e);
			}
			shardedJedisPool = null;
		}else if(jedisPool != null){
			try{
				jedisPool.destroy();
			}catch(Exception e){
				logger.warn("Cannot properly close Jedis pool", e);
				throw new PoolException(e.getMessage(), e);
			}
			jedisPool = null;
		}
	}

}
