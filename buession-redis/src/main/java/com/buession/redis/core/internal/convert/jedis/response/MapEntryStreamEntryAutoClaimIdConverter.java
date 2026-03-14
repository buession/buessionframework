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
import com.buession.redis.core.AutoClaimId;
import com.buession.redis.core.StreamEntryId;
import redis.clients.jedis.StreamEntryID;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * {@link Map.Entry} 转换为 {@link AutoClaimId}
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class MapEntryStreamEntryAutoClaimIdConverter implements
		Converter<Map.Entry<StreamEntryID, List<StreamEntryID>>, AutoClaimId> {

	@Override
	public AutoClaimId convert(final Map.Entry<StreamEntryID, List<StreamEntryID>> source) {
		if(source == null){
			return null;
		}

		final StreamEntryIDConverter streamEntryIdConverter = new StreamEntryIDConverter();
		final List<StreamEntryId> streamEntryIds = source.getValue() == null ? null :
				source.getValue().stream().map(streamEntryIdConverter::convert).collect(Collectors.toList());
		return new AutoClaimId(streamEntryIdConverter.convert(source.getKey()), streamEntryIds);
	}

}
