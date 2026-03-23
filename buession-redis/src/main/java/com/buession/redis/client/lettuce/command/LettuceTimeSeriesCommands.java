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
package com.buession.redis.client.lettuce.command;

import com.buession.lang.Status;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.command.args.timeseries.AggregationType;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.TimeSeriesCommands;
import com.buession.redis.core.command.args.timeseries.AddArgument;
import com.buession.redis.core.command.args.timeseries.AlertArgument;
import com.buession.redis.core.command.args.timeseries.CreateArgument;

/**
 * Lettuce TimeSeries 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceTimeSeriesCommands extends AbstractLettuceRedisCommands implements TimeSeriesCommands {

	public LettuceTimeSeriesCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public Long tsAdd(final String key, final long timestamp, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(timestamp).add(value);
		return executeCommand(Command.TS_ADD, args);
	}

	@Override
	public Long tsAdd(final byte[] key, final long timestamp, final double value) {
		final CommandArguments args = CommandArguments.create(key).add(timestamp).add(value);
		return executeCommand(Command.TS_ADD, args);
	}

	@Override
	public Long tsAdd(final String key, final long timestamp, final double value, final AddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(timestamp).add(value).add(argument);
		return executeCommand(Command.TS_ADD, args);
	}

	@Override
	public Long tsAdd(final byte[] key, final long timestamp, final double value, final AddArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(timestamp).add(value).add(argument);
		return executeCommand(Command.TS_ADD, args);
	}

	@Override
	public Status tsAlert(final String key, final AlertArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(Command.TS_ALTER, args);
	}

	@Override
	public Status tsAlert(final byte[] key, final AlertArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(Command.TS_ALTER, args);
	}

	@Override
	public Status tsCreate(final String key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.TS_CREATE, args);
	}

	@Override
	public Status tsCreate(final byte[] key) {
		final CommandArguments args = CommandArguments.create(key);
		return executeCommand(Command.TS_CREATE, args);
	}

	@Override
	public Status tsCreate(final String key, final CreateArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(Command.TS_CREATE, args);
	}

	@Override
	public Status tsCreate(final byte[] key, final CreateArgument argument) {
		final CommandArguments args = CommandArguments.create(key).add(argument);
		return executeCommand(Command.TS_CREATE, args);
	}

	@Override
	public Status tsCreateRule(final String sourceKey, final String destKey, final AggregationType aggregationType,
							   final long timeBucket) {
		final CommandArguments args = CommandArguments.create(destKey, sourceKey).add("AGGREGATION", aggregationType)
				.add(timeBucket);
		return executeCommand(Command.TS_CREATERULE, args);
	}

	@Override
	public Status tsCreateRule(final byte[] sourceKey, final byte[] destKey, final AggregationType aggregationType,
							   final long timeBucket) {
		final CommandArguments args = CommandArguments.create(destKey, sourceKey).add("AGGREGATION", aggregationType)
				.add(timeBucket);
		return executeCommand(Command.TS_CREATERULE, args);
	}

	@Override
	public Status tsCreateRule(final String sourceKey, final String destKey, final AggregationType aggregationType,
							   final long timeBucket, final long alignTimestamp) {
		final CommandArguments args = CommandArguments.create(destKey, sourceKey).add("AGGREGATION", aggregationType)
				.add(timeBucket, alignTimestamp);
		return executeCommand(Command.TS_CREATERULE, args);
	}

	@Override
	public Status tsCreateRule(final byte[] sourceKey, final byte[] destKey, final AggregationType aggregationType,
							   final long timeBucket, final long alignTimestamp) {
		final CommandArguments args = CommandArguments.create(destKey, sourceKey).add("AGGREGATION", aggregationType)
				.add(timeBucket, alignTimestamp);
		return executeCommand(Command.TS_CREATERULE, args);
	}

}
