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
package com.buession.redis.core.command;

import com.buession.redis.core.Aggregate;
import com.buession.redis.core.GtLt;
import com.buession.redis.core.KeyedZSetElement;
import com.buession.redis.core.NxXx;
import com.buession.redis.core.ScanResult;
import com.buession.redis.core.Tuple;
import com.buession.redis.core.ZRangeBy;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 有序集合命令
 *
 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/index.html" target="_blank">http://redisdoc.com/sorted_set/index.html</a></p>
 *
 * @author Yong.Teng
 */
public interface SortedSetCommands extends RedisCommands {

	/**
	 * 删除并返回有序集合 key 中的 1 个具有最低得分的成员。
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zpopmin.html" target="_blank">https://www.redis.com.cn/commands/zpopmin.html</a></p>
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 *
	 * @return 弹出的元素和分数列表
	 */
	Tuple zPopMin(final String key);

	/**
	 * 删除并返回有序集合 key 中的 1 个具有最低得分的成员。
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zpopmin.html" target="_blank">https://www.redis.com.cn/commands/zpopmin.html</a></p>
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 *
	 * @return 弹出的元素和分数列表
	 */
	Tuple zPopMin(final byte[] key);

	/**
	 * 删除并返回有序集合 key 中的 count 个具有最低得分的成员。
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zpopmin.html" target="_blank">https://www.redis.com.cn/commands/zpopmin.html</a></p>
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 * @param count
	 * 		删除个数
	 *
	 * @return 弹出的元素和分数列表
	 */
	List<Tuple> zPopMin(final String key, final long count);

	/**
	 * 删除并返回有序集合 key 中的 count 个具有最低得分的成员。
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zpopmin.html" target="_blank">https://www.redis.com.cn/commands/zpopmin.html</a></p>
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 * @param count
	 * 		删除个数
	 *
	 * @return 弹出的元素和分数列表
	 */
	List<Tuple> zPopMin(final byte[] key, final long count);

	/**
	 * 删除并返回有序集合 key 中的 1 个具有最高得分的成员。
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 *
	 * @return 弹出的元素和分数列表
	 */
	Tuple zPopMax(final String key);

	/**
	 * 删除并返回有序集合 key 中的 1 个具有最高得分的成员。
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 *
	 * @return 弹出的元素和分数列表
	 */
	Tuple zPopMax(final byte[] key);

	/**
	 * 删除并返回有序集合 key 中的 count 个具有最高得分的成员。
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 * @param count
	 * 		删除个数
	 *
	 * @return 弹出的元素和分数列表
	 */
	List<Tuple> zPopMax(final String key, final long count);

	/**
	 * 删除并返回有序集合 key 中的 count 个具有最高得分的成员。
	 *
	 * @param key
	 * 		弹出的元素和分数列表
	 * @param count
	 * 		删除个数
	 *
	 * @return 弹出的元素和分数列表
	 */
	List<Tuple> zPopMax(final byte[] key, final long count);

	/**
	 * 从非空的第一个有序集中弹出得分最小的成员，按照命令中 key 出现的顺序检查；是有序集 ZPOPMIN 的阻塞变体；
	 * 当没有成员可以从任何给定的有序集中弹出时，它会阻塞连接
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/bzpopmin.html" target="_blank">https://www.redis.com.cn/commands/bzpopmin.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param timeout
	 * 		阻塞的最长时间；为 0 时，则无限期地阻塞，单位：秒
	 *
	 * @return 元素 key 名称，成员名称，元素分数
	 */
	KeyedZSetElement bzPopMin(final String[] keys, final int timeout);

	/**
	 * 从非空的第一个有序集中弹出得分最小的成员，按照命令中 key 出现的顺序检查；是有序集 ZPOPMIN 的阻塞变体；
	 * 当没有成员可以从任何给定的有序集中弹出时，它会阻塞连接
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/bzpopmin.html" target="_blank">https://www.redis.com.cn/commands/bzpopmin.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param timeout
	 * 		阻塞的最长时间；为 0 时，则无限期地阻塞，单位：秒
	 *
	 * @return 元素 key 名称，成员名称，元素分数
	 */
	KeyedZSetElement bzPopMin(final byte[][] keys, final int timeout);

	/**
	 * 从非空的第一个有序集中弹出得分最高的成员，按照命令中 key 出现的顺序检查；是有序集 ZPOPMAX 的阻塞变体；
	 * 当没有成员可以从任何给定的有序集中弹出时，它会阻塞连接
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/bzpopmax.html" target="_blank">https://www.redis.com.cn/commands/bzpopmax.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param timeout
	 * 		阻塞的最长时间；为 0 时，则无限期地阻塞，单位：秒
	 *
	 * @return 元素 key 名称，成员名称，元素分数
	 */
	KeyedZSetElement bzPopMax(final String[] keys, final int timeout);

