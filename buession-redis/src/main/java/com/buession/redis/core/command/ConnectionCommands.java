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
package com.buession.redis.core.command;

import com.buession.lang.Status;
import com.buession.redis.core.Client;
import com.buession.redis.core.ClientInfoOption;
import com.buession.redis.core.ClientReply;
import com.buession.redis.core.ClientType;
import com.buession.redis.core.ClientUnblockType;
import com.buession.redis.core.Hello;
import com.buession.redis.core.TrackingInfo;
import com.buession.redis.utils.ArgStringBuilder;

import java.util.List;
import java.util.Objects;

/**
 * 连接相关命令
 *
 * <p>详情说明 <a href="http://www.redis.cn/commands.html#connection" target="_blank">http://www.redis.cn/commands.html#connection</a></p>
 *
 * @author Yong.Teng
 */
public interface ConnectionCommands extends RedisCommands {

	/**
	 * 通过密码进行认证
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/auth.html" target="_blank">http://redisdoc.com/client_and_server/auth.html</a></p>
	 *
	 * @param user
	 * 		用户账号
	 * @param password
	 * 		密码
	 *
	 * @return 密码匹配时返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status auth(final String user, final String password);

	/**
	 * 通过密码进行认证
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/auth.html" target="_blank">http://redisdoc.com/client_and_server/auth.html</a></p>
	 *
	 * @param user
	 * 		用户账号
	 * @param password
	 * 		密码
	 *
	 * @return 密码匹配时返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status auth(final byte[] user, final byte[] password);

	/**
	 * 通过密码进行认证
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/client_and_server/auth.html" target="_blank">http://redisdoc.com/client_and_server/auth.html</a></p>
	 *
	 * @param password
	 * 		密码
	 *
	 * @return 密码匹配时返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status auth(final String password);

	/**
	 * 通过密码进行认证
	 *
	 * <p>详情说明
	 * <a href="http://redisdoc.com/client_and_server/auth.html" target="_blank">http://redisdoc.com/client_and_server/auth.html</a></p>
	 *
	 * @param password
	 * 		密码
	 *
	 * @return 密码匹配时返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status auth(final byte[] password);

	/**
	 * This command controls the tracking of the keys in the next command executed by the connection,
	 * when tracking is enabled in OPTIN or OPTOUT mode
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/client-caching/" target="_blank">https://redis.io/commands/client-caching/</a></p>
	 *
	 * @param isYes
	 * 		Yes / No
	 *
	 * @return Status.SUCCESS or Status.FAILURE if the argument is not yes or no
	 */
	Status clientCaching(final boolean isYes);

	/**
	 * 获取连接时设置的名字
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_getname.html" target="_blank">http://redisdoc.com/client_and_server/client_getname.html</a></p>
	 *
	 * @return 连接时设置的名字
	 */
	String clientGetName();

	/**
	 * This command returns the client ID we are redirecting our tracking notifications to
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/client-getredir/" target="_blank">https://redis.io/commands/client-getredir/</a></p>
	 *
	 * @return the ID of the client we are redirecting the notifications to;
	 * The command returns -1 if client tracking is not enabled,
	 * or 0 if client tracking is enabled but we are not redirecting the notifications to any client
	 */
	Integer clientGetRedir();

	/**
	 * 返回当前连接的 ID
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/client-id.html" target="_blank">http://www.redis.cn/commands/client-id.html</a></p>
	 *
	 * @return 当前连接的 ID
	 */
	Long clientId();

	/**
	 * The command returns information and statistics about the current client connection in a mostly human readable format
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/client-info/" target="_blank">https://redis.io/commands/client-info/</a></p>
	 *
	 * @return 连接到服务器的客户端信息
	 */
	Client clientInfo();

	/**
	 * 关闭地址为 host:port 的客户端
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_kill.html" target="_blank">http://redisdoc.com/client_and_server/client_kill.html</a></p>
	 *
	 * @param host
	 * 		客户端地址
	 * @param port
	 * 		客户端端口
	 *
	 * @return 当指定的客户端存在，且被成功关闭时，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status clientKill(final String host, final int port);

	/**
	 * 关闭地址为 host:port 的客户端
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_kill.html" target="_blank">http://redisdoc.com/client_and_server/client_kill.html</a></p>
	 *
	 * @param host
	 * 		客户端地址
	 * @param port
	 * 		客户端端口
	 *
	 * @return 当指定的客户端存在，且被成功关闭时，返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status clientKill(final byte[] host, final int port);

	/**
	 * 获取所有连接到服务器的客户端信息和统计数据
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_list.html" target="_blank">http://redisdoc.com/client_and_server/client_list.html</a></p>
	 *
	 * @return 所有连接到服务器的客户端信息和统计数据
	 */
	List<Client> clientList();

