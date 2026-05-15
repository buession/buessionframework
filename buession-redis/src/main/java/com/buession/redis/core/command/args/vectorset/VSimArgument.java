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
package com.buession.redis.core.command.args.vectorset;

import com.buession.redis.core.command.args.Argument;
import com.buession.redis.utils.ArgStringBuilder;
import com.buession.redis.utils.SafeEncoder;

/**
 * <code>VSIM</code> 命令参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class VSimArgument implements Argument {

	private EleFp32Values eleFp32Values;

	private Double epsilon;

	private Integer ef;

	private String filter;

	private Integer filterEf;

	private Boolean truth;

	private Boolean noThread;

	public VSimArgument() {
	}

	public EleFp32Values getEleFp32Values() {
		return eleFp32Values;
	}

	public VSimArgument setEleFp32Values(EleFp32Values eleFp32Values) {
		this.eleFp32Values = eleFp32Values;
		return this;
	}

	public Double getEpsilon() {
		return epsilon;
	}

	public VSimArgument setEpsilon(Double epsilon) {
		this.epsilon = epsilon;
		return this;
	}

	public Integer getEf() {
		return ef;
	}

	public VSimArgument setEf(int ef) {
		this.ef = ef;
		return this;
	}

	public String getFilter() {
		return filter;
	}

	public VSimArgument setFilter(String filter) {
		this.filter = filter;
		return this;
	}

	public VSimArgument setFilter(byte[] filter) {
		return setFilter(SafeEncoder.encode(filter));
	}

	public Integer getFilterEf() {
		return filterEf;
	}

	public VSimArgument setFilterEf(int filterEf) {
		this.filterEf = filterEf;
		return this;
	}

	public Boolean isTruth() {
		return getTruth();
	}

	public Boolean getTruth() {
		return truth;
	}

	public VSimArgument truth() {
		return setTruth(true);
	}

	public VSimArgument setTruth(boolean truth) {
		this.truth = truth;
		return this;
	}

	public Boolean isNoThread() {
		return getNoThread();
	}

	public Boolean getNoThread() {
		return noThread;
	}

	public VSimArgument noThread() {
		return setNoThread(true);
	}

	public VSimArgument setNoThread(boolean noThread) {
		this.noThread = noThread;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create().append(getEleFp32Values()).add("EPSILON", getEpsilon()).add("EF", getEf())
				.add("FILTER", getFilter()).add("FILTER-EF", getFilterEf())
				.append(Boolean.TRUE.equals(getTruth()) ? "TRUTH" : null)
				.append(Boolean.TRUE.equals(getNoThread()) ? "NOTHREAD" : null).build();
	}

	public interface EleFp32Values {

	}

	public final static class Ele implements EleFp32Values {

		@Override
		public String toString() {
			return "ELE";
		}

	}

	public final static class Fp32 implements EleFp32Values {

		@Override
		public String toString() {
			return "FP32";
		}

	}

	public final static class Values implements EleFp32Values {

		private final Integer num;

		public Values(final int num) {
			this.num = num;
		}

		public Integer getNum() {
			return num;
		}

		@Override
		public String toString() {
			return ArgStringBuilder.create().add("VALUES", getNum()).build();
		}

	}

}
