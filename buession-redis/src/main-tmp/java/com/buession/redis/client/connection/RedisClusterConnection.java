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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.connection;

import com.buession.redis.core.RedisMode;
import com.buession.redis.core.command.Command;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.pipeline.Pipeline;
import com.buession.redis.transaction.Transaction;

import java.util.List;

/**
 * Redis 集群连接器
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface RedisClusterConnection extends RedisConnection {

	/**
	 * 返回最大重定向次数
	 *
	 * @return 最大重定向次数
	 */
	int getMaxRedirects();

	/**
	 * 设置最大重定向次数
	 *
	 * @param maxRedirects
	 * 		最大重定向次数
	 */
	void setMaxRedirects(int maxRedirects);

	/**
	 * 返回最大重试持续时长（单位：秒）
	 *
	 * @return 最大重试持续时长
	 */
	int getMaxTotalRetriesDuration();

	/**
	 * 设置最大重试持续时长（单位：秒）
	 *
	 * @param maxTotalRetriesDuration
	 * 		最大重试持续时长
	 */
	void setMaxTotalRetriesDuration(int maxTotalRetriesDuration);

	@Override
	default Pipeline openPipeline() {
		throw new NotSupportedCommandException("Pipeline is currently not supported for JedisClusterConnection.");
	}

	@Override
	default void closePipeline() {
		throw new NotSupportedCommandException("Pipeline is currently not supported for JedisClusterConnection.");
	}

	@Override
	default Transaction multi() {
		throw new NotSupportedCommandException(RedisMode.CLUSTER, Command.MULTI);
	}

	@Override
	default List<Object> exec() throws RedisException {
		throw new NotSupportedCommandException(RedisMode.CLUSTER, Command.EXEC);
	}

	@Override
	default void discard() throws RedisException {
		throw new NotSupportedCommandException(RedisMode.CLUSTER, Command.DISCARD);
	}

}
