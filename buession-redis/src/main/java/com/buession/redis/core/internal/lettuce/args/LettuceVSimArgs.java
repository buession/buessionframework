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

import com.buession.redis.core.command.args.vectorset.VSimArgument;
import io.lettuce.core.VSimArgs;

import java.util.Optional;

/**
 * Lettuce {@link VSimArgs} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceVSimArgs extends VSimArgs {

	/**
	 * 构造函数
	 */
	public LettuceVSimArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param vSimArgument
	 *        {@link VSimArgument}
	 */
	public LettuceVSimArgs(final VSimArgument vSimArgument) {
		super();

		if(vSimArgument != null){
			Optional.ofNullable(vSimArgument.getEpsilon()).ifPresent(this::epsilon);
			Optional.ofNullable(vSimArgument.getEf()).map(Integer::longValue).ifPresent(this::explorationFactor);
			Optional.ofNullable(vSimArgument.getFilter()).ifPresent(this::filter);
			Optional.ofNullable(vSimArgument.getFilterEf()).map(Integer::longValue).ifPresent(this::filterEfficiency);

			if(Boolean.TRUE.equals(vSimArgument.getTruth())){
				truth();
			}
			if(Boolean.TRUE.equals(vSimArgument.getNoThread())){
				noThread();
			}
		}
	}

	/**
	 * 构造函数
	 *
	 * @param vSimArgument
	 *        {@link VSimArgument}
	 * @param count
	 * 		数量
	 */
	public LettuceVSimArgs(final VSimArgument vSimArgument, final int count) {
		this(vSimArgument);
		count((long) count);
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		数量
	 */
	public LettuceVSimArgs(final int count) {
		super();
		count((long) count);
	}

}
