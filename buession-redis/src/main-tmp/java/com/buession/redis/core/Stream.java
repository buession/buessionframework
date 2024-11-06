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
package com.buession.redis.core;

import com.buession.redis.utils.ObjectStringBuilder;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class Stream implements Serializable {

	private final static long serialVersionUID = -4336316668706617743L;

	private final long length;

	private final long radixTreeKeys;

	private final long radixTreeNodes;

	private final long groups;

	private final StreamEntryId lastGeneratedId;

	private final StreamEntry firstEntry;

	private final StreamEntry lastEntry;

	private final Map<String, Object> infos;

	public Stream(final long length, final long radixTreeKeys, final long radixTreeNodes, final long groups,
				  final StreamEntryId lastGeneratedId, final StreamEntry firstEntry, final StreamEntry lastEntry,
				  final Map<String, Object> infos){
		this.length = length;
		this.radixTreeKeys = radixTreeKeys;
		this.radixTreeNodes = radixTreeNodes;
		this.groups = groups;
		this.lastGeneratedId = lastGeneratedId;
		this.firstEntry = firstEntry;
		this.lastEntry = lastEntry;
		this.infos = infos;
	}

	public long getLength(){
		return length;
	}

	public long getRadixTreeKeys(){
		return radixTreeKeys;
	}

	public long getRadixTreeNodes(){
		return radixTreeNodes;
	}

	public long getGroups(){
		return groups;
	}

	public StreamEntryId getLastGeneratedId(){
		return lastGeneratedId;
	}

	public StreamEntry getFirstEntry(){
		return firstEntry;
	}

	public StreamEntry getLastEntry(){
		return lastEntry;
	}

	public Map<String, Object> getInfos(){
		return infos;
	}

	@Override
	public String toString(){
		return ObjectStringBuilder.create()
				.add("length", length)
				.add("radixTreeKeys", radixTreeKeys)
				.add("radixTreeNodes", radixTreeNodes)
				.add("groups", groups)
				.add("lastGeneratedId", lastGeneratedId)
				.add("firstEntry", firstEntry)
				.add("lastEntry", lastEntry)
				.add("infos", infos)
				.build();
	}

}
