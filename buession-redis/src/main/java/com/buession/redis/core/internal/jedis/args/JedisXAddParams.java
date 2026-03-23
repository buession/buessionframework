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
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.command.args.MaxLenMinId;
import com.buession.redis.core.command.args.stream.XAddArgument;
import com.buession.redis.core.internal.convert.jedis.params.StreamDeletionPolicyConverter;
import redis.clients.jedis.params.XAddParams;

/**
 * Jedis {@link XAddParams} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisXAddParams extends XAddParams {

	/**
	 * 构造函数
	 */
	public JedisXAddParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param xAddArgument
	 *        {@link XAddArgument}
	 */
	public JedisXAddParams(final XAddArgument xAddArgument) {
		super();

		if(xAddArgument != null){
			if(Boolean.TRUE.equals(xAddArgument.getNoMkStream())){
				noMkStream();
			}

			if(xAddArgument.getDeletionPolicy() != null){
				final StreamDeletionPolicyConverter xDeletionPolicyConverter = new StreamDeletionPolicyConverter();
				trimmingMode(xDeletionPolicyConverter.convert(xAddArgument.getDeletionPolicy()));
			}

			if(xAddArgument.getIdmp() != null){

			}

			if(xAddArgument.getMaxLenMinId() != null){
				MaxLenMinId<?> maxLenMinId = xAddArgument.getMaxLenMinId();

				if(maxLenMinId instanceof MaxLenMinId.MaxLen){
					maxLen(((MaxLenMinId.MaxLen) maxLenMinId).getThreshold());
				}else if(maxLenMinId instanceof MaxLenMinId.MinId){
					minId(((MaxLenMinId.MinId) maxLenMinId).getThreshold().toString());
				}

				if(xAddArgument.getApproximateExactTrimming() == ApproximateExactTrimming.APPROXIMATE){
					approximateTrimming();
				}else if(xAddArgument.getApproximateExactTrimming() == ApproximateExactTrimming.EXACT){
					exactTrimming();
				}
			}
		}
	}

	/**
	 * 构造函数
	 *
	 * @param xAddArgument
	 *        {@link XAddArgument}
	 * @param id
	 *        {@link StreamEntryId}
	 */
	public JedisXAddParams(final XAddArgument xAddArgument, final StreamEntryId id) {
		this(xAddArgument);

		if(id != null){
			id(id.toString());
		}
	}

	/**
	 * 构造函数
	 *
	 * @param id
	 *        {@link StreamEntryId}
	 */
	public JedisXAddParams(final StreamEntryId id) {
		super();
		if(id != null){
			id(id.toString());
		}
	}

}
