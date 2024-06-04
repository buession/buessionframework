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
import com.buession.redis.client.lettuce.LettuceSentinelClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;

/**
 * Lettuce 哨兵模式 HyperLogLog 命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceSentinelHyperLogLogOperations extends AbstractHyperLogLogOperations<LettuceSentinelClient> {

	public LettuceSentinelHyperLogLogOperations(final LettuceSentinelClient client) {
		super(client);
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements) {
		final CommandArguments args = CommandArguments.create("key", key).put("elements", (Object[]) elements);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<>(client, ProtocolCommand.PFADD, (cmd)->cmd.pfadd(key, elements),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<>(client, ProtocolCommand.PFADD,
					(cmd)->cmd.pfadd(key, elements),
					oneStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.PFADD, (cmd)->cmd.pfadd(key, elements),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<>(client, ProtocolCommand.PFMERGE,
					(cmd)->cmd.pfmerge(destKey, keys),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<>(client, ProtocolCommand.PFMERGE,
					(cmd)->cmd.pfmerge(destKey, keys),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.PFMERGE, (cmd)->cmd.pfmerge(destKey, keys),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long pfCount(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new LettuceSentinelPipelineCommand<>(client, ProtocolCommand.PFMERGE, (cmd)->cmd.pfcount(keys),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceSentinelTransactionCommand<>(client, ProtocolCommand.PFMERGE, (cmd)->cmd.pfcount(keys),
					(v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.PFMERGE, (cmd)->cmd.pfcount(keys), (v)->v)
					.run(args);
		}
	}

}
