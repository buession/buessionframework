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
 * 主从复制信息
 *
 * @author Yong.Teng
 */
public class Replication {

    /**
     * Redis 服务器角色
     */
    private Role role;

    /**
     * 主服务器信息
     */
    private Master master;

    /**
     * 主从服务器连接断开了多少秒
     */
    private int masterLinkDownSinceSeconds;

    /**
     * 已连接的从服务器数量
     */
    private int connectedSlaves;

    /**
     * 当前 master 记录的复制偏移量
     */
    private int masterReplOffset;

    private ReplBacklog replBacklog;

    /**
     * 获取 Redis 服务器角色
     *
     * @return Redis 服务器角色
     */
    public Role getRole(){
        return role;
    }

    /**
     * 设置 Redis 服务器角色
     *
     * @param role
     *         Redis 服务器角色
     */
    public void setRole(Role role){
        this.role = role;
    }

    /**
     * 获取主服务器信息
     *
     * @return 主服务器信息
     */
    public Master getMaster(){
        return master;
    }

    /**
     * 设置主服务器信息
     *
     * @param master
     *         主服务器信息
     */
    public void setMaster(Master master){
        this.master = master;
    }

    /**
     * 获取主从服务器连接断开了多少秒
     *
     * @return 主从服务器连接断开了多少秒，如果主从服务器之间的连接处于断线状态
     */
    public int getMasterLinkDownSinceSeconds(){
        return masterLinkDownSinceSeconds;
    }

    /**
     * 设置主从服务器连接断开了多少秒
     *
     * @param masterLinkDownSinceSeconds
     *         主从服务器连接断开了多少秒
     */
    public void setMasterLinkDownSinceSeconds(int masterLinkDownSinceSeconds){
        this.masterLinkDownSinceSeconds = masterLinkDownSinceSeconds;
    }

    /**
     * 获取已连接的从服务器数量
     *
     * @return 已连接的从服务器数量
     */
    public int getConnectedSlaves(){
        return connectedSlaves;
    }

    /**
     * 设置已连接的从服务器数量
     *
     * @param connectedSlaves
     *         已连接的从服务器数量
     */
    public void setConnectedSlaves(int connectedSlaves){
        this.connectedSlaves = connectedSlaves;
    }

    /**
     * 获取当前 master 记录的复制偏移量
     *
     * @return 当前 master 记录的复制偏移量
     */
    public int getMasterReplOffset(){
        return masterReplOffset;
    }

    /**
     * 设置当前 master 记录的复制偏移量
     *
     * @param masterReplOffset
     *         当前 master 记录的复制偏移量
     */
    public void setMasterReplOffset(int masterReplOffset){
        this.masterReplOffset = masterReplOffset;
    }

    public ReplBacklog getReplBacklog(){
        return replBacklog;
    }

    public void setReplBacklog(ReplBacklog replBacklog){
        this.replBacklog = replBacklog;
    }

    @Override
    public String toString(){
        return "Replication{" + "role=" + role + ", master=" + master + ", masterLinkDownSinceSeconds=" +
                masterLinkDownSinceSeconds + ", connectedSlaves=" + connectedSlaves + ", masterReplOffset=" +
                masterReplOffset + ", replBacklog=" + replBacklog + '}';
    }

    public final static class Master {

        /**
         * 主服务器的主机地址
         */
        private String host;

        /**
         * 主服务器端口
         */
        private int port;

        /**
         * 复制连接当前的状态，up 表示连接正常，down 表示连接断开
         */
        private LinkSstatus linkSstatus;

        /**
         * 距离最近一次与主服务器进行通信已经过去了多少秒钟
         */
        private int lastIoSecondsAgo;

        /**
         * 主服务器是否正在与这个从服务器进行同步
         */
        private boolean syncInProgress;

        /**
         * 默认构造函数
         */
        public Master(){

        }

        /**
         * 构造函数
         *
         * @param host
         *         主服务器的主机地址
         * @param port
         *         主服务器端口
         * @param linkSstatus
         *         复制连接当前的状态
         * @param lastIoSecondsAgo
         *         距离最近一次与主服务器进行通信已经过去了多少秒钟
         * @param syncInProgress
         *         主服务器是否正在与这个从服务器进行同步
         */
        public Master(String host, int port, LinkSstatus linkSstatus, int lastIoSecondsAgo, boolean syncInProgress){
            this.host = host;
            this.port = port;
            this.linkSstatus = linkSstatus;
            this.lastIoSecondsAgo = lastIoSecondsAgo;
            this.syncInProgress = syncInProgress;
        }

