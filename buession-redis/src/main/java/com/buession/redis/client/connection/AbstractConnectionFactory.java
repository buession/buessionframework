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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection;

import com.buession.redis.client.connection.datasource.ClusterDataSource;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.client.connection.datasource.SentinelDataSource;

/**
 * Redis 连接工厂抽象类
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public abstract class AbstractConnectionFactory<DS extends DataSource> implements RedisConnectionFactory {

	private final DS dataSource;

	private RedisConnection redisConnection;

	public AbstractConnectionFactory(final DS dataSource) {
		this.dataSource = dataSource;
	}

	public boolean isRedisSentinelAware() {
		return dataSource instanceof SentinelDataSource;
	}

	public boolean isRedisClusterAware() {
		return dataSource instanceof ClusterDataSource;
	}

	@Override
	public RedisConnection getConnection() {
		if(redisConnection == null){
			synchronized(this){
				if(isRedisClusterAware()){
					redisConnection = getClusterConnection();
				}else if(isRedisSentinelAware()){
					redisConnection = getSentinelConnection();
				}else{
					redisConnection = getStandaloneConnection();
				}
			}
		}
		
		if(redisConnection.isConnect() == false){
			redisConnection.connect();
		}

		return redisConnection;
	}

	protected DS getDataSource() {
		return dataSource;
	}

}
