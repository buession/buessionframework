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

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件上传表单表单请求体
 *
 * @author Yong.Teng
 */
public class MultipartFormRequestBody extends AbstractRequestBody<List<MultipartRequestBodyElement>> {

	/**
	 * 构造函数
	 */
	public MultipartFormRequestBody(){
		this(null);
	}

	/**
	 * 构造函数
	 *
	 * @param content
	 * 		请求元素
	 */
	public MultipartFormRequestBody(List<MultipartRequestBodyElement> content){
		super(ContentType.MULTIPART_FORM_DATA, content);
	}

	/**
	 * 构造函数
	 *
	 * @param content
	 * 		请求元素
	 * @param contentLength
	 * 		请求体大小
	 */
	public MultipartFormRequestBody(List<MultipartRequestBodyElement> content, long contentLength){
		super(ContentType.MULTIPART_FORM_DATA, content, contentLength);
	}

	/**
	 * 构造函数
	 *
	 * @param content
	 * 		请求元素
	 * @param charset
	 * 		请求体编码
	 */
	public MultipartFormRequestBody(List<MultipartRequestBodyElement> content, Charset charset){
		super(new ContentType(ContentType.MULTIPART_FORM_DATA.getMimeType(), charset), content);
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
	public MultipartFormRequestBody(List<MultipartRequestBodyElement> content, long contentLength, Charset charset){
		super(new ContentType(ContentType.MULTIPART_FORM_DATA.getMimeType(), charset), content, contentLength);
	}

	/**
	 * 添加表单元素
	 *
	 * @param requestBodyElement
	 * 		表单元素
	 */
	public void addRequestBodyElement(MultipartRequestBodyElement requestBodyElement){
		if(getContent() == null){
			setContent(new ArrayList<>());
		}

		getContent().add(requestBodyElement);
	}

	/**
	 * 添加表单元素
	 *
	 * @param name
	 * 		表单元素名称
	 * @param value
	 * 		文件对象
	 *
	 * @since 1.2.1
	 */
	public void addRequestBodyElement(String name, File value){
		if(getContent() == null){
			setContent(new ArrayList<>());
		}

		getContent().add(new MultipartRequestBodyElement(name, value));
	}

	/**
	 * 添加表单元素
	 *
	 * @param name
	 * 		表单元素名称
	 * @param value
	 * 		表单元素值
	 *
	 * @since 1.2.1
	 */
	public void addRequestBodyElement(String name, short value){
		if(getContent() == null){
			setContent(new ArrayList<>());
		}

		getContent().add(new MultipartRequestBodyElement(name, value));
	}

	/**
	 * 添加表单元素
	 *
	 * @param name
	 * 		表单元素名称
	 * @param value
	 * 		表单元素值
	 *
	 * @since 1.2.1
	 */
	public void addRequestBodyElement(String name, int value){
		if(getContent() == null){
			setContent(new ArrayList<>());
		}

		getContent().add(new MultipartRequestBodyElement(name, value));
	}

	/**
	 * 添加表单元素
	 *
	 * @param name
	 * 		表单元素名称
	 * @param value
	 * 		表单元素值
	 *
	 * @since 1.2.1
	 */
	public void addRequestBodyElement(String name, long value){
		if(getContent() == null){
			setContent(new ArrayList<>());
		}

		getContent().add(new MultipartRequestBodyElement(name, value));
	}

	/**
	 * 添加表单元素
	 *
	 * @param name
	 * 		表单元素名称
	 * @param value
	 * 		表单元素值
	 *
	 * @since 1.2.1
	 */
	public void addRequestBodyElement(String name, float value){
		if(getContent() == null){
			setContent(new ArrayList<>());
		}

		getContent().add(new MultipartRequestBodyElement(name, value));
	}

	/**
	 * 添加表单元素
	 *
	 * @param name
	 * 		表单元素名称
	 * @param value
	 * 		表单元素值
	 *
	 * @since 1.2.1
	 */
	public void addRequestBodyElement(String name, double value){
		if(getContent() == null){
			setContent(new ArrayList<>());
		}

		getContent().add(new MultipartRequestBodyElement(name, value));
	}

	/**
	 * 添加表单元素
	 *
	 * @param name
	 * 		表单元素名称
	 * @param value
	 * 		表单元素值
	 *
	 * @since 1.2.1
	 */
	public void addRequestBodyElement(String name, boolean value){
		if(getContent() == null){
			setContent(new ArrayList<>());
		}

		getContent().add(new MultipartRequestBodyElement(name, value));
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
		if(getContent() == null){
			setContent(new ArrayList<>());
		}

		getContent().add(new MultipartRequestBodyElement(name, value));
	}

	/**
	 * 添加表单元素
	 *
	 * @param name
	 * 		表单元素名称
	 * @param value
	 * 		表单元素值
	 *
	 * @since 1.2.1
	 */
	public void addRequestBodyElement(String name, Object value){
		if(getContent() == null){
			setContent(new ArrayList<>());
		}

		getContent().add(new MultipartRequestBodyElement(name, value));
	}

	/**
	 * 批量添加表单元素
	 *
	 * @param requestBodyElements
	 * 		表单元素
	 *
	 * @since 1.2.1
	 */
	public void addRequestBodyElements(List<MultipartRequestBodyElement> requestBodyElements){
		if(getContent() == null){
			setContent(new ArrayList<>());
		}

		getContent().addAll(requestBodyElements);
	}

	/**
	 * 批量添加表单元素
	 *
	 * @param requestBodyElements
	 * 		表单元素名称和值健对值
	 *
	 * @since 1.2.1
	 */
	public void addRequestBodyElements(Map<String, Object> requestBodyElements){
		if(getContent() == null){
			setContent(new ArrayList<>());
		}

		if(requestBodyElements != null){
			requestBodyElements.forEach((name, value)->{
				if(value instanceof File){
					getContent().add(new MultipartRequestBodyElement(name, (File) value));
				}else{
					getContent().add(new MultipartRequestBodyElement(name, value));
				}
			});
		}
	}

}
