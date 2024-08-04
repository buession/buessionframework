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
package com.buession.redis.core;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Yong.Teng
 * @since 2.0.0
 */
public class MemoryStats implements Serializable {

	private final static long serialVersionUID = -5308964580953471955L;

	/**
	 * 全部数据所使用的内存{for dataset.bytes}
	 */
	private Long dataset;

	/**
	 * redis 数据占用内存占总内存分配的百分比{for dataset.percentage}
	 */
	private Double datasetPercentage;

	/**
	 * 常驻内存开销大小{for rss-overhead.bytes}
	 */
	private Long rssOverhead;

	/**
	 * 常驻内存开销比例{for rss-overhead.ratio}
	 */
	private Double rssOverheadRatio;

	/**
	 * 当前内存使用量与峰值时的占比{for peak.percentage}
	 */
	private Double peakPercentage;

	/**
	 * Aof 持久化使用的缓存和 Aof Rewrite 时产生的缓存之和，若关闭了 appendonly 则为 0{for aof.buffer}
	 */
	private Long aofBuffer;

	/**
	 * 平均每一个 key 的内存大小{for keys.bytes-per-key}
	 */
	private Long keysBytesPerKey;

	/**
	 * 分配器分配的内存{for allocator.allocated}
	 */
	private Long allocatorAllocated;

	/**
	 * used_memory_rss 和 used_memory 的差值 {for allocator-fragmentation.bytes}
	 */
	private Long allocatorFragmentation;

	/**
	 * used_memory_rss Redis从系统申请的内存和 used_memory Redis 给数据分配的内存的比率；不仅包括碎片，还包括其他进程及代码、共享库、堆栈的内存开销等等{for allocator
	 * -fragmentation.ratio}
	 */
	private Double allocatorFragmentationRatio;

	/**
	 * 分配器活动页中的字节量，包含外部碎片{for allocator.active}
	 */
	private Long allocatorActive;

	/**
	 * 分配器的常驻内存大小{for allocator-rss.bytes}
	 */
	private Long allocatorRss;

	/**
	 * 分配器常驻内存比例{for allocator-rss.ratio}
	 */
	private Double allocatorRssRatio;

	/**
	 * 分配器包含的总字节量{for allocator.resident}
	 */
	private Long allocatorResident;

	/**
	 * 所有常规客户端消耗内存节字数{for clients.normal}
	 */
	private Long clientsNormal;

	/**
	 * 所有 slave clients 消耗的内存字节数{for clients.slaves}
	 */
	private Long clientsSlaves;

	/**
	 * 内存碎片{for fragmentation.bytes}
	 */
	private Long fragmentation;

	/**
	 * 内存碎片率{for fragmentation}
	 */
	private Double fragmentationRatio;

	/**
	 * Lua 脚本缓存开销内存节字数{for lua.caches}
	 */
	private Long luaCaches;

	/**
	 * redis 启动至今，最多使用过多少内存{for peak.allocated}
	 */
	private Long peakAllocated;

	/**
	 * 当前使用的内存总量{for total.allocated}
	 */
	private Long totalAllocated;

	/**
	 * 主从复制 backlog 使用的内存{for replication.backlog}
	 */
	private Long replicationBacklog;

	/**
	 * redis 启动完成使用的内存字节数{for startup.allocated}
	 */
	private Long startupAllocated;

	/**
	 * redis 额外的总开销内存字节数；即分配器分配的总内存 total.allocated，减去数据实际存储使用内存{for overhead.total}
	 */
	private Long overheadTotal;

	/**
	 * key 总量{for keys.count}
	 */
	private Long keysCount;

	/**
	 * 每个 DB 统计{for db.x}
	 */
	private Map<Integer, Db> dbs;

	/**
	 * 返回全部数据所使用的内存
	 *
	 * @return 全部数据所使用的内存{for dataset.bytes}
	 */
	public Long getDataset() {
		return dataset;
	}

	/**
	 * 设置全部数据所使用的内存{for dataset.bytes}
	 *
	 * @param dataset
	 * 		全部数据所使用的内存
	 */
	public void setDataset(Long dataset) {
		this.dataset = dataset;
	}

	/**
	 * 返回 redis 数据占用内存占总内存分配的百分比
	 *
	 * @return redis 数据占用内存占总内存分配的百分比{for dataset.percentage}
	 */
	public Double getDatasetPercentage() {
		return datasetPercentage;
	}

	/**
	 * 设置 redis 数据占用内存占总内存分配的百分比{for dataset.percentage}
	 *
	 * @param datasetPercentage
	 * 		redis 数据占用内存占总内存分配的百分比
	 */
	public void setDatasetPercentage(Double datasetPercentage) {
		this.datasetPercentage = datasetPercentage;
	}