	/**
	 * 获取所有连接到服务器的客户端信息和统计数据
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_list.html" target="_blank">http://redisdoc.com/client_and_server/client_list.html</a></p>
	 *
	 * @param clientType
	 * 		客户端类型
	 *
	 * @return 所有连接到服务器的客户端信息和统计数据
	 */
	List<Client> clientList(final ClientType clientType);

	/**
	 * 获取所有连接到服务器的客户端信息和统计数据
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_list.html" target="_blank">http://redisdoc.com/client_and_server/client_list.html</a></p>
	 *
	 * @param clientIds
	 * 		客户端 ID
	 *
	 * @return 所有连接到服务器的客户端信息和统计数据
	 */
	List<Client> clientList(final long... clientIds);

	/**
	 * 临时禁止当前客户端连接所使用的内存被驱逐
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/client-no-evict/" target="_blank">https://redis.io/docs/latest/commands/client-no-evict/</a></p>
	 *
	 * @param on
	 * 		true 为启用“不驱逐”模式；false 为禁用
	 *
	 * @return 操作结果
	 */
	Status clientNoEvict(final boolean on);

	/**
	 * 临时禁止当前客户端连接在执行读操作时更新键的 LRU/LFU 访问时间戳
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/client-no-touch/" target="_blank">https://redis.io/docs/latest/commands/client-no-touch/</a></p>
	 *
	 * @param on
	 * 		true 为启用“不触碰”模式；false 为禁用
	 *
	 * @return 操作结果
	 */
	Status clientNoTouch(final boolean on);

	/**
	 * 将所有客户端的访问暂停给定的毫秒数
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/client-pause.html" target="_blank">http://www.redis.cn/commands/client-pause.html</a></p>
	 *
	 * @param timeout
	 * 		暂停时间（单位：毫秒）
	 *
	 * @return 操作结果
	 */
	Status clientPause(final int timeout);

	/**
	 * 当需要完全禁用redis服务器对当前客户端的回复时可使用该命令
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/client-reply.html" target="_blank">http://www.redis.cn/commands/client-reply.html</a></p>
	 *
	 * @param option
	 * 		选项
	 *
	 * @return 当执行命令设置为 OFF 或 SKIP，设置命令收不到任何回复，当设置为 ON 时，返回OK
	 */
	Status clientReply(final ClientReply option);

	/**
	 * 设置当前客户端连接的元信息
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/client-setinfo/" target="_blank">https://redis.io/docs/latest/commands/client-setinfo/</a></p>
	 *
	 * @param option
	 * 		集群信息选项
	 * @param value
	 * 		值
	 *
	 * @return 设置成功时返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status clientSetInfo(final ClientInfoOption option, final String value);

	/**
	 * 为当前连接分配一个名字
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_setname.html" target="_blank">http://redisdoc.com/client_and_server/client_setname.html</a></p>
	 *
	 * @param name
	 * 		名字
	 *
	 * @return 设置成功时返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status clientSetName(final String name);

	/**
	 * 为当前连接分配一个名字
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/client_and_server/client_setname.html" target="_blank">http://redisdoc.com/client_and_server/client_setname.html</a></p>
	 *
	 * @param name
	 * 		名字
	 *
	 * @return 设置成功时返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status clientSetName(final byte[] name);

	/**
	 * When tracking is enabled Redis remembers the keys that the connection requested,
	 * in order to send later invalidation messages when such keys are modified.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/client-tracking/" target="_blank">https://redis.io/docs/latest/commands/client-tracking/</a></p>
	 *
	 * @param on
	 * 		是否启用
	 * @param trackingArgument
	 * 		CLIENT TRACKING 参数
	 *
	 * @return 设置成功时返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status clientTracking(final boolean on, final TrackingArgument trackingArgument);

	/**
	 * Returns information about the current client connection's use of the server assisted client side caching feature.
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/client-trackinginfo/" target="_blank">https://redis.io/docs/latest/commands/client-trackinginfo/</a></p>
	 *
	 * @return Information about the current client connection's use of the server assisted client side caching feature
	 */
	TrackingInfo clientTrackingInfo();

