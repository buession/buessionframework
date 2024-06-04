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

import com.buession.core.converter.Converter;
import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceSentinelClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import io.lettuce.core.TransactionResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Lettuce 哨兵模式事务命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceSentinelTransactionOperations extends AbstractTransactionOperations<LettuceSentinelClient> {

	public LettuceSentinelTransactionOperations(final LettuceSentinelClient client) {
		super(client);
	}

	@Override
	public Status multi() {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<>(client, ProtocolCommand.MULTI, (cmd)->cmd.multi(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<>(client, ProtocolCommand.MULTI, (cmd)->cmd.multi(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceSentinelCommand<>(client, ProtocolCommand.MULTI, (cmd)->cmd.multi(), okStatusConverter)
					.run();
		}
	}

	@Override
	public List<Object> exec() {
		final Converter<TransactionResult, List<Object>> converter = (v)->v.stream().collect(Collectors.toList());

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<>(client, ProtocolCommand.MULTI, (cmd)->cmd.exec(), converter)
					.run();
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<>(client, ProtocolCommand.MULTI, (cmd)->cmd.exec(), converter)
					.run();
		}else{
			return new LettuceSentinelCommand<>(client, ProtocolCommand.MULTI, (cmd)->cmd.exec(), converter)
					.run();
		}
	}

	@Override
	public void discard() {
		if(isPipeline()){
			new LettuceSentinelPipelineCommand<>(client, ProtocolCommand.DISCARD, (cmd)->cmd.discard(), (v)->v)
					.run();
		}else if(isTransaction()){
			new LettuceSentinelTransactionCommand<>(client, ProtocolCommand.DISCARD, (cmd)->cmd.discard(), (v)->v)
					.run();
		}else{
			new LettuceSentinelCommand<>(client, ProtocolCommand.DISCARD, (cmd)->cmd.discard(), (v)->v)
					.run();
		}
	}

	@Override
	public Status watch(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<>(client, ProtocolCommand.WATCH, (cmd)->cmd.watch(keys),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<>(client, ProtocolCommand.WATCH, (cmd)->cmd.watch(keys),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceSentinelCommand<>(client, ProtocolCommand.WATCH, (cmd)->cmd.watch(keys),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status unwatch() {
		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<>(client, ProtocolCommand.UNWATCH, (cmd)->cmd.unwatch(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<>(client, ProtocolCommand.UNWATCH, (cmd)->cmd.unwatch(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceSentinelCommand<>(client, ProtocolCommand.UNWATCH, (cmd)->cmd.unwatch(),
					okStatusConverter)
					.run();
		}
	}

}
