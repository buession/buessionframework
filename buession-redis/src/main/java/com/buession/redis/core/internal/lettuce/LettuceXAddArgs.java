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
import com.buession.redis.core.command.StreamCommands;
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

	public LettuceXAddArgs() {
		super();
	}

	public LettuceXAddArgs(final String id) {
		super();
		id(id);
	}

	public LettuceXAddArgs(final byte[] id) {
		this(SafeEncoder.encode(id));
	}

	public LettuceXAddArgs(final StreamEntryId id) {
		this((new StreamEntryIdConverter()).convert(id));
	}

	public LettuceXAddArgs(final long maxLen) {
		super();
		maxlen(maxLen);
	}

	public LettuceXAddArgs(final String id, final long maxLen) {
		this(id);
		maxlen(maxLen);
	}

	public LettuceXAddArgs(final byte[] id, final long maxLen) {
		this(id);
		maxlen(maxLen);
	}

	public LettuceXAddArgs(final StreamEntryId id, final long maxLen) {
		this(id);
		maxlen(maxLen);
	}

	public LettuceXAddArgs(final boolean approximateTrimming) {
		super();
		approximateTrimming(this, approximateTrimming);
	}

	public LettuceXAddArgs(final String id, final boolean approximateTrimming) {
		this(id);
		approximateTrimming(this, approximateTrimming);
	}

	public LettuceXAddArgs(final byte[] id, final boolean approximateTrimming) {
		this(id);
		approximateTrimming(this, approximateTrimming);
	}

	public LettuceXAddArgs(final StreamEntryId id, final boolean approximateTrimming) {
		this(id);
		approximateTrimming(this, approximateTrimming);
	}

	public LettuceXAddArgs(final String id, final long maxLen, final boolean approximateTrimming) {
		this(id, approximateTrimming);
		maxlen(maxLen);
	}

	public LettuceXAddArgs(final byte[] id, final long maxLen, final boolean approximateTrimming) {
		this(id, approximateTrimming);
		maxlen(maxLen);
	}

	public LettuceXAddArgs(final StreamEntryId id, final long maxLen, final boolean approximateTrimming) {
		this(id, approximateTrimming);
		maxlen(maxLen);
	}

	public static LettuceXAddArgs from(final StreamCommands.XAddArgument xAddArgument) {
		final LettuceXAddArgs xAddArgs = new LettuceXAddArgs();

		if(xAddArgument != null){
			Optional.ofNullable(xAddArgument.getMaxLen()).ifPresent(xAddArgs::maxlen);
			approximateTrimming(xAddArgs, xAddArgument.isApproximateTrimming());
		}

		return xAddArgs;
	}

	private static void approximateTrimming(final LettuceXAddArgs xAddArgs, final Boolean approximateTrimming) {
		if(Boolean.TRUE.equals(approximateTrimming)){
			xAddArgs.approximateTrimming();
		}
	}

}
