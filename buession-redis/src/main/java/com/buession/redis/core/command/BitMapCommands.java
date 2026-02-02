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
package com.buession.redis.core.command;

import com.buession.redis.core.BitCountOption;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.Keyword;
import com.buession.redis.utils.ArgStringBuilder;

import java.util.List;

/**
 * BitMap 命令
 *
 * <p>详情说明 <a href="https://redis.io/commands/?group=bitmap" target="_blank">https://redis.io/commands/?group=bitmap</a></p>
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface BitMapCommands extends RedisCommands {

	/**
	 * 计算给定字符串中，被设置为 1 的比特位的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/bitcount.html" target="_blank">http://redisdoc.com/bitmap/bitcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 被设置为 1 的位的数量
	 */
	Long bitCount(final String key);

	/**
	 * 计算给定字符串中，被设置为 1 的比特位的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/bitcount.html" target="_blank">http://redisdoc.com/bitmap/bitcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 被设置为 1 的位的数量
	 */
	Long bitCount(final byte[] key);

	/**
	 * 计算给定字符串中，被设置为 1 的比特位的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/bitcount.html" target="_blank">http://redisdoc.com/bitmap/bitcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 被设置为 1 的位的数量
	 */
	Long bitCount(final String key, final long start, final long end);

	/**
	 * 计算给定字符串中，被设置为 1 的比特位的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/bitcount.html" target="_blank">http://redisdoc.com/bitmap/bitcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 被设置为 1 的位的数量
	 */
	Long bitCount(final byte[] key, final long start, final long end);

	/**
	 * 计算给定字符串中，被设置为 1 的比特位的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/bitcount.html" target="_blank">http://redisdoc.com/bitmap/bitcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param option
	 *        {@link BitCountOption}
	 *
	 * @return 被设置为 1 的位的数量
	 */
	Long bitCount(final String key, final long start, final long end, final BitCountOption option);

	/**
	 * 计算给定字符串中，被设置为 1 的比特位的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/bitcount.html" target="_blank">http://redisdoc.com/bitmap/bitcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param option
	 *        {@link BitCountOption}
	 *
	 * @return 被设置为 1 的位的数量
	 */
	Long bitCount(final byte[] key, final long start, final long end, final BitCountOption option);

	/**
	 * 可以将一个 Redis 字符串看作是一个由二进制位组成的数组，并对这个数组中储存的长度不同的整数进行访问；
	 * 可以在一次调用中同时对多个位范围进行操作，
	 * 它接受一系列待执行的操作作为参数，并返回一个数组作为回复，数组中的每个元素就是对应操作的执行结果
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/bitfield.html" target="_blank">http://redisdoc.com/bitmap/bitfield.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		命令参数
	 *
	 * @return 返回值是一个数组，数组中的每个元素对应一个被执行的子命令
	 *
	 * @since 2.3.0
	 */
	List<Long> bitField(final String key, final BitFieldArgument argument);

	/**
	 * 可以将一个 Redis 字符串看作是一个由二进制位组成的数组，并对这个数组中储存的长度不同的整数进行访问；
	 * 可以在一次调用中同时对多个位范围进行操作，
	 * 它接受一系列待执行的操作作为参数，并返回一个数组作为回复，数组中的每个元素就是对应操作的执行结果
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/bitfield.html" target="_blank">http://redisdoc.com/bitmap/bitfield.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		命令参数
	 *
	 * @return 返回值是一个数组，数组中的每个元素对应一个被执行的子命令
	 *
	 * @since 2.3.0
	 */
	List<Long> bitField(final byte[] key, final BitFieldArgument argument);

	/**
	 * 可以将一个 Redis 字符串看作是一个由二进制位组成的数组，并对这个数组中储存的长度不同的整数进行访问；
	 * 可以在一次调用中同时对多个位范围进行操作，
	 * 它接受一系列待执行的操作作为参数，并返回一个数组作为回复，数组中的每个元素就是对应操作的执行结果
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/bitfield.html" target="_blank">http://redisdoc.com/bitmap/bitfield.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		命令参数
	 *
	 * @return 返回值是一个数组，数组中的每个元素对应一个被执行的子命令
	 */
	List<Long> bitFieldRo(final String key, final BitFieldRoArgument argument);

	/**
	 * 可以将一个 Redis 字符串看作是一个由二进制位组成的数组，并对这个数组中储存的长度不同的整数进行访问；
	 * 可以在一次调用中同时对多个位范围进行操作，
	 * 它接受一系列待执行的操作作为参数，并返回一个数组作为回复，数组中的每个元素就是对应操作的执行结果
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/bitfield.html" target="_blank">http://redisdoc.com/bitmap/bitfield.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param argument
	 * 		命令参数
	 *
	 * @return 返回值是一个数组，数组中的每个元素对应一个被执行的子命令
	 */
	List<Long> bitFieldRo(final byte[] key, final BitFieldRoArgument argument);

	/**
	 * 对一个或多个保存二进制位的字符串 key 进行位元操作，并将结果保存到 destKey 上，
	 * 除了 Operation.NOT 操作之外，其他操作都可以接受一个或多个 key 作为输入
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/bitop.html" target="_blank">http://redisdoc.com/bitmap/bitop.html</a></p>
	 *
	 * @param operation
	 * 		运算操作
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		Keys
	 *
	 * @return 保存到 destKey 的字符串的长度
	 */
	Long bitOp(final BitOperation operation, final String destKey, final String... keys);

	/**
	 * 对一个或多个保存二进制位的字符串 key 进行位元操作，并将结果保存到 destKey 上，
	 * 除了 Operation.NOT 操作之外，其他操作都可以接受一个或多个 key 作为输入
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/bitop.html" target="_blank">http://redisdoc.com/bitmap/bitop.html</a></p>
	 *
	 * @param operation
	 * 		运算操作
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		Keys
	 *
	 * @return 保存到 destKey 的字符串的长度
	 */
	Long bitOp(final BitOperation operation, final byte[] destKey, final byte[]... keys);

	/**
	 * 获取位图中第一个值为 bit 的二进制位的位置
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/bitpos.html" target="_blank">http://redisdoc.com/bitmap/bitpos.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		位
	 *
	 * @return 位图中第一个值为 bit 的二进制位的位置
	 */
	Long bitPos(final String key, final boolean value);

	/**
	 * 获取位图中第一个值为 bit 的二进制位的位置
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/bitpos.html" target="_blank">http://redisdoc.com/bitmap/bitpos.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		位
	 *
	 * @return 位图中第一个值为 bit 的二进制位的位置
	 */
	Long bitPos(final byte[] key, final boolean value);

	/**
	 * 获取位图中第一个值为 bit 的二进制位的位置
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/bitpos.html" target="_blank">http://redisdoc.com/bitmap/bitpos.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		位
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 位图中第一个值为 bit 的二进制位的位置
	 */
	Long bitPos(final String key, final boolean value, final long start, final long end);

	/**
	 * 获取位图中第一个值为 bit 的二进制位的位置
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/bitpos.html" target="_blank">http://redisdoc.com/bitmap/bitpos.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param value
	 * 		位
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return 位图中第一个值为 bit 的二进制位的位置
	 */
	Long bitPos(final byte[] key, final boolean value, final long start, final long end);

	/**
	 * 获取 key 指定偏移量上的位
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/getbit.html" target="_blank">http://redisdoc.com/bitmap/getbit.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param offset
	 * 		偏移量
	 *
	 * @return Key 指定偏移量上的位
	 */
	Boolean getBit(final String key, final long offset);

	/**
	 * 获取 key 指定偏移量上的位
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/getbit.html" target="_blank">http://redisdoc.com/bitmap/getbit.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param offset
	 * 		偏移量
	 *
	 * @return Key 指定偏移量上的位
	 */
	Boolean getBit(final byte[] key, final long offset);

	/**
	 * 对 key 所储存的字符串值，设置或清除指定偏移量上的位；位的设置或清除取决于 value 参数
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/setbit.html" target="_blank">http://redisdoc.com/bitmap/setbit.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param offset
	 * 		偏移量
	 * @param value
	 * 		true 设置位，false 清除位
	 *
	 * @return 偏移量原来储存的位
	 */
	Boolean setBit(final String key, final long offset, final boolean value);

	/**
	 * 对 key 所储存的字符串值，设置或清除指定偏移量上的位；位的设置或清除取决于 value 参数
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/bitmap/setbit.html" target="_blank">http://redisdoc.com/bitmap/setbit.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param offset
	 * 		偏移量
	 * @param value
	 * 		true 设置位，false 清除位
	 *
	 * @return 偏移量原来储存的位
	 */
	Boolean setBit(final byte[] key, final long offset, final boolean value);

	/**
	 * BITFIELD 命令参数
	 *
	 * @author Yong.Teng
	 * @since 2.3.0
	 */
	final class BitFieldArgument {

		/**
		 * 写操作
		 */
		private Op set;

		/**
		 * 获取操作
		 */
		private Op get;

		/**
		 * 加操作
		 */
		private Op incrBy;

		/**
		 * 数值溢出行为
		 */
		private Overflow overflow;

		private BitFieldArgument() {

		}

		/**
		 * 返回写操作
		 *
		 * @return 写操作
		 */
		public Op getSet() {
			return set;
		}

		/**
		 * 返回获取操作
		 *
		 * @return 获取操作
		 */
		public Op getGet() {
			return get;
		}

		/**
		 * 返回加操作
		 *
		 * @return 加操作
		 */
		public Op getIncrBy() {
			return incrBy;
		}

		/**
		 * 返回数值溢出行为
		 *
		 * @return 数值溢出行为
		 */
		public Overflow getOverflow() {
			return overflow;
		}

		@Override
		public String toString() {
			final ArgStringBuilder builder = ArgStringBuilder.create();

			builder.append(set);
			builder.append(get);
			builder.append(incrBy);
			builder.append(overflow);

			return builder.toString();
		}

		public static class Builder extends BaseArgumentBuilder<BitFieldArgument> {

			private Builder() {
				super(new BitFieldArgument());
			}

			/**
			 * 创建 {@link BitFieldArgument} 狗坚器 {@link Builder}
			 *
			 * @return {@link BitFieldArgument} 狗坚器 {@link Builder}
			 */
			public static Builder create() {
				return new Builder();
			}

			public Builder set(BitFieldType bitFieldType, long value) {
				return set(bitFieldType, 0, value);
			}

			public Builder set(BitFieldType bitFieldType, int offset, long value) {
				return set(bitFieldType, false, offset, value);
			}

			public Builder set(BitFieldType bitFieldType, boolean bitOffset, int offset, long value) {
				arguament.set = new Op("SET", bitFieldType, bitOffset, offset, value);
				return this;
			}

			public Builder get(BitFieldType bitFieldType, long value) {
				return get(bitFieldType, 0, value);
			}

			public Builder get(BitFieldType bitFieldType, int offset, long value) {
				return get(bitFieldType, false, offset, value);
			}

			public Builder get(BitFieldType bitFieldType, boolean bitOffset, int offset, long value) {
				arguament.get = new Op("GET", bitFieldType, bitOffset, offset, value);
				return this;
			}

			public Builder incrBy(BitFieldType bitFieldType, long value) {
				return incrBy(bitFieldType, 0, value);
			}

			public Builder incrBy(BitFieldType bitFieldType, int offset, long value) {
				return incrBy(bitFieldType, false, offset, value);
			}

			public Builder incrBy(BitFieldType bitFieldType, boolean bitOffset, int offset, long value) {
				arguament.incrBy = new Op("INCRBY", bitFieldType, bitOffset, offset, value);
				return this;
			}

			public Builder overflow(Overflow overflow) {
				arguament.overflow = overflow;
				return this;
			}

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

	/**
	 * BITFIELD_RO 命令参数
	 *
	 * @author Yong.Teng
	 * @since 4.0.0
	 */
	final class BitFieldRoArgument {

		private Op set;

		private Op get;

		private BitFieldRoArgument() {

		}

		public Op getSet() {
			return set;
		}

		public Op getGet() {
			return get;
		}

		@Override
		public String toString() {
			final ArgStringBuilder builder = ArgStringBuilder.create();

			builder.append(set);
			builder.append(get);

			return builder.toString();
		}

		public static class Builder extends BaseArgumentBuilder<BitFieldRoArgument> {

			private Builder() {
				super(new BitFieldRoArgument());
			}

			public static Builder create() {
				return new Builder();
			}

			public Builder set(BitFieldType bitFieldType, long value) {
				return set(bitFieldType, 0, value);
			}

			public Builder set(BitFieldType bitFieldType, int offset, long value) {
				return set(bitFieldType, false, offset, value);
			}

			public Builder set(BitFieldType bitFieldType, boolean bitOffset, int offset, long value) {
				arguament.set = new Op("SET", bitFieldType, bitOffset, offset, value);
				return this;
			}

			public Builder get(BitFieldType bitFieldType, long value) {
				return get(bitFieldType, 0, value);
			}

			public Builder get(BitFieldType bitFieldType, int offset, long value) {
				return get(bitFieldType, false, offset, value);
			}

			public Builder get(BitFieldType bitFieldType, boolean bitOffset, int offset, long value) {
				arguament.get = new Op("GET", bitFieldType, bitOffset, offset, value);
				return this;
			}

		}

	}

	class BitFieldType {

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

	final class Op {

		private final String commandType;

		private final BitFieldType bitFieldType;

		private final boolean bitOffset;

		private final int offset;

		private final long value;

		private Op(final String commandType, final BitFieldType bitFieldType, final boolean bitOffset,
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

}
