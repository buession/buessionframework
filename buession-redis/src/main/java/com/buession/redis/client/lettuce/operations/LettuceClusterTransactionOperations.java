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
import com.buession.redis.core.command.ProtocolCommand;

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
	public void discard() {
		if(isPipeline()){
			new LettuceClusterPipelineCommand<>(client, ProtocolCommand.DISCARD)
					.run();
		}else if(isTransaction()){
			new LettuceClusterTransactionCommand<>(client, ProtocolCommand.DISCARD)
					.run();
		}else{
			new LettuceClusterCommand<>(client, ProtocolCommand.DISCARD)
					.run();
		}
	}

	@Override
	public List<Object> exec() {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<List<Object>, List<Object>>(client, ProtocolCommand.MULTI)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<List<Object>, List<Object>>(client, ProtocolCommand.MULTI)
					.run();
		}else{
			return new LettuceClusterCommand<List<Object>, List<Object>>(client, ProtocolCommand.MULTI)
					.run();
		}
	}

	@Override
	public Status multi() {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<Status, Status>(client, ProtocolCommand.MULTI)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<Status, Status>(client, ProtocolCommand.MULTI)
					.run();
		}else{
			return new LettuceClusterCommand<Status, Status>(client, ProtocolCommand.MULTI)
					.run();
		}
	}

	@Override
	public Status unwatch() {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<Status, Status>(client, ProtocolCommand.UNWATCH)
					.run();
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<Status, Status>(client, ProtocolCommand.UNWATCH)
					.run();
		}else{
			return new LettuceClusterCommand<Status, Status>(client, ProtocolCommand.UNWATCH)
					.run();
		}
	}

	@Override
	public Status watch(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<Status, Status>(client, ProtocolCommand.WATCH)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<Status, Status>(client, ProtocolCommand.WATCH)
					.run(args);
		}else{
			return new LettuceClusterCommand<Status, Status>(client, ProtocolCommand.WATCH)
					.run(args);
		}
	}

}
