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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.httpclient.core;

import com.buession.core.utils.Assert;
import com.buession.lang.Constants;

import java.util.Objects;

/**
 * 状态行 <a href="https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html" target="_blank">https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html</a>
 *
 * @author Yong.Teng
 */
public class StatusLine {

	/**
	 * 状态码
	 */
	private int statusCode;

	/**
	 * 状态信息
	 */
	private String statusText;

	/**
	 * 构造函数
	 */
	@Deprecated
	public StatusLine() {
	}

	/**
	 * 构造函数
	 *
	 * @param statusCode
	 * 		状态码
	 * @param statusText
	 * 		状态信息
	 */
	public StatusLine(int statusCode, String statusText) {
		setStatusCode(statusCode);
		this.statusText = statusText;
	}

	/**
	 * 返回状态码
	 *
	 * @return 状态码
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * 设置状态码
	 *
	 * @param statusCode
	 * 		状态码
	 */
	public void setStatusCode(final int statusCode) {
		if(statusCode != 0 && (statusCode < 100 || statusCode > 599)){
			throw new IllegalArgumentException("Illegal HTTP response status code: " + statusCode);
		}
		this.statusCode = statusCode;
	}

	/**
	 * 返回状态信息
	 *
	 * @return 状态信息
	 */
	public String getStatusText() {
		return statusText;
	}

	/**
	 * 设置状态信息
	 *
	 * @param statusText
	 * 		状态信息
	 */
	public void setStatusText(final String statusText) {
		Assert.isBlank(statusText, "HTTP response status text cloud not be null or empty.");
		this.statusText = statusText;
	}

	@Override
	public int hashCode() {
		int result = 16;

		result = 32 * result + statusCode;
		result = 32 * result + (statusText == null ? 0 : statusText.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj){
			return true;
		}

		if(obj instanceof StatusLine){
			StatusLine that = (StatusLine) obj;
			return Objects.equals(statusCode, that.getStatusCode());
		}

		return false;
	}

	@Override
	public String toString() {
		return statusCode + Constants.SPACING_STRING + statusText;
	}

}
