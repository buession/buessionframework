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

import java.io.InputStream;
import java.util.List;

/**
 * @author Yong.Teng
 */
public class Response {

	/**
	 * 协议及其版本
	 */
	private ProtocolVersion protocolVersion;

	/**
	 * 状态码
	 */
	private int statusCode;

	/**
	 * 状态信息
	 */
	private String statusText;

	/**
	 * 响应状态
	 */
	private StatusLine statusLine;

	/**
	 * 响应头
	 */
	private List<Header> headers;

	/**
	 * 响应体，以流的形式返回
	 */
	private InputStream inputStream;

	/**
	 * 响应头，以文本的形式返回
	 */
	private String body;

	/**
	 * 响应内容大小
	 */
	private long contentLength;

	/**
	 * 返回协议及其版本
	 *
	 * @return 协议及其版本
	 */
	public ProtocolVersion getProtocolVersion(){
		return protocolVersion;
	}

	/**
	 * 设置协议及其版本
	 *
	 * @param protocolVersion
	 * 		协议及其版本
	 */
	public void setProtocolVersion(ProtocolVersion protocolVersion){
		this.protocolVersion = protocolVersion;
	}

	/**
	 * 返回状态码
	 *
	 * @return 状态码
	 */
	public int getStatusCode(){
		return statusCode;
	}

	/**
	 * 设置状态码
	 *
	 * @param statusCode
	 * 		状态码
	 */
	public void setStatusCode(final int statusCode){
		this.statusCode = statusCode;
	}

	/**
	 * 返回状态信息
	 *
	 * @return 状态信息
	 */
	public String getStatusText(){
		return statusText;
	}

	/**
	 * 设置状态信息
	 *
	 * @param statusText
	 * 		状态信息
	 */
	public void setStatusText(final String statusText){
		this.statusText = statusText;
	}

	/**
	 * 返回响应状态
	 *
	 * @return 响应状态
	 */
	public StatusLine getStatusLine(){
		return statusLine;
	}

	/**
	 * 设置响应状态
	 *
	 * @param statusLine
	 * 		响应状态
	 */
	public void setStatusLine(final StatusLine statusLine){
		this.statusLine = statusLine;
	}

	/**
	 * 返回响应头
	 *
	 * @return 响应头
	 */
	public List<Header> getHeaders(){
		return headers;
	}

	/**
	 * 设置响应头
	 *
	 * @param headers
	 * 		响应头
	 */
	public void setHeaders(final List<Header> headers){
		this.headers = headers;
	}

	/**
	 * 返回响应体，以流的形式返回
	 *
	 * @return 响应体，以流的形式返回
	 */
	public InputStream getInputStream(){
		return inputStream;
	}

	/**
	 * 设置响应体，以流的形式返回
	 *
	 * @param inputStream
	 * 		响应体，以流的形式返回
	 */
	public void setInputStream(final InputStream inputStream){
		this.inputStream = inputStream;
	}

	/**
	 * 返回响应头，以文本的形式返回
	 *
	 * @return 响应头，以文本的形式返回
	 */
	public String getBody(){
		return body;
	}

	/**
	 * 设置响应头，以文本的形式返回
	 *
	 * @param body
	 * 		响应头，以文本的形式返回
	 */
	public void setBody(final String body){
		this.body = body;
	}

	/**
	 * 返回响应内容大小
	 *
	 * @return 响应内容大小
	 */
	public long getContentLength(){
		return contentLength;
	}

	/**
	 * 设置响应内容大小
	 *
	 * @param contentLength
	 * 		响应内容大小
	 */
	public void setContentLength(long contentLength){
		this.contentLength = contentLength;
	}

	/**
	 * 返回是否为成功状态，即：状态码大于等于 200 且小于 300 为成功状态
	 *
	 * @return 是否为成功状态
	 */
	public boolean isSuccessful(){
		return statusCode >= 200 && statusCode < 300;
	}

	@Override
	public String toString(){
		return "Response{" + "protocolVersion=" + protocolVersion + ", statusCode=" + statusCode + ", statusText='" +
				statusText + '\'' + ", statusLine=" + statusLine + ", headers=" + headers + ", inputStream=" +
				inputStream + ", body='" + body + '\'' + ", contentLength=" + contentLength + '}';
	}

}
