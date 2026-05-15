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

import com.buession.redis.core.Quantization;
import com.buession.redis.core.command.args.Argument;
import com.buession.redis.utils.ArgStringBuilder;
import com.buession.redis.utils.SafeEncoder;

/**
 * <code>VADD</code> 命令参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class VAddArgument implements Argument {

	private Integer reduce;

	private Fp32Values fp32Values;

	private Boolean cas;

	private Quantization quantization;

	private Integer ef;

	private String setattr;

	private Integer m;

	public VAddArgument() {
	}

	public Integer getReduce() {
		return reduce;
	}

	public VAddArgument setReduce(int reduce) {
		this.reduce = reduce;
		return this;
	}

	public Fp32Values getFp32Values() {
		return fp32Values;
	}

	public VAddArgument setFp32Values(Fp32Values fp32Values) {
		this.fp32Values = fp32Values;
		return this;
	}

	public Boolean getCas() {
		return cas;
	}

	public VAddArgument setCas(boolean cas) {
		this.cas = cas;
		return this;
	}

	public Quantization getQuantization() {
		return quantization;
	}

	public VAddArgument setQuantization(Quantization quantization) {
		this.quantization = quantization;
		return this;
	}

	public Integer getEf() {
		return ef;
	}

	public VAddArgument setEf(int ef) {
		this.ef = ef;
		return this;
	}

	public String getSetattr() {
		return setattr;
	}

	public VAddArgument setSetattr(String setattr) {
		this.setattr = setattr;
		return this;
	}

	public VAddArgument setSetattr(byte[] setattr) {
		return setSetattr(SafeEncoder.encode(setattr));
	}

	public Integer getM() {
		return m;
	}

	public VAddArgument setM(int m) {
		this.m = m;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create().add("REDUCE", getReduce()).append(getFp32Values())
				.append(Boolean.TRUE.equals(getCas()) ? "CAS" : null).append(getQuantization()).add("EF", getEf())
				.add("SETATTR", getSetattr()).add("M", getM()).build();
	}

	public interface Fp32Values {

	}

	public final static class Fp32 implements Fp32Values {

		@Override
		public String toString() {
			return "FP32";
		}

	}

	public final static class Values implements Fp32Values {

		private Integer num;

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
