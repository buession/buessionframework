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

/**
 * 
 *
 * @author Yong.Teng <webmaster@buession.com>
 */
public class ByteMessage extends AbstractMessage<byte[]> {

	private static final long serialVersionUID = 4574374392870404013L;

	private byte[] data;

	/**
	 * 获取数据
	 * 
	 * @return 数据
	 */
	@Override
	public byte[] getData() {
		return data;
	}

	/**
	 * 设置数据
	 * 
	 * @param data
	 *            数据
	 */
	@Override
	public void setData(byte[] data) {
		this.data = data;
	}

	/**
	 * 检测消息内容是否为空
	 * 
	 * @return 消息内容是否为空
	 */
	@Override
	public boolean isEmpty() {
		byte[] data = getData();
		return data == null || data.length == 0;
	}

	public String valueOf() {
		byte[] data = getData();
		return data == null ? null : new String(data);
	}

	@Override
	public String toString() {
		byte[] data = getData();
		return getClass().getName() + "@" + Integer.toHexString(hashCode()) + "->Message [data="
				+ (data == null ? "(null)" : new String(data)) + ", context=" + getContext() + "]";
	}

}