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
package com.buession.redis.core;

import java.io.Serializable;

/**
 * Redis 服务器的内存信息
 *
 * @author Yong.Teng
 */
public class Memory implements Serializable {

	private final static long serialVersionUID = 1058962019266560745L;

	/**
	 * 由 Redis 分配器分配的内存总量，包括使用的虚拟内存(单位：byte)
	 */
	private long usedMemory;

	/**
	 * 由 Redis 分配器分配的内存总量，包括使用的虚拟内存的可读信息
	 */
	private String usedMemoryHuman;

	/**
	 * 从操作系统角度返回 Redis 使用内存数(单位：byte)
	 */
	private long usedMemoryRss;

	/**
	 * usedMemoryRss 可读信息
	 */
	private String usedMemoryRssHuman;

	/**
	 * Redis 的内存消耗峰值，即：Redis 内存使用的最大值(单位：byte)
	 */
	private long usedMemoryPeak;

	/**
	 * Redis 的内存消耗峰值，即：Redis 内存使用的最大值的可读信息
	 */
	private String usedMemoryPeakHuman;

	/**
	 * used_memory_peak 在 used_memory 中所占的百分比
	 */
	private double usedMemoryPeakPerc;

	/**
	 * Redis 维护整个内存数据集可用内部机制所需要的内存开销(单位：byte)
	 */
	private long usedMemoryOverhead;

	/**
	 * Redis 消耗的初始内存值(单位：byte)
	 */
	private long usedMemoryStartup;

	/**
	 * Redis 中数据集所占内存数据大小，即：used_memory - used_memory_overhead(单位：byte)
	 */
	private long usedMemoryDataset;

	/**
	 * used_memory_dataset 在净内存（used_memory - used_memory_startup）使用量中所占的百分比
	 */
	private double usedMemoryDatasetPerc;

	/**
	 * Redis 中分配器分配内存数(单位：byte)
	 */
	private long allocatorAllocated;

	/**
	 * Redis 中分配器活跃内存数(单位：byte)
	 */
	private long allocatorActive;

	/**
	 * Redis 中分配器常驻内存数(单位：byte)
	 */
	private long allocatorResident;

	/**
	 * 系统内存总数(单位：byte)
	 */
	private long totalSystemMemory;

	/**
	 * 系统内存总数的可读信息
	 */
	private String totalSystemMemoryHuman;

	/**
	 * lua 引擎使用内存量(单位：byte)
	 */
	private long usedMemoryLua;

	/**
	 * lua 引擎使用内存量的可读信息
	 */
	private String usedMemoryLuaHuman;

	private long usedMemoryScripts;

	private String usedMemoryScriptsHuman;

	private int numberOfCachedScripts;

	/**
	 * Redis 中可分配的最大内存数
	 */
	private long maxMemory;

	/**
	 * Redis 中可分配的最大内存数的可读信息
	 */
	private String maxMemoryHuman;

	/**
	 * 达到 Redis 最大内存数 maxmemory 后的处理策略
	 */
	private MaxMemoryPolicy maxMemoryPolicy;

	/**
	 * 分配器碎片率
	 */
	private double allocatorFragRatio;

	/**
	 * 分配器内存碎片大小(单位：byte)
	 */
	private long allocatorFragBytes;

	/**
	 * 分配器常驻内存比例
	 */
	private double allocatorRssRatio;

	/**
	 * 分配器常驻内存大小(单位：byte)
	 */
	private long allocatorRssBytes;

	/**
	 * 常驻内存开销比例
	 */
	private double rssOverheadRatio;

	/**
	 * 常驻内存开销大小(单位：byte)
	 */
	private long rssOverheadBytes;

	/**
	 * Redis 中内存碎片率
	 */
	private double memFragmentationRatio;

	/**
	 * Redis 中内存碎片大小
	 */
	private long memFragmentationBytes;

	/**
	 * 被驱逐的内存大小
	 */
	private long memNotCountedForEvict;

	/**
	 * 从服务其中 backlog 内存大小
	 */
	private long memReplicationBacklog;

	private int memClientsSlaves;

	private int memClientsNormal;

