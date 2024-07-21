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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command.args;

import com.buession.core.collect.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code BITFIELD} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class BitFieldArgument implements ArrayArgument<Object> {

	/**
	 * 子命令列表
	 */
	private final List<SubCommand> commands = new ArrayList<>();

	/**
	 * 构造函数
	 */
	private BitFieldArgument() {

	}

	/**
	 * Build a new {@code GET} subcommand.
	 *
	 * @param bitFieldType
	 * 		The bit field type, must not be {@code null}.
	 * @param offset
	 * 		The bitfield offset.
	 *
	 * @return The {@link BitFieldArgument} instance.
	 */
	public BitFieldArgument get(final BitFieldType bitFieldType, final int offset) {
		return get(bitFieldType, false, offset);
	}

	/**
	 * Build a new {@code GET} subcommand.
	 *
	 * @param bitFieldType
	 * 		The bit field type, must not be {@code null}.
	 * @param bitOffset
	 * 		-
	 * @param offset
	 * 		The bitfield offset.
	 *
	 * @return The {@link BitFieldArgument} instance.
	 */
	public BitFieldArgument get(final BitFieldType bitFieldType, final boolean bitOffset, final int offset) {
		commands.add(new Get(bitFieldType, bitOffset, offset));
		return this;
	}

	/**
	 * Build a new {@code SET} subcommand.
	 *
	 * @param bitFieldType
	 * 		The bit field type, must not be {@code null}.
	 * @param offset
	 * 		The bitfield offset.
	 * @param value
	 * 		The value.
	 *
	 * @return The {@link BitFieldArgument} instance.
	 */
	public BitFieldArgument set(final BitFieldType bitFieldType, final int offset, final long value) {
		return set(bitFieldType, false, offset, value);
	}

	/**
	 * Build a new {@code SET} subcommand.
	 *
	 * @param bitFieldType
	 * 		The bit field type, must not be {@code null}.
	 * @param bitOffset
	 * 		-
	 * @param offset
	 * 		The bitfield offset.
	 * @param value
	 * 		The value.
	 *
	 * @return The {@link BitFieldArgument} instance.
	 */
	public BitFieldArgument set(final BitFieldType bitFieldType, final boolean bitOffset, final int offset,
								final long value) {
		commands.add(new Set(bitFieldType, bitOffset, offset, value));
		return this;
	}

	/**
	 * Build a new {@code INCRBY} subcommand.
	 *
	 * @param bitFieldType
	 * 		The bit field type, must not be {@code null}.
	 * @param offset
	 * 		The bitfield offset.
	 * @param value
	 * 		The value.
	 *
	 * @return The {@link BitFieldArgument} instance.
	 */
	public BitFieldArgument incrBy(final BitFieldType bitFieldType, final int offset, final long value) {
		return incrBy(bitFieldType, false, offset, value);
	}

	/**
	 * Build a new {@code INCRBY} subcommand.
	 *
	 * @param bitFieldType
	 * 		The bit field type, must not be {@code null}.
	 * @param bitOffset
	 * 		-
	 * @param offset
	 * 		The bitfield offset.
	 * @param value
	 * 		The value.
	 *
	 * @return The {@link BitFieldArgument} instance.
	 */
	public BitFieldArgument incrBy(final BitFieldType bitFieldType, final boolean bitOffset, final int offset,
								   final long value) {
		commands.add(new IncrBy(bitFieldType, bitOffset, offset, value));
		return this;
	}

	/**
	 * Build a new {@code OVERFLOW} subcommand.
	 *
	 * @param overflowType
	 * 		The of overflow, must not be {@code null}.
	 *
	 * @return The {@link BitFieldArgument} instance.
	 */
	public BitFieldArgument overflow(final OverflowType overflowType) {
		commands.add(new Overflow(overflowType));
		return this;
	}

	/**
	 * 返回子命令列表
	 *
	 * @return 子命令列表
	 */
	public List<SubCommand> getCommands() {
		return commands;
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[]{};

		for(SubCommand subCommand : commands){
			result = Arrays.merge(result, subCommand.toArray());
		}

		return result;
	}

	/**
	 * 子命令类型
	 */
	public enum CommandType {

		/**
		 * 返回指定的位域
		 */
		GET,

		/**
		 * 设置指定位域的值并返回它的原值
		 */
		SET,

		/**
		 * 递增或递减指定的位域并返回新值
		 */
		INCRBY,

		/**
		 * 在期望整数类型的情况下，可以通过i为有符号整数和u无符号整数加上整数类型的位数来构成它
		 */
		OVERFLOW

	}

	/**
	 * 在期望整数类型的情况下，可以通过i为有符号整数和u无符号整数加上整数类型的位数来构成它
	 */
	public enum OverflowType {
		/**
		 * 环绕，包含有符号和无符号整数
		 */
		WRAP,

		/**
		 * 使用饱和算术，即在下溢时将该值设置为最小整数值，并在溢出时将其设置为最大整数值
		 */
		SAT,

		/**
		 * 在这种模式下，没有检测到溢出或下溢操作
		 */
		FAIL
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

	public interface SubCommand extends ArrayArgument<Object> {

		CommandType getCommandType();

	}

	/**
	 * Representation for the {@code GET} subcommand for {@code BITFIELD}.
	 */
	public final static class Get implements SubCommand {

		private final BitFieldType bitFieldType;

		private final boolean bitOffset;

		private final int offset;

		public Get(final BitFieldType bitFieldType, final boolean bitOffset, final int offset) {
			this.bitFieldType = bitFieldType;
			this.bitOffset = bitOffset;
			this.offset = offset;
		}

		@Override
		public CommandType getCommandType() {
			return CommandType.GET;
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

		@Override
		public Object[] toArray() {
			return new Object[]{getCommandType().name(), bitFieldType.toString(), bitOffset ? "#" + offset : offset};
		}

		@Override
		public String toString() {
			return getCommandType() + " " + bitFieldType + " " + (bitOffset ? "#" + offset : offset);
		}

	}

	public final static class Set implements SubCommand {

		private final BitFieldType bitFieldType;

		private final boolean bitOffset;

		private final int offset;

		private final long value;

		public Set(final BitFieldType bitFieldType, final boolean bitOffset, final int offset, final long value) {
			this.bitFieldType = bitFieldType;
			this.bitOffset = bitOffset;
			this.offset = offset;
			this.value = value;
		}

		@Override
		public CommandType getCommandType() {
			return CommandType.SET;
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
		public Object[] toArray() {
			return new Object[]{getCommandType().name(), bitFieldType.toString(), bitOffset ? "#" + offset : offset,
					value};
		}

		@Override
		public String toString() {
			return getCommandType() + " " + bitFieldType + " " + (bitOffset ? "#" + offset : offset) + " " + value;
		}

	}

	public final static class IncrBy implements SubCommand {

		private final BitFieldType bitFieldType;

		private final boolean bitOffset;

		private final int offset;

		private final long value;

		public IncrBy(final BitFieldType bitFieldType, final boolean bitOffset, final int offset, final long value) {
			this.bitFieldType = bitFieldType;
			this.bitOffset = bitOffset;
			this.offset = offset;
			this.value = value;
		}

		@Override
		public CommandType getCommandType() {
			return CommandType.INCRBY;
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
		public Object[] toArray() {
			return new Object[]{getCommandType().name(), bitFieldType.toString(), bitOffset ? "#" + offset : offset,
					value};
		}

		@Override
		public String toString() {
			return getCommandType() + " " + bitFieldType + " " + (bitOffset ? "#" + offset : offset) + " " + value;
		}

	}

	public final static class Overflow implements SubCommand {

		private final OverflowType overflowType;

		public Overflow(final OverflowType overflowType) {
			this.overflowType = overflowType;
		}

		@Override
		public CommandType getCommandType() {
			return CommandType.OVERFLOW;
		}

		public OverflowType getOverflowType() {
			return overflowType;
		}

		@Override
		public Object[] toArray() {
			return new Object[]{getCommandType().name(), overflowType.name()};
		}

		@Override
		public String toString() {
			return getCommandType() + " " + overflowType;
		}

	}

}
