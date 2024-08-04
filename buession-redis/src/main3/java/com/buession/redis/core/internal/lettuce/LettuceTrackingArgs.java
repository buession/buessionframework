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

import com.buession.redis.core.command.args.ClientTracking;
import io.lettuce.core.TrackingArgs;

import java.util.Optional;

/**
 * Lettuce {@link TrackingArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceTrackingArgs extends TrackingArgs {

	public LettuceTrackingArgs() {
		super();
	}

	public LettuceTrackingArgs(final boolean enabled) {
		super();
		enabled(enabled);
	}

	public LettuceTrackingArgs(final boolean enabled, final long redirect) {
		this(enabled);
		redirect(redirect);
	}

	public LettuceTrackingArgs(final boolean enabled, final String[] prefixes) {
		this(enabled);
		prefixes(prefixes);
	}

	public LettuceTrackingArgs(final boolean enabled, final long redirect, final String[] prefixes) {
		this(enabled, redirect);
		prefixes(prefixes);
	}

	public LettuceTrackingArgs(final boolean enabled, final long redirect, final String[] prefixes, final boolean bcast,
							   final boolean optin, final boolean optout, final boolean noloop) {
		this(enabled, redirect, prefixes);
		bcast(this, bcast);
		optin(this, optin);
		optout(this, optout);
		noloop(this, noloop);
	}

	public static LettuceTrackingArgs from(final ClientTracking clientTracking) {
		final LettuceTrackingArgs trackArgs = new LettuceTrackingArgs();

		Optional.ofNullable(clientTracking.isEnabled()).ifPresent(trackArgs::enabled);
		Optional.ofNullable(clientTracking.getRedirect()).ifPresent(trackArgs::redirect);
		Optional.ofNullable(clientTracking.getPrefixes()).ifPresent(trackArgs::prefixes);
		bcast(trackArgs, clientTracking.isBcast());
		optin(trackArgs, clientTracking.isOptin());
		optout(trackArgs, clientTracking.isOptout());
		noloop(trackArgs, clientTracking.isNoloop());

		return trackArgs;
	}

	private static void bcast(final TrackingArgs trackingArgs, final Boolean bcast) {
		if(Boolean.TRUE.equals(bcast)){
			trackingArgs.bcast();
		}
	}

	private static void optin(final TrackingArgs trackingArgs, final Boolean optin) {
		if(Boolean.TRUE.equals(optin)){
			trackingArgs.optin();
		}
	}

	private static void optout(final TrackingArgs trackingArgs, final Boolean optout) {
		if(Boolean.TRUE.equals(optout)){
			trackingArgs.optout();
		}
	}

	private static void noloop(final TrackingArgs trackingArgs, final Boolean noloop) {
		if(Boolean.TRUE.equals(noloop)){
			trackingArgs.noloop();
		}
	}

}
