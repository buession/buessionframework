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

import com.buession.redis.core.command.args.timeseries.CreateArgument;
import com.buession.redis.core.command.args.timeseries.DecrByArgument;
import com.buession.redis.core.command.args.timeseries.DuplicatePolicy;
import com.buession.redis.core.command.args.timeseries.Encoding;
import redis.clients.jedis.timeseries.EncodingFormat;
import redis.clients.jedis.timeseries.TSDecrByParams;

import java.util.Optional;

/**
 * Jedis {@link TSDecrByParams} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisTSDecrByParams extends TSDecrByParams {

	/**
	 * 构造函数
	 */
	public JedisTSDecrByParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param decrByArgument
	 *        {@link DecrByArgument}
	 */
	public JedisTSDecrByParams(final DecrByArgument decrByArgument) {
		super();

		if(decrByArgument != null){
			Optional.ofNullable(decrByArgument.getTimestamp()).ifPresent(this::timestamp);
			Optional.ofNullable(decrByArgument.getRetention()).ifPresent(this::retention);
			Optional.ofNullable(decrByArgument.getChunkSize()).ifPresent(this::chunkSize);
			Optional.ofNullable(decrByArgument.getLabels()).ifPresent(this::labels);

			if(decrByArgument.getIgnore() != null){
				ignore(decrByArgument.getIgnore().getMaxTimediff(), decrByArgument.getIgnore().getMaxValDiff());
			}

			if(decrByArgument.getEncoding() == Encoding.COMPRESSED){
				encoding(EncodingFormat.COMPRESSED);
			}else if(decrByArgument.getEncoding() == Encoding.UNCOMPRESSED){
				encoding(EncodingFormat.UNCOMPRESSED);
			}

			if(decrByArgument.getDuplicatePolicy() == DuplicatePolicy.BLOCK){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.BLOCK);
			}else if(decrByArgument.getDuplicatePolicy() == DuplicatePolicy.FIRST){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.FIRST);
			}else if(decrByArgument.getDuplicatePolicy() == DuplicatePolicy.LAST){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.LAST);
			}else if(decrByArgument.getDuplicatePolicy() == DuplicatePolicy.MIN){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.MIN);
			}else if(decrByArgument.getDuplicatePolicy() == DuplicatePolicy.MAX){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.MAX);
			}else if(decrByArgument.getDuplicatePolicy() == DuplicatePolicy.SUM){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.SUM);
			}
		}
	}

}
