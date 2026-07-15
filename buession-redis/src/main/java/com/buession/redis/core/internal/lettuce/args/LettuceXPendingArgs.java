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
package com.buession.redis.core.internal.lettuce.args;

import com.buession.redis.core.StreamEntryId;
import io.lettuce.core.Consumer;
import io.lettuce.core.Limit;
import io.lettuce.core.Range;
import io.lettuce.core.XPendingArgs;

/**
 * Lettuce {@link XPendingArgs} 扩展
 *
 * @param <T>
 * 		类型
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceXPendingArgs<T> extends XPendingArgs<T> {

	public LettuceXPendingArgs() {
		super();
	}

	public LettuceXPendingArgs(final StreamEntryId start, final StreamEntryId end, final int count) {
		super();
		range(Range.create(start.toString(), end.toString()));
		limit(Limit.from(count));
	}

	public LettuceXPendingArgs(final StreamEntryId start, final StreamEntryId end, final int count, final long idle) {
		this(start, end, count);
		idle(idle);
	}

	public LettuceXPendingArgs(final StreamEntryId start, final StreamEntryId end, final int count, final T groupName,
							   final T consumerName) {
		this(start, end, count);
		consumer(Consumer.from(groupName, consumerName));
	}

	public LettuceXPendingArgs(final StreamEntryId start, final StreamEntryId end, final int count, final long idle,
							   final T groupName, final T consumerName) {
		this(start, end, count, groupName, consumerName);
		idle(idle);
	}

	public LettuceXPendingArgs(final StreamEntryId start, final StreamEntryId end, final int count, final T groupName) {
		this(start, end, count);
		group(groupName);
	}

	public LettuceXPendingArgs(final StreamEntryId start, final StreamEntryId end, final int count, final long idle,
							   final T groupName) {
		this(start, end, count, groupName);
		idle(idle);
	}

}
