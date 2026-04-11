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

import com.buession.core.converter.MapConverter;
import com.buession.redis.client.lettuce.LettuceRedisClient;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.command.RedisCommand;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.PubSubCommands;
import com.buession.redis.core.command.RedisSubCommand;
import com.buession.redis.core.internal.convert.BinaryListStringListConverter;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.Map;

/**
 * Lettuce Pub/Sub 命令
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettucePubSubCommands extends AbstractLettuceRedisCommands implements PubSubCommands {

	public LettucePubSubCommands(final LettuceRedisClient client) {
		super(client);
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener) {
		final CommandArguments args = CommandArguments.create(patterns).add(pubSubListener);
		executeCommand(RedisCommand.PSUBSCRIBE, args);
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener) {
		final CommandArguments args = CommandArguments.create(patterns).add(pubSubListener);
		executeCommand(RedisCommand.PSUBSCRIBE, args);
	}

	@Override
	public Long publish(final String channel, final String message) {
		return publish(SafeEncoder.encode(channel), SafeEncoder.encode(message));
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message) {
		final CommandArguments args = CommandArguments.create(channel).add(message);
		return executeCommand(RedisCommand.PUBLISH, args, (cmd)->cmd.publish(channel, message),
				(cmd)->cmd.publish(channel, message));
	}

	@Override
	public List<String> pubsubChannels() {
		return executeCommand(RedisCommand.PUBSUB, RedisSubCommand.PUBSUB_CHANNELS, (cmd)->cmd.pubsubChannels(),
				(cmd)->cmd.pubsubChannels(), new BinaryListStringListConverter());
	}

	@Override
	public List<String> pubsubChannels(final String pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return executeCommand(RedisCommand.PUBSUB, RedisSubCommand.PUBSUB_CHANNELS, args,
				(cmd)->cmd.pubsubChannels(SafeEncoder.encode(pattern)),
				(cmd)->cmd.pubsubChannels(SafeEncoder.encode(pattern)),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> pubsubChannels(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return executeCommand(RedisCommand.PUBSUB, RedisSubCommand.PUBSUB_CHANNELS, args,
				(cmd)->cmd.pubsubChannels(pattern), (cmd)->cmd.pubsubChannels(pattern));
	}

	@Override
	public Long pubsubNumPat() {
		return executeCommand(RedisCommand.PUBSUB, RedisSubCommand.PUBSUB_NUMPAT, (cmd)->cmd.pubsubNumpat(),
				(cmd)->cmd.pubsubNumpat());
	}

	@Override
	public Map<String, Long> pubsubNumSub() {
		return executeCommand(RedisCommand.PUBSUB, RedisSubCommand.PUBSUB_NUMSUB, (cmd)->cmd.pubsubNumsub(),
				(cmd)->cmd.pubsubNumsub(), new MapConverter<>(SafeEncoder::encode, (v)->v));
	}

	@Override
	public Map<String, Long> pubsubNumSub(final String... channels) {
		final CommandArguments args = CommandArguments.create(channels);
		return executeCommand(RedisCommand.PUBSUB, RedisSubCommand.PUBSUB_NUMSUB, args,
				(cmd)->cmd.pubsubNumsub(SafeEncoder.encode(channels)),
				(cmd)->cmd.pubsubNumsub(SafeEncoder.encode(channels)),
				new MapConverter<>(SafeEncoder::encode, (v)->v));
	}

	@Override
	public Map<byte[], Long> pubsubNumSub(final byte[]... channels) {
		final CommandArguments args = CommandArguments.create(channels);
		return executeCommand(RedisCommand.PUBSUB, RedisSubCommand.PUBSUB_NUMSUB, args,
				(cmd)->cmd.pubsubNumsub(channels), (cmd)->cmd.pubsubNumsub(channels));
	}

	@Override
	public List<String> pubsubShardChannels() {
		return executeCommand(RedisCommand.PUBSUB, RedisSubCommand.PUBSUB_SHARDCHANNELS,
				(cmd)->cmd.pubsubShardChannels(), (cmd)->cmd.pubsubShardChannels(),
				new BinaryListStringListConverter());
	}

	@Override
	public List<String> pubsubShardChannels(final String pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return executeCommand(RedisCommand.PUBSUB, RedisSubCommand.PUBSUB_SHARDCHANNELS, args,
				(cmd)->cmd.pubsubShardChannels(SafeEncoder.encode(pattern)),
				(cmd)->cmd.pubsubShardChannels(SafeEncoder.encode(pattern)),
				new BinaryListStringListConverter());
	}

	@Override
	public List<byte[]> pubsubShardChannels(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return executeCommand(RedisCommand.PUBSUB, RedisSubCommand.PUBSUB_SHARDCHANNELS, args,
				(cmd)->cmd.pubsubShardChannels(pattern), (cmd)->cmd.pubsubShardChannels(pattern));
	}

	@Override
	public Map<String, Long> pubsubShardNumSub() {
		return executeCommand(RedisCommand.PUBSUB, RedisSubCommand.PUBSUB_SHARDNUMSUB, (cmd)->cmd.pubsubShardNumsub(),
				(cmd)->cmd.pubsubShardNumsub(), new MapConverter<>(SafeEncoder::encode, (v)->v));
	}

	@Override
	public Map<String, Long> pubsubShardNumSub(final String... shardChannels) {
		final CommandArguments args = CommandArguments.create(shardChannels);
		return executeCommand(RedisCommand.PUBSUB, RedisSubCommand.PUBSUB_SHARDNUMSUB, args,
				(cmd)->cmd.pubsubShardNumsub(SafeEncoder.encode(shardChannels)),
				(cmd)->cmd.pubsubShardNumsub(SafeEncoder.encode(shardChannels)),
				new MapConverter<>(SafeEncoder::encode, (v)->v));
	}

	@Override
	public Map<byte[], Long> pubsubShardNumSub(final byte[]... shardChannels) {
		final CommandArguments args = CommandArguments.create(shardChannels);
		return executeCommand(RedisCommand.PUBSUB, RedisSubCommand.PUBSUB_SHARDNUMSUB, args,
				(cmd)->cmd.pubsubShardNumsub(shardChannels), (cmd)->cmd.pubsubShardNumsub(shardChannels));
	}

	@Override
	public Object pUnSubscribe() {
		return executeCommand(RedisCommand.PUNSUBSCRIBE);
	}

	@Override
	public Object pUnSubscribe(final String... patterns) {
		final CommandArguments args = CommandArguments.create(patterns);
		return executeCommand(RedisCommand.PUNSUBSCRIBE, args);
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns) {
		final CommandArguments args = CommandArguments.create(patterns);
		return executeCommand(RedisCommand.PUNSUBSCRIBE, args);
	}

	@Override
	public Long sPublish(final String shardchannel, final String message) {
		return sPublish(SafeEncoder.encode(shardchannel), SafeEncoder.encode(message));
	}

	@Override
	public Long sPublish(final byte[] shardchannel, final byte[] message) {
		final CommandArguments args = CommandArguments.create(shardchannel).add(message);
		return executeCommand(RedisCommand.SPUBLISH, args, (cmd)->cmd.spublish(shardchannel, message),
				(cmd)->cmd.spublish(shardchannel, message));
	}

	@Override
	public void sSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener) {
		final CommandArguments args = CommandArguments.create(patterns).add(pubSubListener);
		executeCommand(RedisCommand.SSUBSCRIBE, args);
	}

	@Override
	public void sSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener) {
		final CommandArguments args = CommandArguments.create(patterns).add(pubSubListener);
		executeCommand(RedisCommand.SSUBSCRIBE, args);
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener) {
		final CommandArguments args = CommandArguments.create(channels).add(pubSubListener);
		executeCommand(RedisCommand.SUBSCRIBE, args);
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener) {
		final CommandArguments args = CommandArguments.create(channels).add(pubSubListener);
		executeCommand(RedisCommand.SUBSCRIBE, args);
	}

	@Override
	public Object sUnSubscribe() {
		return executeCommand(RedisCommand.SUNSUBSCRIBE);
	}

	@Override
	public Object sUnSubscribe(final String... shardchannel) {
		final CommandArguments args = CommandArguments.create(shardchannel);
		return executeCommand(RedisCommand.SUNSUBSCRIBE, args);
	}

	@Override
	public Object sUnSubscribe(final byte[]... shardchannel) {
		final CommandArguments args = CommandArguments.create(shardchannel);
		return executeCommand(RedisCommand.SUNSUBSCRIBE, args);
	}

	@Override
	public Object unSubscribe() {
		return executeCommand(RedisCommand.UNSUBSCRIBE);
	}

	@Override
	public Object unSubscribe(final String... channels) {
		final CommandArguments args = CommandArguments.create(channels);
		return executeCommand(RedisCommand.UNSUBSCRIBE, args);
	}

	@Override
	public Object unSubscribe(final byte[]... channels) {
		final CommandArguments args = CommandArguments.create(channels);
		return executeCommand(RedisCommand.UNSUBSCRIBE, args);
	}

}
