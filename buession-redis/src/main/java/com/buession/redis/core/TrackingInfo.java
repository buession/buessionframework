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
package com.buession.redis.core;

import com.buession.redis.utils.ObjectStringBuilder;

import java.util.List;
import java.util.Set;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public record TrackingInfo(Set<TrackingFlag> flags, Long redirect, List<String> prefixes) {

	@Override
	public String toString() {
		return ObjectStringBuilder.create()
				.add("flags", flags)
				.add("redirect", redirect)
				.add("prefixes", prefixes)
				.build();
	}

	public enum TrackingFlag implements Keyword {

		/**
		 * The connection isn't using server assisted client side caching.
		 */
		OFF,
		/**
		 * Server assisted client side caching is enabled for the connection.
		 */
		ON,
		/**
		 * The client uses broadcasting mode.
		 */
		BCAST,
		/**
		 * The client does not cache keys by default.
		 */
		OPTIN,
		/**
		 * The client caches keys by default.
		 */
		OPTOUT,
		/**
		 * The next command will cache keys (exists only together with optin).
		 */
		CACHING_YES,
		/**
		 * The next command won't cache keys (exists only together with optout).
		 */
		CACHING_NO,
		/**
		 * The client isn't notified about keys modified by itself.
		 */
		NOLOOP,
		/**
		 * The client ID used for redirection isn't valid anymore.
		 */
		BROKEN_REDIRECT;

		@Override
		public String getValue() {
			return name();
		}

		@Override
		public String toString() {
			return getValue();
		}

	}

}
