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

import redis.clients.jedis.params.ZParams;

/**
 * @author Yong.Teng
 */
public final class JedisZParams extends ZParams {

	/**
	 * 构造函数
	 */
	public JedisZParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param aggregate
	 *        {@link Aggregate}
	 */
	public JedisZParams(final com.buession.redis.core.Aggregate aggregate) {
		super();
		aggregate(aggregate);
	}

	/**
	 * 构造函数
	 *
	 * @param weights
	 * 		权重
	 */
	public JedisZParams(final double... weights) {
		super();
		weights(weights);
	}

	/**
	 * 构造函数
	 *
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param weights
	 * 		权重
	 */
	public JedisZParams(final com.buession.redis.core.Aggregate aggregate, final double... weights) {
		super();
		aggregate(aggregate);
		weights(weights);
	}

	private void aggregate(final com.buession.redis.core.Aggregate aggregate) {
		if(aggregate != null){
			switch(aggregate){
				case MIN:
					aggregate(Aggregate.MIN);
					break;
				case MAX:
					aggregate(Aggregate.MAX);
					break;
				case SUM:
					aggregate(Aggregate.SUM);
					break;
				default:
					break;
			}
		}
	}

}
