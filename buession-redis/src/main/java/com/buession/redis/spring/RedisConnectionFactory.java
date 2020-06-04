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
import com.buession.redis.Constants;
import com.buession.redis.core.RedisNode;
import com.buession.redis.core.ShardedRedisNode;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class RedisConnectionFactory {

	private String host = RedisNode.DEFAULT_HOST;

	private int port = RedisNode.DEFAULT_PORT;

	private String password;

	private int database = RedisNode.DEFAULT_DATABASE;

	private String clientName;

	private Set<ShardedRedisNode> redisNodes;

	protected int connectTimeout = Constants.DEFAULT_CONNECT_TIMEOUT;

	protected int soTimeout = Constants.DEFAULT_SO_TIMEOUT;

	protected boolean usePool = true;

	protected boolean useSsl = false;

	protected SSLSocketFactory sslSocketFactory;

	protected SSLParameters sslParameters;

	protected HostnameVerifier hostnameVerifier;

	private boolean isGenericConnection = true;

	private boolean isShardedConnection = false;

	public RedisConnectionFactory(){
	}

	public RedisConnectionFactory(final String host){
		this.host = host;
	}

	public RedisConnectionFactory(final String host, final String password){
		this.host = host;
		this.password = password;
	}

	public RedisConnectionFactory(final String host, final String password, final int database){
		this(host, password);
		setDatabase(database);
	}

	public RedisConnectionFactory(final String host, final String password, final int database, final boolean usePool){
		this(host, password, database);
		this.usePool = usePool;
	}

	public RedisConnectionFactory(final String host, final String password, final int database,
			final int connectTimeout, final int soTimeout){
		this(host, password, database);
		this.connectTimeout = connectTimeout;
		this.soTimeout = soTimeout;
	}

	public RedisConnectionFactory(final String host, final String password, final boolean usePool){
		this(host, password);
		this.usePool = usePool;
	}

	public RedisConnectionFactory(final String host, final String password, final int connectTimeout,
			final int soTimeout){
		this(host, password);
		this.connectTimeout = connectTimeout;
		this.soTimeout = soTimeout;
	}

	public RedisConnectionFactory(final String host, final int port){
		this.host = host;
		this.port = port;
	}

	public RedisConnectionFactory(final String host, final int port, final int database){
		this(host, port);
		setDatabase(database);
	}

	public RedisConnectionFactory(final String host, final int port, final String password){
		this(host, port);
		this.password = password;
	}

	public RedisConnectionFactory(final String host, final int port, final String password, final int database){
		this(host, port, password);
		setDatabase(database);
	}

	public RedisConnectionFactory(final String host, final int port, final String password, final int database,
			final boolean usePool){
		this(host, port, password, database);
		this.usePool = usePool;
	}

	public RedisConnectionFactory(final String host, final int port, final String password, final int database,
			final int connectTimeout, final int soTimeout){
		this(host, port, password, database);
		this.connectTimeout = connectTimeout;
		this.soTimeout = soTimeout;
	}

	public RedisConnectionFactory(final String host, final int port, final String password, final int database,
			final int connectTimeout, final int soTimeout, final boolean usePool){
		this(host, port, password, database, connectTimeout, soTimeout);
		this.usePool = usePool;
	}

	public RedisConnectionFactory(final String host, final int port, final String password, final int database,
			final String clientName, final int connectTimeout, final int soTimeout){
		this(host, port, password, database, connectTimeout, soTimeout);
		this.clientName = clientName;
	}

	public RedisConnectionFactory(final String host, final int port, final String password, final int database,
			final String clientName, final boolean usePool){
		this(host, port, password, database, usePool);
		this.clientName = clientName;
	}

	public RedisConnectionFactory(final String host, final int port, final String password, final int database,
			final String clientName, final int connectTimeout, final int soTimeout, final boolean usePool){
		this(host, port, password, database, connectTimeout, soTimeout, usePool);
		this.clientName = clientName;
	}

	public RedisConnectionFactory(final String host, final int port, final String password, final int database,
			final String clientName, final int connectTimeout, final int soTimeout, final boolean usePool,
			final boolean useSsl){
		this(host, port, password, database, clientName, connectTimeout, soTimeout, usePool);
		this.useSsl = useSsl;
	}

	public RedisConnectionFactory(final String host, final int port, final String password, final int database,
			final String clientName, final int connectTimeout, final int soTimeout, final boolean usePool,
			final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
			final HostnameVerifier hostnameVerifier){
		this(host, port, password, database, clientName, connectTimeout, soTimeout, usePool,
				checkUseSsl(sslSocketFactory, sslParameters, hostnameVerifier), sslSocketFactory, sslParameters,
				hostnameVerifier);
	}

	public RedisConnectionFactory(final String host, final int port, final String password, final int database,
			final String clientName, final int connectTimeout, final int soTimeout, final boolean usePool,
			final boolean useSsl, final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
			final HostnameVerifier hostnameVerifier){
		this(host, port, password, database, clientName, connectTimeout, soTimeout, usePool, useSsl);
		this.sslSocketFactory = sslSocketFactory;
		this.sslParameters = sslParameters;
		this.hostnameVerifier = hostnameVerifier;
	}

	public RedisConnectionFactory(final Set<ShardedRedisNode> redisNodes){
		this.redisNodes = redisNodes;
		this.isShardedConnection = true;
	}

	public RedisConnectionFactory(final Set<ShardedRedisNode> redisNodes, final int database){
		this(redisNodes);
		setDatabase(database);
	}

	public RedisConnectionFactory(final Set<ShardedRedisNode> redisNodes, final int database, final boolean usePool){
		this(redisNodes, database);
		this.usePool = usePool;
	}

	public RedisConnectionFactory(final Set<ShardedRedisNode> redisNodes, final boolean usePool){
		this(redisNodes);
		this.usePool = usePool;
	}

	public RedisConnectionFactory(final Set<ShardedRedisNode> redisNodes, final int connectTimeout,
			final int soTimeout){
		this(redisNodes);
		this.connectTimeout = connectTimeout;
		this.soTimeout = soTimeout;
	}

	public RedisConnectionFactory(final Set<ShardedRedisNode> redisNodes, final int database, final int connectTimeout
			, final int soTimeout){
		this(redisNodes, connectTimeout, soTimeout);
		setDatabase(database);
	}

	public RedisConnectionFactory(final Set<ShardedRedisNode> redisNodes, final int connectTimeout,
			final int soTimeout, final boolean usePool){
		this(redisNodes, connectTimeout, soTimeout);
		this.usePool = usePool;
	}

	public RedisConnectionFactory(final Set<ShardedRedisNode> redisNodes, final int database, final int connectTimeout
			, final int soTimeout, final boolean usePool){
		this(redisNodes, connectTimeout, soTimeout, usePool);
		setDatabase(database);
	}

	public RedisConnectionFactory(final Set<ShardedRedisNode> redisNodes, final int database, final int connectTimeout
			, final int soTimeout, final boolean usePool, final SSLSocketFactory sslSocketFactory,
			final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){
		this(redisNodes, database, connectTimeout, soTimeout, usePool, checkUseSsl(sslSocketFactory, sslParameters,
				hostnameVerifier), sslSocketFactory, sslParameters, hostnameVerifier);
	}

	public RedisConnectionFactory(final Set<ShardedRedisNode> redisNodes, final int database, final int connectTimeout
			, final int soTimeout, final boolean usePool, final boolean useSsl,
			final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
			final HostnameVerifier hostnameVerifier){
		this(redisNodes, connectTimeout, soTimeout, usePool);
		this.useSsl = useSsl;
		this.sslSocketFactory = sslSocketFactory;
		this.sslParameters = sslParameters;
		this.hostnameVerifier = hostnameVerifier;
		setDatabase(database);
	}

	public String getHost(){
		return host;
	}

	public int getPort(){
		return port;
	}

	public String getPassword(){
		return password;
	}

	public int getDatabase(){
		return database;
	}

	public void setDatabase(int database){
		Assert.isNegative(database, "invalid DB index (a positive index required)");
		this.database = database;
	}

	public String getClientName(){
		return clientName;
	}

	public Set<ShardedRedisNode> getRedisNodes(){
		return redisNodes;
	}

	public int getConnectTimeout(){
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout){
		this.connectTimeout = connectTimeout;
	}

	public int getSoTimeout(){
		return soTimeout;
	}

	public void setSoTimeout(int soTimeout){
		this.soTimeout = soTimeout;
	}

	public boolean isUsePool(){
		return usePool;
	}

	public void setUsePool(boolean usePool){
		this.usePool = usePool;
	}

	public boolean isUseSsl(){
		return useSsl;
	}

	public void setUseSsl(boolean useSsl){
		this.useSsl = useSsl;
	}

	public SSLSocketFactory getSslSocketFactory(){
		return sslSocketFactory;
	}

	public void setSslSocketFactory(final SSLSocketFactory sslSocketFactory){
		this.sslSocketFactory = sslSocketFactory;
	}

	public SSLParameters getSslParameters(){
		return sslParameters;
	}

	public void setSslParameters(final SSLParameters sslParameters){
		this.sslParameters = sslParameters;
	}

	public HostnameVerifier getHostnameVerifier(){
		return hostnameVerifier;
	}

	public void setHostnameVerifier(final HostnameVerifier hostnameVerifier){
		this.hostnameVerifier = hostnameVerifier;
	}

	protected boolean isGenericConnection(){
		return isGenericConnection;
	}

	protected boolean isShardedConnection(){
		return isShardedConnection;
	}

	protected final static boolean checkUseSsl(final SSLSocketFactory sslSocketFactory,
			final SSLParameters sslParameters, final HostnameVerifier hostnameVerifier){
		return sslSocketFactory != null || sslParameters != null || hostnameVerifier != null;
	}

}
