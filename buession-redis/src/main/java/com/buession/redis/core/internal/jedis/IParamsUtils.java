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
import com.buession.redis.core.command.args.BFInsertArgument;
import com.buession.redis.core.command.args.BFReserveArgument;
import com.buession.redis.core.command.args.CFInsertArgument;
import com.buession.redis.core.command.args.CFReserveArgument;
import com.buession.redis.core.command.args.GeoAddArgument;
import com.buession.redis.core.command.args.GeoRadiusArgument;
import com.buession.redis.core.command.args.GeoSearchArgument;
import com.buession.redis.core.command.args.GetExArgument;
import com.buession.redis.core.command.args.HSetExArgument;
import com.buession.redis.core.command.args.LPosArgument;
import redis.clients.jedis.bloom.BFInsertParams;
import redis.clients.jedis.bloom.BFReserveParams;
import redis.clients.jedis.bloom.CFInsertParams;
import redis.clients.jedis.bloom.CFReserveParams;
import redis.clients.jedis.params.GeoAddParams;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.GeoSearchParam;
import redis.clients.jedis.params.HGetExParams;
import redis.clients.jedis.params.HSetExParams;
import redis.clients.jedis.params.LPosParams;

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

	public static BFInsertParams bfnsertParams(final BFInsertArgument bfInsertArgument) {
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

	public static BFReserveParams bfReserveParams(final BFReserveArgument bfInsertArgument) {
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

	public static CFInsertParams cfInsertParams(final CFInsertArgument cfInsertArgument) {
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

	public static CFReserveParams cfReserveParams(final CFReserveArgument cfReserveArgument) {
		if(cfReserveArgument == null){
			return null;
		}

		final CFReserveParams cfReserveParams = new CFReserveParams();

		Optional.ofNullable(cfReserveArgument.getBucketSize()).ifPresent(cfReserveParams::bucketSize);
		Optional.ofNullable(cfReserveArgument.getMaxIterations()).ifPresent(cfReserveParams::maxIterations);
		Optional.ofNullable(cfReserveArgument.getExpansion()).ifPresent(cfReserveParams::expansion);

		return cfReserveParams;
	}

	public static GeoAddParams geoAddParams(final GeoAddArgument geoAddArgument) {
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

	public static GeoRadiusParam geoRadiusParam(final GeoRadiusArgument geoRadiusArgument) {
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

	public static GeoSearchParam geoSearchParam(final GeoSearchArgument geoSearchArgument) {
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

	public static HGetExParams hGetExParams(final GetExArgument getExArgument) {
		if(getExArgument == null || getExArgument.getType() == null){
			return null;
		}

		final HGetExParams hGetExParams = new HGetExParams();

		switch(getExArgument.getType()){
			case EX -> hGetExParams.ex(getExArgument.getValue());
			case EXAT -> hGetExParams.exAt(getExArgument.getValue());
			case PX -> hGetExParams.px(getExArgument.getValue());
			case PXAT -> hGetExParams.pxAt(getExArgument.getValue());
			case PERSIST -> hGetExParams.persist();
		}

		return hGetExParams;
	}

	public static HSetExParams hSetExParams(final HSetExArgument hSetExArgument) {
		if(hSetExArgument == null){
			return null;
		}

		final HSetExParams hSetExParams = new HSetExParams();

		if(hSetExArgument.getType() == null){
			switch(hSetExArgument.getType()){
				case EX -> hSetExParams.ex(hSetExArgument.getValue());
				case EXAT -> hSetExParams.exAt(hSetExArgument.getValue());
				case PX -> hSetExParams.px(hSetExArgument.getValue());
				case PXAT -> hSetExParams.pxAt(hSetExArgument.getValue());
				case KEEPTTL -> hSetExParams.keepTtl();
			}
		}

		if(hSetExArgument.getFnxFxx() != null){
			switch(hSetExArgument.getFnxFxx()){
				case FNX -> hSetExParams.fnx();
				case FXX -> hSetExParams.fxx();
			}
		}

		return hSetExParams;
	}

	public static LPosParams lPosParams(final LPosArgument lPosArgument) {
		if(lPosArgument == null){
			return null;
		}

		final LPosParams lPosParams = new LPosParams();

		Optional.ofNullable(lPosArgument.getRank()).ifPresent(lPosParams::rank);
		Optional.ofNullable(lPosArgument.getMaxLen()).ifPresent(lPosParams::maxlen);

		return lPosParams;
	}

}
