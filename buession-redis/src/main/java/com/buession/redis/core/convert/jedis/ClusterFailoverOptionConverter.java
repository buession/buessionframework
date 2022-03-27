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
package com.buession.redis.core.convert.jedis;

import com.buession.core.converter.Converter;
import com.buession.redis.core.ClusterFailoverOption;

/**
 * {@link ClusterFailoverOption} 和 jedis {@link redis.clients.jedis.args.ClusterFailoverOption} 互转
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public interface ClusterFailoverOptionConverter<S, T> extends Converter<S, T> {

	/**
	 * {@link ClusterFailoverOption} 转换为 jedis {@link redis.clients.jedis.args.ClusterFailoverOption}
	 *
	 * @author Yong.Teng
	 * @since 2.0.0
	 */
	final class ClusterFailoverOptionJedisConverter
			implements BitOperationConverter<ClusterFailoverOption, redis.clients.jedis.args.ClusterFailoverOption> {

		@Override
		public redis.clients.jedis.args.ClusterFailoverOption convert(final ClusterFailoverOption source){
			switch(source){
				case FORCE:
					return redis.clients.jedis.args.ClusterFailoverOption.FORCE;
				case TAKEOVER:
					return redis.clients.jedis.args.ClusterFailoverOption.TAKEOVER;
				default:
					return null;
			}
		}

	}

	/**
	 * jedis {@link redis.clients.jedis.args.ClusterFailoverOption} 转换为 {@link ClusterFailoverOption}
	 *
	 * @author Yong.Teng
	 * @since 1.2.1
	 */
	final class ClusterFailoverOptionExposeConverter
			implements BitOperationConverter<redis.clients.jedis.args.ClusterFailoverOption, ClusterFailoverOption> {

		@Override
		public ClusterFailoverOption convert(final redis.clients.jedis.args.ClusterFailoverOption source){
			switch(source){
				case FORCE:
					return ClusterFailoverOption.FORCE;
				case TAKEOVER:
					return ClusterFailoverOption.TAKEOVER;
				default:
					return null;
			}
		}

	}

}
