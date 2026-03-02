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
package com.buession.redis.core.internal.convert.jedis.params;

import com.buession.core.converter.Converter;
import com.buession.redis.core.command.args.RestoreArgument;
import redis.clients.jedis.params.RestoreParams;

import java.util.Optional;

/**
 * {@link RestoreArgument} 转换为 jedis {@link RestoreParams}
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class RestoreArgumentConverter implements Converter<RestoreArgument, RestoreParams> {

	@Override
	public RestoreParams convert(final RestoreArgument source) {
		if(source == null){
			return null;
		}

		final RestoreParams restoreParams = new RestoreParams();

		if(Boolean.TRUE.equals(source.getReplace())){
			restoreParams.replace();
		}
		if(Boolean.TRUE.equals(source.getAbsTtl())){
			restoreParams.absTtl();
		}
		Optional.ofNullable(source.getIdleTime()).ifPresent(restoreParams::idleTime);
		Optional.ofNullable(source.getFrequency()).ifPresent(restoreParams::frequency);

		return restoreParams;
	}

}
