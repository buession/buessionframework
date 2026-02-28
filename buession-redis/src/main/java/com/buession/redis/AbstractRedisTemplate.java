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

import com.buession.core.validator.Validate;
import com.buession.lang.Status;
import com.buession.redis.client.connection.datasource.DataSource;
import com.buession.redis.core.command.*;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Yong.Teng
 */
public abstract class AbstractRedisTemplate extends RedisAccessor
		implements BloomFilterCommands, BitMapCommands, CuckooFilterCommands, ClusterCommands, CountMinSketchCommands,
		ConnectionCommands, GenericCommand, GeoCommands, HashCommands, HyperLogLogCommands, JsonCommands, ListCommands,
		PubSubCommands, ScriptingCommands,/*SearchCommands, */ServerCommands, SetCommands, SortedSetCommands,
		StreamCommands,
		TransactionCommands, KeyCommands, StringCommands {

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

	@Override
	public Status discard() {
		Status result = execute((client)->{
			client.getConnection().discard();
			return null;
		});
		resetTransactionOrPipeline();
		return result;
	}

	@SuppressWarnings({"unchecked"})
	@Override
	public List<Object> exec() {
		List<Object> result = execute((client)->client.getConnection().exec());

		if(result != null){
			Map<Integer, Function<?, ?>> map = txConverters.get();

			if(Validate.isNotEmpty(map)){
				for(int i = 0, j = result.size(); i < j; i++){
					Function<Object, Object> fun = (Function<Object, Object>) map.get(i);

					if(fun != null){
						result.set(i, fun.apply(result.get(i)));
					}
				}
			}
		}

		resetTransactionOrPipeline();

		return result;
	}

}
