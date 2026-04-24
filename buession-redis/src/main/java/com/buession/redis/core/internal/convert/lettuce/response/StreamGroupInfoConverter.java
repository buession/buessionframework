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
package com.buession.redis.core.internal.convert.lettuce.response;

import com.buession.core.converter.Converter;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.StreamGroup;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 'xinfo-groups' 命令结果转换为 {@link StreamGroup}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class StreamGroupInfoConverter implements Converter<Object, StreamGroup> {

	@Override
	public StreamGroup convert(final Object source) {
		if(source instanceof List<?> tmp){
			int sourceSize = tmp.size();

			String name = SafeEncoder.encode((byte[]) tmp.get(1));
			Long consumers = null;
			Long pending = null;
			StreamEntryId lastDeliveredId = null;
			Long entriesRread = null;
			Long lag = null;

			if(sourceSize >= 4){
				consumers = (Long) tmp.get(3);
				if(sourceSize >= 6){
					pending = (Long) tmp.get(5);
					if(sourceSize >= 8){
						lastDeliveredId = new StreamEntryId((byte[]) tmp.get(7));
						if(sourceSize >= 10){
							entriesRread = (Long) tmp.get(9);
							if(sourceSize >= 12){
								lag = (Long) tmp.get(11);
							}
						}
					}
				}
			}

			return new StreamGroup(name, consumers, pending, lastDeliveredId, entriesRread, lag);
		}else{
			return null;
		}
	}

}
