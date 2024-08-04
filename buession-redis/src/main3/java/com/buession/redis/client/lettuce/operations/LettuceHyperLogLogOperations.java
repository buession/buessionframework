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
import com.buession.redis.core.command.Command;

/**
 * Lettuce 单机模式 HyperLogLog 命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceHyperLogLogOperations extends AbstractHyperLogLogOperations<LettuceStandaloneClient> {

	public LettuceHyperLogLogOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements) {
		final CommandArguments args = CommandArguments.create(key).add(elements);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PFADD, (cmd)->cmd.pfadd(key, elements),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PFADD, (cmd)->cmd.pfadd(key, elements),
					oneStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.PFADD, (cmd)->cmd.pfadd(key, elements), oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long pfCount(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(keys);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PFCOUNT, (cmd)->cmd.pfcount(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PFCOUNT, (cmd)->cmd.pfcount(keys), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.PFCOUNT, (cmd)->cmd.pfcount(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create(destKey).add(keys);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PFMERGE, (cmd)->cmd.pfmerge(destKey, keys),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PFMERGE, (cmd)->cmd.pfmerge(destKey, keys),
					okStatusConverter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.PFMERGE, (cmd)->cmd.pfmerge(destKey, keys), okStatusConverter)
					.run(args);
		}
	}

}
