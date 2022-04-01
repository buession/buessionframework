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

import com.buession.core.converter.Converter;
import com.buession.core.converter.EnumConverter;
import com.buession.core.utils.EnumUtils;
import com.buession.core.utils.NumberUtils;
import com.buession.redis.client.jedis.JedisRedisClient;
import com.buession.redis.client.operations.KeyOperations;
import com.buession.redis.core.ObjectEncoding;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Type;
import com.buession.redis.core.internal.convert.jedis.MigrateOperationConverter;
import com.buession.redis.core.internal.convert.jedis.RestoreArgumentConverter;
import com.buession.redis.core.internal.convert.jedis.ScanResultConverter;
import com.buession.redis.core.internal.convert.jedis.SortArgumentConverter;
import com.buession.redis.utils.SafeEncoder;

import java.util.List;

/**
 * Jedis Key 命令操作抽象类
 *
 * @param <CMD>
 * 		Jedis 原始命令对象
 *
 * @author Yong.Teng
 */
public abstract class AbstractKeyOperations<CMD> extends AbstractJedisRedisOperations<CMD>
		implements KeyOperations<CMD> {

	protected final static MigrateOperationConverter.MigrateOperationJedisConverter MIGRATE_OPERATION_JEDIS_CONVERTER = new MigrateOperationConverter.MigrateOperationJedisConverter();

	protected final static Converter<String, ObjectEncoding> STRING_OBJECT_ENCODING_CONVERTER = (value)->EnumUtils.getEnumIgnoreCase(
			ObjectEncoding.class, value);

	protected final static Converter<byte[], ObjectEncoding> BINARY_OBJECT_ENCODING_CONVERTER = (value)->EnumUtils.getEnumIgnoreCase(
			ObjectEncoding.class, SafeEncoder.encode(value));

	protected final static RestoreArgumentConverter.RestoreArgumentJedisConverter RESTORE_ARGUMENT_JEDIS_CONVERTER = new RestoreArgumentConverter.RestoreArgumentJedisConverter();

	protected final static ScanResultConverter.ListScanResultExposeConverter<String> STRING_LIST_SCAN_RESULT_EXPOSE_CONVERTER = new ScanResultConverter.ListScanResultExposeConverter<>();

	protected final static ScanResultConverter.ListScanResultExposeConverter<byte[]> BINARY_LIST_SCAN_RESULT_EXPOSE_CONVERTER = new ScanResultConverter.ListScanResultExposeConverter<>();

	protected final static SortArgumentConverter.SortArgumentJedisConverter SORT_ARGUMENT_JEDIS_CONVERTER = new SortArgumentConverter.SortArgumentJedisConverter();

	protected final static EnumConverter<Type> TYPE_CONVERTER = new EnumConverter<>(Type.class);

	public AbstractKeyOperations(final JedisRedisClient client){
		super(client);
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor){
		return scan(Long.toString(cursor));
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern){
		return scan(Long.toString(cursor), pattern);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern){
		return scan(NumberUtils.long2bytes(cursor), pattern);
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final long count){
		return scan(Long.toString(cursor), count);
	}

	@Override
	public ScanResult<List<String>> scan(final long cursor, final String pattern, final long count){
		return scan(Long.toString(cursor), pattern, count);
	}

	@Override
	public ScanResult<List<byte[]>> scan(final long cursor, final byte[] pattern, final long count){
		return scan(NumberUtils.long2bytes(cursor), pattern, count);
	}

}
