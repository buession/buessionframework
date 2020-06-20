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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.redis.client.RedisClient;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.NotSupportedPipelineCommandException;
import com.buession.redis.exception.NotSupportedTransactionCommandException;
import com.buession.redis.pubsub.jedis.DefaultBinaryJedisPubSub;
import com.buession.redis.pubsub.jedis.DefaultJedisPubSub;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class JedisPubSubOperations extends AbstractPubSubOperations<Jedis> {

	public JedisPubSubOperations(final RedisClient client){
		super(client);
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
		if(isPipeline()){
			throw new NotSupportedPipelineCommandException(ProtocolCommand.PSUBSCRIBE);
		}else if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.PSUBSCRIBE);
		}else{
			execute((cmd)->{
				cmd.psubscribe(new DefaultJedisPubSub(pubSubListener), patterns);
				return null;
			});
		}
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
		if(isPipeline()){
			throw new NotSupportedPipelineCommandException(ProtocolCommand.PSUBSCRIBE);
		}else if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.PSUBSCRIBE);
		}else{
			execute((cmd)->{
				cmd.psubscribe(new DefaultBinaryJedisPubSub(pubSubListener), patterns);
				return null;
			});
		}
	}

	@Override
	public List<String> pubsubChannels(){
		if(isPipeline()){
			throw new NotSupportedPipelineCommandException(ProtocolCommand.PUBSUB);
		}else if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.PUBSUB);
		}else{
			return execute((cmd)->cmd.pubsubChannels(null));
		}
	}

	@Override
	public List<String> pubsubChannels(final String pattern){
		if(isPipeline()){
			throw new NotSupportedPipelineCommandException(ProtocolCommand.PUBSUB);
		}else if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.PUBSUB);
		}else{
			return execute((cmd)->cmd.pubsubChannels(pattern));
		}
	}

	@Override
	public List<byte[]> pubsubChannels(final byte[] pattern){
		return STRING_TO_BINARY_LIST_CONVERTER.convert(pubsubChannels(SafeEncoder.encode(pattern)));
	}

	@Override
	public Long pubsubNumPat(){
		if(isPipeline()){
			throw new NotSupportedPipelineCommandException(ProtocolCommand.PUBSUB);
		}else if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.PUBSUB);
		}else{
			return execute((cmd)->cmd.pubsubNumPat());
		}
	}

	@Override
	public Map<String, String> pubsubNumSub(final String... channels){
		if(isPipeline()){
			throw new NotSupportedPipelineCommandException(ProtocolCommand.PUBSUB);
		}else if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.PUBSUB);
		}else{
			return execute((cmd)->cmd.pubsubNumSub(channels));
		}
	}

	@Override
	public Map<byte[], byte[]> pubsubNumSub(final byte[]... channels){
		if(isPipeline()){
			throw new NotSupportedPipelineCommandException(ProtocolCommand.PSUBSCRIBE);
		}else if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.PSUBSCRIBE);
		}else{
			return execute((cmd)->{
				String[] sChannels = new String[channels.length];

				for(int i = 0; i < channels.length; i++){
					sChannels[i] = SafeEncoder.encode(channels[i]);
				}

				return STRING_TO_BINARY_HASH_CONVERTER.convert(cmd.pubsubNumSub(sChannels));
			});
		}
	}

	@Override
	public Long publish(final String channel, final String message){
		if(isPipeline()){
			throw new NotSupportedPipelineCommandException(ProtocolCommand.PUBLISH);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().publish(channel, message)));
		}else{
			return execute((cmd)->cmd.publish(channel, message));
		}
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message){
		if(isPipeline()){
			throw new NotSupportedPipelineCommandException(ProtocolCommand.PUBLISH);
		}else if(isTransaction()){
			return transactionExecute((cmd)->newJedisResult(getTransaction().publish(channel, message)));
		}else{
			return execute((cmd)->cmd.publish(channel, message));
		}
	}

	@Override
	public Object pUnSubscribe(){
		throw new NotSupportedCommandException(ProtocolCommand.PUNSUBSCRIBE);
	}

	@Override
	public Object pUnSubscribe(final String... patterns){
		throw new NotSupportedCommandException(ProtocolCommand.PUNSUBSCRIBE);
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns){
		throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
		if(isPipeline()){
			throw new NotSupportedPipelineCommandException(ProtocolCommand.SUBSCRIBE);
		}else if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SUBSCRIBE);
		}else{
			execute((cmd)->{
				cmd.subscribe(new DefaultJedisPubSub(pubSubListener), channels);
				return null;
			});
		}
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
		if(isPipeline()){
			throw new NotSupportedPipelineCommandException(ProtocolCommand.SUBSCRIBE);
		}else if(isTransaction()){
			throw new NotSupportedTransactionCommandException(ProtocolCommand.SUBSCRIBE);
		}else{
			execute((cmd)->{
				cmd.subscribe(new DefaultBinaryJedisPubSub(pubSubListener), channels);
				return null;
			});
		}
	}

	@Override
	public Object unSubscribe(){
		throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	public Object unSubscribe(final String... channels){
		throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
	}

	@Override
	public Object unSubscribe(final byte[]... channels){
		throw new NotSupportedCommandException(ProtocolCommand.UNSUBSCRIBE);
	}

}