	/**
	 * 返回常驻内存开销大小{for rss-overhead.bytes}
	 *
	 * @return 常驻内存开销大小
	 */
	public Long getRssOverhead() {
		return rssOverhead;
	}

	/**
	 * 设置常驻内存开销大小{for rss-overhead.bytes}
	 *
	 * @param rssOverhead
	 * 		常驻内存开销大小
	 */
	public void setRssOverhead(Long rssOverhead) {
		this.rssOverhead = rssOverhead;
	}

	/**
	 * 返回常驻内存开销比例{for rss-overhead.ratio}
	 *
	 * @return 常驻内存开销比例{for rss-overhead.ratio}
	 */
	public Double getRssOverheadRatio() {
		return rssOverheadRatio;
	}

	/**
	 * 设置常驻内存开销比例{for rss-overhead.ratio}
	 *
	 * @param rssOverheadRatio
	 * 		常驻内存开销比例
	 */
	public void setRssOverheadRatio(Double rssOverheadRatio) {
		this.rssOverheadRatio = rssOverheadRatio;
	}

	/**
	 * 返回当前内存使用量与峰值时的占比{for peak.percentage}
	 *
	 * @return 当前内存使用量与峰值时的占比
	 */
	public Double getPeakPercentage() {
		return peakPercentage;
	}

	/**
	 * 设置当前内存使用量与峰值时的占比{for peak.percentage}
	 *
	 * @param peakPercentage
	 * 		当前内存使用量与峰值时的占比
	 */
	public void setPeakPercentage(Double peakPercentage) {
		this.peakPercentage = peakPercentage;
	}

	/**
	 * 返回 Aof 持久化使用的缓存和 Aof Rewrite 时产生的缓存之和{for aof.buffer}
	 *
	 * @return Aof 持久化使用的缓存和 Aof Rewrite 时产生的缓存之和，若关闭了 appendonly 则为 0
	 */
	public Long getAofBuffer() {
		return aofBuffer;
	}

	/**
	 * 设置 Aof 持久化使用的缓存和 Aof Rewrite 时产生的缓存之和{for aof.buffer}
	 *
	 * @param aofBuffer
	 * 		Aof 持久化使用的缓存和 Aof Rewrite 时产生的缓存之和
	 */
	public void setAofBuffer(Long aofBuffer) {
		this.aofBuffer = aofBuffer;
	}

	/**
	 * 返回平均每一个 key 的内存大小{for keys.bytes-per-key}
	 *
	 * @return 平均每一个 key 的内存大小
	 */
	public Long getKeysBytesPerKey() {
		return keysBytesPerKey;
	}

	/**
	 * 设置平均每一个 key 的内存大小{for keys.bytes-per-key}
	 *
	 * @param keysBytesPerKey
	 * 		平均每一个 key 的内存大小
	 */
	public void setKeysBytesPerKey(Long keysBytesPerKey) {
		this.keysBytesPerKey = keysBytesPerKey;
	}

	/**
	 * 返回分配器分配的内存{for allocator.allocated}
	 *
	 * @return 分配器分配的内存
	 */
	public Long getAllocatorAllocated() {
		return allocatorAllocated;
	}

	/**
	 * 设置分配器分配的内存{for allocator.allocated}
	 *
	 * @param allocatorAllocated
	 * 		分配器分配的内存
	 */
	public void setAllocatorAllocated(Long allocatorAllocated) {
		this.allocatorAllocated = allocatorAllocated;
	}

	/**
	 * 返回 used_memory_rss 和 used_memory 的差值 {for allocator-fragmentation.bytes}
	 *
	 * @return used_memory_rss 和 used_memory 的差值
	 */
	public Long getAllocatorFragmentation() {
		return allocatorFragmentation;
	}

	/**
	 * 设置 used_memory_rss 和 used_memory 的差值 {for allocator-fragmentation.bytes}
	 *
	 * @param allocatorFragmentation
	 * 		used_memory_rss 和 used_memory 的差值
	 */
	public void setAllocatorFragmentation(Long allocatorFragmentation) {
		this.allocatorFragmentation = allocatorFragmentation;
	}

	/**
	 * 返回 used_memory_rss Redis从系统申请的内存和 used_memory Redis 给数据分配的内存的比率；不仅包括碎片，还包括其他进程及代码、共享库、堆栈的内存开销等等{for allocator
	 * -fragmentation.ratio}
	 *
	 * @return used_memory_rss Redis从系统申请的内存和 used_memory Redis 给数据分配的内存的比率
	 */
	public Double getAllocatorFragmentationRatio() {
		return allocatorFragmentationRatio;
	}

