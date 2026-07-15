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
package com.buession.redis.core.internal.lettuce.args;

import com.buession.redis.core.Quantization;
import com.buession.redis.core.command.args.vectorset.VAddArgument;
import io.lettuce.core.VAddArgs;
import io.lettuce.core.vector.QuantizationType;

import java.util.Optional;

/**
 * Lettuce {@link VAddArgs} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceVAddArgs extends VAddArgs {

	/**
	 * 构造函数
	 */
	public LettuceVAddArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param vAddArgument
	 *        {@link VAddArgument}
	 */
	public LettuceVAddArgs(final VAddArgument vAddArgument) {
		super();

		if(vAddArgument != null){
			if(Boolean.TRUE.equals(vAddArgument.getCas())){
				checkAndSet(true);
			}

			if(vAddArgument.getQuantization() == Quantization.BIN){
				quantizationType(QuantizationType.BINARY);
			}else if(vAddArgument.getQuantization() == Quantization.NOQUANT){
				quantizationType(QuantizationType.NO_QUANTIZATION);
			}else if(vAddArgument.getQuantization() == Quantization.Q8){
				quantizationType(QuantizationType.Q8);
			}

			Optional.ofNullable(vAddArgument.getSetattr()).ifPresent(this::attributes);
			Optional.ofNullable(vAddArgument.getEf()).map(Integer::longValue).ifPresent(this::explorationFactor);
			Optional.ofNullable(vAddArgument.getM()).map(Integer::longValue).ifPresent(this::maxNodes);
		}
	}

}
