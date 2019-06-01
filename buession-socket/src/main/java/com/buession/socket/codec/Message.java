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

import java.io.Serializable;

/**
 * 
 *
 * @author Yong.Teng <webmaster@buession.com>
 */
public interface Message<T> extends Serializable {

	/**
	 * 获取 ChannelHandlerContext
	 * 
	 * @return ChannelHandlerContext
	 */
	public ChannelHandlerContext getContext();

	/**
	 * 设置 ChannelHandlerContext
	 * 
	 * @param context
	 *            ChannelHandlerContext
	 */
	public void setContext(ChannelHandlerContext context);

	/**
	 * 获取数据
	 * 
	 * @return 数据
	 */
	public T getData();

	/**
	 * 设置数据
	 * 
	 * @param data
	 *            数据
	 */
	public void setData(T data);

	/**
	 * 检测消息内容是否为空
	 * 
	 * @return 消息内容是否为空
	 */
	public boolean isEmpty();

}