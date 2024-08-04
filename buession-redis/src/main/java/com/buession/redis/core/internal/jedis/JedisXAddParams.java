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

import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.command.StreamCommands;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.params.XAddParams;

import java.util.Optional;

/**
 * Jedis {@link XAddParams} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisXAddParams extends XAddParams {

	public JedisXAddParams() {
		super();
	}

	public JedisXAddParams(final String id) {
		super();
		id(id);
	}

	public JedisXAddParams(final byte[] id) {
		super();
		id(id);
	}

	public JedisXAddParams(final StreamEntryID id) {
		super();
		id(id);
	}

	public JedisXAddParams(final StreamEntryId id) {
		this(JedisStreamEntryID.from(id));
	}

	public JedisXAddParams(final String id, final String minId) {
		this(id);
		minId(minId);
	}

	public JedisXAddParams(final byte[] id, final byte[] minId) {
		this(id);
		minId(SafeEncoder.encode(minId));
	}

	public JedisXAddParams(final StreamEntryID id, final String minId) {
		this(id);
		minId(minId);
	}

	public JedisXAddParams(final StreamEntryID id, final byte[] minId) {
		this(id);
		minId(SafeEncoder.encode(minId));
	}

	public JedisXAddParams(final StreamEntryId id, final String minId) {
		this(id);
		minId(minId);
	}

	public JedisXAddParams(final StreamEntryId id, final byte[] minId) {
		this(id);
		minId(SafeEncoder.encode(minId));
	}

	public JedisXAddParams(final long maxLen) {
		super();
		maxLen(maxLen);
	}

	public JedisXAddParams(final String id, final long maxLen) {
		this(id);
		maxLen(maxLen);
	}

	public JedisXAddParams(final byte[] id, final long maxLen) {
		this(id);
		maxLen(maxLen);
	}

	public JedisXAddParams(final StreamEntryID id, final long maxLen) {
		this(id);
		maxLen(maxLen);
	}

	public JedisXAddParams(final StreamEntryId id, final long maxLen) {
		this(id);
		maxLen(maxLen);
	}

	public JedisXAddParams(final String id, final String minId, final long maxLen) {
		this(id, minId);
		maxLen(maxLen);
	}

	public JedisXAddParams(final byte[] id, final byte[] minId, final long maxLen) {
		this(id, minId);
		maxLen(maxLen);
	}

	public JedisXAddParams(final StreamEntryID id, final String minId, final long maxLen) {
		this(id, minId);
		maxLen(maxLen);
	}

	public JedisXAddParams(final StreamEntryID id, final byte[] minId, final long maxLen) {
		this(id, minId);
		maxLen(maxLen);
	}

	public JedisXAddParams(final StreamEntryId id, final String minId, final long maxLen) {
		this(id, minId);
		maxLen(maxLen);
	}

	public JedisXAddParams(final StreamEntryId id, final byte[] minId, final long maxLen) {
		this(id, minId);
		maxLen(maxLen);
	}

	public JedisXAddParams(final String id, final String minId, final long maxLen, final boolean approximateTrimming,
						   final boolean exactTrimming, final boolean noMkStream, final long limit) {
		this(id, minId, maxLen);
		approximateTrimming(this, approximateTrimming);
		exactTrimming(this, exactTrimming);
		noMkStream(this, noMkStream);
		limit(limit);
	}

	public JedisXAddParams(final byte[] id, final byte[] minId, final long maxLen, final boolean approximateTrimming,
						   final boolean exactTrimming, final boolean noMkStream, final long limit) {
		this(id, minId, maxLen);
		approximateTrimming(this, approximateTrimming);
		exactTrimming(this, exactTrimming);
		noMkStream(this, noMkStream);
		limit(limit);
	}

	public JedisXAddParams(final StreamEntryID id, final String minId, final long maxLen,
						   final boolean approximateTrimming, final boolean exactTrimming, final boolean noMkStream,
						   final long limit) {
		this(id, minId, maxLen);
		approximateTrimming(this, approximateTrimming);
		exactTrimming(this, exactTrimming);
		noMkStream(this, noMkStream);
		limit(limit);
	}

	public JedisXAddParams(final StreamEntryID id, final byte[] minId, final long maxLen,
						   final boolean approximateTrimming, final boolean exactTrimming, final boolean noMkStream,
						   final long limit) {
		this(id, minId, maxLen);
		approximateTrimming(this, approximateTrimming);
		exactTrimming(this, exactTrimming);
		noMkStream(this, noMkStream);
		limit(limit);
	}

	public JedisXAddParams(final StreamEntryId id, final String minId, final long maxLen,
						   final boolean approximateTrimming, final boolean exactTrimming, final boolean noMkStream,
						   final long limit) {
		this(id, minId, maxLen);
		approximateTrimming(this, approximateTrimming);
		exactTrimming(this, exactTrimming);
		noMkStream(this, noMkStream);
		limit(limit);
	}

	public JedisXAddParams(final StreamEntryId id, final byte[] minId, final long maxLen,
						   final boolean approximateTrimming, final boolean exactTrimming, final boolean noMkStream,
						   final long limit) {
		this(id, minId, maxLen);
		approximateTrimming(this, approximateTrimming);
		exactTrimming(this, exactTrimming);
		noMkStream(this, noMkStream);
		limit(limit);
	}

	public static JedisXAddParams from(final StreamCommands.XAddArgument xAddArgument) {
		final JedisXAddParams xAddParams = new JedisXAddParams();

		if(xAddArgument != null){
			Optional.ofNullable(xAddArgument.getMinId()).ifPresent(xAddParams::minId);
			Optional.ofNullable(xAddArgument.getMaxLen()).ifPresent(xAddParams::maxLen);
			approximateTrimming(xAddParams, xAddArgument.isApproximateTrimming());
			exactTrimming(xAddParams, xAddArgument.isExactTrimming());
			noMkStream(xAddParams, xAddArgument.isNoMkStream());
			Optional.ofNullable(xAddArgument.getLimit()).ifPresent(xAddParams::limit);
		}

		return xAddParams;
	}

	private static void approximateTrimming(final JedisXAddParams xAddParams, final Boolean approximateTrimming) {
		if(Boolean.TRUE.equals(approximateTrimming)){
			xAddParams.approximateTrimming();
		}
	}

	private static void exactTrimming(final JedisXAddParams xAddParams, final Boolean exactTrimming) {
		if(Boolean.TRUE.equals(exactTrimming)){
			xAddParams.exactTrimming();
		}
	}

	private static void noMkStream(final JedisXAddParams xAddParams, final Boolean noMkStream) {
		if(Boolean.TRUE.equals(noMkStream)){
			xAddParams.noMkStream();
		}
	}

}
