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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.command;

import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.AggregationResult;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SearchCommands;
import com.buession.redis.core.command.args.FtAggregateArgument;
import com.buession.redis.core.internal.convert.jedis.params.JedisAggregationBuilder;
import com.buession.redis.core.internal.convert.jedis.response.AggregationResultConverter;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.search.aggr.AggregationBuilder;

import java.util.Set;

/**
 * Jedis 搜索命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisSearchCommands extends AbstractJedisRedisCommands implements SearchCommands {

	public JedisSearchCommands(final JedisRedisClient client) {
		super(client);
	}

	/*
	@Override
	public Set<String> ftList() {
		return executeCommand(Command.FT_LIST, (cmd)->cmd.ftList(), (v)->v);
	}

	@Override
	public AggregationResult ftAggregate(final String index, final String query) {
		final CommandArguments args = CommandArguments.create(index).add(query);
		return ftAggregate(index, new JedisAggregationBuilder(query), args);
	}

	@Override
	public AggregationResult ftAggregate(final byte[] index, final byte[] query) {
		final CommandArguments args = CommandArguments.create(index).add(query);
		return ftAggregate(SafeEncoder.encode(index), new JedisAggregationBuilder(query), args);
	}

	@Override
	public AggregationResult ftAggregate(final String index, final String query, final FtAggregateArgument argument) {
		//return executeCommand(Command.FT_AGGREGATE, (cmd)->cmd.ftAggregate(), (v)->v);
		return executeCommand(Command.FT_AGGREGATE);
	}

	@Override
	public AggregationResult ftAggregate(final byte[] index, final byte[] query, final FtAggregateArgument argument) {
		//return executeCommand(Command.FT_AGGREGATE, (cmd)->cmd.ftAggregate(), (v)->v);
		return executeCommand(Command.FT_AGGREGATE);
	}

	private AggregationResult ftAggregate(final String index, final AggregationBuilder builder,
										  final CommandArguments args) {
		return executeCommand(Command.FT_AGGREGATE, args, (cmd)->cmd.ftAggregate(index, builder),
				new AggregationResultConverter());
	}

	 */

}
