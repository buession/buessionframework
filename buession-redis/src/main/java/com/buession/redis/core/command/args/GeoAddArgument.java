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
 * | Copyright @ 2013-2024 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.command.args;

import com.buession.redis.core.Keyword;
import com.buession.redis.core.NxXx;

/**
 * {@code GEOADD} 命令参数
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class GeoAddArgument {

	/**
	 * {@link NxXx}
	 */
	private NxXx nxXx;

	private Boolean ch;

	/**
	 * 构造函数
	 */
	public GeoAddArgument() {
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 */
	public GeoAddArgument(final NxXx nxXx) {
		this.nxXx = nxXx;
	}

	/**
	 * 构造函数
	 *
	 * @param ch
	 * 		-
	 */
	public GeoAddArgument(final Boolean ch) {
		this.ch = ch;
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param ch
	 * 		-
	 */
	public GeoAddArgument(final NxXx nxXx, final Boolean ch) {
		this(nxXx);
		this.ch = ch;
	}

	public NxXx getNxXx() {
		return nxXx;
	}

	public void setNxXx(NxXx nxXx) {
		this.nxXx = nxXx;
	}

	public Boolean isCh() {
		return getCh();
	}

	public Boolean getCh() {
		return ch;
	}

	public void ch() {
		this.ch = true;
	}

	public void setCh(Boolean ch) {
		this.ch = ch;
	}

	@Override
	public String toString() {
		final ArgumentStringBuilder builder = ArgumentStringBuilder.create();

		if(nxXx != null){
			builder.append(nxXx);
		}

		if(Boolean.TRUE.equals(ch)){
			builder.append(Keyword.Common.CH);
		}

		return builder.toString();
	}

}
