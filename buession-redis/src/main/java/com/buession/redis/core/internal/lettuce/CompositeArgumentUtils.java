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
package com.buession.redis.core.internal.lettuce;

import com.buession.lang.Order;
import com.buession.redis.core.ExpireOption;
import com.buession.redis.core.command.args.BitFieldArgument;
import com.buession.redis.core.command.args.GeoAddArgument;
import com.buession.redis.core.command.args.GeoRadiusArgument;
import com.buession.redis.core.command.args.GeoSearchArgument;
import com.buession.redis.core.command.args.GetExArgument;
import com.buession.redis.core.command.args.HSetExArgument;
import com.buession.redis.core.command.args.JsonGetArgument;
import com.buession.redis.core.command.args.TrackingArgument;
import io.lettuce.core.BitFieldArgs;
import io.lettuce.core.ExpireArgs;
import io.lettuce.core.GeoAddArgs;
import io.lettuce.core.GeoArgs;
import io.lettuce.core.HGetExArgs;
import io.lettuce.core.HSetExArgs;
import io.lettuce.core.TrackingArgs;
import io.lettuce.core.json.arguments.JsonGetArgs;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

/**
 *
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public class CompositeArgumentUtils {

	private CompositeArgumentUtils() {
	}

	public static BitFieldArgs bitFieldArgs(final BitFieldArgument bitFieldArgument) {
		if(bitFieldArgument == null){
			return null;
		}

		final BitFieldArgs bitFieldArgs = new BitFieldArgs();

		final BitFieldArgument.SetOp setOp = bitFieldArgument.getSet();
		if(setOp != null){
			bitFieldArgs.set(setOp.getBitFieldType().isSigned() ? BitFieldArgs.signed(
							setOp.getBitFieldType().getBits()) : BitFieldArgs.unsigned(setOp.getBitFieldType().getBits()),
					setOp.getOffset(), setOp.getValue());
		}

		final BitFieldArgument.GetOp getOp = bitFieldArgument.getGet();
		if(getOp != null){
			bitFieldArgs.get(getOp.getBitFieldType().isSigned() ? BitFieldArgs.signed(
							getOp.getBitFieldType().getBits()) : BitFieldArgs.unsigned(getOp.getBitFieldType().getBits()),
					getOp.getOffset());
		}

		final BitFieldArgument.IncrbyOp incrByOp = bitFieldArgument.getIncrBy();
		if(incrByOp != null){
			bitFieldArgs.incrBy(incrByOp.getBitFieldType().isSigned() ? BitFieldArgs.signed(
							incrByOp.getBitFieldType().getBits()) : BitFieldArgs.unsigned(incrByOp.getBitFieldType().getBits()),
					incrByOp.getOffset(), incrByOp.getValue());
		}

		if(bitFieldArgument.getOverflow() != null){
			switch(bitFieldArgument.getOverflow()){
				case FAIL:
					bitFieldArgs.overflow(BitFieldArgs.OverflowType.FAIL);
					break;
				case SAT:
					bitFieldArgs.overflow(BitFieldArgs.OverflowType.SAT);
					break;
				case WRAP:
					bitFieldArgs.overflow(BitFieldArgs.OverflowType.WRAP);
					break;
				default:
					break;
			}
		}

		return bitFieldArgs;
	}

	public static TrackingArgs trackingArgs(final TrackingArgument trackingArgument) {
		if(trackingArgument == null){
			return null;
		}

		final TrackingArgs trackingArgs = new TrackingArgs();

		Optional.ofNullable(trackingArgument.getRedirect()).ifPresent(trackingArgs::redirect);
		Optional.ofNullable(trackingArgument.getPrefixes()).ifPresent(trackingArgs::prefixes);
		if(Boolean.TRUE.equals(trackingArgument.getBcast())){
			trackingArgs.bcast();
		}
		if(Boolean.TRUE.equals(trackingArgument.getOptin())){
			trackingArgs.optin();
		}
		if(Boolean.TRUE.equals(trackingArgument.getOptout())){
			trackingArgs.optout();
		}
		if(Boolean.TRUE.equals(trackingArgument.getNoloop())){
			trackingArgs.noloop();
		}

		return trackingArgs;
	}

	public static GeoAddArgs geoAddArgs(final GeoAddArgument geoAddArgument) {
		if(geoAddArgument == null){
			return null;
		}

		final GeoAddArgs geoAddArgs = new GeoAddArgs();
		if(Boolean.TRUE.equals(geoAddArgument.isNx())){
			geoAddArgs.nx();
		}
		if(Boolean.TRUE.equals(geoAddArgument.isXx())){
			geoAddArgs.xx();
		}
		if(Boolean.TRUE.equals(geoAddArgument.isCh())){
			geoAddArgs.ch();
		}

		return geoAddArgs;
	}

	public static GeoArgs geoArgs(final GeoRadiusArgument geoRadiusArgument) {
		if(geoRadiusArgument == null){
			return null;
		}

		final GeoArgs geoArgs = new GeoArgs();

		if(Boolean.TRUE.equals(geoRadiusArgument.isWithCoord())){
			geoArgs.withCoordinates();
		}
		if(Boolean.TRUE.equals(geoRadiusArgument.isWithDist())){
			geoArgs.withDistance();
		}
		if(Boolean.TRUE.equals(geoRadiusArgument.isWithHash())){
			geoArgs.withHash();
		}

		if(geoRadiusArgument.getOrder() == Order.ASC){
			geoArgs.sort(GeoArgs.Sort.asc);
		}else if(geoRadiusArgument.getOrder() == Order.DESC){
			geoArgs.sort(GeoArgs.Sort.desc);
		}

		Optional.ofNullable(geoRadiusArgument.getCount()).ifPresent(geoArgs::withCount);

		if(geoRadiusArgument.getCount() != null){
			geoArgs.withCount(geoRadiusArgument.getCount(), Boolean.TRUE.equals(geoRadiusArgument.isAny()));
		}

		return geoArgs;
	}

	public static GeoArgs geoArgs(final GeoSearchArgument geoSearchArgument) {
		if(geoSearchArgument == null){
			return null;
		}

		final GeoArgs geoArgs = new GeoArgs();

		if(Boolean.TRUE.equals(geoSearchArgument.isWithCoord())){
			geoArgs.withCoordinates();
		}
		if(Boolean.TRUE.equals(geoSearchArgument.isWithDist())){
			geoArgs.withDistance();
		}
		if(Boolean.TRUE.equals(geoSearchArgument.isWithHash())){
			geoArgs.withHash();
		}

		if(geoSearchArgument.getOrder() == Order.ASC){
			geoArgs.sort(GeoArgs.Sort.asc);
		}else if(geoSearchArgument.getOrder() == Order.DESC){
			geoArgs.sort(GeoArgs.Sort.desc);
		}

		Optional.ofNullable(geoSearchArgument.getCount()).ifPresent(geoArgs::withCount);

		if(geoSearchArgument.getCount() != null){
			geoArgs.withCount(geoSearchArgument.getCount(), Boolean.TRUE.equals(geoSearchArgument.isAny()));
		}

		return geoArgs;
	}

	public static ExpireArgs expireArgs(final ExpireOption expireOption) {
		if(expireOption == null){
			return null;
		}

		final ExpireArgs expireArgs = new ExpireArgs();

		switch(expireOption){
			case NX:
				expireArgs.nx();
				break;
			case XX:
				expireArgs.xx();
				break;
			case GT:
				expireArgs.gt();
				break;
			case LT:
				expireArgs.lt();
				break;
			default:
				break;
		}

		return expireArgs;
	}

	public static HGetExArgs hGetExArgs(final GetExArgument getExArgument) {
		if(getExArgument == null || getExArgument.getType() == null){
			return null;
		}

		final HGetExArgs hGetExArgs = new HGetExArgs();

		switch(getExArgument.getType()){
			case EX -> hGetExArgs.ex(Duration.ofSeconds(getExArgument.getValue()));
			case EXAT -> hGetExArgs.exAt(Instant.ofEpochSecond(getExArgument.getValue()));
			case PX -> hGetExArgs.px(Duration.ofMillis(getExArgument.getValue()));
			case PXAT -> hGetExArgs.pxAt(Instant.ofEpochMilli(getExArgument.getValue()));
			case PERSIST -> hGetExArgs.persist();
		}

		return hGetExArgs;
	}

	public static HSetExArgs hSetExArgs(final HSetExArgument hSetExArgument) {
		if(hSetExArgument == null){
			return null;
		}

		final HSetExArgs hSetExArgs = new HSetExArgs();

		if(hSetExArgument.getType() == null){
			switch(hSetExArgument.getType()){
				case EX -> hSetExArgs.ex(Duration.ofSeconds(hSetExArgument.getValue()));
				case EXAT -> hSetExArgs.exAt(Instant.ofEpochSecond(hSetExArgument.getValue()));
				case PX -> hSetExArgs.px(Duration.ofMillis(hSetExArgument.getValue()));
				case PXAT -> hSetExArgs.pxAt(Instant.ofEpochMilli(hSetExArgument.getValue()));
				case KEEPTTL -> hSetExArgs.keepttl();
			}
		}

		if(hSetExArgument.getFnxFxx() != null){
			switch(hSetExArgument.getFnxFxx()){
				case FNX -> hSetExArgs.fnx();
				case FXX -> hSetExArgs.fxx();
			}
		}

		return hSetExArgs;
	}

	public static JsonGetArgs jsonGetArgs(final JsonGetArgument jsonGetArgument) {
		if(jsonGetArgument == null){
			return null;
		}

		final JsonGetArgs jsonGetArgs = new JsonGetArgs();

		Optional.ofNullable(jsonGetArgument.getIndent()).map(jsonGetArgs::indent);
		Optional.ofNullable(jsonGetArgument.getNewline()).map(jsonGetArgs::newline);
		Optional.ofNullable(jsonGetArgument.getSpace()).map(jsonGetArgs::space);

		return jsonGetArgs;
	}

}
