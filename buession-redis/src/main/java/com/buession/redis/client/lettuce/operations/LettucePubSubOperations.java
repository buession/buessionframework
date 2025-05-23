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

import com.buession.core.converter.Converter;
import com.buession.core.converter.MapConverter;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 单机模式 Pub/Sub 命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettucePubSubOperations extends AbstractPubSubOperations<LettuceStandaloneClient> {

	public LettucePubSubOperations(final LettuceStandaloneClient client) {
		super(client);
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener) {
		final CommandArguments args = CommandArguments.create(patterns)
				.add(pubSubListener);
		pSubscribe(args);
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener) {
		final CommandArguments args = CommandArguments.create(patterns)
				.add(pubSubListener);
		pSubscribe(args);
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message) {
		final CommandArguments args = CommandArguments.create(channel).add(message);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.PUBLISH, (cmd)->cmd.publish(channel, message),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.PUBLISH,
					(cmd)->cmd.publish(channel, message), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.PUBLISH, (cmd)->cmd.publish(channel, message), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> pubsubChannels() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.PUBSUB_CHANNELS, (cmd)->cmd.pubsubChannels(),
					binaryToStringListConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.PUBSUB_CHANNELS, (cmd)->cmd.pubsubChannels(),
					binaryToStringListConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.PUBSUB_CHANNELS, (cmd)->cmd.pubsubChannels(),
					binaryToStringListConverter)
					.run();
		}
	}

	@Override
	public List<String> pubsubChannels(final String pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		final byte[] bPattern = SafeEncoder.encode(pattern);

		return pubsubChannels(bPattern, binaryToStringListConverter, args);
	}

	@Override
	public List<byte[]> pubsubChannels(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create(pattern);
		return pubsubChannels(pattern, (v)->v, args);
	}

	@Override
	public Long pubsubNumPat() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.PUBSUB_NUMPAT, (cmd)->cmd.pubsubNumpat(),
					(v)->v)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.PUBSUB_NUMPAT, (cmd)->cmd.pubsubNumpat(),
					(v)->v)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.PUBSUB_NUMPAT, (cmd)->cmd.pubsubNumpat(), (v)->v)
					.run();
		}
	}

	@Override
	public Map<String, Long> pubsubNumSub(final String... channels) {
		final CommandArguments args = CommandArguments.create(channels);
		final byte[][] bChannels = SafeEncoder.encode(channels);
		final MapConverter<byte[], Long, String, Long> converter = new MapConverter<>(SafeEncoder::encode, (v)->v);

		return pubsubNumSub(bChannels, converter, args);
	}

	@Override
	public Map<byte[], Long> pubsubNumSub(final byte[]... channels) {
		final CommandArguments args = CommandArguments.create(channels);
		return pubsubNumSub(channels, (v)->v, args);
	}

	@Override
	public Object pUnSubscribe() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.PUNSUBSCRIBE)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.PUNSUBSCRIBE)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.PUNSUBSCRIBE)
					.run();
		}
	}

	@Override
	public Object pUnSubscribe(final String... patterns) {
		final CommandArguments args = CommandArguments.create(patterns);
		return pUnSubscribe(args);
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns) {
		final CommandArguments args = CommandArguments.create(patterns);
		return pUnSubscribe(args);
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener) {
		final CommandArguments args = CommandArguments.create(channels)
				.add(pubSubListener);
		subscribe(args);
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener) {
		final CommandArguments args = CommandArguments.create(channels)
				.add(pubSubListener);
		subscribe(args);
	}

	@Override
	public Object unSubscribe() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.UNSUBSCRIBE)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.UNSUBSCRIBE)
					.run();
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.UNSUBSCRIBE)
					.run();
		}
	}

	@Override
	public Object unSubscribe(final String... channels) {
		final CommandArguments args = CommandArguments.create(channels);
		return unSubscribe(args);
	}

	@Override
	public Object unSubscribe(final byte[]... channels) {
		final CommandArguments args = CommandArguments.create(channels);
		return unSubscribe(args);
	}

	private void pSubscribe(final CommandArguments args) {
		if(isPipeline()){
			new LettucePipelineCommand<>(client, ProtocolCommand.PSUBSCRIBE)
					.run(args);
		}else if(isTransaction()){
			new LettuceTransactionCommand<>(client, ProtocolCommand.PSUBSCRIBE)
					.run(args);
		}else{
			new LettuceCommand<>(client, ProtocolCommand.PSUBSCRIBE)
					.run(args);
		}
	}

	private <V> List<V> pubsubChannels(final byte[] pattern, final Converter<List<byte[]>, List<V>> converter,
									   final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.PUBSUB_CHANNELS,
					(cmd)->cmd.pubsubChannels(pattern), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.PUBSUB_CHANNELS,
					(cmd)->cmd.pubsubChannels(pattern), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.PUBSUB_CHANNELS, (cmd)->cmd.pubsubChannels(pattern),
					converter)
					.run(args);
		}
	}

	private <K> Map<K, Long> pubsubNumSub(final byte[][] channels,
										  final Converter<Map<byte[], Long>, Map<K, Long>> converter,
										  final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.PUBSUB_NUMSUB,
					(cmd)->cmd.pubsubNumsub(channels), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.PUBSUB_NUMSUB,
					(cmd)->cmd.pubsubNumsub(channels), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.PUBSUB_NUMSUB, (cmd)->cmd.pubsubNumsub(channels),
					converter)
					.run(args);
		}
	}

	private Object pUnSubscribe(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.PUNSUBSCRIBE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.PUNSUBSCRIBE)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.PUNSUBSCRIBE)
					.run(args);
		}
	}

	private void subscribe(final CommandArguments args) {
		if(isPipeline()){
			new LettucePipelineCommand<>(client, ProtocolCommand.SUBSCRIBE)
					.run(args);
		}else if(isTransaction()){
			new LettuceTransactionCommand<>(client, ProtocolCommand.SUBSCRIBE)
					.run(args);
		}else{
			new LettuceCommand<>(client, ProtocolCommand.SUBSCRIBE)
					.run(args);
		}
	}

	private Object unSubscribe(final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, ProtocolCommand.UNSUBSCRIBE)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, ProtocolCommand.UNSUBSCRIBE)
					.run(args);
		}else{
			return new LettuceCommand<>(client, ProtocolCommand.UNSUBSCRIBE)
					.run(args);
		}
	}

}