	/**
	 * 设置 used_memory_rss Redis从系统申请的内存和 used_memory Redis 给数据分配的内存的比率；不仅包括碎片，还包括其他进程及代码、共享库、堆栈的内存开销等等{for allocator-fragmentation.ratio}
	 *
	 * @param allocatorFragmentationRatio
	 * 		used_memory_rss Redis从系统申请的内存和 used_memory Redis 给数据分配的内存的比率
	 */
	public void setAllocatorFragmentationRatio(Double allocatorFragmentationRatio) {
		this.allocatorFragmentationRatio = allocatorFragmentationRatio;
	}

	/**
	 * 返回分配器活动页中的字节量，包含外部碎片{for allocator.active}
	 *
	 * @return 分配器活动页中的字节量，包含外部碎片
	 */
	public Long getAllocatorActive() {
		return allocatorActive;
	}

	/**
	 * 设置分配器活动页中的字节量，包含外部碎片{for allocator.active}
	 *
	 * @param allocatorActive
	 * 		分配器活动页中的字节量
	 */
	public void setAllocatorActive(Long allocatorActive) {
		this.allocatorActive = allocatorActive;
	}

	/**
	 * 返回分配器的常驻内存大小{for allocator-rss.bytes}
	 *
	 * @return 分配器的常驻内存大小
	 */
	public Long getAllocatorRss() {
		return allocatorRss;
	}

	/**
	 * 设置分配器的常驻内存大小{for allocator-rss.bytes}
	 *
	 * @param allocatorRss
	 * 		分配器的常驻内存大小
	 */
	public void setAllocatorRss(Long allocatorRss) {
		this.allocatorRss = allocatorRss;
	}

	/**
	 * 返回分配器常驻内存比例{for allocator-rss.ratio}
	 *
	 * @return 分配器常驻内存比例
	 */
	public Double getAllocatorRssRatio() {
		return allocatorRssRatio;
	}

	/**
	 * 设置分配器常驻内存比例{for allocator-rss.ratio}
	 *
	 * @param allocatorRssRatio
	 * 		分配器常驻内存比例
	 */
	public void setAllocatorRssRatio(Double allocatorRssRatio) {
		this.allocatorRssRatio = allocatorRssRatio;
	}

	/**
	 * 返回分配器包含的总字节量{for allocator.resident}
	 *
	 * @return 分配器包含的总字节量
	 */
	public Long getAllocatorResident() {
		return allocatorResident;
	}

	/**
	 * 设置分配器包含的总字节量{for allocator.resident}
	 *
	 * @param allocatorResident
	 * 		分配器包含的总字节量
	 */
	public void setAllocatorResident(Long allocatorResident) {
		this.allocatorResident = allocatorResident;
	}

	/**
	 * 返回所有常规客户端消耗内存节字数{for clients.normal}
	 *
	 * @return 所有常规客户端消耗内存节字数
	 */
	public Long getClientsNormal() {
		return clientsNormal;
	}

	/**
	 * 设置所有常规客户端消耗内存节字数{for clients.normal}
	 *
	 * @param clientsNormal
	 * 		所有常规客户端消耗内存节字数
	 */
	public void setClientsNormal(Long clientsNormal) {
		this.clientsNormal = clientsNormal;
	}

	/**
	 * 返回所有 slave clients 消耗的内存字节数{for clients.slaves}
	 *
	 * @return 所有 slave clients 消耗的内存字节数
	 */
	public Long getClientsSlaves() {
		return clientsSlaves;
	}

	/**
	 * 设置所有 slave clients 消耗的内存字节数{for clients.slaves}
	 *
	 * @param clientsSlaves
	 * 		所有 slave clients 消耗的内存字节数
	 */
	public void setClientsSlaves(Long clientsSlaves) {
		this.clientsSlaves = clientsSlaves;
	}

	/**
	 * 返回内存碎片{for fragmentation.bytes}
	 *
	 * @return 内存碎片
	 */
	public Long getFragmentation() {
		return fragmentation;
	}

	/**
	 * 设置内存碎片{for fragmentation.bytes}
	 *
	 * @param fragmentation
	 * 		内存碎片
	 */
	public void setFragmentation(Long fragmentation) {
		this.fragmentation = fragmentation;
	}

	/**
	 * 返回内存碎片率{for fragmentation}
	 *
	 * @return 内存碎片率
	 */
	public Double getFragmentationRatio() {
		return fragmentationRatio;
	}

	/**
	 * 设置内存碎片率{for fragmentation}
	 *
	 * @param fragmentationRatio
	 * 		内存碎片率
	 */
	public void setFragmentationRatio(Double fragmentationRatio) {
		this.fragmentationRatio = fragmentationRatio;
	}

