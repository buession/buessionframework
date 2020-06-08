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
package com.buession.redis.client;

import com.buession.core.Executor;
import com.buession.lang.Geo;
import com.buession.lang.Status;
import com.buession.redis.client.connection.RedisConnection;
import com.buession.redis.client.connection.RedisConnectionFactory;
import com.buession.redis.client.connection.RedisConnectionUtils;
import com.buession.redis.core.GeoRadius;
import com.buession.redis.core.GeoUnit;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.command.ProtocolCommand;
import com.buession.redis.core.operations.OperationsCommandArguments;
import com.buession.redis.exception.RedisException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 */
public abstract class AbstractRedisClient implements RedisClient {

	private RedisConnectionFactory connectionFactory;

	private RedisConnection connection;

	private boolean enableTransactionSupport = false;

	private final static Logger logger = LoggerFactory.getLogger(AbstractRedisClient.class);

	public AbstractRedisClient(){
		super();
	}

	public AbstractRedisClient(RedisConnection connection){
		setConnection(connection);
	}

	@Override
	public RedisConnection getConnection(){
		return connection;
	}

	@Override
	public void setConnection(RedisConnection connection){
		this.connection = connection;
		connectionFactory = new RedisConnectionFactory(connection);
	}

	public boolean isEnableTransactionSupport(){
		return getEnableTransactionSupport();
	}

	public boolean getEnableTransactionSupport(){
		return enableTransactionSupport;
	}

	public void setEnableTransactionSupport(boolean enableTransactionSupport){
		this.enableTransactionSupport = enableTransactionSupport;
	}

	@Override
	public Status pfAdd(final String key, final String... elements){
		return execute(hyperLogLogOperations, (HyperLogLogRedisOperations ops)->ops.pfAdd(key, elements));
	}

	@Override
	public Status pfAdd(final byte[] key, final byte[]... elements){
		return execute(binaryHyperLogLogOperations, (BinaryHyperLogLogRedisOperations ops)->ops.pfAdd(key, elements));
	}

	@Override
	public Long geoAdd(final String key, final String member, final double longitude, final double latitude){
		return execute(geoOperations, (ops)->ops.geoAdd(key, member, longitude, latitude));
	}

	@Override
	public Long geoAdd(final byte[] key, final byte[] member, final double longitude, final double latitude){
		return execute(binaryGeoOperations, (ops)->ops.geoAdd(key, member, longitude, latitude));
	}

	@Override
	public Long geoAdd(final String key, final Map<String, Geo> memberCoordinates){
		return execute(geoOperations, (ops)->ops.geoAdd(key, memberCoordinates));
	}

	@Override
	public Long geoAdd(final byte[] key, final Map<byte[], Geo> memberCoordinates){
		return execute(binaryGeoOperations, (ops)->ops.geoAdd(key, memberCoordinates));
	}

	@Override
	public List<Geo> geoPos(final String key, final String... members){
		return execute(geoOperations, (ops)->ops.geoPos(key, members));
	}

	@Override
	public List<Geo> geoPos(final byte[] key, final byte[]... members){
		return execute(binaryGeoOperations, (ops)->ops.geoPos(key, members));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2){
		return execute(geoOperations, (ops)->ops.geoDist(key, member1, member2));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2){
		return execute(binaryGeoOperations, (ops)->ops.geoDist(key, member1, member2));
	}

	@Override
	public Double geoDist(final String key, final String member1, final String member2, final GeoUnit unit){
		return execute(geoOperations, (ops)->ops.geoDist(key, member1, member2, unit));
	}

