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
package com.buession.redis.client.operations;

import com.buession.core.converter.BooleanStatusConverter;
import com.buession.core.converter.ListConverter;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;
import com.buession.redis.core.internal.convert.response.OneBooleanConverter;
import com.buession.redis.core.internal.convert.response.OneStatusConverter;
import com.buession.redis.utils.SafeEncoder;

/**
 * Redis 命令操作抽象类
 *
 * @param <C>
 * 		Redis Client {@link RedisClient}
 *
 * @author Yong.Teng
 */
public abstract class AbstractRedisOperations<C extends RedisClient> implements RedisOperations {

	protected C client;

	protected final ListConverter<byte[], String> binaryToStringListConverter =
			new ListConverter<>(SafeEncoder::encode);

	protected final static OkStatusConverter okStatusConverter = new OkStatusConverter();

	protected final static BooleanStatusConverter booleanStatusConverter = new BooleanStatusConverter();

	protected final static OneStatusConverter oneStatusConverter = new OneStatusConverter();

	protected final static OneBooleanConverter oneBooleanConverter = new OneBooleanConverter();

	public AbstractRedisOperations(final C client) {
		this.client = client;
	}

	protected boolean isPipeline() {
		return client.getConnection().isPipeline();
	}

	protected boolean isTransaction() {
		return client.getConnection().isTransaction();
	}

	protected boolean isMulti() {
		final RedisConnection connection = client.getConnection();
		return connection.isPipeline() || connection.isTransaction();
	}

}
