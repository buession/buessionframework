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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.internal.convert.lettuce.response;

import com.buession.core.converter.Converter;
import com.buession.redis.core.StreamConsumer;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;

/**
 * Lettuce 'xinfo-consumers' 命令结果转换为 {@link StreamConsumer}
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class StreamConsumersInfoConverter implements Converter<Object, StreamConsumer> {

	@SuppressWarnings("unchecked")
	@Override
	public StreamConsumer convert(final Object source) {
		if(source == null){
			return null;
		}else{
			final List<Object> tmp = (List<Object>) source;
			String key;
			Object value;
			String name = null;
			Long idle = null;
			Long pending = null;
			Long inactive = null;

			for(int i = 0, j = tmp.size(); i < j; i += 2){
				key = SafeEncoder.encode((byte[]) tmp.get(i));
				value = tmp.get(i + 1);

				switch(key){
					case "name":
						name = SafeEncoder.encode((byte[]) value);
						break;
					case "idle":
						idle = (Long) value;
						break;
					case "pending":
						pending = (Long) value;
						break;
					case "inactive":
						inactive = (Long) value;
						break;
					default:
						break;
				}
			}

			return new StreamConsumer(name, idle, pending, inactive);
		}
	}

}
