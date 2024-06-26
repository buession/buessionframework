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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.internal.jedis;

import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.internal.convert.jedis.params.StreamEntryIdConverter;
import redis.clients.jedis.params.XPendingParams;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class JedisXPendingParams extends XPendingParams {

	public JedisXPendingParams(){
		super();
	}

	public JedisXPendingParams(final long minIdleTime){
		this();
		idle(minIdleTime);
	}

	public JedisXPendingParams(final long minIdleTime, final StreamEntryId start, final StreamEntryId end,
							   final long count){
		this(start, end, count);
		idle(minIdleTime);
	}

	public JedisXPendingParams(final long minIdleTime, final String consumer){
		this(minIdleTime);
		consumer(consumer);
	}

	public JedisXPendingParams(final StreamEntryId start, final StreamEntryId end, final long count){
		super(StreamEntryIdConverter.INSTANCE.convert(start), StreamEntryIdConverter.INSTANCE.convert(end),
				(int) count);
	}

	public JedisXPendingParams(final StreamEntryId start, final StreamEntryId end, final long count,
							   final String consumer){
		this(start, end, count);
		consumer(consumer);
	}

	public JedisXPendingParams(final long minIdleTime, final StreamEntryId start, final StreamEntryId end,
							   final long count, final String consumer){
		this(minIdleTime, start, end, count);
		consumer(consumer);
	}

	public JedisXPendingParams(final String consumer){
		this();
		consumer(consumer);
	}

}
