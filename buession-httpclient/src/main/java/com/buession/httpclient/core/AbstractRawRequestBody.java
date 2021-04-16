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

import java.nio.charset.Charset;

/**
 * 原始请求体抽象类
 *
 * @param <V>
 * 		请求体类型
 *
 * @author Yong.Teng
 */
public abstract class AbstractRawRequestBody<C extends ContentType, V> extends AbstractRequestBody<V> implements RawRequestBody<C, V> {

	/**
	 * 构造函数
	 */
	public AbstractRawRequestBody(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param contentType
	 * 		请求体 Content-Type
	 * @param content
	 * 		请求体
	 */
	public AbstractRawRequestBody(ContentType contentType, V content){
		super(contentType, content);
	}

	/**
	 * 构造函数
	 *
	 * @param contentType
	 * 		请求体 Content-Type
	 * @param content
	 * 		请求体
	 * @param contentLength
	 * 		请求体大小
	 */
	public AbstractRawRequestBody(ContentType contentType, V content, long contentLength){
		super(contentType, content, contentLength);
	}

	/**
	 * 构造函数，ContentType 为 text/plain
	 *
	 * @param content
	 * 		请求体
	 * @param charset
	 * 		字符集
	 *
	 * @since 1.2.1
	 */
	public AbstractRawRequestBody(V content, Charset charset){
		super(content, charset);
	}

	/**
	 * 构造函数，ContentType 为 text/plain
	 *
	 * @param content
	 * 		请求体
	 * @param contentLength
	 * 		请求体大小
	 * @param charset
	 * 		字符集
	 *
	 * @since 1.2.1
	 */
	public AbstractRawRequestBody(V content, long contentLength, Charset charset){
		super(content, contentLength, charset);
	}

}
