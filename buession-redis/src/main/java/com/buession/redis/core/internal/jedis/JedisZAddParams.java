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
package com.buession.redis.core.internal.jedis;

import com.buession.redis.core.GtLt;
import com.buession.redis.core.NxXx;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public final class JedisZAddParams extends redis.clients.jedis.params.ZAddParams {

	/**
	 * 构造函数
	 */
	public JedisZAddParams(){
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 * 		更新成员方式
	 */
	public JedisZAddParams(final NxXx nxXx){
		super();
		nxXx(nxXx);
	}

	/**
	 * 构造函数
	 *
	 * @param gtLt
	 *        {@link GtLt}
	 */
	public JedisZAddParams(final GtLt gtLt){
		super();
		gtLt(gtLt);
	}

	/**
	 * 构造函数
	 *
	 * @param ch
	 * 		是否返回变更成员的数量
	 */
	public JedisZAddParams(final boolean ch){
		super();

		if(ch){
			ch();
		}
	}

	/**
	 * 构造函数
	 *
	 * @param nxXx
	 * 		更新成员方式
	 * @param gtLt
	 * 		更新新的分值方式
	 */
	public JedisZAddParams(final NxXx nxXx, final GtLt gtLt){
		super();
		nxXx(nxXx);
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
	public JedisZAddParams(final NxXx nxXx, final boolean ch){
		super();
		nxXx(nxXx);

		if(ch){
			ch();
		}
	}

	/**
	 * 构造函数
	 *
	 * @param gtLt
	 * 		更新新的分值方式
	 * @param ch
	 * 		是否返回变更成员的数量
	 */
	public JedisZAddParams(final GtLt gtLt, final boolean ch){
		super();
		gtLt(gtLt);

		if(ch){
			ch();
		}
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
	public JedisZAddParams(final NxXx nxXx, final GtLt gtLt, final boolean ch){
		super();
		nxXx(nxXx);
		gtLt(gtLt);

		if(ch){
			ch();
		}
	}

	/**
	 * 设置更新成员方式
	 *
	 * @param nxXx
	 * 		更新成员方式
	 *
	 * @return JedisZAddParams
	 */
	private void nxXx(final NxXx nxXx){
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

	/**
	 * 更新新的分值方式
	 *
	 * @param gtLt
	 * 		更新新的分值方式
	 *
	 * @return JedisZAddParams
	 */
	private void gtLt(final GtLt gtLt){
		switch(gtLt){
			case GT:
				gt();
				break;
			case LT:
				lt();
				break;
			default:
				break;
		}
	}

}
