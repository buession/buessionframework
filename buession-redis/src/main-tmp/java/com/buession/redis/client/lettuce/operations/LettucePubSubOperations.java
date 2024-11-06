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
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.MapConverter;
import com.buession.redis.client.lettuce.LettuceStandaloneClient;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.internal.convert.Converters;
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
		final CommandArguments args = CommandArguments.create("patterns", (Object[]) patterns)
				.put("pubSubListener", pubSubListener);
		notCommand(client, Command.PSUBSCRIBE, args);
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener) {
		final CommandArguments args = CommandArguments.create("patterns", (Object[]) patterns)
				.put("pubSubListener", pubSubListener);
		notCommand(client, Command.PSUBSCRIBE, args);
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message) {
		final CommandArguments args = CommandArguments.create("channel", channel).put("message", message);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PUBLISH, (cmd)->cmd.publish(channel, message),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PUBLISH,
					(cmd)->cmd.publish(channel, message), (v)->v)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.PUBLISH, (cmd)->cmd.publish(channel, message), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> pubsubChannels() {
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PUBSUB_CHANNELS, (cmd)->cmd.pubsubChannels(),
					listConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PUBSUB_CHANNELS, (cmd)->cmd.pubsubChannels(),
					listConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.PUBSUB_CHANNELS, (cmd)->cmd.pubsubChannels(),
					listConverter)
					.run();
		}
	}

	@Override
	public List<String> pubsubChannels(final String pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		final byte[] bPattern = SafeEncoder.encode(pattern);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return pubsubChannels(bPattern, listConverter, args);
	}

	@Override
	public List<byte[]> pubsubChannels(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return pubsubChannels(pattern, (v)->v, args);
	}

	@Override
	public List<String> pubsubShardChannels() {
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PUBSUB_SHARDCHANNELS,
					(cmd)->cmd.pubsubChannels(), listConverter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PUBSUB_SHARDCHANNELS,
					(cmd)->cmd.pubsubChannels(), listConverter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.PUBSUB_SHARDCHANNELS, (cmd)->cmd.pubsubChannels(),
					listConverter)
					.run();
		}
	}

	@Override
	public List<String> pubsubShardChannels(final String pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		final byte[] bPattern = SafeEncoder.encode(pattern);
		final ListConverter<byte[], String> listConverter = Converters.listBinaryToString();

		return pubsubShardChannels(bPattern, listConverter, args);
	}

	@Override
	public List<byte[]> pubsubShardChannels(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return pubsubShardChannels(pattern, (v)->v, args);
	}

	@Override
	public Long pubsubNumPat() {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PUBSUB_NUMPAT, (cmd)->cmd.pubsubNumpat(),
					(v)->v)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PUBSUB_NUMPAT, (cmd)->cmd.pubsubNumpat(),
					(v)->v)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.PUBSUB_NUMPAT, (cmd)->cmd.pubsubNumpat(), (v)->v)
					.run();
		}
	}

	@Override
	public Map<String, Long> pubsubNumSub() {
		final MapConverter<byte[], Long, String, Long> converter = new MapConverter<>(SafeEncoder::encode, (v)->v);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PUBSUB_NUMSUB,
					(cmd)->cmd.pubsubNumsub(), converter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PUBSUB_NUMSUB,
					(cmd)->cmd.pubsubNumsub(), converter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.PUBSUB_NUMSUB, (cmd)->cmd.pubsubNumsub(),
					converter)
					.run();
		}
	}

	@Override
	public Map<String, Long> pubsubNumSub(final String... channels) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		final byte[][] bChannels = SafeEncoder.encode(channels);
		final MapConverter<byte[], Long, String, Long> converter = new MapConverter<>(SafeEncoder::encode, (v)->v);

		return pubsubNumSub(bChannels, converter, args);
	}

	@Override
	public Map<byte[], Long> pubsubNumSub(final byte[]... channels) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		return pubsubNumSub(channels, (v)->v, args);
	}

	@Override
	public Map<String, Long> pubsubShardNumSub() {
		final MapConverter<byte[], Long, String, Long> converter = new MapConverter<>(SafeEncoder::encode, (v)->v);

		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PUBSUB_SHARDNUMSUB,
					(cmd)->cmd.pubsubShardNumsub(), converter)
					.run();
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PUBSUB_SHARDNUMSUB,
					(cmd)->cmd.pubsubShardNumsub(), converter)
					.run();
		}else{
			return new LettuceCommand<>(client, Command.PUBSUB_SHARDNUMSUB,
					(cmd)->cmd.pubsubShardNumsub(), converter)
					.run();
		}
	}

	@Override
	public Map<String, Long> pubsubShardNumSub(final String... shardChannels) {
		final CommandArguments args = CommandArguments.create("shardChannels", (Object[]) shardChannels);
		final byte[][] bShardChannels = SafeEncoder.encode(shardChannels);
		final MapConverter<byte[], Long, String, Long> converter = new MapConverter<>(SafeEncoder::encode, (v)->v);

		return pubsubShardNumSub(bShardChannels, converter, args);
	}

	@Override
	public Map<byte[], Long> pubsubShardNumSub(final byte[]... shardChannels) {
		final CommandArguments args = CommandArguments.create("shardChannels", (Object[]) shardChannels);
		return pubsubShardNumSub(shardChannels, (v)->v, args);
	}

	@Override
	public Object pUnSubscribe() {
		return notCommand(client, Command.PUNSUBSCRIBE);
	}

	@Override
	public Object pUnSubscribe(final String... patterns) {
		final CommandArguments args = CommandArguments.create("patterns", (Object[]) patterns);
		return notCommand(client, Command.PUNSUBSCRIBE, args);
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns) {
		final CommandArguments args = CommandArguments.create("patterns", (Object[]) patterns);
		return notCommand(client, Command.PUNSUBSCRIBE, args);
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels)
				.put("pubSubListener", pubSubListener);
		notCommand(client, Command.SUBSCRIBE, args);
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels)
				.put("pubSubListener", pubSubListener);
		notCommand(client, Command.SUBSCRIBE, args);
	}

	@Override
	public Object unSubscribe() {
		return notCommand(client, Command.UNSUBSCRIBE);
	}

	@Override
	public Object unSubscribe(final String... channels) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		return notCommand(client, Command.UNSUBSCRIBE, args);
	}

	@Override
	public Object unSubscribe(final byte[]... channels) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		return notCommand(client, Command.UNSUBSCRIBE, args);
	}

	private <V> List<V> pubsubChannels(final byte[] pattern, final Converter<List<byte[]>, List<V>> converter,
									   final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PUBSUB_CHANNELS,
					(cmd)->cmd.pubsubChannels(pattern), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PUBSUB_CHANNELS,
					(cmd)->cmd.pubsubChannels(pattern), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.PUBSUB_CHANNELS, (cmd)->cmd.pubsubChannels(pattern),
					converter)
					.run(args);
		}
	}

	private <V> List<V> pubsubShardChannels(final byte[] pattern, final Converter<List<byte[]>, List<V>> converter,
											final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PUBSUB_SHARDCHANNELS,
					(cmd)->cmd.pubsubShardChannels(pattern), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PUBSUB_SHARDCHANNELS,
					(cmd)->cmd.pubsubShardChannels(pattern), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.PUBSUB_SHARDCHANNELS,
					(cmd)->cmd.pubsubShardChannels(pattern), converter)
					.run(args);
		}
	}

	private <K> Map<K, Long> pubsubNumSub(final byte[][] channels,
										  final Converter<Map<byte[], Long>, Map<K, Long>> converter,
										  final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PUBSUB_NUMSUB,
					(cmd)->cmd.pubsubNumsub(channels), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PUBSUB_NUMSUB,
					(cmd)->cmd.pubsubNumsub(channels), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.PUBSUB_NUMSUB, (cmd)->cmd.pubsubNumsub(channels),
					converter)
					.run(args);
		}
	}

	private <K> Map<K, Long> pubsubShardNumSub(final byte[][] channels,
											   final Converter<Map<byte[], Long>, Map<K, Long>> converter,
											   final CommandArguments args) {
		if(isPipeline()){
			return new LettucePipelineCommand<>(client, Command.PUBSUB_SHARDNUMSUB,
					(cmd)->cmd.pubsubShardNumsub(channels), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceTransactionCommand<>(client, Command.PUBSUB_SHARDNUMSUB,
					(cmd)->cmd.pubsubShardNumsub(channels), converter)
					.run(args);
		}else{
			return new LettuceCommand<>(client, Command.PUBSUB_SHARDNUMSUB,
					(cmd)->cmd.pubsubShardNumsub(channels), converter)
					.run(args);
		}
	}

}
