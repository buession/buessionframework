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

import redis.clients.jedis.params.XReadGroupParams;

/**
 * Jedis {@link XReadGroupParams} 扩展
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisXReadGroupParams extends XReadGroupParams {

	public JedisXReadGroupParams() {
		super();
	}

	public JedisXReadGroupParams(final int count) {
		super();
		count(count);
	}

	public JedisXReadGroupParams(final int count, final long block) {
		this(count);
		block((int) block);
	}

	public JedisXReadGroupParams(final int count, final boolean noAck) {
		this(noAck);
		count(count);
	}

	public JedisXReadGroupParams(final int count, final long block, final boolean noAck) {
		this(count, noAck);
		block((int) block);
	}

	public JedisXReadGroupParams(final long block) {
		super();
		block((int) block);
	}

	public JedisXReadGroupParams(final long block, final boolean noAck) {
		this(noAck);
		block((int) block);
	}

	public JedisXReadGroupParams(final boolean noAck) {
		super();

		if(noAck){
			noAck();
		}
	}

}
