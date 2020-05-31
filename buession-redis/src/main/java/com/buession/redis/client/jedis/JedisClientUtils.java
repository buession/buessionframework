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
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.ShardedRedisNode;
import com.buession.redis.core.command.KeyCommands;
import com.buession.redis.core.command.ListCommands;
import com.buession.redis.core.command.StringCommands;
import com.buession.redis.core.convert.jedis.ListPositionConvert;
import com.buession.redis.core.convert.jedis.MigrateOperationConvert;
import com.buession.redis.core.convert.jedis.ScanResultConvert;
import com.buession.redis.core.convert.jedis.SetArgumentConvert;
import com.buession.redis.core.convert.jedis.SortArgumentConvert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ListPosition;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.SortingParams;
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

}
