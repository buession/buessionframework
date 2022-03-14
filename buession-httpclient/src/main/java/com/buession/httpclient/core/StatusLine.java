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
 * | Copyright @ 2013-2021 Buession.com Inc.														       |
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

	private int statusCode;

	private String statusText;

	public StatusLine(){
	}

	public StatusLine(int statusCode, String statusText){
		this.statusCode = statusCode;
		this.statusText = statusText;
	}

	public int getStatusCode(){
		return statusCode;
	}

	public void setStatusCode(final int statusCode){
		Assert.isNegative(statusCode, "HTTP response status code cloud not be negative.");
		this.statusCode = statusCode;
	}

	public String getStatusText(){
		return statusText;
	}

	public void setStatusText(final String statusText){
		Assert.isBlank(statusText, "HTTP response status text cloud not be null or empty.");
		this.statusText = statusText;
	}

	@Override
	public int hashCode(){
		int result = 16;

		result = 32 * result + statusCode;
		result = 32 * result + (statusText == null ? 0 : statusText.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj){
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
	public String toString(){
		final StringBuilder sb = new StringBuilder(50);

		sb.append(statusCode).append(Constants.SPACING_STRING).append(statusText);

		return sb.toString();
	}

}
