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
package com.buession.redis.core.command.args.bitmap;

import com.buession.redis.core.Keyword;
import com.buession.redis.utils.ArgStringBuilder;

/**
 * BITFIELD 命令参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface BitFieldArgument {

	interface Op extends BitFieldArgument {

	}

	interface GetOp extends Op {

	}

	class DefaultGet implements GetOp {

		private final String commandType;

		private final BitFieldEncoding encoding;

		private final boolean bitOffset;

		private final int offset;

		private DefaultGet(final BitFieldEncoding encoding, final boolean bitOffset, final int offset) {
			this.commandType = Keyword.Common.GET.name();
			this.encoding = encoding;
			this.bitOffset = bitOffset;
			this.offset = offset;
		}

		public BitFieldEncoding getEncoding() {
			return encoding;
		}

		public boolean isBitOffset() {
			return bitOffset;
		}

		public int getOffset() {
			return offset;
		}

		@Override
		public String toString() {
			final ArgStringBuilder builder = ArgStringBuilder.create().add(commandType, getEncoding());

			if(isBitOffset()){
				builder.append(" #" + getOffset());
			}else{
				builder.append(" " + getOffset());
			}

			return builder.build();
		}

	}

	enum Overflow implements GetOp, Keyword {
		WRAP,

		SAT,

		FAIL;

		public String getValue() {
			return name();
		}

		@Override
		public String toString() {
			return "OVERFLOW " + getValue();
		}

	}

	abstract class BaseSetOp implements SetOp {

		private final String commandType;

		private final BitFieldEncoding encoding;

		private final boolean bitOffset;

		private final int offset;

		private final long value;

		private BaseSetOp(final String commandType, final BitFieldEncoding encoding, final boolean bitOffset,
						  final int offset, final long value) {
			this.commandType = commandType;
			this.encoding = encoding;
			this.bitOffset = bitOffset;
			this.offset = offset;
			this.value = value;
		}

		public BitFieldEncoding getEncoding() {
			return encoding;
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
			final ArgStringBuilder builder = ArgStringBuilder.create().add(commandType, getEncoding());

			if(isBitOffset()){
				builder.append(" #" + getOffset());
			}else{
				builder.append(" " + getOffset());
			}

			builder.append(getValue());

			return builder.build();
		}

	}

	interface SetOp extends Op {

	}

	class DefaultSet extends BaseSetOp {

		public DefaultSet(final BitFieldEncoding encoding, final boolean bitOffset, final int offset,
						  final long value) {
			super(Keyword.Common.SET.name(), encoding, bitOffset, offset, value);
		}

	}

	class IncrBy extends BaseSetOp {

		public IncrBy(final BitFieldEncoding encoding, final boolean bitOffset, final int offset, final long value) {
			super("INCRBY", encoding, bitOffset, offset, value);
		}

	}

}
