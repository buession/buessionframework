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
 * | Copyright @ 2013-2020 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core.convert;

import com.buession.core.converter.MapConverter;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.SetConverter;
import com.buession.core.utils.NumberUtils;
import com.buession.lang.Geo;
import com.buession.lang.Order;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.BitOperation;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.Info;
import com.buession.redis.core.Limit;
import com.buession.redis.core.ListPosition;
import com.buession.redis.core.MigrateOperation;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.RedisServerTime;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.command.GeoCommands;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.exception.NotSupportedCommandException;
import com.buession.redis.exception.PoolException;
import com.buession.redis.exception.RedisConnectionFailureException;
import com.buession.redis.exception.RedisException;
import com.buession.redis.utils.InfoUtil;
import com.buession.redis.utils.ReturnUtils;
import com.buession.redis.utils.SafeEncoder;
import org.springframework.core.convert.converter.Converter;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.MigrateParams;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Yong.Teng
 */
public class JedisConverters extends Converters {

	public final static Converter<Aggregate, ZParams.Aggregate> aggregateJedisConverter(){
		return (source)->{
			switch(source){
				case MIN:
					return ZParams.Aggregate.MIN;
				case MAX:
					return ZParams.Aggregate.MAX;
				case SUM:
					return ZParams.Aggregate.SUM;
				default:
					return null;
			}
		};
	}

	public final static Converter<ZParams.Aggregate, Aggregate> aggregateExposeConverter(){
		return (source)->{
			switch(source){
				case MIN:
					return Aggregate.MIN;
				case MAX:
					return Aggregate.MAX;
				case SUM:
					return Aggregate.SUM;
				default:
					return null;
			}
		};
	}

	public final static Converter<BitOperation, BitOP> bitOperationJedisConverter(){
		return (source)->{
			switch(source){
				case AND:
					return BitOP.AND;
				case OR:
					return BitOP.OR;
				case NOT:
					return BitOP.NOT;
				case XOR:
					return BitOP.XOR;
				default:
					return null;
			}
		};
	}

	public final static Converter<BitOP, BitOperation> bitOperationExposeConverter(){
		return (source)->{
			switch(source){
				case AND:
					return BitOperation.AND;
				case OR:
					return BitOperation.OR;
				case NOT:
					return BitOperation.NOT;
				case XOR:
					return BitOperation.XOR;
				default:
					return null;
			}
		};
	}

	public final static Converter<ListPosition, redis.clients.jedis.ListPosition> listPositionJedisConverter(){
		return (source)->{
			switch(source){
				case BEFORE:
					return redis.clients.jedis.ListPosition.BEFORE;
				case AFTER:
					return redis.clients.jedis.ListPosition.AFTER;
				default:
					return null;
			}
		};
	}

	public final static Converter<redis.clients.jedis.ListPosition, ListPosition> listPositionExposeConverter(){
		return (source)->{
			switch(source){
				case BEFORE:
					return ListPosition.BEFORE;
				case AFTER:
					return ListPosition.AFTER;
				default:
					return null;
			}
		};
	}

	public final static Converter<MigrateOperation, MigrateParams> migrateOperationJedisConverter(){
		return (source)->{
			switch(source){
				case COPY:
					return MigrateParams.migrateParams().copy();
				case REPLACE:
					return MigrateParams.migrateParams().replace();
				default:
					return null;
			}
		};
	}

	public final static Converter<MigrateParams, MigrateOperation> migrateOperationExposeConverter(){
		return (source)->{
			if(source.getParam(MigrateOperation.COPY.name())){
				return MigrateOperation.COPY;
			}else if(source.getParam(MigrateOperation.REPLACE.name())){
				return MigrateOperation.REPLACE;
			}else{
				return null;
			}
		};
	}

	public final static Converter<GeoUnit, redis.clients.jedis.GeoUnit> geoUnitJedisConverter(){
		return (source)->{
			switch(source){
				case M:
					return redis.clients.jedis.GeoUnit.M;
				case KM:
					return redis.clients.jedis.GeoUnit.KM;
				case MI:
					return redis.clients.jedis.GeoUnit.MI;
				case FT:
					return redis.clients.jedis.GeoUnit.FT;
				default:
					return null;
			}
		};
	}