	/**
	 * 返回 Lua 脚本缓存开销内存节字数{for lua.caches}
	 *
	 * @return Lua 脚本缓存开销内存节字数
	 */
	public Long getLuaCaches() {
		return luaCaches;
	}

	/**
	 * 设置 Lua 脚本缓存开销内存节字数{for lua.caches}
	 *
	 * @param luaCaches
	 * 		Lua 脚本缓存开销内存节字数
	 */
	public void setLuaCaches(Long luaCaches) {
		this.luaCaches = luaCaches;
	}

	/**
	 * 返回 redis 启动至今，最多使用过多少内存{for peak.allocated}
	 *
	 * @return redis 启动至今，最多使用过多少内存
	 */
	public Long getPeakAllocated() {
		return peakAllocated;
	}

	/**
	 * 设置 redis 启动至今，最多使用过多少内存{for peak.allocated}
	 *
	 * @param peakAllocated
	 * 		redis 启动至今，最多使用过多少内存
	 */
	public void setPeakAllocated(Long peakAllocated) {
		this.peakAllocated = peakAllocated;
	}

	/**
	 * 返回当前使用的内存总量{for total.allocated}
	 *
	 * @return 当前使用的内存总量
	 */
	public Long getTotalAllocated() {
		return totalAllocated;
	}

	/**
	 * 设置当前使用的内存总量{for total.allocated}
	 *
	 * @param totalAllocated
	 * 		当前使用的内存总量
	 */
	public void setTotalAllocated(Long totalAllocated) {
		this.totalAllocated = totalAllocated;
	}

	/**
	 * 返回主从复制 backlog 使用的内存{for replication.backlog}
	 *
	 * @return 主从复制 backlog 使用的内存
	 */
	public Long getReplicationBacklog() {
		return replicationBacklog;
	}

	/**
	 * 设置主从复制 backlog 使用的内存{for replication.backlog}
	 *
	 * @param replicationBacklog
	 * 		主从复制 backlog 使用的内存
	 */
	public void setReplicationBacklog(Long replicationBacklog) {
		this.replicationBacklog = replicationBacklog;
	}

	/**
	 * 返回 redis 启动完成使用的内存字节数{for startup.allocated}
	 *
	 * @return redis 启动完成使用的内存字节数
	 */
	public Long getStartupAllocated() {
		return startupAllocated;
	}

	/**
	 * 设置 redis 启动完成使用的内存字节数{for startup.allocated}
	 *
	 * @param startupAllocated
	 * 		redis 启动完成使用的内存字节数
	 */
	public void setStartupAllocated(Long startupAllocated) {
		this.startupAllocated = startupAllocated;
	}

	/**
	 * 返回 redis 额外的总开销内存字节数；即分配器分配的总内存 total.allocated，减去数据实际存储使用内存{for overhead.total}
	 *
	 * @return redis 额外的总开销内存字节数
	 */
	public Long getOverheadTotal() {
		return overheadTotal;
	}

	/**
	 * 设置 redis 额外的总开销内存字节数；即分配器分配的总内存 total.allocated，减去数据实际存储使用内存{for overhead.total}
	 *
	 * @param overheadTotal
	 * 		redis 额外的总开销内存字节数；即分配器分配的总内存 total.allocated，减去数据实际存储使用内存
	 */
	public void setOverheadTotal(Long overheadTotal) {
		this.overheadTotal = overheadTotal;
	}

	/**
	 * 返回 key 总量{for keys.count}
	 *
	 * @return key 总量
	 */
	public Long getKeysCount() {
		return keysCount;
	}

	/**
	 * 设置 key 总量{for keys.count}
	 *
	 * @param keysCount
	 * 		key 总量
	 */
	public void setKeysCount(Long keysCount) {
		this.keysCount = keysCount;
	}

	/**
	 * 返回每个 DB 统计{for db.x}
	 *
	 * @return 每个 DB 统计
	 */
	public Map<Integer, Db> getDbs() {
		return dbs;
	}

	/**
	 * 设置每个 DB 统计{for db.x}
	 *
	 * @param dbs
	 * 		每个 DB 统计
	 */
	public void setDbs(Map<Integer, Db> dbs) {
		this.dbs = dbs;
	}

	public final static class Db {

		private long overheadHashTableMain;

		private long overheadHashTableExpires;

		public long getOverheadHashTableMain() {
			return overheadHashTableMain;
		}

		public void setOverheadHashTableMain(long overheadHashTableMain) {
			this.overheadHashTableMain = overheadHashTableMain;
		}

		public long getOverheadHashTableExpires() {
			return overheadHashTableExpires;
		}

		public void setOverheadHashTableExpires(long overheadHashTableExpires) {
			this.overheadHashTableExpires = overheadHashTableExpires;
		}

	}

}
