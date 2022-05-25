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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.core;

import com.buession.core.utils.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * HTTP 头
 *
 * @author Yong.Teng
 */
public class Header {

	/**
	 * HTTP 头名称
	 */
	private String name;

	/**
	 * HTTP 头值
	 */
	private String value;

	/**
	 * 构造函数
	 */
	public Header(){
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 */
	public Header(String name, String value){
		this.name = name;
		this.value = value;
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @since 2.0.0
	 */
	public Header(String name, char value){
		this(name, Character.toString(value));
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @since 2.0.0
	 */
	public Header(String name, byte[] value){
		this(name, new String(value, StandardCharsets.UTF_8));
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @since 2.0.0
	 */
	public Header(String name, short value){
		this(name, Short.toString(value));
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @since 2.0.0
	 */
	public Header(String name, int value){
		this(name, Integer.toString(value));
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @since 2.0.0
	 */
	public Header(String name, long value){
		this(name, Long.toString(value));
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @since 2.0.0
	 */
	public Header(String name, float value){
		this(name, Float.toString(value));
	}

	/**
	 * 构造函数
	 *
	 * @param name
	 * 		HTTP 头名称
	 * @param value
	 * 		HTTP 头值
	 *
	 * @since 2.0.0
	 */
	public Header(String name, double value){
		this(name, Double.toString(value));
	}

	/**
	 * 返回 HTTP 头名称
	 *
	 * @return HTTP 头名称
	 */
	public String getName(){
		return name;
	}

	/**
	 * 设置 HTTP 头名称
	 *
	 * @param name
	 * 		HTTP 头名称
	 */
	public void setName(final String name){
		this.name = name;
	}

	/**
	 * 返回 HTTP 头值
	 *
	 * @return HTTP 头值
	 */
	public String getValue(){
		return value;
	}

	/**
	 * 设置 HTTP 头值
	 *
	 * @param value
	 * 		HTTP 头值
	 */
	public void setValue(final String value){
		this.value = value;
	}

	@Override
	public int hashCode(){
		return Objects.hash(name, value);
	}

	@Override
	public boolean equals(Object obj){
		if(this == obj){
			return true;
		}

		if(obj instanceof Header){
			Header that = (Header) obj;
			return StringUtils.equalsIgnoreCase(name, that.name) && Objects.equals(value, that.value);
		}

		return false;
	}

	@Override
	public String toString(){
		return new StringJoiner(": ")
				.add(name)
				.add(value)
				.toString();
	}

}
