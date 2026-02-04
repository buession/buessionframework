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

import com.buession.redis.core.Keyword;
import com.buession.redis.utils.ArgStringBuilder;

/**
 * BITFIELD 命令参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class BitFieldArgument extends BaseBitFieldArgument {

	/**
	 * 加操作
	 */
	private IncrbyOp incrBy;

	/**
	 * 数值溢出行为
	 */
	private Overflow overflow;

	/**
	 * 构造函数
	 */
	public BitFieldArgument() {

	}

	/**
	 * 构造函数
	 *
	 * @param set
	 * 		写操作
	 */
	public BitFieldArgument(SetOp set) {
		super(set);
	}

	/**
	 * 构造函数
	 *
	 * @param set
	 * 		写操作
	 * @param get
	 * 		获取操作
	 */
	public BitFieldArgument(SetOp set, GetOp get) {
		super(set, get);
	}

	/**
	 * 构造函数
	 *
	 * @param set
	 * 		写操作
	 * @param get
	 * 		获取操作
	 * @param incrBy
	 * 		加操作
	 */
	public BitFieldArgument(SetOp set, GetOp get, IncrbyOp incrBy) {
		this(set, get);
		this.incrBy = incrBy;
	}

	/**
	 * 构造函数
	 *
	 * @param set
	 * 		写操作
	 * @param get
	 * 		获取操作
	 * @param incrBy
	 * 		加操作
	 * @param overflow
	 * 		数值溢出行为
	 */
	public BitFieldArgument(SetOp set, GetOp get, IncrbyOp incrBy, Overflow overflow) {
		this(set, get, incrBy);
		this.overflow = overflow;
	}

	/**
	 * 构造函数
	 *
	 * @param set
	 * 		写操作
	 * @param incrBy
	 * 		加操作
	 */
	public BitFieldArgument(SetOp set, IncrbyOp incrBy) {
		this(set);
		this.incrBy = incrBy;
	}

	/**
	 * 构造函数
	 *
	 * @param set
	 * 		写操作
	 * @param incrBy
	 * 		加操作
	 * @param overflow
	 * 		数值溢出行为
	 */
	public BitFieldArgument(SetOp set, IncrbyOp incrBy, Overflow overflow) {
		this(set, incrBy);
		this.overflow = overflow;
	}

	/**
	 * 构造函数
	 *
	 * @param set
	 * 		写操作
	 * @param overflow
	 * 		数值溢出行为
	 */
	public BitFieldArgument(SetOp set, Overflow overflow) {
		this(set);
		this.overflow = overflow;
	}

	/**
	 * 构造函数
	 *
	 * @param get
	 * 		获取操作
	 */
	public BitFieldArgument(GetOp get) {
		super(get);
	}

	/**
	 * 构造函数
	 *
	 * @param get
	 * 		获取操作
	 * @param incrBy
	 * 		加操作
	 */
	public BitFieldArgument(GetOp get, IncrbyOp incrBy) {
		this(get);
		this.incrBy = incrBy;
	}

	/**
	 * 构造函数
	 *
	 * @param get
	 * 		获取操作
	 * @param incrBy
	 * 		加操作
	 * @param overflow
	 * 		数值溢出行为
	 */
	public BitFieldArgument(GetOp get, IncrbyOp incrBy, Overflow overflow) {
		this(get, incrBy);
		this.overflow = overflow;
	}

	/**
	 * 构造函数
	 *
	 * @param incrBy
	 * 		加操作
	 */
	public BitFieldArgument(IncrbyOp incrBy) {
		this.incrBy = incrBy;
	}

	/**
	 * 构造函数
	 *
	 * @param incrBy
	 * 		加操作
	 * @param overflow
	 * 		数值溢出行为
	 */
	public BitFieldArgument(IncrbyOp incrBy, Overflow overflow) {
		this(incrBy);
		this.overflow = overflow;
	}

	/**
	 * 构造函数
	 *
	 * @param overflow
	 * 		数值溢出行为
	 */
	public BitFieldArgument(Overflow overflow) {
		this.overflow = overflow;
	}

	/**
	 * 返回加操作
	 *
	 * @return 加操作
	 */
	public IncrbyOp getIncrBy() {
		return incrBy;
	}

	/**
	 * 设置加操作
	 *
	 * @param incrBy
	 * 		加操作
	 *
	 * @return {@link BitFieldArgument}
	 */
	public BitFieldArgument setIncrBy(IncrbyOp incrBy) {
		this.incrBy = incrBy;
		return this;
	}

	/**
	 * 设置加操作
	 *
	 * @param bitFieldType
	 * 		操作类型
	 * @param value
	 * 		值
	 *
	 * @return {@link BitFieldArgument}
	 */
	public BitFieldArgument setIncrBy(BitFieldType bitFieldType, long value) {
		return setIncrBy(bitFieldType, 0, value);
	}

	/**
	 * 设置加操作
	 *
	 * @param bitFieldType
	 * 		操作类型
	 * @param offset
	 * 		偏移量
	 * @param value
	 * 		值
	 *
	 * @return {@link BitFieldArgument}
	 */
	public BitFieldArgument setIncrBy(BitFieldType bitFieldType, int offset, long value) {
		return setIncrBy(bitFieldType, false, offset, value);
	}

	/**
	 * 设置加操作
	 *
	 * @param bitFieldType
	 * 		操作类型
	 * @param bitOffset
	 * 		-
	 * @param offset
	 * 		偏移量
	 * @param value
	 * 		值
	 *
	 * @return {@link BitFieldArgument}
	 */
	public BitFieldArgument setIncrBy(BitFieldType bitFieldType, boolean bitOffset, int offset, long value) {
		return setIncrBy(new IncrbyOp(bitFieldType, bitOffset, offset, value));
	}

	/**
	 * 返回数值溢出行为
	 *
	 * @return 数值溢出行为
	 */
	public Overflow getOverflow() {
		return overflow;
	}

	/**
	 * 设置数值溢出行为
	 *
	 * @param overflow
	 * 		数值溢出行为
	 *
	 * @return {@link BitFieldArgument}
	 */
	public BitFieldArgument setOverflow(Overflow overflow) {
		this.overflow = overflow;
		return this;
	}

	@Override
	public String toString() {
		final ArgStringBuilder builder = ArgStringBuilder.create();

		builder.append(getSet());
		builder.append(getGet());
		builder.append(incrBy);
		builder.append(overflow);

		return builder.toString();
	}

	public enum Overflow implements Keyword {
		WRAP,

		SAT,

		FAIL;

		public String getValue() {
			return name();
		}

		@Override
		public String toString() {
			return getValue();
		}

	}

}