	/**
	 * Redis 使用 aof 持久化方式中 aof_buffer 缓冲区大小(单位：byte)
	 */
	private long memAofBuffer;

	/**
	 * Redis 在编译时指定的，Redis 所使用的内存分配器及其版本
	 */
	private String memAllocator;

	/**
	 * 内存整理是否处于活动状态
	 */
	private boolean activeDefragRunning;

	/**
	 * 等待释放对象数
	 */
	private int lazyfreePendingObjects;

	/**
	 * 返回由 Redis 分配器分配的内存总量，包括使用的虚拟内存(单位：byte)
	 *
	 * @return 由 Redis 分配器分配的内存总量，包括使用的虚拟内存
	 */
	public long getUsedMemory(){
		return usedMemory;
	}

	/**
	 * 设置由 Redis 分配器分配的内存总量，包括使用的虚拟内存(单位：byte)
	 *
	 * @param usedMemory
	 * 		由 Redis 分配器分配的内存总量，包括使用的虚拟内存
	 */
	public void setUsedMemory(long usedMemory){
		this.usedMemory = usedMemory;
	}

	/**
	 * 由 Redis 分配器分配的内存总量，包括使用的虚拟内存的可读信息
	 *
	 * @return 由 Redis 分配器分配的内存总量，包括使用的虚拟内存的可读信息
	 */
	public String getUsedMemoryHuman(){
		return usedMemoryHuman;
	}

	/**
	 * 设置由 Redis 分配器分配的内存总量，包括使用的虚拟内存的可读信息
	 *
	 * @param usedMemoryHuman
	 * 		由 Redis 分配器分配的内存总量，包括使用的虚拟内存的可读信息
	 */
	public void setUsedMemoryHuman(String usedMemoryHuman){
		this.usedMemoryHuman = usedMemoryHuman;
	}

	/**
	 * 获取从操作系统角度返回 Redis 使用内存数，与 Linux 中 top，ps 命令返回数据一致
	 * 除了分配器分配的内存之外，used_memory_rss 还包括进程运行本身需要的内存、内存碎片等，但是不包括虚拟内存(单位：byte)
	 *
	 * @return Redis 使用内存数
	 */
	public long getUsedMemoryRss(){
		return usedMemoryRss;
	}

	/**
	 * 设置 Redis 使用内存数
	 *
	 * @param usedMemoryRss
	 * 		Redis 使用内存数
	 */
	public void setUsedMemoryRss(long usedMemoryRss){
		this.usedMemoryRss = usedMemoryRss;
	}

	/**
	 * 返回 Redis 使用内存数的可读信息
	 *
	 * @return Redis 使用内存数的可读信息
	 */
	public String getUsedMemoryRssHuman(){
		return usedMemoryRssHuman;
	}

	/**
	 * 设置 Redis 使用内存数的可读信息
	 *
	 * @param usedMemoryRssHuman
	 * 		Redis 使用内存数的可读信息
	 */
	public void setUsedMemoryRssHuman(String usedMemoryRssHuman){
		this.usedMemoryRssHuman = usedMemoryRssHuman;
	}

	/**
	 * 返回 Redis 的内存消耗峰值，即：Redis 内存使用的最大值(单位：byte)
	 *
	 * @return Redis 的内存消耗峰值，即：Redis 内存使用的最大值
	 */
	public long getUsedMemoryPeak(){
		return usedMemoryPeak;
	}

	/**
	 * 设置 Redis 的内存消耗峰值
	 *
	 * @param usedMemoryPeak
	 * 		Redis 的内存消耗峰值
	 */
	public void setUsedMemoryPeak(long usedMemoryPeak){
		this.usedMemoryPeak = usedMemoryPeak;
	}

	/**
	 * 返回 Redis 的内存消耗峰值，即：Redis 内存使用的最大值的可读信息
	 *
	 * @return Redis 的内存消耗峰值，即：Redis 内存使用的最大值的可读信息
	 */
	public String getUsedMemoryPeakHuman(){
		return usedMemoryPeakHuman;
	}