	public final static Converter<GeoCommands.GeoRadiusArgument, GeoRadiusParam> geoRadiusArgumentJedisConverter(){
		return (source)->{
			final GeoRadiusParam geoRadiusParam = new GeoRadiusParam();

			if(source.isWithCoord()){
				geoRadiusParam.withCoord();
			}

			if(source.isWithDist()){
				geoRadiusParam.withDist();
			}

			if(source.getOrder() == Order.ASC){
				geoRadiusParam.sortAscending();
			}else if(source.getOrder() == Order.DESC){
				geoRadiusParam.sortDescending();
			}

			if(source.getCount() != null){
				geoRadiusParam.count(source.getCount());
			}

			return geoRadiusParam;
		};
	}

	public final static Converter<GeoRadiusParam, GeoCommands.GeoRadiusArgument> geoRadiusArgumentExposeConverter(){
		return (source)->{
			final GeoCommands.GeoRadiusArgument.Builder builder = GeoCommands.GeoRadiusArgument.Builder.create();

			for(byte[] v : source.getByteParams()){
				String s = redis.clients.jedis.util.SafeEncoder.encode(v);

				if("withcoord".equals(s)){
					builder.withCoord();
				}else if("withdist".equals(s)){
					builder.withDist();
				}else if("asc".equals(s)){
					builder.order(Order.ASC);
				}else if("desc".equals(s)){
					builder.order(Order.DESC);
				}else if("count".equals(s)){
					builder.count(source.getParam("count"));
				}
			}

			return builder.build();
		};
	}

	public final static Converter<Geo, GeoCoordinate> geoJedisConverter(){
		return (source)->source == null ? null : new GeoCoordinate(source.getLongitude(), source.getLatitude());
	}

	public final static Converter<GeoCoordinate, Geo> geoExposeConverter(){
		return (source)->source == null ? null : new Geo(source.getLongitude(), source.getLatitude());
	}

	public final static ListConverter<Geo, GeoCoordinate> listGeoJedisConverter(){
		return new ListConverter<>((source)->new GeoCoordinate(source.getLongitude(), source.getLatitude()));
	}

	public final static ListConverter<GeoCoordinate, Geo> listGeoExposeConverter(){
		return new ListConverter<>((source)->new Geo(source.getLongitude(), source.getLatitude()));
	}

	public final static Converter<redis.clients.jedis.GeoUnit, GeoUnit> geoUnitExposeConverter(){
		return (source)->{
			switch(source){
				case M:
					return GeoUnit.M;
				case KM:
					return GeoUnit.KM;
				case MI:
					return GeoUnit.MI;
				case FT:
					return GeoUnit.FT;
				default:
					return null;
			}
		};
	}

	public final static <K> MapConverter<K, Geo, K, GeoCoordinate> mapGeoMapJedisConverter(){
		return new MapConverter<>((key)->key, (value)->new GeoCoordinate(value.getLongitude(), value.getLatitude()));
	}

	public final static <K> MapConverter<K, GeoCoordinate, K, Geo> mapGeoMapExposeConverter(){
		return new MapConverter<>((key)->key, (value)->new Geo(value.getLongitude(), value.getLatitude()));
	}

	public final static Converter<GeoRadius, GeoRadiusResponse> geoRadiusJedisConverter(){
		return (source)->{
			final GeoRadiusResponse geoRadiusResponse = new GeoRadiusResponse(source.getMember());

			geoRadiusResponse.setDistance(source.getDistance());
			geoRadiusResponse.setCoordinate(geoJedisConverter().convert(source.getGeo()));

			return geoRadiusResponse;
		};
	}

