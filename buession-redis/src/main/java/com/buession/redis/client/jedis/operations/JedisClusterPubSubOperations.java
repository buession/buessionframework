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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.redis.client.jedis.JedisClusterClient;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.CommandNotSupported;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.pubsub.jedis.DefaultBinaryJedisPubSub;
import com.buession.redis.pubsub.jedis.DefaultJedisPubSub;
import redis.clients.jedis.JedisCluster;

import java.util.List;
import java.util.Map;

/**
 * Jedis 单机模式 Pub/Sub 命令操作
 *
 * @author Yong.Teng
 */
public final class JedisClusterPubSubOperations extends AbstractPubSubOperations<JedisCluster> {

	public JedisClusterPubSubOperations(final JedisClusterClient client){
		super(client);
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
		final CommandArguments args = CommandArguments.create("patterns", patterns)
				.put("pubSubListener", pubSubListener);

		if(isPipeline()){
			execute(CommandNotSupported.PIPELINE, ProtocolCommand.PSUBSCRIBE, args);
		}else if(isTransaction()){
			execute(CommandNotSupported.TRANSACTION, ProtocolCommand.PSUBSCRIBE, args);
		}else{
			execute((cmd)->{
				cmd.psubscribe(new DefaultJedisPubSub(pubSubListener), patterns);
				return null;
			}, ProtocolCommand.PSUBSCRIBE, args);
		}
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
		final CommandArguments args = CommandArguments.create("patterns", patterns)
				.put("pubSubListener", pubSubListener);

		if(isPipeline()){
			execute(CommandNotSupported.PIPELINE, ProtocolCommand.PSUBSCRIBE, args);
		}else if(isTransaction()){
			execute(CommandNotSupported.TRANSACTION, ProtocolCommand.PSUBSCRIBE, args);
		}else{
			execute((cmd)->{
				cmd.psubscribe(new DefaultBinaryJedisPubSub(pubSubListener), patterns);
				return null;
			}, ProtocolCommand.PSUBSCRIBE, args);
		}
	}

	@Override
	public Long publish(final String channel, final String message){
		final CommandArguments args = CommandArguments.create("channel", channel).put("message", message);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().publish(channel, message)),
					ProtocolCommand.PUBLISH, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().publish(channel, message)),
					ProtocolCommand.PUBLISH, args);
		}else{
			return execute((cmd)->cmd.publish(channel, message), ProtocolCommand.PUBLISH, args);
		}
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message){
		final CommandArguments args = CommandArguments.create("channel", channel).put("message", message);

		if(isPipeline()){
			return pipelineExecute((cmd)->newJedisResult(getPipeline().publish(channel, message)),
					ProtocolCommand.PUBLISH, args);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().publish(channel, message)),
					ProtocolCommand.PUBLISH, args);
		}else{
			return execute((cmd)->cmd.publish(channel, message), ProtocolCommand.PUBLISH, args);
		}
	}

	@Override
	public List<String> pubsubChannels(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.PUBSUB);
	}

	@Override
	public List<String> pubsubChannels(final String pattern){
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return execute(CommandNotSupported.ALL, ProtocolCommand.PUBSUB, args);
	}

	@Override
	public List<byte[]> pubsubChannels(final byte[] pattern){
		final CommandArguments args = CommandArguments.create("pattern", pattern);
		return execute(CommandNotSupported.ALL, ProtocolCommand.PUBSUB, args);
	}

	@Override
	public Long pubsubNumPat(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.PUBSUB);
	}

	@Override
	public Map<String, String> pubsubNumSub(final String... channels){
		final CommandArguments args = CommandArguments.create("channels", channels);
		return execute(CommandNotSupported.ALL, ProtocolCommand.PUBSUB, args);
	}

	@Override
	public Map<byte[], byte[]> pubsubNumSub(final byte[]... channels){
		final CommandArguments args = CommandArguments.create("channels", channels);
		return execute(CommandNotSupported.ALL, ProtocolCommand.PUBSUB, args);
	}

	@Override
	public Object pUnSubscribe(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.PUNSUBSCRIBE);
	}

	@Override
	public Object pUnSubscribe(final String... patterns){
		final CommandArguments args = CommandArguments.create("patterns", patterns);
		return execute(CommandNotSupported.ALL, ProtocolCommand.PUNSUBSCRIBE, args);
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns){
		final CommandArguments args = CommandArguments.create("patterns", patterns);
		return execute(CommandNotSupported.ALL, ProtocolCommand.PUNSUBSCRIBE, args);
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
		final CommandArguments args = CommandArguments.create("channels", channels)
				.put("pubSubListener", pubSubListener);

		if(isPipeline()){
			execute(CommandNotSupported.PIPELINE, ProtocolCommand.SUBSCRIBE, args);
		}else if(isTransaction()){
			execute(CommandNotSupported.PIPELINE, ProtocolCommand.SUBSCRIBE, args);
		}else{
			execute((cmd)->{
				cmd.subscribe(new DefaultJedisPubSub(pubSubListener), channels);
				return null;
			}, ProtocolCommand.SUBSCRIBE, args);
		}
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
		final CommandArguments args = CommandArguments.create("channels", channels)
				.put("pubSubListener", pubSubListener);

		if(isPipeline()){
			execute(CommandNotSupported.PIPELINE, ProtocolCommand.SUBSCRIBE, args);
		}else if(isTransaction()){
			execute(CommandNotSupported.PIPELINE, ProtocolCommand.SUBSCRIBE, args);
		}else{
			execute((cmd)->{
				cmd.subscribe(new DefaultBinaryJedisPubSub(pubSubListener), channels);
				return null;
			}, ProtocolCommand.SUBSCRIBE, args);
		}
	}

	@Override
	public Object unSubscribe(){
		return execute(CommandNotSupported.ALL, ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	public Object unSubscribe(final String... channels){
		final CommandArguments args = CommandArguments.create("channels", channels);
		return execute(CommandNotSupported.ALL, ProtocolCommand.UNSUBSCRIBE, args);
	}

	@Override
	public Object unSubscribe(final byte[]... channels){
		final CommandArguments args = CommandArguments.create("channels", channels);
		return execute(CommandNotSupported.ALL, ProtocolCommand.UNSUBSCRIBE, args);
	}

}
