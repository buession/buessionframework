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
package com.buession.redis.core.command.args.string;

import com.buession.redis.core.command.args.Argument;
import com.buession.redis.utils.ArgStringBuilder;

/**
 * <code>LCS</code> 命令参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class LcsArgument implements Argument {

	private Boolean len;

	private Boolean idx;

	private Long minMatchLen;

	private Boolean withMatchLen;

	public LcsArgument() {
	}

	public LcsArgument(final boolean len, final boolean idx, final long minMatchLen, final boolean withMatchLen) {
		this.len = len;
		this.idx = idx;
		this.minMatchLen = minMatchLen;
		this.withMatchLen = withMatchLen;
	}

	public Boolean getLen() {
		return len;
	}

	public LcsArgument setLen(Boolean len) {
		this.len = len;
		return this;
	}

	public Boolean isIdx() {
		return getIdx();
	}

	public Boolean getIdx() {
		return idx;
	}

	public LcsArgument idx() {
		return setIdx(true);
	}

	public LcsArgument setIdx(Boolean idx) {
		this.idx = idx;
		return this;
	}

	public Long getMinMatchLen() {
		return minMatchLen;
	}

	public LcsArgument setMinMatchLen(long minMatchLen) {
		this.minMatchLen = minMatchLen;
		return this;
	}

	public Boolean isWithMatchLen() {
		return getWithMatchLen();
	}

	public Boolean getWithMatchLen() {
		return withMatchLen;
	}

	public LcsArgument withMatchLen() {
		return setWithMatchLen(true);
	}

	public LcsArgument setWithMatchLen(boolean withMatchLen) {
		this.withMatchLen = withMatchLen;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create()
				.append(Boolean.TRUE.equals(getLen()) ? "LEN" : null)
				.append(Boolean.TRUE.equals(getIdx()) ? "IDX" : null)
				.add("MINMATCHLEN", getMinMatchLen())
				.append(Boolean.TRUE.equals(getWithMatchLen()) ? "WITHMATCHLEN" : null)
				.build();
	}

}
