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

import com.buession.lang.Status;
import com.buession.redis.client.connection.datasource.RedisDataSource;
import com.buession.redis.core.Transaction;

import java.io.IOException;

/**
 * Redis 链接对象抽象类
 *
 * @author Yong.Teng
 */
public abstract class AbstractRedisConnection implements RedisConnection {

	protected Transaction transaction;

	private RedisDataSource dataSource;

	public AbstractRedisConnection(){
	}

	public AbstractRedisConnection(RedisDataSource dataSource){
		this.dataSource = dataSource;
	}

	@Override
	public RedisDataSource getDataSource(){
		return dataSource;
	}

	@Override
	public void setDataSource(RedisDataSource dataSource){
		this.dataSource = dataSource;
	}

	@Override
	public Status connect(){
		if(isClosed()){
			dataSource.connect();
		}

		return Status.SUCCESS;
	}

	@Override
	public void disconnect() throws IOException{
		if(getDataSource() != null){
			getDataSource().disconnect();
		}
	}

	@Override
	public boolean isClosed(){
		return getDataSource() == null ? true : getDataSource().isClosed();
	}

	@Override
	public void close() throws IOException{
		if(getDataSource() != null){
			getDataSource().close();
		}
	}

}