	/**
	 * 设置 Redis 的内存消耗峰值，即：Redis 内存使用的最大值的可读信息
	 *
	 * @param usedMemoryPeakHuman
	 * 		Redis 的内存消耗峰值，即：Redis 内存使用的最大值的可读信息
	 */
	public void setUsedMemoryPeakHuman(String usedMemoryPeakHuman){
		this.usedMemoryPeakHuman = usedMemoryPeakHuman;
	}

	/**
	 * 返回 used_memory_peak 在 used_memory 中所占的百分比
	 *
	 * @return used_memory_peak 在 used_memory 中所占的百分比
	 */
	public double getUsedMemoryPeakPerc(){
		return usedMemoryPeakPerc;
	}

	/**
	 * 设置 used_memory_peak 在 used_memory 中所占的百分比
	 *
	 * @param usedMemoryPeakPerc
	 * 		used_memory_peak 在 used_memory 中所占的百分比
	 */
	public void setUsedMemoryPeakPerc(double usedMemoryPeakPerc){
		this.usedMemoryPeakPerc = usedMemoryPeakPerc;
	}

	/**
	 * 返回 Redis 维护整个内存数据集可用内部机制所需要的内存开销。
	 * 包括维护内部数据结构，所有客户端输出缓冲区，AOF 重写缓冲区，查询缓冲区，AOF 写入缓冲区和主从复制的 backlog(单位：byte)
	 *
	 * @return Redis 维护整个内存数据集可用内部机制所需要的内存开销
	 */
	public long getUsedMemoryOverhead(){
		return usedMemoryOverhead;
	}

	/**
	 * 设置 Redis 维护整个内存数据集可用内部机制所需要的内存开销
	 *
	 * @param usedMemoryOverhead
	 * 		Redis 维护整个内存数据集可用内部机制所需要的内存开销
	 */
	public void setUsedMemoryOverhead(long usedMemoryOverhead){
		this.usedMemoryOverhead = usedMemoryOverhead;
	}

	/**
	 * 返回 Redis 消耗的初始内存值
	 *
	 * @return Redis 消耗的初始内存值
	 */
	public long getUsedMemoryStartup(){
		return usedMemoryStartup;
	}

	/**
	 * 设置 Redis 消耗的初始内存值
	 *
	 * @param usedMemoryStartup
	 * 		Redis 消耗的初始内存值
	 */
	public void setUsedMemoryStartup(long usedMemoryStartup){
		this.usedMemoryStartup = usedMemoryStartup;
	}

	/**
	 * 返回 Redis 中数据集所占内存数据大小，即：used_memory - used_memory_overhead(单位：byte)
	 *
	 * @return Redis 中数据集所占内存数据大小
	 */
	public long getUsedMemoryDataset(){
		return usedMemoryDataset;
	}

	/**
	 * 设置 Redis 中数据集所占内存数据大小，即：used_memory - used_memory_overhead
	 *
	 * @param usedMemoryDataset
	 * 		Redis 中数据集所占内存数据大小
	 */
	public void setUsedMemoryDataset(long usedMemoryDataset){
		this.usedMemoryDataset = usedMemoryDataset;
	}

	/**
	 * used_memory_dataset 在净内存（used_memory - used_memory_startup）使用量中所占的百分比，即：
	 * (used_memory_dataset/(used_memory—used_memory_startup)) * 100%
	 *
	 * @return used_memory_dataset 在净内存（used_memory - used_memory_startup）使用量中所占的百分比
	 */
	public double getUsedMemoryDatasetPerc(){
		return usedMemoryDatasetPerc;
	}

	/**
	 * 设置 used_memory_dataset 在净内存（used_memory - used_memory_startup）使用量中所占的百分比
	 *
	 * @param usedMemoryDatasetPerc
	 * 		used_memory_dataset 在净内存（used_memory - used_memory_startup）使用量中所占的百分比
	 */
	public void setUsedMemoryDatasetPerc(double usedMemoryDatasetPerc){
		this.usedMemoryDatasetPerc = usedMemoryDatasetPerc;
	}

	/**
	 * 返回 Redis 中分配器分配内存数(单位：byte)
	 *
	 * @return Redis 中分配器分配内存数
	 */
	public long getAllocatorAllocated(){
		return allocatorAllocated;
	}

