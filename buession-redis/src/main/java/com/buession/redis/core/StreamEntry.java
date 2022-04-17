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

import com.buession.core.utils.StringUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class StreamEntry implements Comparable<StreamEntry>, Serializable {

	private final static long serialVersionUID = -4487927281373256508L;

	private final long time;

	private final long sequence;

	public StreamEntry(){
		this(0L, 0L);
	}

	public StreamEntry(final String id){
		String[] split = StringUtils.split(id, '-');
		this.time = Long.parseLong(split[0]);
		this.sequence = Long.parseLong(split[1]);
	}

	public StreamEntry(final long time){
		this(time, 0L);
	}

	public StreamEntry(final long time, final long sequence){
		this.time = time;
		this.sequence = sequence;
	}

	public long getTime(){
		return time;
	}

	public long getSequence(){
		return sequence;
	}

	@Override
	public int compareTo(StreamEntry other){
		int timeCompare = Long.compare(this.time, other.time);
		return timeCompare != 0 ? timeCompare : Long.compare(this.sequence, other.sequence);
	}

	@Override
	public int hashCode(){
		return Objects.hash(time, sequence);
	}

	@Override
	public boolean equals(Object obj){
		if(obj == this){
			return true;
		}

		if(obj instanceof StreamEntry){
			StreamEntry that = (StreamEntry) obj;
			return that.time == time && that.sequence == sequence;
		}

		return false;
	}

	@Override
	public String toString(){
		return time + "-" + sequence;
	}

}
