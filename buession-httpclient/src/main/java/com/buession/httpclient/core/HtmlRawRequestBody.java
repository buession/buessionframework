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

import java.nio.charset.Charset;

/**
 * HTML 请求体
 *
 * @author Yong.Teng
 */
public class HtmlRawRequestBody extends AbstractRawRequestBody<String> {

	/**
	 * 构造函数
	 */
	public HtmlRawRequestBody(){
		this(null);
	}

	/**
	 * 构造函数
	 *
	 * @param content
	 * 		请求体
	 */
	public HtmlRawRequestBody(String content){
		super(ContentType.TEXT_HTML, content);
	}

	/**
	 * 构造函数
	 *
	 * @param content
	 * 		请求体
	 * @param contentLength
	 * 		请求体大小
	 */
	public HtmlRawRequestBody(String content, long contentLength){
		super(ContentType.TEXT_HTML, content, contentLength);
	}

	/**
	 * 构造函数
	 *
	 * @param content
	 * 		请求体
	 * @param charset
	 * 		请求体编码
	 *
	 * @since 1.2.1
	 */
	public HtmlRawRequestBody(String content, Charset charset){
		super(ContentType.TEXT_HTML, content, charset);
	}

	/**
	 * 构造函数
	 *
	 * @param content
	 * 		请求体
	 * @param contentLength
	 * 		请求体大小
	 * @param charset
	 * 		请求体编码
	 *
	 * @since 1.2.1
	 */
	public HtmlRawRequestBody(String content, long contentLength, Charset charset){
		super(ContentType.TEXT_HTML, content, contentLength, charset);
	}

}
