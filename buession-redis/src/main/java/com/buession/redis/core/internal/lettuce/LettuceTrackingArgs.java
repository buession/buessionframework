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
package com.buession.redis.core.internal.lettuce;

import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.redis.core.command.ConnectionCommands;
import io.lettuce.core.TrackingArgs;

import java.util.Objects;

/**
 * Lettuce {@link TrackingArgs} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class LettuceTrackingArgs extends TrackingArgs {

	/**
	 * 构造函数
	 */
	public LettuceTrackingArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param redirect
	 * 		将失效消息转发给另一个客户端
	 */
	public LettuceTrackingArgs(final Long redirect) {
		super();
		redirect(redirect);
	}

	/**
	 * 构造函数
	 *
	 * @param prefixes
	 * 		仅跟踪以该前缀开头的 key
	 */
	public LettuceTrackingArgs(final String[] prefixes) {
		super();
		prefixes(prefixes);
	}

	/**
	 * 构造函数
	 *
	 * @param redirect
	 * 		将失效消息转发给另一个客户端
	 * @param prefixes
	 * 		仅跟踪以该前缀开头的 key
	 */
	public LettuceTrackingArgs(final Long redirect, final String[] prefixes) {
		this(redirect);
		prefixes(prefixes);
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
	public LettuceTrackingArgs(final Long redirect, final boolean bcast, final boolean optin, final boolean optout,
							   final boolean noloop) {
		this(redirect);
		if(bcast){
			bcast();
		}
		if(optin){
			optin();
		}
		if(optout){
			optout();
		}
		if(noloop){
			noloop();
		}
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
	public LettuceTrackingArgs(final String[] prefixes, final boolean bcast, final boolean optin, final boolean optout,
							   final boolean noloop) {
		this(prefixes);
		if(bcast){
			bcast();
		}
		if(optin){
			optin();
		}
		if(optout){
			optout();
		}
		if(noloop){
			noloop();
		}
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
	public LettuceTrackingArgs(final Long redirect, final String[] prefixes, final boolean bcast, final boolean optin,
							   final boolean optout, final boolean noloop) {
		this(redirect, bcast, optin, optout, noloop);
		prefixes(prefixes);
	}

	public static LettuceTrackingArgs from(final ConnectionCommands.TrackingArgument trackingArgument) {
		if(trackingArgument == null){
			return null;
		}

		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		final LettuceTrackingArgs trackingArgs = new LettuceTrackingArgs();

		propertyMapper.from(trackingArgument.getRedirect()).to(trackingArgs::redirect);
		propertyMapper.from(trackingArgument.getPrefixes()).to(trackingArgs::prefixes);
		if(Objects.equals(trackingArgument.getBcast(), true)){
			trackingArgs.bcast();
		}
		if(Objects.equals(trackingArgument.getOptin(), true)){
			trackingArgs.optin();
		}
		if(Objects.equals(trackingArgument.getOptout(), true)){
			trackingArgs.optout();
		}
		if(Objects.equals(trackingArgument.getNoloop(), true)){
			trackingArgs.noloop();
		}

		return trackingArgs;
	}

}
