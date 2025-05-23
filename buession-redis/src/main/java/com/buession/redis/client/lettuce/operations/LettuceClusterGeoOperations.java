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
package com.buession.redis.client.lettuce.operations;

import com.buession.core.builder.ListBuilder;
import com.buession.core.converter.ListConverter;
import com.buession.core.converter.SetListConverter;
import com.buession.lang.Geo;
import com.buession.redis.client.lettuce.LettuceClusterClient;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.command.CommandArguments;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.internal.convert.lettuce.params.GeoUnitConverter;
import com.buession.redis.core.internal.convert.lettuce.response.GeoCoordinateConverter;
import com.buession.redis.core.internal.convert.lettuce.response.GeoRadiusGeneralResultConverter;
import com.buession.redis.core.internal.convert.lettuce.response.GeoRadiusResponseConverter;
import com.buession.redis.core.internal.lettuce.LettuceGeoArgs;
import com.buession.redis.utils.SafeEncoder;
import io.lettuce.core.GeoArgs;
import io.lettuce.core.GeoCoordinates;
import io.lettuce.core.GeoWithin;
import io.lettuce.core.Value;

import java.util.List;
import java.util.Map;

/**
 * Lettuce 集群模式地理位置命令操作
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public final class LettuceClusterGeoOperations extends AbstractGeoOperations<LettuceClusterClient> {

	public LettuceClusterGeoOperations(final LettuceClusterClient client) {
		super(client);
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude) {
		final CommandArguments args = CommandArguments.create(key).add(member)
				.add(longitude).add(latitude);

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.GEOADD,
					(cmd)->cmd.geoadd(key, longitude, latitude, member), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.GEOADD,
					(cmd)->cmd.geoadd(key, longitude, latitude, member), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.GEOADD,
					(cmd)->cmd.geoadd(key, longitude, latitude, member), (v)->v)
					.run(args);
		}
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(memberCoordinates);
		return geoAdd(key, memberCoordinates, args);
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates) {
		final CommandArguments args = CommandArguments.create(key).add(memberCoordinates);
		return geoAdd(key, memberCoordinates, args);
	}

	@Override
	public List<String> geoHash(final String key, final String... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		final byte[] bKey = SafeEncoder.encode(key);
		final byte[][] bMembers = SafeEncoder.encode(members);
		final ListConverter<Value<String>, String> listConverter = new ListConverter<>(Value::getValue);

		return geoHash(bKey, bMembers, listConverter, args);
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		final ListConverter<Value<String>, byte[]> listConverter = new ListConverter<>(
				(v)->SafeEncoder.encode(v.getValue()));

		return geoHash(key, members, listConverter, args);
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members) {
		final CommandArguments args = CommandArguments.create(key).add(members);
		final ListConverter<GeoCoordinates, Geo> listGeoCoordinateConverter = GeoCoordinateConverter.listConverter();

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.GEOPOS, (cmd)->cmd.geopos(key, members),
					listGeoCoordinateConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.GEOPOS,
					(cmd)->cmd.geopos(key, members), listGeoCoordinateConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.GEOPOS, (cmd)->cmd.geopos(key, members),
					listGeoCoordinateConverter)
					.run(args);
		}
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2) {
		final CommandArguments args = CommandArguments.create(key).add(member1)
				.add(member2);
		return geoDist(key, member1, member2, GeoArgs.Unit.m, args);
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member1)
				.add(member2).add(unit);
		final GeoArgs.Unit geoArgsUnit = (new GeoUnitConverter()).convert(unit);

		return geoDist(key, member1, member2, geoArgsUnit, args);
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude)
				.add(latitude).add(radius).add(unit);
		final GeoArgs.Unit geoArgsUnit = (new GeoUnitConverter()).convert(unit);
		final SetListConverter<byte[], GeoRadius> setListGeoRadiusGeneralResultConverter =
				GeoRadiusGeneralResultConverter.setListConverter();

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoArgsUnit),
					setListGeoRadiusGeneralResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoArgsUnit),
					setListGeoRadiusGeneralResultConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoArgsUnit),
					setListGeoRadiusGeneralResultConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
									 final double radius, final GeoUnit unit,
									 final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude)
				.add(latitude).add(radius).add(unit)
				.add(geoRadiusArgument);
		final GeoArgs.Unit geoArgsUnit = (new GeoUnitConverter()).convert(unit);
		final GeoArgs geoArgs = LettuceGeoArgs.from(geoRadiusArgument);
		final ListConverter<GeoWithin<byte[]>, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoArgsUnit, geoArgs),
					listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoArgsUnit, geoArgs),
					listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.GEORADIUS,
					(cmd)->cmd.georadius(key, longitude, latitude, radius, geoArgsUnit, geoArgs),
					listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude)
				.add(latitude).add(radius).add(unit);
		return geoRadiusRo(args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(longitude)
				.add(latitude).add(radius).add(unit);
		return geoRadiusRo(args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final String key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude)
				.add(latitude).add(radius).add(unit)
				.add(geoRadiusArgument);
		return geoRadiusRo(args);
	}

	@Override
	public List<GeoRadius> geoRadiusRo(final byte[] key, final double longitude, final double latitude,
									   final double radius, final GeoUnit unit,
									   final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create(key).add(longitude)
				.add(latitude).add(radius).add(unit)
				.add(geoRadiusArgument);
		return geoRadiusRo(args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius)
				.add(unit);
		final GeoArgs.Unit geoArgsUnit = (new GeoUnitConverter()).convert(unit);
		final SetListConverter<byte[], GeoRadius> setListGeoRadiusGeneralResultConverter =
				GeoRadiusGeneralResultConverter.setListConverter();

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusbymember(key, member, radius, geoArgsUnit),
					setListGeoRadiusGeneralResultConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusbymember(key, member, radius, geoArgsUnit),
					setListGeoRadiusGeneralResultConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusbymember(key, member, radius, geoArgsUnit),
					setListGeoRadiusGeneralResultConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
											 final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius)
				.add(unit).add(geoRadiusArgument);
		final GeoArgs.Unit geoArgsUnit = (new GeoUnitConverter()).convert(unit);
		final GeoArgs geoArgs = LettuceGeoArgs.from(geoRadiusArgument);
		final ListConverter<GeoWithin<byte[]>, GeoRadius> listGeoRadiusResponseConverter =
				GeoRadiusResponseConverter.listConverter();

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusbymember(key, member, radius, geoArgsUnit, geoArgs),
					listGeoRadiusResponseConverter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusbymember(key, member, radius, geoArgsUnit, geoArgs),
					listGeoRadiusResponseConverter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.GEORADIUSBYMEMBER,
					(cmd)->cmd.georadiusbymember(key, member, radius, geoArgsUnit, geoArgs),
					listGeoRadiusResponseConverter)
					.run(args);
		}
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius)
				.add(unit);
		return geoRadiusByMemberRo(args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius)
				.add(unit);
		return geoRadiusByMemberRo(args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final String key, final String member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius)
				.add(unit).add(geoRadiusArgument);
		return geoRadiusByMemberRo(args);
	}

	@Override
	public List<GeoRadius> geoRadiusByMemberRo(final byte[] key, final byte[] member, final double radius,
											   final GeoUnit unit, final GeoRadiusArgument geoRadiusArgument) {
		final CommandArguments args = CommandArguments.create(key).add(member).add(radius)
				.add(unit).add(geoRadiusArgument);
		return geoRadiusByMemberRo(args);
	}

	@Override
	protected Long geoAdd(final byte[] key, final ListBuilder<Object> lngLatMemberBuilder,
						  final CommandArguments args) {
		final Object[] lngLatMembers = lngLatMemberBuilder.build().toArray(new Object[]{});

		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.GEOADD,
					(cmd)->cmd.geoadd(key, lngLatMembers), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.GEOADD,
					(cmd)->cmd.geoadd(key, lngLatMembers), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.GEOADD, (cmd)->cmd.geoadd(key, lngLatMembers),
					(v)->v)
					.run(args);
		}
	}

	private <V> List<V> geoHash(final byte[] key, final byte[][] members,
								final ListConverter<Value<String>, V> converter, final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.GEOHASH,
					(cmd)->cmd.geohash(key, members), converter)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.GEOHASH,
					(cmd)->cmd.geohash(key, members), converter)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.GEOHASH, (cmd)->cmd.geohash(key, members),
					converter)
					.run(args);
		}
	}

	private Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoArgs.Unit unit,
						   final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<>(client, ProtocolCommand.GEODIST,
					(cmd)->cmd.geodist(key, member1, member2, unit), (v)->v)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<>(client, ProtocolCommand.GEODIST,
					(cmd)->cmd.geodist(key, member1, member2, unit), (v)->v)
					.run(args);
		}else{
			return new LettuceClusterCommand<>(client, ProtocolCommand.GEODIST,
					(cmd)->cmd.geodist(key, member1, member2, unit), (v)->v)
					.run(args);
		}
	}

	private List<GeoRadius> geoRadiusRo(final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<List<GeoRadius>, List<GeoRadius>>(client,
					ProtocolCommand.GEORADIUS_RO)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<List<GeoRadius>, List<GeoRadius>>(client,
					ProtocolCommand.GEORADIUS_RO)
					.run(args);
		}else{
			return new LettuceClusterCommand<List<GeoRadius>, List<GeoRadius>>(client, ProtocolCommand.GEORADIUS_RO)
					.run(args);
		}
	}

	private List<GeoRadius> geoRadiusByMemberRo(final CommandArguments args) {
		if(isPipeline()){
			return new LettuceClusterPipelineCommand<List<GeoRadius>, List<GeoRadius>>(client,
					ProtocolCommand.GEORADIUSBYMEMBER_RO)
					.run(args);
		}else if(isTransaction()){
			return new LettuceClusterTransactionCommand<List<GeoRadius>, List<GeoRadius>>(client,
					ProtocolCommand.GEORADIUSBYMEMBER_RO)
					.run(args);
		}else{
			return new LettuceClusterCommand<List<GeoRadius>, List<GeoRadius>>(client,
					ProtocolCommand.GEORADIUSBYMEMBER_RO)
					.run(args);
		}
	}

}
