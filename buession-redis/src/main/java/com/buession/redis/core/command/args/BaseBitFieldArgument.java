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

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
abstract class BaseBitFieldArgument {

	/**
	 * 写操作
	 */
	private SetOp set;

	/**
	 * 获取操作
	 */
	private GetOp get;

	/**
	 * 构造函数
	 */
	protected BaseBitFieldArgument() {

	}

	/**
	 * 构造函数
	 *
	 * @param set
	 * 		写操作
	 */
	protected BaseBitFieldArgument(SetOp set) {
		this.set = set;
	}

	/**
	 * 构造函数
	 *
	 * @param set
	 * 		写操作
	 * @param get
	 * 		获取操作
	 */
	protected BaseBitFieldArgument(SetOp set, GetOp get) {
		this(set);
		this.get = get;
	}

	/**
	 * 构造函数
	 *
	 * @param get
	 * 		获取操作
	 */
	protected BaseBitFieldArgument(GetOp get) {
		this.get = get;
	}

	/**
	 * 返回写操作
	 *
	 * @return 写操作
	 */
	public SetOp getSet() {
		return set;
	}

	/**
	 * 设置写操作
	 *
	 * @param set
	 * 		写操作
	 *
	 * @return {@link BaseBitFieldArgument}
	 */
	public BaseBitFieldArgument setSet(SetOp set) {
		this.set = set;
		return this;
	}

	/**
	 * 设置写操作
	 *
	 * @param bitFieldType
	 * 		操作类型
	 * @param value
	 * 		值
	 *
	 * @return {@link BaseBitFieldArgument}
	 */
	public BaseBitFieldArgument setSet(BitFieldType bitFieldType, long value) {
		return setSet(bitFieldType, 0, value);
	}

	/**
	 * 设置写操作
	 *
	 * @param bitFieldType
	 * 		操作类型
	 * @param offset
	 * 		偏移
	 * @param value
	 * 		值
	 *
	 * @return {@link BaseBitFieldArgument}
	 */
	public BaseBitFieldArgument setSet(BitFieldType bitFieldType, int offset, long value) {
		return setSet(bitFieldType, false, offset, value);
	}

	/**
	 * 设置写操作
	 *
	 * @param bitFieldType
	 * 		操作类型
	 * @param bitOffset
	 * 		-
	 * @param offset
	 * 		偏移
	 * @param value
	 * 		值
	 *
	 * @return {@link BaseBitFieldArgument}
	 */
	public BaseBitFieldArgument setSet(BitFieldType bitFieldType, boolean bitOffset, int offset, long value) {
		return setSet(new SetOp(bitFieldType, bitOffset, offset, value));
	}

	/**
	 * 返回获取操作
	 *
	 * @return 获取操作
	 */
	public GetOp getGet() {
		return get;
	}

	/**
	 * 设置获取操作
	 *
	 * @param get
	 * 		获取操作
	 *
	 * @return {@link BaseBitFieldArgument}
	 */
	public BaseBitFieldArgument setGet(GetOp get) {
		this.get = get;
		return this;
	}

	/**
	 * 设置获取操作
	 *
	 * @param bitFieldType
	 * 		操作类型
	 * @param value
	 * 		值
	 *
	 * @return {@link BaseBitFieldArgument}
	 */
	public BaseBitFieldArgument setGet(BitFieldType bitFieldType, long value) {
		return setGet(bitFieldType, 0, value);
	}

	/**
	 * 设置获取操作
	 *
	 * @param bitFieldType
	 * 		操作类型
	 * @param offset
	 * 		偏移量
	 * @param value
	 * 		值
	 *
	 * @return {@link BaseBitFieldArgument}
	 */
	public BaseBitFieldArgument setGet(BitFieldType bitFieldType, int offset, long value) {
		return setGet(bitFieldType, false, offset, value);
	}

	/**
	 * 设置获取操作
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
	 * @return {@link BaseBitFieldArgument}
	 */
	public BaseBitFieldArgument setGet(BitFieldType bitFieldType, boolean bitOffset, int offset, long value) {
		return setGet(new GetOp(bitFieldType, bitOffset, offset, value));
	}

	public final static class BitFieldType {

		private final boolean signed;

		private final int bits;

		private BitFieldType(final boolean signed, final int bits) {
			this.signed = signed;
			this.bits = bits;
		}

		public boolean isSigned() {
			return signed;
		}

		public int getBits() {
			return bits;
		}

		@Override
		public String toString() {
			return (signed ? "i" : "u") + bits;
		}

	}

	abstract static class BaseOp {

		private final String commandType;

		private final BitFieldType bitFieldType;

		private final boolean bitOffset;

		private final int offset;

		private final long value;

		private BaseOp(final String commandType, final BitFieldType bitFieldType, final boolean bitOffset,
					   final int offset, final long value) {
			this.commandType = commandType;
			this.bitFieldType = bitFieldType;
			this.bitOffset = bitOffset;
			this.offset = offset;
			this.value = value;
		}

		public BitFieldType getBitFieldType() {
			return bitFieldType;
		}

		public boolean isBitOffset() {
			return bitOffset;
		}

		public int getOffset() {
			return offset;
		}

		public long getValue() {
			return value;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder(commandType);

			sb.append(" ").append(bitFieldType);

			if(bitOffset){
				sb.append(" #").append(offset);
			}else{
				sb.append(" ").append(offset);
			}

			sb.append(" ").append(value);

			return sb.toString();
		}

	}

	public final static class GetOp extends BaseOp {

		public GetOp(final BitFieldType bitFieldType, final boolean bitOffset, final int offset, final long value) {
			super("GET", bitFieldType, bitOffset, offset, value);
		}

	}

	public final static class SetOp extends BaseOp {

		public SetOp(final BitFieldType bitFieldType, final boolean bitOffset, final int offset, final long value) {
			super("SET", bitFieldType, bitOffset, offset, value);
		}

	}

	public final static class IncrbyOp extends BaseOp {

		public IncrbyOp(final BitFieldType bitFieldType, final boolean bitOffset, final int offset, final long value) {
			super("INCRBY", bitFieldType, bitOffset, offset, value);
		}

	}

}
