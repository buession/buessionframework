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

import com.buession.lang.KeyValue;
import com.buession.lang.Status;
import com.buession.redis.core.TopKInfo;
import com.buession.redis.core.command.TopKCommands;

import java.util.List;

/**
 * TOP K 命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=topk" target="_blank">https://redis.io/docs/latest/commands/?group=topk</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface TopKOperations extends TopKCommands, RedisOperations {

	@Override
	default List<String> topKAdd(final String key, final String... items) {
		return execute((client)->client.topKCommands().topKAdd(key, items));
	}

	@Override
	default List<byte[]> topKAdd(final byte[] key, final byte[]... items) {
		return execute((client)->client.topKCommands().topKAdd(key, items));
	}

	@Override
	default List<Long> topKCount(final String key, final String... items) {
		return execute((client)->client.topKCommands().topKCount(key, items));
	}

	@Override
	default List<Long> topKCount(final byte[] key, final byte[]... items) {
		return execute((client)->client.topKCommands().topKCount(key, items));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	default List<String> topKIncrBy(final String key, final KeyValue<String, Long>... items) {
		return execute((client)->client.topKCommands().topKIncrBy(key, items));
	}

	@SuppressWarnings({"unchecked"})
	@Override
	default List<byte[]> topKIncrBy(final byte[] key, final KeyValue<byte[], Long>... items) {
		return execute((client)->client.topKCommands().topKIncrBy(key, items));
	}

	/**
	 * 按指定的增量（权重）增加 Top-K 数据结构中元素的计数 1
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.count/" target="_blank">https://redis.io/docs/latest/commands/topk.count</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param items
	 * 		一个或多个要增加计数的元素
	 *
	 * @return 每个元素对应一个操作的反馈
	 */
	@SuppressWarnings({"unchecked"})
	default List<String> topKIncr(final String key, final String... items) {
		final KeyValue<String, Long>[] itemAndValue = new KeyValue[items.length];
		for(int i = 0; i < items.length; i++){
			itemAndValue[i] = new KeyValue<>(items[i], 1L);
		}
		return topKIncrBy(key, itemAndValue);
	}

	/**
	 * 按指定的增量（权重）增加 Top-K 数据结构中元素的计数 1
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/topk.incrby/" target="_blank">https://redis.io/docs/latest/commands/topk.incrby/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param items
	 * 		一个或多个要增加计数的元素
	 *
	 * @return 每个元素对应一个操作的反馈
	 */
	@SuppressWarnings({"unchecked"})
	default List<byte[]> topKIncrBy(final byte[] key, final byte[]... items) {
		final KeyValue<byte[], Long>[] itemAndValue = new KeyValue[items.length];
		for(int i = 0; i < items.length; i++){
			itemAndValue[i] = new KeyValue<>(items[i], 1L);
		}
		return topKIncrBy(key, itemAndValue);
	}

	@Override
	default TopKInfo topKInfo(final String key) {
		return execute((client)->client.topKCommands().topKInfo(key));
	}

	@Override
	default TopKInfo topKInfo(final byte[] key) {
		return execute((client)->client.topKCommands().topKInfo(key));
	}

	@Override
	default List<String> topKList(final String key) {
		return execute((client)->client.topKCommands().topKList(key));
	}

	@Override
	default List<String> topKList(final byte[] key) {
		return execute((client)->client.topKCommands().topKList(key));
	}

	@Override
	default List<KeyValue<String, Long>> topKListWithCount(final String key) {
		return execute((client)->client.topKCommands().topKListWithCount(key));
	}

	@Override
	default List<KeyValue<byte[], Long>> topKListWithCount(final byte[] key) {
		return execute((client)->client.topKCommands().topKListWithCount(key));
	}

	@Override
	default List<Boolean> topKQuery(final String key, final String... items) {
		return execute((client)->client.topKCommands().topKQuery(key, items));
	}

	@Override
	default List<Boolean> topKQuery(final byte[] key, final byte[]... items) {
		return execute((client)->client.topKCommands().topKQuery(key, items));
	}

	@Override
	default Status topKReserve(final String key, final long topK) {
		return execute((client)->client.topKCommands().topKReserve(key, topK));
	}

	@Override
	default Status topKReserve(final byte[] key, final long topK) {
		return execute((client)->client.topKCommands().topKReserve(key, topK));
	}

	@Override
	default Status topKReserve(final String key, final long topK, final long width, final long depth,
							   final double decay) {
		return execute((client)->client.topKCommands().topKReserve(key, topK, width, depth, decay));
	}

	@Override
	default Status topKReserve(final byte[] key, final long topK, final long width, final long depth,
							   final double decay) {
		return execute((client)->client.topKCommands().topKReserve(key, topK, width, depth, decay));
	}

}
