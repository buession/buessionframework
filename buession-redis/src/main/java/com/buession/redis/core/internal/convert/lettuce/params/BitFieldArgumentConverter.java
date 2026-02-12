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
package com.buession.redis.core.internal.convert.lettuce.params;

import com.buession.core.converter.Converter;
import com.buession.redis.core.command.args.BitFieldArgument;
import io.lettuce.core.BitFieldArgs;
import org.springframework.lang.Nullable;

/**
 * {@link BitFieldArgument} 转换为 lettuce {@link BitFieldArgs}
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class BitFieldArgumentConverter implements Converter<BitFieldArgument, BitFieldArgs> {

	@Nullable
	@Override
	public BitFieldArgs convert(final BitFieldArgument source) {
		if(source == null){
			return null;
		}

		final BitFieldArgs bitFieldArgs = new BitFieldArgs();

		final BitFieldArgument.SetOp setOp = source.getSet();
		if(setOp != null){
			bitFieldArgs.set(setOp.getBitFieldType().isSigned() ? BitFieldArgs.signed(
							setOp.getBitFieldType().getBits()) : BitFieldArgs.unsigned(setOp.getBitFieldType().getBits()),
					setOp.getOffset(), setOp.getValue());
		}

		final BitFieldArgument.GetOp getOp = source.getGet();
		if(getOp != null){
			bitFieldArgs.get(getOp.getBitFieldType().isSigned() ? BitFieldArgs.signed(
							getOp.getBitFieldType().getBits()) : BitFieldArgs.unsigned(getOp.getBitFieldType().getBits()),
					getOp.getOffset());
		}

		final BitFieldArgument.IncrbyOp incrByOp = source.getIncrBy();
		if(incrByOp != null){
			bitFieldArgs.incrBy(incrByOp.getBitFieldType().isSigned() ? BitFieldArgs.signed(
							incrByOp.getBitFieldType().getBits()) : BitFieldArgs.unsigned(incrByOp.getBitFieldType().getBits()),
					incrByOp.getOffset(), incrByOp.getValue());
		}

		if(source.getOverflow() != null){
			switch(source.getOverflow()){
				case FAIL:
					bitFieldArgs.overflow(BitFieldArgs.OverflowType.FAIL);
					break;
				case SAT:
					bitFieldArgs.overflow(BitFieldArgs.OverflowType.SAT);
					break;
				case WRAP:
					bitFieldArgs.overflow(BitFieldArgs.OverflowType.WRAP);
					break;
				default:
					break;
			}
		}

		return bitFieldArgs;
	}

}
