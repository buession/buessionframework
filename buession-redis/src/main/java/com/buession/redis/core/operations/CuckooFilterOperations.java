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
package com.buession.redis.core.operations;

import com.buession.lang.Status;
import com.buession.redis.core.command.CuckooFilterCommands;
import com.buession.redis.core.command.args.cuckoofilter.CFReserveArgument;
import com.buession.redis.utils.KeyUtils;

import java.util.List;
import java.util.Map;

/**
 * 布谷鸟过滤器命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=cf" target="_blank">https://redis.io/docs/latest/commands/?group=cf</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface CuckooFilterOperations extends CuckooFilterCommands, RedisOperations {

	@Override
	default Status cfAdd(final String key, final String item) {
		return execute((client)->client.cuckooFilterCommands().cfAdd(KeyUtils.rawKey(this, key), item));
	}

	@Override
	default Status cfAdd(final byte[] key, final byte[] item) {
		return execute((client)->client.cuckooFilterCommands().cfAdd(KeyUtils.rawKey(this, key), item));
	}

	@Override
	default Status cfAddNx(final String key, final String item) {
		return execute((client)->client.cuckooFilterCommands().cfAddNx(KeyUtils.rawKey(this, key), item));
	}

	@Override
	default Status cfAddNx(final byte[] key, final byte[] item) {
		return execute((client)->client.cuckooFilterCommands().cfAddNx(KeyUtils.rawKey(this, key), item));
	}

	@Override
	default Long cfCount(final String key, final String item) {
		return execute((client)->client.cuckooFilterCommands().cfCount(KeyUtils.rawKey(this, key), item));
	}

	@Override
	default Long cfCount(final byte[] key, final byte[] item) {
		return execute((client)->client.cuckooFilterCommands().cfCount(KeyUtils.rawKey(this, key), item));
	}

	@Override
	default Status cfDel(final String key, final String item) {
		return execute((client)->client.cuckooFilterCommands().cfDel(KeyUtils.rawKey(this, key), item));
	}

	@Override
	default Status cfDel(final byte[] key, final byte[] item) {
		return execute((client)->client.cuckooFilterCommands().cfDel(KeyUtils.rawKey(this, key), item));
	}

	@Override
	default Boolean cfExists(final String key, final String item) {
		return execute((client)->client.cuckooFilterCommands().cfExists(KeyUtils.rawKey(this, key), item));
	}

	@Override
	default Boolean cfExists(final byte[] key, final byte[] item) {
		return execute((client)->client.cuckooFilterCommands().cfExists(KeyUtils.rawKey(this, key), item));
	}

	@Override
	default Map<String, Object> cfInfo(final String key) {
		return execute((client)->client.cuckooFilterCommands().cfInfo(KeyUtils.rawKey(this, key)));
	}

	@Override
	default Map<String, Object> cfInfo(final byte[] key) {
		return execute((client)->client.cuckooFilterCommands().cfInfo(KeyUtils.rawKey(this, key)));
	}

	@Override
	default List<Boolean> cfInsert(final String key, final String... items) {
		return execute((client)->client.cuckooFilterCommands().cfInsert(KeyUtils.rawKey(this, key), items));
	}

	@Override
	default List<Boolean> cfInsert(final byte[] key, final byte[]... items) {
		return execute((client)->client.cuckooFilterCommands().cfInsert(KeyUtils.rawKey(this, key), items));
	}

	@Override
	default List<Boolean> cfInsert(final String key, final Long capacity, final String... items) {
		return execute((client)->client.cuckooFilterCommands().cfInsert(KeyUtils.rawKey(this, key), capacity, items));
	}

	@Override
	default List<Boolean> cfInsert(final byte[] key, final Long capacity, final byte[]... items) {
		return execute((client)->client.cuckooFilterCommands().cfInsert(KeyUtils.rawKey(this, key), capacity, items));
	}

	@Override
	default List<Boolean> cfInsert(final String key, final Long capacity, final boolean noCreate,
	                               final String... items) {
		return execute((client)->client.cuckooFilterCommands()
				.cfInsert(KeyUtils.rawKey(this, key), capacity, noCreate, items));
	}

	@Override
	default List<Boolean> cfInsert(final byte[] key, final Long capacity, final boolean noCreate,
	                               final byte[]... items) {
		return execute((client)->client.cuckooFilterCommands()
				.cfInsert(KeyUtils.rawKey(this, key), capacity, noCreate, items));
	}

	@Override
	default List<Boolean> cfInsert(final String key, final boolean noCreate, final String... items) {
		return execute((client)->client.cuckooFilterCommands().cfInsert(KeyUtils.rawKey(this, key), noCreate, items));
	}

	@Override
	default List<Boolean> cfInsert(final byte[] key, final boolean noCreate, final byte[]... items) {
		return execute((client)->client.cuckooFilterCommands().cfInsert(KeyUtils.rawKey(this, key), noCreate, items));
	}

	@Override
	default List<Boolean> cfInsertNx(final String key, final String... items) {
		return execute((client)->client.cuckooFilterCommands().cfInsertNx(KeyUtils.rawKey(this, key), items));
	}

	@Override
	default List<Boolean> cfInsertNx(final byte[] key, final byte[]... items) {
		return execute((client)->client.cuckooFilterCommands().cfInsertNx(KeyUtils.rawKey(this, key), items));
	}

	@Override
	default List<Boolean> cfInsertNx(final String key, final Long capacity, final String... items) {
		return execute((client)->client.cuckooFilterCommands().cfInsertNx(KeyUtils.rawKey(this, key), capacity, items));
	}

	@Override
	default List<Boolean> cfInsertNx(final byte[] key, final Long capacity, final byte[]... items) {
		return execute((client)->client.cuckooFilterCommands().cfInsertNx(KeyUtils.rawKey(this, key), capacity, items));
	}

	@Override
	default List<Boolean> cfInsertNx(final String key, final Long capacity, final boolean noCreate,
	                                 final String... items) {
		return execute((client)->client.cuckooFilterCommands()
				.cfInsertNx(KeyUtils.rawKey(this, key), capacity, noCreate, items));
	}

	@Override
	default List<Boolean> cfInsertNx(final byte[] key, final Long capacity, final boolean noCreate,
	                                 final byte[]... items) {
		return execute((client)->client.cuckooFilterCommands()
				.cfInsertNx(KeyUtils.rawKey(this, key), capacity, noCreate, items));
	}

	@Override
	default List<Boolean> cfInsertNx(final String key, final boolean noCreate, final String... items) {
		return execute((client)->client.cuckooFilterCommands().cfInsertNx(KeyUtils.rawKey(this, key), noCreate, items));
	}

	@Override
	default List<Boolean> cfInsertNx(final byte[] key, final boolean noCreate, final byte[]... items) {
		return execute((client)->client.cuckooFilterCommands().cfInsertNx(KeyUtils.rawKey(this, key), noCreate, items));
	}

	@Override
	default Status cfLoadchunk(final String key, final long iterator, final byte[] data) {
		return execute((client)->client.cuckooFilterCommands().cfLoadchunk(KeyUtils.rawKey(this, key), iterator, data));
	}

	@Override
	default Status cfLoadchunk(final byte[] key, final long iterator, final byte[] data) {
		return execute((client)->client.cuckooFilterCommands().cfLoadchunk(KeyUtils.rawKey(this, key), iterator, data));
	}

	@Override
	default List<Boolean> cfMExists(final String key, final String... items) {
		return execute((client)->client.cuckooFilterCommands().cfMExists(KeyUtils.rawKey(this, key), items));
	}

	@Override
	default List<Boolean> cfMExists(final byte[] key, final byte[]... items) {
		return execute((client)->client.cuckooFilterCommands().cfMExists(KeyUtils.rawKey(this, key), items));
	}

	@Override
	default Status cfReserve(final String key, final long capacity) {
		return execute((client)->client.cuckooFilterCommands().cfReserve(KeyUtils.rawKey(this, key), capacity));
	}

	@Override
	default Status cfReserve(final byte[] key, final long capacity) {
		return execute((client)->client.cuckooFilterCommands().cfReserve(KeyUtils.rawKey(this, key), capacity));
	}

	@Override
	default Status cfReserve(final String key, final long capacity, final CFReserveArgument argument) {
		return execute(
				(client)->client.cuckooFilterCommands().cfReserve(KeyUtils.rawKey(this, key), capacity, argument));
	}

	@Override
	default Status cfReserve(final byte[] key, final long capacity, final CFReserveArgument argument) {
		return execute(
				(client)->client.cuckooFilterCommands().cfReserve(KeyUtils.rawKey(this, key), capacity, argument));
	}

	@Override
	default Map<Long, byte[]> cfScanDump(final String key, final long iterator) {
		return execute((client)->client.cuckooFilterCommands().cfScanDump(KeyUtils.rawKey(this, key), iterator));
	}

	@Override
	default Map<Long, byte[]> cfScanDump(final byte[] key, final long iterator) {
		return execute((client)->client.cuckooFilterCommands().cfScanDump(KeyUtils.rawKey(this, key), iterator));
	}

}
