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
package com.buession.redis.core.command.args;

import com.buession.redis.core.Keyword;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.utils.ArgStringBuilder;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public abstract class MaxLenMinId<T> {

	private final Type type;

	private final T threshold;

	public MaxLenMinId(final Type type, final T threshold) {
		this.type = type;
		this.threshold = threshold;
	}

	public Type getType() {
		return type;
	}

	public T getThreshold() {
		return threshold;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create().append(getType())
				.append(getThreshold())
				.build();
	}

	public final static class MaxLen extends MaxLenMinId<Long> {

		public MaxLen(final long value) {
			super(Type.MAXLEN, value);
		}

	}

	public final static class MinId extends MaxLenMinId<StreamEntryId> {

		public MinId(final StreamEntryId value) {
			super(Type.MINID, value);
		}

	}

	public enum Type implements Keyword {

		MAXLEN,

		MINID;

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
