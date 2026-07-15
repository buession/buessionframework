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
package com.buession.redis.core.internal.jedis.args;

import com.buession.redis.core.command.args.stream.ApproximateExactTrimming;
import com.buession.redis.core.StreamDeletionPolicy;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.command.args.MaxLenMinId;
import redis.clients.jedis.params.XTrimParams;

/**
 * Jedis {@link XTrimParams} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class JedisXTrimParams extends XTrimParams {

	/**
	 * 构造函数
	 */
	public JedisXTrimParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param maxLenMinId
	 *        {@link MaxLenMinId}
	 */
	public JedisXTrimParams(final MaxLenMinId<?> maxLenMinId) {
		super();
		if(maxLenMinId instanceof MaxLenMinId.MaxLen){
			maxLen(((MaxLenMinId.MaxLen) maxLenMinId).getThreshold());
		}else if(maxLenMinId instanceof MaxLenMinId.MinId){
			final StreamEntryId id = ((MaxLenMinId.MinId) maxLenMinId).getThreshold();

			if(id != null){
				minId(id.toString());
			}
		}
	}

	/**
	 * 构造函数
	 *
	 * @param maxLenMinId
	 *        {@link MaxLenMinId}
	 * @param approximateExactTrimming
	 *        {@link ApproximateExactTrimming }
	 */
	public JedisXTrimParams(final MaxLenMinId<?> maxLenMinId, final ApproximateExactTrimming approximateExactTrimming) {
		this(maxLenMinId);
		if(approximateExactTrimming == ApproximateExactTrimming.EXACT){
			exactTrimming();
		}else if(approximateExactTrimming == ApproximateExactTrimming.APPROXIMATE){
			approximateTrimming();
		}
	}

	/**
	 * 构造函数
	 *
	 * @param maxLenMinId
	 *        {@link MaxLenMinId}
	 * @param approximateExactTrimming
	 *        {@link ApproximateExactTrimming }
	 * @param count
	 * 		数量
	 */
	public JedisXTrimParams(final MaxLenMinId<?> maxLenMinId, final ApproximateExactTrimming approximateExactTrimming,
							final int count) {
		this(maxLenMinId, approximateExactTrimming);
		limit(count);
	}

	/**
	 * 构造函数
	 *
	 * @param maxLenMinId
	 *        {@link MaxLenMinId}
	 * @param count
	 * 		数量
	 */
	public JedisXTrimParams(final MaxLenMinId<?> maxLenMinId, final int count) {
		this(maxLenMinId);
		limit(count);
	}

	/**
	 * 构造函数
	 *
	 * @param count
	 * 		数量
	 */
	public JedisXTrimParams(final int count) {
		super();
		limit(count);
	}

	public JedisXTrimParams deletionPolicy(StreamDeletionPolicy deletionPolicy) {
		if(deletionPolicy != null){
			switch(deletionPolicy){
				case ACKED -> trimmingMode(redis.clients.jedis.args.StreamDeletionPolicy.ACKNOWLEDGED);
				case DELREF -> trimmingMode(redis.clients.jedis.args.StreamDeletionPolicy.DELETE_REFERENCES);
				case KEEPREF -> trimmingMode(redis.clients.jedis.args.StreamDeletionPolicy.KEEP_REFERENCES);
			}
		}

		return this;
	}

	public JedisXTrimParams minId(StreamEntryId id) {
		if(id != null){
			minId(id.toString());
		}

		return this;
	}

}
