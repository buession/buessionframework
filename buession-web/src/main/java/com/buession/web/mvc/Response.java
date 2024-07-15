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
 * | Copyright @ 2013-2024 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.web.mvc;

import java.util.StringJoiner;

/**
 * Response 数据输出类
 *
 * @param <E>
 * 		输出数据
 *
 * @author Yong.Teng
 */
public class Response<E> {

	/**
	 * 状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 */
	private boolean state;

	/**
	 * 错误状态码
	 */
	private int code;

	/**
	 * 提示或错误消息
	 */
	private String message;

	/**
	 * 数据
	 */
	private E data;

	/**
	 * 分页对象
	 */
	private Pagination pagination;

	/**
	 * 构造函数
	 */
	public Response() {
	}

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 */
	public Response(boolean state) {
		setState(state);
	}

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 * @param code
	 * 		错误码
	 */
	public Response(boolean state, int code) {
		setState(state);
		setCode(code);
	}

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 * @param code
	 * 		错误码
	 * @param message
	 * 		提示或错误消息
	 */
	public Response(boolean state, int code, String message) {
		this(state, code);
		setMessage(message);
	}

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 * @param message
	 * 		提示或错误消息
	 */
	public Response(boolean state, String message) {
		setState(state);
		this.message = message;
	}

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 * @param code
	 * 		错误码
	 * @param message
	 * 		提示或错误消息
	 * @param data
	 * 		数据
	 */
	public Response(boolean state, int code, String message, E data) {
		this(state, code, message);
		setData(data);
	}

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 * @param message
	 * 		提示或错误消息
	 * @param data
	 * 		数据
	 */
	public Response(boolean state, String message, E data) {
		this(state, message);
		setData(data);
	}

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 * @param code
	 * 		错误码
	 * @param message
	 * 		提示或错误消息
	 * @param data
	 * 		数据
	 * @param pagination
	 * 		分页对象
	 *
	 * @since 2.3.0
	 */
	public Response(boolean state, int code, String message, E data, Pagination pagination) {
		this(state, code, message, data);
		setPagination(pagination);
	}

	/**
	 * 构造函数
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 * @param message
	 * 		提示或错误消息
	 * @param data
	 * 		数据
	 * @param pagination
	 * 		分页对象
	 *
	 * @since 2.3.0
	 */
	public Response(boolean state, String message, E data, Pagination pagination) {
		this(state, message, data);
		setPagination(pagination);
	}

	/**
	 * 返回状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 *
	 * @return 状态
	 */
	public boolean isState() {
		return getState();
	}

	/**
	 * 返回状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 *
	 * @return 状态
	 */
	public boolean getState() {
		return state;
	}

	/**
	 * 设置状态
	 *
	 * @param state
	 * 		状态，true 为逻辑成功；false 为逻辑失败或出现异常
	 */
	public void setState(boolean state) {
		this.state = state;
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
	 * 返回提示或错误消息
	 *
	 * @return 提示或错误消息
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 设置提示或错误消息
	 *
	 * @param message
	 * 		提示或错误消息
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 返回数据
	 *
	 * @return 数据
	 */
	public E getData() {
		return data;
	}

	/**
	 * 设置数据
	 *
	 * @param data
	 * 		数据
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * 返回分页对象
	 *
	 * @return 分页对象
	 */
	public Pagination getPagination() {
		return this.pagination;
	}

	/**
	 * 设置分页对象
	 *
	 * @param pagination
	 * 		分页对象
	 *
	 * @since 2.3.0
	 */
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", "[", "]")
				.add("state=" + state)
				.add("code=" + code)
				.add("message='" + message + "'")
				.add("data=" + data)
				.add("pagination=" + pagination)
				.toString();
	}

}
