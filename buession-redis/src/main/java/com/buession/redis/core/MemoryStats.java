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
package com.buession.redis.core;

import com.buession.redis.utils.ObjectStringBuilder;

import java.util.Map;

/**
 * 内存统计信息
 *
 * @param dataset
 * 		全部数据所使用的内存{for dataset.bytes}
 * @param datasetPercentage
 * 		redis 数据占用内存占总内存分配的百分比{for dataset.percentage}
 * @param rssOverhead
 * 		常驻内存开销大小{for rss-overhead.bytes}
 * @param rssOverheadRatio
 * 		常驻内存开销比例{for rss-overhead.ratio}
 * @param peakPercentage
 * 		当前内存使用量与峰值时的占比{for peak.percentage}
 * @param aofBuffer
 * 		Aof 持久化使用的缓存和 Aof Rewrite 时产生的缓存之和，若关闭了 appendonly 则为 0{for aof.buffer}
 * @param keysBytesPerKey
 * 		平均每一个 key 的内存大小{for keys.bytes-per-key}
 * @param allocatorAllocated
 * 		分配器分配的内存{for allocator.allocated}
 * @param allocatorFragmentation
 * 		used_memory_rss 和 used_memory 的差值 {for allocator-fragmentation.bytes}
 * @param allocatorFragmentationRatio
 * 		used_memory_rss Redis从系统申请的内存和 used_memory Redis 给数据分配的内存的比率；不仅包括碎片，还包括其他进程及代码、共享库、堆栈的内存开销等等{for allocator-fragmentation.ratio}
 * @param allocatorActive
 * 		分配器活动页中的字节量，包含外部碎片{for allocator.active}
 * @param allocatorRss
 * 		分配器的常驻内存大小{for allocator-rss.bytes}
 * @param allocatorResident
 * 		分配器包含的总字节量{for allocator.resident}
 * @param clientsNormal
 * 		所有常规客户端消耗内存节字数{for clients.normal}
 * @param clientsSlaves
 * 		所有 slave clients 消耗的内存字节数{for clients.slaves}
 * @param fragmentation
 * 		内存碎片{for fragmentation.bytes}
 * @param fragmentationRatio
 * 		内存碎片率{for fragmentation}
 * @param luaCaches
 * 		Lua 脚本缓存开销内存节字数{for lua.caches}
 * @param peakAllocated
 * 		redis 启动至今，最多使用过多少内存{for peak.allocated}
 * @param totalAllocated
 * 		当前使用的内存总量{for total.allocated}
 * @param replicationBacklog
 * 		主从复制 backlog 使用的内存{for replication.backlog}
 * @param startupAllocated
 * 		redis 启动完成使用的内存字节数{for startup.allocated}
 * @param overheadTotal
 * 		redis 额外的总开销内存字节数；即分配器分配的总内存 total.allocated，减去数据实际存储使用内存{for overhead.total}
 * @param keysCount
 * 		key 总量{for keys.count}
 * @param dbs
 * 		每个 DB 统计{for db.x}
 *
 * @author Yong.Teng
 * @since 2.0.0
 */
public record MemoryStats(Long dataset, Double datasetPercentage, Long rssOverhead, Double rssOverheadRatio,
						  Double peakPercentage, Long aofBuffer, Long keysBytesPerKey, Long allocatorAllocated,
						  Long allocatorFragmentation, Double allocatorFragmentationRatio, Long allocatorActive,
						  Long allocatorRss, Double allocatorRssRatio, Long allocatorResident, Long clientsNormal,
						  Long clientsSlaves, Long fragmentation, Double fragmentationRatio, Long luaCaches,
						  Long peakAllocated, Long totalAllocated, Long replicationBacklog, Long startupAllocated,
						  Long overheadTotal, Long keysCount, Map<Integer, Db> dbs) {

	@Override
	public String toString() {
		final ObjectStringBuilder builder = ObjectStringBuilder.create().add("dataset", dataset)
				.add("dataset percentage", datasetPercentage).add("rss overhead", rssOverhead)
				.add("rss overhead ratio", rssOverheadRatio).add("peak percentage", peakPercentage)
				.add("aof buffer", aofBuffer).add("keys bytes per key", keysBytesPerKey)
				.add("allocator allocated", allocatorAllocated).add("allocator fragmentation", allocatorFragmentation)
				.add("allocator fragmentation ratio", allocatorFragmentationRatio)
				.add("allocator active", allocatorActive).add("allocator rss", allocatorRss)
				.add("clients normal", clientsNormal).add("clients slaves", clientsSlaves)
				.add("fragmentation", fragmentation).add("fragmentation ratio", fragmentationRatio)
				.add("lua caches", luaCaches).add("peak allocated", peakAllocated)
				.add("total allocated", totalAllocated).add("replication backlog", replicationBacklog)
				.add("startup allocated", startupAllocated).add("overhead total", overheadTotal)
				.add("keys count", keysCount);

		for(final Map.Entry<Integer, Db> entry : dbs.entrySet()){
			builder.add("db " + entry.getKey(), entry.getValue());
		}

		return builder.build();
	}

	public record Db(Long overheadHashTableMain, Long overheadHashTableExpires) {

		@Override
		public String toString() {
			return ObjectStringBuilder.create().add("overhead hash table main", overheadHashTableMain)
					.add("overhead hash table expires", overheadHashTableExpires).build();
		}

	}

}
