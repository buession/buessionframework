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
package com.buession.redis.client.jedis;

import com.buession.core.utils.ReflectUtils;
import com.buession.lang.Geo;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.ShardedRedisNode;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.command.BitMapCommands;
import com.buession.redis.core.command.GeoCommands;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.command.ListCommands;
import com.buession.redis.core.command.SortedSetCommands;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.core.convert.jedis.AggregateConvert;
import com.buession.redis.core.convert.jedis.BitOperationConvert;
import com.buession.redis.core.convert.jedis.GeoArgumentConvert;
import com.buession.redis.core.convert.jedis.GeoConvert;
import com.buession.redis.core.convert.jedis.ListPositionConvert;
import com.buession.redis.core.convert.jedis.MigrateOperationConvert;
import com.buession.redis.core.convert.jedis.ScanResultConvert;
import com.buession.redis.core.convert.jedis.SetArgumentConvert;
import com.buession.redis.core.convert.jedis.SortArgumentConvert;
import com.buession.redis.core.convert.jedis.TupleConvert;
import redis.clients.jedis.BitOP;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ListPosition;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.SortingParams;
import redis.clients.jedis.ZParams;
import redis.clients.jedis.params.GeoRadiusParam;
import redis.clients.jedis.params.MigrateParams;
import redis.clients.jedis.params.SetParams;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocketFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yong.Teng
 */
public class JedisClientUtils {

	private JedisClientUtils(){
	}

	public final static List<JedisShardInfo> createJedisShardInfo(final Set<ShardedRedisNode> redisNodes,
			final int database, final int connectionTimeout, final int soTimeout, final boolean useSsl,
			final SSLSocketFactory sslSocketFactory, final SSLParameters sslParameters,
			final HostnameVerifier hostnameVerifier){
		List<JedisShardInfo> shardInfos = new ArrayList<>();
		JedisShardInfo shardInfo;

		for(ShardedRedisNode node : redisNodes){
			shardInfo = new JedisShardInfo(node.getHost(), node.getName(), node.getPort(), 0, node.getWeight(), useSsl
					, sslSocketFactory, sslParameters, hostnameVerifier);

			shardInfo.setConnectionTimeout(connectionTimeout);
			shardInfo.setSoTimeout(soTimeout);

			ReflectUtils.setField(shardInfo, "db", database);

			shardInfos.add(shardInfo);
		}

		return shardInfos;
	}

	public static boolean isInMulti(final Jedis jedis){
		return jedis.getClient().isInMulti();
	}

	public static boolean isInMulti(final ShardedJedis shardedJedis){
		for(Jedis jedis : shardedJedis.getAllShards()){
			if(jedis.getClient().isInMulti()){
				return true;
			}
		}

		return false;
	}

	public final static <V> ScanResult<List<V>> listScanResultDeconvert(final redis.clients.jedis.ScanResult<V> scanResult){
		final ScanResultConvert.ListScanResultConvert<V> scanResultConvert =
				new ScanResultConvert.ListScanResultConvert<>();
		return scanResultConvert.deconvert(scanResult);
	}

	public final static ScanResult<List<Tuple>> listTupleScanResultDeconvert(final redis.clients.jedis.ScanResult<redis.clients.jedis.Tuple> scanResult){
		final ScanResultConvert.ListTupleScanResultConvert scanResultConvert =
				new ScanResultConvert.ListTupleScanResultConvert();
		return scanResultConvert.deconvert(scanResult);
	}

	public final static <V> ScanResult<Map<V, V>> mapScanResultConvert(final redis.clients.jedis.ScanResult<Map.Entry<V, V>> scanResult){
		final ScanResultConvert.MapScanResultConvert<V, V> scanResultConvert =
				new ScanResultConvert.MapScanResultConvert<>();
		return scanResultConvert.deconvert(scanResult);
	}

	public final static SortingParams sortArgumentConvert(final KeyCommands.SortArgument sortArgument){
		final SortArgumentConvert sortArgumentConvert = new SortArgumentConvert();
		return sortArgumentConvert.convert(sortArgument);
	}

	public final static MigrateParams migrateOperationConvert(final KeyCommands.MigrateOperation migrateOperation){
		final MigrateOperationConvert migrateOperationConvert = new MigrateOperationConvert();
		return migrateOperationConvert.convert(migrateOperation);
	}

	public final static SetParams setArgumentConvert(final StringCommands.SetArgument setArgument){
		final SetArgumentConvert setArgumentConvert = new SetArgumentConvert();
		return setArgumentConvert.convert(setArgument);
	}

	public final static ListPosition listPositionConvert(final ListCommands.ListPosition position){
		final ListPositionConvert listPositionConvert = new ListPositionConvert();
		return listPositionConvert.convert(position);
	}

	public final static Set<Tuple> setTupleDeconvert(final Set<redis.clients.jedis.Tuple> tuples){
		final TupleConvert.SetTupleConvert setTupleConvert = new TupleConvert.SetTupleConvert();
		return setTupleConvert.deconvert(tuples);
	}

	public final static ZParams.Aggregate aggregateConvert(final SortedSetCommands.Aggregate aggregate){
		final AggregateConvert aggregateConvert = new AggregateConvert();
		return aggregateConvert.convert(aggregate);
	}

	public final static <V> Map<V, GeoCoordinate> geoMapConvert(final Map<V, Geo> geoMap){
		final GeoConvert.GeoMapConvert<V> geoMapConvert = new GeoConvert.GeoMapConvert<>();
		return geoMapConvert.convert(geoMap);
	}

	public final static List<Geo> geoListDeconvert(final List<GeoCoordinate> geoCoordinates){
		final GeoConvert.GeoListConvert geoMapConvert = new GeoConvert.GeoListConvert();
		return geoMapConvert.deconvert(geoCoordinates);
	}

	public final static redis.clients.jedis.GeoUnit geoUnitConvert(final GeoUnit source){
		final GeoConvert.GeoUnitConvert geoUnitConvert = new GeoConvert.GeoUnitConvert();
		return geoUnitConvert.convert(source);
	}

	public final static List<GeoRadius> listGeoRadiusDeconvert(final List<GeoRadiusResponse> geoRadiusResponses){
		final GeoConvert.GeoRadiusConvert.ListGeoRadiusConvert listGeoRadiusConvert =
				new GeoConvert.GeoRadiusConvert.ListGeoRadiusConvert();
		return listGeoRadiusConvert.deconvert(geoRadiusResponses);
	}

	public final static GeoRadiusParam geoArgumentConvert(final GeoCommands.GeoArgument geoArgument){
		final GeoArgumentConvert geoArgumentConvert = new GeoArgumentConvert();
		return geoArgumentConvert.convert(geoArgument);
	}

	public final static BitOP bitOperationConvert(final BitMapCommands.Operation bitMapOperation){
		final BitOperationConvert bitOperationConvert = new BitOperationConvert();
		return bitOperationConvert.convert(bitMapOperation);
	}

}
