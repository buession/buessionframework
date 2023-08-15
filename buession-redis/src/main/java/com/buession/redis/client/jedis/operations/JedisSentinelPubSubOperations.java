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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.redis.client.jedis.JedisSentinelClient;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.pubsub.jedis.DefaultBinaryJedisPubSub;
import com.buession.redis.pubsub.jedis.DefaultJedisPubSub;
import com.buession.redis.utils.SafeEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Jedis 哨兵模式 Pub/Sub 命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisSentinelPubSubOperations extends AbstractPubSubOperations<JedisSentinelClient> {

	public JedisSentinelPubSubOperations(final JedisSentinelClient client){
		super(client);
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
		final CommandArguments args = CommandArguments.create("patterns", (Object[]) patterns)
				.put("pubSubListener", pubSubListener);
		new JedisSentinelCommand<Void>(client, ProtocolCommand.PSUBSCRIBE)
				.general((cmd)->{
					cmd.psubscribe(new DefaultJedisPubSub(pubSubListener), patterns);
					return null;
				})
				.run(args);
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
		final CommandArguments args = CommandArguments.create("patterns", (Object[]) patterns)
				.put("pubSubListener", pubSubListener);
		new JedisSentinelCommand<Void>(client, ProtocolCommand.PSUBSCRIBE)
				.general((cmd)->{
					cmd.psubscribe(new DefaultBinaryJedisPubSub(pubSubListener), patterns);
					return null;
				})
				.run(args);
	}

	@Override
	public Long publish(final String channel, final String message){
		final CommandArguments args = CommandArguments.create("channel", channel).put("message", message);
		return new JedisSentinelCommand<Long>(client, ProtocolCommand.PUBLISH)
				.general((cmd)->cmd.publish(channel, message))
				.pipeline((cmd)->cmd.publish(channel, message))
				.transaction((cmd)->cmd.publish(channel, message))
				.run(args);
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message){
		final CommandArguments args = CommandArguments.create("channel", channel).put("message", message);
		return new JedisSentinelCommand<Long>(client, ProtocolCommand.PUBLISH)
				.general((cmd)->cmd.publish(channel, message))
				.pipeline((cmd)->cmd.publish(channel, message))
				.transaction((cmd)->cmd.publish(channel, message))
				.run(args);
	}

	@Override
	public List<String> pubsubChannels(){
		return new JedisSentinelCommand<List<String>>(client, ProtocolCommand.PUBSUB_CHANNELS)
				.general((cmd)->cmd.pubsubChannels())
				.run();
	}

	@Override
	public List<String> pubsubChannels(final String pattern){
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return new JedisSentinelCommand<List<String>>(client, ProtocolCommand.PUBSUB_CHANNELS)
				.general((cmd)->cmd.pubsubChannels(pattern))
				.run(args);
	}

	@Override
	public List<byte[]> pubsubChannels(final byte[] pattern){
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return new JedisSentinelCommand<List<byte[]>>(client, ProtocolCommand.PUBSUB_CHANNELS)
				.general((cmd)->cmd.pubsubChannels(SafeEncoder.encode(pattern)),
						Converters.STRING_LIST_TO_BINARY_LIST_CONVERTER)
				.run(args);
	}

	@Override
	public Long pubsubNumPat(){
		return new JedisSentinelCommand<Long>(client, ProtocolCommand.PUBSUB_NUMPAT)
				.general((cmd)->cmd.pubsubNumPat())
				.run();
	}

	@Override
	public Map<String, Long> pubsubNumSub(final String... channels){
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		return new JedisSentinelCommand<Map<String, Long>>(client, ProtocolCommand.PUBSUB_NUMSUB)
				.general((cmd)->cmd.pubsubNumSub(channels))
				.run(args);
	}

	@Override
	public Map<byte[], Long> pubsubNumSub(final byte[]... channels){
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		return new JedisSentinelCommand<Map<byte[], Long>>(client, ProtocolCommand.PUBSUB_NUMSUB)
				.general((cmd)->{
					final Map<String, Long> temp = cmd.pubsubNumSub(
							Converters.BINARY_ARRAY_TO_STRING_ARRAY_CONVERTER.convert(channels));
					final Map<byte[], Long> result = new HashMap<>(temp.size());

					temp.forEach((key, value)->{
						result.put(SafeEncoder.encode(key), value);
					});

					return result;
				})
				.run(args);
	}

	@Override
	public Object pUnSubscribe(){
		return new JedisSentinelCommand<>(client, ProtocolCommand.PUNSUBSCRIBE)
				.run();
	}

	@Override
	public Object pUnSubscribe(final String... patterns){
		final CommandArguments args = CommandArguments.create("patterns", (Object[]) patterns);
		return new JedisSentinelCommand<>(client, ProtocolCommand.PUNSUBSCRIBE)
				.run(args);
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns){
		final CommandArguments args = CommandArguments.create("patterns", (Object[]) patterns);
		return new JedisSentinelCommand<>(client, ProtocolCommand.PUNSUBSCRIBE)
				.run(args);
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels)
				.put("pubSubListener", pubSubListener);
		new JedisSentinelCommand<Void>(client, ProtocolCommand.PUBLISH)
				.general((cmd)->{
					cmd.subscribe(new DefaultJedisPubSub(pubSubListener), channels);
					return null;
				})
				.run(args);
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels)
				.put("pubSubListener", pubSubListener);
		new JedisSentinelCommand<Void>(client, ProtocolCommand.PUBLISH)
				.general((cmd)->{
					cmd.subscribe(new DefaultBinaryJedisPubSub(pubSubListener), channels);
					return null;
				})
				.run(args);
	}

	@Override
	public Object unSubscribe(){
		return new JedisSentinelCommand<>(client, ProtocolCommand.UNSUBSCRIBE)
				.run();
	}

	@Override
	public Object unSubscribe(final String... channels){
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		return new JedisSentinelCommand<>(client, ProtocolCommand.UNSUBSCRIBE)
				.run(args);
	}

	@Override
	public Object unSubscribe(final byte[]... channels){
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		return new JedisSentinelCommand<>(client, ProtocolCommand.UNSUBSCRIBE)
				.run(args);
	}

}
