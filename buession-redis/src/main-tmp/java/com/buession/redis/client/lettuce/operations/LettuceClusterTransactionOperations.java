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
package com.buession.redis.client.lettuce.operations;

import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceClusterClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;

import java.util.List;

/**
 * Lettuce 集群模式事务命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceClusterTransactionOperations extends AbstractTransactionOperations<LettuceClusterClient> {

	public LettuceClusterTransactionOperations(final LettuceClusterClient client) {
		super(client);
	}

	@Override
	public Status multi() {
		return notCommand(client, Command.MULTI);
	}

	@Override
	public List<Object> exec() {
		return notCommand(client, Command.EXEC);
	}

	@Override
	public void discard() {
		notCommand(client, Command.DISCARD);
	}

	@Override
	public Status watch(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return notCommand(client, Command.WATCH, args);
	}

	@Override
	public Status watch(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return notCommand(client, Command.WATCH, args);
	}

	@Override
	public Status unwatch() {
		return notCommand(client, Command.UNWATCH);
	}

}
