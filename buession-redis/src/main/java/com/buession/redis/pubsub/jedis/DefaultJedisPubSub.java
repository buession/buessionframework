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
package com.buession.redis.pubsub.jedis;

import com.buession.core.utils.Assert;
import com.buession.redis.core.PubSubListener;
import redis.clients.jedis.JedisPubSub;

/**
 * @author Yong.Teng
 */
public class DefaultJedisPubSub extends JedisPubSub {

	private final PubSubListener<String> delegate;

	public DefaultJedisPubSub(PubSubListener<String> pubSubListener) {
		Assert.isNull(pubSubListener, "Pubsub listener cloud not be null.");
		this.delegate = pubSubListener;
	}

	@Override
	public void onMessage(String channel, String message) {
		delegate.onMessage(channel, message);
	}

	@Override
	public void onPMessage(String pattern, String channel, String message) {
		delegate.onPMessage(pattern, channel, message);
	}

	@Override
	public void onSubscribe(String channel, int subscribedChannels) {
		delegate.onSubscribe(channel, subscribedChannels);
	}

	@Override
	public void onUnsubscribe(String channel, int subscribedChannels) {
		delegate.onUnsubscribe(channel, subscribedChannels);
	}

	@Override
	public void onPUnsubscribe(String pattern, int subscribedChannels) {
		delegate.onPUnsubscribe(pattern, subscribedChannels);
	}

	@Override
	public void onPSubscribe(String pattern, int subscribedChannels) {
		delegate.onPSubscribe(pattern, subscribedChannels);
	}

	@Override
	public void onPong(String pattern) {
		delegate.onPong(pattern);
	}

}
