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

import com.buession.redis.core.command.args.LcsArgument;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.LcsArgs;

/**
 * Lettuce {@link LcsArgs} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceLcsArgs extends LcsArgs {

	/**
	 * 构造函数
	 */
	public LettuceLcsArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param key1
	 * 		-
	 * @param key2
	 * 		-
	 */
	public LettuceLcsArgs(final String key1, final String key2) {
		super();
		by(key1, key2);
	}

	/**
	 * 构造函数
	 *
	 * @param key1
	 * 		-
	 * @param key2
	 * 		-
	 */
	public LettuceLcsArgs(final byte[] key1, final byte[] key2) {
		super();
		by(SafeEncoder.encode(key1), SafeEncoder.encode(key2));
	}

	/**
	 * 构造函数
	 *
	 * @param key1
	 * 		-
	 * @param key2
	 * 		-
	 * @param lcsArgument
	 *        {@link LcsArgument}
	 */
	public LettuceLcsArgs(final String key1, final String key2, final LcsArgument lcsArgument) {
		this(key1, key2);
		lcsArgument(lcsArgument);
	}

	/**
	 * 构造函数
	 *
	 * @param key1
	 * 		-
	 * @param key2
	 * 		-
	 * @param lcsArgument
	 *        {@link LcsArgument}
	 */
	public LettuceLcsArgs(final byte[] key1, final byte[] key2, final LcsArgument lcsArgument) {
		this(key1, key2);
		lcsArgument(lcsArgument);
	}

	private void lcsArgument(final LcsArgument lcsArgument) {
		if(lcsArgument != null){
			if(Boolean.TRUE.equals(lcsArgument.getLen())){
				justLen();
			}
			if(Boolean.TRUE.equals(lcsArgument.getIdx())){
				withIdx();
			}
			if(lcsArgument.getMinMatchLen() != null){
				minMatchLen(lcsArgument.getMinMatchLen().intValue());
			}
			if(Boolean.TRUE.equals(lcsArgument.getWithMatchLen())){
				withMatchLen();
			}
		}
	}

}
