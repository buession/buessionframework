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
package com.buession.redis.core.command.args.connection;

import com.buession.redis.utils.ArgStringBuilder;
import com.buession.redis.utils.SafeEncoder;

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
	 * @param prefixes
	 * 		仅跟踪以该前缀开头的 key
	 */
	public TrackingArgument(final byte[][] prefixes) {
		this(SafeEncoder.encode(prefixes));
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
	 * @param prefixes
	 * 		仅跟踪以该前缀开头的 key
	 */
	public TrackingArgument(final long redirect, final byte[][] prefixes) {
		this(redirect, SafeEncoder.encode(prefixes));
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
	public TrackingArgument(final byte[][] prefixes, final boolean bcast, final boolean optin,
	                        final boolean optout, final boolean noloop) {
		this(SafeEncoder.encode(prefixes), bcast, optin, optout, noloop);
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
	public TrackingArgument(final long redirect, final byte[][] prefixes, final boolean bcast, final boolean optin,
	                        final boolean optout, final boolean noloop) {
		this(redirect, SafeEncoder.encode(prefixes), bcast, optin, optout, noloop);
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
	public TrackingArgument setRedirect(long redirect) {
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
	 * 设置仅跟踪以该前缀开头的 key
	 *
	 * @param prefixes
	 * 		仅跟踪以该前缀开头的 key
	 *
	 * @return {@link TrackingArgument}
	 */
	public TrackingArgument setPrefixes(byte[][] prefixes) {
		return setPrefixes(SafeEncoder.encode(prefixes));
	}

	/**
	 * 返回是否为广播模式
	 *
	 * @return 是否为广播模式
	 */
	public Boolean isBcast() {
		return getBcast();
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
	public TrackingArgument bcast() {
		return setBcast(true);
	}

	/**
	 * 设置为广播模式
	 *
	 * @param bcast
	 * 		是否为广播模式
	 *
	 * @return {@link TrackingArgument}
	 */
	public TrackingArgument setBcast(boolean bcast) {
		this.bcast = bcast;
		return this;
	}

	/**
	 * 返回是否为选择性跟踪
	 *
	 * @return 是否为选择性跟踪
	 */
	public Boolean isOptin() {
		return getOptin();
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
	public TrackingArgument optin() {
		return setOptin(true);
	}

	/**
	 * 设置为选择性跟踪
	 *
	 * @param optin
	 * 		是否为选择性跟踪
	 *
	 * @return {@link TrackingArgument}
	 */
	public TrackingArgument setOptin(boolean optin) {
		this.optin = optin;
		return this;
	}

	/**
	 * 返回是否排除跟踪
	 *
	 * @return 是否排除跟踪
	 */
	public Boolean isOptout() {
		return getOptout();
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
	public TrackingArgument optout() {
		return setOptout(true);
	}

	/**
	 * 设置为排除跟踪
	 *
	 * @param optout
	 * 		是否为排除跟踪
	 *
	 * @return {@link TrackingArgument}
	 */
	public TrackingArgument setOptout(boolean optout) {
		this.optout = optout;
		return this;
	}

	/**
	 * 返回是否不向自己发送失效通知
	 *
	 * @return 是否不向自己发送失效通知
	 */
	public Boolean isNoloop() {
		return getNoloop();
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
	public TrackingArgument noloop() {
		return setNoloop(true);
	}

	/**
	 * 设置为不向自己发送失效通知
	 *
	 * @param noloop
	 * 		是否为不向自己发送失效通知
	 *
	 * @return {@link TrackingArgument}
	 */
	public TrackingArgument setNoloop(boolean noloop) {
		this.noloop = noloop;
		return this;
	}

	@Override
	public String toString() {
		final ArgStringBuilder builder = ArgStringBuilder.create().add("REDIRECT", getRedirect());

		if(getPrefixes() != null){
			for(String prefix : getPrefixes()){
				builder.add("PREFIX", prefix);
			}
		}

		if(Boolean.TRUE.equals(getBcast())){
			builder.append("BCAST");
		}
		if(Boolean.TRUE.equals(getOptin())){
			builder.append("OPTIN");
		}
		if(Boolean.TRUE.equals(getOptout())){
			builder.append("OPTOUT");
		}
		if(Boolean.TRUE.equals(getNoloop())){
			builder.append("NOLOOP");
		}

		return builder.build();
	}

}
