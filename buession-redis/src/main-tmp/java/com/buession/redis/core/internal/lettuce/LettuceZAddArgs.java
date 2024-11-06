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
package com.buession.redis.core.internal.lettuce;

import com.buession.redis.core.GtLt;
import com.buession.redis.core.NxXx;
import io.lettuce.core.ZAddArgs;

/**
 * Lettuce {@link ZAddArgs} 扩展
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceZAddArgs extends ZAddArgs {

	/**
	 * 构造函数
	 */
	public LettuceZAddArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 * 		更新成员方式
	 */
	public LettuceZAddArgs(final NxXx nxXx) {
		super();
		if(nxXx != null){
			switch(nxXx){
				case NX:
					nx();
					break;
				case XX:
					xx();
					break;
				default:
					break;
			}
		}
	}

	/**
	 * 构造函数
	 *
	 * @param gtLt
	 *        {@link GtLt}
	 */
	public LettuceZAddArgs(final GtLt gtLt) {
		super();
		gtLt(gtLt);
	}

	/**
	 * 构造函数
	 *
	 * @param ch
	 * 		是否返回变更成员的数量
	 */
	public LettuceZAddArgs(final boolean ch) {
		super();
		ch(ch);
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 * 		更新成员方式
	 * @param gtLt
	 * 		更新新的分值方式
	 */
	public LettuceZAddArgs(final NxXx nxXx, final GtLt gtLt) {
		this(nxXx);
		gtLt(gtLt);
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 * 		更新成员方式
	 * @param ch
	 * 		是否返回变更成员的数量
	 */
	public LettuceZAddArgs(final NxXx nxXx, final boolean ch) {
		this(nxXx);
		ch(ch);
	}

	/**
	 * 构造函数
	 *
	 * @param gtLt
	 * 		更新新的分值方式
	 * @param ch
	 * 		是否返回变更成员的数量
	 */
	public LettuceZAddArgs(final GtLt gtLt, final boolean ch) {
		this(gtLt);
		ch(ch);
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 * 		更新成员方式
	 * @param gtLt
	 * 		更新新的分值方式
	 * @param ch
	 * 		是否返回变更成员的数量
	 */
	public LettuceZAddArgs(final NxXx nxXx, final GtLt gtLt, final boolean ch) {
		this(nxXx, gtLt);
		ch(ch);
	}

	/**
	 * 更新新的分值方式
	 *
	 * @param gtLt
	 * 		更新新的分值方式
	 */
	private void gtLt(final GtLt gtLt) {
		if(gtLt != null){
			switch(gtLt){
				case GT:
					break;
				case LT:
					break;
				default:
					break;
			}
		}
	}

	private void ch(boolean ch) {
		if(ch){
			ch();
		}
	}

}
