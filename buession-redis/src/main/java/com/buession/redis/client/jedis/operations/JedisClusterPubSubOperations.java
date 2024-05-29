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

import com.buession.core.converter.Converter;
import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.response.ListConverter;
import com.buession.redis.core.internal.convert.response.MapConverter;
import com.buession.redis.pubsub.jedis.DefaultBinaryJedisPubSub;
import com.buession.redis.pubsub.jedis.DefaultJedisPubSub;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.Map;

/**
 * Jedis 集群模式 Pub/Sub 命令操作
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public class JedisClusterPubSubOperations extends AbstractPubSubOperations<JedisClusterClient> {

	public JedisClusterPubSubOperations(final JedisClusterClient client) {
		super(client);
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener) {
		final CommandArguments args = CommandArguments.create("patterns", (Object[]) patterns)
				.put("pubSubListener", pubSubListener);

		if(isPipeline()){
			new JedisClusterPipelineCommand<>(client, ProtocolCommand.PSUBSCRIBE)
					.run(args);
		}else if(isTransaction()){
			new JedisClusterTransactionCommand<>(client, ProtocolCommand.PSUBSCRIBE)
					.run(args);
		}else{
			new JedisClusterCommand<>(client, ProtocolCommand.PSUBSCRIBE, (cmd)->{
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
			new JedisClusterPipelineCommand<>(client, ProtocolCommand.PSUBSCRIBE)
					.run(args);
		}else if(isTransaction()){
			new JedisClusterTransactionCommand<>(client, ProtocolCommand.PSUBSCRIBE)
					.run(args);
		}else{
			new JedisClusterCommand<>(client, ProtocolCommand.PSUBSCRIBE, (cmd)->{
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
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.PUBLISH,
					(cmd)->cmd.publish(channel, message), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.PUBLISH,
					(cmd)->cmd.publish(channel, message), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.PUBLISH, (cmd)->cmd.publish(channel, message),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message) {
		final CommandArguments args = CommandArguments.create("channel", channel).put("message", message);

		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.PUBLISH,
					(cmd)->cmd.publish(channel, message), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.PUBLISH,
					(cmd)->cmd.publish(channel, message), (v)->v)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.PUBLISH, (cmd)->cmd.publish(channel, message),
					(v)->v)
					.run(args);
		}
	}

	@Override
	public List<String> pubsubChannels() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<String>, List<String>>(client, ProtocolCommand.PUBSUB_CHANNELS)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<String>, List<String>>(client,
					ProtocolCommand.PUBSUB_CHANNELS)
					.run();
		}else{
			return new JedisClusterCommand<List<String>, List<String>>(client, ProtocolCommand.PUBSUB_CHANNELS)
					.run();
		}
	}

	@Override
	public List<String> pubsubChannels(final String pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return pubsubChannels(args);
	}

	@Override
	public List<byte[]> pubsubChannels(final byte[] pattern) {
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return pubsubChannels(args);
	}

	@Override
	public Long pubsubNumPat() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<Long, Long>(client, ProtocolCommand.PUBSUB_NUMPAT)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Long, Long>(client, ProtocolCommand.PUBSUB_NUMPAT)
					.run();
		}else{
			return new JedisClusterCommand<Long, Long>(client, ProtocolCommand.PUBSUB_NUMPAT)
					.run();
		}
	}

	@Override
	public Map<String, Long> pubsubNumSub(final String... channels) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		return pubsubNumSub(args);
	}

	@Override
	public Map<byte[], Long> pubsubNumSub(final byte[]... channels) {
		final CommandArguments args = CommandArguments.create("channels", (Object[]) channels);
		return pubsubNumSub(args);
	}

	@Override
	public Object pUnSubscribe() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.PUNSUBSCRIBE)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.PUNSUBSCRIBE)
					.run();
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.PUNSUBSCRIBE)
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
			new JedisClusterPipelineCommand<>(client, ProtocolCommand.SUBSCRIBE)
					.run(args);
		}else if(isTransaction()){
			new JedisClusterTransactionCommand<>(client, ProtocolCommand.SUBSCRIBE)
					.run(args);
		}else{
			new JedisClusterCommand<>(client, ProtocolCommand.SUBSCRIBE, (cmd)->{
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
			new JedisClusterPipelineCommand<>(client, ProtocolCommand.SUBSCRIBE)
					.run(args);
		}else if(isTransaction()){
			new JedisClusterTransactionCommand<>(client, ProtocolCommand.SUBSCRIBE)
					.run(args);
		}else{
			new JedisClusterCommand<>(client, ProtocolCommand.SUBSCRIBE, (cmd)->{
				cmd.subscribe(new DefaultBinaryJedisPubSub(pubSubListener), channels);
				return null;
			}, (v)->v)
					.run(args);
		}
	}

	@Override
	public Object unSubscribe() {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.UNSUBSCRIBE)
					.run();
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.UNSUBSCRIBE)
					.run();
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.UNSUBSCRIBE)
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

	private <V> List<V> pubsubChannels(final CommandArguments args) {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<List<V>, List<V>>(client, ProtocolCommand.PUBSUB_CHANNELS)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<List<V>, List<V>>(client, ProtocolCommand.PUBSUB_CHANNELS)
					.run(args);
		}else{
			return new JedisClusterCommand<List<V>, List<V>>(client, ProtocolCommand.PUBSUB_CHANNELS)
					.run();
		}
	}

	private <K> Map<K, Long> pubsubNumSub(final CommandArguments args) {

		if(isPipeline()){
			return new JedisClusterPipelineCommand<Map<K, Long>, Map<K, Long>>(client, ProtocolCommand.PUBSUB_NUMSUB)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<Map<K, Long>, Map<K, Long>>(client, ProtocolCommand.PUBSUB_NUMSUB)
					.run(args);
		}else{
			return new JedisClusterCommand<Map<K, Long>, Map<K, Long>>(client, ProtocolCommand.PUBSUB_NUMSUB)
					.run(args);
		}
	}

	private Object pUnSubscribe(final CommandArguments args) {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.PUNSUBSCRIBE)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.PUNSUBSCRIBE)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.PUNSUBSCRIBE)
					.run(args);
		}
	}

	private Object unSubscribe(final CommandArguments args) {
		if(isPipeline()){
			return new JedisClusterPipelineCommand<>(client, ProtocolCommand.UNSUBSCRIBE)
					.run(args);
		}else if(isTransaction()){
			return new JedisClusterTransactionCommand<>(client, ProtocolCommand.UNSUBSCRIBE)
					.run(args);
		}else{
			return new JedisClusterCommand<>(client, ProtocolCommand.UNSUBSCRIBE)
					.run(args);
		}
	}

}
