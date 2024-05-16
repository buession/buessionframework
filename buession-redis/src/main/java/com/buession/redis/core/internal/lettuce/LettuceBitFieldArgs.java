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
package com.buession.redis.core.internal.lettuce;

import com.buession.redis.core.command.BitMapCommands;
import io.lettuce.core.BitFieldArgs;

/**
 * Lettuce {@link BitFieldArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceBitFieldArgs extends BitFieldArgs {

	public LettuceBitFieldArgs() {
		super();
	}

	public LettuceBitFieldArgs(final int set, final int get, final int incrBy) {
		super();
		set(set);
		get(set);
		incrBy(incrBy);
	}

	public LettuceBitFieldArgs(final int set, final int get, final int incrBy,
							   final BitMapCommands.BitFieldArgument.Overflow overflow) {
		this(set, get, incrBy);
		overflow(this, overflow);
	}

	public static LettuceBitFieldArgs from(final BitMapCommands.BitFieldArgument bitFieldArgument) {
		final LettuceBitFieldArgs bitFieldArgs = new LettuceBitFieldArgs();

		if(bitFieldArgument != null){
			final BitMapCommands.BitFieldArgument.Op setOp = bitFieldArgument.getSet();
			if(setOp != null){
				bitFieldArgs.set(
						setOp.getBitFieldType().isSigned() ? BitFieldArgs.signed(setOp.getBitFieldType().getBits()) :
								BitFieldArgs.unsigned(setOp.getBitFieldType().getBits()), setOp.getOffset(),
						setOp.getValue());
			}

			final BitMapCommands.BitFieldArgument.Op getOp = bitFieldArgument.getSet();
			if(getOp != null){
				bitFieldArgs.get(
						getOp.getBitFieldType().isSigned() ? BitFieldArgs.signed(getOp.getBitFieldType().getBits()) :
								BitFieldArgs.unsigned(getOp.getBitFieldType().getBits()), getOp.getOffset());
			}

			final BitMapCommands.BitFieldArgument.Op incrByOp = bitFieldArgument.getIncrBy();
			if(incrByOp != null){
				bitFieldArgs.incrBy(
						incrByOp.getBitFieldType().isSigned() ? BitFieldArgs.signed(
								incrByOp.getBitFieldType().getBits()) :
								BitFieldArgs.unsigned(incrByOp.getBitFieldType().getBits()), incrByOp.getOffset(),
						incrByOp.getValue());
			}

			overflow(bitFieldArgs, bitFieldArgument.getOverflow());
		}

		return bitFieldArgs;
	}

	private static void overflow(final BitFieldArgs bitFieldArgs,
								 final BitMapCommands.BitFieldArgument.Overflow overflow) {
		if(overflow != null){
			switch(overflow){
				case FAIL:
					bitFieldArgs.overflow(OverflowType.FAIL);
					break;
				case SAT:
					bitFieldArgs.overflow(OverflowType.SAT);
					break;
				case WRAP:
					bitFieldArgs.overflow(OverflowType.WRAP);
					break;
				default:
					break;
			}
		}

	}

}
