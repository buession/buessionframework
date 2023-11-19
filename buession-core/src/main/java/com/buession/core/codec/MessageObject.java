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
 * | License: http://www.apache.org/licenses/LICENSE-2.0.txt 										|
 * | Author: Yong.Teng <webmaster@buession.com> 													|
 * | Copyright @ 2013-2023 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.core.codec;

import java.io.Serializable;
import java.util.Objects;

/**
 * 消息
 *
 * @author Yong.Teng
 */
public class MessageObject implements Serializable {

	private final static long serialVersionUID = -5074627895618171892L;

	/**
	 * 错误码
	 */
	private int code;

	/**
	 * 错误文本
	 */
	private String text;

	/**
	 * 构造函数
	 */
	public MessageObject() {
	}

	/**
	 * 构造函数
	 *
	 * @param code
	 * 		错误码
	 * @param text
	 * 		错误文本
	 */
	public MessageObject(int code, String text) {
		this.code = code;
		this.text = text;
	}

	/**
	 * 返回错误码
	 *
	 * @return 错误码
	 */
	public int getCode() {
		return code;
	}

	/**
	 * 设置错误码
	 *
	 * @param code
	 * 		错误码
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * 返回错误文本
	 *
	 * @return 错误文本
	 */
	public String getText() {
		return text;
	}

	/**
	 * 设置错误文本
	 *
	 * @param text
	 * 		错误文本
	 */
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, text);
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}

		if(obj instanceof MessageObject){
			MessageObject that = (MessageObject) obj;
			return code == that.code;
		}

		return false;
	}

}
