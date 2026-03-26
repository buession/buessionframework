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
package com.buession.redis.core.command.args.timeseries;

import com.buession.redis.utils.ArgStringBuilder;

/**
 * <code>TS.CREATE</code> 命令参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class CreateArgument extends BaseTsAACArgument {

	private Encoding encoding;

	private Ignore ignore;

	/**
	 * 构造函数
	 */
	public CreateArgument() {
		super();
	}

	public Encoding getEncoding() {
		return encoding;
	}

	public CreateArgument setEncoding(Encoding encoding) {
		this.encoding = encoding;
		return this;
	}

	public Ignore getIgnore() {
		return ignore;
	}

	public CreateArgument setIgnore(Ignore ignore) {
		this.ignore = ignore;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create().add("RETENTION", getRetention()).add("ENCODING", getEncoding())
				.add("CHUNK_SIZE", getChunkSize())
				.add("DUPLICATE_POLICY", getDuplicatePolicy())
				.append(getIgnore())
				.append(getLabels())
				.build();
	}

}