	/**
	 * 设置 Redis 中分配器分配内存数
	 *
	 * @param allocatorAllocated
	 * 		Redis 中分配器分配内存数
	 */
	public void setAllocatorAllocated(long allocatorAllocated){
		this.allocatorAllocated = allocatorAllocated;
	}

	/**
	 * 返回 Redis 中分配器活跃内存数(单位：byte)
	 *
	 * @return Redis 中分配器活跃内存数
	 */
	public long getAllocatorActive(){
		return allocatorActive;
	}

	/**
	 * 设置 Redis 中分配器活跃内存数
	 *
	 * @param allocatorActive
	 * 		Redis 中分配器活跃内存数
	 */
	public void setAllocatorActive(long allocatorActive){
		this.allocatorActive = allocatorActive;
	}

	/**
	 * 返回 Redis 中分配器常驻内存数(单位：byte)
	 *
	 * @return Redis 中分配器常驻内存数
	 */
	public long getAllocatorResident(){
		return allocatorResident;
	}

	/**
	 * 设置 Redis 中分配器常驻内存数
	 *
	 * @param allocatorResident
	 * 		Redis 中分配器常驻内存数
	 */
	public void setAllocatorResident(long allocatorResident){
		this.allocatorResident = allocatorResident;
	}

	/**
	 * 返回系统内存总数
	 *
	 * @return 系统内存总数
	 */
	public long getTotalSystemMemory(){
		return totalSystemMemory;
	}

	/**
	 * 设置系统内存总数
	 *
	 * @param totalSystemMemory
	 * 		系统内存总数
	 */
	public void setTotalSystemMemory(long totalSystemMemory){
		this.totalSystemMemory = totalSystemMemory;
	}

	/**
	 * 返回系统内存总数的可读信息(单位：byte)
	 *
	 * @return 系统内存总数的可读信息
	 */
	public String getTotalSystemMemoryHuman(){
		return totalSystemMemoryHuman;
	}

	/**
	 * 设置系统内存总数的可读信息
	 *
	 * @param totalSystemMemoryHuman
	 * 		系统内存总数的可读信息
	 */
	public void setTotalSystemMemoryHuman(String totalSystemMemoryHuman){
		this.totalSystemMemoryHuman = totalSystemMemoryHuman;
	}

	/**
	 * 返回 lua 引擎使用内存量(单位：byte)
	 *
	 * @return lua 引擎使用内存量
	 */
	public long getUsedMemoryLua(){
		return usedMemoryLua;
	}

	/**
	 * 设置 lua 引擎使用内存量
	 *
	 * @param usedMemoryLua
	 * 		lua 引擎使用内存量
	 */
	public void setUsedMemoryLua(long usedMemoryLua){
		this.usedMemoryLua = usedMemoryLua;
	}

	/**
	 * 返回 lua 引擎使用内存量的可读信息
	 *
	 * @return lua 引擎使用内存量的可读信息
	 */
	public String getUsedMemoryLuaHuman(){
		return usedMemoryLuaHuman;
	}

	/**
	 * 设置 lua 引擎使用内存量的可读信息
	 *
	 * @param usedMemoryLuaHuman
	 * 		lua 引擎使用内存量的可读信息
	 */
	public void setUsedMemoryLuaHuman(String usedMemoryLuaHuman){
		this.usedMemoryLuaHuman = usedMemoryLuaHuman;
	}

	public long getUsedMemoryScripts(){
		return usedMemoryScripts;
	}

	public void setUsedMemoryScripts(long usedMemoryScripts){
		this.usedMemoryScripts = usedMemoryScripts;
	}

	public String getUsedMemoryScriptsHuman(){
		return usedMemoryScriptsHuman;
	}

	public void setUsedMemoryScriptsHuman(String usedMemoryScriptsHuman){
		this.usedMemoryScriptsHuman = usedMemoryScriptsHuman;
	}

	public int getNumberOfCachedScripts(){
		return numberOfCachedScripts;
	}

	public void setNumberOfCachedScripts(int numberOfCachedScripts){
		this.numberOfCachedScripts = numberOfCachedScripts;
	}