	/**
	 * 该命令可以通过其他连接解除客户端的阻塞
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/client-unblock.html" target="_blank">http://www.redis.cn/commands/client-unblock.html</a></p>
	 *
	 * @param clientId
	 * 		客户端 ID
	 *
	 * @return 当执行命令设置为 OFF 或 SKIP，设置命令收不到任何回复，当设置为 ON 时，返回OK
	 */
	Status clientUnblock(final int clientId);

	/**
	 * 该命令可以通过其他连接解除客户端的阻塞
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/client-unblock.html" target="_blank">http://www.redis.cn/commands/client-unblock.html</a></p>
	 *
	 * @param clientId
	 * 		客户端 ID
	 * @param type
	 * 		type
	 *
	 * @return 当执行命令设置为 OFF 或 SKIP，设置命令收不到任何回复，当设置为 ON 时，返回OK
	 */
	Status clientUnblock(final int clientId, final ClientUnblockType type);

	/**
	 * 用于恢复被暂停的客户端命令处理
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/client-unpause/" target="_blank">https://redis.io/docs/latest/commands/client-unpause/</a></p>
	 *
	 * @return 当执行命令设置为 OFF 或 SKIP，设置命令收不到任何回复，当设置为 ON 时，返回OK
	 */
	Status clientUnpause();

	/**
	 * 打印一个特定的字符串
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/echo.html" target="_blank">http://www.redis.cn/commands/echo.html</a></p>
	 *
	 * @param str
	 * 		待打印的字符串
	 *
	 * @return 字符串本身
	 */
	String echo(final String str);

	/**
	 * 打印一个特定的字符串
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/echo.html" target="_blank">http://www.redis.cn/commands/echo.html</a></p>
	 *
	 * @param str
	 * 		待打印的字符串
	 *
	 * @return 字符串本身
	 */
	byte[] echo(final byte[] str);

	/**
	 * 切换通信协议版本、获取服务器基本信息（类似 INFO 的轻量版）、认证
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hello/" target="_blank">https://redis.io/docs/latest/commands/hello/</a></p>
	 *
	 * @return 相关操作结果
	 */
	Hello hello();

	/**
	 * 切换通信协议版本、获取服务器基本信息（类似 INFO 的轻量版）、认证
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hello/" target="_blank">https://redis.io/docs/latest/commands/hello/</a></p>
	 *
	 * @param protover
	 * 		协议版本
	 *
	 * @return 相关操作结果
	 */
	Hello hello(final int protover);

	/**
	 * 切换通信协议版本、获取服务器基本信息（类似 INFO 的轻量版）、认证
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hello/" target="_blank">https://redis.io/docs/latest/commands/hello/</a></p>
	 *
	 * @param protover
	 * 		协议版本
	 * @param password
	 * 		密码
	 *
	 * @return 相关操作结果
	 */
	Hello hello(final int protover, final String password);

	/**
	 * 切换通信协议版本、获取服务器基本信息（类似 INFO 的轻量版）、认证
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hello/" target="_blank">https://redis.io/docs/latest/commands/hello/</a></p>
	 *
	 * @param protover
	 * 		协议版本
	 * @param password
	 * 		密码
	 *
	 * @return 相关操作结果
	 */
	Hello hello(final int protover, final byte[] password);

	/**
	 * 切换通信协议版本、获取服务器基本信息（类似 INFO 的轻量版）、认证
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hello/" target="_blank">https://redis.io/docs/latest/commands/hello/</a></p>
	 *
	 * @param protover
	 * 		协议版本
	 * @param username
	 * 		用户名
	 * @param password
	 * 		密码
	 *
	 * @return 相关操作结果
	 */
	Hello hello(final int protover, final String username, final String password);

	/**
	 * 切换通信协议版本、获取服务器基本信息（类似 INFO 的轻量版）、认证
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hello/" target="_blank">https://redis.io/docs/latest/commands/hello/</a></p>
	 *
	 * @param protover
	 * 		协议版本
	 * @param username
	 * 		用户名
	 * @param password
	 * 		密码
	 *
	 * @return 相关操作结果
	 */
	Hello hello(final int protover, final byte[] username, final byte[] password);

	/**
	 * 切换通信协议版本、获取服务器基本信息（类似 INFO 的轻量版）、认证
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hello/" target="_blank">https://redis.io/docs/latest/commands/hello/</a></p>
	 *
	 * @param protover
	 * 		协议版本
	 * @param username
	 * 		用户名
	 * @param password
	 * 		密码
	 * @param clientName
	 * 		客户端名称
	 *
	 * @return 相关操作结果
	 */
	Hello hello(final int protover, final String username, final String password, final String clientName);