	public final static Converter<GeoRadiusResponse, GeoRadius> geoRadiusExposeConverter(){
		return (source)->{
			final GeoRadius geoRadius = new GeoRadius();

			geoRadius.setMember(source.getMember());
			geoRadius.setDistance(source.getDistance());
			geoRadius.setGeo(geoExposeConverter().convert(source.getCoordinate()));

			return geoRadius;
		};
	}

	public final static ListConverter<GeoRadius, GeoRadiusResponse> listGeoRadiusJedisConverter(){
		return new ListConverter<>((source)->{
			GeoRadiusResponse geoRadiusResponse = new GeoRadiusResponse(source.getMember());

			geoRadiusResponse.setDistance(source.getDistance());
			geoRadiusResponse.setCoordinate(geoJedisConverter().convert(source.getGeo()));

			return geoRadiusResponse;
		});
	}

	public final static ListConverter<GeoRadiusResponse, GeoRadius> listGeoRadiusExposeConverter(){
		return new ListConverter<>((source)->{
			GeoRadius geoRadius = new GeoRadius();

			geoRadius.setMember(source.getMember());
			geoRadius.setDistance(source.getDistance());
			geoRadius.setGeo(geoExposeConverter().convert(source.getCoordinate()));

			return geoRadius;
		});
	}

	public final static Converter<Tuple, redis.clients.jedis.Tuple> tupleJedisConverter(){
		return (source)->source == null ? null : new redis.clients.jedis.Tuple(source.getBinaryElement(),
				source.getScore());
	}

	public final static Converter<redis.clients.jedis.Tuple, Tuple> tupleExposeConverter(){
		return (source)->source == null ? null : new Tuple(source.getBinaryElement(), source.getScore());
	}

	public final static SetConverter<Tuple, redis.clients.jedis.Tuple> setTupleJedisConverter(){
		return new SetConverter<>((source)->new redis.clients.jedis.Tuple(source.getElement(), source.getScore()));
	}

	public final static SetConverter<redis.clients.jedis.Tuple, Tuple> setTupleExposeConverter(){
		return new SetConverter<>((source)->new Tuple(source.getElement(), source.getScore()));
	}

	public final static Converter<KeyCommands.SortArgument, SortingParams> sortArgumentJedisConverter(){
		return (source)->{
			final SortingParams sortingParams = new SortingParams();

			if(source.getBy() != null){
				sortingParams.by(source.getBy());
			}

			if(source.getOrder() == Order.ASC){
				sortingParams.asc();
			}else if(source.getOrder() == Order.DESC){
				sortingParams.desc();
			}

			if(source.getLimit() != null){
				Limit limit = source.getLimit();
				sortingParams.limit((int) limit.getOffset(), (int) limit.getCount());
			}

			if(source.getAlpha() != null){
				sortingParams.alpha();
			}

			return sortingParams;
		};
	}

	public final static Converter<SortingParams, KeyCommands.SortArgument> sortArgumentExposeConverter(){
		return (source)->{
			final KeyCommands.SortArgument.Builder builder = KeyCommands.SortArgument.Builder.create();

			Collection<byte[]> collections = source.getParams();
			Iterator<byte[]> iterator = collections.iterator();

			while(iterator.hasNext()){
				byte[] v = iterator.next();

				if(v == Protocol.Keyword.BY.raw){
					v = iterator.next();
					builder.by(SafeEncoder.encode(v));
				}else if(v == Protocol.Keyword.ASC.raw){
					builder.asc();
				}else if(v == Protocol.Keyword.DESC.raw){
					builder.desc();
				}else if(v == Protocol.Keyword.LIMIT.raw){
					byte[] start = iterator.next();
					byte[] end = iterator.next();

					builder.limit(NumberUtils.bytes2long(start), NumberUtils.bytes2long(end));
				}else if(v == Protocol.Keyword.ALPHA.raw){
					builder.alpha();
				}
			}

			return builder.build();
		};
	}

