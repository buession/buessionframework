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

/**
 * <code>MSETEX</code> 命令参数
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class MSetExArgument extends BaseSetExArgument {

	private final NxXx nxXx;

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 */
	public MSetExArgument(final NxXx nxXx) {
		this.nxXx = nxXx;
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 *        {@link NxXx}
	 * @param type
	 *        {@link SetExType}
	 * @param value
	 * 		值
	 */
	public MSetExArgument(final NxXx nxXx, final SetExType type, final long value) {
		super(type, value);
		this.nxXx = nxXx;
	}

	public NxXx getNxXx() {
		return nxXx;
	}

	@Override
	public String toString() {
		return ArgStringBuilder.create()
				.append(getNxXx())
				.add(getType().name(), getType() == SetExType.KEEPTTL ? null : getValue())
				.build();
	}

}
