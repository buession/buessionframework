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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.operations;

import com.buession.redis.core.PubSubListener;
import com.buession.redis.core.command.PubSubCommands;

/**
 * 发布与订阅命运算
 *
 * <p>详情说明 <a href="http://redisdoc.com/pubsub/index.html" target="_blank">http://redisdoc.com/pubsub/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface PubSubOperations extends PubSubCommands, RedisOperations {

    /**
     * 订阅给定的频道的信息
     *
     * @param channel
     *         频道
     * @param pubSubListener
     *         订阅监听者
     */
    void subscribe(final String channel, final PubSubListener<String> pubSubListener);

    /**
     * 订阅给定的频道的信息
     *
     * @param channel
     *         频道
     * @param pubSubListener
     *         订阅监听者
     */
    void subscribe(final byte[] channel, final PubSubListener<byte[]> pubSubListener);

    /**
     * 订阅给定模式的频道的信息
     *
     * @param pattern
     *         模式
     * @param pubSubListener
     *         订阅监听者
     */
    void pSubscribe(final String pattern, final PubSubListener<String> pubSubListener);

    /**
     * 订阅给定模式的频道的信息
     *
     * @param pattern
     *         模式
     * @param pubSubListener
     *         订阅监听者
     */
    void pSubscribe(final byte[] pattern, final PubSubListener<byte[]> pubSubListener);

    /**
     * 指示客户端退订给定的频道
     *
     * @param channel
     *         频道
     *
     * @return 在不同的客户端中有不同的表现
     */
    Object unSubscribe(final String channel);

    /**
     * 指示客户端退订给定的频道
     *
     * @param channel
     *         频道
     *
     * @return 在不同的客户端中有不同的表现
     */
    Object unSubscribe(final byte[] channel);

    /**
     * 指示客户端退订所有给定模式的消息
     *
     * @param pattern
     *         模式
     *
     * @return 在不同的客户端中有不同的表现
     */
    Object pUnSubscribe(final String pattern);

    /**
     * 指示客户端退订所有给定模式的消息
     *
     * @param pattern
     *         模式
     *
     * @return 在不同的客户端中有不同的表现
     */
    Object pUnSubscribe(final byte[] pattern);

}
