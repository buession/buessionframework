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
import com.buession.core.utils.NumberUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code BITFIELD_RO} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class BitFieldRoArgument implements ArrayArgument<String> {

	/**
	 * 子命令列表
	 */
	private final List<SubCommand> commands = new ArrayList<>();

	/**
	 * 构造函数
	 */
	public BitFieldRoArgument() {
	}

	/**
	 * Build a new {@code GET} subcommand.
	 *
	 * @param bitFieldType
	 * 		The bit field type, must not be {@code null}.
	 * @param offset
	 * 		The bitfield offset.
	 *
	 * @return The {@link BitFieldRoArgument} instance.
	 */
	public BitFieldRoArgument get(final BitFieldType bitFieldType, final int offset) {
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
	 * @return The {@link BitFieldRoArgument} instance.
	 */
	public BitFieldRoArgument get(final BitFieldType bitFieldType, final boolean bitOffset, final int offset) {
		commands.add(new Get(bitFieldType, bitOffset, offset));
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
	public String[] toArray() {
		String[] result = new String[]{};

		for(SubCommand subCommand : commands){
			result = Arrays.merge(result, subCommand.toArray());
		}

		return result;
	}

	@Override
	public byte[][] toBinaryArray() {
		byte[][] result = new byte[][]{};

		for(SubCommand subCommand : commands){
			result = Arrays.merge(result, subCommand.toBinaryArray());
		}

		return result;
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

	public interface SubCommand extends ArrayArgument<String> {

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
		public String[] toArray() {
			return new String[]{
					com.buession.redis.core.command.SubCommand.GET.name(),
					bitFieldType.toString(),
					bitOffset ? "#" + offset : Integer.toString(offset)
			};
		}

		@Override
		public byte[][] toBinaryArray() {
			return new byte[][]{
					com.buession.redis.core.command.SubCommand.GET.name().getBytes(StandardCharsets.US_ASCII),
					bitFieldType.toString().getBytes(StandardCharsets.US_ASCII),
					bitOffset ? ("#" + offset).getBytes(StandardCharsets.US_ASCII) : NumberUtils.int2bytes(offset)
			};
		}

		@Override
		public String toString() {
			final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

			builder.append(com.buession.redis.core.command.SubCommand.GET).append(bitFieldType)
					.append(bitOffset ? "#" + offset : Integer.toString(offset));

			return builder.build();
		}

	}

}
