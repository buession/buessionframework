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
import com.buession.redis.core.command.args.timeseries.AddArgument;
import redis.clients.jedis.timeseries.EncodingFormat;
import redis.clients.jedis.timeseries.TSAddParams;

import java.util.Optional;

/**
 * Jedis {@link TSAddParams} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisTSAddParams extends TSAddParams {

	/**
	 * 构造函数
	 */
	public JedisTSAddParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param addArgument
	 *        {@link AddArgument}
	 */
	public JedisTSAddParams(final AddArgument addArgument) {
		super();

		if(addArgument != null){
			Optional.ofNullable(addArgument.getRetention()).ifPresent(this::retention);
			Optional.ofNullable(addArgument.getChunkSize()).ifPresent(this::chunkSize);
			Optional.ofNullable(addArgument.getLabels()).ifPresent(this::labels);

			if(addArgument.getIgnore() != null){
				ignore(addArgument.getIgnore().getMaxTimediff(), addArgument.getIgnore().getMaxValDiff());
			}

			if(addArgument.getEncoding() == Encoding.COMPRESSED){
				encoding(EncodingFormat.COMPRESSED);
			}else if(addArgument.getEncoding() == Encoding.UNCOMPRESSED){
				encoding(EncodingFormat.UNCOMPRESSED);
			}

			if(addArgument.getDuplicatePolicy() == DuplicatePolicy.BLOCK){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.BLOCK);
			}else if(addArgument.getDuplicatePolicy() == DuplicatePolicy.FIRST){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.FIRST);
			}else if(addArgument.getDuplicatePolicy() == DuplicatePolicy.LAST){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.LAST);
			}else if(addArgument.getDuplicatePolicy() == DuplicatePolicy.MIN){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.MIN);
			}else if(addArgument.getDuplicatePolicy() == DuplicatePolicy.MAX){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.MAX);
			}else if(addArgument.getDuplicatePolicy() == DuplicatePolicy.SUM){
				duplicatePolicy(redis.clients.jedis.timeseries.DuplicatePolicy.SUM);
			}

			if(addArgument.getOnDuplicate() == DuplicatePolicy.BLOCK){
				onDuplicate(redis.clients.jedis.timeseries.DuplicatePolicy.BLOCK);
			}else if(addArgument.getOnDuplicate() == DuplicatePolicy.FIRST){
				onDuplicate(redis.clients.jedis.timeseries.DuplicatePolicy.FIRST);
			}else if(addArgument.getOnDuplicate() == DuplicatePolicy.LAST){
				onDuplicate(redis.clients.jedis.timeseries.DuplicatePolicy.LAST);
			}else if(addArgument.getOnDuplicate() == DuplicatePolicy.MIN){
				onDuplicate(redis.clients.jedis.timeseries.DuplicatePolicy.MIN);
			}else if(addArgument.getOnDuplicate() == DuplicatePolicy.MAX){
				onDuplicate(redis.clients.jedis.timeseries.DuplicatePolicy.MAX);
			}else if(addArgument.getOnDuplicate() == DuplicatePolicy.SUM){
				onDuplicate(redis.clients.jedis.timeseries.DuplicatePolicy.SUM);
			}
		}
	}

}
