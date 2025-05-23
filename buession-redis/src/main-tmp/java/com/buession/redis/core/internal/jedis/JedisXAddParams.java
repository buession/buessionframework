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
import com.buession.redis.core.command.args.ApproximateExactTrimming;
import com.buession.redis.core.command.args.MaxLenMinId;
import com.buession.redis.core.command.args.XAddArgument;
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

	/**
	 * 构造函数
	 */
	public JedisXAddParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 */
	public JedisXAddParams(final String id) {
		super();
		id(id);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 */
	public JedisXAddParams(final byte[] id) {
		super();
		id(id);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 */
	public JedisXAddParams(final StreamEntryID id) {
		super();
		id(id);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 */
	public JedisXAddParams(final StreamEntryId id) {
		this(JedisStreamEntryID.from(id));
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 */
	public JedisXAddParams(final String id, final MaxLenMinId<?> maxLenMinId) {
		this(id);
		maxLenMinId(this, maxLenMinId);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 */
	public JedisXAddParams(final byte[] id, final MaxLenMinId<?> maxLenMinId) {
		this(id);
		maxLenMinId(this, maxLenMinId);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryID id, final MaxLenMinId<?> maxLenMinId) {
		this(id);
		maxLenMinId(this, maxLenMinId);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryId id, final MaxLenMinId<?> maxLenMinId) {
		this(id);
		maxLenMinId(this, maxLenMinId);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param noMkStream
	 * 		-
	 */
	public JedisXAddParams(final String id, final boolean noMkStream) {
		this(id);
		noMkStream(this, noMkStream);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param noMkStream
	 * 		-
	 */
	public JedisXAddParams(final byte[] id, final boolean noMkStream) {
		this(id);
		noMkStream(this, noMkStream);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param noMkStream
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryID id, final boolean noMkStream) {
		this(id);
		noMkStream(this, noMkStream);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param noMkStream
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryId id, final boolean noMkStream) {
		this(id);
		noMkStream(this, noMkStream);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final String id, final long limit) {
		this(id);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final byte[] id, final long limit) {
		this(id);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryID id, final long limit) {
		this(id);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryId id, final long limit) {
		this(id);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param noMkStream
	 * 		-
	 */
	public JedisXAddParams(final String id, final MaxLenMinId<?> maxLenMinId, final boolean noMkStream) {
		this(id, maxLenMinId);
		noMkStream(this, noMkStream);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param noMkStream
	 * 		-
	 */
	public JedisXAddParams(final byte[] id, final MaxLenMinId<?> maxLenMinId, final boolean noMkStream) {
		this(id, maxLenMinId);
		noMkStream(this, noMkStream);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param noMkStream
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryID id, final MaxLenMinId<?> maxLenMinId, final boolean noMkStream) {
		this(id, maxLenMinId);
		noMkStream(this, noMkStream);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param noMkStream
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryId id, final MaxLenMinId<?> maxLenMinId, final boolean noMkStream) {
		this(id, maxLenMinId);
		noMkStream(this, noMkStream);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final String id, final MaxLenMinId<?> maxLenMinId, final long limit) {
		this(id, maxLenMinId);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final byte[] id, final MaxLenMinId<?> maxLenMinId, final long limit) {
		this(id, maxLenMinId);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryID id, final MaxLenMinId<?> maxLenMinId, final long limit) {
		this(id, maxLenMinId);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryId id, final MaxLenMinId<?> maxLenMinId, final long limit) {
		this(id, maxLenMinId);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param noMkStream
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final String id, final MaxLenMinId<?> maxLenMinId, final boolean noMkStream,
						   final long limit) {
		this(id, maxLenMinId, noMkStream);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param noMkStream
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final byte[] id, final MaxLenMinId<?> maxLenMinId, final boolean noMkStream,
						   final long limit) {
		this(id, maxLenMinId, noMkStream);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param noMkStream
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryID id, final MaxLenMinId<?> maxLenMinId, final boolean noMkStream,
						   final long limit) {
		this(id, maxLenMinId, noMkStream);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param noMkStream
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryId id, final MaxLenMinId<?> maxLenMinId, final boolean noMkStream,
						   final long limit) {
		this(id, maxLenMinId, noMkStream);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 */
	public JedisXAddParams(final String id, final MaxLenMinId<?> maxLenMinId,
						   final ApproximateExactTrimming approximateExactTrimming) {
		this(id);
		maxLenMinId(this, maxLenMinId, approximateExactTrimming);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 */
	public JedisXAddParams(final byte[] id, final MaxLenMinId<?> maxLenMinId,
						   final ApproximateExactTrimming approximateExactTrimming) {
		this(id);
		maxLenMinId(this, maxLenMinId, approximateExactTrimming);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryID id, final MaxLenMinId<?> maxLenMinId,
						   final ApproximateExactTrimming approximateExactTrimming) {
		this(id);
		maxLenMinId(this, maxLenMinId, approximateExactTrimming);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryId id, final MaxLenMinId<?> maxLenMinId,
						   final ApproximateExactTrimming approximateExactTrimming) {
		this(id);
		maxLenMinId(this, maxLenMinId, approximateExactTrimming);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 * @param noMkStream
	 * 		-
	 */
	public JedisXAddParams(final String id, final MaxLenMinId<?> maxLenMinId,
						   final ApproximateExactTrimming approximateExactTrimming, final boolean noMkStream) {
		this(id, maxLenMinId, approximateExactTrimming);
		noMkStream(this, noMkStream);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 * @param noMkStream
	 * 		-
	 */
	public JedisXAddParams(final byte[] id, final MaxLenMinId<?> maxLenMinId,
						   final ApproximateExactTrimming approximateExactTrimming, final boolean noMkStream) {
		this(id, maxLenMinId, approximateExactTrimming);
		noMkStream(this, noMkStream);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 * @param noMkStream
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryID id, final MaxLenMinId<?> maxLenMinId,
						   final ApproximateExactTrimming approximateExactTrimming, final boolean noMkStream) {
		this(id, maxLenMinId, approximateExactTrimming);
		noMkStream(this, noMkStream);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 * @param noMkStream
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryId id, final MaxLenMinId<?> maxLenMinId,
						   final ApproximateExactTrimming approximateExactTrimming, final boolean noMkStream) {
		this(id, maxLenMinId, approximateExactTrimming);
		noMkStream(this, noMkStream);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final String id, final MaxLenMinId<?> maxLenMinId,
						   final ApproximateExactTrimming approximateExactTrimming, final long limit) {
		this(id, maxLenMinId, approximateExactTrimming);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final byte[] id, final MaxLenMinId<?> maxLenMinId,
						   final ApproximateExactTrimming approximateExactTrimming, final long limit) {
		this(id, maxLenMinId, approximateExactTrimming);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryID id, final MaxLenMinId<?> maxLenMinId,
						   final ApproximateExactTrimming approximateExactTrimming, final long limit) {
		this(id, maxLenMinId, approximateExactTrimming);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryId id, final MaxLenMinId<?> maxLenMinId,
						   final ApproximateExactTrimming approximateExactTrimming, final long limit) {
		this(id, maxLenMinId, approximateExactTrimming);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 * @param noMkStream
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final String id, final MaxLenMinId<?> maxLenMinId,
						   final ApproximateExactTrimming approximateExactTrimming, final boolean noMkStream,
						   final long limit) {
		this(id, maxLenMinId, approximateExactTrimming, noMkStream);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 * @param noMkStream
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final byte[] id, final MaxLenMinId<?> maxLenMinId,
						   final ApproximateExactTrimming approximateExactTrimming, final boolean noMkStream,
						   final long limit) {
		this(id, maxLenMinId, approximateExactTrimming, noMkStream);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 * @param noMkStream
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryID id, final MaxLenMinId<?> maxLenMinId,
						   final ApproximateExactTrimming approximateExactTrimming, final boolean noMkStream,
						   final long limit) {
		this(id, maxLenMinId, approximateExactTrimming, noMkStream);
		limit(limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 * @param approximateExactTrimming
	 * 		-
	 * @param noMkStream
	 * 		-
	 * @param limit
	 * 		-
	 */
	public JedisXAddParams(final StreamEntryId id, final MaxLenMinId<?> maxLenMinId,
						   final ApproximateExactTrimming approximateExactTrimming, final boolean noMkStream,
						   final long limit) {
		this(id, maxLenMinId, approximateExactTrimming, noMkStream);
		Optional.ofNullable(limit).ifPresent(this::limit);
	}

	public JedisXAddParams id(final StreamEntryId id) {
		if(id != null){
			id(JedisStreamEntryID.from(id));
		}

		return this;
	}

	/**
	 * 从 {@link XAddArgument} 创建 {@link XAddParams} 实例
	 *
	 * @param xAddArgument
	 *        {@link XAddArgument}
	 *
	 * @return {@link JedisXAddParams} 实例
	 */
	public static JedisXAddParams from(final XAddArgument xAddArgument) {
		final JedisXAddParams xAddParams = new JedisXAddParams();

		maxLenMinId(xAddParams, xAddArgument.getMaxLenMinId(), xAddArgument.getApproximateExactTrimming());
		noMkStream(xAddParams, xAddArgument.isNoMkStream());
		Optional.ofNullable(xAddArgument.getLimit()).ifPresent(xAddParams::limit);

		return xAddParams;
	}

	private static void maxLenMinId(final JedisXAddParams xAddParams, final MaxLenMinId<?> maxLenMinId) {
		if(maxLenMinId != null){
			if(maxLenMinId instanceof MaxLenMinId.MaxLen){
				xAddParams.maxLen(((MaxLenMinId.MaxLen) maxLenMinId).getValue());
			}else if(maxLenMinId instanceof MaxLenMinId.MinId){
				xAddParams.minId(((MaxLenMinId.MinId) maxLenMinId).getValue().toString());
			}
		}
	}

	private static void maxLenMinId(final JedisXAddParams xAddParams, final MaxLenMinId<?> maxLenMinId,
									final ApproximateExactTrimming approximateExactTrimming) {
		maxLenMinId(xAddParams, maxLenMinId);
		if(maxLenMinId != null){
			if(approximateExactTrimming == ApproximateExactTrimming.APPROXIMATE){
				xAddParams.approximateTrimming();
			}else if(approximateExactTrimming == ApproximateExactTrimming.EXACT){
				xAddParams.exactTrimming();
			}
		}
	}

	private static void noMkStream(final JedisXAddParams xAddParams, final Boolean noMkStream) {
		if(Boolean.TRUE.equals(noMkStream)){
			xAddParams.noMkStream();
		}
	}

}
