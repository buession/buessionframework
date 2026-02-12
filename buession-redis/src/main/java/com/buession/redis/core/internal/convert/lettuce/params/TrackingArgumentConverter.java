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
import com.buession.redis.core.command.args.TrackingArgument;
import io.lettuce.core.TrackingArgs;
import org.springframework.lang.Nullable;

import java.util.Optional;

/**
 * {@link TrackingArgument} 转换为 lettuce {@link TrackingArgs}
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class TrackingArgumentConverter implements Converter<TrackingArgument, TrackingArgs> {

	@Nullable
	@Override
	public TrackingArgs convert(final TrackingArgument source) {
		if(source == null){
			return null;
		}

		final TrackingArgs trackingArgs = new TrackingArgs();

		Optional.ofNullable(source.getRedirect()).ifPresent(trackingArgs::redirect);
		Optional.ofNullable(source.getPrefixes()).ifPresent(trackingArgs::prefixes);
		if(Boolean.TRUE.equals(source.getBcast())){
			trackingArgs.bcast();
		}
		if(Boolean.TRUE.equals(source.getOptin())){
			trackingArgs.optin();
		}
		if(Boolean.TRUE.equals(source.getOptout())){
			trackingArgs.optout();
		}
		if(Boolean.TRUE.equals(source.getNoloop())){
			trackingArgs.noloop();
		}

		return trackingArgs;
	}

}
