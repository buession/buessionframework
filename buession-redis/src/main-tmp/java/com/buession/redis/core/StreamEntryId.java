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

import com.buession.core.Rawable;
import com.buession.core.utils.StringUtils;
import com.buession.redis.utils.SafeEncoder;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class StreamEntryId implements Rawable, Comparable<StreamEntryId>, Serializable {

	private final static long serialVersionUID = -4487927281373256508L;

	/**
	 * Should be used only with XADD
	 *
	 * <code>
	 * XADD mystream * field1 value1
	 * </code>
	 */
	public final static StreamEntryId NEW_ENTRY = new StreamEntryId() {

		@Override
		public String toString() {
			return "*";
		}

	};

	/**
	 * Can be used in XRANGE, XREVRANGE and XPENDING commands.
	 */
	public final static StreamEntryId MINIMUM_ID = new StreamEntryId() {

		@Override
		public String toString() {
			return "-";
		}

	};

	/**
	 * Can be used in XRANGE, XREVRANGE and XPENDING commands.
	 */
	public final static StreamEntryId MAXIMUM_ID = new StreamEntryId() {

		@Override
		public String toString() {
			return "+";
		}

	};

	/**
	 * Should be used only with XGROUP CREATE
	 *
	 * <code>
	 * XGROUP CREATE mystream consumer-group-name $
	 * </code>
	 */
	public final static StreamEntryId LAST_ENTRY = new StreamEntryId() {

		@Override
		public String toString() {
			return "$";
		}

	};

	/**
	 * Should be used only with XREADGROUP
	 * <p>
	 * {@code XREADGROUP $GroupName $ConsumerName BLOCK 2000 COUNT 10 STREAMS mystream >}
	 * </p>
	 */
	public final static StreamEntryId UNRECEIVED_ENTRY = new StreamEntryId() {

		@Override
		public String toString() {
			return ">";
		}

	};

	private final long time;

	private final long sequence;

	public StreamEntryId() {
		this(0L, 0L);
	}

	public StreamEntryId(final String id) {
		String[] split = StringUtils.split(id, '-');
		this.time = Long.parseLong(split[0]);
		this.sequence = Long.parseLong(split[1]);
	}

	public StreamEntryId(final byte[] id) {
		this(SafeEncoder.encode(id));
	}

	public StreamEntryId(final long time) {
		this(time, 0L);
	}

	public StreamEntryId(final long time, final long sequence) {
		this.time = time;
		this.sequence = sequence;
	}

	public long getTime() {
		return time;
	}

	public long getSequence() {
		return sequence;
	}

	@Override
	public byte[] getRaw() {
		return toString().getBytes(StandardCharsets.UTF_8);
	}

	@Override
	public int compareTo(StreamEntryId other) {
		int timeCompare = Long.compare(this.time, other.time);
		return timeCompare != 0 ? timeCompare : Long.compare(this.sequence, other.sequence);
	}

	@Override
	public int hashCode() {
		return Objects.hash(time, sequence);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == this){
			return true;
		}

		if(obj instanceof StreamEntryId){
			StreamEntryId that = (StreamEntryId) obj;
			return that.time == time && that.sequence == sequence;
		}

		return false;
	}

	@Override
	public String toString() {
		return time + "-" + sequence;
	}

}
