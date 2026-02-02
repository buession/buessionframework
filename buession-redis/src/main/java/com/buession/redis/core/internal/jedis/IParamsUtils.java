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
package com.buession.redis.core.internal.jedis;

import com.buession.lang.Order;
import com.buession.redis.core.command.BloomFilterCommands;
import com.buession.redis.core.command.CuckooFilterCommands;
import com.buession.redis.core.command.GeoCommands;
import redis.clients.jedis.bloom.BFInsertParams;
import redis.clients.jedis.bloom.BFReserveParams;
import redis.clients.jedis.bloom.CFInsertParams;
import redis.clients.jedis.bloom.CFReserveParams;
import redis.clients.jedis.params.GeoAddParams;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.GeoSearchParam;

import java.util.Optional;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class IParamsUtils {

	private IParamsUtils() {

	}

	public static BFInsertParams bfnsertParams(final BloomFilterCommands.BFInsertArgument bfInsertArgument) {
		if(bfInsertArgument == null){
			return null;
		}


		final BFInsertParams bfInsertParams = new BFInsertParams();

		Optional.ofNullable(bfInsertArgument.getCapacity()).ifPresent(bfInsertParams::capacity);
		Optional.ofNullable(bfInsertArgument.getErrorRate()).ifPresent(bfInsertParams::error);
		Optional.ofNullable(bfInsertArgument.getExpansion()).ifPresent(bfInsertParams::expansion);

		if(Boolean.TRUE.equals(bfInsertArgument.isNoCreate())){
			bfInsertParams.noCreate();
		}

		if(Boolean.TRUE.equals(bfInsertArgument.isNonScaling())){
			bfInsertParams.nonScaling();
		}

		return bfInsertParams;
	}

	public static BFReserveParams bfReserveParams(final BloomFilterCommands.BFReserveArgument bfInsertArgument) {
		if(bfInsertArgument == null){
			return null;
		}

		final BFReserveParams bfReserveParams = new BFReserveParams();

		Optional.ofNullable(bfInsertArgument.getExpansion()).ifPresent(bfReserveParams::expansion);

		if(Boolean.TRUE.equals(bfInsertArgument.isNonScaling())){
			bfReserveParams.nonScaling();
		}

		return bfReserveParams;
	}

	public static CFInsertParams cfInsertParams(final CuckooFilterCommands.CFInsertArgument cfInsertArgument) {
		if(cfInsertArgument == null){
			return null;
		}

		final CFInsertParams cfInsertParams = new CFInsertParams();

		Optional.ofNullable(cfInsertArgument.getCapacity()).ifPresent(cfInsertParams::capacity);

		if(Boolean.TRUE.equals(cfInsertArgument.isNoCreate())){
			cfInsertParams.noCreate();
		}

		return cfInsertParams;
	}

	public static CFReserveParams cfReserveParams(final CuckooFilterCommands.CFReserveArgument cfReserveArgument) {
		if(cfReserveArgument == null){
			return null;
		}

		final CFReserveParams cfReserveParams = new CFReserveParams();

		Optional.ofNullable(cfReserveArgument.getBucketSize()).ifPresent(cfReserveParams::bucketSize);
		Optional.ofNullable(cfReserveArgument.getMaxIterations()).ifPresent(cfReserveParams::maxIterations);
		Optional.ofNullable(cfReserveArgument.getExpansion()).ifPresent(cfReserveParams::expansion);

		return cfReserveParams;
	}

	public static GeoAddParams geoAddParams(final GeoCommands.GeoAddArgument geoAddArgument) {
		if(geoAddArgument == null){
			return null;
		}

		final GeoAddParams geoAddParams = new GeoAddParams();

		if(Boolean.TRUE.equals(geoAddArgument.isNx())){
			geoAddParams.nx();
		}
		if(Boolean.TRUE.equals(geoAddArgument.isXx())){
			geoAddParams.xx();
		}
		if(Boolean.TRUE.equals(geoAddArgument.isCh())){
			geoAddParams.ch();
		}

		return geoAddParams;
	}

	public static GeoRadiusParam geoRadiusParam(final GeoCommands.GeoRadiusArgument geoRadiusArgument) {
		if(geoRadiusArgument == null){
			return null;
		}

		final GeoRadiusParam geoRadiusParam = new GeoRadiusParam();

		if(Boolean.TRUE.equals(geoRadiusArgument.isWithCoord())){
			geoRadiusParam.withCoord();
		}
		if(Boolean.TRUE.equals(geoRadiusArgument.isWithDist())){
			geoRadiusParam.withDist();
		}
		if(Boolean.TRUE.equals(geoRadiusArgument.isWithHash())){
			geoRadiusParam.withHash();
		}

		if(geoRadiusArgument.getOrder() == Order.ASC){
			geoRadiusParam.sortAscending();
		}else if(geoRadiusArgument.getOrder() == Order.DESC){
			geoRadiusParam.sortDescending();
		}

		Optional.ofNullable(geoRadiusArgument.getCount()).ifPresent(geoRadiusParam::count);

		if(Boolean.TRUE.equals(geoRadiusArgument.isAny())){
			geoRadiusParam.any();
		}

		return geoRadiusParam;
	}

	public static GeoSearchParam geoSearchParam(final GeoCommands.GeoSearchArgument geoSearchArgument) {
		if(geoSearchArgument == null){
			return null;
		}

		final GeoSearchParam geoSearchParam = new GeoSearchParam();

		if(Boolean.TRUE.equals(geoSearchArgument.isWithCoord())){
			geoSearchParam.withCoord();
		}
		if(Boolean.TRUE.equals(geoSearchArgument.isWithDist())){
			geoSearchParam.withDist();
		}
		if(Boolean.TRUE.equals(geoSearchArgument.isWithHash())){
			geoSearchParam.withHash();
		}

		if(geoSearchArgument.getOrder() == Order.ASC){
			geoSearchParam.asc();
		}else if(geoSearchArgument.getOrder() == Order.DESC){
			geoSearchParam.desc();
		}

		Optional.ofNullable(geoSearchArgument.getCount()).ifPresent(geoSearchParam::count);

		if(Boolean.TRUE.equals(geoSearchArgument.isAny())){
			geoSearchParam.any();
		}

		return geoSearchParam;

	}

}
