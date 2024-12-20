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
package com.buession.redis.core.internal;

import com.buession.core.converter.Converter;
import com.buession.redis.client.RedisClient;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.core.AbstractRedisCommand;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.RedisException;

/**
 * Redis 运算命令抽象类
 *
 * @author Yong.Teng
 * @since 2.3.0
 */
public abstract class AbstractRedisOperationsCommand<CLIENT extends RedisClient, CONN extends RedisConnection, CXT,
		OSR, SR, R> extends AbstractRedisCommand<CLIENT, R> {

	protected final CONN connection;

	protected final Executor<CXT, OSR> executor;

	protected final Converter<SR, R> converter;

	@SuppressWarnings({"unchecked"})
	public AbstractRedisOperationsCommand(final CLIENT client, final ProtocolCommand command) {
		this(client, command, null, (value)->(R) value);
	}

	@SuppressWarnings({"unchecked"})
	public AbstractRedisOperationsCommand(final CLIENT client, final ProtocolCommand command,
										  final Executor<CXT, OSR> executor, final Converter<SR, R> converter) {
		super(client, command);
		connection = (CONN) client.getConnection();
		this.executor = executor;
		this.converter = converter;
	}

	protected abstract R doExecute() throws RedisException;

}
