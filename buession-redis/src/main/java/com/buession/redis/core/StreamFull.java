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

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class StreamFull implements Serializable {

	private final static long serialVersionUID = -4336316668706617743L;

	private final long length;

	private final long radixTreeKeys;

	private final long radixTreeNodes;

	private final List<StreamGroupFull> groups;

	private final StreamEntryId lastGeneratedId;

	private final List<StreamEntry> entries;

	private final Map<String, Object> infos;

	public StreamFull(final long length, final long radixTreeKeys, final long radixTreeNodes,
					  final List<StreamGroupFull> groups, final StreamEntryId lastGeneratedId,
					  final List<StreamEntry> entries, final Map<String, Object> infos){
		this.length = length;
		this.radixTreeKeys = radixTreeKeys;
		this.radixTreeNodes = radixTreeNodes;
		this.groups = groups;
		this.lastGeneratedId = lastGeneratedId;
		this.entries = entries;
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

	public List<StreamGroupFull> getGroups(){
		return groups;
	}

	public StreamEntryId getLastGeneratedId(){
		return lastGeneratedId;
	}

	public List<StreamEntry> getEntries(){
		return entries;
	}

	public Map<String, Object> getInfos(){
		return infos;
	}

	@Override
	public String toString(){
		return new StringJoiner(", ", "{", "}")
				.add("length=" + length)
				.add("radixTreeKeys=" + radixTreeKeys)
				.add("radixTreeNodes=" + radixTreeNodes)
				.add("groups=" + groups)
				.add("lastGeneratedId=" + lastGeneratedId)
				.add("entries=" + entries)
				.add("infos=" + infos)
				.toString();
	}
}
