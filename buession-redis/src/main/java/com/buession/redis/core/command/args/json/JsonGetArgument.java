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
 * | Copyright @ 2013-2026 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command.args.json;

import com.buession.redis.utils.ArgStringBuilder;

/**
 * JSON.GET 命令参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class JsonGetArgument {

	private String indent;

	private String newline;

	private String space;

	/**
	 * 构造函数
	 */
	public JsonGetArgument() {

	}

	/**
	 * 构造函数
	 *
	 * @param indent
	 * 		-
	 * @param newline
	 * 		-
	 * @param space
	 * 		-
	 */
	public JsonGetArgument(final String indent, final String newline, final String space) {
		this.indent = indent;
		this.newline = newline;
		this.space = space;
	}

	public String getIndent() {
		return indent;
	}

	public JsonGetArgument setIndent(String indent) {
		this.indent = indent;
		return this;
	}

	public String getNewline() {
		return newline;
	}

	public JsonGetArgument setNewline(String newline) {
		this.newline = newline;
		return this;
	}

	public String getSpace() {
		return space;
	}

	public JsonGetArgument setSpace(String space) {
		this.space = space;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create()
				.add("INDENT", getIndent())
				.add("NEWLINE", getNewline())
				.add("SPACE", getSpace())
				.build();
	}

}
