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
 * | Copyright @ 2013-2022 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.client.jedis.operations;

import com.buession.core.utils.NumberUtils;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.SetOperations;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.internal.convert.jedis.ScanResultConverter;

import java.util.List;

/**
 * Jedis 集合命令操作抽象类
 *
 * @param <CMD>
 * 		Jedis 原始命令对象
 *
 * @author Yong.Teng
 */
public abstract class AbstractSetOperations<CMD> extends AbstractJedisRedisOperations<CMD>
		implements SetOperations<CMD> {

	protected final static ScanResultConverter.ListScanResultExposeConverter<String> STRING_LIST_SCAN_RESULT_EXPOSE_CONVERTER = new ScanResultConverter.ListScanResultExposeConverter<>();

	protected final static ScanResultConverter.ListScanResultExposeConverter<byte[]> BINARY_LIST_SCAN_RESULT_EXPOSE_CONVERTER = new ScanResultConverter.ListScanResultExposeConverter<>();

	protected final static ScanResultConverter.BinaryToStringListScanResultExposeConverter BINARY_TO_STRING_LIST_SCAN_RESULT_EXPOSE_CONVERTER = new ScanResultConverter.BinaryToStringListScanResultExposeConverter();

	public AbstractSetOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor){
		return sScan(key, Long.toString(cursor));
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor){
		return sScan(key, NumberUtils.long2bytes(cursor));
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern){
		return sScan(key, Long.toString(cursor), pattern);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern){
		return sScan(key, NumberUtils.long2bytes(cursor), pattern);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final long count){
		return sScan(key, Long.toString(cursor), count);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final long count){
		return sScan(key, NumberUtils.long2bytes(cursor), count);
	}

	@Override
	public ScanResult<List<String>> sScan(final String key, final long cursor, final String pattern, final long count){
		return sScan(key, Long.toString(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<byte[]>> sScan(final byte[] key, final long cursor, final byte[] pattern, final long count){
		return sScan(key, NumberUtils.long2bytes(cursor), pattern, count);
	}

}
