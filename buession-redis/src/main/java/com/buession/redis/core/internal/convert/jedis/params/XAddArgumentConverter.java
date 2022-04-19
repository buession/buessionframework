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
package com.buession.redis.core.internal.convert.jedis.params;

import com.buession.core.converter.Converter;
import com.buession.redis.core.command.StreamCommands;
import redis.clients.jedis.params.XAddParams;

/**
 * {@link StreamCommands.XAddArgument} 转换为 jedis {@link XAddParams}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class XAddArgumentConverter implements Converter<StreamCommands.XAddArgument, XAddParams> {

	public final static XAddArgumentConverter INSTANCE = new XAddArgumentConverter();

	@Override
	public XAddParams convert(final StreamCommands.XAddArgument source){
		final XAddParams xAddParams = XAddParams.xAddParams();

		if(source.getMaxLen() != null){
			xAddParams.maxLen(source.getMaxLen());
		}

		if(Boolean.TRUE.equals(source.isApproximateTrimming())){
			xAddParams.approximateTrimming();
		}

		if(Boolean.TRUE.equals(source.isExactTrimming())){
			xAddParams.exactTrimming();
		}

		if(Boolean.TRUE.equals(source.isNoMkStream())){
			xAddParams.noMkStream();
		}

		if(source.getMinId() != null){
			xAddParams.minId(source.getMinId());
		}

		if(source.getLimit() != null){
			xAddParams.limit(source.getLimit());
		}

		return xAddParams;
	}

}