	public final static Converter<StringCommands.SetArgument, SetParams> setArgumentJedisConverter(){
		return (source)->{
			final SetParams setParams = new SetParams();

			if(source.getEx() != null){
				setParams.ex(source.getEx().intValue());
			}

			if(source.getPx() != null){
				setParams.px(source.getPx().intValue());
			}

			if(source.getNxXx() == NxXx.NX){
				setParams.nx();
			}else if(source.getNxXx() == NxXx.XX){
				setParams.xx();
			}

			return setParams;
		};
	}

	public final static Converter<SetParams, StringCommands.SetArgument> setArgumentExposeConverter(){
		return (source)->{
			final StringCommands.SetArgument.Builder builder = StringCommands.SetArgument.Builder.create();
			byte[][] params = source.getByteParams();

			for(int i = 0; i < params.length; i++){
				String s = SafeEncoder.encode(params[i]);

				if("ex".equals(s)){
					builder.ex(NumberUtils.bytes2long(params[++i]));
				}else if("px".equals(s)){
					builder.px(NumberUtils.bytes2long(params[++i]));
				}else if("nx".equals(s)){
					builder.nxXX(NxXx.NX);
				}else if("xx".equals(s)){
					builder.nxXX(NxXx.XX);
				}
			}

			return builder.build();
		};
	}

	public final static <K, V> Converter<ScanResult<Map<K, V>>, redis.clients.jedis.ScanResult<Map.Entry<K, V>>> mapScanResultJedisConverter(){
		return (source)->new redis.clients.jedis.ScanResult<>(source.getCursor(),
				new ArrayList<>(source.getResults().entrySet()));
	}

	public final static <K, V> Converter<redis.clients.jedis.ScanResult<Map.Entry<K, V>>, ScanResult<Map<K, V>>> mapScanResultExposeConverter(){
		return (source)->{
			Map<K, V> data = source.getResult().stream().collect(Collectors.toMap(item->item.getKey(),
					item->item.getValue(), (a, b)->a, LinkedHashMap::new));

			return new ScanResult<>(source.getCursorAsBytes(), data);
		};
	}

	public final static <V> Converter<ScanResult<List<V>>, redis.clients.jedis.ScanResult<V>> listScanResultJedisConverter(){
		return (source)->new redis.clients.jedis.ScanResult<>(source.getCursor(), source.getResults());
	}

	public final static <V> Converter<redis.clients.jedis.ScanResult<V>, ScanResult<List<V>>> listScanResultExposeConverter(){
		return (source)->new ScanResult<>(source.getCursorAsBytes(), source.getResult());
	}

	public final static Converter<ScanResult<List<Tuple>>, redis.clients.jedis.ScanResult<redis.clients.jedis.Tuple>> listTupleScanResultJedisConverter(){
		return (source)->new redis.clients.jedis.ScanResult<>(source.getCursor(), new ListConverter<Tuple,
				redis.clients.jedis.Tuple>((item)->new redis.clients.jedis.Tuple(item.getBinaryElement(),
				item.getScore())).convert(source.getResults()));
	}

	public final static Converter<redis.clients.jedis.ScanResult<redis.clients.jedis.Tuple>, ScanResult<List<Tuple>>> listTupleScanResultExposeConverter(){
		return (source)->new ScanResult<>(source.getCursor(),
				new ListConverter<redis.clients.jedis.Tuple, Tuple>((item)->new Tuple(item.getBinaryElement(),
						item.getScore())).convert(source.getResult()));
	}

	public final static Converter<String, Info> infoConvert(){
		return (source)->InfoUtil.convert(source);
	}

	public final static Converter<List<String>, RedisServerTime> redisServerTimeConvert(){
		return (source)->ReturnUtils.redisServerTime(source);
	}

	public final static RedisException exceptionConvert(final Exception e){
		if(e instanceof JedisConnectionException){
			if(e.getMessage().contains("pool")){
				return new PoolException(e.getMessage(), e);
			}else{
				return new RedisConnectionFailureException(e.getMessage(), e);
			}
		}else if(e instanceof NotSupportedCommandException){
			return (NotSupportedCommandException) e;
		}else{
			return new RedisException(e.getMessage(), e);
		}
	}

}