	/**
	 * 从非空的第一个有序集中弹出得分最高的成员，按照命令中 key 出现的顺序检查；是有序集 ZPOPMAX 的阻塞变体；
	 * 当没有成员可以从任何给定的有序集中弹出时，它会阻塞连接
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/bzpopmax.html" target="_blank">https://www.redis.com.cn/commands/bzpopmax.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param timeout
	 * 		阻塞的最长时间；为 0 时，则无限期地阻塞，单位：秒
	 *
	 * @return 元素 key 名称，成员名称，元素分数
	 */
	KeyedZSetElement bzPopMax(final byte[][] keys, final int timeout);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	Long zAdd(final String key, final Map<String, Double> members);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	Long zAdd(final byte[] key, final Map<byte[], Double> members);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">http://redisdoc.com/sorted_set/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
	 * @param nxXx
	 * 		更新成员方式：
	 * 		1）NxXx.NX：不更新存在的成员，只添加新成员
	 * 		2）NxXx.XX：仅更新存在的成员，不添加新成员
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	Long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">http://redisdoc.com/sorted_set/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
	 * @param gtLt
	 * 		更新新的分值方式：
	 * 		1）GtLt.LT: 更新新的分值比当前分值小的成员，不存在则新增
	 * 		2）GtLt.GT: 更新新的分值比当前分值大的成员，不存在则新增
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	Long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	Long zAdd(final String key, final Map<String, Double> members, final boolean ch);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">http://redisdoc.com/sorted_set/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
	 * @param ch
	 * 		是否返回变更成员的数量；变更的成员是指新增成员 和 score值更新的成员，命令指明的和之前 score 值相同的成员不计在内；
	 * 		在通常情况下，ZADD返回值只计算新添加成员的数量
	 *
	 * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员
	 */
	Long zAdd(final byte[] key, final Map<byte[], Double> members, final boolean ch);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
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
	Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">http://redisdoc.com/sorted_set/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
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
	Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
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
	Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final boolean ch);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">http://redisdoc.com/sorted_set/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
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
	Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final boolean ch);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
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
	Long zAdd(final String key, final Map<String, Double> members, final GtLt gtLt, final boolean ch);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">http://redisdoc.com/sorted_set/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
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
	Long zAdd(final byte[] key, final Map<byte[], Double> members, final GtLt gtLt, final boolean ch);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">https://www.redis.com.cn/commands/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
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
	Long zAdd(final String key, final Map<String, Double> members, final NxXx nxXx, final GtLt gtLt, final boolean ch);

	/**
	 * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zadd.html" target="_blank">http://redisdoc.com/sorted_set/zadd.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个 member 元素及其 score
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
	Long zAdd(final byte[] key, final Map<byte[], Double> members, final NxXx nxXx, final GtLt gtLt, final boolean ch);

	/**
	 * 获取有序集 key 的基数
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcard.html" target="_blank">http://redisdoc.com/sorted_set/zcard.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 有序集 key 的基数
	 */
	Long zCard(final String key);

	/**
	 * 获取有序集 key 的基数
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcard.html" target="_blank">http://redisdoc.com/sorted_set/zcard.html</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 有序集 key 的基数
	 */
	Long zCard(final byte[] key);

	/**
	 * 获取有序集 key 中，score 值在 min 和 max 之间（包括 score 值等于 min 或 max ）的成员的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcount.html" target="_blank">http://redisdoc.com/sorted_set/zcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return score 值在 min 和 max 之间的成员的数量
	 */
	Long zCount(final String key, final double min, final double max);

	/**
	 * 获取有序集 key 中，score 值在 min 和 max 之间（包括 score 值等于 min 或 max ）的成员的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcount.html" target="_blank">http://redisdoc.com/sorted_set/zcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return score 值在 min 和 max 之间的成员的数量
	 */
	Long zCount(final byte[] key, final double min, final double max);

	/**
	 * 获取有序集 key 中，score 值在 min 和 max 之间（包括 score 值等于 min 或 max ）的成员的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcount.html" target="_blank">http://redisdoc.com/sorted_set/zcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return score 值在 min 和 max 之间的成员的数量
	 */
	Long zCount(final String key, final String min, final String max);

	/**
	 * 获取有序集 key 中，score 值在 min 和 max 之间（包括 score 值等于 min 或 max ）的成员的数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zcount.html" target="_blank">http://redisdoc.com/sorted_set/zcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return score 值在 min 和 max 之间的成员的数量
	 */
	Long zCount(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return The result of the difference
	 */
	Set<String> zDiff(final String... keys);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return The result of the difference
	 */
	Set<byte[]> zDiff(final byte[]... keys);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return The result of the difference with their scores
	 */
	Set<Tuple> zDiffWithScores(final String... keys);

	/**
	 * This command is similar to ZDIFFSTORE, but instead of storing the resulting sorted set, it is returned to the client
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiff/" target="_blank">https://redis.io/commands/zdiff/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return The result of the difference with their scores
	 */
	Set<Tuple> zDiffWithScores(final byte[]... keys);

	/**
	 * Computes the difference between the first and all successive input sorted sets and stores the result in destination
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiffstore/" target="_blank">https://redis.io/commands/zdiffstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 结果集中的元素数量
	 */
	Long zDiffStore(final String destKey, final String... keys);

	/**
	 * Computes the difference between the first and all successive input sorted sets and stores the result in destination
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zdiffstore/" target="_blank">https://redis.io/commands/zdiffstore/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 结果集中的元素数量
	 */
	Long zDiffStore(final byte[] destKey, final byte[]... keys);

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量 increment
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
	Double zIncrBy(final String key, final double increment, final String member);

	/**
	 * 为有序集 key 的成员 member 的 score 值加上增量 increment
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
	Double zIncrBy(final byte[] key, final double increment, final byte[] member);

	/**
	 * 计算给定的一个或多个有序集合的交集
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 有序集合的交集
	 */
	Set<String> zInter(final String... keys);

	/**
	 * 计算给定的一个或多个有序集合的交集
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 有序集合的交集
	 */
	Set<byte[]> zInter(final byte[]... keys);

	/**
	 * 计算给定的一个或多个有序集合的交集
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 *
	 * @return 有序集合的交集
	 */
	Set<String> zInter(final String[] keys, final Aggregate aggregate);

	/**
	 * 计算给定的一个或多个有序集合的交集
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 *
	 * @return 有序集合的交集
	 */
	Set<byte[]> zInter(final byte[][] keys, final Aggregate aggregate);

	/**
	 * 计算给定的一个或多个有序集合的交集
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 有序集合的交集
	 */
	Set<String> zInter(final String[] keys, final double... weights);

	/**
	 * 计算给定的一个或多个有序集合的交集
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 有序集合的交集
	 */
	Set<byte[]> zInter(final byte[][] keys, final double... weights);

	/**
	 * 计算给定的一个或多个有序集合的交集
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 有序集合的交集
	 */
	Set<String> zInter(final String[] keys, final Aggregate aggregate, final double... weights);

	/**
	 * 计算给定的一个或多个有序集合的交集
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 有序集合的交集
	 */
	Set<byte[]> zInter(final byte[][] keys, final Aggregate aggregate, final double... weights);

	/**
	 * 计算给定的一个或多个有序集合的交集
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 含 scores 的有序集合的交集
	 */
	Set<Tuple> zInterWithScores(final String... keys);

	/**
	 * 计算给定的一个或多个有序集合的交集
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 含 scores 的有序集合的交集
	 */
	Set<Tuple> zInterWithScores(final byte[]... keys);

	/**
	 * 计算给定的一个或多个有序集合的交集
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 *
	 * @return 含 scores 的有序集合的交集
	 */
	Set<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate);

	/**
	 * 计算给定的一个或多个有序集合的交集
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 *
	 * @return 含 scores 的有序集合的交集
	 */
	Set<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate);

	/**
	 * 计算给定的一个或多个有序集合的交集
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 含 scores 的有序集合的交集
	 */
	Set<Tuple> zInterWithScores(final String[] keys, final double... weights);

	/**
	 * 计算给定的一个或多个有序集合的交集
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 含 scores 的有序集合的交集
	 */
	Set<Tuple> zInterWithScores(final byte[][] keys, final double... weights);

	/**
	 * 计算给定的一个或多个有序集合的交集
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 含 scores 的有序集合的交集
	 */
	Set<Tuple> zInterWithScores(final String[] keys, final Aggregate aggregate, final double... weights);

	/**
	 * 计算给定的一个或多个有序集合的交集
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zinter/" target="_blank">https://redis.io/commands/zinter/</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 *        {@link Aggregate}
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 含 scores 的有序集合的交集
	 */
	Set<Tuple> zInterWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights);

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zinterstore.html" target="_blank">http://redisdoc.com/sorted_set/zinterstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	Long zInterStore(final String destKey, final String... keys);

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zinterstore.html" target="_blank">http://redisdoc.com/sorted_set/zinterstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	Long zInterStore(final byte[] destKey, final byte[]... keys);

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zinterstore.html" target="_blank">http://redisdoc.com/sorted_set/zinterstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate);

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zinterstore.html" target="_blank">http://redisdoc.com/sorted_set/zinterstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate);

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zinterstore.html" target="_blank">http://redisdoc.com/sorted_set/zinterstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	Long zInterStore(final String destKey, final String[] keys, final double... weights);

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zinterstore.html" target="_blank">http://redisdoc.com/sorted_set/zinterstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	Long zInterStore(final byte[] destKey, final byte[][] keys, final double... weights);

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zinterstore.html" target="_blank">http://redisdoc.com/sorted_set/zinterstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	Long zInterStore(final String destKey, final String[] keys, final Aggregate aggregate, final double... weights);

	/**
	 * 计算给定的一个或多个有序集的交集，并将该交集储存到 destKey 中
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zinterstore.html" target="_blank">http://redisdoc.com/sorted_set/zinterstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 保存到 destKey 结果集的基数
	 */
	Long zInterStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate, final double... weights);

	/**
	 * 获取集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zlexcount.html" target="_blank">http://redisdoc.com/sorted_set/zlexcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 */
	Long zLexCount(final String key, final double min, final double max);

	/**
	 * 获取集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zlexcount.html" target="_blank">http://redisdoc.com/sorted_set/zlexcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 */
	Long zLexCount(final byte[] key, final double min, final double max);

	/**
	 * 获取集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zlexcount.html" target="_blank">http://redisdoc.com/sorted_set/zlexcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 */
	Long zLexCount(final String key, final String min, final String max);

	/**
	 * 获取集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zlexcount.html" target="_blank">http://redisdoc.com/sorted_set/zlexcount.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 集合 Key 中，成员介于 min 和 max 范围内的元素数量
	 */
	Long zLexCount(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 返回有序集中指定成员的 members 的 scores
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zmscore.html" target="_blank">https://www.redis.com.cn/commands/zmscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		成员
	 *
	 * @return 有序集中指定成员的 members 的 scores
	 */
	List<Double> zMScore(final String key, final String... members);

	/**
	 * 返回有序集中指定成员的 members 的 scores
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zmscore.html" target="_blank">https://www.redis.com.cn/commands/zmscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		成员
	 *
	 * @return 有序集中指定成员的 members 的 scores
	 */
	List<Double> zMScore(final byte[] key, final byte[]... members);

	/**
	 * 返回有序集合 key 中的一个随机元素
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 有序集合 key 中的一个随机元素
	 */
	String zRandMember(final String key);

	/**
	 * 返回有序集合 key 中的一个随机元素
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 *
	 * @return 有序集合 key 中的一个随机元素
	 */
	byte[] zRandMember(final byte[] key);

	/**
	 * 返回有序集合 key 中的 count 个随机元素
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 *
	 * @return 有序集合 key 中的随机元素
	 */
	List<String> zRandMember(final String key, final long count);

	/**
	 * 返回有序集合 key 中的 count 个随机元素
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 *
	 * @return 有序集合 key 中的随机元素
	 */
	List<byte[]> zRandMember(final byte[] key, final long count);

	/**
	 * 返回有序集合 key 中的 count 个随机元素及 score
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 *
	 * @return 有序集合 key 中的随机元素
	 */
	List<Tuple> zRandMemberWithScores(final String key, final long count);

	/**
	 * 返回有序集合 key 中的 count 个随机元素及 score
	 *
	 * <p>详情说明 <a href="https://redis.io/commands/zrandmember/" target="_blank">https://redis.io/commands/zrandmember/</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param count
	 * 		需要返回的元素数量
	 *
	 * @return 有序集合 key 中的随机元素
	 */
	List<Tuple> zRandMemberWithScores(final byte[] key, final long count);

	/**
	 * 获取有序集 key 中，指定区间内的成员；其中成员的位置按 score 值递增(从小到大)来排序；
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
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	List<String> zRange(final String key, final long start, final long end);

	/**
	 * 获取有序集 key 中，指定区间内的成员；其中成员的位置按 score 值递增(从小到大)来排序；
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
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	List<byte[]> zRange(final byte[] key, final long start, final long end);

	/**
	 * 获取有序集 key 中，指定区间内的带有 score 成员；其中成员的位置按 score 值递增(从小到大)来排序；
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
	 *
	 * @return 指定区间内，带有 score 有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	List<Tuple> zRangeWithScores(final String key, final long start, final long end);

	/**
	 * 获取有序集 key 中，指定区间内的带有 score 成员；其中成员的位置按 score 值递增(从小到大)来排序；
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
	 *
	 * @return 指定区间内，带有 score 有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	List<Tuple> zRangeWithScores(final byte[] key, final long start, final long end);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员的列表
	 */
	List<String> zRangeByLex(final String key, final double min, final double max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员的列表
	 */
	List<byte[]> zRangeByLex(final byte[] key, final double min, final double max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员的列表
	 */
	List<String> zRangeByLex(final String key, final String min, final String max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员的列表
	 */
	List<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的从 offset 开始的 count 个成员
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
	 *
	 * @return 包含了有序集合在指定范围内从 offset 开始的 count 个成员的列表
	 */
	List<String> zRangeByLex(final String key, final double min, final double max, final long offset, final long count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的从 offset 开始的 count 个成员
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
	 *
	 * @return 包含了有序集合在指定范围内从 offset 开始的 count 个成员的列表
	 */
	List<byte[]> zRangeByLex(final byte[] key, final double min, final double max, final long offset, final long count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的从 offset 开始的 count 个成员
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
	 *
	 * @return 包含了有序集合在指定范围内从 offset 开始的 count 个成员的列表
	 */
	List<String> zRangeByLex(final String key, final String min, final String max, final long offset, final long count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的从 offset 开始的 count 个成员
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
	 *
	 * @return 包含了有序集合在指定范围内从 offset 开始的 count 个成员的列表
	 */
	List<byte[]> zRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset, final long count);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<String> zRangeByScore(final String key, final double min, final double max);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<byte[]> zRangeByScore(final byte[] key, final double min, final double max);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<String> zRangeByScore(final String key, final String min, final String max);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
	 * 有序集成员按 score 值递增（从小到大）次序排列，具有相同 score 值的成员按字典序排列
	 *
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
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
	 *
	 * @return 指定区间内从 offset 开是的 count 个，有序集成员的列表
	 */
	List<String> zRangeByScore(final String key, final double min, final double max, final long offset,
							   final long count);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
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
	 *
	 * @return 指定区间内从 offset 开是的 count 个，有序集成员的列表
	 */
	List<byte[]> zRangeByScore(final byte[] key, final double min, final double max, final long offset,
							   final long count);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
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
	 *
	 * @return 指定区间内从 offset 开是的 count 个，有序集成员的列表
	 */
	List<String> zRangeByScore(final String key, final String min, final String max, final long offset,
							   final long count);

	/**
	 * 获取有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
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
	 *
	 * @return 指定区间内从 offset 开是的 count 个，有序集成员的列表
	 */
	List<byte[]> zRangeByScore(final byte[] key, final byte[] min, final byte[] max, final long offset,
							   final long count);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
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
	 *
	 * @return 指定区间内，带有 score 的有序集成员的列表
	 */
	List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
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
	 *
	 * @return 指定区间内，带有 score 的有序集成员的列表
	 */
	List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
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
	 *
	 * @return 指定区间内，带有 score 的有序集成员的列表
	 */
	List<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员；
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
	 *
	 * @return 指定区间内，带有 score 的有序集成员的列表
	 */
	List<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
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
	 *
	 * @return 指定区间内带有 score 的从 offset 开是的 count 个，有序集成员的列表
	 */
	List<Tuple> zRangeByScoreWithScores(final String key, final double min, final double max, final long offset,
										final long count);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
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
	 *
	 * @return 指定区间内带有 score 的从 offset 开是的 count 个，有序集成员的列表
	 */
	List<Tuple> zRangeByScoreWithScores(final byte[] key, final double min, final double max, final long offset,
										final long count);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
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
	 *
	 * @return 指定区间内带有 score 的从 offset 开是的 count 个，有序集成员的列表
	 */
	List<Tuple> zRangeByScoreWithScores(final String key, final String min, final String max, final long offset,
										final long count);

	/**
	 * 获取有序集 key 中，带有 score 的所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
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
	 *
	 * @return 指定区间内带有 score 的从 offset 开是的 count 个，有序集成员的列表
	 */
	List<Tuple> zRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final long offset,
										final long count);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并保存到 destKey 中；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return The number of elements in the resulting sorted set
	 */
	Long zRangeStore(final String destKey, final String key, final long start, final long end);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并保存到 destKey 中；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 *
	 * @return The number of elements in the resulting sorted set
	 */
	Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并保存到 destKey 中；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param by
	 *        {@link ZRangeBy}
	 *
	 * @return The number of elements in the resulting sorted set
	 */
	Long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并保存到 destKey 中；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param by
	 *        {@link ZRangeBy}
	 *
	 * @return The number of elements in the resulting sorted set
	 */
	Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并保存到 destKey 中；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param rev
	 * 		The REV options reverses the order of the 'start' and 'end' elements
	 *
	 * @return The number of elements in the resulting sorted set
	 */
	Long zRangeStore(final String destKey, final String key, final long start, final long end, final boolean rev);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并保存到 destKey 中；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param rev
	 * 		The REV options reverses the order of the 'start' and 'end' elements
	 *
	 * @return The number of elements in the resulting sorted set
	 */
	Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final boolean rev);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并保存到 destKey 中；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 *
	 * @return The number of elements in the resulting sorted set
	 */
	Long zRangeStore(final String destKey, final String key, final long start, final long end, final long offset,
					 final long count);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并保存到 destKey 中；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 *
	 * @return The number of elements in the resulting sorted set
	 */
	Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final long offset,
					 final long count);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并保存到 destKey 中；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param by
	 *        {@link ZRangeBy}
	 * @param rev
	 * 		The REV options reverses the order of the 'start' and 'end' elements
	 *
	 * @return The number of elements in the resulting sorted set
	 */
	Long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by,
					 final boolean rev);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并保存到 destKey 中；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param by
	 *        {@link ZRangeBy}
	 * @param rev
	 * 		The REV options reverses the order of the 'start' and 'end' elements
	 *
	 * @return The number of elements in the resulting sorted set
	 */
	Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by,
					 final boolean rev);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并保存到 destKey 中；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param by
	 *        {@link ZRangeBy}
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 *
	 * @return The number of elements in the resulting sorted set
	 */
	Long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by,
					 final long offset, final long count);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并保存到 destKey 中；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param by
	 *        {@link ZRangeBy}
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 *
	 * @return The number of elements in the resulting sorted set
	 */
	Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by,
					 final long offset, final long count);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并保存到 destKey 中；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param rev
	 * 		The REV options reverses the order of the 'start' and 'end' elements
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 *
	 * @return The number of elements in the resulting sorted set
	 */
	Long zRangeStore(final String destKey, final String key, final long start, final long end, final boolean rev,
					 final long offset, final long count);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并保存到 destKey 中；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param rev
	 * 		The REV options reverses the order of the 'start' and 'end' elements
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 *
	 * @return The number of elements in the resulting sorted set
	 */
	Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final boolean rev,
					 final long offset, final long count);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并保存到 destKey 中；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param by
	 *        {@link ZRangeBy}
	 * @param rev
	 * 		The REV options reverses the order of the 'start' and 'end' elements
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 *
	 * @return The number of elements in the resulting sorted set
	 */
	Long zRangeStore(final String destKey, final String key, final long start, final long end, final ZRangeBy by,
					 final boolean rev, final long offset, final long count);

	/**
	 * 获取有序集 key 中，指定区间内的成员，并保存到 destKey 中；其中成员的位置按 score 值递增(从小到大)来排序；
	 * 具有相同 score 值的成员按字典序来排列；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始位置
	 * @param end
	 * 		结束位置
	 * @param by
	 *        {@link ZRangeBy}
	 * @param rev
	 * 		The REV options reverses the order of the 'start' and 'end' elements
	 * @param offset
	 * 		偏移量
	 * @param count
	 * 		返回个数
	 *
	 * @return The number of elements in the resulting sorted set
	 */
	Long zRangeStore(final byte[] destKey, final byte[] key, final long start, final long end, final ZRangeBy by,
					 final boolean rev, final long offset, final long count);

	/**
	 * 获取有序集 key 中成员 member 的排名；其中有序集成员按 score 值递增（从小到大）顺序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrank.html" target="_blank">http://redisdoc.com/sorted_set/zrank.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member
	 *
	 * @return 如果 member 是有序集 key 的成员，返回 member 的排名；
	 * 如果 member 不是有序集 key 的成员，返回 null
	 */
	Long zRank(final String key, final String member);

	/**
	 * 获取有序集 key 中成员 member 的排名；其中有序集成员按 score 值递增（从小到大）顺序排列
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrank.html" target="_blank">http://redisdoc.com/sorted_set/zrank.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member
	 *
	 * @return 如果 member 是有序集 key 的成员，返回 member 的排名；
	 * 如果 member 不是有序集 key 的成员，返回 null
	 */
	Long zRank(final byte[] key, final byte[] member);

	/**
	 * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrem.html" target="_blank">http://redisdoc.com/sorted_set/zrem.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个成员
	 *
	 * @return 被成功移除的成员的数量，不包括被忽略的成员
	 */
	Long zRem(final String key, final String... members);

	/**
	 * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrem.html" target="_blank">http://redisdoccom/sorted_set/zrem.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param members
	 * 		一个或多个成员
	 *
	 * @return 被成功移除的成员的数量，不包括被忽略的成员
	 */
	Long zRem(final byte[] key, final byte[]... members);

	/**
	 * 对于一个所有成员的分值都相同的有序集合键 key 来说，这个命令会移除该集合中，成员介于 min 和 max 范围内的所有元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zremrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除的元素数量
	 */
	Long zRemRangeByLex(final String key, final double min, final double max);

	/**
	 * 对于一个所有成员的分值都相同的有序集合键 key 来说，这个命令会移除该集合中，成员介于 min 和 max 范围内的所有元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zremrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除的元素数量
	 */
	Long zRemRangeByLex(final byte[] key, final double min, final double max);

	/**
	 * 对于一个所有成员的分值都相同的有序集合键 key 来说，这个命令会移除该集合中，成员介于 min 和 max 范围内的所有元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zremrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除的元素数量
	 */
	Long zRemRangeByLex(final String key, final String min, final String max);

	/**
	 * 对于一个所有成员的分值都相同的有序集合键 key 来说，这个命令会移除该集合中，成员介于 min 和 max 范围内的所有元素
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zremrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除的元素数量
	 */
	Long zRemRangeByLex(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zremrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByScore(final String key, final double min, final double max);

	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zremrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByScore(final byte[] key, final double min, final double max);

	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zremrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByScore(final String key, final String min, final String max);

	/**
	 * 移除有序集 key 中，所有 score 值介于 min 和 max 之间（包括等于 min 或 max ）的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyscore.html" target="_blank">http://redisdoc.com/sorted_set/zremrangebyscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByScore(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 移除有序集 key 中，指定排名（rank）区间内的所有成员；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyrank.html" target="_blank">http://redisdoc.com/sorted_set/zremrangebyrank.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始排名
	 * @param end
	 * 		结束排名
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByRank(final String key, final long start, final long end);

	/**
	 * 移除有序集 key 中，指定排名（rank）区间内的所有成员；
	 * 也可以使用负数下标，以 -1 表示最后一个成员，-2 表示倒数第二个成员，以此类推
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zremrangebyrank.html" target="_blank">http://redisdoc.com/sorted_set/zremrangebyrank.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param start
	 * 		开始排名
	 * @param end
	 * 		结束排名
	 *
	 * @return 被移除成员的数量
	 */
	Long zRemRangeByRank(final byte[] key, final long start, final long end);

	/**
	 * 获取有序集 key 中，指定区间内的成员；其中成员的位置按 score 值递减（从大到小）来排列；
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
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	List<String> zRevRange(final String key, final long start, final long end);

	/**
	 * 获取有序集 key 中，指定区间内的成员；其中成员的位置按 score 值递减（从大到小）来排列；
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
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	List<byte[]> zRevRange(final byte[] key, final long start, final long end);

	/**
	 * 获取有序集 key 中，带有 score 指定区间内的成员；其中成员的位置按 score 值递减（从大到小）来排列；
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
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	List<Tuple> zRevRangeWithScores(final String key, final long start, final long end);

	/**
	 * 获取有序集 key 中，带有 score 指定区间内的成员；其中成员的位置按 score 值递减（从大到小）来排列；
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
	 *
	 * @return 指定区间内，有序集成员的列表；
	 * 当 start 的值比有序集的最大下标还要大，或是 start &gt; end 时，返回一个空列表；
	 * 当 end 参数的值比有序集的最大下标还要大时，最多返回到 end
	 */
	List<Tuple> zRevRangeWithScores(final byte[] key, final long start, final long end);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	List<String> zRevRangeByLex(final String key, final double min, final double max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	List<String> zRevRangeByLex(final String key, final String min, final String max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间的成员
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrangebylex.html" target="_blank">http://redisdoc.com/sorted_set/zrangebylex.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param min
	 * 		最小 score
	 * @param max
	 * 		最大 score
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	List<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间从 offset 开始的 count 个成员
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
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	List<String> zRevRangeByLex(final String key, final double min, final double max, final long offset,
								final long count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间从 offset 开始的 count 个成员
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
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	List<byte[]> zRevRangeByLex(final byte[] key, final double min, final double max, final long offset,
								final long count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间从 offset 开始的 count 个成员
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
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	List<String> zRevRangeByLex(final String key, final String min, final String max, final long offset,
								final long count);

	/**
	 * 当有序集合的所有成员都具有相同的分值时，有序集合的元素会根据成员的字典序来进行排序，
	 * 而这个命令则可以返回给定的有序集合键 key 中，值介于 min 和 max 之间从 offset 开始的 count 个成员
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
	 *
	 * @return 包含了有序集合在指定范围内的成员列表
	 */
	List<byte[]> zRevRangeByLex(final byte[] key, final byte[] min, final byte[] max, final long offset,
								final long count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<String> zRevRangeByScore(final String key, final double min, final double max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<String> zRevRangeByScore(final String key, final String min, final String max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的所有的成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<String> zRevRangeByScore(final String key, final double min, final double max, final long offset,
								  final long count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<byte[]> zRevRangeByScore(final byte[] key, final double min, final double max, final long offset,
								  final long count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<String> zRevRangeByScore(final String key, final String min, final String max, final long offset,
								  final long count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<byte[]> zRevRangeByScore(final byte[] key, final byte[] min, final byte[] max, final long offset,
								  final long count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的带有 score 的所有的成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的带有 score 的所有的成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的带有 score 的所有的成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）的带有 score 的所有的成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个带有 score 的成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<Tuple> zRevRangeByScoreWithScores(final String key, final double min, final double max, final long offset,
										   final long count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个带有 score 的成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final double min, final double max, final long offset,
										   final long count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个带有 score 的成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<Tuple> zRevRangeByScoreWithScores(final String key, final String min, final String max, final long offset,
										   final long count);

	/**
	 * 获取有序集 key 中，score 值介于 min 和 max 之间（包括等于 min 或 max ）从 offset 开始的 count 个带有 score 的成员；
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
	 *
	 * @return 指定区间内，有序集成员的列表
	 */
	List<Tuple> zRevRangeByScoreWithScores(final byte[] key, final byte[] min, final byte[] max, final long offset,
										   final long count);

	/**
	 * 获取有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减（从大到小）排序
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrank.html" target="_blank">http://redisdoc.com/sorted_set/zrevrank.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member
	 *
	 * @return 如果 member 是有序集 key 的成员，返回 member 的排名；
	 * 如果 member 不是有序集 key 的成员，返回 null
	 */
	Long zRevRank(final String key, final String member);

	/**
	 * 获取有序集 key 中成员 member 的排名。其中有序集成员按 score 值递减（从大到小）排序
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zrevrank.html" target="_blank">http://redisdoc.com/sorted_set/zrevrank.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member
	 *
	 * @return 如果 member 是有序集 key 的成员，返回 member 的排名；
	 * 如果 member 不是有序集 key 的成员，返回 null
	 */
	Long zRevRank(final byte[] key, final byte[] member);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	ScanResult<List<Tuple>> zScan(final String key, final long cursor);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	ScanResult<List<Tuple>> zScan(final String key, final String cursor);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 *
	 * @return 返回的每个元素都是一个键值对
	 */
	ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 *
	 * @return 返回和给定模式相匹配的元素
	 */
	ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 *
	 * @return 返回和给定模式相匹配的元素
	 */
	ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 *
	 * @return 返回和给定模式相匹配的元素
	 */
	ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 *
	 * @return 返回和给定模式相匹配的元素
	 */
	ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 *
	 * @return 返回的指定数量的键值对
	 */
	ScanResult<List<Tuple>> zScan(final String key, final long cursor, final long count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 *
	 * @return 返回的指定数量的键值对
	 */
	ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final long count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 *
	 * @return 返回的指定数量的键值对
	 */
	ScanResult<List<Tuple>> zScan(final String key, final String cursor, final long count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param count
	 * 		返回元素数量
	 *
	 * @return 返回的指定数量的键值对
	 */
	ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final long count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 *
	 * @return 返回和给定模式相匹配指定数量的元素
	 */
	ScanResult<List<Tuple>> zScan(final String key, final long cursor, final String pattern, final long count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 *
	 * @return 返回和给定模式相匹配指定数量的元素
	 */
	ScanResult<List<Tuple>> zScan(final byte[] key, final long cursor, final byte[] pattern, final long count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 *
	 * @return 返回和给定模式相匹配指定数量的元素
	 */
	ScanResult<List<Tuple>> zScan(final String key, final String cursor, final String pattern, final long count);

	/**
	 * 迭代有序集 key 中的键值对
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscan.html" target="_blank">http://redisdoc.com/sorted_set/zscan.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param cursor
	 * 		游标
	 * @param pattern
	 * 		glob 风格的模式参数
	 * @param count
	 * 		返回元素数量
	 *
	 * @return 返回和给定模式相匹配指定数量的元素
	 */
	ScanResult<List<Tuple>> zScan(final byte[] key, final byte[] cursor, final byte[] pattern, final long count);

	/**
	 * 获取有序集 key 中，成员 member 的 score 值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscore.html" target="_blank">http://redisdoc.com/sorted_set/zscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member
	 *
	 * @return member 成员的 score 值
	 */
	Double zScore(final String key, final String member);

	/**
	 * 获取有序集 key 中，成员 member 的 score 值
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zscore.html" target="_blank">http://redisdoc.com/sorted_set/zscore.html</a></p>
	 *
	 * @param key
	 * 		Key
	 * @param member
	 * 		member
	 *
	 * @return member 成员的 score 值
	 */
	Double zScore(final byte[] key, final byte[] member);

	/**
	 * 计算给定的一个或多个有序集的并集
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 集合的并集
	 */
	Set<String> zUnion(final String... keys);

	/**
	 * 计算给定的一个或多个有序集的并集
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 集合的并集
	 */
	Set<byte[]> zUnion(final byte[]... keys);

	/**
	 * 计算给定的一个或多个有序集的并集
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 *
	 * @return 集合的并集
	 */
	Set<String> zUnion(final String[] keys, final Aggregate aggregate);

	/**
	 * 计算给定的一个或多个有序集的并集
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 *
	 * @return 集合的并集
	 */
	Set<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate);

	/**
	 * 计算给定的一个或多个有序集的并集
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 集合的并集
	 */
	Set<String> zUnion(final String[] keys, final double... weights);

	/**
	 * 计算给定的一个或多个有序集的并集
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 集合的并集
	 */
	Set<byte[]> zUnion(final byte[][] keys, final double... weights);

	/**
	 * 计算给定的一个或多个有序集的并集
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 集合的并集
	 */
	Set<String> zUnion(final String[] keys, final Aggregate aggregate, final double... weights);

	/**
	 * 计算给定的一个或多个有序集的并集
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 集合的并集
	 */
	Set<byte[]> zUnion(final byte[][] keys, final Aggregate aggregate, final double... weights);

	/**
	 * 计算给定的一个或多个有序集的并集
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 带有分数的集合的并集
	 */
	Set<Tuple> zUnionWithScores(final String... keys);

	/**
	 * 计算给定的一个或多个有序集的并集
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 带有分数的集合的并集
	 */
	Set<Tuple> zUnionWithScores(final byte[]... keys);

	/**
	 * 计算给定的一个或多个有序集的并集
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 *
	 * @return 带有分数的集合的并集
	 */
	Set<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate);

	/**
	 * 计算给定的一个或多个有序集的并集
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 *
	 * @return 带有分数的集合的并集
	 */
	Set<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate);

	/**
	 * 计算给定的一个或多个有序集的并集
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 带有分数的集合的并集
	 */
	Set<Tuple> zUnionWithScores(final String[] keys, final double... weights);

	/**
	 * 计算给定的一个或多个有序集的并集
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 带有分数的集合的并集
	 */
	Set<Tuple> zUnionWithScores(final byte[][] keys, final double... weights);

	/**
	 * 计算给定的一个或多个有序集的并集
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 带有分数的集合的并集
	 */
	Set<Tuple> zUnionWithScores(final String[] keys, final Aggregate aggregate, final double... weights);

	/**
	 * 计算给定的一个或多个有序集的并集
	 *
	 * <p>详情说明 <a href="https://www.redis.com.cn/commands/zunion.html" target="_blank">https://www.redis.com.cn/commands/zunion.html</a></p>
	 *
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 带有分数的集合的并集
	 */
	Set<Tuple> zUnionWithScores(final byte[][] keys, final Aggregate aggregate, final double... weights);

	/**
	 * 计算给定的一个或多个有序集的并集，并将该并集储存到 destKey
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zunionstore.html" target="_blank">http://redisdoc.com/sorted_set/zunionstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	Long zUnionStore(final String destKey, final String... keys);

	/**
	 * 计算给定的一个或多个有序集的并集，并将该并集储存到 destKey
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zunionstore.html" target="_blank">http://redisdoc.com/sorted_set/zunionstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	Long zUnionStore(final byte[] destKey, final byte[]... keys);

	/**
	 * 计算给定的一个或多个有序集的并集，并将该并集储存到 destKey
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zunionstore.html" target="_blank">http://redisdoc.com/sorted_set/zunionstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate);

	/**
	 * 计算给定的一个或多个有序集的并集，并将该并集储存到 destKey
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zunionstore.html" target="_blank">http://redisdoc.com/sorted_set/zunionstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate);

	/**
	 * 计算给定的一个或多个有序集的并集，并将该并集储存到 destKey
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zunionstore.html" target="_blank">http://redisdoc.com/sorted_set/zunionstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	Long zUnionStore(final String destKey, final String[] keys, final double... weights);

	/**
	 * 计算给定的一个或多个有序集的并集，并将该并集储存到 destKey
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zunionstore.html" target="_blank">http://redisdoc.com/sorted_set/zunionstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	Long zUnionStore(final byte[] destKey, final byte[][] keys, final double... weights);

	/**
	 * 计算给定的一个或多个有序集的并集，并将该并集储存到 destKey
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zunionstore.html" target="_blank">http://redisdoc.com/sorted_set/zunionstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	Long zUnionStore(final String destKey, final String[] keys, final Aggregate aggregate, final double... weights);

	/**
	 * 计算给定的一个或多个有序集的并集，并将该并集储存到 destKey
	 *
	 * <p>详情说明 <a href="http://redisdoc.com/sorted_set/zunionstore.html" target="_blank">http://redisdoc.com/sorted_set/zunionstore.html</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param keys
	 * 		一个或多个 Key
	 * @param aggregate
	 * 		并集的结果集的聚合方式
	 * @param weights
	 * 		每个给定有序集的乘法因子
	 *
	 * @return 保存到 destKey 的结果集的基数
	 */
	Long zUnionStore(final byte[] destKey, final byte[][] keys, final Aggregate aggregate, final double... weights);

}
