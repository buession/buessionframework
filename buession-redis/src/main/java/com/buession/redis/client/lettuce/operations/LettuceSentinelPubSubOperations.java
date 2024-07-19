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

import com.buession.redis.client.lettuce.LettuceSentinelClient;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 哨兵模式 Pub/Sub 命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceSentinelPubSubOperations extends AbstractPubSubOperations<LettuceSentinelClient> {

	public LettuceSentinelPubSubOperations(final LettuceSentinelClient client) {
		super(client);
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener) {
		final CommandArguments args = CommandArguments.create("patterns", (Object[]) patterns)
				.put("pubSubListener", pubSubListener);
		notCommand(client, ProtocolCommand.PSUBSCRIBE, args);
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener) {
		final CommandArguments args = CommandArguments.create("patterns", (Object[]) patterns)
				.put("pubSubListener", pubSubListener);
		notCommand(client, ProtocolCommand.PSUBSCRIBE, args);
	}

	@Override
	public Long publish(final String channel, final String message) {
		final CommandArguments args = CommandArguments.create("channel", channel).put("message", message);
		return notCommand(client, ProtocolCommand.PUBLISH, args);
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message) {
		final CommandArguments args = CommandArguments.create("channel", channel).put("message", message);
		return notCommand(client, ProtocolCommand.PUBLISH, args);
	}

	@Override
	public List<String> pubsubChannels() {
		return notCommand(client, ProtocolCommand.PUBSUB_CHANNELS);
	}

	@Override
	public List<String> pubsubChannels(final String pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return notCommand(client, ProtocolCommand.PUBSUB_CHANNELS, args);
	}

	@Override
	public List<byte[]> pubsubChannels(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return notCommand(client, ProtocolCommand.PUBSUB_CHANNELS, args);
	}

	@Override
	public Long pubsubNumPat() {
		return notCommand(client, ProtocolCommand.PUBSUB_NUMPAT);
	}

	@Override
	public Map<String, Long> pubsubNumSub(final String... channels) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		return notCommand(client, ProtocolCommand.PUBSUB_NUMSUB, args);
	}

	@Override
	public Map<byte[], Long> pubsubNumSub(final byte[]... channels) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		return notCommand(client, ProtocolCommand.PUBSUB_NUMSUB, args);
	}

	@Override
	public Object pUnSubscribe() {
		return notCommand(client, ProtocolCommand.PUNSUBSCRIBE);
	}

	@Override
	public Object pUnSubscribe(final String... patterns) {
		final CommandArguments args = CommandArguments.create("patterns", (Object[]) patterns);
		return notCommand(client, ProtocolCommand.PUNSUBSCRIBE, args);
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns) {
		final CommandArguments args = CommandArguments.create("patterns", (Object[]) patterns);
		return notCommand(client, ProtocolCommand.PUNSUBSCRIBE, args);
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels)
				.put("pubSubListener", pubSubListener);
		notCommand(client, ProtocolCommand.SUBSCRIBE, args);
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels)
				.put("pubSubListener", pubSubListener);
		notCommand(client, ProtocolCommand.SUBSCRIBE, args);
	}

	@Override
	public Object unSubscribe() {
		return notCommand(client, ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	public Object unSubscribe(final String... channels) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		return notCommand(client, ProtocolCommand.UNSUBSCRIBE, args);
	}

	@Override
	public Object unSubscribe(final byte[]... channels) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		return notCommand(client, ProtocolCommand.UNSUBSCRIBE, args);
	}

}
