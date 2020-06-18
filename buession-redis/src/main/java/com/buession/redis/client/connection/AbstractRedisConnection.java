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
package com.buession.redis.client.connection;

import com.buession.core.Executor;
import com.buession.lang.Status;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.exception.RedisConnectionFailureException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.transaction.Transaction;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.IOException;

/**
 * Redis 链接对象抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractRedisConnection<T> implements RedisConnection {

	private DataSource dataSource;

	private String clientName;

	private boolean useSsl;

	private SslConfiguration sslConfiguration;

	protected Transaction transaction;

	public AbstractRedisConnection(){
	}

	public AbstractRedisConnection(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public AbstractRedisConnection(DataSource dataSource, SslConfiguration sslConfiguration){
		this.dataSource = dataSource;
		this.sslConfiguration = sslConfiguration;
	}

	public AbstractRedisConnection(DataSource dataSource, String clientName){
		this.dataSource = dataSource;
		this.clientName = clientName;
	}

	public AbstractRedisConnection(DataSource dataSource, String clientName, SslConfiguration sslConfiguration){
		this(dataSource, clientName);
		this.sslConfiguration = sslConfiguration;
	}

	public AbstractRedisConnection(DataSource dataSource, boolean useSsl){
		this.dataSource = dataSource;
		this.useSsl = useSsl;
	}

	public AbstractRedisConnection(DataSource dataSource, boolean useSsl, SslConfiguration sslConfiguration){
		this(dataSource, useSsl);
		this.sslConfiguration = sslConfiguration;
	}

	public AbstractRedisConnection(DataSource dataSource, String clientName, boolean useSsl){
		this(dataSource, clientName);
		this.useSsl = useSsl;
	}

	public AbstractRedisConnection(DataSource dataSource, String clientName, boolean useSsl,
			SslConfiguration sslConfiguration){
		this(dataSource, clientName, useSsl);
		this.sslConfiguration = sslConfiguration;
	}

	@Override
	public DataSource getDataSource(){
		return dataSource;
	}

	@Override
	public void setDataSource(DataSource dataSource){
		this.dataSource = dataSource;
	}

	public String getClientName(){
		return clientName;
	}

	public void setClientName(String clientName){
		this.clientName = clientName;
	}

	public boolean isUseSsl(){
		return useSsl;
	}

	public void setUseSsl(final boolean useSsl){
		this.useSsl = useSsl;
	}

	public SslConfiguration getSslConfiguration(){
		return sslConfiguration;
	}

	public void setSslConfiguration(SslConfiguration sslConfiguration){
		this.sslConfiguration = sslConfiguration;
	}

	@Override
	public Status connect() throws IOException{
		if(isClosed()){
			doConnect();
		}

		return Status.SUCCESS;
	}

	@Override
	public <C, R> R execute(final Executor<C, R> executor) throws RedisException{
		try{
			return doExecute((Executor<T, R>) executor);
		}catch(JedisConnectionException e){
			throw new RedisConnectionFailureException(e.getMessage(), e);
		}
	}

	@Override
	public boolean isConnect(){
		return getDataSource() != null && checkConnect();
	}

	@Override
	public boolean isClosed(){
		return getDataSource() == null || checkClosed();
	}

	@Override
	public Transaction getTransaction(){
		return transaction;
	}

	@Override
	public void disconnect() throws IOException{
		if(getDataSource() != null){
			doDisconnect();
		}
	}

	@Override
	public void close() throws IOException{
		if(getDataSource() != null){
			doClose();
		}
	}

	protected abstract void doConnect() throws IOException;

	protected abstract <R> R doExecute(final Executor<T, R> executor) throws RedisException;

	protected abstract boolean checkConnect();

	protected abstract boolean checkClosed();

	protected abstract void doDisconnect() throws IOException;

	protected abstract void doClose() throws IOException;

}
