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
 * | Copyright @ 2013-2026 Buession.com Inc.														|
 * +------------------------------------------------------------------------------------------------+
 */
package com.buession.web.mvc.view.document;

import com.buession.core.utils.StringUtils;

import java.io.Serializable;

/**
 * 页面元信息
 *
 * @param title
 * 		标题
 * @param charset
 * 		编码
 * @param keywords
 * 		关键字
 * @param description
 * 		描述信息
 * @param author
 * 		作者
 * @param copyright
 * 		版权
 *
 * @author Yong.Teng
 */
public record MetaData(String title, String charset, String keywords, String description, String author,
					   String copyright) implements Serializable {

	private final static long serialVersionUID = -2362098929099645692L;

	/**
	 * 返回页面标题
	 *
	 * @return 页面标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 返回页面编码
	 *
	 * @return 页面编码
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * 返回页面关键字
	 *
	 * @return 页面关键字
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * 返回页面描述信息
	 *
	 * @return 页面描述信息
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 返回页面作者
	 *
	 * @return 页面作者
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * 返回页面版权
	 *
	 * @return 页面版权
	 */
	public String getCopyright() {
		return copyright;
	}

	@Override
	public String toString() {
		final String equalsSign = StringUtils.repeat("=", 16);
		final StringBuilder sb = new StringBuilder(128);

		sb.append(equalsSign).append(System.lineSeparator());
		sb.append("title：").append(title).append(System.lineSeparator());
		sb.append("charset: ").append(charset).append(System.lineSeparator());
		sb.append("keywords: ").append(keywords).append(System.lineSeparator());
		sb.append("description: ").append(description).append(System.lineSeparator());
		sb.append("author: ").append(author).append(System.lineSeparator());
		sb.append("copyright: ").append(copyright).append(System.lineSeparator());
		sb.append(equalsSign);

		return sb.toString();
	}

}
