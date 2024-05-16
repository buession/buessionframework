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
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.response.OkStatusConverter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Lettuce 单机模式事务命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceTransactionOperations extends AbstractTransactionOperations<LettuceStandaloneClient> {

	public LettuceTransactionOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public Status multi() {
		return new LettuceCommand<>(client, ProtocolCommand.MULTI, (cmd)->cmd.multi(), OkStatusConverter.INSTANCE)
				.run();
	}

	@Override
	public List<Object> exec() {
		return new LettuceCommand<>(client, ProtocolCommand.MULTI, (cmd)->cmd.exec(),
				(value)->value.stream().collect(Collectors.toList()))
				.run();
	}

	@Override
	public void discard() {
		new LettuceCommand<>(client, ProtocolCommand.DISCARD, (cmd)->cmd.discard(), (value)->value)
				.run();
	}

	@Override
	public Status watch(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);
		return new LettuceCommand<>(client, ProtocolCommand.WATCH, (cmd)->cmd.watch(keys), OkStatusConverter.INSTANCE)
				.run(args);
	}

	@Override
	public Status unwatch() {
		return new LettuceCommand<>(client, ProtocolCommand.UNWATCH, (cmd)->cmd.unwatch(), OkStatusConverter.INSTANCE)
				.run();
	}

}
