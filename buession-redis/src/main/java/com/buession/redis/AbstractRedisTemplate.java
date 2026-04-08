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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis;

import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.core.Options;
import com.buession.redis.core.command.*;

import java.util.List;

/**
 * @author Yong.Teng
 */
public abstract class AbstractRedisTemplate extends RedisAccessor implements AutoSuggestCommands, BitMapCommands,
		BloomFilterCommands, ClusterCommands, ConnectionCommands, CountMinSketchCommands, CuckooFilterCommands,
		GenericCommands, GeoCommands, HashCommands, HyperLogLogCommands, JsonCommands, KeyCommands, ListCommands,
		PubSubCommands, ScriptingCommands, SearchCommands, ServerCommands, SetCommands, SortedSetCommands,
		StreamCommands, StringCommands, TDigestCommands, TimeSeriesCommands, TopKCommands, TransactionCommands,
		VectorSetCommands {

	/**
	 * 构造函数
	 */
	public AbstractRedisTemplate() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		数据源
	 */
	public AbstractRedisTemplate(DataSource dataSource) {
		super(dataSource);
	}

	/**
	 * 构造函数
	 *
	 * @param dataSource
	 * 		数据源
	 * @param options
	 * 		配置选项
	 */
	public AbstractRedisTemplate(DataSource dataSource, Options options) {
		super(dataSource, options);
	}

	@Override
	public Status discard() {
		try{
			//execute((client)->client.transactionCommands().discard());
			return client.getConnection().discard();
		}finally{
			RedisConnectionUtils.releaseConnection(connectionFactory, client.getConnection(), enableTransactionSupport);
		}
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public List<Object> exec() {
		try{
			//execute((client)->client.transactionCommands().exec());
			return client.getConnection().exec();
		}finally{
			RedisConnectionUtils.releaseConnection(connectionFactory, client.getConnection(), enableTransactionSupport);
		}
	}

}
