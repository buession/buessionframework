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
package com.buession.redis.core.command.args;

import com.buession.redis.utils.ArgStringBuilder;

/**
 * <code>FT.SUGGET</code> 命令参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class FtSugGetArgument {

	private Boolean fuzzy;

	private Boolean withScores;

	private Boolean withPayloads;

	private Long max;

	public FtSugGetArgument() {
	}

	public FtSugGetArgument(final boolean fuzzy, final boolean withScores, final boolean withPayloads, final long max) {
		this.fuzzy = fuzzy;
		this.withScores = withScores;
		this.withPayloads = withPayloads;
		this.max = max;
	}

	public FtSugGetArgument(final long max) {
		this.max = max;
	}

	public Boolean isFuzzy() {
		return getFuzzy();
	}

	public Boolean getFuzzy() {
		return fuzzy;
	}

	public FtSugGetArgument fuzzy() {
		return setFuzzy(true);
	}

	public FtSugGetArgument setFuzzy(boolean fuzzy) {
		this.fuzzy = fuzzy;
		return this;
	}

	public Boolean isWithScores() {
		return getWithScores();
	}

	public Boolean getWithScores() {
		return withScores;
	}

	public FtSugGetArgument withScores() {
		return setWithScores(true);
	}

	public FtSugGetArgument setWithScores(Boolean withScores) {
		this.withScores = withScores;
		return this;
	}

	public Boolean isWithPayloads() {
		return getWithPayloads();
	}

	public Boolean getWithPayloads() {
		return withPayloads;
	}

	public FtSugGetArgument withPayloads() {
		return setWithPayloads(true);
	}

	public FtSugGetArgument setWithPayloads(Boolean withPayloads) {
		this.withPayloads = withPayloads;
		return this;
	}

	public Long getMax() {
		return max;
	}

	public FtSugGetArgument setMax(long max) {
		this.max = max;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create().append(Boolean.TRUE.equals(getFuzzy()) ? "FUZZY" : null)
				.append(Boolean.TRUE.equals(getWithScores()) ? "WITHSCORES" : null)
				.append(Boolean.TRUE.equals(getWithPayloads()) ? "WITHPAYLOADS" : null).add("MAX", max).toString();
	}

}