	/**
	 * 返回 Redis 中可分配的最大内存数，默认为0不限制
	 *
	 * @return Redis 中可分配的最大内存数
	 */
	public long getMaxMemory(){
		return maxMemory;
	}

	/**
	 * 设置 Redis 中可分配的最大内存数
	 *
	 * @param maxMemory
	 * 		Redis 中可分配的最大内存数
	 */
	public void setMaxMemory(long maxMemory){
		this.maxMemory = maxMemory;
	}

	/**
	 * 返回 Redis 中可分配的最大内存数的可读信息
	 *
	 * @return Redis 中可分配的最大内存数的可读信息
	 */
	public String getMaxMemoryHuman(){
		return maxMemoryHuman;
	}

	/**
	 * 设置 Redis 中可分配的最大内存数的可读信息
	 *
	 * @param maxMemoryHuman
	 * 		Redis 中可分配的最大内存数的可读信息
	 */
	public void setMaxMemoryHuman(String maxMemoryHuman){
		this.maxMemoryHuman = maxMemoryHuman;
	}

	/**
	 * 返回达到 Redis 最大内存数 maxmemory 后的处理策略
	 *
	 * @return 达到 Redis 最大内存数 maxmemory 后的处理策略
	 */
	public MaxMemoryPolicy getMaxMemoryPolicy(){
		return maxMemoryPolicy;
	}

	/**
	 * 设置达到 Redis 最大内存数 maxmemory 后的处理策略
	 *
	 * @param maxMemoryPolicy
	 * 		达到 Redis 最大内存数 maxmemory 后的处理策略
	 */
	public void setMaxMemoryPolicy(MaxMemoryPolicy maxMemoryPolicy){
		this.maxMemoryPolicy = maxMemoryPolicy;
	}

	/**
	 * 返回分配器碎片率
	 *
	 * @return 分配器碎片率
	 */
	public double getAllocatorFragRatio(){
		return allocatorFragRatio;
	}

	/**
	 * 设置分配器碎片率
	 *
	 * @param allocatorFragRatio
	 * 		分配器碎片率
	 */
	public void setAllocatorFragRatio(double allocatorFragRatio){
		this.allocatorFragRatio = allocatorFragRatio;
	}

	/**
	 * 返回分配器内存碎片大小(单位：byte)
	 *
	 * @return 分配器内存碎片大小
	 */
	public long getAllocatorFragBytes(){
		return allocatorFragBytes;
	}

	/**
	 * 设置分配器内存碎片大小
	 *
	 * @param allocatorFragBytes
	 * 		分配器内存碎片大小
	 */
	public void setAllocatorFragBytes(long allocatorFragBytes){
		this.allocatorFragBytes = allocatorFragBytes;
	}

	/**
	 * 返回分配器常驻内存比例
	 *
	 * @return 分配器常驻内存比例
	 */
	public double getAllocatorRssRatio(){
		return allocatorRssRatio;
	}

	/**
	 * 设置分配器常驻内存比例
	 *
	 * @param allocatorRssRatio
	 * 		分配器常驻内存比例
	 */
	public void setAllocatorRssRatio(double allocatorRssRatio){
		this.allocatorRssRatio = allocatorRssRatio;
	}

	/**
	 * 返回分配器常驻内存大小(单位：byte)
	 *
	 * @return 分配器常驻内存大小
	 */
	public long getAllocatorRssBytes(){
		return allocatorRssBytes;
	}

	/**
	 * 设置分配器常驻内存大小
	 *
	 * @param allocatorRssBytes
	 * 		分配器常驻内存大小
	 */
	public void setAllocatorRssBytes(long allocatorRssBytes){
		this.allocatorRssBytes = allocatorRssBytes;
	}

	/**
	 * 返回常驻内存开销比例
	 *
	 * @return 常驻内存开销比例
	 */
	public double getRssOverheadRatio(){
		return rssOverheadRatio;
	}

	/**
	 * 设置常驻内存开销比例
	 *
	 * @param rssOverheadRatio
	 * 		常驻内存开销比例
	 */
	public void setRssOverheadRatio(double rssOverheadRatio){
		this.rssOverheadRatio = rssOverheadRatio;
	}

