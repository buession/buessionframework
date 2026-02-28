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
package com.buession.redis.core;

/**
 * Deletion policy for stream commands that handle consumer group references. Used with XDELEX,
 * XACKDEL, and enhanced XADD/XTRIM commands.
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public enum StreamDeletionPolicy implements Keyword {

	/**
	 * Preserves existing references to entries in all consumer groups' PEL. This is the default
	 * behavior similar to XDEL.
	 */
	KEEPREF,

	/**
	 * Removes all references to entries from all consumer groups' pending entry lists, effectively
	 * cleaning up all traces of the messages.
	 */
	DELREF,

	/**
	 * Only operates on entries that were read and acknowledged by all consumer groups.
	 */
	ACKED;

	@Override
	public String getValue() {
		return name();
	}

	@Override
	public String toString() {
		return getValue();
	}

}
