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

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.buession.socket.codec.ByteMessage;

/**
 * 
 *
 * @author Yong.Teng <webmaster@buession.com>
 */
public class ByteMessageEncoder extends MessageToByteEncoder<ByteMessage> {

	private final static Logger logger = LoggerFactory.getLogger(ByteMessageEncoder.class);

	@Override
	protected void encode(ChannelHandlerContext context, ByteMessage in, ByteBuf out)
			throws Exception {
		if (in.isEmpty() == true) {
			logger.debug("Message is empty.");
			return;
		}

		String str = in == null || in.getData() == null ? null : new String(in.getData());

		// out.add(ByteBufUtil.encodeString(context.alloc(), CharBuffer.wrap(str),
		// Charset.defaultCharset()));
		logger.debug("Encoder byte message: {}({}) width context: {}.", str,
				str == null ? 0 : str.length(), context);
	}
}