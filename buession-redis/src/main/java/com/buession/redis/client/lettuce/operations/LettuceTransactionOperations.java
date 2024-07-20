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
import com.buession.redis.core.internal.convert.lettuce.response.TransactionResultConverter;

import java.util.List;

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
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.MULTI, (cmd)->cmd.multi(), okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.MULTI, (cmd)->cmd.multi(), okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.MULTI, (cmd)->cmd.multi(), okStatusConverter)
					.run();
		}
	}

	@Override
	public List<Object> exec() {
		final TransactionResultConverter transactionResultConverter = new TransactionResultConverter();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.MULTI, (cmd)->cmd.exec(),
					transactionResultConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.MULTI, (cmd)->cmd.exec(),
					transactionResultConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.MULTI, (cmd)->cmd.exec(), transactionResultConverter)
					.run();
		}
	}

	@Override
	public void discard() {
		if(isPipeline()){
			new LettucePipelineCommand<>(client, ProtocolCommand.DISCARD, (cmd)->cmd.discard(), (v)->v)
					.run();
		}else if(isTransaction()){
			new LettuceTransactionCommand<>(client, ProtocolCommand.DISCARD, (cmd)->cmd.discard(), (v)->v)
					.run();
		}else{
			new LettuceCommand<>(client, ProtocolCommand.DISCARD, (cmd)->cmd.discard(), (v)->v)
					.run();
		}
	}

	@Override
	public Status watch(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.WATCH, (cmd)->cmd.watch(keys),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.WATCH, (cmd)->cmd.watch(keys),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.WATCH, (cmd)->cmd.watch(keys), okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status unwatch() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.UNWATCH, (cmd)->cmd.unwatch(),
					okStatusConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.UNWATCH, (cmd)->cmd.unwatch(),
					okStatusConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.UNWATCH, (cmd)->cmd.unwatch(), okStatusConverter)
					.run();
		}
	}

}
