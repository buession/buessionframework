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
package com.buession.redis.core.internal.lettuce.args;

import com.buession.lang.Order;
import com.buession.redis.core.GeoStoreOption;
import com.buession.redis.core.command.args.GeoRadiusArgument;
import io.lettuce.core.GeoRadiusStoreArgs;

/**
 * Lettuce {@link GeoRadiusStoreArgs} 扩展
 *
 * @param <K>
 * 		值类型
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public final class LettuceGeoRadiusStoreArgs<K> extends GeoRadiusStoreArgs<K> {

	/**
	 * 构造函数
	 */
	public LettuceGeoRadiusStoreArgs() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param key
	 * 		存储 Key
	 * @param storeOption
	 * 		存储方式
	 * @param geoRadiusArgument
	 *        {@link GeoRadiusArgument}
	 */
	public LettuceGeoRadiusStoreArgs(final K key, final GeoStoreOption storeOption,
									 final GeoRadiusArgument geoRadiusArgument) {
		this(key, storeOption);
		if(geoRadiusArgument != null){
			if(geoRadiusArgument.getOrder() == Order.ASC){
				asc();
			}else if(geoRadiusArgument.getOrder() == Order.DESC){
				desc();
			}
		}
	}

	/**
	 * 构造函数
	 *
	 * @param key
	 * 		存储 Key
	 * @param storeOption
	 * 		存储方式
	 * @param geoRadiusArgument
	 *        {@link GeoRadiusArgument}
	 * @param count
	 * 		返回数量
	 */
	public LettuceGeoRadiusStoreArgs(final K key, final GeoStoreOption storeOption,
									 final GeoRadiusArgument geoRadiusArgument, final Integer count) {
		this(key, storeOption, geoRadiusArgument);
		withCount(count);
	}

	/**
	 * 构造函数
	 *
	 * @param key
	 * 		存储 Key
	 * @param storeOption
	 * 		存储方式
	 * @param geoRadiusArgument
	 *        {@link GeoRadiusArgument}
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 */
	public LettuceGeoRadiusStoreArgs(final K key, final GeoStoreOption storeOption,
									 final GeoRadiusArgument geoRadiusArgument, final Integer count,
									 final Boolean any) {
		this(key, storeOption, geoRadiusArgument);
		withCount(count);
	}

	/**
	 * 构造函数
	 *
	 * @param key
	 * 		存储 Key
	 * @param storeOption
	 * 		存储方式
	 * @param count
	 * 		返回数量
	 */
	public LettuceGeoRadiusStoreArgs(final K key, final GeoStoreOption storeOption, final Integer count) {
		this(key, storeOption);
		withCount(count);
	}

	/**
	 * 构造函数
	 *
	 * @param key
	 * 		存储 Key
	 * @param storeOption
	 * 		存储方式
	 * @param count
	 * 		返回数量
	 * @param any
	 * 		ANY
	 */
	public LettuceGeoRadiusStoreArgs(final K key, final GeoStoreOption storeOption, final Integer count,
									 final Boolean any) {
		this(key, storeOption);
		withCount(count);
	}

	/**
	 * 构造函数
	 *
	 * @param key
	 * 		存储 Key
	 * @param storeOption
	 * 		存储方式
	 */
	public LettuceGeoRadiusStoreArgs(final K key, final GeoStoreOption storeOption) {
		super();
		if(storeOption == GeoStoreOption.STOREDIST){
			withStoreDist(key);
		}else if(storeOption == GeoStoreOption.STORE){
			withStore(key);
		}
	}

}
