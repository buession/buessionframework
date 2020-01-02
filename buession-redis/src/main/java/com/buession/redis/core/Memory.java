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
 * Redis 服务器的内存信息
 *
 * @author Yong.Teng
 */
public class Memory {

    /**
     * 由 Redis 分配器分配的内存总量
     */
    private MemoryInfo usedMemory;

    /**
     * 从操作系统的角度，返回 Redis 已分配的内存总量
     */
    private MemoryInfo usedMemoryRss;

    /**
     * Redis 的内存消耗峰值
     */
    private MemoryInfo usedMemoryPeak;

    private MemoryInfo totalSystemMemory;

    /**
     * LUA 引擎所使用的内存大小
     */
    private MemoryInfo usedMemoryLua;

    private MemoryInfo maxMemory;

    private String maxMemoryPolicy;

    /**
     * used_memory_rss 和 used_memory 之间的比率
     */
    private String memFragmentationTatio;

    /**
     * Redis 在编译时指定的，Redis 所使用的内存分配器
     */
    private String memAllocator;

    /**
     * 获取由 Redis 分配器分配的内存总量
     *
     * @return 由 Redis 分配器分配的内存总量
     */
    public MemoryInfo getUsedMemory(){
        return usedMemory;
    }

    /**
     * 设置由 Redis 分配器分配的内存总量
     *
     * @param usedMemory
     *         由 Redis 分配器分配的内存总量
     */
    public void setUsedMemory(MemoryInfo usedMemory){
        this.usedMemory = usedMemory;
    }

    /**
     * 返回从操作系统的角度，返回 Redis 已分配的内存总量
     *
     * @return 从操作系统的角度，返回 Redis 已分配的内存总量
     */
    public MemoryInfo getUsedMemoryRss(){
        return usedMemoryRss;
    }

    /**
     * 设置从操作系统的角度，返回 Redis 已分配的内存总量
     *
     * @param usedMemoryRss
     *         从操作系统的角度，返回 Redis 已分配的内存总量
     */
    public void setUsedMemoryRss(MemoryInfo usedMemoryRss){
        this.usedMemoryRss = usedMemoryRss;
    }

    /**
     * 获取 Redis 的内存消耗峰值
     *
     * @return Redis 的内存消耗峰值
     */
    public MemoryInfo getUsedMemoryPeak(){
        return usedMemoryPeak;
    }

    /**
     * 设置 Redis 的内存消耗峰值
     *
     * @param usedMemoryPeak
     *         Redis 的内存消耗峰值
     */
    public void setUsedMemoryPeak(MemoryInfo usedMemoryPeak){
        this.usedMemoryPeak = usedMemoryPeak;
    }


    public MemoryInfo getTotalSystemMemory(){
        return totalSystemMemory;
    }

    public void setTotalSystemMemory(MemoryInfo totalSystemMemory){
        this.totalSystemMemory = totalSystemMemory;
    }

    /**
     * 获取 LUA 引擎所使用的内存大小
     *
     * @return LUA 引擎所使用的内存大小
     */
    public MemoryInfo getUsedMemoryLua(){
        return usedMemoryLua;
    }

    /**
     * 设置 LUA 引擎所使用的内存大小
     *
     * @param usedMemoryLua
     *         LUA 引擎所使用的内存大小
     */
    public void setUsedMemoryLua(MemoryInfo usedMemoryLua){
        this.usedMemoryLua = usedMemoryLua;
    }

    public MemoryInfo getMaxMemory(){
        return maxMemory;
    }

    public void setMaxMemory(MemoryInfo maxMemory){
        this.maxMemory = maxMemory;
    }

    public String getMaxMemoryPolicy(){
        return maxMemoryPolicy;
    }

    public void setMaxMemoryPolicy(String maxMemoryPolicy){
        this.maxMemoryPolicy = maxMemoryPolicy;
    }

    /**
     * 获取 used_memory_rss 和 used_memory 之间的比率
     *
     * @return used_memory_rss 和 used_memory 之间的比率
     */
    public String getMemFragmentationTatio(){
        return memFragmentationTatio;
    }

    /**
     * 设置 used_memory_rss 和 used_memory 之间的比率
     *
     * @param memFragmentationTatio
     *         used_memory_rss 和 used_memory 之间的比率
     */
    public void setMemFragmentationTatio(String memFragmentationTatio){
        this.memFragmentationTatio = memFragmentationTatio;
    }

    /**
     * 获取 Redis 在编译时指定的，Redis 所使用的内存分配器
     *
     * @return Redis 在编译时指定的，Redis 所使用的内存分配器
     */
    public String getMemAllocator(){
        return memAllocator;
    }

    /**
     * 设置 Redis 在编译时指定的，Redis 所使用的内存分配器
     *
     * @param memAllocator
     *         Redis 在编译时指定的，Redis 所使用的内存分配器
     */
    public void setMemAllocator(String memAllocator){
        this.memAllocator = memAllocator;
    }

    @Override
    public String toString(){
        return "Memory{" + "usedMemory=" + usedMemory + ", usedMemoryRss=" + usedMemoryRss + ", usedMemoryPeak=" +
                usedMemoryPeak + ", totalSystemMemory=" + totalSystemMemory + ", usedMemoryLua=" + usedMemoryLua + "," +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + " " +
                "maxMemory=" + maxMemory + ", " + "" + "" + "" + "" + "" + "" + "" + "" + "maxMemoryPolicy='" +
                maxMemoryPolicy + '\'' + ", " + "memFragmentationTatio='" + memFragmentationTatio + '\'' + ", " +
                "memAllocator='" + memAllocator + '\'' + '}';
    }

    public final static class MemoryInfo {

        /**
         * 内存大小
         */
        private long value;

        /**
         * 可读格式内存大小
         */
        private String human;

        /**
         * 默认构造函数
         */
        public MemoryInfo(){

        }

        /**
         * 构造函数
         *
         * @param value
         *         内存大小
         * @param human
         *         可读格式内存大小
         */
        public MemoryInfo(long value, String human){
            this.value = value;
            this.human = human;
        }

        /**
         * 返回内存大小
         *
         * @return 内存大小
         */
        public long getValue(){
            return value;
        }

        /**
         * 设置内存大小
         *
         * @param value
         *         内存大小
         */
        public void setValue(long value){
            this.value = value;
        }

        /**
         * 返回可读格式内存大小
         *
         * @return 可读格式内存大小
         */
        public String getHuman(){
            return human;
        }

        /**
         * 设置可读格式内存大小
         *
         * @param human
         *         可读格式内存大小
         */
        public void setHuman(String human){
            this.human = human;
        }

        @Override
        public String toString(){
            return "MemoryInfo{" + "value=" + value + ", human='" + human + '\'' + '}';
        }
    }

}
