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
package com.buession.redis.core.operations;

import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.command.PubSubCommands;

import java.util.List;
import java.util.Map;

/**
 * 发布与订阅命运算
 *
 * <p>详情说明 <a href="http://redisdoc.com/pubsub/index.html" target="_blank">http://redisdoc.com/pubsub/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface PubSubOperations extends PubSubCommands, RedisOperations {

	@Override
	default void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener) {
		execute((client)->{
			client.pubSubOperations().pSubscribe(patterns, pubSubListener);
			return null;
		});
	}

	@Override
	default void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener) {
		execute((client)->{
			client.pubSubOperations().pSubscribe(patterns, pubSubListener);
			return null;
		});
	}

	@Override
	default Long publish(final String channel, final String message) {
		return execute((client)->client.pubSubOperations().publish(channel, message));
	}

	@Override
	default Long publish(final byte[] channel, final byte[] message) {
		return execute((client)->client.pubSubOperations().publish(channel, message));
	}

	@Override
	default List<String> pubsubChannels() {
		return execute((client)->client.pubSubOperations().pubsubChannels());
	}

	@Override
	default List<String> pubsubChannels(final String pattern) {
		return execute((client)->client.pubSubOperations().pubsubChannels(pattern));
	}

	@Override
	default List<byte[]> pubsubChannels(final byte[] pattern) {
		return execute((client)->client.pubSubOperations().pubsubChannels(pattern));
	}

	@Override
	default Long pubsubNumPat() {
		return execute((client)->client.pubSubOperations().pubsubNumPat());
	}

	@Override
	default Map<String, Long> pubsubNumSub(final String... channels) {
		return execute((client)->client.pubSubOperations().pubsubNumSub(channels));
	}

	@Override
	default Map<byte[], Long> pubsubNumSub(final byte[]... channels) {
		return execute((client)->client.pubSubOperations().pubsubNumSub(channels));
	}

	@Override
	default Object pUnSubscribe() {
		return execute((client)->client.pubSubOperations().pUnSubscribe());
	}

	@Override
	default Object pUnSubscribe(final String... patterns) {
		return execute((client)->client.pubSubOperations().pUnSubscribe(patterns));
	}

	@Override
	default Object pUnSubscribe(final byte[]... patterns) {
		return execute((client)->client.pubSubOperations().pUnSubscribe(patterns));
	}

	@Override
	default void subscribe(final String[] channels, final PubSubListener<String> pubSubListener) {
		execute((client)->{
			client.pubSubOperations().subscribe(channels, pubSubListener);
			return null;
		});
	}

	@Override
	default void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener) {
		execute((client)->{
			client.pubSubOperations().subscribe(channels, pubSubListener);
			return null;
		});
	}

	@Override
	default Object unSubscribe() {
		return execute((client)->client.pubSubOperations().unSubscribe());
	}

	@Override
	default Object unSubscribe(final String... channels) {
		return execute((client)->client.pubSubOperations().unSubscribe(channels));
	}

	@Override
	default Object unSubscribe(final byte[]... channels) {
		return execute((client)->client.pubSubOperations().unSubscribe(channels));
	}

}