	/**
	 * 切换通信协议版本、获取服务器基本信息（类似 INFO 的轻量版）、认证
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/hello/" target="_blank">https://redis.io/docs/latest/commands/hello/</a></p>
	 *
	 * @param protover
	 * 		协议版本
	 * @param username
	 * 		用户名
	 * @param password
	 * 		密码
	 * @param clientName
	 * 		客户端名称
	 *
	 * @return 相关操作结果
	 */
	Hello hello(final int protover, final byte[] username, final byte[] password, final byte[] clientName);

	/**
	 * 使用客户端向 Redis 服务器发送一个 PING ，如果服务器运作正常的话，会返回一个 PONG；
	 * 通常用于测试与服务器的连接是否仍然生效，或者用于测量延迟值
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/ping.html" target="_blank">http://www.redis.cn/commands/ping.html</a></p>
	 *
	 * @return PING 结果
	 */
	Status ping();

	/**
	 * 请求服务器关闭与当前客户端的连接
	 *
	 * <p>详情说明 <a href="http://www.redis.cn/commands/quit.html" target="_blank">http://www.redis.cn/commands/quit.html</a></p>
	 *
	 * @return 总是返回 Status.SUCCESS
	 */
	Status quit();

	/**
	 * This command performs a full reset of the connection’s server-side context,
	 * mimicking the effect of disconnecting and reconnecting again.
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/reset/" target="_blank">https://redis.io/commands/reset/</a></p>
	 *
	 * @return 总是返回 Status.SUCCESS
	 */
	Status reset();

	/**
	 * 切换到指定的数据库
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/database/select.html" target="_blank">http://redisdoc.com/database/select.html</a></p>
	 *
	 * @param db
	 * 		数据库索引号
	 *
	 * @return 切换成功返回 Status.SUCCESS；否则，返回 Status.FAILURE
	 */
	Status select(final int db);

	/**
	 * CLIENT TRACKING 参数
	 */
	final class TrackingArgument {

		/**
		 * 将失效消息转发给另一个客户端
		 */
		private Long redirect;

		/**
		 * 仅跟踪以该前缀开头的 key
		 */
		private String[] prefixes;

		/**
		 * 是否为广播模式
		 */
		private Boolean bcast;

		/**
		 * 是否为选择性跟踪
		 */
		private Boolean optin;

		/**
		 * 是否排除跟踪
		 */
		private Boolean optout;

		/**
		 * 是否不向自己发送失效通知
		 */
		private Boolean noloop;

		/**
		 * 构造函数
		 */
		public TrackingArgument() {
		}

		/**
		 * 构造函数
		 *
		 * @param redirect
		 * 		将失效消息转发给另一个客户端
		 */
		public TrackingArgument(final Long redirect) {
			this.redirect = redirect;
		}

		/**
		 * 构造函数
		 *
		 * @param prefixes
		 * 		仅跟踪以该前缀开头的 key
		 */
		public TrackingArgument(final String[] prefixes) {
			this.prefixes = prefixes;
		}

		/**
		 * 构造函数
		 *
		 * @param redirect
		 * 		将失效消息转发给另一个客户端
		 * @param prefixes
		 * 		仅跟踪以该前缀开头的 key
		 */
		public TrackingArgument(final Long redirect, final String[] prefixes) {
			this(redirect);
			this.prefixes = prefixes;
		}

		/**
		 * 构造函数
		 *
		 * @param redirect
		 * 		将失效消息转发给另一个客户端
		 * @param bcast
		 * 		是否为广播模式
		 * @param optin
		 * 		是否为选择性跟踪
		 * @param optout
		 * 		是否排除跟踪
		 * @param noloop
		 * 		是否不向自己发送失效通知
		 */
		public TrackingArgument(final Long redirect, final boolean bcast, final boolean optin, final boolean optout,
								final boolean noloop) {
			this(redirect);
			this.bcast = bcast;
			this.optin = optin;
			this.optout = optout;
			this.noloop = noloop;
		}

		/**
		 * 构造函数
		 *
		 * @param prefixes
		 * 		仅跟踪以该前缀开头的 key
		 * @param bcast
		 * 		是否为广播模式
		 * @param optin
		 * 		是否为选择性跟踪
		 * @param optout
		 * 		是否排除跟踪
		 * @param noloop
		 * 		是否不向自己发送失效通知
		 */
		public TrackingArgument(final String[] prefixes, final boolean bcast, final boolean optin, final boolean optout,
								final boolean noloop) {
			this(prefixes);
			this.bcast = bcast;
			this.optin = optin;
			this.optout = optout;
			this.noloop = noloop;
		}

