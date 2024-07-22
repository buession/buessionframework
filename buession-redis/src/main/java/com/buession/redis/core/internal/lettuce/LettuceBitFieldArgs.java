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

import com.buession.redis.core.command.args.BitFieldArgument;
import io.lettuce.core.BitFieldArgs;

import java.util.List;

/**
 * Lettuce {@link BitFieldArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceBitFieldArgs extends BitFieldArgs {

	/**
	 * 构造函数
	 */
	public LettuceBitFieldArgs() {
		super();
	}

	/**
	 * 从 {@link BitFieldArgument} 创建 {@link BitFieldArgs} 实例
	 *
	 * @param bitFieldArgument
	 *        {@link BitFieldArgument}
	 *
	 * @return {@link LettuceBitFieldArgs} 实例
	 */
	public static LettuceBitFieldArgs from(final BitFieldArgument bitFieldArgument) {
		final LettuceBitFieldArgs bitFieldArgs = new LettuceBitFieldArgs();

		if(bitFieldArgument != null){
			List<BitFieldArgument.SubCommand> commands = bitFieldArgument.getCommands();

			if(commands != null){
				for(BitFieldArgument.SubCommand command : commands){
					switch(command.getCommandType()){
						case GET:
							get(bitFieldArgs, (BitFieldArgument.Get) command);
							break;
						case SET:
							set(bitFieldArgs, (BitFieldArgument.Set) command);
							break;
						case INCRBY:
							incry(bitFieldArgs, (BitFieldArgument.IncrBy) command);
							break;
						case OVERFLOW:
							overflow(bitFieldArgs, (BitFieldArgument.Overflow) command);
							break;
						default:
							break;
					}
				}
			}
		}

		return bitFieldArgs;
	}

	private static void get(final BitFieldArgs bitFieldArgs, final BitFieldArgument.Get get) {
		if(get != null && get.getBitFieldType() != null){
			final BitFieldArgument.BitFieldType bitFieldType = get.getBitFieldType();
			bitFieldArgs.get(bitFieldType.isSigned() ? BitFieldArgs.signed(bitFieldType.getBits()) :
					BitFieldArgs.unsigned(bitFieldType.getBits()), get.getOffset());
		}
	}

	private static void set(final BitFieldArgs bitFieldArgs, final BitFieldArgument.Set set) {
		if(set != null && set.getBitFieldType() != null){
			final BitFieldArgument.BitFieldType bitFieldType = set.getBitFieldType();
			bitFieldArgs.set(bitFieldType.isSigned() ? BitFieldArgs.signed(bitFieldType.getBits()) :
					BitFieldArgs.unsigned(bitFieldType.getBits()), set.getOffset(), set.getValue());
		}
	}

	private static void incry(final BitFieldArgs bitFieldArgs, final BitFieldArgument.IncrBy incrBy) {
		if(incrBy != null && incrBy.getBitFieldType() != null){
			final BitFieldArgument.BitFieldType bitFieldType = incrBy.getBitFieldType();
			bitFieldArgs.set(bitFieldType.isSigned() ? BitFieldArgs.signed(bitFieldType.getBits()) :
					BitFieldArgs.unsigned(bitFieldType.getBits()), incrBy.getOffset(), incrBy.getValue());
		}
	}

	private static void overflow(final BitFieldArgs bitFieldArgs,
								 final BitFieldArgument.Overflow overflow) {
		if(overflow != null && overflow.getOverflowType() != null){
			switch(overflow.getOverflowType()){
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
