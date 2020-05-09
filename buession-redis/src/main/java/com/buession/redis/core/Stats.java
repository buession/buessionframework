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
 * 一般统计信息
 *
 * @author Yong.Teng
 */
public class Stats implements Serializable {

	private final static long serialVersionUID = 668423701291623945L;

	/**
	 * 服务器已接受的连接请求数量
	 */
	private int totalConnectionsReceived;

	/**
	 * 服务器已执行的命令数量
	 */
	private int totalCommandsProcessed;

	/**
	 * 服务器每秒钟执行的命令数量
	 */
	private int instantaneousOpsPerSec;

	/**
	 * Redis 服务接受输入总数据量(单位: byte)
	 */
	private int totalNetInputBytes;

	/**
	 * Redis 服务输出总数据量(单位: byte)
	 */
	private int totalNetOutputBytes;

	/**
	 * 输入带宽，Redis 服务每秒读字节数
	 */
	private double instantaneousInputKbps;

	/**
	 * 输出带宽，Redis 服务每秒写字节数
	 */
	private double instantaneousOutputKbps;

	/**
	 * 因为最大客户端数量限制而被拒绝的连接请求数量
	 */
	private int rejectedConnections;

	/**
	 * 主从之间数据同步完全成功次数
	 */
	private int syncFull;

	/**
	 * 主从之间数据同步部分成功次数
	 */
	private int syncPartialOk;

	/**
	 * 主从之间数据同步部分失败次数
	 */
	private int syncPartialErr;

	/**
	 * 因为过期而被自动删除的数据库键数量
	 */
	private int expiredKeys;

	/**
	 * 过期 key 占总 key 比率
	 */
	private double expiredStalePerc;

	/**
	 * 过期计数
	 */
	private int expiredTimeCapReachedCount;

	/**
	 * 因为最大内存容量限制而被驱逐（evict）的键数量
	 */
	private int evictedKeys;

	/**
	 * 查找数据库键成功的次数
	 */
	private int keyspaceHits;

	/**
	 * 查找数据库键失败的次数
	 */
	private int keyspaceMisses;

	/**
	 * 目前被订阅的频道数量
	 */
	private int pubsubChannels;

	/**
	 * 目前被订阅的模式数量
	 */
	private int pubsubPatterns;

	/**
	 * 最近一次 fork() 操作耗费的毫秒数
	 */
	private int latestForkUsec;

	/**
	 * 0
	 */
	private int migrateCachedSockets;

	/**
	 * 从服务器中到期 key 数量
	 */
	private int slaveExpiresTrackedKeys;

	/**
	 * 主动垃圾碎片整理命中次数
	 */
	private int activeDefragHits;

	/**
	 * 主动垃圾碎片整理未命中
	 */
	private int activeDefragMisses;

	/**
	 * 主动垃圾碎片整理 key 命中次数
	 */
	private int activeDefragKeyHits;

	/**
	 * 主动垃圾碎片整理 key 未命中次数
	 */
	private int activeDefragKeyMisses;

	/**
	 * 获取服务器已接受的连接请求数量
	 *
	 * @return 服务器已接受的连接请求数量
	 */
	public int getTotalConnectionsReceived(){
		return totalConnectionsReceived;
	}

	/**
	 * 设置服务器已接受的连接请求数量
	 *
	 * @param totalConnectionsReceived
	 * 		服务器已接受的连接请求数量
	 */
	public void setTotalConnectionsReceived(int totalConnectionsReceived){
		this.totalConnectionsReceived = totalConnectionsReceived;
	}

	/**
	 * 获取服务器已执行的命令数量
	 *
	 * @return 服务器已执行的命令数量
	 */
	public int getTotalCommandsProcessed(){
		return totalCommandsProcessed;
	}

	/**
	 * 设置服务器已执行的命令数量
	 *
	 * @param totalCommandsProcessed
	 * 		服务器已执行的命令数量
	 */
	public void setTotalCommandsProcessed(int totalCommandsProcessed){
		this.totalCommandsProcessed = totalCommandsProcessed;
	}

	/**
	 * 获取服务器每秒钟执行的命令数量
	 *
	 * @return 服务器每秒钟执行的命令数量
	 */
	public int getInstantaneousOpsPerSec(){
		return instantaneousOpsPerSec;
	}

	/**
	 * 设置服务器每秒钟执行的命令数量
	 *
	 * @param instantaneousOpsPerSec
	 * 		服务器每秒钟执行的命令数量
	 */
	public void setInstantaneousOpsPerSec(int instantaneousOpsPerSec){
		this.instantaneousOpsPerSec = instantaneousOpsPerSec;
	}

