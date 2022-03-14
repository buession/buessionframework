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
import java.util.List;

/**
 * 表单请求体抽象类
 *
 * @param <V>
 * 		表单请求元素
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public abstract class AbstractFormRequestBody<V extends RequestBodyElement> extends AbstractRequestBody<List<V>> {

	/**
	 * 构造函数
	 */
	public AbstractFormRequestBody(){
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
	public AbstractFormRequestBody(ContentType contentType, List<V> content){
		super(contentType, content);
	}

	/**
	 * 构造函数
	 *
	 * @param contentType
	 * 		请求体 Content-Type
	 * @param content
	 * 		请求体
	 * @param charset
	 * 		字符集
	 *
	 * @since 2.0.0
	 */
	public AbstractFormRequestBody(ContentType contentType, List<V> content, Charset charset){
		super(new ContentType(contentType.getMimeType(), charset), content);
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
	public AbstractFormRequestBody(ContentType contentType, List<V> content, long contentLength){
		super(contentType, content, contentLength);
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
	 * @param charset
	 * 		字符集
	 */
	public AbstractFormRequestBody(ContentType contentType, List<V> content, long contentLength, Charset charset){
		super(new ContentType(contentType.getMimeType(), charset), content, contentLength);
	}

}
