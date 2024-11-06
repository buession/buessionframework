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

import com.buession.lang.KeyValue;
import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.jedis.response.KeyValueConverter;

/**
 * Jedis 哨兵模式一般命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisSentinelGenericOperations extends AbstractGenericOperations<JedisSentinelClient> {

	public JedisSentinelGenericOperations(final JedisSentinelClient client) {
		super(client);
	}

	@Override
	public Long wait(final int replicas, final int timeout) {
		final CommandArguments args = CommandArguments.create(replicas).add(timeout);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.WAIT,
					(cmd)->cmd.waitReplicas(replicas, timeout), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.WAIT,
					(cmd)->cmd.waitReplicas(replicas, timeout), (v)->v)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.WAIT, (cmd)->cmd.waitReplicas(replicas, timeout),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public KeyValue<Long, Long> waitOf(final int locals, final int replicas, final int timeout) {
		final CommandArguments args = CommandArguments.create(locals).add(replicas).add(timeout);
		final KeyValueConverter<Long, Long, Long, Long> keyValueConverter = new KeyValueConverter<>((k)->k, (v)->v);

		if(isPipeline()){
			return new JedisSentinelPipelineCommand<>(client, ProtocolCommand.WAITOF,
					(cmd)->cmd.waitAOF((String) null, locals, replicas, timeout), keyValueConverter)
					.run(args);
		}else if(isTransaction()){
			return new JedisSentinelTransactionCommand<>(client, ProtocolCommand.WAITOF,
					(cmd)->cmd.waitAOF((String) null, locals, replicas, timeout), keyValueConverter)
					.run(args);
		}else{
			return new JedisSentinelCommand<>(client, ProtocolCommand.WAITOF,
					(cmd)->cmd.waitAOF(locals, replicas, timeout), keyValueConverter)
					.run(args);
		}
	}

}
