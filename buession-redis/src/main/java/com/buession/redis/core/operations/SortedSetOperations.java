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

import com.buession.core.builder.MapBuilder;
import com.buession.core.type.TypeReference;
import com.buession.lang.KeyValue;
import com.buession.redis.core.Aggregate;
import com.buession.redis.core.GtLt;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.ZRangeBy;
import com.buession.redis.core.command.SortedSetCommands;

import java.util.List;
import java.util.Map;

/**
 * 有序集合运算
 *
 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/index.html" target="_blank">http://redisdoc.com/sorted_set/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface SortedSetOperations extends SortedSetCommands, RedisOperations {

	@Override
	default Tuple zPopMin(final String key) {
		return execute((client)->client.sortedSetOperations().zPopMin(key));
	}

	@Override
	default Tuple zPopMin(final byte[] key) {
		return execute((client)->client.sortedSetOperations().zPopMin(key));
	}

	@Override
	default List<Tuple> zPopMin(final String key, long count) {
		return execute((client)->client.sortedSetOperations().zPopMin(key, count));
	}

	@Override
	default List<Tuple> zPopMin(final byte[] key, long count) {
		return execute((client)->client.sortedSetOperations().zPopMin(key, count));
	}

	@Override
	default Tuple zPopMax(final String key) {
		return execute((client)->client.sortedSetOperations().zPopMax(key));
	}

	@Override
	default Tuple zPopMax(final byte[] key) {
		return execute((client)->client.sortedSetOperations().zPopMax(key));
	}

	@Override
	default List<Tuple> zPopMax(final String key, final long count) {
		return execute((client)->client.sortedSetOperations().zPopMax(key, count));
	}

	@Override
	default List<Tuple> zPopMax(final byte[] key, final long count) {
		return execute((client)->client.sortedSetOperations().zPopMax(key, count));
	}

	@Override
	default KeyValue<String, Tuple> bzPopMin(final String[] keys, final int timeout) {
		return execute((client)->client.sortedSetOperations().bzPopMin(keys, timeout));
	}

	@Override
	default KeyValue<byte[], Tuple> bzPopMin(final byte[][] keys, final int timeout) {
		return execute((client)->client.sortedSetOperations().bzPopMin(keys, timeout));
	}

	@Override
	default KeyValue<String, Tuple> bzPopMax(final String[] keys, final int timeout) {
		return execute((client)->client.sortedSetOperations().bzPopMax(keys, timeout));
	}

	@Override
	default KeyValue<byte[], Tuple> bzPopMax(final byte[][] keys, final int timeout) {
		return execute((client)->client.sortedSetOperations().bzPopMax(keys, timeout));
	}

	@Override
	default Long zAdd(final String key, final Map<String, Double> members) {
		return execute((client)->client.sortedSetOperations().zAdd(key, members));
	}

	@Override
	default Long zAdd(final byte[] key, final Map<byte[], Double> members) {
		return execute((client)->client.sortedSetOperations().zAdd(key, members));
	}

	@Override
	default Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx) {
		return execute((client)->client.sortedSetOperations().zAdd(key, members, nxXx));
	}

	@Override
	default Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx) {
		return execute((client)->client.sortedSetOperations().zAdd(key, members, nxXx));
	}

	@Override
	default Long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt) {
		return execute((client)->client.sortedSetOperations().zAdd(key, members, gtLt));
	}

	@Override
	default Long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt) {
		return execute((client)->client.sortedSetOperations().zAdd(key, members, gtLt));
	}

	@Override
	default Long zAdd(final String key, final Map<String, Double> members, final boolean ch) {
		return execute((client)->client.sortedSetOperations().zAdd(key, members, ch));
	}

	@Override
	default Long zAdd(final byte[] key, final Map<byte[], Double> members, final boolean ch) {
		return execute((client)->client.sortedSetOperations().zAdd(key, members, ch));
	}

	@Override
	default Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt) {
		return execute((client)->client.sortedSetOperations().zAdd(key, members, nxXx, gtLt));
	}

	@Override
	default Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt) {
		return execute((client)->client.sortedSetOperations().zAdd(key, members, nxXx, gtLt));
	}

	@Override
	default Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final boolean ch) {
		return execute((client)->client.sortedSetOperations().zAdd(key, members, nxXx, ch));
	}

	@Override
	default Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final boolean ch) {
		return execute((client)->client.sortedSetOperations().zAdd(key, members, nxXx, ch));
	}

	@Override
	default Long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt, final boolean ch) {
		return execute((client)->client.sortedSetOperations().zAdd(key, members, gtLt, ch));
	}

	@Override
	default Long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt, final boolean ch) {
		return execute((client)->client.sortedSetOperations().zAdd(key, members, gtLt, ch));
	}

	@Override
	default Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt,
					  final boolean ch) {
		return execute((client)->client.sortedSetOperations().zAdd(key, members, nxXx, gtLt, ch));
	}

	@Override
	default Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt,
					  final boolean ch) {
		return execute((client)->client.sortedSetOperations().zAdd(key, members, nxXx, gtLt, ch));
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final String key, final double score, final String member) {
		return zAdd(key, MapBuilder.of(member, score));
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final byte[] key, final double score, final byte[] member) {
		return zAdd(key, MapBuilder.of(member, score));
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final String key, final double score, final String member, final NxXx nxXx) {
		return zAdd(key, MapBuilder.of(member, score), nxXx);
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">http://redisdoc.com/sorted_set/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final byte[] key, final double score, final byte[] member, final NxXx nxXx) {
		return zAdd(key, MapBuilder.of(member, score), nxXx);
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final String key, final double score, final String member, final GtLt gtLt) {
		return zAdd(key, MapBuilder.of(member, score), gtLt);
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">http://redisdoc.com/sorted_set/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final byte[] key, final double score, final byte[] member, final GtLt gtLt) {
		return zAdd(key, MapBuilder.of(member, score), gtLt);
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final String key, final double score, final String member, final boolean ch) {
		return zAdd(key, MapBuilder.of(member, score), ch);
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">http://redisdoc.com/sorted_set/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final byte[] key, final double score, final byte[] member, final boolean ch) {
		return zAdd(key, MapBuilder.of(member, score), ch);
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final String key, final double score, final String member, final NxXx nxXx, final GtLt gtLt) {
		return zAdd(key, MapBuilder.of(member, score), nxXx, gtLt);
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">http://redisdoc.com/sorted_set/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final byte[] key, final double score, final byte[] member, final NxXx nxXx, final GtLt gtLt) {
		return zAdd(key, MapBuilder.of(member, score), nxXx, gtLt);
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final String key, final double score, final String member, final NxXx nxXx, final boolean ch) {
		return zAdd(key, MapBuilder.of(member, score), nxXx, ch);
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">http://redisdoc.com/sorted_set/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final byte[] key, final double score, final byte[] member, final NxXx nxXx, final boolean ch) {
		return zAdd(key, MapBuilder.of(member, score), nxXx, ch);
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final String key, final double score, final String member, final GtLt gtLt, final boolean ch) {
		return zAdd(key, MapBuilder.of(member, score), gtLt, ch);
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">http://redisdoc.com/sorted_set/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final byte[] key, final double score, final byte[] member, final GtLt gtLt, final boolean ch) {
		return zAdd(key, MapBuilder.of(member, score), gtLt, ch);
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final String key, final double score, final String member, final NxXx nxXx, final GtLt gtLt,
					  final boolean ch) {
		return zAdd(key, MapBuilder.of(member, score), nxXx, gtLt, ch);
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">http://redisdoc.com/sorted_set/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	default Long zAdd(final byte[] key, final double score, final byte[] member, final NxXx nxXx, final GtLt gtLt,
					  final boolean ch) {
		return zAdd(key, MapBuilder.of(member, score), nxXx, gtLt, ch);
	}

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final String key, final double score, final V member);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final byte[] key, final double score, final V member);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final String key, final double score, final V member, final NxXx nxXx);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final byte[] key, final double score, final V member, final NxXx nxXx);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final String key, final double score, final V member, final GtLt gtLt);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final byte[] key, final double score, final V member, final GtLt gtLt);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final String key, final double score, final V member, final boolean ch);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final byte[] key, final double score, final V member, final boolean ch);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final String key, final double score, final V member, final NxXx nxXx, final GtLt gtLt);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final byte[] key, final double score, final V member, final NxXx nxXx, final GtLt gtLt);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final String key, final double score, final V member, final NxXx nxXx, final boolean ch);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final byte[] key, final double score, final V member, final NxXx nxXx, final boolean ch);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final String key, final double score, final V member, final GtLt gtLt, final boolean ch);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final byte[] key, final double score, final V member, final GtLt gtLt, final boolean ch);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final String key, final double score, final V member, final NxXx nxXx, final GtLt gtLt,
				  final boolean ch);

	/**
	 * 将元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param score
	 * 		score
	 * @param member
	 * 		元素
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 * @param <V>
	 * 		元素值类型
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	<V> Long zAdd(final byte[] key, final double score, final V member, final NxXx nxXx, final GtLt gtLt,
				  final boolean ch);

	@Override
	default Long zCard(final String key) {
		return execute((client)->client.sortedSetOperations().zCard(key));
	}

	@Override
	default Long zCard(final byte[] key) {
		return execute((client)->client.sortedSetOperations().zCard(key));
	}

	@Override
	default Long zCount(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zCount(key, min, max));
	}

	@Override
	default Long zCount(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zCount(key, min, max));
	}

	@Override
	default List<String> zDiff(final String... keys) {
		return execute((client)->client.sortedSetOperations().zDiff(keys));
	}

	@Override
	default List<byte[]> zDiff(final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zDiff(keys));
	}

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 */
	<V> List<V> zDiffObject(final String[] keys);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 */
	<V> List<V> zDiffObject(final byte[][] keys);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 */
	<V> List<V> zDiffObject(final String[] keys, final Class<V> clazz);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 */
	<V> List<V> zDiffObject(final byte[][] keys, final Class<V> clazz);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 *
	 * @see TypeReference
	 */
	<V> List<V> zDiffObject(final String[] keys, final TypeReference<V> type);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of the difference
	 *
	 * @see TypeReference
	 */
	<V> List<V> zDiffObject(final byte[][] keys, final TypeReference<V> type);

	@Override
	default List<Tuple> zDiffWithScores(final String... keys) {
		return execute((client)->client.sortedSetOperations().zDiffWithScores(keys));
	}

	@Override
	default List<Tuple> zDiffWithScores(final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zDiffWithScores(keys));
	}

	@Override
	default Long zDiffStore(final String destKey, final String... keys) {
		return execute((client)->client.sortedSetOperations().zDiffStore(destKey, keys));
	}

	@Override
	default Long zDiffStore(final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zDiffStore(destKey, keys));
	}

	@Override
	default Double zIncrBy(final String key, final double increment, final String member) {
		return execute((client)->client.sortedSetOperations().zIncrBy(key, increment, member));
	}

	@Override
	default Double zIncrBy(final byte[] key, final double increment, final byte[] member) {
		return execute((client)->client.sortedSetOperations().zIncrBy(key, increment, member));
	}

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量一
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member 元素
	 *
	 * @return member 成员的新 score 值
	 */
	default Double zIncr(final String key, final String member) {
		return zIncrBy(key, 1, member);
	}

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量一
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member 元素
	 *
	 * @return member 成员的新 score 值
	 */
	default Double zIncr(final byte[] key, final byte[] member) {
		return zIncrBy(key, 1, member);
	}

	/**
	 * 为有序集 key 的成员 member 的 score 值加上减量一
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member 元素
	 *
	 * @return member 成员的新 score 值
	 */
	default Double zDecr(final String key, final String member) {
		return zIncrBy(key, -1, member);
	}

	/**
	 * 为有序集 key 的成员 member 的 score 值加上减量一
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member 元素
	 *
	 * @return member 成员的新 score 值
	 */
	default Double zDecr(final byte[] key, final byte[] member) {
		return zIncrBy(key, -1, member);
	}

	/**
	 * 为有序集 key 的成员 member 的 score 值加上减量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zincrby.html" target="_blank">http://redisdoc.com/sorted_set/zincrby.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param increment
	 * 		增量
	 * @param member
	 * 		member 元素
	 *
	 * @return member 成员的新 score 值
	 */
	default Double zDecrBy(final String key, final double increment, final String member) {
		return zIncrBy(key, increment * -1, member);
	}

	/**
	 * 为有序集 key 的成员 member 的 score 值加上减量 increment
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zincrby.html" target="_blank">http://redisdoc.com/sorted_set/zincrby.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param increment
	 * 		增量
	 * @param member
	 * 		member 元素
	 *
	 * @return member 成员的新 score 值
	 */
	default Double zDecrBy(final byte[] key, final double increment, final byte[] member) {
		return zIncrBy(key, increment * -1, member);
	}

	@Override
	default List<String> zInter(final String... keys) {
		return execute((client)->client.sortedSetOperations().zInter(keys));
	}

	@Override
	default List<byte[]> zInter(final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zInter(keys));
	}

	@Override
	default List<String> zInter(final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zInter(keys, aggregate));
	}

	@Override
	default List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zInter(keys, aggregate));
	}

	@Override
	default List<String> zInter(final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInter(keys, weights));
	}

	@Override
	default List<byte[]> zInter(final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInter(keys, weights));
	}

	@Override
	default List<String> zInter(final String[] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInter(keys, aggregate, weights));
	}

	@Override
	default List<byte[]> zInter(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInter(keys, aggregate, weights));
	}

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInterObject(final String[] keys);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInterObject(final byte[][] keys);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInterObject(final String[] keys, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInterObject(final byte[][] keys, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 *
	 * @see TypeReference
	 */
	<V> List<V> zInterObject(final String[] keys, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 *
	 * @see TypeReference
	 */
	<V> List<V> zInterObject(final byte[][] keys, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInterObject(final String[] keys, final Aggregate aggregate);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInterObject(final String[] keys, final Aggregate aggregate, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 *
	 * @see TypeReference
	 */
	<V> List<V> zInterObject(final String[] keys, final Aggregate aggregate, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 *
	 * @see TypeReference
	 */
	<V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInterObject(final String[] keys, final double[] weights);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInterObject(final byte[][] keys, final double[] weights);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInterObject(final String[] keys, final double[] weights, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInterObject(final byte[][] keys, final double[] weights, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 *
	 * @see TypeReference
	 */
	<V> List<V> zInterObject(final String[] keys, final double[] weights, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 *
	 * @see TypeReference
	 */
	<V> List<V> zInterObject(final byte[][] keys, final double[] weights, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInterObject(final String[] keys, final Aggregate aggregate, final double[] weights);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of intersection
	 */
	<V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate, final double[] weights);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInterObject(final String[] keys, final Aggregate aggregate, final double[] weights,
							 final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 */
	<V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate, final double[] weights,
							 final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合的交集反序列为对象
	 *
	 * @see TypeReference
	 */
	<V> List<V> zInterObject(final String[] keys, final Aggregate aggregate, final double[] weights,
							 final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集合的交集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return The result of intersection
	 *
	 * @see TypeReference
	 */
	<V> List<V> zInterObject(final byte[][] keys, final Aggregate aggregate, final double[] weights,
							 final TypeReference<V> type);

	@Override
	default List<Tuple> zInterWithScores(final String... keys) {
		return execute((client)->client.sortedSetOperations().zInterWithScores(keys));
	}

	@Override
	default List<Tuple> zInterWithScores(final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zInterWithScores(keys));
	}

	@Override
	default List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zInterWithScores(keys, aggregate));
	}

	@Override
	default List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zInterWithScores(keys, aggregate));
	}

	@Override
	default List<Tuple> zInterWithScores(final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInterWithScores(keys, weights));
	}

	@Override
	default List<Tuple> zInterWithScores(final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInterWithScores(keys, weights));
	}

	@Override
	default List<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInterWithScores(keys, aggregate, weights));
	}

	@Override
	default List<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInterWithScores(keys, aggregate, weights));
	}

	@Override
	default Long zInterStore(final String destKey, final String... keys) {
		return execute((client)->client.sortedSetOperations().zInterStore(destKey, keys));
	}

	@Override
	default Long zInterStore(final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zInterStore(destKey, keys));
	}

	@Override
	default Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zInterStore(destKey, keys, aggregate));
	}

	@Override
	default Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zInterStore(destKey, keys, aggregate));
	}

	@Override
	default Long zInterStore(final String destKey, final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInterStore(destKey, keys, weights));
	}

	@Override
	default Long zInterStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zInterStore(destKey, keys, weights));
	}

	@Override
	default Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate,
							 final double... weights) {
		return execute(
				(client)->client.sortedSetOperations().zInterStore(destKey, keys, aggregate, weights));
	}

	@Override
	default Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							 final double... weights) {
		return execute((client)->client.sortedSetOperations().zInterStore(destKey, keys, aggregate, weights));
	}

	@Override
	default Long zLexCount(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zLexCount(key, min, max));
	}

	@Override
	default Long zLexCount(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zLexCount(key, min, max));
	}

	@Override
	default List<Double> zMScore(final String key, final String... members) {
		return execute((client)->client.sortedSetOperations().zMScore(key, members));
	}

	@Override
	default List<Double> zMScore(final byte[] key, final byte[]... members) {
		return execute((client)->client.sortedSetOperations().zMScore(key, members));
	}

	@Override
	default String zRandMember(final String key) {
		return execute((client)->client.sortedSetOperations().zRandMember(key));
	}

	@Override
	default byte[] zRandMember(final byte[] key) {
		return execute((client)->client.sortedSetOperations().zRandMember(key));
	}

	@Override
	default List<String> zRandMember(final String key, final long count) {
		return execute((client)->client.sortedSetOperations().zRandMember(key, count));
	}

	@Override
	default List<byte[]> zRandMember(final byte[] key, final long count) {
		return execute((client)->client.sortedSetOperations().zRandMember(key, count));
	}

	/**
	 * 返回有序集合 key 中的一个随机元素，并反序列为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的一个随机元素反序列化后的对象
	 */
	<V> V zRandMemberObject(final String key);

	/**
	 * 返回有序集合 key 中的一个随机元素，并反序列为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的一个随机元素反序列化后的对象
	 */
	<V> V zRandMemberObject(final byte[] key);

	/**
	 * 返回有序集合 key 中的一个随机元素，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的一个随机元素反序列化后的对象
	 */
	<V> V zRandMemberObject(final String key, final Class<V> clazz);

	/**
	 * 返回有序集合 key 中的一个随机元素，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的一个随机元素反序列化后的对象
	 */
	<V> V zRandMemberObject(final byte[] key, final Class<V> clazz);

	/**
	 * 返回有序集合 key 中的一个随机元素，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的一个随机元素反序列化后的对象
	 *
	 * @see TypeReference
	 */
	<V> V zRandMemberObject(final String key, final TypeReference<V> type);

	/**
	 * 返回有序集合 key 中的一个随机元素，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的一个随机元素反序列化后的对象
	 *
	 * @see TypeReference
	 */
	<V> V zRandMemberObject(final byte[] key, final TypeReference<V> type);

	/**
	 * 返回有序集合 key 中的 count 个随机元素，并反序列为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的随机元素反序列化后的对象
	 */
	<V> List<V> zRandMemberObject(final String key, final long count);

	/**
	 * 返回有序集合 key 中的 count 个随机元素，并反序列为对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的随机元素反序列化后的对象
	 */
	<V> List<V> zRandMemberObject(final byte[] key, final long count);

	/**
	 * 返回有序集合 key 中的 count 个随机元素，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的随机元素反序列化后的对象
	 */
	<V> List<V> zRandMemberObject(final String key, final long count, final Class<V> clazz);

	/**
	 * 返回有序集合 key 中的 count 个随机元素，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的随机元素反序列化后的对象
	 */
	<V> List<V> zRandMemberObject(final byte[] key, final long count, final Class<V> clazz);

	/**
	 * 返回有序集合 key 中的 count 个随机元素，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的随机元素反序列化后的对象
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRandMemberObject(final String key, final long count, final TypeReference<V> type);

	/**
	 * 返回有序集合 key 中的 count 个随机元素，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 有序集合 key 中的随机元素反序列化后的对象
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRandMemberObject(final byte[] key, final long count, final TypeReference<V> type);

	@Override
	default List<Tuple> zRandMemberWithScores(final String key, final long count) {
		return execute((client)->client.sortedSetOperations().zRandMemberWithScores(key, count));
	}

	@Override
	default List<Tuple> zRandMemberWithScores(final byte[] key, final long count) {
		return execute((client)->client.sortedSetOperations().zRandMemberWithScores(key, count));
	}

	@Override
	default List<String> zRange(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end));
	}

	@Override
	default List<byte[]> zRange(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRange(key, start, end));
	}

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列为对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRangeObject(final String key, final long start, final long end);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列为对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRangeObject(final byte[] key, final long start, final long end);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRangeObject(final String key, final long start, final long end, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRangeObject(final byte[] key, final long start, final long end, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRangeObject(final String key, final long start, final long end, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrange.html" target="_blank">http://redisdoc.com/sorted_set/zrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，反序列化为对象的有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRangeObject(final byte[] key, final long start, final long end, final TypeReference<V> type);

	@Override
	default List<Tuple> zRangeWithScores(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRangeWithScores(key, start, end));
	}

	@Override
	default List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRangeWithScores(key, start, end));
	}

	@Override
	default List<String> zRangeByLex(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(key, min, max));
	}

	@Override
	default List<byte[]> zRangeByLex(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(key, min, max));
	}

	@Override
	default List<String> zRangeByLex(final String key, final double min, final double max, final long offset,
									 final long count) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(key, min, max, offset, count));
	}

	@Override
	default List<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final long offset,
									 final long count) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(key, min, max, offset, count));
	}

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内的成员反序列化为对象后的列表
	 */
	<V> List<V> zRangeByLexObject(final String key, final double min, final double max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内的成员反序列化为对象后的列表
	 */
	<V> List<V> zRangeByLexObject(final byte[] key, final double min, final double max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内的成员反序列化为对象后的列表
	 */
	<V> List<V> zRangeByLexObject(final String key, final double min, final double max, final Class<V> clazz);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内的成员反序列化为对象后的列表
	 */
	<V> List<V> zRangeByLexObject(final byte[] key, final double min, final double max, final Class<V> clazz);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内的成员反序列化为对象后的列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRangeByLexObject(final String key, final double min, final double max, final TypeReference<V> type);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内的成员反序列化为对象后的列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRangeByLexObject(final byte[] key, final double min, final double max, final TypeReference<V> type);

	@Override
	default List<String> zRangeByScore(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByLex(key, min, max));
	}

	@Override
	default List<byte[]> zRangeByScore(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max));
	}

	@Override
	default List<String> zRangeByScore(final String key, final double min, final double max, final long offset,
									   final long count) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max, offset, count));
	}

	@Override
	default List<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final long offset,
									   final long count) {
		return execute((client)->client.sortedSetOperations().zRangeByScore(key, min, max, offset, count));
	}

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列为对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRangeByScoreObject(final String key, final double min, final double max);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列为对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRangeByScoreObject(final byte[] key, final double min, final double max);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列化为 clazz 指定的对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRangeByScoreObject(final String key, final double min, final double max, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列化为 clazz 指定的对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRangeByScoreObject(final byte[] key, final double min, final double max, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列化为 type 指定的对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRangeByScoreObject(final String key, final double min, final double max, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列化为 type 指定的对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRangeByScoreObject(final byte[] key, final double min, final double max, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列为对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRangeByScoreObject(final String key, final double min, final double max, final long offset,
									final long count);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列为对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRangeByScoreObject(final byte[] key, final double min, final double max, final long offset,
									final long count);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列化为 clazz 指定的对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRangeByScoreObject(final String key, final double min, final double max, final long offset,
									final long count, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列化为 clazz 指定的对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRangeByScoreObject(final byte[] key, final double min, final double max, final long offset,
									final long count, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列化为 type 指定的对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRangeByScoreObject(final String key, final double min, final double max, final long offset,
									final long count, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员，并反序列化为 type 指定的对象；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRangeByScoreObject(final byte[] key, final double min, final double max, final long offset,
									final long count, final TypeReference<V> type);

	@Override
	default List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByScoreWithScores(key, min, max));
	}

	@Override
	default List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRangeByScoreWithScores(key, min, max));
	}

	@Override
	default List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final long offset,
												final long count) {
		return execute(
				(client)->client.sortedSetOperations().zRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	default List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final long offset,
												final long count) {
		return execute(
				(client)->client.sortedSetOperations().zRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	default Long zRangeStore(final String destKey, final String key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRangeStore(destKey, key, start, end));
	}

	@Override
	default Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRangeStore(destKey, key, start, end));
	}

	@Override
	default Long zRangeStore(final String destKey, final String key, final long start, final long end,
							 final ZRangeBy by) {
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(destKey, key, start, end, by));
	}

	@Override
	default Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							 final ZRangeBy by) {
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(destKey, key, start, end, by));
	}

	@Override
	default Long zRangeStore(final String destKey, final String key, final long start, final long end,
							 final boolean rev) {
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(destKey, key, start, end, rev));
	}

	@Override
	default Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							 final boolean rev) {
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(destKey, key, start, end, rev));
	}

	@Override
	default Long zRangeStore(final String destKey, final String key, final long start, final long end,
							 final long offset, final long count) {
		return execute((client)->client.sortedSetOperations().zRangeStore(destKey, key, start, end, offset, count));
	}

	@Override
	default Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							 final long offset, final long count) {
		return execute((client)->client.sortedSetOperations().zRangeStore(destKey, key, start, end, offset, count));
	}

	@Override
	default Long zRangeStore(final String destKey, final String key, final long start, final long end,
							 final ZRangeBy by, final boolean rev) {
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(destKey, key, start, end, by, rev));
	}

	@Override
	default Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							 final ZRangeBy by, final boolean rev) {
		return execute(
				(client)->client.sortedSetOperations().zRangeStore(destKey, key, start, end, by, rev));
	}

	@Override
	default Long zRangeStore(final String destKey, final String key, final long start, final long end,
							 final ZRangeBy by, final long offset, final long count) {
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(destKey, key, start, end, by, offset, count));
	}

	@Override
	default Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							 final ZRangeBy by, final long offset, final long count) {
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(destKey, key, start, end, by, offset, count));
	}

	@Override
	default Long zRangeStore(final String destKey, final String key, final long start, final long end,
							 final boolean rev, final long offset, final long count) {
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(destKey, key, start, end, rev, offset, count));
	}

	@Override
	default Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							 final boolean rev, final long offset, final long count) {
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(destKey, key, start, end, rev, offset, count));
	}

	@Override
	default Long zRangeStore(final String destKey, final String key, final long start, final long end,
							 final ZRangeBy by, final boolean rev, final long offset, final long count) {
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(destKey, key, start, end, by, rev, offset, count));
	}

	@Override
	default Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end,
							 final ZRangeBy by, final boolean rev, final long offset, final long count) {
		return execute((client)->client.sortedSetOperations()
				.zRangeStore(destKey, key, start, end, by, rev, offset, count));
	}

	@Override
	default Long zRank(final String key, final String member) {
		return execute((client)->client.sortedSetOperations().zRank(key, member));
	}

	@Override
	default Long zRank(final byte[] key, final byte[] member) {
		return execute((client)->client.sortedSetOperations().zRank(key, member));
	}

	@Override
	default Long zRem(final String key, final String... members) {
		return execute((client)->client.sortedSetOperations().zRem(key, members));
	}

	@Override
	default Long zRem(final byte[] key, final byte[]... members) {
		return execute((client)->client.sortedSetOperations().zRem(key, members));
	}

	/**
	 * 移除有序集 key 中的成员，不存在的成员将被忽略
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrem.html" target="_blank">http://redisdoccom/sorted_set/zrem.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 *
	 * @return 被成功移除的成员的数量，不包括被忽略的成员
	 */
	default long zRem(final String key, final String member) {
		return zRem(key, new String[]{member});
	}

	/**
	 * 移除有序集 key 中的成员，不存在的成员将被忽略
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrem.html" target="_blank">http://redisdoccom/sorted_set/zrem.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		成员
	 *
	 * @return 被成功移除的成员的数量，不包括被忽略的成员
	 */
	default long zRem(final byte[] key, final byte[] member) {
		return zRem(key, new byte[][]{member});
	}

	@Override
	default Long zRemRangeByLex(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRemRangeByLex(key, min, max));
	}

	@Override
	default Long zRemRangeByLex(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRemRangeByLex(key, min, max));
	}

	@Override
	default Long zRemRangeByScore(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRemRangeByScore(key, min, max));
	}

	@Override
	default Long zRemRangeByScore(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRemRangeByScore(key, min, max));
	}

	@Override
	default Long zRemRangeByRank(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRemRangeByRank(key, start, end));
	}

	@Override
	default Long zRemRangeByRank(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRemRangeByRank(key, start, end));
	}

	@Override
	default List<String> zRevRange(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRevRange(key, start, end));
	}

	@Override
	default List<byte[]> zRevRange(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRevRange(key, start, end));
	}

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列为对象；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc.com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象后的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRevRangeObject(final String key, final long start, final long end);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列为对象；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc.com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象后的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRevRangeObject(final byte[] key, final long start, final long end);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc.com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象后的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRevRangeObject(final String key, final long start, final long end, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 clazz 指定的对象；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc.com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象后的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	<V> List<V> zRevRangeObject(final byte[] key, final long start, final long end, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc.com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象后的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRevRangeObject(final String key, final long start, final long end, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并反序列化为 type 指定的对象；其中成员的位置按 score 值递减（从大到小）来排列；
	 * 具有相同 score 值的成员按字典序的逆序排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrange.html" target="_blank">http://redisdoc.com/sorted_set/zrevrange.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象后的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRevRangeObject(final byte[] key, final long start, final long end, final TypeReference<V> type);

	@Override
	default List<Tuple> zRevRangeWithScores(final String key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRevRangeWithScores(key, start, end));
	}

	@Override
	default List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end) {
		return execute((client)->client.sortedSetOperations().zRevRangeWithScores(key, start, end));
	}

	@Override
	default List<String> zRevRangeByLex(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max));
	}

	@Override
	default List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max));
	}

	@Override
	default List<String> zRevRangeByLex(final String key, final double min, final double max, final long offset,
										final long count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count));
	}

	@Override
	default List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final long offset,
										final long count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByLex(key, min, max, offset, count));
	}

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 */
	<V> List<V> zRevRangeByLexObject(final String key, final double min, final double max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 */
	<V> List<V> zRevRangeByLexObject(final byte[] key, final double min, final double max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 */
	<V> List<V> zRevRangeByLexObject(final String key, final double min, final double max, final Class<V> clazz);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 */
	<V> List<V> zRevRangeByLexObject(final byte[] key, final double min, final double max, final Class<V> clazz);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRevRangeByLexObject(final String key, final double min, final double max, final TypeReference<V> type);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRevRangeByLexObject(final byte[] key, final double min, final double max, final TypeReference<V> type);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 */
	<V> List<V> zRevRangeByLexObject(final String key, final double min, final double max, final long offset,
									 final long count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列为对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 */
	<V> List<V> zRevRangeByLexObject(final byte[] key, final double min, final double max, final long offset,
									 final long count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 */
	<V> List<V> zRevRangeByLexObject(final String key, final double min, final double max, final long offset,
									 final long count, final Class<V> clazz);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 */
	<V> List<V> zRevRangeByLexObject(final byte[] key, final double min, final double max, final long offset,
									 final long count, final Class<V> clazz);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRevRangeByLexObject(final String key, final double min, final double max, final long offset,
									 final long count, final TypeReference<V> type);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 包含了有序集合在指定范围内反序列化为对象后的成员列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRevRangeByLexObject(final byte[] key, final double min, final double max, final long offset,
									 final long count, final TypeReference<V> type);

	@Override
	default List<String> zRevRangeByScore(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max));
	}

	@Override
	default List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max));
	}

	@Override
	default List<String> zRevRangeByScore(final String key, final double min, final double max, final long offset,
										  final long count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count));
	}

	@Override
	default List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final long offset,
										  final long count) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScore(key, min, max, offset, count));
	}

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列为对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRevRangeByScoreObject(final String key, final double min, final double max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列为对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRevRangeByScoreObject(final byte[] key, final double min, final double max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列化为 clazz 指定的对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRevRangeByScoreObject(final String key, final double min, final double max, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列化为 clazz 指定的对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRevRangeByScoreObject(final byte[] key, final double min, final double max, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列化为 type 指定的对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRevRangeByScoreObject(final String key, final double min, final double max,
									   final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列化为 type 指定的对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRevRangeByScoreObject(final byte[] key, final double min, final double max,
									   final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列为对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRevRangeByScoreObject(final String key, final double min, final double max, final long offset,
									   final long count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列为对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRevRangeByScoreObject(final byte[] key, final double min, final double max, final long offset,
									   final long count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列化为 clazz 指定的对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRevRangeByScoreObject(final String key, final double min, final double max, final long offset,
									   final long count, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列化为 clazz 指定的对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 */
	<V> List<V> zRevRangeByScoreObject(final byte[] key, final double min, final double max, final long offset,
									   final long count, final Class<V> clazz);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列化为 type 指定的对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRevRangeByScoreObject(final String key, final double min, final double max, final long offset,
									   final long count, final TypeReference<V> type);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员，并反序列化为 type 指定的对象；
	 * 有序集成员按 score 值递减（从大到小）的次序排列；
	 * 具有相同 score 值的成员按字典序的逆序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrevrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回数量
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 指定区间内，有序集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zRevRangeByScoreObject(final byte[] key, final double min, final double max, final long offset,
									   final long count, final TypeReference<V> type);

	@Override
	default List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScoreWithScores(key, min, max));
	}

	@Override
	default List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max) {
		return execute((client)->client.sortedSetOperations().zRevRangeByScoreWithScores(key, min, max));
	}

	@Override
	default List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max,
												   final long offset, final long count) {
		return execute((client)->client.sortedSetOperations()
				.zRevRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	default List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max,
												   final long offset, final long count) {
		return execute((client)->client.sortedSetOperations()
				.zRevRangeByScoreWithScores(key, min, max, offset, count));
	}

	@Override
	default Long zRevRank(final String key, final String member) {
		return execute((client)->client.sortedSetOperations().zRevRank(key, member));
	}

	@Override
	default Long zRevRank(final byte[] key, final byte[] member) {
		return execute((client)->client.sortedSetOperations().zRevRank(key, member));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final long cursor) {
		return execute((client)->client.sortedSetOperations().zScan(key, cursor));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor) {
		return execute((client)->client.sortedSetOperations().zScan(key, cursor));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final String cursor) {
		return execute((client)->client.sortedSetOperations().zScan(key, cursor));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor) {
		return execute((client)->client.sortedSetOperations().zScan(key, cursor));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern) {
		return execute((client)->client.sortedSetOperations().zScan(key, cursor, pattern));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern) {
		return execute((client)->client.sortedSetOperations().zScan(key, cursor, pattern));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern) {
		return execute((client)->client.sortedSetOperations().zScan(key, cursor, pattern));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern) {
		return execute((client)->client.sortedSetOperations().zScan(key, cursor, pattern));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final long cursor, final long count) {
		return execute((client)->client.sortedSetOperations().zScan(key, cursor, count));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final long count) {
		return execute((client)->client.sortedSetOperations().zScan(key, cursor, count));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final String cursor, final long count) {
		return execute((client)->client.sortedSetOperations().zScan(key, cursor, count));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final long count) {
		return execute((client)->client.sortedSetOperations().zScan(key, cursor, count));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final long count) {
		return execute((client)->client.sortedSetOperations().zScan(key, cursor, pattern, count));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern, final long count) {
		return execute((client)->client.sortedSetOperations().zScan(key, cursor, pattern, count));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern,
										  final long count) {
		return execute((client)->client.sortedSetOperations().zScan(key, cursor, pattern, count));
	}

	@Override
	default ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern,
										  final long count) {
		return execute((client)->client.sortedSetOperations().zScan(key, cursor, pattern, count));
	}

	@Override
	default Double zScore(final String key, final String member) {
		return execute((client)->client.sortedSetOperations().zScore(key, member));
	}

	@Override
	default Double zScore(final byte[] key, final byte[] member) {
		return execute((client)->client.sortedSetOperations().zScore(key, member));
	}

	@Override
	default List<String> zUnion(final String... keys) {
		return execute((client)->client.sortedSetOperations().zUnion(keys));
	}

	@Override
	default List<byte[]> zUnion(final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zUnion(keys));
	}

	@Override
	default List<String> zUnion(final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, aggregate));
	}

	@Override
	default List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, aggregate));
	}

	@Override
	default List<String> zUnion(final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, weights));
	}

	@Override
	default List<byte[]> zUnion(final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, weights));
	}

	@Override
	default List<String> zUnion(final String[] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, aggregate, weights));
	}

	@Override
	default List<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnion(keys, aggregate, weights));
	}

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列为对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 反序列化为对象后的集合并集
	 */
	<V> List<V> zUnionObject(final String[] keys);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列为对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param <V>
	 * 		值类型
	 *
	 * @return 反序列化为对象后的集合并集
	 */
	<V> List<V> zUnionObject(final byte[][] keys);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 反序列化为对象后的集合并集
	 */
	<V> List<V> zUnionObject(final String[] keys, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 反序列化为对象后的集合并集
	 */
	<V> List<V> zUnionObject(final byte[][] keys, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 反序列化为对象后的集合并集
	 *
	 * @see TypeReference
	 */
	<V> List<V> zUnionObject(final String[] keys, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 反序列化为对象后的集合并集
	 *
	 * @see TypeReference
	 */
	<V> List<V> zUnionObject(final byte[][] keys, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列为对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列为对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列为对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnionObject(final String[] keys, final double[] weights);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列为对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnionObject(final byte[][] keys, final double[] weights);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnionObject(final String[] keys, final double[] weights, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnionObject(final byte[][] keys, final double[] weights, final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zUnionObject(final String[] keys, final double[] weights, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zUnionObject(final byte[][] keys, final double[] weights, final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列为对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate, final double[] weights);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列为对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate, final double[] weights);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate, final double[] weights,
							 final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 clazz 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param clazz
	 * 		值对象类
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 */
	<V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate, final double[] weights,
							 final Class<V> clazz);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zUnionObject(final String[] keys, final Aggregate aggregate, final double[] weights,
							 final TypeReference<V> type);

	/**
	 * 计算给定的一个或多个有序集的并集，并反序列化为 type 指定的对象
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 * @param type
	 * 		值类型引用
	 * @param <V>
	 * 		值类型
	 *
	 * @return 并集成员反序列化为对象的列表
	 *
	 * @see TypeReference
	 */
	<V> List<V> zUnionObject(final byte[][] keys, final Aggregate aggregate, final double[] weights,
							 final TypeReference<V> type);

	@Override
	default List<Tuple> zUnionWithScores(final String... keys) {
		return execute((client)->client.sortedSetOperations().zUnionWithScores(keys));
	}

	@Override
	default List<Tuple> zUnionWithScores(final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zUnionWithScores(keys));
	}

	@Override
	default List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zUnionWithScores(keys, aggregate));
	}

	@Override
	default List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zUnionWithScores(keys, aggregate));
	}

	@Override
	default List<Tuple> zUnionWithScores(final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnionWithScores(keys, weights));
	}

	@Override
	default List<Tuple> zUnionWithScores(final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnionWithScores(keys, weights));
	}

	@Override
	default List<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnionWithScores(keys, aggregate, weights));
	}

	@Override
	default List<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnionWithScores(keys, aggregate, weights));
	}

	@Override
	default Long zUnionStore(final String destKey, final String... keys) {
		return execute((client)->client.sortedSetOperations().zUnionStore(destKey, keys));
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final byte[]... keys) {
		return execute((client)->client.sortedSetOperations().zUnionStore(destKey, keys));
	}

	@Override
	default Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zUnionStore(destKey, keys, aggregate));
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate) {
		return execute((client)->client.sortedSetOperations().zUnionStore(destKey, keys, aggregate));
	}

	@Override
	default Long zUnionStore(final String destKey, final String[] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnionStore(destKey, keys, weights));
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final byte[][] keys, final double... weights) {
		return execute((client)->client.sortedSetOperations().zUnionStore(destKey, keys, weights));
	}

	@Override
	default Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate,
							 final double... weights) {
		return execute(
				(client)->client.sortedSetOperations().zUnionStore(destKey, keys, aggregate, weights));
	}

	@Override
	default Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate,
							 final double... weights) {
		return execute(
				(client)->client.sortedSetOperations().zUnionStore(destKey, keys, aggregate, weights));
	}

}
