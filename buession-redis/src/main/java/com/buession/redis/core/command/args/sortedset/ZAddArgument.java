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
package com.buession.redis.core.command.args.sortedset;

import com.buession.redis.core.command.args.Argument;
import com.buession.redis.core.command.args.GtLt;
import com.buession.redis.core.command.args.NxXx;
import com.buession.redis.utils.ArgStringBuilder;

/**
 * ZADD 参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class ZAddArgument implements Argument {

	private NxXx nxXx;

	private GtLt gtLt;

	private Boolean ch;

	private Boolean incr;

	/**
	 * 构造函数
	 */
	public ZAddArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 */
	public ZAddArgument(final NxXx nxXx) {
		this.nxXx = nxXx;
	}

	/**
	 * 构造函数
	 *
	 * @param gtLt
	 *        {@link GtLt}
	 */
	public ZAddArgument(final GtLt gtLt) {
		this.gtLt = gtLt;
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param gtLt
	 *        {@link GtLt}
	 */
	public ZAddArgument(final NxXx nxXx, final GtLt gtLt) {
		this.nxXx = nxXx;
		this.gtLt = gtLt;
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param gtLt
	 *        {@link GtLt}
	 * @param ch
	 * 		-
	 * @param incr
	 * 		-
	 */
	public ZAddArgument(final NxXx nxXx, final GtLt gtLt, final boolean ch, final boolean incr) {
		this(nxXx, gtLt);
		this.ch = ch;
		this.incr = incr;
	}

	public NxXx getNxXx() {
		return nxXx;
	}

	public ZAddArgument setNxXx(NxXx nxXx) {
		this.nxXx = nxXx;
		return this;
	}

	public GtLt getGtLt() {
		return gtLt;
	}

	public ZAddArgument setGtLt(GtLt gtLt) {
		this.gtLt = gtLt;
		return this;
	}

	public Boolean getCh() {
		return ch;
	}

	public ZAddArgument ch() {
		return setCh(true);
	}

	public ZAddArgument setCh(boolean ch) {
		this.ch = ch;
		return this;
	}

	public Boolean isIncr() {
		return getIncr();
	}

	public Boolean getIncr() {
		return incr;
	}

	public ZAddArgument incr() {
		return setIncr(true);
	}

	public ZAddArgument setIncr(boolean incr) {
		this.incr = incr;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create()
				.append(getNxXx())
				.append(getGtLt())
				.append(Boolean.TRUE.equals(getCh()) ? "CH" : null)
				.append(Boolean.TRUE.equals(getIncr()) ? "INCR" : null).build();
	}

}
