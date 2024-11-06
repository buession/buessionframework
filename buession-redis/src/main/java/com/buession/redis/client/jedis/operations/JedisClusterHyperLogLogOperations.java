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
package com.buession.redis.client.jedis.operations;

import com.buession.lang.Status;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;

/**
 * Jedis 集群模式 HyperLogLog 命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisClusterHyperLogLogOperations extends AbstractHyperLogLogOperations<JedisClusterClient> {

	public JedisClusterHyperLogLogOperations(final JedisClusterClient client) {
		super(client);
	}

	@Override
	public Status pfAdd(final String key, final String... elements) {
		final CommandArguments args = CommandArguments.create(key).add(elements);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.PFADD, (cmd)->cmd.pfadd(key, elements),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.PFADD, (cmd)->cmd.pfadd(key, elements),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.PFADD, (cmd)->cmd.pfadd(key, elements),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements) {
		final CommandArguments args = CommandArguments.create(key).add(elements);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.PFADD, (cmd)->cmd.pfadd(key, elements),
					oneStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.PFADD, (cmd)->cmd.pfadd(key, elements),
					oneStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.PFADD, (cmd)->cmd.pfadd(key, elements),
					oneStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pfMerge(final String destKey, final String... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.PFMERGE, (cmd)->cmd.pfmerge(destKey, keys),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.PFMERGE,
					(cmd)->cmd.pfmerge(destKey, keys), okStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.PFMERGE, (cmd)->cmd.pfmerge(destKey, keys),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Status pfMerge(final byte[] destKey, final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("destKey", destKey).put("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.PFMERGE, (cmd)->cmd.pfmerge(destKey, keys),
					okStatusConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.PFMERGE,
					(cmd)->cmd.pfmerge(destKey, keys), okStatusConverter)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.PFMERGE, (cmd)->cmd.pfmerge(destKey, keys),
					okStatusConverter)
					.run(args);
		}
	}

	@Override
	public Long pfCount(final String... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.PFCOUNT, (cmd)->cmd.pfcount(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.PFCOUNT, (cmd)->cmd.pfcount(keys),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.PFCOUNT, (cmd)->cmd.pfcount(keys), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long pfCount(final byte[]... keys) {
		final CommandArguments args = CommandArguments.create("keys", (Object[]) keys);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.PFCOUNT, (cmd)->cmd.pfcount(keys), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.PFCOUNT, (cmd)->cmd.pfcount(keys),
					(v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.PFCOUNT, (cmd)->cmd.pfcount(keys), (v)->v)
					.run(args);
		}
	}

}
