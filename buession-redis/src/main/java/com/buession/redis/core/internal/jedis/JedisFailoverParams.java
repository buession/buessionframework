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
 * | Copyright @ 2013-2023 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.internal.jedis;

import redis.clients.jedis.params.FailoverParams;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisFailoverParams extends FailoverParams {

	public JedisFailoverParams(final String host, final int port) {
		super();
		to(host, port);
	}

	public JedisFailoverParams(final String host, final int port, final int timeout) {
		this(host, port);
		timeout(timeout);
	}

	public JedisFailoverParams(final String host, final int port, final boolean force) {
		this(host, port);
		force(force);
	}

	public JedisFailoverParams(final String host, final int port, final int timeout, final boolean force) {
		this(host, port, timeout);
		force(force);
	}

	public JedisFailoverParams(final int timeout) {
		super();
		timeout(timeout);
	}

	private void force(boolean force) {
		if(force){
			force();
		}
	}

}
