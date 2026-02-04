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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command.args;

import com.buession.redis.core.command.ConnectionCommands;
import com.buession.redis.utils.ArgStringBuilder;

import java.util.Objects;

/**
 * CLIENT TRACKING 参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class TrackingArgument {

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
	public TrackingArgument(final long redirect) {
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
	public TrackingArgument(final long redirect, final String[] prefixes) {
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
	public TrackingArgument(final long redirect, final boolean bcast, final boolean optin, final boolean optout,
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
	public TrackingArgument(final String[] prefixes, final boolean bcast, final boolean optin,
							final boolean optout, final boolean noloop) {
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
	public TrackingArgument(final long redirect, final String[] prefixes, final boolean bcast, final boolean optin,
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
	 * 设置将失效消息转发给另一个客户端
	 *
	 * @param redirect
	 * 		失效消息转发给另一个客户端
	 *
	 * @return {@link TrackingArgument}
	 */
	public TrackingArgument setRedirect(final long redirect) {
		this.redirect = redirect;
		return this;
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
	 * 设置仅跟踪以该前缀开头的 key
	 *
	 * @param prefixes
	 * 		仅跟踪以该前缀开头的 key
	 *
	 * @return {@link TrackingArgument}
	 */
	public TrackingArgument setPrefixes(String[] prefixes) {
		this.prefixes = prefixes;
		return this;
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
	 * 设置为广播模式
	 *
	 * @return {@link TrackingArgument}
	 */
	public TrackingArgument setBcast() {
		this.bcast = true;
		return this;
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
	 * 设置为选择性跟踪
	 *
	 * @return {@link TrackingArgument}
	 */
	public TrackingArgument setOptin() {
		this.optin = true;
		return this;
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
	 * 设置为排除跟踪
	 *
	 * @return {@link TrackingArgument}
	 */
	public TrackingArgument setOptout() {
		this.optout = true;
		return this;
	}

	/**
	 * 返回是否不向自己发送失效通知
	 *
	 * @return 是否不向自己发送失效通知
	 */
	public Boolean getNoloop() {
		return noloop;
	}

	/**
	 * 设置为不向自己发送失效通知
	 *
	 * @return {@link TrackingArgument}
	 */
	public TrackingArgument setNoloop() {
		this.noloop = true;
		return this;
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

}
