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
package com.buession.redis.client.jedis.operations;

import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.PubSubOperations;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.SubCommand;
import com.buession.redis.pubsub.jedis.DefaultBinaryJedisPubSub;
import com.buession.redis.pubsub.jedis.DefaultJedisPubSub;

import java.util.List;
import java.util.Map;

/**
 * Jedis Pub/Sub 命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisPubSubOperations extends AbstractJedisRedisOperations implements PubSubOperations {

	public JedisPubSubOperations(final JedisRedisClient client) {
		super(client);
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener) {
		final CommandArguments args = CommandArguments.create(patterns).add(pubSubListener);
		executeCommand(Command.PSUBSCRIBE, args, (cmd)->{
			cmd.psubscribe(new DefaultJedisPubSub(pubSubListener), patterns);
			return null;
		}, (v)->v);
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener) {
		final CommandArguments args = CommandArguments.create(patterns).add(pubSubListener);
		executeCommand(Command.PSUBSCRIBE, args, (cmd)->{
			cmd.psubscribe(new DefaultBinaryJedisPubSub(pubSubListener), patterns);
			return null;
		}, (v)->v);
	}

	@Override
	public Long publish(final String channel, final String message) {
		final CommandArguments args = CommandArguments.create(channel).add(message);
		return executeCommand(Command.PUBLISH, args, (cmd)->cmd.publish(channel, message), (v)->v);
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message) {
		final CommandArguments args = CommandArguments.create(channel).add(message);
		return executeCommand(Command.PUBLISH, args, (cmd)->cmd.publish(channel, message), (v)->v);
	}

	@Override
	public List<String> pubsubChannels() {
		return executeCommand(Command.PUBSUB, SubCommand.PUBSUB_CHANNELS);
	}

	@Override
	public List<String> pubsubChannels(final String pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return executeCommand(Command.PUBSUB, SubCommand.PUBSUB_CHANNELS, args);
	}

	@Override
	public List<byte[]> pubsubChannels(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return executeCommand(Command.PUBSUB, SubCommand.PUBSUB_CHANNELS, args);
	}

	@Override
	public Long pubsubNumPat() {
		return executeCommand(Command.PUBSUB, SubCommand.PUBSUB_NUMPAT);
	}

	@Override
	public Map<String, Long> pubsubNumSub() {
		return executeCommand(Command.PUBSUB, SubCommand.PUBSUB_NUMSUB);
	}

	@Override
	public Map<String, Long> pubsubNumSub(final String... channels) {
		final CommandArguments args = CommandArguments.create(channels);
		return executeCommand(Command.PUBSUB, SubCommand.PUBSUB_NUMSUB, args);
	}

	@Override
	public Map<byte[], Long> pubsubNumSub(final byte[]... channels) {
		final CommandArguments args = CommandArguments.create(channels);
		return executeCommand(Command.PUBSUB, SubCommand.PUBSUB_NUMSUB, args);
	}

	@Override
	public List<String> pubsubShardChannels() {
		return executeCommand(Command.PUBSUB, SubCommand.PUBSUB_SHARDCHANNELS);
	}

	@Override
	public List<String> pubsubShardChannels(final String pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return executeCommand(Command.PUBSUB, SubCommand.PUBSUB_SHARDCHANNELS, args);
	}

	@Override
	public List<byte[]> pubsubShardChannels(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return executeCommand(Command.PUBSUB, SubCommand.PUBSUB_SHARDCHANNELS, args);
	}

	@Override
	public Map<String, Long> pubsubShardNumSub() {
		return executeCommand(Command.PUBSUB, SubCommand.PUBSUB_SHARDNUMSUB);
	}

	@Override
	public Map<String, Long> pubsubShardNumSub(final String... shardChannels) {
		final CommandArguments args = CommandArguments.create(shardChannels);
		return executeCommand(Command.PUBSUB, SubCommand.PUBSUB_SHARDNUMSUB, args);
	}

	@Override
	public Map<byte[], Long> pubsubShardNumSub(final byte[]... shardChannels) {
		final CommandArguments args = CommandArguments.create(shardChannels);
		return executeCommand(Command.PUBSUB, SubCommand.PUBSUB_SHARDNUMSUB, args);
	}

	@Override
	public Object pUnSubscribe() {
		return executeCommand(Command.PUNSUBSCRIBE);
	}

	@Override
	public Object pUnSubscribe(final String... patterns) {
		final CommandArguments args = CommandArguments.create(patterns);
		return executeCommand(Command.PUNSUBSCRIBE, args);
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns) {
		final CommandArguments args = CommandArguments.create(patterns);
		return executeCommand(Command.PUNSUBSCRIBE, args);
	}

	@Override
	public Long sPublish(final String shardchannel, final String message) {
		final CommandArguments args = CommandArguments.create(shardchannel).add(message);
		return executeCommand(Command.SPUBLISH, args);
	}

	@Override
	public Long sPublish(final byte[] shardchannel, final byte[] message) {
		final CommandArguments args = CommandArguments.create(shardchannel).add(message);
		return executeCommand(Command.SPUBLISH, args);
	}

	@Override
	public void sSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener) {
		final CommandArguments args = CommandArguments.create(patterns).add(pubSubListener);
		executeCommand(Command.SSUBSCRIBE, args);
	}

	@Override
	public void sSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener) {
		final CommandArguments args = CommandArguments.create(patterns).add(pubSubListener);
		executeCommand(Command.SSUBSCRIBE, args);
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener) {
		final CommandArguments args = CommandArguments.create(channels).add(pubSubListener);
		executeCommand(Command.SUBSCRIBE, args, (cmd)->{
			cmd.subscribe(new DefaultJedisPubSub(pubSubListener), channels);
			return null;
		}, (v)->v);
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener) {
		final CommandArguments args = CommandArguments.create(channels).add(pubSubListener);
		executeCommand(Command.SUBSCRIBE, args, (cmd)->{
			cmd.subscribe(new DefaultBinaryJedisPubSub(pubSubListener), channels);
			return null;
		}, (v)->v);
	}

	@Override
	public Object sUnSubscribe() {
		return executeCommand(Command.SUNSUBSCRIBE);
	}

	@Override
	public Object sUnSubscribe(final String... shardchannel) {
		final CommandArguments args = CommandArguments.create(shardchannel);
		return executeCommand(Command.SUNSUBSCRIBE, args);
	}

	@Override
	public Object sUnSubscribe(final byte[]... shardchannel) {
		final CommandArguments args = CommandArguments.create(shardchannel);
		return executeCommand(Command.SUNSUBSCRIBE, args);
	}

	@Override
	public Object unSubscribe() {
		return executeCommand(Command.UNSUBSCRIBE);
	}

	@Override
	public Object unSubscribe(final String... channels) {
		final CommandArguments args = CommandArguments.create(channels);
		return executeCommand(Command.UNSUBSCRIBE, args);
	}

	@Override
	public Object unSubscribe(final byte[]... channels) {
		final CommandArguments args = CommandArguments.create(channels);
		return executeCommand(Command.UNSUBSCRIBE, args);
	}

}
