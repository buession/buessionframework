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
package com.buession.web.http;

import com.buession.core.builder.MapBuilder;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * 错误
 *
 * @author Yong.Teng
 */
public class Error implements Serializable {

	private final static long serialVersionUID = -552295330575205966L;

	/**
	 * HTTP 状态码及状态信息
	 */
	private HttpStatus httpStatus;

	/**
	 * 错误码
	 */
	private int code;

	/**
	 * 错误信息
	 */
	private String message;

	/**
	 * 构造函数
	 */
	public Error(){
	}

	/**
	 * 构造函数
	 *
	 * @param httpStatus
	 * 		HTTP 状态码及状态信息
	 * @param code
	 * 		错误码
	 */
	public Error(HttpStatus httpStatus, int code){
		this(httpStatus, code, httpStatus.getReasonPhrase());
	}

	/**
	 * 构造函数
	 *
	 * @param httpStatus
	 * 		HTTP 状态码及状态信息
	 * @param code
	 * 		错误码
	 * @param message
	 * 		错误信息
	 */
	public Error(HttpStatus httpStatus, int code, String message){
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}

	/**
	 * 返回 HTTP 状态码及状态信息
	 *
	 * @return HTTP 状态码及状态信息
	 */
	public HttpStatus getHttpStatus(){
		return httpStatus;
	}

	/**
	 * 设置 HTTP 状态码及状态信息
	 *
	 * @param httpStatus
	 * 		HTTP 状态码及状态信息
	 */
	public void setHttpStatus(HttpStatus httpStatus){
		this.httpStatus = httpStatus;
	}

	/**
	 * 返回错误码
	 *
	 * @return 错误码
	 */
	public int getCode(){
		return code;
	}

	/**
	 * 设置错误码
	 *
	 * @param code
	 * 		错误码
	 */
	public void setCode(int code){
		this.code = code;
	}

	/**
	 * 返回错误信息
	 *
	 * @return 错误信息
	 */
	public String getMessage(){
		return message;
	}

	/**
	 * 设置错误信息
	 *
	 * @param message
	 * 		错误信息
	 */
	public void setMessage(String message){
		this.message = message;
	}

	/**
	 * 将错误信息转换为 Map
	 *
	 * @return Map
	 */
	public Map<String, Object> toMap(){
		return MapBuilder.<String, Object>create().put("state", false).put("code", getCode())
				.put("message", getMessage())
				.put("status", getHttpStatus()).build();
	}

	/**
	 * 序列错误页视图文件
	 *
	 * @return 错误页视图文件集合
	 */
	public static Map<HttpStatus.Series, String> seriesViews(){
		Map<HttpStatus.Series, String> views = new EnumMap<>(HttpStatus.Series.class);

		views.put(HttpStatus.Series.CLIENT_ERROR, "4xx");
		views.put(HttpStatus.Series.SERVER_ERROR, "5xx");

		return Collections.unmodifiableMap(views);
	}

}