	@Override
	public Double geoDist(final byte[] key, final byte[] member1, final byte[] member2, final GeoUnit unit){
		return execute(binaryGeoOperations, (ops)->ops.geoDist(key, member1, member2, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius){
		return execute(geoOperations, (ops)->ops.geoRadius(key, longitude, latitude, radius));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius){
		return execute(binaryGeoOperations, (ops)->ops.geoRadius(key, longitude, latitude, radius));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit){
		return execute(geoOperations, (ops)->ops.geoRadius(key, longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit){
		return execute(binaryGeoOperations, (ops)->ops.geoRadius(key, longitude, latitude, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius, final GeoArgument geoArgument){
		return execute(geoOperations, (ops)->ops.geoRadius(key, longitude, latitude, radius, geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoArgument geoArgument){
		return execute(binaryGeoOperations, (ops)->ops.geoRadius(key, longitude, latitude, radius, geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadius(final String key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit, final GeoArgument geoArgument){
		return execute(geoOperations, (ops)->ops.geoRadius(key, longitude, latitude, radius, unit, geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadius(final byte[] key, final double longitude, final double latitude,
			final double radius, final GeoUnit unit, final GeoArgument geoArgument){
		return execute(binaryGeoOperations, (ops)->ops.geoRadius(key, longitude, latitude, radius, unit, geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius){
		return execute(geoOperations, (ops)->ops.geoRadiusByMember(key, member, radius));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius){
		return execute(binaryGeoOperations, (ops)->ops.geoRadiusByMember(key, member, radius));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoUnit unit){
		return execute(geoOperations, (ops)->ops.geoRadiusByMember(key, member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit){
		return execute(binaryGeoOperations, (ops)->ops.geoRadiusByMember(key, member, radius, unit));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoArgument geoArgument){
		return execute(geoOperations, (ops)->ops.geoRadiusByMember(key, member, radius, geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoArgument geoArgument){
		return execute(binaryGeoOperations, (ops)->ops.geoRadiusByMember(key, member, radius, geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final String key, final String member, final double radius,
			final GeoUnit unit, final GeoArgument geoArgument){
		return execute(geoOperations, (ops)->ops.geoRadiusByMember(key, member, radius, unit, geoArgument));
	}

	@Override
	public List<GeoRadius> geoRadiusByMember(final byte[] key, final byte[] member, final double radius,
			final GeoUnit unit, final GeoArgument geoArgument){
		return execute(binaryGeoOperations, (ops)->ops.geoRadiusByMember(key, member, radius, unit, geoArgument));
	}

	@Override
	public List<String> geoHash(final String key, final String... members){
		return execute(geoOperations, (ops)->ops.geoHash(key, members));
	}

	@Override
	public List<byte[]> geoHash(final byte[] key, final byte[]... members){
		return execute(binaryGeoOperations, (ops)->ops.geoHash(key, members));
	}

	@Override
	public Status setBit(final String key, final int offset, final String value){
		return execute(bitMapOperations, (ops)->ops.setBit(key, offset, value));
	}

	@Override
	public Status setBit(final byte[] key, final int offset, final byte[] value){
		return execute(binaryBitMapOperations, (ops)->ops.setBit(key, offset, value));
	}

	@Override
	public Status setBit(final String key, final long offset, final String value){
		return execute(bitMapOperations, (ops)->ops.setBit(key, offset, value));
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final byte[] value){
		return execute(binaryBitMapOperations, (ops)->ops.setBit(key, offset, value));
	}

	@Override
	public Status setBit(final String key, final int offset, final boolean value){
		return execute(bitMapOperations, (ops)->ops.setBit(key, offset, value));
	}

	@Override
	public Status setBit(final byte[] key, final int offset, final boolean value){
		return execute(binaryBitMapOperations, (ops)->ops.setBit(key, offset, value));
	}

	@Override
	public Status setBit(final String key, final long offset, final boolean value){
		return execute(bitMapOperations, (ops)->ops.setBit(key, offset, value));
	}

	@Override
	public Status setBit(final byte[] key, final long offset, final boolean value){
		return execute(binaryBitMapOperations, (ops)->ops.setBit(key, offset, value));
	}

	@Override
	public Status getBit(final String key, final int offset){
		return execute(bitMapOperations, (ops)->ops.getBit(key, offset));
	}

	@Override
	public Status getBit(final byte[] key, final int offset){
		return execute(binaryBitMapOperations, (ops)->ops.getBit(key, offset));
	}

	@Override
	public Status getBit(final String key, final long offset){
		return execute(bitMapOperations, (ops)->ops.getBit(key, offset));
	}

	@Override
	public Status getBit(final byte[] key, final long offset){
		return execute(binaryBitMapOperations, (ops)->ops.getBit(key, offset));
	}

	@Override
	public Long bitPos(final String key, final boolean value){
		return execute(bitMapOperations, (ops)->ops.bitPos(key, value));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value){
		return execute(binaryBitMapOperations, (ops)->ops.bitPos(key, value));
	}

	@Override
	public Long bitPos(final String key, final boolean value, final int start, final int end){
		return execute(bitMapOperations, (ops)->ops.bitPos(key, value, start, end));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final int start, final int end){
		return execute(binaryBitMapOperations, (ops)->ops.bitPos(key, value, start, end));
	}

	@Override
	public Long bitPos(final String key, final boolean value, final long start, final long end){
		return execute(bitMapOperations, (ops)->ops.bitPos(key, value, start, end));
	}

	@Override
	public Long bitPos(final byte[] key, final boolean value, final long start, final long end){
		return execute(binaryBitMapOperations, (ops)->ops.bitPos(key, value, start, end));
	}

	@Override
	public List<Long> bitField(final String key, final String... arguments){
		return execute(bitMapOperations, (ops)->ops.bitField(key, arguments));
	}

	@Override
	public List<Long> bitField(final byte[] key, final byte[]... arguments){
		return execute(binaryBitMapOperations, (ops)->ops.bitField(key, arguments));
	}

	@Override
	public Long bitCount(final String key){
		return execute(bitMapOperations, (ops)->ops.bitCount(key));
	}

	@Override
	public Long bitCount(final byte[] key){
		return execute(binaryBitMapOperations, (ops)->ops.bitCount(key));
	}

	@Override
	public Long bitCount(final String key, final int start, final int end){
		return execute(bitMapOperations, (ops)->ops.bitCount(key, start, end));
	}

	@Override
	public Long bitCount(final byte[] key, final int start, final int end){
		return execute(binaryBitMapOperations, (ops)->ops.bitCount(key, start, end));
	}

	@Override
	public Long bitCount(final String key, final long start, final long end){
		return execute(bitMapOperations, (ops)->ops.bitCount(key, start, end));
	}

	@Override
	public Long bitCount(final byte[] key, final long start, final long end){
		return execute(binaryBitMapOperations, (ops)->ops.bitCount(key, start, end));
	}

	@Override
	public String echo(final String str){
		return execute(debugOperations, (ops)->ops.echo(str));
	}

	@Override
	public byte[] echo(final byte[] str){
		return execute(binaryDebugOperations, (ops)->ops.echo(str));
	}

	@Override
	public Object object(final ObjectCommand command, final String key){
		return execute(debugOperations, (ops)->ops.object(command, key));
	}

	@Override
	public Object object(final ObjectCommand command, final byte[] key){
		return execute(binaryDebugOperations, (ops)->ops.object(command, key));
	}

	protected <C, R> R doExecute(final Executor<C, R> executor, final ProtocolCommand command){
		return doExecute(executor, command, null);
	}

	protected <C, R> R doExecute(final Executor<C, R> executor, final ProtocolCommand command,
			final OperationsCommandArguments arguments){
		String argumentsString = logger.isDebugEnabled() && arguments != null ? arguments.toString() : null;
		RedisConnection connection;

		if(logger.isDebugEnabled()){
			if(arguments != null){
				logger.debug("Execute command '{}' width arguments: {}", command, argumentsString);
			}else{
				logger.debug("Execute command '{}'", command);
			}
		}

		if(enableTransactionSupport){
			// only bind resources in case of potential transaction synchronization
			connection = RedisConnectionUtils.bindConnection(connectionFactory, true);
		}else{
			connection = RedisConnectionUtils.getConnection(connectionFactory);
		}

		try{
			return connection.execute(command, executor);
		}catch(RedisException e){
			if(logger.isDebugEnabled()){
				if(arguments != null){
					logger.error("Execute command '{}' width arguments: {}, failure: {}", command, argumentsString,
							e.getMessage());
				}else{
					logger.error("Execute command '{}', failure: {}", command, e.getMessage());
				}
			}
			throw e;
		}finally{
			RedisConnectionUtils.releaseConnection(connectionFactory, connection, enableTransactionSupport);
		}
	}

	protected boolean isTransaction(){
		return getConnection().isTransaction();
	}

	protected void close(){
		try{
			connection.close();
		}catch(IOException e){
			logger.error("RedisConnection close error: {}", e.getMessage());
		}
	}

}