	/**
	 * 返回 Redis 服务接受输入总数据量(单位: byte)
	 *
	 * @return Redis 服务接受输入总数据量
	 */
	public int getTotalNetInputBytes(){
		return totalNetInputBytes;
	}

	/**
	 * 设置 Redis 服务接受输入总数据量
	 *
	 * @param totalNetInputBytes
	 * 		Redis 服务接受输入总数据量
	 */
	public void setTotalNetInputBytes(int totalNetInputBytes){
		this.totalNetInputBytes = totalNetInputBytes;
	}

	/**
	 * 返回 Redis 服务输出总数据量(单位: byte)
	 *
	 * @return Redis 服务输出总数据量
	 */
	public int getTotalNetOutputBytes(){
		return totalNetOutputBytes;
	}

	/**
	 * 设置 Redis 服务输出总数据量
	 *
	 * @param totalNetOutputBytes
	 * 		Redis 服务输出总数据量
	 */
	public void setTotalNetOutputBytes(int totalNetOutputBytes){
		this.totalNetOutputBytes = totalNetOutputBytes;
	}

	/**
	 * 返回输入带宽，Redis 服务每秒读字节数
	 *
	 * @return 输入带宽，Redis 服务每秒读字节数
	 */
	public double getInstantaneousInputKbps(){
		return instantaneousInputKbps;
	}

	/**
	 * 设置输入带宽，Redis 服务每秒读字节数
	 *
	 * @param instantaneousInputKbps
	 * 		输入带宽，Redis 服务每秒读字节数
	 */
	public void setInstantaneousInputKbps(double instantaneousInputKbps){
		this.instantaneousInputKbps = instantaneousInputKbps;
	}

	/**
	 * 返回输出带宽，Redis 服务每秒写字节数
	 *
	 * @return 输出带宽，Redis 服务每秒写字节数
	 */
	public double getInstantaneousOutputKbps(){
		return instantaneousOutputKbps;
	}

	/**
	 * 设置输出带宽，Redis 服务每秒写字节数
	 *
	 * @param instantaneousOutputKbps
	 * 		输出带宽，Redis 服务每秒写字节数
	 */
	public void setInstantaneousOutputKbps(double instantaneousOutputKbps){
		this.instantaneousOutputKbps = instantaneousOutputKbps;
	}

	/**
	 * 获取因为最大客户端数量限制而被拒绝的连接请求数量
	 *
	 * @return 因为最大客户端数量限制而被拒绝的连接请求数量
	 */
	public int getRejectedConnections(){
		return rejectedConnections;
	}

	/**
	 * 设置因为最大客户端数量限制而被拒绝的连接请求数量
	 *
	 * @param rejectedConnections
	 * 		因为最大客户端数量限制而被拒绝的连接请求数量
	 */
	public void setRejectedConnections(int rejectedConnections){
		this.rejectedConnections = rejectedConnections;
	}

	/**
	 * 返回主从之间数据同步完全成功次数
	 *
	 * @return 主从之间数据同步完全成功次数
	 */
	public int getSyncFull(){
		return syncFull;
	}

	/**
	 * 设置主从之间数据同步完全成功次数
	 *
	 * @param syncFull
	 * 		主从之间数据同步完全成功次数
	 */
	public void setSyncFull(int syncFull){
		this.syncFull = syncFull;
	}

	/**
	 * 返回主从之间数据同步部分成功次数
	 *
	 * @return 主从之间数据同步部分成功次数
	 */
	public int getSyncPartialOk(){
		return syncPartialOk;
	}

	/**
	 * 设置主从之间数据同步部分成功次数
	 *
	 * @param syncPartialOk
	 * 		主从之间数据同步部分成功次数
	 */
	public void setSyncPartialOk(int syncPartialOk){
		this.syncPartialOk = syncPartialOk;
	}

	/**
	 * 返回主从之间数据同步部分失败次数
	 *
	 * @return 主从之间数据同步部分失败次数
	 */
	public int getSyncPartialErr(){
		return syncPartialErr;
	}

	/**
	 * 设置主从之间数据同步部分失败次数
	 *
	 * @param syncPartialErr
	 * 		主从之间数据同步部分失败次数
	 */
	public void setSyncPartialErr(int syncPartialErr){
		this.syncPartialErr = syncPartialErr;
	}

	/**
	 * 获取因为过期而被自动删除的数据库键数量
	 *
	 * @return 因为过期而被自动删除的数据库键数量
	 */
	public int getExpiredKeys(){
		return expiredKeys;
	}

