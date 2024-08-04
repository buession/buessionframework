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
package com.buession.redis.core.command;

import com.buession.redis.core.PubSubListener;

import java.util.List;
import java.util.Map;

/**
 * 发布与订阅命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/pubsub/index.html" target="_blank">http://redisdoc.com/pubsub/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface PubSubCommands extends RedisCommands {

	/**
	 * 订阅一个或多个符合给定模式的频道的信息
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/pubsub/psubscribe.html" target="_blank">http://redisdoc.com/pubsub/psubscribe.html</a></p>
	 *
	 * @param patterns
	 * 		一个或多个模式
	 * @param pubSubListener
	 * 		订阅监听者
	 */
	void pSubscribe(final String[] patterns, final PubSubListener<String> pubSubListener);

	/**
	 * 订阅一个或多个符合给定模式的频道的信息
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/pubsub/psubscribe.html" target="_blank">http://redisdoc.com/pubsub/psubscribe.html</a></p>
	 *
	 * @param patterns
	 * 		一个或多个模式
	 * @param pubSubListener
	 * 		订阅监听者
	 */
	void pSubscribe(final byte[][] patterns, final PubSubListener<byte[]> pubSubListener);

	/**
	 * 将信息 message 发送到指定的频道 channel
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/pubsub/publish.html" target="_blank">http://redisdoc.com/pubsub/publish.html</a></p>
	 *
	 * @param channel
	 * 		频道名称
	 * @param message
	 * 		信息
	 *
	 * @return 接收到信息 message 的订阅者数量
	 */
	Long publish(final String channel, final String message);

	/**
	 * 将信息 message 发送到指定的频道 channel
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/pubsub/publish.html" target="_blank">http://redisdoc.com/pubsub/publish.html</a></p>
	 *
	 * @param channel
	 * 		频道名称
	 * @param message
	 * 		信息
	 *
	 * @return 接收到信息 message 的订阅者数量
	 */
	Long publish(final byte[] channel, final byte[] message);

	/**
	 * 列出当前 active channels 活跃是指信道含有一个或多个订阅者(不包括从模式接收订阅的客户端)
	 * 如果 pattern未提供，所有的信道都被列出，否则只列出匹配上指定全局-类型模式的信道被列出
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/pubsub.html" target="_blank">https://www.redis.com.cn/commands/pubsub.html</a></p>
	 *
	 * @return 活跃的信道列表，或者符合指定模式的信道
	 */
	List<String> pubsubChannels();

	/**
	 * 列出当前 active channels 活跃是指信道含有一个或多个订阅者(不包括从模式接收订阅的客户端)
	 * 如果 pattern未提供，所有的信道都被列出，否则只列出匹配上指定全局-类型模式的信道被列出
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/pubsub.html" target="_blank">https://www.redis.com.cn/commands/pubsub.html</a></p>
	 *
	 * @param pattern
	 * 		匹配模式
	 *
	 * @return 活跃的信道列表，或者符合指定模式的信道
	 */
	List<String> pubsubChannels(final String pattern);

	/**
	 * 列出当前 active channels 活跃是指信道含有一个或多个订阅者(不包括从模式接收订阅的客户端)
	 * 如果 pattern未提供，所有的信道都被列出，否则只列出匹配上指定全局-类型模式的信道被列出
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/pubsub.html" target="_blank">https://www.redis.com.cn/commands/pubsub.html</a></p>
	 *
	 * @param pattern
	 * 		匹配模式
	 *
	 * @return 活跃的信道列表，或者符合指定模式的信道
	 */
	List<byte[]> pubsubChannels(final byte[] pattern);

	/**
	 * 列出当前 active channels 活跃是指信道含有一个或多个订阅者(不包括从模式接收订阅的客户端)
	 * 如果 pattern未提供，所有的信道都被列出，否则只列出匹配上指定全局-类型模式的信道被列出
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pubsub-shardchannels/" target="_blank">https://redis.io/docs/latest/commands/pubsub-shardchannels/</a></p>
	 *
	 * @return 活跃的信道列表，或者符合指定模式的信道
	 *
	 * @since 3.0.0
	 */
	List<String> pubsubShardChannels();

	/**
	 * 列出当前 active channels 活跃是指信道含有一个或多个订阅者(不包括从模式接收订阅的客户端)
	 * 如果 pattern未提供，所有的信道都被列出，否则只列出匹配上指定全局-类型模式的信道被列出
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pubsub-shardchannels/" target="_blank">https://redis.io/docs/latest/commands/pubsub-shardchannels/</a></p>
	 *
	 * @param pattern
	 * 		匹配模式
	 *
	 * @return 活跃的信道列表，或者符合指定模式的信道
	 *
	 * @since 3.0.0
	 */
	List<String> pubsubShardChannels(final String pattern);

	/**
	 * 列出当前 active channels 活跃是指信道含有一个或多个订阅者(不包括从模式接收订阅的客户端)
	 * 如果 pattern未提供，所有的信道都被列出，否则只列出匹配上指定全局-类型模式的信道被列出
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pubsub-shardchannels/" target="_blank">https://redis.io/docs/latest/commands/pubsub-shardchannels/</a></p>
	 *
	 * @param pattern
	 * 		匹配模式
	 *
	 * @return 活跃的信道列表，或者符合指定模式的信道
	 *
	 * @since 3.0.0
	 */
	List<byte[]> pubsubShardChannels(final byte[] pattern);

	/**
	 * 返回订阅模式的数量(使用命令PSUBSCRIBE实现)。
	 * 注意，这个命令返回的不是订阅模式的客户端的数量， 而是客户端订阅的所有模式的数量总和。
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/pubsub.html" target="_blank">https://www.redis.com.cn/commands/pubsub.html</a></p>
	 *
	 * @return 阅模式的数量
	 */
	Long pubsubNumPat();

	/**
	 * 列出指定信道的订阅者个数(不包括订阅模式的客户端订阅者)
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pubsub-numsub/" target="_blank">https://redis.io/docs/latest/commands/pubsub-numsub/</a></p>
	 *
	 * @return 指定信道的订阅者个数
	 *
	 * @since 3.0.0
	 */
	Map<String, Long> pubsubNumSub();

	/**
	 * 列出指定信道的订阅者个数(不包括订阅模式的客户端订阅者)
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pubsub-numsub/" target="_blank">https://redis.io/docs/latest/commands/pubsub-numsub/</a></p>
	 *
	 * @param channels
	 * 		一个或多个频道
	 *
	 * @return 指定信道的订阅者个数
	 */
	Map<String, Long> pubsubNumSub(final String... channels);

	/**
	 * 列出指定信道的订阅者个数(不包括订阅模式的客户端订阅者)
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pubsub-numsub/" target="_blank">https://redis.io/docs/latest/commands/pubsub-numsub/</a></p>
	 *
	 * @param channels
	 * 		一个或多个频道
	 *
	 * @return 指定信道的订阅者个数
	 */
	Map<byte[], Long> pubsubNumSub(final byte[]... channels);

	/**
	 * 列出指定信道的订阅者个数(不包括订阅模式的客户端订阅者)
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pubsub-shardnumsub/" target="_blank">https://redis.io/docs/latest/commands/pubsub-shardnumsub/</a></p>
	 *
	 * @return 指定信道的订阅者个数
	 *
	 * @since 3.0.0
	 */
	Map<String, Long> pubsubShardNumSub();

	/**
	 * 列出指定信道的订阅者个数(不包括订阅模式的客户端订阅者)
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pubsub-shardnumsub/" target="_blank">https://redis.io/docs/latest/commands/pubsub-shardnumsub/</a></p>
	 *
	 * @param shardChannels
	 * 		一个或多个频道
	 *
	 * @return 指定信道的订阅者个数
	 */
	Map<String, Long> pubsubShardNumSub(final String... shardChannels);

	/**
	 * 列出指定信道的订阅者个数(不包括订阅模式的客户端订阅者)
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/pubsub-shardnumsub/" target="_blank">https://redis.io/docs/latest/commands/pubsub-shardnumsub/</a></p>
	 *
	 * @param shardChannels
	 * 		一个或多个频道
	 *
	 * @return 指定信道的订阅者个数
	 */
	Map<byte[], Long> pubsubShardNumSub(final byte[]... shardChannels);

	/**
	 * 指示客户端退订使用 PSUBSCRIBE pattern [pattern …] 命令订阅的所有模式消息
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/pubsub/punsubscribe.html" target="_blank">http://redisdoc.com/pubsub/punsubscribe.html</a></p>
	 *
	 * @return 在不同的客户端中有不同的表现
	 */
	Object pUnSubscribe();

	/**
	 * 指示客户端退订所有给定一个或多个模式的消息
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/pubsub/punsubscribe.html" target="_blank">http://redisdoc.com/pubsub/punsubscribe.html</a></p>
	 *
	 * @param patterns
	 * 		一个或多个模式
	 *
	 * @return 在不同的客户端中有不同的表现
	 */
	Object pUnSubscribe(final String... patterns);

	/**
	 * 指示客户端退订所有给定一个或多个模式的消息
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/pubsub/punsubscribe.html" target="_blank">http://redisdoc.com/pubsub/punsubscribe.html</a></p>
	 *
	 * @param patterns
	 * 		一个或多个模式
	 *
	 * @return 在不同的客户端中有不同的表现
	 */
	Object pUnSubscribe(final byte[]... patterns);

	/**
	 * 订阅给定的一个或多个频道的信息
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/pubsub/subscribe.html" target="_blank">http://redisdoc.com/pubsub/subscribe.html</a></p>
	 *
	 * @param channels
	 * 		一个或多个频道
	 * @param pubSubListener
	 * 		订阅监听者
	 */
	void subscribe(final String[] channels, final PubSubListener<String> pubSubListener);

	/**
	 * 订阅给定的一个或多个频道的信息
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/pubsub/subscribe.html" target="_blank">http://redisdoc.com/pubsub/subscribe.html</a></p>
	 *
	 * @param channels
	 * 		一个或多个频道
	 * @param pubSubListener
	 * 		订阅监听者
	 */
	void subscribe(final byte[][] channels, final PubSubListener<byte[]> pubSubListener);

	/**
	 * 指示客户端退订所有频道
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/pubsub/unsubscribe.html" target="_blank">http://redisdoc.com/pubsub/unsubscribe.html</a></p>
	 *
	 * @return 在不同的客户端中有不同的表现
	 */
	Object unSubscribe();

	/**
	 * 指示客户端退订给定的一个或多个频道
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/pubsub/unsubscribe.html" target="_blank">http://redisdoc.com/pubsub/unsubscribe.html</a></p>
	 *
	 * @param channels
	 * 		一个或多个频道
	 *
	 * @return 在不同的客户端中有不同的表现
	 */
	Object unSubscribe(final String... channels);

	/**
	 * 指示客户端退订给定的一个或多个频道
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/pubsub/unsubscribe.html" target="_blank">http://redisdoc.com/pubsub/unsubscribe.html</a></p>
	 *
	 * @param channels
	 * 		一个或多个频道
	 *
	 * @return 在不同的客户端中有不同的表现
	 */
	Object unSubscribe(final byte[]... channels);

}
