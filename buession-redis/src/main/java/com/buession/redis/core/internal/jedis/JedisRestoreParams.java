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
package com.buession.redis.core.internal.jedis;

import com.buession.redis.core.command.KeyCommands;
import redis.clients.jedis.params.RestoreParams;

import java.util.Objects;
import java.util.Optional;

/**
 * Jedis {@link RestoreParams} 扩展
 *
 * @author Yong.Teng
 * @since 2.4.0
 */
public class JedisRestoreParams extends RestoreParams {

	public JedisRestoreParams() {
	}

	public JedisRestoreParams(final boolean replace) {
		if(replace){
			replace();
		}
	}

	public JedisRestoreParams(final boolean replace, final boolean ttl) {
		this(replace);
		if(ttl){
			absTtl();
		}
	}

	public JedisRestoreParams(final boolean replace, final boolean ttl, final long idleTime, final long frequency) {
		this(replace, ttl);
		idleTime(idleTime);
		frequency(frequency);
		Optional.ofNullable(idleTime).ifPresent(this::idleTime);
		Optional.ofNullable(frequency).ifPresent(this::frequency);
	}

	public static JedisRestoreParams from(final KeyCommands.RestoreArgument argument) {
		final JedisRestoreParams restoreParams = new JedisRestoreParams();

		if(argument != null){
			if(Objects.equals(argument.isReplace(), Boolean.TRUE)){
				restoreParams.replace();
			}
			if(Objects.equals(argument.isAbsTtl(), Boolean.TRUE)){
				restoreParams.absTtl();
			}
			Optional.ofNullable(argument.getIdleTime()).ifPresent(restoreParams::idleTime);
			Optional.ofNullable(argument.getFrequency()).ifPresent(restoreParams::frequency);
		}

		return restoreParams;
	}

}
