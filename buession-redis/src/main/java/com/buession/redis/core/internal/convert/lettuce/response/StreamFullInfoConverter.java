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
import com.buession.redis.core.StreamEntry;
import com.buession.redis.core.StreamEntryId;
import com.buession.redis.core.StreamFull;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class StreamFullInfoConverter<K, V> implements Converter<List<Object>, StreamFull<K, V>> {

	@Override
	public StreamFull<K, V> convert(final List<Object> source) {
		if(source == null){
			return null;
		}

		String key;
		Object value;
		Long length = null;
		List<StreamFull.Group> groups = null;
		Long radixTreeKeys = null;
		Long radixTreeNodes = null;
		StreamEntryId lastGeneratedId = null;
		StreamEntryId maxDeletedEntryId = null;
		StreamEntryId recordedFirstEntryId = null;
		Long entriesAdded = null;
		List<StreamEntry<K, V>> entries = null;

		for(int i = 0, j = source.size(); i < j; i += 2){
			key = SafeEncoder.encode((byte[]) source.get(i));
			value = source.get(i + 1);

			switch(key){
				case "length":
					length = (Long) value;
					break;
				case "radix-tree-keys":
					radixTreeKeys = (Long) value;
					break;
				case "radix-tree-nodes":
					radixTreeNodes = (Long) value;
					break;
				case "last-generated-id":
					lastGeneratedId = new StreamEntryId((byte[]) value);
					break;
				case "max-deleted-entry-id":
					maxDeletedEntryId = new StreamEntryId((byte[]) value);
					break;
				case "entries-added":
					entriesAdded = (Long) value;
					break;
				case "recorded-first-entry-id":
					recordedFirstEntryId = new StreamEntryId((byte[]) value);
					break;
				default:
					break;
			}
		}

		return new StreamFull<>(length, groups, radixTreeKeys, radixTreeNodes, lastGeneratedId, maxDeletedEntryId,
				recordedFirstEntryId, entriesAdded, null, null, null, null, null, null, entries);
	}

}