		/**
		 * 构造函数
		 *
		 * @param redirect
		 * 		将失效消息转发给另一个客户端
		 * @param prefixes
		 * 		仅跟踪以该前缀开头的 key
		 * @param bcast
		 * 		是否为广播模式
		 * @param optin
		 * 		是否为选择性跟踪
		 * @param optout
		 * 		是否排除跟踪
		 * @param noloop
		 * 		是否不向自己发送失效通知
		 */
		public TrackingArgument(final Long redirect, final String[] prefixes, final boolean bcast, final boolean optin,
								final boolean optout, final boolean noloop) {
			this(redirect, bcast, optin, optout, noloop);
			this.prefixes = prefixes;
		}

		/**
		 * 返回将失效消息转发给另一个客户端
		 *
		 * @return 将失效消息转发给另一个客户端
		 */
		public Long getRedirect() {
			return redirect;
		}

		/**
		 * 返回仅跟踪以该前缀开头的 key
		 *
		 * @return 仅跟踪以该前缀开头的 key
		 */
		public String[] getPrefixes() {
			return prefixes;
		}

		/**
		 * 返回是否为广播模式
		 *
		 * @return 是否为广播模式
		 */
		public Boolean getBcast() {
			return bcast;
		}

		/**
		 * 返回是否为选择性跟踪
		 *
		 * @return 是否为选择性跟踪
		 */
		public Boolean getOptin() {
			return optin;
		}

		/**
		 * 返回是否排除跟踪
		 *
		 * @return 是否排除跟踪
		 */
		public Boolean getOptout() {
			return optout;
		}

		/**
		 * 返回是否不向自己发送失效通知
		 *
		 * @return 是否不向自己发送失效通知
		 */
		public Boolean getNoloop() {
			return noloop;
		}

		@Override
		public String toString() {
			final ArgStringBuilder builder = ArgStringBuilder.create().add("REDIRECT", redirect);

			if(prefixes != null){
				for(String prefix : prefixes){
					builder.add("PREFIX", prefix);
				}
			}

			if(Objects.equals(bcast, true)){
				builder.append("BCAST");
			}
			if(Objects.equals(optin, true)){
				builder.append("OPTIN");
			}
			if(Objects.equals(optout, true)){
				builder.append("OPTOUT");
			}
			if(Objects.equals(noloop, true)){
				builder.append("NOLOOP");
			}

			return builder.build();
		}


		public static class Builder extends BaseArgumentBuilder<TrackingArgument> {

			private Builder() {
				super(new TrackingArgument());
			}

			/**
			 * 创建 {@link TrackingArgument} 构建器 {@link Builder}
			 *
			 * @return {@link TrackingArgument} 构建器 {@link Builder}
			 */
			public static Builder create() {
				return new Builder();
			}

			/**
			 * 设置将失效消息转发给另一个客户端
			 *
			 * @param redirect
			 * 		失效消息转发给另一个客户端
			 *
			 * @return {@link TrackingArgument} 构建器 {@link Builder}
			 */
			public Builder redirect(final Long redirect) {
				arguament.redirect = redirect;
				return this;
			}

			/**
			 * 设置仅跟踪以该前缀开头的 key
			 *
			 * @param prefixes
			 * 		仅跟踪以该前缀开头的 key
			 *
			 * @return {@link TrackingArgument} 构建器 {@link Builder}
			 */
			public Builder prefixes(String[] prefixes) {
				arguament.prefixes = prefixes;
				return this;
			}

			/**
			 * 设置为广播模式
			 *
			 * @return {@link TrackingArgument} 构建器 {@link Builder}
			 */
			public Builder bcast() {
				arguament.bcast = true;
				return this;
			}

			/**
			 * 设置为选择性跟踪
			 *
			 * @return {@link TrackingArgument} 构建器 {@link Builder}
			 */
			public Builder optin() {
				arguament.optin = true;
				return this;
			}

			/**
			 * 设置为排除跟踪
			 *
			 * @return {@link TrackingArgument} 构建器 {@link Builder}
			 */
			public Builder optout() {
				arguament.optout = true;
				return this;
			}

			/**
			 * 设置为不向自己发送失效通知
			 *
			 * @return {@link TrackingArgument} 构建器 {@link Builder}
			 */
			public Builder noloop() {
				arguament.noloop = true;
				return this;
			}

		}

	}

}
