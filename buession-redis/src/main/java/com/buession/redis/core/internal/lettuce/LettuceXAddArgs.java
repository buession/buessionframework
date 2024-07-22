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

import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.command.args.XAddArgument;
import com.buession.redis.core.internal.convert.lettuce.params.StreamEntryIdConverter;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.XAddArgs;

import java.util.Optional;

/**
 * Lettuce {@link XAddArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceXAddArgs extends XAddArgs {

	/**
	 * 构造函数
	 */
	public LettuceXAddArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 */
	public LettuceXAddArgs(final String id) {
		super();
		id(id);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 */
	public LettuceXAddArgs(final byte[] id) {
		this(SafeEncoder.encode(id));
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 */
	public LettuceXAddArgs(final StreamEntryId id) {
		this((new StreamEntryIdConverter()).convert(id));
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param maxLenMinId
	 * 		-
	 */
	public LettuceXAddArgs(final String id, final XAddArgument.MaxLenMinId<?> maxLenMinId) {
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
	public LettuceXAddArgs(final byte[] id, final XAddArgument.MaxLenMinId<?> maxLenMinId) {
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
	public LettuceXAddArgs(final StreamEntryId id, final XAddArgument.MaxLenMinId<?> maxLenMinId) {
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
	public LettuceXAddArgs(final String id, final Boolean noMkStream) {
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
	public LettuceXAddArgs(final byte[] id, final Boolean noMkStream) {
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
	public LettuceXAddArgs(final StreamEntryId id, final Boolean noMkStream) {
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
	public LettuceXAddArgs(final String id, final Long limit) {
		this(id);
		Optional.ofNullable(limit).ifPresent(this::limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param limit
	 * 		-
	 */
	public LettuceXAddArgs(final byte[] id, final Long limit) {
		this(id);
		Optional.ofNullable(limit).ifPresent(this::limit);
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 * 		Stream Id
	 * @param limit
	 * 		-
	 */
	public LettuceXAddArgs(final StreamEntryId id, final Long limit) {
		this(id);
		Optional.ofNullable(limit).ifPresent(this::limit);
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
	public LettuceXAddArgs(final String id, final XAddArgument.MaxLenMinId<?> maxLenMinId, final Boolean noMkStream) {
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
	public LettuceXAddArgs(final byte[] id, final XAddArgument.MaxLenMinId<?> maxLenMinId, final Boolean noMkStream) {
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
	public LettuceXAddArgs(final StreamEntryId id, final XAddArgument.MaxLenMinId<?> maxLenMinId,
						   final Boolean noMkStream) {
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
	 * @param limit
	 * 		-
	 */
	public LettuceXAddArgs(final String id, final XAddArgument.MaxLenMinId<?> maxLenMinId, final Boolean noMkStream,
						   final Long limit) {
		this(id, maxLenMinId, noMkStream);
		Optional.ofNullable(limit).ifPresent(this::limit);
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
	public LettuceXAddArgs(final byte[] id, final XAddArgument.MaxLenMinId<?> maxLenMinId, final Boolean noMkStream,
						   final Long limit) {
		this(id, maxLenMinId, noMkStream);
		Optional.ofNullable(limit).ifPresent(this::limit);
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
	public LettuceXAddArgs(final StreamEntryId id, final XAddArgument.MaxLenMinId<?> maxLenMinId,
						   final Boolean noMkStream, final Long limit) {
		this(id, maxLenMinId, noMkStream);
		Optional.ofNullable(limit).ifPresent(this::limit);
	}

	public LettuceXAddArgs id(final StreamEntryId id) {
		if(id != null){
			id(id.toString());
		}

		return this;
	}

	/**
	 * 从 {@link XAddArgument} 创建 {@link XAddArgs} 实例
	 *
	 * @param xAddArgument
	 *        {@link XAddArgument}
	 *
	 * @return {@link LettuceXAddArgs} 实例
	 */
	public static LettuceXAddArgs from(final XAddArgument xAddArgument) {
		final LettuceXAddArgs xAddArgs = new LettuceXAddArgs();

		if(xAddArgument != null){
			maxLenMinId(xAddArgs, xAddArgument.getMaxLenMinId());
			noMkStream(xAddArgs, xAddArgument.getNoMkStream());
			Optional.ofNullable(xAddArgument.getLimit()).ifPresent(xAddArgs::limit);
		}

		return xAddArgs;
	}

	private static void maxLenMinId(final LettuceXAddArgs xAddArgs, final XAddArgument.MaxLenMinId<?> maxLenMinId) {
		if(maxLenMinId != null){
			if(maxLenMinId instanceof XAddArgument.MaxLen){
				xAddArgs.maxlen(((XAddArgument.MaxLen) maxLenMinId).getValue());
			}else if(maxLenMinId instanceof XAddArgument.MinId){
				xAddArgs.minId(((XAddArgument.MinId) maxLenMinId).getValue().toString());
			}

			if(maxLenMinId.getApproximateExactTrimming() == XAddArgument.ApproximateExactTrimming.APPROXIMATE){
				xAddArgs.approximateTrimming();
			}else if(maxLenMinId.getApproximateExactTrimming() == XAddArgument.ApproximateExactTrimming.EXACT){
				xAddArgs.exactTrimming();
			}
		}
	}

	private static void noMkStream(final LettuceXAddArgs xAddArgs, final Boolean noMkStream) {
		if(Boolean.TRUE.equals(noMkStream)){
			xAddArgs.nomkstream();
		}
	}

}
