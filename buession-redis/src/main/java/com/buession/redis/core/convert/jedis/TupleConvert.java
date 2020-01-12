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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.convert.jedis;

import com.buession.redis.core.Tuple;
import com.buession.redis.core.convert.Convert;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 */
public class TupleConvert implements Convert<Tuple, redis.clients.jedis.Tuple> {

	@Override
	public redis.clients.jedis.Tuple convert(final Tuple source){
		return source == null ? null : new redis.clients.jedis.Tuple(source.getBinaryElement(), source.getScore());
	}

	@Override
	public Tuple deconvert(final redis.clients.jedis.Tuple target){
		return target == null ? null : new Tuple(target.getBinaryElement(), target.getScore());
	}

	public static class SetTupleConvert implements Convert<Set<Tuple>, Set<redis.clients.jedis.Tuple>> {

		@Override
		public Set<redis.clients.jedis.Tuple> convert(final Set<Tuple> source){
			if(source == null){
				return null;
			}

			return source.stream().map(tuple->new redis.clients.jedis.Tuple(tuple.getElement(), tuple.getScore()))
					.collect(Collectors.toCollection(LinkedHashSet::new));
		}

		@Override
		public Set<Tuple> deconvert(final Set<redis.clients.jedis.Tuple> target){
			if(target == null){
				return null;
			}

			return target.stream().map(tuple->new Tuple(tuple.getElement(), tuple.getScore())).collect(Collectors
					.toCollection(LinkedHashSet::new));
		}
	}

}