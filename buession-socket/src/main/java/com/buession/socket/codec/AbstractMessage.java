/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 
 * (the "License"); you may not use this file except in compliance with the License. You may obtain 
 * a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * 
 * =================================================================================================
 * 
 * This software consists of voluntary contributions made by many individuals on behalf of the
 * Apache Software Foundation. For more information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 * 
 * +------------------------------------------------------------------------------------------------+
 * | License: http://buession.buession.com.cn/LICENSE 												|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2015 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.socket.codec;

import io.netty.channel.ChannelHandlerContext;

/**
 * 
 *
 * @author Yong.Teng <webmaster@buession.com>
 */
public abstract class AbstractMessage<T> implements Message<T> {

	private static final long serialVersionUID = 7818084196668407421L;

	private ChannelHandlerContext context;

	/**
	 * 获取 ChannelHandlerContext
	 * 
	 * @return ChannelHandlerContext
	 */
	@Override
	public ChannelHandlerContext getContext() {
		return context;
	}

	/**
	 * 设置 ChannelHandlerContext
	 * 
	 * @param context
	 *            ChannelHandlerContext
	 */
	@Override
	public void setContext(ChannelHandlerContext context) {
		this.context = context;
	}

}