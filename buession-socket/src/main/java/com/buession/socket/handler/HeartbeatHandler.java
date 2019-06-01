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
package com.buession.socket.handler;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 *
 * @author Yong.Teng <webmaster@buession.com>
 */
public class HeartbeatHandler extends ChannelDuplexHandler {

	private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSS");

	private final static Logger logger = LoggerFactory.getLogger(HeartbeatHandler.class);

	@Override
	public void userEventTriggered(ChannelHandlerContext context, Object event) throws Exception {
		if (event instanceof IdleStateEvent) {
			IdleStateEvent e = (IdleStateEvent) event;
			IdleState state = e.state();

			String message = simpleDateFormat.format(new Date());

			if (IdleState.READER_IDLE.equals(state) == true) {
				logger.info("空闲超过心跳事件，断开连接");
				logger.debug("Send the message of heartbeat detection(READER_IDLE): {}", message);
				// context.writeAndFlush(message);
				context.pipeline().close();
			} else if (IdleState.WRITER_IDLE.equals(state) == true) {
				logger.debug("Send the message of heartbeat detection(WRITER_IDLE): {}", message);
				context.writeAndFlush(message + "\r\n");
			} else if (IdleState.ALL_IDLE.equals(state) == true) {	// 发送心跳
				logger.debug("IDLE");
				context.write("ping\r\n");
			}

			logger.debug("event instanceof " + state.getClass().getName() + ": " + state.toString());
		}

		logger.debug("event instanceof "
				+ (event == null ? Object.class.getName() : event.getClass().getName()));

		super.userEventTriggered(context, event);
	}

}