	/**
	 * 返回常驻内存开销大小(单位：byte)
	 *
	 * @return 常驻内存开销大小
	 */
	public long getRssOverheadBytes(){
		return rssOverheadBytes;
	}

	/**
	 * 设置常驻内存开销大小
	 *
	 * @param rssOverheadBytes
	 * 		常驻内存开销大小
	 */
	public void setRssOverheadBytes(long rssOverheadBytes){
		this.rssOverheadBytes = rssOverheadBytes;
	}

	/**
	 * 返回 Redis 中内存碎片率，此值为 (used_memory_rss/used_memory)*100%
	 *
	 * @return Redis 中内存碎片率
	 */
	public double getMemFragmentationRatio(){
		return memFragmentationRatio;
	}

	/**
	 * 设置 Redis 中内存碎片率
	 *
	 * @param memFragmentationRatio
	 * 		Redis 中内存碎片率
	 */
	public void setMemFragmentationRatio(double memFragmentationRatio){
		this.memFragmentationRatio = memFragmentationRatio;
	}

	/**
	 * Redis 中内存碎片大小(单位：byte)
	 *
	 * @return Redis 中内存碎片大小
	 */
	public long getMemFragmentationBytes(){
		return memFragmentationBytes;
	}

	/**
	 * 设置  Redis 中内存碎片大小
	 *
	 * @param memFragmentationBytes
	 * 		Redis 中内存碎片大小
	 */
	public void setMemFragmentationBytes(long memFragmentationBytes){
		this.memFragmentationBytes = memFragmentationBytes;
	}

	/**
	 * 返回被驱逐的内存大小
	 *
	 * @return 被驱逐的内存大小
	 */
	public long getMemNotCountedForEvict(){
		return memNotCountedForEvict;
	}

	/**
	 * 设置被驱逐的内存大小
	 *
	 * @param memNotCountedForEvict
	 * 		被驱逐的内存大小
	 */
	public void setMemNotCountedForEvict(long memNotCountedForEvict){
		this.memNotCountedForEvict = memNotCountedForEvict;
	}

	/**
	 * 返回从服务其中 backlog 内存大小
	 *
	 * @return 从服务其中 backlog 内存大小
	 */
	public long getMemReplicationBacklog(){
		return memReplicationBacklog;
	}

	/**
	 * 设置从服务其中 backlog 内存大小
	 *
	 * @param memReplicationBacklog
	 * 		从服务其中 backlog 内存大小
	 */
	public void setMemReplicationBacklog(long memReplicationBacklog){
		this.memReplicationBacklog = memReplicationBacklog;
	}

	public int getMemClientsSlaves(){
		return memClientsSlaves;
	}

	public void setMemClientsSlaves(int memClientsSlaves){
		this.memClientsSlaves = memClientsSlaves;
	}

	public int getMemClientsNormal(){
		return memClientsNormal;
	}

	public void setMemClientsNormal(int memClientsNormal){
		this.memClientsNormal = memClientsNormal;
	}

	/**
	 * 返回 Redis 使用 aof 持久化方式中 aof_buffer 缓冲区大小(单位：byte)
	 *
	 * @return Redis 使用 ao f持久化方式中 aof_buffer 缓冲区大小
	 */
	public long getMemAofBuffer(){
		return memAofBuffer;
	}

	/**
	 * 设置 Redis 使用 aof 持久化方式中 aof_buffer 缓冲区大小
	 *
	 * @param memAofBuffer
	 * 		Redis 使用 ao f持久化方式中 aof_buffer 缓冲区大小
	 */
	public void setMemAofBuffer(long memAofBuffer){
		this.memAofBuffer = memAofBuffer;
	}

	/**
	 * 返回 Redis 在编译时指定的，Redis 所使用的内存分配器及其版本
	 *
	 * @return Redis 在编译时指定的，Redis 所使用的内存分配器及其版本
	 */
	public String getMemAllocator(){
		return memAllocator;
	}

	/**
	 * 设置 Redis 在编译时指定的，Redis 所使用的内存分配器及其版本
	 *
	 * @param memAllocator
	 * 		Redis 在编译时指定的，Redis 所使用的内存分配器及其版本
	 */
	public void setMemAllocator(String memAllocator){
		this.memAllocator = memAllocator;
	}