	/**
	 * 设置因为过期而被自动删除的数据库键数量
	 *
	 * @param expiredKeys
	 * 		因为过期而被自动删除的数据库键数量
	 */
	public void setExpiredKeys(int expiredKeys){
		this.expiredKeys = expiredKeys;
	}

	/**
	 * 返回过期 key 占总 key 比率
	 *
	 * @return 过期 key 占总 key 比率
	 */
	public double getExpiredStalePerc(){
		return expiredStalePerc;
	}

	/**
	 * 设置过期 key 占总 key 比率
	 *
	 * @param expiredStalePerc
	 * 		过期 key 占总 key 比率
	 */
	public void setExpiredStalePerc(double expiredStalePerc){
		this.expiredStalePerc = expiredStalePerc;
	}

	/**
	 * 返回过期计数
	 *
	 * @return 过期计数
	 */
	public int getExpiredTimeCapReachedCount(){
		return expiredTimeCapReachedCount;
	}

	/**
	 * 设置过期计数
	 *
	 * @param expiredTimeCapReachedCount
	 * 		过期计数
	 */
	public void setExpiredTimeCapReachedCount(int expiredTimeCapReachedCount){
		this.expiredTimeCapReachedCount = expiredTimeCapReachedCount;
	}

	/**
	 * 获取因为最大内存容量限制而被驱逐（evict）的键数量
	 *
	 * @return 因为最大内存容量限制而被驱逐（evict）的键数量
	 */
	public int getEvictedKeys(){
		return evictedKeys;
	}

	/**
	 * 设置因为最大内存容量限制而被驱逐（evict）的键数量
	 *
	 * @param evictedKeys
	 * 		因为最大内存容量限制而被驱逐（evict）的键数量
	 */
	public void setEvictedKeys(int evictedKeys){
		this.evictedKeys = evictedKeys;
	}

	/**
	 * 获取查找数据库键成功的次数
	 *
	 * @return 查找数据库键成功的次数
	 */
	public int getKeyspaceHits(){
		return keyspaceHits;
	}

	/**
	 * 设置查找数据库键成功的次数
	 *
	 * @param keyspaceHits
	 * 		查找数据库键成功的次数
	 */
	public void setKeyspaceHits(int keyspaceHits){
		this.keyspaceHits = keyspaceHits;
	}

	/**
	 * 获取查找数据库键失败的次数
	 *
	 * @return 查找数据库键失败的次数
	 */
	public int getKeyspaceMisses(){
		return keyspaceMisses;
	}

	/**
	 * 设置查找数据库键失败的次数
	 *
	 * @param keyspaceMisses
	 * 		查找数据库键失败的次数
	 */
	public void setKeyspaceMisses(int keyspaceMisses){
		this.keyspaceMisses = keyspaceMisses;
	}

	/**
	 * 获取目前被订阅的频道数量
	 *
	 * @return 目前被订阅的频道数量
	 */
	public int getPubsubChannels(){
		return pubsubChannels;
	}

	/**
	 * 设置目前被订阅的频道数量
	 *
	 * @param pubsubChannels
	 * 		目前被订阅的频道数量
	 */
	public void setPubsubChannels(int pubsubChannels){
		this.pubsubChannels = pubsubChannels;
	}

	/**
	 * 获取目前被订阅的模式数量
	 *
	 * @return 目前被订阅的模式数量
	 */
	public int getPubsubPatterns(){
		return pubsubPatterns;
	}

	/**
	 * 设置目前被订阅的模式数量
	 *
	 * @param pubsubPatterns
	 * 		目前被订阅的模式数量
	 */
	public void setPubsubPatterns(int pubsubPatterns){
		this.pubsubPatterns = pubsubPatterns;
	}

	/**
	 * 获取 最近一次 fork() 操作耗费的毫秒数
	 *
	 * @return 最近一次 fork() 操作耗费的毫秒数
	 */
	public int getLatestForkUsec(){
		return latestForkUsec;
	}

	/**
	 * 设置 最近一次 fork() 操作耗费的毫秒数
	 *
	 * @param latestForkUsec
	 * 		最近一次 fork() 操作耗费的毫秒数
	 */
	public void setLatestForkUsec(int latestForkUsec){
		this.latestForkUsec = latestForkUsec;
	}

	public int getMigrateCachedSockets(){
		return migrateCachedSockets;
	}

	public void setMigrateCachedSockets(int migrateCachedSockets){
		this.migrateCachedSockets = migrateCachedSockets;
	}

