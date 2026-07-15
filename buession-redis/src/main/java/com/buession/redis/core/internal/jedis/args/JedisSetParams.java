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
package com.buession.redis.core.internal.jedis.args;

import com.buession.redis.core.command.args.PxExType;
import com.buession.redis.core.command.args.string.SetType;
import redis.clients.jedis.params.SetParams;
import redis.clients.jedis.util.CompareCondition;

/**
 * Jedis {@link SetParams} 扩展
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class JedisSetParams extends SetParams {

	/**
	 * 构造函数
	 */
	public JedisSetParams() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param setType
	 *        {@link SetType}
	 */
	public JedisSetParams(final SetType setType) {
		super();
		setType(setType);
	}

	/**
	 * 构造函数
	 *
	 * @param setType
	 *        {@link SetType}
	 * @param pxExType
	 * 		过期时间类型
	 * @param expires
	 * 		过期时间
	 */
	public JedisSetParams(final SetType setType, final PxExType pxExType, final long expires) {
		super();
		setType(setType);
		pxEx(pxExType, expires);
	}

	/**
	 * 构造函数
	 *
	 * @param pxExType
	 * 		过期时间类型
	 * @param expires
	 * 		过期时间
	 */
	public JedisSetParams(final PxExType pxExType, final long expires) {
		super();
		pxEx(pxExType, expires);
	}

	private void setType(final SetType setType) {
		if(setType != null){
			if(setType.getNxXx() != null){
				switch(setType.getNxXx()){
					case NX -> nx();
					case XX -> xx();
				}
			}else if(setType.getCompareCondition() != null && setType.getCompareValue() != null){
				switch(setType.getCompareCondition()){
					case IFEQ -> condition(CompareCondition.valueEq(setType.getCompareValue()));
					case IFDEQ -> condition(CompareCondition.digestEq(setType.getCompareValue()));
					case IFNE -> condition(CompareCondition.valueNe(setType.getCompareValue()));
					case IFDNE -> condition(CompareCondition.digestNe(setType.getCompareValue()));
				}
			}
		}
	}

	private void pxEx(final PxExType pxExType, final long expires) {
		if(pxExType == PxExType.EX){
			ex(expires);
		}else if(pxExType == PxExType.EXAT){
			exAt(expires);
		}else if(pxExType == PxExType.PX){
			px(expires);
		}else if(pxExType == PxExType.PXAT){
			pxAt(expires);
		}else if(pxExType == PxExType.KEEPTTL){
			keepTtl();
		}
	}

}
