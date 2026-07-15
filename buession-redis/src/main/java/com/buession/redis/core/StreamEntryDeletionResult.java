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
 * Represents the result of a stream entry deletion operation for XDELEX and XACKDEL commands.
 * <p>
 * Represents the outcome of attempting to delete a specific stream entry:
 * <ul>
 * <li>NOT_FOUND (-1): ID doesn't exist in stream</li>
 * <li>DELETED (1): Entry was deleted/acknowledged and deleted</li>
 * <li>NOT_DELETED_UNACKNOWLEDGED_OR_STILL_REFERENCED (2): Entry wasn't deleted.</li>
 * </ul>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public enum StreamEntryDeletionResult {

	UNKNOWN(-2),

	/**
	 * The stream entry ID doesn't exist in the stream.
	 * <p>
	 * Returned when trying to delete/acknowledge a non-existent entry.
	 * </p>
	 */
	NOT_FOUND(-1),

	/**
	 * The entry was successfully deleted/acknowledged and deleted.
	 * <p>
	 * This is the typical successful case.
	 * </p>
	 */
	DELETED(1),

	/**
	 * The entry was not deleted due to one of the following reasons:
	 * <ul>
	 * <li>For XDELEX: The entry was not acknowledged by any consumer group</li>
	 * <li>For XACKDEL: The entry still has pending references in other consumer groups</li>
	 * </ul>
	 */
	NOT_DELETED_UNACKNOWLEDGED_OR_STILL_REFERENCED(2);

	private final int code;

	StreamEntryDeletionResult(int code) {
		this.code = code;
	}

	/**
	 * Gets the numeric code returned by Redis for this result.
	 *
	 * @return the numeric code (-1, 1, or 2)
	 */
	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return name() + "(" + code + ")";
	}

}
