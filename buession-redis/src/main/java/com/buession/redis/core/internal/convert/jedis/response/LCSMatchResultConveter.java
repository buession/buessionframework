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
package com.buession.redis.core.internal.convert.jedis.response;

import com.buession.core.converter.Converter;
import com.buession.core.converter.ListConverter;
import com.buession.redis.core.LcsResult;
import redis.clients.jedis.resps.LCSMatchResult;

/**
 * Jedis {@link LCSMatchResult} 转换为 {@link LcsResult}
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LCSMatchResultConveter implements Converter<LCSMatchResult, LcsResult> {

	@Override
	public LcsResult convert(final LCSMatchResult source) {
		if(source == null){
			return null;
		}

		final ListConverter<LCSMatchResult.MatchedPosition, LcsResult.MatchedPosition> lcsMatchResultMatchedPositionConveter = new ListConverter<>(
				new LCSMatchResultMatchedPositionConveter());
		return new LcsResult(source.getMatchString(),
				source.getMatches() == null ? null : lcsMatchResultMatchedPositionConveter.convert(source.getMatches()),
				source.getLen());
	}

	private final static class LCSMatchResultMatchedPositionConveter
			implements Converter<LCSMatchResult.MatchedPosition, LcsResult.MatchedPosition> {

		@Override
		public LcsResult.MatchedPosition convert(final LCSMatchResult.MatchedPosition source) {
			return source == null ? null : new LcsResult.MatchedPosition(positionConvert(source.getA()),
					positionConvert(source.getB()), source.getMatchLen());
		}

		private LcsResult.Position positionConvert(final LCSMatchResult.Position source) {
			return source == null ? null : new LcsResult.Position(source.getStart(), source.getEnd());
		}

	}

}
