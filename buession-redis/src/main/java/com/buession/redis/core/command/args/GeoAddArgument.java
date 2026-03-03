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

import com.buession.redis.core.NxXx;
import com.buession.redis.utils.ArgStringBuilder;

import java.util.Objects;

/**
 * GEO ADD 参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class GeoAddArgument {

	private NxXx nxXx;

	private Boolean ch;

	/**
	 * 构造函数
	 */
	public GeoAddArgument() {

	}

	/**
	 * 构造函数
	 */
	public GeoAddArgument(final NxXx nxXx) {
		this.nxXx = nxXx;
	}

	/**
	 * 构造函数
	 */
	public GeoAddArgument(final NxXx nxXx, final Boolean ch) {
		this.nxXx = nxXx;
		this.ch = ch;
	}

	/**
	 * 构造函数
	 */
	public GeoAddArgument(final Boolean ch) {
		this.ch = ch;
	}

	public NxXx getNxXx() {
		return nxXx;
	}

	public GeoAddArgument setNxXx(NxXx nxXx) {
		this.nxXx = nxXx;
		return this;
	}

	public GeoAddArgument nx() {
		return setNxXx(NxXx.NX);
	}

	public GeoAddArgument xx() {
		return setNxXx(NxXx.XX);
	}

	public Boolean isCh() {
		return getCh();
	}

	public Boolean getCh() {
		return ch;
	}

	public GeoAddArgument ch() {
		return setCh(true);
	}

	public GeoAddArgument setCh(Boolean ch) {
		this.ch = ch;
		return this;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create()
				.append(getNxXx())
				.append(Boolean.TRUE.equals(getCh()) ? "CH" : null)
				.build();
	}

}
