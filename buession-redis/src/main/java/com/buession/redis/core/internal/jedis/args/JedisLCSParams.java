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
package com.buession.redis.core.internal.jedis.args;

import com.buession.redis.core.command.args.LcsArgument;
import redis.clients.jedis.params.LCSParams;

import java.util.Optional;

/**
 * Jedis {@link LCSParams} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisLCSParams extends LCSParams {

	/**
	 * 构造函数
	 */
	public JedisLCSParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param lcsArgument
	 *        {@link LcsArgument}
	 */
	public JedisLCSParams(final LcsArgument lcsArgument) {
		super();

		if(lcsArgument != null){
			if(Boolean.TRUE.equals(lcsArgument.getLen())){
				len();
			}
			if(Boolean.TRUE.equals(lcsArgument.getIdx())){
				idx();
			}
			Optional.ofNullable(lcsArgument.getMinMatchLen()).ifPresent(this::minMatchLen);
			if(Boolean.TRUE.equals(lcsArgument.getWithMatchLen())){
				withMatchLen();
			}
		}
	}

}