	/**
	 * 检测内存整理是否处于活动状态
	 *
	 * @return 内存整理是否处于活动状态
	 */
	public boolean isActiveDefragRunning(){
		return getActiveDefragRunning();
	}

	/**
	 * 检测内存整理是否处于活动状态
	 *
	 * @return 内存整理是否处于活动状态
	 */
	public boolean getActiveDefragRunning(){
		return activeDefragRunning;
	}

	/**
	 * 设置内存整理是否处于活动状态
	 *
	 * @param activeDefragRunning
	 * 		内存整理是否处于活动状态
	 */
	public void setActiveDefragRunning(boolean activeDefragRunning){
		this.activeDefragRunning = activeDefragRunning;
	}

	/**
	 * 返回等待释放对象数，此值只会在使用 ASYNC 选项并调用 UNLINK 或 FLUSHDB 和 FLUSHALL 时存在
	 *
	 * @return 等待释放对象数
	 */
	public int getLazyfreePendingObjects(){
		return lazyfreePendingObjects;
	}

	/**
	 * 设置等待释放对象数
	 *
	 * @param lazyfreePendingObjects
	 * 		等待释放对象数
	 */
	public void setLazyfreePendingObjects(int lazyfreePendingObjects){
		this.lazyfreePendingObjects = lazyfreePendingObjects;
	}

	@Override
	public String toString(){
		return "usedMemory=" + usedMemory + ", usedMemoryHuman='" + usedMemoryHuman + '\'' + ", " + "usedMemoryRss=" + usedMemoryRss + ", usedMemoryRssHuman='" + usedMemoryRssHuman + '\'' + ", " + "usedMemoryPeak=" + usedMemoryPeak + ", usedMemoryPeakHuman='" + usedMemoryPeakHuman + '\'' + ", " + "usedMemoryPeakPerc=" + usedMemoryPeakPerc + ", usedMemoryOverhead=" + usedMemoryOverhead + ", " + "usedMemoryStartup=" + usedMemoryStartup + ", usedMemoryDataset=" + usedMemoryDataset + ", " + "usedMemoryDatasetPerc=" + usedMemoryDatasetPerc + ", allocatorAllocated=" + allocatorAllocated + ", " + "allocatorActive=" + allocatorActive + ", allocatorResident=" + allocatorResident + ", " + "totalSystemMemory=" + totalSystemMemory + ", totalSystemMemoryHuman='" + totalSystemMemoryHuman + '\'' + ", usedMemoryLua=" + usedMemoryLua + ", usedMemoryLuaHuman='" + usedMemoryLuaHuman + '\'' + "," + " " + "usedMemoryScripts=" + usedMemoryScripts + ", usedMemoryScriptsHuman='" + usedMemoryScriptsHuman + '\'' + ", numberOfCachedScripts=" + numberOfCachedScripts + ", maxMemory=" + maxMemory + ", " + "maxMemoryHuman='" + maxMemoryHuman + '\'' + ", maxMemoryPolicy=" + maxMemoryPolicy + ", " + "allocatorFragRatio=" + allocatorFragRatio + ", allocatorFragBytes=" + allocatorFragBytes + ", " + "allocatorRssRatio=" + allocatorRssRatio + ", allocatorRssBytes=" + allocatorRssBytes + ", " + "rssOverheadRatio=" + rssOverheadRatio + ", rssOverheadBytes=" + rssOverheadBytes + ", " + "memFragmentationRatio=" + memFragmentationRatio + ", memFragmentationBytes=" + memFragmentationBytes + ", memNotCountedForEvict=" + memNotCountedForEvict + ", memReplicationBacklog=" + memReplicationBacklog + ", memClientsSlaves=" + memClientsSlaves + ", memClientsNormal=" + memClientsNormal + ", memAofBuffer=" + memAofBuffer + ", memAllocator='" + memAllocator + '\'' + ", " + "activeDefragRunning=" + activeDefragRunning + ", lazyfreePendingObjects=" + lazyfreePendingObjects;
	}

}
