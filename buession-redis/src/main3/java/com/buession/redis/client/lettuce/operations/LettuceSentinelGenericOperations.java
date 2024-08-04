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

import com.buession.lang.KeyValue;
import com.buession.redis.client.lettuce.LettuceSentinelClient;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;

/**
 * Lettuce 哨兵模式一般命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceSentinelGenericOperations extends AbstractGenericOperations<LettuceSentinelClient> {

	public LettuceSentinelGenericOperations(final LettuceSentinelClient client) {
		super(client);
	}

	@Override
	public Long wait(final int replicas, final int timeout) {
		final CommandArguments args = CommandArguments.create(replicas).add(timeout);
		return notCommand(client, Command.WAIT, args);
	}

	@Override
	public KeyValue<Long, Long> waitOf(final int locals, final int replicas, final int timeout) {
		final CommandArguments args = CommandArguments.create(locals).add(replicas).add(timeout);
		return notCommand(client, Command.WAITOF, args);
	}

}
