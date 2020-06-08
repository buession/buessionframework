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
import com.buession.lang.KeyValue;
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
import java.util.Set;

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
	public Long lPush(final String key, final String... values){
		return execute(listOperations, (ops)->ops.lPush(key, values));
	}

	@Override
	public Long lPush(final byte[] key, final byte[]... values){
		return execute(binaryListOperations, (ops)->ops.lPush(key, values));
	}

	@Override
	public Long lPushX(final String key, final String... values){
		return execute(listOperations, (ops)->ops.lPushX(key, values));
	}

	@Override
	public Long lPushX(final byte[] key, final byte[]... values){
		return execute(binaryListOperations, (ops)->ops.lPushX(key, values));
	}

	@Override
	public Long lInsert(final String key, final String value, final ListPosition position, final String pivot){
		return execute(listOperations, (ops)->ops.lInsert(key, value, position, pivot));
	}

	@Override
	public Long lInsert(final byte[] key, final byte[] value, final ListPosition position, final byte[] pivot){
		return execute(binaryListOperations, (ops)->ops.lInsert(key, value, position, pivot));
	}

	@Override
	public Status lSet(final String key, final int index, final String value){
		return execute(listOperations, (ops)->ops.lSet(key, index, value));
	}

	@Override
	public Status lSet(final byte[] key, final int index, final byte[] value){
		return execute(binaryListOperations, (ops)->ops.lSet(key, index, value));
	}

	@Override
	public Status lSet(final String key, final long index, final String value){
		return execute(listOperations, (ops)->ops.lSet(key, index, value));
	}

	@Override
	public Status lSet(final byte[] key, final long index, final byte[] value){
		return execute(binaryListOperations, (ops)->ops.lSet(key, index, value));
	}

	@Override
	public String lIndex(final String key, final int index){
		return execute(listOperations, (ops)->ops.lIndex(key, index));
	}

	@Override
	public byte[] lIndex(final byte[] key, final int index){
		return execute(binaryListOperations, (ops)->ops.lIndex(key, index));
	}

	@Override
	public String lIndex(final String key, final long index){
		return execute(listOperations, (ops)->ops.lIndex(key, index));
	}

	@Override
	public byte[] lIndex(final byte[] key, final long index){
		return execute(binaryListOperations, (ops)->ops.lIndex(key, index));
	}

	@Override
	public String lPop(final String key){
		return execute(listOperations, (ops)->ops.lPop(key));
	}

	@Override
	public byte[] lPop(final byte[] key){
		return execute(binaryListOperations, (ops)->ops.lPop(key));
	}

	@Override
	public List<String> blPop(final String[] keys, final int timeout){
		return execute(listOperations, (ops)->ops.blPop(keys, timeout));
	}

	@Override
	public List<byte[]> blPop(final byte[][] keys, final int timeout){
		return execute(binaryListOperations, (ops)->ops.blPop(keys, timeout));
	}

	@Override
	public String rPop(final String key){
		return execute(listOperations, (ops)->ops.rPop(key));
	}

	@Override
	public byte[] rPop(final byte[] key){
		return execute(binaryListOperations, (ops)->ops.rPop(key));
	}

	@Override
	public List<String> brPop(final String[] keys, final int timeout){
		return execute(listOperations, (ops)->ops.brPop(keys, timeout));
	}

	@Override
	public List<byte[]> brPop(final byte[][] keys, final int timeout){
		return execute(binaryListOperations, (ops)->ops.brPop(keys, timeout));
	}

	@Override
	public Long rPush(final String key, final String... values){
		return execute(listOperations, (ops)->ops.rPush(key, values));
	}

	@Override
	public Long rPush(final byte[] key, final byte[]... values){
		return execute(binaryListOperations, (ops)->ops.rPush(key, values));
	}

	@Override
	public Long rPushX(final String key, final String... values){
		return execute(listOperations, (ops)->ops.rPushX(key, values));
	}

	@Override
	public Long rPushX(final byte[] key, final byte[]... values){
		return execute(binaryListOperations, (ops)->ops.rPushX(key, values));
	}

	@Override
	public Status lTrim(final String key, final int start, final int end){
		return execute(listOperations, (ops)->ops.lTrim(key, start, end));
	}

	@Override
	public Status lTrim(final byte[] key, final int start, final int end){
		return execute(binaryListOperations, (ops)->ops.lTrim(key, start, end));
	}

	@Override
	public Status lTrim(final String key, final long start, final long end){
		return execute(listOperations, (ops)->ops.lTrim(key, start, end));
	}

	@Override
	public Status lTrim(final byte[] key, final long start, final long end){
		return execute(binaryListOperations, (ops)->ops.lTrim(key, start, end));
	}

	@Override
	public Long lRem(final String key, final String value, final int count){
		return execute(listOperations, (ops)->ops.lRem(key, value, count));
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final int count){
		return execute(binaryListOperations, (ops)->ops.lRem(key, value, count));
	}

	@Override
	public Long lRem(final String key, final String value, final long count){
		return execute(listOperations, (ops)->ops.lRem(key, value, count));
	}

	@Override
	public Long lRem(final byte[] key, final byte[] value, final long count){
		return execute(binaryListOperations, (ops)->ops.lRem(key, value, count));
	}

	@Override
	public List<String> lRange(final String key, final int start, final int end){
		return execute(listOperations, (ops)->ops.lRange(key, start, end));
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final int start, final int end){
		return execute(binaryListOperations, (ops)->ops.lRange(key, start, end));
	}

	@Override
	public List<String> lRange(final String key, final long start, final long end){
		return execute(listOperations, (ops)->ops.lRange(key, start, end));
	}

	@Override
	public List<byte[]> lRange(final byte[] key, final long start, final long end){
		return execute(binaryListOperations, (ops)->ops.lRange(key, start, end));
	}

	@Override
	public Long lLen(final String key){
		return execute(listOperations, (ops)->ops.lLen(key));
	}

	@Override
	public Long lLen(final byte[] key){
		return execute(binaryListOperations, (ops)->ops.lLen(key));
	}

	@Override
	public Long sAdd(final String key, final String... members){
		return execute(setOperations, (ops)->ops.sAdd(key, members));
	}

	@Override
	public Long sAdd(final byte[] key, final byte[]... members){
		return execute(binarySetOperations, (ops)->ops.sAdd(key, members));
	}

	@Override
	public Long sCard(final String key){
		return execute(setOperations, (ops)->ops.sCard(key));
	}

	@Override
	public Long sCard(final byte[] key){
		return execute(binarySetOperations, (ops)->ops.sCard(key));
	}

	@Override
	public boolean sisMember(final String key, final String member){
		return execute(setOperations, (ops)->ops.sisMember(key, member));
	}

	@Override
	public boolean sisMember(final byte[] key, final byte[] member){
		return execute(binarySetOperations, (ops)->ops.sisMember(key, member));
	}

	@Override
	public Set<String> sMembers(final String key){
		return execute(setOperations, (ops)->ops.sMembers(key));
	}

	@Override
	public Set<byte[]> sMembers(final byte[] key){
		return execute(binarySetOperations, (ops)->ops.sMembers(key));
	}

	@Override
	public String sPop(final String key){
		return execute(setOperations, (ops)->ops.sPop(key));
	}

	@Override
	public byte[] sPop(final byte[] key){
		return execute(binarySetOperations, (ops)->ops.sPop(key));
	}

	@Override
	public String sRandMember(final String key){
		return execute(setOperations, (ops)->ops.sRandMember(key));
	}

	@Override
	public byte[] sRandMember(final byte[] key){
		return execute(binarySetOperations, (ops)->ops.sRandMember(key));
	}

	@Override
	public List<String> sRandMember(final String key, final int count){
		return execute(setOperations, (ops)->ops.sRandMember(key, count));
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final int count){
		return execute(binarySetOperations, (ops)->ops.sRandMember(key, count));
	}

	@Override
	public List<String> sRandMember(final String key, final long count){
		return execute(setOperations, (ops)->ops.sRandMember(key, count));
	}

	@Override
	public List<byte[]> sRandMember(final byte[] key, final long count){
		return execute(binarySetOperations, (ops)->ops.sRandMember(key, count));
	}

	@Override
	public Long sRem(final String key, final String... members){
		return execute(setOperations, (ops)->ops.sRem(key, members));
	}

	@Override
	public Long sRem(final byte[] key, final byte[]... members){
		return execute(binarySetOperations, (ops)->ops.sRem(key, members));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor){
		return execute(setOperations, (ops)->ops.sScan(key, cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor){
		return execute(setOperations, (ops)->ops.sScan(key, cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor){
		return execute(setOperations, (ops)->ops.sScan(key, cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final int count){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final int count){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final int count){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final int count){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final int count){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final int count){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final int cursor, final String pattern, final int count){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern, final int count){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern, final int count){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final String cursor, final String pattern,
			final int count){
		return execute(setOperations, (ops)->ops.sScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final byte[] cursor, final byte[] pattern,
			final int count){
		return execute(binarySetOperations, (ops)->ops.sScan(key, cursor, pattern, count));
	}

	@Override
	public Long zAdd(final String key, final Map<String, Number> members){
		return execute(sortedSetOperations, (ops)->ops.zAdd(key, members));
	}

	@Override
	public Long zAdd(final byte[] key, final Map<byte[], Number> members){
		return execute(binarySortedSetOperations, (ops)->ops.zAdd(key, members));
	}

	@Override
	public Long zAdd(final String key, final List<KeyValue<String, Number>> members){
		return execute(sortedSetOperations, (ops)->ops.zAdd(key, members));
	}

	@Override
	public Long zAdd(final byte[] key, final List<KeyValue<byte[], Number>> members){
		return execute(binarySortedSetOperations, (ops)->ops.zAdd(key, members));
	}

	@Override
	public Double zScore(final String key, final String member){
		return execute(sortedSetOperations, (ops)->ops.zScore(key, member));
	}

	@Override
	public Double zScore(final byte[] key, final byte[] member){
		return execute(binarySortedSetOperations, (ops)->ops.zScore(key, member));
	}

	@Override
	public Long zCard(final String key){
		return execute(sortedSetOperations, (ops)->ops.zCard(key));
	}

	@Override
	public Long zCard(final byte[] key){
		return execute(binarySortedSetOperations, (ops)->ops.zCard(key));
	}

	@Override
	public Double zIncrBy(final String key, final String member, final float increment){
		return execute(sortedSetOperations, (ops)->ops.zIncrBy(key, member, increment));
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final float increment){
		return execute(binarySortedSetOperations, (ops)->ops.zIncrBy(key, member, increment));
	}

	@Override
	public Double zIncrBy(final String key, final String member, final double increment){
		return execute(sortedSetOperations, (ops)->ops.zIncrBy(key, member, increment));
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final double increment){
		return execute(binarySortedSetOperations, (ops)->ops.zIncrBy(key, member, increment));
	}

	@Override
	public Double zIncrBy(final String key, final String member, final int increment){
		return execute(sortedSetOperations, (ops)->ops.zIncrBy(key, member, increment));
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final int increment){
		return execute(binarySortedSetOperations, (ops)->ops.zIncrBy(key, member, increment));
	}

	@Override
	public Double zIncrBy(final String key, final String member, final long increment){
		return execute(sortedSetOperations, (ops)->ops.zIncrBy(key, member, increment));
	}

	@Override
	public Double zIncrBy(final byte[] key, final byte[] member, final long increment){
		return execute(binarySortedSetOperations, (ops)->ops.zIncrBy(key, member, increment));
	}

	@Override
	public Long zCount(final String key, final float min, final float max){
		return execute(sortedSetOperations, (ops)->ops.zCount(key, min, max));
	}

	@Override
	public Long zCount(final byte[] key, final float min, final float max){
		return execute(binarySortedSetOperations, (ops)->ops.zCount(key, min, max));
	}

	@Override
	public Long zCount(final String key, final double min, final double max){
		return execute(sortedSetOperations, (ops)->ops.zCount(key, min, max));
	}

	@Override
	public Long zCount(final byte[] key, final double min, final double max){
		return execute(binarySortedSetOperations, (ops)->ops.zCount(key, min, max));
	}

	@Override
	public Long zCount(final String key, final int min, final int max){
		return execute(sortedSetOperations, (ops)->ops.zCount(key, min, max));
	}

	@Override
	public Long zCount(final byte[] key, final int min, final int max){
		return execute(binarySortedSetOperations, (ops)->ops.zCount(key, min, max));
	}

	@Override
	public Long zCount(final String key, final long min, final long max){
		return execute(sortedSetOperations, (ops)->ops.zCount(key, min, max));
	}

	@Override
	public Long zCount(final byte[] key, final long min, final long max){
		return execute(binarySortedSetOperations, (ops)->ops.zCount(key, min, max));
	}

	@Override
	public Long zCount(final String key, final String min, final String max){
		return execute(sortedSetOperations, (ops)->ops.zCount(key, min, max));
	}

	@Override
	public Long zCount(final byte[] key, final byte[] min, final byte[] max){
		return execute(binarySortedSetOperations, (ops)->ops.zCount(key, min, max));
	}

	@Override
	public Set<String> zRange(final String key, final int start, final int end){
		return execute(sortedSetOperations, (ops)->ops.zRange(key, start, end));
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final int start, final int end){
		return execute(binarySortedSetOperations, (ops)->ops.zRange(key, start, end));
	}

	@Override
	public Set<String> zRange(final String key, final long start, final long end){
		return execute(sortedSetOperations, (ops)->ops.zRange(key, start, end));
	}

	@Override
	public Set<byte[]> zRange(final byte[] key, final long start, final long end){
		return execute(binarySortedSetOperations, (ops)->ops.zRange(key, start, end));
	}

	@Override
	public Set<Tuple> zRangeWithScores(final String key, final int start, final int end){
		return execute(sortedSetOperations, (ops)->ops.zRangeWithScores(key, start, end));
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final int start, final int end){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeWithScores(key, start, end));
	}

	@Override
	public Set<Tuple> zRangeWithScores(final String key, final long start, final long end){
		return execute(sortedSetOperations, (ops)->ops.zRangeWithScores(key, start, end));
	}

	@Override
	public Set<Tuple> zRangeWithScores(final byte[] key, final long start, final long end){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeWithScores(key, start, end));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final float min, final float max){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScore(key, min, max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScore(key, min, max));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScore(key, min, max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScore(key, min, max));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final int min, final int max){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScore(key, min, max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final int min, final int max){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScore(key, min, max));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final long min, final long max){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScore(key, min, max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScore(key, min, max));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScore(key, min, max));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScore(key, min, max));
	}


	@Override
	public Set<String> zRangeByScore(final String key, final float min, final float max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final float min, final float max, final int offset,
			fin l int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final double min, final double max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final int min, final int max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final long min, final long max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByScore(final String key, final String min, final String max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, String min, final String max){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final float min, final float max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final int offset,
			f nal int count){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final int min, final int max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final long min, final long max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final float min, final float max){
		return execute(sortedSetOperations, (ops)->ops.zRangeByLex(key, min, max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final float min, final float max){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByLex(key, min, max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final double min, final double max){
		return execute(sortedSetOperations, (ops)->ops.zRangeByLex(key, min, max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByLex(key, min, max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final int min, final int max){
		return execute(sortedSetOperations, (ops)->ops.zRangeByLex(key, min, max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final int min, final int max){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByLex(key, min, max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final long min, final long max){
		return execute(sortedSetOperations, (ops)->ops.zRangeByLex(key, min, max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByLex(key, min, max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max){
		return execute(sortedSetOperations, (ops)->ops.zRangeByLex(key, min, max));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByLex(key, min, max));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final float min, final float max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final double min, final double max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final int min, final int max, final int offset, final int count){
		return execute(sortedSetOperations, (ops)->ops.zRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final int min, final int max, final int offset, final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final long min, final long max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRangeByLex(final String key, final String min, final String max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Long zRank(final String key, final String member){
		return execute(sortedSetOperations, (ops)->ops.zRank(key, member));
	}

	@Override
	public Long zRank(final byte[] key, final byte[] member){
		return execute(binarySortedSetOperations, (ops)->ops.zRank(key, member));
	}

	@Override
	public Long zRevRank(final String key, final String member){
		return execute(sortedSetOperations, (ops)->ops.zRank(key, member));
	}

	@Override
	public Long zRevRank(final byte[] key, final byte[] member){
		return execute(binarySortedSetOperations, (ops)->ops.zRank(key, member));
	}

	@Override
	public Long zRem(final String key, final String... members){
		return execute(sortedSetOperations, (ops)->ops.zRem(key, members));
	}

	@Override
	public Long zRem(final byte[] key, final byte[]... members){
		return execute(binarySortedSetOperations, (ops)->ops.zRem(key, members));
	}

	@Override
	public Long zRemRangeByRank(final String key, final int start, final int end){
		return execute(sortedSetOperations, (ops)->ops.zRemRangeByRank(key, start, end));
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final int start, final int end){
		return execute(binarySortedSetOperations, (ops)->ops.zRemRangeByRank(key, start, end));
	}

	@Override
	public Long zRemRangeByRank(final String key, final long start, final long end){
		return execute(sortedSetOperations, (ops)->ops.zRemRangeByRank(key, start, end));
	}

	@Override
	public Long zRemRangeByRank(final byte[] key, final long start, final long end){
		return execute(binarySortedSetOperations, (ops)->ops.zRemRangeByRank(key, start, end));
	}

	@Override
	public Long zRemRangeByScore(final String key, final float min, final float max){
		return execute(sortedSetOperations, (ops)->ops.zRemRangeByScore(key, min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final float min, final float max){
		return execute(binarySortedSetOperations, (ops)->ops.zRemRangeByScore(key, min, max));
	}

	@Override
	public Long zRemRangeByScore(final String key, final double min, final double max){
		return execute(sortedSetOperations, (ops)->zRemRangeByScore(key, min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final double min, final double max){
		return execute(binarySortedSetOperations, (ops)->ops.zRemRangeByScore(key, min, max));
	}

	@Override
	public Long zRemRangeByScore(final String key, final int min, final int max){
		return execute(sortedSetOperations, (ops)->ops.zRemRangeByScore(key, min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final int min, final int max){
		return execute(binarySortedSetOperations, (ops)->ops.zRemRangeByScore(key, min, max));
	}

	@Override
	public Long zRemRangeByScore(final String key, final long min, final long max){
		return execute(sortedSetOperations, (ops)->ops.zRemRangeByScore(key, min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final long min, final long max){
		return execute(binarySortedSetOperations, (ops)->ops.zRemRangeByScore(key, min, max));
	}

	@Override
	public Long zRemRangeByScore(final String key, final String min, final String max){
		return execute(sortedSetOperations, (ops)->ops.zRemRangeByScore(key, min, max));
	}

	@Override
	public Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute(binarySortedSetOperations, (ops)->ops.zRemRangeByScore(key, min, max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final float min, final float max){
		return execute(sortedSetOperations, (ops)->ops.zRemRangeByLex(key, min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final float min, final float max){
		return execute(binarySortedSetOperations, (ops)->ops.zRemRangeByLex(key, min, max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final double min, final double max){
		return execute(sortedSetOperations, (ops)->ops.zRemRangeByLex(key, min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final double min, final double max){
		return execute(binarySortedSetOperations, (ops)->ops.zRemRangeByLex(key, min, max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final int min, final int max){
		return execute(sortedSetOperations, (ops)->ops.zRemRangeByLex(key, min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final int min, final int max){
		return execute(binarySortedSetOperations, (ops)->ops.zRemRangeByLex(key, min, max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final long min, final long max){
		return execute(sortedSetOperations, (ops)->ops.zRemRangeByLex(key, min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final long min, final long max){
		return execute(binarySortedSetOperations, (ops)->ops.zRemRangeByLex(key, min, max));
	}

	@Override
	public Long zRemRangeByLex(final String key, final String min, final String max){
		return execute(sortedSetOperations, (ops)->ops.zRemRangeByLex(key, min, max));
	}

	@Override
	public Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute(binarySortedSetOperations, (ops)->ops.zRemRangeByLex(key, min, max));
	}

	@Override
	public Set<String> zRevRange(final String key, final int start, final int end){
		return execute(sortedSetOperations, (ops)->ops.zRevRange(key, start, end));
	}

	@Override
	public Set<byte[]> zRevRange(final byte[] key, final int start, final int end){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRange(key, start, end));
	}

	@Override
	public Set<String> zRevRange(final String key, final long start, final long end){
		return execute(sortedSetOperations, (ops)->ops.zRevRange(key, start, end));
	}

	@Override
	public Set<byte[]> zRevRange(final byte[] key, final long start, final long end){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRange(key, start, end));
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final String key, final int start, final int end){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeWithScores(key, start, end));
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final int start, final int end){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeWithScores(key, start, end));
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final String key, final long start, final long end){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeWithScores(key, start, end));
	}

	@Override
	public Set<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeWithScores(key, start, end));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final float min, final float max){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final float min, final float max){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final int min, final int max){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final int min, final int max){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final long min, final long max){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final float min, final float max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final double min, final double max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final int min, final int max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final long min, final long max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRevRangeByScore(final String key, final String min, final String max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScore(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final float min, final float max){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final float max){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final float min, final float max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
			final int offset, final int count){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
			final int offset, final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final int min, final int max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final long min, final long max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max,
			final int offset, final int count){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max,
			final int offset, final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final float min, final float max){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final float min, final float max){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final double min, final double max){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final int min, final int max){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final int min, final int max){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final long min, final long max){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final float min, final float max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final float min, final float max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final double min, final double max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final int min, final int max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final int min, final int max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final long min, final long max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final long min, final long max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<String> zRevRangeByLex(final String key, final String min, final String max, final int offset,
			final int count){
		return execute(sortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Set<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final int offset,
			final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zRevRangeByLex(key, min, max, offset, count));
	}

	@Override
	public Long zLexCount(final String key, final float min, final float max){
		return execute(sortedSetOperations, (ops)->ops.zLexCount(key, min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final float min, final float max){
		return execute(binarySortedSetOperations, (ops)->ops.zLexCount(key, min, max));
	}

	@Override
	public Long zLexCount(final String key, final double min, final double max){
		return execute(sortedSetOperations, (ops)->ops.zLexCount(key, min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final double min, final double max){
		return execute(binarySortedSetOperations, (ops)->ops.zLexCount(key, min, max));
	}

	@Override
	public Long zLexCount(final String key, final int min, final int max){
		return execute(sortedSetOperations, (ops)->ops.zLexCount(key, min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final int min, final int max){
		return execute(binarySortedSetOperations, (ops)->ops.zLexCount(key, min, max));
	}

	@Override
	public Long zLexCount(final String key, final long min, final long max){
		return execute(sortedSetOperations, (ops)->ops.zLexCount(key, min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final long min, final long max){
		return execute(binarySortedSetOperations, (ops)->ops.zLexCount(key, min, max));
	}

	@Override
	public Long zLexCount(final String key, final String min, final String max){
		return execute(sortedSetOperations, (ops)->ops.zLexCount(key, min, max));
	}

	@Override
	public Long zLexCount(final byte[] key, final byte[] min, final byte[] max){
		return execute(binarySortedSetOperations, (ops)->ops.zLexCount(key, min, max));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor){
		return execute(sortedSetOperations, (ops)->ops.zScan(key, cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor){
		return execute(binarySortedSetOperations, (ops)->ops.zScan(key, cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor){
		return execute(sortedSetOperations, (ops)->ops.zScan(key, cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor){
		return execute(binarySortedSetOperations, (ops)->ops.zScan(key, cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor){
		return execute(sortedSetOperations, (ops)->ops.zScan(key, cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor){
		return execute(binarySortedSetOperations, (ops)->ops.zScan(key, cursor));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern){
		return execute(sortedSetOperations, (ops)->ops.zScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern){
		return execute(binarySortedSetOperations, (ops)->ops.zScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern){
		return execute(sortedSetOperations, (ops)->ops.zScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern){
		return execute(binarySortedSetOperations, (ops)->ops.zScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern){
		return execute(sortedSetOperations, (ops)->ops.zScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern){
		return execute(binarySortedSetOperations, (ops)->ops.zScan(key, cursor, pattern));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final int count){
		return execute(sortedSetOperations, (ops)->ops.zScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final int count){
		return execute(sortedSetOperations, (ops)->ops.zScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final int count){
		return execute(sortedSetOperations, (ops)->ops.zScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zScan(key, cursor, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final int cursor, final String pattern, final int count){
		return execute(sortedSetOperations, (ops)->ops.zScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final int cursor, final byte[] pattern, final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final int count){
		return execute(sortedSetOperations, (ops)->ops.zScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern, final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final int count){
		return execute(sortedSetOperations, (ops)->ops.zScan(key, cursor, pattern, count));
	}

	@Override
	public ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final int count){
		return execute(binarySortedSetOperations, (ops)->ops.zScan(key, cursor, pattern, count));
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