	/**
	 * 返回从服务器中到期 key 数量
	 *
	 * @return 从服务器中到期 key 数量
	 */
	public int getSlaveExpiresTrackedKeys(){
		return slaveExpiresTrackedKeys;
	}

	/**
	 * 设置从服务器中到期 key 数量
	 *
	 * @param slaveExpiresTrackedKeys
	 * 		从服务器中到期 key 数量
	 */
	public void setSlaveExpiresTrackedKeys(int slaveExpiresTrackedKeys){
		this.slaveExpiresTrackedKeys = slaveExpiresTrackedKeys;
	}

	/**
	 * 返回主动垃圾碎片整理命中次数
	 *
	 * @return 主动垃圾碎片整理命中次数
	 */
	public int getActiveDefragHits(){
		return activeDefragHits;
	}

	/**
	 * 设置主动垃圾碎片整理命中次数
	 *
	 * @param activeDefragHits
	 * 		主动垃圾碎片整理命中次数
	 */
	public void setActiveDefragHits(int activeDefragHits){
		this.activeDefragHits = activeDefragHits;
	}

	/**
	 * 返回主动垃圾碎片整理未命中
	 *
	 * @return 主动垃圾碎片整理未命中
	 */
	public int getActiveDefragMisses(){
		return activeDefragMisses;
	}

	/**
	 * 设置主动垃圾碎片整理未命中
	 *
	 * @param activeDefragMisses
	 * 		主动垃圾碎片整理未命中
	 */
	public void setActiveDefragMisses(int activeDefragMisses){
		this.activeDefragMisses = activeDefragMisses;
	}

	/**
	 * 返回主动垃圾碎片整理 key 命中次数
	 *
	 * @return 主动垃圾碎片整理 key 命中次数
	 */
	public int getActiveDefragKeyHits(){
		return activeDefragKeyHits;
	}

	/**
	 * 设置主动垃圾碎片整理 key 命中次数
	 *
	 * @param activeDefragKeyHits
	 * 		主动垃圾碎片整理 key 命中次数
	 */
	public void setActiveDefragKeyHits(int activeDefragKeyHits){
		this.activeDefragKeyHits = activeDefragKeyHits;
	}

	/**
	 * 返回主动垃圾碎片整理 key 未命中次数
	 *
	 * @return 主动垃圾碎片整理 key 未命中次数
	 */
	public int getActiveDefragKeyMisses(){
		return activeDefragKeyMisses;
	}

	/**
	 * 设置主动垃圾碎片整理 key 未命中次数
	 *
	 * @param activeDefragKeyMisses
	 * 		主动垃圾碎片整理 key 未命中次数
	 */
	public void setActiveDefragKeyMisses(int activeDefragKeyMisses){
		this.activeDefragKeyMisses = activeDefragKeyMisses;
	}

	@Override
	public String toString(){
		return "Stats{" + "totalConnectionsReceived=" + totalConnectionsReceived + ", totalCommandsProcessed=" +
				totalCommandsProcessed + ", instantaneousOpsPerSec=" + instantaneousOpsPerSec + ", " +
				"totalNetInputBytes=" + totalNetInputBytes + ", totalNetOutputBytes=" + totalNetOutputBytes + ", " +
				"instantaneousInputKbps=" + instantaneousInputKbps + ", instantaneousOutputKbps=" +
				instantaneousOutputKbps + ", rejectedConnections=" + rejectedConnections + ", syncFull=" + syncFull +
				", syncPartialOk=" + syncPartialOk + ", syncPartialErr=" + syncPartialErr + ", expiredKeys=" +
				expiredKeys + ", expiredStalePerc=" + expiredStalePerc + ", expiredTimeCapReachedCount=" +
				expiredTimeCapReachedCount + ", evictedKeys=" + evictedKeys + ", keyspaceHits=" + keyspaceHits + ", "
				+ "keyspaceMisses=" + keyspaceMisses + ", pubsubChannels=" + pubsubChannels + ", pubsubPatterns=" +
				pubsubPatterns + ", latestForkUsec=" + latestForkUsec + ", migrateCachedSockets=" +
				migrateCachedSockets + ", slaveExpiresTrackedKeys=" + slaveExpiresTrackedKeys + ", activeDefragHits="
				+ activeDefragHits + ", activeDefragMisses=" + activeDefragMisses + ", activeDefragKeyHits=" +
				activeDefragKeyHits + ", activeDefragKeyMisses=" + activeDefragKeyMisses + '}';
	}

}
