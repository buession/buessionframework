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
package com.buession.redis.client.lettuce;

import com.buession.redis.client.AbstractRedisClient;
import com.buession.redis.client.ClientOptions;
import com.buession.redis.client.connection.lettuce.LettuceRedisConnection;
import com.buession.redis.client.lettuce.command.*;
import com.buession.redis.core.command.*;
import io.lettuce.core.api.StatefulConnection;

/**
 * Lettuce Redis 客户端
 *
 * @author Yong.Teng
 * @since 3.0.0
 */
public class LettuceRedisClient extends AbstractRedisClient {

	/**
	 * 构造函数
	 */
	public LettuceRedisClient() {
		super();
	}

	/**
	 * 构造函数
	 *
	 * @param clientOptions
	 * 		客户端选项
	 * @param connection
	 * 		Lettuce Redis 连接对象 {@link LettuceRedisConnection}
	 *
	 * @since 4.0.0
	 */
	public LettuceRedisClient(final ClientOptions clientOptions,
							  final LettuceRedisConnection<? extends StatefulConnection<byte[], byte[]>> connection) {
		super(clientOptions, connection);
	}

	/**
	 * 构造函数
	 *
	 * @param connection
	 * 		Lettuce Redis 连接对象 {@link LettuceRedisConnection}
	 */
	public LettuceRedisClient(final LettuceRedisConnection<? extends StatefulConnection<byte[], byte[]>> connection) {
		super(connection);
	}

	@Override
	public AutoSuggestCommands autoSuggestCommands() {
		if(autoSuggestCommands == null){
			autoSuggestCommands = new LettuceAutoSuggestCommands(this);
		}

		return autoSuggestCommands;
	}

	@Override
	public BitMapCommands bitMapCommands() {
		if(bitMapCommands == null){
			bitMapCommands = new LettuceBitMapCommands(this);
		}

		return bitMapCommands;
	}

	@Override
	public BloomFilterCommands bloomFilterCommands() {
		if(bloomFilterCommands == null){
			bloomFilterCommands = new LettuceBloomFilterCommands(this);
		}

		return bloomFilterCommands;
	}

	@Override
	public CuckooFilterCommands cuckooFilterCommands() {
		if(cuckooFilterCommands == null){
			cuckooFilterCommands = new LettuceCuckooFilterCommands(this);
		}

		return cuckooFilterCommands;
	}

	@Override
	public CountMinSketchCommands countMinSketchCommands() {
		if(countMinSketchCommands == null){
			countMinSketchCommands = new LettuceCountMinSketchCommands(this);
		}

		return countMinSketchCommands;
	}

	@Override
	public ClusterCommands clusterCommands() {
		if(clusterCommands == null){
			clusterCommands = new LettuceClusterCommands(this);
		}

		return clusterCommands;
	}

	@Override
	public ConnectionCommands connectionCommands() {
		if(connectionCommands == null){
			connectionCommands = new LettuceConnectionCommands(this);
		}

		return connectionCommands;
	}

	@Override
	public GenericCommands genericCommands() {
		if(genericCommands == null){
			genericCommands = new LettuceGenericCommands(this);
		}

		return genericCommands;
	}

	@Override
	public GeoCommands geoCommands() {
		if(geoCommands == null){
			geoCommands = new LettuceGeoCommands(this);
		}

		return geoCommands;
	}

	@Override
	public HashCommands hashCommands() {
		if(hashCommands == null){
			hashCommands = new LettuceHashCommands(this);
		}

		return hashCommands;
	}

	@Override
	public HyperLogLogCommands hyperLogLogCommands() {
		if(hyperLogLogCommands == null){
			hyperLogLogCommands = new LettuceHyperLogLoCommands(this);
		}

		return hyperLogLogCommands;
	}

	@Override
	public JsonCommands jsonCommands() {
		if(jsonCommands == null){
			jsonCommands = new LettuceJsonCommands(this);
		}

		return jsonCommands;
	}

	@Override
	public KeyCommands keyCommands() {
		if(keyCommands == null){
			keyCommands = new LettuceKeyCommands(this);
		}

		return keyCommands;
	}

	@Override
	public ListCommands listCommands() {
		if(listCommands == null){
			listCommands = new LettuceListCommands(this);
		}

		return listCommands;
	}

	@Override
	public PubSubCommands pubSubCommands() {
		if(pubSubCommands == null){
			pubSubCommands = new LettucePubSubCommands(this);
		}

		return pubSubCommands;
	}

	@Override
	public ScriptingCommands scriptingCommands() {
		if(scriptingCommands == null){
			scriptingCommands = new LettuceScriptingCommands(this);
		}

		return scriptingCommands;
	}

	@Override
	public SearchCommands searchCommands() {
		if(searchCommands == null){
			searchCommands = new LettuceSearchCommands(this);
		}

		return searchCommands;
	}

	@Override
	public ServerCommands serverCommands() {
		if(serverCommands == null){
			serverCommands = new LettuceServerCommands(this);
		}

		return serverCommands;
	}

	@Override
	public SetCommands setCommands() {
		if(setCommands == null){
			setCommands = new LettuceSetCommands(this);
		}

		return setCommands;
	}

	@Override
	public SortedSetCommands sortedSetCommands() {
		if(sortedSetCommands == null){
			sortedSetCommands = new LettuceSortedSetCommands(this);
		}

		return sortedSetCommands;
	}

	@Override
	public StreamCommands streamCommands() {
		if(streamCommands == null){
			streamCommands = new LettuceStreamCommands(this);
		}

		return streamCommands;
	}

	@Override
	public StringCommands stringCommands() {
		if(stringCommands == null){
			stringCommands = new LettuceStringCommands(this);
		}

		return stringCommands;
	}

	@Override
	public TDigestCommands tDigestCommands() {
		if(tDigestCommands == null){
			tDigestCommands = new LettuceTDigestCommands(this);
		}

		return tDigestCommands;
	}

	@Override
	public TimeSeriesCommands timeSeriesCommands() {
		if(timeSeriesCommands == null){
			timeSeriesCommands = new LettuceTimeSeriesCommands(this);
		}

		return timeSeriesCommands;
	}

	@Override
	public TopKCommands topKCommands() {
		if(topKCommands == null){
			topKCommands = new LettuceTopKCommands(this);
		}

		return topKCommands;
	}

	@Override
	public TransactionCommands transactionCommands() {
		if(transactionCommands == null){
			transactionCommands = new LettuceTransactionCommands(this);
		}

		return transactionCommands;
	}

	@Override
	public VectorSetCommands vectorSetCommands() {
		if(vectorSetCommands == null){
			vectorSetCommands = new LettuceVectorSetCommands(this);
		}

		return vectorSetCommands;
	}

}
