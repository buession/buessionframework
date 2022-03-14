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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 普通表单请求体
 *
 * @author Yong.Teng
 */
public class EncodedFormRequestBody extends AbstractFormRequestBody<RequestBodyElement> {

	/**
	 * 构造函数
	 */
	public EncodedFormRequestBody(){
		this(null);
	}

	/**
	 * 构造函数
	 *
	 * @param content
	 * 		请求元素
	 */
	public EncodedFormRequestBody(List<RequestBodyElement> content){
		super(ContentType.APPLICATION_FORM_URLENCODED, content);
	}

	/**
	 * 构造函数
	 *
	 * @param content
	 * 		请求元素
	 * @param contentLength
	 * 		请求体大小
	 */
	public EncodedFormRequestBody(List<RequestBodyElement> content, long contentLength){
		super(ContentType.APPLICATION_FORM_URLENCODED, content, contentLength);
	}

	/**
	 * 构造函数
	 *
	 * @param content
	 * 		请求元素
	 * @param charset
	 * 		请求体编码
	 */
	public EncodedFormRequestBody(List<RequestBodyElement> content, Charset charset){
		super(ContentType.APPLICATION_FORM_URLENCODED, content, charset);
	}

	/**
	 * 构造函数
	 *
	 * @param content
	 * 		请求元素
	 * @param contentLength
	 * 		请求体大小
	 * @param charset
	 * 		请求体编码
	 */
	public EncodedFormRequestBody(List<RequestBodyElement> content, long contentLength, Charset charset){
		super(ContentType.APPLICATION_FORM_URLENCODED, content, contentLength, charset);
	}

	/**
	 * 添加表单元素
	 *
	 * @param name
	 * 		表单元素名称
	 * @param value
	 * 		表单元素值
	 *
	 * @since 2.0.0
	 */
	public void addRequestBodyElement(String name, short value){
		ensureNotNull();
		getContent().add(new RequestBodyElement(name, value));
	}

	/**
	 * 添加表单元素
	 *
	 * @param name
	 * 		表单元素名称
	 * @param value
	 * 		表单元素值
	 *
	 * @since 2.0.0
	 */
	public void addRequestBodyElement(String name, int value){
		ensureNotNull();
		getContent().add(new RequestBodyElement(name, value));
	}

	/**
	 * 添加表单元素
	 *
	 * @param name
	 * 		表单元素名称
	 * @param value
	 * 		表单元素值
	 *
	 * @since 2.0.0
	 */
	public void addRequestBodyElement(String name, long value){
		ensureNotNull();
		getContent().add(new RequestBodyElement(name, value));
	}

	/**
	 * 添加表单元素
	 *
	 * @param name
	 * 		表单元素名称
	 * @param value
	 * 		表单元素值
	 *
	 * @since 2.0.0
	 */
	public void addRequestBodyElement(String name, float value){
		ensureNotNull();
		getContent().add(new RequestBodyElement(name, value));
	}

	/**
	 * 添加表单元素
	 *
	 * @param name
	 * 		表单元素名称
	 * @param value
	 * 		表单元素值
	 *
	 * @since 2.0.0
	 */
	public void addRequestBodyElement(String name, double value){
		ensureNotNull();
		getContent().add(new RequestBodyElement(name, value));
	}

	/**
	 * 添加表单元素
	 *
	 * @param name
	 * 		表单元素名称
	 * @param value
	 * 		表单元素值
	 *
	 * @since 2.0.0
	 */
	public void addRequestBodyElement(String name, boolean value){
		ensureNotNull();
		getContent().add(new RequestBodyElement(name, value));
	}

	/**
	 * 添加表单元素
	 *
	 * @param name
	 * 		表单元素名称
	 * @param value
	 * 		表单元素值
	 */
	public void addRequestBodyElement(String name, String value){
		ensureNotNull();
		getContent().add(new RequestBodyElement(name, value));
	}

	/**
	 * 添加表单元素
	 *
	 * @param requestBodyElement
	 * 		表单元素
	 */
	public void addRequestBodyElement(RequestBodyElement requestBodyElement){
		ensureNotNull();
		getContent().add(requestBodyElement);
	}

	/**
	 * 批量添加表单元素
	 *
	 * @param requestBodyElements
	 * 		表单元素
	 *
	 * @since 1.2.1
	 */
	public void addRequestBodyElements(List<RequestBodyElement> requestBodyElements){
		if(requestBodyElements != null){
			ensureNotNull();
			getContent().addAll(requestBodyElements);
		}
	}

	/**
	 * 批量添加表单元素
	 *
	 * @param requestBodyElements
	 * 		表单元素名称和值健对值
	 *
	 * @since 1.2.1
	 */
	public void addRequestBodyElements(Map<String, String> requestBodyElements){
		if(requestBodyElements != null){
			ensureNotNull();
			requestBodyElements.forEach((name, value)->getContent().add(new RequestBodyElement(name, value)));
		}
	}

	private void ensureNotNull(){
		if(getContent() == null){
			setContent(new ArrayList<>());
		}
	}

}
