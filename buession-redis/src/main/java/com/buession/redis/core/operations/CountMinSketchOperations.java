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
import com.buession.redis.core.CmsInfo;
import com.buession.redis.core.command.CountMinSketchCommands;

import java.util.List;

/**
 * 计数最小草图命令
 *
 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/?group=cms" target="_blank">https://redis.io/docs/latest/commands/?group=cms</a></p>
 *
 * @author Yong.Teng
 * @since 4.0.0
 */
public interface CountMinSketchOperations extends CountMinSketchCommands, RedisOperations {

	@Override
	default List<Long> cmsIncrby(final String key, final KeyValue<String, Long>... items) {
		return execute((client)->client.countMinSketchCommands().cmsIncrby(key, items));
	}

	@Override
	default List<Long> cmsIncrby(final byte[] key, final KeyValue<byte[], Long>... items) {
		return execute((client)->client.countMinSketchCommands().cmsIncrby(key, items));
	}

	@Override
	default CmsInfo cmsInfo(final String key) {
		return execute((client)->client.countMinSketchCommands().cmsInfo(key));
	}

	@Override
	default CmsInfo cmsInfo(final byte[] key) {
		return execute((client)->client.countMinSketchCommands().cmsInfo(key));
	}

	@Override
	default Status cmsInitByDim(final String key, final int width, final int depth) {
		return execute((client)->client.countMinSketchCommands().cmsInitByDim(key, width, depth));
	}

	@Override
	default Status cmsInitByDim(final byte[] key, final int width, final int depth) {
		return execute((client)->client.countMinSketchCommands().cmsInitByDim(key, width, depth));
	}

	@Override
	default Status cmsInitByProb(final String key, final double error, final double probability) {
		return execute((client)->client.countMinSketchCommands().cmsInitByProb(key, error, probability));
	}

	@Override
	default Status cmsInitByProb(final byte[] key, final double error, final double probability) {
		return execute((client)->client.countMinSketchCommands().cmsInitByProb(key, error, probability));
	}

	@Override
	default Status cmsMerge(final String destKey, final KeyValue<String, Long>... keysAndWeights) {
		return execute((client)->client.countMinSketchCommands().cmsMerge(destKey, keysAndWeights));
	}

	@Override
	default Status cmsMerge(final byte[] destKey, final KeyValue<byte[], Long>... keysAndWeights) {
		return execute((client)->client.countMinSketchCommands().cmsMerge(destKey, keysAndWeights));
	}

	/**
	 * 将多个 Count-Min Sketch（CMS）对象合并成一个新的 CMS
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cms.merge/" target="_blank">https://redis.io/docs/latest/commands/cms.merge/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param sourceKey
	 * 		原始 Key
	 * @param weight
	 * 		权重
	 *
	 * @return 操作结果
	 */
	default Status cmsMerge(final String destKey, final String sourceKey, final Long weight) {
		return cmsMerge(destKey, new KeyValue<>(sourceKey, weight));
	}

	/**
	 * 将多个 Count-Min Sketch（CMS）对象合并成一个新的 CMS
	 *
	 * <p>详情说明 <a href="https://redis.io/docs/latest/commands/cms.merge/" target="_blank">https://redis.io/docs/latest/commands/cms.merge/</a></p>
	 *
	 * @param destKey
	 * 		目标 Key
	 * @param sourceKey
	 * 		原始 Key
	 * @param weight
	 * 		权重
	 *
	 * @return 操作结果
	 */
	default Status cmsMerge(final byte[] destKey, final byte[] sourceKey, final Long weight) {
		return cmsMerge(destKey, new KeyValue<>(sourceKey, weight));
	}

	@Override
	default List<Long> cmsQuery(final String key, final String... items) {
		return execute((client)->client.countMinSketchCommands().cmsQuery(key, items));
	}

	@Override
	default List<Long> cmsQuery(final byte[] key, final byte[]... items) {
		return execute((client)->client.countMinSketchCommands().cmsQuery(key, items));
	}

}
