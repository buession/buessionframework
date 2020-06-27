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

import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.core.ClusterMode;
import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.command.ProtocolCommand;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public class ShardedJedisPubSubOperations extends AbstractPubSubOperations<ShardedJedis, ShardedJedisPipeline> {

	public ShardedJedisPubSubOperations(final JedisRedisClient<ShardedJedis> client){
		super(client, ClusterMode.SHARDED);
	}

	@Override
	public void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener){
		commandAllNotSupportedException(ProtocolCommand.PSUBSCRIBE);
	}

	@Override
	public void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener){
		commandAllNotSupportedException(ProtocolCommand.PSUBSCRIBE);
	}

	@Override
	public List<String> pubsubChannels(){
		commandAllNotSupportedException(ProtocolCommand.PUBSUB);
		return null;
	}

	@Override
	public List<String> pubsubChannels(final String pattern){
		commandAllNotSupportedException(ProtocolCommand.PUBSUB);
		return null;
	}

	@Override
	public List<byte[]> pubsubChannels(final byte[] pattern){
		commandAllNotSupportedException(ProtocolCommand.PUBSUB);
		return null;
	}

	@Override
	public Long pubsubNumPat(){
		commandAllNotSupportedException(ProtocolCommand.PUBSUB);
		return null;
	}

	@Override
	public Map<String, String> pubsubNumSub(final String... channels){
		commandAllNotSupportedException(ProtocolCommand.PUBSUB);
		return null;
	}

	@Override
	public Map<byte[], byte[]> pubsubNumSub(final byte[]... channels){
		commandAllNotSupportedException(ProtocolCommand.PUBSUB);
		return null;
	}

	@Override
	public Long publish(final String channel, final String message){
		commandAllNotSupportedException(ProtocolCommand.PUBLISH);
		return null;
	}

	@Override
	public Long publish(final byte[] channel, final byte[] message){
		commandAllNotSupportedException(ProtocolCommand.PUBLISH);
		return null;
	}

	@Override
	public Object pUnSubscribe(){
		commandAllNotSupportedException(ProtocolCommand.PUNSUBSCRIBE);
		return null;
	}

	@Override
	public Object pUnSubscribe(final String... patterns){
		commandAllNotSupportedException(ProtocolCommand.PUNSUBSCRIBE);
		return null;
	}

	@Override
	public Object pUnSubscribe(final byte[]... patterns){
		commandAllNotSupportedException(ProtocolCommand.PUNSUBSCRIBE);
		return null;
	}

	@Override
	public void subscribe(final String[] channels, final PubSubListener<String> pubSubListener){
		commandAllNotSupportedException(ProtocolCommand.SUBSCRIBE);
	}

	@Override
	public void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener){
		commandAllNotSupportedException(ProtocolCommand.SUBSCRIBE);
	}

	@Override
	public Object unSubscribe(){
		commandAllNotSupportedException(ProtocolCommand.UNSUBSCRIBE);
		return null;
	}

	@Override
	public Object unSubscribe(final String... channels){
		commandAllNotSupportedException(ProtocolCommand.UNSUBSCRIBE);
		return null;
	}

	@Override
	public Object unSubscribe(final byte[]... channels){
		commandAllNotSupportedException(ProtocolCommand.UNSUBSCRIBE);
		return null;
	}

}
