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
 * 已连接客户端的信息
 *
 * @author Yong.Teng
 */
public class Clients implements Serializable {

    private final static long serialVersionUID = 2525187017390996219L;

    /**
     * 已连接客户端的数量
     */
    private int connecteds;

    /**
     * 当前连接的客户端当中，最长的输出列表
     */
    private int longestOutputList;

    /**
     * 当前连接的客户端当中，最大输入缓存
     */
    private int biggestInputBuffer;

    /**
     * 正在等待阻塞命令的客户端的数量
     */
    private int blockeds;

    /**
     * 获取已连接客户端的数量
     *
     * @return 已连接客户端的数量
     */
    public int getConnecteds(){
        return connecteds;
    }

    /**
     * 设置已连接客户端的数量
     *
     * @param connecteds
     *         已连接客户端的数量
     */
    public void setConnecteds(int connecteds){
        this.connecteds = connecteds;
    }

    /**
     * 获取当前连接的客户端当中，最长的输出列表
     *
     * @return 当前连接的客户端当中，最长的输出列表
     */
    public int getLongestOutputList(){
        return longestOutputList;
    }

    /**
     * 设置当前连接的客户端当中，最长的输出列表
     *
     * @param longestOutputList
     *         当前连接的客户端当中，最长的输出列表
     */
    public void setLongestOutputList(int longestOutputList){
        this.longestOutputList = longestOutputList;
    }

    /**
     * 获取当前连接的客户端当中，最大输入缓存
     *
     * @return 当前连接的客户端当中，最大输入缓存
     */
    public int getBiggestInputBuffer(){
        return biggestInputBuffer;
    }

    /**
     * 设置当前连接的客户端当中，最大输入缓存
     *
     * @param biggestInputBuffer
     *         当前连接的客户端当中，最大输入缓存
     */
    public void setBiggestInputBuffer(int biggestInputBuffer){
        this.biggestInputBuffer = biggestInputBuffer;
    }

    /**
     * 获取正在等待阻塞命令的客户端的数量
     *
     * @return 正在等待阻塞命令的客户端的数量
     */
    public int getBlockeds(){
        return blockeds;
    }

    /**
     * 设置正在等待阻塞命令的客户端的数量
     *
     * @param blockeds
     *         正在等待阻塞命令的客户端的数量
     */
    public void setBlockeds(int blockeds){
        this.blockeds = blockeds;
    }

    @Override
    public String toString(){
        return "Clients{" + "connecteds=" + connecteds + ", longestOutputList=" + longestOutputList + ", " +
                "biggestInputBuffer=" + biggestInputBuffer + ", blockeds=" + blockeds + '}';
    }

}
