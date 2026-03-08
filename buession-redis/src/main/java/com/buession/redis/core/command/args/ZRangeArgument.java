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
package com.buession.redis.core.command.args;

import com.buession.redis.core.Keyword;
import com.buession.redis.utils.ArgStringBuilder;

import java.util.Objects;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class ZRangeArgument {

	private By by;

	private Boolean rev;

	public ZRangeArgument() {
	}

	public ZRangeArgument(final By by) {
		this.by = by;
	}

	public ZRangeArgument(final boolean rev) {
		this.rev = rev;
	}

	public ZRangeArgument(final By by, final boolean rev) {
		this.by = by;
		this.rev = rev;
	}

	public By getBy() {
		return by;
	}

	public ZRangeArgument setBy(By by) {
		this.by = by;
		return this;
	}

	public Boolean isRev() {
		return getRev();
	}

	public Boolean getRev() {
		return rev;
	}

	public ZRangeArgument rev() {
		return setRev(true);
	}

	public ZRangeArgument setRev(Boolean rev) {
		this.rev = rev;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create().append(getBy()).append(Boolean.TRUE.equals(getRev()) ? "REV" : null).build();
	}

	public enum By implements Keyword {
		BYSCORE,

		BYLEX;

		@Override
		public String getValue() {
			return name();
		}

		@Override
		public String toString() {
			return getValue();
		}

	}

}
