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

import com.buession.redis.core.command.args.timeseries.DuplicatePolicy;
import com.buession.redis.core.command.args.timeseries.Encoding;
import com.buession.redis.core.command.args.timeseries.CreateArgument;
import redis.clients.jedis.timeseries.EncodingFormat;
import redis.clients.jedis.timeseries.TSCreateParams;

import java.util.Optional;

/**
 * Jedis {@link TSCreateParams} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisTSCreateParams extends TSCreateParams {

	/**
	 * 构造函数
	 */
	public JedisTSCreateParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param createArgument
	 *        {@link CreateArgument}
	 */
	public JedisTSCreateParams(final CreateArgument createArgument) {
		super();

		if(createArgument != null){
			Optional.ofNullable(createArgument.getRetention()).ifPresent(this::retention);
			Optional.ofNullable(createArgument.getChunkSize()).ifPresent(this::chunkSize);
			Optional.ofNullable(createArgument.getLabels()).ifPresent(this::labels);

			if(createArgument.getIgnore() != null){
				ignore(createArgument.getIgnore().getMaxTimediff(), createArgument.getIgnore().getMaxValDiff());
			}

			if(createArgument.getEncoding() == Encoding.COMPRESSED){
				encoding(EncodingFormat.COMPRESSED);
			}else if(createArgument.getEncoding() == Encoding.UNCOMPRESSED){
				encoding(EncodingFormat.UNCOMPRESSED);
			}

			if(createArgument.getDuplicatePolicy() == DuplicatePolicy.BLOCK){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.BLOCK);
			}else if(createArgument.getDuplicatePolicy() == DuplicatePolicy.FIRST){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.FIRST);
			}else if(createArgument.getDuplicatePolicy() == DuplicatePolicy.LAST){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.LAST);
			}else if(createArgument.getDuplicatePolicy() == DuplicatePolicy.MIN){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.MIN);
			}else if(createArgument.getDuplicatePolicy() == DuplicatePolicy.MAX){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.MAX);
			}else if(createArgument.getDuplicatePolicy() == DuplicatePolicy.SUM){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.SUM);
			}
		}
	}

}
