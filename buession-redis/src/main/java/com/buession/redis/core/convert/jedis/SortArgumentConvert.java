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

import com.buession.lang.Order;
import com.buession.redis.core.SortArgument;
import com.buession.redis.core.convert.Convert;
import com.buession.redis.utils.SafeEncoder;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.SortingParams;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Yong.Teng
 */
public class SortArgumentConvert implements Convert<SortArgument, SortingParams> {

	@Override
	public SortingParams convert(final SortArgument source){
		if(source == null){
			return null;
		}

		final SortingParams sortingParams = new SortingParams();

		if(source.getBy() != null){
			sortingParams.by(source.getBy());
		}

		if(source.getOrder() == Order.ASC){
			sortingParams.asc();
		}else if(source.getOrder() == Order.DESC){
			sortingParams.desc();
		}

		if(source.getLimit() != null){
			sortingParams.limit((int) source.getLimit().getOffset(), (int) source.getLimit().getCount());
		}

		if(source.getAlpha() != null){
			sortingParams.alpha();
		}

		return sortingParams;
	}

	@Override
	public SortArgument deconvert(final SortingParams target){
		if(target == null){
			return null;
		}

		final SortArgument.Builder sortArgumentBuilder = SortArgument.Builder.create();

		Collection<byte[]> collections = target.getParams();
		Iterator<byte[]> iterator = collections.iterator();

		while(iterator.hasNext()){
			byte[] v = iterator.next();

			if(v == Protocol.Keyword.BY.raw){
				v = iterator.next();
				sortArgumentBuilder.by(SafeEncoder.encode(v));
			}else if(v == Protocol.Keyword.ASC.raw){
				sortArgumentBuilder.asc();
			}else if(v == Protocol.Keyword.DESC.raw){
				sortArgumentBuilder.desc();
			}else if(v == Protocol.Keyword.LIMIT.raw){
				byte[] start = iterator.next();
				byte[] end = iterator.next();
				sortArgumentBuilder.limit(Long.valueOf(SafeEncoder.encode(start)),
						Long.valueOf(SafeEncoder.encode(end)));
			}else if(v == Protocol.Keyword.ALPHA.raw){
				sortArgumentBuilder.alpha();
			}
		}

		return sortArgumentBuilder.build();
	}

}
