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
package com.buession.redis.spring;

import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.RedisURI;
import com.buession.redis.core.ShardedRedisNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public abstract class RedisConnectionFactoryBean<C extends RedisConnection> extends RedisConnectionFactory implements FactoryBean<C>, InitializingBean, DisposableBean {

	private C connection;

	private final static Logger logger = LoggerFactory.getLogger(RedisConnectionFactoryBean.class);

	public RedisConnectionFactoryBean(){
		super();
	}

	public RedisConnectionFactoryBean(final RedisURI redisURI){
		super(redisURI);
	}

	public RedisConnectionFactoryBean(final RedisURI redisURI, final boolean usePool){
		super(redisURI, usePool);
	}

	public RedisConnectionFactoryBean(final RedisURI redisURI, final SSLSocketFactory sslSocketFactory,
			final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){
		super(redisURI, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public RedisConnectionFactoryBean(final RedisURI redisURI, final boolean usePool,
			final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
			final HostnameVerifier hostnameVerifier){
		super(redisURI, usePool, sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public RedisConnectionFactoryBean(final String host){
		super(host);
	}

	public RedisConnectionFactoryBean(final String host, final String password){
		super(host, password);
	}

	public RedisConnectionFactoryBean(final String host, final String password, final int database){
		super(host, password, database);
	}

	public RedisConnectionFactoryBean(final String host, final String password, final int database,
			final boolean usePool){
		super(host, password, database, usePool);
	}

	public RedisConnectionFactoryBean(final String host, final String password, final int database,
			final int connectTimeout, final int soTimeout){
		super(host, password, database, connectTimeout, soTimeout);
	}

	public RedisConnectionFactoryBean(final String host, final String password, final boolean usePool){
		super(host, password, usePool);
	}

	public RedisConnectionFactoryBean(final String host, final String password, final int connectTimeout,
			final int soTimeout){
		super(host, password, connectTimeout, soTimeout);
	}

	public RedisConnectionFactoryBean(final String host, final int port){
		super(host, port);
	}

	public RedisConnectionFactoryBean(final String host, final int port, final int database){
		super(host, port, database);
	}

	public RedisConnectionFactoryBean(final String host, final int port, final String password){
		super(host, port, password);
	}

	public RedisConnectionFactoryBean(final String host, final int port, final String password, final int database){
		super(host, port, password, database);
	}

	public RedisConnectionFactoryBean(final String host, final int port, final String password, final int database,
			final boolean usePool){
		super(host, port, password, database, usePool);
	}

	public RedisConnectionFactoryBean(final String host, final int port, final String password, final int database,
			final int connectTimeout, final int soTimeout){
		super(host, port, password, database, connectTimeout, soTimeout);
	}

	public RedisConnectionFactoryBean(final String host, final int port, final String password, final int database,
			final int connectTimeout, final int soTimeout, final boolean usePool){
		super(host, port, password, database, connectTimeout, soTimeout, usePool);
	}

	public RedisConnectionFactoryBean(final String host, final int port, final String password, final int database,
			final String clientName, final int connectTimeout, final int soTimeout){
		super(host, port, password, database, clientName, connectTimeout, soTimeout);
	}

	public RedisConnectionFactoryBean(final String host, final int port, final String password, final int database,
			final String clientName, final boolean usePool){
		super(host, port, password, database, clientName, usePool);
	}

	public RedisConnectionFactoryBean(final String host, final int port, final String password, final int database,
			final String clientName, final int connectTimeout, final int soTimeout, final boolean usePool){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, usePool);
	}

	public RedisConnectionFactoryBean(final String host, final int port, final String password, final int database,
			final String clientName, final int connectTimeout, final int soTimeout, final boolean usePool,
			final boolean useSsl){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, usePool, useSsl);
	}

	public RedisConnectionFactoryBean(final String host, final int port, final String password, final int database,
			final String clientName, final int connectTimeout, final int soTimeout, final boolean usePool,
			final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
			final HostnameVerifier hostnameVerifier){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, usePool, sslSocketFactory,
				sslParameters, hostnameVerifier);
	}

	public RedisConnectionFactoryBean(final String host, final int port, final String password, final int database,
			final String clientName, final int connectTimeout, final int soTimeout, final boolean usePool,
			final boolean useSsl, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
			final HostnameVerifier hostnameVerifier){
		super(host, port, password, database, clientName, connectTimeout, soTimeout, usePool, useSsl, sslSocketFactory
				, sslParameters, hostnameVerifier);
	}

	public RedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes){
		super(redisNodes);
	}

	public RedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database){
		super(redisNodes, database);
	}

	public RedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database,
			final boolean usePool){
		super(redisNodes, database, usePool);
	}

	public RedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final boolean usePool){
		super(redisNodes, usePool);
	}

	public RedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int connectTimeout,
			final int soTimeout){
		super(redisNodes, connectTimeout, soTimeout);
	}

	public RedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database,
			final int connectTimeout, final int soTimeout){
		super(redisNodes, database, connectTimeout, soTimeout);
	}

	public RedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int connectTimeout,
			final int soTimeout, final boolean usePool){
		super(redisNodes, connectTimeout, soTimeout, usePool);
	}

	public RedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database,
			final int connectTimeout, final int soTimeout, final boolean usePool){
		super(redisNodes, database, connectTimeout, soTimeout, usePool);
	}

	public RedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database,
			final int connectTimeout, final int soTimeout, final boolean usePool,
			final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
			final HostnameVerifier hostnameVerifier){
		super(redisNodes, database, connectTimeout, soTimeout, usePool, sslSocketFactory, sslParameters,
				hostnameVerifier);
	}

	public RedisConnectionFactoryBean(final Set<ShardedRedisNode> redisNodes, final int database,
			final int connectTimeout, final int soTimeout, final boolean usePool, final boolean useSsl,
			final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
			final HostnameVerifier hostnameVerifier){
		super(redisNodes, database, connectTimeout, soTimeout, usePool, useSsl, sslSocketFactory, sslParameters,
				hostnameVerifier);
	}

	@Override
	public Class<? extends RedisConnection> getObjectType(){
		return connection.getClass();
	}

	@Override
	public C getObject() throws Exception{
		return connection;
	}

	@Override
	public boolean isSingleton(){
		return true;
	}

	@Override
	public void destroy() throws Exception{
		beforeDestroy(connection);
		try{
			connection.close();
		}catch(IOException e){
			logger.error("Redis connection close error.", e);
		}
		afterDestroy(connection);
	}

	protected void setConnection(C connection){
		this.connection = connection;
	}

	protected void beforeDestroy(C connection){

	}

	protected void afterDestroy(C connection){

	}

}
