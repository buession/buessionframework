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

import com.buession.core.converter.Converter;
import com.buession.core.converter.MapConverter;
import com.buession.redis.client.jedis.JedisStandaloneClient;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.Command;
import com.buession.redis.core.internal.convert.Converters;
import com.buession.redis.pubsub.jedis.DefaultBinaryJedisPubSub;
import com.buession.redis.pubsub.jedis.DefaultJedisPubSub;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.Map;

/**
 * Jedis 单机模式 Pub/Sub 命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisPubSubOperations extends AbstractPubSubOperations<JedisStandaloneClient> {

	public JedisPubSubOperations(final JedisStandaloneClient client) {
		super(client);
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener) {
		final CommandArguments args = CommandArguments.create("patterns", (Object[]) patterns)
				.put("pubSubListener", pubSubListener);

		if(isPipeline()){
			new JedisPipelineCommand<>(client, Command.PSUBSCRIBE)
					.run(args);
		}else if(isTransaction()){
			new JedisTransactionCommand<>(client, Command.PSUBSCRIBE)
					.run(args);
		}else{
			new JedisCommand<>(client, Command.PSUBSCRIBE, (cmd)->{
				cmd.psubscribe(new DefaultJedisPubSub(pubSubListener), patterns);
				return null;
			}, (v)->v)
					.run(args);
		}
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener) {
		final CommandArguments args = CommandArguments.create("patterns", (Object[]) patterns)
				.put("pubSubListener", pubSubListener);

		if(isPipeline()){
			new JedisPipelineCommand<>(client, Command.PSUBSCRIBE)
					.run(args);
		}else if(isTransaction()){
			new JedisTransactionCommand<>(client, Command.PSUBSCRIBE)
					.run(args);
		}else{
			new JedisCommand<>(client, Command.PSUBSCRIBE, (cmd)->{
				cmd.psubscribe(new DefaultBinaryJedisPubSub(pubSubListener), patterns);
				return null;
			}, (v)->v)
					.run(args);
		}
	}

	@Override
	public Long publish(final String channel, final String message) {
		final CommandArguments args = CommandArguments.create("channel", channel).put("message", message);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.PUBLISH, (cmd)->cmd.publish(channel, message),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Long, Long>(client, Command.PUBLISH)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.PUBLISH, (cmd)->cmd.publish(channel, message), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message) {
		final CommandArguments args = CommandArguments.create("channel", channel).put("message", message);

		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.PUBLISH, (cmd)->cmd.publish(channel, message),
					(v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Long, Long>(client, Command.PUBLISH)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.PUBLISH, (cmd)->cmd.publish(channel, message), (v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> pubsubChannels() {
		if(isPipeline()){
			return new JedisPipelineCommand<List<String>, List<String>>(client, Command.PUBSUB_CHANNELS)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<List<String>, List<String>>(client, Command.PUBSUB_CHANNELS)
					.run();
		}else{
			return new JedisCommand<>(client, Command.PUBSUB_CHANNELS, (cmd)->cmd.pubsubChannels(), (v)->v)
					.run();
		}
	}

	@Override
	public List<String> pubsubChannels(final String pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return pubsubChannels(pattern, (v)->v, args);
	}

	@Override
	public List<byte[]> pubsubChannels(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		final String sPattern = SafeEncoder.encode(pattern);
		final com.buession.core.converter.ListConverter<String, byte[]> stringToBinaryListConverter =
				Converters.listStringToBinary();

		return pubsubChannels(sPattern, stringToBinaryListConverter, args);
	}

	@Override
	public List<String> pubsubShardChannels() {
		if(isPipeline()){
			return new JedisPipelineCommand<List<String>, List<String>>(client,
					Command.PUBSUB_SHARDCHANNELS)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<List<String>, List<String>>(client,
					Command.PUBSUB_SHARDCHANNELS)
					.run();
		}else{
			return new JedisCommand<>(client, Command.PUBSUB_SHARDCHANNELS, (cmd)->cmd.pubsubShardChannels(),
					(v)->v)
					.run();
		}
	}

	@Override
	public List<String> pubsubShardChannels(final String pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return pubsubShardChannels(pattern, (v)->v, args);
	}

	@Override
	public List<byte[]> pubsubShardChannels(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		final String sPattern = SafeEncoder.encode(pattern);
		final com.buession.core.converter.ListConverter<String, byte[]> stringToBinaryListConverter =
				Converters.listStringToBinary();

		return pubsubShardChannels(sPattern, stringToBinaryListConverter, args);
	}

	@Override
	public Long pubsubNumPat() {
		if(isPipeline()){
			return new JedisPipelineCommand<Long, Long>(client, Command.PUBSUB_NUMPAT)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<Long, Long>(client, Command.PUBSUB_NUMPAT)
					.run();
		}else{
			return new JedisCommand<>(client, Command.PUBSUB_NUMPAT, (cmd)->cmd.pubsubNumPat(), (v)->v)
					.run();
		}
	}

	@Override
	public Map<String, Long> pubsubNumSub() {
		if(isPipeline()){
			return new JedisPipelineCommand<Map<String, Long>, Map<String, Long>>(client,
					Command.PUBSUB_NUMSUB)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<Map<String, Long>, Map<String, Long>>(client,
					Command.PUBSUB_NUMSUB)
					.run();
		}else{
			return new JedisCommand<>(client, Command.PUBSUB_NUMSUB, (cmd)->cmd.pubsubNumSub(), (v)->v)
					.run();
		}
	}

	@Override
	public Map<String, Long> pubsubNumSub(final String... channels) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		return pubsubNumSub(channels, (v)->v, args);
	}

	@Override
	public Map<byte[], Long> pubsubNumSub(final byte[]... channels) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		final String[] sChannels = SafeEncoder.encode(channels);
		final MapConverter<String, Long, byte[], Long> stringToBinaryKeyPrimitiveValueMapConverter =
				new MapConverter<>(SafeEncoder::encode, (v)->v);

		return pubsubNumSub(sChannels, stringToBinaryKeyPrimitiveValueMapConverter, args);
	}

	@Override
	public Map<String, Long> pubsubShardNumSub() {
		if(isPipeline()){
			return new JedisPipelineCommand<Map<String, Long>, Map<String, Long>>(client,
					Command.PUBSUB_SHARDNUMSUB)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<Map<String, Long>, Map<String, Long>>(client,
					Command.PUBSUB_SHARDNUMSUB)
					.run();
		}else{
			return new JedisCommand<>(client, Command.PUBSUB_SHARDNUMSUB, (cmd)->cmd.pubsubShardNumSub(),
					(v)->v)
					.run();
		}
	}

	@Override
	public Map<String, Long> pubsubShardNumSub(final String... shardChannels) {
		final CommandArguments args = CommandArguments.create("shardChannels", (Object[]) shardChannels);
		return pubsubShardNumSub(shardChannels, (v)->v, args);
	}

	@Override
	public Map<byte[], Long> pubsubShardNumSub(final byte[]... shardChannels) {
		final CommandArguments args = CommandArguments.create("shardChannels", (Object[]) shardChannels);
		final String[] sChannels = SafeEncoder.encode(shardChannels);
		final MapConverter<String, Long, byte[], Long> stringToBinaryKeyPrimitiveValueMapConverter =
				new MapConverter<>(SafeEncoder::encode, (v)->v);

		return pubsubShardNumSub(sChannels, stringToBinaryKeyPrimitiveValueMapConverter, args);
	}

	@Override
	public Object pUnSubscribe() {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.PUNSUBSCRIBE)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.PUNSUBSCRIBE)
					.run();
		}else{
			return new JedisCommand<>(client, Command.PUNSUBSCRIBE)
					.run();
		}
	}

	@Override
	public Object pUnSubscribe(final String... patterns) {
		final CommandArguments args = CommandArguments.create("patterns", (Object[]) patterns);
		return pUnSubscribe(args);
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns) {
		final CommandArguments args = CommandArguments.create("patterns", (Object[]) patterns);
		return pUnSubscribe(args);
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels)
				.put("pubSubListener", pubSubListener);

		if(isPipeline()){
			new JedisPipelineCommand<>(client, Command.SUBSCRIBE)
					.run(args);
		}else if(isTransaction()){
			new JedisTransactionCommand<>(client, Command.SUBSCRIBE)
					.run(args);
		}else{
			new JedisCommand<>(client, Command.SUBSCRIBE, (cmd)->{
				cmd.subscribe(new DefaultJedisPubSub(pubSubListener), channels);
				return null;
			}, (v)->v)
					.run(args);
		}
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels)
				.put("pubSubListener", pubSubListener);

		if(isPipeline()){
			new JedisPipelineCommand<>(client, Command.SUBSCRIBE)
					.run(args);
		}else if(isTransaction()){
			new JedisTransactionCommand<>(client, Command.SUBSCRIBE)
					.run(args);
		}else{
			new JedisCommand<>(client, Command.SUBSCRIBE, (cmd)->{
				cmd.subscribe(new DefaultBinaryJedisPubSub(pubSubListener), channels);
				return null;
			}, (v)->v)
					.run(args);
		}
	}

	@Override
	public Object unSubscribe() {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.UNSUBSCRIBE)
					.run();
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.UNSUBSCRIBE)
					.run();
		}else{
			return new JedisCommand<>(client, Command.UNSUBSCRIBE)
					.run();
		}
	}

	@Override
	public Object unSubscribe(final String... channels) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		return unSubscribe(args);
	}

	@Override
	public Object unSubscribe(final byte[]... channels) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		return unSubscribe(args);
	}

	private <V> List<V> pubsubChannels(final String pattern,
									   final Converter<List<String>, List<V>> converter, final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<List<V>, List<V>>(client, Command.PUBSUB_CHANNELS)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<List<V>, List<V>>(client, Command.PUBSUB_CHANNELS)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.PUBSUB_CHANNELS, (cmd)->cmd.pubsubChannels(pattern),
					converter)
					.run();
		}
	}

	private <V> List<V> pubsubShardChannels(final String pattern,
											final Converter<List<String>, List<V>> converter,
											final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<List<V>, List<V>>(client, Command.PUBSUB_SHARDCHANNELS)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<List<V>, List<V>>(client, Command.PUBSUB_SHARDCHANNELS)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.PUBSUB_SHARDCHANNELS,
					(cmd)->cmd.pubsubShardChannels(pattern), converter)
					.run();
		}
	}

	private <K> Map<K, Long> pubsubNumSub(final String[] channels,
										  final Converter<Map<String, Long>, Map<K, Long>> converter,
										  final CommandArguments args) {

		if(isPipeline()){
			return new JedisPipelineCommand<Map<K, Long>, Map<K, Long>>(client, Command.PUBSUB_NUMSUB)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Map<K, Long>, Map<K, Long>>(client, Command.PUBSUB_NUMSUB)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.PUBSUB_NUMSUB, (cmd)->cmd.pubsubNumSub(channels),
					converter)
					.run(args);
		}
	}

	private <K> Map<K, Long> pubsubShardNumSub(final String[] shardChannels,
											   final Converter<Map<String, Long>, Map<K, Long>> converter,
											   final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<Map<K, Long>, Map<K, Long>>(client, Command.PUBSUB_NUMSUB)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<Map<K, Long>, Map<K, Long>>(client, Command.PUBSUB_NUMSUB)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.PUBSUB_NUMSUB,
					(cmd)->cmd.pubsubShardNumSub(shardChannels), converter)
					.run(args);
		}
	}

	private Object pUnSubscribe(final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.PUNSUBSCRIBE)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.PUNSUBSCRIBE)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.PUNSUBSCRIBE)
					.run(args);
		}
	}

	private Object unSubscribe(final CommandArguments args) {
		if(isPipeline()){
			return new JedisPipelineCommand<>(client, Command.UNSUBSCRIBE)
					.run(args);
		}else if(isTransaction()){
			return new JedisTransactionCommand<>(client, Command.UNSUBSCRIBE)
					.run(args);
		}else{
			return new JedisCommand<>(client, Command.UNSUBSCRIBE)
					.run(args);
		}
	}

}