        /**
         * 获取主服务器的主机地址
         *
         * @return 主服务器的主机地址
         */
        public String getHost(){
            return host;
        }

        /**
         * 设置主服务器的主机地址
         *
         * @param host
         *         主服务器的主机地址
         */
        public void setHost(String host){
            this.host = host;
        }

        /**
         * 获取主服务器端口
         *
         * @return 主服务器端口
         */
        public int getPort(){
            return port;
        }

        /**
         * 设置主服务器端口
         *
         * @param port
         *         主服务器端口
         */
        public void setPort(int port){
            this.port = port;
        }

        /**
         * 获取复制连接当前的状态，up 表示连接正常，down 表示连接断开
         *
         * @return 复制连接当前的状态，up 表示连接正常，down 表示连接断开
         */
        public LinkSstatus getLinkSstatus(){
            return linkSstatus;
        }

        /**
         * 设置复制连接当前的状态
         *
         * @param linkSstatus
         *         复制连接当前的状态
         */
        public void setLinkSstatus(LinkSstatus linkSstatus){
            this.linkSstatus = linkSstatus;
        }

        /**
         * 获取距离最近一次与主服务器进行通信已经过去了多少秒钟
         *
         * @return 距离最近一次与主服务器进行通信已经过去了多少秒钟
         */
        public int getLastIoSecondsAgo(){
            return lastIoSecondsAgo;
        }

        /**
         * 设置距离最近一次与主服务器进行通信已经过去了多少秒钟
         *
         * @param lastIoSecondsAgo
         *         距离最近一次与主服务器进行通信已经过去了多少秒钟
         */
        public void setLastIoSecondsAgo(int lastIoSecondsAgo){
            this.lastIoSecondsAgo = lastIoSecondsAgo;
        }

        /**
         * 获取主服务器是否正在与这个从服务器进行同步
         *
         * @return 主服务器是否正在与这个从服务器进行同步
         */
        public boolean isSyncInProgress(){
            return getSyncInProgress();
        }

        /**
         * 获取主服务器是否正在与这个从服务器进行同步
         *
         * @return 主服务器是否正在与这个从服务器进行同步
         */
        public boolean getSyncInProgress(){
            return syncInProgress;
        }

        /**
         * 设置主服务器是否正在与这个从服务器进行同步
         *
         * @param syncInProgress
         *         主服务器是否正在与这个从服务器进行同步
         */
        public void setSyncInProgress(boolean syncInProgress){
            this.syncInProgress = syncInProgress;
        }

        @Override
        public String toString(){
            return "Master{" + "host='" + host + '\'' + ", port=" + port + ", linkSstatus=" + linkSstatus + ", " +
                    "lastIoSecondsAgo=" + lastIoSecondsAgo + ", syncInProgress=" + syncInProgress + '}';
        }

    }

    public final static class ReplBacklog {

        private int active;

        private int size;

        private int firstByteOffset;

        private int histlen;

        public ReplBacklog(){

        }

        public ReplBacklog(int active, int size, int firstByteOffset, int histlen){
            this.active = active;
            this.size = size;
            this.firstByteOffset = firstByteOffset;
            this.histlen = histlen;
        }

        public int getActive(){
            return active;
        }

        public void setActive(int active){
            this.active = active;
        }

        public int getSize(){
            return size;
        }

        public void setSize(int size){
            this.size = size;
        }

        public int getFirstByteOffset(){
            return firstByteOffset;
        }

        public void setFirstByteOffset(int firstByteOffset){
            this.firstByteOffset = firstByteOffset;
        }

        public int getHistlen(){
            return histlen;
        }

        public void setHistlen(int histlen){
            this.histlen = histlen;
        }

        @Override
        public String toString(){
            return "ReplBacklog{" + "active=" + active + ", size=" + size + ", firstByteOffset=" + firstByteOffset +
                    ", histlen=" + histlen + '}';
        }
    }

    public enum LinkSstatus {

        UP("up"),

        DOWN("down");

        private String value;

        LinkSstatus(String value){
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }

}
