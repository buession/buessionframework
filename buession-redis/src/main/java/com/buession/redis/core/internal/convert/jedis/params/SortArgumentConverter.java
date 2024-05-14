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
package com.buession.redis.core.internal.convert.jedis.params;

import com.buession.core.converter.Converter;
import com.buession.core.converter.mapper.PropertyMapper;
import com.buession.lang.Order;
import com.buession.redis.core.Limit;
import com.buession.redis.core.command.KeyCommands;
import redis.clients.jedis.params.SortingParams;

/**
 * {@link KeyCommands.SortArgument} 转换为 jedis {@link SortingParams}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
@Deprecated
public final class SortArgumentConverter implements Converter<KeyCommands.SortArgument, SortingParams> {

	public final static SortArgumentConverter INSTANCE = new SortArgumentConverter();

	@Override
	public SortingParams convert(final KeyCommands.SortArgument source) {
		final PropertyMapper propertyMapper = PropertyMapper.get().alwaysApplyingWhenNonNull();
		final SortingParams sortingParams = new SortingParams();

		propertyMapper.from(source.getBy()).to(sortingParams::by);
		propertyMapper.from(source.getGetPatterns()).to(sortingParams::get);

		if(source.getOrder() == Order.ASC){
			sortingParams.asc();
		}else if(source.getOrder() == Order.DESC){
			sortingParams.desc();
		}

		if(source.getLimit() != null){
			Limit limit = source.getLimit();
			sortingParams.limit((int) limit.getOffset(), (int) limit.getCount());
		}

		if(Boolean.TRUE.equals(source.isAlpha())){
			sortingParams.alpha();
		}

		return sortingParams;
	}

}
