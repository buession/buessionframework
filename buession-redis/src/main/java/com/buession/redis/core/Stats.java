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
 * | Copyright @ 2013-2019 Buession.com Inc.														       |
 * +-------------------------------------------------------------------------------------------------------+
 */
package com.buession.redis.core;

/**
 * 一般统计信息
 *
 * @author Yong.Teng
 */
public class Stats {

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
     *
     */
    private int totalNetInputBytes;

    /**
     *
     */
    private int totalNetOutputBytes;

    /**
     *
     */
    private double instantaneousInputKbps;

    /**
     *
     */
    private double instantaneousOutputKbps;

    /**
     * 因为最大客户端数量限制而被拒绝的连接请求数量
     */
    private int rejectedConnections;

    /**
     *
     */
    private int syncFull;

    /**
     *
     */
    private int syncPartialOk;

    /**
     *
     */
    private int syncPartialErr;

    /**
     * 因为过期而被自动删除的数据库键数量
     */
    private int expiredKeys;

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
     *         服务器已接受的连接请求数量
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
     *         服务器已执行的命令数量
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
     *         服务器每秒钟执行的命令数量
     */
    public void setInstantaneousOpsPerSec(int instantaneousOpsPerSec){
        this.instantaneousOpsPerSec = instantaneousOpsPerSec;
    }

    public int getTotalNetInputBytes(){
        return totalNetInputBytes;
    }

    public void setTotalNetInputBytes(int totalNetInputBytes){
        this.totalNetInputBytes = totalNetInputBytes;
    }

    public int getTotalNetOutputBytes(){
        return totalNetOutputBytes;
    }

    public void setTotalNetOutputBytes(int totalNetOutputBytes){
        this.totalNetOutputBytes = totalNetOutputBytes;
    }

    public double getInstantaneousInputKbps(){
        return instantaneousInputKbps;
    }

    public void setInstantaneousInputKbps(double instantaneousInputKbps){
        this.instantaneousInputKbps = instantaneousInputKbps;
    }

    public double getInstantaneousOutputKbps(){
        return instantaneousOutputKbps;
    }

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
     *         因为最大客户端数量限制而被拒绝的连接请求数量
     */
    public void setRejectedConnections(int rejectedConnections){
        this.rejectedConnections = rejectedConnections;
    }

    public int getSyncFull(){
        return syncFull;
    }

    public void setSyncFull(int syncFull){
        this.syncFull = syncFull;
    }

    public int getSyncPartialOk(){
        return syncPartialOk;
    }

    public void setSyncPartialOk(int syncPartialOk){
        this.syncPartialOk = syncPartialOk;
    }

    public int getSyncPartialErr(){
        return syncPartialErr;
    }

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
     *         因为过期而被自动删除的数据库键数量
     */
    public void setExpiredKeys(int expiredKeys){
        this.expiredKeys = expiredKeys;
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
     *         因为最大内存容量限制而被驱逐（evict）的键数量
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
     *         查找数据库键成功的次数
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
     *         查找数据库键失败的次数
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
     *         目前被订阅的频道数量
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
     *         目前被订阅的模式数量
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
     *         最近一次 fork() 操作耗费的毫秒数
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

    @Override
    public String toString(){
        return "Stats{" + "totalConnectionsReceived=" + totalConnectionsReceived + ", totalCommandsProcessed=" +
                totalCommandsProcessed + ", instantaneousOpsPerSec=" + instantaneousOpsPerSec + ", " +
                "totalNetInputBytes=" + totalNetInputBytes + ", totalNetOutputBytes=" + totalNetOutputBytes + ", " +
                "instantaneousInputKbps=" + instantaneousInputKbps + ", instantaneousOutputKbps=" +
                instantaneousOutputKbps + ", rejectedConnections=" + rejectedConnections + ", syncFull=" + syncFull +
                ", syncPartialOk=" + syncPartialOk + ", syncPartialErr=" + syncPartialErr + ", expiredKeys=" +
                expiredKeys + ", evictedKeys=" + evictedKeys + ", keyspaceHits=" + keyspaceHits + ", keyspaceMisses="
                + keyspaceMisses + ", pubsubChannels=" + pubsubChannels + ", pubsubPatterns=" + pubsubPatterns + ", "
                + "latestForkUsec=" + latestForkUsec + ", migrateCachedSockets=" + migrateCachedSockets + '}';
    }
}
